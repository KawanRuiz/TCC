<%@page import="Model.Comandas"%>
<%@page import="Model.Relatorios"%>
<%@page import="Model.Produtos"%>
<%@page import="java.util.List"%>
<%@page import="Model.Lotes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="background-color:white;" >

    <head>


        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>ADEGA</title>

        <link rel="stylesheet" href="assets/demo.css">
        <link rel="stylesheet" href="assets/navigation-icons.css">
        <link rel="stylesheet" href="assets/slicknav/slicknav.min.css">

        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.0/font/bootstrap-icons.css">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.6.0/chart.min.js" integrity="sha512-GMGzUEevhWh8Tc/njS0bDpwgxdCJLQBWG3Z2Ct+JGOpVnEmjvNx6ts4v6A2XJf1HOrtOsfhv3hBKpK9kE5z8AQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    </head>

    <body >


        <nav class="menu-navigation-icons">
            <a href="ComandasServlet" class="menu-magenta"><i class="bi bi-clipboard"></i><span>Comandas</span></a>
            <a href="ProdutosServlet" class="menu-blue"><i class="bi bi-cup-straw"></i><span>Produtos</span></a>
            <a href="LotesServlet" class="menu-green"><i class="bi bi-collection"></i><span>Lotes</span></a>
            <a href="RelatoriosServlet" class="menu-yellow"><i class="bi bi-graph-up"></i><span>Relatórios</span></a>
            <a href="DevedoresServlet" class="menu-red"><i class="bi bi-clipboard-x"></i><span>Devedores</span></a>
        </nav>


     
<div class="menu">
            <h1>Relatórios</h1>
            
            <a  style="background-color: #dadf50;" class="btn btn-outline-dark btn-lg" href="RelatoriosServlet?oper=Diario"><i class="bi bi-graph-up"></i> Diario</a>
            <a  style="background-color: #8ccc7a; " class="btn btn-outline-dark btn-lg" href="RelatoriosServlet?oper=Semanal"><i class="bi bi-graph-up-arrow"></i> Semanal</a>
            <a  style="background-color: #57b2d8; " class="btn btn-outline-dark btn-lg" href="RelatoriosServlet?oper=Mensal"><i class="bi bi-bar-chart-line"></i> Mensal</a>
            <a  style="background-color: #dc68bf; " class="btn btn-outline-dark btn-lg" href="RelatoriosServlet?oper=Anual"><i class="bi bi-bar-chart-line-fill"></i> Anual</a>           
</div>
      




        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="assets/slicknav/jquery.slicknav.min.js"></script>

        <script>
                    $(function () {
                        $('.menu-navigation-icons').slicknav();
                    });
        </script>

    </body>



</html>
