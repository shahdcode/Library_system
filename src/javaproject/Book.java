/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;
import java.util.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
//addBook() 
//equals() 
//editBook() 
//savebooks() 
// removeBook() 
//
// listBooks() 
//
// searchBook() 
//
// getCategory() 
//
// getNumberOfBorrows()= getBorrows()
//
//getMostBorrowedBook()  
//
// getMostRevenueBook()  



//HALA TOHFA 

public class Book implements Serializable {
    private String title;
    private String author;
    private String category;
    //for total borrows for each individual book
    private int borrows;
    //for total borrows of all books
    private int price;
    // creates a list of book objects 
   private static ArrayList<Book> books= new ArrayList<>();
    
    //data memeber shahd me7tagha
    private static int totalBorrows=-1;
   private int copiesCount=0;
    private static List<Integer> ratings=new ArrayList<>();
    public void addRating(int rating){
        this.ratings.add(rating);
    }
    
    public double getAverageRating(){
        int sum=0;
        for(int rating:ratings){
            sum+=rating;
        }
        return ratings.isEmpty()?0:(double)sum/ratings.size();
    }

    public static ArrayList<Book> getBooks() {
        return books;
    }

    public int getCopiesCount() {
        return copiesCount;
    }
    public void increaseCopiesCount(){
        copiesCount++;
    }
    
    public void decreaseCopiesCount(){
        copiesCount--;
    }

    public static List<Integer> getRatings() {
        return ratings;
    }
  
    private Borrower borrower; //aggeragtion

    public Borrower getBorrower() {
        return borrower;
    }
    
    
    //end of what shahd added
//     private LocalDateTime borrowedDate;
//     private LocalDateTime returnedDate;
    public Book( String title,String author,String category){
        this.title= title;
        this.author=author;
        this.category=category;
        this.borrows=0;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBorrows(int borrows) {
        this.borrows = borrows;
    }

    public static void setTotalBorrows(int totalBorrows) {
        Book.totalBorrows = totalBorrows;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getBorrows() {
        return borrows;
    }

    public static int getTotalBorrows() {
        return totalBorrows;
    }

    public int getPrice() {
        return price;
    }
    
    //used for the .contains function in the arraylist
    @Override
    public boolean equals(Object obj){
        if(this==obj)
            return true;
//        getClass() is a method of the object class in java that checks 
//        if (this) and ( obj) are instances of the same class

//getClass()!= obj.getClass()
        if(obj==null|| !(obj instanceof Book))
            return false;
        
        Book book = (Book) obj ;
                return Objects.equals(getTitle(),book.getTitle())&&
                        Objects.equals(getAuthor(),book.getAuthor())&&
                        Objects.equals(getCategory(),book.getCategory());
                        
    }
    //adds book to arraylist in file 
    public static void addBook(Book book){
        try{
            if (books.contains(book)){
            throw new Exception ("Book already exists in file ");
            }
            books.add(book);
          //  Librarian_Mgn.getBook2(); //zaina me7taga deih
            //saves el arraylist ashan teb2a updated 
           
            saveBooks();
        }
        catch(Exception e){
            //if the exception has a detail msg the getmessage displays it it not, it returns null
            System.out.println("Error adding book:"+e.getMessage());
        }
          }
//        public static void saveBooks()throws IOException{
//            ObjectOutputStream file= new ObjectOutputStream(new FileOutputStream("books.dat"));
//            //writes the array of books into the file
//            file.writeObject(books);
//            file.close();
//        }
    public static void saveBooks()throws IOException{
            ObjectOutputStream file= new ObjectOutputStream(new FileOutputStream("books.dat"));
            //writes the array of books into the file
            for(Book book : books){
            file.writeObject(books);}
            file.close();
        }
        
        public static void removeBook(Book book){
            try{
                if(!books.contains(book)){
                    
                    throw new Exception("book does not exist in the system.");
                    
                }
                books.remove(book);
                saveBooks();
            }catch (Exception e){
                
                System.out.println("error removing book:"+e.getMessage());
                
            }
        }
        //changes info of book
       public static void editBook(Book book){
           try{
               if(!books.contains(book)){
               
               throw new Exception("book does not exist in the system");
           }
             Scanner input = new Scanner(System.in)  ;
               
             System.out.println("what information would you like to change about this book? (Author, Title,Category) ");
             System.out.println("please seperate each choice by comma only");
             //biakhod el string mn el user f array ashan lw el user ekhtar 2 coices aw 
             //aktar ye3mel split by el comma w yemshi aal cases b each choice 
             String[] userChoices = input.nextLine().split(",");
             //for each choice in the array of choices 
             for(String choice: userChoices){
                 //trim-> removes each any whitespace around choice
                 switch(choice.trim().toLowerCase()){
                     
                     case "title":
                         System.out.println("Enter new title: ");
                         String newTitle = input.nextLine();
                         book.setTitle(newTitle);
                         break;
                     
                     case "author":
                         System.out.println("Enter new author's name: ");
                         String newAuthor = input.nextLine();
                         book.setAuthor(newAuthor);
                         break;
                     
                     case "category":
                         System.out.println("Enter new category: ");
                         String newCategory = input.nextLine();
                         book.setCategory(newCategory);
                         break;     
                     default:
                         System.out.println("invalid input"+ Arrays.toString(userChoices) +"please enter title,category or author ");
                     editBook(book);
                 }
             }
             saveBooks();
              input.close();
           }catch (Exception e){
               System.out.println("error editing book:"+e.getMessage());
           }
          
       }
       public static void listBooks(){
           try{
           for(Book book :books){
               
               System.out.println("Title: "+book.getTitle()+", Athuor: "+book.getAuthor()+", Category: "+ book.getCategory());
           }}catch (Exception e){
               
               System.out.println("error listing books:"+e.getMessage());
           }
           
           
           
       }     
       public static Book searchBook(String title){
           try{
           for(Book book :books){
               if(book.getTitle().equalsIgnoreCase(title)){
                   return book;
                           }
               
           }
           System.out.println("Book not found");
           }catch (Exception e){
               
                System.out.println("error searching for book :"+e.getMessage());
               
           }
          return null; 
       }     
            
       public static Book getMostBorrowedBook(){
           
           Book mostBorrowedBook= null;
           int maxBorrows=0;
           for (Book book:books){
               if(book.getBorrows()>maxBorrows){
                   maxBorrows= book.getBorrows();
                   mostBorrowedBook=book;
                   
               }
           }
           
           return mostBorrowedBook;
       }     
//       public static Book getMostRevenueBook(){
//           Admin admin = new Admin();
//           Book mostRevenueBook= null;
//           int maxRevenue=0;
//           for (Book book:books){
//               double revenue = admin.getRevenue();
//               if(revenue>maxRevenue){
//                   maxRevenue= revenue;
//                   mostRevenueBook=book;
//                   
//               }
//           }
//           
//           return mostRevenueBook;
//       }     
//       
       
       
        
       //brace el class 
       
       //Me7tgha for zaina
       @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
 //aggeration relation (shahd)
//    public void returnBook(Book book){
//       Borrower borrower = getBorrower();
//    Borrower_Mgn history = borrower != null ? new Borrower_Mgn(borrower, book) : new Borrower_Mgn(book);
//      book.decreaseCopiesCount();
//      borrower.setIsBorrowed(false);
//      
//      //calculate the fine
//     // int fine=history.CalculateFine();
////      if(fine>0){
////          System.out.println("You have a fine of "+fine+"for returning the book late");
////      }else{
////          System.out.println("Book returned succesfully on time, no fine!");
////      }
//    }   
    
    
    //function shahd needs
    public static Book findBookByTitle(String title) {
    // Assuming you have a list or other collection of books
    for (Book book : books) {
        if (book.getTitle().equals(title)) {
            return book;
        }
    }
    return null; // or throw an exception if a book with the given title doesn't exist
}

  
    
}