/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

/**
 *
 * @author Marina
 */
public class Cliente extends Pessoa {
    private String ultimaCompra;

    public Cliente(String nome, String cpf, String login, String senha, String ultimaCompra) {
        super(nome, cpf, login, senha);
        this.ultimaCompra = ultimaCompra;
    }

    public String getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(String ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }
 
}
