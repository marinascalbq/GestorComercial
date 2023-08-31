/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom.Telas;

/**
 *
 * @author Marina
 */
import av2.gescom.Cliente;
import av2.gescom.ClienteRepository;
import av2.gescom.Estoque;
import av2.gescom.Produto;
import av2.gescom.ProdutoRepository;
import av2.gescom.Venda;
import av2.gescom.VendaRepository;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaEfetuarCompra {
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;
    private VendaRepository vendaRepository;
    private Estoque estoque;
    

    public TelaEfetuarCompra(ClienteRepository clienteRepository, ProdutoRepository produtoRepository,
                             VendaRepository vendaRepository, Estoque estoque) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.vendaRepository = vendaRepository;
        this.estoque = estoque;
    }

     public void mostrarTela() {
                
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
            try {
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
                String dataVenda = LocalDate.now().toString();
                
                // Registrar a venda no CSV
                vendaRepository.registrarVenda(cliente, produtosVenda,quantidade, dataVenda);
                
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
                JOptionPane.showMessageDialog(compraFrame, "Atenção: A quantidade após a compra ficará abaixo de 5!");
                panel.add(fecharButton);
                
                
            } catch (ParseException ex) {
                Logger.getLogger(TelaEfetuarCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
            fecharButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                compraFrame.dispose(); // Fecha a janela
    }
});
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

    public void atualizarQuantidadeNoCSV(int idProduto, int quantidadeVendida) {
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
    
    private Cliente encontrarClientePorCPF(String cpf) throws ParseException {
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
                    
                                   
                    SimpleDateFormat dateFormat  =  new SimpleDateFormat("dd.MM.yyyy");
                    Date dataVenda = null; // Verificar se vai dar erro
                   try {
                       dataVenda = dateFormat.parse(ultimaCompra);
                   } catch (ParseException ex) {
                       Logger.getLogger(TelaCadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
                       JOptionPane.showMessageDialog(null, "Formato de data inválido. Use o formato dd.MM.yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
                   
                   }

                    clienteEncontrado = new Cliente(nome, cpf, login, senha, dataVenda);
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