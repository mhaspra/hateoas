package ch.haspra.hateoas.account;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AccountRepository {

    private final HashMap<Long, Account> accounts;

    public AccountRepository() {
        accounts = new HashMap<>();

        Account positiveAccount = new Account(1, 100);
        Account emptyAccount = new Account(2, 0);

        accounts.put(positiveAccount.getAccountNumber(), positiveAccount);
        accounts.put(emptyAccount.getAccountNumber(), emptyAccount);
    }

    public List<Account> getAll(){
        ArrayList<Account> accounts = new ArrayList<>();
        this.accounts.values().forEach(account -> accounts.add(new Account(account)));
        return accounts;
    }

    public Account get(long accountNumber){
        return new Account(accounts.get(accountNumber));
    }
}
