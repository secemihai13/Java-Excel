/**
 * 
 */
package ExcelProject.ExcelArtifact;

public class ObiectRand {
	
	private String hhnr, pnr, firstName, lastName, birthday;

	public String getHhnr() {
		return hhnr;
	}

	public void setHhnr(String hhnr) {
		this.hhnr = hhnr;
	}

	public String getPnr() {
		return pnr.substring(pnr.length() - 2);
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public ObiectRand(String hhnr, String pnr, String firstName, String lastName, String birthday) {
		this.hhnr = hhnr;
		this.pnr = pnr;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
	}
	
	public String printFirstLine() {
		return this.hhnr + " " + this.pnr + " " + this.firstName + " " + this.lastName + " " + this.birthday;
	}

	@Override
	public String toString() {
//		String forPNR = this.pnr.substring(this.pnr.length() - 2);
		return pnr + " " + firstName + " " + lastName
				+ " " + birthday + "\t";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((hhnr == null) ? 0 : hhnr.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((pnr == null) ? 0 : pnr.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObiectRand other = (ObiectRand) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (hhnr == null) {
			if (other.hhnr != null)
				return false;
		} else if (!hhnr.equals(other.hhnr))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (pnr == null) {
			if (other.pnr != null)
				return false;
		} else if (!pnr.equals(other.pnr))
			return false;
		return true;
	}

	

	

	
	
	

}
