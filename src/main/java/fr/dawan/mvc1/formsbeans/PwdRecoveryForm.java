package fr.dawan.mvc1.formsbeans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class PwdRecoveryForm {

	@NotEmpty
	@Pattern(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message = "format (identifiant@fournisseur.dom)")
	private String email;

	public PwdRecoveryForm() {
		super();
	}

	public PwdRecoveryForm(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
