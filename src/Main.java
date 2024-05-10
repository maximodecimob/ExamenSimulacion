import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            Supermercado supermercado = new Supermercado("Mercadona");
            PorductoPerecedero manzana = new PorductoPerecedero("Manzana","Fruta",TipoMedida.UNIDADES,
                    LocalDate.now().plusDays(10));
            PorductoPerecedero peras = new PorductoPerecedero("Peras","Fruta",TipoMedida.UNIDADES,
                    LocalDate.now().plusDays(10));
            supermercado.comprar(manzana,1000,LocalDate.of(2023,05,26),100);
            supermercado.comprar(peras,500,LocalDate.of(2023,05,26),10);
            PorductoNoPerecedero primero = new PorductoNoPerecedero("Platano","Fruta",TipoMedida.KILOGRAMOS);
            PorductoNoPerecedero segundo = new PorductoNoPerecedero("Naranjas","Color Naranja",TipoMedida.KILOGRAMOS);
            supermercado.comprar(primero,100,LocalDate.of(2024,04,05),100);
            supermercado.comprar(segundo,50,LocalDate.of(2024,04,04),80);
            supermercado.vender(manzana,50,LocalDate.of(2023,06,26),20);
            supermercado.vender(peras,50,LocalDate.of(2023,06,26),10);
            supermercado.vender(primero,50,LocalDate.of(2024,06,26),30);
            ArrayList<Operacion> op = supermercado.proximaCaducidad(15);
            for (int i = 0;i<op.size();i++){
                String nombre ;
                if(op.get(i).getProducto().getMedida()==TipoMedida.KILOGRAMOS){
                    nombre = "Kg";
                } else if (op.get(i).getProducto().getMedida()==TipoMedida.LITROS) {
                    nombre = "l";
                }else{
                    nombre = "ud";
                }
                String caduca;
                if (op.get(i).getProducto() instanceof PorductoPerecedero){
                    caduca = "Cad: "+((PorductoPerecedero) op.get(i).getProducto()).getFechaCaducidad().toString();
                }else{
                    caduca = "";
                }
                System.out.printf(" %-22s %-12.2f%2s %-12s Valor: %-7.2f€ \n",op.get(i).getProducto().getNombre(),op.get(i).getCantidad(),nombre,
                        caduca,op.get(i).getPrecio());
            }
            FileOutputStream file = new FileOutputStream("supermercado.sup");
            ObjectOutputStream archivo = new ObjectOutputStream(file);
            archivo.writeObject(supermercado);
            archivo.close();
            crearArchivo(supermercado);

        } catch (Exception e) {
            System.out.println("Error al crear el supermercado");
        }

    }

    private static void crearArchivo(Supermercado supermercado) {
        PrintWriter pw;
        ArrayList<Operacion>almacen = supermercado.getAlmacen();
        try {
            pw = new PrintWriter("Operaciones supermercado.txt");
            pw.println("Productos perecederos");
            for(Operacion op: almacen){
                if(op.getProducto() instanceof PorductoPerecedero){
                    pw.printf("10%s,20%s,%7.2f,%7.2f,%10s,%10f\n",op.getOperacion(),op.getProducto().getNombre(),op.getCantidad(),op.getPrecio(),op.getFecha()
                            ,((PorductoPerecedero) op.getProducto()).getFechaCaducidad());
                }
            }
            for(Operacion op: almacen){
                if(op.getProducto() instanceof PorductoNoPerecedero){
                    pw.printf("10%s,20%s,%7.2f,%7.2f,%10s\n",op.getOperacion(),op.getProducto().getNombre(),op.getCantidad(),op.getPrecio(),op.getFecha();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    }
}
/*
 Comentario
 */
/**
 * Documentacion
 *
 * @params nombre
 * @params desripcion
 * @retrun boolean
 */