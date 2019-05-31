package quest_02;

public class Main {

	public static void main(String[] args) {

		// produtores
		// Thread[] producers = new Thread[4];
		// consumidores
		// Thread[] consumers = new Thread[4];

		Channel canal01 = new ChannelImpl(10);

		// for (int i = 0; i < producers.length; i++) {
		// producers[i] = new Thread(() -> {
		// canal01.putMessage("teste " + Integer.toString(count));
		// });
		// }
		//
		// for (int i = 0; i < consumers.length; i++) {
		// consumers[i] = new Thread(() -> {
		// System.out.println(canal01.takeMessage());
		// });
		// }

		/*
		 * Como um dos construtores de Thread recebe uma interface funcional, o
		 * compilador sabe que deve tentar converter esse lambda para um
		 * Runnable! É sempre necessário o envolvimento de uma interface
		 * funcional.
		 */

		Thread th01 = new Thread(() -> {
			canal01.putMessage("um");
		});
		Thread th02 = new Thread(() -> {
			canal01.putMessage("pequeno");
		});
		Thread th03 = new Thread(() -> {
			canal01.putMessage("teste");
		});
		Thread th04 = new Thread(() -> {
			canal01.putMessage("fim");
		});
		
		
		Thread th05 = new Thread(() -> {
			System.out.println(canal01.takeMessage());
		});
		Thread th06 = new Thread(() -> {
			System.out.println(canal01.takeMessage());
		});
		Thread th07 = new Thread(() -> {
			System.out.println(canal01.takeMessage());
		});

		Thread th08 = new Thread(() -> {
			System.out.println(canal01.takeMessage());
		});

		th01.start();
		th02.start();
		th03.start();
		th04.start();
		th05.start();
		th06.start();
		th07.start();
		th08.start();
	}

}
