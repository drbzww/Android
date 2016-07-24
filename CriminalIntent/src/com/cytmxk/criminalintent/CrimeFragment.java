package com.cytmxk.criminalintent;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

@SuppressLint("SimpleDateFormat")
public class CrimeFragment extends Fragment {

	public static final String EXTRA_CRIME_ID = "com.cytmxk.criminalintent.crime_id";
	private static final String DIALOG_DATE = "date";
	private static final String DIALOG_IMAGE = "image";
	private static final int REQUEST_DATE = 0;
	private static final int REQUEST_PHOTO = 1;
	private static final int REQUEST_SUSPECT = 2;

	private Crime mCrime = null;

	private EditText mTitleField = null;
	private Button mDateButton = null;
	private CheckBox mSolvedCheckBox = null;
	private ImageButton mIbStartCamera = null;
	private ImageView mImageView = null;
	private Button mSendReportButton = null;
	private Button mChooseSuspectButton = null;
	private Button mCallSuspectButton = null;
	private Callbacks mCallbacks = null;

	public interface Callbacks {
		void onCrimeUpdated(Crime crime);
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

		setHasOptionsMenu(true);
		UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
		mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		android.util.Log.i("chenyang", "CrimeFragment onPause");
		CrimeLab.get(getActivity()).saveCrimes();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		showPhoto();
	}

	private void showPhoto() {
		Photo photo = mCrime.getPhoto();
		BitmapDrawable bitmapDrawable = null;
		if (null != photo) {
			String path = getActivity().getFileStreamPath(photo.getFilename())
					.getAbsolutePath();
			bitmapDrawable = PictureUtils
					.getScaledDrawable(getActivity(), path);
		}
		mImageView.setImageDrawable(bitmapDrawable);

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		PictureUtils.cleanImageView(mImageView);
	}

	public static CrimeFragment newInstance(UUID crimeId) {

		CrimeFragment mCrimeFragment = new CrimeFragment();
		Bundle arguments = new Bundle();
		arguments.putSerializable(EXTRA_CRIME_ID, crimeId);
		mCrimeFragment.setArguments(arguments);
		return mCrimeFragment;
	}

	@TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View v = inflater.inflate(R.layout.fragment_crime, container, false);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			if (NavUtils.getParentActivityName(getActivity()) != null) {
				getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
			}
		}

		mTitleField = (EditText) v.findViewById(R.id.crime_title);
		mDateButton = (Button) v.findViewById(R.id.crime_date);
		mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
		mIbStartCamera = (ImageButton) v.findViewById(R.id.ib_start_camera);
		mImageView = (ImageView) v.findViewById(R.id.iv_crime_photo);
		mSendReportButton = (Button) v.findViewById(R.id.bt_send_report);
		mChooseSuspectButton = (Button) v.findViewById(R.id.bt_choose_suspect);
		mCallSuspectButton = (Button) v.findViewById(R.id.bt_call_suspect);

		mTitleField.setText(mCrime.getTitle());
		mTitleField.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				mCrime.setTitle(s.toString());
				mCallbacks.onCrimeUpdated(mCrime);
				getActivity().setTitle(mCrime.getTitle());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		updateDate();
		mDateButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fm = getActivity().getSupportFragmentManager();
				DatePickerFragment dateDialog = DatePickerFragment
						.newInstance(mCrime.getDate());
				dateDialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
				dateDialog.show(fm, DIALOG_DATE);
			}
		});

		mSolvedCheckBox.setChecked(mCrime.isSolved());
		mSolvedCheckBox
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						mCrime.setSolved(isChecked);
						mCallbacks.onCrimeUpdated(mCrime);
					}
				});

		mIbStartCamera.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						CrimeCameraActivity.class);
				startActivityForResult(intent, REQUEST_PHOTO);
			}
		});

		mImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Photo photo = mCrime.getPhoto();
				if (null == photo) {
					return;
				}
				FragmentManager manager = getActivity()
						.getSupportFragmentManager();
				String imagePath = getActivity().getFileStreamPath(
						photo.getFilename()).getAbsolutePath();
				ImageFragment.newInstance(imagePath)
						.show(manager, DIALOG_IMAGE);
			}
		});
		mImageView.setOnLongClickListener(new View.OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				getActivity().startActionMode(new ActionMode.Callback() {

					@Override
					public boolean onPrepareActionMode(ActionMode mode,
							Menu menu) {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public void onDestroyActionMode(ActionMode mode) {
						// TODO Auto-generated method stub
						mImageView.setBackgroundColor(0xFFAAAAAA);
					}

					@Override
					public boolean onCreateActionMode(ActionMode mode, Menu menu) {
						// TODO Auto-generated method stub
						mode.getMenuInflater().inflate(
								R.menu.crime_photo_context, menu);
						mImageView.setBackgroundColor(0xFFF0FFFF);
						return true;
					}

					@Override
					public boolean onActionItemClicked(ActionMode mode,
							MenuItem item) {
						// TODO Auto-generated method stub
						switch (item.getItemId()) {
						case R.id.menu_item_delete_crime_photo:
							Photo photo = mCrime.getPhoto();
							if (null != photo) {
								PictureUtils.cleanImageView(mImageView);
								getActivity().deleteFile(photo.getFilename());
								mCrime.setPhoto(null);
							}
							return true;

						default:
							return false;
						}
					}
				});
				return true;
			}
		});

		mSendReportButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT, getCrimeReport());
				intent.putExtra(Intent.EXTRA_SUBJECT,
						getString(R.string.crime_report_subject));
				intent = Intent.createChooser(intent,
						getString(R.string.send_report));
				if (intentIsSafe(intent)) {
					startActivity(intent);
				}
			}
		});

		mChooseSuspectButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK,
						ContactsContract.Contacts.CONTENT_URI);
				if (intentIsSafe(intent)) {
					startActivityForResult(intent, REQUEST_SUSPECT);
				}
			}
		});
		
		mCallSuspectButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456"));
				if (intentIsSafe(intent)) {
					startActivity(intent);
				}
			}
		});

		PackageManager packageManager = getActivity().getPackageManager();
		boolean hasCamera = packageManager
				.hasSystemFeature(PackageManager.FEATURE_CAMERA)
				|| packageManager
						.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)
				|| Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD
				|| Camera.getNumberOfCameras() > 0;
		if (!hasCamera) {
			mIbStartCamera.setEnabled(false);
		}

		if (!TextUtils.isEmpty(mCrime.getSuspect())) {
			mChooseSuspectButton.setText(mCrime.getSuspect());
		}
		return v;
	}

	private boolean intentIsSafe(Intent intent) {

		PackageManager pm = getActivity().getPackageManager();
		List<ResolveInfo> activities = pm.queryIntentActivities(intent, 0);
		return activities.size() > 0;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode != Activity.RESULT_OK) {
			return;
		}

		if (requestCode == REQUEST_DATE) {
			Date date = (Date) data
					.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			mCrime.setDate(date);
			mCallbacks.onCrimeUpdated(mCrime);
			updateDate();
		} else if (requestCode == REQUEST_PHOTO) {
			String filename = data.getExtras().getString(
					CrimeCameraFragment.EXTRA_PHOTO_FILENAME);
			if (null != filename) {
				Photo photo = mCrime.getPhoto();
				if (null != photo) {
					getActivity().deleteFile(photo.getFilename());
				}
				mCrime.setPhoto(new Photo(filename));
				mCallbacks.onCrimeUpdated(mCrime);
				showPhoto();
			}
		} else if (requestCode == REQUEST_SUSPECT) {
			Uri uri = data.getData();
			Cursor cursor = getActivity().getContentResolver().query(uri,
					new String[] { ContactsContract.Contacts.DISPLAY_NAME },
					null, null, null);
			if (cursor.getCount() <= 0) {
				cursor.close();
				return;
			}

			cursor.moveToFirst();
			String suspect = cursor.getString(0);
			mCrime.setSuspect(suspect);
			mCallbacks.onCrimeUpdated(mCrime);
			mChooseSuspectButton.setText(suspect);
			cursor.close();
		}
	}

	private void updateDate() {
		mDateButton.setText(DateFormat.format("EEEE, MMMM dd, yyyy",
				mCrime.getDate()));
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_crime, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			if (NavUtils.getParentActivityName(getActivity()) != null) {
				NavUtils.navigateUpFromSameTask(getActivity());
			}
			return true;
		case R.id.menu_item_delete_crime:
			CrimeLab.get(getActivity()).deleteCrime(mCrime);
			getActivity().finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	private String getCrimeReport() {
		String slovedString = null;
		if (mCrime.isSolved()) {
			slovedString = getString(R.string.crime_report_solved);
		} else {
			slovedString = getString(R.string.crime_report_unsolved);
		}

		String dateFormat = "EEE, MMM dd";
		String dateString = DateFormat.format(dateFormat, mCrime.getDate())
				.toString();

		String suspectString = mCrime.getSuspect();
		if (TextUtils.isEmpty(suspectString)) {
			suspectString = getString(R.string.crime_report_no_suspect);
		} else {
			suspectString = getString(R.string.crime_report_suspect,
					suspectString);
		}

		String report = getString(R.string.crime_report, mCrime.getTitle(),
				slovedString, dateString, suspectString);
		return report;
	}
}
