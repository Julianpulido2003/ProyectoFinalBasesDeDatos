/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package start;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author julian
 */
public class Start extends Application {
    
    public static void main (String[] args){
        launch (args);
    }
    
    @Override
    public void start (Stage stage) throws IOException
    
    {
        stage.setOnCloseRequest(even -> {even.consume();});
        stage.setResizable(false);
        stage.setTitle("MENU PRINCIPAL");
        Parent root = FXMLLoader.load(getClass().getResource("/vista/MenuPrincipal.fxml"));
        Scene scene = new Scene (root);
        stage.setScene(scene);
        
        stage.show();
        
    
    }
}
