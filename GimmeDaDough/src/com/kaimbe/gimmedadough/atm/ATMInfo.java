package com.kaimbe.gimmedadough.atm;

public class ATMInfo {
	
	private String id;
	private String branch;
	private String institution;
	
	public ATMInfo(String id, String branch, String institution) {
		try {
			if(!(id.matches("\\d{2}") || branch.matches("\\d{5}") || institution.matches("\\d{3}"))) {
				throw new IllegalArgumentException();
			}
			else {
				this.id = id;
				this.branch = branch;
				this.institution = institution;
			}
		} 
		catch (IllegalArgumentException e) {
			// TODO: catch this
		}
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}

	/**
	 * @return the institution
	 */
	public String getInstitution() {
		return institution;
	}

}
