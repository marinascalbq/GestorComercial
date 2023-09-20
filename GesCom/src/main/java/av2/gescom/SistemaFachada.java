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
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Marina
*/
public class SistemaFachada {
    
    private List<Cliente> listaClientes; 
    private static SistemaFachada instance;
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;
    private VendaRepository vendaRepository;

    private SistemaFachada() throws ParseException {
        clienteRepository = new ClienteRepository();
        produtoRepository = new ProdutoRepository();
        vendaRepository = new VendaRepository();
    }

    public static SistemaFachada getInstance() throws ParseException {
        if (instance == null) {
            instance = new SistemaFachada();
        }
        return instance;
    }
}