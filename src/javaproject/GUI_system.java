/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javaproject;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import javafx.scene.effect.*;
import javafx.collections.*;
import java.util.*;
import java.io.*;
import javafx.scene.shape.*;




public class GUI_system extends Application {
    //HALA 
     static ArrayList<String> borrowers = new ArrayList<>();
    static ArrayList<String> admins = new ArrayList<>();
    static ArrayList<String> librarians = new ArrayList<>();

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
public static void displayArraylists(){
    try{
        System.out.println("Borrowers: ");
        for(String borrower: borrowers){
            System.out.println(borrower);
        }
        System.out.println("\nLibrarians: ");
        for(String librarian: librarians){
            System.out.println(librarian);
        }
        System.out.println("\nAdmins: ");
        for(String admin: admins){
            System.out.println(admin);
        }
        
        
        
    }catch(Exception e){
         System.out.println("an error occurred while displaying the arraylists");
        e.printStackTrace();
    }
    
    
}
    
    
    
    Stage window;
    @Override
    public void start(Stage primaryStage) {
        
       Button borrowerbtn = new Button("BORROWER");
        Button librarianbtn = new Button("LIBRARIAN");
        Button adminbtn = new Button("ADMIN");
        
        borrowerbtn.setStyle("-fx-background-color: #044669;-fx-text-fill:#ffffff;-fx-min-width: 100;-fx-min-height:50;");
        librarianbtn.setStyle("-fx-background-color: #044669;-fx-text-fill:#ffffff;-fx-min-width: 100;-fx-min-height:50;");
        adminbtn.setStyle("-fx-background-color: #044669;-fx-text-fill:#ffffff;-fx-min-width: 100;-fx-min-height:50;");
        
        Text text=  new Text("What would you like to enter as:");
        text.setFill(Color.rgb(4, 70, 105));
        text.setFont(new Font("Verdana",21));
        text.setStyle("-fx-background-color:#ffffff; -fx-padding:10;");
        
        Text underline = new Text ("__________________________");
        underline.setFill(Color.rgb(204,204,204));
        underline.setFont(new Font("Verdana",21));
        underline.setStyle("-fx-background-color:#ffffff; -fx-padding:10;");
      
// Image image = new Image("C:\\Users\\DELL\\Downloads\\white-pattern-priano-vines-blue-leaves-wallpaper.jpeg");
       // BackgroundImage backgroundimage= new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        borrowerbtn.setOnAction(event->{System.out.println("borrower selected");
                setEntryType(1);
                });
        librarianbtn.setOnAction(event->{System.out.println("librarian selected");
                setEntryType(2);
                });
             
        adminbtn.setOnAction(event->{System.out.println("admin selected");
                setEntryType(3);
                });    
           VBox textbox= new VBox(text,underline);
           
        
        GridPane roothala = new GridPane();
        roothala.setStyle("-fx-background-color:#ffffff;");
        
        roothala.setHgap(50);
        roothala.setVgap(50);
        
        roothala.setAlignment(Pos.CENTER);
       
        
        roothala.add(textbox,0,0,3,1);
       // roothala.add(underline,0,0,3,1);
        roothala.add(borrowerbtn,0,1);
        roothala.add(librarianbtn,1,1);
        roothala.add(adminbtn,2,1); 
        
        Scene scenehala1 = new Scene(roothala, 300, 250);
        primaryStage.setTitle("Registration form");
        primaryStage.setScene(scenehala1);
        primaryStage.show();
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
    
    //Method hala needs for first scene ------
    private int entryType=0;

    public void setEntryType(int entryType) {
        this.entryType = entryType;
    }

    public int getEntryType() {
        return entryType;
    }
    //------------
}
