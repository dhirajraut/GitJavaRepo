package com.intertek.export.template;

import java.util.ArrayList;
import java.util.List;

import com.intertek.domain.BankAccountSearchResult;

public class BankAccountTemplate extends CSVTemplate{
	List<BankAccountSearchResult> rows;
	int currentIndex=0;
	
	public BankAccountTemplate(List<BankAccountSearchResult> rows){
		this.rows=rows;
	}
	
	public String getName(){
		return "bank_account.csv";
	}
	
	public List<ExportColumn> getHeader(){
		List<ExportColumn> header=new ArrayList<ExportColumn>();
		header.add(new ExportColumn("Primary", "primary"));
		header.add(new ExportColumn("Bank Name", "bankDesc"));
		header.add(new ExportColumn("BU", "BU"));
		header.add(new ExportColumn("Currency Code", "currencyCode"));
		header.add(new ExportColumn("Bank Code", "bankCode"));
		header.add(new ExportColumn("Bank Account Code", "bankAccountCode"));
		header.add(new ExportColumn("Bank Account Desc", "bankAccountDesc"));
		header.add(new ExportColumn("Bank Id Nbr", "bankIdNbr"));
		header.add(new ExportColumn("BI", "BI"));
		return header;
	}
	
	public boolean hasMoreRow(){
		return rows!=null && currentIndex<rows.size();
	}
	
	public BankAccountSearchResult getNextRow(){
		BankAccountSearchResult obj=rows.get(currentIndex++);
		return obj;
	}
}
