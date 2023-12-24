/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public  class User implements Serializable {
    private String Name;
private String pass;
 private ArrayList<Book> BorrowedBooks=new ArrayList();
 protected static ArrayList<User> users=new ArrayList<>();//stores the borrower object 
public User(String Name, String pass) {//constructor
        this.Name = Name;
        this.pass=pass;
    }

    public User() {
    }

    public ArrayList<Book> getBorrowedBooks() {
        return BorrowedBooks;
    }
     public void clearCart()
 {
     BorrowedBooks.clear();
 }
          public   void removeBooksFromCart(User borrower, List<Book> booksToCancel) {
    for (Book i : booksToCancel) {
       System.out.println( "i");
    }
    }
               public  Book findbookbytitle(String title)
{
    for(Book book:Book.getBooks())
    {
        if(book.getTitle().equals(title))
        {
            System.out.println(book);
        }
    }
    return null;
}
                     public void addLibrarian( User newLibrarian){
        System.out.println("No librarian user");
    }
    public void setBorrowedBooks(ArrayList<Book> BorrowedBooks) {
        this.BorrowedBooks = BorrowedBooks;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
//    public void addLibrarian( User newLibrarian){
//        System.out.println("No librarian user");
//    }
    
//    public void addTo(String name){
//        System.out.println(" No user");
//    }
    
    
//    public static void serializeData() throws IOException
//    {
//    
//        //writes the array of books into the file
//        try (ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream("User.dat"))) {
//            //writes the array of books into the file
//            for(User usr : users){
//                file.writeObject(usr);}
//        }
//        
//    }
//    
//    public static void deserializeData() throws FileNotFoundException, IOException, ClassNotFoundException
//    {
//        
//        try (FileInputStream fileIn = new FileInputStream("User.dat")) {
//            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
//            int numObjects = objectIn.readInt(); // Read the number of objects stored in the file
//            for (int i = 0; i < numObjects; i++) {
//                User usr = (User) objectIn.readObject();
//                users.add(usr);
//            }
//            objectIn.close();
//        }
//    }
//        
   public  void borrowBook(String title){
       System.out.println("No book borrowed");
   }
   public void editName(String newName){
       System.out.println("No user to edit");
   }
   public  Boolean search(String key){
       return false;
   }
    
   //public abstract void editPass(String newpass);
   public  void remove(){
       System.out.println("No user with borrowing list");
   }
   public void display(){
       System.out.println("Nothing to display");
   }
   
   
    public void returnBook( Book book){
        System.out.println("No active borrow to return a book");
    }
   
   public void ListBorrowed(){
       System.out.println("You did not yet borrow a book");
  }
   
   public void rateBook(String title, int rating){
       System.out.println("You have to pick a book to rate");
   }
   public int getNumberOfBorrowings(){
       return 0;  
    }
   
   public void displayAllBorrowers(){
       System.out.println("No information available");
   }
   
 public void writeRatingsToFile(String title){
      System.out.println("No availabe ratings");
  }
 
 public void displayAllRatings(String Title){
     System.out.println("no available ratings");
 }
//    public void calculatePayment() {
//        
//    }
}
    

   
   
   

