package com.tochycomputerservices.civilengtools;

import android.app.Activity;
import android.os.Bundle;

import com.example.gili.simplegraphlibrary.XYchart;
import com.example.gili.simplegraphlibrary.Point;
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

public class GraphActivity extends Activity  {

    private XYchart g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);


        g = (XYchart)findViewById(R.id.graph);

        float[] x = {5067, 2846};
        float[] y = {10000, 3047};
        g.setData(x,y);

        //g.clearData();
        g.addValue(-5300, 1400);

        float[] ax = {7326, 1624};
        float[] ay = {8273, 6253};
        g.addData(ax, ay);
        //g.removeValues(x,y);

    }

}
