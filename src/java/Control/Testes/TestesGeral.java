/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Testes;


import DAO.ComandasDAO;
import DAO.ProdutosLotesDAO;
import DAO.UsuariosDAO;
import Model.Comandas;
import Model.Lotes;
import Model.Produtos;
import Model.Usuarios;
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
public class TestesGeral {
    
   public static void main(String[] args) throws ParseException {
      
        double Multiplicador;
        double Valor;
        double Resultado =0;
        
       Multiplicador = 2;
       Valor = 10;
       
       Validar validar = new Validar();
       
       if ( validar.isValidar(Multiplicador)){
             
           for(int i=0; Multiplicador>i; i++){
              Resultado = Resultado + Valor; 
           }
           System.out.println("Resultado: " + Resultado);   
       }else{
            System.out.println("Valor igual ou menor que 0: " + Multiplicador);     
       }
       
                


}}
