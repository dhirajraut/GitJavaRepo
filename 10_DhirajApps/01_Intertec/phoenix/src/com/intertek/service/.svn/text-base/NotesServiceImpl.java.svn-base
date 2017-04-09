package com.intertek.service;

import java.util.List;

import com.intertek.entity.Notes;

public class NotesServiceImpl extends BaseService implements NotesService {

  public void UpdateDeleteNote(List<Notes> noteList, List<Notes> delList) {

      if(noteList!=null){
        for(Notes note:noteList)
        {
          getDao().update(note);
        }

        }
      if(delList!=null){
        for(Notes note:delList)
        {
         getDao().remove(note);

        }
      }


  }

  public void addNotes(List<Notes> listNotes) {

     if(listNotes!=null){
        for(Notes note:listNotes)
        {
          getDao().save(note);
        }
      }

  }

  public List getNotes(String noteCode, String noteType) {

      List listNotes=getDao().find("from Notes custNote where custNote.noteCode = ? and custNote.noteType = ?"
          , new Object[] { noteCode,noteType});
      return listNotes;
  }

}
