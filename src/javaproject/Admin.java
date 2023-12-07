
package javaproject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Collections;

/**
 *
 * @author zaina
 */
//Mariam
public  class Admin extends User{
        private static ArrayList<ArrayList<String>> newSupplierList = new ArrayList<>();
        private static ArrayList<ArrayList<String>> admins = new ArrayList<>();
        private List<Borrower_Mgn> records=new ArrayList<>();
       private  List<User> user=new ArrayList<>();
       private List<User> USER=new ArrayList<>();
       private List<Book> books;
       private String id;
       private double revenue;
    private String sName;
    private int numOfOrders;
    private int[] sales;
    private double[] prices;

       
//       public void viewBorrowingDetails()
//       {
//        
//    }
       
       
   
   public Admin(String name,String password) {
       super(name,password);
         this.revenue = 0.0;
        this.numOfOrders = 0;
        this.sales = sales;
        this.prices = prices;
       
    }
   public Admin(){
       
   }

    public static ArrayList<ArrayList<String>> getNewSupplierList() {
        return newSupplierList;
    }
   
     public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getSName() {
        return sName;
    }

    public void setNumberOfOrders(int numOfOrders) {
        this.numOfOrders = numOfOrders;
    }

    public int getNumberOfOrders() {
        return numOfOrders;
    }
   

 public void addUser(User U) {
        user.add(U);
       
    }
 
 
 //association (Hala)
public void addBookAdmin(Book book){
    
    Book.addBook(book);
    
}
public void removeBookAdmin(Book book){
    
    Book.removeBook(book);
}

public void editBookAdmin(Book book){
    
    Book.editBook(book);
}
//end of hala

//Shahd
public void addBorrower(String name, String password){
    Borrower newUser=new Borrower(name,password);
    newUser.addTo(name,password);
}
public void removeBorrower(){
    Borrower remove_user=new Borrower();
    remove_user.remove();
}
public void editUser( String oldname, String field, String newvalue) {
    for (User i : user) {
       if (i.getName().equals(oldname)) {
            if ("name".equals(field)) {
               i.setName(newvalue);
           } else if ("password".equals(field)) {
              i.setPass(newvalue);
           } else {
               System.out.println("Invalid field specified.");
           }
         break;
       }
    }
        System.out.println("Updated list of users:");
    for (User i : user) {
        System.out.println("Name: " + i.getName() + ", Password: " + i.getPass());
}

}
    public List<User> searchUsers(String field, String value) {
        List<User> results = new ArrayList<>();
        // Search for users based on the specified field and value
          if ("id".equals(field)) {
            for (User U : user) {
                if (U. getPass().equals(value)) {
                    results.add(U);
                }
            }
        } else if ("name".equals(field)) {
            for (User U : user) {
                if (U.getName().equals(value)) {
                    results.add(U);
                }
            }
        }
        return results;
    }
    
 public static double getTotalRevenue() {
    
    double totalRevenue = 0.0;
    for (Book book : Librarian_Mgn.getAllbooks()) {
        totalRevenue += book.getPrice();
    }
    return totalRevenue;
}
 
 public double getAverageRevenue()
    {
        
    double average = 0.0;
        average = getTotalRevenue()/Librarian_Mgn.getAllbooks().size();
    
    return average;
    
}
    
    
    
//    public void viewLibrarianWithMaxBorrowings()from shahd
//    {
//        
//    }
//    public void viewBorrowingsPerLibrarian() from malak
   // {
//        
//    }
    
    
    //Mariam
      public void addOrder(String supplierName, String book, double amount) throws IOException {
      
           for (int i=0;i<newSupplierList.size();i++) {
         
           if (newSupplierList.get(i).get(0).equals(sName)){
             numOfOrders = Integer.parseInt(newSupplierList.get(i).get(1)) + 1;
             revenue = Double.parseDouble(newSupplierList.get(i).get(2)) + amount;

            newSupplierList.get(i).set(1, Integer.toString(numOfOrders));
            newSupplierList.get(i).set(2, Double.toString(revenue));

            System.out.println("Order added successfully.");
        } else {
            System.out.println("Supplier not found.");
        }
            
    }

    }


       public void addSupplier(String sName, int numOfOrders, double revenue) throws IOException {
              Scanner s = new Scanner(System.in);
                System.out.print("Enter supplier name:");
             System.out.println();
             sName = s.next();
             System.out.print("Enter supplier number of orders:");
             System.out.println();
             numOfOrders = s.nextInt();
             System.out.print("Enter supplier revenue:");
             System.out.println();
             revenue = s.nextDouble();
        ArrayList<String> supplierInfo = new ArrayList<>();
        supplierInfo.add(sName);
        supplierInfo.add(Integer.toString(numOfOrders));
        supplierInfo.add(Double.toString(revenue));

        newSupplierList.add(supplierInfo);

    }
    
public static void addLibrarian( Librarian newLibrarian){
        Librarian.getLibrarians().add(newLibrarian);
        
        saveToFile();
}
 public static void saveToFile(){
          try{
             
              PrintWriter print=new PrintWriter("librarians.txt");
              
              for(User user:Librarian.getLibrarians()){
                  if( user instanceof Librarian){
                Librarian librarian=(Librarian)user;
                  print.println(librarian.getUserName()+", "+librarian.getPassword());
              }
              }
              print.close();
          }
          catch(IOException e){
             System.out.println(e);
          }
      }



public void addSupplier(String sName,String password, int numOfOrders, double revenue) throws IOException {
        ArrayList<String> supplierInfo = new ArrayList<>();
        if(!searchSupplier(sName)){
        supplierInfo.add(sName);
        supplierInfo.add(password);
        supplierInfo.add(Integer.toString(numOfOrders));
        supplierInfo.add(Double.toString(revenue));
        newSupplierList.add(supplierInfo);
        }
        else{
            System.out.println("This supplier already exists");
        }
        

    }
public void specifyBorrowingTermDetails(User user) {
       int totalfine=0;
       
       for(Borrower_Mgn i:records)
       {  
           if(i.getUser().equals(user))
           {
          // totalfine+=i.CalculateFine(); 
           }
       }
       System.out.println( totalfine);
}
    public void removeSupplier(String sName) {
         for (int i=0;i<newSupplierList.size();i++) {
         
           if (newSupplierList.get(i).get(0).equals(sName)) {
                newSupplierList.remove(newSupplierList.get(i));
                System.out.println("supplier removed sucessully");
            }
           else{
               System.out.println("supplier not found");
           }
        }
      
    }

    public void saveToFile(String sName) {
      for (int i=0;i<newSupplierList.size();i++) {
         
           if (newSupplierList.get(i).get(0).equals(sName)){
        String fileName = newSupplierList.get(i).get(0) + "_details.txt";
        try (FileWriter writer = new FileWriter("C:\\Users\\maria\\Documents\\NetBeansProjects\\ LibraryMangmentSystem\\src\\librarymangmentsystem\\"+fileName)) {
            writer.write("Name: " + newSupplierList.get(i).get(0) + "\n");
             writer.write("Password: " + newSupplierList.get(i).get(1) + "\n");
            writer.write("Number of Orders: " + newSupplierList.get(i).get(2) + "\n");
            writer.write("Total Revenue: " +  newSupplierList.get(i).get(3)+ "\n");
        } catch (IOException e) {
            System.out.println("Error saving file for supplier: " + newSupplierList.get(i).get(0));
        }
        }
    }}
    public void saveAllToFile() throws IOException {
      for (int i=0;i<newSupplierList.size();i++) {
        String fileName = "Suppliers.txt";
        try (FileWriter writer = new FileWriter("C:\\Users\\maria\\Documents\\NetBeansProjects\\ LibraryMangmentSystem\\src\\librarymangmentsystem\\"+fileName,true)) {
            writer.write(newSupplierList.get(i).get(0) + "\t"+newSupplierList.get(i).get(1) + "\n");
} 
        catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());}
           }}      
        
public String getSupplierWithMaxOrders() {
    String s = newSupplierList.get(0).get(0);
    for (int i = 0; i < newSupplierList.size()-1 ; i++) {
        if (Integer.parseInt(newSupplierList.get(i).get(2)) > Integer.parseInt(newSupplierList.get(i + 1).get(2))) {
            s = newSupplierList.get(i).get(0);
        } else {
            s = newSupplierList.get(i + 1).get(0);
        }
    }
    return s;
}

public String getSupplierWithMaxRevenue() {
    String s = newSupplierList.get(0).get(0);
    for (int i = 0; i < newSupplierList.size()-1; i++) {
        if (Double.parseDouble(newSupplierList.get(i).get(3)) > Double.parseDouble(newSupplierList.get(i + 1).get(3))) {
            s = newSupplierList.get(i).get(0);
            
        } else {
            s = newSupplierList.get(i + 1).get(0);
            
        }
        
    }
    return s;
}
    public static boolean searchSupplier(String sName) {
         for (int i=0;i<newSupplierList.size();i++) {
         
           if (newSupplierList.get(i).get(0).equals(sName))
        {
                return true;
            }
        }
        return false;
    }
        public void displaySupplierInfo(String sName) {
       for (int i=0;i<newSupplierList.size();i++) {
         
           if (newSupplierList.get(i).get(0).equals(sName)){
                System.out.println("Displaying Supplier Information:");
                System.out.println("Name: " + newSupplierList.get(i).get(0));
                System.out.println("Password: " + newSupplierList.get(i).get(1));
                System.out.println("Number of Orders: " + newSupplierList.get(i).get(2));
                System.out.println("Total Revenue: " + newSupplierList.get(i).get(3));
                return;
            }
           else{
           System.out.println("Supplier not found");
           }
        }
        
    }
      public void displayAllSuppliers(){
       for (int i=0;i<newSupplierList.size();i++) {
         
         {
                System.out.println("Displaying Suppliers Information:");
                System.out.println("Name: " + newSupplierList.get(i).get(0));
                System.out.println("Password: " + newSupplierList.get(i).get(1));
               
                return;
            }
        }
        
    }

      public void addAdmin(String name,String id) {
       
       Scanner s = new Scanner(System.in);
                System.out.print("Enter admin's name:");
             System.out.println();
             sName = s.next();
             System.out.print("Enter admin's password");
             System.out.println();
            id = s.next();
            
        ArrayList<String> adminsinfo = new ArrayList<>();
        adminsinfo.add(name);
        adminsinfo.add(id);
        admins.add(adminsinfo);
    }
    public void saveAllAdminsToFile() throws IOException {
      for (int i=0;i<admins.size();i++) {
        String fileName = "admins.txt";
        try (FileWriter writer = new FileWriter("C:\\Users\\maria\\Documents\\NetBeansProjects\\ LibraryMangmentSystem\\src\\librarymangmentsystem\\"+fileName,true)) {
            writer.write(admins.get(i).get(0) + "\t"+admins.get(i).get(1) + "\n");
} 
        catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());}
           }}

}



