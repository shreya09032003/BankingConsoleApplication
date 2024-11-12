# Console Banking System

## Introduction
This Java-based Console Banking System is designed to simulate essential banking operations. Users can register, log in, manage their accounts, make deposits and withdrawals, track balances, and view transaction history. For savings accounts, the system also calculates monthly interest.

## Key Features

### User Authentication
- Create a new account by registering with a username and password.
- Secure login using registered credentials.

### Account Management
- Open a new account by providing personal details, account type, and initial deposit.
- Supports two types of accounts: savings and checking.

### Transaction Handling
- Deposit funds into the account.
- Withdraw funds from the account while ensuring sufficient balance.
- Each transaction is logged with details like amount, type, and date.

### Statement Generation
- Generate a transaction statement listing all deposits and withdrawals.

### Interest Calculation
- For savings accounts, the system automatically calculates and adds monthly interest.
- Interest is calculated based on a fixed rate.

### Balance Inquiry
- Check the current balance of each account at any time.

## Core Classes

### User
This class represents the users, storing their login credentials and associated accounts.

### BankAccount
Manages the account's transactions (deposits and withdrawals) and maintains the balance. It also handles interest calculations for savings accounts.

### Transaction
Keeps a record of each transaction, storing details such as the transaction type (deposit/withdrawal), amount, and the date of the transaction.

### BankingApp
The central controller class that manages user input, displays menus, and processes user choices.

## Application Workflow

1. **Register**: Create a new user account by choosing a unique username and password.
2. **Login**: Access your account using your registered credentials.
3. **Open an Account**: Provide account holder details, choose an account type, and make an initial deposit.
4. **Transactions**: Perform deposits and withdrawals on any of your accounts.
5. **Balance & Statements**: View account balances and transaction histories.
6. **Interest**: Calculate and apply monthly interest to savings accounts.
7. **Logout**: End the current session and return to the main menu.

## Running the Application

1. **Compile the code**:

   To compile all Java files, use the following command:

   ```bash
   javac *.java
