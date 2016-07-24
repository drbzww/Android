package com.cytmxk.criminalintent;

import java.util.ArrayList;
import java.util.List;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AbsListView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class CrimeListFragment extends ListFragment {

	private ArrayList<Crime> mCrimes = null;
	private boolean mSubtitleVisible = false;
	private Button mBtNewCrime = null;
	private Callbacks mCallbacks = null;
	
	public interface Callbacks {
		void onCrimeSelected(Crime crime);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		mCallbacks = null;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setRetainInstance(true);
		setHasOptionsMenu(true);
		mCrimes = CrimeLab.get(getActivity()).getCrimes();

		CrimeAdapter adapter = new CrimeAdapter(mCrimes);
		setListAdapter(adapter);
	}

	@TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getActivity().setTitle(R.string.crimes_title);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB && mSubtitleVisible) {
			getActivity().getActionBar().setSubtitle(R.string.subtitle);
		}
		View view = inflater.inflate(R.layout.fragment_list_crime, container, false);
		mBtNewCrime = (Button) view.findViewById(R.id.bt_new_crime);
		mBtNewCrime.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goCrimeEditView();
			}
		});
		ListView listview = (ListView) view.findViewById(android.R.id.list);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			registerForContextMenu(listview);
		} else {
			listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
			listview.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
				
				@Override
				public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public void onDestroyActionMode(ActionMode mode) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public boolean onCreateActionMode(ActionMode mode, Menu menu) {
					// TODO Auto-generated method stub
					mode.getMenuInflater().inflate(R.menu.crime_list_item_context, menu);
					return true;
				}
				
				@Override
				public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
					// TODO Auto-generated method stub
					switch (item.getItemId()) {
					case R.id.menu_item_delete_crime:
						CrimeAdapter adapter = (CrimeAdapter) getListAdapter();
						CrimeLab crimeLab = CrimeLab.get(getActivity());
						ListView view = getListView();
						for (int i = adapter.getCount() -1; i >= 0; i--) {
							if (view.isItemChecked(i)) {
								crimeLab.deleteCrime(adapter.getItem(i));
							}
						}
						crimeLab.saveCrimes();
						mode.finish();
						adapter.notifyDataSetChanged();
						return true;

					default:
						return false;
					}
				}
				
				@Override
				public void onItemCheckedStateChanged(ActionMode mode, int position,
						long id, boolean checked) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		return view;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Crime crime = (Crime) getListAdapter().getItem(position);
		
		mCallbacks.onCrimeSelected(crime);
	}

	private class CrimeAdapter extends ArrayAdapter<Crime> {

		public CrimeAdapter(List<Crime> objects) {
			super(getActivity(), 0, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			if (null == convertView) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.list_item_crime, parent, false);
			}
			
			Crime crime = getItem(position);
			
			TextView titleTV = (TextView) convertView.findViewById(R.id.crime_list_item_titleTextView);
			TextView dateTV = (TextView) convertView.findViewById(R.id.crime_list_item_titleDate);
			CheckBox slovedCB = (CheckBox) convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
			titleTV.setText(crime.getTitle());
			dateTV.setText(crime.getDate().toString());
			slovedCB.setChecked(crime.isSolved());
			return convertView;
		}
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_crime_list, menu);
		MenuItem item = menu.findItem(R.id.menu_item_show_subtitle);
		if (null != item && mSubtitleVisible) {
			item.setTitle(R.string.hide_subtitle);
		}
	}
	
	@TargetApi(11)
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.menu_item_new_crime:
			goCrimeEditView();
			return true;
		
		case R.id.menu_item_show_subtitle:
			if (getActivity().getActionBar().getSubtitle() == null) {
				getActivity().getActionBar().setSubtitle(R.string.subtitle);
				mSubtitleVisible = true;
				item.setTitle(R.string.hide_subtitle);
			} else {
				getActivity().getActionBar().setSubtitle(null);
				mSubtitleVisible = false;
				item.setTitle(R.string.show_subtitle);
			}
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		getActivity().getMenuInflater().inflate(R.menu.crime_list_item_context, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		CrimeAdapter adapter = (CrimeAdapter) getListAdapter();
		Crime crime = adapter.getItem(info.position);
		switch (item.getItemId()) {
		case R.id.menu_item_delete_crime:
			CrimeLab.get(getActivity()).deleteCrime(crime);
			CrimeLab.get(getActivity()).saveCrimes();
			adapter.notifyDataSetChanged();
			return true;

		default:
			return super.onContextItemSelected(item);
		}
	}
	
	private void goCrimeEditView() {
		Crime crime = new Crime();
		mCrimes.add(crime);
		((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
		mCallbacks.onCrimeSelected(crime);
	}
	
	public void updateUI() {
		((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
	}
}
