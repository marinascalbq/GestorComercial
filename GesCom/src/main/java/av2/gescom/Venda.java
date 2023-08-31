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
public class Venda {
    private Cliente cliente;
    private List<Produto> produtos;
    private String dataVenda;
    private int quantidade;

    public Venda(Cliente cliente, List<Produto> produtosVenda,int quantidade, String dataVenda) {
        this.cliente = cliente;
        this.produtos = produtosVenda;
        this.dataVenda = dataVenda;
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setProdutos(List<Produto> produtosVenda) {
        this.produtos = produtosVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double calcularValorTotal() {
        double valorTotal = 0;
        for (Produto produto : produtos) {
            valorTotal += produto.getPreco();
        }
        return valorTotal;
    }
    
        public List<Produto> getProdutos() {
        return produtos;
    }
        
        public Cliente getCliente() {
        return cliente;
    }
        
            public String getDataVenda() {
        return dataVenda;
    }
            
}