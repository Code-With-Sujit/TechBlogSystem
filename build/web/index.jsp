

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page  import="com.tech.blog.helper.ConnectionProvider" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <!--css-->

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .banner-background{
                clip-path: polygon(0 0, 100% 0, 100% 26%, 100% 92%, 59% 100%, 27% 91%, 0 100%, 0% 30%);
            }
        </style>
    </head>
    <body>

        <!--navbar-->
        <%@include file="normal_navbar.jsp" %>

        <!--Banner-->

        <div class="container-fluid p-0 m-0">
            <div class="jumbotron primary-background text-white banner-background">
                <div class="container">
                    <h3 class="display-3">Welcome to TechBlog</h3>

                    <p>A programming language is a system of notation for writing computer programs.[1] Most programming languages are text-based formal languages, but they may also be graphical. They are a kind of computer language.</p> 
                    <p>Programming language theory is the subfield of computer science that studies the design, implementation, analysis, characterization, and classification of programming languages.</p>
                    <button class="btn btn-outline-light btn-lg"><span class="	fa fa-user-plus"></span> Start ! it's Free</button>&nbsp;
                    <a href="login_page.jsp" class="btn btn-outline-light btn-lg"><span class="fa fa-user-circle fa-spin"></span> Login</a>
                </div>
            </div>
        </div>

        <br/>

        <!--Cards-->
        <div class="container">
            <!--row1-->
            <div class="row mb-2">
                <div class="col-md-4">
                    <div class="card-body">
                        <h5 class="card-title">Java Programming</h5>
                        <p class="card-text">Java is a widely-used programming language for coding web applications. It has been used with millions of Java applications in use today. </p>
                        <a href="#" class="btn primary-background text-white">Read more</a>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card-body">
                        <h5 class="card-title">Python Programming</h5>
                        <p class="card-text">Python is a popular programming languages and used in everything from machine learning to building websites and software testing.</p>
                        <a href="#" class="btn primary-background text-white">Read more</a>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card-body">
                        <h5 class="card-title">C++ Programming</h5>
                        <p class="card-text">C++ is an object-oriented programming (OOP) language that is viewed by many as the best language for creating large-scale applications.</p>
                        <a href="#" class="btn primary-background text-white">Read more</a>
                    </div>
                </div>
            </div>
           
            </div>





        </div>





        <!--javascript-->
        <script
            src="https://code.jquery.com/jquery-3.7.0.min.js"
            integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="
            crossorigin="anonymous">
        </script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="js/myjs.j" type="text/javascript"></script>
      


    </body>
</html>
