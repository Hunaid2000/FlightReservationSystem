public class Plane{
	private String Model;
	private int totalSeats, avaliableSeats;
	
	public Plane(String model, int tseats, int aseats) {
		Model = model;
		totalSeats = tseats;
		avaliableSeats = aseats;
	}
	
	public String getModel() {
		return Model;
	}
	
	public void setModel(String model) {
		Model = model;
	}
	
	public int getTotalSeats() {
		return totalSeats;
	}
	
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	public int getAvaliableSeats() {
		return avaliableSeats;
	}
	
	public void setAvaliableSeats(int avaliableSeats) {
		this.avaliableSeats = avaliableSeats;
	}
}