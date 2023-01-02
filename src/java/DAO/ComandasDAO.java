/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Comandas;
import Model.Lotes;
import Model.Produtos;
import Model.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexoes;

/**
 *
 * @author KawaN
 */
public class ComandasDAO {

    private String buscarTudo = "select * from Comandas where status = true and datacomanda = (select inicio_dia from Sistema) order by id";
    private String buscarTudoDevedores = "select * from Comandas where status = true and datacomanda < (select inicio_dia from Sistema) order by id";
    private String buscarTudoFechadas = "select * from Comandas where status = false order by id";    
    private String buscarPorId = "select * from Comandas where comandas.id = ?";
    private String inserir = "insert into Comandas(nome,numero,status,dataComanda,FK_idUsuarios) values (?,?,?,?,?);";
    private String atualizar = "update Comandas set nome = ?, numero = ?, status = ?, dataComanda = ?, FK_idUsuarios = ? where id = ?;";
    private String atualizarValorPago = "update Comandas set valorPago = ? where id = ?;";
    private String deletar = "update Comandas set status = false where id = ?";

    public List<Comandas> buscarTudo() {
        Connection conexao = null;
        List<Comandas> comandas = new ArrayList<Comandas>();
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(buscarTudo);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Comandas c = new Comandas();

                c.setIdComanda(rs.getInt("id"));
                c.setNomeComanda(rs.getString("nome"));
                c.setNumero(rs.getInt("numero"));
                c.setStatus(rs.getBoolean("status"));
                c.setDataComanda(rs.getDate("dataComanda"));
                Usuarios usuarios = new Usuarios();
                usuarios.setId(rs.getInt("FK_idUsuarios"));
                c.setUsuarios(usuarios);// 

                comandas.add(c);

            }
            return comandas;
        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
        public List<Comandas> buscarTudoDevedores() {
        Connection conexao = null;
        List<Comandas> comandas = new ArrayList<Comandas>();
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(buscarTudoDevedores);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Comandas c = new Comandas();

                c.setIdComanda(rs.getInt("id"));
                c.setNomeComanda(rs.getString("nome"));
                c.setNumero(rs.getInt("numero"));
                c.setStatus(rs.getBoolean("status"));
                c.setDataComanda(rs.getDate("dataComanda"));
                Usuarios usuarios = new Usuarios();
                usuarios.setId(rs.getInt("FK_idUsuarios"));
                c.setUsuarios(usuarios);// 

                comandas.add(c);

            }
            return comandas;
        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
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

                public List<Comandas> buscarTudoFechadas() {
        Connection conexao = null;
        List<Comandas> comandas = new ArrayList<Comandas>();
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(buscarTudoFechadas);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Comandas c = new Comandas();

                c.setIdComanda(rs.getInt("id"));
                c.setNomeComanda(rs.getString("nome"));
                c.setNumero(rs.getInt("numero"));
                c.setStatus(rs.getBoolean("status"));
                c.setDataComanda(rs.getDate("dataComanda"));
                Usuarios usuarios = new Usuarios();
                usuarios.setId(rs.getInt("FK_idUsuarios"));
                c.setUsuarios(usuarios);// 

                comandas.add(c);

            }
            return comandas;
        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public Comandas buscarPorId(int id) {
        Connection conexao = null;
        Comandas c = new Comandas();
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(buscarPorId);
            pstmt.setInt(1, id); //ID para busca
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                c.setIdComanda(rs.getInt("id"));
                c.setNomeComanda(rs.getString("nome"));
                c.setNumero(rs.getInt("numero"));
                c.setStatus(rs.getBoolean("status"));
                c.setDataComanda(rs.getDate("dataComanda"));
                Usuarios usuarios = new Usuarios();
                usuarios.setId(rs.getInt("FK_idUsuarios"));
                c.setUsuarios(usuarios);//               

            }
            return c;
        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean inserir(Comandas c) {
        Connection conexao = null;
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(inserir, Statement.RETURN_GENERATED_KEYS);
            //Nome,Marca,PrecoVenda,KgLt,TipoUnidade,TipoNome,CodBarras,StatusProduto
            pstmt.setString(1, c.getNomeComanda());
            pstmt.setInt(2, c.getNumero());
            pstmt.setBoolean(3, c.isStatus());
            pstmt.setDate(4, c.getDataComanda());
            pstmt.setInt(5, c.getUsuarios().getId());

            pstmt.execute();
            return true;

        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return false;
    }

    public boolean atualizar(Comandas c) {
        Connection conexao = null;

        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(atualizar);

            pstmt.setString(1, c.getNomeComanda());
            pstmt.setInt(2, c.getNumero());
            pstmt.setBoolean(3, c.isStatus());
            pstmt.setDate(4, c.getDataComanda());
            pstmt.setInt(5, c.getUsuarios().getId());
            pstmt.setInt(6, c.getIdComanda());
            pstmt.execute();
            return true;
        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return false;

    }
    
        public boolean atualizarValorPago(Comandas c) {
        Connection conexao = null;

        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(atualizarValorPago);

            pstmt.setDouble(1,c.getValorPago());
            pstmt.setInt(2, c.getIdComanda());  
            pstmt.execute();
            return true;
        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return false;

    }

    public boolean deletar(Comandas c) {
        Connection conexao = null;

        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(deletar);
            pstmt.setInt(1, c.getIdComanda());
            pstmt.execute();
            return true;
        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return false;

    }

    //ITENS DA COMANDA
    private String buscarItens = "select c.id as idC, c.nome as nomeComanda, l.id as idL, p.id as idP, p.nome as nomeProduto, ic.id as idItens, * from ItensComanda ic, Comandas c, Lotes l, Produtos p where ic.FK_idComandas = c.id and c.status = true and l.FK_idProdutos = p.id and ic.FK_idProdutos = p.id and ic.FK_idLotes = l.id order by ic.id;";
    private String buscarItensID = "select c.id as idC, c.nome as nomeComanda, l.id as idL, p.id as idP, p.nome as nomeProduto, ic.id as idItens, * from Imagens i, ItensComanda ic, Comandas c, Lotes l, Produtos p where ic.FK_idComandas = c.id and l.FK_idProdutos = p.id and ic.FK_idProdutos = p.id and ic.FK_idLotes = l.id and fk_idcomandas = ? and  i.FK_idProdutos = p.id order by idp ";
    private String inserirItens = "insert into ItensComanda(quantidade,dataVenda,FK_idComandas,FK_idProdutos,FK_idLotes) values (?,?,?,?,?);"; 
    private String atualizarItens = "update ItensComanda set quantidade = ?, dataVenda = ?, FK_idComandas = ?, FK_idProdutos = ?, FK_idLotes = ? where id = ?;";
    private String deletarItens = "Delete from ItensComanda where id = ?";

    public List<Comandas> buscarItens() {
        Connection conexao = null;
        List<Comandas> comandas = new ArrayList<Comandas>();
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(buscarItens);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Comandas c = new Comandas();

                c.setIdItens(rs.getInt("idC"));
                c.setQuantidade(rs.getInt("quantidade"));
                c.setValorPago(rs.getDouble("valorPago"));                
                c.setDataVenda(rs.getDate("dataVenda"));
                c.setIdComanda(rs.getInt("FK_idComandas"));
                Lotes lotes = new Lotes();
                lotes.setId(rs.getInt("FK_idProdutos"));
                lotes.setNome(rs.getString("nomeProduto"));
                lotes.setKgLt(rs.getInt("kglt"));
                c.setLotes(lotes);//

                comandas.add(c);

            }
            return comandas;
        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public Comandas buscarItensID(int id) {
        Connection conexao = null;
        Comandas c = new Comandas();
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(buscarItensID);
            pstmt.setInt(1, id); //ID para busca
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                c.setIdItens(rs.getInt("id"));
                c.setQuantidade(rs.getInt("quantidade"));
                c.setValorPago(rs.getDouble("valorPago"));                
                c.setDataVenda(rs.getDate("dataVenda"));
                c.setIdComanda(rs.getInt("FK_idComandas"));
                Lotes lotes = new Lotes();
                lotes.setId(rs.getInt("FK_idProdutos"));
                lotes.setIdLote(rs.getInt("FK_idLotes"));
                c.setLotes(lotes);//          

            }
            return c;
        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean inserirItens(Comandas c) {
        Connection conexao = null;
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(inserirItens, Statement.RETURN_GENERATED_KEYS);
            //Nome,Marca,PrecoVenda,KgLt,TipoUnidade,TipoNome,CodBarras,StatusProduto
            pstmt.setInt(1, c.getQuantidade());            
            pstmt.setDate(2, c.getDataVenda());
            pstmt.setInt(3, c.getIdComanda());
            pstmt.setInt(4, c.getLotes().getId());    //PRODUTO      
            pstmt.setInt(5, c.getLotes().getIdLote()); //LOTE
            

            pstmt.execute();
            return true;

        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return false;
    }

    public boolean atualizarItens(Comandas c) {
        Connection conexao = null;

        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(atualizarItens);

            pstmt.setInt(1, c.getQuantidade());            
            pstmt.setDate(2, c.getDataVenda());
            pstmt.setInt(3, c.getIdComanda());
            pstmt.setInt(4, c.getLotes().getId());
            pstmt.setInt(5, c.getLotes().getIdLote());
            pstmt.setInt(6, c.getIdItens());
          
            pstmt.execute();
            return true;
        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return false;

    }
    
        public boolean atualizarQTD(Comandas c) {
        Connection conexao = null;

        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement("update ItensComanda set quantidade = ? where id = ?;");

            pstmt.setInt(1, c.getQuantidade());
            pstmt.setInt(2, c.getIdItens());
          
            pstmt.execute();
            return true;
        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return false;

    }

    public boolean deletarItens(int id) {
        Connection conexao = null;

        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(deletarItens);
            pstmt.setInt(1, id);
            pstmt.execute();
            return true;
        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return false;

    }

    public Comandas Sistema(String op) {
        Connection conexao = null;
        Comandas c = new Comandas();
        try {
            conexao = new Conexoes().getConnection();

            PreparedStatement pstmt = conexao.prepareStatement("select * from Sistema where id = 1;");

            if (op == "Abrir") {
                pstmt = conexao.prepareStatement("update Sistema set inicio_dia = (SELECT CURRENT_DATE), numeroComanda = 0, status_dia = true where id = 1;");  
                
            } else if (op == "Fechar") {
                pstmt = conexao.prepareStatement("update Sistema set status_dia = false where id = 1;");
                
            } else if (op == "numeroComanda") {
                pstmt = conexao.prepareStatement("update Sistema set numeroComanda = (numeroComanda + 1) where id = 1;");
                
            } else if (op == "Buscar") {
                pstmt = conexao.prepareStatement("select * from Sistema where id = 1;");
                ResultSet rs = pstmt.executeQuery();
                 while (rs.next()) {
                c.setIncio_dia(rs.getDate("inicio_dia"));   
                c.setNumeroComanda(rs.getInt("numeroComanda"));
                c.setStatus_dia(rs.getBoolean("status_dia"));
                return c;
                 }
            } else {
                System.out.println("Opção Invalida: " + op);
            }
            
            pstmt.execute();
            
        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return c;

    }
    
    public List<Comandas> buscarItensComanda(int id) {
        Connection conexao = null;
        List<Comandas> comandas = new ArrayList<Comandas>();
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(buscarItensID);
            pstmt.setInt(1, id); //ID para busca
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Comandas c = new Comandas();
             
                c.setIdComanda(rs.getInt("idC"));
                c.setIdItens(rs.getInt("idItens"));                
               c.setNomeComanda(rs.getString("nomeComanda"));
                c.setQuantidade(rs.getInt("quantidade"));
                c.setValorPago(rs.getDouble("valorPago"));                
                c.setDataVenda(rs.getDate("dataVenda"));
                c.setIdComanda(rs.getInt("FK_idComandas"));
                Lotes lotes = new Lotes();
                
                //produtos
                lotes.setId(rs.getInt("FK_idProdutos"));
                lotes.setNome(rs.getString("nomeProduto"));
                lotes.setKgLt(rs.getInt("kglt"));
                lotes.setTipoUnidade(rs.getString("tipoUnidade"));
                lotes.setPrecoVenda(rs.getDouble("precoVenda"));
                
                //lotes
                lotes.setNumeroLote(rs.getString("numeroLote"));
                lotes.setValidade(rs.getDate("validade"));
                lotes.setQuantidade(rs.getInt("quantidade"));            
                              
                lotes.setPrecoCompra(rs.getDouble("precoCompra"));
                 lotes.setDataEntrada(rs.getDate("dataEntrada"));
                 
               //Imagem
            
                lotes.setCaminho(rs.getString("caminho"));
                lotes.setTipo(rs.getString("tipo"));

                c.setLotes(lotes);//

                
                comandas.add(c);

            }
            return comandas;
        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
        public List<Comandas> buscarComandaProduto(int idComanda, int idProduto) {
        Connection conexao = null;
        List<Comandas> comandas = new ArrayList<Comandas>();
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement("select c.id as idC, c.nome as nomeComanda, l.id as idL, p.id as idP, p.nome as nomeProduto, ic.id as idItens, l.quantidade as LoteQTD, * from ItensComanda ic, Comandas c, Lotes l, Produtos p where ic.FK_idComandas = c.id and c.status = true and l.FK_idProdutos = p.id and ic.FK_idProdutos = p.id and ic.FK_idLotes = l.id and ic.FK_idComandas = ? and ic.FK_idProdutos = ?;");
            pstmt.setInt(1, idComanda); //ID da Comanda
             pstmt.setInt(2, idProduto); // UD do Produto
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Comandas c = new Comandas();
             
                c.setIdComanda(rs.getInt("idC"));
                c.setIdItens(rs.getInt("idItens"));                
               c.setNomeComanda(rs.getString("nomeComanda"));
                c.setQuantidade(rs.getInt("quantidade"));
                c.setValorPago(rs.getDouble("valorPago"));                
                c.setDataVenda(rs.getDate("dataVenda"));
                c.setIdComanda(rs.getInt("FK_idComandas"));
                Lotes lotes = new Lotes();
                
                //produtos
                lotes.setId(rs.getInt("FK_idProdutos"));
                lotes.setNome(rs.getString("nomeProduto"));
                lotes.setKgLt(rs.getInt("kglt"));
                lotes.setTipoUnidade(rs.getString("tipoUnidade"));
                lotes.setPrecoVenda(rs.getDouble("precoVenda"));
                
                //lotes
                lotes.setIdLote(rs.getInt("idL"));
                lotes.setNumeroLote(rs.getString("numeroLote"));
                lotes.setValidade(rs.getDate("validade"));
                lotes.setQuantidade(rs.getInt("LoteQTD"));            
                              
                lotes.setPrecoCompra(rs.getDouble("precoCompra"));
                 lotes.setDataEntrada(rs.getDate("dataEntrada"));

                c.setLotes(lotes);//

                
                comandas.add(c);

            }
            return comandas;
        } catch (SQLException sql) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComandasDAO.class.getName()).log(Level.SEVERE, null, ex);
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
