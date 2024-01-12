package javaproject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

 public  class Borrower extends User implements Serializable  {
     private LocalDateTime borrowedDate;
     private LocalDateTime returnedDate;
     private boolean isBorrowed;
private static int borrow_count=0; 
 private  static ArrayList<String> names = new ArrayList<>(); //stores names of borrowers
 private String BorrowerName;
 private ArrayList<Book> BorrowedBooks; //names of borrowed books

 //zaina needs this
  //private List<Book> booksToCancel;
 //private Date BorrowedDate;

 File file_all=new File("AllBorrowers.dat");
 
 
public ArrayList<String> getnames (){
    return names;
}


    public ArrayList<Book> getBorrowedBooks() {
        return BorrowedBooks;
    }
 
    public boolean isIsBorrowed() {
        return isBorrowed;
    }

    public void setIsBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

//    public Borrower(String name) {
//        super(name);
//        this.BorrowedBooks=new ArrayList<>();
//        
//    }
    
    public Borrower(String Name, String pass) {
        super(Name, pass);
        BorrowerName=Name;
        this.BorrowedBooks=new ArrayList<>();
        
        
        
    }
    
    
    public String getBorrowerName(){
        return BorrowerName;
    }
    

    public Borrower() {
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public LocalDateTime getBorrowedDate() {
        return borrowedDate;
    }

    public LocalDateTime getReturnedDate() {
        return returnedDate;
    }

    public static int getBorrow_count() {
        return borrow_count;
    }

    public static void setBorrow_count(int borrow_count) {
        borrow_count = borrow_count; 
    }

    
    
public void addTo(String name, String pass){
 User newUser = new Borrower(name,pass);
    users.add(newUser);
    //names.add(newUser.getName());
    printtofile();
   
}




    public void editName(String newName){
        setName(newName);
        addTo(newName,getPass());
        display();
        
    }
    
    public Boolean search(String key){
        for(int i=0;i<names.size();i++){
            if(key.equals(names.get(i))){
                return true;
            }
        }
        return false;
    }
    
    
    public  void returnBook(Book book){
   Borrower_Mgn history=new  Borrower_Mgn(book);
      book.decreaseCopiesCount();
      this.returnedDate=LocalDateTime.now();
       DateReturned();
       printtofile();
       setIsBorrowed(false);
      // BorrowedBooks.remove(book);
    }
    
      public Date DateBorrowed(){
        Date date= new Date();
        System.out.println("Date & time of borrowing" +date);
        return date;
    }
  
    public  Date DateReturned(){
       
        Date date= new Date();
        System.out.println("Date & time returned" +date);
        return date;
        
    }
    
    @Override
    public void remove(){//removes user from borrowing list
       for(int i=0;i<users.size();i++){
           if(users.get(i).getBorrowedBooks().isEmpty()){
               users.remove(i);
               names.remove(i);
               printtofile();
               System.out.println("User returned all borrowed Books");
          return;
           }
       }
        
    }
    
    
     //@Override
    public void display(){
      printtofile();
try(BufferedReader  reader= new BufferedReader(new FileReader(file_all)) ){
    String line;
    while((line=reader.readLine())!=null){
        System.out.println(line);
    }
}catch(IOException e){
    System.out.println(e);
}
// me4 me7taga a7ot filenotfoundexception, le2n its a subclass of IOexception
    }
    
    public void displayAllBorrowers(){
     printtofile();
try(BufferedReader  reader= new BufferedReader(new FileReader(file_all)) ){
    String line;
    while((line=reader.readLine())!=null){
        System.out.println(line);
    }
}catch(IOException e){
    System.out.println(e);
}
// me4 me7taga a7ot filenotfoundexception, le2n its a subclass of IOexception
    }
    

    
    
    @Override
    public void borrowBook(Book book){
     //Book book=Book.searchBook(title);
    if (book!=null) {
        //make sure copies are avaialable
        if(book.getCopiesCount()>3){ //mafrod tet7ded by the supplier
            System.out.println("All copies of the book are currently  not avaiable, please try again later");   
             return;
        }
       setIsBorrowed(true);
       borrow_count=borrow_count+1;
       BorrowedBooks.add(book); //Stored in the borrowedBooks
       Borrower_Mgn.getLoanHistory().add(book); //stores the history 
      Librarian_Mgn.getAllbooks().add(book);
       book.increaseCopiesCount();
       
        //this.reservations.add(book);
        System.out.println("Book borrowed successfully.");
        this.borrowedDate=LocalDateTime.now();
        DateBorrowed();
      //  printtofile();
      Borrower_Mgn.printToFile(this);
    }
    }
    
   
    
  
  public void ListBorrowed(){
      for(int i=0;i<BorrowedBooks.size();i++){
          System.out.println(BorrowedBooks.get(i));
      }
  }
  
  @Override
    public void writeRatingsToFile(Book book,int rate) {
        //Book book=Book.searchBook(title);
        if(book!=null){
           try (PrintWriter writer = new PrintWriter(new FileWriter(book.getTitle() + "_ratings.txt",true))) {
        
            writer.print(Integer.toString(rate));
            writer.println();
        
    } catch (IOException e) {
               System.out.println(e);
    } 
        }else{
            System.out.println("Book not found");
        }
    
}
    
   @Override
public void rateBook(Book book, int rating){
   
   if(book == null){
       System.out.println("Book is currently unavailable");
   } else {
       
       book.setisRated(true);
       System.out.println("You rated the book " + rating + ". Its average rating is now " + book.getAverageRating());
      writeRatingsToFile(book,rating);
      displayAllRatings( book);
   }
}

 public void displayAllRatings(Book book){
      printtofile();
try(Scanner  reader= new Scanner(new FileReader(book.getTitle() + "_ratings.txt")) ){
    int line;
    while(reader.hasNext()){
        line=reader.nextInt();
        book.addRating(line);
        System.out.println(line);
    }
}catch(IOException e){
    System.out.println(e);
}
// me4 me7taga a7ot filenotfoundexception, le2n its a subclass of IOexception
    }



public void clearCart()
 {
     BorrowedBooks.clear();
 }



public User getBorrowerWithMaxBorrowings(){
    User maxBorrower=null; //maynfa34 ayot int f object
int maxBorrowings=0;
for(User user:users){
    if(user instanceof Borrower){
     int borrowings=getNumberOfBorrowings();
     if(borrowings>maxBorrowings){
        maxBorrowings=borrowings;
        maxBorrower=user;
      } 
    }
    
}
return maxBorrower;
}

public void numberOfBorrowperUser(){
     try (PrintWriter writer = new PrintWriter(new FileWriter("No_of_borrows.txt",true))) {
            writer.print(Integer.toString(getBorrow_count()));
            writer.println();
            writer.close();
     } catch(IOException e){
         System.out.println("Cant write to file");
     }
}

public static void removeBooksFromCart(Borrower borrower, List<Book> booksToCancel) {
    for (Book i : booksToCancel) {
        borrower.BorrowedBooks.remove(i);
    }
}   
    
  public  List<User> readFromFile() {
       List<User> usr=new ArrayList<User>();//hy store el borrowers mn el file
        try (BufferedReader reader = new BufferedReader(new FileReader(file_all))) {
            String line;
            boolean readingBooks = false;  // Flag to indicate when to start reading book titles
            int index=-1;
            
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("User Name: ")) {                  
                    String[] userInfo = line.substring("User Name: ".length()).split(" ");                 
                    String currentUserName = userInfo[0];
                    String currentUserPass = userInfo[1];
                     User br=new Borrower(currentUserName,currentUserPass);
                     usr.add(br);
                     index++;
                } else if (line.equals("Borrowed Books:")) {
                    readingBooks = true;  // Start reading book titles
                      
                    
                } else if (readingBooks && !line.isEmpty()) {
                    // Store the borrowed book with its associated username
                    
                   ///////////////////////////////////////////// usr.get(index).borrowBook(line);
                   
                }
                else if(line.isEmpty())
                {
                     readingBooks = false; 
                }
            }       
            
        } catch (IOException e) {
            System.out.println(e);
        }
    return usr;
   }  
    
  
//public User getBorrowerWithMaxRevenue(){
//    User maxRevenueBorrower = null;
//    int maxRevenue = 0;
//    for (User user : users) {
//        int revenue = user.calculateTotalFine();
//        if (revenue > maxRevenue) {
//            maxRevenue = revenue;
//            maxRevenueBorrower = user;
//        }
//    }
//    return maxRevenueBorrower;
//}

@Override
public String toString() {
    return "Borrower [name=" + getnames() + ", borrowedBooks=" + BorrowedBooks + "]";
}

 }



