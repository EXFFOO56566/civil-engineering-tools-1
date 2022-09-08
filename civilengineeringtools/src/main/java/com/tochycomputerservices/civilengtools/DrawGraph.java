package com.tochycomputerservices.civilengtools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
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

public class DrawGraph extends View {

	private Paint graphPaint = new Paint();
	private Paint circlePaint = new Paint();
	private Paint circlePaintOut = new Paint();
	Context con;
	Bitmap bitmap;

	public DrawGraph(Context context) {
		super(context);
		con = context;
		graphPaint.setColor(Color.BLUE);
		circlePaint.setColor(Color.RED);
		circlePaintOut.setColor(Color.RED);
		circlePaint.setStyle(Style.FILL);
		circlePaintOut.setStyle(Style.STROKE);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		canvas.save();

		if (Beamanalysis.graphMode == Beamanalysis.SHEAR)
			canvas.drawLines(DiagramActivity.vLines, graphPaint);
		else if (Beamanalysis.graphMode == Beamanalysis.MOMENT)
			canvas.drawLines(DiagramActivity.mLines, graphPaint);

		canvas.restore();

	}

	public void stop() {
		
	}

}
