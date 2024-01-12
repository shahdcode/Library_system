package javaproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author zaina
 */
//Mariam
public  class Admin extends User implements Serializable {
   private transient Scanner scanner;
     private List<Borrower_Mgn> records=new ArrayList<>(); 
private static ArrayList<Supplier> suppliers = new ArrayList<>();
     private static ArrayList<Admin> admins = new ArrayList<>();
       private static ArrayList<Librarian> librarians;
        private static ArrayList<ArrayList<String>> newSupplierList = new ArrayList<>();
       private ArrayList<Book> books;
       private String pass;
       private String name;
        private String sName;
    private int numOfOrders;
        @Override
    public String toString() {
        return "Admin [username=" + name + ", password=" + pass + "]"; 
    }
       private double revenue;
    private File file=new File("admins3.dat");
   private File file1=new File("librarians.dat");
    public void addAdmin(User ad) {
        Admin add=new Admin(ad.getName(),ad.getPass());
       admins.add(add);
    }

    public Admin() {
    }
   public Admin(String name,String id)
   {
        super(name, id);
         this.name = name;
        this.pass = id;
               this.scanner = new Scanner(System.in);
    }
    public String getName() {
        return name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }
     public static ArrayList<Librarian> getLibrarians() {
        return librarians;
    }
     private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(name);
        out.writeObject(pass);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        pass = (String) in.readObject();
    }

 public void saveAllAdminsToFile() throws IOException{
     try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:\\Users\\zaina\\OneDrive\\Documents\\NetBeansProjects\\javaproject\\" + file))){
         oos.writeObject(admins);
     }catch(IOException e){
          System.out.println("Failed to save admins: " + e.getMessage());
          e.printStackTrace();
     }
 }
public String displayAdminsFromFile() throws IOException, ClassNotFoundException {
    StringBuilder adminDetails = new StringBuilder();
    
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\zaina\\OneDrive\\Documents\\NetBeansProjects\\javaproject\\" + file))) {
        ArrayList<Admin> readAdmins = (ArrayList<Admin>) ois.readObject();  
        
        for (Admin admin : readAdmins) {
            adminDetails.append("Name: ").append(admin.getName()).append(", ID: ").append(admin.getPass()).append("\n");
        }
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Failed to read admins from file: " + e.getMessage());
        e.printStackTrace();
    }
    
    return adminDetails.toString();  
}


//    
// public void specifyBorrowingTermDetails(User user) {
//       int totalfine=0;
//       
//       for(Borrower_Mgn i:records)
//       {  
//           if(i.getUser().equals(user))
//           {
//           totalfine+=i.CalculateFine(); 
//           }
//       }
//       System.out.println( totalfine);
//     
//    }
      

  

   
   
     public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getRevenue() {
        return revenue;
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
public ArrayList<Admin> readfromfileadmin()throws IOException, ClassNotFoundException {
    ArrayList<Admin> adminlist = new ArrayList<>();
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\zaina\\OneDrive\\Documents\\NetBeansProjects\\javaproject\\" + file))) {
        int numOfAdmins = ois.readInt();
        for (int i = 0; i < numOfAdmins; i++) {
            Admin admin = (Admin) ois.readObject(); 
            adminlist.add(admin);
            System.out.println("Reading admin: " + admin);
        }
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + e.getMessage());
        e.printStackTrace();
    } catch (IOException e) {
        System.out.println("Error reading admins: " + e.getMessage());
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        System.out.println("Class not found: " + e.getMessage());
        e.printStackTrace();
    }

    return adminlist;
}
public void removeAdmin(String NameToRemove) throws IOException, ClassNotFoundException {

  if(NameToRemove==null|| NameToRemove.trim().isEmpty()){
        System.out.println("invalid username");
        return;
}
      
  boolean removed=false;
Iterator<Admin>it=admins.iterator();

while(it.hasNext())
{
    Admin ad=it.next();
    if(ad.getName().equals(NameToRemove)){
        it.remove();
        System.out.println("Admin"+NameToRemove+" removed successfully");
        removed=true;
        break;
}

    }
if(!removed)
{
     System.out.println("Admin"+NameToRemove+" not found");
     return;
}

    saveAllAdminsToFile();
}
public void editadmin( String old, String field, String newvalue) throws IOException, ClassNotFoundException {

     boolean userFound = false;
    for (Admin i : admins) {
       if (i.getName().equals(old)) {
         userFound = true;
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
       if (!userFound) {
        System.out.println("User not found.");
        return;
    }
    try{
        FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.close();
   saveAllAdminsToFile();
    }catch(Exception e)
    {
        System.out.println(e); 
    }
        System.out.println("Updated list of users:");
    for (Admin i : admins) {
        System.out.println("Name: " + i.getName() + ", Password: " + i.getPass());
}

}
//public void editlibrarian( String old, String field, String newvalue) {
//    librarians=readfromfilelibrarian();
//    boolean userFound = false;
//    for (Librarian i : librarians) {
//       if (i.getUserName().equals(old)) {
//            if ("name".equals(field)) {
//               i.setUserName(newvalue);
//              
//           } else if ("password".equals(field)) {
//              i.setPassword(newvalue);
//           } else {
//               System.out.println("Invalid field specified.");
//           }
//         break;
//       }
//    }
//     if (!userFound) {
//        System.out.println("User not found.");
//        return;
//     }
//    try{
//        FileWriter fileWriter = new FileWriter(file1, false);
//            fileWriter.close();
//  saveToFile();
//    }catch(Exception e)
//    {
//        System.out.println(e); 
//    }
//        System.out.println("Updated list of users:");
//    for (Librarian i : librarians) {
//        System.out.println("Name: " + i.getUserName() + ", Password: " + i.getPassword());
//}
//
//}
 

         
 public List<User> searchAdmin(String field, String value) {
    List<User> results = new ArrayList<>();
  
    if ("id".equalsIgnoreCase(field)) {
        for (Admin admin : admins) {
            if (admin.getPass().equals(value)) {
                results.add(admin);
            }
        }
    } else if ("name".equalsIgnoreCase(field)) {
        for (Admin admin : admins) {
            if (admin.getName().equalsIgnoreCase(value)) {
                results.add(admin);
            }
        }
    }
    return results;
}
  public static void searchLibrarian(String userName){
         if(userName==null ){
             System.out.println("Invalid user name entered");
             return;
         }
         
        ArrayList<Librarian> librarians = Librarian.getLibrarians();
        if(librarians==null){
            System.out.println("librarian system is empty, there are no librarians to retrive");
            return;
        }
       
        for(User user:librarians){
            if(user instanceof Librarian){
                Librarian librarian=(Librarian)user;
         if(librarian.getUserName().equals(userName)){
             System.out.println("Librarian found");
             System.out.println("user name: "+librarian.getUserName());
             return;
         }
         }   
        }
        System.out.println("No librarian was found by this user name: "+userName);
    }
public static double getTotalRevenue(){
    double totalrev=0.0;
    List<Double> payments=Librarian_Mgn.readPaymentsFromFile();
    for(Double i:payments)
    {
        totalrev+=i;
    }
    return totalrev;
}

    /**
     *
     * @return
     */
public double getAverageRevenue(){
        List<Double> avg=Librarian_Mgn.readPaymentsFromFile();
        if(avg.isEmpty())
        {
            return 0.0;
        }
        double total= getTotalRevenue();
        return total/avg.size();
    }

   public boolean searchNameInFile(String fileName, String targetName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+"); 
                if (parts.length >= 1 && parts[0].equals(targetName)) {
                    }
            }
            return true;
        }}
    //Mariam
            
      public void addOrder(String supplierName, String book, double amount)  {
      
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
     //  Librarian.getLibrarians().add(newLibrarian);
        
       if(newLibrarian != null){
           Librarian.getLibrarians().add(newLibrarian);
           newLibrarian.saveToFile();
       }

//    try{
//         Librarian.getWriter().close();
//    }catch(IOException e){
//        System.out.println(e);
//    }
       
      //  saveToFile();
}
 public static void saveToFile(){
          try{
             
             ObjectOutputStream out =new ObjectOutputStream(new FileOutputStream("librarians.binary"));
              
              for(User user:librarians){
                  if( user instanceof Librarian){
                Librarian librarian=(Librarian)user;
                  out.writeObject(librarian);
              }
              }
              out.close();
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
 

    public boolean searchFile(String directoryPath, String targetFileName) {
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().equals(targetFileName)) {
                        return true;
                    }
                }
            } else {
                System.err.println("Error listing files in the directory.");
            }
        } else {
            System.err.println("Invalid directory path: " + directoryPath);
        }

        return false;
    }

    
    //Mariam
      private ArrayList<String> supplierNames = new ArrayList<>();
  
    public void displaySuppliers() {
        String fileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.dat";

        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(fileName))) {
            while (inputStream.available() > 0) {
                String supplierName = inputStream.readUTF();
                int contactInfo = inputStream.readInt();
                String newLine = inputStream.readUTF();  

                System.out.println("Supplier Name: " + supplierName);
                System.out.println("Contact Info: " + contactInfo);
                System.out.println("\n");
            }
        } catch (IOException e) {
                        System.out.println(e);

        }
    }


public void saveSuppliersToFile(Supplier sup) {
    String fileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.dat";
    try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
        raf.seek(raf.length());
            raf.writeUTF( sup.getSName());
            //raf.writeUTF( "\t"); 
            raf.writeInt( sup.getScontactInfo());
            raf.writeUTF( "\n"); 

        
    } catch (IOException e) {
                    System.out.println(e);

    }
}

public void addSupplier(String name, int contactInfo) throws IOException {
                           Supplier sup=new Supplier(name,contactInfo);
      

    ArrayList<String> supplierInfo = new ArrayList<>();
    if (!searchSupplier(name)) {
        supplierInfo.add(name);
        supplierInfo.add(Integer.toString(contactInfo)); 
        supplierInfo.add("0"); 
        supplierInfo.add("0.0"); 
        supplierNames.add(name);
        saveSuppliersToFile(sup);
        sup.saveSupplierInfo();

        System.out.println("Supplier added successfully!");
    } else {
        System.out.println("This supplier already exists");
    }

}




    public void displayOrders(Supplier sup) throws IOException {
            System.out.println("Orders for " + sup.getSName() + ":");
          
        sup.displayOrders();

    }

    public void displayPrices(Supplier  sup) throws IOException {
               try (RandomAccessFile raf = new RandomAccessFile( "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+sup.getSName() + "_Prices.dat", "r")) {
            System.out.println("Prices for " + sup.getSName() + ":");
           sup.displayPrices();
        }

    }


   
public void displaySupplierInfo(Supplier sup) {
    sup.displaySupplierInfo();
}
public  boolean searchSupplier(String sName) {
    boolean found=false;
     String fileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.dat";

        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(fileName))) {
            while (inputStream.available() > 0) {
                String supplierName = inputStream.readUTF();
                Supplier sup=new Supplier(supplierName);
                int contactInfo = inputStream.readInt();
                String newLine = inputStream.readUTF();  
                if(sName.equalsIgnoreCase( supplierName)){
                found=true;
                }
            }
        } catch (IOException e) {
                        System.out.println(e);

        }
        return found;
}

  
public void deleteSupplierFiles(Supplier sup) throws IOException {
    String name = sup.getSName();
    String contactInfo = String.valueOf(sup.getScontactInfo());

    File infoFile = new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + name + "_Info.dat");
    File ordersFile = new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + name + "_Orders.dat");
    File pricesFile = new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + name + "_Prices.dat");

    if (infoFile.exists()) {
        infoFile.delete();
    }
    if (ordersFile.exists()) {
        ordersFile.delete();
    }
    if (pricesFile.exists()) {
        pricesFile.delete();
    }
        File inputFile = new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.dat" );
       File tempFile = new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\temp.dat" );

      
    try (RandomAccessFile raf = new RandomAccessFile(inputFile, "rw");
         RandomAccessFile tempRaf = new RandomAccessFile(tempFile, "rw")) {

        long pos = 0;

        while (raf.getFilePointer() < raf.length()) {
            long currentPosition = raf.getFilePointer();
            String currentLine = raf.readUTF();
             int currentContactInfo = raf.readInt();
            raf.readUTF();
            if (!currentLine.contains(sup.getSName())) {
             tempRaf.writeUTF(currentLine);
                tempRaf.writeInt(currentContactInfo);
             tempRaf.writeUTF("\n");
                pos = currentPosition;
            }
        }

        raf.seek(pos);
        tempRaf.seek(0);
        while (tempRaf.getFilePointer() < tempRaf.length()) {
            raf.writeUTF(tempRaf.readUTF());
             raf.writeInt(tempRaf.readInt());
            raf.writeUTF("\n");
        }

    } catch (IOException e) {
    } finally {
        if (tempFile.exists()) {
            if (inputFile.delete()) {
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Error renaming file.");
                }
            } else {
                System.out.println("Error deleting file.");
            }
        }
    
}

    }
public String getsMaxRevenue(){
    double max=0;
    String sMax=null;
          String fileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.dat";

        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(fileName))) {
            while (inputStream.available() > 0) {
                String supplierName = inputStream.readUTF();
                Supplier sup=new Supplier(supplierName);
                int contactInfo = inputStream.readInt();
                String newLine = inputStream.readUTF();  
    String infoFileName =  "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+sup.getSName() + "_info.dat";

    try (DataInputStream infoInputStream = new DataInputStream(new FileInputStream(infoFileName));
               RandomAccessFile raf = new RandomAccessFile(infoFileName, "r")) {

        while (infoInputStream.available() > 0) {
                String  name = raf.readUTF();
                int  contact = raf.readInt();
                double rev = raf.readDouble();
                int   orders = raf.readInt();
                if(max<rev){
                max=rev;
                sMax=sup.getSName();
                }
           }
            }catch (IOException e) {
                            System.out.println(e);

        }
            }     
             
        } catch (IOException e) {
            System.out.println(e);
        }
    return sMax;
}

public String getsMaxOrders(){
     int max=0;
    String sMax=null;
          String fileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.dat";

        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(fileName))) {
            while (inputStream.available() > 0) {
                String supplierName = inputStream.readUTF();
                Supplier sup=new Supplier(supplierName);
                int contactInfo = inputStream.readInt();
                String newLine = inputStream.readUTF();  
    String infoFileName =  "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+sup.getSName() + "_info.dat";

    try (DataInputStream infoInputStream = new DataInputStream(new FileInputStream(infoFileName));
               RandomAccessFile raf = new RandomAccessFile(infoFileName, "r")) {

        while (infoInputStream.available() > 0) {
                String  name = raf.readUTF();
                int  contact = raf.readInt();
                double rev = raf.readDouble();
                int   orders = raf.readInt();
                if(max<orders){
                max=orders;
                sMax=sup.getSName();
                }
            }
            }catch (IOException e) {
                            System.out.println(e);

        }
            }     
             
        } catch (IOException e) {
            System.out.println(e);
        }
    return sMax;
//return max;
}
  
   public void editSupplier(Supplier sup,int newContactInfo) throws IOException{
    File inputFile = new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.dat" );
       File tempFile = new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\temp.dat" );

      
    try (RandomAccessFile raf = new RandomAccessFile(inputFile, "rw");
         RandomAccessFile tempRaf = new RandomAccessFile(tempFile, "rw")) {

        long pos = 0;

        while (raf.getFilePointer() < raf.length()) {
            long currentPosition = raf.getFilePointer();
            String currentLine = raf.readUTF();
            int currentContactInfo = raf.readInt();
            raf.readUTF();
            if (!currentLine.contains(sup.getSName())) {
                tempRaf.writeUTF(currentLine);
                tempRaf.writeInt(currentContactInfo);
                tempRaf.writeUTF("\n");
                pos = currentPosition;
            }
        }

        raf.seek(pos);
        tempRaf.seek(0);
        while (tempRaf.getFilePointer() < tempRaf.length()) {
            raf.writeUTF(tempRaf.readUTF());
            raf.writeInt(tempRaf.readInt());
            raf.writeUTF("\n");
        }

    } catch (IOException e) {
                    System.out.println(e);

    } finally {
        if (tempFile.exists()) {
            if (inputFile.delete()) {
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Error renaming temp file to original file.");
                }
            } else {
                System.out.println("Error deleting original file before renaming.");
            }
        }
   sup.edit(newContactInfo);
   sup.setScontactInfo(newContactInfo);
   saveSuppliersToFile(sup);
   
   }

    
}}