package javaproject;
import java.io.File;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.time.*;
 public  class Borrower extends User {
     private LocalDateTime borrowedDate;
     private LocalDateTime returnedDate;
    private boolean isBorrowed;
 //private ArrayList<Book> reservations = new ArrayList<>();
 private  static ArrayList<String> names = new ArrayList<>(); //stores names of borrowers
 
 private ArrayList<Book> BorrowedBooks; //names of borrowed books
 // private static  ArrayList<Book> allbooks=new ArrayList<>();
 //zaina needs this
  //private List<Book> booksToCancel;
 
 File file = new File("Borrower.txt"); //per user
 File file_all=new File("AllBorrowers.txt");
 
 
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
        this.BorrowedBooks=new ArrayList<>();
        
        
        
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

    
    
public void addTo(String name, String pass){
 User newUser = new Borrower(name,pass);
    users.add(newUser);
    //names.add(newUser.getName());
    printtofile();
    printtofileAPP();
}

    public void printtofile(){
      try (PrintWriter writer = new PrintWriter(new FileWriter(file, false))) {
           writer.println("User Name: "+this.getName()+ "\nPassword: "+this.getPass());
           writer.println("Borrowed Books: ");
       for(Book book: BorrowedBooks)
        {
          writer.println(book.getTitle());
        }
       //haseb space between users
       writer.println();
       writer.println();
    }
    catch(IOException e){
    System.out.println(e);
    }
    }
    
    public void printtofileAPP(){
      try (PrintWriter writer = new PrintWriter(new FileWriter(file_all, true))) {
           writer.println("User Name: "+this.getName()+ "\nPassword: "+this.getPass());
           writer.println("Borrowed Books: ");
       for(Book book: BorrowedBooks)
        {
          writer.println(book.getTitle());
        }
       //haseb space between users
       writer.println();
       writer.println();
    }
    catch(IOException e){
    System.out.println(e);
    }
    }
    
    //@Override
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
    
    
//    public void returnBook(Book book){
//   Borrower_Mgn history=new  Borrower_Mgn(book);
//      BorrowedBooks.remove(book);
//      book.decreaseCopiesCount();
//      setIsBorrowed(false);
//     this.returnedDate=LocalDateTime.now();
//    }
//    
    
    @Override
    public void remove(){//removes user from borrowing list
       for(int i=0;i<users.size();i++){
           if(users.get(i).getBorrowedBooks().isEmpty()){
               users.remove(i);
               names.remove(i);
               printtofile();
               printtofileAPP();
               System.out.println("User returned all borrowed Books");
          return;
           }
       }
        
    }
    
    
     //@Override
    public void display(){
      printtofile();
try(BufferedReader  reader= new BufferedReader(new FileReader(file)) ){
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
     printtofileAPP();
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
    
//    public void reserve(String title) { //me7taga hala    will be used later fel GUI
//        Book book=Book.searchBook(title);
//    if (book==null) {
//        System.out.println("Book is currently unavailable.");
//    } else if(isIsBorrowed()){
//        System.out.println("Book is currently borrowed, cannot reserve");
//    }
//    else{
//       // setIsBorrowed(true);
//        this.reservations.add(book);
//        System.out.println("Book reserved successfully.");
//    }   
//}
    
    
    
    //@Override
    public void borrowBook(String title){
     Book book=Book.searchBook(title);
    if (book!=null) {
        //make sure copies are avaialable
        if(book.getCopiesCount()>3){ //mafrod tet7ded by the supplier
            System.out.println("All copies of the book are currently  not avaiable, please try again later");   
             return;
        }
       setIsBorrowed(true);
     //  Book.getBooks().remove(book);
       BorrowedBooks.add(book); //Stored in the borrowedBooks
       Borrower_Mgn.getLoanHistory().add(book); //stores the history 
      Librarian_Mgn.getAllbooks().add(book);
       book.increaseCopiesCount();
       
        //this.reservations.add(book);
        System.out.println("Book borrowed successfully.");
        this.borrowedDate=LocalDateTime.now();
    }
    }
    
    @Override
    public int getNumberOfBorrowings(){
       return BorrowedBooks.size();  
    }
    
  
  public void ListBorrowed(){
      for(int i=0;i<BorrowedBooks.size();i++){
          System.out.println(BorrowedBooks.get(i));
      }
  }
  
    public void writeRatingsToFile(String title) {
        Book book=Book.searchBook(title);
        if(book!=null){
           try (BufferedWriter writer = new BufferedWriter(new FileWriter(title + "_ratings.txt",true))) {
        for (int rating :Book.getRatings()) {
            writer.write(Integer.toString(rating));
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    } 
        }else{
            System.out.println("Book not found");
        }
    
}
public void rateBook(String Title, int rating){
   Book book=Book.searchBook(Title);
   if(book == null){
       System.out.println("Book is currently unavailable");
   } else {
       book.addRating(rating);
       System.out.println("You rated the book " + rating + ". Its average rating is now " + book.getAverageRating());
      writeRatingsToFile( Title);
   }
}

 public void displayAllRatings(String Title){
      printtofile();
try(BufferedReader  reader= new BufferedReader(new FileReader(Title + "_ratings.txt")) ){
    String line;
    while((line=reader.readLine())!=null){
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


public static void removeBooksFromCart(Borrower borrower, List<Book> booksToCancel) {
    for (Book i : booksToCancel) {
        borrower.BorrowedBooks.remove(i);
    }
}   
    
  public  List<User> readFromFile() {
       List<User> usr=new ArrayList<User>();//hy store el borrowers mn el file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
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
                    
                    usr.get(index).borrowBook(line);
                   
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



 }



