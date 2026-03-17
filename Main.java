import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClassFactory factory = new ClassFactory();
        
        System.out.print("1. Hashmap\n2. Treemap\n3. LinkedHashMap\n\nSelecciona el tipo de mapa que deseas utilizar: ");
        int option = scanner.nextInt();
        
        Map<String,List<String>> map = factory.mapFactory(option);

        if (map != null){
            try {
                List<String> listado = FileHelper.readFile("ListadoProducto.txt");
                
                for (String line : listado){
                    String[] parts = line.split("\\|");

                    String key = parts[0].trim();
                    String value = parts[1].trim().toLowerCase();
                    
                    if (!map.containsKey(key)) {
                        map.put(key, new ArrayList<String>());
                    }
                    map.get(key).add(value);
                }
               
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
                return;
            }
        } else {
            System.out.println("Opcion de Map no válida");
            return;
        }

        Inventory inventario = new Inventory(map);
        inventario.menu();
    }
}