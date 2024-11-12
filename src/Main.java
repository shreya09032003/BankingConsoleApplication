import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Using a static ArrayList to manage users for this session-based application.
    private static ArrayList<User> users = new ArrayList<>();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Loop to show the main menu or user options based on login status
        while (true) {
            if (loggedInUser == null) {
                showMainMenu();
                int choice = getUserChoice(input);

                switch (choice) {
                    case 1 -> registerUser(input);   // Register a new user
                    case 2 -> loginUser(input);      // Log in as an existing user
                    case 3 -> {
                        System.out.println("\nThank you for using the Banking System. Goodbye!");
                        return;  // Exit the application
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } else {
                showUserMenu();
                int choice = getUserChoice(input);

                switch (choice) {
                    case 1 -> openAccount(input);            // Open a new account
                    case 2 -> depositFunds(input);           // Deposit funds into account
                    case 3 -> withdrawFunds(input);          // Withdraw funds from account
                    case 4 -> checkAccountBalance();         // Display account balance
                    case 5 -> displayTransactionHistory(input); // Show transaction history
                    case 6 -> calculateInterest(input);      // Calculate interest for savings accounts
                    case 7 -> {
                        loggedInUser = null;
                        System.out.println("You have been logged out successfully.");
                    }
                    default -> System.out.println("Invalid selection, please try again.");
                }
            }
        }
    }

    // Display the main menu for users who are not logged in
    private static void showMainMenu() {
        System.out.println("\n########## Welcome to the Bank App ##########");
        System.out.println("Please choose an option:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    // Display menu options for users who are logged in
    private static void showUserMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Open a new account");
        System.out.println("2. Deposit funds");
        System.out.println("3. Withdraw funds");
        System.out.println("4. Check balance");
        System.out.println("5. View transaction history");
        System.out.println("6. Calculate interest (Savings only)");
        System.out.println("7. Logout");
    }

    // Get and validate the user's menu choice
    private static int getUserChoice(Scanner input) {
        try {
            return input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number.");
            input.nextLine(); // Clear the invalid input
            return -1;
        }
    }

    // Register a new user by accepting their username and password
    private static void registerUser(Scanner scanner) {
        System.out.print("Please enter a username: ");
        String username = scanner.next();
        System.out.print("Create a password: ");
        String password = scanner.next();

        users.add(new User(username, password));
        System.out.println("You have been registered successfully.");
    }

    // Login an existing user by verifying their username and password
    private static void loginUser(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.checkLogin(username, password)) {
                loggedInUser = user;
                System.out.println("Login successful. Welcome back!");
                return;
            }
        }
        System.out.println("Incorrect username or password. Please try again.");
    }

    // Open a new account for the logged-in user
    private static void openAccount(Scanner scanner) {
        System.out.print("Enter the name for the new account holder: ");
        scanner.nextLine(); // Clear the buffer
        String accountHolderName = scanner.nextLine();
        System.out.print("What type of account would you like to open? (savings/checking): ");
        String accountType = scanner.nextLine();

        if (!accountType.equalsIgnoreCase("savings") && !accountType.equalsIgnoreCase("checking")) {
            System.out.println("Invalid account type. Please choose 'savings' or 'checking'.");
            return;
        }

        try {
            System.out.print("Enter your initial deposit amount: ");
            double initialDeposit = scanner.nextDouble();
            if (initialDeposit <= 0) {
                System.out.println("Initial deposit must be greater than zero.");
                return;
            }

            Account newAccount = new Account(String.valueOf(loggedInUser.getAccounts().size() + 1), accountHolderName, accountType, initialDeposit);
            loggedInUser.addAccount(newAccount);
            System.out.println("Your account has been successfully opened.");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid deposit amount.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    // Deposit money into the selected account
    private static void depositFunds(Scanner scanner) {
        Account account = chooseAccount(scanner);
        if (account != null) {
            try {
                System.out.print("Enter the deposit amount: ");
                double amount = scanner.nextDouble();
                if (amount <= 0) {
                    System.out.println("Deposit amount must be positive.");
                } else {
                    account.deposit(amount);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please provide a valid deposit amount.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    // Withdraw money from the selected account
    private static void withdrawFunds(Scanner scanner) {
        Account account = chooseAccount(scanner);
        if (account != null) {
            try {
                System.out.print("Enter the withdrawal amount: ");
                double amount = scanner.nextDouble();
                if (amount <= 0) {
                    System.out.println("Withdrawal amount must be positive.");
                } else {
                    account.withdraw(amount);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please provide a valid withdrawal amount.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    // Display the balance for all accounts of the logged-in user
    private static void checkAccountBalance() {
        for (Account account : loggedInUser.getAccounts()) {
            System.out.println("Your account balance is: " + account.getBalance());
        }
    }

    // Display the transaction history of a selected account
    private static void displayTransactionHistory(Scanner scanner) {
        Account account = chooseAccount(scanner);
        if (account != null) {
            account.displayTransactions();
        }
    }

    // Calculate interest for a selected savings account
    private static void calculateInterest(Scanner scanner) {
        Account account = chooseAccount(scanner);
        if (account != null) {
            System.out.print("Enter the number of months to calculate interest for: ");
            int months = getUserChoice(scanner);
            account.calculateMonthlyInterest(months);
        }
    }

    // Choose an account for performing operations like deposit, withdrawal, etc.
    private static Account chooseAccount(Scanner scanner) {
        ArrayList<Account> accounts = loggedInUser.getAccounts();
        if (accounts.isEmpty()) {
            System.out.println("You do not have any accounts. Please open one first.");
            return null;
        }

        System.out.println("Please select an account to proceed:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". Account Balance: " + accounts.get(i).getBalance());
        }

        int choice = getUserChoice(scanner);
        if (choice < 1 || choice > accounts.size()) {
            System.out.println("Invalid selection.");
            return null;
        }
        return accounts.get(choice - 1);
    }
}
