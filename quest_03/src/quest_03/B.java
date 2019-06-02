package quest_03;

import java.util.Random;

public class B {

	static int soma = 0;

	public static void main(String[] args) throws InterruptedException {
		System.out.println(gateway(5));
	}
	
	static int gateway(int num_replicas){
		Thread thread = null;
		for (int i = 0; i < num_replicas; i++) {
			thread = new Thread(){
				@Override
				public void run() {
					int valor = request();
					soma += valor;
					try {
						sleep(valor * 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					super.run();
				}
			};
			thread.start();
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return soma;
	}

	static int request() {
		Random gerador = new Random();
		int valor = gerador.nextInt(30) + 1;
		return valor;
	}

}