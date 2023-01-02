/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Testes;

/**
 *
 * @author KawaN
 */
public class Validar {
    
    private boolean validar;

    public boolean isValidar(double numero) {
         if (numero > 0){
             return true;
         }else {
             return false;
         }
        
    }

    public void setValidar(boolean validar) {
        this.validar = validar;
    }
    
}
