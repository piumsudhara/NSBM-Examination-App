package com.example.piumsudhara.application;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        private int progressStatus = 0;
        private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("Home");
        DefaultFragment defaultFragment = new DefaultFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fram,defaultFragment, "DefaultFragment");
        fragmentTransaction.commit();

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
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_f1)
        {
            setTitle("Student Details");
            Fragment1 fragment = new Fragment1();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram,fragment,"Fragment2");
            fragmentTransaction.commit();
        }
        else if (id == R.id.nav_f2)
        {
            setTitle("Examination Results");
            Fragment2 fragment = new Fragment2();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram,fragment,"Fragment2");
            fragmentTransaction.commit();
        }
        else if (id == R.id.nav_f3)
        {
            setTitle("Exam TimeTable");
            Fragment3 fragment = new Fragment3();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram,fragment,"Fragment3");
            fragmentTransaction.commit();
        }
        else if (id == R.id.nav_f4)
        {
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainMenu.this,R.style.MyDialogTheme);
            alertDialogBuilder.setMessage("Are You Sure Want To Exit ?");
            alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int arg1)
                {
                    final ProgressDialog pd = new ProgressDialog(MainMenu.this);
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd.setMessage("Logging Out....");
                    pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2f3b4d")));
                    pd.setIndeterminate(false);
                    pd.show();
                    progressStatus = 0;

                    new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            while(progressStatus < 100)
                            {
                                progressStatus +=1;
                                try
                                {
                                    Thread.sleep(50);
                                }
                                catch(InterruptedException e)
                                {
                                    e.printStackTrace();
                                }
                                handler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        pd.setProgress(progressStatus);
                                        if(progressStatus == 40)
                                        {
                                            pd.dismiss();
                                            Intent in = new Intent(MainMenu.this,Login.class);
                                            startActivity(in);
                                            finish();
                                        }
                                    }
                                });
                            }
                        }
                    }).start();
                }
            });

            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
