/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gescom.TelasClientes;

/**
 *
 * @author Allan, Marina
 */

import av2.gescom.Cliente;
import av2.gescom.ClienteRepository;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter; // Adicione esta importação
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.List;

public class TelaVisualizarClientes {
    private final ClienteRepository clienteRepository;

    public TelaVisualizarClientes(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void mostrarTela() {
        JFrame telaVisualizacao = new JFrame("Clientes Cadastrados");
        telaVisualizacao.setSize(800, 400);
        telaVisualizacao.setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Nome");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Login");
        tableModel.addColumn("Última Compra");

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        List<Cliente> clientes = clienteRepository.obterTodosClientes();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        for (Cliente cliente : clientes) {
            String[] rowData = new String[4];
            rowData[0] = cliente.getNome();
            rowData[1] = cliente.getCpf();
            rowData[2] = cliente.getLogin();
            rowData[3] = dateFormat.format(cliente.getultimaCompra());
            tableModel.addRow(rowData);
        }

        JTextField textFieldPesquisar = new JTextField(20);
        JButton buttonPesquisar = new JButton("Pesquisar");

        buttonPesquisar.addActionListener((ActionEvent e) -> {
            String nomePesquisa = textFieldPesquisar.getText().trim().toLowerCase();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
            table.setRowSorter(sorter);
            
            RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + nomePesquisa, 0);
            sorter.setRowFilter(rowFilter);
        });

        JPanel panelPesquisar = new JPanel();
        panelPesquisar.add(new JLabel("Pesquisar por Nome: "));
        panelPesquisar.add(textFieldPesquisar);
        panelPesquisar.add(buttonPesquisar);

        telaVisualizacao.add(panelPesquisar, BorderLayout.NORTH);
        telaVisualizacao.add(scrollPane, BorderLayout.CENTER);
        telaVisualizacao.setVisible(true);
    }
}
