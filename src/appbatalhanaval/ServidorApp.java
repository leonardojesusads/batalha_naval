/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appbatalhanaval;

import appbatalhanaval.TelaJogo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jogo.InicioJogo;
import jogobatalhanaval.InicioJogoBatalhaNaval;
import jogobatalhanaval.JogadaBatalhaNaval;
import jogobatalhanaval.JogadorBatalhaNaval;
import mensagem.Mensagem;
import projetoservidor.ProjetoServidor;
import servidorbatalhanaval.ServidorBatalhaNaval;

/**
 *
 * @author Fernando
 */
public class ServidorApp extends ProjetoServidor{

    private TelaJogo tela;

    public TelaJogo getTela() {
        return tela;
    }

    public void setTela(TelaJogo tela) {
        this.tela = tela;
    }
    
    @Override
    protected void executarPreCarga() {
        tela.setPort(getPort()); 
        tela.setServidor(this);
    }
    
    public ServidorApp(String pHost, int pPort) {
        super(pHost, pPort);    
    }
    
    @Override
    protected synchronized void executarAcaoReceberMensagem(ObjectInputStream entrada) {
        try {
            Object obj = entrada.readObject();
            if (obj instanceof JogadaBatalhaNaval) {
                JogadaBatalhaNaval jogada = (JogadaBatalhaNaval) obj;  
                tela.renderizar(jogada);
                if (tela.getNomeJogador().equals(jogada.getJogador().getNome())) {
                    tela.atualizarPontuacao(jogada);
                }
            } else if (obj instanceof InicioJogo) {
                InicioJogo inicio = (InicioJogo) obj;
                tela.iniciarJogo();
            } else if (obj instanceof JogadorBatalhaNaval) {
                JogadorBatalhaNaval jogador = (JogadorBatalhaNaval) obj;
                JOptionPane.showMessageDialog(tela, "Vencedor " + jogador.getNome());
            } 
        } catch (IOException ex) {
            System.out.println("Erro ao decodificar mensagem no jogador " + getPort());
            Logger.getLogger(ServidorApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
           
}
