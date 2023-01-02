<%@page import="Model.Usuarios"%>
<%@page import="java.awt.Color"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="Model.Comandas"%>
<%@page import="Model.Produtos"%>
<%@page import="java.util.List"%>
<%@page import="Model.Lotes"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >

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


    </head>

    <body  >

        <nav class="menu-navigation-icons">
            <a href="ComandasServlet" class="menu-magenta"><i class="bi bi-clipboard"></i><span>Comandas</span></a>
            <a href="ProdutosServlet" class="menu-blue"><i class="bi bi-cup-straw"></i><span>Produtos</span></a>
            <a href="LotesServlet" class="menu-green"><i class="bi bi-collection"></i><span>Lotes</span></a>
            <a href="RelatoriosServlet" class="menu-yellow"><i class="bi bi-graph-up"></i><span>Relatórios</span></a>
            <a href="DevedoresServlet" class="menu-red"><i class="bi bi-clipboard-x"></i><span>Devedores</span></a>
           
           
        </nav>
        

        <div class="menu">

          
            <div >

                <%  Comandas comandas = (Comandas) request.getAttribute("comandas"); %>              
                <%if (comandas != null) {

                %>

                <h1>Devedores</h1>
                <font color="red">  <%if(request.getAttribute("erro") != null){out.print(request.getAttribute("erro"));}%></font><br>
                <i>Data: <%out.print(comandas.getIncio_dia());%></i><p>

                <table  class="table table-hover" >
                    <thead >
                        <%  List<Comandas> arrp = (List<Comandas>) request.getAttribute("arrp"); %> 
                        <tr>											

                            <th></th>
                            <th>Comanda</th>

                            <th>Data</th>
                            <th>Status</th>
                            <th>Ações</th>
                            <th></th>
                            <th></th>
                            <th></th>

                        </tr>
                    </thead>
                    <tfoot >

                    </tfoot>

                    <tbody >
                        
                        <%  for (Comandas c : arrp) {%>
                        <tr >

                     
                            <td></td>
                            <td ><font color="red" face="arial"><%out.print(c.getNomeComanda());%></td>

                            <td><font color="red"><%out.print(c.getDataComanda());%></td>
                            <td><font color="red"><%if (c.isStatus() == true) {%> Aberta <%} else {%> Fechada <%} %></td>



                            <td>

                                <a class='btn btn-outline-danger btn-sm' href='ComandasServlet?idComanda=<%out.print(c.getIdComanda());%>&oper=AbrirComanda'><i class="bi bi-box-arrow-in-up-right"></i> Abrir</a>

                            <td>   

                        </tr>

                        <%}%>  

                    </tbody>


                </table>




                <% } else { %>


                <h1>Comandas <a class='btn btn-success'  href='ComandasServlet?oper=NovoDia'><i class="bi bi-arrow-right-circle"></i> Iniciar o Dia </a>  </h1>         



                <%}%>          


            </div>
                
               <div >

                <%  Comandas comandas2 = (Comandas) request.getAttribute("comandas2");%>              
                <%if (comandas2 != null) {

                %>

                <h1>Fechadas</h1>
                <font color="red">  <%if(request.getAttribute("erro") != null){out.print(request.getAttribute("erro"));}%></font><br>
                <i>Data: <%out.print(comandas.getIncio_dia());%></i><p>

                <table  class="table table-hover" >
                    <thead >
                        <%  List<Comandas> arrp2 = (List<Comandas>) request.getAttribute("arrp2"); %> 
                        <tr>											

                            <th></th>
                            <th>Comanda</th>

                            <th>Data</th>
                            <th>Status</th>
                            <th>Ações</th>
                            <th></th>
                            <th></th>
                            <th></th>

                        </tr>
                    </thead>
                    <tfoot >                       
                    </tfoot>

                    <tbody >
                        
                        <%  for (Comandas c : arrp2) {%>
                        <tr >

                     
                            <td></td>
                            <td ><font color="grey" face="arial"><%out.print(c.getNomeComanda());%></td>

                            <td><font color="grey"><%out.print(c.getDataComanda());%></td>
                            <td><font color="grey"><%if (c.isStatus() == true) {%> Aberta <%} else {%> Fechada <%} %></td>



                            <td>

                                <a class='btn btn-outline-dark btn-sm' href='ComandasServlet?idComanda=<%out.print(c.getIdComanda());%>&oper=AbrirComanda'><font color="grey"><i class="bi bi-eye-fill"></i> Verificar</a>

                            <td>   

                        </tr>

                        <%}%>  
                       
                    </tbody>


                </table>




                <% } else { %>


                <h1>Comandas <a class='btn btn-success'  href='ComandasServlet?oper=NovoDia'><i class="bi bi-arrow-right-circle"></i> Iniciar o Dia </a>  </h1>         



                <%}%>          


            </div>    

           <%Usuarios usuario = (Usuarios) request.getSession().getAttribute("usuario");%>
           <%if (usuario != null){%> 
           <b> Logado: </b><% out.print(usuario.getNome());%>
            <b><a class="btn btn-danger btn-sm" href='UsuariosServlet?oper=Deslogar'><i class="bi bi-box-arrow-right"></i></a>
                <%}%>

        </div>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="assets/slicknav/jquery.slicknav.min.js"></script>

        <script>
            $(function () {
                $('.menu-navigation-icons').slicknav();
            });
        </script>

    </body>

</html>
