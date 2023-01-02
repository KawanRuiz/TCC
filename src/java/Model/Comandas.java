/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;


/**
 *
 * @author KawaN
 */
public class Comandas {
    //Comandas Var
    private int idComanda;
    private String nomeComanda;
    private int numero; //Joga o 0 no banco Comandas 'numero' todas as vezes que iniciar o dia.
    private double valorPago;
    private boolean status;
    private Date dataComanda; 
    private Usuarios usuarios;
    //Variaveis para ItensComanda
    private int idItens;
    private int quantidade;
    private Date dataVenda;
    private Lotes lotes;
    
    
    //Sistema
    
    private Date incio_dia;
    private int numeroComanda;
    private boolean status_dia;

    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public String getNomeComanda() {
        return nomeComanda;
    }

    public void setNomeComanda(String nomeComanda) {
        this.nomeComanda = nomeComanda;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDataComanda() {
        return dataComanda;
    }

    public void setDataComanda(Date dataComanda) {
        this.dataComanda = dataComanda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }


    public int getIdItens() {
        return idItens;
    }

    public void setIdItens(int idItens) {
        this.idItens = idItens;
    }

    public Date getIncio_dia() {
        return incio_dia;
    }

    public void setIncio_dia(Date incio_dia) {
        this.incio_dia = incio_dia;
    }

    public int getNumeroComanda() {
        return numeroComanda;
    }

    public void setNumeroComanda(int numeroComanda) {
        this.numeroComanda = numeroComanda;
    }

    public boolean isStatus_dia() {
        return status_dia;
    }

    public void setStatus_dia(boolean status_dia) {
        this.status_dia = status_dia;
    }   

    public Lotes getLotes() {
        return lotes;
    }

    public void setLotes(Lotes lotes) {
        this.lotes = lotes;
    }

    
}