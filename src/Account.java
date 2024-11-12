import java.util.ArrayList;

public class Account {
    private String accountNumber;
    private String accountHolderName;
    private String accountType;
    private double balance;
    private ArrayList<Transaction> transactions; // Stores all transactions for this account
    private double monthlyInterestRate = 0.0025; // 0.25% per month for savings accounts

    public Account(String accountNumber, String accountHolderName, String accountType, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>(); // Initialize transaction list
        logTransaction("Deposit", initialDeposit); // Log initial deposit
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            logTransaction("Deposit", amount); // Log deposit transaction
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            logTransaction("Withdrawal", amount); // Log withdrawal transaction
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    private void logTransaction(String type, double amount) {
        Transaction transaction = new Transaction(type, amount); // Create a new transaction
        transactions.add(transaction); // Add transaction to the list
    }

    public void displayTransactions() {
        System.out.println("Transaction history for account: " + accountNumber);
        for (Transaction transaction : transactions) {
            System.out.println(transaction); // Print each transaction
        }
    }

    public void calculateMonthlyInterest(int months) {
        if (accountType.equalsIgnoreCase("savings")) {
            double interest = balance * Math.pow(1 + monthlyInterestRate, months) - balance;
            System.out.println("Interest for " + months + " months: " + interest);
        } else {
            System.out.println("Interest calculation is only applicable to savings accounts.");
        }
    }

    public double getBalance() {
        return balance;
    }
}
