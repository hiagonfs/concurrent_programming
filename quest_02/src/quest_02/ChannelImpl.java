package quest_02;

import java.util.LinkedList;
import java.util.Queue;

public class ChannelImpl implements Channel {

	private int capacidadeMaxima;
	private Queue<String> buffer;

	public ChannelImpl(int capacidade) {
		this.capacidadeMaxima = capacidade;
		this.buffer = new LinkedList<>();
	}

	@Override
	public void putMessage(String message) {
		synchronized (this.buffer) {
			while(isFull()) {
				try {
					this.buffer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.buffer.add(message); 
			this.buffer.notifyAll();
		}
	}

	@Override
	public String takeMessage() {
		String mensagem = null; 
		synchronized (this.buffer) {
			while(isEmpty()) {
				try {
					this.buffer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			mensagem = this.buffer.poll(); 
			this.buffer.notifyAll();
		}
		return mensagem;
	} 

	private boolean isFull() {
		return this.buffer.size() == this.capacidadeMaxima;
	}

	private boolean isEmpty() {
		return this.buffer.size() == 0;
	}

}
