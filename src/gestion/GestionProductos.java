/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion;
import Negocio.Producto;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class GestionProductos {

    // variables de clase
    private String ruta;
  
    // METODOS obligatorios
    //constructor
    public GestionProductos() {
        // ingresar variables y invocar metodos locales
        this.ruta = "./src/Archivos/Productos.txt";
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
  
    public void guardaProducto(Producto produ) {
        try {
            File file = new File(this.ruta);
            FileWriter fr = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fr);
            pw.println(produ);
            pw.close();
            
        } catch (IOException xxx) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el cliente");
        }
    }
          
    public boolean verificaProducto(String codigo) {
        
        boolean existe = false;
        FileReader file;
        BufferedReader br;
        String registro;                
        
        try {
            file = new FileReader(this.ruta);
            br= new BufferedReader (file);
           
            while ((registro = br.readLine()) != null) {
                String[] campos = registro.split(",");
                
                if (campos [0].equals (codigo)){
                existe = true;
                break;
                }
            }            
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Error buscando cliente en archivo");
        }        
         return existe;
    }     
    
    public ObservableList<Producto> getProducto() {
        FileReader file;
        BufferedReader br;
        String registro;
        Producto produ = null;
        ObservableList<Producto> prod= FXCollections.observableArrayList();;

        try {
            file = new FileReader(this.ruta);
            br = new BufferedReader(file);
            while ((registro = br.readLine()) != null) {
                String[] campos = registro.split(",");
                produ = new Producto((campos[0]),campos[1],Float.parseFloat(campos[2]),Float.parseFloat(campos[3]),Integer.parseInt(campos[4]), campos[5]);
                prod.add(produ);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error buscando producto");
        }
        return prod;
    }
   
    
    public Producto buscarProducto(String code) {
        FileReader file;
        BufferedReader br;
        String registro;
        Producto prod = null;

        try {
            file = new FileReader(this.ruta);
            br = new BufferedReader(file);
            while ((registro = br.readLine()) != null) {
                String[] campos = registro.split(",");
                if (campos[0].toUpperCase().equals(code)) {
                    prod = new Producto((campos[0]),campos[1],Float.parseFloat(campos[2]),Float.parseFloat(campos[3]),Integer.parseInt(campos[4]), campos[5]);
                    break;
                }
            }
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(null,"Error buscando producto");
        }
        return prod;
    }  
    
    public Producto buscarProductoNombre(String nombre) {
        FileReader file;
        BufferedReader br;
        String registro;
        Producto prod = null;

        try {
            file = new FileReader(this.ruta);
            br = new BufferedReader(file);
            while ((registro = br.readLine()) != null) {
                String[] campos = registro.split(",");
                if (campos[1].toUpperCase().equals(nombre)) {
                    prod = new Producto((campos[0]),campos[1],Float.parseFloat(campos[2]),Float.parseFloat(campos[3]),Integer.parseInt(campos[4]), campos[5]);
                    break;
                }
            }
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(null,"Error buscando producto");
        }
        return prod;
    }  
        
    public void eliminarProducto(String code) {
        boolean existe = false;
        ObservableList<Producto> products = this.getProducto();

         
        for (Producto prod : products) {
            if (prod.getCodigo().equals(code)) {
                existe = true;
                products.remove(prod);
                this.remplazarArchivo(products);
                JOptionPane.showMessageDialog(null, "El Producto ha sido eliminado en el archivo");
                break;
            }
        }

        if(!existe){
            JOptionPane.showMessageDialog(null, "El codigo no existe");
        }
    }

    private void remplazarArchivo(ObservableList<Producto> products) {
    try {
        File file = new File(this.ruta);
        FileWriter fr = new FileWriter(file, false);
        PrintWriter pw = new PrintWriter(fr);
        for (Producto produ : products)
            pw.println(produ);
        pw.close();
    } catch (IOException xxx) {
        JOptionPane.showMessageDialog(null, "No se pudo eliminar el producto");
    }
 }
    
    public void modificarProducto(String codigo, String nombre, float precioCompra, float precioVenta, String foto) {
        boolean existe = false;
        ObservableList<Producto> products = this.getProducto();

         for (Producto produ : products) {

            if (produ.getCodigo().equals(codigo)) {
                existe = true;
                produ.setNombre(nombre);
                produ.setPrecioCompra(precioCompra);
                produ.setPrecioVenta(precioVenta);
                produ.setFoto(foto);
                this.remplazarArchivo(products);             
                break;
            }
        }
      if(!existe)
          JOptionPane.showMessageDialog(null, "Ese codigo no existe!");
         
    }
    
    public void modificarExistencias(String codigo, int cantidad) {
        boolean existe = false;
        ObservableList<Producto> products = this.getProducto();

         for (Producto produ : products) {

            if (produ.getCodigo().equals(codigo)) {
                existe = true;
                produ.setExistencia(cantidad);
                 this.remplazarArchivo(products);             
                break;
            }
        }
      if(!existe)
          JOptionPane.showMessageDialog(null, "Ese codigo no existe!");
         
    }
   
    public boolean hayProductos() {
        FileReader file;
        BufferedReader br;
        String registro;
        boolean hay = false;

        try {
            file = new FileReader(this.ruta);
            br = new BufferedReader(file);
            while ((registro = br.readLine()) != null) {
                hay = true;
                break;
            }

        } catch (IOException ex) {
            System.out.println("fallo buscando al estudiante");
        }
        return hay;
    }

    public boolean verificaExistencia(String code) {
         boolean existe = false;
        ObservableList<Producto> products = this.getProducto();
           for (Producto produ : products) {
            if (produ.getCodigo().equals(code)) {
                existe = true;
                break;
            }
        }
           return existe;
    }

}