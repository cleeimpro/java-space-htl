package proj.supermarkt;

public class Boss {

	public static void main(String[] args) {
		Product milch = new Product("GSH39shg2A", "Laktosefreie Milch, homogenisiert und haltbar gemacht", 299, 450);
		Product brot = new Product("SDJKA832ha", "Vollkornbrot mit Sonnenblumenkernen", 320, 200);
		Product ms = new Product("--Manner--", "Richtig geile Mannerschnitten von Manner", 549, 80);
		Product ms2 = new Product("Manner", "Richtig geile Mannerschnitten von Manner", 0, -2);

		for (int i = 0; i < Product.getAllProducts().size(); i++) {
			System.out.println(Product.getAllProducts().get(i));
		}
	}

}
