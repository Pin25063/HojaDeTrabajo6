import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
    private Map<String, List<String>> inventory;
    private Map<String, Integer> userCollection;

    public Inventory(Map<String, List<String>> inventory) {
        this.inventory = inventory;
        this.userCollection = new HashMap<>();
    }

    public void menu() {
        //Scanner scanner = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        int option = 0;

        while (option != 7) {
            System.out.println("\nMenu:");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar categoría de producto");
            System.out.println("3. Mostrar colección del usuario");
            System.out.println("4. Mostrar colección ordenada por categoría");
            System.out.println("5. Mostrar inventario");
            System.out.println("6. Mostrar inventario ordenado por categoría");
            System.out.println("7. Salir");
            System.out.print("\nSelecciona la acción que deseas realizar: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch(option) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    showCategory(scanner);
                    break;
                case 3:
                    showCollection();
                    break;
                case 4:
                    showCollectionInOrder();
                    break;
                case 5:
                    showInventory();
                    break;
                case 6:
                    showInventoryInOrder();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        }
    }

    private void addProduct(Scanner scanner) {
        System.out.print("Ingresa el nombre del producto: ");
        String product = scanner.nextLine().trim().toLowerCase();
        //product = "Mesas de jardín";
        String category = findCategory(product);

        System.out.println(product);
        if (category == null) {
            System.out.println("Producto no encontrado en el inventario");
            return;
        }

        userCollection.put(product, userCollection.getOrDefault(product, 0) + 1);
        System.out.println("Producto agregado");
    }

    private void showCategory(Scanner scanner) {
        System.out.print("Ingresa el nombre del producto: ");
        String product = scanner.nextLine().trim().toLowerCase();

        String category = findCategory(product);
        
        if(category == null){
            System.out.println("Producto no encontrado");
        } else {
            System.out.println("Categoría: " + category);
        }
    }

    private String findCategory(String product) {
        for (Map.Entry<String, List<String>> entry : inventory.entrySet()) {
            String category = entry.getKey();
            List<String> products = entry.getValue();

            if(products.contains(product)) {
                    return category;
                }

        }
        return null;
    }

    private void showCollection() {
        if (userCollection.isEmpty()) {
            System.out.println("Colección vacía");
            return;
        }

        System.out.println("\nColección:");
        System.out.printf("%-20s %-20s %-10s\n", "Categoría", "Producto", "Cantidad");

        for (String product : userCollection.keySet()) {
            String category = findCategory(product);
            int amount = userCollection.get(product);

            System.out.printf("%-20s %-20s %-10d\n", category, product, amount);
        }
    }

    private void showCollectionInOrder() {
        if (userCollection.isEmpty()) {
            System.out.println("Colección vacía");
            return;
        }
        System.out.println("\nColección ordenada por categoría:");
        System.out.printf("%-20s %-20s %-10s\n", "Categoría", "Producto", "Cantidad");

        for(Map.Entry<String, List<String>> entry : inventory.entrySet()){

            String category = entry.getKey();

            for(String product : entry.getValue()){

                if(userCollection.containsKey(product)){

                    int amount = userCollection.get(product);

                    System.out.printf("%-20s %-20s %-10d\n", category, product, amount);
                }
            }
        }
    }

    private void showInventory() {
       System.out.println("\nInventario:");
       System.out.printf("%-25s %-20s\n", "Producto", "Categoría");


        for(Map.Entry<String, List<String>> entry : inventory.entrySet()){

            String category = entry.getKey();

            for(String product : entry.getValue()){
                System.out.printf("%-25s %-20s\n", product, category);
            }
        }
    }

    private void showInventoryInOrder() {
        System.out.println("\nInventario ordenado por categoría:");
        System.out.printf("%-25s %-20s\n", "Categoría", "Producto");

        List<String> categories = new ArrayList<>(inventory.keySet());
        Collections.sort(categories);

        for(String category : categories){

            for(String product : inventory.get(category)){
                System.out.printf("%-25s %-20s\n", category, product);
            }
        }
    }
    
}
