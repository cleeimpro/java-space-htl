package lib;

/**
 * Komplexe Zahl mit diversen mathematischen Funktionen
 * @author Clemens Freudenthaler
 *
 */
public class Complex {
	
	/*
	 * Objektdefinition
	 */
	
		// Parameter
		/** Realteil */
		private double re;
		/** Imaginaerteil */
		private double im;
		
		
		//Konstruktor
		/**
		 * Erzeugt eine Komplexe Zahl aus Real und Imaginaerteil
		 * @param re Realteil
		 * @param im Imaginaerteil
		 */
		public Complex(double re, double im) {
			this.re = re;
			this.im = im;
		}
		
		/**
		 * Erzeugt eine komplexe Zahl mit einem Imaginaerteil gleich 0
		 * @param re Realteil
		 */
		public Complex(double re) {
			this.re = re;
			this.im = 0;
		}
		
		@Override
		public String toString() {
			String ret="";
			if(re==0); else if(re-(int)re==0) ret+=(int)re; else ret+=re;	
			if(im>0 && re!=0) ret += "+";
			if(im==0); else if(im==1) ret+="j"; else if (im == -1) ret+= "-j"; else if(im-(int)im==0) ret+=(int)im+"j"; else ret+=im+"j";
			return ret;
		}
		
		/**
		 * Ausgabe in Polarform
		 * @return String mit Polarform
		 */
		public String toPolar() {
			return getAbs()+" < "+getArg()+"grad";
		}
		
	
		public double getRe() {return re;}
		public double getIm() {return im;}
		
		public void setRe(double re) {this.re = re;}
		public void setIm(double im) {this.im = im;}
		
		/** @return Absolutbetrag */
		public double getAbs() {return Math.sqrt(re*re+im*im);}
		
		/** @return Winkel im Gradmass */
		public double getArg() {return Math.toDegrees( Math.atan2(im, re) );}
		
		/** Addiert zur bestehenden Zahl, ein Zahl dazu */
		public Complex add(Complex x) {
			return new Complex(re+x.getRe(), im + x.getIm());
		}
		
		/** Subtrahiert von der bestehenden Zahl eine Zahl*/
		public Complex sub(Complex x) {
			return new Complex(re-x.getRe(), im - x.getIm());
		}
		
		/** Multiplikation von der bestehenden Zahl mit einer Zahl*/
		public Complex mul(Complex x) {
			return new Complex(re*x.getRe()-im*x.getIm(), re * x.getIm()+im*x.getRe());
		}
		
		/** Division von der bestehenden Zahl durch eine Zahl*/
		public Complex div(Complex x) {
			double n = x.getRe()*x.getRe()+x.getIm()*x.getIm(); 
			return new Complex((re*x.getRe()+im*x.getIm())/n,(im*x.getRe()-re*x.getIm())/n);	
		}
		
		/** Invertiert die komplexe Zahl */
		public Complex inv() {
			double n = re*re+im*im;
			return new Complex(re/n,-im/n);
		}
		
		/** Konjugiert die komplexe Zahl */
		public Complex konj() {
			return new Complex(re, -im); 
		}
}
