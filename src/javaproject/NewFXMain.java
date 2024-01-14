/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javaproject;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */



import java.io.*;
import javafx.animation.*;
import javafx.util.Duration;
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
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.shape.*;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Screen;

public class NewFXMain extends Application {
static ArrayList<String> borrowers = new ArrayList<>();
    static ArrayList<String> admins = new ArrayList<>();
    static ArrayList<String> librarians = new ArrayList<>();
    private SVGPath[] Fivestars;
    private int imagesize = 69;
    private int rate=0;
    private static boolean Authentication=false;
    //right side (kol 7aga hatet7t fel a5r fe border pane 3a4an el 4akl
    private BorderPane Border= new BorderPane();
    private  Scene sceneBorrower;
    private Scene  scenehala1;
    private Scene scenezeina1;
    private Scene scenehala2;
    private Scene scenehala3;
    private Scene scenemalak1;
    private Scene mariam;
    private Admin adminobj = new Admin();
    private ComboBox<String> cbo4;
    private Stage primaryStage;
    private static Text errorText;
    private GridPane rightsidepane= new GridPane();
   List <Book> books= new ArrayList<>();
    private double imagesize1=70.0;
    private TextField supplierNameField;
    private TextField bookTitleField;
    private PasswordField passwordField;
    private TextField numberOfCopiesField;
    private TextField  contactInfoField;
    private TextField  newContactInfoField;
    private TextField  supplierDisplayOrdersField;
    private TextField  borrowerNameField;
    HBox contentPane = new HBox(10);
    HBox newPane = new HBox(10);
       

    User user;

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
                    Authentication=false;
                    
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
                    Authentication=false;
                 
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
                    Authentication=false;
                   
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
                Authentication=false;
                
                return;
            
        }
        
        System.out.println("user successfully signed up");
        Authentication=true;
        
        
        
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
                        Authentication=true;
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
                        Authentication=true;
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
                        Authentication=true;
                       return;
                        
                    }
                }
                //kanet fo2 el for loop
                raf.close();
                break;
            
            
            default:
            System.out.println("user type incorrect.");
           
            
                return;
        }
        System.out.println("incorrect username or password");
        Authentication=false;
            
            
            
            
            
            
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
 
//librarian----------------------------------------------
 
 private void showLibrarianPage(Stage primaryStage){
      this.primaryStage = primaryStage;
//    Image houseIcon=new Image(getClass().getResourceAsStream("file:D:\\Desktop\\Univeristy\\Semester 3\\Java book questions\\JAVAFXSTUDYING\\src\\home1.png"));  
//    Image profileIcon=new Image(getClass().getResourceAsStream("file:D:\\Desktop\\Univeristy\\Semester 3\\Java book questions\\JAVAFXSTUDYING\\src\\picon.png"));
//    Image bookIcon=new Image(getClass().getResourceAsStream("file:D:\\Desktop\\Univeristy\\Semester 3\\Java book questions\\JAVAFXSTUDYING\\src\\Bmng.png"));
//    Image statIcon=new Image(getClass().getResourceAsStream("file:D:\\Desktop\\Univeristy\\Semester 3\\Java book questions\\JAVAFXSTUDYING\\src\\statIcon.png"));
//    Image bmarkIcon=new Image(getClass().getResourceAsStream("file:D:\\Desktop\\Univeristy\\Semester 3\\Java book questions\\JAVAFXSTUDYING\\src\\borrowing.png"));
//    Image arrow=new Image(getClass().getResourceAsStream("file:D:\\Desktop\\Univeristy\\Semester 3\\Java book questions\\JAVAFXSTUDYING\\src\\arrow2.png"));
//    Image bookspic=new Image(getClass().getResourceAsStream("file:D:\\Desktop\\Univeristy\\Semester 3\\Java book questions\\JAVAFXSTUDYING\\src\\logo1.png"));
//    Image menuIcon=new Image(getClass().getResourceAsStream("file:D:\\Desktop\\Univeristy\\Semester 3\\Java book questions\\JAVAFXSTUDYING\\src\\menu1.png"));   
//    Image logIcon=new Image(getClass().getResourceAsStream("file:D:\\Desktop\\Univeristy\\Semester 3\\Java book questions\\JAVAFXSTUDYING\\src\\logout.png"));
//    Image line=new Image(getClass().getResourceAsStream("file:D:\\Desktop\\Univeristy\\Semester 3\\Java book questions\\JAVAFXSTUDYING\\src\\lineP.png"));
//    Image book=new Image(getClass().getResourceAsStream("file:D:\\Desktop\\Univeristy\\Semester 3\\Java book questions\\JAVAFXSTUDYING\\src\\addbook.png"));
//    Image calcI=new Image(getClass().getResourceAsStream("file:D:\\Desktop\\Univeristy\\Semester 3\\Java book questions\\JAVAFXSTUDYING\\src\\calc.png"));
    primaryStage.setTitle("Librarian");
    BorderPane root=new BorderPane();
       VBox left=new VBox();
      left.setMinWidth(300);
      left.setStyle("-fx-background-color: #044669;");
      left.setPrefHeight(1500);
 
 
 Button home=new Button(" Home");
      home.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;");
     home.setOnMouseEntered(e -> home.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 26px;"));
     home.setOnMouseExited(e -> home.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;"));
      VBox.setMargin(home, new Insets(80,0,10,0));
      home.setMinHeight(75);
      home.setMinWidth(350);
//      ImageView house=new ImageView(houseIcon);
//      house.setFitHeight(30);
//      house.setFitWidth(30);
     // home.setGraphic(house);
//      
      
           Button logout=new Button(" Logout");
      logout.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;");
     logout.setOnMouseEntered(e -> logout.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 26px;"));
     logout.setOnMouseExited(e -> logout.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;"));
     VBox.setMargin(logout, new Insets(100,0,10,0));
      logout.setMinHeight(75);
      logout.setMinWidth(350);
     //   ImageView logoutI=new ImageView(logIcon);
//      logoutI.setFitHeight(30);
//      logoutI.setFitWidth(30);
//      logout.setGraphic(logoutI);
//      
      
      
      Button back1=new Button(" Back");
         back1.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 20px;");
          back1.setOnMouseEntered(e -> back1.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 20px;"));
           back1.setOnMouseExited(e -> back1.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 20px;"));
             VBox.setMargin(back1, new Insets(300,0,5,0));
          back1.setMinHeight(75);
      back1.setMinWidth(350);
//            ImageView backI=new ImageView(arrow);
//      backI.setFitHeight(30);
//      backI.setFitWidth(30);
//      back1.setGraphic(backI);
     // back1.setOnAction(event-> primaryStage.setScene());

      
      left.getChildren().addAll(home,logout,back1);
 
 root.setLeft(left);
 
       GridPane grid=new GridPane();
      grid.setPadding(new Insets(0,30,0,0));
      grid.setHgap(10);
      grid.setVgap(10);
  
      
     Label L1=new Label("Welcome Librarian!");
     L1.setStyle("-fx-text-fill:#044669;-fx-font-weight:bold;-fx-font-size: 22px;");
     grid.add(L1,9,5,2,1);
     
      
     VBox.setVgrow(grid, Priority.ALWAYS);
 
 
 MenuBar menuBar=new MenuBar();
     root.setTop(menuBar);
     
     Menu menu=new Menu(" Menu");
      menu.setStyle("-fx-font-weight:bold;-fx-font-size: 20px;");
       menuBar.getMenus().add(menu);
//       ImageView menuI=new ImageView(menuIcon);
//      menuI.setFitHeight(25);
//      menuI.setFitWidth(25);
//      menu.setGraphic(menuI);
       
      
//      ImageView booksp=new ImageView(bookspic);
//      booksp.setFitHeight(170);
//      booksp.setFitWidth(170);
//      grid.add(booksp, 23, 45);
       
  
      
         root.setCenter(grid);
         scenemalak1 = new Scene(root, 800, 600);
  
     //end of format
 ColumnConstraints col1 = new ColumnConstraints();
    col1.setPercentWidth(10);
    col1.setMinWidth(100);
    ColumnConstraints col2 = new ColumnConstraints();
    col2.setPercentWidth(10);
    col2.setMinWidth(140);
    grid.getColumnConstraints().addAll(col1, col2);

   
    
     
     Button profile=new Button("Profile");
     profile.setMinWidth(150);
     profile.setMinHeight(40);
     grid.add(profile,2,17);//col 0, r 1
 
     
     //when it's pressed:
     profile.setOnAction(e->{
         
    // Image profileICON=new Image(getClass().getResourceAsStream("file:D:\\Desktop\\Univeristy\\Semester 3\\Java book questions\\JAVAFXSTUDYING\\src\\picon.png"));
     BorderPane root1=new BorderPane();
       VBox left1=new VBox();
      left1.setMinWidth(300);
      left1.setStyle("-fx-background-color: #044669;");
      left1.setPrefHeight(1500);
      
      Button home1=new Button(" Home");
      home1.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;");
     home1.setOnMouseEntered(event -> home1.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 26px;"));
     home1.setOnMouseExited(event -> home1.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;"));
      VBox.setMargin(home1, new Insets(80,0,10,0));
      home1.setMinHeight(75);
      home1.setMinWidth(350);
//        ImageView house2=new ImageView(houseIcon);
//      house2.setFitHeight(30);
//      house2.setFitWidth(30);
//      home1.setGraphic(house2);
      
      
            Button logout2=new Button(" Logout");
      logout2.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;");
     logout2.setOnMouseEntered(event -> logout2.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 26px;"));
     logout2.setOnMouseExited(event -> logout2.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;"));
     VBox.setMargin(logout2, new Insets(100,0,10,0));
      logout2.setMinHeight(75);
      logout2.setMinWidth(350);
//        ImageView logoutI2=new ImageView(logIcon);
//      logoutI2.setFitHeight(30);
//      logoutI2.setFitWidth(30);
//      logout2.setGraphic(logoutI2);
     
      
          Button back11=new Button(" Back");
         back11.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 20px;");
          back11.setOnMouseEntered(event -> back11.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 20px;"));
           back11.setOnMouseExited(event -> back11.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 20px;"));
             VBox.setMargin(back11, new Insets(300,0,10,0));
          back11.setMinHeight(75);
      back11.setMinWidth(350);
//             ImageView backI2=new ImageView(arrow);
//      backI2.setFitHeight(30);
//      backI2.setFitWidth(30);
//      back11.setGraphic(backI2);
      //handling
   back11.setOnAction(event-> primaryStage.setScene(scenemalak1));
   
   left1.getChildren().addAll(home1,logout2,back11);
   
            root1.setLeft(left1);   
      
        MenuBar menuBar2=new MenuBar();
     root1.setTop(menuBar2);
     
     Menu menu2=new Menu("Menu");
      menu2.setStyle("-fx-font-weight:bold;-fx-font-size: 20px;");
       menuBar2.getMenus().add(menu2);

        GridPane grid2=new GridPane();
        grid2.setPadding(new Insets(0,0,5,0));
     
        ColumnConstraints column1= new ColumnConstraints();
        column1.setPercentWidth(4);
         column1.setMinWidth(100);
        ColumnConstraints column2= new ColumnConstraints();
        column2.setPercentWidth(20);
         column2.setMinWidth(140);
        ColumnConstraints column3= new ColumnConstraints();
        column3.setPercentWidth(55);
        // column3.setMinWidth(130);
        
        RowConstraints row1=new RowConstraints();
        row1.setPercentHeight(7);
        row1.setMinHeight(100);
        RowConstraints row2=new RowConstraints();
        row2.setPercentHeight(10);
        row2.setMinHeight(140);
        RowConstraints row3=new RowConstraints();
        row3.setPercentHeight(20);
       // row3.setMinHeight(100);
    
        grid2.setHgap(3);
        grid2.setVgap(3);
        grid2.getColumnConstraints().addAll(column1,column2,column3);
       grid2.getRowConstraints().addAll(row1,row2,row3);
       ComboBox<String> edit=new ComboBox<>();
   edit.setPromptText("Edit Info");
  // edit.setStyle("-fx-background-color: transparent; -fx-padding: 5 12 5 12; -fx-font-size: 20px;");
   edit.getItems().addAll("Username","Password");
    grid2.add(edit,1,4);
   
    edit.setOnAction(event2 ->{
         String selectedEdit=edit.getValue();
        if("Username".equalsIgnoreCase(selectedEdit)){
            //handling
            TextField username=new TextField();
            username.setPromptText("Enter new username here");
            grid2.add(username,1,5);
            Button enter33=new Button("Enter");
            grid2.add(enter33,2,5);
            enter33.setOnAction(event ->{
                //handel
            });
        }
        if("Password".equalsIgnoreCase(selectedEdit)){
            //handling
               TextField pass=new TextField();
            pass.setPromptText("Enter new password here");
            grid2.add(pass,1,5);
            Button enter34=new Button("Enter");
            grid2.add(enter34,2,5);
            enter34.setOnAction(event->{
                
            });
        }
    });
    
     Button search=new Button("Search librarian");
     search.setPrefWidth(170);
     search.setPrefHeight(30);
     grid2.add(search,1,0);
     search.setOnAction(event->{
        TextField searchLib=new TextField();
        searchLib.setPromptText("Enter librarian here");
        searchLib.setStyle(" -fx-background-color: white; -fx-border-color: #044669; -fx-border-width: 2px;");
        searchLib.setPrefWidth(90);
        searchLib.setPrefHeight(20);
        Button enter2=new Button("Enter");
        grid2.add(enter2,2,1);
        enter2.setOnAction(event2-> {
//            String userName= searchLib.getText();
//            searchLibrarian(userName);
        });
        grid2.add(searchLib,1,1);
     });

         
         root1.setCenter(grid2);
    
     
     Scene profileScenemalak2=new Scene(root1,800,600);
     primaryStage.setTitle("Librarian profile");
     primaryStage.setScene(profileScenemalak2);
     });
     
     //end of profile
     
        
      Button stat=new Button("Performance Statistics");
       stat.setMinWidth(150);
        stat.setMinHeight(40);
          grid.add(stat, 2, 24);
     
          //when pressed:
       stat.setOnAction(e->{
           RowConstraints row1=new RowConstraints();
         row1.setPercentHeight(5);
         RowConstraints row2=new RowConstraints();
         row2.setPercentHeight(20);
         
         ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(5);
    ColumnConstraints col11 = new ColumnConstraints();
    col11.setPercentWidth(30);
  
     BorderPane root2=new BorderPane();
       VBox left2=new VBox();
      left2.setMinWidth(300);
      left2.setStyle("-fx-background-color: #044669;");
      left2.setPrefHeight(1500);
     
        
      
        Button home2=new Button(" Home");
      home2.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;");
     home2.setOnMouseEntered(event -> home2.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 26px;"));
     home2.setOnMouseExited(event -> home2.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;"));
       VBox.setMargin(home2, new Insets(80,0,10,0));
      home2.setMinHeight(75);
      home2.setMinWidth(350);
//      ImageView house3=new ImageView(houseIcon);
//      house3.setFitHeight(30);
//      house3.setFitWidth(30);
//      home2.setGraphic(house3);
      //handling
      
          Button logout3=new Button(" Logout");
      logout3.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;");
     logout3.setOnMouseEntered(event -> logout3.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 26px;"));
     logout3.setOnMouseExited(event -> logout3.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;"));
     VBox.setMargin(logout3, new Insets(100,0,10,0));
      logout3.setMinHeight(75);
      logout3.setMinWidth(350);
//        ImageView logoutI3=new ImageView(logIcon);
//      logoutI3.setFitHeight(30);
//      logoutI3.setFitWidth(30);
//      logout3.setGraphic(logoutI3);
      //handling
      
      
          Button back2=new Button(" Back");
         back2.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 20px;");
          back2.setOnMouseEntered(event -> back2.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 20px;"));
           back2.setOnMouseExited(event -> back2.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 20px;"));
             VBox.setMargin(back2, new Insets(300,0,10,0));
          back2.setMinHeight(75);
      back2.setMinWidth(350);
//             ImageView backI3=new ImageView(arrow);
//      backI3.setFitHeight(30);
//      backI3.setFitWidth(30);
//      back2.setGraphic(backI3);
      //handling
   back2.setOnAction(event-> primaryStage.setScene(scenemalak1));
      
      
      left2.getChildren().addAll(home2,logout3,back2);
      
 root2.setLeft(left2);      

              MenuBar menuBar3=new MenuBar();
     root2.setTop(menuBar3);
     
     Menu menu3=new Menu("Menu");
      menu3.setStyle("-fx-font-weight:bold;-fx-font-size: 20px;");
       menuBar3.getMenus().add(menu3);
      
           GridPane grid3=new GridPane();
           grid3.setPadding(new Insets(5,5,5,5));
           grid3.getRowConstraints().addAll(row1,row2);
             grid3.getColumnConstraints().addAll(col, col11);
             
               grid3.setHgap(10);
             grid3.setVgap(10);
             
             Label L2=new Label("What would you like to view?");
     L2.setStyle("-fx-text-fill:#044669;-fx-font-weight:bold;-fx-font-size: 22px;");
     grid3.add(L2,1,1,3,5);
             
     TextArea statArea=new TextArea();
     statArea.setEditable(true);
     statArea.setMinWidth(180);
     statArea.prefHeight(50);
     statArea.setStyle("-fx-background-color: white; -fx-border-color: #044669; -fx-border-width: 2px; ");
     
      statArea.setText("  Please click on the statistic you'd like to view");
           Button libMaxBorrowings=new Button("Librarian with max borrowings");
           libMaxBorrowings.setMinWidth(180);
           libMaxBorrowings.setMinHeight(40);
           grid3.add(libMaxBorrowings,1,10);
           libMaxBorrowings.setOnAction(event -> {
               
              statArea.setText("  Username: "+ "\n "+ "\nMaximum Borrowings: ");
              
           });
           
           
           Button libMaxRev=new Button ("  Librarian with max revenue");
           libMaxRev.setMinWidth(180);
           libMaxRev.setMinHeight(40);
           grid3.add(libMaxRev, 1, 25);
           libMaxRev.setOnAction(event -> {
               statArea.setText("  Username: "+ "\n "+ "\nMaximum Revenue: ");
              
           });
           
           Button totalBorrowings=new Button("Total number of borrowings");
           totalBorrowings.setMinWidth(180);
           totalBorrowings.setMinHeight(40);
           grid3.add(totalBorrowings,1,40);
           totalBorrowings.setOnAction(event -> {
               statArea.setText("Current total borrowings: ");
           });
            grid3.add(statArea, 7, 10);
           
//                 ImageView booksp2=new ImageView(bookspic);
//      booksp2.setFitHeight(170);
//      booksp2.setFitWidth(170);
//      grid3.add(booksp2, 20, 41);
           
         root2.setCenter(grid3);
      
           
           Scene statScenemalak3=new Scene(root2,800,600);
           primaryStage.setTitle("Performance Statistics");
           primaryStage.setScene(statScenemalak3);
       });
       
       
       Button books=new Button("Book Manegment");
          books.setMinWidth(150);
           books.setMinHeight(40);
         grid.add(books, 13, 17);
         
//           ImageView bookI=new ImageView(bookIcon);
//      bookI.setFitHeight(50);
//      bookI.setFitWidth(50);
//      grid.add(bookI, 12, 17);
      
      //when book mangemnt pressed
        books.setOnAction(e->{
           RowConstraints row1=new RowConstraints();
         row1.setPercentHeight(9);
         RowConstraints row2=new RowConstraints();
         row2.setPercentHeight(29);
         
         ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(9);
    ColumnConstraints col11 = new ColumnConstraints();
    col11.setPercentWidth(40);
  
     BorderPane root3=new BorderPane();
       VBox left3=new VBox();
      left3.setMinWidth(300);
      left3.setStyle("-fx-background-color: #044669;");
      left3.setPrefHeight(1500);
      
      
       Button home3=new Button(" Home");
      home3.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;");
     home3.setOnMouseEntered(event -> home3.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 26px;"));
     home3.setOnMouseExited(event -> home3.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;"));
           VBox.setMargin(home3, new Insets(80,0,10,0));
      home3.setMinHeight(75);
      home3.setMinWidth(350);
//        ImageView house4=new ImageView(houseIcon);
//      house4.setFitHeight(30);
//      house4.setFitWidth(30);
//      home3.setGraphic(house4);
      
           Button logout4=new Button(" Logout");
      logout4.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;");
     logout4.setOnMouseEntered(event -> logout4.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 26px;"));
     logout4.setOnMouseExited(event -> logout4.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;"));
     VBox.setMargin(logout4, new Insets(90,0,10,0));
      logout4.setMinHeight(75);
      logout4.setMinWidth(350);
//        ImageView logoutI4=new ImageView(logIcon);
//      logoutI4.setFitHeight(30);
//      logoutI4.setFitWidth(30);
//      logout4.setGraphic(logoutI4);
      
      Button addBook=new Button(" Add book");
      addBook.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;");
      addBook.setOnMouseEntered(event -> addBook.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 26px;"));
     addBook.setOnMouseExited(event -> addBook.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;"));
      VBox.setMargin(addBook,new Insets(100,0,10,0));
         addBook.setMinHeight(75);
      addBook.setMinWidth(350);
//               ImageView book3=new ImageView(book);
//      book3.setFitHeight(30);
//      book3.setFitWidth(30);
//      addBook.setGraphic(book3);
      
          Button back3=new Button(" Back");
         back3.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 20px;");
          back3.setOnMouseEntered(event -> back3.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 20px;"));
           back3.setOnMouseExited(event -> back3.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 20px;"));
           VBox.setMargin(back3, new Insets(130,0,10,0));
          back3.setMinHeight(75);
      back3.setMinWidth(350);
//             ImageView backI4=new ImageView(arrow);
//      backI4.setFitHeight(30);
//      backI4.setFitWidth(30);
//      back3.setGraphic(backI4);
      //handling
   back3.setOnAction(event-> primaryStage.setScene(scenemalak1));
       
      
      left3.getChildren().addAll(home3,logout4,addBook,back3);
      
 root3.setLeft(left3); 
   MenuBar menuBar4=new MenuBar();
     root3.setTop(menuBar4);
     
     
           GridPane grid4=new GridPane();
           grid4.setPadding(new Insets(0,30,0,0));
           grid4.getRowConstraints().addAll(row1,row2);
             grid4.getColumnConstraints().addAll(col, col11);
      
    
     
  Menu menu4=new Menu("Menu");
      menu4.setStyle("-fx-font-weight:bold;-fx-font-size: 20px;");
       menuBar4.getMenus().add(menu4);
//              ImageView menuI2=new ImageView(menuIcon);
//      menuI2.setFitHeight(25);
//      menuI2.setFitWidth(25);
//      menu4.setGraphic(menuI2);
       
       Menu selectCat=new Menu ();
   ComboBox<String> select=new ComboBox<>();
   select.setPromptText("Select Category");
   select.setStyle("-fx-background-color: transparent; -fx-padding: 5 12 5 12; -fx-font-size: 20px;");
   select.getItems().addAll("Fiction","Non-Fiction","Romance","Science","Thriller");
   selectCat.setGraphic(select);
  // selectCat.setAlignment( Pos.CENTER_RIGHT);
   
   Menu list=new Menu();
   Button list1=new Button("List Books");
   list1.setStyle("-fx-background-color: transparent; -fx-padding: 8 12 8 12; -fx-font-size: 20px;");
   list.setGraphic(list1);
    //list.setAlignment( Pos.CENTER);
   
   Menu search=new Menu();
   Button searchBook=new Button("Search");
  searchBook.setStyle("-fx-background-color: transparent; -fx-padding: 8 12 8 12; -fx-font-size: 20px;");
   search.setGraphic(searchBook);
   searchBook.setOnAction(event->{
       Label L3=new Label("Search for book here");
       L3.setStyle("-fx-text-fill:#044669;-fx-font-weight:bold;-fx-font-size: 22px;");
       grid4.add(L3,1,1);
       TextField searchBar= new TextField();
       searchBar.setPromptText("Search...");
       searchBar.setPrefWidth(250);
       searchBar.setPrefHeight(40);
       searchBar.setStyle("-fx-border-color: #044669; -fx-border-width: 2px;");
  grid4.add(searchBar,2,1);
   
   });
   
    //search.setAlignment( Pos.CENTER_LEFT);
       menuBar4.getMenus().addAll(selectCat,list, search);
       
   
//        ImageView booksp3=new ImageView(bookspic);
//      booksp3.setFitHeight(170);
//      booksp3.setFitWidth(170);
//      grid4.add(booksp3,30,50);
    
      
            
         root3.setCenter(grid4);
      
           
           Scene bookScenemalak4=new Scene(root3,800,600);
           primaryStage.setTitle("Book Manegment");
           primaryStage.setScene(bookScenemalak4);
       });
      
      //end of book mangment
   
         
        
       Button borrowing=new Button("Borrowing Overview");
        borrowing.setMinWidth(150);
         borrowing.setMinHeight(40);
      grid.add(borrowing, 13, 24);
      
//            ImageView borrowingI=new ImageView(bmarkIcon);
//      borrowingI.setFitHeight(50);
//      borrowingI.setFitWidth(50);
//      grid.add(borrowingI, 12, 24);
      
      //when borrowing pressed on 
       borrowing.setOnAction(e->{
           RowConstraints row1=new RowConstraints();
         row1.setPercentHeight(7);
         RowConstraints row2=new RowConstraints();
         row2.setPercentHeight(24);
          RowConstraints row3=new RowConstraints();
         row3.setPercentHeight(23);
            RowConstraints row4=new RowConstraints();
         row4.setPercentHeight(20);
         
         ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(7);
    ColumnConstraints col11 = new ColumnConstraints();
    col11.setPercentWidth(50);
  
     BorderPane root4=new BorderPane();
       VBox left4=new VBox();
      left4.setMinWidth(300);
      left4.setStyle("-fx-background-color: #044669;");
      left4.setPrefHeight(1500);
      
      
       Button home4=new Button(" Home");
      home4.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;");
     home4.setOnMouseEntered(event -> home4.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 26px;"));
     home4.setOnMouseExited(event -> home4.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;"));
             VBox.setMargin(home4, new Insets(80,0,10,0));
      home4.setMinHeight(75);
      home4.setMinWidth(350);
//        ImageView house5=new ImageView(houseIcon);
//      house5.setFitHeight(30);
//      house5.setFitWidth(30);
//      home4.setGraphic(house5);
      
                 Button logout5=new Button(" Logout");
      logout5.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;");
     logout5.setOnMouseEntered(event -> logout5.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 26px;"));
     logout5.setOnMouseExited(event -> logout5.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;"));
     VBox.setMargin(logout5, new Insets(90,0,10,0));
      logout5.setMinHeight(75);
      logout5.setMinWidth(350);
//        ImageView logoutI5=new ImageView(logIcon);
//      logoutI5.setFitHeight(30);
//      logoutI5.setFitWidth(30);
//      logout5.setGraphic(logoutI5);
      
           Button borrow=new Button(" Borrow");
      borrow.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;");
      borrow.setOnMouseEntered(event -> borrow.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 26px;"));
      borrow.setOnMouseExited(event -> borrow.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 26px;"));
             VBox.setMargin(borrow, new Insets(100,0,10,0));
              borrow.setMinHeight(75);
      borrow.setMinWidth(350);
      borrow.setOnAction(event ->{
//         Borrower borrower=new Borrower(selectedUser);
//         createBorrowing(borrower);
      });
      
//              ImageView book4=new ImageView(book);
//      book4.setFitHeight(30);
//      book4.setFitWidth(30);
//      borrow.setGraphic(book4);
      
      
      
          Button back4=new Button(" Back");
         back4.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 20px;");
          back4.setOnMouseEntered(event -> back4.setStyle("-fx-background-color: #2288a3; -fx-text-fill: #f9f9f9; -fx-border-color: #044669; -fx-border-width: 0px; -fx-font-size: 20px;"));
           back4.setOnMouseExited(event -> back4.setStyle("-fx-background-color: transparent; -fx-text-fill: #dadada; -fx-font-size: 20px;"));
            VBox.setMargin(back4, new Insets(150,0,10,0));
          back4.setMinHeight(75);
      back4.setMinWidth(350);
//             ImageView backI5=new ImageView(arrow);
//      backI5.setFitHeight(30);
//      backI5.setFitWidth(30);
//      back4.setGraphic(backI5);
      //handling
   back4.setOnAction(event-> primaryStage.setScene(scenemalak1));
      
      left4.getChildren().addAll(home4,logout5,borrow,back4);
      
 root4.setLeft(left4); 
   MenuBar menuBar5=new MenuBar();
     root4.setTop(menuBar5);
     
 
        GridPane grid5=new GridPane();
           grid5.setPadding(new Insets(1,0,0,1));
           grid5.getRowConstraints().addAll(row1,row2,row3,row4);
             grid5.getColumnConstraints().addAll(col, col11);
     
  Menu menu5=new Menu("Menu");
      menu5.setStyle("-fx-font-weight:bold;-fx-font-size: 20px;");
       menuBar5.getMenus().add(menu5);
       
        Menu selectCat2=new Menu ();
   ComboBox<String> select2=new ComboBox<>();
   select2.setPromptText("Select Category");
   select2.setStyle("-fx-background-color: transparent; -fx-padding: 5 12 5 12; -fx-font-size: 20px;");
   select2.getItems().addAll("Fiction","Non-Fiction","Science");
   selectCat2.setGraphic(select2);
   
   
    Menu list2=new Menu();
   Button list22=new Button("List Books");
   list22.setStyle("-fx-background-color: transparent; -fx-padding: 8 12 8 12; -fx-font-size: 20px;");
   list2.setGraphic(list22);
    //list.setAlignment( Pos.CENTER);
   
    menuBar5.getMenus().addAll(selectCat2,list2);
   
        
      
     Label L4=new Label("Enter borrower username here");
     L4.setStyle("-fx-text-fill:#044669;-fx-font-weight:bold;-fx-font-size: 22px;");
     TextArea user=new TextArea();
     user.setPromptText("Borrower username");
     user.setPrefWidth(200);
     user.setPrefHeight(20);
     user.setStyle("-fx-border-color: #044669; -fx-border-width: 2px;");
     Button enter=new Button("Enter");
     enter.setPrefHeight(35);
     enter.setPrefWidth(52);
     grid5.add(enter, 40, 2);
     enter.setOnAction(event-> {
         L4.setVisible(false);
         user.setVisible(false);
         enter.setVisible(false);
         
         Button borrowDets=new Button("Borrowing Details");
         borrowDets.setPrefWidth(120);
         borrowDets.setPrefHeight(42);
         grid5.add(borrowDets,1,1);
         TextArea details=new TextArea();
         borrowDets.setOnAction(event1->{
             
             details.setPrefWidth(310);
             details.setPrefHeight(300);
             details.setStyle("-fx-border-color: #044669; -fx-border-width: 2px;");
          //   details.setText("  ");
          
          grid5.add(details,10,1);
         });
         
         
         Button calc=new Button("Calculate Payment ");
            calc.setPrefWidth(120);
         calc.setPrefHeight(42);
         grid5.add(calc,1,2);
//            ImageView calcIcon=new ImageView(calcI);
//      calcIcon.setFitHeight(50);
//      calcIcon.setFitWidth(50);
//      grid5.add(calcIcon, 1, 2);
         
         calc.setOnAction(event1 ->{
             details.setVisible(false);
             Label L5=new Label("Total: ");
             L5.setStyle("-fx-text-fill:#044669; -fx-font-size: 16px;");
             grid5.add(L5,8,3);
             TextArea total=new TextArea();
             total.setStyle("-fx-border-color: #044669; -fx-border-width: 2px;");
             total.setPrefWidth(90);
             total.setPrefHeight(20);
             
            // double totalPayement=calculatePayment(  borrow);
        //    total.setText(String.valueOf(totalPayment));
             
             grid5.add(total,10,3);
         });
         
         Button cancel=new Button("Cancel Borrowing");
            cancel.setPrefWidth(125);
         cancel.setPrefHeight(30);
         //cancelBorrowing(borrower);
         grid5.add(cancel, 1, 11);
         
     });
             grid5.add(L4,1,1);
             grid5.add(user,1,2);
      
         root4.setCenter(grid5);
      
           
           Scene borrowingScenemalak5=new Scene(root4,800,600);
           primaryStage.setTitle("Borrowing Overview");
           primaryStage.setScene(borrowingScenemalak5);
       });
  //end of borrowing    
      
      
  
      
        
      primaryStage.setScene(scenemalak1);
       primaryStage.show();
 }
 //--------------------------------------------------------librarian
 
 public void supplierstage(Stage primaryStage){
     this.primaryStage=primaryStage;
             //Admin scene---------------------------------------------------------------------------------------------------
        
        Admin admin= new Admin();

Image supImage1=new Image("file:C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\addIcon.png");
                ImageView supImage11=new ImageView(supImage1);
                ImageView supAdd=changeImage(supImage11);
                 supAdd.setFitWidth(23);  
                 supAdd.setFitHeight(25); 
                 supAdd.setTranslateX(-36);    
                // iv1.setLayoutY(-50); 
               supAdd.setTranslateY(-5); 
          
          Image supImage2=new Image("file:C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\removeIcon.png");
                ImageView supImage22=new ImageView(supImage2);
                ImageView removeSup=changeImage(supImage22);
                 removeSup.setFitWidth(23);  
                 removeSup.setFitHeight(25); 
                 removeSup.setTranslateX(-24);    
//                 iv2.setLayoutY(-75); 
//                 iv2.setTranslateY(151); 
          
          Image supImage3=new Image("file:C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\editIcon.png");
          ImageView supImage33=new ImageView(supImage3);
           ImageView editSup=changeImage(supImage33);
                 editSup.setFitWidth(23);  
                 editSup.setFitHeight(25); 
                 editSup.setTranslateX(-35);    
//                 iv3.setLayoutY(-80); 
//                 iv3.setTranslateY(193); 
//                 
                 
           Image supImage4=new Image("file:C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\displayIcon.png");
          ImageView supImage44=new ImageView(supImage4);
                    ImageView displaySup=changeImage(supImage44);

                 displaySup.setFitWidth(23);  
                 displaySup.setFitHeight(25); 
                 displaySup.setTranslateX(-25);    
//                 iv4.setLayoutY(-80); 
//                 iv4.setTranslateY(235);         
//                 
                 
                 
          Image supImage5=new Image("file:C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\searchIcon.png");
          ImageView supImage55=new ImageView(supImage5);
          ImageView searchSup=changeImage(supImage55);
                 searchSup.setFitWidth(23);  
                 searchSup.setFitHeight(25); 
                 searchSup.setTranslateX(-28);   
          Image supImage6=new Image("file:C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\orderIcon.png");
          ImageView supImage66=new ImageView(supImage6);
          ImageView supOrder=changeImage(supImage66);
                 supOrder.setFitWidth(23);  
                 supOrder.setFitHeight(25); 
                 supOrder.setTranslateX(-32); 
            Image supImage7=new Image("file:C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\maxIcon.png");
          ImageView supImage77=new ImageView(supImage7);
          ImageView supRevenue=changeImage(supImage77);
                 supRevenue.setFitWidth(25);  
                 supRevenue.setFitHeight(25); 
                 supRevenue.setTranslateX(-25); 
           Image supImage8=new Image("file:C:\\Users\\maria\\Documents\\NetBeansProjects\\library\\src\\library\\maxOrderIcon.png");
          ImageView supImage88=new ImageView(supImage8);
          ImageView supOrders=changeImage(supImage88);
                 supOrders.setFitWidth(23);  
                 supOrders.setFitHeight(25); 
                 supOrders.setTranslateX(-25); 
//                 iv5.setLayoutY(-80); 
//                 iv5.setTranslateY(276);  
           Button addSupplierButton = new Button("Add Supplier",supAdd);
        addSupplierButton.setOnAction((ActionEvent e) -> {
        TextField borrowerNameField = new TextField();
        TextField contactField = new TextField();
        Button addButton = new Button("Add");
contactField.setPrefWidth(200);
borrowerNameField.setPrefWidth(200);

        GridPane supRoot = new GridPane();
        Label supLabel=new Label("Name:");
         supLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
            Label supLabel2=new Label("Contact Info:");
         supLabel2.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        supRoot.add(supLabel, 0, 1);
        supRoot.add(borrowerNameField, 1, 1);
        supRoot.add(supLabel2, 0, 2);
        supRoot.add(contactField, 1, 2);
        supRoot.add(addButton, 1, 3);
        supRoot.setVgap(20);
        supRoot.setHgap(20);
        supRoot.requestFocus();
        supRoot.setPadding(new Insets(30));
        supRoot.setAlignment(Pos.CENTER);

        Rectangle supwhiteBox = new Rectangle(450, 200);
        supwhiteBox.setTranslateX(20);
        supwhiteBox.setLayoutY(50);
        supwhiteBox.setFill(Color.web("#F6F6F6"));

        Group supGroup = new Group();
        supGroup.getChildren().addAll(supwhiteBox, supRoot);
        
        newPane.getChildren().setAll(supGroup);
        newPane.setAlignment(Pos.CENTER);

        addButton.setOnAction((ActionEvent s) -> {
            String supName = borrowerNameField.getText();
            String supCon = contactField.getText();
            int contactInfo= Integer.valueOf(supCon);
           
            if(!admin.searchSupplier(supName)){
                 try {
                admin.addSupplier(supName, contactInfo);
            } catch (IOException ex) {
            //    Logger.getLogger(Mariam.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("User added successfully!");
            alert.showAndWait();
             Rectangle supwhiteBox1 = new Rectangle(450, 200);
        supwhiteBox1.setTranslateX(20);
        supwhiteBox1.setLayoutY(50);
        supwhiteBox1.setFill(Color.web("#F6F6F6"));
        borrowerNameField.setText("");
       contactField.setText("");

       // return;
      }
            else{
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("User already exists!");
            alert.showAndWait();
             Rectangle supwhiteBox1 = new Rectangle(450, 200);
        supwhiteBox1.setTranslateX(20);
        supwhiteBox1.setLayoutY(50);
        supwhiteBox1.setFill(Color.web("#F6F6F6"));
       borrowerNameField.setText("");
       contactField.setText("");
      //  return;
      //  newPane.setAlignment(Pos.CENTER);
            
            }
        });
    });

        Button displayOrdersButton = new Button("Display Orders",supOrder);
        displayOrdersButton.setOnAction((var e) ->{          
              supplierNameField = new TextField();
            TextArea textArea = new TextArea();
        textArea.setWrapText(true); 
         textArea.setEditable(false);
             textArea.setPrefWidth(250);
Label label=new Label("Name: ");
        supplierNameField.setPrefWidth(250);
           label.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        textArea.setPrefHeight(200);
         Button displayButton=new Button("Display");
          displayButton.setOnAction((ActionEvent s) -> {
              Supplier sup= new Supplier(supplierNameField.getText());
              ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);
                 try {
                     admin.displayOrders(sup);
                 } catch (IOException ex) {
                     System.out.println(ex);
                    // Logger.getLogger(Mariam.class.getName()).log(Level.SEVERE, null, ex);
                 }
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            textArea.appendText(outputStream.toString());

    });
                GridPane root = new GridPane();
       root.add(label,0,1);  
        root.add(supplierNameField,1,1);  

    root.add(displayButton, 1, 2);
    root.add(textArea, 1, 3);
    root.setVgap(20);
    root.setHgap(20);
    root.requestFocus();
    root.setPadding(new Insets(20));
    root.setAlignment(Pos.CENTER);

    Rectangle whiteBox = new Rectangle(450, 200);
    whiteBox.setTranslateX(20);
    whiteBox.setLayoutY(70);
    whiteBox.setFill(Color.web("#F6F6F6"));

    Group group = new Group();
    group.getChildren().addAll(whiteBox, root);
    
    newPane.getChildren().setAll(group);
    newPane.setAlignment(Pos.CENTER);});

        Button displaySupplierInfoButton = new Button("Search Supplier",searchSup);
        displaySupplierInfoButton.setOnAction((var e) ->{          
             TextField supplierNameField = new TextField();
            TextArea textArea = new TextArea();
        textArea.setWrapText(true); 
         textArea.setEditable(false);
           textArea.setPrefWidth(250);
        //supplierNameField.setPrefHeight(300);
          supplierNameField.setPrefWidth(250);
        textArea.setPrefHeight(200);
         Button searchButton=new Button("Search");
          searchButton.setOnAction((ActionEvent s) -> {
              Supplier sup= new Supplier(supplierNameField.getText());
              if(admin.searchSupplier(supplierNameField.getText())){
              ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);
            admin.displaySupplierInfo(sup);
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            textArea.appendText(outputStream.toString());}
              else{            
                  textArea.appendText("user not found");}


    });
                GridPane root = new GridPane();
                Label label=new Label("Name: ");
                        label.setFont(Font.font("Arial", FontWeight.BOLD, 13));
       root.add(label,0,1);  
        root.add(supplierNameField,1,1);  

    root.add(searchButton, 1, 2);
    root.add(textArea, 1, 3);
    root.setVgap(20);
    root.setHgap(20);
    root.requestFocus();
    root.setPadding(new Insets(20));
    root.setAlignment(Pos.CENTER);

    Rectangle whiteBox = new Rectangle(450, 200);
    whiteBox.setTranslateX(20);
    whiteBox.setLayoutY(70);
    whiteBox.setFill(Color.web("#F6F6F6"));

    Group group = new Group();
    group.getChildren().addAll(whiteBox, root);
    
    newPane.getChildren().setAll(group);
    newPane.setAlignment(Pos.CENTER);});

        Button displaySuppliersButton = new Button("Display Suppliers",displaySup);
       displaySuppliersButton.setOnAction((ActionEvent e) ->{
              TextArea textArea = new TextArea();
        textArea.setWrapText(true); 
         textArea.setEditable(false);
        textArea.setPrefWidth(340);
        textArea.setPrefHeight(300);
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);
            admin.displaySuppliers();
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            textArea.appendText(outputStream.toString());
          Label label=  new Label("All Suppliers:");
          label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                GridPane root = new GridPane();
    root.add(label, 0, 1);
    root.add(textArea, 0, 2);
    root.setVgap(20);
    root.setHgap(20);
    root.requestFocus();
    root.setPadding(new Insets(20));
    root.setAlignment(Pos.TOP_RIGHT);

    Rectangle whiteBox = new Rectangle(450, 200);
    whiteBox.setTranslateX(20);
    whiteBox.setLayoutY(50);
    whiteBox.setFill(Color.web("#F6F6F6"));

    Group group = new Group();
    group.getChildren().addAll(whiteBox, root);
    
    newPane.getChildren().setAll(group);
    newPane.setAlignment(Pos.CENTER);
        });
        
        Button removeSupplierButton = new Button("Remove Supplier",removeSup);
        removeSupplierButton.setOnAction((ActionEvent e) -> {
        TextField borrowerNameField = new TextField();
        TextField contactField = new TextField();
        Button removeButton = new Button("Remove");

        GridPane root = new GridPane();
        Label label=new Label("Name:");
        Label labelmariam=new Label("Contact Info:");

            //    label.setFont(boldFont);
          label.setFont(Font.font("Arial", FontWeight.BOLD, 13));
          labelmariam.setFont(Font.font("Arial", FontWeight.BOLD, 13));
borrowerNameField.setPrefWidth(200);
contactField.setPrefWidth(200);
        root.add(label, 0, 1);
        root.add(borrowerNameField, 1, 1);
        root.add(labelmariam, 0, 2);
        root.add(contactField, 1, 2);
        root.add(removeButton, 1, 3);
        root.setVgap(20);
        root.setHgap(20);
        root.requestFocus();
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.TOP_RIGHT);

        Rectangle whiteBox = new Rectangle(450, 200);
        whiteBox.setTranslateX(20);
        whiteBox.setLayoutY(50);
        whiteBox.setFill(Color.web("#F6F6F6"));

        Group group = new Group();
        group.getChildren().addAll(whiteBox, root);
        
        newPane.getChildren().setAll(group);
        newPane.setAlignment(Pos.CENTER);

        removeButton.setOnAction((ActionEvent s) -> {
            String name = borrowerNameField.getText();
                 Supplier sup=new Supplier(name);
            try {
                admin.deleteSupplierFiles(sup);
            } catch (IOException ex) {
                System.out.println(ex);
                //Logger.getLogger(Mariam.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            if(admin.searchSupplier(name)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("User removed successfully!");
            alert.showAndWait();
             borrowerNameField.setText("");
             contactField.setText("");
            }else{ Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Faliure");
            alert.setHeaderText(null);
            alert.setContentText("User not found!");
            alert.showAndWait();
             borrowerNameField.setText("");
             contactField.setText("");}
                         Rectangle whiteBox1 = new Rectangle(450, 200);

        whiteBox1.setTranslateX(20);
        whiteBox1.setLayoutY(50);
        whiteBox1.setFill(Color.web("#F6F6F6"));

        Group group1 = new Group();
        group1.getChildren().addAll(whiteBox1);
           
        newPane.getChildren().setAll(group1);
        newPane.setAlignment(Pos.CENTER);});
        });
        Button editSupplierButton = new Button("Edit Supplier", editSup);
editSupplierButton.setOnAction((ActionEvent e) -> {
    TextField borrowerNameField = new TextField();
    TextField contactField = new TextField();
    TextField newContactField = new TextField();
    Button editButton = new Button("Edit");
    Label label = new Label("Name:");
    Label labell = new Label("Contact Info:");
    Label labelll = new Label("New Contact Info:");
    label.setFont(Font.font("Arial", FontWeight.BOLD, 13));
    labell.setFont(Font.font("Arial", FontWeight.BOLD, 13));
    labelll.setFont(Font.font("Arial", FontWeight.BOLD, 13));
    borrowerNameField.setPrefWidth(170);
    contactField.setPrefWidth(170);
    newContactField.setPrefWidth(170);

    GridPane root = new GridPane();
    root.add(label, 0, 1);
    root.add(borrowerNameField, 1, 1);
    root.add(labell, 0, 2);
    root.add(contactField, 1, 2);
    root.add(labelll, 0, 3);
    root.add(newContactField, 1, 3);
    root.add(editButton, 1, 4);
    root.setVgap(15);
    root.setHgap(15);
    root.requestFocus();
    root.setPadding(new Insets(20));
    root.setAlignment(Pos.TOP_RIGHT);

    Rectangle whiteBox = new Rectangle(450, 200);
    whiteBox.setTranslateX(20);
    whiteBox.setLayoutY(50);
    whiteBox.setFill(Color.web("#F6F6F6"));

    Group group = new Group();
    group.getChildren().addAll(whiteBox, root);

    newPane.getChildren().setAll(group);
    newPane.setAlignment(Pos.CENTER);

    editButton.setOnAction((ActionEvent s) -> {
        String name = borrowerNameField.getText();
        String con = newContactField.getText();
        
        int contactInfo = Integer.parseInt(con); // Convert to integer
        Supplier sup = new Supplier(name);
        try {
            admin.editSupplier(sup, contactInfo);
        } catch (IOException ex) {
            ex.printStackTrace();
        }Rectangle whiteBox1 = new Rectangle(450, 200);
       

        if (admin.searchSupplier(name)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("User edited successfully!");
            alert.showAndWait();
             
         borrowerNameField.setText("");
         newContactField.setText("");
         contactField.setText("");

         
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("User not found!");
            alert.showAndWait();
          
         borrowerNameField.setText("");
         newContactField.setText("");
         contactField.setText("");

         
        }
 whiteBox1.setTranslateX(20);
        whiteBox1.setLayoutY(50);
        whiteBox1.setFill(Color.web("#F6F6F6"));
         Group group1 = new Group();
        group1.getChildren().addAll(whiteBox1);
        newPane.getChildren().setAll(group1);
        newPane.setAlignment(Pos.CENTER);
    });
       
       
      
 
});

        
        Button supplierWithMaxRev = new Button("Maxmium Revnue",supRevenue);
        supplierWithMaxRev.setOnAction((ActionEvent e) ->{
              TextArea textArea = new TextArea();
        textArea.setWrapText(true); 
         textArea.setEditable(false);
           textArea.setPrefWidth(360);
        textArea.setPrefHeight(270);
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);
            Supplier sup= new Supplier(admin.getsMaxRevenue());
            admin.displaySupplierInfo(sup);
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            textArea.appendText(outputStream.toString());
            textArea.setPrefWidth(310);

            Label label=new Label("Supplier with maximum revenue:");
                GridPane root = new GridPane();
                label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    root.add(label, 0, 1);
    root.add(textArea, 0, 2);
    root.setVgap(20);
    root.setHgap(20);
    root.requestFocus();
    root.setPadding(new Insets(20));
    root.setAlignment(Pos.TOP_RIGHT);

    Rectangle whiteBox = new Rectangle(450, 200);
    whiteBox.setTranslateX(20);
    whiteBox.setLayoutY(35);
    whiteBox.setFill(Color.web("#F6F6F6"));

    Group group = new Group();
    group.getChildren().addAll(whiteBox, root);
    
    newPane.getChildren().setAll(group);
    newPane.setAlignment(Pos.CENTER);
        });
         Button supplierWithMaxOrders = new Button("Maxmium Orders",supOrders);
        supplierWithMaxOrders.setOnAction((ActionEvent e) ->{
              TextArea textArea = new TextArea();
        textArea.setWrapText(true); 
         textArea.setEditable(false);
           textArea.setPrefWidth(360);
        textArea.setPrefHeight(270);
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);
            Supplier sup= new Supplier(admin.getsMaxOrders());
            admin.displaySupplierInfo(sup);
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            textArea.appendText(outputStream.toString());
            textArea.setPrefWidth(310);
            Label label=new Label("Supplier with maximum Orders:");
                GridPane root = new GridPane();
                label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    root.add(label, 0, 1);
    root.add(textArea, 0, 2);
    root.setVgap(20);
    root.setHgap(20);
    root.requestFocus();
    root.setPadding(new Insets(20));
    root.setAlignment(Pos.CENTER);

    Rectangle whiteBox = new Rectangle(450, 200);
    whiteBox.setTranslateX(20);
    whiteBox.setLayoutY(50);
    whiteBox.setFill(Color.web("#F6F6F6"));

    Group group = new Group();
    group.getChildren().addAll(whiteBox, root);
    
    newPane.getChildren().setAll(group);
    newPane.setAlignment(Pos.CENTER);
        });
        
        Button backButton = new Button("Back");
        backButton.setOnAction((ActionEvent e) -> {
            
            //primaryStage.setScene(scenezeina1);
    primaryStage.setScene(scenezeina1);
   primaryStage.show();
        });
        primaryStage.setTitle("Supplier Functions");
        GridPane root = new GridPane();  
    Label l3=new Label("Supplier Managing");
          root.add( l3, 1, 1);
           root.add(addSupplierButton, 1,3 );
          root.add(removeSupplierButton, 1, 4);
         root.add( editSupplierButton, 1, 5);
        root.add(displayOrdersButton , 1, 6);
         root.add(displaySupplierInfoButton, 1, 7);
        root.add(displaySuppliersButton, 1, 8);
         root.add(supplierWithMaxRev, 1, 9);
         root.add(supplierWithMaxOrders, 1, 10);
          root.add(backButton, 1, 11);


            contentPane.setAlignment(Pos.CENTER);

       
     addSupplierButton.setStyle("-fx-background-color: #044669; -fx-text-fill: white; -fx-border-color: white;");
       removeSupplierButton.setStyle("-fx-background-color: #044669; -fx-text-fill: white; -fx-border-color: white;");
       editSupplierButton.setStyle("-fx-background-color: #044669; -fx-text-fill: white; -fx-border-color: white;");
       displayOrdersButton.setStyle("-fx-background-color: #044669; -fx-text-fill: white; -fx-border-color: white;");
       displaySupplierInfoButton.setStyle("-fx-background-color: #044669; -fx-text-fill: white; -fx-border-color: white;");
       displaySuppliersButton.setStyle("-fx-background-color: #044669; -fx-text-fill: white; -fx-border-color: white;");
       supplierWithMaxRev.setStyle("-fx-background-color: #044669; -fx-text-fill: white; -fx-border-color: white;");
       backButton.setStyle("-fx-background-color: #044669; -fx-text-fill: white; -fx-border-color: white;");
       supplierWithMaxOrders.setStyle("-fx-background-color: #044669; -fx-text-fill: white; -fx-border-color: white;");
        double buttonWidth = 196; 
        double buttonHeight = 30; 
              StackPane rt = new StackPane();
        addSupplierButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        addSupplierButton.setPrefWidth(buttonWidth);
        addSupplierButton.setPrefHeight(buttonHeight);
        removeSupplierButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        removeSupplierButton.setPrefWidth(buttonWidth);
        removeSupplierButton.setPrefHeight(buttonHeight);
        editSupplierButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        editSupplierButton.setPrefWidth(buttonWidth);
        editSupplierButton.setPrefHeight(buttonHeight);
        displayOrdersButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        displayOrdersButton.setPrefWidth(buttonWidth);
        displayOrdersButton.setPrefHeight(buttonHeight);
        displaySupplierInfoButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        displaySupplierInfoButton.setPrefWidth(buttonWidth);
        displaySupplierInfoButton.setPrefHeight(buttonHeight);
        supplierWithMaxOrders.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        supplierWithMaxOrders.setPrefWidth(buttonWidth);
        supplierWithMaxOrders.setPrefHeight(buttonHeight);
         displaySuppliersButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        displaySuppliersButton.setPrefWidth(buttonWidth);
        displaySuppliersButton.setPrefHeight(buttonHeight);
        supplierWithMaxRev.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        supplierWithMaxRev.setPrefWidth(buttonWidth);
        supplierWithMaxRev.setPrefHeight(buttonHeight);
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        backButton.setPrefWidth(buttonWidth);
        backButton.setPrefHeight(buttonHeight);
           l3.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        l3.setTextFill(Color.WHITE);
          root.setHgap(30);
        root.setVgap(17);
         root.setAlignment(Pos.BASELINE_LEFT);
       // root.setLayoutX();
        VBox vb=new VBox(10);
       // vb.getChildren().add(root);
         Rectangle blueBox = new Rectangle(225, 550); 
       blueBox.setFill(Color.web("#044669"));
//        blueBox.setX(20);
//        blueBox.setY(20);

           Group group = new Group();
        group.getChildren().addAll(blueBox,root);
         //group.getChildren().addAll(iv1,iv2,iv3,iv4,iv5);
        vb.getChildren().add(group);
vb.setAlignment(Pos.TOP_LEFT);
//vb.setSpacing( );
       // group.setLayoutX(-443);
      //  group.setLayoutY(-443);
        
        
      //  contentPane.getChildren().add(group);

    
    contentPane.getChildren().addAll(vb,newPane);
    contentPane.setAlignment(Pos.TOP_LEFT);
    mariam=new Scene(contentPane,600,550);
//mariam=new Scene(contentPane, 600, 550);
    primaryStage.setTitle(" Supplier");
    primaryStage.setScene(mariam);
    primaryStage.show();
        
        
        //-------------------------------------------------------------------------------------------------------end of amin
 }
 public void Libscene(Stage primaryStage)
      {
             this.primaryStage=primaryStage;
          
            Group groupremove = new Group();
   Group groupadd = new Group();    
     Group groupsearch = new Group(); 
      Group groupedit = new Group(); 
       Group groupcalc = new Group(); 
      Group grouptota = new Group(); 
           Scene originalScene = primaryStage.getScene();
             GridPane root5=new GridPane();
               GridPane root2=new GridPane();

          
          
                 
                Image i1=new Image("file:D:\\Addadmin.png");////add
                ImageView iv1=new ImageView(i1);
                 iv1.setFitWidth(45);  
                 iv1.setFitHeight(50); 
                 iv1.setTranslateX(-35);    
                 iv1.setLayoutY(-50); 
                 iv1.setTranslateY(85); 
          
          Image i2=new Image("file:D:\\editAdmin.png");//edit
                ImageView iv2=new ImageView(i2);
                 iv2.setFitWidth(46);  
                 iv2.setFitHeight(43); 
                 iv2.setTranslateX(-32);    
                 iv2.setLayoutY(-75); 
                 iv2.setTranslateY(125); 
          
          Image i3=new Image("file:D:\\searchAdmin.png");///search
          ImageView iv3=new ImageView(i3);
                 iv3.setFitWidth(40);  
                 iv3.setFitHeight(55); 
                 iv3.setTranslateX(-33);    
                 iv3.setLayoutY(-80); 
                 iv3.setTranslateY(167); 
                 
                 
           Image i4=new Image("file:D:\\deleteAdmin.png");//remove
          ImageView iv4=new ImageView(i4);
                 iv4.setFitWidth(35);  
                 iv4.setFitHeight(30); 
                 iv4.setTranslateX(-37);    
                 iv4.setLayoutY(-80); 
                 iv4.setTranslateY(207);         
                 
                 
                 
                      Image i5=new Image("file:C:\\Users\\zaina\\OneDrive\\Pictures\\download (6).png");//total
          ImageView iv5=new ImageView(i5);
                 iv5.setFitWidth(40);  
                 iv5.setFitHeight(40); 
                 iv5.setTranslateX(-37);    
                 iv5.setLayoutY(-80); 
                 iv5.setTranslateY(273);  
                 
                 
          
          
                 StackPane rt = new StackPane();
        rt.getChildren().addAll(iv1,iv2,iv3,iv4);
    
          
          
          
          
          
          
          
          
          
          
          
        Button cbo = new Button();
        cbo.setText("Add Librarian");
     setBackground(cbo, Color.web("#044669"));
     cbo.setTextFill(Color.WHITE);
        cbo.setFont(Font.font("System", FontWeight.BOLD, 14));
        cbo.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
       cbo.setPrefWidth(176);
        cbo.setPrefHeight(30);
        
        
        
           Button borrowdetails = new Button();
        borrowdetails.setText("View Term Details");
     setBackground(borrowdetails, Color.web("#044669"));
     borrowdetails.setTextFill(Color.WHITE);
        borrowdetails.setFont(Font.font("System", FontWeight.BOLD, 14));
        borrowdetails.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
       borrowdetails.setPrefWidth(176);
        borrowdetails.setPrefHeight(30);
        
        
        Button btn=new Button();
        btn.setText("Edit Librarian");
          setBackground(btn, Color.web("#044669"));
     btn.setTextFill(Color.WHITE);
        btn.setFont(Font.font("System", FontWeight.BOLD, 14));
      btn.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
    btn.setPrefWidth(176);
    
  
     
       
   
   
      Button bt3=new Button();
     bt3.setText("Remove ALibrarian");
           setBackground(bt3, Color.web("#044669"));
     bt3.setTextFill(Color.WHITE);
        bt3.setFont(Font.font("System", FontWeight.BOLD, 14));
       bt3.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
   bt3.setPrefWidth(176);
   
 
        
        
    
    Button cbo3 = new Button();
   cbo3.setText("Search Librarian");
 setBackground(cbo3, Color.web("#044669"));
     cbo3.setTextFill(Color.WHITE);
        cbo3.setFont(Font.font("System", FontWeight.BOLD, 14));
       cbo3.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
   cbo3.setPrefWidth(176);  
   
    
   GridPane contentadd=new GridPane();
         GridPane content=new GridPane();
         HBox r=new HBox();       
        r.setPrefSize(300,200);

         
       ////////////////1edit 
        
        btn.setOnAction(g->{
  groupremove.getChildren().clear();
    groupadd.getChildren().clear();
      groupsearch.getChildren().clear();
       groupcalc.getChildren().clear();
        grouptota.getChildren().clear();
    
     Label lb=new Label("Enter Librarian's name to edit :"); 
    TextField tf=new TextField();
    Label lb1=new Label("Enter the new value");
     TextField newValueField=new TextField();
    Button editbt=new Button("Edit");
    Label lb2=new Label("Value you want to edit :"); 
    RadioButton name1 = new RadioButton("Name");
    RadioButton password = new RadioButton("Password");
    ToggleGroup fieldToggleGroup = new ToggleGroup();
    name1.setToggleGroup(fieldToggleGroup);
       TextArea textArea = new TextArea();
        textArea.setPrefSize(145, 50);
          editbt.setOnAction(f->{
  
          try {
    String name = tf.getText();
    String field;
    if (name1.isSelected()) {
        field = "name";
    } else if (password.isSelected()) {
        field = "password";
    } else {
        return;
    }
    String newValue = newValueField.getText();

    Librarian editedLibrarian = new Librarian();

    if ("name".equals(field)) {
      
        editedLibrarian.setUserName(newValue);
    } else if ("password".equals(field)) {
   
        editedLibrarian.setPassword(newValue);
    } 

    adminobj.editLibrarian(editedLibrarian);

    ArrayList<Librarian> updatedLibrarians = Librarian.getLibrarians();

    StringBuilder sb = new StringBuilder();
    for (Librarian librarian : updatedLibrarians) {
        sb.append("Librarian Name: ").append(librarian.getUserName()).append("\n");
     
    }

    textArea.setText(sb.toString());

    System.out.println("Librarian Edited Successfully");
} catch (Exception ex) {
    ex.printStackTrace();
}
                     

    });

   
    content.setAlignment(Pos.CENTER);

    HBox buttonBox = new HBox(10, name1, password); 
   HBox Box = new HBox(10, lb1, newValueField); 
    HBox Box2 = new HBox(10, lb, tf); 
     HBox Box1 = new HBox(10,lb2,buttonBox); //choosing field
        content.add(Box1, 0, 2, 3, 3);
          content.add(Box2, 0,0, 1, 1);
     content.add(Box, 0, 6, 1, 6);
      content.add(editbt, 0,9);
    content.add( textArea,0,10);
    buttonBox.setAlignment(Pos.CENTER); 
    content.setHgap(10);
    content.setVgap(10);
   //   r.getChildren().add(content);
       content.requestFocus();
       content.setPadding(new Insets(80));
        content.setAlignment(Pos.CENTER);
                   Rectangle whiteBox = new Rectangle(600, 500);  
             whiteBox.setTranslateX(10);
        whiteBox.setLayoutY(50);
         whiteBox.setFill(Color.web("#F6F6F6")); 
       try{
        groupedit.getChildren().addAll(whiteBox, content);
      
        r.getChildren().add(groupedit);
        }catch(Exception t)
        {
           // System.out.println(t);
        }
        });
        
        
     //////////////////////////2 add  
        cbo.setOnAction(e->{   
 groupremove.getChildren().clear();
    groupedit.getChildren().clear();
      groupsearch.getChildren().clear();
        groupcalc.getChildren().clear();
        grouptota.getChildren().clear();
            Label id=new Label("Enter New Name :");
        Label name=new Label("Enter New ID :");
    Font f=new Font("Arial",14);
    id.setFont(f);
    name.setFont(f);  
        
        TextField idfield=new TextField();
        TextField namefield=new TextField();
        
        Button bt=new Button();
        bt.setText("ADD");
    
      TextArea textArea1 = new TextArea();
      textArea1.setPrefSize(150, 70);
      
      bt.setOnAction(d->{
                
                   String adminid = idfield.getText();
                   String adminame = namefield.getText();
                   Librarian lb = new Librarian(adminid, adminame);
                   adminobj.addLibrarian(lb);
                  
                     //  libobj.saveLibData();    
                  
                  ArrayList<Librarian> updatedLibrarians = Librarian.getLibrarians();
                    StringBuilder updatedLibrarianText = new StringBuilder();
        for (Librarian librarian : updatedLibrarians) {
            updatedLibrarianText.append("Librarian ID: ").append(librarian.getPassword()).append(", Name: ").append(librarian. getUserName()).append("\n");
        }
                   textArea1.setText(updatedLibrarianText.toString());
                   System.out.println("Librarian Added Successfully");
             
      });
  
       contentadd.add(id,0,0);
       contentadd.add(name,0,1);
       contentadd.setVgap(15);
       contentadd.setHgap(8);
       contentadd.add(idfield,1,0);
       contentadd.add(namefield,1,1);
       contentadd.add(bt, 1, 3);  
     contentadd.add(textArea1, 0, 3); 


       r.getChildren().add(contentadd);
       contentadd.requestFocus();
       contentadd.setPadding(new Insets(95));
          Rectangle whiteBox = new Rectangle(600, 500);  
             whiteBox.setTranslateX(10);
        whiteBox.setLayoutY(50);
         whiteBox.setFill(Color.web("#F6F6F6")); 
          try{
    groupadd.getChildren().addAll(whiteBox, contentadd); 
    
    r.getChildren().add(groupadd);
       
        }catch(Exception eo)
        {
          //  System.out.println(eo);
        }
           });
        
        
        
   //////////////////////////////////////////////////////////////////3 search
       cbo3.setOnAction(o->{
 groupcalc.getChildren().clear();
        grouptota.getChildren().clear();
 groupremove.getChildren().clear();
    groupadd.getChildren().clear();
      groupedit.getChildren().clear();
//            Label lb2=new Label("choose what to search with : ");
//            RadioButton name1 = new RadioButton("Name");
//    RadioButton password = new RadioButton("Password");
    Button searchbt=new Button("search");
     TextArea textArea3 = new TextArea();
     textArea3.setPrefSize(145, 50);
    ToggleGroup fieldToggleGroup = new ToggleGroup();

     Label lb1=new Label("Enter the name to search for :");
     TextField ValueField=new TextField();
    
     searchbt.setOnAction(a->{

         String value=ValueField.getText();

     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);
       adminobj.searchLibrarian(value);
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        textArea3.setText(outputStream.toString());
     });
     
  // HBox buttonBox = new HBox(10, name1, password);
   HBox Box = new HBox(10, lb1, ValueField);
  //   HBox buttonBox1 = new HBox(10, lb2,   buttonBox );
   //  root2.add(buttonBox1, 0, 0, 1, 1);
    root2.add(Box, 0, 3, 1, 3);
    root2.add(searchbt,0,7);
    root2.add(textArea3, 0, 8); 
      root2.setVgap(10);
      root2.setVgap(5);
       r.getChildren().add(root2);
       root2.requestFocus();
       root2.setPadding(new Insets(95));
       root2.setAlignment(Pos.CENTER);
                  Rectangle whiteBox = new Rectangle(600, 500);  
             whiteBox.setTranslateX(10);
        whiteBox.setLayoutY(50);
         whiteBox.setFill(Color.web("#F6F6F6")); 
     try{
        groupsearch.getChildren().addAll(whiteBox, root2);
        
        r.getChildren().add(groupsearch);
        }catch(Exception p)
        {
           // System.out.println(p);
            
        }
       
       });
   ////////////////////////////////////////////////////remove admin
   bt3.setOnAction(s->{
 groupcalc.getChildren().clear();
        grouptota.getChildren().clear();
 groupedit.getChildren().clear();
    groupadd.getChildren().clear();
      groupsearch.getChildren().clear();
        Label lb1=new Label("Enter the name to remove :");
     TextField ValueField=new TextField();
     TextArea ta=new TextArea();
     ta.setPrefSize(150, 50);
       Button bt=new Button();
        bt.setText("Remove");

       bt.setOnAction(t->{
           String removename=ValueField.getText();
       
               adminobj.removeLibrarian(removename);
                // Librarian.saveLibData();
             
           ArrayList<Librarian> updatedLibrarians = Librarian.getLibrarians();
                StringBuilder updatedLibrarianText = new StringBuilder();
        for (Librarian librarian : updatedLibrarians) {
            updatedLibrarianText.append("Librarian ID: ").append(librarian.getPassword()).append(", Name: ").append(librarian.getUserName()).append("\n");
        }
                ta.setText(updatedLibrarianText.toString());
           System.out.println("Librarian Removed Successfully");
       });
      
   HBox Box = new HBox(10, lb1, ValueField); 
 
    root5.add(Box, 0, 3, 2, 2);
    root5.add(bt,1,7);
    root5.add(ta, 1, 8); 
      root5.setVgap(10);
      root5.setVgap(5);
       root5.requestFocus();
       root5.setPadding(new Insets(95));
       root5.setAlignment(Pos.CENTER);
           Rectangle whiteBox = new Rectangle(600, 500);  
             whiteBox.setTranslateX(10);
        whiteBox.setLayoutY(50);
         whiteBox.setFill(Color.web("#F6F6F6")); 
     try{
        groupremove.getChildren().addAll(whiteBox, root5);
       
       r.getChildren().add(groupremove);
        }catch(Exception y)
        {
           // System.out.println(y);
        }
      
       
   });
/////////////////////////////////////////////////borrowdetails
borrowdetails.setOnAction(h->{
    
    groupcalc.getChildren().clear();
        grouptota.getChildren().clear();
 groupedit.getChildren().clear();
    groupadd.getChildren().clear();
      groupsearch.getChildren().clear();
      Label ll=new Label();
      ll.setText("Borrowing Details");
          ll.setFont( Font.font("Impact",FontWeight.BOLD,30));
       Button bt=new Button("Display");
     TextArea ta=new TextArea();
     ta.setEditable(false); 
     ta.setPrefSize(250, 70);
     bt.setOnAction(t->{
           try{
        String output = adminobj.specifyBorrowingTermDetails();
        ta.setText(output); 
           }catch(Exception e)
           {
               System.out.println(e);
           }
       });
     VBox bb=new VBox();
 bb.getChildren().addAll(ll,ta,bt);
bb.setAlignment(Pos.CENTER);
       bb.setSpacing(5);
            bb.setTranslateX(100);
         bb.setLayoutY(220);
           Rectangle whiteBox = new Rectangle(600, 500);  
             whiteBox.setTranslateX(10);
        whiteBox.setLayoutY(50);
         whiteBox.setFill(Color.web("#F6F6F6")); 
     try{
        groupremove.getChildren().addAll(whiteBox, bb);
        
       r.getChildren().add(groupremove);
        }catch(Exception y)
        {
           // System.out.println(y);
        }
    
});




         Button backbt=new Button();
   backbt.setText("Back");
   backbt.setOnAction(q->{
     cbo4.getSelectionModel().clearSelection();
       goBack(originalScene);
        cbo4.setValue("Manage");
   });
           GridPane root = new GridPane();    
    Label l3=new Label("LIB Handling");
    l3.setTranslateX(13);
    l3.setTranslateY(24);
  
          root.add(l3, 1, 0);
          root.add(cbo, 1, 5);
         root.add(btn, 1, 6);
        root.add(cbo3, 1, 7);
         root.add(bt3, 1, 8);
 root.add(backbt, 1, 10);
root.add(borrowdetails, 1, 9);
           l3.setFont(Font.font("Arial", FontWeight.BOLD, 23));
        l3.setTextFill(Color.WHITE);
          root.setHgap(25);
        root.setVgap(10);
        
        VBox vb=new VBox(10);
        vb.getChildren().add(root);
      
         Rectangle blueBox = new Rectangle(200, 500);  
         blueBox.setFill(Color.web("#044669")); 
       Group group = new Group();
        group.getChildren().addAll(blueBox, root,rt);
        
         root.setLayoutX(-50);
        root.setLayoutY(20);
        blueBox.setLayoutX(-50);
        blueBox.setLayoutY(0);
        
        r.getChildren().add(group);


  
        Scene scenezeina2 = new Scene(r,800,450);
        
        primaryStage.setTitle("Admin");
        primaryStage.setScene(scenezeina2);
        primaryStage.show();

   
      }
 //HALA BOOK SCENE-------------------------------------------------------------------------------------------------
 
 private GridPane createEditBookContent(Book book){
       GridPane editGrid= new GridPane();
       editGrid.setHgap(10);
       editGrid.setVgap(10);
       
       
       TextField titleField = new TextField(book.getTitle());
       titleField.setPromptText("Title:");
       editGrid.add(new Label("Title:"),0,0);
       editGrid.add(titleField,1,0);
       
       
       TextField authorField = new TextField(book.getAuthor());
       authorField.setPromptText("Author:");
       editGrid.add(new Label("Author:"),0,1);
       editGrid.add(authorField,1,1);
       
       
       TextField categoryField = new TextField(book.getCategory());
       categoryField.setPromptText("Category:");
       editGrid.add(new Label("Category:"),0,2);
       editGrid.add(categoryField,1,2);
       
       return editGrid;
       
   }
   
   
   //bi update el gui
   private void updateBookDetails(Book book, TextInputDialog editDialog ){
       
       book.setTitle(editDialog.getEditor().getText());
       
       TextField authorField = (TextField) editDialog.getDialogPane().lookup(".text-field");
       book.setAuthor(authorField.getText());
       
       
       TextField categoryField = (TextField) editDialog.getDialogPane().lookupAll(".text-field").toArray()[1];
       book.setCategory(categoryField.getText());
       
       
       
   }
   
   
   
   private StackPane createBookPane2(Book book) {
        // Image
        Image bookImage1 = new Image("file:D:\\Desktop\\Univeristy\\Semester 3\\OOP\\book_logo.png");
        ImageView imageView1 = new ImageView(bookImage1);
        imageView1.setFitWidth(imagesize1);
        imageView1.setPreserveRatio(true);

        // Book name label
        Label bookNameLabel1 = new Label(book.getTitle());
        bookNameLabel1.setFont(new Font("Lucida Sans Unicode", 14));
        Label category1= new Label(book.getCategory());
      //  bookNameLabel.setTranslateY(-32);
       
        //author
         Label authorLabel1 = new Label (book.getAuthor());
        //highlight effect on category
        Color highlight1=  getCategoryColor(book.getCategory());
        Region recHighlight1=new Region(); //beyzbt 3ala 7agm el label
        
        recHighlight1.setTranslateY(-6);
        
     //buttons
           Button editbutton = new Button ("Edit");
            Button removebutton = new Button ("Remove");
     
        // recHighlight.widthProperty().bind(category.widthProperty());
        StackPane Highlightpane= new StackPane();
//       Rectangle rec= new Rectangle(35,10);
//       rec.setFill(highlight);
//       
        Highlightpane.getChildren().addAll(category1);
//        Highlightpane.setAlignment(Pos.BOTTOM_CENTER);
        Highlightpane.setTranslateY(-6);
        
        VBox labels = new VBox();
        labels.setSpacing(5);
        labels.getChildren().addAll(bookNameLabel1,authorLabel1,Highlightpane);
        labels.setAlignment(Pos.CENTER);
     // StackPane to hold image and label
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView1,labels ,editbutton,removebutton);
        StackPane.setAlignment(imageView1, Pos.TOP_LEFT);
        StackPane.setAlignment(labels, Pos.CENTER);
    
        StackPane.setAlignment(editbutton, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(removebutton, Pos.BOTTOM_RIGHT);
        
        Insets in = new Insets(10,20,10,20);
       // StackPane.setMargin(imageView,in);
       
       StackPane.setMargin(removebutton,in);
       StackPane.setMargin(editbutton,in);
       // StackPane.setMargin(imageView,in);
        
        
        
        
        

       
      //mouse format
      stackPane.setOnMouseEntered(e->stackPane.setCursor(Cursor.HAND));
      stackPane.setOnMouseExited(e->stackPane.setCursor(Cursor.DEFAULT));
      
        // StackPane.setAlignment(Highlightpane, Pos.BOTTOM_CENTER);
       
        stackPane.setPrefSize(200, 200);
        stackPane.setStyle("-fx-border-color: black;");
        
        // Set white background for each cell
        BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        stackPane.setBackground(background);
        
        //se fixed size for each cell
        stackPane.setMinSize(200,200);
        stackPane.setMaxSize(200,200);
        
        
        editbutton.setOnAction(event->{
            
       TextInputDialog editDialog= new TextInputDialog();
       editDialog.setTitle("Edit Book");
       editDialog.setHeaderText("Edit details for the book");
       
       
        GridPane editGrid= new GridPane();
       editGrid.setHgap(10);
       editGrid.setVgap(10);
       
       
       TextField titleField = new TextField(book.getTitle());
       titleField.setPromptText("Title:");
       editGrid.add(new Label("Title:"),0,0);
       editGrid.add(titleField,1,0);
       
       
       TextField authorField = new TextField(book.getAuthor());
       authorField.setPromptText("Author:");
       editGrid.add(new Label("Author:"),0,1);
       editGrid.add(authorField,1,1);
       
       
       TextField categoryField = new TextField(book.getCategory());
       categoryField.setPromptText("Category:");
       editGrid.add(new Label("Category:"),0,2);
       editGrid.add(categoryField,1,2);
       
       
      editDialog.getDialogPane().setContent(editGrid);
      
       Platform.runLater( ()->     { 
         
                     Screen screen = Screen.getPrimary();
                     double centerX= screen.getBounds().getWidth()/2;
                     double centerY= screen.getBounds().getHeight()/2;
        
        
                     editDialog.setX(centerX- editDialog.getWidth()/2);
                     editDialog.setY(centerY- editDialog.getHeight()/2);
        
        
        
                    });
      
     //  editDialog.getDialogPane().setContent(createEditBookContent(book));
       
     
     
     
     
     
       editDialog.getDialogPane().setPrefSize(300,300);
       
       editDialog.setGraphic(null);
     
       Optional<String> result1 = editDialog.showAndWait();
       if (result1.isPresent()){
           
           
           
            book.setTitle(titleField.getText());
            book.setAuthor(authorField.getText());
            book.setCategory(categoryField.getText());




           bookNameLabel1.setText(book.getTitle());
           authorLabel1.setText(book.getAuthor());
           category1.setText(book.getCategory());
           
           
                 
           try{
               
               Book.saveBooks();
           }catch(IOException e){
               
               System.out.println("Error saving books:"+ e.getMessage());
               
           }
           
           
       }
       
       
   });
        
        
        
        
        
        
        
        
        
        
        
        removebutton.setOnAction(event->{
     Alert remove = new Alert(Alert.AlertType.CONFIRMATION);
     //setting the dialouge in the middle
     Platform.runLater( ()->     { 
         
        Screen screen = Screen.getPrimary();
        double centerX= screen.getBounds().getWidth()/2;
        double centerY= screen.getBounds().getHeight()/2;
        
        
        remove.setX(centerX- remove.getWidth()/2);
        remove.setY(centerY- remove.getHeight()/2);
        
        
        
     }  );
     remove.setTitle("Remove Book");
     remove.setHeaderText(null);
     remove.setContentText("Are you sure you want to delete this book??");
     Optional<ButtonType> result= remove.showAndWait();
     if(result.isPresent()&&result.get()==ButtonType.OK){
         Book.removeBook(book);
         try{
             Book.saveBooks();
             ((GridPane) stackPane.getParent()).getChildren().remove(stackPane);
             
         }catch (IOException e){
             
             System.out.println("Error saving books:"+e.getMessage());
         }
         
     }
     
     
     
     
     
     
     });
        
        
        
        
        
        //stackPane.setOnMouseClicked(e ->showLibraryScene(book,(Stage) stackPane.getScene().getWindow()));
        // Display all information about the book when clicked
  
  
        return stackPane;   
    }

 //------------------------------------------------------------------------------------------------------end of hala
 public void adminscene (Stage primaryStage){
     //Admin scene--------------------------------------------------------------------------------------------------
        
        this.primaryStage=primaryStage;
  Group groupremove = new Group();
   Group groupadd = new Group();    
     Group groupsearch = new Group(); 
      Group groupedit = new Group(); 
       Group groupcalc = new Group(); 
      Group grouptota = new Group(); 
       
       
               GridPane root5=new GridPane();
                GridPane root2=new GridPane();
//                Image i=new Image("");
//                ImageView iv=new ImageView(i);
//                      iv.setFitWidth(50);  
//        iv.setFitHeight(50); 
//        iv.setTranslateX(30);    
//        iv.setLayoutY(-30); 
//          iv.setTranslateY(380); 
          
          
          
                 
                Image i1=new Image("file:D:\\Addadmin.png");
                ImageView iv1=new ImageView(i1);
                 iv1.setFitWidth(45);  
                 iv1.setFitHeight(50); 
                 iv1.setTranslateX(-35);    
                 iv1.setLayoutY(-50); 
                 iv1.setTranslateY(112); 
          
          Image i2=new Image("file:D:\\editAdmin.png");
                ImageView iv2=new ImageView(i2);
                 iv2.setFitWidth(46);  
                 iv2.setFitHeight(43); 
                 iv2.setTranslateX(-32);    
                 iv2.setLayoutY(-75); 
                 iv2.setTranslateY(152); 
          
          Image i3=new Image("file:D:\\searchAdmin.png");
          ImageView iv3=new ImageView(i3);
                 iv3.setFitWidth(40);  
                 iv3.setFitHeight(55); 
                 iv3.setTranslateX(-33);    
                 iv3.setLayoutY(-80); 
                 iv3.setTranslateY(196); 
                 
                 
           Image i4=new Image("file:D:\\deleteAdmin.png");
          ImageView iv4=new ImageView(i4);
                 iv4.setFitWidth(35);  
                 iv4.setFitHeight(30); 
                 iv4.setTranslateX(-35);    
                 iv4.setLayoutY(-80); 
                 iv4.setTranslateY(237);         
                 
                 
                 
                      Image i5=new Image("file:D:\\TotalRevenue.png");
          ImageView iv5=new ImageView(i5);
                 iv5.setFitWidth(40);  
                 iv5.setFitHeight(40); 
                 iv5.setTranslateX(-37);    
                 iv5.setLayoutY(-80); 
                 iv5.setTranslateY(280);  
                 
                 
          Image i6=new Image("file:D:\\doorexit.png");
          ImageView iv6=new ImageView(i6);
                 iv6.setFitWidth(45);  
                 iv6.setFitHeight(40); 
                 iv6.setTranslateX(-33);    
                 iv6.setLayoutY(-80); 
                 iv6.setTranslateY(368);
          
                 StackPane rt = new StackPane();
     rt.getChildren().addAll(iv1,iv2,iv3,iv4,iv5,iv6);
    
        Button cbo = new Button();
        cbo.setText("Add Admin");
     setBackground(cbo, Color.web("#044669"));
     cbo.setTextFill(Color.WHITE);
        cbo.setFont(Font.font("System", FontWeight.BOLD, 14));
        cbo.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
       cbo.setPrefWidth(176);
        cbo.setPrefHeight(30);
        
        Button btn=new Button();
        btn.setText("Edit Admin");
          setBackground(btn, Color.web("#044669"));
     btn.setTextFill(Color.WHITE);
        btn.setFont(Font.font("System", FontWeight.BOLD, 14));
      btn.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
    btn.setPrefWidth(176);
    
      
      Button bt1=new Button();
     bt1.setText("View Total revenue");
        setBackground(bt1, Color.web("#044669"));
     bt1.setTextFill(Color.WHITE);
        bt1.setFont(Font.font("System", FontWeight.BOLD, 14));
       bt1.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
   bt1.setPrefWidth(176);
     
       
   
   
      Button bt3=new Button();
     bt3.setText("Remove Admin");
           setBackground(bt3, Color.web("#044669"));
     bt3.setTextFill(Color.WHITE);
        bt3.setFont(Font.font("System", FontWeight.BOLD, 14));
       bt3.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
   bt3.setPrefWidth(176);
   
   
   
   
       Button bt2=new Button();
     bt2.setText("View Average revenue");
           setBackground(bt2, Color.web("#044669"));
     bt2.setTextFill(Color.WHITE);
        bt2.setFont(Font.font("System", FontWeight.BOLD, 14));
       bt2.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
   bt2.setPrefWidth(176);
    
       Button btexit=new Button(); 
     btexit.setText("Exit");
           setBackground(btexit, Color.web("#044669"));
     btexit.setTextFill(Color.WHITE);
       btexit.setFont(Font.font("System", FontWeight.BOLD, 14));
      btexit.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
  btexit.setPrefWidth(176);
  
  btexit.setOnAction(e->{
  primaryStage.setScene(scenehala1);
  
  });
   btexit.setOnMouseEntered(e->btexit.setCursor(Cursor.HAND));
   btexit.setOnMouseExited(e->btexit.setCursor(Cursor.DEFAULT));

     cbo4=new ComboBox<>(FXCollections.observableArrayList(
      "Borrower","Supplier","Book","Librarian"));
        cbo4.setValue("Manage");
       
       cbo4.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
   cbo4.setPrefWidth(176);
        cbo4.setOnAction(n->{
            String selected=cbo4.getValue();
           if("Librarian".equals(selected))
           {
               Libscene(primaryStage);
           }
           else if("Borrower".equals(selected))
           {
//               Libscene();
           }
           else if("Book".equals(selected))
           {
             //  Libscene(); 
               primaryStage.setScene(scenehala2);
               primaryStage.setMaximized(true);
           }
           else if("Supplier".equals(selected))
           {
              supplierstage(primaryStage);
           }
        });
        
    
    Button cbo3 = new Button();
   cbo3.setText("Search Admin");
 setBackground(cbo3, Color.web("#044669"));
     cbo3.setTextFill(Color.WHITE);
        cbo3.setFont(Font.font("System", FontWeight.BOLD, 14));
       cbo3.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
   cbo3.setPrefWidth(176);
   
   StackPane animatedpic= new StackPane();
   
   
   GridPane contentadd=new GridPane();
         GridPane content=new GridPane();
         HBox r=new HBox();       
        r.setPrefSize(300,200);
           Label tot=new Label();
         Label tot1=new Label();
         ////////////////////total rev
         bt1.setOnMouseEntered(e->bt1.setCursor(Cursor.HAND));
         bt1.setOnMouseExited(e->bt1.setCursor(Cursor.DEFAULT));
         bt1.setOnAction(u->{
             Image coin_pic= new Image("file:D:\\coin.png");
   ImageView coinview= new ImageView(coin_pic);
   coinview.setFitWidth(50);
   coinview.setPreserveRatio(true);
   TranslateTransition animate= new TranslateTransition(Duration.seconds(10),coinview);
   animate.setByX(200);
   animate.setCycleCount(TranslateTransition.INDEFINITE);
   animate.setAutoReverse(true);
   animate.play();
   
   animatedpic.getChildren().add(coinview);
   animatedpic.setAlignment(Pos.BOTTOM_RIGHT);
   
               tot.setText("Total Revenue");
               tot.setAlignment(Pos.CENTER);
         tot.setFont( Font.font("Impact",FontWeight.BOLD,30));
              groupremove.getChildren().clear();
    groupadd.getChildren().clear();
      groupsearch.getChildren().clear();
       groupedit.getChildren().clear();
       groupcalc.getChildren().clear();
         Button b=new Button();
              b.setText("Calculate Total.");
              b.setOnMouseEntered(e->b.setCursor(Cursor.HAND));
         b.setOnMouseExited(e->b.setCursor(Cursor.DEFAULT));
              b.setOnAction(l->{
                   FileManagment browerer=new FileManagment();
                       List<User> k=browerer.readFromFile();
             double av=adminobj.getTotalRevenue();
               tot1.setText( av+"$");
               tot1.setAlignment(Pos.CENTER);
                 tot1.setFont( Font.font("Arial",FontWeight.BOLD,30));
                 tot1.setTextFill(Color.RED);
              });
          Rectangle whiteBox = new Rectangle(600, 500);  
            
              VBox v=new VBox();
              v.getChildren().addAll(tot,tot1,b,animatedpic); 
              v.setAlignment(Pos.CENTER);
               v.setLayoutX(200);
             v.setLayoutY(200);
             v.setSpacing(20);
             
        whiteBox.setLayoutY(50);
         whiteBox.setFill(Color.web("#F6F6F6")); 
         try{
    grouptota.getChildren().addAll(whiteBox, v);
        
    
    r.getChildren().add(grouptota);
      
        }catch(Exception eo)
        {
          //  System.out.println(eo);
        }
       
         });
         
         
         /////////////////average rev
         Label avgr=new Label();
         Label avgr1=new Label();
         bt2.setOnMouseEntered(e->bt2.setCursor(Cursor.HAND));
         bt2.setOnMouseExited(e->bt2.setCursor(Cursor.DEFAULT));
          bt2.setOnAction(u->{
                   Image coin_pic= new Image("file:D:\\coin.png");
   ImageView coinview= new ImageView(coin_pic);
   coinview.setFitWidth(50);
   coinview.setPreserveRatio(true);
   TranslateTransition animate= new TranslateTransition(Duration.seconds(10),coinview);
   animate.setByX(200);
   animate.setCycleCount(TranslateTransition.INDEFINITE);
   animate.setAutoReverse(true);
   animate.play();
   animatedpic.setAlignment(Pos.BOTTOM_CENTER);
   animatedpic.getChildren().add(coinview);
   //animatedpic.setAlignment(Pos.BOTTOM_RIGHT);
         avgr1.setText("Average Revenue");
         avgr1.setFont( Font.font("Impact",FontWeight.BOLD,30));
         groupremove.getChildren().clear();
         groupadd.getChildren().clear();
         groupsearch.getChildren().clear();
         groupedit.getChildren().clear();
        grouptota.getChildren().clear();
              Button b=new Button();
              b.setText("Calculate Avg.");
              b.setOnMouseEntered(e->b.setCursor(Cursor.HAND));
              b.setOnMouseExited(e->b.setCursor(Cursor.DEFAULT));
              b.setOnAction(l->{
                       FileManagment browerer=new FileManagment();
                       List<User> list=browerer.readFromFile(); 

                 double averageRevenue = adminobj.getAverageRevenue();
                 String Average = String.format("%.2f", averageRevenue);

                 System.out.println("Average Revenue: " + Average);

                 avgr.setText( Average+"$");
                 avgr.setFont( Font.font("Arial",FontWeight.BOLD,30));
                 avgr.setTextFill(Color.RED);
              });
           
             Rectangle whiteBox = new Rectangle(600, 500);  
             whiteBox.setTranslateX(10);
                VBox v2=new VBox();
              v2.getChildren().addAll(avgr1,avgr,b);
              v2.setAlignment(Pos.CENTER);
                v2.setLayoutX(200);
             v2.setLayoutY(200);
             v2.setSpacing(20);
        whiteBox.setLayoutY(50);
         whiteBox.setFill(Color.web("#F6F6F6")); 
         try{
    groupcalc.getChildren().addAll(whiteBox, v2,animatedpic);
        
    
    r.getChildren().add(groupcalc);
      
        }catch(Exception eo)
        {
          //  System.out.println(eo);
        }
});

       ////////////////1edit 
        
        btn.setOnAction(g->{
            
  groupremove.getChildren().clear();
    groupadd.getChildren().clear();
      groupsearch.getChildren().clear();
       groupcalc.getChildren().clear();
        grouptota.getChildren().clear();
    
     Label lb=new Label("Enter Admin's name to edit :"); 
    TextField tf=new TextField();
    Label lb1=new Label("Enter the new value");
     TextField newValueField=new TextField();
    Button editbt=new Button("Edit");
    Label lb2=new Label("Value you want to edit :"); 
    RadioButton name1 = new RadioButton("Name");
    RadioButton password = new RadioButton("Password");
    ToggleGroup fieldToggleGroup = new ToggleGroup();
    name1.setToggleGroup(fieldToggleGroup);
       TextArea textArea = new TextArea();
       textArea.setEditable(false);
        textArea.setPrefSize(145, 50);
          editbt.setOnAction(f->{
  
            try {
                String Name = tf.getText();
                String Field;
                if (name1.isSelected()) {
                    Field = "name";
                } else if (password.isSelected()) {
                    Field = "password";
                } else{
                    return;
                }
                String newValue = newValueField.getText();

    adminobj.editadmin(Name, Field, newValue);

    adminobj.saveAllAdminsToFile();

String admintext=adminobj.displayAdminsFromFile();
textArea.setText(admintext);
System.out.println("Admin Edited Successfully");
 } catch (IOException |  ClassNotFoundException ex) {
                 ex.printStackTrace();
            }
                     

    });

   
    content.setAlignment(Pos.CENTER);

    HBox buttonBox = new HBox(10, name1, password); 
   HBox Box = new HBox(10, lb1, newValueField); 
    HBox Box2 = new HBox(10, lb, tf); 
     HBox Box1 = new HBox(10,lb2,buttonBox); //choosing field
        content.add(Box1, 0, 2, 3, 3);
          content.add(Box2, 0,0, 1, 1);
     content.add(Box, 0, 6, 1, 6);
      content.add(editbt, 0,9);
    content.add( textArea,0,10);
    buttonBox.setAlignment(Pos.CENTER); 
    content.setHgap(10);
    content.setVgap(10);
   //   r.getChildren().add(content);
       content.requestFocus();
       content.setPadding(new Insets(80));
        content.setAlignment(Pos.CENTER);
                   Rectangle whiteBox = new Rectangle(450, 250);  
             whiteBox.setTranslateX(10);
        whiteBox.setLayoutY(50);
         whiteBox.setFill(Color.web("#F6F6F6")); 
     
        groupedit.getChildren().addAll(whiteBox, content);
        try{
        r.getChildren().add(groupedit);
        }catch(Exception t)
        {
          //  System.out.println(t);
        }
        });
        
        
     //////////////////////////2 add  
        cbo.setOnAction(e->{   
 groupremove.getChildren().clear();
    groupedit.getChildren().clear();
      groupsearch.getChildren().clear();
        groupcalc.getChildren().clear();
        grouptota.getChildren().clear();
            Label id=new Label("Enter New Name :");
        Label name=new Label("Enter New ID :");
    Font f=new Font("Arial",14);
    id.setFont(f);
    name.setFont(f);  
        
        TextField idfield=new TextField();
        TextField namefield=new TextField();
        
        Button bt=new Button();
        bt.setText("ADD");
    
      TextArea textArea1 = new TextArea();
      textArea1.setPrefSize(150, 70);
       textArea1.setEditable(false);
      bt.setOnAction(d->{
                 try {
                   String adminid = idfield.getText();
                   String adminame = namefield.getText();
                   Admin ad = new Admin(adminid, adminame);
                   adminobj.addAdmin(ad);
                   try{
                       adminobj.saveAllAdminsToFile();    
                     }catch(IOException ei){
                       System.out.println(ei);
                   }
                   String admintext=adminobj.displayAdminsFromFile();
                   textArea1.setText(admintext);
                   System.out.println("Admin Added Successfully");
                  }catch(IOException ex)
                  {
                    System.out.println(ex);
                  }catch(ClassNotFoundException eo)
                 {
                   System.out.println(eo);
                 }
      });
     
       contentadd.add(id,0,0);
       contentadd.add(name,0,1);
       contentadd.setVgap(15);
       contentadd.setHgap(8);
       contentadd.add(idfield,1,0);
       contentadd.add(namefield,1,1);
       contentadd.add(bt, 1, 3);  
     contentadd.add(textArea1, 0, 3); 


       r.getChildren().add(contentadd);
       contentadd.requestFocus();
       contentadd.setPadding(new Insets(95));
          Rectangle whiteBox = new Rectangle(450, 200);  
             whiteBox.setTranslateX(10);
        whiteBox.setLayoutY(50);
         whiteBox.setFill(Color.web("#F6F6F6")); 
     try{
         groupadd.getChildren().addAll(whiteBox, contentadd);
       
    
    r.getChildren().add(groupadd);
       
        }catch(Exception eo)
        {
           // System.out.println(eo);
        }
           });
        
        
        
   //////////////////////////////////////////////////////////////////3 search
       cbo3.setOnAction(o->{
 groupcalc.getChildren().clear();
        grouptota.getChildren().clear();
 groupremove.getChildren().clear();
    groupadd.getChildren().clear();
      groupedit.getChildren().clear();
            Label lb2=new Label("choose what to search with : ");
            RadioButton name1 = new RadioButton("Name");
    RadioButton password = new RadioButton("Password");
    Button searchbt=new Button("search");
     TextArea textArea3 = new TextArea();
     textArea3.setEditable(false);
     textArea3.setPrefSize(145, 50);
    ToggleGroup fieldToggleGroup = new ToggleGroup();
    name1.setToggleGroup(fieldToggleGroup);
    password.setToggleGroup(fieldToggleGroup);
     Label lb1=new Label("enter the value to search for :");
     TextField ValueField=new TextField();
    
     searchbt.setOnAction(a->{
         String field="";
         String value=ValueField.getText().trim();
         if(name1.isSelected()){ 
             field="name";
         }else if(password.isSelected())
         {
             field="id";
         }
         List<User> results = adminobj.searchAdmin(field, value);
         StringBuilder resultText=new StringBuilder();
          if (results.isEmpty()) {
        textArea3.setText("No Admin found");
    } else {
         for(User user:results)
         {
           resultText.append(user.toString()).append("\n");
         }
          textArea3.setText(resultText.toString());
          }
     });
     
   HBox buttonBox = new HBox(10, name1, password);
   HBox Box = new HBox(10, lb1, ValueField);
     HBox buttonBox1 = new HBox(10, lb2,   buttonBox );
     root2.add(buttonBox1, 0, 0, 1, 1);
    root2.add(Box, 0, 3, 1, 3);
    root2.add(searchbt,0,7);
    root2.add(textArea3, 0, 8); 
      root2.setVgap(10);
      root2.setVgap(5);
       r.getChildren().add(root2);
       root2.requestFocus();
       root2.setPadding(new Insets(95));
       root2.setAlignment(Pos.CENTER);
                  Rectangle whiteBox = new Rectangle(450, 200);  
             whiteBox.setTranslateX(10);
        whiteBox.setLayoutY(50);
         whiteBox.setFill(Color.web("#F6F6F6")); 
      try{
        groupsearch.getChildren().addAll(whiteBox, root2);
       
        r.getChildren().add(groupsearch);
        }catch(Exception p)
        {
          //  System.out.println(p);
            
        }
       
       });
   ////////////////////////////////////////////////////remove admin
   bt3.setOnAction(s->{
 groupcalc.getChildren().clear();
        grouptota.getChildren().clear();
 groupedit.getChildren().clear();
    groupadd.getChildren().clear();
      groupsearch.getChildren().clear();
        Label lb1=new Label("Enter the name to remove :");
     TextField ValueField=new TextField();
     TextArea ta=new TextArea();
     ta.setEditable(false);
     ta.setPrefSize(150, 50);
       Button bt=new Button();
        bt.setText("Remove");

       bt.setOnAction(t->{
           String removename=ValueField.getText();
           try{
               adminobj.removeAdmin(removename);
               adminobj.saveAllAdminsToFile();
               String adremove=adminobj.displayAdminsFromFile();
               ta.setText(adremove);  
           }catch(IOException | ClassNotFoundException e){
               System.out.println("failed to read admins");
               e.printStackTrace();
           }
           System.out.println("Admin Removed Successfully");
       });
      
   HBox Box = new HBox(10, lb1, ValueField); 
 
    root5.add(Box, 0, 3, 2, 2);
    root5.add(bt,1,7);
    root5.add(ta, 1, 8); 
      root5.setVgap(10);
      root5.setVgap(5);
       root5.requestFocus();
       root5.setPadding(new Insets(95));
       root5.setAlignment(Pos.CENTER);
           Rectangle whiteBox = new Rectangle(450, 200);  
             whiteBox.setTranslateX(10);
        whiteBox.setLayoutY(50);
         whiteBox.setFill(Color.web("#F6F6F6")); 
      try{
        groupremove.getChildren().addAll(whiteBox, root5);
       
       r.getChildren().add(groupremove);
        }catch(Exception y)
        {
           // System.out.println(y);
        }
      
       
   });

        
           GridPane Root = new GridPane();    
    Label l3=new Label("User Handling");
     l3.setTranslateX(8);
        l3.setTranslateY(20);
          Root.add(l3, 1, 0);
           Root.add(cbo4, 1,4 );
          Root.add(cbo, 1, 5);
         Root.add(btn, 1, 6);
        Root.add(cbo3, 1, 7);
         Root.add(bt3, 1, 8);
        Root.add(bt1, 1, 9);
         Root.add(bt2, 1, 10);
         Root.add(btexit, 1, 11);

        l3.setFont(Font.font("Arial", FontWeight.BOLD, 23));
        l3.setTextFill(Color.WHITE);
        Root.setHgap(25);
        Root.setVgap(10);
        
        VBox vb=new VBox(10);
        vb.getChildren().add(Root);
      
         Rectangle blueBox = new Rectangle(200, 500);  
         blueBox.setFill(Color.web("#044669")); 
         Group group = new Group();
         group.getChildren().addAll(blueBox, Root,rt);
        
        Root.setLayoutX(-50);
        Root.setLayoutY(20);
        blueBox.setLayoutX(-50);
        blueBox.setLayoutY(0);
        
        r.getChildren().add(group);


  
    Scene scenezeina1 = new Scene(r, 800,450 );  //width x height
        
        primaryStage.setTitle("Admin");
        primaryStage.setScene(scenezeina1);
        primaryStage.show();
        
        
        
        //---------------------------------------------------------------------------------------------------end of admin
        
 }
    
     @Override
    public void start(Stage stage) {
        //SCENES USED
        
        
        //SCENE SHAHD ------------------------------------------------------------------------------------(tani scene)
      
        HBox rootShahd = new HBox();

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
         errorText= new Text ();
        errorText.setFill(Color.MAROON);
       
        
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

             signIn(getEntryType(), username, password);
             
             //case borrower
             if(Authentication==true && getEntryType()==1){
                //user info 
                user= new Borrower(username,password); 
               stage.setScene(sceneBorrower);
               stage.setMaximized(true);
               
               errorText.setText("");
           } else{
                  errorText.setText("*Username is not found please create account");
             }
           if(Authentication==true && getEntryType()==3){
               adminscene(stage);
//               Stage adminStage= new Stage();
//              adminStage=primaryStage;
//             adminStage.setTitle("Admin");
//             adminStage.setScene(scenezeina1);
//             adminStage.show();

           }
           else{
                  errorText.setText("*Username is not found please create account");
             }
             
             if(Authentication==true && getEntryType()==2){
            
            showLibrarianPage(stage);
            //primaryStage.setMaximized(true);


           }
           else{
                  errorText.setText("*Username is not found please create account");
             }

        });
        
        
        Button Sign_upButton = new Button("Sign up");
        Sign_upButton.setOnAction(e->{
            String username= usernameField.getText();
            String password= passwordField.getText();
        
           signUp(getEntryType(), username, password);
           
          //case borrower
           if(Authentication==true && getEntryType()==1){
               user= new Borrower(username,password);
               stage.setScene(sceneBorrower);
               stage.setMaximized(true);
               errorText.setText("");
           }
           if(Authentication==true && getEntryType()==3){
             adminscene(stage);
             
           //stage.setTitle("Library system Admin");
           //stage.setMaximized(true);
           }
           
           
           //case Librarian
           
           if(Authentication==true && getEntryType()==2){
            
            showLibrarianPage(stage);
            primaryStage.setMaximized(true);


           }
           else{
                  errorText.setText("*Username is not found please create account");
             }

        });
        
        
        Button sign_backButton = new Button("Back"); //handled fe scene hala (ba3d this scene)
        HBox buttons= new HBox();
        buttons.getChildren().addAll(Sign_inButton,Sign_upButton,sign_backButton);
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);
       
        rightSide.getChildren().addAll(labelVbox,textfieldVbox,errorText, buttons);
        
        //spacing between el vbox, hay7t fe hbox
        HBox.setMargin(rightSide, new Insets(20,0,0,65));
        rootShahd.getChildren().addAll(leftSide, rightSide);
       //------------------------------------------------------------------------------------------------------END OF SHAHD
       
       
       
        Scene Sign_details_Scene = new Scene(rootShahd, 700, 450);
        
        
        //SCENE HALA ------------------------------------------------------------------------------------------(awl scene)
        
        Button borrowerbtn = new Button("BORROWER");
        Button librarianbtn = new Button("LIBRARIAN");
        Button adminbtn = new Button("ADMIN");
        
        adminbtn.setOnMouseEntered(e->adminbtn.setCursor(Cursor.HAND));
        adminbtn.setOnMouseExited(e->adminbtn.setCursor(Cursor.DEFAULT));
        
        librarianbtn.setOnMouseEntered(e->librarianbtn.setCursor(Cursor.HAND));
        librarianbtn.setOnMouseExited(e->librarianbtn.setCursor(Cursor.DEFAULT));
        
        borrowerbtn.setOnMouseEntered(e->borrowerbtn.setCursor(Cursor.HAND));
        borrowerbtn.setOnMouseExited(e->borrowerbtn.setCursor(Cursor.DEFAULT));
        
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
        
        scenehala1 = new Scene(roothala, 500, 250);
       
        stage.setScene(scenehala1);
        //---------------------------------------------- END OF HALA
        
        //back button handled here (to return from scene2 to scene1
        sign_backButton.setOnAction(e->{
          stage.setScene(scenehala1);
          stage.setTitle("Library");
        });

        
        
        
        
        //Borrower scene--------------------------------------------------------
                Book book1= new Book("The Visiting Privilege","Author: Joy Williams","fiction");
        Book.addBook(book1);
        
        Book book2= new Book("The Hunger Games","Suzanne Collins","fiction");
        Book.addBook(book2);
        
        Book book3= new Book("Becoming","Michelle Obama","non-fiction");
        Book.addBook(book3);
        
        Book book4= new Book("Pride and Prejudice","Jane Austen","fiction");
        Book.addBook(book4);
        
        Book book5= new Book("Breath Becomes air","Paul Kalanithi","science");
        Book.addBook(book5);
        
        Book book6= new Book("cinderella","disney","fiction");
        Book.addBook(book6);
        
        Book book7= new Book("Atomic Habits","James Clear","science");
        Book.addBook(book7);
        
        Book book8= new Book("Hamlet","William Shakespeare","fiction");
        Book.addBook(book8);
        
        Book book9= new Book("Educated","Tara Westover","non-fiction");
        Book.addBook(book9);
        
        Book book10= new Book("Single at heart","Bella DePaulo","non-fiction");
        Book.addBook(book10);
        //Book.removeBook(book9);
        
        
        Book book11= new Book("Outliers","Malcolm Gladwell","science");
        Book.addBook(book11);
          VBox vboxbooks = new VBox();
        vboxbooks.setAlignment(Pos.CENTER);

        // Left side
        VBox leftSide_b = new VBox();
        leftSide_b.setMinWidth(200);
        leftSide_b.setStyle("-fx-background-color: #044669;");
        
        //left sid buttons
        Button viewBook= new Button("Books");
        viewBook.setAlignment(Pos.CENTER_RIGHT);
        Button dashboard= new Button ("User Account");
        dashboard.setAlignment(Pos.CENTER_RIGHT);
        Button awelScene = new Button("Exit");
        awelScene.setAlignment(Pos.CENTER_RIGHT);
        leftSide_b.getChildren().addAll(viewBook,dashboard,awelScene);
        leftSide_b.setSpacing(40);
        leftSide_b.setAlignment(Pos.CENTER);
        
        //Button formats (left Side)
       //handled to open shahd's scene when nagivating through the system
       
       viewBook.setStyle("-fx-background-color: #044669; -fx-text-fill: white; -fx-border-color: white;");
       setBackground(viewBook, Color.web("#044669"));
       viewBook.setTextFill(Color.WHITE);
       viewBook.setFont(Font.font("System", FontWeight.BOLD, 14));
       viewBook.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
       viewBook.setPrefWidth(150);
       //Button handle
       viewBook.setOnAction(e->stage.setScene(sceneBorrower));
       
       //Will be handled to open users scene (Hala's scene)
       dashboard.setStyle("-fx-background-color: #044669; -fx-text-fill: white; -fx-border-color: white;");
       setBackground(dashboard, Color.web("#044669"));
       dashboard.setTextFill(Color.WHITE);
       dashboard.setFont(Font.font("System", FontWeight.BOLD, 14));
       dashboard.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
       dashboard.setPrefWidth(150);
       
       dashboard.setOnAction(e->{
       stage.setScene(scenehala3);
       });
       
       //will be handled to open awel scene (Hala's scene)
       awelScene.setStyle("-fx-background-color: #044669; -fx-text-fill: white; -fx-border-color: white;");
       setBackground(awelScene, Color.web("#044669"));
       awelScene.setTextFill(Color.WHITE);
       awelScene.setFont(Font.font("System", FontWeight.BOLD, 14));
       awelScene.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
       awelScene.setPrefWidth(150);
     
       
       //mouse format (left side)
        viewBook.setOnMouseEntered(e->viewBook.setCursor(Cursor.HAND));
        viewBook.setOnMouseExited(e->viewBook.setCursor(Cursor.DEFAULT));
        dashboard.setOnMouseEntered(e->dashboard.setCursor(Cursor.HAND));
        dashboard.setOnMouseExited(e->dashboard.setCursor(Cursor.DEFAULT)); 
        awelScene.setOnMouseEntered(e->awelScene.setCursor(Cursor.HAND));
        awelScene.setOnMouseExited(e->awelScene.setCursor(Cursor.DEFAULT));
        
        awelScene.setOnAction(e->{
            System.out.println();
            System.out.println();
            System.out.println("user borrowing detail:");
            System.out.println();
            FileManagment brow= new FileManagment();
            List<User> b= brow.readFromFile();
            Borrower ba= new Borrower();
            Librarian_Mgn lib= new  Librarian_Mgn();
           brow.printAllToFile(user.getName());
          user.addTo(usernameField.getText(),passwordField.getText());
           //ba.removeBorrowerFromFile("sama mohamed");
       
           Librarian_Mgn.calculatePayment(user);
            System.out.println( user.search("namama"));
            // Borrower.setBorrow_count(count_borrow);
            
             user.numberOfBorrowperUser(user);
             stage.setScene(scenehala1);
        });
        
        // Load/ read books from file
        Book.loadBooks();
        try{
        Book.saveBooks();
        }catch(IOException e){
            System.out.println(e);
        }
        List<Book> books = Book.getBooks();
        
       

        // Grid & scroll
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(30);
        
        

        // this is where el grid (each cell) haytmli with book information
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            StackPane bookPane = createBookPane(book, book.getCategory()); //leih ba3t el book category?
            grid.add(bookPane, i % 3, i / 3);
        }
        //scroll pane to hold the grid and maintain its size
        ScrollPane scrollpane = new ScrollPane(grid);
        scrollpane.setFitToHeight(true);
        scrollpane.setFitToWidth(true);
        scrollpane.setBackground(Background.EMPTY);
        
        
        //Vbox ha7ot feiha el combo  box (filter) fo2 el gridpane
        ObservableList<String> categories= FXCollections.observableArrayList("fiction","non-fiction","science","No filter");
        ComboBox filter= new ComboBox(categories);
        filter.setPromptText("Filter");
        VBox filterCombo= new VBox(filter);
        filterCombo.setAlignment(Pos.TOP_RIGHT);
        VBox.setMargin(filterCombo, new Insets(0,30,0,20));
        
         filter.setOnAction(e -> {
        String selectedCategory = (String) filter.getValue();
        updateDisplayedBooks(selectedCategory);
        
    });
        
        
        //vbox to hold el combo box w ta7tiha el books
        
        //ha7ot el combo box & search text field fo2 fel top border 
        TextField searchfield= new TextField();
        searchfield.setPromptText("Search book");
        
        //TextField handle
        searchfield.setOnKeyPressed(e->{
           if(e.getCode()==KeyCode.ENTER){
            System.out.println( Book.searchBook(searchfield.getText()));
           
            if(Book.searchBook(searchfield.getText())==null && !searchfield.getText().isEmpty()){
                Alert alert= new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Search book result");
                alert.setContentText("Book not found!");
                
                alert.show();
            }else {
                updateDisplayedBooks(searchfield.getText());
            }
           }
        });
        
        
        //searchfield.setText();
        HBox topBorder= new HBox(20,searchfield,filterCombo);
        Border.setTop(topBorder);  //7ateto fel top border
        
        //Left border (haykon feiha el scrollPane
        Border.setLeft(scrollpane);
        BorderPane.setMargin(Border,new Insets(0,30,0,20));
        
       
        // HBox containing left side and right side 
        HBox borrowroot = new HBox();
        HBox.setMargin(grid, new Insets(0,30,0,20)); 
        HBox.setHgrow(leftSide_b, Priority.NEVER); //me4 3ayza el left side yekbar
       
        
        
        // HBox.setHgrow(rightsideAll, Priority.ALWAYS );
        borrowroot.getChildren().addAll(leftSide_b, Border);
        HBox.setHgrow(scrollpane, Priority.ALWAYS);  // 3a4an ya5od any availavle horizontal spce (el scroll makn4 beby2a on right most)
         
        grid.setPadding(new Insets(30,50,0,50));
        sceneBorrower = new Scene(borrowroot, 900, 600);
        
        //end of Borrower scene-------------------------------------------------
        
        
        
        
        
        //Boook manage scene 2-------------------------------------------------------------------------------------------
        
        
        //scene 2 hala
        Label label2= new Label("List of books:");
        Button btn2 = new Button("back");
        btn2.setOnAction (e-> primaryStage.setScene(scenehala1));
      
        VBox leftside= new VBox();
       
        leftside.setStyle("-fx-background-color: #044669;");
        leftside.setMinWidth(290);
       
        //  rightside.add(btn2,0,0);
        // rightsidepane.add(label2,0,0);
        HBox top = new HBox(10);
         
        top.getChildren().add(btn2);
       
        // top.setMaxWidth(Double.MAX_VALUE);
         //top.setAlignment(Pos.TOP_RIGHT);
         //top.getChildren().add(rightside);
         VBox rightSIDE= new VBox(20);
          leftside.getChildren().add(top);
          rightSIDE.getChildren().add(label2);
         Button addNewBook = new Button("Add new book");
          VBox info= new VBox(100);
          info.setAlignment(Pos.CENTER);
          
          //make a stackpane for the mostborrowedbook
          
          Book mostBorrowedBook= Book.getMostBorrowedBook();
          
          Image mostborrowed = new Image("file:D:\\bluebook.jpg");
          
          ImageView mbimage=new ImageView (mostborrowed);
          mbimage.setFitWidth(100);
          mbimage.setFitHeight(100);
          
          Label mostborrowedlabel1 = new Label ("Most Borrowed Book!!");
          mostborrowedlabel1.setFont(Font.font("Arial",FontWeight.BOLD,18));
          
          
           Label mostborrowedlabel2 = new Label ("esm el ketab");
          //da el sah
         // Label mostborrowedlabel2 = new Label (mostBorrowedBook.getTitle());
          mostborrowedlabel2.setFont(Font.font("Arial",16));
          
          
          Label mostborrowedlabel3 = new Label ("esm el author");
           //da el sah
         // Label mostborrowedlabel3 = new Label (mostBorrowedBook.getAuthor());
          mostborrowedlabel3.setFont(Font.font("Arial",13));
          
          VBox mostborrowedbox = new VBox();
          
          mostborrowedbox.setSpacing(5);
          mostborrowedbox.getChildren().addAll(mbimage,mostborrowedlabel1,mostborrowedlabel2,mostborrowedlabel3);
          mostborrowedbox.setAlignment(Pos.CENTER);
          
          StackPane mostborrowedstack=new StackPane();
          
          mostborrowedstack.getChildren().add(mostborrowedbox);
          StackPane.setAlignment(mostborrowedbox, Pos.CENTER);
          
          BackgroundFill backgroundfill = new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY);
          Background backg =new Background(backgroundfill);
          mostborrowedstack.setBackground(backg);
          
          mostborrowedstack.setMinSize(300,200);
          mostborrowedstack.setMaxSize(300,200);
          
          mostborrowedstack.setStyle("-fx-border-color: black;");
          
          info.getChildren().add(mostborrowedstack);
          
          
          
           //Book mostRevBook= Book.getMostRevenueBook();
          
          Image mostRevenue = new Image("file:D:\\Dollar.jpg");
          
          ImageView mostRevenueview =new ImageView (mostRevenue);
          mostRevenueview.setFitWidth(100);
          mostRevenueview.setFitHeight(100);
          
          Label mostrevlabel1 = new Label ("Most Revenue Book!!");
          mostrevlabel1.setFont(Font.font("Arial",FontWeight.BOLD,18));
          
          
          Label mostrevlabel2 = new Label ("esm el ketab");
          //da el sah
        // Label mostborrowedlabel6 = new Label (Book.getMostRevenueBook().getTitle());
          mostrevlabel2.setFont(Font.font("Arial",16));
          
          
          Label mostrevlabel3 = new Label ("esm el author");
           //da el sah
         // Label mostborrowedlabel3 = new Label (mostRevBook.getAuthor());
          mostrevlabel3.setFont(Font.font("Arial",13));
          
          VBox mostrevbox = new VBox();
          
          mostrevbox.setSpacing(5);
          mostrevbox.getChildren().addAll(mostRevenueview,mostrevlabel1,mostrevlabel2,mostrevlabel3);
          mostrevbox.setAlignment(Pos.CENTER);
          
          StackPane mostrevstack=new StackPane();
          
          mostrevstack.getChildren().add(mostrevbox);
          StackPane.setAlignment(mostrevbox, Pos.CENTER);
          
          //BackgroundFill backgroundfill = new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY);
          //Background backg =new Background(backgroundfill);
          mostrevstack.setBackground(backg);
          
          mostrevstack.setMinSize(300,200);
          mostrevstack.setMaxSize(300,200);
          
          mostrevstack.setStyle("-fx-border-color: black;");
          
          info.getChildren().add(mostrevstack);
          
          
          
          
          
          
          
           ComboBox<String> categoryComboBox= new ComboBox<>();
           categoryComboBox.getItems().addAll("All","Fiction","Non-Fiction","Science");
           categoryComboBox.setPromptText("Select Category");
          
          TextField search = new TextField();
          search.setPromptText("Search");
          
          HBox filterh = new HBox(10);
          filterh.getChildren().addAll(categoryComboBox,search);
          
          search.setOnKeyPressed(event -> {
//          List<Book> searchedBooks ; 
//          searchedBooks=Book.getBooks();

                   if(event.getCode()==KeyCode.ENTER){
                rightsidepane.getChildren().clear();
          
                String newValue= search.getText().trim();
                
                if(!newValue.isEmpty()){
             
                     Book foundbook = Book.searchBook(newValue.trim());
          
              if(foundbook!=null){
                  
                  int col1=0;
                  int row1=0;
                  
                  StackPane bookPaneH=createBookPane2(foundbook);
                  
                  rightsidepane.add(bookPaneH, col1++, row1);
                  
              }else{
                  Alert notf= new Alert(Alert.AlertType.INFORMATION);
                  notf.setTitle("Book Not Found");
                  notf.setHeaderText(null);
                  notf.setContentText("No book found with the specified title");
                   Platform.runLater( ()->     { 
                   Screen screen = Screen.getPrimary();
                     double centerX= screen.getBounds().getWidth()/2;
                     double centerY= screen.getBounds().getHeight()/2;
        
        
                     notf.setX(centerX- notf.getWidth()/2);
                     notf.setY(centerY- notf.getHeight()/2);
                   });
                  notf.showAndWait();
                  
                  
                  
                  
              }
              
          }else {
                    int col1=0;
                    int row1=0;
                    
                    for(Book book: Book.getBooks()){
                        StackPane bookPaneH = createBookPane2(book);
                        
                        rightsidepane.add(bookPaneH,col1++,row1);
                        
                        if(col1==3){
                            col1=0;
                            row1++;
                            
                        }
                        
                    }
                     
                }
                   }
         });
          
          
          
          
         rightSIDE.getChildren().addAll(filterh,rightsidepane,addNewBook);
         
          ScrollPane scroll = new ScrollPane(rightSIDE);
           
            rightSIDE.setPrefSize(800, 100);
//          scroll.setFitToHeight(true);
//          scroll.setFitToWidth(true);
         
         HBox booksandinfo = new HBox(20);
         booksandinfo.getChildren().addAll(scroll,info);
        addNewBook.setOnAction(event->{             
        
            //setting the dialogues in the center 
        Screen screen2 = Screen.getPrimary();
       
        
            
            
            
           TextInputDialog dialog= new TextInputDialog();
           
             Platform.runLater( ()->     { 
         
                     Screen screen = Screen.getPrimary();
                     double centerX= screen.getBounds().getWidth()/2;
                     double centerY= screen.getBounds().getHeight()/2;
        
        
                     dialog.setX(centerX- dialog.getWidth()/2);
                     dialog.setY(centerY- dialog.getHeight()/2);
        
        
        
                    });
           
           dialog.setTitle("Add new Book");
           dialog.setHeaderText("Enter details for the new book ");
           dialog.setContentText("Title: ");
           
           //size
               dialog.getDialogPane().setPrefSize(400, 200);
               //remove icon
               dialog.setGraphic(null); 
               
               
                    dialog.setOnShown(titleEvent->{
                        
                         double centerX= screen2.getBounds().getWidth()/2;
                         double centerY= screen2.getBounds().getHeight()/2;
                        
                        dialog.setX(centerX- dialog.getWidth()/2);
                        dialog.setY(centerY- dialog.getHeight()/2); 
                        
                        
                    });
        
            
               
               
           Optional<String> result = dialog.showAndWait();
           result.ifPresent(title->{
           
               TextInputDialog authorDialog = new TextInputDialog();
               
               Platform.runLater( ()->     { 
         
                     Screen screen = Screen.getPrimary();
                     double centerX= screen.getBounds().getWidth()/2;
                     double centerY= screen.getBounds().getHeight()/2;
        
        
                     authorDialog.setX(centerX- authorDialog.getWidth()/2);
                     authorDialog.setY(centerY- authorDialog.getHeight()/2);
        
        
        
                    });
               
               authorDialog.setTitle("Add new Book");
               authorDialog.setHeaderText("Enter details for the new book");
               authorDialog.setContentText("Author: ");
               
                //size
               authorDialog.getDialogPane().setPrefSize(400, 200);
               //remove icon
               authorDialog.setGraphic(null); 
               
            
               
               
               Optional<String> authorResult= authorDialog.showAndWait();
               
               
               TextInputDialog categoryDialog = new TextInputDialog();
               
                Platform.runLater( ()->     { 
         
                     Screen screen = Screen.getPrimary();
                     double centerX= screen.getBounds().getWidth()/2;
                     double centerY= screen.getBounds().getHeight()/2;
        
        
                     categoryDialog.setX(centerX- categoryDialog.getWidth()/2);
                     categoryDialog.setY(centerY- categoryDialog.getHeight()/2);
        
        
        
                    });
               
               categoryDialog.setTitle("Add new Book");
               categoryDialog.setHeaderText("Enter details for the new book ");
               categoryDialog.setContentText("Category: ");
               //size
               categoryDialog.getDialogPane().setPrefSize(400, 200);
               //remove icon
               categoryDialog.setGraphic(null);
               
               
               
            
               
               
               Optional<String> categoryResult= categoryDialog.showAndWait();
                       
                       
               String author =authorResult.orElse("unknown author");
               String category=categoryResult.orElse("unknown category");
               Book newBook = new Book(title,author,category );
               
                if(!Book.getBooks().contains(newBook)){
               try{
                   //Book.addBook(newBook);
                   Book.loadBooks();
                  // books.add(0, newBook);
              Book.saveBooks();
               
               }catch(IOException e){
                   e.getMessage();
               }
                
               
             
               int totalbooks= Book.getBooks().size();
               int rows= 4+totalbooks/3;
               int cols= totalbooks%3;
               
               StackPane newbookPane = createBookPane2(newBook);
               
             rightsidepane.add(newbookPane,cols,rows);
               Book.addBook(newBook);
               if (cols==0){
                   
                   rows++;
               }
              }
                else{
                    
                   System.out.println("book already exists");
                    
                   Alert bookexists = new Alert(Alert.AlertType.INFORMATION);
                   
                    Platform.runLater( ()->     { 
         
                     Screen screen = Screen.getPrimary();
                     double centerX= screen.getBounds().getWidth()/2;
                     double centerY= screen.getBounds().getHeight()/2;
        
        
                     bookexists.setX(centerX- bookexists.getWidth()/2);
                     bookexists.setY(centerY- bookexists.getHeight()/2);
        
        
        
                    });
                   bookexists.setTitle("Error Adding Book");
                   bookexists.setHeaderText(null);
                   bookexists.setContentText("This Book Already Exists!");
                
                  
                   
                  
                  
                   bookexists.showAndWait();
                   
                   
                }
               
              
           });
      
     
        
      
      
        
        
        } );
        
        


        
        rightsidepane.setAlignment(Pos.CENTER);
        rightsidepane.setHgap(30);
        rightsidepane.setVgap(30);
        rightsidepane.setMaxWidth(600);
        rightsidepane.setMaxHeight(600);
        rightsidepane.setPadding(new Insets(30,50,0,50));
        
        
       

        
        
        HBox mainlayout= new HBox();
       mainlayout.getChildren().addAll(leftside,booksandinfo);
        Book.loadBooks();
        List <Book> books1= Book.getBooks();
        int row =4;
        int col=0;
//           VBox bookEntry = new VBox();

 
            
     if(books1 != null){
        for(Book book: books1){
            
           StackPane bookPane= createBookPane2(book);
           rightsidepane.add(bookPane,col++,row);
            
            if(col==3){
                col= 0;
                row++;
                
            }
            
          
         
           }
       }
      
     
     categoryComboBox.setOnAction(event->{
                String selectedCategory= categoryComboBox.getValue();
               
                List<Book> filteredBooks ; //Book.getBooks().stream().filter(book->selectedCategory.equalsIgnoreCase(book.getCategory())).collect(Collectors.toList());
                
                
                if("All".equalsIgnoreCase(selectedCategory)){
                    
                    
                    filteredBooks=Book.getBooks();
                    
                    
                }else{
                    
                    filteredBooks=Book.getBooks().stream().filter(book-> selectedCategory.equalsIgnoreCase(book.getCategory())).collect(Collectors.toList());
                    
                    
                }
                
                int col1=0;
                int row1=0;
                rightsidepane.getChildren().clear();
                
                
              if(books1 != null){
        for(Book book: filteredBooks){
            
           StackPane bookPane= createBookPane2(book);
           rightsidepane.add(bookPane,col1++,row1);
            
            if(col1==3){
                col1= 0;
                row1++;
                
            }
        }
              }      
            });
     
     
     
     
     
     
     
     
     
     
     
      //rightSIDE.getChildren().addAll(bookEntry,addNewBook);
         scenehala2 = new Scene(mainlayout,600,550);
        
        
        //---------------------------------------------------------------------------------------------end of book manage
        
        
        
        
        //user account scene------------------------------------------------------------------------------------------
        VBox mainlayout2 = new VBox(20);
        
        mainlayout2.setPadding(new Insets(20));
        
       String currentBorrowerName= usernameField.getText().trim();
       
       // String currentBorrowerName= "hala elaby";
        
        
        
        //will not use the next line
      // String history =  readBorrowerHistory(currentBorrowerName);

        
        
       VBox bookListContainer = new VBox(30);
        
       Button historyback= new Button("Go Back");
       Label historyheader= new Label("YOUR HISTORY:");
        historyheader.setFont(Font.font("Arial",FontWeight.BOLD,14));
       
        StringBuilder history = new StringBuilder();
        //replace bl actual file name bta3 shahd 
        
        
        try(BufferedReader reader = new BufferedReader(new FileReader("Borrowers_ALL.txt"))){
            int row_history=0;
            String line;
              GridPane historypane = new GridPane();
              historypane.setHgap(20);
              historypane.setVgap(20);
            boolean borrowerFound = false;
            
            while((line= reader.readLine())!=null){
                
                if(line.equalsIgnoreCase(currentBorrowerName)){
                    
                    borrowerFound= true;
                    continue;
                    
                }
                
                if(line.isEmpty()&& borrowerFound){
                    break;
                    
                    
                }
                if(borrowerFound){
                    
                    history.append(line).append("\n").append("\n");
                    
                    Label bookhist= new Label(line);
                    
                    bookhist.setFont(Font.font("Arial",FontWeight.BOLD,14));
                    
                    Button returnBook= new Button("Return");
                    
                    returnBook.setOnAction(event->{
                        
                        System.out.println("returning book");
                    });
                    
                   
                    
                    
                  
                    historypane.add(bookhist, 0, row_history);
                    historypane.add(returnBook,2,row_history);
                    
                    row_history+=2;
                    
                   
                    
                }
                
            }
        //mainlayout2.getChildren().add(bookListContainer);
             bookListContainer.getChildren().addAll(historyback,historyheader,historypane,new Separator());
        }catch(IOException ex){
            
            System.out.println("error reading borrower history"+ex.getMessage());
            
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
         scenehala3= new Scene(bookListContainer,400,300);
        
        //-----------------------------------------------------------------------------------------end of user account
        
        

        
        
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
    
    //SHAHD Methods-------------------------------------------------------------
    
    private StackPane createBookPane(Book book, String selectedCategory) {
        // Image
        Image bookImage = new Image("file:D://Desktop//Univeristy//Semester 3//OOP//book_logo.png");
        ImageView imageView1 = new ImageView(bookImage);
        imageView1.setFitWidth(imagesize);
        imageView1.setPreserveRatio(true);

        // Book name label
        Label bookNameLabel = new Label(book.getTitle());
        bookNameLabel.setFont(new Font("Lucida Sans Unicode", 14));
        Label category= new Label(book.getCategory());
        bookNameLabel.setTranslateY(-32);
       
        //highlight effect on category
        Color highlight=  getCategoryColor(book.getCategory());
       // Rectangle recHighlight=new Rectangle(); //beyzbt 3ala 7agm el label
    
     
        // recHighlight.widthProperty().bind(category.widthPropert());
        StackPane Highlightpane= new StackPane();
        Highlightpane.getChildren().addAll(new Rectangle(35,10,highlight),category);
        Highlightpane.setAlignment(Pos.BOTTOM_CENTER);
        Highlightpane.setTranslateY(-12);
        
     // StackPane to hold image and label
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView1, bookNameLabel,Highlightpane);
        StackPane.setAlignment(imageView1, Pos.TOP_LEFT);
        StackPane.setAlignment(bookNameLabel, Pos.BOTTOM_CENTER);
      
      
        // StackPane.setAlignment(Highlightpane, Pos.BOTTOM_CENTER);
       
        stackPane.setPrefSize(180, 180);
        stackPane.setStyle("-fx-border-color: black;");
        
        // Set white background for each cell
        BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        stackPane.setBackground(background);
        
        //se fixed size for each cell
        stackPane.setMinSize(180,180);
        stackPane.setMaxSize(180,180);
        
        stackPane.setOnMouseClicked(e ->{
        showBookDetails(book,Border);
        });
        // Display all information about the book when clicked
       
        //small popup message
        Tooltip tooltip= new Tooltip("Show details");
       
       //Hover effect & cursor change
       DropShadow dropshadow= new DropShadow();
       Color shadowColor= Color.web("#3B3E57");
       dropshadow.setColor(shadowColor);
       
       stackPane.setOnMouseEntered(e->{
       stackPane.setCursor(Cursor.HAND);
       stackPane.setEffect(dropshadow);
       Tooltip.install(stackPane,tooltip);
       
       stackPane.setOnMouseMoved(b->{ 
       tooltip.setX(b.getScreenX());
       tooltip.setY(b.getScreenY());
       });
       
       });
       
       stackPane.setOnMouseExited(e->{
       stackPane.setCursor(Cursor.DEFAULT);
       stackPane.setEffect(null);
       });
       
     
      //ha filter by category or ha update 3ala el search 
      if(book.getTitle().toLowerCase().contains(selectedCategory.toLowerCase()) ||book.getCategory().equals(selectedCategory) ||
              selectedCategory==null ||selectedCategory.equalsIgnoreCase("No filter") 
              ||selectedCategory.equalsIgnoreCase("" ))
          return stackPane;
      

        return null;   
    }
    
private void showBookDetails(Book book, BorderPane border) {
   
    // Create a new scene to display detailed information about the book
    VBox detailsBox = new VBox(10);
    detailsBox.setAlignment(Pos.CENTER);
    detailsBox.setPadding(new Insets(20));
    
    
    //stars for rating w el handle bet3ha
        HBox AllStars= new HBox();
       
        String starPath = "M 50,5 L 55,30 L 75,30 L 60,45 L 65,65 L 50,55 L 35,65 L 40,45 L 25,30 L 45,30 Z";

        Fivestars= new SVGPath[5];
        for(int i=0;i<Fivestars.length;i++){
        SVGPath star= new SVGPath();
        
        star.setContent(starPath);
       
        star.setFill(Color.WHITE);
        star.setStroke(Color.BLACK);
        star.setStrokeWidth(3);
        final int index=i;
        
        star.setOnMouseClicked(m->{
        rate=index+1;
            System.out.println(rate);
        star.setFill(Color.GOLD);
        colorStars(index);
        });


        star.setLayoutX(i*100+50); //3a4an yet7to gamb ba3d horizontally
        star.setLayoutX(50);
         Fivestars[i]=star;
        AllStars.getChildren().add(star);
        AllStars.setAlignment(Pos.CENTER);
        
        
         //mouse format (bagyar 4akl el cursor)
         star.setOnMouseEntered(e->star.setCursor(Cursor.HAND));
         star.setOnMouseExited(e->star.setCursor(Cursor.DEFAULT));
        }
        
    Label details= new Label("Book details");
    details.setFont(Font.font("Comic Sans MS",FontWeight.BOLD, 30));
    
    Label titleLabel = new Label("Title: " + book.getTitle());
    titleLabel.setFont(new Font("Lucida Sans Unicode", 16));

    Label authorLabel = new Label("Author: "+ book.getAuthor());
    authorLabel.setFont(new Font("Lucida Sans Unicode", 14));

    Label categoryLabel = new Label("Category: " + book.getCategory());
    categoryLabel.setFont(new Font("Lucida Sans Unicode", 14));
    
    Label avgrating = new Label();
    Label userRating= new Label();
  
    Label messageLabel= new Label();
    messageLabel.setAlignment(Pos.CENTER);
    messageLabel.setTextFill(Color.MAROON);
    
    //back & borrow button
    ToggleButton RateButton= new ToggleButton("Rate");
    ToggleButton BorrowBook= new ToggleButton("Borrow");
    
    //handle buttons
    //BorrowBook.setSelected(false);
    BorrowBook.setOnAction(e->{
        
       if(!book.isBorrowed()){
           user.borrowBook(book);
           book.setBorrowed(true);
           book.setPrice(20);
           BorrowBook.setText("Borrowed");
           BorrowBook.setDisable(true);
          // messageLabel.setText("Go to User Account to return book");
           
         
            
     }
       //else{
//           messageLabel.setText("This book is already Borrowed by you");
//           BorrowBook.setDisable(true);
//        }
      BorrowBook.setDisable(book.isBorrowed());


     
    });
    
    
     RateButton.setOnAction(e->{
  
       if(!book.isRated()){
           book.setisRated(true);
           user.rateBook(book, rate);
           //book.addRating(rate); 7atha fel rateBook method already
           RateButton.setText("Rated");
           RateButton.setDisable(true);
           userRating.setText("you rated the book: "+ rate);
           messageLabel.setText("thank you for rating :)");
            
       }else{
           messageLabel.setText("You already rated this book!");
           RateButton.setDisable(true);
        }
      RateButton.setDisable(book.isRated());
      // user.displayAllRatings(book));
      avgrating.setText("Average rating: "+ book.getAverageRating());
    });
     
     
    //HBOX to hold the buttons
    HBox bookdetailsButtons= new HBox(20,BorrowBook,RateButton);
    bookdetailsButtons.setAlignment(Pos.CENTER);
    
    //mouse format (bagyar 4akl el cursor)
    RateButton.setOnMouseEntered(e->RateButton.setCursor(Cursor.HAND));
    RateButton.setOnMouseExited(e->RateButton.setCursor(Cursor.DEFAULT));
    BorrowBook.setOnMouseEntered(e->BorrowBook.setCursor(Cursor.HAND));
    BorrowBook.setOnMouseExited(e->BorrowBook.setCursor(Cursor.DEFAULT));
    
    //Button handle
  //  backToBooks.setOnAction(e->primaryStage.setScene(scene));
    detailsBox.getChildren().addAll(details,titleLabel, authorLabel, categoryLabel,avgrating,AllStars,userRating,bookdetailsButtons,messageLabel);
  //  border.setRight(null);
 
    border.setRight(detailsBox);
    HBox.setHgrow(border,Priority.ALWAYS);
    HBox.setMargin(border,new Insets(0,30,0,20)); 
    // Set the detailed scene on the primary stage using a lambda expression
  
  
}

private  Color getCategoryColor(String category){
    Map<String,Color> highlightColors= new HashMap<>();
    highlightColors.put("fiction", Color.web("#95B9C7"));
    highlightColors.put("non-fiction", Color.web("#FCBACB"));
    highlightColors.put("science", Color.web("#FFF2B2"));
    
    return highlightColors.getOrDefault(category, Color.WHITE);
     
}

 public void colorStars(int clickedIndex){
       
        for(int i=0;i<Fivestars.length;i++){
            if(i<=clickedIndex){
                
                Fivestars[i].setFill(Color.GOLD);
               // rate=i+1;
            }else{
                 Fivestars[i].setFill(Color.WHITE);
            }
            
        }
        //System.out.println(rate);
    }


    
    
 
    private void updateDisplayedBooks(String selectedCategory) {
    List<Book> books = Book.getBooks();
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(30);
    grid.setVgap(30);

    int count = 0;
    for (int i = 0; i < books.size(); i++) {
        Book book = books.get(i);
        StackPane bookPane = createBookPane(book, selectedCategory);
        if (bookPane != null) {
            grid.add(bookPane, count % 3, count / 3);
            count= count+ 1;
        }
    }

    grid.setPadding(new Insets(30, 50, 0, 50));

    // hast5dm el existing ScrollPane from the BorderPane
    ScrollPane scrollpane = (ScrollPane) Border.getLeft();
    
    scrollpane.setContent(grid);
}

    
    
    
    
    
    //--------------------------------------------------------------end of shahd
    
   //Zaina method---------------------------------------------------------------------------
    private void goBack(Scene originalScene) {
   
        primaryStage.setScene(originalScene);
    }
    
    //deih hatet7t mara wa7da
    private void setBackground(Button button, Color color) {
        BackgroundFill backgroundFill = new BackgroundFill(color, null, null);
        Background background = new Background(backgroundFill);
        button.setBackground(background);
    }
    
    
    
    
   //----------------------------------------------------------------------------end of zaina
//        
    private ImageView changeImage( ImageView imageView) {
       // Image image = new Image(imageUrl);
        
        // Apply a ColorAdjust effect to set the image color to white
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(1); // Set brightness to make it white

      //  ImageView imageView = new ImageView(image);
        imageView.setFitWidth(30); // Adjust these values as needed
        imageView.setFitHeight(30);
        imageView.setEffect(colorAdjust);
       
        //Button button = new Button(text, imageView);
        return imageView;
    }
//    }
}
