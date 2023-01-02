package Control;

import DAO.ProdutosLotesDAO;
import Model.Lotes;
import Model.Usuarios;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

public class Upload extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Upload() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        
        // Verifica o tipo multipart/form-data*/
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                // Parse do request
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                /**
                 * Escreve o arquivo na pasta img, dentro do build
                 * C:\Users\seu_usuario\Documents\NetBeansProjects\UploadImagem\build\web\img
                 */
                //Pegar o INPUT quando vem junto com arquivos
                FileItemFactory itemfactory = new DiskFileItemFactory();
                Map<String, String> fields = new HashMap<>();
                Map<String, List<String>> multiValueFields = new HashMap<>();//Pegar o INPUT quando vem junto com arquivos

                for (FileItem item : multiparts) {

                 //Pegar o INPUT quando vem junto com arquivos       
                    String name = item.getFieldName();
                    String value = item.getString();
                    if (!multiValueFields.containsKey(name)) {
                        multiValueFields.put(name, new ArrayList<String>());
                    }
                    fields.put(name, value);
                    multiValueFields.get(name).add(value);
                    //Pegar o INPUT quando vem junto com arquivos FIM
                    
                    
                    if (!item.isFormField()) {
                        String ext = FilenameUtils.getExtension(item.getName());
                        if (ext.equals("png") || ext.equals("PNG") || ext.equals("jpg") || ext.equals("JPG") || ext.equals("JPEG") || ext.equals("jpeg")) {

                            System.out.println("ID Produto " + fields.get("idProduto")); //Comando para pegar o input
                            item.write(new File(request.getServletContext().getRealPath("/img") + File.separator + fields.get("idProduto")+item.getName()));
                            Lotes lote = new Lotes();
                            lote.setCaminho(fields.get("idProduto")+item.getName());
                            lote.setTipo(ext);
                            lote.setId(Integer.parseInt(fields.get("idProduto")));
                            ProdutosLotesDAO pdao = new ProdutosLotesDAO();
                            pdao.atualizarIMG(lote);
                            response.sendRedirect("ProdutosServlet?idProduto=" + lote.getId() + "&oper=EditarProduto");
                            System.out.println(ext);
                            request.setAttribute("message", "Arquivo carregado com sucesso");

                        } else {
                            request.setAttribute("message", "Só é permitido arquivos do tipo: PNG e JPG");
                        }
                    }
                }

                /**
                 * Aqui deve ser colocado o procedimento para gravar o nome do
                 * arquivo no banco O nome do arquivo postado deve ser
                 * recuperado com o método: item.getName()
                 */
            } catch (Exception ex) {
                request.setAttribute("message", "Upload de arquivo falhou devido a " + ex);
            }

        } else {
            request.setAttribute("message", "Apenas para upload de imagens");
        }

        //  request.getRequestDispatcher("/upload.jsp").forward(request, response);
    }
    }
}
