package com.tochycomputerservices.civilengtools;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
public class MainScreen extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		
		Button aashtob = (Button) findViewById(R.id.aashto);
        System.out.println("aashto button assigned");
        aashtob.setOnClickListener(new View.OnClickListener() {
        	
            @Override
            public void onClick(View v10){
            	startActivity(new Intent(MainScreen.this,AASHTO.class));
            }
        });

        Button uscsb = (Button) findViewById(R.id.uscs);
        System.out.println("uscs button assigned");
        uscsb.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View v20) {
                // TODO Auto-generated method stub
                startActivity(new Intent(MainScreen.this,USCS.class));
            }
        });
		
        Button isscsb = (Button) findViewById(R.id.isscs);
        System.out.println("isscs button assigned");
        isscsb.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View v30) {
                // TODO Auto-generated method stub
                startActivity(new Intent(MainScreen.this,ISSCS.class));
            }
        });
       
        Button Exit=(Button)findViewById(R.id.exit);
        System.out.println("exit button set");
        Exit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v40) {
                // TODO Auto-generated method stub
                finish();
                System.exit(0);
            }
        });
        
        Button about = (Button) findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View v302) {
                // TODO Auto-generated method stub
                startActivity(new Intent(MainScreen.this,About.class));
            }
        });

        Button methods = (Button) findViewById(R.id.methods);
        methods.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v80) {
                // TODO Auto-generated method stub
                startActivity(new Intent(MainScreen.this, Beamanalysis.class));
            }
        });

        Button formulars = (Button) findViewById(R.id.formulars);
        formulars.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v90) {
                // TODO Auto-generated method stub
                startActivity(new Intent(MainScreen.this, XyActivity.class));
            }
        });

        Button graph = (Button) findViewById(R.id.graph);
        graph.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v120) {
                // TODO Auto-generated method stub
                startActivity(new Intent(MainScreen.this,AsaActivity.class));
            }
        });

        Button pastquestions = (Button) findViewById(R.id.pastquestions);
        pastquestions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v130) {
                // TODO Auto-generated method stub
                startActivity(new Intent(MainScreen.this,PdfActivity.class));
            }
        });

        Button basicsoilmechanics = (Button) findViewById(R.id.basicsoilmechanics);
        basicsoilmechanics.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v80) {
                // TODO Auto-generated method stub
                startActivity(new Intent(MainScreen.this, NotesListActivity.class));
            }
        });


        Button results = (Button) findViewById(R.id.results);
        results.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v60) {
                // TODO Auto-generated method stub
                startActivity(new Intent(MainScreen.this,Calculatorloader.class));
            }
        });

	}

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainScreen.super.onBackPressed();
                        quit();
                    }
                }).create().show();
    }


    public void quit() {
        Intent start = new Intent(Intent.ACTION_MAIN);
        start.addCategory(Intent.CATEGORY_HOME);
        start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(start);
    }

  //  @Override
    //public void onBackPressed(){
    //    System.gc();
    //    System.exit(0);
   // }
}
