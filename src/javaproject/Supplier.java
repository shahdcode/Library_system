/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;
import java.util.*;
import java.io.*;
public class Supplier extends Admin{
   
    
    private ArrayList<ArrayList<Object>> bookPrice = new ArrayList<>();
    public Supplier(){
        
    }
    public void SupplierOrders(String name){
        String fileName="C:\\Users\\maria\\Documents\\NetBeansProjects\\ LibraryMangmentSystem\\src\\librarymangmentsystem\\"+name+"_orders.txt";
     System.out.println("\t\tHere's all the orders for supplier "+name+":\t\t");
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            // Read and display the content line by line.
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // Close the file.
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
    
    
      public void recieveOrder(String name)  {
             for (int c=0;c<Admin.getNewSupplierList().size();c++) {
             if (Admin.getNewSupplierList().get(c).get(0).equals(name))
        {
     for(int i=0;i<Librarian.getNewOrder().size();i++){
         for(int j=0;j<bookPrice.size();j++){
         if(bookPrice.get(j).equals(Librarian.getNewOrder().get(i))){
            String sValue1=String.valueOf(bookPrice.get(j).get(1));
            String sValue2=String.valueOf(Librarian.getNewOrder().get(i).get(1));
            double d1= Double.valueOf(sValue1);
            double d2= Double.valueOf(sValue2);
           double newRevenue= Double.valueOf(Admin.getNewSupplierList().get(c).get(3));
           int newNumOfOrders= Integer.valueOf(Admin.getNewSupplierList().get(c).get(2))+1;
           double Finall=d1*d2;
           String FinalRev=String.valueOf(Finall);
           String FinalNumOfOrders=String.valueOf(newNumOfOrders);
        
            Admin.getNewSupplierList().get(c).set(2,FinalNumOfOrders);
            Admin.getNewSupplierList().get(c).set(3,FinalRev);
        }
         }
            }
         }
     }
    }
    
   public void settingPrices(){

     

        for (int i = 0; i < Book.getBooks().size(); i++) {
            ArrayList<Object> bookInfo = new ArrayList<>();
            bookInfo.add(Book.getBooks().get(i)); 
            bookInfo.add((int) (Math.random() * (41) + 10));

            bookPrice.add(bookInfo);
        }
   }

    public void editSupplier(String sName) {
        int c;
        
        for (int i = 0; i < Admin.getNewSupplierList().size(); i++) {
            
            if (Admin.getNewSupplierList().get(i).get(0).equals(sName)) {
                Scanner s = new Scanner(System.in);

                do {
                    System.out.println("Which part would you like to edit:");
                    System.out.println("1: Name");
                    System.out.println("2: Password");
                    System.out.println("0: Exit");

                    c = s.nextInt();

                    switch (c) {
                        case 1 -> {
                            System.out.println("Enter new Name here:");
                            Admin.getNewSupplierList().get(i).set(0, s.next());
                        }
                        case 2 -> {
                            System.out.println("Enter new Password here:");
                            Admin.getNewSupplierList().get(i).set(1, s.next());
                        }
                    }
                } while (c != 0);

                break;
            }
            else{
            System.out.println("Supplier not Found");
            }
        }
    }

    public static boolean searchSupplier(String sName) {
         for (int i=0;i<Admin.getNewSupplierList().size();i++) {
         
           if (Admin.getNewSupplierList().get(i).get(0).equals(sName))
        {
                return true;
            }
        }
        return false;
    }
    @Override
 public void displayAllSuppliers(){
    for (int i=0;i<Admin.getNewSupplierList().size();i++) {
         
         {
                System.out.println("Displaying Suppliers Information:");
                System.out.println("Name: " + Admin.getNewSupplierList().get(i).get(0));
                return;
            }
        }
        
 
 }
}
