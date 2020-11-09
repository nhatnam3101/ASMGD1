package com.example.asmgd1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmgd1.Model.model;
import com.example.asmgd1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<model> {
    TextView txtTitle,txtpubDate;
    ImageView imgV;
    Context context;
    ArrayList<model> ds;

    public NewsAdapter(@NonNull Context context, int resource, ArrayList<model> ds) {
        super(context, 0,ds);
        this.context=context;
        this.ds=ds;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view =convertView;

        if (view==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_news,null);
        }
        final model item = ds.get(position);
        if(item!=null){
            txtTitle = view.findViewById(R.id.tvTitle);
            txtTitle.setText(item.title);
            txtpubDate=view.findViewById(R.id.tvpuDate);
            txtpubDate.setText(item.pubDate);


            imgV = view.findViewById(R.id.imgV);
            Picasso.with(context).load(item.image).into(imgV);
        }


        return view;
    }
}
