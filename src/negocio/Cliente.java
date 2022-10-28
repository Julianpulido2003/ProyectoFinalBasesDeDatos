package Negocio;

import java.util.Objects;

public class Cliente extends ClienteProveedor {

    private String tipoCliente;

    public Cliente(){
        super();
    }

    public Cliente(Integer identificacion, String nombre, String tipoCliente){
        super(identificacion, nombre);
        this.tipoCliente= tipoCliente;
    }
    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    public String getTipoCliente() {
        return tipoCliente;
    }
    @Override
    public String toString() {
        return  this.getIdentificacion() + "," + this.getNombre()+ "," +this.tipoCliente;
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
    
    final Cliente other = (Cliente) obj;
    if(!Objects.equals(this.getIdentificacion(), other.getIdentificacion())){
    return false;
    }
    return true;
    }
}
