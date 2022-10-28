package Negocio;

import java.util.Objects;

public class ClienteProveedor {

    private Integer identificacion;
    private String nombre;
    
    public ClienteProveedor(){
        this.identificacion = null;
        this.nombre= "";
    }
    
    public ClienteProveedor(Integer identificacion, String nombre){
        this.identificacion = identificacion;
        this.nombre= nombre;
        
    }       
    
    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }
    @Override
    public String toString() {
        return this.identificacion + "," + this.nombre;
    }
      
    @Override
    public boolean equals(Object obj) {
    if (this == obj){
    return true;
    }
    if (obj == null){
    return false;
    }
    
    if (getClass() != obj.getClass()){
        return false;
    }
    
    final ClienteProveedor other = (ClienteProveedor) obj;
    if (!Objects.equals(this.identificacion, other.identificacion)){
    return false;
    }
    return true;
    }
    
}
