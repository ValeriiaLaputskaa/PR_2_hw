import org.example.Transaction;
import org.example.TransactionAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TransactionAnalyzerTest {

    @Test
    public void testCountTransactionsByMonth() {
        // Підготовка тестових даних
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід", "Без категорії");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата", "Продукти");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід", "Без категорії");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        // Виклик статичного методу для підрахунку транзакцій за місяць
        int countFeb = TransactionAnalyzer.countTransactionsByMonth("02-2023", transactions);
        int countMar = TransactionAnalyzer.countTransactionsByMonth("03-2023", transactions);

        // Перевірка результатів
        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }
}
