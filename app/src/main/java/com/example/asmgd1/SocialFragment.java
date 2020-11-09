package com.example.asmgd1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;


public class SocialFragment extends Fragment {
    CallbackManager callbackManager;
   Toolbar toolbar;
   ImageView imgFb;
   TextView tv_Fb;
    public SocialFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_social, null);
        toolbar=view.findViewById(R.id.tobar);
        imgFb=view.findViewById(R.id.imhFb);
        tv_Fb=view.findViewById(R.id.tv_Fb);
        toolbar.setNavigationIcon(R.drawable.ic_user);
        Picasso.with(getContext()).load(MainActivity.anh).into(imgFb);
        tv_Fb.setText(MainActivity.ten);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Social");

        return view;
    }


    }

