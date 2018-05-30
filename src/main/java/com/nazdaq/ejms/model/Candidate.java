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

@Entity
@Table(name = "candidate")
public class Candidate implements Serializable {
	private static final long serialVersionUID = 7101825781950716138L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "recurite_by", nullable = true)
	private HREmployee hrEmployee;

	@Column(name = "date_of_join")
	private String dateOfJoin;

	@Column(name = "old_date_of_join")
	private String oldDateOfJoin;

	@Column(name = "ejms_joining_date")
	private String ejmsJoiningDate;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id", nullable = true)
	private Company company;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "designation_id", nullable = true)
	private Designation designation;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id", nullable = true)
	private Department department;

	@Column(name = "source_of_cand")
	private String sourceOfCand;

	@Column(name = "cand_id")
	private String candId;

	@Column(name = "cand_name")
	private String candName;

	@Column(name = "cand_email")
	private String candEmail;

	@Column(name = "cand_mobile_no")
	private String candMobileNo;

	@Column(name = "cand_status")
	private String status;

	@Column(name = "decline_date")
	private String declineDate;

	@Column(name = "decline_reason")
	private String declineReason;

	@Column(name = "decline_by")
	private String declineBy;

	@Column(name = "reject_reason_by_cand")
	private String rejectReasonByCandidate;

	@Column(name = "rejected_by_cand_date")
	private Date rejectedByCandidateDate;

	@Column(name = "upload_app_letter")
	private String uploadAppLetter = "0";

	@Column(name = "upload_extend_letter")
	private String uploadExtendLetter = "0";

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

	@Column(name = "ref_no")
	private String refNo;

	@Column(name = "cand_profile_app_date")
	private String candProfileApproveDate;

	@Column(name = "employee_id")
	private String employeeId;

	// recommand by tasnim start
	@Column(name = "upload_resign_letter")
	private String uploadResignLetter = "0";

	@Column(name = "upload_others_doc")
	private String uploadOthersLetter = "0";
	// recommand by tasnim end

	@Transient
	private Integer companyId;

	@Transient
	private String recuriteById;

	@Transient
	private String departmentId;

	@Transient
	private String designationId;

	@Transient
	private String empId;

	@Transient
	private String fromDate;

	@Transient
	private String toDate;

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDesignationId() {
		return designationId;
	}

	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDateOfJoin() {
		return dateOfJoin;
	}

	public void setDateOfJoin(String dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getSourceOfCand() {
		return sourceOfCand;
	}

	public void setSourceOfCand(String sourceOfCand) {
		this.sourceOfCand = sourceOfCand;
	}

	public String getCandId() {
		return candId;
	}

	public void setCandId(String candId) {
		this.candId = candId;
	}

	public String getCandName() {
		return candName;
	}

	public void setCandName(String candName) {
		this.candName = candName;
	}

	public String getCandEmail() {
		return candEmail;
	}

	public void setCandEmail(String candEmail) {
		this.candEmail = candEmail;
	}

	public String getCandMobileNo() {
		return candMobileNo;
	}

	public void setCandMobileNo(String candMobileNo) {
		this.candMobileNo = candMobileNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getDeclineDate() {
		return declineDate;
	}

	public void setDeclineDate(String declineDate) {
		this.declineDate = declineDate;
	}

	public String getDeclineReason() {
		return declineReason;
	}

	public void setDeclineReason(String declineReason) {
		this.declineReason = declineReason;
	}

	public String getDeclineBy() {
		return declineBy;
	}

	public void setDeclineBy(String declineBy) {
		this.declineBy = declineBy;
	}

	public HREmployee getHrEmployee() {
		return hrEmployee;
	}

	public void setHrEmployee(HREmployee hrEmployee) {
		this.hrEmployee = hrEmployee;
	}

	public String getRecuriteById() {
		return recuriteById;
	}

	public void setRecuriteById(String recuriteById) {
		this.recuriteById = recuriteById;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getRejectReasonByCandidate() {
		return rejectReasonByCandidate;
	}

	public void setRejectReasonByCandidate(String rejectReasonByCandidate) {
		this.rejectReasonByCandidate = rejectReasonByCandidate;
	}

	public Date getRejectedByCandidateDate() {
		return rejectedByCandidateDate;
	}

	public void setRejectedByCandidateDate(Date rejectedByCandidateDate) {
		this.rejectedByCandidateDate = rejectedByCandidateDate;
	}

	public String getUploadAppLetter() {
		return uploadAppLetter;
	}

	public void setUploadAppLetter(String uploadAppLetter) {
		this.uploadAppLetter = uploadAppLetter;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEjmsJoiningDate() {
		return ejmsJoiningDate;
	}

	public void setEjmsJoiningDate(String ejmsJoiningDate) {
		this.ejmsJoiningDate = ejmsJoiningDate;
	}

	public String getOldDateOfJoin() {
		return oldDateOfJoin;
	}

	public void setOldDateOfJoin(String oldDateOfJoin) {
		this.oldDateOfJoin = oldDateOfJoin;
	}

	public String getUploadExtendLetter() {
		return uploadExtendLetter;
	}

	public void setUploadExtendLetter(String uploadExtendLetter) {
		this.uploadExtendLetter = uploadExtendLetter;
	}

	public String getUploadResignLetter() {
		return uploadResignLetter;
	}

	public void setUploadResignLetter(String uploadResignLetter) {
		this.uploadResignLetter = uploadResignLetter;
	}

	public String getUploadOthersLetter() {
		return uploadOthersLetter;
	}

	public void setUploadOthersLetter(String uploadOthersLetter) {
		this.uploadOthersLetter = uploadOthersLetter;
	}

}
