package com.kaimbe.gimmedadough.atm;

/**
 * 
 * @author Matthew Newell
 * 
 *         stores information about an atm
 * 
 */
public class ATMInformation {
	/**
	 * the id of the atm
	 */
	private String id;
	/**
	 * the branch this atm is located at
	 */
	private String branch;
	/**
	 * the institution that owns the atm
	 */
	private String institution;

	/**
	 * constructor. id must have 2 digits, branch must have 5, institution must
	 * have 3.
	 * 
	 * @param id
	 * @param branch
	 * @param institution
	 */
	public ATMInformation(String id, String branch, String institution) {
		try {
			if (!(id.matches("\\d{2}") || branch.matches("\\d{5}") || institution
					.matches("\\d{3}"))) {
				throw new IllegalArgumentException();
			} else {
				this.id = id;
				this.branch = branch;
				this.institution = institution;
			}
		} catch (IllegalArgumentException e) {
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
