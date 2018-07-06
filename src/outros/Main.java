package outros;

import connection.Server;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        Server s = new Server();
        s.start();
        
		Scanner scan = new Scanner(System.in);
        while(!scan.nextLine().equals("###") && s.isClosed() == false) {
        }
        
        s.close();
        scan.close();
        System.out.println("=> Main finalizada");
    }
    
}
