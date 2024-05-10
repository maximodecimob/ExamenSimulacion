import java.time.LocalDate;

public class PorductoPerecedero extends Producto{
    private LocalDate fechaCaducidad;

    public PorductoPerecedero(String nombre, String descripcion, LocalDate fechaCaducidad) throws Exception {
        super(nombre, descripcion);
        if(fechaCaducidad==null){
            throw new Exception();
        }
        this.fechaCaducidad = fechaCaducidad;
    }
    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }
    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
}
