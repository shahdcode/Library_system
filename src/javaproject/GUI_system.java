/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javaproject;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.PasswordField;
import javafx.stage.StageStyle;
import javafx.scene.layout.VBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Rectangle;





public class GUI_system extends Application {
    Stage window;
    @Override
    public void start(Stage primaryStage) {
        
        //awel stage hatban:
        window=primaryStage;
        Label Welcomelabel=new Label("Welcome user");
        VBox welcomelayout= new VBox(20,Welcomelabel);
        welcomelayout.setAlignment(Pos.CENTER);
        Scene welcomescene=new Scene(welcomelayout,550,380);
        
        //back stage
        Label libraryLabel=new Label("Helo please choose how u want to sign in");
        VBox librarylayout= new VBox(20,libraryLabel);
        librarylayout.setAlignment(Pos.CENTER);
        Scene libraryScene= new Scene(librarylayout,550,380);
        //Image
        Image image= new Image("file:D:/Desktop/Univeristy/Semester 3/OOP/user.png");
      //  ""
        ImageView imageView= new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        
        //Button code
       Button back=new Button("Back");
       Button bt1= new Button("Sign in");
       Button bt2=new Button("Sign up");
       
       //button appearance
       
      Color color=Color.web("#F19EB0");
      BackgroundFill backgroundfill= new BackgroundFill(color,CornerRadii.EMPTY,Insets.EMPTY);
      back.setBackground(new Background(backgroundfill));
      back.setTextFill(Color.WHITE);
      bt1.setBackground(new Background(backgroundfill));
      bt1.setTextFill(Color.WHITE);
      bt2.setBackground(new Background(backgroundfill));
      bt2.setTextFill(Color.WHITE);
      
      //Button effect 3a4an menrfezani
      DropShadow shadow= new DropShadow();
      bt1.setOnMousePressed(e->bt1.setEffect(shadow));
      bt1.setOnMouseReleased(e->bt1.setEffect(null));
      bt2.setOnMousePressed(e->bt2.setEffect(shadow));
      bt2.setOnMouseReleased(e->bt2.setEffect(null));
      back.setOnMousePressed(e->back.setEffect(shadow));
      back.setOnMouseReleased(e->back.setEffect(null));
      //Button alignment
       HBox buttonbox=new HBox(15,back,bt1,bt2);
       buttonbox.setAlignment(Pos.CENTER);
       
       
       //label
        Label l1=new Label("Name:");
        Label l2=new Label("Password");
        
       //set on action lel button (sign  in and up code)
       SIGN sign=new SIGN();
       bt1.setOnAction(e->{
       sign.signIn(1, l1.getText(),l2.getText());
       window.setScene(welcomescene); //haro7 le next scene
       });
       
        bt2.setOnAction(e->{
       sign.signUp(1, l1.getText(),l2.getText());
        window.setScene(welcomescene); //haro7 le next scene
        });
        
        
        //button wedeni next scene
      
       back.setOnAction(e->window.setScene(libraryScene));
       
       
        //Label alignment
        VBox vbox= new VBox(15,l1,l2);
        vbox.setAlignment(Pos.CENTER);
        
        
        //Text field
         TextField tf1=new TextField ();
         PasswordField password= new PasswordField();
         
         //text field alignemnt
        VBox text= new VBox(15,tf1,password);
        text.setAlignment(Pos.CENTER);
        
        //Hbox
        HBox h= new HBox(25,vbox,text);
        h.setAlignment(Pos.CENTER);
        
        //Rectangle for desgin
        
        Rectangle sqaure= new Rectangle();
        sqaure.setWidth(270);
        sqaure.setHeight(270);
        sqaure.setFill(Color.WHITE);
       
        //create stackpane for the layout
        StackPane stackpane= new StackPane();
        
        //Vbox to hold el button b ba2i el vbox
        VBox vboxall= new VBox(25,imageView,h,buttonbox);
        stackpane.getChildren().addAll(sqaure,vboxall);
        //vboxall.setStyle("-fx-background-color: #E3C6C0");
        vboxall.setAlignment(Pos.CENTER);
        
        
//        //stackpane & children
//       StackPane stackpane= new StackPane();
//        stackpane.getChildren().addAll(h,buttonbox);

        
        //create scene 
       Scene scene = new Scene(stackpane, 550, 380);
       scene.setFill(Color.TRANSPARENT);
       
       //Background fill
//       Color fill=Color.web("#E3C6C080");
//       BackgroundFill background_color=new BackgroundFill(fill,CornerRadii.EMPTY,Insets.EMPTY);
//      Background background= new Background(background_color);
//       vboxall.setBackground(background);


        //Stage
       // primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Registration form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
