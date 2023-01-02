/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.UsuariosDAO;
import Model.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KawaN
 */
public class UsuariosServlet extends HttpServlet {

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
            
            Usuarios usuarios = new Usuarios();
            UsuariosDAO udao = new UsuariosDAO();
            usuarios = udao.buscarLoginSenha(request.getParameter("login"), request.getParameter("senha"));
            //usuarios = udao.buscarLoginSenha("kawan", "kawan"); //REMOVER
            
            String op = ""; //Iniciando a variavel de comparação
            op = request.getParameter("oper"); //pegando o request da opção selecionada.
            
            
            
            //Verificando se OP é nulo
            if (op == null) {                           
                response.sendRedirect("index.jsp");
            }

            if (0 == (op.compareTo("Logar"))) {              
         
            if (usuarios.getLogin() != null && usuarios.getSenha() != null && !usuarios.getLogin().isEmpty() && !usuarios.getSenha().isEmpty()) {
                
                
                System.out.println("Nivel: " + usuarios.getNivel() + " Nome: " + usuarios.getNome());
                request.getSession().setAttribute("usuario", usuarios);
                response.sendRedirect("ComandasServlet");
               // request.getRequestDispatcher("ComandasServlet").forward(request, response);

            } else {
                System.out.println("Login ou senha invalidos!");
                request.setAttribute("erro", "Login ou Senha Invalidos!");
                
                 request.getRequestDispatcher("index.jsp").forward(request, response);
              //  response.sendRedirect("index.jsp");
                
                
              //  response.sendRedirect("index.jsp");
            }
            
           }else if (0 == (op.compareTo("Deslogar"))) {    
               request.getSession().invalidate();
               response.sendRedirect("index.jsp");
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
