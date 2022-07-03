package lib.awt;

public class FensterAnimated extends Fenster implements Runnable{

	private static final long serialVersionUID = -3829114986512144112L;
	
	public static boolean RUN = true; //Ob das Fenster neu gepaintet wird
	public static int SLEEPTIME = 20; //Wie lange zwischen zwei Aktualisierungen Zeit ist
	Thread t;

	public FensterAnimated() {
		super();
		t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		while(true) {
			if(RUN)
				this.repaint();
			try {
				
					Thread.sleep(SLEEPTIME);
				
			} catch (InterruptedException e) { }
		}
	}
	
}
