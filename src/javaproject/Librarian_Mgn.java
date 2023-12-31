package javaproject;
import java.util.Scanner;
//import java.util.List;
import java.util.ArrayList;

public class Librarian_Mgn {
  //  private Borrower currentBorrowing;
  //  private Borrower borrower;
    private static ArrayList<Book> allbooks=new ArrayList<>();
   private  ArrayList<Book> books=new ArrayList<>();
    private  ArrayList<String> cancelarr = new ArrayList<>();// per user, me4 static
    private ArrayList<Book> cancelbooks=new ArrayList<>();
    private ArrayList<String> bookTitles = new ArrayList<String>();
    private ArrayList<Borrower_Mgn> records=new ArrayList<>();

    public Librarian_Mgn() {
    }
public Librarian_Mgn(String Name,String password)
  {
    super(Name.password);
  }

    public static ArrayList<Book> getAllbooks() {
        return allbooks;
    }

    public  ArrayList<String> getCancelarr() {
        return cancelarr;
    }
    

    // public static ArrayList<Book> getBook2() {
    //     return book2;
    // }
    
    
    
  // private static ArrayList<Book> books=new ArrayList<>();
//        public double createBorrowing( String category) 
//       {
//       ArrayList<Book> borrowedBooks = new ArrayList<>();
//     double totalPayment = 0.0;
//     for (Book i : book2) {
//         if (i.getCategory().equals(category)) {
//           System.out.println(i.getTitle());
//            System.out.println("Do you want to borrow " + i.getTitle() + "? (yes/no)");
//             Scanner scanner = new Scanner(System.in);
//           String answer = scanner.nextLine();
//            if (answer.equals("yes")) {
//               borrowedBooks.add(i);
//                 totalPayment += i.getPrice();
//             }
//        }
//     }
//     System.out.println("You have successfully borrowed " + borrowedBooks.size() + " books from the " + category + " category.");
//     System.out.println("The total payment for the borrowed books is $" + totalPayment + ".");
//     return totalPayment;

// }
   public void createBorrowing(Borrower borrower,Book book)
    {
        Borrower_Mgn record=new Borrower_Mgn(borrower,book);
        records.add(record);
        
        
    }
  public void display()
   {
       for(Borrower_Mgn i:records)
       {
           System.out.println(i);
       }
   }
    public static void selectCategory() {
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

//  public void specifyBorrowingTermDetails(User user) {
//       int totalfine=0;
//       for(Borrower_Mgn i:records)
//       {  
//           if(i.getUser().equals(user))
//           {
//           totalfine+=i.CalculateFine(); 
//           }
//       }
//       System.out.println( totalfine);
//}
 
   public static double calculatePayment( User borrow) {
     try{
     int totalPayment = 0;
    for (Book book : borrow.getBorrowedBooks()) {
       
            totalPayment += book.getPrice();
       
    }
    return totalPayment;   
        }catch(Exception e)
       {
           System.out.println(e);
                   return 0;
       }
}
   
   public ArrayList<String> parseBookTitles(String input) {
    String[] titles = input.split(",");
    for (String title : titles) {
        bookTitles.add(title.trim());
    }
    return bookTitles;
}
public void cancelBorrowing(Borrower borrower) {
  try{
 System.out.println("Do you want to cancel any books from your cart? (Y/N)");
        Scanner s=new Scanner(System.in);
            String cancelBooksInput = s.next();
                        if (cancelBooksInput.equals("yes")) {
                System.out.println("Enter the books to cancel (seperating them by commas :");
                // Assuming you have a method to read user input, you can replace the line below with your actual implementation
                String booksToCancel = s.next();
                 cancelarr = parseBookTitles(booksToCancel);
                
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

}
