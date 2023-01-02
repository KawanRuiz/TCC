/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.ComandasDAO;
import DAO.ProdutosLotesDAO;
import Model.Comandas;
import Model.Lotes;
import Model.Produtos;
import Model.Usuarios;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KawaN
 */
public class ProdutosServlet extends HttpServlet {

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

            //Validando LOGIN
            Usuarios usuario = (Usuarios) request.getSession().getAttribute("usuario");

            if (usuario == null) {
                System.out.println("Usuario nulo");
                response.sendRedirect("index.jsp");
            } else if (usuario.getNivel() == 1){
                 request.setAttribute("erro", "Você não tem permissão para acessar Produtos.");
                
                 request.getRequestDispatcher("ComandasServlet").forward(request, response);
                
            }          
            else if(usuario.getNivel() == 2) {

                                
                
                ProdutosLotesDAO pd = new ProdutosLotesDAO();
                List<Produtos> arrp = new ArrayList<Produtos>();
                String op = ""; //Iniciando a variavel de comparação
                op = request.getParameter("oper"); //pegando o request da opção selecionada.

                //Verificando se OP é nulo
                if (op == null) {
                    op = "BuscarTodos";
                    arrp = pd.buscarTudo();
                    request.setAttribute("arrp", arrp);
                    request.getRequestDispatcher("buscarProdutos.jsp").forward(request, response);
                    System.out.println("Aq");

                }

                if (0 == (op.compareTo("INSERIRPRODUTO"))) {

                    //INSERIR INICIO
                    Produtos prod = new Produtos();
                    prod.setNome(request.getParameter("nome"));
                    prod.setMarca(request.getParameter("marca"));
                    prod.setPrecoVenda(Double.parseDouble(request.getParameter("precoVenda")));
                    prod.setKgLt(Integer.parseInt(request.getParameter("kgLt")));
                    prod.setTipoUnidade(request.getParameter("tipoUnidade")); //   Copo, Unidade, Fardo, Drink           
                    prod.setTipoNome(request.getParameter("tipoNome")); // Cerveja, Vodka, Cachaça
                    prod.setCodBarras(request.getParameter("codBarras"));
                    prod.setStatusProduto(true);
                    int idProduto = pd.inserir(prod);
                    System.out.println("ID do Produto Inserido" + idProduto);
                    //Imagem
                     Lotes lotes = new Lotes();
                    lotes.setCaminho("sem-imagem.png");
                    lotes.setTipo("png");
                    lotes.setId(idProduto);                   
                   
                    pd.inserirIMG(lotes);
                    // Fim do INSERIR
                    //LISTAR
                    op = "BuscarTodos";
                    arrp = pd.buscarTudo();
                    request.setAttribute("arrp", arrp);
                    request.getRequestDispatcher("buscarProdutos.jsp").forward(request, response);
                    //FIM DO LISTAR

                } else if (0 == (op.compareTo("EditarProduto"))) {

                    Produtos prod = new Produtos();
                    prod = pd.buscarPorId(Integer.parseInt(request.getParameter("idProduto")));

                    request.setAttribute("prod", prod);
                    request.getRequestDispatcher("cadastrarProduto.jsp").forward(request, response);

                } else if (0 == (op.compareTo("AtualizarPRODUTO"))) {

                    Produtos prod = new Produtos();
                    prod.setNome(request.getParameter("nome"));
                    prod.setMarca(request.getParameter("marca"));
                    prod.setPrecoVenda(Double.parseDouble(request.getParameter("precoVenda")));
                    prod.setKgLt(Integer.parseInt(request.getParameter("kgLt")));
                    prod.setTipoUnidade(request.getParameter("tipoUnidade")); //   Copo, Unidade, Fardo, Drink           
                    prod.setTipoNome(request.getParameter("tipoNome")); // Cerveja, Vodka, Cachaça
                    prod.setCodBarras(request.getParameter("codBarras"));
                    prod.setStatusProduto(true);
                    prod.setId(Integer.parseInt(request.getParameter("idProduto"))); //VEM DA VIEW
                    pd.atualizar(prod);

                    arrp = pd.buscarTudo();
                    request.setAttribute("arrp", arrp);
                    request.getRequestDispatcher("buscarProdutos.jsp").forward(request, response);

                } else if (0 == (op.compareTo("DeletarProduto"))) {
                    Produtos prod = new Produtos();
                    prod.setId(Integer.parseInt(request.getParameter("idProduto"))); //VEM DA VIEW
                    pd.deletar(prod);

                    arrp = pd.buscarTudo();
                    request.setAttribute("arrp", arrp);
                    request.getRequestDispatcher("buscarProdutos.jsp").forward(request, response);

                } else {
                    String teste;
                    teste = request.getParameter("oper");
                    System.out.println(teste + " Está no Else");

                }

                // Gson objJson = new Gson();
                // ArrayList<Object> arrobj = new ArrayList<Object>();
                // arrobj.add(arrp);
                //  out.print(objJson.toJson(arrp));
                // System.out.println(objJson.toJson(arrp));
            }
        }
    }

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
