package com.sgm.transaction.management.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.sgm.transaction.management.execption.ClientTransactionFormatIsNotValidException;
import com.sgm.transaction.management.execption.ClientTransactionGenerateInvoiceException;
import com.sgm.transaction.management.format.ClientTransactionFormatFactory;
import com.sgm.transaction.management.format.ClientTransactionFormatInf;

/**
*This is an entry point to run Transaction management assignment.
*
* @author Aman Bansal
* @version   1.0
**/
public class ClientTransactionManager {

	public static void main(String[] args) throws ClientTransactionFormatIsNotValidException, IOException, ClientTransactionGenerateInvoiceException,InterruptedException {
		String fName = "SampleData.csv";
		File f = new File(new File("").getAbsolutePath(), "src/main/resources/" + "SampleData.csv");
		if(f.exists()){
				ClientTransactionFormatInf fileFormatObject = ClientTransactionFormatFactory.getFileFormatObject(fName.substring(fName.lastIndexOf('.') + 1));
				fileFormatObject.processClientTransaction(f);
				fileFormatObject.generateInvoicing();
		}
		else{
			throw new FileNotFoundException();
		}
	}
}
