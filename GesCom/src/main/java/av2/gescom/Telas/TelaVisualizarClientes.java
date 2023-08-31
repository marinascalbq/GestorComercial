/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom.Telas;

import av2.gescom.Cliente;
import av2.gescom.ClienteRepository;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class TelaVisualizarClientes {
    private ClienteRepository clienteRepository;

    public TelaVisualizarClientes(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void mostrarTela() {
        JFrame telaVisualizacao = new JFrame("Clientes Cadastrados");
        telaVisualizacao.setSize(600, 400);
        telaVisualizacao.setLayout(new BorderLayout());

        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);

        // Obtém a lista de clientes do repositório
        List<Cliente> clientes = clienteRepository.obterTodosClientes();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        // Preenche o JTextArea com os dados dos clientes
        for (Cliente cliente : clientes) {
            areaTexto.append("Nome: " + cliente.getNome() + "\n");
            areaTexto.append("CPF: " + cliente.getCpf() + "\n");
            areaTexto.append("Login: " + cliente.getLogin() + "\n");

            // Formata a data antes de exibi-la
            String dataFormatada = dateFormat.format(cliente.getUltimaCompra());
            areaTexto.append("Última Compra: " + dataFormatada + "\n");

            areaTexto.append("-------------------------\n");
        }

        telaVisualizacao.add(scrollPane, BorderLayout.CENTER);
        telaVisualizacao.setVisible(true);
    }
}

