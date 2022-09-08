package com.tochycomputerservices.civilengtools;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
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
public class PointLoad implements Parcelable {

	private String magnitude, position;
	private static ImageView image;
	private int index;

	public PointLoad() {
		this.position = null;
		this.magnitude = null;
		image = null;
	}

	public PointLoad(String magnitude, String position, Context context) {
		this.magnitude = magnitude;
		this.position = position;
		
		image = new ImageView(context);
		image.setImageBitmap(GlobalProperties.resizedPt);
		image.setOnTouchListener(new LoadOnTouchListener(this, context));
	
	}

	public final double getPosition() {
		return Double.parseDouble(position);
	}

	public final double getMagnitude() {
		return Double.parseDouble(magnitude);
	}

	public ImageView getImage() {
		return image;
	}

	public void setPosition(double pos) {
		position = Double.toString(pos);
	}

	public void setMagnitude(double mag) {
		magnitude = Double.toString(mag);
	}
	
	public final int getIndex() {
		return index;
	}
	
	public void setIndex(int i) {
		index = i;
	}
	
	private PointLoad(Parcel in) {
		magnitude = in.readString();
		position = in.readString();
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		// TODO Auto-generated method stub
		out.writeString(magnitude);
		out.writeString(position);
	}
	
	public static final Creator<PointLoad> CREATOR = new Creator<PointLoad>() {
		public PointLoad createFromParcel(Parcel in) {
			return new PointLoad(in);
		}
		
		public PointLoad[] newArray(int size) {
			return new PointLoad[size];
		}
	};
	
}
