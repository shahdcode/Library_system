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




public class GUI_system extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        //Image
        Image image= new Image("file:D:/Downloads/image.png");
        ImageView imageView= new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        
        //Button code
       Button back=new Button("Back");
       Button bt1= new Button("Sign in");
       Button bt2=new Button("Sign up");
       
       //button appearance
       
      Color color=Color.web("#0D4683");
      BackgroundFill backgroundfill= new BackgroundFill(color,CornerRadii.EMPTY,Insets.EMPTY);
      back.setBackground(new Background(backgroundfill));
      back.setTextFill(Color.WHITE);
      bt1.setBackground(new Background(backgroundfill));
      bt1.setTextFill(Color.WHITE);
      bt2.setBackground(new Background(backgroundfill));
      bt2.setTextFill(Color.WHITE);
      
       //Button alignment
       HBox buttonbox=new HBox(15,back,bt1,bt2);
       buttonbox.setAlignment(Pos.CENTER);
       
       
       //label
        Label l1=new Label("Name:");
        Label l2=new Label("Password");
        
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
        
        
        //Vbox to hold el button b ba2i el vbox
        VBox vboxall= new VBox(25,imageView,h,buttonbox);
        //vboxall.setStyle("-fx-background-color: #E3C6C0");
        vboxall.setAlignment(Pos.CENTER);
        
        
//        //stackpane & children
//       StackPane stackpane= new StackPane();
//        stackpane.getChildren().addAll(h,buttonbox);

        
        //create scene
        Scene scene = new Scene(vboxall, 500, 300);
       scene.setFill(Color.TRANSPARENT);
      // vboxall.setStyle("-fx-background-color: #E3C6C0;");
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
