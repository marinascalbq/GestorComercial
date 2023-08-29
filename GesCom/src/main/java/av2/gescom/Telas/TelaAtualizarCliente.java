/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom.Telas;

import av2.gescom.Cliente;
import av2.gescom.ClienteRepository;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Marina
 */
public class TelaAtualizarCliente {
    private ClienteRepository clienteRepository;

    public TelaAtualizarCliente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void mostrarTela() {
        JFrame atualizarClienteFrame = new JFrame("Atualizar Cliente");
        atualizarClienteFrame.setSize(400, 200);
        atualizarClienteFrame.setLayout(new GridLayout(3, 2));

        JLabel cpfLabel = new JLabel("CPF do Cliente:");
        JTextField cpfField = new JTextField();

        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfField.getText();

                // Encontra o cliente no repositório pelo CPF
                Cliente cliente = clienteRepository.encontrarClientePorCPF(cpf);

                if (cliente != null) {
                    // Abre a mesma tela de cadastro com os dados do cliente
                    criarTelaCadastroCliente(cliente.getNome(), cliente.getCpf(), cliente.getLogin(), cliente.getSenha(), cliente.getUltimaCompra());

                    // Fecha a janela após atualizar
                    atualizarClienteFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                }
            }
        });

        atualizarClienteFrame.add(cpfLabel);
        atualizarClienteFrame.add(cpfField);
        atualizarClienteFrame.add(atualizarButton);

        atualizarClienteFrame.setVisible(true);
    }

    private void criarTelaCadastroCliente(String nome, String cpf, String login, String senha, String ultimaCompra) {
        TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente(clienteRepository);
        telaCadastroCliente.mostrarTela();
}
}
