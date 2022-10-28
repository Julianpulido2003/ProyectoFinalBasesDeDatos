/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control;

import Negocio.Cliente;
import Negocio.CompraVenta;
import Negocio.Producto;
import gestion.GestionCompra;
import gestion.GestionProductos;
import gestion.GestionClientes;
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
public class VentasController implements Initializable {

    GestionProductos gestor;
    GestionClientes gestorcli;
    GestionCompra gestorcomp;
    
    @FXML
    private Button btn_Agregar;
    @FXML
    private Button btn_Eliminar;
    @FXML
    private Button btn_VerTodas;
    @FXML
    private Button btn_MenuPrincipal;
    @FXML
    private ComboBox<String> cb_Cliente;
    @FXML
    private ComboBox<String> cb_Producto;
    private ObservableList<Producto> productos;
    private ObservableList<Cliente> clientes;
    private ObservableList<CompraVenta> ventas;
    private float Total=0;
    private int nro_fra=0;
    @FXML
    private TextField txf_fra;
    @FXML
    private TextField txf_cantidad;
    @FXML
    private TableView<CompraVenta> tbl_ventas;
    @FXML
    private TableColumn tblc_Producto;
    @FXML
    private TableColumn  tblc_Cantidad;
    @FXML
    private TableColumn  tblc_Venta;
    @FXML
    private TableColumn tblc_Subtotal;
    @FXML
    private CheckBox ckb_credito;
    @FXML
    private TextField txf_total;
    @FXML
    private DatePicker Dtp_fecha;
    @FXML
    private Button btn_GuardarVenta;
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
        this.gestorcli= new GestionClientes();
        this.clientes = gestorcli.getClientes();
              
        for(Cliente cli : clientes) {
        this.cb_Cliente.getItems().add(cli.getNombre());
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
    private void VerTodasVentas(ActionEvent event) {
        try
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/TodasVentas.fxml"));
        Parent root=loader.load();
        Scene scene = new Scene(root);

        this.ventas = gestorcomp.getCompraoVenta(2);        
         
        TodasVentasController ouControlador = loader.getController();
        ouControlador.constructorNuevo(ventas);
        
        Stage stage = new Stage();
        stage.setOnCloseRequest(even -> {even.consume();});
        stage.setResizable(false);
        stage.setTitle("INVENTARIO VENTAS");
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

  /*  private void addFecha(ActionEvent event) 
    {
        LocalDate inicio=this.Dtp_fecha.getValue();
        if(inicio!=null)
        {
            this.Dtp_fecha.setText(inicio.toString());
        }
    }*/
    
    @FXML
    private void doAgregar(ActionEvent event){
        String producto,msg,id,fra, cli,ck_cre,check;
        int cantidad, tipo, cantinv, existencia;
        check= "false";
        
        float vr_venta, vr_subtotal; 
        String date;
        
        tipo = 2;
        
        try{
            cli=this.cb_Cliente.getSelectionModel().getSelectedItem();
            producto=this.cb_Producto.getSelectionModel().getSelectedItem();
            cantidad=Integer.parseInt(this.txf_cantidad.getText());
            fra=String.valueOf(nro_fra);
            date =String.valueOf(this.Dtp_fecha.getValue());
            
            if (this.ckb_credito.isSelected()) {
                check ="true";
            }
                               
            if (producto == null || cli == null|| producto.isEmpty() || cli.isEmpty() ){
               msg="Debe seleccionar item para las listas...Por favor revise..!!";
               this.showMessages (msg,1);
            }else if (date == null|| date.isBlank() || date.isEmpty()){
                msg="Debe seleccionar una fecha...Por favor revise..!!";
                this.showMessages (msg,1);}            
            else{  
                  Producto prod = this.gestor.buscarProductoNombre(producto.toUpperCase());
                   if (prod !=null){
                       vr_venta = prod.getPrecioVenta();
                       id =prod.getCodigo();
                       cantinv = prod.getExistencia();
                       
                        if (cantinv < cantidad) {
                            msg="De ese producto solo se tienen "+cantinv+" unidades, desea llevarlas?";
                            if (showMessages (msg,3)){
                              cantidad = cantinv; 
                            }                         
                        }  
                      
                        if (cantidad <=cantinv){
                            vr_subtotal=vr_venta*cantidad;  
                            Total = Total + vr_subtotal;
              
                            CompraVenta com = new CompraVenta (fra,id,producto,cli,date,cantidad,vr_venta,vr_subtotal,Total,check,tipo); //se crea el objeto
                           if(!this.ventas.contains(com))//se puso equals en la clase Cliente
                             { this.ventas.add(com); 
                               this.tbl_ventas.setItems(ventas);
                               this.txf_total.setText(String.valueOf(Total));
                             } 
                        else
                            { msg="El item ya fue agregado... Revise...!!";
                              this.showMessages (msg,1);
                            }
                        }
                   }
            }   
        }catch(NumberFormatException nfe){
            msg="El tipo de datos de la cantidad se encuentra incorrecto...Por favor revise..!!";
            this.showMessages (msg,1);
        }            
    }    
     
    @FXML
    private void GuardarVenta(ActionEvent event) {
          String msg;
          int x, cantvta, cantinv, existencia;           
          x=0;       
           
        for (CompraVenta vta : ventas){
            cantvta = vta.getCantidad();
            cantinv = gestor.buscarProducto(vta.getIdAfectado()).getExistencia(); 
            existencia = cantinv-cantvta;
            
            gestor.modificarExistencias (vta.getIdAfectado(),existencia);
                         if (x==ventas.size()-1 && vta.getCantidad()>0)             
                gestorcomp.guardaCompra(vta);
                x=x+1;
            }
        if (x>=1) {
        this.txf_fra.setText(String.valueOf(nro_fra));        
        msg="La operacion ha sido exitosa!";
        this.showMessages (msg,2);    
        tbl_ventas.refresh();}
        else{
        msg="No existen registros para Guardar...";
        this.showMessages (msg,1); 
        }
    }
    
    @FXML
    private void doEliminar(ActionEvent event) {
                String msg;
                CompraVenta com = this.tbl_ventas.getSelectionModel().getSelectedItem();
           
                if(com != null){
                this.ventas.remove (com);
                this.tbl_ventas.refresh();
                Total = Total - com.getSubTotal();
                this.txf_total.setText(String.valueOf(Total)); 
                }
                else
                {
                    msg="Debe seleccionar un registro";
                    this.showMessages (msg,1);
                }        
    }
    
      private void modelaTabla()
    {
        this.ventas=FXCollections.observableArrayList();
        //Se mapea qué atributo del objeto va para cada columna...
        this.tblc_Producto.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tblc_Cantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.tblc_Venta.setCellValueFactory(new PropertyValueFactory("precioCompra")); 
        this.tblc_Subtotal.setCellValueFactory(new PropertyValueFactory("subTotal"));         
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
        this.txf_fra.setText(String.valueOf(""));
        this.txf_cantidad.setText(String.valueOf(""));
        this.txf_total.setText(String.valueOf(""));
        this.cb_Producto.setValue(String.valueOf(""));
        this.cb_Cliente.setValue(String.valueOf(""));
        this.Dtp_fecha.setConverter(null);
        this.ckb_credito.setSelected(false); 
        this.tbl_ventas.getItems().clear();
        this.txf_cantidad.requestFocus();
        
    }

    
}
   