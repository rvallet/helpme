package fr.dawan.mvc1.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "ryft_users")
public class User implements Serializable {

	@Id
	@Column(name = "idUser", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;

	@Column(nullable = false)
	@NotEmpty(message = "Ce champ doit être renseigné")
	private String pseudo;

	@Column(nullable = false)
	@NotEmpty(message = "Ce champ doit être selectionné")
	private String sexe;

	@Column(nullable = false)
	@NotEmpty(message = "Ce champ doit être renseigné")
	@Pattern(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message = "format (identifiant@fournisseur.dom)")
	private String email;

	@Column(nullable = false)
	@NotEmpty(message = "Ce champ doit être renseigné")
	@Size(min = 6, message = "(minimum 6 caractères)")
	private String password;

	@Column
	private String resetToken;

	public enum StatusUser {
		VISITOR, USER, ADMIN
	}

	@Enumerated(EnumType.STRING)
	private StatusUser statut;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Version
	private int version;

	@ManyToMany(mappedBy = "liUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Problem> liProblem;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
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

	public StatusUser getStatut() {
		return statut;
	}

	public void setStatut(StatusUser statut) {
		this.statut = statut;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<Problem> getLiProblem() {

		return liProblem;
	}

	public void setLiProblem(List<Problem> liProblem) {
		this.liProblem = liProblem;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public User(Long idUser, String pseudo, String sexe, String email, String password, StatusUser statut,
			Date creationDate, int version, List<Problem> liProblem) {
		super();
		this.idUser = idUser;
		this.pseudo = pseudo;
		this.sexe = sexe;
		this.email = email;
		this.password = password;
		this.statut = statut;
		this.creationDate = creationDate;
		this.version = version;
		this.liProblem = liProblem;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", pseudo=" + pseudo + ", sexe=" + sexe + ", email=" + email + ", password="
				+ password + ", statut=" + statut + ", creationDate=" + creationDate + ", version=" + version + "]";
	}

	public String getStatusStr() {
		switch (statut) {
		case ADMIN:
			return "ADMIN";
		case USER:
			return "USER";
		case VISITOR:
			return "VISITOR";
		}
		return null;
	}
}