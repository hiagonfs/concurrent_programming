package quest_05;

import java.util.Map;

public class EvaluateMap {
	
	public EvaluateMap() {
		// TODO Auto-generated constructor stub
	}

	public String testGet(Map<Integer, Integer> map, int value) throws InterruptedException {

		Thread[] ths = new Thread[5];

		for (int i = 0; i < ths.length; i++) {
			ths[i] = new Thread(() -> {
				for (int j = value; j < value * 2; j++) {
					map.get(j);
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

		return "Test Get, Time: " + tempo;
	}

	public String testPut(Map<Integer, Integer> map, int value) throws InterruptedException {

		Thread[] ths = new Thread[5];

		for (int i = 0; i < ths.length; i++) {
			ths[i] = new Thread(() -> {
				for (int j = value; j < value * 2; j++) {
					map.put(j, j * 2);
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

		return "Test Put, Time: " + tempo;

	}

	public String testRemove(Map<Integer, Integer> map, int value) throws InterruptedException {

		Thread[] ths = new Thread[5];

		for (int i = 0; i < ths.length; i++) {
			ths[i] = new Thread(() -> {
				for (int j = value; j < value * 2; j++) {
					map.remove(j);
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

		return "Test Remove, Time: " + tempo;
	}

}
