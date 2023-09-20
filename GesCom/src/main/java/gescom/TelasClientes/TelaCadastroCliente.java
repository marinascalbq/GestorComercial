/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gescom.TelasClientes;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TelaCadastroCliente {
    private ClienteRepository clienteRepository;
    private JFrame telaCadastro;

    public TelaCadastroCliente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        this.telaCadastro = new JFrame("Cadastrar Cliente");
    }

    public void mostrarTela() {
        telaCadastro = new JFrame("Cadastrar Cliente");
        
        telaCadastro.setSize(400, 300);
        telaCadastro.setLayout(new GridLayout(6, 2));

        JLabel labelNome = new JLabel("Nome:");
        JTextField campoNome = new JTextField();

        JLabel labelCpf = new JLabel("CPF:");
        JTextField campoCpf = new JTextField();

        JLabel labelLogin = new JLabel("Login:");
        JTextField campoLogin = new JTextField();

        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField campoSenha = new JPasswordField();

        JLabel labelultimaCompra = new JLabel("Data do cadastro (dd.MM.yyyy):");
        JTextField campoultimaCompra = new JTextField();

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();
                String cpf = campoCpf.getText();
                String login = campoLogin.getText();
                String senha = new String(campoSenha.getPassword());
                String dataVendaStr = campoultimaCompra.getText();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date dataVenda = null;

                try {
                    dataVenda = dateFormat.parse(dataVendaStr);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    exibirErro("Formato de data inválido. Use o formato dd.MM.yyyy.");
                    return;
                }

                Cliente novoCliente = new Cliente(nome, cpf, login, senha, dataVenda);

                clienteRepository.cadastrarCliente(novoCliente);

                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                telaCadastro.dispose();
            }
        });

        telaCadastro.add(labelNome);
        telaCadastro.add(campoNome);
        telaCadastro.add(labelCpf);
        telaCadastro.add(campoCpf);
        telaCadastro.add(labelLogin);
        telaCadastro.add(campoLogin);
        telaCadastro.add(labelSenha);
        telaCadastro.add(campoSenha);
        telaCadastro.add(labelultimaCompra);
        telaCadastro.add(campoultimaCompra);
        telaCadastro.add(botaoCadastrar);
        
        telaCadastro.setVisible(true);
    }


    private void exibirErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
        mostrarTela(); 
    }
}


