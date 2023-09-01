/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

/**
 *
 * @author Marina
 */


import av2.gescom.Telas.TelaAtualizarClientes;
import av2.gescom.Telas.TelaCadastroCliente;
import av2.gescom.Telas.TelaEfetuarCompra;
import av2.gescom.Telas.TelaMostrarComprasEfetuadas;
import av2.gescom.Telas.TelaMostrarEstoque;
import av2.gescom.Telas.TelaRegistrarProdutos;
import av2.gescom.Telas.TelaVisualizarClientes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuGUI extends JFrame {

    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;
    private VendaRepository vendaRepository;
    private Estoque estoque;
    
    private java.util.List<Produto> listaProdutos;

    private static MenuGUI instance;

    public static MenuGUI getInstance(ClienteRepository clienteRepository, ProdutoRepository produtoRepository,
                                      VendaRepository vendaRepository, Estoque estoque) {
        if (instance == null) {
            instance = new MenuGUI(clienteRepository, produtoRepository, vendaRepository, estoque);
        }
        return instance;
    }

    private MenuGUI(ClienteRepository clienteRepository, ProdutoRepository produtoRepository,
                    VendaRepository vendaRepository, Estoque estoque) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.vendaRepository = vendaRepository;
        this.estoque = estoque;

        createMenu();
    }

    private void createMenu() {
        
    TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente(clienteRepository);
    TelaEfetuarCompra telaEfetuarCompra = new TelaEfetuarCompra(clienteRepository, produtoRepository, vendaRepository, estoque);
    TelaMostrarComprasEfetuadas telaMostrarComprasEfetuadas = new TelaMostrarComprasEfetuadas(vendaRepository);
    TelaMostrarEstoque telaMostrarEstoque = new TelaMostrarEstoque(estoque);
    TelaRegistrarProdutos telaRegistrarProdutos = new TelaRegistrarProdutos(produtoRepository,listaProdutos);
    TelaVisualizarClientes telaVisualizarClientes = new TelaVisualizarClientes(clienteRepository);
    TelaAtualizarClientes telaAtualizarClientes = new TelaAtualizarClientes(clienteRepository);

        
        
        setTitle("Menu Principal");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JButton cadastrarClienteButton = new JButton("Cadastrar Cliente");
        JButton atualizarClienteButton = new JButton("Atualizar Cliente");
        JButton visualizarClientesButton = new JButton("Visualizar Clientes");
        JButton efetuarCompraButton = new JButton("Efetuar Compra");
        JButton mostrarComprasButton = new JButton("Mostrar Compras Efetuadas");
        JButton registrarProdutoButton = new JButton("Registrar Novos Produtos");
        JButton mostrarEstoqueButton = new JButton("Estoque");

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(cadastrarClienteButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(atualizarClienteButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(visualizarClientesButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(efetuarCompraButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(mostrarComprasButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        add(registrarProdutoButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        add(mostrarEstoqueButton, constraints);
       

        cadastrarClienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaCadastroCliente.mostrarTela();
            }
        });

        efetuarCompraButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaEfetuarCompra.mostrarTela();
            }
        });

        mostrarComprasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaMostrarComprasEfetuadas.mostrarTela();
            }
        });

        mostrarEstoqueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaMostrarEstoque.mostrarTela();
            }
        });

        registrarProdutoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaRegistrarProdutos.mostrarTela();
            }
        });

        visualizarClientesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaVisualizarClientes.mostrarTela();
            }
        });
        
        atualizarClienteButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Solicita ao usuário o CPF do cliente a ser atualizado
            String cpfCliente = JOptionPane.showInputDialog(null, "Digite o CPF do cliente a ser atualizado:");

            // Obtém o cliente pelo CPF do clienteRepository
            Cliente clienteParaAtualizar = null;
            try {
                clienteParaAtualizar = clienteRepository.encontrarClientePorCPF(cpfCliente);
            } catch (ParseException ex) {
                Logger.getLogger(MenuGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (clienteParaAtualizar != null) {
                // Cria uma instância do TelaAtualizarClientes e mostra a tela
                TelaAtualizarClientes telaAtualizarClientes = new TelaAtualizarClientes(clienteRepository);
                telaAtualizarClientes.mostrarTela(clienteParaAtualizar);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            }
        }
    });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}