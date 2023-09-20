/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gescom.TelasProdutos;

/**
 *
 * @author allan
 */


import av2.gescom.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenuEstoque extends JFrame {
    
    private java.util.List<Produto> listaProdutos;
    private ProdutoRepository produtoRepository; // Repositório de produtos
    private Estoque estoque; // Estoque de produtos

    public TelaMenuEstoque(ProdutoRepository produtoRepository, Estoque estoque) {
        TelaMostrarEstoque telaMostrarEstoque = new TelaMostrarEstoque(estoque);
        TelaRegistrarProdutos telaRegistrarProdutos = new TelaRegistrarProdutos(produtoRepository,listaProdutos);
        
        this.produtoRepository = produtoRepository;
        this.estoque = estoque;

        // Configuração da tela de produtos
        setTitle("Menu de Estoque");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.PINK);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 30, 20);

        // Crie um JLabel com texto "Menu de Produtos" em negrito
        JLabel TituloLabel = new JLabel("Menu de Estoque");

        // Crie os botões
        JButton mostrarEstoqueButton = new JButton("         Estoque          ");
        JButton registrarProdutoButton = new JButton(" Registrar Produtos ");
        

        Font fonteMaior = TituloLabel.getFont().deriveFont(Font.BOLD, 40);
        TituloLabel.setFont(fonteMaior);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(TituloLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(mostrarEstoqueButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(registrarProdutoButton, constraints);

       

        // Adicione ações aos botões
        mostrarEstoqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaMostrarEstoque.mostrarTela();
            }
        });

        registrarProdutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaRegistrarProdutos.mostrarTela();
            }
        });

        
    }
    public TelaMenuEstoque(VendaRepository vendaRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository, Estoque estoque) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public void mostrarTela() {
        setVisible(true);
    }
}


