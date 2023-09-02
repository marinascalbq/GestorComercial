/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

import av2.gescom.Telas.TelaCadastroCliente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteRepository {
    private List<Cliente> listaClientes;
    private TelaCadastroCliente telaCadastroCliente;
        

    public ClienteRepository() throws ParseException {
        listaClientes = obterTodosClientes();
    }

    public List<Cliente> obterTodosClientes() {
        List<Cliente> clientes = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("clientes.csv"));
            String linha;

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

            while ((linha = reader.readLine()) != null) {
                String[] dadosCliente = linha.split(",");
                String nome = dadosCliente[0];
                String cpf = dadosCliente[1];
                String login = dadosCliente[2];
                String senha = dadosCliente[3];
                String dataVendaStr = dadosCliente[4];

                Date dataVenda = dateFormat.parse(dataVendaStr);

                Cliente cliente = new Cliente(nome, cpf, login, senha, dataVenda);
                clientes.add(cliente);
            }

            reader.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public boolean cpfExiste(String cpf) {
        cpf = cpf.trim().replaceAll("[^0-9]", "");

        for (Cliente cliente : listaClientes) {
            if (cliente.getCpf().equals(cpf)) {
                return true; 
            }
        }

        return false;
    }

    public boolean loginExiste(String login) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getLogin().equalsIgnoreCase(login)) {
                return true; 
            }
        }
        return false; 
    }

    public boolean cpfValido(String cpf) {
        return cpf.matches("\\d{11}");
    }

    public void cadastrarCliente(Cliente novoCliente) {
        String cpf = novoCliente.getCpf();
        String login = novoCliente.getLogin();

        if (cpfExiste(cpf)) {
            JOptionPane.showMessageDialog(null, "CPF já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            telaCadastroCliente.mostrarTela();
        } else if (loginExiste(login)) {
            JOptionPane.showMessageDialog(null, "Login já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            telaCadastroCliente.mostrarTela();
        } else {
            if (!cpfValido(cpf)) {
                JOptionPane.showMessageDialog(null, "Formato de CPF inválido. Escreva sem caracteres especiais.", "Erro", JOptionPane.ERROR_MESSAGE);
                telaCadastroCliente.mostrarTela();
            } else {
                listaClientes.add(novoCliente);
                salvarClientesNoCSV(listaClientes);
            }
        }
    }

    public void salvarClientesNoCSV(List<Cliente> clientes) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.csv"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

            for (Cliente cliente : clientes) {
                String dataVendaStr = dateFormat.format(cliente.getUltimaCompra());

                String linha = cliente.getNome() + "," + cliente.getCpf() + ","
                        + cliente.getLogin() + "," + cliente.getSenha() + "," + dataVendaStr;

                writer.write(linha);
                writer.newLine();
            }
            
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void atualizarClientesNoCSV(List<Cliente> clienteParaAtualizar) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.csv"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

            for (Cliente cliente : clienteParaAtualizar) {
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

    public void atualizarDadosDoCliente(Cliente clienteParaAtualizar, String campoNome, String campoCpf, String campoLogin, String campoSenha) {
        clienteParaAtualizar.setNome(campoNome);
        clienteParaAtualizar.setCpf(campoCpf);
        clienteParaAtualizar.setLogin(campoLogin);
        clienteParaAtualizar.setSenha(campoSenha);

        List<Cliente> todosClientes = obterTodosClientes();

        int indiceAtualizado = -1;
        for (int i = 0; i < todosClientes.size(); i++) {
            if (todosClientes.get(i).getCpf().equals(clienteParaAtualizar.getCpf())) {
                indiceAtualizado = i;
                break;
            }
        }

        if (indiceAtualizado != -1) {
            todosClientes.set(indiceAtualizado, clienteParaAtualizar);

            File arquivoCSV = new File("clientes.csv");
            arquivoCSV.delete();

            atualizarClientesNoCSV(todosClientes);
        } else {
            JOptionPane.showMessageDialog(null, "Não é possível alterar o CPF.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
