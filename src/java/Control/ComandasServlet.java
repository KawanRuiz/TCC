/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.Arrays;
import java.util.Collections;
import DAO.ComandasDAO;
import DAO.ProdutosLotesDAO;
import Model.Comandas;
import Model.Lotes;
import Model.Produtos;
import Model.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KawaN
 */
public class ComandasServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            //Validando LOGIN
            Usuarios usuario = (Usuarios) request.getSession().getAttribute("usuario");

            if (usuario == null) {               
                response.sendRedirect("index.jsp");
            } else {
               

                String op = ""; //Iniciando a variavel de comparação
                op = request.getParameter("oper"); //pegando o request da opção selecionada.

                ComandasDAO dao = new ComandasDAO();
                Comandas comandas = new Comandas();
                List<Comandas> arrp = new ArrayList<Comandas>();

                if (op == null) {
                    op = "Buscar";

                    comandas = dao.Sistema("Buscar");
                    arrp = dao.buscarTudo();

                    request.setAttribute("arrp", arrp);
                    request.setAttribute("comandas", comandas);
                    request.getRequestDispatcher("comandas.jsp").forward(request, response);

                }

                if (0 == (op.compareTo("NovoDia"))) {

                    dao.Sistema("Abrir");

                    dao.Sistema("Buscar");
                    comandas = dao.Sistema("Buscar");
                    request.setAttribute("comandas", comandas);

                    arrp = dao.buscarTudo();

                    request.setAttribute("arrp", arrp);
                    request.getRequestDispatcher("comandas.jsp").forward(request, response);

                } else if (0 == (op.compareTo("FecharDia"))) {
                    dao.Sistema("Fechar");

                    comandas = dao.Sistema("Buscar");
                    request.setAttribute("comandas", comandas);
                    request.getRequestDispatcher("comandas.jsp").forward(request, response);

                } else if (0 == (op.compareTo("NovaComanda"))) {

                    dao.Sistema("numeroComanda"); //Atualiza o Sistema com o numero da comanda. Numero + 1

                    comandas = dao.Sistema("Buscar"); //Busca os dados do Sistema
                    comandas.setNomeComanda("Comanda " + comandas.getNumeroComanda());
                    comandas.setNumero(comandas.getNumeroComanda());
                    comandas.setStatus(true);

                    comandas.setDataComanda(comandas.getIncio_dia()); //Sera a data do inicio do dia.

                    Usuarios usuarios = new Usuarios();
                    usuario.setId(usuario.getId()); //id vem da sessão
                    comandas.setUsuarios(usuario);
                   // comandas.setIdComanda(5); //Vem da VIEW /*///////////////////////////////////////////////////////////////////////////////////////////VERIFICAR DEPOIS
                    dao.inserir(comandas);

                    dao.Sistema("Buscar");
                    comandas = dao.Sistema("Buscar");
                    request.setAttribute("comandas", comandas);

                    arrp = dao.buscarTudo();

                    request.setAttribute("arrp", arrp);
                    request.getRequestDispatcher("comandas.jsp").forward(request, response);

                } else if (0 == (op.compareTo("AbrirComanda"))) {

                    arrp = dao.buscarItensComanda(Integer.parseInt(request.getParameter("idComanda")));
                    comandas = dao.buscarPorId(Integer.parseInt(request.getParameter("idComanda")));

                    request.setAttribute("arrc", arrp);
                    request.setAttribute("comandas", comandas);
                    request.getRequestDispatcher("cadastrarItem.jsp").forward(request, response);

                } else if (0 == (op.compareTo("AdicionarItem"))) { //Gera a aba de produtos

                    arrp = dao.buscarItensComanda(Integer.parseInt(request.getParameter("idComanda")));
                    comandas = dao.buscarPorId(Integer.parseInt(request.getParameter("idComanda")));

                    request.setAttribute("arrc", arrp);
                    request.setAttribute("comandas", comandas);

                    List<Lotes> lotes = new ArrayList<Lotes>();
                    ProdutosLotesDAO pd = new ProdutosLotesDAO();
                    lotes = pd.buscarLotes();
                    request.setAttribute("produtos", lotes);
                    request.getRequestDispatcher("cadastrarItem.jsp").forward(request, response);

                } else if (0 == (op.compareTo("InserirItem"))) { //INSERE OS PRODUTOS NA COMANDA //////////////////////////////////////////////////////////////DASDASDASDASDASDAS
                    /* System.out.println(request.getParameter("Quantidade"));
                System.out.println(request.getParameter("idComanda"));
                System.out.println(request.getParameter("idProduto"));*/

                    comandas = dao.Sistema("Buscar");

                    comandas.setDataVenda(comandas.getIncio_dia()); //Sera a data do inicio do dia.

                    Lotes lotes = new Lotes();
                    ProdutosLotesDAO pd = new ProdutosLotesDAO();
                    List<Lotes> arrlotes = new ArrayList<Lotes>();
                    lotes.setId(Integer.parseInt(request.getParameter("idProduto"))); //PRODUTO PEGO NA VIEW
                    comandas.setLotes(lotes);

                    arrlotes = pd.buscarLotesPorProduto(comandas.getLotes().getId());//PASSANDO O ID DO PRODUTO PEGO DA VIEW ACIMA

                    //Variaveis de Controle
                    int soma = 0;
                    int resultado = 0;
                    boolean EntrouNoIF = true;
                    comandas.setQuantidade(Integer.parseInt(request.getParameter("Quantidade"))); //QUANTIDADE DO PRODUTO VEM DA VIEW

                    //FOR PARA DEBITAR DOS LOTES
                    int EntradaQTD = comandas.getQuantidade();
                    for (int i = 0; i < arrlotes.size() && EntradaQTD > 0; i++) {

                        if (EntradaQTD <= arrlotes.get(i).getQuantidade()) { //SE A QUANTIDADE FOR MENOR QUE A QUANTIDADE DO LOTE.

                            soma = arrlotes.get(i).getQuantidade() - EntradaQTD; //Calculo para atualizar o lote
                            //DEBITANDO DO LOTE
                            lotes.setIdLote(arrlotes.get(i).getIdLote());
                            lotes.setQuantidade(soma);
                            pd.atualizarQuantidadeLote(lotes);
                            //INSERINDO NA COMANDA
                            comandas.setQuantidade(EntradaQTD); //Quantidade selecionada
                            comandas.setIdComanda(Integer.parseInt(request.getParameter("idComanda"))); //Será pego na view      
                            comandas.setLotes(lotes);
                            dao.inserirItens(comandas);

                            //Controle
                            EntradaQTD = 0; //Zerar a entrada.

                        } else if (EntradaQTD >= arrlotes.get(i).getQuantidade() ) {

                            EntradaQTD = EntradaQTD - arrlotes.get(i).getQuantidade();
                            lotes.setIdLote(arrlotes.get(i).getIdLote());
                            lotes.setQuantidade(0); //Lote com quantidade inferior a entrada.
                            pd.atualizarQuantidadeLote(lotes);

                            //INSERINDO NA COMANDA
                            comandas.setQuantidade(arrlotes.get(i).getQuantidade()); //Quantidade selecionada
                            comandas.setIdComanda(Integer.parseInt(request.getParameter("idComanda"))); //Será pego na view      
                            comandas.setLotes(lotes);
                            dao.inserirItens(comandas);

                        }

                    }

                    /* comandas.setIdComanda(Integer.parseInt(request.getParameter("idComanda"))); //SerÃ¡ pego na view                
                comandas.setLotes(lotes);               
                dao.inserirItens(comandas); */
                    arrp = dao.buscarItensComanda(Integer.parseInt(request.getParameter("idComanda")));
                    comandas = dao.buscarPorId(Integer.parseInt(request.getParameter("idComanda")));

                    request.setAttribute("arrc", arrp);
                    request.setAttribute("comandas", comandas);
                    request.getRequestDispatcher("cadastrarItem.jsp").forward(request, response);

                }//FIm do INSERIR ITENS NA COMANDA
                else if (0 == (op.compareTo("PagarComanda"))) { // BOTAO PAGAR COMANDA ATUALIZAR OS VALORES

                    comandas.setValorPago(Double.parseDouble(request.getParameter("valorPago")));
                    System.out.println("Valor Pago: " + request.getParameter("valorPago") + " Id Comanda: " + Integer.parseInt(request.getParameter("idComanda")));
                    comandas.setIdComanda(Integer.parseInt(request.getParameter("idComanda")));
                    dao.atualizarValorPago(comandas);

                    arrp = dao.buscarItensComanda(Integer.parseInt(request.getParameter("idComanda")));
                    comandas = dao.buscarPorId(Integer.parseInt(request.getParameter("idComanda")));

                    request.setAttribute("arrc", arrp);
                    request.setAttribute("comandas", comandas);
                    request.getRequestDispatcher("cadastrarItem.jsp").forward(request, response);

                } else if (0 == (op.compareTo("FecharComanda"))) { // BOTAO FECHAR COMANDA
                    comandas.setIdComanda(Integer.parseInt(request.getParameter("idComanda")));
                    dao.deletar(comandas);

                    response.sendRedirect("ComandasServlet");
                } else if (0 == (op.compareTo("DeletarProdutoComanda"))) { // BOTAO DELETAR ITENS COMANDA

                    ProdutosLotesDAO pd = new ProdutosLotesDAO();
                    Lotes lotes = new Lotes();

                    arrp = dao.buscarComandaProduto(Integer.parseInt(request.getParameter("idComanda")), Integer.parseInt(request.getParameter("idProduto")));
                    int quantidade = 0;
                    for (int i = 0; i < arrp.size(); i++) {
                        lotes = pd.LotesPorID(arrp.get(i).getLotes().getIdLote());       //Busca a quantidade do Lote            
                        lotes.setQuantidade(arrp.get(i).getQuantidade() + lotes.getQuantidade()); // Soma a Qunatidade do Lote com o da comanda
                        pd.atualizarQuantidadeLote(lotes); // Atualiza a quantidade no Lote
                        dao.deletarItens(arrp.get(i).getIdItens());   //Deleta o Item da Comanda               

                    }

                    // LISTAR NOVAMENTE OS ITENS
                    arrp = dao.buscarItensComanda(Integer.parseInt(request.getParameter("idComanda")));
                    comandas = dao.buscarPorId(Integer.parseInt(request.getParameter("idComanda")));

                    request.setAttribute("arrc", arrp);
                    request.setAttribute("comandas", comandas);
                    request.getRequestDispatcher("cadastrarItem.jsp").forward(request, response);
                } else if (0 == (op.compareTo("CliqueNaCaneta"))) { //BOTAO PARA LIBERAR O INPUT

                    // LISTAR NOVAMENTE OS ITENS
                    arrp = dao.buscarItensComanda(Integer.parseInt(request.getParameter("idComanda")));
                    comandas = dao.buscarPorId(Integer.parseInt(request.getParameter("idComanda")));

                    String inputAtivo = "Ativo"; //Libera o Campo de quantidade

                    request.setAttribute("inputAtivo", inputAtivo);
                    request.setAttribute("arrc", arrp);
                    request.setAttribute("comandas", comandas);
                    request.getRequestDispatcher("cadastrarItem.jsp").forward(request, response);

                } else if (0 == (op.compareTo("AtualizarProdutoComanda"))) {

                    //MESMO PROCEDIMENTO DO DeletarProdutoComanda
                    ProdutosLotesDAO pd = new ProdutosLotesDAO();
                    Lotes lotes = new Lotes();
                    arrp = dao.buscarComandaProduto(Integer.parseInt(request.getParameter("idComanda")), Integer.parseInt(request.getParameter("idProduto")));
                    int quantidade = 0;
                    for (int i = 0; i < arrp.size(); i++) {
                        lotes = pd.LotesPorID(arrp.get(i).getLotes().getIdLote());       //Busca a quantidade do Lote            
                        lotes.setQuantidade(arrp.get(i).getQuantidade() + lotes.getQuantidade()); // Soma a Qunatidade do Lote com o da comanda
                        pd.atualizarQuantidadeLote(lotes); // Atualiza a quantidade no Lote
                        dao.deletarItens(arrp.get(i).getIdItens());   //Deleta o Item da Comanda               

                    }//MESMO PROCEDIMENTO DO DeletarProdutoComanda             
                    response.sendRedirect("ComandasServlet?idComanda=" + Integer.parseInt(request.getParameter("idComanda")) + "&idProduto=" + Integer.parseInt(request.getParameter("idProduto")) + "&Quantidade=" + Integer.parseInt(request.getParameter("quantidadeNOVA")) + "&oper=InserirItem");

                }

            }//Login
        }//Try
    }//PROCESS

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
