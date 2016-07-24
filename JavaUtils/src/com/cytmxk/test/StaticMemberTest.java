package com.cytmxk.test;

import org.junit.Test;

public class StaticMemberTest {
	
	
	private static class1 c1 = new class1();
	private static class1 c2 = new class1();
	
	static{
		System.out.println("StaticMemberTest staic{}");
	}
	
	{
		System.out.println("StaticMemberTest non-staic{}");
	}

	public StaticMemberTest() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("StaticMemberTest StaticMemberTest()");
	}



	@Test
	public void test(){
		
		System.out.println("StaticMemberTest test()");
		System.out.println("StaticMemberTest c1.int1 = " + c1.int1
				+ "    c1.char1 = " + c1.char1 + "    c1.string1 = " + c1.string1);
		System.out.println("StaticMemberTest c2.int1 = " + c2.int1
				+ "    c2.char1 = " + c2.char1 + "    c2.string1 = " + c2.string1);
		System.out.println("StaticMemberTest class1.int1 = " + class1.int1
				+ "    class1.char1 = " + class1.char1 + "    class1.string1 = " + class1.string1);
		class1.int1 = 2;
		class1.char1 = 'b';
		class1.string1 = "fff";
		System.out.println("----------------------------------------------");
		System.out.println("StaticMemberTest c1.int1 = " + c1.int1
				+ "    c1.char1 = " + c1.char1 + "    c1.string1 = " + c1.string1);
		System.out.println("StaticMemberTest c2.int1 = " + c2.int1
				+ "    c2.char1 = " + c2.char1 + "    c2.string1 = " + c2.string1);
		System.out.println("StaticMemberTest class1.int1 = " + class1.int1
				+ "    class1.char1 = " + class1.char1 + "    class1.string1 = " + class1.string1);
	}
}

class class1 {
	public static int int1 = 1;
	public static char char1 = 'a';
	public static String string1 = "";
	
	static{
		System.out.println("class1 staic{}");
	}
	
	{
		System.out.println("class1 non-staic{}");
	}

	public class1() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("class1 class1()");
	}
	
}


