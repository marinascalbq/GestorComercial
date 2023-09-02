/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom.Telas;

import av2.gescom.Estoque;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Marina
 */
public class TelaMostrarEstoque {
    private Estoque estoque;

    public TelaMostrarEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public void mostrarTela() {
        JFrame estoqueFrame = new JFrame("Estoque de Produtos");
        estoqueFrame.setSize(600, 400);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

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


                textArea.append("ID: " + idProduto + "\n");
                textArea.append("Nome: " + nomeProduto + "\n");
                textArea.append("Preço: " + preco + "\n");
                textArea.append("Quantidade: " + quantidade + sinalizacao + "\n");
                textArea.append("----------------------\n");
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        estoqueFrame.add(scrollPane);
        estoqueFrame.setVisible(true);
    }
}

