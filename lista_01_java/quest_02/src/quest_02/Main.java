package quest_02;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		final Channel canal01 = new ChannelImpl(2);

		// producer
		Thread th01 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					canal01.putMessage(Integer.toString(i + 1));
				}
			}
		});

		//consumers
		Thread th05 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(canal01.takeMessage());
			}
		});

		Thread th06 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(canal01.takeMessage());
			}
		});

		Thread th07 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(canal01.takeMessage());
			}
		});

		Thread th08 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(canal01.takeMessage());
			}
		});

		th01.start();
		th05.start();
		th06.start();
		th07.start();
		th08.start();

		th01.join();
		th05.join();
		th06.join();
		th07.join();
		th08.join();

	}

}
