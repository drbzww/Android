package com.cytmxk.testthread.synchronize;

import android.test.AndroidTestCase;

public class TestSynchronize extends AndroidTestCase {

	public void test1(){
		Stack stack = new Stack();
		new PushTread(stack, 'h').start();
		new PopTread(stack).start();
		new PushTread(stack, 'e').start();
		new PopTread(stack).start();
		new PushTread(stack, 'g').start();
		new PopTread(stack).start();
		new PushTread(stack, 'a').start();
		new PopTread(stack).start();
		new PushTread(stack, 'b').start();
		new PopTread(stack).start();
	}
	
	class PopTread extends Thread{
		
		private Stack s = null;

		public PopTread(Stack s) {
			super();
			this.s = s;
		}


		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			s.pop();
		}
	}
	
	class PushTread extends Thread{
		
		private Stack s = null;
		private char push = 0;
		
		public PushTread(Stack s,char push) {
			super();
			this.s = s;
			this.push = push;
		}
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			s.push(push);
		}
	}
}
