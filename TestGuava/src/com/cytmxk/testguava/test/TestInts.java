package com.cytmxk.testguava.test;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import android.test.AndroidTestCase;

public class TestInts extends AndroidTestCase {

	public void testCompare() {
		int compare = Ints.compare(9, 3);
		android.util.Log.i("chenyang", compare + "");
	}
	
	public void testListToIntArray() {
		int[] array = Ints.toArray(ImmutableList.of(1, 5, 9));
		android.util.Log.i("chenyang", array[0] + ", " + array[1] + ", " + array[2]);
	}
}
