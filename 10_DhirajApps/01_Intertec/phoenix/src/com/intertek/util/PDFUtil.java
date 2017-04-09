package com.intertek.util;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;

public class PDFUtil{
	public static List<String> getMissingFiles(String rootFolder, String[] fileNames){
		ArrayList<String> missing=new ArrayList<String>();
		for(int i=0; i<fileNames.length; i++){
			File f=new File(rootFolder+fileNames[i]);
			
			if(f.isDirectory() || !f.exists()){
				missing.add(fileNames[i]);
				System.out.println(f.getAbsolutePath());
			}
			else{
				System.out.println("found:"+f.getAbsolutePath());
			}
		}
		return missing;
	}
	
	public static void concatPDFs(String rootFolder, String[] fileNames, OutputStream out)throws Exception {
		PdfCopyFields copy = new PdfCopyFields(out);
		for(int i=0; i<fileNames.length; i++){
			PdfReader reader=new PdfReader(rootFolder+fileNames[i]);
			copy.addDocument(reader);
			reader.close();
		}
		copy.close();
	}
}
