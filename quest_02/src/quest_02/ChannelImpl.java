package quest_02;

import java.util.LinkedList;
import java.util.Queue;

public class ChannelImpl implements Channel {

	private int capacidadeMaxima;
	private Queue<String> buffer;
	private volatile boolean close;

	public ChannelImpl(int capacidade) {
		this.capacidadeMaxima = capacidade;
		this.buffer = new LinkedList<>();
		this.close = false;
	}

	@Override
	public void putMessage(String message) {
		synchronized (this.buffer) {
			while (isFull() && !this.close) {
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
		synchronized (this.buffer) {
			while (isEmpty() && this.close) {
				try {
					this.buffer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			String mensagem = this.buffer.poll();
			this.buffer.notifyAll();
			return mensagem;
		}
	}

	@Override
	public void close() {
		synchronized (this.buffer) {
			close = true;
			this.buffer.notifyAll();
		}
	}

	private boolean isFull() {
		return this.buffer.size() == this.capacidadeMaxima;
	}

	private boolean isEmpty() {
		return this.buffer.size() == 0;
	}

}
