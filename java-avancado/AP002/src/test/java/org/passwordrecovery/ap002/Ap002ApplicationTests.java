package org.passwordrecovery.ap002;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.core.token.Token;

import java.util.Base64;
import java.util.Date;

@SpringBootTest
class Ap002ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void createToken() throws Exception{
		KeyBasedPersistenceTokenService service = new KeyBasedPersistenceTokenService();
		service.setServerSecret("SECRET123");
		service.setServerInteger(16);
		service.setSecureRandom(new SecureRandomFactoryBean().getObject());

		Token token = service.allocateToken("maria@email.com");

		System.out.println(token.getExtendedInformation());
		System.out.println(new Date(token.getKeyCreationTime()));
		System.out.println(token.getKey());

		//Mon May 06 00:36:14 BRT 2024

		//MTcxNDk2NjU3NDI4Njo0MDU5MzQ0YjNhNDVhNmJkYWQ1Mjg3NmIxMjFkMzY4OGE1YWRjNWEyOWRjNmNmYjA4MjY5MjhkMmM3YWIyZWQwOm1hcmlhQGVtYWlsLmNvbTphYzFkMWY3NjQ1MTUzOTlmOTAxZTgzODE0YjY3ZTc5NjA2MjJjNWZiNDFhZjlhMGVmZTJkNjQ4ZTY4NGRmNjZlNGYxMTUwNDNkOWQwMjRjMWIyOTQxZDA2M2EzZmUxYmUxODUxZWYxN2E3ZGI2ZGZmZDA5MWEwZDhjMWYzM2MxZQ==
	}

	@Test
	public void readToken() throws Exception{
		KeyBasedPersistenceTokenService service = new KeyBasedPersistenceTokenService();
		service.setServerSecret("SECRET123");
		service.setServerInteger(16);
		service.setSecureRandom(new SecureRandomFactoryBean().getObject());

		String rawToken = "MTcxNDk2NjU3NDI4Njo0MDU5MzQ0YjNhNDVhNmJkYWQ1Mjg3NmIxMjFkMzY4OGE1YWRjNWEyOWRjNmNmYjA4MjY5MjhkMmM3YWIyZWQwOm1hcmlhQGVtYWlsLmNvbTphYzFkMWY3NjQ1MTUzOTlmOTAxZTgzODE0YjY3ZTc5NjA2MjJjNWZiNDFhZjlhMGVmZTJkNjQ4ZTY4NGRmNjZlNGYxMTUwNDNkOWQwMjRjMWIyOTQxZDA2M2EzZmUxYmUxODUxZWYxN2E3ZGI2ZGZmZDA5MWEwZDhjMWYzM2MxZQ==";

		Token token = service.verifyToken(rawToken);

		System.out.println(token.getExtendedInformation());
		System.out.println(new Date(token.getKeyCreationTime()));
		System.out.println(token.getKey());
	}

	@Test
	public void readPublicTokenInfo() throws Exception{
		String rawToken = "MTcxNDk2NjU3NDI4Njo0MDU5MzQ0YjNhNDVhNmJkYWQ1Mjg3NmIxMjFkMzY4OGE1YWRjNWEyOWRjNmNmYjA4MjY5MjhkMmM3YWIyZWQwOm1hcmlhQGVtYWlsLmNvbTphYzFkMWY3NjQ1MTUzOTlmOTAxZTgzODE0YjY3ZTc5NjA2MjJjNWZiNDFhZjlhMGVmZTJkNjQ4ZTY4NGRmNjZlNGYxMTUwNDNkOWQwMjRjMWIyOTQxZDA2M2EzZmUxYmUxODUxZWYxN2E3ZGI2ZGZmZDA5MWEwZDhjMWYzM2MxZQ==";

		byte[] bytes = Base64.getDecoder().decode(rawToken);
		String rawTokenDecoded = new String(bytes);

		System.out.println(rawTokenDecoded);

		String[] tokenParts = rawTokenDecoded.split(":");

		Long timestamp = Long.parseLong(tokenParts[0]);
		System.out.println(new Date(timestamp));

		System.out.println(tokenParts[2]);
	}

}
