package com.example.asmgd1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.asmgd1.Adapter.ViewpagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    Toolbar toolbar;
    ViewPager viewPager;
    public  static  String anh ,ten;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= findViewById(R.id.tobar);
        viewPager = findViewById(R.id.vpg);
        isPermissionGranted();
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorOrange));
        setUpViewPager();
        Bundle in = getIntent().getExtras();
       anh= in.getString("profile_pic");
       ten = in.getString("name");
//        if (savedInstanceState == null){
//            HomeFragment homeFragment = new HomeFragment();
//            FragmentManager manager=getSupportFragmentManager();
//                            manager.beginTransaction()
//                                    .replace(R.id.vpg,homeFragment)
//                                    .commit();
//
//        }


        bnv=findViewById(R.id.bottom_nv);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.Course:
                        Toast.makeText(MainActivity.this, "Ban chon menu Course", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.News:
                        Toast.makeText(MainActivity.this, "Ban chon menu News", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.Social:
                        Toast.makeText(MainActivity.this, "Ban chon menu Social", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.Maps:
                        Toast.makeText(MainActivity.this, "Ban chon menu Maps", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(3);
                        break;
                }
              return true;

            }
        });
    }
        public void setUpViewPager(){
            ViewpagerAdapter viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            viewPager.setAdapter(viewpagerAdapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                        switch (position){
                            case 0:
                                bnv.getMenu().findItem(R.id.Course).setChecked(true);
                                break;
                            case 1:
                                bnv.getMenu().findItem(R.id.News).setChecked(true);
                                break;
                            case 2:
                                bnv.getMenu().findItem(R.id.Social).setChecked(true);
                                break;
                            case 3:
                                bnv.getMenu().findItem(R.id.Maps).setChecked(true);
                                break;
                        }
                }
                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }

    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED
                    && this.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 2);
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {




        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            //do your specific task after read phone state granted
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
        return;
    }
}