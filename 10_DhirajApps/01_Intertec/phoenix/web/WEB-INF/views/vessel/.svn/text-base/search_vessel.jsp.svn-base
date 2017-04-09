<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<form:form name="contractSearchForm" method="POST" action="search_contract.htm">
<input type="hidden" name="pageNumber" value="1" />

<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span>Contract Search  </span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
                <tr>
                  <th colspan="3">Contract Search </th>
                </tr>
                <tr>
                  <td width="20%" class="TDShade">Contract ID: </td>
                  <td class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="contractId.operator" items="${icbfn:dropdown('operator', null)}" itemLabel="name" itemValue="value"/>
                  </td>
                  <td class="TDShadeBlue">
                    <form:input cssClass="inputBox" path="contractId.value" />
                  </td>
                </tr>
                <tr>
                  <td class="TDShade">Contract Status   :</td>
                  <td class="TDShadeBlue">=</td>
                  <td class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="contractStatus.value" items="${icbfn:dropdown('contractStatus', null)}" itemLabel="name" itemValue="value"/>
                  </td>
                </tr>
                <tr>
                  <td class="TDShade">Contract Description : </td>
                  <td class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="contractDescription.operator" items="${icbfn:dropdown('operator', null)}" itemLabel="name" itemValue="value"/>
                  </td>
                  <td class="TDShadeBlue">
                    <form:input cssClass="inputBox" path="contractDescription.value" />
                  </td>
                </tr>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td><input name="Submit" type="submit" class="button1" value="Search" /></td>
                      <td style="text-align:right"><a href="jobs/ins_jobsinstructions.html"></a></td>
                    </tr>
                  </table></td>
                </tr>
              </table>
              <br>
              <c:if test="${command.results != null}">
                <div id="contractsearchresults"> 
                  <strong>Search Results
                  </strong>
                  <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                    <tr>
                      <th><span class="TDShade">Contract ID </span></th>
                      <th><span class="TDShade">Contract Status  </span></th>
                      <th><span class="TDShade">Description </span></th>
                    </tr>
                    <c:forEach items="${command.results}" var="contract" varStatus="status">
                      <tr>
                        <td><A href="edit_contract.htm?id=${contract.id}">${contract.id}</A></td>
                        <td>${contract.status}</td>
                        <td>${contract.description}</td>
                      </tr>
                    </c:forEach>
                  </table>
                </div>
              </c:if>
            </div>
            <!----------------- TAB 1 CONTAINER END ------------------------------ -->
          </div>
        </div>
        <script type="text/javascript">
      
      //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
      dolphintabs.init("tabnav", 0)
      
      </script>
        <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
      </div>
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
    </td>
  </tr>
</table>
</form:form>

