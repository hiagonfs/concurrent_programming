package quest_04;

import java.util.Random;

import quest_04.Channel;
import quest_04.ChannelImpl;

public class B4 {

	static final Channel canal01 = new ChannelImpl(5);
	static int soma = 0;
	static int seconds = 0;

	public static void main(String[] args) {
		timer();
		System.out.println("soma "+gateway(5));
	}
	
	static int gateway(int num_replicas) {
		Thread th = null;
		for (int i = 0; i < num_replicas; i++) {
			th = new Thread() {

				@Override
				public void run() {
					int valor = request();
					System.out.println(valor);
					canal01.putMessage(Integer.toString(valor));
				}
			};
			th.start();
		}
		try {
			th.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (!canal01.isEmpty()) {
			soma += Integer.valueOf(canal01.takeMessage());
		}
		return soma;
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

	
	private static void timer() {
		long start = System.currentTimeMillis();
		Thread counter = new Thread() {

			@Override
			public void run() {
				while (seconds < 31) {
					try {
						seconds = (int)(System.currentTimeMillis() - start) / 1000;
						System.out.println(seconds+"s");
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		};
		counter.start();
	}

}