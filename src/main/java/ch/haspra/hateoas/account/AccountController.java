package ch.haspra.hateoas.account;

import ch.haspra.hateoas.user.CustomerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class AccountController {
    private AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping(path = "/accounts", produces = MediaTypes.HAL_JSON_VALUE)
    public Resources<Account> accounts() {

        List<Account> accounts = accountRepository.getAll();
        for(Account account : accounts){
            account.add(linkTo(methodOn(AccountController.class).accounts(account.getAccountNumber())).withSelfRel());
            account.add(linkTo(methodOn(CustomerController.class).customers(account.getOwner())).withRel("owner"));
        }

        return new Resources<>(accounts, linkTo(methodOn(AccountController.class).accounts()).withSelfRel());
    }

    @RequestMapping(path = "/accounts/{accountNumber}", produces = MediaTypes.HAL_JSON_VALUE)
    public Resource<Account> accounts(@PathVariable("accountNumber") long accountNumber) {

        Account account = accountRepository.get(accountNumber);
        account.add(linkTo(methodOn(AccountController.class).accounts(account.getAccountNumber())).withSelfRel());
        account.add(linkTo(methodOn(CustomerController.class).customers(account.getOwner())).withRel("owner"));

        if(account.getBalance() > 0){
            account.add(linkTo(methodOn(AccountController.class).withdraw(account.getAccountNumber())).withRel("withdraw"));
        }

        return new Resource<>(account);
    }

    @RequestMapping(path = "/accounts/{accountNumber}/withdraw", produces = MediaTypes.HAL_JSON_VALUE)
    public Resource<Account> withdraw(@PathVariable("accountNumber") long accountNumber) {
        Account account = accountRepository.get(accountNumber);
        account.add(linkTo(methodOn(AccountController.class).accounts(account.getAccountNumber())).withSelfRel());

        return new Resource<>(account);
    }

}
