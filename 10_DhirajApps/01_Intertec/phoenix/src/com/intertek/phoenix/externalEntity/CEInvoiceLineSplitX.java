/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

/**
 * 
 * @author Eric.Nguyen
 */
public class CEInvoiceLineSplitX {
    private String allocPct;// "/></PERCENTAGE>
    private String branchName;// "/></OPERATING_UNIT>
    private String allocAmt;// "/></AMOUNT>

    public String getAllocPct() {
        return allocPct;
    }

    public void setAllocPct(String allocPct) {
        this.allocPct = allocPct;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAllocAmt() {
        return allocAmt;
    }

    public void setAllocAmt(String allocAmt) {
        this.allocAmt = allocAmt;
    }

}
