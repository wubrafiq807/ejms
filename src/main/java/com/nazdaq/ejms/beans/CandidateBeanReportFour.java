package com.nazdaq.ejms.beans;

import javax.persistence.Column;

public class CandidateBeanReportFour {
	private String candName;
	private String recuriteEmpName;
	private String desiName;
	private Double grossSalary;
	private String recuriteDeptNameName;
	private String referenceAndCurrentDate;
	private String alIssuedBy;
	private String alIssuedByDesignation;
	private Double basicSalary;
	private Double houseRent;
	private Double medicalIn;
	private Double conveyance;
	private Double salaryOthersAmt;

	public CandidateBeanReportFour() {
		// TODO Auto-generated constructor stub
	}

	public CandidateBeanReportFour(String candName, String recuriteEmpName, String desiName, Double grossSalary,
			String referenceAndCurrentDate, String alIssuedBy, String alIssuedByDesignation) {
		super();
		this.candName = candName;
		this.recuriteEmpName = recuriteEmpName;
		this.desiName = desiName;
		this.grossSalary = grossSalary;
		this.referenceAndCurrentDate = referenceAndCurrentDate;
		this.alIssuedBy = alIssuedBy;
		this.alIssuedByDesignation = alIssuedByDesignation;
	}

	public CandidateBeanReportFour(String candName, String recuriteEmpName, String desiName,
			String referenceAndCurrentDate, Double grossSalary) {
		super();
		this.candName = candName;
		this.recuriteEmpName = recuriteEmpName;
		this.desiName = desiName;
		this.grossSalary = grossSalary;
		this.referenceAndCurrentDate = referenceAndCurrentDate;
	}

	public CandidateBeanReportFour(String candName, String recuriteEmpName, String desiName, Double grossSalary,
			String recuriteDeptNameName) {
		super();
		this.candName = candName;
		this.recuriteEmpName = recuriteEmpName;
		this.desiName = desiName;
		this.grossSalary = grossSalary;
		this.recuriteDeptNameName = recuriteDeptNameName;
	}

	public Double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Double getHouseRent() {
		return houseRent;
	}

	public void setHouseRent(Double houseRent) {
		this.houseRent = houseRent;
	}

	public Double getMedicalIn() {
		return medicalIn;
	}

	public void setMedicalIn(Double medicalIn) {
		this.medicalIn = medicalIn;
	}

	public Double getConveyance() {
		return conveyance;
	}

	public void setConveyance(Double conveyance) {
		this.conveyance = conveyance;
	}

	public Double getSalaryOthersAmt() {
		return salaryOthersAmt;
	}

	public void setSalaryOthersAmt(Double salaryOthersAmt) {
		this.salaryOthersAmt = salaryOthersAmt;
	}

	public String getAlIssuedBy() {
		return alIssuedBy;
	}

	public void setAlIssuedBy(String alIssuedBy) {
		this.alIssuedBy = alIssuedBy;
	}

	public String getAlIssuedByDesignation() {
		return alIssuedByDesignation;
	}

	public void setAlIssuedByDesignation(String alIssuedByDesignation) {
		this.alIssuedByDesignation = alIssuedByDesignation;
	}

	public String getRecuriteDeptNameName() {
		return recuriteDeptNameName;
	}

	public void setRecuriteDeptNameName(String recuriteDeptNameName) {
		this.recuriteDeptNameName = recuriteDeptNameName;
	}

	public String getCandName() {
		return candName;
	}

	public void setCandName(String candName) {
		this.candName = candName;
	}

	public String getRecuriteEmpName() {
		return recuriteEmpName;
	}

	public void setRecuriteEmpName(String recuriteEmpName) {
		this.recuriteEmpName = recuriteEmpName;
	}

	public String getDesiName() {
		return desiName;
	}

	public void setDesiName(String desiName) {
		this.desiName = desiName;
	}

	public Double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(Double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public String getReferenceAndCurrentDate() {
		return referenceAndCurrentDate;
	}

	public void setReferenceAndCurrentDate(String referenceAndCurrentDate) {
		this.referenceAndCurrentDate = referenceAndCurrentDate;
	}

}
