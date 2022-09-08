package com.tochycomputerservices.civilengtools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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

public class DrawBeam extends View {

	private final Paint flangesPaint, webPaint, supportPaint;

	public DrawBeam(Context context) {
		super(context);
		flangesPaint = new Paint();
		webPaint = new Paint();
		supportPaint = new Paint();

		setPaints();
	}

	private void setPaints() {
		flangesPaint.setColor(Color.rgb(85, 0, 13));
		webPaint.setColor(Color.rgb(174, 0, 27));
		supportPaint.setColor(Color.rgb(124, 124, 124));
		flangesPaint.setStyle(Paint.Style.FILL);
		webPaint.setStyle(Paint.Style.FILL);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		final float left = (float) GlobalProperties.beamLeft;
		final float top = (float) GlobalProperties.beamTop;
		final float right = (float) GlobalProperties.beamRight;
		final float bottom = (float) GlobalProperties.beamBottom;

		canvas.drawRect(left, top, right, top + 7, flangesPaint);
		canvas.drawRect(left, top + 7, right, bottom - 7, webPaint);
		canvas.drawRect(left, bottom - 7, right, bottom, flangesPaint);

		if (Beamanalysis.beamType == BeamOptionsActivity.SIMPLE_BEAM) {

			Path pin = new Path();

			pin.setFillType(Path.FillType.EVEN_ODD);
			pin.moveTo((float) (0.25 * left), bottom + 30);
			pin.lineTo(left, bottom);
			pin.lineTo((float) (1.75 * left), bottom + 30);
			pin.lineTo((float) (0.25 * left), bottom + 30);
			pin.close();

			canvas.drawPath(pin, supportPaint);

			canvas.drawCircle(right, bottom + 15, 15, supportPaint);

			this.invalidate();
		} else if(Beamanalysis.beamType == BeamOptionsActivity.CANTILEVER) {
			
			canvas.drawRect(0, (float)(GlobalProperties.beamTop - 30), (float)GlobalProperties.beamLeft, (float)(GlobalProperties.beamBottom + 30), supportPaint);
			this.invalidate();
			
		}

	}

	public void stop() {

	}

}