package quest_03;

import java.util.Random;

public class A {

	static final Channel canal01 = new ChannelImpl(5);

	public static void main(String[] args) {
		System.out.println("primeiro "+gateway(5));
	}

	static int gateway(int num_replicas) {
		for (int i = 0; i < num_replicas; i++) {
			Thread th = new Thread() {

				@Override
				public void run() {
					int valor = request();
					canal01.putMessage(Integer.toString(valor));
				}
			};
			th.start();
		}
		return Integer.valueOf(canal01.takeMessage());
	}

	static int request() {
		Random gerador = new Random();
		int valor = gerador.nextInt(30) + 1;
		try {
			Thread.sleep(valor * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valor;
	}

}