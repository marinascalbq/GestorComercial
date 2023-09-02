/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;



public class VendaRepository {

    private Venda quantidade;  
    private Estoque estoque;
    

    
    public void registrarVenda(Cliente cliente, List<Produto> produtos, int quantidade, String dataVenda) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("vendas.csv", true)); 
            

            StringBuilder linha = new StringBuilder();
            linha.append(cliente.getCpf()); 
            for (Produto produto : produtos) {
                linha.append(";").append(produto.getNome()); 
            }
            linha.append(";").append(quantidade);
            linha.append(";").append(dataVenda);

            writer.write(linha.toString());
            writer.write("\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

