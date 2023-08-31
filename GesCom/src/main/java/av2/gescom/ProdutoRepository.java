/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marina
 */
    public class ProdutoRepository {

    private List<Produto> listaProdutos;

    public ProdutoRepository() {
        listaProdutos = new ArrayList<>();
        carregarProdutosDoCSV();
    }

    public void carregarProdutosDoCSV() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("produtos.csv"));
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dadosProduto = linha.split(",");
                int idProduto = Integer.parseInt(dadosProduto[0].trim());
                String nome = dadosProduto[1];
                double preco = Double.parseDouble(dadosProduto[2]);
                int quantidade = Integer.parseInt(dadosProduto[3]);

                Produto produto = new Produto(idProduto, nome, preco, quantidade);
                listaProdutos.add(produto);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Produto encontrarProdutoPorId(int idProduto) {
        for (Produto produto : listaProdutos) {
            if (produto.getIdProduto() == idProduto) {
                return produto;
            }
        }
        return null; // Retorna null se não encontrar o produto com o ID especificado
    }

    public List<Produto> obterProdutosPorId(int... ids) {
        List<Produto> produtos = new ArrayList<>();

        for (int id : ids) {
            Produto produto = encontrarProdutoPorId(id);
            if (produto != null) {
                produtos.add(produto);
            }
        }

        return produtos;
    }
    
public void atualizarQuantidadeNoCSV(int idProduto, int novaQuantidade) {
    try {
        BufferedReader reader = new BufferedReader(new FileReader("produtos.csv"));
        List<String> linhas = new ArrayList<>();
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] dadosProduto = linha.split(";");
            int idProdutoCSV = Integer.parseInt(dadosProduto[0].trim());

            if (idProdutoCSV == idProduto) {
                dadosProduto[3] = Integer.toString(novaQuantidade);
                linha = String.join(";", dadosProduto);
            }

            linhas.add(linha);
        }

        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter("produtos.csv"));
        for (String linhaAtualizada : linhas) {
            writer.write(linhaAtualizada);
            writer.newLine();
        }
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}