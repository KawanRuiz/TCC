/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Model.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexoes;

/**
 *
 * @author KawaN
 */
public class UsuariosDAO {
    
    
        public Usuarios buscarLoginSenha(String login, String senha) {
        Connection conexao = null;
        Usuarios usuarios = new Usuarios();
        try {
            conexao = new Conexoes().getConnection();
            PreparedStatement pstmt = conexao.prepareStatement("select * from Usuarios u where u.login = ? and u.senha = ?;");
            pstmt.setString(1, login); //Login
             pstmt.setString(2, senha); //Senha
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

            
                usuarios.setId(rs.getInt("id"));
                usuarios.setNome(rs.getString("nome"));
                usuarios.setLogin(rs.getString("login"));
                usuarios.setSenha(rs.getString("senha"));
                usuarios.setNivel(rs.getInt("nivel"));
              
                

            }
            return usuarios;
        } catch (SQLException sql) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
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
