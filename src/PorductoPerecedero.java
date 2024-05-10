import java.time.LocalDate;

public class PorductoPerecedero extends Producto{
    private LocalDate fechaCaducidad;

    public PorductoPerecedero(String nombre, String descripcion,TipoMedida medida, LocalDate fechaCaducidad) throws Exception {
        super(nombre, descripcion,medida);
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
