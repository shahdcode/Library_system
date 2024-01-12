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
import java.util.ArrayList;
import java.util.List;

public  class User {
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
    
     
   public  void borrowBook(Book book){
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
   
   public void rateBook(Book book, int rating){
       System.out.println("You have to pick a book to rate");
   }
   public int getNumberOfBorrowings(){
       return 0;  
    }
   
   public void displayAllBorrowers(){
       System.out.println("No information available");
   }
   
 public void writeRatingsToFile(Book book, int rate){
      System.out.println("No availabe ratings");
  }
 
 public void ReadAllRatings(Book book){
     System.out.println("no available ratings");
 }
//    public void calculatePayment() {
//        '
 
//    }
 
 public void printtofile() {
   
     System.out.println("no borrower to add");
}
 
public void numberOfBorrowperUser(){
    System.out.println("user didnt borrow book");
}
 
public void clearCart()
 {
     BorrowedBooks.clear();
 }
     

          public   void removeBooksFromCart(User borrower, List<Book> booksToCancel) {
     for (Book i : booksToCancel) {
       getBorrowedBooks().remove(i);
    }
    }
public void displayAllSuppliers(){
    System.out.println("no supplies");
}
// public void AllBorrowers(List<User> users){
//     System.out.println("No borrower to add");
//}
//
//public List<User> readAllBorrowers(){
//    List<User> users= new ArrayList<>();
//      System.out.println("No borrower to read");
//      return users;
//}
}
    


   
   

