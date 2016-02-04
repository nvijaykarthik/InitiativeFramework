<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" ng-app="initiativeApp">
    <%@ include file="header.jsp" %>
    <body>
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-inverse-collapse">
                        <i class="icon-reorder shaded"></i></a><a class="brand" href="index.html">IniFm </a>
                    <div class="nav-collapse collapse navbar-inverse-collapse" ng-controller="menuController">
                        <ul class="nav">
                         <li ng-repeat="menu in menuList">
      							<a href="{{menu.url}}">{{menu.label}}</a>
   						   </li>
                            <li></li>
                        </ul>
                    </div>
                    <!-- /.nav-collapse -->
                </div>
            </div>
            <!-- /navbar-inner -->
        </div>
        <!-- /navbar -->
        <div class="wrapper">
            <div class="container">
                <div class="row">
                    <div class="span12">
                        <div class="content">
                            <!-- Load the content here -->
                        </div>
                        <!--/.content-->
                    </div>
                    <!--/.span9-->
                </div>
            </div>
            <!--/.container-->
        </div>
         <%@ include file="footer.jsp" %>
    </body>