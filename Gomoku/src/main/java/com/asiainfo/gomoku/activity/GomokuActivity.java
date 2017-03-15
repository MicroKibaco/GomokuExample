package com.asiainfo.gomoku.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.asiainfo.gomoku.R;
import com.asiainfo.gomoku.view.GomokuPannel;

public class GomokuActivity extends Activity {


    private GomokuPannel mGomokuPannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gomoku);

        mGomokuPannel = (GomokuPannel) findViewById(R.id.my_gomoku);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.my_gomoku:
                mGomokuPannel.reStart();
                break;

            default:
                break;

        }

        return true;
    }
}
