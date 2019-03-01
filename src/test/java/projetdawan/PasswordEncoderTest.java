package projetdawan;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import fr.dawan.mvc1.tools.PasswordEncoderArgon2;

public class PasswordEncoderTest {

	@Test
	public void passwordMatch() throws Exception {
		assertTrue(new PasswordEncoderArgon2().matches("password", new PasswordEncoderArgon2().encode("password")));
	}

	@Test
	public void passwordNotMatch() throws Exception {
		assertFalse(new PasswordEncoderArgon2().matches("not_the_password",
				new PasswordEncoderArgon2().encode("password")));
	}

}
