/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control;

import Negocio.CompraVenta;
import gestion.GestionCompra;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author julia
 */
public class TodasComprasController implements Initializable {
    GestionCompra gestorcomp;
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
    private Button btn_PagarFactura;
    @FXML
    private TableView<CompraVenta> tbl_todasCompras;
    private ObservableList<CompraVenta> compras;
    private ObservableList<CompraVenta> filtrados;
    @FXML
    private MenuButton Mbutton_filtro;
    
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.modelaTabla();       
        this.gestorcomp= new GestionCompra();
        filtrados=FXCollections.observableArrayList();
    }    
    
    public void constructorNuevo(ObservableList lista){
                  
        this.compras = lista;
        
        for (CompraVenta com: compras) {            
            if ("true".equals(com.getEstado())) 
                com.setEstado("Credito"); 
            else
                com.setEstado("Contado"); 
            this.tbl_todasCompras.setItems(compras);  
        }            
    }

    @FXML
    private void GoBackCompras(ActionEvent event) {
        try
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Compras.fxml"));
        Parent root=loader.load();
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        stage.setOnCloseRequest(even -> {even.consume();});
        stage.setResizable(false);
        stage.setTitle("COMPRAS");
        stage.setScene(scene);
        stage.show();
        
        Stage myStage=(Stage)this.btn_Regresar.getScene().getWindow();
        myStage.close();
    } catch (IOException ex){
        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

 
    @FXML
    private void doPagarfactura(ActionEvent event) {
        
        String msg;
        CompraVenta com = this.tbl_todasCompras.getSelectionModel().getSelectedItem();
      
            if(com != null){
                            
                com.setEstado("Contado");
                this.tbl_todasCompras.refresh();
                gestorcomp.modificarExistencias (com.getIdFactura(),"false");
                }
                else
                {   msg="Debe seleccionar un registro";
                    this.showMessages (msg,1);
                }       
    }
        
    private void modelaTabla()
    {
        this.compras=FXCollections.observableArrayList();
        //Se mapea qué atributo del objeto va para cada columna...
        this.tbl_Fecha.setCellValueFactory(new PropertyValueFactory("Fecha"));
        this.tbl_Factura.setCellValueFactory(new PropertyValueFactory("idFactura"));
        this.tbl_Proveedor.setCellValueFactory(new PropertyValueFactory("proveedor"));
        this.tbl_Total.setCellValueFactory(new PropertyValueFactory("Total"));
        this.tbl_Estado.setCellValueFactory(new PropertyValueFactory("estado"));        
    }
    
    private boolean showMessages (String mensaje,int caso){
    Alert msg;
    boolean ok = false;
    
    if (caso==1) //error
    {
        msg= new Alert(Alert.AlertType.ERROR);
        msg.setTitle("ERROR");
        
        msg.setHeaderText(null);
        msg.setContentText(mensaje);
        msg.showAndWait();    
    }
    
    if (caso==2)
    {
        msg=new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("MENSAJE");
        
        msg.setHeaderText (null);
        msg.setContentText(mensaje);
        msg.showAndWait();
    }
    
    if (caso==3)
    {
        msg=new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("CONFIRMACIÓN");
        
        msg.setHeaderText (null);
        msg.setContentText(mensaje);
        msg.initStyle(StageStyle.UTILITY);
        
        Optional<ButtonType> result = msg.showAndWait();
        if(result.get()==ButtonType.OK)
            ok=true;
    }
    return ok;
    }        

    @FXML
    private void doFiltroTodo(ActionEvent event) 
    {
        this.tbl_todasCompras.setItems(compras);                
    }

    @FXML
    private void doFiltroCredito(ActionEvent event) 
    {
       String credito="Credito";
       
       this.filtrados.clear();
    
       for(CompraVenta c:this.compras)
       {
           if (c.getEstado().contains(credito))
              this.filtrados.add(c);                     
       }              
       this.tbl_todasCompras.setItems(filtrados);
    }

    @FXML
    private void doFiltroContado(ActionEvent event) 
    {
        String contado="Contado";
       
       this.filtrados.clear();
    
       for(CompraVenta c:this.compras)
       {
           if (c.getEstado().contains(contado))
              this.filtrados.add(c);                     
       }              
       this.tbl_todasCompras.setItems(filtrados);
        
    }
    
}
