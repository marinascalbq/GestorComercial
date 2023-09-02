/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom.Telas;


import av2.gescom.VendaRepository;
import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;

public class TelaMostrarComprasEfetuadas {
    private VendaRepository vendaRepository;

    public TelaMostrarComprasEfetuadas(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public void mostrarTela() {

        JFrame comprasFrame = new JFrame("Compras Efetuadas");
        comprasFrame.setSize(600, 400);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("vendas.csv"));
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dadosCompra = linha.split(";"); 

                if (dadosCompra.length >= 3 && !dadosCompra[0].equals("null")) {
                    String cpfCliente = dadosCompra[0];
                    String nomeProduto = dadosCompra[1];
                    String quantidade = dadosCompra[2];

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String formattedDate = LocalDateTime.now().format(formatter);

                    textArea.append("\n");
                    textArea.append("CPF do Cliente: " + cpfCliente + "\n");
                    textArea.append("Nome do Produto: " + nomeProduto + "\n");
                    textArea.append("Quantidade: " + quantidade + "\n");
                    textArea.append("Data da Compra: " + formattedDate + "\n");
                    textArea.append("\n");
                    textArea.append("---------------------------------\n");
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        comprasFrame.add(scrollPane);
        comprasFrame.setVisible(true);
    }
}