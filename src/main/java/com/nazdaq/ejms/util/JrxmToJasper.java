package com.nazdaq.ejms.util;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;


/**
 * @author RAFIQUL
 *
 */
public class JrxmToJasper {
	public static void main(String[] args) throws JRException {
		// TODO Auto-generated method stub
		
        JasperCompileManager.compileReportToFile(
        		"D:\\gitproject\\ejms\\src\\main\\resources\\CandidateProfile.jrxml", 
        		"D:\\gitproject\\ejms\\src\\main\\resources\\CandidateProfile.jasper");
     }

}
