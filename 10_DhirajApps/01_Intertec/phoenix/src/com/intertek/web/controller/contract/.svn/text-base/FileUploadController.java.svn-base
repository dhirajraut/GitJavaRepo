package com.intertek.web.controller.contract;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.FileUploadBean;
import com.intertek.util.DateUtil;

public class FileUploadController extends SimpleFormController {


	 public FileUploadController() {
	 super();
	 setCommandClass(FileUploadBean.class);
	 setSessionForm(true);
     }

    protected ModelAndView onSubmit(
        HttpServletRequest request,
        HttpServletResponse response,
        Object command,
        BindException errors) throws ServletException, IOException {

         // cast the bean
        FileUploadBean bean = (FileUploadBean) command;
        String path = bean.getFilepath();
        if(path== null){
			//PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("filepath");
			PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("config");
			if(pRB == null)
				System.out.println("pRB is null");
			if(pRB != null)
			{
				path = pRB.getString("contractfilepath");
				System.out.println("path value :"+path);
			}
        }
        MultipartFile file = bean.getFile();
        try{
	        if (file == null) {
			System.out.println("file is null");
	        }
			else
			{
				String dateFolder = DateUtil.formatDate(new Date(), "yyyyMMdd"); 
		        boolean exists = (new File(path + dateFolder +  file.getOriginalFilename())).exists();
		        System.out.println(" file exists"+exists);
		        if(exists){
		        	request.getSession().setAttribute("fileUploadStatus", "File name exists");
		        	errors.reject("upload.attach.contract.error", new Object[] {"File name exists!"+ file.getOriginalFilename()}, null);
					return showForm(request, response, errors);
		        }else{
		        	
		            File f = new File(path.concat(dateFolder));
		            if(!f.exists())
		          	  f.mkdir();
		        	
		        	File xferFile = new File(path + dateFolder + "/" + file.getOriginalFilename());
		        	file.transferTo(xferFile);
		        }
			}
	        request.setAttribute("success", "true");
			return showForm(request, response, errors);
		 }
		 catch(Exception e)
		{
			 			 		System.out.println("got exception :"+e.toString());

		}

		return new ModelAndView();
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
        throws ServletException {
        // to actually be able to convert Multipart instance to byte[]
        // we have to register a custom editor

        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());

        // now Spring knows how to handle multipart object and convert them
	

    }
    /**
     * Specify custom behaviour on binding to extract the filename from the file uploaded
     * in the multipart request.
     */
    protected void onBind(HttpServletRequest req, Object command, BindException errors) {
        // Get the filename from the request.

        FileUploadBean uplBean = (FileUploadBean) command;

        MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) req;

        MultipartFile multipart = multiReq.getFile("data");
		if(multipart != null)
		{
			uplBean.setFileName(multipart.getOriginalFilename());
		}

    }

  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
	  String inputFieldId = request.getParameter("inputFieldId");
	  String formName = request.getParameter("formName");

	  FileUploadBean fileUploadBean = new FileUploadBean();
  	fileUploadBean.setInputFieldId(inputFieldId);
  	fileUploadBean.setFormName(formName);

	  return fileUploadBean;
  }
}
