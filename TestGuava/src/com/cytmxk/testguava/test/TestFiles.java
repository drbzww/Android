package com.cytmxk.testguava.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.cytmxk.testguava.collections.FileUtils;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import android.test.AndroidTestCase;

public class TestFiles extends AndroidTestCase {

	public void testReadFile() {
		File file = FileUtils.getDiskCacheDir(getContext(), "test.txt");
		List<String> lines = null;
		try {
		  lines = Files.readLines(file, Charsets.UTF_8);
		} catch (IOException e) {
		  e.printStackTrace();
		}
		android.util.Log.i("chenyang", lines + "");
	}
}
