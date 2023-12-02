package main;
import java.util.Scanner;
import java.io.File;

public class Parser {
    private Scanner leitor;

    public Parser(String fileName) {
    	try {
        leitor = new Scanner(new File(System.getProperty("user.dir") + "/src/main/resources/" + fileName));
    	} catch(Exception e) {
    		System.err.println("Nao foi possivel abrir o arquivo informado: "+e.getMessage());
    	}
    }
    
    public boolean hasNext() {
        return leitor.hasNext();
    }

    public String nextLine() {
        return leitor.nextLine();
    }
}
