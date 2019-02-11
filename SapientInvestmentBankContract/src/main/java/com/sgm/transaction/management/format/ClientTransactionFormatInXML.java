package com.sgm.transaction.management.format;

import java.io.File;
import java.io.IOException;

import com.sgm.transaction.management.execption.ClientTransactionFormatIsNotValidException;
import com.sgm.transaction.management.execption.ClientTransactionGenerateInvoiceException;

public class ClientTransactionFormatInXML implements ClientTransactionFormatInf {

	@Override
	public void processClientTransaction(File file) throws ClientTransactionFormatIsNotValidException, IOException,InterruptedException {
		throw new ClientTransactionFormatIsNotValidException("Client Transaction EXCEL format is not implemented yet.");
	}

	@Override
	public void generateInvoicing() throws ClientTransactionGenerateInvoiceException {
		throw new ClientTransactionGenerateInvoiceException("Exception occurred while generating invioce for EXCEL format.");
	}

}
