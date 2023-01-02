/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.ProdutosLotesDAO;
import Model.Lotes;
import Model.Produtos;
import Model.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Datas;

/**
 *
 * @author KawaN
 */
public class LotesServlet extends HttpServlet {

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
                 request.setAttribute("erro", "Você não tem permissão para acessar Lotes.");
                
                 request.getRequestDispatcher("ComandasServlet").forward(request, response);
                
            }          
            else if(usuario.getNivel() == 2) {
                
                       
            
            
            ProdutosLotesDAO pd = new ProdutosLotesDAO();
            List<Lotes> arrp = new ArrayList<Lotes>();

            String op = ""; //Iniciando a variavel de comparação
            op = request.getParameter("oper"); //pegando o request da opção selecionada.

            //Verificando se OP é nulo
            if (op == null) {
                op = "BuscarTodos";
                arrp = pd.buscarLotes();

                request.setAttribute("arrp", arrp);
                request.getRequestDispatcher("buscarLotes.jsp").forward(request, response);
              //  System.out.println("Aq");

            }

            if (0 == (op.compareTo("InserirLote"))) {

                //INSERIR INICIO
                Lotes lote = new Lotes();
                lote.setNumeroLote(request.getParameter("numeroLote"));

                Datas dat = new Datas();

                lote.setValidade((Date) dat.formatarData(request.getParameter("validade")));
                lote.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                lote.setPrecoCompra(Double.parseDouble(request.getParameter("precoCompra")));
                lote.setId(Integer.parseInt(request.getParameter("idProduto"))); //ID do produto irá fazer a pesquisa antes, id vem do front
              //  Usuarios usuarios = new Usuarios();
               // usuarios.setId(usuario.getId()); //id vem da sessão
                lote.setUsuarios(usuario);
                pd.inserirLote(lote);
                // Fim do INSERIR
                //LISTAR
                op = "BuscarTodos";
                arrp = pd.buscarLotes();
                request.setAttribute("arrp", arrp);
                request.getRequestDispatcher("buscarLotes.jsp").forward(request, response);
                //FIM DO LISTAR

            } else if (0 == (op.compareTo("EditarLote"))) {

                Lotes lotes = new Lotes();
                lotes = pd.LotesPorID(Integer.parseInt(request.getParameter("idLote")));

                request.setAttribute("lotes", lotes);
                request.getRequestDispatcher("cadastrarLote.jsp").forward(request, response);

            } else if (0 == (op.compareTo("AtualizarLote"))) {

             
                  Lotes lote = new Lotes();
                  lote.setNumeroLote(request.getParameter("numeroLote"));
                 
                  Datas dat = new Datas();
                  lote.setValidade((Date) dat.formatarData(request.getParameter("validade")));
                  lote.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                  lote.setPrecoCompra(Double.parseDouble(request.getParameter("precoCompra")));
                  lote.setId(Integer.parseInt(request.getParameter("idProduto"))); //ID do produto irá fazer a pesquisa antes, id vem do front                            
                
                  lote.setUsuarios(usuario);
                  lote.setIdLote(Integer.parseInt(request.getParameter("idLote"))); //ID do Lote para ALterar vem da view
                  pd.atualizarLote(lote);  
                  
                arrp = pd.buscarLotes();
                request.setAttribute("arrp", arrp);
                request.getRequestDispatcher("buscarLotes.jsp").forward(request, response);

            } else if (0 == (op.compareTo("DeletarLote"))) {
                Lotes lotes = new Lotes();
                System.out.println(request.getParameter("idLote"));
                lotes.setIdLote(Integer.parseInt(request.getParameter("idLote"))); //VEM DA VIEW
                pd.deletarLotes(lotes);

                arrp = pd.buscarLotes();
                request.setAttribute("arrp", arrp);
                request.getRequestDispatcher("buscarLotes.jsp").forward(request, response);

            } else if (0 == (op.compareTo("RetornoProduto"))) {
                List<Produtos> arr = new ArrayList<Produtos>();
                arr = pd.buscarTudo();
                request.setAttribute("arr", arr);
                request.getRequestDispatcher("cadastrarLote.jsp").forward(request, response);
                
            }else if (0 == (op.compareTo("ProdutoSelecionado"))) {
                    
                    Produtos prod = new Produtos();
                    
                    prod = pd.buscarPorId(Integer.parseInt(request.getParameter("idProduto")));
                    
                    request.setAttribute("prodi", prod);
                    request.getRequestDispatcher("cadastrarLote.jsp").forward(request, response);
                    
            } else {
                String teste;
                teste = request.getParameter("oper");
                System.out.println(teste + " Está no Else");

            }
            } //FINAL DO VALIDAR LOGIN

        } catch (ParseException ex) {
            Logger.getLogger(LotesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
