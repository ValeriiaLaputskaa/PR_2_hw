import org.example.Transaction;
import org.example.TransactionCSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReadTransactionsTest {
    @Test
    public void testReadTransactions() throws IOException {
        // Створення тимчасового файлу
        File tempFile = File.createTempFile("transactions", ".csv");
        tempFile.deleteOnExit(); // Забезпечує видалення файлу після завершення тесту


        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("2023-01-01,100.0,Дохід\n");
            writer.write("2023-01-02,-50.0,Витрата\n");
            writer.write("2023-01-03,150.0,Дохід\n");
        }


        String filePath = tempFile.toURI().toString(); // Отримання шляху до файлу у вигляді URL рядка
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);


        Assertions.assertNotNull(transactions, "Транзакції не повинні бути null");
        Assertions.assertEquals(3, transactions.size(), "Кількість транзакцій неправильна");


        Assertions.assertEquals("2023-01-01", transactions.get(0).getDate());
        Assertions.assertEquals(100.0, transactions.get(0).getAmount());
        Assertions.assertEquals("Дохід", transactions.get(0).getDescription());

        Assertions.assertEquals("2023-01-02", transactions.get(1).getDate());
        Assertions.assertEquals(-50.0, transactions.get(1).getAmount());
        Assertions.assertEquals("Витрата", transactions.get(1).getDescription());

        Assertions.assertEquals("2023-01-03", transactions.get(2).getDate());
        Assertions.assertEquals(150.0, transactions.get(2).getAmount());
        Assertions.assertEquals("Дохід", transactions.get(2).getDescription());
    }

}
