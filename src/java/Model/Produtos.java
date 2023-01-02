/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Time;

/**
 *
 * @author KawaN
 */
public class Produtos {
    
    //Produtos var
    private int id;
    private String nome;
    private String marca;
    private double precoVenda;
    private int kgLt;
    private String tipoUnidade; //-- Copo, Unidade, Fardo, Drink
    private String tipoNome; //-- Cerveja, Vodka, Cachaça
    private String codBarras;
    private Boolean statusProduto;
    private String dataCadastro;
    //Imagens Var
    private int IdIMG;
    private String caminho;
    private String tipo;
    
    //Get e Set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getKgLt() {
        return kgLt;
    }

    public void setKgLt(int kgLt) {
        this.kgLt = kgLt;
    }

    public String getTipoUnidade() {
        return tipoUnidade;
    }

    public void setTipoUnidade(String tipoUnidade) {
        this.tipoUnidade = tipoUnidade;
    }

    public String getTipoNome() {
        return tipoNome;
    }

    public void setTipoNome(String tipoNome) {
        this.tipoNome = tipoNome;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public Boolean getStatusProduto() {
        return statusProduto;
    }

    public void setStatusProduto(Boolean statusProduto) {
        this.statusProduto = statusProduto;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public int getIdIMG() {
        return IdIMG;
    }

    public void setIdIMG(int IdIMG) {
        this.IdIMG = IdIMG;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    

} 