package com.nazdaq.ejms.beans;

public class CandidateBeanReportThree {
	private String candName;
	private String recuriteEmpName;
	private String recuriteDeptNameName;
	private String alIssuedBy;
	private String alIssuedByDesignation;
	public CandidateBeanReportThree() {
		// TODO Auto-generated constructor stub
	}
	
	public CandidateBeanReportThree(String candName, String recuriteEmpName, String alIssuedBy,
			String alIssuedByDesignation) {
		super();
		this.candName = candName;
		this.recuriteEmpName = recuriteEmpName;
		this.alIssuedBy = alIssuedBy;
		this.alIssuedByDesignation = alIssuedByDesignation;
	}

	public CandidateBeanReportThree(String candName, String recuriteEmpName) {
		super();
		this.candName = candName;
		this.recuriteEmpName = recuriteEmpName;
	}
	
	

	public CandidateBeanReportThree(String candName, String recuriteEmpName, String recuriteDeptNameName) {
		super();
		this.candName = candName;
		this.recuriteEmpName = recuriteEmpName;
		this.recuriteDeptNameName = recuriteDeptNameName;
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
	
}
