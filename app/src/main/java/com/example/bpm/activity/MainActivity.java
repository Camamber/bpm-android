package com.example.bpm.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bpm.R;
import com.example.bpm.database.AppDatabase;
import com.example.bpm.fragment.AddFragment;
import com.example.bpm.fragment.MeasurementsFragment;
import com.example.bpm.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


/** Represents an MainActivity View and handle View Events.
 * @author Yehor Kaliuzhniy
 */
public class MainActivity extends AppCompatActivity {

    /** Called on view creation super function
     * init menu handlers
     * @author Yehor Kaliuzhniy
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new UserFragment()).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.bottomNavList:
                        selectedFragment = new MeasurementsFragment();
                        break;
                    case R.id.bottomNavAdd:
                        selectedFragment = new AddFragment();
                        break;
                    case R.id.bottomNavMe:
                        selectedFragment = new UserFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
                return true;
            }
        });
        AppDatabase.getAppDatabase(this);
    }

    /** Called when view destroying
     * @author Yehor Kaliuzhniy
     */
    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
