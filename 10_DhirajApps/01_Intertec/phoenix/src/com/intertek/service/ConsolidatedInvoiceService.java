package com.intertek.service;

import java.util.List;

import com.intertek.domain.ConsolidatedInvoiceSearch;
import com.intertek.domain.InvoiceSearch;
import com.intertek.entity.ConsolidatedInvoice;

public interface ConsolidatedInvoiceService
{
  ConsolidatedInvoice saveConsolidatedInvoice(ConsolidatedInvoice consolidatedInvoice);
  //InvoiceSearch searchInvoice(InvoiceSearch invoiceSearch);
  InvoiceSearch searchInvoice(InvoiceSearch invoiceSearch,String sortFlag,String sortOrderFlag);
  void searchConsolidatedInvoice(ConsolidatedInvoiceSearch consolidatedInvoiceSearch,String sortFlag);
  ConsolidatedInvoice getConsolidatedInvoiceByInvoiceNumberBuName(String conslInvNumber, String buName);
  List getAllConsolidatedInvoices();
  void generateInvoicePDF(String dirName, String jasperDirName, String consoleInvoiceNo, String buName, String custCode, int locationNumber);
  List getJobContractInvoiceByConsol(String consolInvoiceNo, String consolBuName, String orderBy);
  List getConsolidatedInvoicesByUserName(String name);
  void generateConsolInvoice(String consolInvoiceNo, String buName);
  ConsolidatedInvoice createConsolidatedInvoice(ConsolidatedInvoice consolidatedInvoice);
  public void setInvoiceFileCountable(long id, boolean countable);
}

