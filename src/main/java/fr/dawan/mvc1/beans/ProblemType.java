package fr.dawan.mvc1.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "ryft_problemtypes")
public class ProblemType implements Serializable {

	@Id
	@Column(name = "idproblemtypes", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProblemTypes;

	@Column
	private String title;

	@Override
	public String toString() {
		return title;
	}

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "liProblemType")
	private Set<Advice> advices = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "problemTypes", fetch = FetchType.EAGER)
	private Set<Testemony> liTestemony = new HashSet<>();

	@Enumerated(EnumType.STRING)
	private ProblemCategory pbCategory;

	public enum ProblemCategory {
		ADDICTION, FULFILLEMENT
	}

	@Version
	private int version;

	public Long getIdProblemTypes() {
		return idProblemTypes;
	}

	public void setIdProblemTypes(Long idProblemTypes) {
		this.idProblemTypes = idProblemTypes;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Advice> getAdvices() {
		return advices;
	}

	public void setAdvices(Set<Advice> advices) {
		this.advices = advices;
	}

	public Set<Testemony> getLiTestemony() {
		return liTestemony;
	}

	public void setLiTestemony(Set<Testemony> liTestemony) {
		this.liTestemony = liTestemony;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public ProblemCategory getPbCategory() {
		return pbCategory;
	}

	public void setPbCategory(ProblemCategory pbCategory) {
		this.pbCategory = pbCategory;
	}

	public ProblemType(Long idProblemTypes, String title, Set<Advice> advices, Set<Testemony> liTestemony,
			int version) {
		super();
		this.idProblemTypes = idProblemTypes;
		this.title = title;
		this.advices = advices;
		this.liTestemony = liTestemony;
		this.version = version;
	}

	public ProblemType() {
		super();
	}

}