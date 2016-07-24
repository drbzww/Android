package com.cytmxk.criminalintent;

import java.util.ArrayList;
import java.util.List;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.ActionMode.Callback;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class CrimeListFragment extends SherlockListFragment {

	private ArrayList<Crime> mCrimes = null;
	private boolean mSubtitleVisible = false;
	private Button mBtNewCrime = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setRetainInstance(true);
		setHasOptionsMenu(true);
		mCrimes = CrimeLab.get(getSherlockActivity()).getCrimes();

		CrimeAdapter adapter = new CrimeAdapter(mCrimes);
		setListAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getSherlockActivity().setTitle(R.string.crimes_title);
		if (mSubtitleVisible) {
			getSherlockActivity().getSupportActionBar().setSubtitle(
					R.string.subtitle);
		}

		View view = inflater.inflate(R.layout.fragment_list_crime, container,
				false);
		mBtNewCrime = (Button) view.findViewById(R.id.bt_new_crime);
		mBtNewCrime.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goCrimeEditView();
			}
		});
		ListView listview = (ListView) view.findViewById(android.R.id.list);
		listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				android.util.Log.i("chenyang","onItemLongClick ");
				getSherlockActivity().startActionMode(new Callback() {

					@Override
					public boolean onCreateActionMode(ActionMode mode, Menu menu) {
						// TODO Auto-generated method stub
						mode.getMenuInflater().inflate(
								R.menu.crime_list_item_context, menu);
						return true;
					}

					@Override
					public boolean onPrepareActionMode(ActionMode mode,
							Menu menu) {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public boolean onActionItemClicked(ActionMode mode,
							MenuItem item) {
						// TODO Auto-generated method stub
						switch (item.getItemId()) {
						case R.id.menu_item_delete_crime:
							CrimeAdapter adapter = (CrimeAdapter) getListAdapter();
							CrimeLab crimeLab = CrimeLab.get(getSherlockActivity());
							ListView view = getListView();
							for (int i = adapter.getCount() - 1; i >= 0; i--) {
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
					public void onDestroyActionMode(ActionMode mode) {
						// TODO Auto-generated method stub
					}
				});
				return true;
			}
		});
		return view;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		android.util.Log.i("chenyang","onListItemClick ");
		Crime crime = (Crime) getListAdapter().getItem(position);

		Intent intent = new Intent(getSherlockActivity(), CrimePagerActivity.class);
		intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
		startActivity(intent);
	}

	private class CrimeAdapter extends ArrayAdapter<Crime> {

		public CrimeAdapter(List<Crime> objects) {
			super(getSherlockActivity(), 0, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			if (null == convertView) {
				convertView = getSherlockActivity().getLayoutInflater().inflate(
						R.layout.list_item_crime, parent, false);
			}

			Crime crime = getItem(position);

			TextView titleTV = (TextView) convertView
					.findViewById(R.id.crime_list_item_titleTextView);
			TextView dateTV = (TextView) convertView
					.findViewById(R.id.crime_list_item_titleDate);
			CheckBox slovedCB = (CheckBox) convertView
					.findViewById(R.id.crime_list_item_solvedCheckBox);
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

		((CrimeAdapter) getListAdapter()).notifyDataSetChanged();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_crime_list, menu);
		com.actionbarsherlock.view.MenuItem item = menu
				.findItem(R.id.menu_item_show_subtitle);
		if (null != item && mSubtitleVisible) {
			item.setTitle(R.string.hide_subtitle);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.menu_item_new_crime:
			goCrimeEditView();
			return true;

		case R.id.menu_item_show_subtitle:
			if (getSherlockActivity().getSupportActionBar().getSubtitle() == null) {
				getSherlockActivity().getSupportActionBar().setSubtitle(R.string.subtitle);
				mSubtitleVisible = true;
				item.setTitle(R.string.hide_subtitle);
			} else {
				getSherlockActivity().getSupportActionBar().setSubtitle(null);
				mSubtitleVisible = false;
				item.setTitle(R.string.show_subtitle);
			}
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void goCrimeEditView() {
		Crime crime = new Crime();
		mCrimes.add(crime);
		Intent intent = new Intent(getSherlockActivity(), CrimePagerActivity.class);
		intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
		startActivityForResult(intent, 0);
	}
}
