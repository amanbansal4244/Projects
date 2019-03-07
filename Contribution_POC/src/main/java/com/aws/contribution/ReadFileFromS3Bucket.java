package com.aws.contribution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import com.aman.lambda.db.HelperCSV;
import com.aman.lambda.pojo.parameter.Report;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.BatchWriteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.TableWriteItems;
import com.amazonaws.services.dynamodbv2.model.WriteRequest;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaAsyncClient;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.invoke.LambdaFunction;
import com.amazonaws.services.lambda.invoke.LambdaSerializationException;
import com.amazonaws.services.lambda.model.InvocationType;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.google.common.collect.Lists;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import au.com.bytecode.opencsv.CSVReader;

/*
 * If you do not want to use POJOs or if Lambda's serialization approach does not meet your needs,
 *  you can use the byte stream implementation. In this case, you can use the InputStream and OutputStream
 *   as the input and output types for the handler. An example handler function is shown:
 *   	public void handler(InputStream inputStream, OutputStream outputStream, Context context) throws IOException{
 *   	...
 *   	}
 *   
 *   Note that in this case the handler function uses parameters for both the request and response streams.
 *   
 *   The following is a Lambda function example that implements the handler that uses InputStream and OutputStream types 
 *   for the input and output parameters.
 *   
 *   Note: The input payload must be valid JSON but the output stream does not carry such a restriction. Any bytes are supported.
 */
public class ReadFileFromS3Bucket implements RequestStreamHandler {
	private static final AmazonS3 S3_CLIENT = AmazonS3ClientBuilder.defaultClient();
	AWSLambda awsLambda = AWSLambdaClientBuilder.standard().withRegion(Regions.US_EAST_1)
			/* .withCredentials( new AWSStaticCredentialsProvider( awsCreds ) ) */.build();

	static int counter = 1;
	
	/** Provide the AWS region which your DynamoDB table is hosted. */
	private static final Region AWS_REGION = Region.getRegion(Regions.US_EAST_1);
	private static final String clientRegion = "us-east-1";
	private static AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.standard().build();
	private static DynamoDB dynamoDB = new DynamoDB(dynamoDBClient);
	/** The DynamoDB table name. */
	private static final String DYNAMO_TABLE_NAME = "aman";
	

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
		JSONObject JSONObjectResult = null;
		String uploadedFileName = "";
		String s3BucketName = "";
		LambdaLogger logger = context.getLogger();
		try {
			JSONObjectResult = getJSONInputStream(inputStream);
			JSONObject jsonObject = JSONObjectResult.getJSONObject("S3BucketAndFileDetails");
			uploadedFileName = jsonObject.getString("uploadedFileName");
			s3BucketName = jsonObject.getString("s3BucketName");
			System.out.println("S3 Event Receivedddddd in read file class: " + uploadedFileName + "/" + s3BucketName);
		} catch (JSONException e) {
			System.out.println("JSONException occured:" + e);
			e.printStackTrace();
		}

		Report statusReport = new Report();
		long startTime = System.currentTimeMillis();

		S3Object s3Object = S3_CLIENT.getObject(new GetObjectRequest(s3BucketName, uploadedFileName));

		statusReport.setFileSize(s3Object.getObjectMetadata().getContentLength());
		System.out.println("Content Type:" + s3Object.getObjectContent());

		List<Item> itemList = new ArrayList<Item>();
		try {
			itemList = readFileFromS3(s3Object);
			saveItemsIntoDynamoDB(itemList, logger);
		} catch (IOException io) {
			System.out.println("IOException occurred:" + io);
			io.printStackTrace();
		} catch (java.text.ParseException e) {
			System.out.println("java.text.ParseException occurred:" + e);
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("ParseException:" + e);
			e.printStackTrace();
		}

		System.out.println("Load finish in " + String.valueOf(System.currentTimeMillis() - startTime) + "ms");
		logger.log("Load finish in " + String.valueOf(System.currentTimeMillis() - startTime) + "ms");

		
	//	buildInvokeRequestAndCallOtherLambda("SaveItemsIntoDynamoDB" ,itemList);
		
		/** SaveItemsIntoDynamoDB -> lambda name which saves data into Dynamo DB **/
		/*OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		JSONObject json = new JSONObject();
		String key = "key";
		for (Item item : itemList) {
			System.out.println("in for loop : iterating list:" + item);
			// writer.write(item.toString());
			try {
				json.put(key + counter, item);
				writer.write(json.toString());
				// outputStream.write(item.toString().getBytes());
				counter++;
				// json.put("input1",item );
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println("json string:" +mapper.writer().writeValueAsString(item));
			System.out.println("json input :" + json);
			// writer.write(mapper.writer().writeValueAsString(item));

			// outputStream.write(item.getb);
		}

		writer.close();*/


		s3Object.close();

		statusReport.setStatus(true);

		statusReport.setExecutiongTime(System.currentTimeMillis() - startTime);

		// return itemList;

	}

	private JSONObject getJSONInputStream(InputStream inputStream) throws IOException, JSONException {
		String input = IOUtils.toString(inputStream);
		StringBuilder responseStrBuilder = new StringBuilder();
		// StringReader input2 = new StringReader("{" + input1 + "}");
		/*
		 * Java StringReader class is a character stream with string as a source. It
		 * takes an input string and changes it into character stream. It inherits
		 * Reader class. In StringReader class, system resources like network sockets
		 * and files are not used, therefore closing the StringReader is not necessary.
		 * 
		 * If we don't use StringReader and directly try to 'InputStream' then we will
		 * get exception.
		 * "JSONException occured:org.json.JSONException: A JSONObject text must begin with '{' at character 0"
		 * or may be different exception.
		 */
		StringReader input2 = new StringReader(input);
		System.out.println("input1:" + input);
		int k = 0;
		while ((k = input2.read()) != -1) {
			responseStrBuilder.append((char) k);

		}
		System.out.println("responseStrBuilder:" + responseStrBuilder);
		JSONObject result = null;
		try {
			result = new JSONObject(responseStrBuilder.toString());
			System.out.println("result:" + result);
		} catch (JSONException e) {
			System.out.println("JSONException:" + e);
			e.printStackTrace();
		}
		return result;
	}

	private static List<Item> readFileFromS3(S3Object s3Object)
			throws IOException, ParseException, java.text.ParseException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(s3Object.getObjectContent()));
		CSVReader csvReader = new CSVReader(reader);
		List<Item> itemList = new ArrayList<Item>();
		String[] nextLine;
		HelperCSV helper = new HelperCSV();
		System.out.println("I am inside DynamoDB-Table : csvReader ------>>" + csvReader.readNext());
		while ((nextLine = csvReader.readNext()) != null) {
			Item newItem = helper.parseIt(nextLine);
			itemList.add(newItem);
			System.out.println("I am inside ClouldWatch Console------>>" + nextLine);
		}
		System.out.println("itemList------>>" + itemList);
		csvReader.close();
		reader.close();
		return itemList;
	}

	private void buildInvokeRequestAndCallOtherLambda(String functionName, List<Item> itemListInput) {
		ObjectMapper mapper = new ObjectMapper();
		AWSLambdaAsyncClient client = new AWSLambdaAsyncClient();
		client.withRegion(Regions.US_EAST_1);
		
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		
		for (Item item : itemListInput) {
			InvokeRequest invokeRequest = new InvokeRequest();
			invokeRequest.setFunctionName(functionName);
			/**
			 * Event - Invoke the function asynchronously. Send events that fail multiple
			 * times to the function's dead-letter queue (if it's configured). The API
			 * response only includes a status code.t
			 **/
			invokeRequest.setInvocationType(InvocationType.Event);
			// invokeRequest.setLogType(annotation.logType());
			if (item != null) {
				try {
					/** The JSON that you want to provide to your Lambda function as input. **/
					//String payload = mapper.writer().writeValueAsString(item);
					invokeRequest.setPayload(mapper.writeValueAsString(item.toString()));
					InvokeResult invoke = awsLambda.invoke(invokeRequest);
					System.out.println("Result invoking " + functionName + " " + invoke);
				} catch (Exception ex) {
					throw new LambdaSerializationException("Failed to serialize request object to JSON", ex);
				}
			}
		}
	}
	
	private static void saveItemsIntoDynamoDB(List<Item> itemList, LambdaLogger logger) {
		//AmazonDynamoDB dynamoDBClient = new AmazonDynamoDBClient();
		//dynamoDBClient.setRegion(AWS_REGION);
		//DynamoDB dynamoDB = new DynamoDB(dynamoDBClient);
		    
		
		TableWriteItems dataTableWriteItems = new TableWriteItems(DYNAMO_TABLE_NAME);
		/*
		 * Item is nothing, it is record in DyanmoDB.
		 */
		for (List<Item> partition : Lists.partition(itemList, 25)) {
			dataTableWriteItems.withItemsToPut(partition);
			BatchWriteItemOutcome outcome = dynamoDB.batchWriteItem(dataTableWriteItems);
			// Thread.sleep(1000);
			// logger.log("Insert data after 1 milisecond");
			// logger.log("I am inside for loop");
			 System.out.println("I am inside for loop");
			do {
				// Check for unprocessed keys which could happen if you exceed provisioned throughput
				Map<String, List<WriteRequest>> unprocessedItems = outcome.getUnprocessedItems();
				if (outcome.getUnprocessedItems().size() > 0) {
					//logger.log("Retrieving the unprocessed " +String.valueOf(outcome.getUnprocessedItems().size()) + " items."+"i=====");
					System.out.println("Retrieving the unprocessed " +String.valueOf(outcome.getUnprocessedItems().size()) + " items."+"i=====");
					outcome = dynamoDB.batchWriteItemUnprocessed(unprocessedItems);
				}
			} while (outcome.getUnprocessedItems().size() > 0);
		}
	}
}
