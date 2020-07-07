package lagerregal;

public class Bilanz {
	private int gesamtkonto = 0;
	public Bilanz() {
		
	}
	public int getGesamtkonto() {
		return gesamtkonto;
	}
	public void addGesamtkonto(int geld) {
		this.gesamtkonto = this.gesamtkonto + gesamtkonto;
	}
	public void removeGesamtkonto(int geld) {
		this.gesamtkonto = this.gesamtkonto - gesamtkonto;
	}
}
