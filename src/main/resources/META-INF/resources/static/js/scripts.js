/*!
    * Start Bootstrap - SB Admin v6.0.0 (https://startbootstrap.com/templates/sb-admin)
    * Copyright 2013-2020 Start Bootstrap
    * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    (function($) {
    "use strict";

    // Add active state to sidbar nav links
    var path = window.location.href; // because the 'href' property of the DOM element is the absolute path
        $("#layoutSidenav_nav .sb-sidenav a.nav-link").each(function() {
            if (this.href === path) {
                $(this).addClass("active");
            }
        });

    // Toggle the side navigation
    $("#sidebarToggle").on("click", function(e) {
        e.preventDefault();
        $("body").toggleClass("sb-sidenav-toggled");
    });
})(jQuery);
    
    
    
//27 JANUARY ADDITIONS FOR READING DATA FROM THE TEXT DOCUMENT FOR EXTRACTED CONTENT
    /*   
    function getData()
    {
       const ISTRUE=true;

       while(ISTRUE==true)
       {
   	 var xmlhttp;
   	 
   	 xmlhttp = new XMLHttpRequest();
   	 
   	 xmlhttp.onreadystatechange = function()
   	 {
   	 if(this.readyState==4 && this.status ==200)
   		 {
   		 	document.getElementById("output").innerHTML=this.responseText;
   		 }
   	 };
   	 
   	 xmlhttp.open("GET","/thepdf",true);
        xmlhttp.send();
        //setInterval(getData,2000);
        //ISTRUE=true;
       }
    }
       
   */
   /*
   setInterval(function()
   {
       function getData()
    {
       const ISTRUE=true;

       while(ISTRUE==true)
       {
   	 var xmlhttp;
   	 
   	 xmlhttp = new XMLHttpRequest();
   	 
   	 xmlhttp.onreadystatechange = function()
   	 {
   	 if(this.readyState==4 && this.status ==200)
   		 {
   		 	document.getElementById("output").innerHTML=this.responseText;
   		 }
   	 };
   	 
   	 xmlhttp.open("GET","/thepdf",true);
        xmlhttp.send();
        //setInterval(getData,2000);
        //ISTRUE=true;
       }
    }
   }, 2000);

   */
/* COMMENTED IT OUT BECAUSE THE SYSTEM WAS CRASHING
   function getData()
    {
       const ISTRUE=true;

       while(ISTRUE==true)
       {
   	 var xmlhttp;
   	 
   	 xmlhttp = new XMLHttpRequest();
   	 
   	 xmlhttp.onreadystatechange = function()
   	 {
   	 if(this.readyState==4 && this.status ==200)
   		 {
   		 	document.getElementById("output").innerHTML=this.responseText;
   		 }
   	 };
   	 
   	 xmlhttp.open("GET","/thepdf",true);
        xmlhttp.send();
        
        //setInterval(getData,2000);
        //ISTRUE=true;
       }
       setInterval(getData,2000);

       getData();

    }
*/