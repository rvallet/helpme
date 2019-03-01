package fr.dawan.mvc1.formsbeans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginForm {
	@NotEmpty
	@Pattern(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message = "format (identifiant@fournisseur.dom)")
	private String email;

	@NotEmpty
	@Size(min = 6, message = "(minimum 6 caractères)")
	private String password;

	public LoginForm() {
		super();
	}

	public LoginForm(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
