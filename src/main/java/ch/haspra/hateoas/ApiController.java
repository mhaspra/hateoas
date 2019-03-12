package ch.haspra.hateoas;

import ch.haspra.hateoas.account.AccountController;
import ch.haspra.hateoas.user.CustomerController;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ApiController {
    @RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public Resources<Void> api() {
        return new Resources<>(Collections.emptySet(),
                Arrays.asList(
                        linkTo(methodOn(AccountController.class).accounts()).withRel("accounts"),
                        linkTo(methodOn(CustomerController.class).customers()).withRel("customers")));
    }
}
