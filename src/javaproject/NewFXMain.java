/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javaproject;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */



import java.io.*;
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
import javafx.scene.shape.*;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Alert.AlertType;

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
    private Scene scenezeina1;
    private Admin adminobj = new Admin();
    private ComboBox<String> cbo4;
    private Stage primaryStage;

       

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
              //  Image i=new Image("https://i.pinimg.com/564x/17/5b/18/175b185de89f2034c2394c9f5f97d358.jpg");
              //  ImageView iv=new ImageView(i);
//                      iv.setFitWidth(50);  
//        iv.setFitHeight(50); 
//        iv.setTranslateX(30);    
//        iv.setLayoutY(-30); 
//          iv.setTranslateY(380); 
          
          
          
                 
//                Image i1=new Image("file:C:\\Users\\zaina\\OneDrive\\Pictures\\download (4).png");
//                ImageView iv1=new ImageView(i1);
//                 iv1.setFitWidth(45);  
//                 iv1.setFitHeight(50); 
//                 iv1.setTranslateX(-35);    
//                 iv1.setLayoutY(-50); 
//                 iv1.setTranslateY(112); 
//          
//          Image i2=new Image("file:C:\\Users\\zaina\\OneDrive\\Pictures\\download (3).png");
//                ImageView iv2=new ImageView(i2);
//                 iv2.setFitWidth(46);  
//                 iv2.setFitHeight(43); 
//                 iv2.setTranslateX(-32);    
//                 iv2.setLayoutY(-75); 
//                 iv2.setTranslateY(151); 
//          
//          Image i3=new Image("file:C:\\Users\\zaina\\OneDrive\\Pictures\\download (2).png");
//          ImageView iv3=new ImageView(i3);
//                 iv3.setFitWidth(40);  
//                 iv3.setFitHeight(55); 
//                 iv3.setTranslateX(-33);    
//                 iv3.setLayoutY(-80); 
//                 iv3.setTranslateY(193); 
//                 
//                 
//           Image i4=new Image("file:C:\\Users\\zaina\\OneDrive\\Pictures\\download (1).png");
//          ImageView iv4=new ImageView(i4);
//                 iv4.setFitWidth(35);  
//                 iv4.setFitHeight(30); 
//                 iv4.setTranslateX(-35);    
//                 iv4.setLayoutY(-80); 
//                 iv4.setTranslateY(235);         
//                 
//                 
//                 
//                      Image i5=new Image("file:C:\\Users\\zaina\\OneDrive\\Pictures\\download (6).png");
//          ImageView iv5=new ImageView(i5);
//                 iv5.setFitWidth(40);  
//                 iv5.setFitHeight(40); 
//                 iv5.setTranslateX(-37);    
//                 iv5.setLayoutY(-80); 
//                 iv5.setTranslateY(276);  
                 
                 
          
          
                 StackPane rt = new StackPane();
//        rt.getChildren().addAll(iv1,iv2,iv3,iv4,iv5);
    
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
    
       

     cbo4=new ComboBox<>(FXCollections.observableArrayList(
      "Borrower","Supplier","Book","Librarian"));
        cbo4.setValue("Manage");
       
       cbo4.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
   cbo4.setPrefWidth(176);
        cbo4.setOnAction(n->{
            String selected=cbo4.getValue();
           if("Librarian".equals(selected))
           {
              // Libscene(primaryStage);
           }
           else if("Borrower".equals(selected))
           {
//               Libscene();
           }
           else if("Book".equals(selected))
           {
             //  Libscene();
           }
        });
        
    
    Button cbo3 = new Button();
   cbo3.setText("Search Admin");
 setBackground(cbo3, Color.web("#044669"));
     cbo3.setTextFill(Color.WHITE);
        cbo3.setFont(Font.font("System", FontWeight.BOLD, 14));
       cbo3.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
   cbo3.setPrefWidth(176);
   
   
   GridPane contentadd=new GridPane();
         GridPane content=new GridPane();
         HBox r=new HBox();       
        r.setPrefSize(300,200);
          GridPane avg=new GridPane();
         GridPane total=new GridPane();
         ////////////////////total rev
         bt1.setOnAction(u->{
              groupremove.getChildren().clear();
    groupadd.getChildren().clear();
      groupsearch.getChildren().clear();
       groupedit.getChildren().clear();
       groupcalc.getChildren().clear();
         Button b=new Button();
              b.setText("Calculate Total.");
              TextArea t=new TextArea();
              t.setPrefSize(150,50);
              b.setOnAction(l->{
                   Borrower_Mgn browerer=new Borrower_Mgn();
                       List<User> k=browerer.readFromFile();
             double av=adminobj.getTotalRevenue();
             t.setText("Total revenue: "+av);
              });
             total.add(b,0,5);
             total.add(t,0,2);
                r.getChildren().add(total);
       total.requestFocus();
      total.setPadding(new Insets(95));
          Rectangle whiteBox = new Rectangle(450, 200);  
             whiteBox.setTranslateX(10);
        whiteBox.setLayoutY(50);
         whiteBox.setFill(Color.web("#F6F6F6")); 
    grouptota.getChildren().addAll(whiteBox, total);
        try{
    
    r.getChildren().add(grouptota);
       
        }catch(Exception eo)
        {
          //  System.out.println(eo);
        }
       
         });
         
         
         /////////////////average rev
          bt2.setOnAction(u->{
      groupremove.getChildren().clear();
    groupadd.getChildren().clear();
      groupsearch.getChildren().clear();
       groupedit.getChildren().clear();
        grouptota.getChildren().clear();
              Button b=new Button();
              b.setText("Calculate Avg.");
              TextArea t=new TextArea();
              t.setPrefSize(150,50);
              b.setOnAction(l->{
Borrower_Mgn browerer=new Borrower_Mgn();
                       List<User> list=browerer.readFromFile(); 

                 double averageRevenue = adminobj.getAverageRevenue();
String formattedAverage = String.format("%.2f", averageRevenue);

System.out.println("Average Revenue: " + formattedAverage);

t.setText("Average Revenue: " + formattedAverage);
              });
             avg.add(b,0,5);
             avg.add(t,0,2);
                r.getChildren().add(avg);
       avg.requestFocus();
       avg.setPadding(new Insets(95));
          Rectangle whiteBox = new Rectangle(450, 200);  
             whiteBox.setTranslateX(10);
        whiteBox.setLayoutY(50);
         whiteBox.setFill(Color.web("#F6F6F6")); 
    groupcalc.getChildren().addAll(whiteBox, avg);
        try{
    
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
    groupadd.getChildren().addAll(whiteBox, contentadd);
        try{
    
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
     
        groupsearch.getChildren().addAll(whiteBox, root2);
        try{
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
     
        groupremove.getChildren().addAll(whiteBox, root5);
        try{
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


  
    Scene scenezeina1 = new Scene(r, 500, 450);
        
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
        Text errorText= new Text ();
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
             
             //case admin
             //case librarian 

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
           
           else{
             errorText.setText("*Incorrect username or password");
        }
           //case Librarian
           
           

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

        
        
        
        
        //Borrower scene--------------------------------------------------------
        
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
            Borrower_Mgn brow= new Borrower_Mgn();
            List<User> b= brow.readFromFile();
            Borrower ba= new Borrower();
            brow.printAllToFile(ba);
            // Borrower.setBorrow_count(count_borrow);
             user.numberOfBorrowperUser();
             stage.setScene(scenehala1);
        });
        
        // Load/ read books from file
        Book.loadBooks();
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
           messageLabel.setText("Go to User Account to return book");
           
         
            
       }else{
           messageLabel.setText("This book is already Borrowed by you");
           BorrowBook.setDisable(true);
        }
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
//    }
}
