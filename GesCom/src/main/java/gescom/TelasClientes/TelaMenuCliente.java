/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gescom.TelasClientes;

/**
 *
 * @author Allan, Marina
 */

import av2.gescom.Cliente;
import av2.gescom.MenuGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import av2.gescom.ClienteRepository;

public class TelaMenuCliente extends JFrame {

    private ClienteRepository clienteRepository; // Repositório de clientes

    public TelaMenuCliente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;

        // Configuração da tela do cliente
        setTitle("Menu do Cliente");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.PINK);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 30, 20);

        // JLabel com texto "Menu do Cliente" 
        JLabel TituloLabel = new JLabel("Menu Cliente");

        // Crie os botões
        JButton visualizarClientesButton = new JButton(" Visualizar Cliente ");
        JButton cadastrarClienteButton = new JButton("Cadastrar Cliente");
        JButton atualizarClienteButton = new JButton(" Atualizar Cliente ");

        Font fonteMaior = TituloLabel.getFont().deriveFont(Font.BOLD, 50);
        TituloLabel.setFont(fonteMaior);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(TituloLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(visualizarClientesButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(cadastrarClienteButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(atualizarClienteButton, constraints);

        // ações dos botões
        visualizarClientesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crie e mostre a tela de visualização de clientes
                TelaVisualizarClientes telaVisualizarClientes = new TelaVisualizarClientes(clienteRepository);
                telaVisualizarClientes.mostrarTela();
            }
        });

        cadastrarClienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crie e mostre a tela de cadastro de clientes
                TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente(clienteRepository);
                telaCadastroCliente.mostrarTela();
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
                    // Cinstância de TelaAtualizarClientes e mostra a tela
                    TelaAtualizarClientes telaAtualizarClientes = new TelaAtualizarClientes(clienteRepository);
                    telaAtualizarClientes.mostrarTela(clienteParaAtualizar);
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                }
            }
        });
    }

    public void mostrarTela() {
        setVisible(true);
    }
}
