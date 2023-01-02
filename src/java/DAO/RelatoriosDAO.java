/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Comandas;
import Model.Relatorios;
import Model.Produtos;
import Model.Usuarios;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexoes;
import util.Datas;
import java.sql.Date;

/**
 *
 * @author KawaN
 */
public class RelatoriosDAO {

    private String buscarDia = "select SUM (c.valorPago) as precoVenda, SUM (ic.quantidade *  l.precocompra) as precoCompra from ItensComanda ic, Comandas c, Lotes l, Produtos p where ic.FK_idComandas = c.id and c.status = false and l.FK_idProdutos = p.id and ic.FK_idProdutos = p.id and ic.FK_idLotes = l.id and TO_CHAR(c.datacomanda, 'YYYY-MM-DD') = ?;";
    private String buscarEntreDatas = "select SUM (c.valorPago)  as precoVenda, SUM (ic.quantidade *  l.precocompra) as precoCompra from ItensComanda ic, Comandas c, Lotes l, Produtos p  where ic.FK_idComandas = c.id and c.status = false and l.FK_idProdutos = p.id and ic.FK_idProdutos = p.id and ic.FK_idLotes = l.id and c.datacomanda >= ?::DATE and c.datacomanda <= ?::DATE";
    private String buscarMes = "select SUM (c.valorPago)  as precoVenda, SUM (ic.quantidade *  l.precocompra) as precoCompra from ItensComanda ic, Comandas c, Lotes l, Produtos p where ic.FK_idComandas = c.id and c.status = false and l.FK_idProdutos = p.id and ic.FK_idProdutos = p.id and ic.FK_idLotes = l.id and TO_CHAR(c.datacomanda, 'YYYY-MM') = ?;";
    private String buscarAno = "select SUM (c.valorPago)  as precoVenda, SUM (ic.quantidade *  l.precocompra) as precoCompra from ItensComanda ic, Comandas c, Lotes l, Produtos p where ic.FK_idComandas = c.id and c.status = false and l.FK_idProdutos = p.id and ic.FK_idProdutos = p.id and ic.FK_idLotes = l.id and TO_CHAR(c.datacomanda, 'YYYY') = ?;";
    //Relatorios
    private String relDia = "select p.nome as nomeproduto,  ic.quantidade as qtdProduto, c.valorpago as CValor, (l.precocompra * ic.quantidade) as custo from ItensComanda ic, Comandas c, Lotes l, Produtos p  where ic.FK_idComandas = c.id and c.status = false and l.FK_idProdutos = p.id and ic.FK_idProdutos = p.id and ic.FK_idLotes = l.id and TO_CHAR(c.datacomanda, 'YYYY-MM-DD') = ?;";
    private String relEntreDatas = "select p.nome as nomeproduto,  ic.quantidade as qtdProduto, c.valorpago as CValor, (l.precocompra * ic.quantidade) as custo from ItensComanda ic, Comandas c, Lotes l, Produtos p  where ic.FK_idComandas = c.id and c.status = false and l.FK_idProdutos = p.id and ic.FK_idProdutos = p.id and ic.FK_idLotes = l.id and c.datacomanda >= ?::DATE and c.datacomanda <= ?::DATE";
    private String relMes = "select p.nome as nomeproduto,  ic.quantidade as qtdProduto, c.valorpago as CValor, (l.precocompra * ic.quantidade) as custo from ItensComanda ic, Comandas c, Lotes l, Produtos p where ic.FK_idComandas = c.id and c.status = false and l.FK_idProdutos = p.id and ic.FK_idProdutos = p.id and ic.FK_idLotes = l.id and TO_CHAR(c.datacomanda, 'YYYY-MM') = ?;";
    private String relAno = "select p.nome as nomeproduto,  ic.quantidade as qtdProduto, c.valorpago as CValor, (l.precocompra * ic.quantidade) as custo from ItensComanda ic, Comandas c, Lotes l, Produtos p where ic.FK_idComandas = c.id and c.status = false and l.FK_idProdutos = p.id and ic.FK_idProdutos = p.id and ic.FK_idLotes = l.id and TO_CHAR(c.datacomanda, 'YYYY') = ?;";
    
    
    
    
    public Relatorios buscarDia(String data) {
        Connection conexao = null;

        try {
            Relatorios rel = new Relatorios();
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(buscarDia);
            pstmt.setString(1, data);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                rel.setPrecoCompra(rs.getDouble("precoCompra"));
                rel.setPrecoVenda(rs.getDouble("precoVenda"));

            }
            return rel;
        } catch (SQLException sql) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return null;

    }


        
        
    public Relatorios buscarEntreDatas(String data, String data2) {
        Connection conexao = null;

        try {
            Relatorios rel = new Relatorios();
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(buscarEntreDatas);
            pstmt.setString(1, data);
            pstmt.setString(2, data2);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                rel.setPrecoCompra(rs.getDouble("precoCompra"));
                rel.setPrecoVenda(rs.getDouble("precoVenda"));

            }
            return rel;
        } catch (SQLException sql) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return null;

    }
    


    public Relatorios buscarMes(String data) {
        Connection conexao = null;

        try {
            Relatorios rel = new Relatorios();
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(buscarMes);
            pstmt.setString(1, data);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                rel.setPrecoCompra(rs.getDouble("precoCompra"));
                rel.setPrecoVenda(rs.getDouble("precoVenda"));

            }
            return rel;
        } catch (SQLException sql) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return null;

    }

    public Relatorios buscarAno(String data) {
        Connection conexao = null;

        try {
            Relatorios rel = new Relatorios();
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(buscarAno);
            pstmt.setString(1, data);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                rel.setPrecoCompra(rs.getDouble("precoCompra"));
                rel.setPrecoVenda(rs.getDouble("precoVenda"));

            }
            return rel;
        } catch (SQLException sql) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return null;

    }
    
    
            public List<Relatorios> relDia(String data) {
        Connection conexao = null;
        List<Relatorios> relatorios = new ArrayList<Relatorios>();
      
        try {
            
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(relDia);
            pstmt.setString(1, data);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                  Relatorios rel = new Relatorios();
                rel.setNomeProduto(rs.getString("nomeproduto"));
              
                rel.setQuantidade(rs.getInt("qtdProduto"));
               
                rel.setValorPago(rs.getDouble("CValor"));
                
                rel.setCusto(rs.getDouble("custo"));
                
                relatorios.add(rel);

            }

            return relatorios;
            
            
        } catch (SQLException sql) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return null;

    }
            
  public List<Relatorios> relEntreDatas(String data, String data2) {
        Connection conexao = null;
        List<Relatorios> relatorios = new ArrayList<Relatorios>();
      
        try {
            
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(relEntreDatas);
            pstmt.setString(1, data);
            pstmt.setString(2, data2);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Relatorios rel = new Relatorios();
                rel.setNomeProduto(rs.getString("nomeproduto"));
              
                rel.setQuantidade(rs.getInt("qtdProduto"));
               
                rel.setValorPago(rs.getDouble("CValor"));
                
                rel.setCusto(rs.getDouble("custo"));
                
                relatorios.add(rel);

            }

            return relatorios;
            
            
        } catch (SQLException sql) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return null;

    }

              public List<Relatorios> relMes(String data) {
        Connection conexao = null;
        List<Relatorios> relatorios = new ArrayList<Relatorios>();
      
        try {
            
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(relMes);
            pstmt.setString(1, data);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                  Relatorios rel = new Relatorios();
                rel.setNomeProduto(rs.getString("nomeproduto"));
              
                rel.setQuantidade(rs.getInt("qtdProduto"));
               
                rel.setValorPago(rs.getDouble("CValor"));
                
                rel.setCusto(rs.getDouble("custo"));
                
                relatorios.add(rel);

            }

            return relatorios;
            
            
        } catch (SQLException sql) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return null;

    }
              
               public List<Relatorios> relAno(String data) {
        Connection conexao = null;
        List<Relatorios> relatorios = new ArrayList<Relatorios>();
      
        try {
            
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(relAno);
            pstmt.setString(1, data);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                  Relatorios rel = new Relatorios();
                rel.setNomeProduto(rs.getString("nomeproduto"));
              
                rel.setQuantidade(rs.getInt("qtdProduto"));
               
                rel.setValorPago(rs.getDouble("CValor"));
                
                rel.setCusto(rs.getDouble("custo"));
                
                relatorios.add(rel);

            }

            return relatorios;
            
            
        } catch (SQLException sql) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return null;

    }             
 
}
