package com.intertek.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

public class Constants
{
  public static final int DEFAULT_ROUNDING=6;
  public static final int TAX_VAT_SCALE=5;

  public static final String ROLL_BACK="ROLL_BACK";
  
  public static final String MY_JOB_LOCK="MY_JOB_LOCK";
  public static final String OBJECT_MODIFIER="OBJECT_MODIFIER";
  public static final String PROD_ENV="PROD";
  public static final String STG_ENV="STG";
  public static final String DEV_ENV="DEV";

  public static final String ADMIN = "ADMIN";

  public static final String USER = "USER";

  public static final String VENDOR = "VENDOR";

  public static final String service = "service";
  public static final String api = "api";
  public static final int SUFIXJOBNUMLENGTH=7;
  public static final String NEW = "NEW";
  public static final String DELETED = "DELETED";
  public static final String VIEWED = "VIEWED";
  public static final String SENT = "SENT";
  public static final String FAIL = "FAIL";

  public static final String SUBS = "01";
  public static final String DIV = "02";
  public static final String BRCH = "03";
  public static final String HQRT = "04";
  public static final String SNGLELOC = "05";

  public static final String STATUS_A = "A";
  public static final String STATUS_I = "I";

  public static final String ACTIVE = "ACTIVE";
  public static final String IN_VERIFYING = "IN_VERIFYING";

  public static int DEFAULT_PAGES_TO_DISPLAY = 5;
  public static int DEFAULT_NUM_IN_PAGE = 10;

  public static final String Yes = "Yes";
  public static final String No = "No";

  public static final String UPDATE_TIME = "UPDATE_TIME";

  public static final String SAVED_REQUEST_NAME = "SAVED_REQUEST_NAME";

  public static final String CLASS = "CLASS";
  public static final String name = "name";

  public static final String META_DATA_DIR = "meta.data.dir";

  public static final String VIEW = "view";
  public static final String NAME = "name";
  public static final String OBJECT = "object";
  public static final String FIELD = "field";
  public static final String FIELD_TYPE = "field_type";
  public static final String EDITABLE = "editable";
  public static final String LABEL = "label";
  public static final String KEYS = "keys";
  public static final String KEY = "key";
  public static final String OBJECT_REF = "object_ref";

  public static final String DROPDOWN_FILE = "dropdown.file";

  public static final String DROPDOWN = "dropdown";

  public static final String ID = "id";
  public static final String VALUE = "value";

  public static final String CLASS_ = "class";

  public static final String SEQ_PREFIX = "SEQ_";
  public static final String TEMP = "T";
  public static final String BRANCH_SEQ = "BRANCH_SEQ";

  public static final int BEGINS_WITH = 1;
  public static final int CONTAINS = 2;
  public static final int EQUALS = 3;
  public static final int NOT_EQUALS = 4;

  public static final String BLANK="0";
  public static final String  INPROCESS="INPR";
  public static final String APPROVED="APP"  ;
  public static final String  INACTIVE="INAC";
  public static final String  SUBMIT="SUB";
  public static final String  REJECT="REJ";

  public static final String statusSUB="Inprocess";
  public static final String statusAPP="Approved";
  public static final String statusINAC="Inactive";

  public static final String SERVICE_LOCATION = "~SLOC:";

  public static final String DEFAULT_COLLECT_CODE = "COLLECT";
  public static final String DEFAULT_CREDITANALYST_CODE = "CREDIT";
  public static final String INSPECTION_JOBTYPE ="INSP";
  public static final String CE_JOBTYPE ="CE";
  public static final String ANALYTICAL_SERVICE_JOBTYPE ="FST";
  public static final String OUTSOURCING_JOBTYPE ="OUT";

  public static final String MARINE_JOBTYPE ="MAR";
  public static final String TIME_ZONE = "EST";
  public static final int REVISION_NUMBER = 1 ;
  public static final int MINUTS_PER_HOUR = 60 ;
  public static final int MINUTS_PER_DAY = 1440 ;
  public static final int MILLIS_INS_DAY = 1000*60*60*24;

  public static final double DAY_HOURS = 24.0 ;
  public static final String DATE_PATTERN = "dd/MM/yy";
  public static final String JOB_DESCRIPTION ="loading / discharging / lightering / transfer / sample & analysis of _________, _________, _________ at _________.";


  public static final String DATE_PATTERN_0 = "yyyyMMdd";
  public static final String ADM = "ADM";
  public static final String OPS = "OPS";
  public static final String LAB = "LAB";
  public static final String OPSLAB = "OPSL";
  public static final String PRICE_BOOK = "PB";
  public static final String CONTRACT = "CONTR";
  public static final String CONT = "CONT";
  public static final String STAR = "*";
  public static final String CURRENT = "CURRENT";
  public static final String Current = "Current";
  public static final String BRANCH = "BRNCH";
  public static final String USR = "USR";
  public static final String SERVICE_LOCATION_SEQ="SERVICE_LOCATION_SEQ";
  public static final String CUSTOMER_SEQ="CUSTOMER_SEQ";

  public static final String CALCULATOR_INSPECTION = "InspectionCalculator";
  public static final String CALCULATOR_INSPECTION_VESSEL = "InspectionCalculatorVessel";
  public static final String CALCULATOR_INSPECTION_CONTRACT = "InspectionCalculatorContract";
  public static final String CALCULATOR_INSPECTION_SHELL = "InspectionCalculatorShell";

  public static final String CALCULATOR_SIMPLE = "SimpleCalculator";
  public static final String CALCULATOR_STANDARD_FORMULA = "STANDARD_FORMULA";
  public static final String CALCULATOR_BOOLEAN_BASE_PRICE = "BOOLEAN_BASE_PRICE";
  public static final String CALCULATOR_PB_SAMPLE_RETENTION_DISPOSAL = "PB_SAMPLE_RETENTION_DISPOSAL";
  public static final String CALCULATOR_PB_PRESSURIZED_CYLINDERS = "PB_PRESSURIZED_CYLINDERS";
  public static final String CALCULATOR_SAMPLING_INCLUDES = "SAMPLING_INCLUDES";

  public static final String CALCULATOR_STANDARD_DISCOUNTER = "StandardDiscounter";
  public static final String CALCULATOR_INSPECTION_DISCOUNTER = "InspectionDiscounter";
  public static final String CALCULATOR_TEST_DISCOUNTER = "TestDiscounter";
  public static final String CALCULATOR_SLATE_DISCOUNTER = "SlateDiscounter";

  public static final String CALCULATOR_INSPECTION_PRICER = "InspectionPricer";
  public static final String CALCULATOR_INSPECTION_VESSEL_PRICER = "InspectionVesselPricer";
  public static final String CALCULATOR_INSPECTION_CONTRACT_PRICER = "InspectionContractPricer";
  public static final String CALCULATOR_INSPECTION_SHELL_PRICER = "InspectionShellPricer";

  public static final String CALCULATOR_TEST_PRICER = "TestPricer";
  public static final String CALCULATOR_SLATE_PRICER = "SlatePricer";
  public static final String CALCULATOR_STANDARD_FORMULA_PRICER = "STANDARD_FORMULA";
  public static final String CALCULATOR_BOOLEAN_BASE_PRICE_PRICER = "BOOLEAN_BASE_PRICE";
  public static final String CALCULATOR_PB_SAMPLE_RETENTION_DISPOSAL_PRICER = "PB_SAMPLE_RETENTION_DISPOSAL";
  public static final String CALCULATOR_PB_LIGHTERAGE_DAYS_PRICER = "PB_LIGHTERAGE_DAYS";
  public static final String CALCULATOR_PB_PRESSURIZED_CYLINDERS_PRICER = "PB_PRESSURIZED_CYLINDERS";
  public static final String CALCULATOR_SAMPLING_INCLUDES_PRICER = "SAMPLING_INCLUDES";

  public static final String CALCULATOR_INSPECTION_DESCRIPTOR = "InspectionDescriptor";
  public static final String CALCULATOR_INSPECTION_MODEL_2_DESCRIPTOR = "InspectionModel2Descriptor";
  public static final String CALCULATOR_INSPECTION_VESSEL_DESCRIPTOR = "InspectionVesselDescriptor";
  public static final String CALCULATOR_INSPECTION_CONTRACT_DESCRIPTOR = "InspectionContractDescriptor";
  public static final String CALCULATOR_INSPECTION_SHELL_DESCRIPTOR = "InspectionShellDescriptor";

  public static final String CALCULATOR_TEST_DESCRIPTOR = "TestDescriptor";
  public static final String CALCULATOR_SLATE_DESCRIPTOR = "SlateDescriptor";
  public static final String CALCULATOR_STANDARD_FORMULA_DESCRIPTOR = "STANDARD_FORMULA";
  public static final String CALCULATOR_BOOLEAN_BASE_PRICE_DESCRIPTOR = "BOOLEAN_BASE_PRICE";
  public static final String CALCULATOR_PB_SAMPLE_RETENTION_DISPOSAL_DESCRIPTOR = "PB_SAMPLE_RETENTION_DISPOSAL";
  public static final String CALCULATOR_PB_LIGHTERAGE_DAYS_DESCRIPTOR = "PB_LIGHTERAGE_DAYS";
  public static final String CALCULATOR_PB_PRESSURIZED_CYLINDERS_DESCRIPTOR = "PB_PRESSURIZED_CYLINDERS";
  public static final String CALCULATOR_SAMPLING_INCLUDES_DESCRIPTOR = "SAMPLING_INCLUDES";


  public static final String includedSamples = "includedSamples";
  public static final String noOfQuarts = "noOfQuarts";
  public static final String noOfContainers = "noOfContainers";
  public static final String numPressCylinders = "numPressCylinders";
  public static final String pressCylinderDays = "pressCylinderDays";
  public static final String LIGHTERAGE_TYPE = "LIGHTERAGE_TYPE";
  public static final String PRODUCT_TYPE1 = "PRODUCT_TYPE1";
  public static final String PRODUCT_TYPE = "PRODUCT_TYPE";
  public static final String RADIO_INPUT = "RADIO_INPUT";
  public static final String INSPECTION_QUANTITY = "INSPECTION_QUANTITY";
  public static final String QUANTITY = "QUANTITY";
  public static final String QUANTITY1 = "QUANTITY1";
  public static final String QUANTITY2 = "QUANTITY2";
  public static final String BASE_PRICE = "BASE_PRICE";
  public static final String BASE_PRICE_0 = "BASE_PRICE_0";
  public static final String UNIT_PRICE = "UNIT_PRICE";
  public static final String UNITS_INCLUDED = "UNITS_INCLUDED";
  public static final String MIN_PRICE = "MIN_PRICE";
  public static final String MAX_PRICE = "MAX_PRICE";
  public static final String FLOAT_DATA0 = "FLOAT_DATA0";
  public static final String FLOAT_DATA1 = "FLOAT_DATA1";
  public static final String FLOAT_DATA2 = "FLOAT_DATA2";
  public static final String FLOAT_DATA3 = "FLOAT_DATA3";
  public static final String INT_DATA0 = "INT_DATA0";
  public static final String INT_DATA1 = "INT_DATA1";
  public static final String INT_DATA2 = "INT_DATA2";
  public static final String INT_DATA3 = "INT_DATA3";
  public static final String STRING_VAL = "stringVal"; // Added by RQ
  public static final String STRING_VAL1 = "stringVal1";
  public static final String STRING_VAL2 = "stringVal2";
  public static final String STRING_VAL3 = "stringVal3";
  public static final String PERCENTAGE = "PERCENTAGE";
  public static final String CONTRACT_REF_NO = "CONTRACT_REF_NO";
  public static final String RB = "RB";
  public static final String DESCRIPTION = "DESCRIPTION";
  public static final String PRICE_BEFORE_DISCOUNT = "PRICE_BEFORE_DISCOUNT";

  public static final String GL_CODE = "GL_CODE";
  public static final String DEPARTMENT = "DEPARTMENT";
  public static final String SERVICE_TYPE = "SERVICE_TYPE";
  public static final String PRODUCT_GROUP = "PRODUCT_GROUP";

  public static final String CONTRACT_ID = "CONTRACT_ID";
  public static final String SERVICE_NAME = "SERVICE_NAME";
  public static final String VESSEL_TYPE = "VESSEL_TYPE";
  public static final String PRODUCT_GROUP_ID = "PRODUCT_GROUP_ID";
  public static final String SERVICE_LEVEL = "SERVICE_LEVEL";
  public static final String LOCATION = "LOCATION";
  public static final String NOMINATION_DATE_STR = "NOMINATION_DATE_STR";
  public static final String NOMINATION_DATE = "NOMINATION_DATE";
  public static final String LANGUAGE_CD = "LANGUAGE_CD";
  public static final String JOB_TYPE = "JOB_TYPE";
  public static final String BRANCH_CODE = "BRANCH_CODE";
  public static final String JOB_CODE = "JOB_CODE";
  public static final String CURRENCY_CD = "CURRENCY_CD";
  public static final String MASTER_GROUP_ID = "MASTER_GROUP_ID";

  public static final String RB_KEY = "RB_KEY";
  public static final String RB_VALUE = "RB_VALUE";

  public static final String SELECTED_QTY_PRICE = "SELECTED_QTY_PRICE";
  public static final String ADDITIONAL_LOT = "ADDITIONAL_LOT";
  public static final String ADDITIONAL_VESSEL = "ADDITIONAL_VESSEL";
  public static final String BOOLEAN_VAL_1 = "BOOLEAN_VAL_1";
  public static final String noOfUOM = "noOfUOM";
  public static final String noOfUOM1 = "noOfUOM1";
  public static final String noOfUOM2 = "noOfUOM2";

  public static final String NEW_TEST = "NEW_TEST";
  public static final String IS_CONTRACT_PRICE = "IS_CONTRACT_PRICE";

  public static final String UOM_CODE = "UOM_CODE";

  public static final String TEST_ID = "TEST_ID";
  public static final String SLATE_ID = "SLATE_ID";
  public static final String CONTRACT_EXPRESSION_EXT = "CONTRACT_EXPRESSION_EXT";

  public static final String Inspection = "Inspection";
  public static final String StandAlone = "StandAlone";
  public static final String CAL = "CAL";
  public static final String MTD = "MTD";
  public static final String IRT = "IRT";
  public static final String VMM = "VMM";
  public static final String AGRI = "AGRI";

  public static final String JOB_SERVICE = "JOB_SERVICE";
  public static final String VESSEL_SERVICE = "VESSEL_SERVICE";
  public static final String LOT_SERVICE = "LOT_SERVICE";

  public static final String INLQ = "INLQ";

  public static final String MASTER = "MASTER";

  public static final String Test = "Test";
  public static final String Slate = "Slate";
  public static final int JMColumnSize = 24;
  public static final int MColumnSize = 28 ;
  public static final int DColumnSize = 31;
  public static final int BColumnSize = 10;
  public static final int PColumnSize = 12;
  public static final int UColumnSize = 3 ;
//96509
  public static final int CColumnSize = 6;
//96509 end
  public static final int CoColumnSize = 3;
  public static final int ConColumnSize=2;
  public static final int PLColumnSize=1;
  public static final int RColumnSize=1;
  public static final int CounColumnSize=2;
// 96509
  public static final int BuColumnSize=5;
  public static final int BrColumnSize=7;
// 96509 end
  public static final int VColumnSize=3;
  public static final int TColumnSize=3;
  public static final int SColumnSize=2;
  public static final int SLColumnSize=5;
  public static final int TCColumnSize=2;
  public static final int OColumnSize=2;
  public static final int CPColumnSize=1;
  public static final int JConColumnSize=5;
  public static final int BIColumnSize=4;
  public static final int TLColumnSize=4;
  public static final int BusStreamsColumnSize=3;
  // START : #127441
  public static final int CJColumnSize=3;
  // END : #127441
  public static final String BOTH = "BOTH";
  public static final String HYPHEN = "-";
  public static final String ALL_STATUS = "5";
  public static final String CLOSED_STATUS = "6000";
  public static final String OPENREOPEN_STATUS="4";
  public static final String OPEN_STATUS="1000";
  public static final String REOPENED_STATUS="7000";
  public static final String CANCELLED_STATUS="1";

  public static final String JOBCON_CANCELLED_STATUS = "1";
  public static final String JOBCON_CREDITED_STATUS = "7200";
  public static final String JOBCON_INVOICED_STATUS = "6000";
  public static final String JOBCON_OPEN_STATUS = "1000";
  public static final String JOBCON_REBILLED_STATUS = "7100";

  public static final int FULL_JOIN= 4;
  public static final int INNER_JOIN= 0;
  public static final int LEFT_JOIN =1;

  public static final int NO_OF_RECORDS =10;
  public static final String INSP="edit_job_entry_insp.htm";
  public static final String FST="edit_job_entry_analytical_service.htm";

  public static final String MAR="edit_job_entry_marine.htm";
  public static final String OUT="edit_job_entry_outsourcing.htm";
  public static final String VIEW_INSP="view_job_entry_insp.htm";
  public static final String VIEW_FST="view_job_entry_analytical_service.htm";
  public static final String VIEW_MAR="view_job_entry_marine.htm";
  public static final String VIEW_OUT="view_job_entry_outsourcing.htm";
  public static final String JOB_SEARCH="search_job_results.htm";
  public static final String JOB_LOG="search_job.htm";

  // START : #119240
  public static final String JOB_INSTR="edit_job_operational_info_insp.htm";
  public static final String SELECT_CHARGES="edit_job_select_charges.htm";
  public static final String INV_PREVIEW="edit_job_invoice_preview.htm";
  public static final String VIEW_INV="edit_job_view_invoice.htm";
  
  public static final String JOB_ENTRY_FORM="1";
  public static final String JOB_INSTRUCTIONS="2";
  public static final String JOB_SELECT_CHARGES="3";
  public static final String JOB_PREVIEW_INVOICE="4";
  public static final String JOB_INVOICE="5";
  
  public static final String VIEW_JOB_INSTR="view_job_operational_info_insp.htm";
  public static final String VIEW_SELECT_CHARGES="view_job_select_charges.htm";
  
  // END : #119240
  
  public static final String INSPJOBTYPE="OCA & MN Inspection & Testing";
  public static final String ANLSJOBTYPE="OCA, AS & MN FST";
  public static final String OUTSOURCINGJOBTYPE="OCA, AS & MN OutSourcing";

  public static final String MARINEJOBTYPE="OCA Marine & Calibrations";
  //jobStatus
  public static final String OPEN="Open";
  public static final String CLOSED="Closed";
  public static final String CANCELLED="Cancelled";
  public static final String REOPENED="Reopened";

  public static final String SHARE = "SHARE";

  public static final String CR = "CR";
  public static final String CON = "CON";
  //Job Operations
  public static final String Additivation="Additivation";
  public static final String ADDT="ADDT";
  public static final String AnalysisOnly="Analysis only";
  public static final String AONY="AONY";
  public static final String AtmosphericMontr="Atmospheric monitoring";
  public static final String ATMO="ATMO";
  public static final String Bunkering="Bunkering";
  public static final String BUNK="BUNK";
  public static final String Calibration="Calibration";
  public static final String CALB="CALB";
  public static final String Consulting="Consulting";
  public static final String CONS="CONS";
  public static final String ControlOnMARPOLregulations="Control on MARPOL regulations";
  public static final String MMRR="MMRR";
  public static final String Customsclearance="Customs clearance";
  public static final String CUCL="CUCL";
  public static final String Depotoperations="Depot operations";
  public static final String DEOP="DEOP";
  public static final String Documentcontrol="Document control";
  public static final String DOCMC="DOCMC";
  public static final String DockSupervision="Dock Supervision";
  public static final String DKSP="DKSP";
  public static final String DraftSurvey="Draft Survey";
  public static final String DRFT="DRFT";
  public static final String Expediting="Expediting";
  public static final String EXPD="EXPD";
  public static final String GaugingOnly="Gauging Only";
  public static final String GONL="GONL";
  public static final String HandblendAnalysis="Handblend And Analysis";
  public static final String HBAN="HBAN";
  public static final String HoldConditionSurvey="Hold Condition Survey";
  public static final String HLDC="HLDC";
  public static final String HoldInspection="Hold Inspection";
  public static final String HLDI="HLDI";
  public static final String Inventory="Inventory";
  public static final String INV="INV";
  public static final String Jettyservices="Jetty services";
  public static final String JESE="JESE";
  public static final String Lightering="Lightering";
  public static final String LIGH="LIGH";
  public static final String LineDisplacement="Line Displacement";
  public static final String LNDP="LNDP";
  public static final String Loading="Loading";
  public static final String LOAD="LOAD";
  public static final String Pipeline="Pipeline";
  public static final String PIPE="PIPE";
  public static final String Safety="Safety";
  public static final String SAFE="SAFE";
  public static final String Safetychecklist="Safety checklist";
  public static final String SFCH="SFCH";
  public static final String SampleAnalysis="Sample & Analysis";
  public static final String SAMP="SAMP";
  public static final String SampleShipping="Sample & Shipping";
  public static final String SS="SS";
  public static final String SamplePickUpDelivery="Sample Pick Up / Delivery";
  public static final String SPUD="SPUD";
  public static final String Samplestoragehandling="Sample storage & handling";
  public static final String SASH="SASH";
  public static final String SamplingOnly="Sampling Only";
  public static final String SONY="SONY";
  public static final String SubmittedSample="Submitted Sample(s)";
  public static final String SUBM="SUBM";
  public static final String TankTruckSealing="Tank Truck Sealing";
  public static final String TTSL="TTSL";
  public static final String Technicalassistance="Technical assistance";
  public static final String TEAS="TEAS";
  public static final String TestKit="Test Kit(s)";
  public static final String TSTK="TSTK";
  public static final String Transfer="Transfer";
  public static final String TRAN="TRAN";
  public static final String Transhipment="Transhipment";
  public static final String TRNS="TRNS";
  public static final String VisualInspection="Visual Inspection";
  public static final String VIS="VIS";
  //branch type
  public static final String BRANCH_TYPE="ADMN";
  public static final String OPS_BRANCH_TYPE="Ops";
  public static final String LAB_BRANCH_TYPE="Lab";
  public static final String OPSLAB_BRANCH_TYPE="Ops Lab";
  public static final String ADMIN_BRANCH_TYPE="Admin";
  public static final String OPSCENTER_BRANCH_TYPE="Ops Center";
  public static final String EMP_TYPE="E";
  public static final String DESCR ="General service charge in connection with the";

  public static final String V_INSP = "V-Insp";
  public static final String L_INSP = "L-Insp";
  public static final String VESSEL_INSP = "Vessel-Insp";
  public static final String TOW_MAX = "Tow-Max";
  public static final String INSPECTION_PRICE = "Inspection-Price";
  public static final String SHELL_LOT = "shellLot";
  public static final String SHELL_ADDITIONAL_VESSEL_INSP = "shellAddtnlVessel";
  public static final String SHELL_PRIMARY_VESSEL_INSP = "shellPrimVessel";

  public static final String GROUP = "group";
  public static final String EXTERN = "extern";

  public static final String Petroleum = "Petroleum";
  public static final String Chemical = "Chemical";
  public static final String None = "None";
  public static final String SRDPetroleum = "SRDPetroleum";
  public static final String SRDChemicals = "SRDChemicals";
  public static final String SRDPetroleumBase = "SRDPetroleumBase";
  public static final String SRDChemicalsBase = "SRDChemicalsBase";

  public static final String Harbor = "Harbor";
  public static final String Offshore = "Offshore";
  public static final String No_Lighterage = "No Lighterage";

  public static final String L_HarborDay = "L-HarborDay";
  public static final String L_OffshoreDay = "L-OffshoreDay";

  public static final String PressCylinders = "PressCylinders";

  public static final String JobService = "JobService";
  public static final String StandAloneService = "StandAloneService";
  public static final String CalService = "CalService";
  public static final String MtdService = "MtdService";
  public static final String VesselService = "VesselService";
  public static final String LotLabService = "LotLabService";
  public static final String LotVMMService = "LotVMMService";
  public static final String LotLiquidService = "LotLiquidService";
  public static final String LotDryService = "LotDryService";

  public static final String JOB = "JOB";
  public static final String VESSEL = "VESSEL";
  public static final String LOT = "LOT";

  public static final String TEST = "TEST";
  public static final String SLATE = "SLATE";
  public static final String CE_TEST = "CE_Test";
  
  public static final String DEPOSIT_INVOICE = "Deposits";

  public static String RESULT = "result";
  public static String MESSAGE = "message";
  public static String STATUS = "status";
  public static String SUCCESS = "success";
  public static String ERROR = "error";
  public static String PARAM = "param";

  public static int NULL_SORT_NUM = -9999;
  public static int INSPECTION_CHARGE_START_NO = 1000000;
  public static int SERVICE_CHARGE_START_NO = 3000000;
  public static int TEST_CHARGE_START_NO = 5000000;
  public static int MANUAL_TEST_CHARGE_START_NO = 7000000;
  public static int SLATE_CHARGE_START_NO = 9000000;

  public static int DEFAULT_SORT_NUM = 9999;
  public static int BASE_SORT_NUM = 1000;

  public static String VESSEL_SORT_NUM = "VESSEL_SORT_NUM";
  public static String LOT_SORT_NUM = "LOT_SORT_NUM";
  public static String CHARGE_SORT_NUM = "CHARGE_SORT_NUM";
  public static String VESSEL_NAME = "VESSEL_NAME";
  public static String PRODUCT_NAME = "PRODUCT_NAME";

  public static final double DEFAULT_TEST_SLATE_QTY = 1.0 ;

  public static final String All = "All";

  public static final String CREDIT_INDICATOR_I = "I";
  public static final String CREDIT_INDICATOR_C = "C";
  public static final String CREDIT_INDICATOR_R = "R";
  public static final int RECORDS_PAGE = 20;
  public static final int JOBSEARCH_RECORDS_PAGE = 20;

  public static final String JOB_NUMBER = "jobNumber";

  public static final String SAM = "SAM";
  public static final String LIMS = "LIMS";
  public static final String CONTACT = "CONTACT";
  public static final String CUSTOMER = "CUSTOMER";
  public static final String INVOICE = "INVOICE";

  public static final String ENTITY_KEYS = "ENTITY_KEYS";
 // Commented on27-02-09 due to jobInstructions sample priority default value not blank
 /*public static final String[] staticDropdowns = {"salutation","industryType","workFunction","UOM","option",
                            "plusMinus","sampleLocation","sampleType","containerType",
                            "retainTested","sampleTiming","sampleVolume","origin",
                            "dispatchStatus","jobsearchStatus","jobType","dropdown","priority"};*/
 public static final String[] staticDropdowns = {"salutation","industryType","workFunction","UOM","option",
      "plusMinus","sampleLocation","sampleType","containerType",
      "retainTested","sampleTiming","sampleVolume","origin",
      "dispatchStatus","jobsearchStatus","jobType","dropdown"};
  public static String USER_TIME_FPORMAT = "AM/PM";
  public static String ANTEMERIDIAN = "AM";
  public static String POSTMERIDIAN = "PM";
  public static String DD_MM_YYYY_DATE_FORMAT = "dd/MM/yyyy";
  public static String DD_MMM_YYYY_DATE_FORMAT = "dd/MMM/yyyy";
  public static String MM_DD_YYYY_DATE_FORMAT = "MM/dd/yyyy";
  public static String YYYY_MM_DD_DATE_FORMAT = "yyyy/MM/DD";
  public static String CONTRACT_APPROVED = "Approved";
  public static String CONTRACT_INACTIVE = "InActive";
  public static String CONTRACT_INPROCESS = "InProcess";
  public static String CONTRACT_REJECTED = "Rejected";
  public static String CONTRACT_NULL = "";
  public static String CONTRACT_SUBMITTED = "Submitted";
  public static final String invoiceDir = "invoiceDir";
  public static final String jasperDir = "jasperDir";

  public static String STATUS_APPROVED = "APP";
  public static String STATUS_INACTIVE = "INAC";
  public static String STATUS_INPROCESS = "INPR";
  public static String STATUS_NULL = "0";
  public static String STATUS_REJECTED = "REJ";
  public static String STATUS_SUBMITTED = "SUB";

  public static String INV_PREVIEW_TAB1_SRC = "edit_job_invoice_preview_tab1.htm";
  public static String INV_PREVIEW_TAB2_SRC = "edit_job_invoice_preview_tab2.htm";
  public static String INV_PREVIEW_TAB3_SRC = "edit_job_invoice_preview_tab3.htm";
  public static String INV_PREVIEW_TAB4_SRC = "edit_job_invoice_preview_tab4.htm";
  public static int TWELVE_HOUR = 12;
  public static String  TWENTY_FOURHOUR = "24 Hour";
  public static String  DEFAULT_TIMEZONE = "EST";
  public static String  PHOENIX_TIMEZONE = "CST";
  public static final String UOM = "UOM";
  public static final String OPTION = "option";

  public static final String INV_TYPE_CON = "CON";
  public static final String INV_TYPE_REG = "REG";
  public static final String INV_TYPE_TAX = "TAX";
  public static final String BILL_STATUS_RDY = "RDY";
  public static final String BILL_STATUS_INV = "INV";

  public static final String OVERTIME = "Overtime";
  public static final String RUSH = "Rush";
  public static final String STANDARD = "Standard";
  public static final String SAMPLE_LOCATION = "sampleLocation";

  public static final String jobcontractfilepath = "jobcontractfilepath";
  public static final String contractfilepath = "contractfilepath";

  public static final String CREATE_JOB_PERM = "CreateJob";
  public static final String SEARCH_JOB_PERM = "SearchJob";
  public static final String DEFAULT_URL = "phx_dashboard.htm";
  public static final String DEFAULT_EMAIL_SUB = "Job Status Update";

  public static final String TOTAL_LOT_INSP_PRICE = "TOTAL_LOT_INSP_PRICE";
  public static final String TOTAL_VESSEL_INSP_PRICE = "TOTAL_VESSEL_INSP_PRICE";
  public static final String MAX_LOT_INSP_QTY = "MAX_LOT_INSP_QTY";

  public static final String IS_EDITABLE = "IS_EDITABLE";

  public static final String Tow = "Tow";
  public static final String NoTow = "NoTow";
  public static final String ADDITIONAL_GRADE = "ADDITIONAL_GRADE";

  public static final String IS_INCLUDED_VALUE = "IS_INCLUDED_VALUE";
  public static final String IS_VESS_PR = "IS_VESS_PR";

  public static final String Pricebook = "Pricebook";
  public static final String Custom = "Custom";
  public static final String[] buBranchRoles = {"Multibranch_User_BU"};
  public static final String[] limitedBranchRoles = {"Branch User","Branch User NOINV","Branch User NSR"};

  //added by jilan

  public static final String FORM_COMMAND = "FORM.command";
  public static final String REFRESH_PAGE = "refresh-page";
  public static final String SELECT_PRODUCT_GROUP = "selectProductGroup";
  public static final String CANNOT_CALCULATE_INSPECTION_PRICE = "CANNOT_CALCULATE_INSPECTION_PRICE";
  public static final String SELECT_VESSEL_TYPE = "selectVesselType";
  public static final String RETURN_VALUE = "return";
  public static final String SUBMIT_VALUE = "submit";
  public static final String SEARCH_VALUE = "search";
  public static final String SELECT_SERVICE ="selectService";
  public static final String YEAR_MONTH_DATE_DFORMATE =Constants.DATE_PATTERN_0;
  public static final String COMMON_MESSAGE = "common-message";
  public static final String COMMON_MESSAGEPOPUP = "common-messagepopup";
  public static final String SELECT_BU = "selectBU";
  public static final String NONE_VALUE = "none";
  public static final String SELECT_CUSTOMER = "selectCustomer";
  public static final String SELECT_CONTACT = "selectContact";
  public static final String SELECT_CURRENCY = "selectCurrency";
  public static final String SELECT_BANK = "selectBank";
  public static final String SELECT_BILL_STATUS = "selectBillStatus";
  public static final String SELECT_DATE = "selectDate";
  public static final String GENERATE = "generate";
  public static final String SAVE = "save";
  public static final String COMMON_MESSAGE_R = "common-message-r";
  /*public static final String userService = "userService";*/
  public static final String ATTACH = "attach";
  public static final String DETACH = "detach";
  public static final String TRUE_VALUE = "true";
  public static final String FALSE_VALUE = "false";
  public static final String CONINVS = "coninvs";
  public static final String TRACKER_RES = "TrackerRes";
  public static final String CO_HEADING = "CoHeading";
  public static final String ORIGIN_FLAG = "originFlag";
  public static final String SIG_FLAG = "sigFlag";
  public static final String CUSTVAL = "custval";
  public static final String NEWVAL = "newval";
  public static final String ADD = "add";
  public static final String DELETE = "delete";
  public static final String ADD_CONTACT = "addContact";
  public static final String DELETE_CONTACT = "deleteContact";
  public static final String CONFIG = "config";
  public static final String CONTRACT_FILE_PATH = "contractfilepath";
  public static final String DELETE_ATTACH = "deleteAttach";
  public static final String CHANGE_PRICE_BOOK_ID = "changePriceBookId";
  public static final String CHANGE_PRICE_BOOK_SERIES = "changePriceBookSeries";
  public static final String CHANGE_CURRENCY_CD = "changeCurrencyCD";
  public static final String ADD_CFG_CONTRACT = "addCfgContract";
  public static final String CHANGE_PRODUCT_GROUP_SET = "changeProductGroupSet";
  public static final String CHANGE_VESSEL_TYPE_SET = "changeVesselTypeSet";
  public static final String ADD_ZONE = "addZone";
  public static final String DELETE_ZONE = "deleteZone";
  public static final String ADD_LOCATION_DISCOUNT = "addLocationDiscount";
  public static final String DELETE_LOCATION_DISCOUNT = "deleteLocationDiscount";
  public static final String ADD_BRANCH_LOCATION = "addBranchLocation";
  public static final String DELETE_BRANCH_LOCATION = "deleteBranchLocation";
  public static final String ADD_PORT_LOCATION = "addPortLocation";
  public static final String DELETE_PORT_LOCATION = "deletePortLocation";
  public static final String SAVE_CONTRACT = "saveContract";
  public static final String STATE_MACHINE_MGR = "stateMachineMgr";
  public static final String COUNTRY_CODE = "countryCode";
  public static final String COUNTRYS = "Countrys";
  public static final String COUN_HEADING = "CounHeading";
  public static final String JOB_TIME_FORMAT = "jobTimeFormat";
  public static final String DATE_FORMAT = "dateFormat";
  public static final String LOGIN_NAME = "loginName";
  public static final String EMAIL = "email";
  public static final String CONTENT = "Content";
  public static final String COMMON_MESSAGEPOPUP_R = "common-messagepopup-r";
  public static final String CONTRACT_STATE_MACHINE = "ContractStateMachine";
  public static final String CONTRACT_REFRESH_PAGE = "contract-refresh-page";
  public static final String OPERATION_TYPE = "operationType";
  public static final String DATA = "data";
  public static final String CON_HEADING = "ConHeading";
  public static final String CONTENT_TYPE = "ContentType";
  public static final String X_DOWNLOAD = "x-download";
  public static final String FILE_NAME = "FileName";
  public static final String FILE_VIEW = "fileView";
  public static final String ORIGINAL_FILE_NAME = "OriginalFileName";
  public static final String SHOW_ATTACH = "showAttach";
  public static final String INDEX = "index";
  public static final String OTHER =  "Other";

  public static final String ADD_ADDR = "addAddr";
  public static final String DELETE_ADDR = "deleteAddr";
  public static final String ADD_ADDR_DTLS = "addAddrDtls";
  public static final String DELETE_ADDR_DTLS = "deleteAddrDtls";
  public static final String CONTRACT_FLAG = "contractFlag";
  public static final String BUS = "bus";
  public static final String NEW_LOWERCASE = "new";
  public static final String USA = "USA";
  public static final String CAN = "CAN";
  public static final String NLD = "NLD";
  public static final String BEL = "BEL";
  public static final String YES_Y = "Y";
  public static final String NO_N = "N";
  public static final String NEW_N_CAPITAL = "New";
  public static final String CREDIT = "CREDIT";
  public static final String COLLECT = "COLLECT";
  public static final String CRE = "CRE";
  public static final String NEW_CONTACT = "newcontact";
  public static final String NEW_CONTACT_BODY = "newcontactbody";
  public static final String ADDRESS_DESCR = "addressDescr";
  public static final String SEQID_LOCATION_NUMBER = "custAddrSeqId.locationNumber";
  public static final String CUST_CODE = "custCode";
  public static final String CONTRACT_CODE = "contractCode";
  public static final String CREDIT_ANALYST_CODE = "creditAnalystCode";
  public static final String COLLECTOR_CODE = "collectorCode";
  public static final String TAX_CODE = "taxCode";
  public static final String EFF_DATE = "effDate";
  public static final String TAX_PCT = "taxPct";
  public static final String NAV = "nav";
  public static final String FNAME_SORT = "Fnamesort";
  public static final String LNAME_SORT = "Lnamesort";
  public static final String ADDRESS_SEQ = "addressSeq";
  public static final String ADDRESS_DESC = "addressDesc";
  public static final String R_STATUS = "rstatus";
  public static final String CON_CONTRACT_CODE = "con.contractCode";
  public static final String CON_DESCRIPTION = "con.description";
  public static final String VESSEL_SMALL_LETTERS = "Vessel";
  public static final String NEW_CONTACT_VAL = "newcontactval";
  public static final String EXISTING_ADDR = "existingAddr";
  public static final String PRIMARY_ADDR = "primaryAddr";
  public static final String NEW_ADDR = "newAddr";
  public static final String UNDEFINED = "undefined";
  public static final String CONTACTS = "Contacts";
  public static final String CONSL_INV_CREATE_FORM = "conslInvCreateForm";
  public static final String CONSL_INV_EDIT_FORM = "conslInvEditForm";
  public static final String C_HEADING = "CHeading";
  public static final String JOB_TYPES = "jobtypes";
  public static final String J_T_HEADING = "JTHeading";
  public static final String OPERATION = "Operation";
  public static final String O_HEADING = "OHeading";
  public static final String OK = "ok";
  public static final String S_L_HEADING = "SLHeading";
  public static final String PREV = "Prev";
  public static final String JOB_ORDER_SHIPPING_AGENT_NAME = "jobOrder.shippingAgent.name";
  public static final String SHIPPING = "Shipping";
  public static final String S_HEADING = "SHeading";

  public static final String LOAD_LAST_FIVE = "loadLastFive";
  public static final String INVOICE_REPRINT = "invoiceReprint";
  public static final String YEAR_MONTH_DATE_TIME = "yyyy-MM-dd H:mm:ss";
  public static final String POST = "POST";
  public static final String MONTH_MM_FORMATE = "mm";
  public static final String MONTH_MMM_FORMATE = "mmm";
  public static final String JXCEL = "jxcel";
  public static final String MONTH_DATE_YEAR_TIME = "MM/dd/yyyy H:mm:ss";
  public static final String I_HEADING = "IHeading";
  public static final String INVOICES = "Invoices";
  public static final String UPDATE = "update";

  public static final String INVOICE_PREVIEW_REFRESH_PAGE = "invoice-preview-refresh-page";
  public static final String INTERNAL = "internal";
  public static final String TMP_SEARCH_FLAG = "tmpSearchFlag";
  public static final String WARN = "warn";
  public static final String WARNED = "warned";
  public static final String NAVIGATE = "navigate";
  public static final String MASS_DELETE = "massDelete";
  public static final String COPY_VESSEL = "copyVessel";
  public static final String COPY_PRODUCT = "copyProduct";
  public static final String COPY_SAMPLE = "copySample";
  public static final String LOCATION_L_CAP = "Location";

  public static final String YES_SMALL_LETTERS = "yes";
  public static final String DUMMY = "dummy";
  public static final String UNIQUE_FLAG = "uniqueFlag";
  public static final String NONE_CAPITAL_LETTERS = "NONE";
  public static final String DATE_FLAG = "dateFlag";
  public static final String CONT_FLAG = "contFlag";
  public static final String BANK_FLAG = "bankFlag";
  public static final String OPERATION_SMALL_LETTERS = "operation";
  public static final String JOB_VAL = "jobval";
  public static final String DEL_VAL = "delval";
  public static final String JOB_DATE = "jobDate";
  public static final String ENGLISH = "English";
  public static final String HYDRO = "Hydro";
  public static final String SHIPPING_AGENT = "shippingAgent";
  public static final String SERVICE_LOCATION_VALUE = "servicelocation";
  public static final String PORT_LOCATION = "portlocation";
  public static final String TOWING_COMPANY = "towingCompany";
  public static final String GENERATE_INVOICE = "generateInvoice";
  public static final String CALC = "calc";
  public static final String NEXT = "next";
  public static final String VESSEL_SMALL_LETTER = "Vessel";
  public static final String NO_CAPITAL_LETTER = "NO";
  public static final String YES_CAPITAL_LETTER = "YES";
  public static final String PREV_SMALL_LETTER = "prev";
  public static final String MONTHLY_ID = "monthlyid";
  public static final String MAIN = "Main";
  public static final String DISPATCH = "Dispatch";

  public static final String BILLING = "Billing";
  public static final String PROCESS_LOG = "Process Log";
  public static final String DEFAULT_LIST = "DefaultList";
  public static final String OPS_BRANCH = "opsBranch";
  public static final String LAB_BRANCH = "labBranch";
  public static final String ASSOC = "assoc";
  public static final String ADD_ASSOC_BRANCH = "addAssocBranch";
  public static final String OPSC = "OPSC";
  public static final String B_I_HEADING = "BIHeading";
  public static final String BRANCH_INT = "BranchInt";
  public static final String PAGE_FLAG =  "pageFlag";
  public static final String TAXARTICLES = "Taxarticles";
  public static final String T_HEADING = "THeading";
  public static final String BRANCH_CHANGE = "branchChange";
  public static final String ADD_NEW = "addNew";
  public static final String V = "V";
  public static final String VAT = "Vat";
  public static final String SALES = "Sales";
  public static final String V_HEADING = "VHeading";
  public static final String ST_HEADING = "StHeading";
  public static final String BRANCHE = "Branche";
  public static final String BR_HEADING = "BrHeading";
  public static final String BRANCH_FORM = "branchForm";
  public static final String BRANCH_SMALL_LETTERS = "Branch";
  public static final String SERVICE_LOCATION_CREATE_FORM = "serviceLocationCreateForm";
  public static final String OP_INSTR_FORM = "opInstrForm";
  public static final String BUSINESS_UNIT = "BusinessUnit";
  public static final String BU_HEADING = "BuHeading";
  public static final String PERMISSION = "Permission";
  public static final String P_HEADING = "PHeading";
  public static final String ROLES = "Roles";
  public static final String R_HEADING = "RHeading";
  public static final String USER_SMALL_LETTERS = "User";
  public static final String U_HEADING = "UHeading";
  public static final String SERVLOC = "servloc";
  public static final String S = "S";
  public static final String LOG_STR = "log";
  public static final String CONTRACT_STR = "contract";

  public static final String UOM_METRIC_TON_VALUE = "3";
  public static final String UOM_LONG_TON_VALUE = "1";

  public static final String OVERRIDE_CURRENCY_RATE = "OVERRIDE_CURRENCY_RATE";
  
  public static final String GENERATE_INVOICE_ACTION = "generateInvoice";
  public static final String PREVIEW_INVOICE_ACTION = "previewInvoice";
  public static final String GENERATE_DEPOSIT_INVOICE_ACTION = "generateDepositInvoice";
  public static final String SAVE_ACTION = "save";
  public static final String NEXT_ACTION = "next";
  public static final String DEL_SPLIT_ITEM_ACTION = "deleteSplitLineItem";
  public static final String ADD_SPLIT_ITEM_ACTION = "addSplitLineItem";
  public static final String DEL_LINE_ITEM_ACTION = "deleteLineItem";

  public static final String CE_JOB_INV_PREVIEW_TAB0 = "phx_ce_job_invoice_preview_tab0.htm";
  public static final String CE_JOB_INV_PREVIEW_TAB1 = "phx_ce_job_invoice_preview_tab1.htm";
  public static final String CE_JOB_INV_PREVIEW_TAB2 = "phx_ce_job_invoice_preview_tab2.htm";
  public static final String CE_JOB_INV_PREVIEW_TAB3 = "phx_ce_job_invoice_preview_tab3.htm";
  public static final String CE_JOB_INV_PREVIEW_TAB4 = "phx_ce_job_invoice_preview_tab4.htm";
  
 public static final String machineName;
 public static final String evnFlag;

  public static final String T = "T";
  public static final String A = "A";
  public static final String U = "U";

  public static final String INCLUDE_ALL = "IncludeAll";
  public static final String HIDE = "HIDE";
  public static final String ADDL = "ADDL";
  public static final String VIEW_ROLE = "ViewRole";

  public static final String Service_Rate_ID_Seq = "Service_Rate_ID_Seq";
  public static final String Inspection_Rate_ID_Seq = "Inspection_Rate_ID_Seq";

  public static final String Notes = "Notes";
  public static final String NTS = "NTS";
  public static final Set enableVesselSet = new HashSet();
  public static final Set enableProductGroupSet = new HashSet();

  /* For iTrack#134877-START */
  public static final String sampleInstrSeparator = "-------------------------------------------------------------------------------";
  /* For iTrack#134877-END */
  /* For iTrack#135193-START */
  public static final String AccountOwnerRole = "AccountOwner";
  public static final String TIER2 = "Tier2";
  public static final String OAC = "OAC";
  /* For iTrack#135193-START */
  public static final long JOB_LOCK_TIME_AMOUNT=2*60*60*1000; //in mili seconds
  static{
    PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("config");
    machineName=pRB.getString("MACHINE_NAME");
    evnFlag=pRB.getString("ENV_FLAG");

    enableVesselSet.add("FSLB");
    enableVesselSet.add("FSTL");
    enableVesselSet.add("INDR");
    enableVesselSet.add("INLQ");
    enableVesselSet.add("IRT");
    enableVesselSet.add("OTLB");
    enableVesselSet.add("OUTL");
    enableVesselSet.add("INSV");

    enableProductGroupSet.add("FSLB");
    enableProductGroupSet.add("FSTL");
    enableProductGroupSet.add("INDR");
    enableProductGroupSet.add("INLQ");
    enableProductGroupSet.add("IRT");
    enableProductGroupSet.add("OTLB");
    enableProductGroupSet.add("OUTL");
  }
}

