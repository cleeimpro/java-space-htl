package proj.supermarkt;

import java.util.Vector;

public class Product {
	private String shortDescrip;
	private String descrip;
	private int price;
	private int number;
	private static final Vector<String> listProducts = new Vector<String>();
	
	/**
	 * Konstructor von Product
	 * @param shortDescrip Kurzbeschreibung/10 Zeichen
	 * @param descrip Produktbeschreibung
	 * @param price Preis > 0 (in cent)
	 * @param number Stückzahl im Lager >= 0
	 */
	public Product(String shortDescrip,String descrip,int price,int number) {
		
		String error = "Produkt \"" + shortDescrip + "\" wurde nicht eingelagert!\n";
		boolean correct = true;
		
		if(shortDescrip.length() != 10) {
			error += ">\tKurzbeschreibung ist nicht zulässig!\n";
			correct = false;		
		}
		
		if(price <= 0) {
			error += ">\tPreis muss größer als 0 sein!\n";
			correct = false;
		}
		if(number < 0) {
			error += ">\tProduktanzahl muss mindestens 0 sein!\n";
			correct = false;
		}
		
		if(correct) {
			this.shortDescrip=shortDescrip;
			this.descrip=descrip;
			this.price=price;
			this.number=number;
			listProducts.add(shortDescrip +" "+ descrip);
		}
		else {
			System.out.println(error);
		}
	}
	
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void delivery(int number) {
		this.number += number;
	}
	/**
	 * Abgang von Produkten in Stück
	 * @param number verkaufte Stückzahl (Lager darf nicht leer werden)
	 */
	public void leaving(int number) {	
		if(this.number - number<0)
			System.out.println("Lager ist nicht voll genug!");
		else {this.number -= number;}
	}
	
	public String getAllInfo() {
		return (shortDescrip +"\t"+ price +"c\t"+ number +"\t"+ descrip);
	}
	
	public static Vector<String> getAllProducts(){
        return listProducts;
    }
	
}
