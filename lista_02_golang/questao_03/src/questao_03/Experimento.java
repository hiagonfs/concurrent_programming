package questao_03;


public class Experimento {

  public static void main(String[] args) {
    int qtdThreads = 150;
    Thread[] threads = new Thread[qtdThreads];

    long memoriaInicio = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

    for (int i = 0; i < qtdThreads; i++) {
      final int number = i + 1;

      Thread thread = new Thread(() -> {
        System.out.println("Thread " + number);
      });

      threads[i] = thread;
      thread.start();
    }

    for (int i = 0; i < qtdThreads; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    long memoriaFinal = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

    System.out.println("Memoria usada: " +(memoriaFinal - memoriaInicio));
  }

}
