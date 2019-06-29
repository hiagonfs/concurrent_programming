package quest_03;

import java.util.Random;

public class B {

	static final Channel canal01 = new ChannelImpl(5);
	static int soma = 0;

	public static void main(String[] args) {
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

}