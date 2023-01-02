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
import Model.Usuarios;
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
public class DevedoresServlet extends HttpServlet {

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
                Comandas comandas2 = new Comandas();
                List<Comandas> arrp = new ArrayList<Comandas>();
                 List<Comandas> arrp2 = new ArrayList<Comandas>();

                if (op == null) {
                    op = "Buscar";

                    comandas = dao.Sistema("Buscar");
                    arrp = dao.buscarTudoDevedores();
                    arrp2 = dao.buscarTudoFechadas();

                    request.setAttribute("arrp", arrp);
                    request.setAttribute("arrp2", arrp2);
                    request.setAttribute("comandas", comandas);
                    request.setAttribute("comandas2", comandas2);
                    request.getRequestDispatcher("devedores.jsp").forward(request, response);

                }
                

            }//Login
        
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
