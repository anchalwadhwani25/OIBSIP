package atm.model;

import java.util.ArrayList;

public class Bank {

    private ArrayList<Account> accounts;
    private int nextAccountNumber;

    public Bank() {

        accounts = new ArrayList<>();
        nextAccountNumber = 1001;

        accounts.add(
                new Account(
                        "ACC1001",
                        "user1",
                        "Demo User",
                        "1234",
                        50000
                )
        );

        accounts.add(
                new Account(
                        "ACC1002",
                        "user2",
                        "Demo User 2",
                        "5678",
                        30000
                )
        );

        accounts.add(
                new Account(
                        "ACC1003",
                        "user3",
                        "Demo User 3",
                        "1111",
                        20000
                )
        );

        nextAccountNumber = 1004;
    }

    public Account findAccount(String userId) {

        for (Account account : accounts) {

            if (account.getUserId().equalsIgnoreCase(userId)) {
                return account;
            }
        }

        return null;
    }

    public Account findAccountById(String accountId) {

        for (Account account : accounts) {

            if (account.getAccountId().equalsIgnoreCase(accountId)) {
                return account;
            }
        }

        return null;
    }

    public boolean userIdExists(String userId) {

        return findAccount(userId) != null;
    }

    public Account createAccount(
            String fullName,
            String userId,
            String pin,
            double initialBalance
    ) {

        String accountId = "ACC" + nextAccountNumber;
        nextAccountNumber++;

        Account account = new Account(
                accountId,
                userId,
                fullName,
                pin,
                initialBalance
        );

        accounts.add(account);

        return account;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

}
