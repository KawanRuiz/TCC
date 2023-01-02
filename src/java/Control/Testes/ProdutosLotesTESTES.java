/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Testes;


import DAO.ProdutosLotesDAO;
import Model.Comandas;
import Model.Lotes;
import Model.Produtos;
import Model.Usuarios;
import com.google.gson.Gson;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexoes;
import util.Datas;

/**
 *
 * @author KawaN
 */
public class ProdutosLotesTESTES {
    
   public static void main(String[] args) throws ParseException {
      
    /*            Connection conexao = null; //Teste de insert.
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement("insert into Usuarios (nome,login,senha,nivel) values ('Gislene Santos','gislene','gislene',1);", Statement.RETURN_GENERATED_KEYS);
            pstmt.execute();       
            System.out.println(pstmt);
        } catch (SQLException sql) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }*/
    
                //Buscar Todos
               
                
                ProdutosLotesDAO pd = new ProdutosLotesDAO();         
                List<Lotes> arrp = new ArrayList<Lotes>();
                
                arrp = pd.buscarLotesPorProduto(5);
                for (int i =0; i<arrp.size(); i++){
                    System.out.println("ID: " +arrp.get(i).getIdLote() + "IDP: " +arrp.get(i).getId() + " Nome: " + arrp.get(i).getNome()+ " Quantidade: " +arrp.get(i).getQuantidade() + " Usuario: " + arrp.get(i).getUsuarios().getNome() );
                    System.out.println(arrp.get(i).getValidade());
                    System.out.println(arrp.get(i).getDataCadastro());
                     
                } 
                                   Gson objJson = new Gson();
            ArrayList<Object> arrobj = new ArrayList<Object>();
                            arrobj.add(arrp);
                out.print(objJson.toJson(arrobj));
                
                //Buscar por ID
      
               System.out.println(pd.LotesPorID(2).getUsuarios().getNome());
               
               String OP = "";
               // PRODUTOS
               if (OP == "INSERIR") {                               
                //INSERIR INICIO
               Produtos prod = new Produtos();                
                prod.setNome("Cerveja Itaipava");
                prod.setMarca("Itaipava");
                prod.setPrecoVenda(3.50);
                prod.setKgLt(350);
                prod.setTipoUnidade("Unidade"); //   Copo, Unidade, Fardo, Drink           
                prod.setTipoNome("Cerveja"); // Cerveja, Vodka, Cachaça
                prod.setCodBarras("ITA123");
                prod.setStatusProduto(true);
                pd.inserir(prod);
                } // Fim do INSERIR
               
               if (OP == "ATUALIZAR") {  
                //Atualizar Update
                Produtos prod = new Produtos();  
                
                prod.setNome("Cerveja Lokal");
                prod.setMarca("Lokal");
                prod.setPrecoVenda(3.50);
                prod.setKgLt(350);
                prod.setTipoUnidade("Unidade"); //   Copo, Unidade, Fardo, Drink           
                prod.setTipoNome("Cerveja"); // Cerveja, Vodka, Cachaça
                prod.setCodBarras("Lokal123");
                prod.setStatusProduto(false);
                prod.setId(6); //VEM DA VIEW
                pd.atualizar(prod);
               } 
                if (OP == "DELETAR") {  
                    Produtos prod = new Produtos();
                    prod.setId(5); //VEM DA VIEW
                    pd.deletar(prod);
                }
     
                
                //LOTES
                
                  if (OP == "INSERIRLOTES") {                   
                  Lotes lote = new Lotes();
                  lote.setNumeroLote("BUDIE333");
                 
                  Datas dat = new Datas();
                  lote.setValidade((Date) dat.formatarData("2051-10-1"));
                  lote.setQuantidade(20);
                  lote.setPrecoCompra(2);
                  lote.setId(7); //ID do produto irá fazer a pesquisa antes, id vem do front
                  Usuarios usuarios = new Usuarios();                  
                  usuarios.setId(1); //id vem da sessão
                  lote.setUsuarios(usuarios);
                  pd.inserirLote(lote);            
                                 
                  }
                   if (OP == "ATUALIZARLOTE") {   
                  Lotes lote = new Lotes();
                  lote.setNumeroLote("BUDIE333");
                 
                  Datas dat = new Datas();
                  lote.setValidade((Date) dat.formatarData("2050-10-1"));
                  lote.setQuantidade(66);
                  lote.setPrecoCompra(2);
                  lote.setId(7); //ID do produto irá fazer a pesquisa antes, id vem do front
                  Usuarios usuarios = new Usuarios();                  
                  usuarios.setId(1); //id vem da sessão
                  lote.setUsuarios(usuarios);
                  lote.setIdLote(4); //ID do Lote para ALterar vem da view
                  pd.atualizarLote(lote);              
                       
                   }
                   if (OP == "DELETARLOTE") {  
                    Lotes lote = new Lotes();
                    lote.setIdLote(4); //VEM DA VIEW
                    pd.deletarLotes(lote);  
                            }
                
    } 
     

}
