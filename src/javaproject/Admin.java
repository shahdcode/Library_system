package javaproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
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
       // private String sName;
   // private int numOfOrders;
        private static int orderId=1;
       private static int  maxO=0;
      private static double maxR=0.0;
     private static String supWithMaxO=null;
     private static String supWithMaxR=null;





        @Override
    public String toString() {
        return "Admin [username=" + name + ", password=" + pass + "]"; // Assuming you have username and password fields
    }
       private double revenue;
    private File file=new File("admins2.dat");
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
     private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(name);
        out.writeObject(pass);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        pass = (String) in.readObject();
    }
 public void saveAllAdminsToFile() throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\zaina\\OneDrive\\Documents\\NetBeansProjects\\javaproject\\"+file))) {
        List<Admin> admins = this.admins; 
        oos.writeInt(admins.size()); 
        for (Admin admin : admins) {
            System.out.println("Writing admin: " + admin);
            oos.writeObject(admin); 
        }
    } catch (IOException e) { 
        System.out.println("Failed to save admins: " + e.getMessage());
        e.printStackTrace(); 
    }
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
public ArrayList<Librarian> readfromfilelibrarian() {
    ArrayList<Librarian> lib = new ArrayList<>();
    try (FileInputStream fis = new FileInputStream("librarians.dat");
         DataInputStream reader = new DataInputStream(fis)) {
        while (reader.available() > 0) {
            String line = reader.readUTF();
            String[] userInfo = line.split(" ");
            if (userInfo.length >= 2) {
                String currentUserName = userInfo[0];
                String currentUserPass = userInfo[1];              
                Librarian librarian = new Librarian(currentUserName, currentUserPass); 
                lib.add(librarian);
                System.out.println(line);
            }
        }

       } catch (FileNotFoundException e) {
        System.out.println("File not found: " + e.getMessage());
        // Optionally, create a default behavior, log the error, or handle it in another way.
    } catch (EOFException e) {
        System.out.println("End of file reached while reading librarians.");
    } catch (IOException e) {
        System.out.println("Error reading librarians: " + e.getMessage());
        e.printStackTrace();
    }

    return lib;
   
}
public void editadmin( String old, String field, String newvalue) throws IOException, ClassNotFoundException {
    admins=readfromfileadmin();
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
public void editlibrarian( String old, String field, String newvalue) {
    librarians=readfromfilelibrarian();
    boolean userFound = false;
    for (Librarian i : librarians) {
       if (i.getUserName().equals(old)) {
            if ("name".equals(field)) {
               i.setUserName(newvalue);
              
           } else if ("password".equals(field)) {
              i.setPassword(newvalue);
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
        FileWriter fileWriter = new FileWriter(file1, false);
            fileWriter.close();
  saveToFile();
    }catch(Exception e)
    {
        System.out.println(e); 
    }
        System.out.println("Updated list of users:");
    for (Librarian i : librarians) {
        System.out.println("Name: " + i.getUserName() + ", Password: " + i.getPassword());
}

}

 
        
                
 public List<User> searchAdmin(String field, String value) {
    List<User> results = new ArrayList<>();
    ArrayList<Admin> admins;
    try {
        admins = readfromfileadmin();
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
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
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
 public static double getTotalRevenue() {
    
    double totalRevenue = 0.0;
    for (Book book : Librarian_Mgn.getAllbooks()) {
        totalRevenue += book.getPrice();
    }
    return totalRevenue;
}

    /**
     *
     * @return
     */
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

//    public void viewLibrarianWithMaxBorrowings()from shahd
//    {
//        
//    }
//    public void viewBorrowingsPerLibrarian() from malak
   // {
//        
//    }

public static void addLibrarian( Librarian newLibrarian){
        Librarian.getLibrarians().add(newLibrarian);
        
        saveToFile();
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
//mariam

 
    public static int getOrderId() {
        return orderId;
    }
     public static void setOrderId(int orderId) {
        Admin.orderId= orderId;
    }
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
                System.out.println("------------------------------");
            }
        } catch (IOException e) {
            System.out.println("Error displaying suppliers from file: " + e.getMessage());
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
        System.out.println("Error saving suppliers to file: " + e.getMessage());
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
          //   try (RandomAccessFile raf = new RandomAccessFile( "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+sup.getSName() + "_Orders.dat", "r")) {
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
            System.out.println("Error displaying suppliers from file: " + e.getMessage());
        }
        return found;
}

public void writeUTF(RandomAccessFile raf, String value) throws IOException {
    byte[] bytes = value.getBytes();
    raf.writeInt(bytes.length);
    raf.write(bytes);
}

public void writeInt(RandomAccessFile raf, int value) throws IOException {
    raf.writeInt(value);
}

public void writeDouble(RandomAccessFile raf, double value) throws IOException {
    raf.writeDouble(value);
}

public int readInt(RandomAccessFile raf) throws IOException {
    return raf.readInt();
}

public double readDouble(RandomAccessFile raf) throws IOException {
    return raf.readDouble();
}
public String readString(RandomAccessFile raf) throws IOException {
    int length = raf.readInt();
    byte[] bytes = new byte[length];
    raf.readFully(bytes);
    return new String(bytes);
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

        long lastPosition = 0;

        while (raf.getFilePointer() < raf.length()) {
            long currentPosition = raf.getFilePointer();
            String currentLine = raf.readUTF();
            int currentContactInfo = raf.readInt();
            raf.readUTF();
            if (!currentLine.contains(sup.getSName())) {
                tempRaf.writeUTF(currentLine);
                tempRaf.writeInt(currentContactInfo);
                tempRaf.writeUTF("\n");
                lastPosition = currentPosition;
            }
        }

        raf.seek(lastPosition);
        tempRaf.seek(0);
        while (tempRaf.getFilePointer() < tempRaf.length()) {
            raf.writeUTF(tempRaf.readUTF());
            raf.writeInt(tempRaf.readInt());
            raf.writeUTF("\n");
        }

    } catch (IOException e) {
        System.out.println("Error saving suppliers to file: " + e.getMessage());
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
    
}

    }
public String getsMaxRevenue(){
          String fileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.dat";

        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(fileName))) {
            while (inputStream.available() > 0) {
                String supplierName = inputStream.readUTF();
                Supplier sup=new Supplier(supplierName);
                int contactInfo = inputStream.readInt();
                String newLine = inputStream.readUTF();  
                if(maxR<sup.getSTotalRevenue()){
                maxR=sup.getSTotalRevenue();
                supWithMaxR=sup.getSName();
                }
            }
        } catch (IOException e) {
            System.out.println("Error displaying suppliers from file: " + e.getMessage());
        }
    return supWithMaxR;
//return maxR;
}
public String getsMaxOrders(){
          String fileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.dat";

        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(fileName))) {
            while (inputStream.available() > 0) {
                String supplierName = inputStream.readUTF();
                Supplier sup=new Supplier(supplierName);
                int contactInfo = inputStream.readInt();
                String newLine = inputStream.readUTF();  
                if(maxO<sup.getNumberOfOrders()){
                maxO=sup.getNumberOfOrders();
                supWithMaxO=sup.getSName();
                }
            }
        } catch (IOException e) {
            System.out.println("Error displaying suppliers from file: " + e.getMessage());
        }
    return supWithMaxO;
//return maxR;
}
  
   public void editSupplier(Supplier sup,int newContactInfo){
   sup.setScontactInfo(newContactInfo);
  // sup.setSTotalRevenue(sup.getSTotalRevenue());
  // sup.setNumOfOrders( sup.getNumberOfOrders());
   sup.edit();
   sup.saveSupplierInfo();
   saveSuppliersToFile(sup);
   
   }

    

   }

  

    
    
    
 
    

