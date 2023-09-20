/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

/**
 *
 * @author Marina
 */



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import gescom.TelasClientes.TelaMenuCliente;
import gescom.TelasCompras.TelaMenuCompra;
import gescom.TelasProdutos.TelaMenuEstoque;


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

    public MenuGUI(ClienteRepository clienteRepository, ProdutoRepository produtoRepository,
                    VendaRepository vendaRepository, Estoque estoque) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.vendaRepository = vendaRepository;
        this.estoque = estoque;

        createMenu();
    }

    private void createMenu() {
        TelaMenuCliente telaCliente = new TelaMenuCliente(clienteRepository);
        TelaMenuCompra telaMenuCompra = new TelaMenuCompra(vendaRepository,clienteRepository,produtoRepository,estoque);
        TelaMenuEstoque telaMenuEstoque = new TelaMenuEstoque(produtoRepository,estoque);
        setTitle("Menu Principal");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.PINK); 
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        
        JLabel TituloLabel = new JLabel("GesCom");
        JLabel SubTituloLabel = new JLabel("Uma boa gestão para seu negócio");
        JButton ClienteButton = new JButton("    CLIENTES    ");
        JButton ComprasButton = new JButton("    COMPRAS    ");
        JButton EstoqueButton = new JButton("    ESTOQUE    ");
        
        Font fonteMaior = TituloLabel.getFont().deriveFont(Font.BOLD, 60);
        TituloLabel.setFont(fonteMaior);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(TituloLabel, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(SubTituloLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(ClienteButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(ComprasButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(EstoqueButton, constraints);

        ClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostra a telaCliente quando o botão é clicado
                telaCliente.mostrarTela();
            }
        });
        ComprasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostra a telaCliente quando o botão é clicado
                telaMenuCompra.mostrarTela();
            }
        });
        EstoqueButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
         // Mostra a telaEstoque quando o botão é clicado
            telaMenuEstoque.mostrarTela();
    }
});


        // Resto do seu código...

        setLocationRelativeTo(null);
        setVisible(true);
    }
}