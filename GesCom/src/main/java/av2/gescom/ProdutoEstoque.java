/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

/**
 *
 * @author Marina
 */
class ProdutoEstoque {
    private Produto produto;
    private int quantidade;

    public ProdutoEstoque(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void aumentarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    public void diminuirQuantidade(int quantidade) {
        this.quantidade -= quantidade;
    }
}
