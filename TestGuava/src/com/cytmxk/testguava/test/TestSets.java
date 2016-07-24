package com.cytmxk.testguava.test;

import java.util.HashSet;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import android.test.AndroidTestCase;

public class TestSets extends AndroidTestCase {

	public void testCreateHashSet() {
		HashSet<String> hashSet = Sets.newHashSet();
		hashSet.add("aaa");
		hashSet.add("bbb");
		hashSet.add("ccc");
		android.util.Log.i("chenyang", hashSet + "");
	}
	
	public void testCreateAndInitHashSet() {
		ImmutableSet<String> immutableSet = ImmutableSet.of("aaa", "bbb", "ccc");
		android.util.Log.i("chenyang", immutableSet + "");
	}
}
