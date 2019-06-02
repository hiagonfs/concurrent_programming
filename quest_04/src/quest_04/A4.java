package quest_04;

import java.util.Random;

public class A4 {

	static final Channel canal01 = new ChannelImpl(5);
	static int seconds = 0;

	public static void main(String[] args) {
		timer();
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
		if (seconds >= 8 && canal01.isEmpty()) {
			return -1;
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

	private static void timer() {
		long start = System.currentTimeMillis();
		Thread counter = new Thread() {

			@Override
			public void run() {
				while (seconds < 31) {
					try {
						seconds = (int) (System.currentTimeMillis() - start) / 1000;
						System.out.println(seconds + "s");
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