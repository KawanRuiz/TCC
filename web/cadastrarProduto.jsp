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




            <div>

                <%  Produtos prod = (Produtos) request.getAttribute("prod"); %>              
                <%if (prod != null) {

                %>
                <h1>Atualizar Produto</h1>
                <img src=" <%out.print(prod.getCaminho());%>" alt="some text" width=15% height=10%">
                
                <form action="Upload" method="post" enctype="multipart/form-data">
                     <input type="hidden" name="idProduto" id="idProduto" value="<%out.print(prod.getId());%>">
                    <input type="file" name="file" id="file"/> 
                    <input type="submit" value="Enviar" />
                </form>

                <FORM ACTION="ProdutosServlet" style="center">     

                    <b>Nome do Produto</b> <BR>
                    <INPUT TYPE="TEXT" NAME="nome"VALUE="<%out.print(prod.getNome());%>"><BR>

                    <b> Marca</b> <BR>
                    <INPUT TYPE="TEXT" NAME="marca"VALUE="<%out.print(prod.getMarca());%>"><P>

                        <b>   Preço de Venda</b> <BR>
                    <INPUT TYPE="TEXT" NAME="precoVenda"VALUE="<%out.print(prod.getPrecoVenda());%>"><P>

                        <b>      Medida </b><BR>
                    <INPUT TYPE="TEXT" NAME="kgLt"VALUE="<%out.print(prod.getKgLt());%>" placeholder="kg/Lt"><P>                    


                        <b>   Tipo</b> <BR>
                    <INPUT TYPE="TEXT" NAME="tipoNome"VALUE="<%out.print(prod.getTipoNome());%>" placeholder="Cerveja, Vodka, Cachaça..."><P>

                        <b>  Codigo de Barras </b>  <BR>
                    <INPUT TYPE="TEXT" NAME="codBarras"VALUE="<%out.print(prod.getCodBarras());%>"><P>
                        <b> Recipiente</b><BR>
                        <select Name="tipoUnidade">  
                            <option value="<%out.print(prod.getTipoUnidade());%>"><%out.print(prod.getTipoUnidade());%>
                            <option value="Lata">Lata
                            <option value="Copo">Copo   
                            <option value="Garrafa">Garrafa
                            <option value="Fardo">Fardo
                            <option value="Drink">Drink                                     
                            <option value="Outros">Outros
                        </select><br>   
                        <br>   
                        <input type="hidden" name="idProduto" value="<%out.print(prod.getId());%>">
                        <button name="oper" value="AtualizarPRODUTO" type="submit" class="btn btn-success">Enviar</button>
                        <a class='btn btn-danger' href='ProdutosServlet?idProduto='>Cancelar</a>





                </FORM>
                <% } else { %>
                <h1>Cadastrar Produtos</h1>
                <FORM ACTION="ProdutosServlet" style="center">     

                    <b>Nome do Produto</b> <BR>
                    <INPUT TYPE="TEXT" NAME="nome"VALUE=""><BR>

                    <b> Marca</b> <BR>
                    <INPUT TYPE="TEXT" NAME="marca"VALUE=""><P>

                        <b>   Preço de Venda</b> <BR>
                    <INPUT TYPE="TEXT" NAME="precoVenda"VALUE=""><P>

                        <b>      Medida </b><BR>
                    <INPUT TYPE="TEXT" NAME="kgLt"VALUE="" placeholder="kg/Lt"><P>                    


                        <b>   Tipo</b> <BR>
                    <INPUT TYPE="TEXT" NAME="tipoNome"VALUE="" placeholder="Cerveja, Vodka, Cachaça..."><P>

                        <b>  Codigo de Barras </b>  <BR>
                    <INPUT TYPE="TEXT" NAME="codBarras"VALUE=""><P>
                        <b> Recipiente</b><BR>
                        <select Name="tipoUnidade">                              
                            <option value="Lata">Lata
                            <option value="Copo">Copo   
                            <option value="Garrafa">Garrafa
                            <option value="Fardo">Fardo
                            <option value="Drink">Drink                                     
                            <option value="Outros">Outros
                        </select><br>   
                        <br>
                        <button name="oper" value="INSERIRPRODUTO" type="submit" class="btn btn-success">Enviar</button>
                        <a class='btn btn-danger' href='ProdutosServlet?idProduto='>Cancelar</a>

                </FORM>              


                <%}%>          


            </div>


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
