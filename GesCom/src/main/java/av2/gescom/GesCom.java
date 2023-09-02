/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package av2.gescom;

import java.text.ParseException;

/**
 * Este é um software de gerenciamento de vendas que permite registrar transações
 * o objetico é oferecer recomendações personalizadas com base no histórico de compras dos clientes.
 * 
 * Além de proporcionar um gerenciamento eficiente de vendas e registro de transações, 
 * nosso software de gerenciamento de vendas busca apresentar uma característica inovadora 
 * que otimiza a experiência do usuário: a funcionalidade de recomendação personalizada.
 * Para uma segunda wave de desenvolvimento, 
 * iremos criar um histórico de compras de cada cliente, e programar para o sistema utilizar algoritmos 
 * de análise para identificar padrões de compra e preferências individuais. 
 * Isso permite que os vendedores ofereçam produtos relevantes e sugestões de compras 
 * personalizadas, aumentando as vendas cruzadas e a satisfação do cliente. 
 * A recomendação personalizada cria uma abordagem proativa para a interação com o cliente, 
 * gerando uma experiência de compra mais envolvente e melhorando o relacionamento 
 * entre a empresa e seus clientes.
 * @author Marina Allan Izabela
 */


public class GesCom {
    public static void main(String[] args) throws ParseException {
        ClienteRepository clienteRepository = new ClienteRepository();
        ProdutoRepository produtoRepository = new ProdutoRepository();
        VendaRepository vendaRepository = new VendaRepository();
        Estoque estoque = new Estoque(5);

        MenuGUI menuGUI = MenuGUI.getInstance(
            clienteRepository, produtoRepository, vendaRepository, estoque
        );
    }
}


//Verificar as instruções do projeto 
