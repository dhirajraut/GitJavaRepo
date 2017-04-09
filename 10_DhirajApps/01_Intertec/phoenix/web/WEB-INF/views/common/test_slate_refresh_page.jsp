<script>
  function submitParentForm()
  {
     top.document.forms[0].operationType.value = '${requestScope.operationType}';
     top.document.forms[0].pageNumber.value = '${requestScope.pageNumber}';
     top.document.forms[0].activeId.value = '${requestScope.activeId}';
     top.document.forms[0].submit();
  }
  
  submitParentForm(); 
  
  top.closeContractPopups();
</script>
