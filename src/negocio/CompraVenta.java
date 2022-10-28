package Negocio;


import java.util.Objects;

public class CompraVenta {

    private String idFactura;
    private String idAfectado;
    private String Fecha;
    private float Total;
    private String estado;
    private int tipoOperacion;
    private String nombre;
    private String proveedor;
    private int cantidad;
    private float precioCompra;
    private float subTotal;
    
    public CompraVenta(){
        this.idFactura = "";
        this.idAfectado= "";
        this.Fecha= null;
        this.Total= 0;
        this.estado= null;
        this.nombre= "";
        this.cantidad= 0;
        this.precioCompra= 0;
        this.subTotal= 0;
        this.tipoOperacion= 0;
    }
   /* public CompraVenta(String idFactura, String idAfectado, String Fecha, float Total, boolean estado, int tipoOperacion){
        this.idFactura = idFactura;
        this.idAfectado= idAfectado;
        this.Fecha= Fecha;
        this.Total= Total;
        this.estado= estado;
    }*/

     public CompraVenta(String idFactura,String idAfectado, String nombre, String proveedor, String fecha, int cantidad, float precioCompra, float subTotal, float Total, String estado, int tipoOperacion){
        this.idFactura = idFactura;
        this.idAfectado= idAfectado;
        this.nombre= nombre;
        this.cantidad= cantidad;
        this.precioCompra= precioCompra;
        this.subTotal= subTotal;
        this.Fecha= fecha;
        this.Total= Total;
        this.tipoOperacion= tipoOperacion;
        this.estado= estado;
        this.proveedor= proveedor;
    }
   
     public CompraVenta(String idFactura,String idAfectado,String proveedor, String Fecha, float Total, String estado, int tipoOperacion){
        this.idFactura = idFactura;
        this.idAfectado= idAfectado;
        this.Fecha= Fecha;
        this.Total= Total;
        this.tipoOperacion= tipoOperacion;
        this.estado= estado;
        this.proveedor= proveedor;     
    }
    
    public String getIdFactura() {
        return idFactura;
    }

    public String getIdAfectado() {
        return idAfectado;
    }

    public String getFecha() {
        return Fecha;
    }

    public float getTotal() {
        return Total;
    }

    public String getEstado() {
        return estado;
    }

    public int getTipoOperacion() {
        return tipoOperacion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public String getProveedor() {
        return proveedor;
    }
    

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public void setIdAfectado(String idAfectado) {
        this.idAfectado = idAfectado;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTipoOperacion(int tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    public String toString() {
        return this.idFactura + "," + this.idAfectado+ "," + this.proveedor + "," + this.Fecha+ "," + this.Total + "," + this.estado+ "," + this.tipoOperacion;
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
    
    final CompraVenta other = (CompraVenta) obj;
    if(!Objects.equals(this.getIdAfectado(), other.getIdAfectado())){
    return false;
    }
    return true;
    }
    
    
}
