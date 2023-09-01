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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaCadastroCliente {
    private ClienteRepository clienteRepository;

    public TelaCadastroCliente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void mostrarTela() {
        JFrame telaCadastro = new JFrame("Cadastrar Cliente");
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

        JLabel labelUltimaCompra = new JLabel("Última Compra (dd.MM.yyyy):");
        JTextField campoUltimaCompra = new JTextField();

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();
                String cpf = campoCpf.getText();
                String login = campoLogin.getText();
                String senha = new String(campoSenha.getPassword());
                String dataVendaStr = campoUltimaCompra.getText();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date dataVenda = null;

                try {
                    dataVenda = dateFormat.parse(dataVendaStr);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaCadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Formato de data inválido. Use o formato dd.MM.yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Criar um novo cliente
                Cliente novoCliente = new Cliente(nome, cpf, login, senha, dataVenda);

                // Adicionar o cliente ao repositório
                clienteRepository.cadastrarCliente(novoCliente);

                // Fechar a tela de cadastro
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
        telaCadastro.add(labelUltimaCompra);
        telaCadastro.add(campoUltimaCompra);
        telaCadastro.add(botaoCadastrar);

        telaCadastro.setVisible(true);
    }
}
