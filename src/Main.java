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
            supermercado.comprar(manzana,1000,LocalDate.of(2023, 5,26),100);
            supermercado.comprar(peras,500,LocalDate.of(2023,5,26),10);
            PorductoNoPerecedero primero = new PorductoNoPerecedero("Platano","Fruta",TipoMedida.KILOGRAMOS);
            PorductoNoPerecedero segundo = new PorductoNoPerecedero("Naranjas","Color Naranja",TipoMedida.KILOGRAMOS);
            supermercado.comprar(primero,100,LocalDate.of(2024,4,5),100);
            supermercado.comprar(segundo,50,LocalDate.of(2024,4,4),80);
            supermercado.vender(manzana,50,LocalDate.of(2023,6,26),20);
            supermercado.vender(peras,50,LocalDate.of(2023,6,26),10);
            supermercado.vender(primero,50,LocalDate.of(2024,6,26),30);
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
                    caduca = "Cad: "+((PorductoPerecedero) op.get(i).getProducto()).getFechaCaducidad();
                }else{
                    caduca = "";
                }
                System.out.println(op.get(i).getProducto().getNombre()+"\t"+ op.get(i).getCantidad()+nombre+" \t"+
                        caduca+"\tValor: "+op.get(i).getPrecio()+"€");
                System.out.printf("%-10s %6.2f %-2s %-20s Valor: %7.2f€\n",op.get(i).getProducto().getNombre(),op.get(i).getCantidad(),nombre,
                        caduca,op.get(i).getPrecio());
            }
            //Guardar Datos
            FileOutputStream file = new FileOutputStream("supermercado.sup");
            ObjectOutputStream archivo = new ObjectOutputStream(file);
            archivo.writeObject(supermercado);
            archivo.close();
            //generar archivo
            crearArchivo(supermercado);

        } catch (Exception e) {
            System.out.println("Error al crear el supermercado");
        }

    }

    private static void crearArchivo(Supermercado supermercado) {
        ArrayList<Operacion>almacen = supermercado.getAlmacen();
        try {//Aquí me falta hacer en productos no perecederos que no aparezca la fecha de caducidad y poner kg/ud/l
            PrintWriter pw = new PrintWriter("Operaciones supermercado.txt");
            pw.println("Productos perecederos");
            for(Operacion op: almacen){
                if(op.getProducto() instanceof PorductoPerecedero){
                    pw.printf("10%s,20%s,%7.2f,%7.2f,%10s,%10f\n",op.getOperacion(),op.getProducto().getNombre(),op.getCantidad(),op.getPrecio(),op.getFecha()
                            ,((PorductoPerecedero) op.getProducto()).getFechaCaducidad());
                }
            }
            pw.println("Productos no perecederos");
            for(Operacion op: almacen){
                if(op.getProducto() instanceof PorductoNoPerecedero){
                    pw.printf("10%s,20%s,%7.2f,%7.2f,%10s\n",op.getOperacion(),op.getProducto().getNombre(),op.getCantidad(),op.getPrecio(),op.getFecha());
                }
            }
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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