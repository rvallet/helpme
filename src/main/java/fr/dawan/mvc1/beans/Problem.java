package fr.dawan.mvc1.beans;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@Table(name = "ryft_problems")
public class Problem implements Serializable {

	@Id
	@Column(name = "idproblem", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProblem;

	@Column
	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Version
	private int version;

	@ManyToOne(cascade = CascadeType.ALL)
	private ProblemType problemType;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_problems", joinColumns = @JoinColumn(name = "idproblem"), inverseJoinColumns = @JoinColumn(name = "iduser"))
	private Set<User> liUser = new HashSet<>();

	@Enumerated(EnumType.STRING)
	private ProblemObjective objective;

	public enum ProblemObjective {
		TO_DECREASE("Diminution"), TO_STOP("Arrêt");

		private String name;

		ProblemObjective(String name) {
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

	@Column
	@NotEmpty(message = "Tu as bien une raison qui accompagne ta démarche ?")
	private String motivation;

	@Column
	private double unitCost;

	@Column
	private double startQuantity;

	@Column
	private double PackPrice;

	@Column
	private int PackQuantity;

	@Enumerated(EnumType.STRING)
	private ProblemFrequence problemFrequence;

	public enum ProblemFrequence {
		DAILY("Quotidien"), WEEKLY("Hebdomadaire"), MONTHLY("Mensuel");
		private String name;

		ProblemFrequence(String name) {
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

	@OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Objective> liObjectives = new HashSet<>();

	@Column
	private boolean active;

	public Problem(Long idProblem, String title, Date creationDate, int version, ProblemType problemType,
			Set<User> liUser, ProblemObjective objective, double unitCost, double startQuantity,
			ProblemFrequence problemFrequence, Set<Objective> liObjectives) {
		super();
		this.idProblem = idProblem;
		this.title = title;
		this.creationDate = creationDate;
		this.version = version;
		this.problemType = problemType;
		this.liUser = liUser;
		this.objective = objective;
		this.unitCost = unitCost;
		this.startQuantity = startQuantity;
		this.problemFrequence = problemFrequence;
		this.liObjectives = liObjectives;
	}

	public Problem() {
		super();
		creationDate = new Date();
		problemType = new ProblemType();
	}

	public Long getIdProblem() {
		return idProblem;
	}

	public void setIdProblem(Long idProblem) {
		this.idProblem = idProblem;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public ProblemType getProblemType() {
		return problemType;
	}

	public void setProblemType(ProblemType problemType) {
		this.problemType = problemType;
	}

	public Set<User> getLiUser() {
		return liUser;
	}

	public void setLiUser(Set<User> liUser) {
		this.liUser = liUser;
	}

	public ProblemObjective getObjective() {
		return objective;
	}

	public void setObjective(ProblemObjective objective) {
		this.objective = objective;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public double getStartQuantity() {
		return startQuantity;
	}

	public void setStartQuantity(double startQuantity) {
		this.startQuantity = startQuantity;
	}

	public ProblemFrequence getProblemFrequence() {
		return problemFrequence;
	}

	public void setProblemFrequence(ProblemFrequence problemFrequence) {
		this.problemFrequence = problemFrequence;
	}

	public Set<Objective> getLiObjectives() {
		return liObjectives;
	}

	public void setLiObjectives(Set<Objective> liObjectives) {
		this.liObjectives = liObjectives;
	}

	public double getPackPrice() {
		return PackPrice;
	}

	public void setPackPrice(double packPrice) {
		PackPrice = packPrice;
	}

	public int getPackQuantity() {
		return PackQuantity;
	}

	public void setPackQuantity(int packQuantity) {
		PackQuantity = packQuantity;
	}

	public String getMotivation() {
		return motivation;
	}

	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Problem [idProblem=" + idProblem + ", title=" + title + ", creationDate=" + creationDate + ", version="
				+ version + ", problemType=" + problemType + ", liUser=" + liUser + ", objective=" + objective
				+ ", unitCost=" + unitCost + ", startQuantity=" + startQuantity + ", problemFrequence="
				+ problemFrequence + ", liObjectives=" + liObjectives + "]";
	}
}