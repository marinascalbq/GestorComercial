/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Marina
 */
public class Estoque {
    private int estoqueMinimo;
    private Map<Integer, ProdutoEstoque> produtosEmEstoque;

    public Estoque(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
        this.produtosEmEstoque = new HashMap<>();
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        if (produtosEmEstoque.containsKey(produto.getIdProduto())) {
            ProdutoEstoque produtoEstoque = produtosEmEstoque.get(produto.getIdProduto());
            produtoEstoque.aumentarQuantidade(quantidade);
        } else {
            ProdutoEstoque produtoEstoque = new ProdutoEstoque(produto, quantidade);
            produtosEmEstoque.put(produto.getIdProduto(), produtoEstoque);
        }
    }

    public void retirarDoEstoque(int idProduto, int quantidade) {
        if (produtosEmEstoque.containsKey(idProduto)) {
            ProdutoEstoque produtoEstoque = produtosEmEstoque.get(idProduto);
            produtoEstoque.diminuirQuantidade(quantidade);
        } else {
            // Lidar com o caso em que o produto não está no estoque
        }
    }

    public boolean verificarQuantidadeBaixa(int idProduto) {
        if (produtosEmEstoque.containsKey(idProduto)) {
            ProdutoEstoque produtoEstoque = produtosEmEstoque.get(idProduto);
            return produtoEstoque.getQuantidade() < estoqueMinimo;
        }
        return false;
    }

}
