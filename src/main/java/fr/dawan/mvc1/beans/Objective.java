package fr.dawan.mvc1.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "ryft_objectives")

public class Objective implements Serializable {

	@Id
	@Column(name = "idobjective", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idObjective;

	@Column
	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Version
	private int version;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "idproblem", nullable = false)
	private Problem problem;

	@Column
	private Integer goalQuantity;

	@Column
	private double finalQuantity;

	@Column
	private double quantityNotSmoke;

	@Column
	private double moneySave;

	@Enumerated(EnumType.STRING)
	private ObjectiveStatus status;

	public enum ObjectiveStatus {
		IN_PROGRESS, COMPLETED, FAILED, CANCELED;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@JoinColumn(name = "idobjective")
	public Map<Integer, Integer> realConso;

	public Long getIdObjective() {
		return idObjective;
	}

	public void setIdObjective(Long idObjective) {
		this.idObjective = idObjective;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public double getFinalQuantity() {
		return finalQuantity;
	}

	public void setFinalQuantity(double finalQuantity) {
		this.finalQuantity = finalQuantity;
	}

	public ObjectiveStatus getStatus() {
		return status;
	}

	public void setStatus(ObjectiveStatus status) {
		this.status = status;
	}

	public Map<Integer, Integer> getRealConso() {
		return realConso;
	}

	public void setRealConso(Map<Integer, Integer> realConso) {
		this.realConso = realConso;
	}

	public double getQuantityNotSmoke() {
		return quantityNotSmoke;
	}

	public void setQuantityNotSmoke(double quantityNotSmoke) {
		this.quantityNotSmoke = quantityNotSmoke;
	}

	public double getMoneySave() {
		return moneySave;
	}

	public void setMoneySave(double moneySave) {
		this.moneySave = moneySave;
	}

	public Integer getGoalQuantity() {
		return goalQuantity;
	}

	public void setGoalQuantity(Integer goalQuantity) {
		this.goalQuantity = goalQuantity;
	}

	public Objective(Long idObjective, String title, Date startDate, Date endDate, int version, Problem problem,
			int goalQuantity, double finalQuantity, ObjectiveStatus status) {
		super();
		this.idObjective = idObjective;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.version = version;
		this.problem = problem;
		this.goalQuantity = goalQuantity;
		this.finalQuantity = finalQuantity;
		this.status = status;
	}

	public Objective() {
		super();
	}

}
