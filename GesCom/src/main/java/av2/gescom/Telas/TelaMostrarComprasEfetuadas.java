/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom.Telas;


import av2.gescom.ProdutoRepository;
import av2.gescom.VendaRepository;
import av2.gescom.Venda;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import av2.gescom.Produto;

public class TelaMostrarComprasEfetuadas {
    private VendaRepository vendaRepository;
    private ProdutoRepository produtoRepository;


    public TelaMostrarComprasEfetuadas(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public void mostrarTela() {
        JFrame comprasFrame = new JFrame("Compras Efetuadas");
        comprasFrame.setSize(600, 400);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        try {
            List<Venda> vendas = vendaRepository.obterTodasVendas();
            List<Produto> produtos = produtoRepository.obterProdutosPorId();


            for (Venda venda : vendas) {
                String idProduto = Produto.getIdProduto();
                String nomeProduto = venda.getNomeProduto();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDate = dataCompra.format(formatter);

                textArea.append("\n");
                textArea.append("ID Produto: " + idProduto + "\n");
                textArea.append("Nome Produto: " + nomeProduto + "\n");
                textArea.append("Data da Compra: " + formattedDate + "\n");
                textArea.append("\n");
                textArea.append("---------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        comprasFrame.add(scrollPane);
        comprasFrame.setVisible(true);
    }
}