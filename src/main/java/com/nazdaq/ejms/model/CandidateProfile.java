package com.nazdaq.ejms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "candidate_profile")
public class CandidateProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "candidate_id", nullable = true)
	private Candidate candidate;

	@Column(name = "name")
	private String name;

	@Column(name = "date_of_birth")
	private String dateOfBirth;

	@Column(name = "gender")
	private String gender;

	@Column(name = "religion")
	private String religion;

	@Column(name = "blood_group")
	private String bloodGroup;

	@Column(name = "nid_no")
	private String nidNo;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "father_name")
	private String fatherName;

	@Column(name = "mother_name")
	private String motherName;

	@Column(name = "perma_address")
	private String permanentAddress;

	@Column(name = "present_address")
	private String presentAddress;

	@Column(name = "cand_mobile_no")
	private String candMobileNo;

	@Column(name = "cand_email")
	private String emailAddress;

	@Column(name = "emer_cont_no")
	private String emerContNo;

	@Column(name = "emer_cont_person")
	private String emerContPerson;

	@Column(name = "maritial_status")
	private String maritialStatus;

	@Column(name = "spouse_name")
	private String spouseName;

	@Column(name = "spouse_dob")
	private String spouseDob;

	@Column(name = "spouse_cont_no")
	private String spouseContNo;

	@Column(name = "child_1_name")
	private String childOneName;

	@Column(name = "child_1_gender")
	private String childOneGender;

	@Column(name = "child_1_dob")
	private String childOneDob;

	@Column(name = "child_2_name")
	private String childTwoName;

	@Column(name = "child_2_gender")
	private String childTwoGender;

	@Column(name = "child_2_dob")
	private String childTwoDob;

	@Column(name = "prev_org_name_1")
	private String prevOrgName1;

	@Column(name = "prev_org_adrs_1")
	private String prevOrgAdrs1;

	@Column(name = "prev_org_from_1")
	private String prevOrgFrom1;

	@Column(name = "prev_org_to_1")
	private String prevOrgTo1;

	@Column(name = "prev_org_desig_1")
	private String prevOrgDesig1;

	@Column(name = "prev_org_name_2")
	private String prevOrgName2;

	@Column(name = "prev_org_desig_2")
	private String prevOrgDesig2;

	@Column(name = "prev_org_adrs_2")
	private String prevOrgAdrs2;

	@Column(name = "prev_org_from_2")
	private String prevOrgFrom2;

	@Column(name = "prev_org_to_2")
	private String prevOrgTo2;

	@Column(name = "prev_org_name_3")
	private String prevOrgName3;

	@Column(name = "prev_org_desig_3")
	private String prevOrgDesig3;

	@Column(name = "prev_org_adrs_3")
	private String prevOrgAdrs3;

	@Column(name = "prev_org_from_3")
	private String prevOrgFrom3;

	@Column(name = "prev_org_to_3")
	private String prevOrgTo3;

	@Column(name = "cand_dgre_name_1")
	private String candDegreeName1;

	@Column(name = "cand_dgre_major_1")
	private String candDegreeMajor1;

	@Column(name = "cand_dgre_pass_yr_1")
	private String candDegreePasYr1;

	@Column(name = "cand_dgre_inst_1")
	private String candDegreeInst1;

	@Column(name = "cand_dgre_name_2")
	private String candDegreeName2;

	@Column(name = "cand_dgre_major_2")
	private String candDegreeMajor2;

	@Column(name = "cand_dgre_pass_yr_2")
	private String candDegreePasYr2;

	@Column(name = "cand_dgre_inst_2")
	private String candDegreeInst2;

	// office fillup
	@Column(name = "supervisor_name")
	private String supervisorName;

	@Column(name = "supervisor_email")
	private String supervisorEmail;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id", nullable = true)
	private Department department;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "designation_id", nullable = true)
	private Designation designation;

	@Column(name = "grade")
	private String grade;

	@Column(name = "emp_id")
	private String empId;

	@Column(name = "ofs_mobile_no")
	private String officeMobileNo;

	@Column(name = "bank_acc_no")
	private String bankAccNo;

	// 100%
	@Column(name = "gross_salary")
	private Double grossSalary;

	// 60% of gross
	@Column(name = "basic_salary")
	private Double basicSalary;

	// 25% of gross
	@Column(name = "house_rent")
	private Double houseRent;

	// 10% of gross
	@Column(name = "medical_in")
	private Double medicalIn;

	// 5% of gross
	@Column(name = "conveyance")
	private Double conveyance;

	//// 5% of gross
	@Column(name = "provident_fund")
	private Double providentFund;

	@Column(name = "sal_increase_amt")
	private Double salIncreaseAmt;

	// recommend by sandip das start
	@Column(name = "sal_others_amt")
	private Double salaryOthersAmt;

	@Column(name = "al_prepared_by")
	private String alPreparedBy;
	// recommend by sandip das start

	@Column(name = "ofs_location")
	private String officeLocation;

	@ColumnDefault("0")
	@Column(name = "yearly_bonus")
	private String yearlyBonus;

	@ColumnDefault("0")
	@Column(name = "provision_period")
	private String provisionPeriod;

	// common
	@Column(name = "is_active")
	private boolean active;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "al_issued_by")
	private String alIssuedBy;

	@Column(name = "al_issued_by_desig")
	private String alIssuedByDesignation;

	@Transient
	private Integer candidateId;

	@Transient
	private Integer designationId;

	@Transient
	private Integer departmentId;

	@Transient
	private String candId;

	// newly added start
	@Column(name = "cand_training")
	private String candidateTraining;

	@Column(name = "others_skill")
	private String othersSkill;

	@Column(name = "relative_name")
	private String relativeName;

	@Column(name = "relative_desig")
	private String relativeDesig;

	@Column(name = "refer_name1")
	private String referName1;

	@Column(name = "refer_occuption1")
	private String referOccuption1;

	@Column(name = "refer_address1")
	private String referAddress1;

	@Column(name = "refer_contact_no1")
	private String referContactNo1;

	@Column(name = "refer_name2")
	private String referName2;

	@Column(name = "refer_occuption2")
	private String referOccuption2;

	@Column(name = "refer_address2")
	private String referAddress2;

	@Column(name = "refer_contact_no2")
	private String referContactNo2;
	// newly added end

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getNidNo() {
		return nidNo;
	}

	public void setNidNo(String nidNo) {
		this.nidNo = nidNo;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public String getCandMobileNo() {
		return candMobileNo;
	}

	public void setCandMobileNo(String candMobileNo) {
		this.candMobileNo = candMobileNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmerContNo() {
		return emerContNo;
	}

	public void setEmerContNo(String emerContNo) {
		this.emerContNo = emerContNo;
	}

	public String getEmerContPerson() {
		return emerContPerson;
	}

	public void setEmerContPerson(String emerContPerson) {
		this.emerContPerson = emerContPerson;
	}

	public String getMaritialStatus() {
		return maritialStatus;
	}

	public void setMaritialStatus(String maritialStatus) {
		this.maritialStatus = maritialStatus;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getSpouseDob() {
		return spouseDob;
	}

	public void setSpouseDob(String spouseDob) {
		this.spouseDob = spouseDob;
	}

	public String getSpouseContNo() {
		return spouseContNo;
	}

	public void setSpouseContNo(String spouseContNo) {
		this.spouseContNo = spouseContNo;
	}

	public String getChildOneName() {
		return childOneName;
	}

	public void setChildOneName(String childOneName) {
		this.childOneName = childOneName;
	}

	public String getChildOneGender() {
		return childOneGender;
	}

	public void setChildOneGender(String childOneGender) {
		this.childOneGender = childOneGender;
	}

	public String getChildOneDob() {
		return childOneDob;
	}

	public void setChildOneDob(String childOneDob) {
		this.childOneDob = childOneDob;
	}

	public String getChildTwoName() {
		return childTwoName;
	}

	public void setChildTwoName(String childTwoName) {
		this.childTwoName = childTwoName;
	}

	public String getChildTwoGender() {
		return childTwoGender;
	}

	public void setChildTwoGender(String childTwoGender) {
		this.childTwoGender = childTwoGender;
	}

	public String getChildTwoDob() {
		return childTwoDob;
	}

	public void setChildTwoDob(String childTwoDob) {
		this.childTwoDob = childTwoDob;
	}

	public String getPrevOrgName1() {
		return prevOrgName1;
	}

	public void setPrevOrgName1(String prevOrgName1) {
		this.prevOrgName1 = prevOrgName1;
	}

	public String getPrevOrgAdrs1() {
		return prevOrgAdrs1;
	}

	public void setPrevOrgAdrs1(String prevOrgAdrs1) {
		this.prevOrgAdrs1 = prevOrgAdrs1;
	}

	public String getPrevOrgFrom1() {
		return prevOrgFrom1;
	}

	public void setPrevOrgFrom1(String prevOrgFrom1) {
		this.prevOrgFrom1 = prevOrgFrom1;
	}

	public String getPrevOrgTo1() {
		return prevOrgTo1;
	}

	public void setPrevOrgTo1(String prevOrgTo1) {
		this.prevOrgTo1 = prevOrgTo1;
	}

	public String getPrevOrgName2() {
		return prevOrgName2;
	}

	public void setPrevOrgName2(String prevOrgName2) {
		this.prevOrgName2 = prevOrgName2;
	}

	public String getPrevOrgAdrs2() {
		return prevOrgAdrs2;
	}

	public void setPrevOrgAdrs2(String prevOrgAdrs2) {
		this.prevOrgAdrs2 = prevOrgAdrs2;
	}

	public String getPrevOrgFrom2() {
		return prevOrgFrom2;
	}

	public void setPrevOrgFrom2(String prevOrgFrom2) {
		this.prevOrgFrom2 = prevOrgFrom2;
	}

	public String getPrevOrgTo2() {
		return prevOrgTo2;
	}

	public void setPrevOrgTo2(String prevOrgTo2) {
		this.prevOrgTo2 = prevOrgTo2;
	}

	public String getPrevOrgName3() {
		return prevOrgName3;
	}

	public void setPrevOrgName3(String prevOrgName3) {
		this.prevOrgName3 = prevOrgName3;
	}

	public String getPrevOrgAdrs3() {
		return prevOrgAdrs3;
	}

	public void setPrevOrgAdrs3(String prevOrgAdrs3) {
		this.prevOrgAdrs3 = prevOrgAdrs3;
	}

	public String getPrevOrgFrom3() {
		return prevOrgFrom3;
	}

	public void setPrevOrgFrom3(String prevOrgFrom3) {
		this.prevOrgFrom3 = prevOrgFrom3;
	}

	public String getPrevOrgTo3() {
		return prevOrgTo3;
	}

	public void setPrevOrgTo3(String prevOrgTo3) {
		this.prevOrgTo3 = prevOrgTo3;
	}

	public String getCandDegreeName1() {
		return candDegreeName1;
	}

	public void setCandDegreeName1(String candDegreeName1) {
		this.candDegreeName1 = candDegreeName1;
	}

	public String getCandDegreeMajor1() {
		return candDegreeMajor1;
	}

	public void setCandDegreeMajor1(String candDegreeMajor1) {
		this.candDegreeMajor1 = candDegreeMajor1;
	}

	public String getCandDegreePasYr1() {
		return candDegreePasYr1;
	}

	public void setCandDegreePasYr1(String candDegreePasYr1) {
		this.candDegreePasYr1 = candDegreePasYr1;
	}

	public String getCandDegreeInst1() {
		return candDegreeInst1;
	}

	public void setCandDegreeInst1(String candDegreeInst1) {
		this.candDegreeInst1 = candDegreeInst1;
	}

	public String getCandDegreeName2() {
		return candDegreeName2;
	}

	public void setCandDegreeName2(String candDegreeName2) {
		this.candDegreeName2 = candDegreeName2;
	}

	public String getCandDegreeMajor2() {
		return candDegreeMajor2;
	}

	public void setCandDegreeMajor2(String candDegreeMajor2) {
		this.candDegreeMajor2 = candDegreeMajor2;
	}

	public String getCandDegreePasYr2() {
		return candDegreePasYr2;
	}

	public void setCandDegreePasYr2(String candDegreePasYr2) {
		this.candDegreePasYr2 = candDegreePasYr2;
	}

	public String getCandDegreeInst2() {
		return candDegreeInst2;
	}

	public void setCandDegreeInst2(String candDegreeInst2) {
		this.candDegreeInst2 = candDegreeInst2;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getSupervisorEmail() {
		return supervisorEmail;
	}

	public void setSupervisorEmail(String supervisorEmail) {
		this.supervisorEmail = supervisorEmail;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getOfficeMobileNo() {
		return officeMobileNo;
	}

	public void setOfficeMobileNo(String officeMobileNo) {
		this.officeMobileNo = officeMobileNo;
	}

	public String getBankAccNo() {
		return bankAccNo;
	}

	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

	public Double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(Double grossSalary) {
		this.grossSalary = grossSalary;
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

	public String getOfficeLocation() {
		return officeLocation;
	}

	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}

	public String getYearlyBonus() {
		return yearlyBonus;
	}

	public void setYearlyBonus(String yearlyBonus) {
		this.yearlyBonus = yearlyBonus;
	}

	public String getProvisionPeriod() {
		return provisionPeriod;
	}

	public void setProvisionPeriod(String provisionPeriod) {
		this.provisionPeriod = provisionPeriod;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}

	public Integer getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Integer designationId) {
		this.designationId = designationId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getPrevOrgDesig1() {
		return prevOrgDesig1;
	}

	public void setPrevOrgDesig1(String prevOrgDesig1) {
		this.prevOrgDesig1 = prevOrgDesig1;
	}

	public String getPrevOrgDesig2() {
		return prevOrgDesig2;
	}

	public void setPrevOrgDesig2(String prevOrgDesig2) {
		this.prevOrgDesig2 = prevOrgDesig2;
	}

	public String getPrevOrgDesig3() {
		return prevOrgDesig3;
	}

	public void setPrevOrgDesig3(String prevOrgDesig3) {
		this.prevOrgDesig3 = prevOrgDesig3;
	}

	public String getCandId() {
		return candId;
	}

	public void setCandId(String candId) {
		this.candId = candId;
	}

	public Double getProvidentFund() {
		return providentFund;
	}

	public void setProvidentFund(Double providentFund) {
		this.providentFund = providentFund;
	}

	public Double getSalIncreaseAmt() {
		return salIncreaseAmt;
	}

	public void setSalIncreaseAmt(Double salIncreaseAmt) {
		this.salIncreaseAmt = salIncreaseAmt;
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

	public Double getSalaryOthersAmt() {
		return salaryOthersAmt;
	}

	public void setSalaryOthersAmt(Double salaryOthersAmt) {
		this.salaryOthersAmt = salaryOthersAmt;
	}

	public String getAlPreparedBy() {
		return alPreparedBy;
	}

	public void setAlPreparedBy(String alPreparedBy) {
		this.alPreparedBy = alPreparedBy;
	}

	public String getCandidateTraining() {
		return candidateTraining;
	}

	public void setCandidateTraining(String candidateTraining) {
		this.candidateTraining = candidateTraining;
	}

	public String getOthersSkill() {
		return othersSkill;
	}

	public void setOthersSkill(String othersSkill) {
		this.othersSkill = othersSkill;
	}

	public String getRelativeName() {
		return relativeName;
	}

	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}

	public String getRelativeDesig() {
		return relativeDesig;
	}

	public void setRelativeDesig(String relativeDesig) {
		this.relativeDesig = relativeDesig;
	}

	public String getReferName1() {
		return referName1;
	}

	public void setReferName1(String referName1) {
		this.referName1 = referName1;
	}

	public String getReferOccuption1() {
		return referOccuption1;
	}

	public void setReferOccuption1(String referOccuption1) {
		this.referOccuption1 = referOccuption1;
	}

	public String getReferAddress1() {
		return referAddress1;
	}

	public void setReferAddress1(String referAddress1) {
		this.referAddress1 = referAddress1;
	}

	public String getReferContactNo1() {
		return referContactNo1;
	}

	public void setReferContactNo1(String referContactNo1) {
		this.referContactNo1 = referContactNo1;
	}

	public String getReferName2() {
		return referName2;
	}

	public void setReferName2(String referName2) {
		this.referName2 = referName2;
	}

	public String getReferOccuption2() {
		return referOccuption2;
	}

	public void setReferOccuption2(String referOccuption2) {
		this.referOccuption2 = referOccuption2;
	}

	public String getReferAddress2() {
		return referAddress2;
	}

	public void setReferAddress2(String referAddress2) {
		this.referAddress2 = referAddress2;
	}

	public String getReferContactNo2() {
		return referContactNo2;
	}

	public void setReferContactNo2(String referContactNo2) {
		this.referContactNo2 = referContactNo2;
	}

}
