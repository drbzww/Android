package com.cytmxk.criminalintent;

import java.util.ArrayList;
import java.util.UUID;
import android.content.Context;
import android.util.Log;

public class CrimeLab {

	private static final String FILENAME = "crimes.json";
	private static final String TAG = "CrimeLab";
	
	private static CrimeLab sCrimeLab = null;
	
	private Context mAppContext = null;
	private ArrayList<Crime> mCrimes = null;
	private CriminalIntentJSONSerializer serializer = null;

	private CrimeLab(Context appContext) {
		this.mAppContext = appContext;
		serializer = new CriminalIntentJSONSerializer(mAppContext, FILENAME);
		try {
			mCrimes = serializer.loadCrimesFromExternalStorage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mCrimes = new ArrayList<Crime>();
			Log.i(TAG, "Error loading crimes: " + e);
		}
	}

	public static CrimeLab get(Context context) {
		if (null == sCrimeLab) {
			sCrimeLab = new CrimeLab(context.getApplicationContext());
		}
		return sCrimeLab;
	}

	public ArrayList<Crime> getCrimes() {
		return mCrimes;
	}

	public Crime getCrime(UUID id) {

		for (Crime crime : mCrimes) {
			if (crime.getId().equals(id)) {
				return crime;
			}
		}
		return null;
	}
	
	public void addCrime(Crime crime){
		mCrimes.add(crime);
	}
	
	public void deleteCrime(Crime crime){
		mCrimes.remove(crime);
	}
	
	public boolean saveCrimes() {
		try {
			serializer.saveCrimesToExternalStorage(mCrimes);
			Log.i(TAG, "crimes saved to file");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.i(TAG, "Error saving crimes: " + e);
			return false;
		}
	}
}
