package com.sgm.transaction.management.format;

import com.sgm.transaction.management.model.ClientTransactionService;

public class ClientTransactionFormatFactory {

	public static ClientTransactionFormatInf getFileFormatObject(String inputFileFormat){
		ClientTransactionFormatInf  clientTransactionFormatInf= null;
		if (inputFileFormat.equalsIgnoreCase("csv")) {
			clientTransactionFormatInf = new ClientTransactionFormatInCsv();
			((ClientTransactionFormatInCsv)clientTransactionFormatInf).setClientTransactionUtility(new ClientTransactionService());
		}
		else if (inputFileFormat.equalsIgnoreCase("excel")) {

		}
		else if (inputFileFormat.equalsIgnoreCase("xml")) {

		}
		return clientTransactionFormatInf;
	}
}
