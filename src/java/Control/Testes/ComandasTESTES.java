/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Testes;


import DAO.ComandasDAO;
import DAO.ProdutosLotesDAO;
import Model.Comandas;
import Model.Lotes;
import Model.Produtos;
import Model.Usuarios;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexoes;
import util.Datas;

/**
 *
 * @author KawaN
 */
public class ComandasTESTES {
    
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
               
              /* ComandasDAO dao = new ComandasDAO();   
                Comandas comandas = new Comandas();     
                comandas = dao.Sistema("Buscar");
                System.out.println(comandas.getIncio_dia());
                System.out.println(comandas.getNumeroComanda());
                System.out.println(comandas.isStatus_dia());*/
    
              
              //CODIGO PARA LISTAR APENAS UM SEM DUPLICADOS E CALCULAR UM CAMPO
                ComandasDAO dao = new ComandasDAO();         
                List<Comandas> arrp = new ArrayList<Comandas>();             
                arrp = dao.buscarItensComanda(18);
                System.out.println("Lista que vem do Banco");
                for (int i =0; i<arrp.size(); i++){
                     
                    System.out.println("ID: " +arrp.get(i).getIdItens()+" "+ arrp.get(i).getNomeComanda()+ " ID Produto: "+ arrp.get(i).getLotes().getId() + " Nome: " + arrp.get(i).getLotes().getNome()+ " Quantidade: " + arrp.get(i).getQuantidade());
                    
                } ;
                System.out.println("  ");
                System.out.println("Lista formatada");
                int quantidade = 0;
                boolean flag;
                for (int i =0; i<arrp.size(); i++){
                    
                    flag = false;
                    for (int j =0; j<i; j++){
                      
                        if (arrp.get(j).getLotes().getId() == arrp.get(i).getLotes().getId()){                            
                           
                         flag = true;
                         break;
                         
                        }                  
                                   
                    }
                    if(flag == false){
                        
                      System.out.println("ID: " +arrp.get(i).getIdItens()+" "+ arrp.get(i).getNomeComanda()+ " ID Produto: "+ arrp.get(i).getLotes().getId() + " Nome: " + arrp.get(i).getLotes().getNome()+ " Quantidade: " + arrp.get(i).getQuantidade());
                        
                      for (int p =0; p<arrp.size(); p++){
                            if (arrp.get(i).getLotes().getId() == arrp.get(p).getLotes().getId()){
                                quantidade = quantidade + arrp.get(p).getQuantidade();
                                
                            }
                            
                        }
                      
                      System.out.println("Quantidade: " + quantidade);
                      quantidade = 0;
                    }                
                  
                } ;
  //CODIGO ACIMA PARA LISTAR APENAS UM SEM DUPLICADOS E CALCULAR UM CAMPO 
           
       

        // ajuste do tamanho do vetor resultante
   
       
   

     
                //Buscar por ID
      
             //  System.out.println(dao.buscarItensID(6).getProdutos().getId());
            

            
            
             
             
             
             /*   Comandas comandas = new Comandas();
               Lotes lotes = new Lotes();
             
                ProdutosLotesDAO pd = new ProdutosLotesDAO();         
                List<Lotes> arrlotes = new ArrayList<Lotes>();
                lotes.setId(5); //PRODUTO QUE VEM DA VIEW
                comandas.setLotes(lotes);
                
                arrlotes = pd.buscarLotesPorProduto(comandas.getLotes().getId()); // 
                
                int soma =0;
                int resultado = 0;
               comandas.setQuantidade(300); // QUANTIDADE DA VIEW
                int EntradaQTD = comandas.getQuantidade();
               for (int i =0; i<arrlotes.size() && comandas.getQuantidade() > 0; i++){                   
                   if (comandas.getQuantidade()>= arrlotes.get(i).getQuantidade()){
                       soma = comandas.getQuantidade() - arrlotes.get(i).getQuantidade();
                       comandas.setQuantidade(comandas.getQuantidade() - arrlotes.get(i).getQuantidade());
                       
                       resultado = resultado + arrlotes.get(i).getQuantidade();
                     System.out.println("QTD Maior " + arrlotes.get(i).getQuantidade());

                   
                   System.out.println("Soma " + soma);
                   System.out.println("Comandas QTD " + comandas.getQuantidade());
                       lotes.setIdLote(arrlotes.get(i).getIdLote());
                        lotes.setQuantidade(0);
                        pd.atualizarQuantidadeLote(lotes);
                       
                        System.out.println("Lotes ID: " + lotes.getIdLote());
                       System.out.println("Lotes Quantidade: " + lotes.getQuantidade());
                   
                   if (comandas.getQuantidade() == 0){
                        System.out.println("Tem no estoque " + resultado);

                        
                   }else if (i + 1 == arrlotes.size()){
                       System.out.println("Não tem no estoque " + resultado);
                       //Retorna o total do estoque 
                       
                   }
                      
                   
                 //   comandas.setQuantidade(soma); 
                   }else if (comandas.getQuantidade()<= arrlotes.get(i).getQuantidade() && comandas.getQuantidade() > 0){
                       
                        soma = arrlotes.get(i).getQuantidade() - comandas.getQuantidade(); //Numero para debitar no lote
                        comandas.setQuantidade(0);
                        
                        System.out.println("QTD Menor " + arrlotes.get(i).getQuantidade());
                      
                       System.out.println("Soma Menor " + soma ); //soma é o retorno para o banco.
                       lotes.setIdLote(arrlotes.get(i).getIdLote());
                        lotes.setQuantidade(soma);
                        pd.atualizarQuantidadeLote(lotes);
                        
                   System.out.println("Retorno " + EntradaQTD);
                 //                         lotes.setIdLote(arrlotes.get(i).getIdLote());
                  //      lotes.setQuantidade(soma);
                  //      pd.atualizarQuantidadeLote(lotes);
                   
                     
                   }else{
                        System.out.println("Algo deu errado!");
                   }

                   
               }*/
               
               
              
     
                   
                   
               
               
           /*  
               String OP = "";
               // PRODUTOS
               if (OP == "INSERIR") {                               
                //INSERIR INICIO
                Comandas comandas = new Comandas();            

                Datas dat = new Datas();
                comandas.setDataVenda((Date) dat.formatarData("01-10-2021")); //Sera a data do inicio do dia.
                Produtos produtos = new Produtos();                  
                produtos.setId(1); //id vem da sessão
                comandas.setProdutos(produtos);
                dao.inserir(comandas);
                } // Fim do INSERIR
               
               if (OP == "ATUALIZAR") {  
                //Atualizar Update
                Comandas comandas = new Comandas();            
                comandas.setNomeComanda("Comanda 5");
                comandas.setNumero(5);
                comandas.setStatus(true);
                Datas dat = new Datas();
                comandas.setDataComanda((Date) dat.formatarData("01-10-2021")); //Sera a data do inicio do dia.
                Usuarios usuarios = new Usuarios();                  
                usuarios.setId(1); //id vem da sessão
                comandas.setUsuarios(usuarios);
                comandas.setIdComanda(5); //Vem da VIEW
                dao.atualizar(comandas);
               } 
                if (OP == "DELETAR") {  
                    Comandas comandas = new Comandas();  
                    comandas.setIdComanda(5); //VEM DA VIEW
                    dao.deletar(comandas);
                }
     
                
                //Itens Comanda.
                
                  if (OP == "INSERIRITENS") {                   
                    Comandas comandas = new Comandas();     
                    comandas.setQuantidade(12);
                    comandas.setValorPago(50);
                    comandas.setPrecoCompra(3.50);//vem do lote
                    
                    Datas dat = new Datas();
                    comandas.setDataVenda((Date) dat.formatarData("01-10-2021")); // Será do dia da comanda
                    comandas.setIdComanda(2); //Será pego na view
                    Produtos produtos = new Produtos();
                    produtos.setId(1); // Sera pego na view
                    comandas.setProdutos(produtos);
                   
                    dao.inserirItens(comandas);
           
                                 
                  }
                   if (OP == "ATUALIZARITENS") {   
                    Comandas comandas = new Comandas();     
                    comandas.setQuantidade(12);
                    comandas.setValorPago(30);
                    comandas.setPrecoCompra(3.50);
                    
                    Datas dat = new Datas();
                    comandas.setDataVenda((Date) dat.formatarData("02-09-2021")); // Será do dia da comanda
                    comandas.setIdComanda(2); //Será pego na view
                    Produtos produtos = new Produtos();
                    produtos.setId(1); // Sera pego na view
                    comandas.setProdutos(produtos);
                    comandas.setIdItens(5);
                    dao.atualizarItens(comandas);              
                       
                   }
                   if (OP == "DELETARITENS") {  
                       Comandas comandas = new Comandas();   
                       comandas.setIdItens(2);
                       dao.deletarItens(comandas);

                            }
                
    } 
     */

}}
