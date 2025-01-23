import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int points = 0;
        String answer = "";
        String randomCountry = "";

        Map<String, String> countriesMap = new HashMap<>();

        String file = "src/countries.txt";

        try {
            //  Abrir el archivo para leer
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;

            //  Leer línea por línea
            while ((line = reader.readLine()) != null) {
                //  Separar clave y valor
                String[] parts = line.split(" ");
                if (parts.length == 2) { // Solo si hay una clave y un valor
                    String clave = parts[0];
                    String valor = parts[1];
                    countriesMap.put(clave, valor); // Guardar en el HashMap
                }
            }
            reader.close(); // Cerrar el archivo después de leer
        } catch (IOException e) {
            System.out.println("There was an error reading file: " + e.getMessage());
        }

//        // Mostrar lo que guardamos en el HashMap
//        System.out.println("File data in the HashMap: ");
//        List<String> claves = new ArrayList<>(countriesMap.keySet());
//        for (int i = 0; i < claves.size(); i++) {
//            String clave = claves.get(i);
//            System.out.println("Clave: " + clave + ", Valor: " + countriesMap.get(clave));

        List<String> countries = new ArrayList<>(countriesMap.keySet()); // Pasamos las claves a una lista

        System.out.println("Find the capitals of the countries. You have 10 attempts.");
        System.out.println("What is your name?");
        String userName = input.nextLine();

        for (int i = 0; i < 10; i++) {
            randomCountry = countries.get(random.nextInt(countries.size())); // Elegir un país aleatorio
            System.out.println("¿What is the capital of " + randomCountry + "? ");
            answer = input.nextLine();

            if (answer.equalsIgnoreCase(countriesMap.get(randomCountry))) {
                System.out.println("¡Well Done!");
                points++;
            } else {
                System.out.println("Incorrect. The capital of " + randomCountry + " es " + countriesMap.get(randomCountry));
            }
        }

        // Guardar la clasificación en un archivo llamado classificacio.txt
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("classificacio.txt", true))) {
            bw.write("User name: " + userName + ", Final points: " + points);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving rating: " + e.getMessage());
        }

        System.out.println("¡Game Over! :) ");
        System.out.println("User name: " + userName);
        System.out.println("Final points: " + points);

        input.close();
    }
}


