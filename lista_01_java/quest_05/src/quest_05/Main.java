package quest_05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		EvaluateMap evaluateMap = new EvaluateMap();
		EvaluateList evaluateList = new EvaluateList(); 

		Map<Integer, Integer> mapOne = new ConcurrentHashMap<Integer, Integer>();
		Map<Integer, Integer> mapTwo = Collections.synchronizedMap(new HashMap<Integer, Integer>());

		CopyOnWriteArrayList<Integer> listOne = new CopyOnWriteArrayList<>();
		List<Integer> listTwo = Collections.synchronizedList(new ArrayList<Integer>());

		int[] valoresDeTesteMapeamento = { 1, 2, 3, 10, 100, 1000, 10000, 100000, 1000000, 10000000 };
		int[] valoresDeTesteLista = { 1, 2, 3, 10, 100, 1000, 20000, 10000 };

		System.out.println("Teste CopyOnWriteArrayList");
		for (int i = 0; i < valoresDeTesteLista.length; i++) {
			System.out.println(evaluateList.testRemove(listOne, valoresDeTesteLista[i])); // tipo de teste e colecao a ser testada
		}
		
		System.out.println("Teste Collections.synchronizedList");
		for (int i = 0; i < valoresDeTesteLista.length; i++) {
			System.out.println(evaluateList.testRemove(listTwo, valoresDeTesteLista[i])); // tipo de teste e colecao a ser testada
		}

	}

}
