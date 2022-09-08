package com.tochycomputerservices.civilengtools;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;
/**
 * Copyright 2022 Tochycomputerservices

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

public class DistributedLoad implements Parcelable {

	private String start_magnitude, end_magnitude, start_position, end_position;
	private static ImageView startImage, endImage;
	
	private int index;
	
	final public static int BEGIN = 0;
	final public static int END = 1;

	public DistributedLoad() {
		start_magnitude = end_magnitude = start_position = end_position = null;
	}
	
	public DistributedLoad(String sMag, String eMag, String sPos, String ePos, Context context) {
		start_magnitude = sMag;
		end_magnitude = eMag;
		start_position = sPos;
		end_position = ePos;
		
		
		startImage = new ImageView(context);
		startImage.setImageBitmap(GlobalProperties.resizedDist);
		
		endImage = new ImageView(context);
		endImage.setImageBitmap(GlobalProperties.resizedDist);
		
		Log.d("hfksfjsa", Double.toString(GlobalProperties.distLoadHeight));
		
		startImage.setOnTouchListener(new LoadOnTouchListener(this, BEGIN, context));
		endImage.setOnTouchListener(new LoadOnTouchListener(this, END, context));
	}
	
	public double getStartingPosition() {
		return Double.parseDouble(start_position);
	}
	
	public double getEndingPosition() {
		return Double.parseDouble(end_position);
	}
	
	public double getStartingMagnitude() {
		return Double.parseDouble(start_magnitude);
	}
	
	public double getEndingMagnitude() {
		return Double.parseDouble(end_magnitude);
	}
	
	public ImageView[] getImages() {
		ImageView[] imageArray = {startImage, endImage} ;
		return imageArray;
	}
	
	public void setStartingPosition(double x) {
		start_position = Double.toString(x);
	}
	
	public void setEndingPosition(double x) {
		end_position = Double.toString(x);
	}
	
	public final int getIndex() {
		return index;
	}
	
	public void setIndex(int i) {
		index = i;
	}
	
	private DistributedLoad(Parcel in) {
		start_magnitude = in.readString();
		start_position = in.readString();
		end_magnitude = in.readString();
		end_position = in.readString();
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		// TODO Auto-generated method stub
		out.writeString(start_magnitude);
		out.writeString(start_position);
		out.writeString(end_magnitude);
		out.writeString(end_position);
	}
	
	public static final Creator<DistributedLoad> CREATOR = new Creator<DistributedLoad>() {
		public DistributedLoad createFromParcel(Parcel in) {
			return new DistributedLoad(in);
		}
		
		public DistributedLoad[] newArray(int size) {
			return new DistributedLoad[size];
		}
	};
	
}