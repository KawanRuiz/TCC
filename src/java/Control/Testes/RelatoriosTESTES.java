/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Testes;


import DAO.ComandasDAO;
import DAO.ProdutosLotesDAO;
import DAO.RelatoriosDAO;
import Model.Comandas;
import Model.Lotes;
import Model.Produtos;
import Model.Relatorios;
import Model.Usuarios;
import com.google.gson.Gson;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexoes;
import util.Datas;

/**
 *
 * @author KawaN
 */
public class RelatoriosTESTES {
    
   public static void main(String[] args) throws ParseException {
      
       
       
                       Relatorios rel = new Relatorios();
                RelatoriosDAO reldao = new RelatoriosDAO();
                ComandasDAO cdao = new ComandasDAO();
                Comandas comandas = new Comandas();
                comandas = cdao.Sistema("Buscar");
                
                List<Relatorios> relarr = new ArrayList<Relatorios>();
                
                
                                relarr = reldao.relMes("2021-11");
                                for(int i = 0; i<relarr.size(); i++){
                    
                    System.out.println(+i+"Nome: "+relarr.get(i).getNomeProduto()+" Quantidade: "+relarr.get(i).getQuantidade()+" Valor Pago: " +relarr.get(i).getValorPago()+" Custo: "+ relarr.get(i).getCusto());
                }
                System.out.println(" ");
                relarr = reldao.relEntreDatas("2021-11-17","2021-11-17");
                                for(int i = 0; i<relarr.size(); i++){
                    
                    System.out.println(+i+"Nome: "+relarr.get(i).getNomeProduto()+" Quantidade: "+relarr.get(i).getQuantidade()+" Valor Pago: " +relarr.get(i).getValorPago()+" Custo: "+ relarr.get(i).getCusto());
                }
                    
                  System.out.println(" ");
                   relarr = reldao.relDia("2021-11-17");
                
                for(int i = 0; i<relarr.size(); i++){
                    
                    System.out.println(+i+"Nome: "+relarr.get(i).getNomeProduto()+" Quantidade: "+relarr.get(i).getQuantidade()+" Valor Pago: " +relarr.get(i).getValorPago()+" Custo: "+ relarr.get(i).getCusto());
                } 
       
    
     /*  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         
       
          GregorianCalendar c = new GregorianCalendar();
       c.setTime(comandas.getIncio_dia());
       c.add(Calendar.DAY_OF_MONTH, -1);
       String dataFormatada = dateFormat.format(c.getTime());
       System.out.println(dataFormatada);
       
       // Diario
       rel = reldao.buscarDia("2021-11-14");
       
       System.out.println("Gastou: " + rel.getPrecoCompra());
     
       System.out.println("Vendeu: " +rel.getPrecoVenda());
              
       System.out.println("Lucro Liquido: " + (rel.getPrecoVenda() - rel.getPrecoCompra()));*/
       

       // Fim diario
       
       //Entre datas
     /*  rel = reldao.buscarEntreDatas("01-09-2021","26-09-2021");
       
       System.out.println("Gastou: " + rel.getPrecoCompra());
     
       System.out.println("Vendeu: " +rel.getPrecoVenda());
              
       System.out.println("Lucro Liquido: " + (rel.getPrecoVenda() - rel.getPrecoCompra()));*/
       
       //Mensal
     /*for(int i = 1; i < 13; i++ ){
           
       System.out.println("Mês "+i);
       if(i<10){
         rel = reldao.buscarMes("0"+i+"/2021");  
       }else{
           rel = reldao.buscarMes(i+"/2021");  
       }
               
       System.out.println("Gastou: " + rel.getPrecoCompra());
     
       System.out.println("Vendeu: " +rel.getPrecoVenda());
              
       System.out.println("Lucro Liquido: " + (rel.getPrecoVenda() - rel.getPrecoCompra()));
      
       } */
      /* //Anual
               rel = reldao.buscarAno("2021");
       
       System.out.println("Gastou: " + rel.getPrecoCompra());
     
       System.out.println("Vendeu: " +rel.getPrecoVenda());
              
       System.out.println("Lucro Liquido: " + (rel.getPrecoVenda() - rel.getPrecoCompra()));
       */
       
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
               
               
                
                
                
                /*
                RelatoriosDAO dao = new RelatoriosDAO();         
                List<Comandas> arrp = new ArrayList<Comandas>();
              
                
                arrp = dao.buscarAno("2021");
                for (int i =0; i<arrp.size(); i++){
                    
                    System.out.println("ID: " +arrp.get(i).getIdItens()+ " Quantidade: " + arrp.get(i).getQuantidade()+ " Valor Pago: " +arrp.get(i).getValorPago()+ " Data: " + arrp.get(i).getDataVenda());
                    System.out.println(arrp.get(i).getLotes().getNome()+ " " + arrp.get(i).getLotes().getKgLt() +"ml");
                    
                } 
   
                //Buscar por ID
      
            //   System.out.println(dao.buscarItensID(6).getProdutos().getId());
               
               String OP = "";
               // PRODUTOS
               if (OP == "DIA") {                               
                //BUSCAR POR DIA
                arrp = dao.buscarDia("01/10/2021");
                for (int i =0; i<arrp.size(); i++){
                    System.out.println("ID: " +arrp.get(i).getIdItens()+ " Quantidade: " + arrp.get(i).getQuantidade()+ " Valor Pago: " +arrp.get(i).getValorPago()+ " Data: " + arrp.get(i).getDataVenda());
                    System.out.println(arrp.get(i).getLotes().getNome()+ " " + arrp.get(i).getLotes().getKgLt() +"ml");
                    
                } 
                } // BUSCAR POR DIA
               
               if (OP == "ENTREDIAS") {  
                 arrp = dao.buscarEntreDias("01/09/2021","01/10/2021");
                for (int i =0; i<arrp.size(); i++){
                    System.out.println("ID: " +arrp.get(i).getIdItens()+ " Quantidade: " + arrp.get(i).getQuantidade()+ " Valor Pago: " +arrp.get(i).getValorPago()+ " Data: " + arrp.get(i).getDataVenda());
                    System.out.println(arrp.get(i).getLotes().getNome()+ " " + arrp.get(i).getLotes().getKgLt() +"ml");
                    
                } 
               } 
                if (OP == "BUSCARMES") {  
                arrp = dao.buscarMes("10/2021");
                for (int i =0; i<arrp.size(); i++){
                    System.out.println("ID: " +arrp.get(i).getIdItens()+ " Quantidade: " + arrp.get(i).getQuantidade()+ " Valor Pago: " +arrp.get(i).getValorPago()+ " Data: " + arrp.get(i).getDataVenda());
                    System.out.println(arrp.get(i).getLotes().getNome()+ " " + arrp.get(i).getLotes().getKgLt() +"ml");
                    
                } 
                }
                    
                
                  if (OP == "BUSCARANO") {                   
                 arrp = dao.buscarAno("2021");
                for (int i =0; i<arrp.size(); i++){
                    
                    System.out.println("ID: " +arrp.get(i).getIdItens()+ " Quantidade: " + arrp.get(i).getQuantidade()+ " Valor Pago: " +arrp.get(i).getValorPago()+ " Data: " + arrp.get(i).getDataVenda());
                    System.out.println(arrp.get(i).getLotes().getNome()+ " " + arrp.get(i).getLotes().getKgLt() +"ml");
                    
                }           
                                 
                  }
                   */
    } 
     

}
