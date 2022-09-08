package com.tochycomputerservices.civilengtools;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
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

public class DrawDistLoad extends View {

	private Paint paint = new Paint();
	Context con;
	private ArrayList<DistributedLoad> loads;
	private double startPositions, endPositions;

	public DrawDistLoad(Context context) {
		super(context);
		con = context;
		paint.setColor(Color.rgb(0, 128, 0));
		loads = new ArrayList<DistributedLoad>();
		startPositions = 0;
		endPositions = 0;
	}

	public void update() {

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		loads = DistributedLoadManager.getInstance().getDistLoads();

		for (int i = 0; i < loads.size(); i++) {
			startPositions = loads.get(i).getStartingPosition();
			endPositions = loads.get(i).getEndingPosition();

			double startX = GlobalProperties.convertFeetToPixels(
					startPositions, Beamanalysis.getLength());
			double endX = GlobalProperties.convertFeetToPixels(
					endPositions, Beamanalysis.getLength());

			canvas.drawLine(
					(float) startX,
					(float) (GlobalProperties.beamTop - GlobalProperties.distLoadHeight),
					(float) endX,
					(float) (GlobalProperties.beamTop - GlobalProperties.distLoadHeight),
					paint);

			for (double j = startX; j <= endX; j += 0.5 * GlobalProperties.loadWidth) {
				canvas.drawLine(
						(float) j,
						(float) (GlobalProperties.beamTop - GlobalProperties.distLoadHeight),
						(float) j, (float) GlobalProperties.beamTop, paint);
			}
		}
		
		//this.invalidate();

	}

	public void stop() {

	}

}