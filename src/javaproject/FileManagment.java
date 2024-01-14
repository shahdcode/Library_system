package javaproject;
import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.util.Date;
import java.io.*;

public class FileManagment implements Serializable {
    //member fields
    private User user;
    private Book book;
    private static final String FILE_PATH = "AllBorrowers.dat";
    private String name;
    private String password;
    private static List<Book> borrowedBooks;
    private  static ArrayList<Book> borrowHistory= new ArrayList<>(); //3ayzha total and not per user 
    private int fine=10;//perDay
    //constructor 
 public FileManagment() {
    
    }
    public FileManagment(Book book) {
        this.book = book;
    }
    
    public FileManagment(Borrower borrrower, Book book){
        this.user=user;
        this.book=book;
}

    
//    public Borrower_Mgn(){
////        this.user=user;
//        this.borrowDate=new Date(); //initilizing the borrow date to the current date
//       
//        //set due date for returning the book
//        Calendar c=Calendar.getInstance();
//        c.setTime(borrowDate);
//        c.add(Calendar.MINUTE, 1);
//        this.dueDate=c.getTime();
//    }
    
    //getter functions, no need lel setters 

    public static List<Book> getBorrowHistory() {
        return borrowHistory;
    }

    
  
  
       public void addBorrowedBook(Book book) {
        this.borrowedBooks.add(book);
    }
    
    public User getUser() {
        return user;
    }

    

//  display borrowings
    public void  viewBorrowingHistory(User user){
      for(Book b:borrowHistory ){
          System.out.println(b); 
      }
  }
    
 
    //override toString to display el object
    @Override
    public String toString(){
        return "Name: "+user.getName()+" Book: "+book.getTitle()+"DueDate: ";
    }



   


       private static  ArrayList<Book> info_history= new ArrayList<>();
        public static void printToFile(Borrower borrower) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH, false))) {

            // Write the Borrower object
            objectOutputStream.writeUTF(borrower.getName());

            // Write the BorrowedBooks list
            List<Book> borrowedBooks = borrower.getBorrowedBooks();
            for (Book book : borrowedBooks) {
                objectOutputStream.writeObject(book);
                
            }

            System.out.println("Data written to file in binary format.");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
   
 public List<User> readFromFile() {
    List<User> users = new ArrayList<>(); 

    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
        while (true) {
            try {
                
                String borrowerName = objectInputStream.readUTF();
                System.out.println("Borrower: " + borrowerName);

                User user = new User(); 
                user.setName(borrowerName);  

                while (true) {
                    try {
                        Book book = (Book) objectInputStream.readObject();
                        System.out.println(book);
                       info_history.add(book);
                        user.getBorrowedBooks().add(book); 
                    } catch (EOFException e) {
                        break; 
                    }
                }

                users.add(user); 

            } catch (EOFException e) {
                break; 
            }
        }

        System.out.println("Data read from file in binary format.");
    } catch (IOException | ClassNotFoundException e) {
        System.out.println(e);
    }

    return users;  
}

  
        public static void printAllToFile(String borrower) {
      //  borrower.setName("sara");
      try{
        PrintWriter p = new PrintWriter(new FileWriter("Borrowers_ALL.txt", true));
 
         p.println(borrower);
         for(Book book: info_history){
           p.println(book);
          
         }
         p.println("Total borrowed: "+Borrower.getBorrow_count());
         p.println();
            p.close();
             info_history.clear();
      }catch(IOException e){
          System.out.println(e);
      }
    }
       //display all
   public static void readFromFileAll() {
        try (BufferedReader br = new BufferedReader(new FileReader("Borrowers_ALL.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
  




}