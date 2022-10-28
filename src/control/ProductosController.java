/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control;

import Negocio.Producto;
import gestion.GestionProductos;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author julia
 */
public class ProductosController implements Initializable {
    
    GestionProductos gestor;

    @FXML
    private Button btn_Foto;
    @FXML
    private Button btn_Nuevo;
    @FXML
    private Button btn_eliminar;
    @FXML
    private Button btn_Inventario;
    @FXML
    private Button btn_Salir;
    @FXML
    private Button btn_Modificar;
    @FXML
    private Button btn_Guardar;
    @FXML
    private ImageView imgFoto;
    private String nombreFoto;
    @FXML
    private Button btn_Buscar;
    @FXML
    private TextField txt_codigo;
    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_precioCompra;
    
    private ObservableList<Producto> productos;
    
    private int x =0;
    @FXML
    private Button btn_Siguiente;
    @FXML
    private Button btn_Anterior;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        this.gestor= new GestionProductos();
        this.nombreFoto="defecto.png";      
        this.productos = gestor.getProducto();
        
        mostrar (x);      
    }    
    
    private void mostrar (int x)
    {
        
     this.txt_codigo.setText (productos.get(x).getCodigo());
     this.txt_nombre.setText (productos.get(x).getNombre());
     this.txt_precioCompra.setText(String.valueOf(productos.get(x).getPrecioCompra()));
        
        try{    InputStream stream = new FileInputStream("./src/imagenes/"+productos.get(x).getFoto());
                Image image = new Image(stream);
                this.imgFoto.setImage(image);} 
        catch (IOException ex) {
                 System.out.println("Problemas con la ruta de la imagen...");
                 this.imgFoto.setImage(null);
                }         
        this.txt_codigo.requestFocus();       
    }            
       
    @FXML
    private void goNext(ActionEvent event) {
                
        if (x!=productos.size()-1){
        x= x+1;
        mostrar (x);}     
    }

    @FXML
    
    private void goPrevious(ActionEvent event) {
     
      if (x!=0){
        x= x-1;
        mostrar (x);}
    }

    @FXML
    private void GoInventario(ActionEvent event) {
        try
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Inventario.fxml"));
        Parent root=loader.load();
        Scene scene = new Scene(root);
                      
        InventarioController ouControlador = loader.getController();
        ouControlador.constructorNuevo(productos);
        
        Stage stage = new Stage();
        stage.setOnCloseRequest(even -> {even.consume();});
        stage.setResizable(false);
        stage.setTitle("INVENTARIO");
        stage.setScene(scene);
        stage.show();
        
        Stage myStage=(Stage)this.btn_Salir.getScene().getWindow();
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

        Stage stage = new Stage();
        stage.setOnCloseRequest(even -> {even.consume();});
        stage.setResizable(false);
        stage.setTitle("MENU PRINCIPAL");
        stage.setScene(scene);
        stage.show();
        
        Stage myStage=(Stage)this.btn_Salir.getScene().getWindow();
        myStage.close();
    } catch (IOException ex){
        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    @FXML
    private void doGuardar(ActionEvent event) {
        
        float precioCompra,precioVenta;
        int existencias;
        String code, name,foto,msg;
        
        try{
            code=(this.txt_codigo.getText());
            name=this.txt_nombre.getText();
            precioCompra=Float.parseFloat(this.txt_precioCompra.getText());
            precioVenta= (precioCompra * 1.25f);
            existencias=0;
            foto=this.nombreFoto;
            
            if( name.isEmpty() ){
               msg="Existen campos sin diligenciar...Por favor revise..!!";
               this.showMessages (msg,1);
            } else if (!name.matches(".*[A-Za-z].*")){
               msg="Los datos para el nombre se encuentran incorrectos...Por favor revise..!!";
               this.showMessages (msg,1);
            }else{
            Producto produ = new Producto (code,name,precioCompra,precioVenta,existencias,foto); //se crea el objeto
                     
              if(!gestor.verificaProducto(produ.getCodigo())){
                  gestor.guardaProducto(produ); 
                  msg="El producto ha sido creado!";
                  this.showMessages (msg,2);  
                } else{
                  msg="El codigo ya existe... Revise...!!";
                  this.showMessages (msg,1);
                }            
            }
        }catch(NumberFormatException nfe){
            msg="El tipo de datos para el precio se encuentra incorrecto...Por favor revise..!!";
            this.showMessages (msg,1);
        }   
        this.productos = gestor.getProducto();
    }

    @FXML
    private void NuevoProducto(ActionEvent event) {
                        
        this.txt_codigo.setText(String.valueOf(""));
        this.txt_nombre.setText(String.valueOf(""));
        this.txt_precioCompra.setText(String.valueOf(""));
       
        this.txt_codigo.requestFocus();     
        
        try{   InputStream stream = new FileInputStream("./src/imagenes/defecto.png");
                Image image = new Image(stream);
                this.imgFoto.setImage(image);   
               } catch (IOException ex) {
                 System.out.println("Problemas con la ruta de la imagen...");
                 this.imgFoto.setImage(null);
                }        
    }
    


    @FXML
    private void doEliminar(ActionEvent event) {
        String msg = "¿Estas seguro de eliminar el producto?";
        if (showMessages (msg,3)){
            gestor.eliminarProducto(txt_codigo.getText());
            this.txt_codigo.setText(String.valueOf(""));
            this.txt_nombre.setText(String.valueOf(""));
            this.txt_precioCompra.setText(String.valueOf(""));
            this.imgFoto.setImage(null);
            this.txt_codigo.requestFocus();        
        }
        this.productos = gestor.getProducto();
    }

    @FXML
    private void Modificar(ActionEvent event) {
        
        float precioCompra,precioVenta;
        int existencias;
        String code, name,foto,msg;
        
        try{
            code=(this.txt_codigo.getText());
            name=this.txt_nombre.getText();
            precioCompra=Float.parseFloat(this.txt_precioCompra.getText());
            precioVenta= (precioCompra * 1.25f);
            foto=this.nombreFoto;
            
            if( name.isEmpty() ){
               msg="Existen campos sin diligenciar...Por favor revise..!!";
               this.showMessages (msg,1);
            } else if (!name.matches(".*[A-Za-z].*")){
               msg="Los datos para el nombre se encuentran incorrectos...Por favor revise..!!";
               this.showMessages (msg,1);
            }else{
                     
              if(gestor.verificaProducto(code)){
                  gestor.modificarProducto(code,name,precioCompra,precioVenta, foto); 
                  msg="El producto ha sido modificado!";
                  this.showMessages (msg,2);  
                } else{
                  msg="El codigo no existe... Revise...!!";
                  this.showMessages (msg,1);
                }            
            }
        }catch(NumberFormatException nfe){
            msg="El tipo de datos de valor se encuentra incorrecto...Por favor revise..!!";
            this.showMessages (msg,1);
        }        
        this.productos = gestor.getProducto();
    }
    
    @FXML
    private void doBuscarFoto(ActionEvent event) 
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar imagen");
        fileChooser.setInitialDirectory(new File("./src/imagenes"));

        File imgFile = fileChooser.showOpenDialog(new Stage());
        this.nombreFoto=imgFile.getName();

        if(this.nombreFoto != null)
        {
         Image image = new Image("file:" + imgFile.getAbsolutePath());
         this.imgFoto.setImage(image);
        } 
    }
 

    @FXML
    private void Buscar(ActionEvent event) {
        String msg, imagen;
       Producto prod = gestor.buscarProducto(this.txt_codigo.getText().toUpperCase());

        if (prod != null)
        { this.txt_codigo.setText(prod.getCodigo());
            this.txt_nombre.setText(prod.getNombre());
            this.txt_precioCompra.setText(String.valueOf(prod.getPrecioCompra()));
            try{
                InputStream stream = new FileInputStream("./src/imagenes/"+prod.getFoto());
                Image image = new Image(stream);
                this.imgFoto.setImage(image);   
               } catch (IOException ex) {
                 System.out.println("Problemas con la ruta de la imagen...");
                 this.imgFoto.setImage(null);
                }            
            this.txt_codigo.requestFocus();   }     
        else
        {msg="El código no existe..!!";
            this.showMessages (msg,1);}      
        
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

  
}
