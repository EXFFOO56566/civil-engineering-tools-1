package com.tochycomputerservices.civilengtools;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tochycomputerservices.civilengtools.data.ASADBContract;
/*
 *  Author: Eze-Odikwa Tochukwu
 *  Last date modified: 05-03-2022
 *  (C), All rights reserved, Tochy computer services 2022
 *
 * */
public class AsaActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ASADB_LOADER = 0;
    private int backButtonCount = 0;
    ASADBCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asa);
        setTitle(R.string.graph2);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AsaActivity.this, DataEntryActivity.class);
                startActivity(intent);
            }
        });

        ListView listView = (ListView) findViewById(R.id.list);

        View emptyView = findViewById(R.id.emptyView);
        listView.setEmptyView(emptyView);

        mCursorAdapter = new ASADBCursorAdapter(this, null);
        listView.setAdapter(mCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent resultIntent = new Intent(getApplicationContext(), ResultActivity.class);
                resultIntent.setData(ContentUris.withAppendedId(ASADBContract.ASADBEntry.CONTENT_URI, id));
                startActivity(resultIntent);
            }
        });

        getLoaderManager().initLoader(ASADB_LOADER, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to delete all data? this will clear all saved data.");
                builder.setTitle("Deleting all data?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getContentResolver().delete(ASADBContract.ASADBEntry.CONTENT_URI, null, null);
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       return;
                    }
                });
                builder.create().show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection ={
                ASADBContract.ASADBEntry._ID,
                ASADBContract.ASADBEntry.COLUMN_ASADATA_TITLE,
                ASADBContract.ASADBEntry.COLUMN_ASADATA_DESC
        };
        return new CursorLoader(this,
                ASADBContract.ASADBEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

    // Recoded to return to mainscreen

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
        finish();
    }

   // @Override
    //public void onBackPressed()
   // {
       // if(backButtonCount >= 1)
        //{
          // Intent intent = new Intent(Intent.ACTION_MAIN);
          // intent.addCategory(Intent.CATEGORY_HOME);
           // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           // startActivity(intent);
      //  }
       // else
        //{
        //   Toast.makeText(this, "Press the back button once again to close this window.", Toast.LENGTH_SHORT).show();
        //    backButtonCount++;
       // }
    //}
}
