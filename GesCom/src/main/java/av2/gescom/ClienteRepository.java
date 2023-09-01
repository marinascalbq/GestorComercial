/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Marina
 */
public class ClienteRepository {
    private List<Cliente> listaClientes;

    public ClienteRepository() throws ParseException {
        listaClientes = new ArrayList<>();
        carregarClientesDoCSV();
    }

    public List<Cliente> obterTodosClientes() {
        return new ArrayList<>(listaClientes);
    }

    public void cadastrarCliente(Cliente novoCliente) {
        listaClientes.add(novoCliente);
        salvarClientesNoCSV();
    }

    public Cliente encontrarClientePorCPF(String cpf) throws ParseException {
        cpf = cpf.trim().replaceAll("[^0-9]", "");
        Cliente clienteEncontrado = null;

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
                    String dataVendaStr = dadosCliente[4];

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    Date dataVenda = dateFormat.parse(dataVendaStr);

                    clienteEncontrado = new Cliente(nome, cpf, login, senha, dataVenda);
                    break;
                }
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return clienteEncontrado;
    }

    private void carregarClientesDoCSV() throws ParseException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("clientes.csv"));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dadosCliente = linha.split(",");
                String nome = dadosCliente[0];
                String cpf = dadosCliente[1];
                String login = dadosCliente[2];
                String senha = dadosCliente[3];
                String dataVendaStr = dadosCliente[4];

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date dataVenda = dateFormat.parse(dataVendaStr);

                Cliente cliente = new Cliente(nome, cpf, login, senha, dataVenda);
                listaClientes.add(cliente);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void atualizarDadosDoCliente(Cliente cliente) {
        JFrame atualizarClienteFrame = new JFrame("Atualizar Cliente");
        atualizarClienteFrame.setSize(400, 200);
        atualizarClienteFrame.setLayout(new GridLayout(3, 2));

        JLabel cpfLabel = new JLabel("CPF do Cliente:");
        JTextField cpfField = new JTextField(cliente.getCpf());

        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfField.getText();

                Cliente clienteEncontrado;
                try {
                    clienteEncontrado = encontrarClientePorCPF(cpf);

                    if (clienteEncontrado != null) {
                        cliente.setNome(clienteEncontrado.getNome());
                        cliente.setCpf(clienteEncontrado.getCpf());
                        cliente.setLogin(clienteEncontrado.getLogin());
                        cliente.setSenha(clienteEncontrado.getSenha());

                        salvarClientesNoCSV();

                        atualizarClienteFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                    }

                } catch (ParseException ex) {
                    Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        atualizarClienteFrame.setVisible(true);
    }

    public void salvarClientesNoCSV() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.csv"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

            for (Cliente cliente : listaClientes) {
                String dataVendaStr = dateFormat.format(cliente.getUltimaCompra());

                writer.write(cliente.getNome() + "," + cliente.getCpf() + ","
                        + cliente.getLogin() + "," + cliente.getSenha() + "," + dataVendaStr);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}