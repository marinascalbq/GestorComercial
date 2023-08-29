/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

import java.util.List;

/**
 *
 * @author Marina
 */
public class Produto {
    private int idProduto;
    private String nome;
    private double preco;
    private int quantidade;
    private List<Produto> listaProdutos;

    public Produto(int idProduto, String nome, double preco, int quantidade) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    Produto(String idProduto, String nomeProduto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void retirarDoEstoque() {

    }
    
    public void retirarDoEstoque(int quantidade) {
    if (this.quantidade >= quantidade) {
        this.quantidade -= quantidade;
    }
    }
}