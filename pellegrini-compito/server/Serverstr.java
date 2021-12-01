import java.io.*;
import java.net.*;
import java.util.*;

public class Serverstr {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    Vector<String> v = new Vector<String>();
    Vector<Integer> n = new Vector<Integer>();
    int g = 0;

    public Socket attendi() {
        try {
            System.out.println("SERVER partito in esecuzione...");
            server = new ServerSocket(6789);
            client = server.accept();
            server.close();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
            outVersoClient.writeBytes("Connessione riuscita" + '\n'); // messaggio inviato per verificare la connessione

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
        }
        return client;
    }

    /*
     * for(int i=0;i<v.size();i++){
     * if(v.size()!=1){
     * if(v.get(i).equals(stringaRicevuta)){
     * outVersoClient.writeBytes("Numero gia presente " + '\n');
     * v.remove(stringaRicevuta);
     * }
     * }
     */
    public void comunica() {
        try {
            for (;;) {
                outVersoClient.writeBytes("Dammi il numero estratto" + '\n');
                // rimango in attesa della riga trasmessa dal client
                stringaRicevuta = inDalClient.readLine();
                v.add(stringaRicevuta);
                        System.out.println("stringa dal cliente: " + stringaRicevuta);
                        for (int i = 0; i < v.size(); i++) {
                            outVersoClient.writeBytes("Numero estratto:" + v.get(i) + '\n');
                        }

                    
                outVersoClient.writeBytes("fine" + '\n');
                    }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col client!");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Serverstr servente = new Serverstr();
        servente.attendi();
        servente.comunica();
    }

}