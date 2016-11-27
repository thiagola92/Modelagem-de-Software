
import connection.Server;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        Server s = new Server();
        s.start();
        
        Scanner scan = new Scanner(System.in);
        while(!s.getUltimoComando().equals("###")) {
            s.setUltimoComando(scan.nextLine());
        }
        s.close();
        
        System.out.println("=> Server finalizando");
    }
    
}
