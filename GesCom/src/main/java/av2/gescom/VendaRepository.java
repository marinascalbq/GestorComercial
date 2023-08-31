/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;



public class VendaRepository {

    private Venda quantidade;  
    private Estoque estoque;
    

    
    public void registrarVenda(Cliente cliente, List<Produto> produtos, int quantidade, String dataVenda) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("vendas.csv", true)); // Abre o arquivo para adicionar registros
            
            // Montar a linha do registro da venda (exemplo: cpf_cliente;produto1;produto2;produto3;data)
            StringBuilder linha = new StringBuilder();
            linha.append(cliente.getCpf()); // Substitua pelo método que obtém o CPF do cliente
            for (Produto produto : produtos) {
                linha.append(";").append(produto.getNome()); // Substitua pelo método que obtém o nome do produto
            }
            linha.append(";").append(quantidade);
            linha.append(";").append(dataVenda);

            writer.write(linha.toString()); // Escreve a linha no arquivo
            writer.write("\n"); // Adiciona uma quebra de linha após o registro
            writer.close(); // Fecha o arquivo após escrever

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

