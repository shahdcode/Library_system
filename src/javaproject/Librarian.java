package javaproject;
import java.util.ArrayList;
import java.util.Collection;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.*;
public class Librarian extends User implements Serializable { 
    private static ArrayList<Librarian> librarians = new ArrayList<>();
    private String userName;
    private String password;
    private static File file=new File("librarians.txt");

    static{
        try{
            FileInputStream fis=new FileInputStream(file);
            ObjectInputStream ois=new ObjectInputStream(fis);
            librarians=(ArrayList<Librarian>)ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException | ClassNotFoundException e){
            librarians=new ArrayList<>();
        }
    }

      @Override
    public String toString() {
        return "Admin [username=" + userName + ", password=" + password + "]"; 
    }
    
    
    public Librarian(String userName, String password) {
        super(userName,password);
        //this.librarians = librarians;
        this.userName = userName;
        this.password = password;
        
       // librarians=new ArrayList<>();
    }
    
    public Librarian(){
        
    }
   

    public void setLibrarians(ArrayList<Librarian> librarians) {
        this.librarians = librarians;
    }
    
    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName(){
    return this.userName;
    }
   
    public static ArrayList<Librarian> getLibrarians() {
        return librarians;
    }
    
public static void saveLibData(){
          try{
             FileOutputStream fos=new FileOutputStream(file);
             ObjectOutputStream oos=new ObjectOutputStream(fos);
             oos.writeObject(librarians);
             oos.close();
             fos.close();
          }
          catch(IOException ioe){
             ioe.printStackTrace();
            
          }
      }

  
 public static void listLibrarians(){
     if(file!=null){
         try{
             FileInputStream fis=new FileInputStream(file);
             ObjectInputStream ois=new ObjectInputStream(fis);
             
              ArrayList<Librarian> librariansFromFile=(ArrayList<Librarian>) ois.readObject();
    int i=0;
    for(Librarian librarian: librariansFromFile){
        System.out.println("librarian " + (i+1) +": "+librarian.getUserName());
    
    i++;
    }
    ois.close();
    fis.close();
         }catch(IOException ioe){
             System.out.println("problem reading file");
             ioe.printStackTrace();
         }catch(ClassNotFoundException c){
             System.out.println("class not found");
             c.printStackTrace();
         }
   
        }
     else 
         System.out.println("file is empty");
     
    }


    
     
     public static void searchLibrarian(String userName){
         if(userName==null ){
             System.out.println("Invalid user name entered");
             return;
         }
         
        ArrayList<Librarian> librarians = Librarian.getLibrarians();
        if(librarians==null){
            System.out.println("librarian system is empty, there are no librarians to retrive");
            return;
        }
       
        for(User user:librarians){
            if(user instanceof Librarian){
                Librarian librarian=(Librarian)user;
         if(librarian.getUserName().equals(userName)){
             System.out.println("Librarian found");
             System.out.println("user name: "+librarian.getUserName());
             return;
         }
         }   
        }
        System.out.println("No librarian was found by this user name: "+userName);
    }

     
   public static void editLibrarian( Librarian editedLibrarian){
        ArrayList<Librarian> librarians=Librarian.getLibrarians();
        for(int i=0;i<librarians.size();i++){
            Librarian librarian=librarians.get(i);
            if(librarian.getUserName().equals(editedLibrarian.getUserName())){
                librarians.set(i,editedLibrarian);
                  saveLibData();
                  System.out.println("the librarian "+editedLibrarian.getUserName()+" has been updated");
                  return;
            }
        }
   }
   
   
          
}