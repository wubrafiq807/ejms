package com.nazdaq.ejms.beans;

import java.io.Serializable;

public class CandidateProfileBean implements Serializable {

	private static final long serialVersionUID = 7101825781950716138L;

	private Integer id = null;

	private Integer fieldSlNo = null;

	private String fieldName = null;

	private String fieldValue = null;

	private String fieldExample = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFieldSlNo() {
		return fieldSlNo;
	}

	public void setFieldSlNo(Integer fieldSlNo) {
		this.fieldSlNo = fieldSlNo;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getFieldExample() {
		return fieldExample;
	}

	public void setFieldExample(String fieldExample) {
		this.fieldExample = fieldExample;
	}
	
	public CandidateProfileBean() {
		
	}

	public CandidateProfileBean(Integer id, Integer fieldSlNo, String fieldName, String fieldValue,
			String fieldExample) {
		super();
		this.id = id;
		this.fieldSlNo = fieldSlNo;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.fieldExample = fieldExample;
	}
	
	

}
