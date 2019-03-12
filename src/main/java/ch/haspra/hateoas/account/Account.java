package ch.haspra.hateoas.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class Account extends ResourceSupport {
    private final long accountNumber;
    private long balance;
    private final long owner = 2;

    @JsonCreator
    public Account(@JsonProperty("accountNumber") long accountNumber, @JsonProperty("balance") long balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Account(Account account) {
        accountNumber = account.getAccountNumber();
        balance = account.getBalance();
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public long getBalance() {
        return balance;
    }

    public long getOwner() {
        return owner;
    }
}
