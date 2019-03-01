package fr.dawan.mvc1.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ryft_temoignages")
public class Testemony implements Serializable {

	@Id
	@Column(name = "idtemoignage", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTemoignage;

	@Column
	private String title;

	@Lob
	@Column(nullable = false)
	private String content;

	@Column
	private String pseudo;

	@Temporal(TemporalType.TIMESTAMP)
	Date dateWrite;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "idProblemType")
	private ProblemType problemTypes;

	@Enumerated(EnumType.STRING)
	private TestemonysStatus status;

	public enum TestemonysStatus {
		CREATED, PUBLISHED, OFFLINE, BLOCKED
	}

	public Long getIdTemoignage() {
		return idTemoignage;
	}

	public void setIdTemoignage(Long idTemoignage) {
		this.idTemoignage = idTemoignage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateWrite() {
		return dateWrite;
	}

	public void setDateWrite(Date dateWrite) {
		this.dateWrite = dateWrite;
	}

	public ProblemType getProblemTypes() {
		return problemTypes;
	}

	public void setProblemTypes(ProblemType problemTypes) {
		this.problemTypes = problemTypes;
	}

	public TestemonysStatus getStatus() {
		return status;
	}

	public void setStatus(TestemonysStatus status) {
		this.status = status;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Testemony(Long idTemoignage, String title, String content, String pseudo, Date dateWrite,
			ProblemType problemTypes, TestemonysStatus status) {
		super();
		this.idTemoignage = idTemoignage;
		this.title = title;
		this.content = content;
		this.pseudo = pseudo;
		this.dateWrite = dateWrite;
		this.problemTypes = problemTypes;
		this.status = status;
	}

	public Testemony() {
		super();
	}
}
