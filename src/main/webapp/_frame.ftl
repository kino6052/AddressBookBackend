<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title>Address Book</title>

    <!-- Note: In real application, should not have the ../../../others/ (just to avoid duplications across sample apps)
               it should be href="bootstrap/css/bootstrap.min.css
    -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />


    <!-- Best-Practice: this is the main css to be included first. Component css will be included
                        dynamically, and all start with UpperCase to match their component name -->
    <link rel="stylesheet" href="css/main.css" />
    
    <!-- This is the only hard dependency for Brite -->
    <!-- Note: In real application, should be src="js/jquery.min.js" -->
    <script type="text/javascript" src="js/jquery.js" ></script>
    
    <!-- This is the only hard dependency for brite.js -->
    <script type="text/javascript" src="js/jquery.min.js" ></script>
    
    <!-- Best-Practice: Use handlebars. -->
    <script type="text/javascript" src="js/handlebars.min.js" ></script>
    
    <!-- Note: Should be "js/brite.min.js" but for this sample code we reuse the shared lib -->
    <script type="text/javascript" src="js/brite.min.js" ></script>
    <!--<script type="text/javascript" src="js/brite.InMemoryDaoHandler.js" ></script>-->

    <!-- Dao Handlers for Data Access -->
    <script type="text/javascript" src="js/dao/remoteDao.js" ></script>
    <script type="text/javascript" src="js/dao/dao-bindings.js" ></script>
    
    <!-- Include the main application javascript file -->
    <script type="text/javascript" src="js/main.js" ></script>
    
  </head>
  
  <body>
    <!-- Best-Practice: Here with use a "pageBody" element to get more flexibility on how we want to display 
                        the application. This pageBody will be position in the main.css. -->
    <div id="pageBody">
      
    </div>
  </body>
  
</html>