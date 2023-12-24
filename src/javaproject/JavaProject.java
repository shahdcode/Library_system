
package javaproject;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
public class JavaProject{
    
    
//public static void signIn(int usertype, String username,String password){
//    
//    
//        String filename="User.txt";
//        int userIndex=1;
//        boolean userTypeFound= false;
//        
//        try(BufferedReader reader= new BufferedReader(new FileReader(filename))){
//            String line;
//            //while (file not empty)
//            while((line=reader.readLine())!=null){
//                if(line.equals("-")){
//                    userIndex++;
//                    
//                
//                if(++userIndex==usertype){
//                    
//                    userTypeFound=true;
//                }
//                continue;
//                }
//            
//            if(userTypeFound){
//                //creates a string array and puts each word into an index splits words by whitespace
//                String[] parts= line.split(" ");
//                if(parts.length>=3&&parts[0].equalsIgnoreCase(username)){
//                   
//                    System.out.println("Useername Correct");
//                    if(parts[2].equals(password))
//                    {
//                        System.out.println("Password Correct");
//                        
//                    }
//                    
//                    return;}
//                else{
//                    
//                    break;
//                }
//            }
//            }
//            
//            System.out.println("Error: invalid username or password");
//            
//        }catch(IOException e){
//            System.out.println("Error: " + e.getMessage());
//        }
//         
//    }
 


//    public static void signIn(int usertype, String username, String password) {
//        String filename = "User.txt";
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            String line;
//            int currentUserType = 0;
//
//            // Iterate through each line in the file
//            while ((line = reader.readLine()) != null) {
//                if (line.equals("-")) {
//                    currentUserType++;
//                    continue;
//                }
//
//                if (currentUserType == usertype) {
//                    String[] parts = line.split(" ");
//                    System.out.println(parts[0]+" "+parts[2]);
//                    if (parts.length >= 3 && parts[0].equalsIgnoreCase(username) && parts[2].equals(password)) {
//                        System.out.println("Username and password are correct.");
//                        return;
//                    } else {
//                        System.out.println("Error: Invalid username or password.");
//                        return;
//                    }
//                }
//            }
//
//            // If the loop completes without finding the user type, print an error message
//            System.out.println("Error: User type not found.");
//
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//
//    
public static void signIn(int usertype, String username,String password,
    
    ArrayList<Borrower> borrowers,ArrayList<Admin> admins,ArrayList<Librarian> librarians){
    
    switch(usertype){
        
        case 1: 
            //borrower
            for(Borrower borrower: borrowers){
                
              if(borrower.getName().equalsIgnoreCase(username)&& borrower.getPass().equals(password))  {
                  System.out.println(" borrower Username and password are correct.");
                  return;
              }
                
                
                
            }
        break;
        
        case 2:
            //librarian
            for(Librarian librarian: librarians){
                
              if(librarian.getName().equalsIgnoreCase(username)&& librarian.getPass().equals(password))  {
                  System.out.println(" librarian Username and password are correct.");
                  return;
              }
                
                
                
            }
        break;
        case 3:
        for(Admin admin : admins){
                
              if(admin.getName().equalsIgnoreCase(username)&& admin.getPass().equals(password))  {
                  System.out.println(" admin Username and password are correct.");
                  return;
              }
                
                
                
            }
        break;
        default:
        System.out.println(" user type incorrect.");
        return;
    }
        
    System.out.println(" incorrect username or password.");
      return;
}

    public static void main(String[] args) throws IOException{
//    try {
//        User.deserializeData();
//    } catch (FileNotFoundException | ClassNotFoundException ex) {
//        Logger.getLogger(JavaProject.class.getName()).log(Level.SEVERE, null, ex);
//    }
        ArrayList<Borrower> borrowers = new ArrayList<>();
        ArrayList<Librarian> librarians = new ArrayList<>();
        ArrayList<Admin> admins = new ArrayList<>();

       String password;
        Admin admin=new Admin(); //to call non static methods 
        Librarian librarian=new Librarian();
        Librarian_Mgn manage=new Librarian_Mgn();
         User borrower;  
        Book book1 = new Book("Title 1", "Author 1", "fiction");
        Book book2 = new Book("Title 2", "Author 2", "fiction");
 Book book3 = new Book("Title 3", "Author 1", "non-fiction");
        Book book4 = new Book("Title 4", "Author 2", "science");
         book1.setPrice(100);
 book2.setPrice(80);
book3.setPrice(70);
 book4.setPrice(90);
        // Adding books
        Book.addBook(book1);
        Book.addBook(book2);
          Book.addBook(book3);
        Book.addBook(book4);
Borrower borrower1 = new Borrower("Borrower1", "Password1");
        Borrower borrower2 = new Borrower("Borrower2", "Password2");
        Librarian librarian1 = new Librarian("Librarian1", "Password1");
        Admin admin1 = new Admin("Admin1", "Password4");
        borrowers.add(borrower1);
        borrowers.add(borrower2);
        librarians.add(librarian1);
        admins.add(admin1);
   Scanner input=new Scanner(System.in);
         //executes loop till break is found 
         while(true){
             System.out.println("1.sign in \n2.sign up \n3. exit");
             int choice= input.nextInt();
             input.nextLine(); //3ashan next iteration ye2ra sah from the next line ashan lw el user dakhal input characters aw haga kda el mara el ablaha
             //if (choice == 3) break;
             
             System.out.println("Do you want to enter as: 1-Borrower 2-Librarian 3-Admin ");
             int usertype= input.nextInt();
             input.nextLine();
             
              System.out.println("Enter username:");
              String userName=input.nextLine();
             
              
              if(userName.isEmpty()){
                  System.out.println("Error: username can't be empty");
                  continue; //skips the rest of the loop and goes back to the beginning
                  
              }
              System.out.println("Enter password");
               password= input.nextLine();
                User username= new Borrower(userName,password);
              if(password.length()<=0){
                  System.out.println("Error: password can't be empty or negative");
                  continue; //skips the rest of the loop and goes back to the beginning
                  
              }
              switch(choice){
                  //sign in 
                  case 1:
                  
                  signIn(usertype,userName,password,borrowers,admins,librarians); //1 borrower 2-librarian 3-admin
                  switch(usertype){
                      case 1:
                               int choice1;
                               //User u=new Borrower("Mariam",password); 
        do {
            System.out.println("Menu:");
            System.out.println("1. Borrow a book");
            System.out.println("2. Return a book");
            System.out.println("3. Edit name");
            System.out.println("4. rate a Book");
            System.out.println("5. View ratings of a Book");
            System.out.println("6. Display borrower information");
            System.out.println("7. Total number of borrowings you added so far");
            System.out.println("8: Return  book");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
           //Scanner input= new Scanner(System.in);
            choice1=input.nextInt(); // Consume the newline character
input.nextLine();
            switch (choice1) {
                case 1:
       

        // Adding books
        Book.addBook(book1);
        Book.addBook(book2);
                    Book.listBooks();
                    System.out.print("Enter the title of the book to borrow: ");
                   // System.out.println();
                    String borrowTitle = input.nextLine();
                    username.borrowBook(borrowTitle);
                    while(choice ==1){
                      System.out.println("Do you want to borrow book again? "+"\n 1. Yes\n 2. No");
                      
                    choice=input.nextInt();
                    input.nextLine();
                    if(choice==1){
                        Book.listBooks();
                        System.out.print("Enter the title of the book to borrow: ");
                     borrowTitle = input.nextLine();
                     username.borrowBook(borrowTitle);
                    }
                    else{
                        break;
                    }
                    }
                    
                    break;
                case 2:
                    Book book=null; //not sure
                    System.out.println("The book you currently borrowed");
                    username.ListBorrowed();
                    System.out.print("Enter the title of the book to return: ");
                    String Title = input.nextLine();
//                    Book bookToReturn=Book.findBookByTitle(Title);
//                    book.returnBook(bookToReturn);
                    break;
                case 3:
                    System.out.print("Enter the new name: ");
                    String newName = input.nextLine();
                    username.editName(newName);
                    break;
                case 4:
                    //int rating=0;
                    Book.listBooks();
                    System.out.println();
                    System.out.println("Rate a book");
                    System.out.println("Which book do you want to rate");
                   //input.nextLine(); //possible whitepsace
                    Title=input.nextLine();
                    System.out.println("On a scale of 1 to 5, how do you want to rate the book");
                    int rating=input.nextInt();
                    input.nextLine();
                    username.rateBook(Title,rating);
                    break;
                case 5:
                    System.out.println("Which book do you want view its rating");
                   //input.nextLine();//consume newline
                    Title=input.nextLine();
                    username.displayAllRatings(Title);
                  //  username.writeRatingsToFile(Title);
                    
                    break;
                case 6:
                    System.out.println("Displaying your information: ");
                    System.out.println();
                    username.display();
                    break;
                case 7:
                    System.out.println("Total number of borrowings you added so far");
                    System.out.println(username.getNumberOfBorrowings());
                    break;
                case 8:
                    System.out.println("Total number of borrowings you added so far");
                    System.out.println(username.getNumberOfBorrowings());
                break;
                case 9:
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 9);
                          break;
                          
                      case 2:
                System.out.println("Welcome librarian "+userName);
                System.out.println(" Menu: ");
                System.out.println("1.edit Info");
                System.out.println("2.search librarian");
                System.out.println("3. list librarians ");
                System.out.println("4. number of borrowings");
                System.out.println("5. librarian with max revenue");
                System.out.println("6.librarian with max borrowings");
                System.out.println("7.list books");
            System.out.println("8. cancel borrowing");//working
            System.out.println("9. create a borrowing");//working
            System.out.println("10. select category");//working
            System.out.println("11. Display borrowering term details");//working
            System.out.println("12. by authors preference");//working
            System.out.println("13. calculate payment");//working
             System.out.println("14. exit");//working
                 choice=input.nextInt();

                switch(choice){
                    case 1: 
                   
                        String answer="yes";
                        
                       while(answer.equalsIgnoreCase("yes")){
                           System.out.println("Hi, "+ username+" what would you like to edit: your user name or password ");
                           String choice2=input.nextLine();
                           boolean found=false;
                           // ArrayList<Librarian>
                           librarians=Librarian.getLibrarians();
                           for(int i=0;i<librarians.size();i++){
                              // Librarian librarian=librarians.get(i);
                               if( librarian.getUserName().equals(username)&& librarian.getPassword().equals(password)){
                
                    found=true;
                           
                           switch(choice2.toLowerCase()){
                               case "user name":
                                   System.out.println("please enter your new user name");
                                   userName=input.nextLine();
                                   librarian.setUserName(userName);
                                    librarian.editLibrarian(librarian);
                                   break;
                                   
                               case "password":
                                    System.out.println("please enter your new password");
                                    password=input.nextLine();
                                    librarian.setPassword(password);
                                     librarian.editLibrarian(librarian);
                                    break;
                                 
                                     default:
                            System.out.println("The choice you entered is invalid. Please enter either user name, password or phone number.");
                            break;
                           }
                           }
                           }
                           if(!found){
                               System.out.println("no librarian was found in the system by this user name");
                           }
                           System.out.println("Would you like to edit anything else?");
                           answer=input.nextLine();
                          
        
    }
                    case 2:
                        System.out.println("Please enter the user name of the librarian you want to find.");
                        String name=input.nextLine();
                        Librarian.searchLibrarian(name);
                        break;
                        
                    case 3:
                        System.out.println("Librarians in the system: ");
                        Librarian.listLibrarians();
                        break;
                        
                    case 4:
                        Librarian.getNumOfBorrowings();
                        break;
                        
                    case 5:
                        Librarian.getLibrarianWithMaxRev();
                        break;
                        
                    case 6:
                        Librarian.getLibrarianWithMaxNumOfBorrowings();
                        break;
                    case 7:
                        Book.listBooks();
                        break;
                               case 8:
                            Borrower b=new Borrower();
         List<User> u=b.readFromFile(); 
                    System.out.println("Enter borrower's name to cancel borrowing: ");
                    
                    String borrowerNameToCancel = input.next();
                    for(User i:u)
                    {
                        if(i.getName().equals(borrowerNameToCancel))  
                        {
                         manage.cancelBorrowing(i); 
                         break;//user found
                        }
                    }
                    break;
                        
                case 9:
                                Borrower bro=new Borrower();
         List<User> user=bro.readFromFile(); 
             System.out.print("Enter borrower's name ");
String borrowerName = input.nextLine();
        borrower = null;//(borrowerName.equals("b1")) ? b1 : (borrowerName.equals("b2") ? b2 : null);

                     for(User i:user)
                    {
                        if(i.getName().equals(borrowerName))  
                        {
                        borrower=i;
                         break;//user found
                        }
                    }

if(borrower!=null)
{
    System.out.print("Enter book title: ");
    String bookTitle = input.nextLine();
    Book selectedBook = borrower.findbookbytitle(bookTitle);
    if (selectedBook != null) {
        manage.createBorrowing(borrower, selectedBook);
        manage.display();
    } else {
        System.out.println("Book not found.");
    }
} else {
    System.out.println("Borrower not found.");
}
break;
                   case 10:
                   manage.selectCategory(); 
                    break;
                case 11:     
                      
//          manage.specifyBorrowingTermDetails(username);
          break;
              case 12:         
           System.out.print("Enter the author's name: ");
                    String authorName = input.nextLine();
                    manage.selectbyauthor(authorName);
                    break;
        
            case 13:         
           System.out.println("Enter borrower's name to calculate payment: ");
                    String borrowerNameToCalculatePayment = input.next();
                                        Borrower brow=new Borrower();
         List<User> usered=brow.readFromFile(); 
         borrower=null;
                  for(User i:usered)
                    {
                        if(i.getName().equals(borrowerNameToCalculatePayment))  
                        {
                        borrower=i;
                         break;//user found
                        }
                    }
                   if(borrower!=null)
                   {
                       System.out.println( manage.calculatePayment(borrower));
                   }
       break;           
                        case 14:
               System.out.println("exited" );
                System.exit(0);
                break;
                    default: 
                        System.out.println("choice you entered is invalid");
                }
                //Librarian.saveToFile();
//                filename="librarians.txt";
                     break;
                          
                      case 3: //pass el admin fixed w ha chech hena 3aliha
             
        Scanner bookinput= new Scanner(System.in);
        int choose;
       do{
            
            System.out.println("1: Add User"); 
            System.out.println("2: edit user"); 
            System.out.println("3: search for a user"); 
            System.out.println("4: Add book"); 
            System.out.println("5: Add Supplier"); 
            System.out.println("6: List books");
            System.out.println("7: search book");
            System.out.println("8: Edit book");
            System.out.println("9: Remove book");
            System.out.println("10: Add Order"); 
            System.out.println("11: Remove Order"); 
            System.out.println("12: Remove supplier.");
            System.out.println("13: Display supplier Info.");
            System.out.println("14: Display All suppliers.");
            System.out.println("15: Edit supplier.");
            System.out.println("16: View Supplier Orders"); 
            System.out.println("17: View Total Revenue"); 
            System.out.println("18: View Average Revenue"); 
            System.out.println("19: View Supplier with Max Orders"); 
            System.out.println("20: View Supplier with Max Revenue");
            System.out.println("21: View borrowing term details");
            System.out.println("22: View all borrowers");
            System.out.println("23: Exit");
           choose=input.nextInt();
            input.nextLine();
        
        switch(choose){

                case 1:
//Add User
   try {
        System.out.println("Enter the name of the new user:");
        String newName = input.next();

        System.out.println("Enter the password for the new user:");
        String newPassword = input.next();
        User newUser = new User(newName, newPassword);

        System.out.println("Choose what you want to add this user as: ");
        System.out.println("1) Librarian");
        System.out.println("2) Admin");

        int ch = input.nextInt();

        switch (ch) {
            case 1:
               
                admin.addLibrarian(newUser);
                System.out.println("Librarian added successfully.");
                break;

            case 2:
                Admin adminForAdmin = new Admin();
                adminForAdmin.addAdmin(newUser);
                
                try {
                    adminForAdmin.saveAllAdminsToFile();
                    System.out.println("Admin added and saved successfully.");
                } catch (IOException e) {
                    System.out.println("Failed to save admins: " + e.getMessage());
                    e.printStackTrace();
                }
                break;

            default:
                System.out.println("Invalid choice.");
                break;
        }

    } catch (NoSuchElementException e) {
        System.out.println("Error reading input: " + e.getMessage());
        e.printStackTrace();
    }
    
  break;
                case 2: 
               //Edit User
    System.out.print("Enter user name to edit: ");
    String Name = input.next();
    System.out.print("Enter field to edit (Name/password): ");
    String Field = input.next();
    System.out.print("Enter new value: ");
    String newValue = input.next();
    System.out.print("in what do you want to edit 1)librarian 2)admin: ");
    int value = input.nextInt();
    switch(value)
    {
        case 1:
          //  admin.editlibrarian(Name, Field, newValue);
            break;
        case 2:
                {
                    try {
                        admin.editadmin(Name, Field, newValue);
                    } catch (ClassNotFoundException ex) {
                       System.out.println( ex);
                    }
                }
              break;

    }

    
                    break; 

                    

                case 3: 
                     //Search For A User
                             //Search For A User
     System.out.print("in what do you want to edit 1)librarian 2)admin: ");
    int value1 = input.nextInt();
    switch(value1)
    {
        case 1:   
            //searchlibrarian();
break;
        case 2:
              System.out.print("Enter field to search by (id/name): ");
    String searchField = input.next();
    System.out.print("Enter "+searchField+" to search for: ");
    String searchValue = input.next();
    List<User> searchresult = admin.searchAdmin(searchField, searchValue);
    
    if (!searchresult.isEmpty()) {
        System.out.println("Search results:");
        for (User result : searchresult) {
            System.out.println("Name: " + result.getName() + ", Password: " + result.getPass());
        }
    } else {
        System.out.println("No matching users found.");
    }
    break;
    }
    break;
            case 4: 
                System.out.println("Enter title:");
                String title= bookinput.nextLine();
                System.out.println("Enter author's name:");
                String author= bookinput.nextLine();
                System.out.println("Enter category:");
                String category= bookinput.nextLine();
                Book book= new Book(title,author,category);
            Book.addBook(book);
            System.out.println("List of books:");
             Book.listBooks();
            break;
            
            

                case 5: 

                    // Add Supplier 

                        // Add Supplier 
           System.out.print("Enter supplier name: ");
            String supplierName = input.next();
                System.out.print("Enter supplier Password: ");
                String sPassword = input.next();
              admin.addSupplier(supplierName, password);
          
         

                    break; 
//                      admin.addSupplier(supplierName, password, numOfOrders, revenue);
             
            
   
            case 6:
            System.out.println("List of books:");
             Book.listBooks();
            break;
            case 7:
             System.out.println("Enter title to search:");    
              String searchTitle= bookinput.nextLine();
                
                Book foundBook = Book.searchBook(searchTitle);
        if (foundBook != null) {
            System.out.println("Book found: " + foundBook.getTitle());
        }
        else{
                 System.out.println("Book not found ");
        }
        System.out.println("List of books:");
             Book.listBooks();
                break;
                
            case 8:
                System.out.println("Enter title of the book you want to edit ");
                String editTitle= bookinput.nextLine();
                Book bookToEdit = Book.searchBook(editTitle);
                if (bookToEdit!=null){
                   
                    Book.editBook(bookToEdit);
                }
                else{
                 System.out.println("Book not found ");
        }
                System.out.println("List of books:");
             Book.listBooks();
                break;
                
                
            case 9:
                System.out.println("Enter title of the book you want to remove ");
                String removeTitle= bookinput.nextLine();
                Book bookToRemove = Book.searchBook(removeTitle);
                if (bookToRemove!=null){
                   
                    Book.removeBook(bookToRemove);
                }
                else{
                 System.out.println("Book not found ");
        }
                System.out.println("List of books:");
             Book.listBooks();
                break;
            

                case 10:
                    //Add Order
                            String sName;
                    String titleB;
                    int amount;
                    

                    admin.displaySuppliers();
                    System.out.println("Enter the name of the supplier you want to order from");
                    sName= input.next();
                    if(admin.isAvailable( sName)){
                    admin.displayPrices( sName);
                    System.out.println("what book do you want to order");
                    titleB= input.next();
                    System.out.println("how many books do you want to order");
                    amount= input.nextInt();
                    Supplier Asupplier =new Supplier(sName);
                    admin.addOrder(sName,titleB,amount);}
        else{                    System.out.println("no books available");
}
                  
              
                    
                    
                    
                    break;
                    
                    
              
                case 11:
                    //Remove Order
                    
                String cName;
                    String bTitle;
                    admin.displaySuppliers();
                    System.out.println("Which supplier did you order from");
                    cName= input.next();
                    admin.displayOrders(cName);
                    System.out.println("Whats the title of the book you'd like to cancel");
                    bTitle= input.next();
              
                                   admin.cancelOrder(bTitle,cName);
                        break;    
                case 12:
                        //Remove Supplier
                    
                  String removedSupplier;
                  admin.displaySuppliers();
                  System.out.println("which supplier would you like to remove.");
                  removedSupplier=input.next();
                  admin.removeSupplier(removedSupplier);
                    break;
             
                    
                case 13:
                    //Display a specific supplier info
                  String Dname;
                    admin.displaySuppliers();
                     System.out.println("Which supplier do you want to display thier file?");
                    Dname=input.next();
                   admin.displaySupplierInfo(Dname);
                    
                    break;
                    
                   
                case 14:
                    //display all suppliers info
                    admin.displaySuppliers();
                    break;
                case 15:
                    // Edit Supplier
                            String Ename;
                     String Epass;
                     admin.displaySuppliers();
                     System.out.println("Which supplier do you want to edit");
                     Ename=input.next();
                     System.out.println("Enter supplier password:");
                     Epass=input.next();
                     admin.editSupplier(Ename,Epass);
                     break;
                     
                case 16:
                    //View Supplier Orders
                        String Vname;
                     System.out.println("Which supplier do you want to view their order");
                     Vname=input.next();
                     admin.displayOrders(Vname);
                    
                    break;
                case 17: 

                    // View Total Revenue 
                      Borrower brower=new Borrower();
         List<User> user=brower.readFromFile(); 
                    System.out.println("Total Revenue: " + admin.getTotalRevenue()); 

                    break; 

  

                case 18: 

                    // View Average Revenue 
                     Borrower browerer=new Borrower();
                       List<User> r=browerer.readFromFile(); 

                    System.out.println("Average Revenue: " + admin.getAverageRevenue()); 

                    break; 

  

                case 19: 

                    // View Supplier with Max Orders 
                            System.out.println("Supplier with Max Revenue: " ); 
                   admin.SmaxOrders();
                    break; 

                

  

                case 20: 

                    // View Supplier with Max Revenue 
  
                      System.out.println("Supplier with Max Orders: " ); 
                    admin.SmaxRevenue();
                    break; 
case 21:
    
                     Borrower b=new Borrower();
         List<User> u=b.readFromFile(); 
                   
                    for(User i:u)
                    {
                        
//                         admin.specifyBorrowingTermDetails(i); 
                    }
                    break;
                        
case 22:
  //view all borrowers
            break;
           
                case 23:
                System.out.println("Exiting..");
                bookinput.close();
                return;
            default:
                System.out.println("Invalid input please enter a number from 1 to 6");
                break;
            
        } 
        }while (choose != 23);
                  }
  }
         }
}
    
}