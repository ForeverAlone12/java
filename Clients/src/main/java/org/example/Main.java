package org.example;


import java.util.Scanner;
import java.util.TreeMap;

/**
 * Список покупок.
 */
public class Main {
    public static void main(String[] args) {

        var scanner = new Scanner(System.in);

        // покупатели с покупками
        var customer = new TreeMap<String, TreeMap<String, Integer>>();

        while (scanner.hasNext()) {

            var inputText = scanner.nextLine();

            if (inputText.equals("exit")) {
                break;
            }

            var parts = inputText.split(" ");

            var name = parts[0];
            var productName = parts[1];
            var count = Integer.parseInt(parts[2]);

            if (!customer.containsKey(name))
                customer.put(name, new TreeMap<>());

            var client = customer.get(name);

            if (!client.containsKey(productName))
                client.put(productName, 0);

            var oldCount = client.get(productName);

            client.put(productName, oldCount + count);
        }

        for (var entry : customer.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();

            System.out.println(key + ":");

            for (var product : value.entrySet()) {
                var keyProduct = product.getKey();
                var valueProduct = product.getValue();

                System.out.println(keyProduct + " " + valueProduct);
            }
        }
    }
}