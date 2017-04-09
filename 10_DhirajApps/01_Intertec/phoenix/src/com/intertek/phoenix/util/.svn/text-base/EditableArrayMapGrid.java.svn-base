/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.util;

import java.util.List;


/**
 * This is an editable array map grid. The supported operation is set value 
 * to the current record, add and remove the specified recores.
 * 
 * @author richard.qin
 */
public class EditableArrayMapGrid extends ArrayMapGrid {

    /**
     * Construct a new EditableArrayMapGrid around an existing ArrayMapGrid
     * @param amg
     */
    public EditableArrayMapGrid(ArrayMapGrid amg){
        super(amg.map, amg.dataset);
    }
    
    /**
     * Constructor
     * @param am
     * @param dataset
     */
    public EditableArrayMapGrid(ArrayMap<String, Object> am, List<Object[]> dataset){
        super(am, dataset);
    }
    
    /**
     * Duplicate this EditableArrayMapGrid.
     * @see com.intertek.phoenix.util.ArrayMapGrid#duplicate()
     */
    @Override
    public ArrayMapGrid duplicate(){
        EditableArrayMapGrid newGrid = new EditableArrayMapGrid(this.map, this.dataset);
        newGrid.current = -1;

        return newGrid;
    }
    
    /**
     * Set the named field to the specified value for the current record.
     * @param name
     * @param value
     */
    public void setFieldValue(String name, Object value){
        super.map.put(name, value);
    }
    
    /**
     * Inserts a new blank record at the end of the EditabltArrayMapGrid.
     * The client code should then call setFieldValue to populate the record
     * values.
     */
    public void addValues(){
        int fieldCount = super.getColumnCount();
        super.dataset.add(new Object[fieldCount]);
        // move the current record position to the newly added record
        super.moveTo(this.dataset.size() - 1);
    }
    
    /**
     * Inserts a new blank record at the specified position to the EditableArrayMapGrid.
     * Shifts the record at that position and any subsequent records to lower.
     * @param position - position at which the new blank record should be inserted.
     */
    public void addValues(int position){
        int fieldCount = super.getColumnCount();
        super.dataset.add(position, new Object[fieldCount]);
        // move the current record position to the newly added record
        super.moveTo(position);
    }
    
    /**
     * Remove a record at the specified position
     * @param position
     */
    public void removeValues(int position){
        super.dataset.remove(position);
    }
    
}
