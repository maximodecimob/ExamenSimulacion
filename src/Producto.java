public abstract class Producto {
    private String nombre;
    private String descripcion;

    private TipoMedida medida;
    public Producto(String nombre, String descripcion,TipoMedida medida) throws Exception {
        if(nombre == null||nombre.isEmpty()||descripcion==null||descripcion.isEmpty()||medida == null){
            throw new Exception();
        }
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.medida = medida;
    }

    public TipoMedida getMedida() {
        return medida;
    }

    public void setMedida(TipoMedida medida) {
        this.medida = medida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
