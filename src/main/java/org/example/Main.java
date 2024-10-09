package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(monthYear,transactions);

        TransactionReportGenerator.printBalanceReport(totalBalance);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear,transactionsCount);

        TransactionReportGenerator.printTopExpensesReport(transactions);



/*-------------------------------*/
// Фільтрування транзакцій за діапазоном дат
        String startDate = "01-01-2024";
        String endDate = "31-12-2024";


        List<Transaction> filteredTransactions = TransactionAnalyzer.filterTransactions(transactions, startDate, endDate);

        Transaction maxExpense = TransactionAnalyzer.findMaxExpense(filteredTransactions);
        Transaction minExpense = TransactionAnalyzer.findMinExpense(filteredTransactions);

        if (maxExpense != null) {
            System.out.println("Найбільша витрата: " + maxExpense.getDescription() + " " + maxExpense.getAmount());
        } else {
            System.out.println("Витрат не знайдено");
        }

        if (minExpense != null) {
            System.out.println("Найменша витрата: " + minExpense.getDescription() + " " + minExpense.getAmount());
        } else {
            System.out.println("Витрат не знайдено");
        }


        /*------------------------------------*/
        TransactionReportGenerator.printExpenseReport(transactions);
    }
}