package com.nazdaq.ejms.beans;

public class CandidateBeanReportOne {
private String candName;
private String candMobileNo;
private String depName;
private String desiName;
private String grade;
private String supervisorName;
private String presentAddress;
private String officeAddress;
private String dateOfJoin;
private String provisionPeriod;
private String yearlyBonus;
private String name;
public CandidateBeanReportOne() {
	// TODO Auto-generated constructor stub
}

public CandidateBeanReportOne(String candName, String candMobileNo, String depName, String desiName, String grade,
		String supervisorName, String presentAddress, String officeAddress, String dateOfJoin, String provisionPeriod,
		String yearlyBonus,String name) {
	super();
	this.candName = candName;
	this.candMobileNo = candMobileNo;
	this.depName = depName;
	this.desiName = desiName;
	this.grade = grade;
	this.supervisorName = supervisorName;
	this.presentAddress = presentAddress;
	this.officeAddress = officeAddress;
	this.dateOfJoin = dateOfJoin;
	this.provisionPeriod = provisionPeriod;
	this.yearlyBonus = yearlyBonus;
	this.name=name;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getCandName() {
	return candName;
}
public void setCandName(String candName) {
	this.candName = candName;
}
public String getCandMobileNo() {
	return candMobileNo;
}
public void setCandMobileNo(String candMobileNo) {
	this.candMobileNo = candMobileNo;
}
public String getDepName() {
	return depName;
}
public void setDepName(String depName) {
	this.depName = depName;
}
public String getDesiName() {
	return desiName;
}
public void setDesiName(String desiName) {
	this.desiName = desiName;
}
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}
public String getSupervisorName() {
	return supervisorName;
}
public void setSupervisorName(String supervisorName) {
	this.supervisorName = supervisorName;
}
public String getPresentAddress() {
	return presentAddress;
}
public void setPresentAddress(String presentAddress) {
	this.presentAddress = presentAddress;
}
public String getOfficeAddress() {
	return officeAddress;
}
public void setOfficeAddress(String officeAddress) {
	this.officeAddress = officeAddress;
}
public String getDateOfJoin() {
	return dateOfJoin;
}
public void setDateOfJoin(String dateOfJoin) {
	this.dateOfJoin = dateOfJoin;
}
public String getProvisionPeriod() {
	return provisionPeriod;
}
public void setProvisionPeriod(String provisionPeriod) {
	this.provisionPeriod = provisionPeriod;
}
public String getYearlyBonus() {
	return yearlyBonus;
}
public void setYearlyBonus(String yearlyBonus) {
	this.yearlyBonus = yearlyBonus;
}


}
