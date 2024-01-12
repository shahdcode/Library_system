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
 public void displaySuppliers() {
           String FileName = "Suppliers.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + FileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
         
    }
  public void displaySupplierInfo(String name) {
      
           String FileName = name+"_Info.txt";
           
       if(searchFile("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\",FileName)){
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + FileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }}else{            System.out.println("Supplier not found.");
}
         
    }
        
    public void displayOrders(String supplierName) {
        String file=supplierName+"_Orders.txt";
        Supplier supplier= new Supplier(supplierName) ;
       if(searchFile("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\",file)){
            supplier.displayOrders();
        } else {
            System.out.println("Supplier doesnt have orders.");
        }
    }
      public void displayPrices(String supplierName) {
        String file=supplierName+"_Prices.txt";
        Supplier supplier= new Supplier(supplierName) ;
       if(searchFile("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\",file)){
            supplier.displayPrices();
        }
    }
         public boolean isAvailable(String supplierName) {
        String file=supplierName+"_Prices.txt";
        boolean found =false;
       if(searchFile("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\",file)){
        found=true;
       } 
       return found;
    }
         
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
      public static boolean searchSupplier(String sName) {
         for (int i=0;i<newSupplierList.size();i++) {
         
           if (newSupplierList.get(i).get(0).equals(sName))
        {
                return true;
            }
        }
        return false;
    }
   public void addOrder(String supplierName, String bookTitle, int numberOfCopies) throws IOException {
        Supplier supplier = new Supplier(supplierName);
       
        String FileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.txt";
        if (searchNameInFile(FileName, supplierName)) {
           
            String fileName = supplierName + "_Orders.txt";
            try (FileWriter writer = new FileWriter("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + fileName, true)) {

                writer.write("Book:" + bookTitle + "\tCopies:" + numberOfCopies + "\n");

            } catch (IOException e) {
                System.out.println("Error saving suppliers to file: " + e.getMessage());
            }} else {
            System.out.println("Supplier not found.");
        }
        supplier.receiveOrder(bookTitle, numberOfCopies);
    }
    public void cancelOrder(String title,String name) throws IOException  {

        ArrayList<String> updatedLines = new ArrayList<>();
        boolean orderRemoved = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + name+"_Orders.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line contains the word to remove
                if (line.contains( title)) {
                    // Remove the word from the line
                    line = line.replace( line,"").trim();
                    orderRemoved = true;
                }
                updatedLines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        if (orderRemoved) {
            try (FileWriter writer = new FileWriter("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + name+"_Orders.txt")) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine + System.lineSeparator());
                }
                System.out.println("Order removed successfully");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } else {
            System.out.println("Order not found");
        }
    } public void editSupplierFileContents(String name,String Password) {
        // Implement the logic to edit file contents
        Scanner sr = new Scanner(System.in);
        Supplier newSupplier = new Supplier(name,Password);
              int editValue;
              
              String newName;

        do{
             System.out.println("1:edit name.\n2:edit password\n0:Exit");
             editValue=sr.nextInt();
      if(editValue==1){
             System.out.println("enter new name");
            newName=sr.next();
            newSupplier.setSName(newName);
        try {
       
            File inputFile = new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.txt");
            File tempFile = new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\tempFile.txt");
          
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // Check if the line contains the search text
                if (currentLine.contains(name)) {
                    // Modify the line to replace oldName with newName
                    currentLine = currentLine.replace(name, newName);
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            writer.close();
            reader.close();
               if (inputFile.delete()) {
           
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Error renaming the file.");
                }
            } else {
                System.out.println("Error deleting the original file.");
            }}catch (IOException e) {
            System.out.println("Error reading or writing to the file: " + e.getMessage());
        }
         
        try{
            File infoFile= new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+name+"_Info.txt");
            File infoNew= new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+newName+"_Info.txt");
            File tempInfo = new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\tempInfo.txt");
            
            BufferedReader reader0 = new BufferedReader(new FileReader(infoFile));
            BufferedWriter writer0 = new BufferedWriter(new FileWriter(tempInfo));

            String currentLine0;

            while ((currentLine0 = reader0.readLine()) != null) {
            if (currentLine0.contains("Name:")) {
                    // Get the word next to "Name:" until the next space
                    String[] parts = currentLine0.split("\\s+");
                    if (parts.length > 1) {
                        // Replace the word next to "Name:" with the new name
                        currentLine0= currentLine0.replace(parts[1], newName);
                       newSupplier.setSName(newName);
                    }
                }
                writer0.write(currentLine0 + System.getProperty("line.separator"));
            }

            writer0.close();
            reader0.close();
             tempInfo.renameTo(infoNew); 
               if (!infoFile.delete()&&infoNew.delete()) {
               System.out.println("Error deleting the original file.");

                }
          }catch (IOException e) {
            System.out.println("Error reading or writing to the file: " + e.getMessage());
        }

        File pricesFile= new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+name+"_Prices.txt");
    
        if (pricesFile.delete()) {
            newSupplier.settingPrices();
        }else{ System.out.println("Error deleting file");}
          try{
           File ordersFile= new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+name+"_Orders.txt");
            File ordersNew= new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+newName+"_Orders.txt");
            File tempOrders = new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\tempOrders.txt");

            BufferedReader reader3 = new BufferedReader(new FileReader(ordersFile));
            BufferedWriter writer3 = new BufferedWriter(new FileWriter(tempOrders));

            String currentLine3;

            while ((currentLine3= reader3.readLine()) != null) {
             
                writer3.write(currentLine3 + System.getProperty("line.separator"));
            }

            writer3.close();
            reader3.close();
            tempOrders.renameTo(ordersNew);
                  if (!ordersFile.delete()&& ordersNew.delete()) {
           System.out.println("Error deleting the original file.");
                
            } }catch (IOException e) {
            System.out.println("Error reading or writing to the file: " + e.getMessage());
        }

        
    
       }
         
         else  if(editValue==2){        
          System.out.println("enter new pass");
            String newpass=sr.next();
              newSupplier.setSPassword(newpass);
              try {
            
            File inputFile = new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+newSupplier.getSName()+"_Info.txt");
            File tempFile = new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\tempFile.txt");

                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // Check if the line contains the search keyword
                if (currentLine.contains("Password:")) {
                    // Get the word next to "Password:" until the next space
                    String[] parts = currentLine.split("\\s+");
                    if (parts.length > 1) {
                        // Replace the word next to "Password:" with the new password
                        currentLine = currentLine.replace(parts[1], newpass);
                    }
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            writer.close();
            reader.close();

            // Delete the original file
            if (inputFile.delete()) {
                // Rename the temporary file to the original file name
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Error renaming the file.");
                }
            } else {
                System.out.println("Error deleting the original file.");
            }

            System.out.println("Password changed successfully.");

        } catch (IOException e) {
            System.out.println("Error reading or writing to the file: " + e.getMessage());}
        }
         else    if(editValue==0){
                   System.out.println("exiting");}

                    
             else{
                    System.out.println("invalid input");
                    
            }}while(editValue!=0);}
     public  void deleteSupplierFiles(String name) {
        // Implement the logic to delete files associated with the supplier
         Supplier supplier=new Supplier(name);
        File infoFile = new File( "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+name +"_Info.txt");
        File ordersFile = new File( "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+name +"_Orders.txt");
        File pricesFile = new File( "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+name +"_Prices.txt");
        
        if (infoFile.exists()) {
            infoFile.delete();
        }
        if (ordersFile.exists()) {
            ordersFile.delete();
        }
        if (pricesFile.exists()) {
            pricesFile.delete();
        }
       try {
           if(searchNameInFile("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.txt",supplier.getSName())) {
               
               String filePath = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.txt";
               
               
               try {
                   File inputFile = new File(filePath);
                   File tempFile = new File("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\tempFile.txt");
                   
                   BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                   BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                   
                   String currentLine;
                   
                   while ((currentLine = reader.readLine()) != null) {
                       // Check if the line contains the search text
                       if (currentLine.contains(name)) {
                           continue; // Skip the line
                       }
                       writer.write(currentLine + System.getProperty("line.separator"));
                   }
                   writer.close();
                   reader.close();
                   
                   // Delete the original file
                   if (inputFile.delete()) {
                       // Rename the temporary file to the original file name
                       if (!tempFile.renameTo(inputFile)) {
                           System.out.println("Error renaming the file.");
                       }
                   } else {
                       System.out.println("Error deleting the original file.");
                   }
                   ;
                   
               } catch (IOException e) {
                   System.out.println("Error reading or writing to the file: " + e.getMessage());
               }
           }  } catch (IOException ex) {
           Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
       }
}

       

    public void saveSuppliersToFile() {
           
        try (FileWriter writer = new FileWriter("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.txt",true)) {
        
            for (Supplier supplier : suppliers) {
                 if(searchNameInFile("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.txt",supplier.getSName())) {      
       }
                 else{
                writer.write(supplier.getSName() +"\n");}
                
            }
        } catch (IOException e) {
            System.out.println("Error saving suppliers to file: " + e.getMessage());
        }
    }

   Scanner input=new Scanner(System.in);
    public void addSupplier(String name, String password) {
        Supplier supplier=new Supplier(name,password);
        supplier.settingPrices();
       try {
           if(searchNameInFile("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.txt",name)) {
               System.out.println("Supplier already exist");
           }
           else{
               Supplier newSupplier = new Supplier(name, password);
               suppliers.add(newSupplier);
               saveSuppliersToFile();
               supplier.setSName(name);
               supplier.setSPassword(password);
               supplier.saveSupplierInfo();
               System.out.println("Supplier added successfully!");
               
           }  } catch (IOException ex) {
           Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
       public void removeSupplier(String name) throws IOException {
         Supplier newSupplier = new Supplier(name);
       String FileName="Suppliers.txt";
        if (searchNameInFile("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\"+FileName,name)) {
            //
            suppliers.remove(newSupplier);
            saveSuppliersToFile();
            // Delete the corresponding files
            deleteSupplierFiles(name);
            System.out.println("Supplier removed successfully.");
        } else {
            System.out.println("Supplier not found.");
        }
    }
      

   public void SmaxRevenue(){
  
      double max=0;
           String filePath = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.txt"; 

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            
             String line = null;
           Supplier ssupplier= new Supplier(line);
            while ((line = reader.readLine()) != null) {
           ssupplier= new Supplier(line);
              if(ssupplier.getSTotalRevenue()>max){     
              max=ssupplier.getSTotalRevenue();
              }
            }System.out.println(ssupplier.getSName());
             
            
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());}
             


   
}
     public void SmaxOrders(){
  
      int max=0;
           String filePath = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.txt"; 

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            
             String line = null;
           Supplier ssupplier= new Supplier(line);
            while ((line = reader.readLine()) != null) {
           ssupplier= new Supplier(line);
              if(ssupplier.getNumberOfOrders()>max){     
              max=ssupplier.getNumberOfOrders();
              }
            }System.out.println(ssupplier.getSName());
             
            
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());}
     }

    public static ArrayList<ArrayList<String>> getNewSupplierList() {
        return newSupplierList;
    }

   

     public void editSupplier(String currentName,  String newPassword) throws IOException {
        searchNameInFile("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.txt",currentName);
        if (searchNameInFile("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.txt",currentName)) {

            editSupplierFileContents(currentName,newPassword);
            System.out.println("Supplier edited successfully.");
        } else {
            System.out.println("Supplier not found.");
        }
    }
}

