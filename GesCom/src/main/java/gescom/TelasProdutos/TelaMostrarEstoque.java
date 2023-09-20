/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gescom.TelasProdutos;

/**
 *
 * @author allan, marina
 */

import av2.gescom.Estoque;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TelaMostrarEstoque {
    private Estoque estoque;

    public TelaMostrarEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public void mostrarTela() {
        JFrame estoqueFrame = new JFrame("Estoque de Produtos");
        estoqueFrame.setSize(600, 400);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Preço");
        tableModel.addColumn("Quantidade");

        JTable table = new JTable(tableModel);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("produtos.csv"));
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dadosProduto = linha.split(",");
                int idProduto = Integer.parseInt(dadosProduto[0].trim());
                String nomeProduto = dadosProduto[1];
                double preco = Double.parseDouble(dadosProduto[2]);
                int quantidade = Integer.parseInt(dadosProduto[3]);

                boolean abaixoEstoqueMinimo = false;

                if (estoque != null && quantidade < estoque.getEstoqueMinimo()) {
                    abaixoEstoqueMinimo = true;
                }

                String sinalizacao = abaixoEstoqueMinimo ? " (Abaixo do Estoque Mínimo)" : "";

                tableModel.addRow(new Object[]{idProduto, nomeProduto, preco, quantidade + sinalizacao});
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel panelPesquisar = new JPanel();
        JTextField textFieldPesquisar = new JTextField(20);
        JButton buttonPesquisar = new JButton("Pesquisar");

        panelPesquisar.add(new JLabel("Pesquisar por ID ou Nome: "));
        panelPesquisar.add(textFieldPesquisar);
        panelPesquisar.add(buttonPesquisar);

        estoqueFrame.setLayout(new BorderLayout());
        estoqueFrame.add(panelPesquisar, BorderLayout.NORTH);
        estoqueFrame.add(new JScrollPane(table), BorderLayout.CENTER);
        estoqueFrame.pack();
        estoqueFrame.setLocationRelativeTo(null);

        buttonPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pesquisa = textFieldPesquisar.getText().trim();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
                table.setRowSorter(sorter);

                RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + pesquisa, 0, 1); // Filtrar por ID e Nome
                sorter.setRowFilter(rowFilter);
            }
        });

        estoqueFrame.setVisible(true);
    }
}

