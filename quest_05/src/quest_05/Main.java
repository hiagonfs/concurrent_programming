package quest_05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {

	Map<Integer, Integer> mapOne = new ConcurrentHashMap<Integer, Integer>();
	Map<Integer, Integer> mapTwo = Collections.synchronizedMap(new HashMap<Integer, Integer>());

	CopyOnWriteArrayList<Integer> listOne = new CopyOnWriteArrayList<>();
	List<Integer> listTwo = Collections.synchronizedList(new ArrayList<Integer>());

}
