package wb.bar.entity;

public class ComputerArea {
	private int id;
	private String name;
	private float charging; // 收费/小时
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getCharging() {
		return charging;
	}
	public void setCharging(float charging) {
		this.charging = charging;
	}

}
