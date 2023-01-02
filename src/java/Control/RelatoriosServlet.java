/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.ComandasDAO;
import DAO.RelatoriosDAO;
import Model.Comandas;
import Model.Relatorios;
import Model.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Datas;

/**
 *
 * @author KawaN
 */
public class RelatoriosServlet extends HttpServlet {

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
            } else if (usuario.getNivel() == 1) {
                request.setAttribute("erro", "Você não tem permissão para acessar Relatórios.");

                request.getRequestDispatcher("ComandasServlet").forward(request, response);

            } else if (usuario.getNivel() == 2) { //LOGIN ^
                //INICIO 

                String op = ""; //Iniciando a variavel de comparação
                op = request.getParameter("oper"); //pegando o request da opção selecionada.
                List<Relatorios> relarr = new ArrayList<Relatorios>();
                Relatorios rel = new Relatorios();
                RelatoriosDAO reldao = new RelatoriosDAO();
                ComandasDAO cdao = new ComandasDAO();
                Comandas comandas = new Comandas();
                comandas = cdao.Sistema("Buscar");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dataFormatada = dateFormat.format(comandas.getIncio_dia());
                if (op == null) { // Verificação inicial

                    op = "Nulo";
                    response.sendRedirect("relatorios.jsp");

                }

                if (0 == (op.compareTo("Diario"))) {
                   
                    rel = reldao.buscarDia(dataFormatada);

                    rel.setDataRetorno(dataFormatada);
                    
                       relarr = reldao.relDia(dataFormatada);
                       request.setAttribute("relarr", relarr);
                    request.setAttribute("relatorio", rel);
                    request.getRequestDispatcher("dadosRelatorios.jsp").forward(request, response);
                } else if (0 == (op.compareTo("PesquisarDia"))) {

                    rel = reldao.buscarDia(request.getParameter("dataDia"));
                    rel.setDataRetorno(request.getParameter("dataDia"));
                    
                     
                      relarr = reldao.relDia(request.getParameter("dataDia"));
                       request.setAttribute("relarr", relarr);
                    request.setAttribute("relatorio", rel);
                    request.getRequestDispatcher("dadosRelatorios.jsp").forward(request, response);

                } else if (0 == (op.compareTo("Semanal"))) {
                    //Subtrair -7 dias
                    GregorianCalendar c = new GregorianCalendar();
                    c.setTime(comandas.getIncio_dia());
                    c.add(Calendar.DAY_OF_MONTH, -7);
                    String dataFormatadaMenos7 = dateFormat.format(c.getTime()); // Formatando a Data subtraida
                    rel.setDataRetorno2(dataFormatadaMenos7);
                    rel.setDataRetorno(dataFormatada);

                   
                    rel = reldao.buscarEntreDatas(rel.getDataRetorno2(), rel.getDataRetorno());

                    rel.setDataRetorno2(dataFormatadaMenos7); //Adicionando novamente.
                    rel.setDataRetorno(dataFormatada); //Adicionando novamente.
                    relarr = reldao.relEntreDatas(rel.getDataRetorno2(),rel.getDataRetorno());
                    request.setAttribute("relarr", relarr);
                    request.setAttribute("relatorio", rel);
                    request.getRequestDispatcher("dadosRelatorios.jsp").forward(request, response);

                } else if (0 == (op.compareTo("EntreDias"))) {
                    rel = reldao.buscarEntreDatas(request.getParameter("dataEntreDias1"), request.getParameter("dataEntreDias2"));
                    
                    relarr = reldao.relEntreDatas(request.getParameter("dataEntreDias1"),request.getParameter("dataEntreDias2"));
                    request.setAttribute("relarr", relarr);
                    request.setAttribute("relatorio", rel);
                    request.getRequestDispatcher("dadosRelatorios.jsp").forward(request, response);

                } else if (0 == (op.compareTo("Mensal"))) {

                    DateFormat dateFormatMes = new SimpleDateFormat("yyyy-MM");
                    String dateMes = dateFormatMes.format(comandas.getIncio_dia());

                    rel = reldao.buscarMes(dateMes);
                    rel.setDataRetorno(dateMes);
                    relarr = reldao.relMes(dateMes);
                    request.setAttribute("relarr", relarr);
                    request.setAttribute("relatorio", rel);
                    request.getRequestDispatcher("dadosRelatorios.jsp").forward(request, response);

                } else if (0 == (op.compareTo("PesquisarMes"))) {
                    rel = reldao.buscarMes(request.getParameter("dataMes"));
                    rel.setDataRetorno(request.getParameter("dataMes"));
                    
                    relarr = reldao.relMes(request.getParameter("dataMes"));
                    request.setAttribute("relarr", relarr);
                    request.setAttribute("relatorio", rel);
                    request.getRequestDispatcher("dadosRelatorios.jsp").forward(request, response);

                } else if (0 == (op.compareTo("Anual"))) {

                    DateFormat dateFormatAno = new SimpleDateFormat("yyyy");
                    String dateAno = dateFormatAno.format(comandas.getIncio_dia());
                    rel = reldao.buscarAno(dateAno);
                    rel.setDataRetorno(dateAno);
                    relarr = reldao.relAno(dateAno);
                     request.setAttribute("relarr", relarr);
                    request.setAttribute("relatorio", rel);
                    request.getRequestDispatcher("dadosRelatorios.jsp").forward(request, response);

                } else if (0 == (op.compareTo("PesquisarAnual"))) {

                    rel = reldao.buscarAno(request.getParameter("dataAnual"));
                    rel.setDataRetorno(request.getParameter("dataAnual"));
                     relarr = reldao.relAno(request.getParameter("dataAnual"));
                     request.setAttribute("relarr", relarr);
                    request.setAttribute("relatorio", rel);
                    request.getRequestDispatcher("dadosRelatorios.jsp").forward(request, response);

                }

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
