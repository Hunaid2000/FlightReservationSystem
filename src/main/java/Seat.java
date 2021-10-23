public class Seat{
	private static int count = 1;
	private int seatNo;
	private double fare;
	private Boolean isBooked, isReserved;
	private String Class;
	
	public Seat(String c, Boolean booked, Boolean reserved, Flight f) {
		seatNo = count++;
		Class = c;
		if(Class.compareTo("Business") == 0)
			setFare(f.getFare() + 10000);
		else if(Class.compareTo("First") == 0)
			setFare(f.getFare() + 20000);
		else
			setFare(f.getFare());
		isBooked = booked;
		isReserved = reserved;
	}
	
	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	
	public String getclass() {
		return Class;
	}

	public void setClass(String c) {
		if(Class.compareTo("Economy") == 0) {
			if(c.compareTo("Business") == 0)
				fare += 10000;
			else if(c.compareTo("First") == 0)
				fare += 20000;
		}
		else if(Class.compareTo("Business") == 0) {
			if(c.compareTo("Economy") == 0)
				fare -= 10000;
			else if(c.compareTo("First") == 0)
				fare += 10000;
		}
		else if(Class.compareTo("First") == 0) {
			if(c.compareTo("Economy") == 0)
				fare -= 20000;
			else if(c.compareTo("Business") == 0)
				fare -= 10000;
		}
		Class = c;
	}
	
	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}
	
	public Boolean getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(Boolean isBooked) {
		this.isBooked = isBooked;
	}

	public Boolean getIsReserved() {
		return isReserved;
	}

	public void setIsReserved(Boolean isReserved) {
		this.isReserved = isReserved;
	}
}