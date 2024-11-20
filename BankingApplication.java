import java.util.ArrayList;
import java.util.Scanner;

// BankAccount class to represent individual accounts
class BankAccount {
    private String accountHolderName;
    private String accountNumber;
    private double balance;

    // Constructor
    public BankAccount(String accountHolderName, String accountNumber, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Getters and Setters
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    // Transfer method
    public boolean transfer(BankAccount targetAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            targetAccount.deposit(amount);
            System.out.println("Transferred " + amount + " to account " + targetAccount.getAccountNumber());
            return true;
        } else {
            System.out.println("Transfer failed due to insufficient funds or invalid amount.");
            return false;
        }
    }
}

// BankingSystem class to manage accounts and operations
class BankingSystem {
    private ArrayList<BankAccount> accounts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Method to create a new account
    public void createAccount() {
        System.out.println("Enter account holder's name: ");
        String name = scanner.nextLine();
        System.out.println("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.println("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine();  // consume the newline character

        BankAccount newAccount = new BankAccount(name, accountNumber, initialDeposit);
        accounts.add(newAccount);
        System.out.println("Account created successfully.");
    }

    // Method to find an account by account number
    public BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    // Method to handle deposit
    public void depositMoney() {
        System.out.println("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();  // consume the newline character
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to handle withdrawal
    public void withdrawMoney() {
        System.out.println("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();  // consume the newline character
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to handle fund transfer
    public void transferMoney() {
        System.out.println("Enter your account number: ");
        String fromAccountNumber = scanner.nextLine();
        BankAccount fromAccount = findAccount(fromAccountNumber);
        if (fromAccount != null) {
            System.out.println("Enter recipient account number: ");
            String toAccountNumber = scanner.nextLine();
            BankAccount toAccount = findAccount(toAccountNumber);
            if (toAccount != null) {
                System.out.println("Enter transfer amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();  // consume the newline character
                fromAccount.transfer(toAccount, amount);
            } else {
                System.out.println("Recipient account not found.");
            }
        } else {
            System.out.println("Your account not found.");
        }
    }

    // Method to view account details
    public void viewAccountDetails() {
        System.out.println("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Account holder: " + account.getAccountHolderName());
            System.out.println("Account number: " + account.getAccountNumber());
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    // Display menu options
    public void displayMenu() {
        System.out.println("Welcome to the Banking System");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. View Account Details");
        System.out.println("6. Exit");
    }

    // Main method to run the program
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    transferMoney();
                    break;
                case 5:
                    viewAccountDetails();
                    break;
                case 6:
                    System.out.println("Exiting the system.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

// Main class to start the banking system
public class BankingApplication {
    public static void main(String[] args) {
        BankingSystem system = new BankingSystem();
        system.run();
    }
}
