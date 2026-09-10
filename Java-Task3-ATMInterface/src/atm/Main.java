
package atm;

import atm.model.Account;
import atm.model.Transaction;
import atm.service.ATM;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ATM atm = new ATM();

    public static void main(String[] args) {

        while (true) {

            showWelcomeMenu();

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    login();
                    break;

                case "2":
                    createAccount();
                    break;

                case "3":
                    System.out.println();
                    System.out.println("Thank you for using ATM Interface.");
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showWelcomeMenu() {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║              ATM INTERFACE                   ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║  1. Login                                    ║");
        System.out.println("║  2. Create New Account                       ║");
        System.out.println("║  3. Exit                                     ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
    }

    private static void createAccount() {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║             CREATE NEW ACCOUNT               ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        String fullName;

        while (true) {

            System.out.print("Enter Full Name: ");
            fullName = scanner.nextLine().trim();

            if (!fullName.isEmpty()) {
                break;
            }

            System.out.println("Name cannot be empty.");
        }

        String userId;

        while (true) {

            System.out.print("Create User ID: ");
            userId = scanner.nextLine().trim();

            if (userId.isEmpty()) {
                System.out.println("User ID cannot be empty.");
                continue;
            }

            if (atm.userIdExists(userId)) {
                System.out.println(
                        "User ID already exists. Please choose another."
                );
                continue;
            }

            break;
        }

        String pin;

        while (true) {

            System.out.print("Create 4-digit PIN: ");
            pin = readHiddenPin();

            if (pin.matches("\\d{4}")) {
                break;
            }

            System.out.println("PIN must contain exactly 4 digits.");
        }

        double initialBalance;

        while (true) {

            System.out.print("Enter Initial Deposit: ");
            String balanceInput = scanner.nextLine().trim();

            try {

                initialBalance = Double.parseDouble(balanceInput);

                if (initialBalance < 0) {
                    System.out.println(
                            "Initial deposit cannot be negative."
                    );
                    continue;
                }

                break;

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid amount.");
            }
        }

        Account account =
                atm.createAccount(
                        fullName,
                        userId,
                        pin,
                        initialBalance
                );

        String balanceText =
                String.format(
                        "PKR %,.2f",
                        account.getBalance()
                );

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║          ACCOUNT CREATED SUCCESSFULLY       ║");
        System.out.println("╠══════════════════════════════════════════════╣");

        System.out.printf(
                "║ Name       : %-31s ║%n",
                account.getFullName()
        );

        System.out.printf(
                "║ User ID    : %-31s ║%n",
                account.getUserId()
        );

        System.out.printf(
                "║ Account No.: %-31s ║%n",
                account.getAccountId()
        );

        System.out.printf(
                "║ Balance    : %-31s ║%n",
                balanceText
        );

        System.out.println("╚══════════════════════════════════════════════╝");

        System.out.println();
        System.out.println("Your account has been created.");
        System.out.println("Please remember your User ID and PIN.");
    }

    private static void login() {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║                  LOGIN                       ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        int attempts = 0;
        boolean loggedIn = false;

        while (attempts < 3) {

            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine().trim();

            System.out.print("Enter PIN: ");
            String pin = readHiddenPin();

            if (atm.login(userId, pin)) {

                loggedIn = true;
                break;
            }

            attempts++;

            System.out.println();
            System.out.println("Invalid User ID or PIN.");
            System.out.println(
                    "Attempts remaining: " + (3 - attempts)
            );
        }

        if (!loggedIn) {

            System.out.println();
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║              ACCESS DENIED                  ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.println("Too many incorrect attempts.");
            return;
        }

        System.out.println();
        System.out.println("Login successful!");

        showATMMenu();
    }

    private static String readHiddenPin() {

        try {

            java.io.Console console = System.console();

            if (console != null) {

                char[] password =
                        console.readPassword();

                return new String(password);
            }

        } catch (Exception ignored) {
        }

        StringBuilder pin = new StringBuilder();

        while (true) {

            try {

                int character = System.in.read();

                if (character == '\n' ||
                        character == '\r') {

                    System.out.println();
                    break;
                }

                if (character == 8) {

                    if (pin.length() > 0) {

                        pin.deleteCharAt(
                                pin.length() - 1
                        );

                        System.out.print("\b \b");
                    }

                } else {

                    pin.append((char) character);
                    System.out.print("*");
                }

            } catch (Exception e) {

                return scanner.nextLine().trim();
            }
        }

        return pin.toString();
    }

    private static void showATMMenu() {

        while (atm.getCurrentAccount() != null) {

            Account account = atm.getCurrentAccount();

            String balanceText =
                    String.format(
                            "PKR %,.2f",
                            account.getBalance()
                    );

            String accountNumber =
                    account.getAccountId();

            String maskedAccountNumber =
                    "****" + accountNumber.substring(3);

            System.out.println();
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║                 ATM DASHBOARD                ║");
            System.out.println("╠══════════════════════════════════════════════╣");

            System.out.printf(
                    "║ Name       : %-31s ║%n",
                    account.getFullName()
            );

            System.out.printf(
                    "║ Account No.: %-31s ║%n",
                    maskedAccountNumber
            );

            System.out.printf(
                    "║ Balance    : %-31s ║%n",
                    balanceText
            );

            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║  1. Transaction History                     ║");
            System.out.println("║  2. Withdraw                                ║");
            System.out.println("║  3. Deposit                                 ║");
            System.out.println("║  4. Transfer                                ║");
            System.out.println("║  5. Quit                                    ║");
            System.out.println("╚══════════════════════════════════════════════╝");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    showTransactionHistory();
                    break;

                case "2":
                    withdraw();
                    break;

                case "3":
                    deposit();
                    break;

                case "4":
                    transfer();
                    break;

                case "5":

                    atm.logout();

                    System.out.println();
                    System.out.println("You have been logged out.");
                    System.out.println(
                            "Thank you for using ATM Interface."
                    );

                    break;

                default:

                    System.out.println();
                    System.out.println(
                            "Invalid option. Please select 1-5."
                    );
            }
        }
    }

    private static void showTransactionHistory() {

        ArrayList<Transaction> transactions =
                atm.getTransactions();

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║             TRANSACTION HISTORY              ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        if (transactions.isEmpty()) {

            System.out.println(
                    "No transactions in this session."
            );

            return;
        }

        for (int i = 0; i < transactions.size(); i++) {

            Transaction transaction =
                    transactions.get(i);

            System.out.println();
            System.out.println(
                    (i + 1) + ". " + transaction
            );
        }
    }

    private static void withdraw() {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║                  WITHDRAW                   ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        double amount =
                readAmount("Enter withdrawal amount: ");

        if (amount > atm.getCurrentAccount().getBalance()) {

            System.out.println();
            System.out.println("Insufficient Funds.");
            return;
        }

        if (atm.withdraw(amount)) {

            System.out.println();
            System.out.println("Withdrawal successful.");

            displayCurrentBalance();
        }
    }

    private static void deposit() {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║                   DEPOSIT                   ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        double amount =
                readAmount("Enter deposit amount: ");

        if (atm.deposit(amount)) {

            System.out.println();
            System.out.println("Deposit successful.");

            displayCurrentBalance();
        }
    }

    private static void transfer() {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║                  TRANSFER                   ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        System.out.println();
        System.out.println("Available Recipient Accounts:");

        ArrayList<Account> accounts =
                atm.getAccounts();

        int number = 1;

        for (Account account : accounts) {

            if (!account.equals(atm.getCurrentAccount())) {

                System.out.println(
                        number + ". " +
                                account.getAccountId() +
                                " - " +
                                account.getFullName()
                );

                number++;
            }
        }

        System.out.println();

        System.out.print("Enter recipient Account ID: ");
        String recipientId =
                scanner.nextLine().trim();

        double amount =
                readAmount("Enter transfer amount: ");

        if (amount > atm.getCurrentAccount().getBalance()) {

            System.out.println();
            System.out.println("Insufficient Funds.");
            return;
        }

        if (atm.transfer(recipientId, amount)) {

            System.out.println();
            System.out.println("Transfer successful.");

            System.out.println(
                    "Amount transferred: PKR " +
                            String.format("%,.2f", amount)
            );

            displayCurrentBalance();

        } else {

            System.out.println();
            System.out.println(
                    "Transfer failed. Please check the recipient Account ID."
            );
        }
    }

    private static double readAmount(String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                double amount =
                        Double.parseDouble(input);

                if (amount <= 0) {

                    System.out.println(
                            "Amount must be greater than 0."
                    );

                    continue;
                }

                return amount;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid amount."
                );
            }
        }
    }

    private static void displayCurrentBalance() {

        String balanceText =
                String.format(
                        "PKR %,.2f",
                        atm.getCurrentAccount().getBalance()
                );

        System.out.println(
                "Current Balance: " + balanceText
        );
    }
}
