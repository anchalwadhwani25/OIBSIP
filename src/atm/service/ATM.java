package atm.service;

import atm.model.Account;
import atm.model.Bank;
import atm.model.Transaction;

import java.util.ArrayList;

public class ATM {
    private Bank bank;
    private Account currentAccount;
    private ArrayList<Transaction> transactions;

    public ATM() {

        bank = new Bank();
        transactions = new ArrayList<>();
    }

    public boolean login(String userId, String pin) {

        Account account = bank.findAccount(userId);

        if (account != null &&
                account.getPin().equals(pin)) {

            currentAccount = account;
            transactions.clear();

            return true;
        }

        return false;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public boolean userIdExists(String userId) {
        return bank.userIdExists(userId);
    }

    public Account createAccount(
            String fullName,
            String userId,
            String pin,
            double initialBalance
    ) {

        return bank.createAccount(
                fullName,
                userId,
                pin,
                initialBalance
        );
    }

    public ArrayList<Account> getAccounts() {
        return bank.getAccounts();
    }

    public boolean withdraw(double amount) {

        if (currentAccount == null || amount <= 0) {
            return false;
        }

        if (amount > currentAccount.getBalance()) {
            return false;
        }

        currentAccount.setBalance(
                currentAccount.getBalance() - amount
        );

        transactions.add(
                new Transaction(
                        "WITHDRAW",
                        amount,
                        "Cash withdrawn successfully"
                )
        );

        return true;
    }

    public boolean deposit(double amount) {

        if (currentAccount == null || amount <= 0) {
            return false;
        }

        currentAccount.setBalance(
                currentAccount.getBalance() + amount
        );

        transactions.add(
                new Transaction(
                        "DEPOSIT",
                        amount,
                        "Cash deposited successfully"
                )
        );

        return true;
    }

    public boolean transfer(
            String recipientAccountId,
            double amount
    ) {

        if (currentAccount == null || amount <= 0) {
            return false;
        }

        if (amount > currentAccount.getBalance()) {
            return false;
        }

        Account recipient =
                bank.findAccountById(recipientAccountId);

        if (recipient == null ||
                recipient == currentAccount) {
            return false;
        }

        currentAccount.setBalance(
                currentAccount.getBalance() - amount
        );

        recipient.setBalance(
                recipient.getBalance() + amount
        );

        transactions.add(
                new Transaction(
                        "TRANSFER",
                        amount,
                        "Transferred to " + recipientAccountId
                )
        );

        return true;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void logout() {
        currentAccount = null;
        transactions.clear();
    }

}
