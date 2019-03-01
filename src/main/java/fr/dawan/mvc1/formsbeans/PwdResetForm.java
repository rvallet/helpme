package fr.dawan.mvc1.formsbeans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PwdResetForm {

	@NotEmpty
	@Size(min = 6, message = "(minimum 6 caractères)")
	private String password;

	public PwdResetForm() {
		super();
	}

	public PwdResetForm(@NotEmpty @Size(min = 6) String password) {
		super();
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
