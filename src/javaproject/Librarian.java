package javaproject;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 *
 * @author user
 */
public class Librarian extends User { 
  //  private ArrayList<User> librarians;
    private static ArrayList<Librarian> librarians;
    private String userName;
    private String password;

   // Librarian library=new Librarian();
  //  private Object librarian;
     public Librarian(){
        
    }

    public Librarian(String userName, String password) {
        super(userName,password);
        //this.librarians = librarians;
        this.userName = userName;
        this.password = password;
        
//        this.librarian = librarian;
//        this.writer = writer;
         try {
            this.writer = new FileWriter("librarians.txt",true);
        } catch (IOException e) {
             System.out.println(e);
        }
    
    librarians=new ArrayList<>();
    }
    
   
    
    
//    public Librarian(String userName) {
//        this.setUserName(userName);
//        this.setPassword(password);
//        this.setContactInfo(contactInfo);
//       
//    }
//   

      public static FileWriter writer;

    public void setLibrarians(ArrayList<Librarian> librarians) {
        this.librarians = librarians;
    }
      
      
    
//    public void setLibrarians(ArrayList<Librarian> librarians) {
//        this.librarians = librarians;
//    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName(){
    return this.userName;
    }
   

    public static ArrayList<Librarian> getLibrarians() {
        return librarians;
    }
    
    
    
    

     public static void saveToFile(){
          try{
             
              PrintWriter print=new PrintWriter("librarians.txt");
              
              for(User user:librarians){
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

    
 
    public void addLibrarian( Librarian newLibrarian){
        librarians.add(newLibrarian);
        
        saveToFile();
    }
    
   
    public static void listLibrarians(){
     if(writer!=null){
    ArrayList<Librarian> librarians=Librarian.getLibrarians();
    int i=1;
    for(Librarian librarian:librarians){
    System.out.println("librarian " + (i+1) +": "+librarian.getUserName());
    
    i++;
    }
        }
     else 
         System.out.println("file is empty");
        
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

     
          public static void removeLibrarian(String userName){
         if(userName==null){
             System.out.println("Invalid input entered.");
             return;
         }
         
         ArrayList<Librarian> librarians =Librarian.getLibrarians();
         if(librarians==null){
             System.out.println("There are no librarians in the system to remove");
             return;
         }
         
        for (int i=0;i<librarians.size();i++) {
            Librarian librarian=librarians.get(i);
            if(librarian!=null){
            String libUserName=librarian.getUserName();
            if(libUserName !=null && libUserName.equals(userName)){
            librarians.remove(i);
            System.out.println("The librarian "+ userName +" has been removed.");
            return;
            }
            }
        }
        saveToFile();
        System.out.println("The librarian you're looking for is not registered in the system.");
    }

   
     
    
    public static void editLibrarian(Librarian editedLibrarian){
        ArrayList<Librarian> librarians=Librarian.getLibrarians();
        for(int i=0;i<librarians.size();i++){
            Librarian librarian=librarians.get(i);
            if(librarian.getUserName().equals(editedLibrarian.getUserName())){
                librarians.set(i,editedLibrarian);
                  saveToFile();
                  System.out.println("the librarian "+editedLibrarian.getUserName()+" has been updated");
                  return;
            }
        }
                      
    }

   
    
    public static int getNumOfBorrowings(){
        Borrower B=new Borrower();
       return B.getNumberOfBorrowings();
    }


   
   
   
    public static void getLibrarianWithMaxRev(){
            User maxRevLibrarian= null;
            double maxRev=0;
            for(int i=0;i<librarians.size();i++){
                User librarian=librarians.get(i);
             if(Admin.getTotalRevenue()>maxRev){ //ask shahd
                 maxRev=Admin.getTotalRevenue();
                 maxRevLibrarian=librarian;
             }
            }
            System.out.println("Librarian with max revenue: "+maxRevLibrarian);
       }



    public static void getLibrarianWithMaxNumOfBorrowings(){
        User maxLibBorrowings=null;
        int maxBorrowings=0;
        for(User user:librarians){
            if(user instanceof Borrower){
                Borrower borrower=(Borrower)user;
                int borrowings=borrower.getNumberOfBorrowings();
                if(borrowings>maxBorrowings){
                    maxBorrowings=borrowings;
                    maxLibBorrowings=user;
                }
            }
        }
        System.out.println("Librarian with max number of borrowings: "+maxLibBorrowings);
    }
    public static void listBooksLibrarian(){
        Book.listBooks();
    }
    
    
    //mariam needs this
    private static ArrayList<ArrayList<Object>> newOrder = new ArrayList<>();//array to store librarian order 

    public static ArrayList<ArrayList<Object>> getNewOrder(){
          return newOrder;
    }

      public void addOrder(String sName) throws IOException {
          String book;
          int amount;
          int temp;
          String removedBook;
                  Scanner s = new Scanner(System.in);

              
       for(int j=0;j<Admin.getNewSupplierList().size();j++){//A loop to iterate on  all suppliers 
           if(Admin.getNewSupplierList().get(j).equals( sName)){//checking if entered supplier name exists
                 do {
                    System.out.println("1: Add a book to Your order");
                    System.out.println("2: Remove a book from Your order");
                    System.out.println("3: Place Order");
                    temp=s.nextInt();
                     Book.listBooks();//displaying books for user to choose from
                     switch (temp){         
                         case 1:{        
            System.out.print("what Book do you want to order: ");
            book = s.nextLine();
            System.out.print("how many copies do you need: ");
            amount = s.nextInt();
           ArrayList<Object> bookAdded = new ArrayList<>();
            for (int i=0;i<Book.getBooks().size();i++) {// A loop to iterate on all books availabe 
                String sValue =String.valueOf(Book.getBooks().get(i));
             if(sValue.equals(book)){//checking if entered book title exists
                
            bookAdded.add(Book.getBooks().get(i)); 
           bookAdded.add(amount);
             newOrder.add(bookAdded);//adding book and amount of copies given by user to thr order array list
             System.out.println("book added to cart successfully!");
             String fileName=sName+"_orders.txt";
             for (int count=0;count<newOrder.size();count++) {
             try (FileWriter writer = new FileWriter("C:\\Users\\maria\\Documents\\NetBeansProjects\\ LibraryMangmentSystem\\src\\librarymangmentsystem\\"+fileName,true)) {
            writer.write(newOrder.get(count).get(0) + "\t"+newOrder.get(count).get(1) + "\n");
             }catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
             }
        }
           }
           
             else{
             System.out.println("book not available");
             }
             
            }
                 }
         case 2:{
            System.out.println("which book would you like to remove from cart");
            removedBook=s.next();
              for (int i=0;i<newOrder.size();i++) {
         
           if (newOrder.get(i).get(0).equals(removedBook)) {
                newOrder.remove(newOrder.get(i));
                System.out.println("book removed sucessully");
            }
           else{
               System.out.println("book not found");
           }
        }
            
            }
                 }
              
                 }while(temp!=3);
               System.out.println("order placed successfully!");  
           } 
           else{
           System.out.println("supplier not available");
           }
      
          
      }
    }
}

