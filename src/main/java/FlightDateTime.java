public class FlightDateTime{
	private String DeptDate, DeptTime, ArrTime;
	
	public FlightDateTime(String date, String dtime, String atime) {
		DeptDate = date;
		DeptTime = dtime;
		ArrTime = atime;
	}

	public String getDeptDate() {
		return DeptDate;
	}

	public void setDeptDate(String deptDate) {
		DeptDate = deptDate;
	}

	public String getDeptTime() {
		return DeptTime;
	}

	public void setDeptTime(String deptTime) {
		DeptTime = deptTime;
	}

	public String getArrTime() {
		return ArrTime;
	}

	public void setArrTime(String arrTime) {
		ArrTime = arrTime;
	}	
}