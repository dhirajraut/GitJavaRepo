/**
 * 
 */
package com.intertek.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRRewindableDataSource;

import org.apache.log4j.Logger;

import com.intertek.phoenix.util.ArrayMap;
import com.intertek.phoenix.util.ArrayMapGrid;

/**
 * ArrayMapDataSource is a generic grid type datasource backed by an ArrayMapGrid object.
 * 
 * @author richard.qin
 */
public class ArrayMapDataSource extends ArrayMapGrid implements JRRewindableDataSource {
    private static final Logger log = Logger.getLogger(ArrayMapDataSource.class);
    
    // this is the callback to the actual JasperDataSource implementation, 
    // allowing a JasperDataSource to do some additional data manipulation
    // after the record cursor is moved to the next one.
    private JasperDataSource host;
    // this is the argument that will be passed to the callback
    private Map<String, Object> params;
    // this map contains all the "global" calculated field values
    private Map<String, Object> calculatedFields = new HashMap<String, Object>();

    public ArrayMapDataSource(ArrayMap<String, Object> am, List<Object[]> dataset, JasperDataSource host, Map<String, Object> params){
        super(am, dataset);
        this.host = host;
        this.params = params;
        
        // give the DataSource a chance to calculate some data
        if(host != null){
            host.dataLoaded(this, params);
        }
    }
    
    /**
     * Return a duplicated ArrayMapDataSource that shares the same underlying dataset. The new
     * ArrayMapDataSource will be attached to the given host and be positioned before the first
     * row of the dataset.
     * @param host owner of the deplicated ArrayMapDataSource
     * @param params Map of parameters
     * @return a new ArrayMapDataSource object that shares the same dataset.
     */
    public ArrayMapDataSource duplicate(JasperDataSource host, Map<String, Object> params){
        ArrayMapDataSource ds = new ArrayMapDataSource(this.getArrayMap(), this.getDataset(), 
                                                       this.host, this.params);
        
        return ds;
    }
    
    @Override
    public Object getFieldValue(JRField field) throws JRException {
        String name = field.getName();
        return getFieldValue(name);
    }

    @Override
    public boolean next() throws JRException {
        return super.moveNext();
    }
    
    @Override
    public Object getFieldValue(String name){
        Object value = getArrayMap().get(name);
        if(value == null && host != null){
            // this is a calculated field, use callback to get the value
            try{
                // to prevent bad data corrupt the Phoenix system, NullPointerException will
                // be captured and discarded.
                value = host.calculateFieldValue(name, params, this);
            }
            catch(ArithmeticException e){
                log.warn("Required field " + name + " has invalid value.");
                log.warn(e);
                value = null;
            }
            catch(NullPointerException e){
                log.warn("Required field " + name + " has null value.");
                value = null;
            }
            catch(Throwable t){
                log.error("Unexpected error occured when calculating value for field " + name);
                log.error(t);
                value = null;
            }
        }
        if(value == null){
            log.debug("Field " + name + " has no value.");
        }
        return value;
    }

    @Override
    public void moveFirst() throws JRException {
        super.reset();
    }
    
    /**
     * Save calculated field value to be retrieved later. Calculated field are handled by each
     * specific datasource implementation.
     * @param key
     * @param value
     */
    public void addCalculatedField(String key, Object value){
        this.calculatedFields.put(key, value);
    }
    
    /** 
     * Lookup a previously saved calculated field value.
     * 
     * @param key
     * @return
     */
    public Object getCalculatedField(String key){
        return this.calculatedFields.get(key);
    }
}
