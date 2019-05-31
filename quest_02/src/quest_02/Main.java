package quest_02;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		final Channel canal01 = new ChannelImpl(10);

		Thread th01 = new Thread(new Runnable() {

			@Override
			public void run() {
				canal01.putMessage("um");
			}
		});

		Thread th02 = new Thread(new Runnable() {

			@Override
			public void run() {
				canal01.putMessage("pequeno");
			}
		});

		Thread th03 = new Thread(new Runnable() {

			@Override
			public void run() {
				canal01.putMessage("teste");
			}
		});

		Thread th04 = new Thread(new Runnable() {

			@Override
			public void run() {
				canal01.putMessage("fim");
			}
		});

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
		th02.start();
		th03.start();
		th04.start();
		th05.start();
		th06.start();
		th07.start();
		th08.start();

		th01.join();
		th02.join();
		th03.join();
		th04.join();
		th05.join();
		th06.join();
		th07.join();
		th08.join();

	}

}
