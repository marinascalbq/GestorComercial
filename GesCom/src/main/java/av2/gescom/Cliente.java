/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package av2.gescom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marina
 */
public class Cliente extends Pessoa {
    private Date ultimaCompra;

    public Cliente(String nome, String cpf, String login, String senha, Date ultimaCompra) {
        super(nome, cpf, login, senha);
        this.ultimaCompra = ultimaCompra;
    }
   
    public Date getUltimaCompra() {
        return ultimaCompra;
    }
    
}
