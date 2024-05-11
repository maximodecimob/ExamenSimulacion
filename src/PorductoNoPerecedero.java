import java.io.Serializable;

public class PorductoNoPerecedero extends Producto implements Serializable {
    public PorductoNoPerecedero(String nombre, String descripcion,TipoMedida medida) throws Exception {
        super(nombre, descripcion,medida);
    }
}
