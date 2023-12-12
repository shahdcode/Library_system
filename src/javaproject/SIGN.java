/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.concurrent.Task;
import java.lang.Thread;


/**
 *
 * @author user
 */
public class SIGN {
      static ArrayList<String> borrowers = new ArrayList<>();
    static ArrayList<String> admins = new ArrayList<>();
    static ArrayList<String> librarians = new ArrayList<>();

    public SIGN() {
    }
//public static void signUp(int usertype, String username, String password) {
//    Task<Void> task = new Task<Void>() {
//        @Override
//        protected Void call() throws Exception {
//            ObjectOutputStream out;
//            switch (usertype) {
//                case 1:
//                    // borrower
//                    borrowers.add(username + "," + password);
//                    out = new ObjectOutputStream(new FileOutputStream("borrowers.bin", true));
//                    out.writeObject(borrowers);
//                    out.close();
//                    break;
//
//                case 2:
//                    // librarian
//                    librarians.add(username + "," + password);
//                    out = new ObjectOutputStream(new FileOutputStream("librarians.bin", true));
//                    out.writeObject(librarians);
//                    out.close();
//                    break;
//
//                case 3:
//                    // admins
//                    admins.add(username + "," + password);
//                    out = new ObjectOutputStream(new FileOutputStream("librarians.bin", true));
//                    out.writeObject(admins);
//                    out.close();
//                    break;
//                default:
//                    System.out.println("user type incorrect.");
//                    return null;
//            }
//
//            System.out.println("user successfully signed up");
//            return null;
//        }
//    };
//    new Thread(task).start();
//}

public static void signUp(int usertype, String username,String password){
    try{
        ObjectOutputStream out;
        switch (usertype){
            case 1:
                //borrower
                borrowers.add(username+","+password);
               out= new ObjectOutputStream (new FileOutputStream("borrowers.bin",true));
               out.writeObject(borrowers);
               out.close();
               break;
               
            case 2:
                //librarian
                librarians.add(username+","+password);
                 out= new ObjectOutputStream (new FileOutputStream("librarians.bin",true));
               out.writeObject(librarians);
               out.close();
               break;
            
            case 3:
                //admins
                admins.add(username+","+password);
                 out= new ObjectOutputStream (new FileOutputStream("librarians.bin",true));
               out.writeObject(admins);
               out.close();
               break;
            default:
                System.out.println("user type incorrect.");
                return;
            
        }
        
        System.out.println("user successfully signed up");
        
        
        
    }catch(IOException e){
        e.printStackTrace();
    }
    
}
public static void signIn(int usertype, String username,String password){
    try{
        ObjectInputStream in;
        switch(usertype){
            case 1:
                //borrower
                in= new ObjectInputStream(new FileInputStream("borrowers.bin"));
                borrowers=(ArrayList<String>)in.readObject();
                for(String user: borrowers){
                    
                    String[] parts= user.split(",");
                    if (parts[0].equalsIgnoreCase(username)&& parts[1].equals(password)){
                        System.out.println("Borrower username and password correct");
                        return;
                        
                    }
                    
                }
                //kanet fo2 el for loop
                in.close();
                break;
            case 2:
                //librarians
                in= new ObjectInputStream(new FileInputStream("librarians.bin"));
                librarians=(ArrayList<String>)in.readObject();
                for(String user: librarians){
                    
                    String[] parts= user.split(",");
                    if (parts[0].equalsIgnoreCase(username)&& parts[1].equals(password)){
                        System.out.println("librarian username and password correct");
                        return;
                        
                    }
                    
                }
                //kanet fo2 el for loop
                in.close();
                break;
            
            case 3:
                //admins
                in= new ObjectInputStream(new FileInputStream("admins.bin"));
                admins=(ArrayList<String>)in.readObject();
                for(String user: admins){
                    
                    String[] parts= user.split(",");
                    if (parts[0].equalsIgnoreCase(username)&& parts[1].equals(password)){
                        System.out.println("admin username and password correct");
                        return;
                        
                    }
                    
                }
                //kanet fo2 el for loop
                in.close();
                break;
            
            
            default:
            System.out.println("user type incorrect.");
                return;
        }
        System.out.println("incorrect username or password");
            
            
            
            
            
            
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        
        
    }
}


}
