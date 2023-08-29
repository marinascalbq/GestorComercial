/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom.Telas;

import av2.gescom.Cliente;
import av2.gescom.ClienteRepository;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Marina
 */
public class TelaVisualizarClientes {
    private ClienteRepository clienteRepository;

    public TelaVisualizarClientes(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void mostrarTela() {
    // Cria uma nova tela para visualizar os clientes
    JFrame telaVisualizacao = new JFrame("Clientes Cadastrados");
    telaVisualizacao.setSize(600, 400);
    telaVisualizacao.setLayout(new BorderLayout());

    // Cria um JTextArea para exibir os clientes
    JTextArea areaTexto = new JTextArea();
    areaTexto.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(areaTexto);

    // Obtém a lista de clientes do repositório
    List<Cliente> clientes = clienteRepository.obterTodosClientes();

    // Preenche o JTextArea com os dados dos clientes
    for (Cliente cliente : clientes) {
        areaTexto.append("Nome: " + cliente.getNome() + "\n");
        areaTexto.append("CPF: " + cliente.getCpf() + "\n");
        areaTexto.append("Login: " + cliente.getLogin() + "\n");
        areaTexto.append("Última Compra: " + cliente.getUltimaCompra() + "\n");
        areaTexto.append("-------------------------\n");
    }

    // Adiciona o JTextArea ao painel
    telaVisualizacao.add(scrollPane, BorderLayout.CENTER);

    // Exibe a tela de visualização
    telaVisualizacao.setVisible(true);
}}

