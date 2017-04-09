package com.intertek.phoenix.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.phoenix.web.controller.job.JobOrderNoteForm;

public class CEJobOrderNoteValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return JobOrderNoteForm.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
  JobOrderNoteForm jobOrderNoteForm = (JobOrderNoteForm)obj;
  if(jobOrderNoteForm==null)return; 
  if("save".equals(jobOrderNoteForm.getRequestAction()))
  {
  if(jobOrderNoteForm.getNoteSummary()==null || ("").equals(jobOrderNoteForm.getNoteSummary())){
  errors.reject("note.summary.null");
  }
  
  if(jobOrderNoteForm.getNoteDetail()==null || ("").equals(jobOrderNoteForm.getNoteDetail())){
	  errors.reject("note.detail.null");
      }
  }
}
}
    