

package main;

import java.io.File;
import java.util.Scanner;

public class Parser {
    private Scanner leitor;

    public Parser(String fileName) {
        try {
            this.leitor = new Scanner(new File(System.getProperty("user.dir") + "/src/main/resources/" + fileName));
        } catch (Exception var3) {
            System.err.println("Nao foi possivel abrir o arquivo informado: " + var3.getMessage());
        }

    }

    public boolean hasNext() {
        return this.leitor.hasNext();
    }

    public String nextLine() {
        return this.leitor.nextLine();
    }
}
