/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control;

import gestion.GestionClientes;
import Negocio.Cliente;


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
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author Julian Pulido
 */
public class ClientesController implements Initializable {
    
    GestionClientes gestor;
      
    @FXML
    private Button btn_salir;
    @FXML
    private TableView<Cliente> tblClientes;
    @FXML
    private TextField txt_IDCliente;
    @FXML
    private TextField txt_Nombre;
    @FXML
    private Button btn_agregar;
    @FXML
    private Button btn_verTodos;
    @FXML
    private TableColumn tbl_IDCLiente;
    @FXML
    private TableColumn tbl_Nombre;
    @FXML
    private TableColumn tbl_Tipo;
    
    private ObservableList<Cliente> clientes; //Se crea un contenedor en memoria
    @FXML
    private Button btn_guardar;
    @FXML
    private ChoiceBox<String> ComboTipo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.modelaTabla();
        this.gestor= new GestionClientes();
        
        tblClientes.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.txt_IDCliente.setText(newValue.getIdentificacion().toString());
                this.txt_Nombre.setText( newValue.getNombre());               
            }
        });
             
        this.llenaCombos();
        String atributo ="Seleccione uno"; 
        this.ComboTipo.getSelectionModel().select(atributo);  
    }    
        
    private void llenaCombos()
    {
        this.ComboTipo.getItems().addAll("Persona","Empresa");
    }
    
    @FXML
    private void doAgregar(ActionEvent event){
        Integer id;
        String name, tipo, msg;
        try{
            id=Integer.parseInt(this.txt_IDCliente.getText());
            name=this.txt_Nombre.getText();
            tipo=this.ComboTipo.getValue();
                
            if( name.isEmpty() ){
               msg="Existen campos sin diligenciar...Por favor revise..!!";
               this.showMessages (msg,1);
            }else if (tipo == "Seleccione uno"){
               msg="Debe seleccionar un tipo...Por favor revise..!!";
               this.showMessages (msg,1);
            } else if (!name.matches(".*[A-Za-z]")){
               msg="Los datos para el nombre se encuentran incorrectos...Por favor revise..!!";
               this.showMessages (msg,1);
            }else{
            Cliente cli = new Cliente (id,name,tipo); //se crea el objeto
            if(!this.clientes.contains(cli) && !gestor.verificaCliente(cli.getIdentificacion())) //se puso equals en la clase Cliente
              { this.clientes.add(cli); 
                this.tblClientes.setItems(clientes);}
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
    private void doGuardar(ActionEvent event) {
          String msg;
        for (Cliente cli : clientes){ 
            if(!gestor.verificaCliente(cli.getIdentificacion())){
           gestor.guardaCliente(cli);  }             
        }   
        this.txt_IDCliente.setText(String.valueOf(""));
        this.txt_Nombre.setText(String.valueOf(""));
        this.txt_IDCliente.requestFocus(); 
        
        msg="La operacion ha sido exitosa!";
        this.showMessages (msg,2);                  
    }
    
    @FXML
    private void DoVerTodos(ActionEvent event) {
        this.clientes = gestor.getClientes();

        for (Cliente cli : clientes) {
           this.tblClientes.setItems(clientes);
        }                 
    }
    
    @FXML
    private void doSalir(ActionEvent event){
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
        
        Stage myStage=(Stage)this.btn_salir.getScene().getWindow();
        myStage.close();
    } catch (IOException ex){
        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    private void doLimpiar(ActionEvent event){
    this.txt_IDCliente.setText(String.valueOf(""));
    this.txt_Nombre.setText(String.valueOf(""));
    this.txt_IDCliente.requestFocus();
     }
        
    private void modelaTabla()
    {
        this.clientes=FXCollections.observableArrayList();
        //Se mapea qué atributo del objeto va para cada columna...
        this.tbl_IDCLiente.setCellValueFactory(new PropertyValueFactory("identificacion"));
        this.tbl_Nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tbl_Tipo.setCellValueFactory(new PropertyValueFactory("tipoCliente"));    
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
