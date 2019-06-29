package quest_03;

public interface Channel {
	
	public void putMessage(String message);
	
	public String takeMessage();

	void close();
	
	boolean isFull();
	
	boolean isEmpty();

	public String get();

}
