/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control;

import Negocio.CompraVenta;
import gestion.GestionProductos;
import gestion.GestionProveedores;
import Negocio.ClienteProveedor;
import Negocio.Producto;
import gestion.GestionCompra;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author julia
 */
public class ComprasController implements Initializable {
    GestionProductos gestor;
    GestionProveedores gestorprov;
    GestionCompra gestorcomp;
    
    
    @FXML
    private Button btn_Agregar;
    @FXML
    private Button btn_Eliminar;
    @FXML
    private TableColumn tbl_Producto;
    @FXML
    private TableColumn tbl_Cantidad;
    @FXML
    private TableColumn tbl_Subtotal;
    @FXML
    private TableColumn tc_Compra;
    @FXML
    private Button btn_GuardarCompra;
    @FXML
    private Button btn_VerTodas;
    @FXML
    private Button btn_MenuPrincipal;
    @FXML
    private ComboBox<String> cb_Proveedor;
    @FXML
    private ComboBox <String> cb_Producto;
    private ObservableList<Producto> productos;
    private ObservableList<ClienteProveedor> proveedores;
    private ObservableList<CompraVenta> compras;
       
    @FXML
    private TextField txt_fra;
    @FXML
    private TextField txt_cant;
    @FXML
    private DatePicker txt_fecha;
    @FXML
    private TableView<CompraVenta> tbl_compras;
    @FXML
    private CheckBox ck_credito;
    @FXML
    private TextField txt_total;
    
    private float Total=0;
    private int nro_fra=0;
    @FXML
    private Button btn_Nuevo;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       
        this.modelaTabla();
        this.gestorcomp= new GestionCompra();
        this.gestorprov= new GestionProveedores();
        this.proveedores = gestorprov.getProveedores();
              
        for(ClienteProveedor prov : proveedores) {
        this.cb_Proveedor.getItems().add(prov.getNombre());
         }
         
        this.gestor= new GestionProductos();
        this.productos = gestor.getProducto();
        
        for(Producto prod : productos) {
        this.cb_Producto.getItems().add(prod.getNombre());
         }   
        
        Random r = new Random();
        nro_fra = r.nextInt(90000) + 10000; 
              
    }    
    
    @FXML
    private void VerTodasCompras(ActionEvent event) {
        try
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/TodasCompras.fxml"));
        Parent root=loader.load();
        Scene scene = new Scene(root);
        
        this.compras = gestorcomp.getCompraoVenta(1);        
         
        TodasComprasController ouControlador = loader.getController();
        ouControlador.constructorNuevo(compras);
        
        Stage stage = new Stage();
        stage.setOnCloseRequest(even -> {even.consume();});
        stage.setResizable(false);
        stage.setTitle("INVENTARIO COMPRAS");
        stage.setScene(scene);
        stage.show();
        
        Stage myStage=(Stage)this.btn_VerTodas.getScene().getWindow();
        myStage.close();
    } catch (IOException ex){
        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void GoMenu(ActionEvent event) {
        try
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/MenuPrincipal.fxml"));
        Parent root=loader.load();
        Scene scene = new Scene(root);
//        ClientesController ouControlador = loader.getController();
//        ouControlador.constructorNuevo();
        
        Stage stage = new Stage();
        stage.setOnCloseRequest(even -> {even.consume();});
        stage.setResizable(false);
        stage.setTitle("MENU PRINCIPAL");
        stage.setScene(scene);
        stage.show();
        
        Stage myStage=(Stage)this.btn_MenuPrincipal.getScene().getWindow();
        myStage.close();
    } catch (IOException ex){
        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

     @FXML
    private void doAgregar(ActionEvent event){
        String producto,msg,id,fra, prov,ck_cre,check;
        int cantidad, tipo;
        check= "false";
        
        float vr_compra, vr_subtotal; 
        String date;
        
        tipo = 1;
        
        try{
            prov=this.cb_Proveedor.getSelectionModel().getSelectedItem();
            producto=this.cb_Producto.getSelectionModel().getSelectedItem();
            cantidad=Integer.parseInt(this.txt_cant.getText());
            fra=String.valueOf(nro_fra);
            date =String.valueOf(this.txt_fecha.getValue());
            
            if (this.ck_credito.isSelected()) {
                check ="true";
            }
            
                               
            if (producto== null||prov == null||producto.isEmpty()||prov.isEmpty() ){
               msg="Debe seleccionar item para las listas...Por favor revise..!!";
               this.showMessages (msg,1);
              } else if (date == null||date.isEmpty()||date.isBlank()){
                msg="Debe seleccionar una fecha...Por favor revise..!!";
                this.showMessages (msg,1);
              }              
            else{  
                  Producto prod = this.gestor.buscarProductoNombre(producto.toUpperCase());
                   if (prod !=null){
                       vr_compra = prod.getPrecioCompra();
                       id =prod.getCodigo();
                       vr_subtotal=vr_compra*cantidad;  
                       Total = Total + vr_subtotal;
              
                       CompraVenta com = new CompraVenta (fra,id,producto,prov,date,cantidad,vr_compra,vr_subtotal,Total,check,tipo); //se crea el objeto
                       if(!this.compras.contains(com))//se puso equals en la clase Cliente
                         { this.compras.add(com); 
                           this.tbl_compras.setItems(compras);
                           this.txt_total.setText(String.valueOf(Total));
                       } 
                       else
                       { msg="El item ya fue agregado... Revise...!!";
                         this.showMessages (msg,1);
                        }
                   }
            }   
        }catch(NumberFormatException nfe){
            msg="El tipo de datos de cantidad se encuentra incorrecto...Por favor revise..!!";
            this.showMessages (msg,1);
        }            
    }    
     
    @FXML
    private void GuardarCompra(ActionEvent event) {
        
           String msg;
           int x, cantcomp, cantinv, existencia;           
           x=0;         
                   
        for (CompraVenta comp : compras){
            
            cantcomp = comp.getCantidad();
            cantinv = gestor.buscarProducto(comp.getIdAfectado()).getExistencia(); 
            existencia = cantcomp+cantinv;
            
            gestor.modificarExistencias (comp.getIdAfectado(),existencia);
                                        
              if (x==compras.size()-1)             
              gestorcomp.guardaCompra(comp);
              x=x+1;
        } 
        if (x>=1) {
        this.txt_fra.setText(String.valueOf(nro_fra));        
        msg="La operacion ha sido exitosa!";
        this.showMessages (msg,2);    
        tbl_compras.refresh();}
        else{
        msg="No existen registros para Guardar...";
        this.showMessages (msg,1); 
        }
    }
    
    @FXML
    private void doEliminar(ActionEvent event) {
                String msg;
                CompraVenta com = this.tbl_compras.getSelectionModel().getSelectedItem();
           
                if(com != null){
                this.compras.remove (com);
                this.tbl_compras.refresh();
                Total = Total - com.getSubTotal();
                this.txt_total.setText(String.valueOf(Total));                
                }
                else
                {
                    msg="Debe seleccionar un registro";
                    this.showMessages (msg,1);
                }        
    }
    
      private void modelaTabla()
    {
        this.compras=FXCollections.observableArrayList();
        //Se mapea qué atributo del objeto va para cada columna...
        this.tbl_Producto.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tbl_Cantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.tc_Compra.setCellValueFactory(new PropertyValueFactory("precioCompra")); 
        this.tbl_Subtotal.setCellValueFactory(new PropertyValueFactory("subTotal"));        
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
    private void doNuevo(ActionEvent event) {
        Random r = new Random();
        nro_fra = r.nextInt(90000) + 10000; 
        
        Total=0;
        this.txt_fra.setText(String.valueOf(""));
        this.txt_cant.setText(String.valueOf(""));
        this.txt_total.setText(String.valueOf(""));
        this.cb_Producto.setValue(String.valueOf(""));
        this.cb_Proveedor.setValue(String.valueOf(""));
        this.txt_fecha.setConverter(null);
        this.ck_credito.setSelected(false);
        this.tbl_compras.getItems().clear();
        this.txt_cant.requestFocus();
        
    }
}
    
    

