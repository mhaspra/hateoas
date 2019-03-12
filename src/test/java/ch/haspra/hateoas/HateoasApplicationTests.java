package ch.haspra.hateoas;

import ch.haspra.hateoas.account.Account;
import ch.haspra.hateoas.user.Customer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.mvc.TypeReferences;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HateoasApplicationTests {
	private Traverson client;

	@Before
	public void before() throws URISyntaxException {
		client = new Traverson(new URI("http://localhost:8080"), MediaTypes.HAL_JSON);
	}

	@Test
	public void getAllAccounts() {
		Resources<Resource<Account>> accounts = client
				.follow("accounts")
				.toObject(new TypeReferences.ResourcesType<Resource<Account>>(){});

		assertEquals(2, accounts.getContent().size());
	}

    @Test
    public void getAccount2() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("account", 2);

        Resource<Account> account = client
                .follow("accounts")
                .withTemplateParameters(parameters)
                .toObject(new TypeReferences.ResourceType<Account>(){});

        assertEquals(0, account.getContent().getBalance());
    }

    @Test
    public void getFirstAccount() {
        Resource<Account> account = client
                .follow("accounts")
                .follow("$._embedded.accountList[0]._links.self.href")
                .toObject(new TypeReferences.ResourceType<Account>(){});

        assertEquals(100, account.getContent().getBalance());
    }
}

