import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Supermercado implements Serializable {
    private String nombre;
    private ArrayList<Operacion> almacen;

    public Supermercado(String nombre) throws Exception {
        if(nombre == null|| nombre.isEmpty()){
            throw new Exception("me da la gana ponerlo");
        }
        this.nombre = nombre;
        this.almacen = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Operacion> getAlmacen() {
        return almacen;
    }

    public void setAlmacen(ArrayList<Operacion> almacen) {
        this.almacen = almacen;
    }
    public boolean comprar(Producto producto, double cantidad, LocalDate fecha,double precio){
        try {
            Operacion op = new Operacion(TipoOperacion.COMPRA,producto,cantidad,fecha,precio);
            if(op.getProducto() instanceof PorductoPerecedero){
                if(((PorductoPerecedero) op.getProducto()).getFechaCaducidad().isAfter(fecha)){
                    almacen.add(op);
                    return true;
                }
            }else {
                almacen.add(op);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    public boolean vender(Producto producto, double cantidad, LocalDate fecha,double precio){
        try {
            Operacion op = new Operacion(TipoOperacion.VENTA,producto,cantidad,fecha,precio);
            if(cantidadProducto(producto.getNombre())>=cantidad){
                almacen.add(op);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
        //Comandos arraylist
        /*
        almacen.add(op);
        almacen.size();
        almacen.isEmpty();
        almacen.contains(op);
        almacen.remove(op);
         */
    }
    public ArrayList<Operacion> proximaCaducidad(int numDias){
        ArrayList<Operacion> productosEnfecha= new ArrayList<>();
        LocalDate hoy = LocalDate.now();
        LocalDate fechaFinal = hoy.plusDays(numDias);
        for (int i = 0; i < almacen.size(); i++) {
            if(almacen.get(i).getOperacion()==TipoOperacion.COMPRA && almacen.get(i).getProducto() instanceof PorductoPerecedero){
                if(!((PorductoPerecedero) almacen.get(i).getProducto()).getFechaCaducidad().isBefore(hoy)&&
                !((PorductoPerecedero) almacen.get(i).getProducto()).getFechaCaducidad().isAfter(fechaFinal)){
                    productosEnfecha.add(almacen.get(i));
                }
            }
        }
        return productosEnfecha;
    }
    public double cantidadProducto(String nombre){
        double stock=0;
        for (int i = 0; i < almacen.size(); i++) {
            if(Objects.equals(almacen.get(i).getProducto().getNombre(), nombre)){
                if(almacen.get(i).getOperacion()==TipoOperacion.COMPRA){
                    stock += almacen.get(i).getCantidad();
                }else{
                    stock -= almacen.get(i).getCantidad();
                }
            }
        }
        return stock;
    }
}
