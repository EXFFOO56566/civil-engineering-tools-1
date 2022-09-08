package com.tochycomputerservices.civilengtools;

import com.tochycomputerservices.civilengtools.Beamanalysis.LoadType;

import android.content.Context;
import android.widget.RelativeLayout;
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
public class CustomParams extends RelativeLayout.LayoutParams {
	
	public CustomParams(Context context) {
		super(RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		this.topMargin = (int) (GlobalProperties.beamTop
				- GlobalProperties.ptLoadHeight);

	}
	
	public CustomParams(Context context, int x, int y) {
		super(RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		this.topMargin = (int) y;
		this.leftMargin = (int) x;

	}

	public CustomParams(Context context, LoadType type) {
		super(RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		
		if(type == LoadType.POINT_LOAD) 
			this.topMargin = (int) (GlobalProperties.beamTop- GlobalProperties.ptLoadHeight);
		else if(type == LoadType.DISTRIBUTED_LOAD)
			this.topMargin = (int) (GlobalProperties.beamTop- GlobalProperties.distLoadHeight);

	}

	public void setLeftMargin(double pos) {
		this.leftMargin = (int) (GlobalProperties.convertFeetToPixels(pos, Beamanalysis.getLength()) - 0.5 * GlobalProperties.loadWidth);
	}
}
