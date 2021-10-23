public class Customer{
	private String Name, Gender, Address, PassportNo, CardNo;
	private int Age;
	
	public Customer(String name, String gender, String address, String passportno, String cardno, int age) {
		Name = name;
		Gender = gender;
		PassportNo = passportno;
		Address = address;
		CardNo = cardno;
		Age = age;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public String getAddress() {
		return Address;
	}
	
	public void setAddress(String address) {
		Address = address;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getPassportNo() {
		return PassportNo;
	}

	public void setPassportNo(String passportNo) {
		PassportNo = passportNo;
	}
	
	public void printCustomer() {
		System.out.println("Name: " + getName());
		System.out.println("Gender: " + getGender());
		System.out.println("Age: " + getAge());
		System.out.println("Address: " + getAddress());		
	}
}