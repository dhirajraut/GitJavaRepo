/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import java.util.List;

import com.intertek.phoenix.entity.CEInvoiceLineItem;
import com.intertek.phoenix.entity.CEJobOrderLineItem;

/**
 * @author Eric.Nguyen
 */
public class CEInvoiceLineX {
    private String prebillId; // " select="prebillId"/>
    private double netPrice; // " select="netPrice"/>
    private String lineDescription; // " select="lineDescription"/>
    private double splitPct; // " select="splitPct"/>
    private double discountPct; // " select="discountPct"/>
    private double unitPrice; // " select="unitPrice"/>
    private String level0; // " select="level0"/>
    private String level1; // " select="level1"/>
    private String level2; // " select="level2"/>
    private String busStreamCode; // " select="busStreamCode"/>
    private String account; // " select="account"/>
    private String deptid; // " select="deptid"/>
    private String productGroup; // " select="productGroup"/>
    private String serviceType; // " select="serviceType"/>
    private String taxCode; // " select="taxCode"/>
    private String vatCode; // " select="vatCode"/>
    private String taxArticleCode; // " select="taxArticleCode"/>

    private List<CEInvoiceLineSplitX> invoiceLineSplit;

    public CEInvoiceLineX(){
        
    }
    
    public CEInvoiceLineX(CEInvoiceLineItem item){
        CEJobOrderLineItem ceLineItem=item.getCEJobOrderLineItem();
        
        this.prebillId=item.getId()+""; // " select="prebillId"/>
        this.netPrice=item.getNetPrice(); // " select="netPrice"/>
        this.lineDescription=ceLineItem.getDescription(); // " select="lineDescription"/>
        this.splitPct=ceLineItem.getSplitPct(); // " select="splitPct"/>
        this.discountPct=ceLineItem.getDiscountPct(); // " select="discountPct"/>
        this.unitPrice=ceLineItem.getUnitPrice(); // " select="unitPrice"/>
//TODO          this.level0=; // " select="level0"/>
//TODO        this.level1; // " select="level1"/>
//TODO        this.level2; // " select="level2"/>
//TODO        this.busStreamCode=ceLineItem.getb; // " select="busStreamCode"/>
        this.account=item.getAccount(); // " select="account"/>
        this.deptid=item.getDepId(); // " select="deptid"/>
        this.productGroup=ceLineItem.getProductGroup(); // " select="productGroup"/>
//TODO        this.serviceType=item.get; // " select="serviceType"/>
        this.taxCode=item.getTaxCode(); // " select="taxCode"/>
        this.vatCode=item.getVatCode(); // " select="vatCode"/>
        this.taxArticleCode=item.getTaxArticleCode(); // " select="taxArticleCode"/>
    }

    public String getPrebillId() {
        return prebillId;
    }

    public void setPrebillId(String prebillId) {
        this.prebillId = prebillId;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }

    public String getLineDescription() {
        return lineDescription;
    }

    public void setLineDescription(String lineDescription) {
        this.lineDescription = lineDescription;
    }

    public double getSplitPct() {
        return splitPct;
    }

    public void setSplitPct(double splitPct) {
        this.splitPct = splitPct;
    }

    public double getDiscountPct() {
        return discountPct;
    }

    public void setDiscountPct(double discountPct) {
        this.discountPct = discountPct;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getLevel0() {
        return level0;
    }

    public void setLevel0(String level0) {
        this.level0 = level0;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getBusStreamCode() {
        return busStreamCode;
    }

    public void setBusStreamCode(String busStreamCode) {
        this.busStreamCode = busStreamCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getVatCode() {
        return vatCode;
    }

    public void setVatCode(String vatCode) {
        this.vatCode = vatCode;
    }

    public String getTaxArticleCode() {
        return taxArticleCode;
    }

    public void setTaxArticleCode(String taxArticleCode) {
        this.taxArticleCode = taxArticleCode;
    }

    public List<CEInvoiceLineSplitX> getInvoiceLineSplit() {
        return invoiceLineSplit;
    }

    public void setInvoiceLineSplit(List<CEInvoiceLineSplitX> invoiceLineSplit) {
        this.invoiceLineSplit = invoiceLineSplit;
    }
    
}
