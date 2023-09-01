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
    private Cliente clienteParaAtualizar;

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

    public void mostrarTela(Cliente cliente) {
        this.clienteParaAtualizar = cliente;

        JFrame telaAtualizacaoCliente = new JFrame("Atualizar Dados do Cliente");
        telaAtualizacaoCliente.setSize(400, 300);
        telaAtualizacaoCliente.setLayout(new GridLayout(6, 2));

        JTextField campoNome = new JTextField(cliente.getNome());
        JTextField campoCpf = new JTextField(cliente.getCpf());
        JTextField campoLogin = new JTextField(cliente.getLogin());
        JPasswordField campoSenha = new JPasswordField(cliente.getSenha());

        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String novoNome = campoNome.getText();
                String novoCpf = campoCpf.getText();
                String novoLogin = campoLogin.getText();
                String novaSenha = new String(campoSenha.getPassword());

                clienteParaAtualizar.setNome(novoNome);
                clienteParaAtualizar.setCpf(novoCpf);
                clienteParaAtualizar.setLogin(novoLogin);
                clienteParaAtualizar.setSenha(novaSenha);

                clienteRepository.atualizarDadosDoCliente(clienteParaAtualizar);
                clienteRepository.salvarClientesNoCSV();

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
