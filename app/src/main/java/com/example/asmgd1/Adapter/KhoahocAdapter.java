package com.example.asmgd1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmgd1.DAO.KhoahocDao;
import com.example.asmgd1.Model.Khoahoc;
import com.example.asmgd1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.asmgd1.CourseFragment.rcv_kh;

public class KhoahocAdapter extends RecyclerView.Adapter<KhoahocAdapter.TheLoaiViewHolder> {
    private ArrayList<Khoahoc> list_kh;
    KhoahocDao khoahocDao;
    KhoahocAdapter khoahocAdapter;
    Context context;
    public KhoahocAdapter( Context context,ArrayList<Khoahoc> list_kh){
        this.context=context;
        this.list_kh=list_kh;

    }
    private void dialogsua(final Khoahoc kh){
        final EditText edtMakh,edtTenkh,edtDateSta,edtDateEn;
        Button btnUpdate,btnHuy,btnSta1, btnEn1;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_updatekh,null);
        builder.setView(view);

        edtMakh=view.findViewById(R.id.edtMakh);
        edtTenkh= view.findViewById(R.id.edtTenkh);
        edtDateSta= view.findViewById(R.id.edtStart);
        btnUpdate= view.findViewById(R.id.btnUpdate);
        edtDateEn = view.findViewById(R.id.edtEnd);
        btnHuy = view.findViewById(R.id.btnHuy);
        btnSta1= view.findViewById(R.id.btnStartU);
        btnEn1= view.findViewById(R.id.btnStartU);
        edtMakh.setText(kh.Makh);
        edtMakh.setEnabled(false);
        edtTenkh.setText(kh.Tenkh);
        edtDateSta.setText(kh.startt);
        edtDateEn.setText(kh.endt);
        final AlertDialog dialog = builder.create();
        dialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khoahocDao= new KhoahocDao(context);
                String makh = kh.Makh;
                String tenkh = edtTenkh.getText().toString();
                String startt = edtDateSta.getText().toString();
                String endt = edtDateEn.getText().toString();

                khoahocDao.update( new Khoahoc(makh,tenkh,startt,endt));
                list_kh = khoahocDao.getAllKhoaHoc(context);
                khoahocAdapter = new KhoahocAdapter(context,list_kh);
                rcv_kh.setAdapter(khoahocAdapter);
                dialog.cancel();
                Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
            }
        });
        btnSta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date today = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(today);
                final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                final int months = cal.get(Calendar.MONTH);
                final int years = cal.get(Calendar.YEAR);
                final Calendar calendar = Calendar.getInstance();
                int date = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(i,i1,i2);
                        edtDateSta.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },years,months,dayOfWeek);
                datePickerDialog.show();
            }
        });
        btnEn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date today = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(today);

                final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                final int months = cal.get(Calendar.MONTH);
                final int years = cal.get(Calendar.YEAR);
                final Calendar calendar = Calendar.getInstance();
                int date = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(i,i1,i2);
                        edtDateEn.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },years,months,dayOfWeek);
                datePickerDialog.show();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }
    @NonNull
    @Override
    public TheLoaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View view= inflater.inflate(R.layout.item_khoahoc,parent,false);
        TheLoaiViewHolder holder = new TheLoaiViewHolder (view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiViewHolder holder, final int position) {
        holder.tv_Makh.setText(list_kh.get(position).Makh);
        holder.tv_tenkh.setText(list_kh.get(position).Tenkh);
        holder.tvdate_sta.setText(list_kh.get(position).startt);
        holder.tvdate_end.setText(list_kh.get(position).endt);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogsua(list_kh.get(position));
            }
        });
        holder.imgUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText edtMakh,edtTenkh,edtDateSta,edtDateEn;
                Button btnUpdate,btnHuy;
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_updatekh,null);
                builder.setView(view);

                edtMakh=view.findViewById(R.id.edtMakh);
                edtTenkh= view.findViewById(R.id.edtTenkh);
                edtDateSta= view.findViewById(R.id.edtStart);
                btnUpdate= view.findViewById(R.id.btnUpdate);
                edtDateEn = view.findViewById(R.id.edtEnd);
                btnHuy = view.findViewById(R.id.btnHuy);
                edtMakh.setText(list_kh.get(position).Makh);
                edtMakh.setEnabled(false);
                edtTenkh.setText(list_kh.get(position).Tenkh);
                edtDateSta.setText(list_kh.get(position).startt);
                edtDateEn.setText(list_kh.get(position).endt);
                final AlertDialog dialog = builder.create();
                dialog.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        khoahocDao= new KhoahocDao(context);
                        String makh = list_kh.get(position).Makh;
                        String tenkh = edtTenkh.getText().toString();
                        String startt = edtDateSta.getText().toString();
                        String endt = edtDateEn.getText().toString();

                        khoahocDao.update( new Khoahoc(makh,tenkh,startt,endt));
                        list_kh = khoahocDao.getAllKhoaHoc(context);
                        khoahocAdapter = new KhoahocAdapter(context,list_kh);
                        rcv_kh.setAdapter(khoahocAdapter);
                        dialog.cancel();
                        Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
            }
        });

        holder.imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("delete");
                builder.setMessage("Ban co muon xoa khong?");
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new KhoahocDao(context).delete(list_kh.get(position).Makh);
                                capnhat();
                                dialog.cancel();
                            }
                        });
                builder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                builder.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list_kh.size();
    }

    public class  TheLoaiViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView tv_Makh,tv_tenkh,tvdate_sta,tvdate_end;
        ImageView imgkh,imgclose,imgUP;

        public TheLoaiViewHolder(View view){
            super(view);
            imgclose=view.findViewById(R.id.imgclose);
            imgkh=view.findViewById(R.id.imgkh);
            tv_Makh= view.findViewById(R.id.tv_Makh);
            tv_tenkh= view.findViewById(R.id.tv_Tenkh);
            tvdate_sta= view.findViewById(R.id.tv_Sta);
            tvdate_end= view.findViewById(R.id.tv_End);
            cardView=view.findViewById(R.id.itemKhoahoc);
            imgUP=view.findViewById(R.id.imgUP);


        }
    }
    public void capnhat(){
        list_kh =new ArrayList<>();
        list_kh = KhoahocDao.getAllKhoaHoc(context);
        khoahocAdapter = new KhoahocAdapter(context,list_kh);
        rcv_kh.setAdapter(khoahocAdapter);
    }
}