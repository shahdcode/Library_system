package javaproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Supplier extends User {
    private String Sname;
    private String password;
    private static int numOfOrders;
    private static double totalRevenue;
    private ArrayList<Object> bookInfo = new ArrayList<>();
    public Supplier(String name, String password) {
        this.Sname = name;
        this.password = password;
        this.totalRevenue = 0.0;
        this.numOfOrders = 0;
    }

    public void setSName(String Sname) {
        this.Sname = Sname;
    }

    public void setSPassword(String password) {
        this.password = password;
    }

    public Supplier(String name) {
        this.Sname = name;
    }

    public static int getSNumberOfOrders() {
        return numOfOrders;
    }

    public String getSName() {
        return Sname;
    }

    public String getSPassword() {
        return password;
    }

    public double getSTotalRevenue() {
        return totalRevenue;
    }

    public int getNumberOfOrders() {
        return numOfOrders;
    }

    public void receiveOrder(String bookTitle, int numberOfCopies) {
        double finalPrice = -1;            if(searchBook(bookTitle)){
         String fileName="C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+Sname + "_Prices.txt";
               try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(bookTitle)) {
                    // Assuming the price is a double value in the line
                    String[] parts = line.split("\t"); // Assuming values are tab-separated, adjust accordingly
                    if (parts.length >= 2) {
                        finalPrice=  Double.parseDouble(parts[1]);
                    }
                }
            }
             } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file or parsing price: " + e.getMessage());
        }}
        if(finalPrice!=-1){
        double orderTotal = finalPrice * numberOfCopies;
        totalRevenue =totalRevenue+ orderTotal;
        numOfOrders++;
        
       
        saveSupplierInfo();
              System.out.println("Order placed successfully!");

        }
        else{
        System.out.println("book not found");
        }

    }

    public void settingPrices() {
        try (FileWriter writer = new FileWriter("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + Sname + "_Prices.txt")) {
            for (int i = 0; i < Book.getBooks().size(); i++) {
                writer.write(Book.getBooks().get(i) + "\t" + (Math.random() * (41) + 10) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving orders to file: " + e.getMessage());
        }
    }

    public void displayBooks() {
        String FileName = Sname + "_Prices.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + FileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void displayOrders() {
        String FileName = Sname + "_Orders.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + FileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    public void displayPrices() {
        String FileName = Sname + "_Prices.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + FileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void saveSupplierInfo() {
        String fileName = Sname + "_Info.txt";
        try (FileWriter writer = new FileWriter("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + fileName)) {
            writer.write("Name: " + Sname + "\nPassword: " + password + "\nRevenue:" + totalRevenue + " \nNumber Of Orders:" + numOfOrders + "\n");
        } catch (IOException e) {
            System.out.println("Error saving suppliers to file: " + e.getMessage());
        }
    }



 
     public boolean searchBook(String title) {
      
        boolean found = false;
      
            if (Book.searchBook(title) != null) {
                found = true;
            }
        
        return found;
    }
}
