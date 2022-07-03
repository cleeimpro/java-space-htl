package proj.panjutorials;

public class Mechaniker {
	
	Auto autoName;
	
	public Mechaniker(Auto autoName) {
		this.autoName = autoName;
	}
	
	public void macht() {
		autoName.reparieren();
	}
}
