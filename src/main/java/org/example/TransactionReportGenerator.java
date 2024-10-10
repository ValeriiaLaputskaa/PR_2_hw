package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    /*-------------------------------------------------*/
    public static void printExpenseReport(List<Transaction> transactions) {

        Map<String, Map<String, Double>> categoryMonthMap = new HashMap<>();

        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String monthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));

            if (!categoryMonthMap.containsKey(transaction.getCategory())) {
                categoryMonthMap.put(transaction.getCategory(), new HashMap<>());
            }

            Map<String, Double> monthMap = categoryMonthMap.get(transaction.getCategory());

            if (!monthMap.containsKey(monthYear)) {
                monthMap.put(monthYear, transaction.getAmount());
            } else {
                monthMap.put(monthYear, monthMap.get(monthYear) + transaction.getAmount());
            }
        }

        System.out.println("Звіт про витрати по категоріях та місяцях:");
        for (Map.Entry<String, Map<String, Double>> entry : categoryMonthMap.entrySet()) {
            String category = entry.getKey();
            System.out.println("Категорія: " + category);
            for (Map.Entry<String, Double> monthEntry : entry.getValue().entrySet()) {
                String monthYear = monthEntry.getKey();
                double totalAmount = monthEntry.getValue();

                // Додаємо перевірку, щоб уникнути негативного значення
                int starsCount = (int) Math.max(totalAmount / 1000, 0); // Перевірка, щоб значення не було негативним

                System.out.printf("  %s: %.2f грн %s%n", monthYear, totalAmount, "*".repeat(starsCount));
            }
        }
    }
}
