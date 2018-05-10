package wb.bar.entity;

public class Computer {
	private int id;
	private int number;
	private User user;
	private ComputerArea computerArea;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ComputerArea getComputerArea() {
		return computerArea;
	}
	public void setComputerArea(ComputerArea computerArea) {
		this.computerArea = computerArea;
	}

}
