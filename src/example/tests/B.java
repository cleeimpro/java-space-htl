package example.tests;

public class B extends Thread{
	
	public B() {
		this.start();
	}
	
	@Override
	public void run() {
		Thread.currentThread().setName("Alive");
		while(!Thread.currentThread().isInterrupted()) {
			System.out.println("B laeuft!");
			Thread.currentThread().setName("Alive");
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		Thread.currentThread().setName("Dead");
		System.out.println("B wurde unterbrochen");
	}

}
