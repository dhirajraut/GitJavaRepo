package com.intertek.web.validators;

import java.util.Date;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.UomNote;
import com.intertek.domain.RBExt;
import com.intertek.domain.UpdateUomNotes;
import com.intertek.entity.RB;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.web.util.ValidationUtil;

public class UpdateUomNotesValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return UpdateUomNotes.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    UpdateUomNotes updateUomNotes = (UpdateUomNotes)obj;

    if(updateUomNotes == null) return;

    boolean hasError = false;

    List uomNoteList = updateUomNotes.getUomNoteList();
    if(uomNoteList != null)
    {
      for(int i=0; i<uomNoteList.size(); i++)
      {
        UomNote uomNote = (UomNote)uomNoteList.get(i);

        RBExt rbExt = uomNote.getRbExt();
        if(rbExt != null)
        {
          RB rb = rbExt.getNotesRb();
          if(rb != null)
          {
            Date beginDate = rb.getRbId().getBeginDate();
            Date endDate = rb.getRbId().getEndDate();

            String rbValue = rb.getRbValue();
            if((rbValue == null) || "".equals(rbValue.trim()))
            {
              errors.rejectValue("uomNoteList[" + i + "].rbExt.notesRb.rbValue", "not.blank", "");
              hasError = true;
            }

            if(beginDate == null)
            {
              errors.rejectValue("uomNoteList[" + i + "].rbExt.notesRb.rbId.beginDate", "not.blank", "");
              hasError = true;
            }
            else if(endDate == null)
            {
              errors.rejectValue("uomNoteList[" + i + "].rbExt.notesRb.rbId.endDate", "not.blank", "");
              hasError = true;
            }
            else
            {
              int dateResult = DateUtil.compareToInDate(beginDate, endDate);
              if(dateResult > 0)
              {
                errors.rejectValue("uomNoteList[" + i + "].rbExt.notesRb.rbId.beginDate", "invalid.date", "");
                hasError = true;
              }
            }
          }
        }
      }
    }

    if(hasError) errors.reject("edit.contract.update.uom_note.error");
  }
}
