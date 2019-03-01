package fr.dawan.mvc1.formsbeans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ContactForm {

	@NotEmpty
	@Size(min = 1, max = 40, message = "Ce champ doit être renseigné")
	private String name;

	@NotEmpty
	@Pattern(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message = "format (identifiant@fournisseur.dom)")
	private String email;

	private TypesDemandes type;

	@NotEmpty
	@Size(max = 500, message = "Longueur du message (500 charactères max.")
	private String message;

	public enum TypesDemandes {
		QUESTION("Posez une question"), RECLAMATION("Réclamation"), AUTRE("Autre demande");
		private String name;

		TypesDemandes(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public ContactForm() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TypesDemandes getType() {
		return type;
	}

	public void setType(TypesDemandes type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
