package fr.dawan.mvc1.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "ryft_advices")
public class Advice implements Serializable {

	@Id
	@Column(name = "idadvice", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAdvice;

	@Column
	@NotEmpty(message = "You have to write somehting in")
	private String title;

	@Lob
	@NotEmpty(message = "You have to write somehting in")
	@Column(nullable = false)
	private String content;

	@Temporal(TemporalType.DATE)
	private Date useDate;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ProblemType> liProblemType;

	@Version
	private int version;

	public Long getIdAdvice() {
		return idAdvice;
	}

	public void setIdAdvice(Long idAdvice) {
		this.idAdvice = idAdvice;
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

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public List<ProblemType> getLiProblemType() {
		return liProblemType;
	}

	public void setLiProblemType(List<ProblemType> liProblemType) {
		this.liProblemType = liProblemType;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Advice() {
		super();
	}

	public Advice(Long idAdvice, String title, String content, List<ProblemType> liProblemType) {
		super();
		this.idAdvice = idAdvice;
		this.title = title;
		this.content = content;
		this.liProblemType = liProblemType;
	}

	@Override
	public String toString() {
		return "Advice [idAdvice=" + idAdvice + ", title=" + title + ", content=" + content + ", useDate=" + useDate
				+ "]";
	}
}
