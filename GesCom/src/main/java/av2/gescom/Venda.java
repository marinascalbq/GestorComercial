/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Marina
 */
public class Venda {
    private Cliente cliente;
    private List<Produto> produtos;
    private String dataVenda;

    // Construtor
     public Venda(Cliente cliente, List<Produto> produtos, String dataVenda) {
        this.cliente = cliente;
        this.produtos = produtos;
        this.dataVenda = dataVenda;
    }

    public double calcularValorTotal() {
        double valorTotal = 0;
        for (Produto produto : produtos) {
            valorTotal += produto.getPreco();
        }
        return valorTotal;
    }

    // Método para retirar a quantidade dos produtos do estoque
    public void retirarDoEstoque() {
        for (Produto produto : produtos) {
            produto.retirarDoEstoque();
        }
    }
    
        public List<Produto> getProdutos() {
        return produtos;
    }
        
        public Cliente getCliente() {
        return cliente;
    }
        
            public String getDataVenda() {
        return dataVenda;
    }
            
    // Método para registrar a venda
    public void registrarVenda() {
        try {
            FileWriter fileWriter = new FileWriter("vendas.csv", true); // Abrir arquivo CSV para adicionar registros
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Montar a linha do registro da venda (exemplo: cpf_cliente;produto1;produto2;produto3;data)
            StringBuilder linha = new StringBuilder();
            linha.append(cliente.getCpf()); // Substitua pelo método que obtém o CPF do cliente
            for (Produto produto : produtos) {
                linha.append(";").append(produto.getNome()); // Substitua pelo método que obtém o nome do produto
            }
            linha.append(";").append(dataVenda);

            printWriter.println(linha.toString());

            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
}
}
