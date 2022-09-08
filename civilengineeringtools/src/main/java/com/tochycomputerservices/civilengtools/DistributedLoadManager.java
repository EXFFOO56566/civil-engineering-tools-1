package com.tochycomputerservices.civilengtools;

import java.util.ArrayList;

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

public class DistributedLoadManager extends DistributedLoad {
	
	private ArrayList<DistributedLoad> dLoads = new ArrayList<DistributedLoad>();
	
	private static DistributedLoadManager instance = null;
	
	public static DistributedLoadManager getInstance() {
		if(instance == null) {
			instance = new DistributedLoadManager();
		}
		return instance;
	}
	
	protected DistributedLoadManager() {
	}

	public void addDistLoad(DistributedLoad dl, ImageView[] images, CustomParams[] params) {
		// TODO Auto-generated method stub

		Beamanalysis.rldl.addView(images[0], 2 * dLoads.size(), params[0]);
		Beamanalysis.rldl.addView(images[1], 2 * dLoads.size() + 1, params[1]);
		
		dLoads.add(dl);
		
		dl.setIndex(DistributedLoadManager.getInstance().getDistLoads()
				.size() - 1);

		Beamanalysis.distLoadLine.draw(Beamanalysis.distLoadCanvas);
	}
	
	public void deleteDistLoad(int pos) {
		dLoads.remove(pos);
		Beamanalysis.rldl.removeViews(2 * pos, 2);
		
		for(int i = 0; i < dLoads.size(); i++)
			dLoads.get(i).setIndex(i);

		Beamanalysis.distLoadLine.draw(Beamanalysis.distLoadCanvas);
	}
	
	public ArrayList<DistributedLoad> getDistLoads() {
		return dLoads;
	}

	public void setDistLoads(ArrayList<DistributedLoad> distLoads) {
		this.dLoads = distLoads;
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return dLoads.size();
	}

}