var dolphintabs={
  subcontainers:[], last_accessed_tab:null,

  revealsubmenu:function(curtabref){
  this.hideallsubs()
  if (this.last_accessed_tab!=null)
    this.last_accessed_tab.className=""
  if (curtabref.getAttribute("rel")) //If there's a sub menu defined for this tab item, show it
  document.getElementById(curtabref.getAttribute("rel")).style.display="block"
  curtabref.className="current"
  this.last_accessed_tab=curtabref
  },

  hideallsubs:function(){
  for (var i=0; i<this.subcontainers.length; i++)
    document.getElementById(this.subcontainers[i]).style.display="none"
  },


  init:function(menuId, selectedIndex){
  var tabItems=document.getElementById(menuId).getElementsByTagName("a")
    for (var i=0; i<tabItems.length; i++){
      if (tabItems[i].getAttribute("rel"))
        this.subcontainers[this.subcontainers.length]=tabItems[i].getAttribute("rel") //store id of submenu div of tab menu item
      if (i==selectedIndex){ //if this tab item should be selected by default
        tabItems[i].className="current"
        this.revealsubmenu(tabItems[i])
      }
    tabItems[i].onmousedown=function(){
    dolphintabs.revealsubmenu(this)
    }
    } //END FOR LOOP
  }

}

var dolphintabs2={
  subcontainers:[], last_accessed_tab:null,

  revealsubmenu:function(curtabref){
  this.hideallsubs()
  if (this.last_accessed_tab!=null)
    this.last_accessed_tab.className=""
  if (curtabref.getAttribute("rel")) //If there's a sub menu defined for this tab item, show it
  document.getElementById(curtabref.getAttribute("rel")).style.display="block"
  curtabref.className="current"
  this.last_accessed_tab=curtabref
  },

  hideallsubs:function(){
  for (var i=0; i<this.subcontainers.length; i++)
    document.getElementById(this.subcontainers[i]).style.display="none"
  },


  init:function(menuId, selectedIndex){
  var tabItems=document.getElementById(menuId).getElementsByTagName("a")
    for (var i=0; i<tabItems.length; i++){
      if (tabItems[i].getAttribute("rel"))
        this.subcontainers[this.subcontainers.length]=tabItems[i].getAttribute("rel") //store id of submenu div of tab menu item
      if (i==selectedIndex){ //if this tab item should be selected by default
        tabItems[i].className="current"
        this.revealsubmenu(tabItems[i])
      }
    tabItems[i].onmousedown=function(){
    dolphintabs2.revealsubmenu(this)
    }
    } //END FOR LOOP
  }

}

var dolphintabs3={
  subcontainers:[], last_accessed_tab:null,

  revealsubmenu:function(curtabref){
  this.hideallsubs()
  if (this.last_accessed_tab!=null)
    this.last_accessed_tab.className=""
  if (curtabref.getAttribute("rel")) //If there's a sub menu defined for this tab item, show it
  document.getElementById(curtabref.getAttribute("rel")).style.display="block"
  curtabref.className="current"
  this.last_accessed_tab=curtabref
  },

  hideallsubs:function(){
  for (var i=0; i<this.subcontainers.length; i++)
    document.getElementById(this.subcontainers[i]).style.display="none"
  },


  init:function(menuId, selectedIndex){
  var tabItems=document.getElementById(menuId).getElementsByTagName("a")
    for (var i=0; i<tabItems.length; i++){
      if (tabItems[i].getAttribute("rel"))
        this.subcontainers[this.subcontainers.length]=tabItems[i].getAttribute("rel") //store id of submenu div of tab menu item
      if (i==selectedIndex){ //if this tab item should be selected by default
        tabItems[i].className="current"
        this.revealsubmenu(tabItems[i])
      }
    tabItems[i].onmousedown=function(){
    dolphintabs3.revealsubmenu(this)
    }
    } //END FOR LOOP
  }

}




var dolphintabs4={
  subcontainers:[], last_accessed_tab:null,

  revealsubmenu:function(curtabref){
  this.hideallsubs()
  if (this.last_accessed_tab!=null)
    this.last_accessed_tab.className=""
  if (curtabref.getAttribute("rel")) //If there's a sub menu defined for this tab item, show it
  document.getElementById(curtabref.getAttribute("rel")).style.display="block"
  curtabref.className="current"
  this.last_accessed_tab=curtabref
  },

  hideallsubs:function(){
  for (var i=0; i<this.subcontainers.length; i++)
    document.getElementById(this.subcontainers[i]).style.display="none"
  },


  init:function(menuId, selectedIndex){
  var tabItems=document.getElementById(menuId).getElementsByTagName("a")
    for (var i=0; i<tabItems.length; i++){
      if (tabItems[i].getAttribute("rel"))
        this.subcontainers[this.subcontainers.length]=tabItems[i].getAttribute("rel") //store id of submenu div of tab menu item
      if (i==selectedIndex){ //if this tab item should be selected by default
        tabItems[i].className="current"
        this.revealsubmenu(tabItems[i])
      }
    tabItems[i].onmousedown=function(){
    dolphintabs4.revealsubmenu(this)
    }
    } //END FOR LOOP
  }

}



var dolphintabs5={
  subcontainers:[], last_accessed_tab:null,

  revealsubmenu:function(curtabref){
  this.hideallsubs()
  if (this.last_accessed_tab!=null)
    this.last_accessed_tab.className=""
  if (curtabref.getAttribute("rel")) //If there's a sub menu defined for this tab item, show it
  document.getElementById(curtabref.getAttribute("rel")).style.display="block"
  curtabref.className="current"
  this.last_accessed_tab=curtabref
  },

  hideallsubs:function(){
  for (var i=0; i<this.subcontainers.length; i++)
    document.getElementById(this.subcontainers[i]).style.display="none"
  },


  init:function(menuId, selectedIndex){
  var tabItems=document.getElementById(menuId).getElementsByTagName("a")
    for (var i=0; i<tabItems.length; i++){
      if (tabItems[i].getAttribute("rel"))
        this.subcontainers[this.subcontainers.length]=tabItems[i].getAttribute("rel") //store id of submenu div of tab menu item
      if (i==selectedIndex){ //if this tab item should be selected by default
        tabItems[i].className="current"
        this.revealsubmenu(tabItems[i])
      }
    tabItems[i].onmousedown=function(){
    dolphintabs5.revealsubmenu(this)
    }
    } //END FOR LOOP
  }

}