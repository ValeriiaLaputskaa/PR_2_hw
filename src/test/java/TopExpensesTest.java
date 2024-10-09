import org.example.Transaction;
import org.example.TransactionAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TopExpensesTest {
    @Test
    public void testFindTopExpenses() {
        Transaction transaction1 = new Transaction("01-01-2024", -500.0, "Витрата 1", "Опис 1");
        Transaction transaction2 = new Transaction("02-01-2024", -1500.0, "Витрата 2", "Опис 2");
        Transaction transaction3 = new Transaction("03-01-2024", -200.0, "Витрата 3", "Опис 3");
        Transaction transaction4 = new Transaction("04-01-2024", -1000.0, "Витрата 4", "Опис 4");
        Transaction transaction5 = new Transaction("05-01-2024", -3000.0, "Витрата 5", "Опис 5");
        Transaction transaction6 = new Transaction("06-01-2024", -700.0, "Витрата 6", "Опис 6");
        Transaction transaction7 = new Transaction("07-01-2024", -8000.0, "Витрата 7", "Опис 7");
        Transaction transaction8 = new Transaction("08-01-2024", -4000.0, "Витрата 8", "Опис 8");
        Transaction transaction9 = new Transaction("09-01-2024", -6000.0, "Витрата 9", "Опис 9");
        Transaction transaction10 = new Transaction("10-01-2024", -9000.0, "Витрата 10", "Опис 10");
        Transaction transaction11 = new Transaction("11-01-2024", -11000.0, "Витрата 11", "Опис 11"); // Додаткова витрата

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3, transaction4,
                transaction5, transaction6, transaction7, transaction8, transaction9, transaction10, transaction11);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        Assertions.assertEquals(10, topExpenses.size(), "Кількість найбільших витрат неправильна");

        Assertions.assertEquals(-11000.0, topExpenses.get(0).getAmount(), "Неправильна найбільша витрата");
        Assertions.assertEquals(-9000.0, topExpenses.get(1).getAmount(), "Неправильна друга найбільша витрата");
        Assertions.assertEquals(-8000.0, topExpenses.get(2).getAmount(), "Неправильна третя найбільша витрата");
        Assertions.assertEquals(-6000.0, topExpenses.get(3).getAmount(), "Неправильна четверта найбільша витрата");
        Assertions.assertEquals(-4000.0, topExpenses.get(4).getAmount(), "Неправильна п'ята найбільша витрата");
        Assertions.assertEquals(-3000.0, topExpenses.get(5).getAmount(), "Неправильна шоста найбільша витрата");
        Assertions.assertEquals(-1500.0, topExpenses.get(6).getAmount(), "Неправильна сьома найбільша витрата");
        Assertions.assertEquals(-1000.0, topExpenses.get(7).getAmount(), "Неправильна восьма найбільша витрата");
        Assertions.assertEquals(-700.0, topExpenses.get(8).getAmount(), "Неправильна дев'ята найбільша витрата");
        Assertions.assertEquals(-500.0, topExpenses.get(9).getAmount(), "Неправильна десята найбільша витрата");
    }
}
