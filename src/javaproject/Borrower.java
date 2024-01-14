package javaproject;


import java.io.BufferedReader;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;


 public  class Borrower extends User implements Serializable  {
 private boolean isBorrowed;
 private static int borrow_count=0; 
 private  static ArrayList<String> names = new ArrayList<>(); //stores names of borrowers
 private ArrayList<Book> BorrowedBooks; //names of borrowed books

 //zaina needs this
  //private List<Book> booksToCancel;
 //private Date BorrowedDate;

// File file_all=new File("AllBorrowers.dat");
 
 
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
      //  BorrowerName=Name;
        this.BorrowedBooks=new ArrayList<>();
        
        
        
    }
    
    
   
    

    public Borrower() {
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

   

    public static int getBorrow_count() {
        return borrow_count;
    }

    public static void setBorrow_count(int borrow_count) {
        borrow_count = borrow_count; 
    }

    
    
//public void addTo(String name, String pass){
// User newUser = new Borrower(name,pass);
//    users.add(newUser);
//    //names.add(newUser.getName());
//     Borrower borrower = new Borrower(name,pass);
//    FileManagment.printToFile(borrower);
//   printtofile();
//}
    
//    public static void readNamesFromFile() {
//        try (Scanner scanner = new Scanner(new File("BorrowerNames.txt"))) {
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                names.add(line.trim());
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found: " + e.getMessage());
//        }
//    }
//
//    public static void updateNamesFile() {
//        try (PrintWriter writer = new PrintWriter(new FileWriter("BorrowerNames.txt"))) {
//            for (String name : names) {
//                writer.println(name);
//            }
//        } catch (IOException e) {
//            System.out.println("Error writing to file: " + e.getMessage());
//        }
//    }
//
//    public void addTo(String name, String pass) {
//        User newUser = new Borrower(name, pass);
//        users.add(newUser);
//        names.add(newUser.getName());
//       // FileManagment.printToFile(this);
//        updateNamesFile();
//    }

 public static void readNamesFromFile() {
        try (Scanner scanner = new Scanner(new File("BorrowerNames.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                names.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static void updateNamesFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("BorrowerNames.txt"))) {
            for (String name : names) {
                writer.println(name);
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void addTo(String name, String pass) {
        User newUser = new Borrower(name, pass);
        users.add(newUser);
        names.add(newUser.getName());
       // FileManagment.printToFile(this);
        updateNamesFile();
    }


    
    
 public boolean search(String key) {
    try (Scanner scanner = new Scanner(new File("Borrowers_ALL.txt"))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // Check if the line contains the borrower's name (case-insensitive)
            if (line.trim().equalsIgnoreCase(key)) {
                return true;
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + e.getMessage());
    }
    return false;
}



    
    
    public  void returnBook(Book book){
   FileManagment history=new  FileManagment(book);
     // book.decreaseCopiesCount();
     //  DateReturned();
       printtofile();
       setIsBorrowed(false);
       
    }
    
//      public Date DateBorrowed(){
//        Date date= new Date();
//        System.out.println("Date & time of borrowing" +date);
//        return date;
//    }
//  
//    public  Date DateReturned(){
//       
//        Date date= new Date();
//        System.out.println("Date & time returned" +date);
//        return date;
//        
//    }
    
  
    
     //@Override
//    public void display(){
//   //   printtofile();
//try(BufferedReader  reader= new BufferedReader(new FileReader(file_all)) ){
//    String line;
//    while((line=reader.readLine())!=null){
//        System.out.println(line);
//    }
//}catch(IOException e){
//    System.out.println(e);
//}
//// me4 me7taga a7ot filenotfoundexception, le2n its a subclass of IOexception
//    }
    

// me4 me7taga a7ot filenotfoundexception, le2n its a subclass of IOexception
    
    

    
    
    @Override
    public void borrowBook(Book book){
     //Book book=Book.searchBook(title);
    if (book!=null) {
        //make sure copies are avaialable
//        if(book.getCopiesCount()>1){ //mafrod tet7ded by the supplier
//            System.out.println("All copies of the book are currently  not avaiable, please try again later");   
//             return;
//        }
       setIsBorrowed(true);
       borrow_count=borrow_count+1;
       BorrowedBooks.add(book); //Stored in the borrowedBooks
       FileManagment.getBorrowHistory().add(book); //stores the history 
      Librarian_Mgn.getAllbooks().add(book);
      // book.increaseCopiesCount();
       book.incrementBorrowCount();
      // book.incrementBorrowCount( book);
        //this.reservations.add(book);
        System.out.println("Book borrowed successfully.");
       // DateBorrowed();
      //  printtofile();
      FileManagment.printToFile(this);
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
@Override
    public void  displayAllBorrowers(){
     printtofile();
try(BufferedReader  reader= new BufferedReader(new FileReader("AllBorrowers.dat")) ){
    String line;
    while((line=reader.readLine())!=null){
        System.out.println(line);
    }
}catch(IOException e){
    System.out.println(e);
}}

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



//public User getBorrowerWithMaxBorrowings(){
//    User maxBorrower=null; //maynfa34 ayot int f object
//int maxBorrowings=0;
//for(User user:users){
//    if(user instanceof Borrower){
//     int borrowings=getNumberOfBorrowings();
//     if(borrowings>maxBorrowings){
//        maxBorrowings=borrowings;
//        maxBorrower=user;
//      } 
//    }
//    
//}
//return maxBorrower;
//}

public void numberOfBorrowperUser(User u){
    HashMap<String,Integer> no_borrow= LoadHashmap();
    no_borrow.put(u.getName(), borrow_count);
    writenumberofborrowTofile(no_borrow);
   
}

public HashMap<String,Integer> LoadHashmap(){
    HashMap <String,Integer> hashmap= new HashMap<>();
    try (Scanner scanner = new Scanner(new File("No_of_borrows.txt"))) {
            while (scanner.hasNextLine()) {
                // Assuming each line in the file contains user name and borrow count separated by ":"
                String[] parts = scanner.nextLine().split(":");
                if (parts.length == 2) {
                    String userName = parts[0].trim();
                    int borrowCount = Integer.parseInt(parts[1].trim());
                    hashmap.put(userName, borrowCount);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading HashMap from file: " + e.getMessage());
        }

        return hashmap;
    
}

public void writenumberofborrowTofile(HashMap<String,Integer> hashmap){
      try (PrintWriter writer = new PrintWriter(new FileWriter("No_of_borrows.txt",false))) {
           for(HashMap.Entry<String,Integer> entry: hashmap.entrySet() ){
          writer.print(entry.getKey()+" : "+entry.getValue());
          writer.println();
           }
     } catch(IOException e){
         System.out.println("Cant write to file");
     }
}

public static void removeBooksFromCart(Borrower borrower, List<Book> booksToCancel) {
    for (Book i : booksToCancel) {
        borrower.BorrowedBooks.remove(i);
    }
}   
    
//  public  List<User> readFromFile() {
//       List<User> usr=new ArrayList<User>();//hy store el borrowers mn el file
//        try (BufferedReader reader = new BufferedReader(new FileReader(file_all))) {
//            String line;
//            boolean readingBooks = false;  // Flag to indicate when to start reading book titles
//            int index=-1;
//            
//            while ((line = reader.readLine()) != null) {
//                if (line.startsWith("User Name: ")) {                  
//                    String[] userInfo = line.substring("User Name: ".length()).split(" ");                 
//                    String currentUserName = userInfo[0];
//                    String currentUserPass = userInfo[1];
//                     User br=new Borrower(currentUserName,currentUserPass);
//                     usr.add(br);
//                     index++;
//                } else if (line.equals("Borrowed Books:")) {
//                    readingBooks = true;  // Start reading book titles
//                      
//                    
//                } else if (readingBooks && !line.isEmpty()) {
//                    // Store the borrowed book with its associated username
//                    
//                   ///////////////////////////////////////////// usr.get(index).borrowBook(line);
//                   
//                }
//                else if(line.isEmpty())
//                {
//                     readingBooks = false; 
//                }
//            }       
//            
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//    return usr;
//   }  
    
  
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
//manage button
public User getBorrowerWithMaxRevenue() {
    User maxBorrower = null;
    double maxrev = Double.MIN_VALUE; // Initialize to a very low value

    for (User user : users) {
        if (user instanceof Borrower) {
            double revenue = Librarian_Mgn.calculatePayment(user);

            if (revenue > maxrev) {
                maxrev = revenue;
                maxBorrower = user;
            }
        }
    }

    return maxBorrower;
}


public static void removeBorrowerFromFile(String borrowerName) {
        // Specify the file path
        String filePath = "Borrowers_ALL.txt";

        try {
            // Read the existing content of the file
            List<String> fileContent = new ArrayList<>();
            boolean borrowerFound = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().equals(borrowerName)) {
                        borrowerFound = true;
                    }

                    if (!borrowerFound) {
                        fileContent.add(line);
                    }

                    if (line.trim().isEmpty() && borrowerFound) {
                        borrowerFound = false;
                    }
                }
            }

            // Write the modified content back to the file
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                for (String content : fileContent) {
                    writer.println(content);
                }
            }

            System.out.println("Borrower removed successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


 }