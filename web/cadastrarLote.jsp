<%-- 
    Document   : cadastrarLote
    Created on : 3 de nov. de 2021, 21:01:45
    Author     : KawaN
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="Model.Produtos"%>
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
                
                 <%  Lotes lotes = (Lotes) request.getAttribute("lotes"); 
                     Produtos prodi = (Produtos) request.getAttribute("prodi"); 
                     SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd"); 
                     
                 %>              
                <%if (lotes != null){
                    
               %>
               <h1>Atualizar Lote</h1>
                                 <FORM ACTION="LotesServlet" style="center">     
                   
                    <b>Nome do Produto</b> <a href='LotesServlet?oper=RetornoProduto'><i class="bi bi-plus-circle"></i></a> <BR>
                    <INPUT TYPE="TEXT" NAME="nome"VALUE="<%out.print(lotes.getNome());%>" placeholder="Selecione o Produto"readonly><BR>
                    <INPUT TYPE="hidden" NAME="idLote"VALUE="<%out.print(lotes.getIdLote());%>">
                    <INPUT TYPE="hidden" NAME="idProduto"VALUE="<%out.print(lotes.getId());%>"><P>    
                   <b>Medida</b> <BR>
                  <INPUT TYPE="TEXT" NAME="kgLt"VALUE="<%out.print(lotes.getKgLt());%>" placeholder="ml/Lt" readonly><P>    
                        
                     <b>Preço de Compra</b> <BR>
                    <INPUT TYPE="TEXT" NAME="precoCompra"VALUE="<%out.print(lotes.getPrecoCompra());%>"  placeholder="R$" ><P>
                     
                      <b>Quantidade</b><BR>
                    <INPUT TYPE="TEXT" NAME="quantidade"VALUE="<%out.print(lotes.getQuantidade());%>"><P>                    
                       

                         <b>Validade</b> <BR>
                    <INPUT TYPE="Date" NAME="validade"VALUE="<%out.print(lotes.getValidade());%>"><P>
                     
                    <b>Numero do Lote </b>  <BR> 
                       <INPUT TYPE="TEXT" NAME="numeroLote"VALUE="<%out.print(lotes.getNumeroLote());%>"><P>
                        
                          <br>
                          <button name="oper" value="AtualizarLote" type="submit"  class="btn btn-success">Enviar</button>
                          <a class='btn btn-danger' href='LotesServlet'>Cancelar</a>
                   
                </FORM>  
                         
                <%}else if(prodi != null){%>              
                 <h1>Cadastrar Lote</h1>
                 <FORM ACTION="LotesServlet" style="center">     
                   
                    <b>Nome do Produto</b> <a href='LotesServlet?oper=RetornoProduto'><i class="bi bi-plus-circle"></i></a> <BR>
                    <INPUT TYPE="TEXT" NAME="nome"VALUE="<%out.print(prodi.getNome());%>" placeholder="Selecione o Produto"readonly><BR>
                    <INPUT TYPE="hidden" NAME="idProduto"VALUE="<%out.print(prodi.getId());%>"><P>    
                   <b>Medida</b> <BR>
                  <INPUT TYPE="TEXT" NAME="kgLt"VALUE="<%out.print(prodi.getKgLt());%>" placeholder="ml/Lt"readonly><P>    
                        
                     <b>Preço de Compra</b> <BR>
                    <INPUT TYPE="TEXT" NAME="precoCompra"VALUE=""  placeholder="R$" ><P>
                     
                      <b>Quantidade</b><BR>
                    <INPUT TYPE="TEXT" NAME="quantidade"VALUE=""><P>                    
                       

                         <b>Validade</b> <BR>
                    <INPUT TYPE="Date" NAME="validade"VALUE=""><P>
                     
                    <b>Numero do Lote </b>  <BR> 
                       <INPUT TYPE="TEXT" NAME="numeroLote"VALUE=""><P>
                        
                          <br>
                          <button name="oper" value="InserirLote" type="submit"  class="btn btn-success">Enviar</button>
                          <a class='btn btn-danger' href='LotesServlet'>Cancelar</a>
                   
                </FORM>             
                          
                          
                          
              <% }else{ %>
              <h1>Cadastrar Lote</h1>
                 <FORM ACTION="LotesServlet" style="center">     
                   
                    <b>Nome do Produto</b> <a href='LotesServlet?oper=RetornoProduto'><i class="bi bi-plus-circle"></i></a> <BR>
                    <INPUT TYPE="TEXT" NAME="nome"VALUE="" placeholder="Selecione o Produto"disabled><BR>
                    
                   <b hidden>Medida</b> <BR hidden>
                  <INPUT TYPE="hidden" NAME="kgLt"VALUE="" placeholder="ml/Lt"disabled><P>    
                        
                     <b hidden>Preço de Compra</b> <BR hidden>
                    <INPUT TYPE="hidden" NAME="precoCompra"VALUE=""  placeholder="R$" disabled><P>
                     
                      <b hidden>Quantidade</b><BR hidden>
                    <INPUT TYPE="hidden" NAME="quantidade"VALUE=""disabled><P>                    
                       

                         <b hidden>Validade</b> <BR hidden>
                    <INPUT TYPE="hidden" NAME="validade"VALUE=""disabled><P>
                     
                    <b hidden>Numero do Lote </b>  <BR hidden> 
                       <INPUT TYPE="hidden" NAME="numeroLote"VALUE=""disabled><P>
                        
                          <br>
                          <button name="oper" value="InserirLote" type="submit" disabled class="btn btn-success">Enviar</button>
                          <a class='btn btn-danger' href='LotesServlet'>Cancelar</a>
                   
                </FORM>   
              <%  List<Produtos> arrp = (List<Produtos>) request.getAttribute("arr"); %>
              <%if (arrp != null){ %>
               <div>
            <table class="table">
                <thead >
                   
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
                        <th>Escolher</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
           
                    </tr>
                </tfoot>

                <tbody>
                    <%  for (Produtos prod : arrp) {%>
                    <tr>
                         <td><img src=" <%out.print(prod.getCaminho());%>" alt="some text" width=92.4 height=115.5"></td>
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
                            <a class='btn btn-success' href='LotesServlet?idProduto=<%out.print(prod.getId());%>&oper=ProdutoSelecionado'>Selecionar <i class="bi bi-cup-straw"></i></a>
                            
                        
                        <td>
                    </tr>
                    <%}%>                     

                </tbody>


            </table>
            </div>         
                <%}%>  
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