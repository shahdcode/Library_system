/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javaproject;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.image.*;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class learning_gui extends Application {
static ArrayList<String> borrowers = new ArrayList<>();
    static ArrayList<String> admins = new ArrayList<>();
    static ArrayList<String> librarians = new ArrayList<>();
private static  Text errorText= new Text ();
        

public static void signUp(int usertype, String username,String password){
    
    
    String encusername=encrypt(username);
    String encpassword=encrypt(password);
    
    
    String user;
    
        //ObjectOutputStream out;
        try{
            RandomAccessFile raf;
        switch (usertype){
            case 1:
                //borrower
                borrowers.add(encusername+","+encpassword);
               //out= new ObjectOutputStream (new FileOutputStream("borrowers.bin",true));
              // out.writeObject(borrowers);
               //out.close();
                raf = new RandomAccessFile("borrowers.bin","rw");
                while((user= raf.readLine())!=null){
                    
                    String[] parts = user.split(",");
                if(parts[0].equalsIgnoreCase(encusername)){
                    System.out.println("this user already exists");
                    errorText.setText("this user already exists");
                    errorText.setFill(Color.GOLD);
                    return;
                }
                }
                raf.seek(raf.length());
                raf.writeBytes(encusername+","+encpassword);
               // raf.writeBytes(System.getProperty("lineSeperator"));
                raf.writeBytes("\n");
                raf.close();
                
               break;
               
            case 2:
                //librarian
                librarians.add(encusername+","+encpassword);
//                out= new ObjectOutputStream (new FileOutputStream("librarians.bin",true));
//               out.writeObject(librarians);
//               out.close();
                raf = new RandomAccessFile("librarians.bin","rw");
                while((user= raf.readLine())!=null){
                    
                    String[] parts = user.split(",");
                if(parts[0].equalsIgnoreCase(encusername)){
                    System.out.println("this user already exists");
                     errorText.setText("this user already exists");
                    errorText.setFill(Color.GOLD);
                    return;
                }
                }
                raf.seek(raf.length());
                raf.writeBytes(encusername+","+encpassword);
                //raf.writeBytes(System.getProperty("lineSeperator"));
                raf.writeBytes("\n");
                raf.close();
                
               break;
            
            case 3:
                //admins
                admins.add(encusername+","+encpassword);
//                 out= new ObjectOutputStream (new FileOutputStream("librarians.bin",true));
//               out.writeObject(admins);
//               out.close();
                raf = new RandomAccessFile("admins.bin","rw");
                while((user= raf.readLine())!=null){
                    
                    String[] parts = user.split(",");
                if(parts[0].equalsIgnoreCase(encusername)){
                    System.out.println("this user already exists");
                    errorText.setText("this user already exists");
                    errorText.setFill(Color.GOLD);
                    return;
                }
                }
                raf.seek(raf.length());
                raf.writeBytes(encusername+","+encpassword);
               // raf.writeBytes(System.getProperty("lineSeperator"));
                raf.writeBytes("\n");
                raf.close();
                
               break;
            default:
                System.out.println("user type incorrect.");
                 errorText.setText("Please enter username & pass!!");
                  errorText.setFill(Color.MAROON);
                return;
            
        }
        
        System.out.println("user successfully signed up");
        
        
        
        
    }catch(IOException e){
        e.printStackTrace();
    }
    
}
public static void signIn(int usertype, String username,String password){
     String encusername=encrypt(username);
    String encpassword=encrypt(password);
    
    try{
       // ObjectInputStream in;
      // Boolean found = false;
      RandomAccessFile raf;
      String user;
        switch(usertype){
            case 1:
                //borrower
               // in= new ObjectInputStream(new FileInputStream("borrowers.bin"));
                
               // borrowers=(ArrayList<String>)raf.readObject();
                raf = new RandomAccessFile("borrowers.bin","r");
                while((user= raf.readLine())!= null){
                     String[] parts= user.split(",");
                     if (parts[0].equalsIgnoreCase(encusername)&& parts[1].equals(encpassword)){
                        System.out.println("Borrower username and password correct");
                        
                       return;
                        
                    }
                }
              
                //kanet fo2 el for loop
                raf.close();
                break;
            case 2:
                //librarians
                //in= new ObjectInputStream(new FileInputStream("librarians.bin"));
               // librarians=(ArrayList<String>)in.readObject();
                raf = new RandomAccessFile("librarians.bin","r");
                while((user= raf.readLine())!= null){
                     String[] parts= user.split(",");
                     if (parts[0].equalsIgnoreCase(encusername)&& parts[1].equals(encpassword)){
                        System.out.println("Borrower username and password correct");
                       return;
                        
                    }
                }
                //kanet fo2 el for loop
                raf.close();
                break;
            
            case 3:
                //admins
               // in= new ObjectInputStream(new FileInputStream("admins.bin"));
               // admins=(ArrayList<String>)in.readObject();
                raf = new RandomAccessFile("admins.bin","r");
                while((user= raf.readLine())!= null){
                     String[] parts= user.split(",");
                     if (parts[0].equalsIgnoreCase(encusername)&& parts[1].equals(encpassword)){
                        System.out.println("Borrower username and password correct");
                       return;
                        
                    }
                }
                //kanet fo2 el for loop
                raf.close();
                break;
            
            
            default:
            System.out.println("user type incorrect.");
             errorText.setText("Please enter username & pass!!");
                    errorText.setFill(Color.MAROON);
            
                return;
        }
        System.out.println("incorrect username or password");
            
            
            
            
            
            
        }catch(IOException e){
            e.printStackTrace();
        
        
    }
}
 public static String encrypt(String input){
     
     StringBuilder output = new StringBuilder();
     for (char c : input.toCharArray()){
         output.append((char)(c+1));
        
     }
     return output.toString();
     
     
     
 }
    
     @Override
    public void start(Stage stage) {
        //SCENES USED
        
        
        //SCENE SHAHD ----------------------------------------------(tani scene)
        HBox root = new HBox();

        // Left Side
        VBox leftSide = new VBox();
        leftSide.setMinWidth(290);
        leftSide.setStyle("-fx-background-color: #044669;");

        //7oti el image path
        Image image= new Image("file:D:/Desktop/Univeristy/Semester 3/OOP/books.png");
        ImageView imageView= new ImageView(image);
        
        //image properties
        imageView.setFitWidth(180);
        imageView.setPreserveRatio(true);
        
        //a7ot el sora fel nos 
        leftSide.setAlignment(Pos.CENTER);
        leftSide.getChildren().add(imageView);
        HBox.setMargin(imageView, new Insets(20,0,0,65));
        
        // Right Side (label vbox)
        VBox labelVbox= new VBox(10);
        labelVbox.setAlignment(Pos.CENTER);
        Label welcomeLabel= new Label("Welcome");
        welcomeLabel.setFont(new Font("Impact",40));
        Label accessLabel = new Label("Access your account");
        accessLabel.setFont(new Font("Verdana",20));
        Label createacclabel = new Label("or create an account");
        labelVbox.getChildren().addAll(welcomeLabel,accessLabel);
        
        
        //right Side (textField vbox)
        VBox textfieldVbox = new VBox(10);
        textfieldVbox.setAlignment(Pos.CENTER_RIGHT);
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(200);
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(200);
        passwordField.setPromptText("Password");
        
        textfieldVbox.getChildren().addAll(usernameField,passwordField);
        //error text lama el password/usernmae yeb2o galat
       
        
        
        //spaces between el 7aga el kol vbox (el sabab el 5alani afsl)
        labelVbox.setSpacing(10);
        textfieldVbox.setSpacing(38);
         
        //join el right side fe one vbox 3a4an yeb2o ta7t ba3d
        VBox rightSide= new VBox();
        rightSide.setPadding(new Insets(20));
        rightSide.setSpacing(50);
        
        //buttons bel handle bet3ha 
        
        Button Sign_inButton = new Button("Sign in");
        Sign_inButton.setOnAction(e->{
             String username= usernameField.getText();
             String password= passwordField.getText();
             
//             int userType= 1; //me7taga code hala hena
             //boolean authentication=signIn(getEntryType(), username, password);
             signIn(getEntryType(), username, password);
//             if(!authentication){
//                 errorText.setText("*Username is not found please create account");
//             }else{
//                 errorText.setText("");
//             }
        });
        
        
        Button Sign_upButton = new Button("Sign up");
        Sign_upButton.setOnAction(e->{
            String username= usernameField.getText();
            String password= passwordField.getText();
            
//            int userType= 1; //me7taga code hala hena
           //  boolean authentication=signUp(getEntryType(), username, password);
           signUp(getEntryType(), username, password);
//             if(!authentication){
//                 errorText.setText("*Incorrect username or password");
//             }else{
//                 errorText.setText("");
//             }
        });
        
        
        Button sign_backButton = new Button("Back"); //handled fe scene hala (ba3d this scene)
        HBox buttons= new HBox();
        buttons.getChildren().addAll(Sign_inButton,Sign_upButton,sign_backButton);
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);
       
        rightSide.getChildren().addAll(labelVbox,textfieldVbox,errorText, buttons);
        
        //spacing between el vbox, hay7t fe hbox
        HBox.setMargin(rightSide, new Insets(20,0,0,65));
        root.getChildren().addAll(leftSide, rightSide);
       //----------------------------------------------END OF SHAHD
       
        Scene Sign_details_Scene = new Scene(root, 700, 450);
        
        
        //SCENE HALA ------------------------------- (awl scene)
        
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
               stage.setScene(Sign_details_Scene);
               stage.setTitle("Borrower - Sign in/up");
               setEntryType(1);
                });
        librarianbtn.setOnAction(event->{System.out.println("librarian selected");
                stage.setScene(Sign_details_Scene);
                stage.setTitle("Librarian - Sign in/up");
                setEntryType(2);
                });
             
        adminbtn.setOnAction(event->{System.out.println("admin selected");
                stage.setScene(Sign_details_Scene);
                stage.setTitle("Admin - Sign in/up");
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
        
        Scene scenehala1 = new Scene(roothala, 500, 250);
       
        stage.setScene(scenehala1);
        //---------------------------------------------- END OF HALA
        
        //back button handled here (to return from scene2 to scene1
        sign_backButton.setOnAction(e->{
          stage.setScene(scenehala1);
          stage.setTitle("Library");
        });

        //stage.setScene(Sign_details_Scene);
        stage.setTitle("Library");
        stage.show();
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
    
    //SHAHD
    
//    private Scene signDetailsScene(Stage stage){
//        
//    }
}
