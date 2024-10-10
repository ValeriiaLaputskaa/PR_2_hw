package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {
    public static List<Transaction> readTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            URL url = new URL(filePath);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");

                    if (values.length >= 3) {
                        // Виклик методу для визначення категорії на основі опису
                        String category = categorizeTransaction(values[2]);
                        Transaction transaction = new Transaction(values[0], Double.parseDouble(values[1]), values[2], category);
                        transactions.add(transaction);
                    } else {
                        System.err.println("Неправильний формат рядка: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    // Метод для визначення категорії на основі опису
    private static String categorizeTransaction(String description) {
        description = description.toLowerCase();  // Приведення до нижнього регістру для спрощення порівняння

        if (description.contains("сільпо") || description.contains("Магазин")) {
            return "Магазин";
        } else if (description.contains("ресторан") || description.contains("кафе") || description.contains("кав'ярня")) {
            return "Кафе/Ресторан";
        } else if (description.contains("аптека")) {
            return "Аптека";
        } else if (description.contains("бензин") || description.contains("заправка")) {
            return "Заправка";
        } else if (description.contains("кінотеатр")) {
            return "Розваги";
        } else if (description.contains("подарунки") || description.contains("новорічні прикраси")) {
            return "Подарунки/Шопінг";
        } else if (description.contains("зарплата") || description.contains("фріланс") || description.contains("авторські")) {
            return "Дохід";
        } else if (description.contains("комунальні послуги")) {
            return "Комунальні платежі";
        } else if (description.contains("інші витрати")) {
            return "Інше";
        } else {
            return "Без категорії";  // Категорія за замовчуванням
        }
    }
}
