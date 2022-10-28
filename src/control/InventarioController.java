/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control;

import Negocio.Producto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author julia
 */
public class InventarioController implements Initializable {
    
    @FXML
    private TableColumn tbl_Codigo;
    @FXML
    private TableColumn tbl_Producto;
    @FXML
    private TableColumn tbl_PrecioCompra;
    @FXML
    private TableColumn tbl_PrecioVenta;
    @FXML
    private TableColumn tbl_Existencias;
    @FXML
    private Button btn_Back;    
    
    @FXML
    private TableView<Producto> tbl_inventario;
    
    private ObservableList<Producto> productos;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.modelaTabla();
               
    }    

    public void constructorNuevo(ObservableList lista){
        
         this.productos = lista; 

        for (Producto pro : productos) {
           this.tbl_inventario.setItems(productos);
        } 
    
    }
        
    @FXML
    private void GoProductos(ActionEvent event) {
        try
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Productos.fxml"));
        Parent root=loader.load();
        Scene scene = new Scene(root);
      
        Stage stage = new Stage();
        stage.setOnCloseRequest(even -> {even.consume();});
        stage.setResizable(false);
        stage.setTitle("PRODUCTOS");
        stage.setScene(scene);
        stage.show();
        
        Stage myStage=(Stage)this.btn_Back.getScene().getWindow();
        myStage.close();
    } catch (IOException ex){
        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    private void modelaTabla()
    {
        this.productos=FXCollections.observableArrayList();
        //Se mapea qué atributo del objeto va para cada columna...
        this.tbl_Codigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        this.tbl_Producto.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tbl_PrecioCompra.setCellValueFactory(new PropertyValueFactory("precioCompra"));
        this.tbl_PrecioVenta.setCellValueFactory(new PropertyValueFactory("precioVenta"));
        this.tbl_Existencias.setCellValueFactory(new PropertyValueFactory("existencia"));
        
        
        
    }
    
    

    
}

    
    

