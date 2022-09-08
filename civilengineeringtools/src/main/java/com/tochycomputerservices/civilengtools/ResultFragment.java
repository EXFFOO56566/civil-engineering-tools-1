package com.tochycomputerservices.civilengtools;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

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
public class ResultFragment extends Fragment {

    private final String LOG_TAG = getClass().getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.result_fragment, container, false);
        try {
            JSONObject data = new JSONObject(getArguments().getString("data"));
            ((TextView) view.findViewById(R.id.fm)).setText("Fineness Modulus = " + String.format("%.2f", data.getDouble("FINENESS_MODULUS")));
            ((TextView) view.findViewById(R.id.d60)).setText("D60 = " + String.format("%.2f", data.getDouble("D60")));
            ((TextView) view.findViewById(R.id.d10)).setText("D10 = " + String.format("%.2f", data.getDouble("D10")));
            ((TextView) view.findViewById(R.id.uc)).setText("Uniform Coefficient = " + String.format("%.2f", data.getDouble("CU")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }




}
