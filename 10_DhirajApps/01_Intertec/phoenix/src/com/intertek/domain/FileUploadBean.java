package com.intertek.domain;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadBean {

    private MultipartFile file;
	private String fileName;
	private String uploadedFileName;
	private String inputFieldId;
	private String formName;
	private String filepath;


    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormName() {
        return formName;
    }
    public void setUploadedFileName(String uploadedFileName) {
        this.uploadedFileName = uploadedFileName;
    }

    public String getUploadedFileName() {
        return uploadedFileName;
    }

  public String getInputFieldId()
  {
		return inputFieldId;
	}

	public void setInputFieldId(String inputFieldId)
	{
		this.inputFieldId = inputFieldId;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
}