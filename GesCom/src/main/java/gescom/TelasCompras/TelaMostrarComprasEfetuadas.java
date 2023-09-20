/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gescom.TelasCompras;

/**
 *
 * @author allan
 */

import av2.gescom.VendaRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMostrarComprasEfetuadas {
    private final JTextField textFieldPesquisar;
    private final JButton buttonPesquisar;

    public TelaMostrarComprasEfetuadas(VendaRepository vendaRepository) {
        textFieldPesquisar = new JTextField(50);
        buttonPesquisar = new JButton("Pesquisar");
    }

    public void mostrarTela() {
        JFrame tableFrame = new JFrame("Tabela de Compras Efetuadas");
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        tableModel.addColumn("CPF do Cliente");
        tableModel.addColumn("Nome do Produto");
        tableModel.addColumn("Quantidade");
        tableModel.addColumn("Data da Compra");
        tableModel.addColumn("Total da compra");
        tableModel.addColumn("Nome do Usuario");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("vendas.csv"));
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dadosCompra = linha.split(";");

                if (dadosCompra.length >= 3 && !dadosCompra[0].equals("null")) {
                    String cpfCliente = dadosCompra[0];
                    String nomeProduto = dadosCompra[1];
                    String quantidade = dadosCompra[2];
                    String totalDaCompra = dadosCompra[4];
                    String nomeCliente = dadosCompra[5];
                    
                    tableModel.addRow(new Object[]{cpfCliente, nomeProduto, quantidade, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), totalDaCompra, nomeCliente});
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel panelPesquisar = new JPanel();
        panelPesquisar.add(new JLabel("Pesquisar por CPF: "));
        panelPesquisar.add(textFieldPesquisar);
        panelPesquisar.add(buttonPesquisar);

        JScrollPane scrollPane = new JScrollPane(table); // Use JScrollPane para fazer a tabela se ajustar

        tableFrame.setLayout(new BorderLayout());
        tableFrame.add(panelPesquisar, BorderLayout.NORTH);
        tableFrame.add(scrollPane, BorderLayout.CENTER); // Adicione o JScrollPane em vez da tabela diretamente
        tableFrame.pack();
        tableFrame.setLocationRelativeTo(null);

        buttonPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpfPesquisa = textFieldPesquisar.getText().trim();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
                table.setRowSorter(sorter);

                RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + cpfPesquisa, 0);
                sorter.setRowFilter(rowFilter);
            }
        });

        tableFrame.setVisible(true);
    }
}
