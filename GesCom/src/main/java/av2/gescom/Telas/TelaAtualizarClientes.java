/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom.Telas;

/**
 *
 * @author Marina
 */
import av2.gescom.Cliente;
import av2.gescom.ClienteRepository;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaAtualizarClientes {
    private ClienteRepository clienteRepository;
    public Cliente clienteParaAtualizar;

    public ClienteRepository getClienteRepository() {
        return clienteRepository;
    }

    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void setClienteParaAtualizar(Cliente clienteParaAtualizar) {
        this.clienteParaAtualizar = clienteParaAtualizar;
    }

    public TelaAtualizarClientes(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void mostrarTela(Cliente clienteEncontrado) {
        this.clienteParaAtualizar = clienteEncontrado;

        JFrame telaAtualizacaoCliente = new JFrame("Atualizar Dados do Cliente");
        telaAtualizacaoCliente.setSize(400, 300);
        telaAtualizacaoCliente.setLayout(new GridLayout(6, 2));

        JTextField campoNome = new JTextField(clienteEncontrado.getNome());
        JTextField campoCpf = new JTextField(clienteEncontrado.getCpf());
        JTextField campoLogin = new JTextField(clienteEncontrado.getLogin());
        JPasswordField campoSenha = new JPasswordField(clienteEncontrado.getSenha());

        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                clienteRepository.atualizarDadosDoCliente(clienteParaAtualizar, campoNome.getText(), campoCpf.getText(),
                        campoLogin.getText(), new String(campoSenha.getPassword()));
                
             

                telaAtualizacaoCliente.dispose();
            }
        });

        telaAtualizacaoCliente.add(new JLabel("Nome:"));
        telaAtualizacaoCliente.add(campoNome);
        telaAtualizacaoCliente.add(new JLabel("CPF:"));
        telaAtualizacaoCliente.add(campoCpf);
        telaAtualizacaoCliente.add(new JLabel("Login:"));
        telaAtualizacaoCliente.add(campoLogin);
        telaAtualizacaoCliente.add(new JLabel("Senha:"));
        telaAtualizacaoCliente.add(campoSenha);
        telaAtualizacaoCliente.add(atualizarButton);

        telaAtualizacaoCliente.setVisible(true);
    }
}