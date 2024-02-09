package persistence;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

// creates a save and load feature for balance
public class Save {

    // EFFECTS: saves the current balance of the user, overwrites previous saves
    public String saveBalance(double balance, String file) {
        try {
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            pw.print(balance);
            pw.close();
            return "Balance saved.";
        } catch (Exception e) {
            return "Unable to save.";
        }
    }

    // EFFECTS: loads the saved balance of the user
    public double loadBalance(String file) {
        double load = 0;
        try {
            FileReader fr = new FileReader(file);
            Scanner scan = new Scanner(fr);
            load = scan.nextDouble();
            scan.close();
        } catch (Exception e) {
            System.out.println("Could not find file.");
        }
        return load;
    }
}

