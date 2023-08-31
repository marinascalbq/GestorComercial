/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package av2.gescom;

import java.text.ParseException;

/**
 *
 * @author Marina
 */
public class GesCom {
    public static void main(String[] args) throws ParseException {
        // Crie as instâncias dos repositórios e serviços necessários
        ClienteRepository clienteRepository = new ClienteRepository();
        ProdutoRepository produtoRepository = new ProdutoRepository();
        VendaRepository vendaRepository = new VendaRepository();
        Estoque estoque = new Estoque(5);

        // Crie a instância do menu principal passando as instâncias criadas como argumentos
        MenuGUI menuGUI = MenuGUI.getInstance(
            clienteRepository, produtoRepository, vendaRepository, estoque
        );
    }
}