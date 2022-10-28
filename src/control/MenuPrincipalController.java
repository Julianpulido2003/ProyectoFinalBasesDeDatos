/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author julia
 */
public class MenuPrincipalController implements Initializable {

    @FXML
    private Button btn_clientes;
    @FXML
    private Button btn_proveedores;
    @FXML
    private Button btn_salir;
    @FXML
    private Button btn_compras;
    @FXML
    private Button btn_productos;
    @FXML
    private Button btn_ventas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoClientes(ActionEvent event) {
    try
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Clientes.fxml"));
        Parent root=loader.load();
        Scene scene = new Scene(root);
//        ClientesController ouControlador = loader.getController();
//        ouControlador.constructorNuevo();
        
        Stage stage = new Stage();
        stage.setOnCloseRequest(even -> {even.consume();});
        stage.setResizable(false);
        stage.setTitle("CLIENTES");
        stage.setScene(scene);
        stage.show();
        
        Stage myStage=(Stage)this.btn_clientes.getScene().getWindow();
        myStage.close();
    } catch (IOException ex){
        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
               
        
    }

    @FXML
    private void GoProveedores(ActionEvent event) {
        try
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Proveedores.fxml"));
        Parent root=loader.load();
        Scene scene = new Scene(root);
//        ClientesController ouControlador = loader.getController();
//        ouControlador.constructorNuevo();
        
        Stage stage = new Stage();
        stage.setOnCloseRequest(even -> {even.consume();});
        stage.setResizable(false);
        stage.setTitle("PROVEEDORES");
        stage.setScene(scene);
        stage.show();
        
        Stage myStage=(Stage)this.btn_proveedores.getScene().getWindow();
        myStage.close();
    } catch (IOException ex){
        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void DoSalir(ActionEvent event) {
        Stage stage = (Stage)this.btn_salir.getScene().getWindow();
        stage.close();
        System.exit(0);
    }

    @FXML
    private void GoCompras(ActionEvent event) {
        try
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Compras.fxml"));
        Parent root=loader.load();
        Scene scene = new Scene(root);
//        ClientesController ouControlador = loader.getController();
//        ouControlador.constructorNuevo();
        
        Stage stage = new Stage();
        stage.setOnCloseRequest(even -> {even.consume();});
        stage.setResizable(false);
        stage.setTitle("COMPRAS");
        stage.setScene(scene);
        stage.show();
        
        Stage myStage=(Stage)this.btn_clientes.getScene().getWindow();
        myStage.close();
    } catch (IOException ex){
        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void GoProductos(ActionEvent event) {
        try
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Productos.fxml"));
        Parent root=loader.load();
        Scene scene = new Scene(root);
//        ClientesController ouControlador = loader.getController();
//        ouControlador.constructorNuevo();
        
        Stage stage = new Stage();
        stage.setOnCloseRequest(even -> {even.consume();});
        stage.setResizable(false);
        stage.setTitle("PRODUCTOS");
        stage.setScene(scene);
        stage.show();
        
        Stage myStage=(Stage)this.btn_proveedores.getScene().getWindow();
        myStage.close();
    } catch (IOException ex){
        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void GoVentas(ActionEvent event) {
        try
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Ventas.fxml"));
        Parent root=loader.load();
        Scene scene = new Scene(root);
//        ClientesController ouControlador = loader.getController();
//        ouControlador.constructorNuevo();
        
        Stage stage = new Stage();
        stage.setOnCloseRequest(even -> {even.consume();});
        stage.setResizable(false);
        stage.setTitle("VENTAS");
        stage.setScene(scene);
        stage.show();
        
        Stage myStage=(Stage)this.btn_proveedores.getScene().getWindow();
        myStage.close();
    } catch (IOException ex){
        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
