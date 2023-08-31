/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom.Telas;

import av2.gescom.Produto;
import av2.gescom.ProdutoRepository;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Marina
 */
public class TelaRegistrarProdutos {
    private ProdutoRepository produtoRepository;
    private List<Produto> listaProdutos;

    public TelaRegistrarProdutos(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
        this.listaProdutos = new ArrayList<>(); // Inicializa a lista no construtor
    }

    public void mostrarTela() {
        JFrame registrarProdutosFrame = new JFrame("Registrar Novos Produtos");
        registrarProdutosFrame.setSize(400, 300);

            JLabel labelCodigo = new JLabel("Código do Produto:");
            JTextField campoCodigo = new JTextField();

            JLabel labelNomeProduto = new JLabel("Nome do Produto:");
            JTextField campoNomeProduto = new JTextField();

            JLabel labelPreco = new JLabel("Preço:");
            JTextField campoPreco = new JTextField();

            JLabel labelQuantidade = new JLabel("Quantidade:");
            JTextField campoQuantidade = new JTextField();

            JButton cadastrarProdutoButton = new JButton("Cadastrar Produto");
            cadastrarProdutoButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int idProduto = Integer.parseInt(campoCodigo.getText());
                    String nomeProduto = campoNomeProduto.getText();
                    double preco = Double.parseDouble(campoPreco.getText());
                    int quantidade = Integer.parseInt(campoQuantidade.getText());

                    // Criar um novo produto
                    Produto novoProduto = new Produto(idProduto, nomeProduto, preco, quantidade);

                    // Adicionar o novo produto à lista de produtos
                    listaProdutos.add(novoProduto);

                    // Salvar os produtos no arquivo CSV
                    salvarProdutosNoCSV();

                    // Fechar a janela de registro de produtos
                    registrarProdutosFrame.dispose();
                }
            });

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 2));
            panel.add(labelCodigo);
            panel.add(campoCodigo);
            panel.add(labelNomeProduto);
            panel.add(campoNomeProduto);
            panel.add(labelPreco);
            panel.add(campoPreco);
            panel.add(labelQuantidade);
            panel.add(campoQuantidade);
            panel.add(cadastrarProdutoButton);

            registrarProdutosFrame.add(panel);
            registrarProdutosFrame.setVisible(true);
    }

 private void salvarProdutosNoCSV() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("produtos.csv"));
            for (Produto produto : listaProdutos) {
                writer.write(produto.getIdProduto() + "," + produto.getNome() + ","
                        + produto.getPreco() + "," + produto.getQuantidade());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}