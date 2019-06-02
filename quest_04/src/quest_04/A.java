package quest_04;

import java.util.Random;

public class A {
	
	static final Channel canal01 = new ChannelImpl(1);

	public static void main(String[] args) throws InterruptedException {
		System.out.println(gateway(5));
	}
	
	static int gateway(int num_replicas){
			while (!canal01.isFull()) {
				Thread th = new Thread() {

					@Override
					public void run() {
						int valor = request();
						canal01.putMessage(Integer.toString(valor));
						try {
							sleep(valor * 1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				th.start();
			}
			canal01.close();
		
		return Integer.valueOf(canal01.get());
	}
	
	static int request(){
		Random gerador = new Random();
		int valor = gerador.nextInt(30) + 1;
		return valor;
	}


}