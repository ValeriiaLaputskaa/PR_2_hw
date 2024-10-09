package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {


    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }


public static int countTransactionsByMonth(String monthYear, List<Transaction> transactions) {
    int count = 0;
    for (Transaction transaction : transactions) {
        LocalDate date = LocalDate.parse(transaction.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
        if (transactionMonthYear.equals(monthYear)) {
            count++;
        }
    }
    return count;
}

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Вибірка лише витрат (від'ємні значення)
                .sorted(Comparator.comparing(Transaction::getAmount)) // Сортування за сумою
                .limit(10) // Обмеження результату першими 10 записами
                .collect(Collectors.toList()); // Збір результату в список
    }


/*-------------------------------*/

    public static List<Transaction> filterTransactions(List<Transaction> transactions, String startDate, String endDate) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate(), formatter);
            if (!transactionDate.isBefore(start) && !transactionDate.isAfter(end)) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }

    public static Transaction findMaxExpense(List<Transaction> transactions) {
        Transaction maxExpense = null;

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                if (maxExpense == null || transaction.getAmount() < maxExpense.getAmount()) {
                    maxExpense = transaction; // Оновлюємо maxExpense
                }
            }
        }

        return maxExpense;
    }
    // Метод для пошуку найменшої витрати

    public static Transaction findMinExpense(List<Transaction> transactions) {
        Transaction minExpense = null;

        for (Transaction transaction : transactions) {

            if (transaction.getAmount() < 0) {
                if (minExpense == null || transaction.getAmount() > minExpense.getAmount()) {
                    minExpense = transaction;
                }
            }
        }

        return minExpense;
    }
}
