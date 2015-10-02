/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appbatalhanaval;


/**
 *
 * @author Fernando
 */
public class AppBatalhaNaval {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int ate90 =  ((int)Math.round(Math.random()*90 ));

        ServidorApp servidor = new ServidorApp("192.168.107.220", 4000);
        servidor.setTela(new TelaJogo());
        servidor.getTela().setServidor(servidor);
        servidor.start();        
        servidor.getTela().show();
    }
    
}
