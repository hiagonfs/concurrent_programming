package quest_05;

import java.util.List;

public class EvaluateList {

	public EvaluateList() {
	}

	public String testAdd(List<Integer> list, int value) throws InterruptedException {

		Thread[] ths = new Thread[5];

		for (int i = 0; i < ths.length; i++) {
			ths[i] = new Thread(() -> {
				for (int j = 0; j < value; j++) {
					list.add(j);
				}
			});
		}

		long tempoInicio = System.currentTimeMillis();

		for (int i = 0; i < ths.length; i++) {
			ths[i].start();
		}

		for (int i = 0; i < ths.length; i++) {
			ths[i].join();
		}

		long tempoFinal = System.currentTimeMillis();

		long tempo = tempoFinal - tempoInicio;

		return "Test Add, Qtd_valores: " + value + ", Time: " + tempo;

	}

	public String testRemove(List<Integer> list, int value) throws InterruptedException {

		Thread[] ths = new Thread[5];

		for (int i = 0; i < value * 10; i++) {
			list.add(i);
		}

		for (int i = 0; i < ths.length; i++) {
			ths[i] = new Thread(() -> {
				for (int j = 0; j < value; j++) {
					list.remove(0);
				}
			});
		}

		long tempoInicio = System.currentTimeMillis();

		for (int i = 0; i < ths.length; i++) {
			ths[i].start();
		}

		for (int i = 0; i < ths.length; i++) {
			ths[i].join();
		}

		long tempoFinal = System.currentTimeMillis();

		long tempo = tempoFinal - tempoInicio;

		return "Test Remove, Qtd_valores: " + value + ", Time: " + tempo;

	}

	public String testGet(List<Integer> list, int value) throws InterruptedException {

		Thread[] ths = new Thread[5];

		for (int i = 0; i < value; i++) {
			list.add(i);
		}

		for (int i = 0; i < ths.length; i++) {
			ths[i] = new Thread(() -> {
				for (int j = 0; j < value; j++) {
					list.get(j);
				}
			});
		}

		long tempoInicio = System.currentTimeMillis();

		for (int i = 0; i < ths.length; i++) {
			ths[i].start();
		}

		for (int i = 0; i < ths.length; i++) {
			ths[i].join();
		}

		long tempoFinal = System.currentTimeMillis();

		long tempo = tempoFinal - tempoInicio;

		return "Test Get, Qtd_valores: " + value + ", Time: " + tempo;

	}

}
