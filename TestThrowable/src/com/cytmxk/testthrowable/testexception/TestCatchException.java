package com.cytmxk.testthrowable.testexception;

import android.test.AndroidTestCase;

public class TestCatchException extends AndroidTestCase {

	public void testArrayOutOf() {
		String[] shows = { "chenyang is a good boy!", "beauty is where?",
				"who?" };
		int i = 0;
		while (i < 4) {
			try {
				android.util.Log.i("chenyang", shows[i]);
			} catch (ArrayIndexOutOfBoundsException e) {
				android.util.Log.i("chenyang", "e = " + e);
			} finally {
				android.util.Log.i("chenyang", "finally");
			}
			android.util.Log.i("chenyang", "i = " + i);
			i++;
		}
	}

	public void testFinally() {
		String[] shows = { "chenyang is a good boy!", "beauty is where?",
				"who?" };
		int i = 0;
		while (i < 3) {
			try {
				android.util.Log.i("chenyang", shows[i]);
				System.exit(0);
			} finally {
				android.util.Log.i("chenyang", "finally");
			}
			android.util.Log.i("chenyang", "i = " + i);
			i++;
		}
	}
}
