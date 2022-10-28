package Negocio;

public class Producto {

    private String codigo;
    private String nombre;
    private float precioCompra;
    private float precioVenta;
    private int existencia;
    private String foto;

    public Producto(){
        this.codigo = "";
        this.nombre= "";
        this.precioCompra= 0;
        this.precioVenta= 0;
        this.existencia= 0;
        this.foto= "";
    }
    public Producto(String codigo, String nombre, float precioCompra, float precioVenta, int existencia, String foto){
        this.codigo = codigo;
        this.nombre= nombre;
        this.precioVenta= precioVenta;
        this.precioCompra= precioCompra;
        this.existencia= existencia;
        this.foto= foto;
    }

    public Producto( String nombre){
        this.nombre= nombre;        
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public int getExistencia() {
        return existencia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    @Override
    public String toString() {
        return this.codigo + "," + this.nombre + "," + this.precioCompra+ "," + this.precioVenta + "," + this.existencia + "," + this.foto;
    }

}
