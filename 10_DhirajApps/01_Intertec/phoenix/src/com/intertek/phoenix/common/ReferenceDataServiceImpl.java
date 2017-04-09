/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.intertek.entity.ProductGroup;
import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.dao.FilterOp;
import com.intertek.phoenix.dao.LogicOp;
import com.intertek.phoenix.entity.BillingStatus;
import com.intertek.phoenix.entity.DepositType;
import com.intertek.phoenix.entity.InvoiceGenerationType;
import com.intertek.phoenix.entity.InvoiceStatus;
import com.intertek.phoenix.entity.InvoiceType;
import com.intertek.phoenix.entity.NoteType;
import com.intertek.phoenix.entity.OperationalStatus;
import com.intertek.phoenix.entity.OrderOrigin;
import com.intertek.phoenix.entity.OrderStatus;
import com.intertek.phoenix.entity.PaymentStatus;
import com.intertek.phoenix.entity.PaymentType;
import com.intertek.phoenix.entity.Product;
import com.intertek.phoenix.entity.ProjectType;
import com.intertek.phoenix.entity.SourceOrigin;
import com.intertek.phoenix.entity.TaskOperationalStatus;
import com.intertek.phoenix.entity.UOM;
import com.intertek.phoenix.entity.UserType;
import com.intertek.phoenix.entity.Visibility;
import com.intertek.phoenix.search.SearchKey;
import com.intertek.phoenix.util.CommonUtil;

/**
 * @author eric.nguyen
 */
public class ReferenceDataServiceImpl implements ReferenceDataService {
    protected HashMap<DropdownName, List<Field>> dropdowns;

    // TODO: Eric - remove this tempData
    public static final String[][] tempData = new String[][] { { "UOM", "EACH", "Each" }, { "UOM", "HOUR", "Hour" }, { "UOM", "PER_DAY", "Per Day" },
                                                              { "UOM", "SHIFT", "Shift" },{ "activeStatus", "A", "Active" }, { "activeStatus", "I", "Inactive" },
                                                              { "audienceType", "ALL", "All" }, { "audienceType", "EXTL", "External" },
                                                              { "audienceType", "INTL", "Internal" }, { "audienceType", "NONE", "None" },
                                                              { "branchtype", "ADMN", "Admin" }, { "branchtype", "LAB", "Lab" },
                                                              { "branchtype", "OPS", "Ops" }, { "branchtype", "OPSC", "Ops Center" },
                                                              { "branchtype", "OPSL", "OpsLab" }, { "communication", "Call", "Call" },
                                                              { "communication", "Email", "Email" }, { "communication", "Fax", "Fax" },
                                                              { "communication", "Internet", "Internet" }, { "conslInvStatus", "CAN", "Cancelled" },
                                                              { "conslInvStatus", "INV", "Invoiced" }, { "conslInvStatus", "NEW", "New" },
                                                              { "conslInvStatus", "RDY", "Ready" }, { "contactFlag", "External", "External" },
                                                              { "contactFlag", "Internal", "Internal" }, { "containerType", "AMB", "Amber Bottle(s)" },
                                                              { "containerType", "CAN", "Can(s)" }, { "containerType", "CLR", "Clear Bottle(s)" },
                                                              { "containerType", "CYL", "Cylinder(s)" },
                                                              { "containerType", "EPXL", "Epoxy Lined Container(s)" },
                                                              { "containerType", "IRAT", "IATA Can(s)" }, { "containerType", "PLB", "Plastic Bottle(s)" },
                                                              { "containerType", "VPB", "Vapor Bag(s)" }, { "contractAttachType", "ADMN", "Admin" },
                                                              { "contractAttachType", "BILL", "Billing" }, { "contractAttachType", "FORM", "Forms" },
                                                              { "contractAttachType", "OPER", "Operations" }, { "contractStatus", "0", "" },
                                                              { "contractStatus", "APP", "Approved" }, { "contractStatus", "INAC", "Inactive" },
                                                              { "contractStatus", "INPR", "In Process" }, { "contractStatus", "REJ", "Rejected" },
                                                              { "contractStatus", "SUB", "Submitted" }, { "customerType", "A", "A = $2MM and up" },
                                                              { "customerType", "B", "B = $1MM - $2MM" }, { "customerType", "C", "C = $750K - $1MM" },
                                                              { "customerType", "D", "D = $500K - $750K" }, { "customerType", "E", "E = $250 - $500K" },
                                                              { "customerType", "F", "F = $0 - $250K" }, { "dateFormat", "MM/dd/yyyy", "MM/dd/yyyy" },
                                                              { "dateFormat", "dd/MM/yyyy", "dd/MM/yyyy" }, { "dateFormat", "dd/MMM/yyyy", "dd/MMM/yyyy" },
                                                              { "depositType", "WIRE", "Wire" }, { "depositType", "CC", "Credit Card" },
                                                              { "depositType", "CASH", "Cash" }, { "dispatchStatus", "CMPJ", "01-Completed Job" },
                                                              { "dispatchStatus", "IJOB", "05-Incoming Job" }, { "dispatchStatus", "JBUN", "04-Job Up Next" },
                                                              { "dispatchStatus", "JWG", "03-Job Working" },
                                                              { "dispatchStatus", "WNPW", "02-Waiting on Paperwork" },
                                                              { "dropdown", "UOM", "Job Instructions - Quantity - UOM" },
                                                              { "dropdown", "activeStatus", "Others - Active/Inactive Status" },
                                                              { "dropdown", "audienceType", "Contract - Audience Type" },
                                                              { "dropdown", "communication", "Contact - Communication Flag" },
                                                              { "dropdown", "conslInvStatus", "Consolidated Invoice - Status" },
                                                              { "dropdown", "contactFlag", "Contact - Contact Flag" },
                                                              { "dropdown", "containerType", "Job Instructions - Sample - Container Type" },
                                                              { "dropdown", "contractAttachType", "Contract - Attachment Type" },
                                                              { "dropdown", "contractStatus", "Contract - Status" },
                                                              { "dropdown", "customerType", "Customer - Customer Type" },
                                                              { "dropdown", "dateFormat", "User - Date Format" },
                                                              { "dropdown", "dispatchStatus", "Job Log - Dispatch Status" },
                                                              { "dropdown", "employeeStatus", "User - Employee Status" },
                                                              { "dropdown", "employeeType", "User - Employee Type" },
                                                              { "dropdown", "fullPartTime", "User - Full/Part Time" },
                                                              { "dropdown", "industryType", "Customer - Industry Type" },
                                                              { "dropdown", "invoiceType", "Others - Invoice Type" },
                                                              { "dropdown", "jobContractStatus", "Job Entry - Add Customer - Job Contract Status" },
                                                              { "dropdown", "jobStatus", "Job Entry/Job Instructions/View Invoice - Job Status" },
                                                              { "dropdown", "jobTimeFormat", "Job Entry - Time Format - AM/PM" },
                                                              { "dropdown", "jobType", "Job Search - Job Type" },
                                                              { "dropdown", "joblogStatus", "Job Log - Status" },
                                                              { "dropdown", "jobsearchStatus", "Job Search - Status" },
                                                              { "dropdown", "locationType", "Customer - Location Type" },
                                                              { "dropdown", "noteType", "Job Entry - Add Customer - Add Note - Note Type" },
                                                              { "dropdown", "operator", "Search - Operator" },
                                                              { "dropdown", "option", "Job Instructions - Quantity - Option" },
                                                              { "dropdown", "origin", "Job Entry - Add Customer - Add Note - Origin" },
                                                              { "dropdown", "paymentType", "Job Entry - Add Customer - Payment Type" },
                                                              { "dropdown", "plusMinus", "Job Instructions - Quantity - PlusMinus" },
                                                              { "dropdown", "priority", "Job Instructions - Sample - Priority" },
                                                              { "dropdown", "productType", "Job Entry - Add Customer - Product Type" },
                                                              { "dropdown", "regularTemp", "User - Regular/Temp" },
                                                              { "dropdown", "retainTested", "Job Instructions - Sample - Retain/Tested" },
                                                              { "dropdown", "salutation", "Contact - Salutation" },
                                                              { "dropdown", "sampleLocation", "Job Instructions - Sample - SampleLocation" },
                                                              { "dropdown", "sampleTiming", "Job Instructions - Sample - SampleTiming" },
                                                              { "dropdown", "sampleType", "Job Instructions - Sample - SampleType" },
                                                              { "dropdown", "sampleVolume", "Job Instructions - Sample - sampleVolume" },
                                                              { "dropdown", "searchfields", "Job Entry - Search Type" },
                                                              { "dropdown", "selectedLanguage", "Job Entry - Add Customer - Selected Language" },
                                                              { "dropdown", "timeFormat", "User - Time Format" },
                                                              { "dropdown", "treatServiceforForeignBuyer", "Country - TreatServiceforForeignBuyer" },
                                                              { "dropdown", "visibility", "Job Entry - Add Customer - Add Note - Visibility" },
                                                              { "dropdown", "workFunction", "User - Worker Function" },
                                                              { "dropdown", "workingPb", "Contract - Working PB" },
                                                              { "dropdown", "workingUOM", "Contract - Working UOM" },
                                                              { "dropdown", "yesNo", "Job Instructions - Yes/No" }, { "employeeStatus", "1", "Active" },
                                                              { "employeeStatus", "2", "Terminated" }, { "employeeType", "E", "Employee" },
                                                              { "employeeType", "N", "Non_Employee" }, { "fullPartTime", "1", "Full-Time" },
                                                              { "fullPartTime", "2", "Part-Time" }, { "industryType", "Agents", "Agents" },
                                                              { "industryType", "Bunkering", "Bunkering" },
                                                              { "industryType", "Car Dealers/Suppliers", "Car Dealers/Suppliers" },
                                                              { "industryType", "Chemical Plants", "Chemical Plants" },
                                                              { "industryType", "Distributors", "Distributors" },
                                                              { "industryType", "Educational Institutions", "Educational Institutions" },
                                                              { "industryType", "Emergency Response", "Emergency Response" },
                                                              { "industryType", "Engineering", "Engineering" },
                                                              { "industryType", "Enviromental", "Enviromental" },
                                                              { "industryType", "Exploration/Production", "Exploration/Production" },
                                                              { "industryType", "Freight Forwarders", "Freight Forwarders" },
                                                              { "industryType", "Government", "Government" }, { "industryType", "Healthcare", "Healthcare" },
                                                              { "industryType", "Homecare", "Homecare" },
                                                              { "industryType", "Inspection Company", "Inspection Company" },
                                                              { "industryType", "Insurance", "Insurance" }, { "industryType", "Laboratory", "Laboratory" },
                                                              { "industryType", "Legal (Lawyers)", "Legal (Lawyers)" },
                                                              { "industryType", "Manufacturers", "Manufacturers" },
                                                              { "industryType", "Marine Surveyors", "Marine Surveyors" },
                                                              { "industryType", "Military", "Military" },
                                                              { "industryType", "Pharmaceuticals", "Pharmaceuticals" },
                                                              { "industryType", "Public Terminals", "Public Terminals" },
                                                              { "industryType", "Rail Owners", "Rail Owners" },
                                                              { "industryType", "Re-Claimers (Slop)", "Re-Claimers (Slop)" },
                                                              { "industryType", "Recycling", "Recycling" }, { "industryType", "Refineries", "Refineries" },
                                                              { "industryType", "Retail", "Retail" },
                                                              { "industryType", "Shipyards/Cleaning Facilities", "Shipyards/Cleaning Facilities" },
                                                              { "industryType", "Traders", "Traders" }, { "industryType", "Truck Owners", "Truck Owners" },
                                                              { "industryType", "Vessel Owners/Charterers", "Vessel Owners/Charterers" },
                                                              { "invoiceType", "REG", "Regular" }, { "invoiceType", "CON", "Consolidated" },
                                                              { "jobContractStatus", "0", "" }, { "jobContractStatus", "1", "Cancelled" },
                                                              { "jobContractStatus", "1000", "Open" }, { "jobContractStatus", "6000", "Invoiced" },
                                                              { "jobContractStatus", "7100", "Rebilled" }, { "jobContractStatus", "7200", "Credited" },
                                                              { "jobStatus", "0", "New" }, { "jobStatus", "1", "Cancelled" }, { "jobStatus", "1000", "Open" },
                                                              { "jobStatus", "6000", "Closed" }, { "jobStatus", "7000", "Reopened" },
                                                              { "jobTimeFormat", "AM", "AM" }, { "jobTimeFormat", "PM", "PM" },
                                                              { "jobType", "FST", "OCA, AS & MN FST" }, { "jobType", "INSP", "OCA & MN Inspection & Testing" },
                                                              { "jobType", "MAR", "OCA Marine & Calibrations" }, { "jobType", "OUT", "AS & MN Testing" },
                                                              { "jobType", "CE", "Commercial and Electrical" }, { "joblogStatus", "1", "Cancelled" },
                                                              { "joblogStatus", "1000", "Open" }, { "joblogStatus", "4", "Open/Reopened" },
                                                              { "joblogStatus", "5", "All" }, { "joblogStatus", "6000", "Closed" },
                                                              { "joblogStatus", "7000", "Reopened" }, { "jobsearchStatus", "1000", "Open" },
                                                              { "jobsearchStatus", "1", "Cancelled" }, { "jobsearchStatus", "4", "Open/Reopened" },
                                                              { "jobsearchStatus", "5", "All" }, { "jobsearchStatus", "6000", "Closed" },
                                                              { "jobsearchStatus", "7000", "Reopened" }, { "locationType", "03", "Branch" },
                                                              { "locationType", "04", "Headquarters" }, { "noteType", "BILL", "Billing" },
                                                              { "noteType", "CALL", "Customer Call" }, { "noteType", "CMMT", "Comment" },
                                                              { "noteType", "INVC", "Invoice" }, { "noteType", "PPRC", "Pricing" },
                                                              { "noteType", "RSRC", "Research" }, { "operator", "1", "begins with" },
                                                              { "operator", "2", "contains" }, { "operator", "3", "=" }, { "operator", "4", "not =" },
                                                              { "option", "APPR", "APPROX" }, { "option", "BOL", "Bill of Lading" },
                                                              { "option", "EXCT", "EXACTLY" }, { "option", "MAX", "MAX" }, { "option", "MIMA", "MIN/MAX" },
                                                              { "option", "MIN", "MIN" }, { "option", "VESL", "Load Port Vessel" },
                                                              { "origin", "EML", "Email" }, { "origin", "FAX", "Fax" }, { "origin", "IM", "IM" },
                                                              { "origin", "MYCB", "MyCB" }, { "origin", "PTRO", "Petro" }, { "origin", "TRAN", "Stand Ordr" },
                                                              { "origin", "VRBL", "Verbal" }, { "origin", "WEB", "Web" }, { "origin", "WEBP", "Web Phone" },
                                                              { "origin", "WSAM", "With Samp" }, { "origin", "PHONE", "Phone" },
                                                              { "paymentType", "CHE", "Check" }, { "paymentType", "COD", "Cash" },
                                                              { "paymentType", "CRE", "Credit Card" }, { "paymentType", "DEP", "Deposit Invoice" },
                                                              { "paymentType", "EFT", "EFT" }, { "paymentType", "OAC", "On Account" },
                                                              { "plusMinus", "+", "+" }, { "plusMinus", "+/-", "+/-" }, { "plusMinus", "-", "-" },
                                                              { "priority", "Overtime", "Overtime" }, { "priority", "Rush", "Rush" },
                                                              { "priority", "Standard", "Standard" }, { "productType", "AGRI", "AGRI" },
                                                              { "productType", "BIOFUELS", "Bio-Fuels" }, { "productType", "CHEMICAL", "Specialty Chemical" },
                                                              { "productType", "DOWNSTRM", "Oil & Gas - Downstream" },
                                                              { "productType", "EECOMMERCIAL", "E & E Commercial" },
                                                              { "productType", "EECONSUMER", "E & E Consumer" }, { "productType", "ENERGY", "Energy" },
                                                              { "productType", "FOOD", "Food " }, { "productType", "GOVERNMENT", "Government Services" },
                                                              { "productType", "HYDRO", "HYDRO" }, { "productType", "INDUSTRIAL", "Industrial Services" },
                                                              { "productType", "ITTELECOMS", "IT & Telecoms" }, { "productType", "LNG", "LNG" },
                                                              { "productType", "MINERAL", "Mineral " }, { "productType", "OTHERINDUSTRY", "Other Industries" },
                                                              { "productType", "PHARMA", "Pharma" }, { "productType", "TEXTFOOT", "Textiles & Footwear" },
                                                              { "productType", "TOYSHARD", "Toys and Hardlines" }, { "product", "Roofing", "Roofing" },
                                                              { "product", "Gypsum", "Gypsum" }, { "product", "Cladding", "Cladding" },
                                                              { "product", "Expanded Polystyrene (EPS)", "Expanded Polystyrene (EPS)" },
                                                              { "product", "Steel Studs", "Steel Studs" },
                                                              { "product", "Penetrations/Expanded Joints", "Penetrations/Expanded Joints" },
                                                              { "product", "Insulation", "Insulation" },
                                                              { "product", "Dampproofing and Waterproofing ", "Dampproofing and Waterproofing " },
                                                              { "product", "Panel Products", "Panel Products" },
                                                              { "product", "Caulks and Sealants", "Caulks and Sealants" },
                                                              { "product", "Wall Systems", "Wall Systems" },
                                                              { "product", "Composite Lumber/Decking/Grating", "Composite Lumber/Decking/Grating" },
                                                              { "product", "Insulated Concrete Forms", "Insulated Concrete Forms" },
                                                              { "product", "Flooring", "Flooring" },
                                                              { "product", "Paints/Coatings/Finishes", "Paints/Coatings/Finishes" },
                                                              { "product", "Plastics", "Plastics" }, { "regularTemp", "1", "Regular" },
                                                              { "regularTemp", "2", "Temporary" }, { "retainTested", "R", "Retain Sample(s)" },
                                                              { "retainTested", "S", "Dispatch Sample(s)" }, { "retainTested", "T", "Test Sample(s)" },
                                                              { "salutation", "DR", "Dr." }, { "salutation", "MISS", "Miss" }, { "salutation", "MR", "Mr." },
                                                              { "salutation", "MRS", "Mrs." }, { "salutation", "MS", "Ms." },
                                                              { "sampleLocation", "ASRV", "Analytical Services" }, { "sampleLocation", "BRG", "Barge" },
                                                              { "sampleLocation", "FST", "FST" }, { "sampleLocation", "INLN", "Inline" },
                                                              { "sampleLocation", "ISO", "ISO Container" }, { "sampleLocation", "LINE", "Line" },
                                                              { "sampleLocation", "MNFD", "Manifold" }, { "sampleLocation", "OTH", "Other" },
                                                              { "sampleLocation", "RAIL", "Railcar" }, { "sampleLocation", "SHTK", "Shore Tank" },
                                                              { "sampleLocation", "TATR", "Tank Truck" }, { "sampleLocation", "VESL", "Vessel" },
                                                              { "sampleTiming", "1BEF", "Before" }, { "sampleTiming", "1_FT", "1st Foot" },
                                                              { "sampleTiming", "2DUR", "During" }, { "sampleTiming", "2_3F", "2/3 Full" },
                                                              { "sampleTiming", "3AFT", "After" }, { "sampleTiming", "4ARR", "Arrival" },
                                                              { "sampleTiming", "5DPT", "Departure" }, { "sampleTiming", "6CPF", "Commencement" },
                                                              { "sampleTiming", "7SPO", "Sample Only" }, { "sampleTiming", "8PRE", "Pre-Sample" },
                                                              { "sampleType", "01AL", "All Level" }, { "sampleType", "02RL", "RVP All Level " },
                                                              { "sampleType", "03HL", "H2S All Level" }, { "sampleType", "04RA", "Running Average" },
                                                              { "sampleType", "05RR", "RVP Running Average " }, { "sampleType", "06HR", "H2S Running" },
                                                              { "sampleType", "07SF", "Surface" }, { "sampleType", "08TS", "Top Sample" },
                                                              { "sampleType", "09UP", "Upper" }, { "sampleType", "10MD", "Middle" },
                                                              { "sampleType", "11LW", "Lower" }, { "sampleType", "12UA", "U,M,L,Average" },
                                                              { "sampleType", "13ST", "U,M,L,Suction" }, { "sampleType", "13U1", "U,M,L,1ft" },
                                                              { "sampleType", "13UD", "U,M,L,Dead Bottom" }, { "sampleType", "13UM", "Upper, Middle, Lower" },
                                                              { "sampleType", "14DB", "Dead Bottom" }, { "sampleType", "15DL", "Dockline" },
                                                              { "sampleType", "16IS", "Inline Sampler" }, { "sampleType", "18FF", "First Foot" },
                                                              { "sampleType", "19PS", "Pumpstack" }, { "sampleType", "20OT", "Other" },
                                                              { "sampleType", "21FW", "Freewater" }, { "sampleType", "22OL", "Outlet" },
                                                              { "sampleType", "23FR", "Floating Roof" }, { "sampleType", "24CL", "Clearance" },
                                                              { "sampleType", "25LD", "Line Drip Sample During Load" },
                                                              { "sampleType", "26SP", "Spot Sample" }, { "sampleType", "27CP", "Composite" },
                                                              { "sampleType", "28SS", "Submitted Sample" }, { "sampleType", "31US", "U,M,L,1ft,S" },
                                                              { "sampleVolume", "08OZ", "8oz" }, { "sampleVolume", "100C", "100cc" },
                                                              { "sampleVolume", "1GAL", "1 Gallon" }, { "sampleVolume", "1LTR", "1 Litre" },
                                                              { "sampleVolume", "1PNT", "1 Pint" }, { "sampleVolume", "1QRT", "1 Quart" },
                                                              { "sampleVolume", "250C", "250cc" }, { "sampleVolume", "25LT", "2.5 Litre" },
                                                              { "sampleVolume", "2GAL", "2 Gallon" }, { "sampleVolume", "3000", "3000cc" },
                                                              { "sampleVolume", "300C", "300cc" }, { "sampleVolume", "3GAL", "3 Gallon" },
                                                              { "sampleVolume", "450C", "450cc" }, { "sampleVolume", "4LTR", "4 Litre" },
                                                              { "sampleVolume", "500C", "500cc" }, { "sampleVolume", "50CC", "50cc" },
                                                              { "sampleVolume", "5GAL", "5 Gallon" }, { "sampleVolume", "5LTR", "5 Litre" },
                                                              { "sampleVolume", "HGAL", "1/2 Gallon" }, { "searchfields", "1", "Job ID" },
                                                              { "searchfields", "2", "Invoice" }, { "searchfields", "3", "Quote" },
                                                              { "searchfields", "4", "ECS Order" }, { "selectedLanguage", "ENG", "English" },
                                                              { "selectedLanguage", "DUT", "Dustch" }, { "selectedLanguage", "ESP", "Spanish" },
                                                              { "selectedLanguage", "FRC", "Canadian French" }, { "selectedLanguage", "KOR", "Korean" },
                                                              { "status", "1", "Open" }, { "status", "2", "Closed" }, { "status", "3", "Reopened" },
                                                              { "status", "4", "Cancelled" }, { "timeFormat", "24 Hour", "24 Hour" },
                                                              { "timeFormat", "AM/PM", "AM/PM" }, { "treatServiceforForeignBuyer", "Dom", "Domestic" },
                                                              { "treatServiceforForeignBuyer", "Exp", "Export" }, { "visibility", "ALL", "Everywhere" },
                                                              { "visibility", "INT", "Internal Only" }, { "visibility", "OUT", "External Only" },
                                                              { "workFunction", "A.R./PURCH/COLL", "A.R./PURCHASING/COLLECTIONS" },
                                                              { "workFunction", "ACCOUNTING", "Accounting" },
                                                              { "workFunction", "ACCT MANAGER", "Accounting Mgr" },
                                                              { "workFunction", "ACCT RECEIVABLE", "Accounts Receivable" },
                                                              { "workFunction", "ACCT RECV SUPER", "Account Receivable Super" },
                                                              { "workFunction", "ACCT REP/BUSDEV", "Account Reps/Bus Dev" },
                                                              { "workFunction", "ADM ASST-CONTRL", "Admin Asst to Controller" },
                                                              { "workFunction", "ADMIN ASSISTANT", "Admin Assistant" },
                                                              { "workFunction", "ADMIN ASST-CFO", "Admin Asst-CFO" },
                                                              { "workFunction", "ADMIN MGR", "Administration Mgr" },
                                                              { "workFunction", "ADMINISTRATION", "Administration" },
                                                              { "workFunction", "AGRI SPVSR", "Agriculture Supervisor" },
                                                              { "workFunction", "AREA MANAGER", "Area Manager" },
                                                              { "workFunction", "ASPHALT LAB MGR", "Asphalt Lab Manager" },
                                                              { "workFunction", "ASPHALT LABTECH", "Asphalt Lab Tech" },
                                                              { "workFunction", "BD MKT SERVICES", "BD Mkt Service" },
                                                              { "workFunction", "BILLING", "Invoicing/Billing" },
                                                              { "workFunction", "BRANCH MANAGER", "Branch Manager" },
                                                              { "workFunction", "CARIB/FL LABMGR", "Carib/FL Area Lab Mgr" },
                                                              { "workFunction", "CARIB/FL VP", "Carib/FL VP" }, { "workFunction", "CEO", "CEO" },
                                                              { "workFunction", "CEO GLOBAL", "CEO Global" }, { "workFunction", "CFO", "CFO" },
                                                              { "workFunction", "CFO GLOBAL", "CFO Global" },
                                                              { "workFunction", "CHROMATOGRAPHER", "Chromatographer" },
                                                              { "workFunction", "COLLECTIONS", "Collections" },
                                                              { "workFunction", "CONTRACT ADMIN", "Contract Administrator" },
                                                              { "workFunction", "CONTROLLER", "Controller" },
                                                              { "workFunction", "COORD/OPERATION", "Coordinator/Operations" },
                                                              { "workFunction", "DEVELOPER", "Developer" },
                                                              { "workFunction", "DIR MKT W HEMIS", "Director Mkt Western Hemispher" },
                                                              { "workFunction", "DISPATCHERS", "Dispatchers" },
                                                              { "workFunction", "E COAST R LABMG", "East Coast Reg Lab Mgr" },
                                                              { "workFunction", "E GULF AREA MGR", "E.Gulf Area Manager" },
                                                              { "workFunction", "E GULF IN CO VP", "E Gulf/Inland Coast VP" },
                                                              { "workFunction", "EASTCOAST VP", "East Coast VP" },
                                                              { "workFunction", "EG IN RG LABMGR", "E Gulf/Inland Reg Lab Mgr" },
                                                              { "workFunction", "EX ASST PRES AM", "Exec Asst President Americas" },
                                                              { "workFunction", "EX ASST SR EXVP", "Exec Asst Sr Exec VP" },
                                                              { "workFunction", "EX ASST SRVP LB", "Exec Asst Sr VP Labs" },
                                                              { "workFunction", "EX ASST VP LABS", "Exec Asst VP Labs" },
                                                              { "workFunction", "EXASST CEOCFO", "Exec Asst CEO &; CFO Global" },
                                                              { "workFunction", "EXEC ASSISTANT", "Executive Assistant" },
                                                              { "workFunction", "EXECUTIVE ADMIN", "Executive Administration" },
                                                              { "workFunction", "FIELD SUPV", "Field Supervisor" },
                                                              { "workFunction", "G BILLING MGR", "Global Billing Manger" },
                                                              { "workFunction", "HR", "Human Resources" }, { "workFunction", "INSPECTOR", "Inspector" },
                                                              { "workFunction", "INTERCOMP ACCTS", "Intercompany Accounts" },
                                                              { "workFunction", "INTL COORDINATR", "International Coordinator" },
                                                              { "workFunction", "IT", "Information Technology" }, { "workFunction", "LAB", "Lab" },
                                                              { "workFunction", "LAB BUSI DEV MG", "Lab Business Development Mgr" },
                                                              { "workFunction", "LAB BUSINESS DV", "Lab Business Development" },
                                                              { "workFunction", "LAB COORDINATOR", "Lab Coordinator" },
                                                              { "workFunction", "LAB MANAGER", "Lab Manager" },
                                                              { "workFunction", "LAB SUPERVISOR", "Lab Supervisor" },
                                                              { "workFunction", "LAB TECHNICIAN", "Lab Technician" },
                                                              { "workFunction", "LABFLOOR SUPV", "Lab Floor Supervisor" },
                                                              { "workFunction", "LABPROJ COORD", "Lab Project Coordinator" },
                                                              { "workFunction", "LATINAMER ASST", "Latin America Assistant" },
                                                              { "workFunction", "MARKETING", "Marketing" },
                                                              { "workFunction", "OCM ADMIN", "OCM Administration" },
                                                              { "workFunction", "OPS ASST", "Operations Assistant" },
                                                              { "workFunction", "OPS MANAGER", "Operational Manager" },
                                                              { "workFunction", "PETRO LAB SUPV", "Petro Lab Supervisor" },
                                                              { "workFunction", "PETRO LAB TECH", "Petro Lab Tech" },
                                                              { "workFunction", "PETROLAB MGR", "Petro Lab Manager" },
                                                              { "workFunction", "PRES AMERICAS", "President Americas" },
                                                              { "workFunction", "PROJECTS", "PROJECTS" },
                                                              { "workFunction", "PROJMGR SP PROJ", "Project Mgr Special Projects" },
                                                              { "workFunction", "QUALITY MGR", "Quality Manager" },
                                                              { "workFunction", "SECRETARY", "Secretary" }, { "workFunction", "SENIOR MGR", "Senior Manager" },
                                                              { "workFunction", "SHIPPING RECV", "Shipping &; Receiving" },
                                                              { "workFunction", "SR EXEC VP", "Senior Executive VP" },
                                                              { "workFunction", "SR VP LABS", "Sr VP Labs" }, { "workFunction", "TEMP", "Temporary Employee" },
                                                              { "workFunction", "TEXACO PROGRAM", "Texaco Program" },
                                                              { "workFunction", "VP IT", "VP Information Technology" },
                                                              { "workFunction", "VP LABS", "VP Labs" },
                                                              { "workFunction", "W COAST RLM", "West Coast Reg Lab Mgr" },
                                                              { "workFunction", "W COAST VP", "West Coast VP" },
                                                              { "workFunction", "W GULF CO VP", "West Gulf Coast VP" },
                                                              { "workFunction", "W GULF RLM", "West Gulf Reg Lab Mgr" },
                                                              { "workingPb", "USAUSD06", "USAUSD06" }, { "workingPb", "USAUSD07", "USAUSD07" },
                                                              { "workingUOM", "1", "Long Tons" }, { "workingUOM", "3", "Metric Tons" },
                                                              { "yesNo", "false", "No" }, { "yesNo", "true", "Yes" } };

    public ReferenceDataServiceImpl() {
        dropdowns = new HashMap<DropdownName, List<Field>>();
        // TODO: Eric - Read the database - dropdown table
        // List<> list=searchService.search(entityType, criteria);

        for (int i = 0; i < tempData.length; i++) {
            String[] row = tempData[i];
            DropdownName name = DropdownName.valueOf(row[0]);
            List<Field> fields = dropdowns.get(name);
            if (fields == null) {
                fields = new ArrayList<Field>();
                dropdowns.put(name, fields);
            }
            fields.add(new Field(row[2], row[1]));
        }
    }

    private List<Field> makeList(EnumField[] list) {
        return makeList(list, null);
    }
    
    private List<Field> makeList(EnumField[] list, EnumFieldFilter filter) {
        List<Field> result = new ArrayList<Field>();
        for (EnumField em:list){
            if(filter==null || filter.accept(em)){
                result.add(new Field(em.getDescription(), em.getValue()));
            }
        }
        return CommonUtil.fixIE7Bug(result);
    }

    @SuppressWarnings("unchecked")
    public List<Field> getProducts(String groupId){
        if(groupId==null){
            return new ArrayList<Field>();
        }
        
        List<Object[]> products = null;
        try {
            products = DaoManager.getDao(Product.class).searchByNamedQuery("getProductByProdGroup", new Object[] {groupId});
        }
        catch (DaoException e1) {
            e1.printStackTrace();
        }
        List<Field> results = new ArrayList<Field>();

        Field field1 = new Field();
        field1.setName("");
        field1.setValue("");
        results.add(field1);

        for (Object[] p:products) {
            Field field = new Field();
            field.setName(p[2].toString()+ " ("+p[1].toString()+")");
            field.setValue(p[0].toString());
            results.add(field);
        }
        return CommonUtil.fixIE7Bug(results);
    }
    
    @SuppressWarnings("unchecked")
    public List<Field> getProductGroups(String productType) {
        List<Object[]> list = null;
        try {
            list = DaoManager.getDao(ProductGroup.class).searchByNamedQuery("getProductGroupFields", new Object[] { productType });
        }
        catch (DaoException e1) {
            e1.printStackTrace();
        }
        List<Field> results = new ArrayList<Field>();

        Field field1 = new Field();
        field1.setName("");
        field1.setValue("");
        results.add(field1);

        for (Object[] pg : list) {
            Field field = new Field();
            field.setName(pg[1].toString());
            field.setValue(pg[0].toString());
            results.add(field);
        }

        Comparator myComp = new Comparator() {
            public int compare(Object o1, Object o2) {
                try {
                    Field f1 = (Field) o1;
                    Field f2 = (Field) o2;
                    String n1 = f1.getName();
                    String n2 = f2.getName();

                    String g1 = n1.substring(n1.lastIndexOf("-"));
                    String g2 = n2.substring(n2.lastIndexOf("-"));

                    int g = g1.compareToIgnoreCase(g2);
                    if (g != 0) {
                        return g;
                    }
                    return n1.compareToIgnoreCase(n2);
                }
                catch (Exception e) {
                    // e.printStackTrace();
                }
                return -1;
            }
        };

        Collections.sort(results, myComp);
        
        return CommonUtil.fixIE7Bug(results);
    }
    
    public List<Field> getDropdown(DropdownName name) {
        return CommonUtil.fixIE7Bug(dropdowns.get(name));
    }

    @Override
    public List<Field> getFilterOpFields() {
        return makeList(getFilterOps());
    }

    @Override
    public FilterOp[] getFilterOps() {
        return FilterOp.list();
    }

    @Override
    public List<Field> getLogicOpFields() {
        return makeList(getLogicOps());
    }

    @Override
    public LogicOp[] getLogicOps() {
        return LogicOp.list();
    }

    @Override
    public List<Field> getBillingStatusFields() {
        return makeList(getBillingStatuses());
    }

    @Override
    public BillingStatus[] getBillingStatuses() {
        return BillingStatus.list();
    }

    @Override
    public List<Field> getInvoiceStatusFields() {
        return makeList(getInvoiceStatuses());
    }

    @Override
    public InvoiceStatus[] getInvoiceStatuses() {
        return InvoiceStatus.list();
    }

    @Override
    public List<Field> getInvoiceTypeFields() {
        return makeList(getInvoiceTypes());
    }

    @Override
    public InvoiceType[] getInvoiceTypes() {
        return InvoiceType.list();
    }

    @Override
    public List<Field> getNoteTypeFields() {
        return makeList(getNoteTypes());
    }

    @Override
    public NoteType[] getNoteTypes() {
        return NoteType.list();
    }

    @Override
    public List<Field> getOperationalStatusFields() {
        return makeList(getOperationalStatuses());
    }

    @Override
    public OperationalStatus[] getOperationalStatuses() {
        return OperationalStatus.list();
    }

    @Override
    public List<Field> getOrderOriginFields() {
        return makeList(getOrderOrigins());
    }

    @Override
    public OrderOrigin[] getOrderOrigins() {
        return OrderOrigin.list();
    }

    @Override
    public List<Field> getOrderStatusFields() {
        return makeList(getOrderStatuses());
    }

    @Override
    public OrderStatus[] getOrderStatuses() {
        return OrderStatus.list();
    }

    @Override
    public List<Field> getPaymentStatusFields() {
        return makeList(getPaymentStatuses());
    }

    @Override
    public PaymentStatus[] getPaymentStatuses() {
        return PaymentStatus.list();
    }

    @Override
    public List<Field> getPaymentTypeFields() {
        return makeList(getPaymentTypes());
    }

    @Override
    public PaymentType[] getPaymentTypes() {
        return PaymentType.list();
    }

    @Override
    public List<Field> getProjectTypeFields(EnumFieldFilter filter) {
        return makeList(getProjectTypes(), filter);
    }

    @Override
    public ProjectType[] getProjectTypes() {
        return ProjectType.list();
    }

    @Override
    public List<Field> getSourceOriginFields() {
        return makeList(getSourceOrigins());
    }

    @Override
    public SourceOrigin[] getSourceOrigins() {
        return SourceOrigin.list();
    }

    @Override
    public List<Field> getTaskOperationalStatusFields() {
        return makeList(getLogicOps());
    }

    @Override
    public TaskOperationalStatus[] getTaskOperationalStatuses() {
        return null; // TODO
    }

    @Override
    public List<Field> getUOMFields() {
        return makeList(getLogicOps());
    }

    @Override
    public UOM[] getUOMs() {
        return UOM.list();
    }

    @Override
    public List<Field> getUserTypeFields() {
        return makeList(getUserTypes());
    }

    @Override
    public UserType[] getUserTypes() {
        return UserType.list();
    }

    @Override
    public Visibility[] getVisibilities() {
        return Visibility.list();
    }

    @Override
    public List<Field> getVisibilityFields() {
        return makeList(getVisibilities());
    }

    @Override
    public List<Field> getInvoiceGenerationTypeFields() {
        return makeList(getInvoiceGenerationTypes());
    }

    @Override
    public InvoiceGenerationType[] getInvoiceGenerationTypes() {
        return InvoiceGenerationType.list();
    }

    @Override
    public List<DepositType> getDepositTypeFields() {
        List<DepositType> result = new ArrayList<DepositType>();
        for (int i = 0; i < getDepositTypes().length; i++) {
            result.add(getDepositTypes()[i]);
        }
        return result;
    }

    @Override
    public DepositType[] getDepositTypes() {
        return DepositType.list();
    }

    @Override
    public List<FilterOp> getFilterStringOpFields() {
        List<FilterOp> result = new ArrayList<FilterOp>();
        for (FilterOp op : FilterOp.list()) {
            switch (op) {
                case EQUAL:
                case NOT_EQUAL:
                case LIKE:
                case BEGINS_WITH:
                    result.add(op);
                    break;
                default:
            }

        }
        return result;
    }

    @Override
    public List<SearchKey> getsearchFields() {
        List<SearchKey> searchKeyLi = new ArrayList<SearchKey>();
        SearchKey[] searchKeyArr = SearchKey.values();
        for (SearchKey key : searchKeyArr) {
            searchKeyLi.add(key);
        }
        return searchKeyLi;
    }
}
