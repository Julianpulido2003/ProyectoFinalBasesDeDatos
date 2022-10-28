/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion;
import Negocio.Cliente;
import java.io.*;
import javax.swing.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class GestionClientes {

    // variables de clase
    private String ruta;
  
    // METODOS obligatorios
    //constructor
    public GestionClientes() {
        // ingresar variables y invocar metodos locales
        this.ruta = "./src/Archivos/Clientes.txt";
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
  
    public void guardaCliente(Cliente cli) {
        try {
            File file = new File(this.ruta);
            FileWriter fr = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fr);
            pw.println(cli);
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
    
    public ObservableList<Cliente> getClientes() {
        FileReader file;
        BufferedReader br;
        String registro;
        Cliente cli = null;
        ObservableList<Cliente> clien= FXCollections.observableArrayList();;

        try {
            file = new FileReader(this.ruta);
            br = new BufferedReader(file);
            while ((registro = br.readLine()) != null) {
                String[] campos = registro.split(",");
                cli = new Cliente(Integer.parseInt(campos[0]), campos[1], campos[2]);
                clien.add(cli);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error buscando cliente");

        }
        return clien;
    }
         
     
 /*   public void verEstudiante() {
        String code;

        code = JOptionPane.showInputDialog("Digite el codigo a buscar: ");
        Estudiante stud = this.buscaEstudiante(code);

        if (stud != null)
            System.out.println(stud);
        else
            JOptionPane.showMessageDialog(null, "Ese Codigo No existe!");
    }

    private Estudiante buscaEstudiante(String code) {
        FileReader file;
        BufferedReader br;
        String registro;
        Estudiante stud = null;

        try {
            file = new FileReader(this.ruta);
            br = new BufferedReader(file);
            while ((registro = br.readLine()) != null) {
                String[] campos = registro.split(",");
                if (campos[0].equals(code)) {
                    stud = new Estudiante(campos[0], campos[1], campos[2].charAt(0), Float.parseFloat(campos[3]));
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("Error buscando estudiante");
        }
        return stud;
    }  
        
    public void eliminarEstudiante() {
        String code;
        boolean existe = false;
        ArrayList<Estudiante> students = this.getEstudiantes();

        code = JOptionPane.showInputDialog("Digite el codigo del estudiante a eliminar: ");
        for (Estudiante stud : students) {
            if (stud.getCodigo().equals(code)) {
                existe = true;
                students.remove(stud);
                this.remplazarArchivo(students);
                JOptionPane.showMessageDialog(null, "El Estudiante ha sido eliminado en el archivo");
                break;
            }
        }

        if(!existe){
            JOptionPane.showMessageDialog(null, "El codigo no existe");
        }
    }

    private void remplazarArchivo(ArrayList<Estudiante> students) {
    try {
        File file = new File(this.ruta);
        FileWriter fr = new FileWriter(file, false);
        PrintWriter pw = new PrintWriter(fr);
        for (Estudiante student : students)
            pw.println(student);
        pw.close();
    } catch (IOException xxx) {
        JOptionPane.showMessageDialog(null, "No se pudo eliminar el estudiante");
    }
 }
    
    public void modifyCodigo() {
        String code, newDato;
        boolean existe;
        ArrayList<Estudiante> students = this.getEstudiantes();

        code = JOptionPane.showInputDialog("Digite el codigo del estudiante a modificar: ");
        for (Estudiante stud : students) {

            if (stud.getCodigo().equals(code)) {
                do {
                    newDato = JOptionPane.showInputDialog("Digite el nuevo codigo del estudiante: ");
                    existe = this.verificaExistencia(newDato);
                    if (existe)
                        JOptionPane.showMessageDialog(null, "Ese codigo ya exite");
                } while (existe);
                stud.setCodigo(newDato);
                this.remplazarArchivo(students);
                JOptionPane.showMessageDialog(null, "El Estudiante ha sido modificado en el archivo");
                break;
            }
        }
    }
   
    public boolean hayEstudiantes() {
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
        ArrayList<Estudiante> students = this.getEstudiantes();
           for (Estudiante stud : students) {
            if (stud.getCodigo().equals(code)) {
                existe = true;
                break;
            }
        }
           return existe;
    }
*/
}