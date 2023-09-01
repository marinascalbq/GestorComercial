/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
    }
    
    public void salvarProdutosNoCSV(Produto novoProduto) {
    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter("produtos.csv", true)); // Adiciona o parâmetro "true" para modo de adição
        
        writer.write(novoProduto.getIdProduto() + "," + novoProduto.getNome() + ","
                + novoProduto.getPreco() + "," + novoProduto.getQuantidade());
        writer.newLine();
        
        writer.close();
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
            String[] dadosProduto = linha.split(",");
            int idProdutoCSV = Integer.parseInt(dadosProduto[0].trim());

            if (idProdutoCSV == idProduto) {
                dadosProduto[3] = Integer.toString(novaQuantidade);
                linha = String.join(",", dadosProduto);
            }

            linhas.add(linha);
        }

        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter("produtos_temp.csv")); // Novo arquivo temporário
        for (String linhaAtualizada : linhas) {
            writer.write(linhaAtualizada);
            writer.newLine();
        }
        writer.close();

        // Renomear o arquivo temporário para substituir o arquivo original
        File originalFile = new File("produtos.csv");
        File tempFile = new File("produtos_temp.csv");
        tempFile.renameTo(originalFile);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    }