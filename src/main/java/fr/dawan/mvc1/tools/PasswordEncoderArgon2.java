package fr.dawan.mvc1.tools;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * Key derivation function for the encryption algorithm
 * 
 * @param encode  (CharSequence rawPassword)
 * @param matches (CharSequence rawPassword, String password)
 * 
 **/
public class PasswordEncoderArgon2 {
	private static final Argon2 ARGON2 = Argon2Factory.create();

	private static final int ITERATIONS = 2;
	/*
	 * Affect the time cost. Increase the value to a comfortable response time for
	 * the user.
	 */

	private static final int MEMORY = 65536;
	/*
	 * Affect the memory cost (totalMemoryAvailable/maxSimultaneousLoginExpected).
	 */

	private static final int PARALLELISM = 1;
	/*
	 * Affect the degree of parallelism. Sum of threads your hardware can support
	 * (set the number of your available CPU core).
	 */

	/**
	 * Password hash function
	 * 
	 * @param A ChaSequence of the password to encode
	 * @return A String of the hashed password.
	 **/
	public String encode(CharSequence rawPassword) {
		final String hash = ARGON2.hash(ITERATIONS, MEMORY, PARALLELISM, rawPassword.toString());
		return hash;
	}

	/**
	 * Password verification function entered with the password encoded.
	 * 
	 * @param CharSequence A CharSequence of the password
	 * @param String       A String of the hashed password
	 * @return A Boolean : 'true' if passwords matches.
	 **/
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return ARGON2.verify(encodedPassword, rawPassword.toString());
	}
}