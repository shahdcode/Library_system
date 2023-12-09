package javaproject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

/**
 *
 * @author zaina
 */
public  class Admin extends User{
     private List<Borrower_Mgn> records=new ArrayList<>(); 
private static ArrayList<Supplier> suppliers = new ArrayList<>();
     private static ArrayList<Admin> admins = new ArrayList<>();
       private static ArrayList<Librarian> librarians;
       private ArrayList<Book> books;
       private String pass;
       private String name;
       private double revenue;
    private File file=new File("admins.txt");
   private File file1=new File("librarians.txt");
    public void addAdmin(User ad) {
        Admin add=new Admin(ad.getName(),ad.getPass());
       admins.add(add);
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

   
    public void saveAllAdminsToFile() throws IOException {
      for (int i=0;i<admins.size();i++) {
        String fileName = "admins.txt";
        try (FileWriter writer = new FileWriter("C:\\Users\\zaina\\OneDrive\\Documents\\NetBeansProjects\\JavaApplication13\\"+fileName,true)) {
            writer.write(admins.get(i).getName()+ " "+admins.get(i).getPass() + "\n");
} 
        catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());}
          }
}
 public static void saveToFile(){
          try{
             
              PrintWriter print=new PrintWriter("librarians.txt");
              
              for(User user:librarians){
                  if( user instanceof Librarian){
                Librarian librarian=(Librarian)user;
                  print.println(librarian.getUserName()+" "+librarian.getPassword());
              }
              }
              print.close();
          }
          catch(IOException e){
             System.out.println(e);
          }
      }

    
 
    public void addLibrarian( Librarian newLibrarian){
        librarians.add(newLibrarian);
        
        saveToFile();
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
      
   public Admin(String name,String id)
   {
        super(name, id);
         this.name = name;
        this.pass = id;
         this.revenue = 0.0;
    }
  

   
   
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

public ArrayList<Admin> readfromfileadmin()
{
          ArrayList<Admin> admin=new ArrayList<Admin>();//hy store el borrowers mn el file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean readingBooks = false;  // Flag to indicate when to start reading book titles
            int index=-1;
            
            while ((line = reader.readLine()) != null) {                    
                    String[] userInfo = line.split(" ");                 
                    String currentUserName = userInfo[0];
                    String currentUserPass = userInfo[1];
                     Admin br=new Admin(currentUserName,currentUserPass);
                     admin.add(br);
                     index++;                
            }       
            
        } catch (IOException e) {
            System.out.println(e);
        }
    return admin;
   
}
public ArrayList<Librarian> readfromfilelibrarian()
{
          ArrayList<Librarian> lib=new ArrayList<Librarian>();//hy store el borrowers mn el file
        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            String line;
            boolean readingBooks = false; 
            int index=-1;
            
            while ((line = reader.readLine()) != null) {                    
                    String[] userInfo = line.split(" ");                 
                    String currentUserName = userInfo[0];
                    String currentUserPass = userInfo[1];
                     Librarian br=new Librarian(currentUserName,currentUserPass);
                     lib.add(br);
                     index++;                
            }       
            
        } catch (IOException e) {
            System.out.println(e);
        }
    return lib;
   
}
public void editadmin( String old, String field, String newvalue) {
    admins=readfromfileadmin();
    for (Admin i : admins) {
       if (i.getName().equals(old)) {
         
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

        
    public void displayOrders(String supplierName) {
        String file=supplierName+"_Orders.txt";
        Supplier supplier= new Supplier(supplierName) ;
       if(searchFile("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\",file)){
            supplier.displayOrders();
        } else {
            System.out.println("Supplier doesnt have orders.");
        }
    }
    public List<User> searchUsers(String field, String value) {
        admins=readfromfileadmin();
        librarians=readfromfilelibrarian();
        Scanner input=new Scanner(System.in);
        System.out.println("what user do you want to search for 1)admin 2)librarian :");
        int no=input.nextInt();           
       List<User> results = new ArrayList<>();
        switch(no)
        {
            case 1:
                       if ("id".equals(field)) {
           for (Admin U : admins) {
                if (U. getPass().equals(value)) {
                   results.add(U);
                }
           }
        } else if ("name".equals(field)) {
            for (Admin U : admins) {
                if (U.getName().equals(value)) {
                    results.add(U);
                }
            }
        }
        break;
      
            case 2:
          if ("id".equals(field)) {
           for (Librarian U : librarians) {
                if (U. getPass().equals(value)) {
                   results.add(U);
                }
           }
        } else if ("name".equals(field)) {
            for (Librarian U : librarians) {
                if (U.getName().equals(value)) {
                    results.add(U);
                }
            }
        }
       break;
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
   public boolean searchNameInFile(String fileName, String targetName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+"); 
                if (parts.length >= 1 && parts[0].equals(targetName)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return false;
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
   public void addOrder(String supplierName, String bookTitle, int numberOfCopies) {
        Supplier supplier = new Supplier(supplierName);
       
        String FileName = "C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.txt";
        if (searchNameInFile(FileName, supplierName)) {
            String fileName = supplierName + "_Orders.txt";
            try (FileWriter writer = new FileWriter("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\" + fileName, true)) {

                writer.write("Book:" + bookTitle + "\tCopies:" + numberOfCopies + "\n");

            } catch (IOException e) {
                System.out.println("Error saving suppliers to file: " + e.getMessage());
            }
        } else {
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
    }
    }
       public void removeSupplier(String name) {
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
     public void editSupplier(String currentName,  String newPassword) {
        searchNameInFile("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.txt",currentName);
        if (searchNameInFile("C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\Suppliers.txt",currentName)) {

            editSupplierFileContents(currentName,newPassword);
            System.out.println("Supplier edited successfully.");
        } else {
            System.out.println("Supplier not found.");
        }
    }
}

   


