package com.intertek.cache;

public abstract class ITSCacheManager extends CacheManager {

	/**
		Number of milliseconds a cache entry should live before it is
		invalid.  A value of -1 signifies that a cache entry never
		expires.
	*/
	private static long timeToLive = 3600000;  // 3600000 = 60 Minutes

	/**
		If a cache entry is not read within the time specified, the
		entry becomes invalid.  A value of -1 signifies there there is
		not latency limit.
	*/
	private static long maxLatency = 600000;  // 900000 = 10 Minutes

	/**
	   Maximum size of any one cache space (in entries).  A value of
	   -1 signifies no limit.  The number of cache spaces is
	   unbounded.
	*/
	private static long maxCacheSize = 50;

	/**
	   Default status of a cache when created.
	*/
	private static boolean enabled = false;


	public static final String communication = "communication";
	public static final String contactFlag = "contactFlag";
	public static final String salutation = "salutation";
	public static final String option = "option";
	public static final String selectedLanguage = "selectedLanguage";
	public static final String employeeStatus = "employeeStatus";
	public static final String employeeType = "employeeType";
	public static final String plusMinus = "plusMinus";
	public static final String treatServiceforForeignBuyer = "treatServiceforForeignBuyer";
	public static final String UOM = "UOM";
	public static final String contractAttachType = "contractAttachType";
	public static final String dateFormat = "dateFormat";
	public static final String dispatchStatus = "dispatchStatus";
	public static final String priority = "priority";
	public static final String retainTested = "retainTested";
	public static final String sampleVolume = "sampleVolume";
	public static final String status = "status";
	public static final String activeStatus = "activeStatus";
	public static final String branchtype = "branchtype";
	public static final String invoiceType = "invoiceType";
	public static final String jobsearchStatus = "jobsearchStatus";
	public static final String paymentType = "paymentType";
	public static final String productType = "productType";
	public static final String contractStatus = "contractStatus";
	public static final String origin = "origin";
	public static final String sampleTiming = "sampleTiming";
	public static final String workingUOM = "workingUOM";
	public static final String audienceType = "audienceType";
	public static final String fullPartTime = "fullPartTime";
	public static final String jobContractStatus = "jobContractStatus";
	public static final String jobStatus = "jobStatus";
	public static final String jobType = "jobType";
	public static final String locationType = "locationType";
	public static final String regularTemp = "regularTemp";
	public static final String sampleLocation = "sampleLocation";
	public static final String sampleType = "sampleType";
	public static final String searchResults = "searchResults";
	public static final String searchfields = "searchfields";
	public static final String workingPb = "workingPb";
	public static final String containerType = "containerType";
	public static final String customerType = "customerType";
	public static final String joblogStatus = "joblogStatus";
	public static final String noteType = "noteType";
	public static final String timeFormat = "timeFormat";
	public static final String workFunction = "workFunction";
	public static final String conslInvStatus = "conslInvStatus";
	public static final String dropdown = "dropdown";
	public static final String jobTimeFormat = "jobTimeFormat";
	public static final String operator = "operator";
	public static final String visibility = "visibility";
	public static final String yesNo = "yesNo";
	

	// {CacheName, TimeToLive, MaxLatency, MaxCacheSize, Enabled, allowBulkDelete, bulkDeletePercent}
	public static final String[][] PARAMS = {
		{communication, "600000", "360000", "50", "true", "false", "0"},
		{contactFlag, "600000", "360000", "50", "true", "false", "0"},
		{salutation, "600000", "360000", "50", "true", "false", "0"},
		{option, "600000", "360000", "50", "true", "false", "0"},
		{selectedLanguage, "600000", "360000", "50", "true", "false", "0"},
		{employeeStatus, "600000", "360000", "50", "true", "false", "0"},
		{employeeType, "600000", "360000", "50", "true", "false", "0"},
		{plusMinus, "600000", "360000", "50", "true", "false", "0"},
		{treatServiceforForeignBuyer, "600000", "360000", "50", "true", "false", "0"},
		{UOM, "600000", "360000", "50", "true", "false", "0"},
		{contractAttachType, "600000", "360000", "50", "true", "false", "0"},
		{dateFormat, "600000", "360000", "50", "true", "false", "0"},
		{dispatchStatus, "600000", "360000", "50", "true", "false", "0"},
		{priority, "600000", "360000", "50", "true", "false", "0"},
		{retainTested, "600000", "360000", "50", "true", "false", "0"},
		{sampleVolume, "600000", "360000", "50", "true", "false", "0"},
		{status, "600000", "360000", "50", "true", "false", "0"},
		{activeStatus, "600000", "360000", "50", "true", "false", "0"},
		{branchtype, "600000", "360000", "50", "true", "false", "0"},
		{invoiceType, "600000", "360000", "50", "true", "false", "0"},
		{jobsearchStatus, "600000", "360000", "50", "true", "false", "0"},
		{paymentType, "600000", "360000", "50", "true", "false", "0"},
		{productType, "600000", "360000", "50", "true", "false", "0"},
		{contractStatus, "600000", "360000", "50", "true", "false", "0"},
		{origin, "600000", "360000", "50", "true", "false", "0"},
		{sampleTiming, "600000", "360000", "50", "true", "false", "0"},
		{workingUOM, "600000", "360000", "50", "true", "false", "0"},
		{audienceType, "600000", "360000", "50", "true", "false", "0"},
		{fullPartTime, "600000", "360000", "50", "true", "false", "0"},
		{jobContractStatus, "600000", "360000", "50", "true", "false", "0"},
		{jobStatus, "600000", "360000", "50", "true", "false", "0"},
		{jobType, "600000", "360000", "50", "true", "false", "0"},
		{locationType, "600000", "360000", "50", "true", "false", "0"},
		{regularTemp, "600000", "360000", "50", "true", "false", "0"},
		{sampleLocation, "600000", "360000", "50", "true", "false", "0"},
		{sampleType, "600000", "360000", "50", "true", "false", "0"},
		{searchResults, "600000", "360000", "50", "true", "false", "0"},
		{searchfields, "600000", "360000", "50", "true", "false", "0"},
		{workingPb, "600000", "360000", "50", "true", "false", "0"},
		{containerType, "600000", "360000", "50", "true", "false", "0"},
		{customerType, "600000", "360000", "50", "true", "false", "0"},
		{joblogStatus, "600000", "360000", "50", "true", "false", "0"},
		{noteType, "600000", "360000", "50", "true", "false", "0"},
		{timeFormat, "600000", "360000", "50", "true", "false", "0"},
		{workFunction, "600000", "360000", "100", "true", "false", "0"},
		{conslInvStatus, "600000", "360000", "50", "true", "false", "0"},
		{dropdown, "600000", "360000", "60", "true", "false", "0"},
		{jobTimeFormat, "600000", "360000", "50", "true", "false", "0"},
		{operator, "600000", "360000", "50", "true", "false", "0"},
		{visibility, "600000", "360000", "50", "true", "false", "0"},
		{yesNo, "600000", "360000", "50", "true", "false", "0"}
		};


	public static void initCache() {
		for (int i = 0; i < PARAMS.length; i++) {
			addCache(PARAMS[i][0],                      //Cache Name
					 Long.parseLong(PARAMS[i][1]),      //Time To Live
					 Long.parseLong(PARAMS[i][2]),      //Max Latency
					 Long.parseLong(PARAMS[i][3]),      //Max Cache Size
					 (Boolean.valueOf(PARAMS[i][4])).booleanValue(), //Enabled
					 (Boolean.valueOf(PARAMS[i][5])).booleanValue(), //Clean entries in bulk
					 Double.parseDouble(PARAMS[i][6])); //percent of entries to be deleted
		}
	}
}
