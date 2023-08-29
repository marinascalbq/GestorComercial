/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VendaRepository {
    
    private Estoque estoque;
    
    private static final String ARQUIVO_VENDAS = "vendas.csv"; // Coloque o caminho correto do arquivo

    public List<Venda> obterTodasVendas() throws IOException {
        List<Venda> vendas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_VENDAS))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dadosCompra = linha.split(";");

                if (dadosCompra.length == 3) {
                    String idProduto = dadosCompra[0];
                    String nomeProduto = dadosCompra[1];
                    LocalDateTime dataCompra = LocalDateTime.parse(dadosCompra[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                    // Suponhamos que você tenha uma classe Produto e que possa criar um Produto assim:
                    Produto produto = new Produto(idProduto, nomeProduto);

                    // Agora você pode criar a venda
                    Venda venda = new Venda(produto, dataCompra);
                    vendas.add(venda);
                }
            }
        }

        return vendas;
    }
   
    private void efetuarCompra() {
                
        JFrame compraFrame = new JFrame("Efetuar Compra");
        compraFrame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel cpfLabel = new JLabel("CPF do Cliente:");
        JTextField cpfField = new JTextField();
        JLabel produtoLabel = new JLabel("Código do Produto:");
        JTextField produtoField = new JTextField();
        JLabel quantidadeLabel = new JLabel("Quantidade:");
        JTextField quantidadeField = new JTextField();

        JButton comprarButton = new JButton("Comprar");
        JButton fecharButton = new JButton("Concluído");

        JTextArea dadosCompraTextArea = new JTextArea();
        dadosCompraTextArea.setEditable(false); // Impede edição do campo
        dadosCompraTextArea.setLineWrap(true); // Quebra de linha automática
        JScrollPane scrollPane = new JScrollPane(dadosCompraTextArea);

        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(produtoLabel);
        panel.add(produtoField);
        panel.add(quantidadeLabel);
        panel.add(quantidadeField);
        panel.add(comprarButton);

        comprarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfField.getText();
                int idProduto = Integer.parseInt(produtoField.getText());
                int quantidade = Integer.parseInt(quantidadeField.getText());

                // Encontrar o cliente
                Cliente cliente = encontrarClientePorCPF(cpf);
                if (cliente == null) {
                    JOptionPane.showMessageDialog(compraFrame, "Cliente não encontrado!");
                    return;
                }

                // Encontrar o produto
                Produto produto = encontrarProdutoPorId(idProduto);
                if (produto == null) {
                    JOptionPane.showMessageDialog(compraFrame, "Produto não encontrado!");
                    return;
                }

                // Verificar a quantidade em estoque
                if (quantidade > produto.getQuantidade()) {
                    JOptionPane.showMessageDialog(compraFrame, "Quantidade em estoque insuficiente!");
                    return;
                }
                
                // Verificar se a quantidade após a compra será menor que 5
                if (produto.getQuantidade() - quantidade < 5) {
                    JOptionPane.showMessageDialog(compraFrame, "Atenção: A quantidade após a compra ficará abaixo de 5!");
                }


                // Criar a lista de produtos da venda
                List<Produto> produtosVenda = new ArrayList<>();
                produtosVenda.add(produto);

                // Criar uma nova venda
                String dataVenda = LocalDateTime.now().toString(); // Usando a data atual
                Venda novaVenda = new Venda(cliente, produtosVenda);

                // Registrar a venda no CSV
                novaVenda.registrarVenda();
                                
                // Retirar a quantidade do produto do estoque
                estoque.retirarDoEstoque(idProduto, quantidade);
                
                // Atualizar a quantidade no arquivo produtos.csv
                atualizarQuantidadeNoCSV(idProduto, quantidade);
                
                // Formatação da data
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String formattedDate = LocalDateTime.now().format(formatter);

                // Monta os dados da compra para exibição
                String dadosCompra = "Cliente: " + cliente.getNome() + "\n"
                                    + "Produto: " + produto.getNome() + "\n"
                                    + "Quantidade: " + quantidade + "\n"
                                    + "Data da compra: " + formattedDate;

                // Exibe os dados da compra no campo de texto
                dadosCompraTextArea.setText(dadosCompra);

                // Adiciona o botão "Fechar" ao painel
                panel.remove(comprarButton);
                panel.add(fecharButton);
                
            }
        });

        fecharButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                compraFrame.dispose(); // Fecha a janela
            }
        });

        panel.add(scrollPane); // Adiciona a área de texto ao painel
        compraFrame.add(panel);
        compraFrame.setVisible(true);
    }

    private Produto encontrarProdutoPorId(int idProduto) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("produtos.csv"));
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dadosProduto = linha.split(",");
                int idProdutoCSV = Integer.parseInt(dadosProduto[0].trim());

                if (idProdutoCSV == idProduto) {
                    String nomeProduto = dadosProduto[1];
                    double preco = Double.parseDouble(dadosProduto[2]);
                    int quantidade = Integer.parseInt(dadosProduto[3]);

                    reader.close(); // Fecha o leitor antes de retornar
                    return new Produto(idProduto, nomeProduto, preco, quantidade);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Se não encontrar o produto com o idProduto fornecido
    }
     
    private void mostrarComprasEfetuadas() {
    JFrame comprasFrame = new JFrame("Compras Efetuadas");
    comprasFrame.setSize(600, 400);
    
    JTextArea textArea = new JTextArea();
    textArea.setEditable(false);

    try {
        BufferedReader reader = new BufferedReader(new FileReader("vendas.csv"));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] dadosCompra = linha.split(";");

            if (dadosCompra.length == 3) {
                String idProduto = dadosCompra[0];
                String nomeProduto = dadosCompra[1];
                String dataCompra = dadosCompra[2];

                // Formatar a data
                LocalDateTime dateTime = LocalDateTime.parse(dataCompra);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDate = dateTime.format(formatter);

                textArea.append("\n");
                textArea.append("ID Produto: " + idProduto + "\n");
                textArea.append("Nome Produto: " + nomeProduto + "\n");
                textArea.append("Data da Compra: " + formattedDate + "\n");
                textArea.append("\n");
                textArea.append("---------------------------------");
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
    
    private void atualizarQuantidadeNoCSV(int idProduto, int quantidadeVendida) {
    try {
        BufferedReader reader = new BufferedReader(new FileReader("produtos.csv"));
        List<String> linhas = new ArrayList<>();
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] dadosProduto = linha.split(",");
            int idProdutoCSV = Integer.parseInt(dadosProduto[0].trim());

            if (idProdutoCSV == idProduto) {
                int quantidadeAtual = Integer.parseInt(dadosProduto[3]);
                int novaQuantidade = quantidadeAtual - quantidadeVendida;
                dadosProduto[3] = Integer.toString(novaQuantidade);
                linha = String.join(",", dadosProduto);
            }

            linhas.add(linha);
        }

        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter("produtos.csv"));
        for (String linhaAtualizada : linhas) {
            writer.write(linhaAtualizada);
            writer.newLine();
        }
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
    private Cliente encontrarClientePorCPF(String cpf) {
    cpf = cpf.trim().replaceAll("[^0-9]", "");
    Cliente clienteEncontrado = null; // Inicializa como null

    try {
        BufferedReader reader = new BufferedReader(new FileReader("clientes.csv"));
        String linha;
        
        while ((linha = reader.readLine()) != null) {
            String[] dadosCliente = linha.split(",");
            String cpfCliente = dadosCliente[1].trim().replaceAll("[^0-9]", "");

            if (cpfCliente.equals(cpf)) {
                String nome = dadosCliente[0];
                String login = dadosCliente[2];
                String senha = dadosCliente[3];
                String ultimaCompra = dadosCliente[4];
                
                clienteEncontrado = new Cliente(nome, cpf, login, senha, ultimaCompra);
                break; // Encontrou o cliente, não precisa continuar
            }
        }

        reader.close(); // Fecha o arquivo após a busca

    } catch (IOException e) {
        e.printStackTrace();
    }

    return clienteEncontrado;
}
    
}


