/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

/**
 *
 * @author Marina
 */
import av2.gescom.Telas.TelaAtualizarCliente;
import av2.gescom.Telas.TelaCadastroCliente;
import av2.gescom.Telas.TelaVisualizarClientes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI extends JFrame {

    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;
    private VendaRepository vendaRepository;
    private Estoque estoque;

    private static MenuGUI instance;

    // Construtor que injeta as instâncias dos repositórios e serviços
    private MenuGUI(ClienteRepository clienteRepository, ProdutoRepository produtoRepository,
                   VendaRepository vendaRepository, Estoque estoque) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.vendaRepository = vendaRepository;
        this.estoque = estoque;

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
        // Chamar a tela de Cadastro de clientes  
        public void actionPerformed(ActionEvent e) {
            TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente(clienteRepository);
            telaCadastroCliente.mostrarTela();
            }
        });

        atualizarClienteButton.addActionListener(new ActionListener() {
            // Chamar a tela de atualizar de clientes  
            public void actionPerformed(ActionEvent e) {
                TelaAtualizarCliente telaAtualizarCliente = new TelaAtualizarCliente(clienteRepository);
                telaAtualizarCliente.mostrarTela();
                }
            });
    
     
        visualizarClientesButton.addActionListener(new ActionListener() {
            // Chamar a tela de visualização de clientes  
            public void actionPerformed(ActionEvent e) {
                TelaVisualizarClientes telaVisualizarClientes = new TelaVisualizarClientes(clienteRepository);
                telaVisualizarClientes.mostrarTela();
            }
            });
       

        efetuarCompraButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Chamar a tela de efetuar compra
                TelaVisualizarClientes telaCadastroCliente = new TelaVisualizarClientes(clienteRepository);
                //TelaVisualizarClientes.mostrarTela()
            }
        });

        mostrarComprasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaVisualizarClientes telaCadastroCliente = new TelaVisualizarClientes(clienteRepository);
                //TelaVisualizarClientes.mostrarTela()
            }
        });

        registrarProdutoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Chamar a tela de registrar novos produtos
                TelaVisualizarClientes telaCadastroCliente = new TelaVisualizarClientes(clienteRepository);
                //TelaVisualizarClientes.mostrarTela()
            }
        });

        mostrarEstoqueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Chamar a funcionalidade de mostrar estoque
                TelaVisualizarClientes telaCadastroCliente = new TelaVisualizarClientes(clienteRepository);
                //TelaVisualizarClientes.mostrarTela()
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static MenuGUI getInstance(ClienteRepository clienteRepository, ProdutoRepository produtoRepository,
                                      VendaRepository vendaRepository, Estoque estoque) {
        if (instance == null) {
            instance = new MenuGUI(clienteRepository, produtoRepository, vendaRepository, estoque);
        }
        return instance;
    }
}
