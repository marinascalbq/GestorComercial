/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Marina
 */
public class Estoque {
    private List<Produto> produtos;
    private int estoqueMinimo;


    public Estoque(int estoqueMinimo) {
        this.produtos = new ArrayList<>();
        this.estoqueMinimo = estoqueMinimo;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }

    public boolean estoqueAbaixoMinimo() {
        for (Produto produto : produtos) {
            if (produto.getQuantidade() < estoqueMinimo) {
                return true;
            }
        }
        return false;
    }
    
    public void retirarDoEstoque(int idProduto, int quantidade) {
        for (Produto produto : produtos) {
            if (produto.getIdProduto() == idProduto) {
                produto.retirarDoEstoque(quantidade);
                break;
            }
        }
}

    public int getQuantidade(Produto produto) {
        return produto.getQuantidade();
}
}