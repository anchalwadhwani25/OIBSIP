package atm.model;

public class Account {
    private String accountId;
    private String userId;
    private String fullName;
    private String pin;
    private double balance;

    public Account(
            String accountId,
            String userId,
            String fullName,
            String pin,
            double balance
    ) {
        this.accountId = accountId;
        this.userId = userId;
        this.fullName = fullName;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
