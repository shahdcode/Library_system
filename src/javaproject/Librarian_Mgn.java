package javaproject;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Scanner;



public class Librarian_Mgn  extends User{
    public static ArrayList<Book> records = new ArrayList<>();    
     private ArrayList<String>cancelarr=new ArrayList<>();
      private ArrayList<Book> cancelbooks= new ArrayList<>();
      private List<Book> books=new ArrayList<>();
      private static ArrayList<Book> allbooks=new ArrayList<>();
 private ArrayList<String> bookTitles=new ArrayList<>() ;

    public Librarian_Mgn (String Name,String password) {
        super(Name,password);
    }
     public Librarian_Mgn ()
     {
         
     }

    public static List<Book> getRecords() {
        return records;
    }
     
    public static ArrayList<Book> getAllbooks() {
     return allbooks;
 }

 public  ArrayList<String> getCancelarr() {
     return cancelarr;
 }
 
 
 public static void createBorrowing(User borrower){
     System.out.println("Borrower: "+borrower.getName());
     List<Book>borrowings=borrower.getBorrowedBooks();
     if(borrowings.isEmpty())
     {
          System.out.println("no books borrowed by this borrower");
          
     }
     else{
       System.out.println("Borrowed books:");  
       records.addAll(borrowings);  
     }
 }
    public void display()
   {
       for(Book i:getRecords())
       {
           System.out.println(i.getTitle());
       }
   }

    public void selectbyauthor(String author)
    {
        books=Book.getBooks();
         ArrayList<Book> filteredbooks=new ArrayList<>();
         for(Book i: books)
         {
             if(i.getAuthor().equals(author))
             {
                 filteredbooks.add(i);
             }
         }
         System.out.println(filteredbooks);
    }  

    public static void selectCategory() {
        try{
        System.out.println("Select a category seperated by commas (fiction,non-fiction,science) ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] categ= input.toLowerCase().split(",");
        for(String i:categ)
        {
            i=i.trim();
            ArrayList<Book> selectedBooks=new ArrayList<>();//array han7ot feh el selected categories
            for(Book b:Book.getBooks())
            {
                switch(i)
                {
                    case "fiction":
                        if("fiction".equalsIgnoreCase(b.getCategory()))
                        {
                            selectedBooks.add(b);
                           
                        }
                         break;
                          case "non-fiction":
                        if("non-fiction".equalsIgnoreCase(b.getCategory()))
                        {
                            selectedBooks.add(b);
                        } 
                           break;
                          case "science":
                        if("science".equalsIgnoreCase(b.getCategory()))
                        {
                            selectedBooks.add(b);
                        }
                         break;
                          default:
                              System.out.println("invalid category"+i);
                        
                }
                
            }
            for(Book book1:selectedBooks)
            {
                System.out.println("selected book in category "+i+" : "+ book1.getTitle());
            }
        }      
    }catch(Exception e)
    {
        System.out.println(e);
    }
    }
    
    
    private FileManagment borrowerManager;
   public void specifyBorrowingTermDetails() {
     borrowerManager.readFromFileAll();
    }
    
   
 
  
  public ArrayList<String> parseBookTitles(String input) {
   
    String[] titles = input.split(",");
    for (String title : titles) {
        bookTitles.add(title.trim());
    }
    return bookTitles;
}
public void cancelBorrowing(User borrower) {

try{
            System.out.println("Do you want to cancel any books from your cart? (Y/N)");
        Scanner s=new Scanner(System.in);
            String cancelBooksInput = s.next();
                        if (cancelBooksInput.equals("yes")) {
                System.out.println("Enter the books to cancel (seperating them by commas :");
                          
               String booksToCancel=s.next();
               cancelarr=parseBookTitles(booksToCancel);
                for(Book book:Book.getBooks())
                {
                if(cancelarr.contains(book.getTitle()))
                {
                    cancelbooks.add(book);
                }
                }
                // Remove canceled books from the cart
                borrower.removeBooksFromCart(borrower,cancelbooks);
                //list after cancellation
                  for(Book i:borrower.getBorrowedBooks())
                  {
                   System.out.println(i.getTitle());
                  }
                 //  Calculate payment after removing canceled books
                    System.out.println("Total payment: " +calculatePayment(borrower)+" $");
                borrower.clearCart();
            } else if (cancelBooksInput.equals("No")) {
                // Perform checkout without canceling books
                          System.out.println("Total payment: " +calculatePayment(borrower)+" $");

                borrower.clearCart();
                System.out.println("Checkout completed. Borrower's cart has been cleared.");
            } else {
                System.out.println("Invalid input. Checkout canceled. No books added to the borrower's record.");
            }
}catch(Exception e)
       {
           System.out.println(e);
        }
}
      private static ArrayList<Double> totalList=new ArrayList<>();
public static ArrayList<Double>gettotalpay(){
    return  totalList;
}
   public static double calculatePayment( User borrow) {
       try{
     int totalPayment = 0;
    for (Book book : borrow.getBorrowedBooks()) {
       
            totalPayment += book.getPrice();
       
    }
      writePaymentToFile(totalPayment);
    return totalPayment ;
       }catch(Exception e)
       {
           System.out.println(e);
           System.out.println("ay haga");
                   return 0;
       }
   }
    
   private static final String calcFile = "calculations.dat";
//   private static void writePaymentToFile1(double totalPayment) {
//        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(PAYMENT_FILE_PATH, true))) {
//            dataOutputStream.writeDouble(totalPayment); // Write the totalPayment as a double
//            System.out.println("Total payment written to binary file.");
//        } catch (IOException e) {
//            System.out.println("Error writing to the binary file.");
//            System.out.println(e);
//        }
//    }
   public static void writePaymentToFile(double totalPayment){
       try(DataOutputStream dos=new DataOutputStream(new FileOutputStream(calcFile ,true))){
           dos.writeDouble(totalPayment);
            System.out.println("Total payment written to binary file.");
           } catch (IOException e) {
            System.out.println("Error writing to the binary file.");
            System.out.println(e);
        }   
   }
   
   
  public static List<Double> readPaymentsFromFile() {
        List<Double> pay = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(calcFile))) {
            while (dis.available() > 0) {
                double payment = dis.readDouble();
                pay.add(payment);
            }
        } catch (IOException e) {
            System.out.println("Error reading from the binary file.");
            System.out.println(e);
        }
        return pay;
    }
  
}