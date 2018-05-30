package com.nazdaq.ejms.beans;

public class ExtensionLetterBean {
private String candName;
private String presentAddress;
private String refNo;
private String candProfileApproveDate;
private String desName;
private String depName;
private String oldDateOfJoin;
private String dateOfJoin;
private String alIssuedBy;
private String alIssuedByDesignation;
public ExtensionLetterBean() {
	// TODO Auto-generated constructor stub
}

public ExtensionLetterBean(String candName, String presentAddress, String refNo, String candProfileApproveDate,
		String desName, String depName, String oldDateOfJoin, String dateOfJoin, String alIssuedBy,
		String alIssuedByDesignation) {
	
	this.candName = candName;
	this.presentAddress = presentAddress;
	this.refNo = refNo;
	this.candProfileApproveDate = candProfileApproveDate;
	this.desName = desName;
	this.depName = depName;
	this.oldDateOfJoin = oldDateOfJoin;
	this.dateOfJoin = dateOfJoin;
	this.alIssuedBy = alIssuedBy;
	this.alIssuedByDesignation = alIssuedByDesignation;
}

public String getCandName() {
	return candName;
}
public void setCandName(String candName) {
	this.candName = candName;
}
public String getPresentAddress() {
	return presentAddress;
}
public void setPresentAddress(String presentAddress) {
	this.presentAddress = presentAddress;
}
public String getRefNo() {
	return refNo;
}
public void setRefNo(String refNo) {
	this.refNo = refNo;
}
public String getCandProfileApproveDate() {
	return candProfileApproveDate;
}
public void setCandProfileApproveDate(String candProfileApproveDate) {
	this.candProfileApproveDate = candProfileApproveDate;
}
public String getDesName() {
	return desName;
}
public void setDesName(String desName) {
	this.desName = desName;
}
public String getDepName() {
	return depName;
}
public void setDepName(String depName) {
	this.depName = depName;
}
public String getOldDateOfJoin() {
	return oldDateOfJoin;
}
public void setOldDateOfJoin(String oldDateOfJoin) {
	this.oldDateOfJoin = oldDateOfJoin;
}
public String getDateOfJoin() {
	return dateOfJoin;
}
public void setDateOfJoin(String dateOfJoin) {
	this.dateOfJoin = dateOfJoin;
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
