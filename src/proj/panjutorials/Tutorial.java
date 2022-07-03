package proj.panjutorials;

public class Tutorial {

	public static void main(String[] args) {
		Auto a6 = new Audi(200, "Blau");
		Mechaniker helga = new Mechaniker(a6);

		
		helga.macht();
	}

}
