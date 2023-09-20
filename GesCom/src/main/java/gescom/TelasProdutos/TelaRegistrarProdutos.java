/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gescom.TelasProdutos;

/**
 *
 * @author allan, marina
 */
import av2.gescom.Produto;
import av2.gescom.ProdutoRepository;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TelaRegistrarProdutos {
    private ProdutoRepository produtoRepository;
    private List<Produto> listaProdutos;

    public TelaRegistrarProdutos(ProdutoRepository produtoRepository, List<Produto> listaProdutos) {
        this.produtoRepository = produtoRepository;
        this.listaProdutos = listaProdutos;
    }

    TelaRegistrarProdutos(ProdutoRepository produtoRepository) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            int quantidade = Integer.parseInt(campoQuantidade.getText());

            if (produtoRepository.idProdutoExiste(idProduto)) {

                produtoRepository.atualizarQuantidadeNoCSV(idProduto, quantidade);
            } else {
                String nomeProduto = campoNomeProduto.getText();
                double preco = Double.parseDouble(campoPreco.getText());

                Produto novoProduto = new Produto(idProduto, nomeProduto, preco, quantidade);
                produtoRepository.salvarProdutosNoCSV(novoProduto);
            }

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
}