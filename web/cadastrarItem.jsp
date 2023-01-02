<%-- 
    Document   : cadastrarLote
    Created on : 3 de nov. de 2021, 21:01:45
    Author     : KawaN
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="Model.Comandas"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="Model.Produtos"%>
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
                    List<Comandas> arrc = (List<Comandas>) request.getAttribute("arrc");
                    Comandas c = (Comandas) request.getAttribute("comandas");

                %>              
                <%if (lotes != null) {

                %>
                <h1>Atualizar Lote</h1>
                <FORM ACTION="LotesServlet" style="center">     

                    <b>Nome do Produto</b> <a href='LotesServlet?oper=RetornoProduto'><i class="bi bi-plus-circle"></i></a> <BR>
                    <INPUT TYPE="TEXT" NAME="nome"VALUE="<%out.print(lotes.getNome());%>" placeholder="Selecione o Produto"readonly><BR>
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
                        <button name="oper" value="InserirLote" type="submit"  class="btn btn-success">Enviar</button>
                        <a class='btn btn-danger' href='LotesServlet'>Cancelar</a>

                </FORM>  

                <%} else if (arrc != null) {%>     

                <h1><%out.print(c.getNomeComanda());%></h1>              
                
                
                <a class='btn btn-success' href='ComandasServlet?idComanda=<%out.print(c.getIdComanda());%>&oper=AdicionarItem'>Adicionar Produto <i class="bi bi-cup-straw"></i></a>



                <div>
                    <table class="table table-hover">
                        <thead >

                            <tr>	
                                <th>Imagem</th>
                                <th>Nome</th>
                                <th>Medida - KgLt</th>
                                <th>Recipiente</th>
                                <th>Preço</th>
                                <th>Quantidade</th>
                                <th>Escolher</th>
                            </tr>
                        </thead>


                        <tbody>
                            <% List<Comandas> Listar = new ArrayList<Comandas>();%>
                            <%double soma = 0;%>
                            <%double valorpago = 0;%>
                            <%double falta = 0;%>
                            <%int idComanda = 0;%>

                            <%boolean flag;%>
                            <%if (arrc.size() != 0) {/*IF VERIFICADOR DE ARRAY*/%>    
                            <%int idProdutoNome = 0;%>

                            <%for (int i = 0; i < arrc.size(); i++) {
                                    flag = false;

                                    for (int j = 0; j < i; j++) {
                                        if (arrc.get(j).getLotes().getId() == arrc.get(i).getLotes().getId()) {

                                            flag = true;
                                            break;

                                        }
                                    }

                                    if (flag == false) {
                                        int quantidade = 0;

                                        for (int p = 0; p < arrc.size(); p++) {

                                            if (arrc.get(p).getLotes().getId() == arrc.get(i).getLotes().getId()) {
                                                quantidade = arrc.get(p).getQuantidade() + quantidade;
                                            }
                                        }
                            %>

                            <%idComanda = arrc.get(i).getIdComanda();%>
                            <tr>
                                <td><img src=" <%out.print(arrc.get(i).getLotes().getCaminho());%>" alt="some text" width=123.2 height=92.4"></td>                              
                                <td><%out.print(arrc.get(i).getLotes().getNome());%></td>
                                <td><%out.print(arrc.get(i).getLotes().getKgLt());%></td>
                                <td><%out.print(arrc.get(i).getLotes().getTipoUnidade());%></td>
                                <td><%out.print(arrc.get(i).getLotes().getPrecoVenda());%></td> 

                                <%soma = soma + arrc.get(i).getLotes().getPrecoVenda() * arrc.get(i).getQuantidade();%>
                                <%valorpago = arrc.get(i).getValorPago();%>
                                    
                                    <% String inputAtivo = (String) request.getAttribute("inputAtivo"); %>
                                     <%   if (inputAtivo != "Ativo"){  %>                               
                                
                                <td><%out.print(quantidade);%>                                  

                                    <a  href='ComandasServlet?idProduto=<%out.print(arrc.get(i).getLotes().getId());%>&oper=CliqueNaCaneta&idComanda=<%out.print(arrc.get(i).getIdComanda());%>'><i class="bi bi-pencil"></i></a>
                                     <% }else{;%>
                                <td>  
                                    <FORM ACTION="ComandasServlet" style="center"> 
                                        <INPUT style="width:45px;  "  TYPE="TEXT" NAME="quantidadeNOVA"VALUE="<%out.print(quantidade);%>"> 
                                        <INPUT  TYPE="hidden" NAME="quantidadeANTIGA"VALUE="<%out.print(quantidade);%>"> 
                                        <INPUT  TYPE="hidden" NAME="idProduto"VALUE="<%out.print(arrc.get(i).getLotes().getId());%>"> 
                                        <INPUT  TYPE="hidden" NAME="idComanda"VALUE="<%out.print(arrc.get(i).getIdComanda());%>"> 
                                        <button style="background-color: #4CAF50;" name="oper" value="AtualizarProdutoComanda" type="submit"><i  class="bi bi-check-lg"></i></button> 
                                    </FORM>  

                                        
                                </td> 
                                <%}%>
                                <% quantidade = 0;%>
         
                                <td>                                    
                                    <a class='btn btn-outline-danger' href='ComandasServlet?idProduto=<%out.print(arrc.get(i).getLotes().getId());%>&oper=DeletarProdutoComanda&idComanda=<%out.print(arrc.get(i).getIdComanda());%>'><i class='bi bi-trash'></i></a>
                                <td>

                            </tr>
                            <%idProdutoNome = arrc.get(i).getLotes().getId();%>

                            <%}
                                        }
                                    
                                }/*IF VERIFICADOR DE ARRAY*/%>                     
                        <tfoot>
                            <%falta = soma - valorpago;%>
                            <tr>
                                <td><a class='btn btn-danger' href='ComandasServlet'>Voltar</a></td>
                                <td></td>
                                <td></td>

                                <td>
                                    <b>Total: </b><%out.print(soma);%>R$

                                </td>


                        <FORM ACTION="ComandasServlet" style="center"> 

                            <td>                                   
                                <font color="green">Pago:</font> <INPUT TYPE="TEXT" NAME="valorPago"VALUE="<%out.print(valorpago);%>">  
                                <INPUT TYPE="hidden" NAME="idComanda"VALUE="<%out.print(idComanda);%>"> 
                                <hr> 
                                <% if (falta <= 0 && soma != 0) { %>
                                <font color="green">Pago!</font>
                                <button name="oper" value="FecharComanda" type="submit"  class="btn btn-outline-info">Fechar</button> 
                                <%} else if (falta > 0) { %>
                                <font color="red">Falta: <%out.print(falta);%></font>
                                <button name="oper" value="PagarComanda" type="submit"  class="btn btn-success">Pagar</button> 
                            <td><font size=-3 color="red">Fechar sem pagar</font><br><button name="oper" value="FecharComanda" type="submit"  class="btn btn-danger">Fechar</button> </td>
                                <% }else{%>
                                <button name="oper" value="FecharComanda" type="submit"  class="btn btn-danger">Fechar</button> 
                                <%}%>


                                </hr>

                            </td>
                        </FORM>  
                        </tr>
                        </tfoot>
                        </tbody>
                        <%}%>

                    </table>
                </div>         



            </div>

            <div>
                <%  List<Lotes> produtos = (List<Lotes>) request.getAttribute("produtos"); %>
                <%if (produtos != null) { %>
                <div>
                    <table class="table table-hover">
                        <thead >

                            <tr>
                                <th>Imagem</th>
                                <th>Nome</th>
                                <th>Marca</th>
                                <th>Preço</th>
                                <th>Medida</th>
                                <th>Recipiente</th>


                                <th>Quantidade</th>

                                <th>Escolher</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>

                            </tr>
                        </tfoot>

                        <tbody>
                            <%int id = 0;%>
                            <%  for (Lotes prod : produtos) {%>
                            <tr>
                                <%

                                    if (prod.getId() != id) {//Não repetir o produto%> 
                                <td><img src=" <%out.print(prod.getCaminho());%>" alt="some text" width=98.56 height=73.92"></td>
                                <td><%out.print(prod.getNome());%></td>
                                <td><%out.print(prod.getMarca());%></td>
                                <td><%out.print(prod.getPrecoVenda());%> R$</td>
                                <td><%out.print(prod.getKgLt());%></td>
                                <td><%out.print(prod.getTipoUnidade());%></td>                                
                                <%id = prod.getId();%>

                        <FORM ACTION="ComandasServlet" style="center"> 
                            <INPUT TYPE="hidden" NAME="idComanda"VALUE="<%out.print(c.getIdComanda());%>">   
                            <INPUT TYPE="hidden" NAME="idProduto"VALUE="<%out.print(prod.getId());%>">  
                            <td><INPUT TYPE="TEXT" NAME="Quantidade"VALUE=""></td>

                            <td>
                                <button name="oper" value="InserirItem" type="submit"  class="btn btn-success">Selecionar <i class="bi bi-cup-straw"></i></button>



                            <td>
                        </FORM>  
                        </tr>
                        <%}%> 
                        <%}%>                     
                        <%}%>        
                        </tbody>


                    </table>

                </div>




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