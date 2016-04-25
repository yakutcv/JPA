<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Chernivtsi national hospital</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="css/mainPage.css" rel="stylesheet" type="text/css">
    <link href="css/stylish-portfolio.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <%--Script--%>
    <script src="js/jquery-2.2.3.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="js/Carousel.js"></script>
</head>
<body>
<!-- Navigation -->
<a id="menu-toggle" href="#" class="btn btn-dark btn-lg toggle"><i class="fa fa-bars"></i></a>
<nav id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <a id="menu-close" href="#" class="btn btn-lg pull-right toggle"><i class="fa fa-times"></i></a>
        <li class="sidebar-brand">
            <a href="#top"  onclick = $("#menu-close").click(); >Chernivtsi Hospital</a>
        </li>
        <li>
            <a href="#top" onclick = $("#menu-close").click(); >Home</a>
        </li>
        <li>
            <a href="#about" onclick = $("#menu-close").click(); >About</a>
        </li>
        <li>
            <a href="#services" onclick = $("#menu-close").click(); >Services</a>
        </li>
        <li>
            <a href="#portfolio" onclick = $("#menu-close").click(); >Photo</a>
        </li>
        <li>
            <a href="#contact" onclick = $("#menu-close").click(); >Contact</a>
        </li>
        <li>
            <a href = "<c:url value = "AllPatientController?laboratory=laboratory"/>" onclick = $("#menu-close").click();>Laboratory</a>
        </li>
        <li>
            <a href = "<c:url value = "Admin.jsp"/>" onclick = $("#menu-close").click();>Admin</a>
        </li>
    </ul>
</nav>

<!-- Header -->
<header id="top" class="header">
    <div id="Carousel" class="carousel slide">
        <ol class="carousel-indicators">
            <li class="item1 active"></li>
            <li class="item2 "></li>
            <li class="item3 "></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="img/hospital.jpg" alt="hospital" class="header">

                <div class="hero">
                    <hgroup>
                        <h1>Chernivtsi National Hospital</h1>
                        <h2>Everyone's duty is to be healthy</h2>
                    </hgroup>
                    <br>
                    <p>
                        <a class="btn btn-outlined btn-theme btn-lg mainButton additionalButton" data-wow-delay="0.7s" role= "button"
                           href = "<c:url value = "AllPatientController"/>">All Patients</a>
                    </p>
                </div>
            </div>
            <div class="item">
                <img src="img/hospital2.jpg" alt="hospital2" class="header">
                <div class="hero">
                    <hgroup>
                        <h1>Chernivtsi National Hospital</h1>
                        <h2>Some patients recover, because they believe in doctors</h2>
                    </hgroup>
                    <br>
                    <p>
                        <a class="btn btn-outlined btn-theme btn-lg mainButton additionalButton" data-wow-delay="0.7s" role= "button"
                           href = "<c:url value = "AllPatientController"/>">All Patients</a>
                    </p>
                </div>
            </div>
            <div class="item">
                <img src="img/hospital3.jpg" alt="hospital3" class="header">
                <div class="hero">
                    <hgroup>
                        <h1>Chernivtsi National Hospital</h1>
                        <h2>Health is not simply the absence of sickness</h2>
                    </hgroup>
                    <br>
                    <p>
                        <a class="btn btn-outlined btn-theme btn-lg mainButton additionalButton" data-wow-delay="0.7s" role= "button"
                           href = "<c:url value = "AllPatientController"/>">All Patients</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- About -->
<section id="about" class="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2>Key to Health Life</h2>
                <p class="lead">The Chernivtsi National Hospital, is located on the Bucovina state in Ukraine, clinician-investigators translate laboratory discoveries into better treatments, therapies and interventions to improve the nation's health</p>
            </div>
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->
</section>

<!-- Service -->
<!-- The circle icons use Font Awesome's stacked icon classes. For more information, visit http://fontawesome.io/examples/ -->
<section id="services" class="services bg-primary">
    <div class="container">
        <div class="row text-center">
            <div class="col-lg-10 col-lg-offset-1">
                <h2>Our Services</h2>
                <hr class="small">
                <div class="row">
                    <div class="col-md-3 col-sm-6">
                        <div class="service-item">
                            <span class="fa-stack fa-4x">
                                <i class="fa fa-circle fa-stack-2x"></i>
                                <i class="fa fa-ambulance fa-stack-1x text-primary"></i>
                            </span>
                            <h4>
                                <strong>Ambulances</strong>
                            </h4>
                            <p>Our ambulances are coming faster than anyone</p>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="service-item">
                            <span class="fa-stack fa-4x">
                                <i class="fa fa-circle fa-stack-2x"></i>
                                <i class="fa fa-stethoscope fa-stack-1x text-primary"></i>
                            </span>
                            <h4>
                                <strong>Specialists</strong>
                            </h4>
                            <p>We have the best specialists of the country</p>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="service-item">
                            <span class="fa-stack fa-4x">
                                <i class="fa fa-circle fa-stack-2x"></i>
                                <i class="fa fa-flask fa-stack-1x text-primary"></i>
                            </span>
                            <h4>
                                <strong>Analyzes</strong>
                            </h4>
                            <p>We offer a full range of analyzes</p>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="service-item">
                            <span class="fa-stack fa-4x">
                                <i class="fa fa-circle fa-stack-2x"></i>
                                <i class="fa fa-shield fa-stack-1x text-primary"></i>
                            </span>
                            <h4>
                                <strong>Protect</strong>
                            </h4>
                            <p>We take care of our patients outside the hospital</p>
                        </div>
                    </div>
                </div>
                <!-- /.row (nested) -->
            </div>
            <!-- /.col-lg-10 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->
</section>

<!-- Portfolio -->
<section id="portfolio" class="portfolio">
    <div class="container">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1 text-center">
                <h2>Our Hospital</h2>
                <hr class="small">
                <div class="row">
                    <div class="col-md-6">
                        <div class="portfolio-item">
                            <a href="#">
                                <img class="img-portfolio img-responsive" src="img/portfolio-1.jpg">
                            </a>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="portfolio-item">
                            <a href="#">
                                <img class="img-portfolio img-responsive" src="img/portfolio-2.jpg">
                            </a>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="portfolio-item">
                            <a href="#">
                                <img class="img-portfolio img-responsive" src="img/portfolio-3.jpg">
                            </a>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="portfolio-item">
                            <a href="#">
                                <img class="img-portfolio img-responsive" src="img/portfolio-4.jpg">
                            </a>
                        </div>
                    </div>
                </div>
                <!-- /.row (nested) -->

            </div>
            <!-- /.col-lg-10 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->
</section>

<!-- Call to Action -->
<aside class="call-to-action bg-primary">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h3>You can find us on the map below</h3>
            </div>
        </div>
    </div>
</aside>

<!-- Map -->
<section id="contact" class="map">
    <iframe width="100%" height="100%" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2654.6171707800145!2d25.92740445140565!3d48.29098047913403!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47340899274fc38b%3A0xc7f96ad06ec3980a!2z0LLRg9C7LiDQm9C10YHRliDQo9C60YDQsNGX0L3QutC4LCAxMSwg0KfQtdGA0L3RltCy0YbRliwg0KfQtdGA0L3RltCy0LXRhtGM0LrQsCDQvtCx0LvQsNGB0YLRjA!5e0!3m2!1suk!2sua!4v1460325968926" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
</section>
<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1 text-center">
                <h4><strong>Chernivtsi Hospital</strong>
                </h4>
                <p>Lesi Ukrainki, 11<br>Chernivtsi, UA 58000</p>
                <ul class="list-unstyled">
                    <li><i class="fa fa-phone fa-fw"></i> (0372) 58-14-44</li>
                    <li><i class="fa fa-envelope-o fa-fw"></i>  <a href="mailto:cnhospital@cv.com.ua">cnhospital@cv.com.ua</a>
                    </li>
                </ul>
                <br>
                <ul class="list-inline">
                    <li>
                        <a href="#"><i class="fa fa-facebook fa-fw fa-3x"></i></a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-twitter fa-fw fa-3x"></i></a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-dribbble fa-fw fa-3x"></i></a>
                    </li>
                </ul>
                <hr class="small">
                <p class="text-muted">Copyright &copy; Andrew mindnight coding 2016</p>
            </div>
        </div>
    </div>
</footer>

<!-- jQuery -->
<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script>
    // Closes the sidebar menu
    $("#menu-close").click(function(e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
    });

    // Opens the sidebar menu
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
    });

    // Scrolls to the selected menu item on the page
    $(function() {
        $('a[href*=#]:not([href=#])').click(function() {
            if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') || location.hostname == this.hostname) {
                var target = $(this.hash);
                target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
                if (target.length) {
                    $('html,body').animate({
                        scrollTop: target.offset().top
                    }, 1000);
                    return false;
                }
            }
        });
    });
</script>
</body>
</html>
