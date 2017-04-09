package com.intertek.pagination;

import java.util.ArrayList;
import java.util.List;

import com.intertek.util.Constants;

/**
 * Represent a list of pages in jsp. It contains the following information:
 * total number of records, number of records in one page, current page number,
 * number of pages to display, number of total pages, and a list of pages.
 * 
 **/
public class Pagination {
    public static int UNDEFINED=-1;
    public static int DEFAULT_NUMBER_IN_PAGE = 20;
    private int totalRecord = UNDEFINED;
    private int numInPage = DEFAULT_NUMBER_IN_PAGE;
    private int currentPageNum;
    private int pagesToDisplay;
    private int totalPages;

    private List<Page> pages;

    /**
     * .ctr
     * 
     * @param totalRecord
     *            - total number of records.
     * @param numInPage
     *            - number of records in one page.
     * @param currentPageNum
     *            - current page number.
     * @param pagesToDisplay
     *            - number of pages to display.
     * 
     **/
    public Pagination(int totalRecord, int numInPage, int currentPageNum, int pagesToDisplay) {
        this.totalRecord = totalRecord;
        this.numInPage = numInPage;
        this.currentPageNum = currentPageNum;
        this.pagesToDisplay = pagesToDisplay;

        if (currentPageNum <= 0){
            this.currentPageNum = 1;
        }
        if (pagesToDisplay <= 0){
            this.pagesToDisplay = Constants.DEFAULT_PAGES_TO_DISPLAY;
        }
    }

    /**
     * Get total number of records
     * 
     * @return - total number of records.
     * 
     **/
    public int getTotalRecord() {
        return totalRecord;
    }

    /**
     * Set total number of records
     * 
     * @param totalRecord
     *            - total number of records.
     * 
     **/
    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    /**
     * Get number of records in one page
     * 
     * @return - number of records in one page.
     * 
     **/
    public int getNumInPage() {
        return numInPage;
    }

    /**
     * Set number of records in one page
     * 
     * @param numInPage
     *            - number of records in one page.
     * 
     **/
    public void setNumInPage(int numInPage) {
        this.numInPage = numInPage;
    }

    /**
     * Get current page number
     * 
     * @return - current page number.
     * 
     **/
    public int getCurrentPageNum() {
        return currentPageNum;
    }

    /**
     * Set current page number
     * 
     * @param currentPageNum
     *            - current page number.
     * 
     **/
    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    /**
     * Get number of pages to display
     * 
     * @return - number of pages to display.
     * 
     **/
    public int getPagesToDisplay() {
        return pagesToDisplay;
    }

    /**
     * Set number of pages to display
     * 
     * @param pagesToDisplay
     *            - number of pages to display.
     * 
     **/
    public void setPagesToDisplay(int pagesToDisplay) {
        this.pagesToDisplay = pagesToDisplay;
    }

    /**
     * Get total number of pages
     * 
     * @return - total number of pages.
     * 
     **/
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Set total number of pages
     * 
     * @param totalPages
     *            - total number of pages.
     * 
     **/
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * Get the list of pages
     * 
     * @return - the list of pages.
     * 
     **/
    public List<Page> getPages() {
        return pages;
    }

    /**
     * Set the list of pages
     * 
     * @param pages the list of pages.
     **/
    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    /**
     * Calculate the list of pages and total number of pages based on the
     * parameters in this class: total number of records, number of records in
     * one page, current page number, and number of pages to display.
     * 
     **/
    public void calculatePages() {
        int totalPages = totalRecord / numInPage;
        // Added due to iTrack issue 106894
        pages = new ArrayList<Page>();

        if ((totalRecord % numInPage) != 0){
            totalPages++;
        }
        if (totalPages <= 1){
            return;
        }
        setTotalPages(totalPages);
      // Commented due to iTrack issue 106894
      // pages = new ArrayList<Page>();

        int startPage = ((currentPageNum - 1) / pagesToDisplay) * pagesToDisplay + 1;

        // do first and prev
        if (currentPageNum >= 1) {
            Page page1 = new Page("First");
            Page page2 = new Page("Prev");
            if (currentPageNum == 1) {
                page1.setPageNumber(currentPageNum);
                page2 = new Page("");
            }
            else {
                page1.setPageNumber(1);
                page2.setPageNumber(currentPageNum - 1);
            }
            pages.add(page1);
            pages.add(page2);
        }

        // do the middle of the page section
        for (int i = 0; i < pagesToDisplay; i++) {
            int tmp = startPage + i;
            if (tmp <= totalPages) {
                Page page = new Page(String.valueOf(tmp));
                page.setPageNumber(tmp);
                if (currentPageNum == tmp){
                    page.setSelected(true);
                }
                pages.add(page);
            }
            else {
                break;
            }
        }

        // do next and last
        if (currentPageNum <= totalPages) {
            Page page1 = new Page("Next");
            Page page2 = new Page("Last");
            if (currentPageNum == totalPages){
                page1 = new Page("");
            }
            else{
                page1.setPageNumber(currentPageNum + 1);
            }
            page2.setPageNumber(totalPages);
            pages.add(page1);
            pages.add(page2);
        }
    }
}
