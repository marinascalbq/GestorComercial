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
        BufferedWriter writer = new BufferedWriter(new FileWriter("produtos.csv", true)); 
        
        writer.write(novoProduto.getIdProduto() + "," + novoProduto.getNome() + ","
                + novoProduto.getPreco() + "," + novoProduto.getQuantidade());
        writer.newLine();
        
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public boolean idProdutoExiste(int idProduto) {
    try {
        BufferedReader reader = new BufferedReader(new FileReader("produtos.csv"));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] dadosProduto = linha.split(",");
            int idProdutoCSV = Integer.parseInt(dadosProduto[0].trim());

            if (idProdutoCSV == idProduto) {
                reader.close();
                return true;
            }
        }
        
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }

    return false; 
}


    public Produto encontrarProdutoPorId(int idProduto) {
        for (Produto produto : listaProdutos) {
            if (produto.getIdProduto() == idProduto) {
                return produto;
            }
        }
        return null; 
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
    
    public void atualizarQuantidadeNoCSV(int idProduto, int quantidadeASomar) {
    try {
        File arquivoCSV = new File("produtos.csv");
        File arquivoTemp = new File("produtos_temp.csv");

        BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV));
        BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemp));

        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dadosProduto = linha.split(",");
            int idProdutoCSV = Integer.parseInt(dadosProduto[0].trim());

            if (idProdutoCSV == idProduto) {
                int quantidadeAtual = Integer.parseInt(dadosProduto[3].trim());
                int novaQuantidade = quantidadeAtual + quantidadeASomar;
                dadosProduto[3] = Integer.toString(novaQuantidade);
            }

            writer.write(String.join(",", dadosProduto));
            writer.newLine();
        }

        reader.close();
        writer.close();

        arquivoCSV.delete();
        arquivoTemp.renameTo(arquivoCSV);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}