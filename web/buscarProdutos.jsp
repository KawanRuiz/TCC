<%@page import="Model.Usuarios"%>
<%@page import="Model.Produtos"%>
<%@page import="java.util.List"%>
<%@page import="Model.Lotes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

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

    <body>

        <nav class="menu-navigation-icons">
            <a href="ComandasServlet" class="menu-magenta"><i class="bi bi-clipboard"></i><span>Comandas</span></a>
            <a href="ProdutosServlet" class="menu-blue"><i class="bi bi-cup-straw"></i><span>Produtos</span></a>
            <a href="LotesServlet" class="menu-green"><i class="bi bi-collection"></i><span>Lotes</span></a>
            <a href="RelatoriosServlet" class="menu-yellow"><i class="bi bi-graph-up"></i><span>Relatórios</span></a>
            <a href="DevedoresServlet" class="menu-red"><i class="bi bi-clipboard-x"></i><span>Devedores</span></a>
        </nav>


        <div class="menu">


            <h1>Produtos</h1>
            <a href="cadastrarProduto.jsp" class="btn btn-secondary"><i class="bi bi-cup-straw"></i><span>Cadastrar Produto</span></a>
            

            <div>
            <table class="table">
                <thead >
                    <%  List<Produtos> arrp = (List<Produtos>) request.getAttribute("arrp"); %>
                    <tr>											
                        <th>Imagem</th>
                        <th>Nome</th>
                        <th>Marca</th>
                        <th>Preço</th>
                        <th>Medida</th>
                        <th>Recipiente</th>
                        <th>Tipo</th>
                        <th>Codigo de Barras</th>
                        <th>Data de Cadastro</th>
                        <th>Status</th>
                        <th>Editar</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
           
                    </tr>
                </tfoot>

                <tbody>
                    <%  for (Produtos prod : arrp) {%>
                    <tr>
                         <td>
                             <img src=" <%out.print(prod.getCaminho());%>" alt="some text" width=154 height=115.5">
                            
                         </td>
                        <td><%out.print(prod.getNome());%></td>
                        <td><%out.print(prod.getMarca());%></td>
                        <td><%out.print(prod.getPrecoVenda());%> R$</td>
                        <td><%out.print(prod.getKgLt());%></td>
                        <td><%out.print(prod.getTipoUnidade());%></td>
                        <td><%out.print(prod.getTipoNome());%></td>
                        <td><%out.print(prod.getCodBarras());%></td>
                        <td><%out.print(prod.getDataCadastro());%></td>
                        <td><%out.print(prod.getStatusProduto());%></td>
                        <td>
                            <a class='btn btn-outline-warning' href='ProdutosServlet?idProduto=<%out.print(prod.getId());%>&oper=EditarProduto'><i class="bi bi-pencil"></i></a>
                            <a class='btn btn-outline-danger' href='ProdutosServlet?idProduto=<%out.print(prod.getId());%>&oper=DeletarProduto'><i class="bi bi-trash"></i></a>
                        
                        <td>
                    </tr>
                    

                    <%}%>                     

                </tbody>


            </table>
            </div>             
           


                  
            <%Usuarios usuario = (Usuarios) request.getSession().getAttribute("usuario");%>
           <%if (usuario != null){%> 
           <b> Logado: </b><% out.print(usuario.getNome());%>
            <b><a class="btn btn-danger btn-sm" href='UsuariosServlet?oper=Deslogar'><i class="bi bi-box-arrow-right"></i></a>
             <%}%>   

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
