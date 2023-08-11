package prob5;

import java.util.Arrays;

public class MyStack {
	private int top = 0;
	private String[] buffer;
	
	public MyStack(int size) {
		buffer = new String[size];
	}

	public void push(String string) {
		if(top >= buffer.length) {
			String[] buffer2 = new String[buffer.length*2];
			
			for(int i = 0; i < buffer.length; i++) {
				buffer2[i] = buffer[i];
			}
//			String[] buffer2 = Arrays.copyOf(buffer, buffer.length * 2);
			buffer = buffer2;
		}
		buffer[top] = string;
		top++;
	}

	public boolean isEmpty() {
		if(top == 0) {
			return true;
		}
		return false;
	}

	public String pop() throws MyStackException{
		--top;
		if(top < 0) {
			throw new MyStackException();
		}

		return buffer[top];
	}
}