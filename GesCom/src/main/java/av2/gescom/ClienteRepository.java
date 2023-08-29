/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Marina
 */
    public class ClienteRepository {
        private List<Cliente> listaClientes; 
        
        public List<Cliente> obterTodosClientes() {
        return new ArrayList<>(listaClientes);
    }

    private void criarTelaCadastroCliente(String nome, String cpf, String login, String senha, String ultimaCompra) {
        JFrame telaCadastro = new JFrame("Cadastrar Cliente");
        telaCadastro.setSize(400, 300);
        telaCadastro.setLayout(new GridLayout(6, 2));

        // Campos de entrada para os atributos do cliente
        JLabel labelNome = new JLabel("Nome:");
        JTextField campoNome = new JTextField();
        campoNome.setText(nome);

        JLabel labelCpf = new JLabel("CPF:");
        JTextField campoCpf = new JTextField();
        campoCpf.setText(cpf);

        JLabel labelLogin = new JLabel("Login:");
        JTextField campoLogin = new JTextField();
        campoLogin.setText(login);

        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setText(senha);

        JLabel labelUltimaCompra = new JLabel("Última Compra:");
        JTextField campoUltimaCompra = new JTextField();
        campoUltimaCompra.setText(ultimaCompra);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Captura os dados dos campos de entrada
                String novoNome = campoNome.getText();
                String novoCpf = campoCpf.getText();
                String novoLogin = campoLogin.getText();
                String novaSenha = new String(campoSenha.getPassword());
                String novaUltimaCompra = campoUltimaCompra.getText();

                // Cria um novo cliente
                Cliente novoCliente = new Cliente(novoNome, novoCpf, novoLogin, novaSenha, novaUltimaCompra);

                // Adiciona o cliente à lista de clientes
                listaClientes.add(novoCliente);

                // Chama o método para salvar a lista de clientes no CSV
                salvarClientesNoCSV();

                // Fecha a tela de cadastro
                telaCadastro.dispose();
            }
        });

        // Adiciona os componentes à tela
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

        // Exibe a tela de cadastro
        telaCadastro.setVisible(true);
    }

    public void cadastrarCliente(Cliente novoCliente) {
    criarTelaCadastroCliente("", "", "", "", ""); // Abre a tela de cadastro com campos vazios
    
    }

    private void salvarClientesNoCSV() {
    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.csv", true)); // O parâmetro "true" indica que o arquivo deve ser aberto no modo de adição
        Cliente novoCliente = listaClientes.get(listaClientes.size() - 1); // Pega o último cliente adicionado
        
        writer.write(novoCliente.getNome() + "," + novoCliente.getCpf() + "," + novoCliente.getLogin()
                + "," + novoCliente.getSenha() + "," + novoCliente.getUltimaCompra());
        writer.newLine();
        
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public Cliente encontrarClientePorCPF(String cpf) {
    cpf = cpf.trim().replaceAll("[^0-9]", "");
    Cliente clienteEncontrado = null; // Inicializa como null

    try {
        BufferedReader reader = new BufferedReader(new FileReader("clientes.csv"));
        String linha;
        
        while ((linha = reader.readLine()) != null) {
            String[] dadosCliente = linha.split(",");
            String cpfCliente = dadosCliente[1].trim().replaceAll("[^0-9]", "");

            if (cpfCliente.equals(cpf)) {
                String nome = dadosCliente[0];
                String login = dadosCliente[2];
                String senha = dadosCliente[3];
                String ultimaCompra = dadosCliente[4];
                
                clienteEncontrado = new Cliente(nome, cpf, login, senha, ultimaCompra);
                break; // Encontrou o cliente, não precisa continuar
            }
        }

        reader.close(); // Fecha o arquivo após a busca

    } catch (IOException e) {
        e.printStackTrace();
    }

    return clienteEncontrado;
}

    private void atualizarCliente() {
    JFrame atualizarClienteFrame = new JFrame("Atualizar Cliente");
    atualizarClienteFrame.setSize(400, 200);
    atualizarClienteFrame.setLayout(new GridLayout(3, 2));

    JLabel cpfLabel = new JLabel("CPF do Cliente:");
    JTextField cpfField = new JTextField();

    JButton atualizarButton = new JButton("Atualizar");
    atualizarButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String cpf = cpfField.getText();

            // Encontra o cliente na lista pelo CPF
            Cliente cliente = encontrarClientePorCPF(cpf);

            if (cliente != null) {
                // Salva a lista de clientes atualizada no arquivo CSV
                salvarClientesNoCSV();

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
    
    private void visualizarCliente() {
        // Cria uma nova tela para visualizar os clientes
        JFrame telaVisualizacao = new JFrame("Clientes Cadastrados");
        telaVisualizacao.setSize(600, 400);
        telaVisualizacao.setLayout(new BorderLayout());

        // Cria um JTextArea para exibir os clientes
        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);

        // Lê os dados dos clientes do arquivo CSV e preenche o JTextArea
        try {
            BufferedReader reader = new BufferedReader(new FileReader("clientes.csv"));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dadosCliente = linha.split(",");
                String nome = dadosCliente[0];
                String cpf = dadosCliente[1];
                String login = dadosCliente[2];
                String senha = dadosCliente[3];
                String ultimaCompra = dadosCliente[4];

                areaTexto.append("Nome: " + nome + "\n");
                areaTexto.append("CPF: " + cpf + "\n");
                areaTexto.append("Login: " + login + "\n");
                areaTexto.append("Última Compra: " + ultimaCompra + "\n");
                areaTexto.append("-------------------------\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Adiciona o JTextArea ao painel
        telaVisualizacao.add(scrollPane, BorderLayout.CENTER);

        // Exibe a tela de visualização
        telaVisualizacao.setVisible(true);
    }
}

