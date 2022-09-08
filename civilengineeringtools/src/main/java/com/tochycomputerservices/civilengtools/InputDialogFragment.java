package com.tochycomputerservices.civilengtools;

import com.tochycomputerservices.civilengtools.Beamanalysis.LoadType;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Copyright 2022 Eze-Odikwa Tochukwu jed
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 http://www.apache.org/licenses/LICENSE-2.0
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
public class InputDialogFragment extends DialogFragment {

	private LoadType type;
	private int n;
	private Context context;
	EditText inputName, inputType, inputSMag, inputEMag, inputSPos, inputEPos;

	public InputDialogFragment(Context context) {
		type = Beamanalysis.getLoadType();
		this.context = context;
	}

	/*
	 * The activity that creates an instance of this dialog fragment must
	 * implement this interface in order to receive event callbacks. Each method
	 * passes the DialogFragment in case the host needs to query it.
	 */
	public interface NoticeDialogListener {
		public void onDialogPositiveClick(DialogFragment dialog,
                                          String start_pos, String end_post, String start_mag,
                                          String end_pos);

		public void onDialogNegativeClick(DialogFragment dialog);
	}

	// Use this instance of the interface to deliver action events
	NoticeDialogListener mListener;

	// Override the Fragment.onAttach() method to instantiate the
	// NoticeDialogListener
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Verify that the host activity implements the callback interface
		try {
			// Instantiate the NoticeDialogListener so we can send events to the
			// host
			mListener = (NoticeDialogListener) activity;
		} catch (ClassCastException e) {
			// The activity doesn't implement the interface, throw exception
			throw new ClassCastException(activity.toString()
					+ " must implement NoticeDialogListener");
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();

		int layout[] = { R.layout.dialog_input_point_load,
				R.layout.dialog_input_dist_load };
		if (type == LoadType.POINT_LOAD) {
			n = 0;
		} else if (type == LoadType.DISTRIBUTED_LOAD) {
			n = 1;
		}

		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(inflater.inflate(layout[n], null))
				// Add action buttons
				.setPositiveButton("Continue",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {

								inputSMag = (EditText) ((Dialog) dialog)
										.findViewById(R.id.etStartMag);
								inputEMag = (EditText) ((Dialog) dialog)
										.findViewById(R.id.etEndMag);
								inputSPos = (EditText) ((Dialog) dialog)
										.findViewById(R.id.etStartPos);
								inputEPos = (EditText) ((Dialog) dialog)
										.findViewById(R.id.etEndPos);

								if (type == LoadType.POINT_LOAD) {
									if (inputSMag.getText().toString()
											.equals("")

											|| inputSPos.getText().toString()
													.equals("")) {
										Toast.makeText(context,
												"Missing some values.",
												Toast.LENGTH_SHORT).show();
									} else if (Double.parseDouble(inputSPos
											.getText().toString()) > Beamanalysis
											.getLength()
											|| Double.parseDouble(inputSPos
													.getText().toString()) < 0) {
										Toast.makeText(
												context,
												"Can't put a load on this air!",
												Toast.LENGTH_LONG).show();
									} else {
										mListener.onDialogPositiveClick(
												InputDialogFragment.this,
												inputSMag.getText().toString(),
												null, inputSPos.getText()
														.toString(), null);
									}
								} else if (type == LoadType.DISTRIBUTED_LOAD) {
									if (inputSMag.getText().toString()
											.equals("")
											|| inputEMag.getText().toString()
													.equals("")
											|| inputSPos.getText().toString()
													.equals("")
											|| inputEPos.getText().toString()
													.equals("")) {
										Toast.makeText(context,
												"Missing some values.",
												Toast.LENGTH_SHORT).show();
									} else if (Double.parseDouble(inputSPos
											.getText().toString()) > Double
											.parseDouble(inputEPos.getText()
													.toString())) {
										Toast.makeText(
												context,
												"Ending position cannot be less than the starting position.",
												Toast.LENGTH_LONG).show();
									} else if (Double.parseDouble(inputSPos
											.getText().toString()) > Beamanalysis
											.getLength()
											|| Double.parseDouble(inputEPos
													.getText().toString()) > Beamanalysis
													.getLength()
											|| Double.parseDouble(inputSPos
													.getText().toString()) < 0
											|| Double.parseDouble(inputEPos
													.getText().toString()) < 0 ) {
										Toast.makeText(
												context,
												"Can't put a load on thin air!",
												Toast.LENGTH_LONG).show();
									} else {
										mListener.onDialogPositiveClick(
												InputDialogFragment.this,
												inputSMag.getText().toString(),
												inputEMag.getText().toString(),
												inputSPos.getText().toString(),
												inputEPos.getText().toString());
									}
								}

							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								InputDialogFragment.this.getDialog().cancel();
							}
						});

		return builder.create();
	}

}