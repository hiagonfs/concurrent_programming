package quest_03;

import java.util.Random;

public class Main {
	
	static int soma = 0;
	
	public static void main(String[] args) {
		System.out.println(gateway(5));	
	}
	
	static int gateway(int num_replicas){
		for (int i = 0; i < num_replicas; i++) {
			Thread thread = new Thread(){
				@Override
				public void run() {
					int valor = request();
					System.out.println(valor);
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
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return soma;
	}
	
	static int request(){
		Random gerador = new Random();
		int valor = gerador.nextInt(30) + 1;
		return valor;
	}

}
