/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gescom.TelasCompras;

/**
 *
 * @author allan
 */


import av2.gescom.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenuCompra extends JFrame {

    private VendaRepository vendaRepository; // Repositório de compras
    private ClienteRepository clienteRepository; // Repositório de clientes
    private ProdutoRepository produtoRepository; // Repositório de produtos
    private Estoque estoque; // Estoque de produtos

    public TelaMenuCompra(VendaRepository vendaRepository, ClienteRepository clienteRepository,
                          ProdutoRepository produtoRepository, Estoque estoque) {
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.estoque = estoque;

        // Configuração da tela de compras
        setTitle("Menu de Compras");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.PINK);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 30, 20);

        // Crie um JLabel com texto "Menu de Compras" em negrito
        JLabel TituloLabel = new JLabel("Menu de Compras");

        // Crie os botões
        JButton efetuarCompraButton = new JButton("           Efetuar Compra           ");
        JButton mostrarComprasEfetuadasButton = new JButton("Mostrar Compras Efetuadas");
      

        Font fonteMaior = TituloLabel.getFont().deriveFont(Font.BOLD, 40);
        TituloLabel.setFont(fonteMaior);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(TituloLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(efetuarCompraButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(mostrarComprasEfetuadasButton, constraints);


        // Adicione ações aos botões
        efetuarCompraButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abra a tela de efetuar compra quando o botão "Efetuar Compra" for clicado
                TelaEfetuarCompra telaEfetuarCompra = new TelaEfetuarCompra(clienteRepository, produtoRepository, vendaRepository, estoque);
                telaEfetuarCompra.mostrarTela();
            }
        });

        mostrarComprasEfetuadasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implemente a lógica para mostrar as compras efetuadas aqui
                // Por exemplo, crie e mostre a tela de mostrar compras efetuadas
                TelaMostrarComprasEfetuadas telaMostrarComprasEfetuadas = new TelaMostrarComprasEfetuadas(vendaRepository);
                telaMostrarComprasEfetuadas.mostrarTela();
            }
        });

    }
    public void mostrarTela() {
        setVisible(true);
    }
}
