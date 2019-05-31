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
		synchronized (this) {
			if (!isFull()) {
				this.buffer.add(message);
				this.buffer.notifyAll();
			} else {
				try {
					this.buffer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String takeMessage() {
		String mensagem = null; 
		synchronized (this) {
			if(isEmpty()) {
				try {
					this.buffer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				mensagem = this.buffer.poll();
				this.buffer.notifyAll();
			}
		}
		return mensagem;
	}

	private boolean isFull() {
		return buffer.size() == this.capacidadeMaxima;
	}

	private boolean isEmpty() {
		return this.buffer.size() == 0;
	}

}
