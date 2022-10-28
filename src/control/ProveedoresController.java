/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control;

import gestion.GestionProveedores;
import Negocio.ClienteProveedor;



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
public class ProveedoresController implements Initializable {
    
    GestionProveedores gestor;

    @FXML
    private Button btn_Salir;
    @FXML
    private TextField txt_IDProveedor;
    @FXML
    private TextField txt_Nombre;
    @FXML
    private Button btn_agregar;
    @FXML
    private Button btn_verTodos;
    @FXML
    private TableColumn<?, ?> tbl_Identificacion;
    @FXML
    private TableColumn<?, ?> tbl_nombre;
    @FXML
    private Button btn_guardar;
    @FXML
    private TableView<ClienteProveedor> tblProveedores;
    
    private ObservableList<ClienteProveedor> proveedores;

    /**
     * Initializes the controller class.
     */
    @Override   
    public void initialize(URL url, ResourceBundle rb) {
        this.modelaTabla();
        this.gestor= new GestionProveedores();
        
        tblProveedores.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.txt_IDProveedor.setText(newValue.getIdentificacion().toString());
                this.txt_Nombre.setText( newValue.getNombre());               
            }
        });
             
        
         
       
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
        
        Stage myStage=(Stage)this.btn_Salir.getScene().getWindow();
        myStage.close();
    } catch (IOException ex){
        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    

    @FXML
    private void doAgregar(ActionEvent event) {
        Integer id;
        String name, msg;
        try{
            id=Integer.parseInt(this.txt_IDProveedor.getText());
            name=this.txt_Nombre.getText();
            
                
            if( name.isEmpty() ){
               msg="Existen campos sin diligenciar...Por favor revise..!!";
               this.showMessages (msg,1);
            } else if (!name.matches(".*[A-Za-z]")){
               msg="Los datos para el nombre se encuentran incorrectos...Por favor revise..!!";
               this.showMessages (msg,1);
            }else{
            ClienteProveedor pro = new ClienteProveedor (id,name); //se crea el objeto
            if(!this.proveedores.contains(pro) && !gestor.verificaCliente(pro.getIdentificacion())) //se puso equals en la clase Cliente
              { this.proveedores.add(pro); 
                this.tblProveedores.setItems(proveedores);}
            else
              { msg="La identificación ya existe... Revise...!!";
                this.showMessages (msg,1);
              }
            }
        }catch(NumberFormatException nfe){
            msg="El tipo de datos del ID se encuentra incorrecto...Por favor revise..!!";
            this.showMessages (msg,1);
        }
    }

    @FXML
    private void doVerTodos(ActionEvent event) {
        this.proveedores = gestor.getProveedores();

        for (ClienteProveedor pro : proveedores) {
           this.tblProveedores.setItems(proveedores);
        }
    }

    @FXML
    private void doGuardar(ActionEvent event) {
        String msg;
        for (ClienteProveedor pro : proveedores){ 
            if(!gestor.verificaCliente(pro.getIdentificacion())){
           gestor.guardaCliente(pro);  }             
        }   
        this.txt_IDProveedor.setText(String.valueOf(""));
        this.txt_Nombre.setText(String.valueOf(""));
        this.txt_IDProveedor.requestFocus(); 
        
        msg="La operacion ha sido exitosa!";
        this.showMessages (msg,2);
    }
    private void modelaTabla()
    {
        this.proveedores=FXCollections.observableArrayList();
        //Se mapea qué atributo del objeto va para cada columna...
        this.tbl_Identificacion.setCellValueFactory(new PropertyValueFactory("identificacion"));
        this.tbl_nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
            
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
