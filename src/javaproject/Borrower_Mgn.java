/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class Borrower_Mgn {
    //member fields
    private User user;
    private Book book;
//    private static Date borrowDate;
//    private static Date dueDate;
//    private static Date returnDate;
    private  static ArrayList<Book> loanHistory= new ArrayList<>(); //3ayzha total and not per user 
    private int fine=20;//perDay
    //constructor 

    public Borrower_Mgn(Book book) {
        this.book = book;
    }
    
    public Borrower_Mgn(Borrower borrrower, Book book){
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

    public static List<Book> getLoanHistory() {
        return loanHistory;
    }

    
//    public static int CalculateFine() {
//    if(Book.getBorrowedDate()==null ||Book.getReturnedDate()){
//        System.out.println("Book is not borrowed to return");
//        return 0;
//    } 
//    Duration duration=Duration.between(Book.getBorrowedDate(),Book.getReturnedDate());
//    long minute=duration.toMinutes();
//    if(minute>1){
//        return (int)(minute)*5;
//    }else{
//        return 0;
//    }
//}

    public User getUser() {
        return user;
    }

    


    public void  viewBorrowingHistory(User user){
      for(Book b:loanHistory ){
          System.out.println(b); 
      }
  }
    
    //override toString to display el object
    @Override
    public String toString(){
        return "Name: "+user.getName()+" Book: "+book.getTitle()+"DueDate: ";
    }
}
