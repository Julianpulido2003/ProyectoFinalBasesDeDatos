/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control;

import Negocio.CompraVenta;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author julia
 */
public class TodasVentasController implements Initializable {

    @FXML
    private TableColumn tbl_Fecha;
    @FXML
    private TableColumn tbl_Factura;
    @FXML
    private TableColumn tbl_Proveedor;
    @FXML
    private TableColumn tbl_Total;
    @FXML
    private TableColumn tbl_Estado;
    @FXML
    private Button btn_Regresar;
    private ComboBox<String> cb_Filtrar;
    @FXML
    private TableView<CompraVenta> tbl_todasVentas;
    private ObservableList<CompraVenta> ventas; 
      private ObservableList<CompraVenta> filtrados;
    @FXML
    private MenuButton Mbtn_filtro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.modelaTabla();
        filtrados=FXCollections.observableArrayList();
    }    

    public void constructorNuevo(ObservableList lista){
        
         this.ventas = lista;   
        
        for (CompraVenta vta: ventas) {
        
            if ("true".equals(vta.getEstado())) 
                vta.setEstado("Credito"); 
            else
                vta.setEstado("Contado");    
             this.tbl_todasVentas.setItems(ventas);  
        }     
         
    }
    
    @FXML
    private void GoBackVentas(ActionEvent event) {
        try
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Ventas.fxml"));
        Parent root=loader.load();
        Scene scene = new Scene(root);
   
        Stage stage = new Stage();
        stage.setOnCloseRequest(even -> {even.consume();});
        stage.setResizable(false);
        stage.setTitle("VENTAS");
        stage.setScene(scene);
        stage.show();
        
        Stage myStage=(Stage)this.btn_Regresar.getScene().getWindow();
        myStage.close();
    } catch (IOException ex){
        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    private void modelaTabla()
    {
        this.ventas=FXCollections.observableArrayList();
        //Se mapea qué atributo del objeto va para cada columna...
        this.tbl_Fecha.setCellValueFactory(new PropertyValueFactory("Fecha"));
        this.tbl_Factura.setCellValueFactory(new PropertyValueFactory("idFactura"));
        this.tbl_Proveedor.setCellValueFactory(new PropertyValueFactory("proveedor"));
        this.tbl_Total.setCellValueFactory(new PropertyValueFactory("Total"));
        this.tbl_Estado.setCellValueFactory(new PropertyValueFactory("estado"));        
    }

     @FXML
    private void doFiltroTodo(ActionEvent event) 
    {
        this.tbl_todasVentas.setItems(ventas);                
    }

    @FXML
    private void doFiltroCredito(ActionEvent event) 
    {
       String credito="Credito";
       
       this.filtrados.clear();
    
       for(CompraVenta c:this.ventas)
       {
           if (c.getEstado().contains(credito))
              this.filtrados.add(c);                     
       }              
       this.tbl_todasVentas.setItems(filtrados);
    }

    @FXML
    private void doFiltroContado(ActionEvent event) 
    {
        String contado="Contado";
       
       this.filtrados.clear();
    
       for(CompraVenta c:this.ventas)
       {
           if (c.getEstado().contains(contado))
              this.filtrados.add(c);                     
       }              
       this.tbl_todasVentas.setItems(filtrados);
        
    }
    
}
