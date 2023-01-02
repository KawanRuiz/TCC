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
            <div>
              
                <a  style="background-color: #dadf50;" class="btn btn-outline-dark btn-lg" href="RelatoriosServlet?oper=Diario"><i class="bi bi-graph-up"></i> Diario</a>
                <a  style="background-color: #8ccc7a; " class="btn btn-outline-dark btn-lg" href="RelatoriosServlet?oper=Semanal"><i class="bi bi-graph-up-arrow"></i> Semanal</a>
                <a  style="background-color: #57b2d8; " class="btn btn-outline-dark btn-lg" href="RelatoriosServlet?oper=Mensal"><i class="bi bi-bar-chart-line"></i> Mensal</a>
                <a  style="background-color: #dc68bf; " class="btn btn-outline-dark btn-lg" href="RelatoriosServlet?oper=Anual"><i class="bi bi-bar-chart-line-fill"></i> Anual</a>    
            </div>


            <%  Relatorios rel = (Relatorios) request.getAttribute("relatorio"); %>
            <%  Comandas comandas = (Comandas) request.getAttribute("comandas"); %>
            <%  List<Relatorios> relarr = (List<Relatorios>) request.getAttribute("relarr"); %>
            <%if (rel != null) {%>

            <%if (request.getParameter("oper").equals("Diario") || request.getParameter("oper").equals("PesquisarDia")) {%>
            
            <div style="width: 400px; height: 400px; float: left;"></div> <!<!-- Espaçamento -->
            
            <div  style="width: 500px; height: 400px; float: left;">
                
                <h1>Diario</h1>
                <FORM ACTION="RelatoriosServlet">     
                    <INPUT TYPE="Date" NAME="dataDia"VALUE="<%if (request.getParameter("dataDia") != null) {
                            out.print(request.getParameter("dataDia"));
                        } else {
                            out.print(rel.getDataRetorno());
                        };%>">
                    <button name="oper" value="PesquisarDia" type="submit"  class="btn btn-success btn-sm"><i class="bi bi-search"></i></button>                   
                </FORM>  


                <canvas id="bar-chart"></canvas>  
            </div> 

            <div style="width: 30px; height: 30px; float: left;"></div> <!<!-- Espaçamento -->

            <%} else if (request.getParameter("oper").equals("Semanal") || request.getParameter("oper").equals("EntreDias")) {%>
            
            <div style="width: 400px; height: 400px; float: left;"></div> <!<!-- Espaçamento -->
            <div style="width: 500px; height: 400px; float: left; ">
                <h1>Semanal</h1>
                <FORM ACTION="RelatoriosServlet" style="center">     
                    <INPUT TYPE="Date" NAME="dataEntreDias1"VALUE="<%if (request.getParameter("dataEntreDias1") != null) {
                            out.print(request.getParameter("dataEntreDias1"));
                        } else {
                            out.print(rel.getDataRetorno2());
                        };%>">
                    <INPUT TYPE="Date" NAME="dataEntreDias2"VALUE="<%if (request.getParameter("dataEntreDias2") != null) {
                            out.print(request.getParameter("dataEntreDias2"));
                        } else {
                            out.print(rel.getDataRetorno());
                         };%>">
                    <button name="oper" value="EntreDias" type="submit"  class="btn btn-success btn-sm"><i class="bi bi-search"></i></button>                   
                </FORM>  


                <canvas  id="bar-chart2"></canvas>
            </div>  
                    <div style="width: 30px; height: 30px; float: left;"></div> <!<!-- Espaçamento -->
            <%} else if (request.getParameter("oper").equals("Mensal") || request.getParameter("oper").equals("PesquisarMes")) {%>
            <div style="width: 400px; height: 400px; float: left;"></div> <!<!-- Espaçamento -->
            <div style="width: 500px; height: 400px; float: left;">
                <h1>Mensal</h1>
                <FORM ACTION="RelatoriosServlet">     
                    <INPUT TYPE="month" NAME="dataMes"VALUE="<%if (request.getParameter("dataMes") != null) {
                            out.print(request.getParameter("dataMes"));
                        } else {
                            out.print(rel.getDataRetorno());
                        };%>">
                    <button name="oper" value="PesquisarMes" type="submit"  class="btn btn-success btn-sm"><i class="bi bi-search"></i></button>                   
                </FORM>  


                <canvas id="bar-chart3"></canvas>                

            </div> 
            <div style="width: 30px; height: 30px; float: left;"></div> <!<!-- Espaçamento -->
            <%} else if (request.getParameter("oper").equals("Anual") || request.getParameter("oper").equals("PesquisarAnual")) {%>
            <div style="width: 400px; height: 400px; float: left;"></div> <!<!-- Espaçamento -->
            <div style="width: 500px; height: 400px; float: left;">
                <h1>Anual</h1>
                <FORM ACTION="RelatoriosServlet">     
                    <INPUT TYPE="" NAME="dataAnual"VALUE="<%if (request.getParameter("dataAnual") != null) {
                            out.print(request.getParameter("dataAnual"));
                        } else {
                            out.print(rel.getDataRetorno());
                        };%>">
                    <button name="oper" value="PesquisarAnual" type="submit"  class="btn btn-success btn-sm"><i class="bi bi-search"></i></button>                   
                </FORM>  


                <canvas id="bar-chart4"></canvas>                

            </div> 
                    <div style="width: 30px; height: 30px; float: left;"></div> <!<!-- Espaçamento -->
            <%}%>
            
            <script>
                new Chart(document.getElementById("bar-chart"), {
                    type: 'bar',
                    data: {
                        labels: ["<%if (request.getParameter("dataDia") != null) {
                                out.print(request.getParameter("dataDia"));
                            } else {
                                out.print(rel.getDataRetorno());
                            };%>"],
                        datasets: [
                            {
                                label: "Liquido",
                                backgroundColor: ["#dfc250"],
                                data: [<%out.print(rel.getPrecoVenda() - rel.getPrecoCompra());%>]


                            },
                            {
                                label: "Bruto",
                                backgroundColor: ["#dadf50"],
                                data: [<%out.print(rel.getPrecoVenda());%>]

                            }
                        ]

                    },

                    options: {
                        legend: {display: false},
                        title: {
                            display: true,
                            text: 'Lucros'
                        }
                    }
                });

                new Chart(document.getElementById("bar-chart2"), {
                    type: 'bar',
                    data: {
                        labels: ["<%if (request.getParameter("dataEntreDias1") == null) {
                                out.print(rel.getDataRetorno2() + " - " + rel.getDataRetorno());
                            } else {
                                out.print(request.getParameter("dataEntreDias1") + " - " + request.getParameter("dataEntreDias2"));
                            }%>"],
                        datasets: [
                            {
                                label: "Liquido",
                                backgroundColor: ["#b5cc7a"],
                                data: [<%out.print(rel.getPrecoVenda() - rel.getPrecoCompra());%>]


                            },
                            {
                                label: "Bruto",
                                backgroundColor: ["#8ccc7a"],
                                data: [<%out.print(rel.getPrecoVenda());%>]

                            }
                        ]

                    },

                    options: {
                        legend: {display: false},
                        title: {
                            display: true,
                            text: 'Lucros'
                        }
                    }
                });




            </script>
            <script>
                new Chart(document.getElementById("bar-chart3"), {
                    type: 'bar',
                    data: {
                        labels: ["<%if (request.getParameter("dataMes") != null) {
                                out.print(request.getParameter("dataMes"));
                            } else {
                                out.print(rel.getDataRetorno());
                            };%>"],
                        datasets: [
                            {
                                label: "Liquido",
                                backgroundColor: ["#57cfd8"],
                                data: [<%out.print(rel.getPrecoVenda() - rel.getPrecoCompra());%>]


                            },
                            {
                                label: "Bruto",
                                backgroundColor: ["#57b2d8"],
                                data: [<%out.print(rel.getPrecoVenda());%>]

                            }
                        ]

                    },

                    options: {
                        legend: {display: false},
                        title: {
                            display: true,
                            text: 'Lucros'
                        }
                    }
                });


            </script>
            <script>
                new Chart(document.getElementById("bar-chart4"), {
                    type: 'bar',
                    data: {
                        labels: ["<%if (request.getParameter("dataAnual") != null) {
                                out.print(request.getParameter("dataAnual"));
                            } else {
                                out.print(rel.getDataRetorno());
                            };%>"],
                        datasets: [
                            {
                                label: "Liquido",
                                backgroundColor: ["#ce68dc"],
                                data: [<%out.print(rel.getPrecoVenda() - rel.getPrecoCompra());%>]


                            },
                            {
                                label: "Bruto",
                                backgroundColor: ["#dc68bf"],
                                data: [<%out.print(rel.getPrecoVenda());%>]

                            }
                        ]

                    },

                    options: {
                        legend: {display: false},
                        title: {
                            display: true,
                            text: 'Lucros'
                        }
                    }
                });
            </script>

            <%}%>         
            
            
            <div id="divImprimir" style="width: 500px; height: 400px;  float: left;">
                <h1></h1>
                
                <table  class="table table-borderless">
                    <thead >

                        <tr>											
                            <th>Produtos</th>
                            <th>Quantidades</th>
                            <th>Bruto</th>
                            <th>Custos</th>
                            <th>Liquido</th>

                        </tr>
                    </thead>


                    <tbody>
                        <%int qtd = 0;%>
                        <%double valor = 0;%>
                        <%double custo = 0;%>
                        <%  for (Relatorios relatorios : relarr) {%>
                        <tr>

                            <td><%out.print(relatorios.getNomeProduto());%></td>
                            <td><%out.print(relatorios.getQuantidade());%></td>
                            <td><%out.print(relatorios.getValorPago());%></td>
                            <td><%out.print(relatorios.getCusto());%></td>
                            <td><%out.print(relatorios.getValorPago() - relatorios.getCusto());%></td>
                            <%qtd = qtd + relatorios.getQuantidade();%>
                            <%valor = valor + relatorios.getValorPago();%>
                            <%custo = custo + relatorios.getCusto();%>




                        </tr>
                        <%}%>  




                    <tfoot>
                        <tr class="table-success">
                            <td><b>Total: </b></td>
                            <td><b> <%out.print(qtd);%></b></td>
                            <td><b><%out.print(valor);%></b></td>
                            <td><b><%out.print(custo);%></b></td>
                            <td><b><%out.print(valor - custo);%></b></td>
                        </tr>
                    </tfoot>
                    </tbody>


                </table>
            </div>   
        <button style="float: left;" id="btn"><i class="bi bi-printer-fill"></i></button>
        <script>
        document.getElementById('btn').onclick = function() {
            var conteudo = document.getElementById('divImprimir').innerHTML,
                tela_impressao = window.open('about:blank');

            tela_impressao.document.write(conteudo);
            tela_impressao.window.print();
            tela_impressao.window.close();
        };
        </script>


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
