import java.time.LocalDate;

public class Operacion {
    private TipoOperacion operacion;
    private Producto producto;
    private double cantidad;
    private LocalDate fecha;
    private double precio;

    public TipoOperacion getOperacion() {
        return operacion;
    }

    public void setOperacion(TipoOperacion operacion) {
        this.operacion = operacion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Operacion(TipoOperacion operacion, Producto producto, double cantidad, LocalDate fecha, double precio) throws Exception {
        if(operacion==null|| producto==null||cantidad<0||fecha==null||precio<0){
            throw new Exception("Has metido mal los datos");
        }
        this.operacion = operacion;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.precio = precio;
    }
}
