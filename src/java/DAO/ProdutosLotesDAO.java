/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Lotes;
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

/**
 *
 * @author KawaN
 */
public class ProdutosLotesDAO {
 
    // SQL dos Produtos
    private String buscarTudo = "select * from Produtos p, Imagens i where p.statusProduto = true  and p.id = i.FK_idProdutos and i.FK_idProdutos = p.id order by nome;";
    private String buscarPorId = "select * from Produtos p, Imagens i where p.id = ? and p.id = i.FK_idProdutos";
    private String inserir = "insert into Produtos(nome,marca,precoVenda,kgLt,tipoUnidade,tipoNome,codBarras,statusProduto) values (?,?,?,?,?,?,?,?);";
    private String atualizar = "update Produtos set nome = ?, marca = ?, precoVenda = ?, kgLt = ?, tipoUnidade = ?, tipoNome = ?, codBarras = ?, statusProduto = ? where id = ?;";
    private String deletar = "update Produtos set statusProduto = false where id = ?";

    public List<Produtos> buscarTudo() {
        Connection conexao = null;
        List<Produtos> produtos = new ArrayList<Produtos>();
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(buscarTudo);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Produtos prod = new Produtos();
                prod.setId(rs.getInt("id"));
                prod.setNome(rs.getString("nome"));
                prod.setMarca(rs.getString("marca"));
                prod.setPrecoVenda(rs.getDouble("precoVenda"));
                prod.setKgLt(rs.getInt("kgLt"));
                prod.setTipoUnidade(rs.getString("tipoUnidade")); //   Copo, Unidade, Fardo, Drink           
                prod.setTipoNome(rs.getString("tipoNome")); // Cerveja, Vodka, Cachaça
                prod.setCodBarras(rs.getString("codBarras"));
                prod.setStatusProduto(rs.getBoolean("statusProduto"));
                prod.setDataCadastro(rs.getString("dataCadastro")); 
                //Imagem
            
                                prod.setCaminho(rs.getString("caminho"));
                prod.setTipo(rs.getString("tipo"));
                
                produtos.add(prod);

            }
            return produtos;
        } catch (SQLException sql) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public Produtos buscarPorId(int id) {
        Connection conexao = null;
        Produtos prod = new Produtos();
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(buscarPorId);
            pstmt.setInt(1, id); //ID para busca
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

            
                prod.setId(rs.getInt("id"));
                prod.setNome(rs.getString("nome"));
                prod.setMarca(rs.getString("marca"));
                prod.setPrecoVenda(rs.getDouble("precoVenda"));
                prod.setKgLt(rs.getInt("kgLt"));
                prod.setTipoUnidade(rs.getString("tipoUnidade")); //   Copo, Unidade, Fardo, Drink           
                prod.setTipoNome(rs.getString("tipoNome")); // Cerveja, Vodka, Cachaça
                prod.setCodBarras(rs.getString("codBarras"));
                prod.setStatusProduto(rs.getBoolean("statusProduto"));
                prod.setDataCadastro(rs.getString("dataCadastro"));               
                                //Imagem
                prod.setCaminho(rs.getString("caminho"));

            }
            return prod;
        } catch (SQLException sql) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public int inserir(Produtos prod) {
        Connection conexao = null;
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(inserir, Statement.RETURN_GENERATED_KEYS);
            //Nome,Marca,PrecoVenda,KgLt,TipoUnidade,TipoNome,CodBarras,StatusProduto
            pstmt.setString(1, prod.getNome());
            pstmt.setString(2, prod.getMarca());
            pstmt.setDouble(3, prod.getPrecoVenda());
            pstmt.setDouble(4, prod.getKgLt());
            pstmt.setString(5, prod.getTipoUnidade());
            pstmt.setString(6, prod.getTipoNome());
            pstmt.setString(7, prod.getCodBarras());
            pstmt.setBoolean(8, prod.getStatusProduto());
            
           

            pstmt.execute();
            
           ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        return id;
                       
                    }
            //return true;

        } catch (SQLException sql) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        return 9999;
    }

    public boolean atualizar(Produtos prod) {
        Connection conexao = null;

        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(atualizar);

            pstmt.setString(1, prod.getNome());
            pstmt.setString(2, prod.getMarca());
            pstmt.setDouble(3, prod.getPrecoVenda());
            pstmt.setDouble(4, prod.getKgLt());
            pstmt.setString(5, prod.getTipoUnidade());
            pstmt.setString(6, prod.getTipoNome());
            pstmt.setString(7, prod.getCodBarras());
            pstmt.setBoolean(8, prod.getStatusProduto());
            pstmt.setInt(9, prod.getId()); //ID vem da VIEW
            pstmt.execute();
            return true;
        } catch (SQLException sql) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean deletar(Produtos prod) {
        Connection conexao = null;

        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(deletar);
            pstmt.setInt(1, prod.getId());
            pstmt.execute();
            return true;
        } catch (SQLException sql) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    // SQL dos Lotes
    
        private String buscarLotes = "select *, u.id as idUser, u.nome as nomeUser, l.id as idLote, p.id as IdProd, p.nome as nomeProd from Imagens i, lotes l, Produtos p, Usuarios u where l.FK_idProdutos = p.id and u.id = l.FK_idUsuarios and quantidade > 0 and p.statusProduto = true and p.id = i.FK_idProdutos order by p.nome;";
        private String buscarLoteID =  "select *, u.id as idUser, u.nome as nomeUser, l.id as idLote, p.id as IdProd, p.nome as nomeProd from Imagens i, lotes l, Produtos p, Usuarios u where l.FK_idProdutos = p.id and u.id = l.FK_idUsuarios and l.id = ? and i.FK_idProdutos = p.id;";
        private String inserirLote = "insert into Lotes(numeroLote,validade,quantidade,precoCompra,FK_idProdutos,FK_idUsuarios) values (?,?,?,?,?,?);";
        private String atualizarLote = "update Lotes set numeroLote = ?, validade = ?, quantidade = ?, precoCompra = ?, FK_idProdutos = ?, FK_idUsuarios = ? where id = ?;";
        private String deletarLote = "update Lotes set quantidade = 0 where id = ?";
    
    public List<Lotes> buscarLotes() {
        Connection conexao = null;
        List<Lotes> lotes = new ArrayList<Lotes>();
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(buscarLotes);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Lotes lote = new Lotes();
                lote.setId(rs.getInt("IdProd"));
                lote.setNome(rs.getString("nome"));
                lote.setMarca(rs.getString("marca"));
                lote.setPrecoVenda(rs.getDouble("precoVenda"));
                lote.setKgLt(rs.getInt("kgLt"));
                lote.setTipoUnidade(rs.getString("tipoUnidade")); //   Copo, Unidade, Fardo, Drink           
                lote.setTipoNome(rs.getString("tipoNome")); // Cerveja, Vodka, Cachaça
                lote.setCodBarras(rs.getString("codBarras"));
                lote.setStatusProduto(rs.getBoolean("statusProduto"));
                lote.setDataCadastro(rs.getString("dataCadastro")); 
            
                //lote
                lote.setIdLote(rs.getInt("idLote"));
                lote.setNumeroLote(rs.getString("numeroLote"));
                lote.setValidade(rs.getDate("validade")); 
                lote.setQuantidade(rs.getInt("quantidade"));
                lote.setPrecoCompra(rs.getDouble("precoCompra"));
                lote.setDataEntrada(rs.getDate("dataEntrada"));
                
               Usuarios usuario = new Usuarios();
               usuario.setId(rs.getInt("idUser"));
                usuario.setNome(rs.getString("nomeUser"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setNivel(rs.getInt("FK_idUsuarios"));
                lote.setUsuarios(usuario);
                
                
                                //Imagem
            
                lote.setCaminho(rs.getString("caminho"));
                lote.setTipo(rs.getString("tipo"));
                
                lotes.add(lote);

            }
            return lotes;
        } catch (SQLException sql) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        public Lotes LotesPorID(int id) {
        Connection conexao = null;
        Lotes lote = new Lotes();
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(buscarLoteID);
            pstmt.setInt(1, id); //ID para busca
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                            
                lote.setId(rs.getInt("IdProd"));
                lote.setNome(rs.getString("nome"));
                lote.setMarca(rs.getString("marca"));
                lote.setPrecoVenda(rs.getDouble("precoVenda"));
                lote.setKgLt(rs.getInt("kgLt"));
                lote.setTipoUnidade(rs.getString("tipoUnidade")); //   Copo, Unidade, Fardo, Drink           
                lote.setTipoNome(rs.getString("tipoNome")); // Cerveja, Vodka, Cachaça
                lote.setCodBarras(rs.getString("codBarras"));
                lote.setStatusProduto(rs.getBoolean("statusProduto"));
                lote.setDataCadastro(rs.getString("dataCadastro")); 
                //lote
                lote.setIdLote(rs.getInt("idLote"));
                lote.setNumeroLote(rs.getString("numeroLote"));
                            
                lote.setValidade(rs.getDate("validade")); 
                lote.setQuantidade(rs.getInt("quantidade"));
                lote.setPrecoCompra(rs.getDouble("precoCompra"));
                lote.setDataEntrada(rs.getDate("dataEntrada"));
                
               Usuarios usuario = new Usuarios();
               usuario.setId(rs.getInt("idUser"));
                usuario.setNome(rs.getString("nomeUser"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setNivel(rs.getInt("FK_idUsuarios"));
                lote.setUsuarios(usuario);         
                
                                                //Imagem
            
                lote.setCaminho(rs.getString("caminho"));
                lote.setTipo(rs.getString("tipo"));
                

            }
            return lote;
        } catch (SQLException sql) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
 
             public boolean inserirLote(Lotes lote) {
        Connection conexao = null;
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(inserirLote, Statement.RETURN_GENERATED_KEYS);
            //Nome,Marca,PrecoVenda,KgLt,TipoUnidade,TipoNome,CodBarras,StatusProduto
            pstmt.setString(1, lote.getNumeroLote());
            pstmt.setDate(2, lote.getValidade());
            pstmt.setInt(3, lote.getQuantidade());
            pstmt.setDouble(4, lote.getPrecoCompra());
            pstmt.setInt(5, lote.getId());
            System.out.print(lote.getUsuarios().getId());
            pstmt.setInt(6, lote.getUsuarios().getId());    

           

            pstmt.execute();
            return true;

        } catch (SQLException sql) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
             
             
     public boolean atualizarLote(Lotes lote) {
        Connection conexao = null;

        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(atualizarLote);

            pstmt.setString(1, lote.getNumeroLote());
            pstmt.setDate(2, lote.getValidade());
            pstmt.setInt(3, lote.getQuantidade());
            pstmt.setDouble(4, lote.getPrecoCompra());
            pstmt.setInt(5, lote.getId());
            System.out.print(lote.getUsuarios().getId());
            pstmt.setInt(6, lote.getUsuarios().getId());    
            pstmt.setInt(7, lote.getIdLote()); //ID vem da VIEW
            pstmt.execute();
            
            return true;
        } catch (SQLException sql) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean deletarLotes(Lotes lotes) {
        Connection conexao = null;

        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement(deletarLote);
            pstmt.setInt(1, lotes.getIdLote());
            pstmt.execute();
            return true;
        } catch (SQLException sql) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
        public List<Lotes> buscarLotesPorProduto(int idProduto) {
        Connection conexao = null;
        List<Lotes> lotes = new ArrayList<Lotes>();
        try {
            conexao = new Conexoes().getConnection();
             PreparedStatement pstmt = conexao.prepareStatement("select *, u.id as idUser, u.nome as nomeUser, l.id as idLote, p.id as IdProd, p.nome as nomeProd from Imagens i, lotes l, Produtos p, Usuarios u where l.FK_idProdutos = p.id and u.id = l.FK_idUsuarios and quantidade > 0 and p.statusProduto = true and l.FK_idProdutos = ? and i.FK_idProdutos = p.id order by l.id ASC;");
            
            pstmt.setInt(1, idProduto); //ID para busca
           
           
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Lotes lote = new Lotes();
                lote.setId(rs.getInt("IdProd"));
                lote.setNome(rs.getString("nome"));
                lote.setMarca(rs.getString("marca"));
                lote.setPrecoVenda(rs.getDouble("precoVenda"));
                lote.setKgLt(rs.getInt("kgLt"));
                lote.setTipoUnidade(rs.getString("tipoUnidade")); //   Copo, Unidade, Fardo, Drink           
                lote.setTipoNome(rs.getString("tipoNome")); // Cerveja, Vodka, Cachaça
                lote.setCodBarras(rs.getString("codBarras"));
                lote.setStatusProduto(rs.getBoolean("statusProduto"));
                lote.setDataCadastro(rs.getString("dataCadastro")); 
            
                //lote
                lote.setIdLote(rs.getInt("idLote"));
                lote.setNumeroLote(rs.getString("numeroLote"));
                lote.setValidade(rs.getDate("validade")); 
                lote.setQuantidade(rs.getInt("quantidade"));
                lote.setPrecoCompra(rs.getDouble("precoCompra"));
                lote.setDataEntrada(rs.getDate("dataEntrada"));
                
               Usuarios usuario = new Usuarios();
               usuario.setId(rs.getInt("idUser"));
                usuario.setNome(rs.getString("nomeUser"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setNivel(rs.getInt("FK_idUsuarios"));
                lote.setUsuarios(usuario);
                
                                                //Imagem
            
                lote.setCaminho(rs.getString("caminho"));
                lote.setTipo(rs.getString("tipo"));
                lotes.add(lote);

            }
            return lotes;
        } catch (SQLException sql) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        
             public boolean atualizarQuantidadeLote(Lotes lote) {
        Connection conexao = null;

        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement("update Lotes set quantidade = ? where id = ?;");


            pstmt.setInt(1, lote.getQuantidade());
           
            pstmt.setInt(2, lote.getIdLote()); //ID vem da VIEW
            pstmt.execute();
            
            return true;
        } catch (SQLException sql) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        
             
public boolean inserirIMG(Lotes lote) {
        Connection conexao = null;
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement("insert into Imagens(caminho,tipo,FK_idProdutos) values (?,?,?);", Statement.RETURN_GENERATED_KEYS);
            //Nome,Marca,PrecoVenda,KgLt,TipoUnidade,TipoNome,CodBarras,StatusProduto
            pstmt.setString(1, "img/"+lote.getCaminho());//Nome da Imagem
            pstmt.setString(2, lote.getTipo()); //Tipo da Imagem JPG PNG
            pstmt.setInt(3, lote.getId());


           

            pstmt.execute();
            return true;

        } catch (SQLException sql) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public Lotes buscarIMG(int id) {
        Connection conexao = null;
        Lotes lote = new Lotes();
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement("select * from Imagens i, Produtos p where i.FK_idProdutos = p.id and p.id = ?");
            pstmt.setInt(1, id); //ID para busca
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

            
                lote.setId(rs.getInt("id"));
                lote.setCaminho(rs.getString("caminho"));
                lote.setTipo(rs.getString("tipo"));
                lote.setId(rs.getInt("FK_idProdutos"));


            }
            return lote;
        } catch (SQLException sql) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean atualizarIMG(Lotes lote) {
        Connection conexao = null;
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement("update Imagens set caminho = ?, tipo = ? where FK_idProdutos = ?");
            //Nome,Marca,PrecoVenda,KgLt,TipoUnidade,TipoNome,CodBarras,StatusProduto
            pstmt.setString(1, "img/"+lote.getCaminho());//Nome da Imagem
            pstmt.setString(2, lote.getTipo()); //Tipo da Imagem JPG PNG
            pstmt.setInt(3, lote.getId());


           

            pstmt.execute();
            return true;

        } catch (SQLException sql) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosLotesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
}
