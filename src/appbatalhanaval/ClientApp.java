/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appbatalhanaval;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jogobatalhanaval.JogadaBatalhaNaval;
import jogobatalhanaval.JogadorBatalhaNaval;
import mensagem.Mensagem;
import projetocliente.ProjetoCliente;

/**
 *
 * @author Fernando
 */
public class ClientApp extends ProjetoCliente {

    @Override
    protected void executarAcaoEnviarMensagem(ObjectOutputStream saida) { 
        try { 
            Mensagem msg = getMensagem();      
            if (msg instanceof JogadaBatalhaNaval) {
                saida.writeObject((JogadaBatalhaNaval) msg);
            } else if (msg instanceof JogadorBatalhaNaval) {
                saida.writeObject((JogadorBatalhaNaval) msg);
            }

        } catch (IOException ex) {
            Logger.getLogger(ProjetoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ClientApp() {
        super();
    }
    
}
