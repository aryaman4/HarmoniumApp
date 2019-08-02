package aryaman.harmonium;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class NavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnTouchListener {

    Field[] fields = R.raw.class.getFields();
    int[] ranked = new int[fields.length];
    private Map<Integer, Integer> noteMap = new HashMap<>();
    private int CURRENT_NOTE = 0;
    private MediaPlayer[] mp = new MediaPlayer[8];

    private int nameHash(String fileName) {
        String[] parts = fileName.split("_");
        int score = 0;
        if (parts[0].equals("high")) {
            score += 14;
        } else if (parts[0].equals("middle")) {
            score += 7;
        }

        score += Integer.parseInt(parts[1]);
        return score;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        for (Field field : fields) {
            int index = nameHash(field.getName()) - 1;
            try {
                ranked[index] = field.getInt(field);
                Log.d("rank", ""+field.getInt(field));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        Intent intent = getIntent();
        Log.d("Sent Scale", intent.getStringExtra("Message"));
        int scale = Integer.parseInt(intent.getStringExtra("Message")) - 1;
        Button saButton = (Button) findViewById(R.id.saButton);
        mp[0] = MediaPlayer.create(this, ranked[scale]);
        noteMap.put(R.id.saButton, 0);
        saButton.setOnTouchListener(this);

        Button reButton = (Button) findViewById(R.id.reButton);
        reButton.setOnTouchListener(this);
        mp[1] = MediaPlayer.create(this, ranked[scale + 1]);
        noteMap.put(R.id.reButton, 1);

        Button gaButton = (Button) findViewById(R.id.gaButton);
        mp[2] = MediaPlayer.create(this, ranked[scale + 2]);
        gaButton.setOnTouchListener(this);
        noteMap.put(R.id.gaButton, 2);

        Button maButton = (Button) findViewById(R.id.maButton);
        mp[3] = MediaPlayer.create(this, ranked[scale + 3]);
        maButton.setOnTouchListener(this);
        noteMap.put(R.id.maButton, 3);

        Button paButton = (Button) findViewById(R.id.paButton);
        paButton.setOnTouchListener(this);
        mp[4] = MediaPlayer.create(this, ranked[scale + 4]);
        noteMap.put(R.id.paButton, 4);

        Button dhaButton = (Button) findViewById(R.id.dhaButton);
        dhaButton.setOnTouchListener(this);
        mp[5] = MediaPlayer.create(this, ranked[scale + 5]);
        noteMap.put(R.id.dhaButton, 5);

        Button niButton = (Button) findViewById(R.id.niButton);
        niButton.setOnTouchListener(this);
        mp[6] = MediaPlayer.create(this, ranked[scale + 6]);
        noteMap.put(R.id.niButton, 6);

        Button hsaButton = (Button) findViewById(R.id.hsaButton);
        hsaButton.setOnTouchListener(this);
        mp[7] = MediaPlayer.create(this, ranked[scale + 7]);
        noteMap.put(R.id.hsaButton, 7);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction())
        {

            case MotionEvent.ACTION_DOWN:
            {
                mp[noteMap.get(v.getId())].setLooping(true);
                mp[noteMap.get(v.getId())].start();
            }

            break;
            case MotionEvent.ACTION_UP:
            {
                for (MediaPlayer m : mp) {
                    if (m.isPlaying()) {
                        m.pause();
                    }
                }
            }
            break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
