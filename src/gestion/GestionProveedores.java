/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion;
import Negocio.ClienteProveedor;
import java.io.*;
import javax.swing.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class GestionProveedores {

    // variables de clase
    private String ruta;
  
    // METODOS obligatorios
    //constructor
    public GestionProveedores() {
        // ingresar variables y invocar metodos locales
        this.ruta = "./src/Archivos/Proveedores.txt";
        this.verificaArchivo();
    }

    private void verificaArchivo() {
        try {
            File filex = new File(this.ruta);
            if (!filex.exists())
                filex.createNewFile();
        } catch (IOException ex) {
            System.out.println("Problemas con la ruta del archivo...");
        }
    }

    //metodos de servicio
  
    public void guardaCliente(ClienteProveedor pro) {
        try {
            File file = new File(this.ruta);
            FileWriter fr = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fr);
            pw.println(pro);
            pw.close();
            
        } catch (IOException xxx) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el cliente");
        }
    }
    
    public boolean verificaCliente(Integer codigo) {
        
        boolean existe = false;
        FileReader file;
        BufferedReader br;
        String registro;                
        
        try {
            file = new FileReader(this.ruta);
            br= new BufferedReader (file);
           
            while ((registro = br.readLine()) != null) {
                String[] campos = registro.split(",");
                
                if (campos [0].equals (codigo.toString())){
                existe = true;
                break;
                }
            }
            
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Error buscando cliente en archivo");
        }
        
         return existe;
    }     
    
    public ObservableList<ClienteProveedor> getProveedores() {
        FileReader file;
        BufferedReader br;
        String registro;
        ClienteProveedor pro = null;
        ObservableList<ClienteProveedor> prove= FXCollections.observableArrayList();;

        try {
            file = new FileReader(this.ruta);
            br = new BufferedReader(file);
            while ((registro = br.readLine()) != null) {
                String[] campos = registro.split(",");
                pro = new ClienteProveedor(Integer.parseInt(campos[0]), campos[1]);
                prove.add(pro);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error buscando cliente");

        }
        return prove;
    }
            
}