/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion;
import Negocio.CompraVenta;
import java.io.*;
import javax.swing.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionCompra {

    // variables de clase
    private String ruta;
  
    // METODOS obligatorios
    //constructor
    public GestionCompra() {
        // ingresar variables y invocar metodos locales
        this.ruta = "./src/Archivos/CompraVenta.txt";
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
  
    public void guardaCompra(CompraVenta com) {
        try {
            File file = new File(this.ruta);
            FileWriter fr = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fr);
            pw.println(com);
            pw.close();
            
        } catch (IOException xxx) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar la compra");
        }
    }
    
    public boolean verificaCompra(String idFactura) {
        
        boolean existe = false;
        FileReader file;
        BufferedReader br;
        String registro;                
        
        try {
            file = new FileReader(this.ruta);
            br= new BufferedReader (file);
           
            while ((registro = br.readLine()) != null) {
                String[] campos = registro.split(",");
                
                if (campos [0].equals (idFactura)){
                existe = true;
                break;
                }
            }
            
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Error buscando factura en archivo");
        }
        
         return existe;
    }     
    
    public boolean verificaProducto(String idProd) {
        
        boolean existe = false;
        FileReader file;
        BufferedReader br;
        String registro;                
        
        try {
            file = new FileReader(this.ruta);
            br= new BufferedReader (file);
           
            while ((registro = br.readLine()) != null) {
                String[] campos = registro.split(",");
                
                if (campos [1].equals (idProd)){
                existe = true;
                break;
                }
            }
            
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Error buscando producto en archivo");
        }
        
         return existe;
    }     
    
  public ObservableList<CompraVenta> getCompraVenta () {
        FileReader file;
        BufferedReader br;
        String registro;
        CompraVenta com = null;
        ObservableList<CompraVenta> co= FXCollections.observableArrayList();;

        try {
            file = new FileReader(this.ruta);
            br = new BufferedReader(file);
            while ((registro = br.readLine()) != null) {
                String[] campos = registro.split(",");
               // if (Integer.parseInt(campos[6])==tipo){
                com = new CompraVenta((campos[0]),(campos[1]), campos[2],campos[3], Float.parseFloat(campos[4]),campos[5],Integer.parseInt(campos[6]));
               // }
                co.add(com);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error buscando cliente");

        }
        return co;
    }
    
    public ObservableList<CompraVenta> getCompraoVenta (int tipo) {
        FileReader file;
        BufferedReader br;
        String registro;
        CompraVenta com = null;
        ObservableList<CompraVenta> co= FXCollections.observableArrayList();;

        try {
            file = new FileReader(this.ruta);
            br = new BufferedReader(file);
            while ((registro = br.readLine()) != null) {
                String[] campos = registro.split(",");
                if (Integer.parseInt(campos[6])==tipo){
                com = new CompraVenta((campos[0]),(campos[1]), campos[2],campos[3], Float.parseFloat(campos[4]),campos[5],Integer.parseInt(campos[6]));
                co.add(com);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error buscando cliente");

        }
        return co;
    }
  
    public void modificarExistencias(String idfra, String estado) {
        boolean existe = false;
        ObservableList<CompraVenta> com = this.getCompraVenta();

         for (CompraVenta fra : com) {

            if (fra.getIdFactura().equals(idfra)) {
                existe = true;
                fra.setEstado(estado);
                 this.remplazarArchivo(com);             
                break;
            }
        }
      if(!existe)
          JOptionPane.showMessageDialog(null, "Ese codigo no existe!");
         
    }
    
    private void remplazarArchivo(ObservableList<CompraVenta> compras) {
    try {
        File file = new File(this.ruta);
        FileWriter fr = new FileWriter(file, false);
        PrintWriter pw = new PrintWriter(fr);
        for (CompraVenta comp : compras)
            pw.println(comp);
        pw.close();
    } catch (IOException xxx) {
        JOptionPane.showMessageDialog(null, "No se pudo eliminar la compra ");
    }
 }
        
}