package com.sgm.transaction.management.format;

import java.io.File;
import java.io.IOException;

import com.sgm.transaction.management.execption.ClientTransactionFormatIsNotValidException;
import com.sgm.transaction.management.execption.ClientTransactionGenerateInvoiceException;


public interface ClientTransactionFormatInf {

	public void processClientTransaction(File file) throws ClientTransactionFormatIsNotValidException, IOException,InterruptedException;

	public void generateInvoicing() throws ClientTransactionGenerateInvoiceException;

}
