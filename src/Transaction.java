import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String transactionType;
    private double amount;
    private String timestamp;

    public Transaction(String transactionType, double amount) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    @Override
    public String toString() {
        return transactionType + " - " + amount + " on " + timestamp;
    }
}
