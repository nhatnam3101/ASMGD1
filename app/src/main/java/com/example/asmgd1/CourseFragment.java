package com.example.asmgd1;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asmgd1.Adapter.KhoahocAdapter;
import com.example.asmgd1.DAO.KhoahocDao;
import com.example.asmgd1.Model.Khoahoc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class CourseFragment extends Fragment {
    Toolbar toolbar;
    public  static RecyclerView rcv_kh;
    KhoahocDao khoahocDao;
    KhoahocAdapter khoahocAdapter;
    ArrayList<Khoahoc> list_kh;

    FloatingActionButton fl_add;
    TextView tv_Makh,tv_Tenkh,tv_Sta,tv_End;
    public CourseFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_course, container, false);
        rcv_kh = view.findViewById(R.id.rcv_kh);
        fl_add=view.findViewById(R.id.fl_pl);
        toolbar=view.findViewById(R.id.tobar);
        toolbar.setNavigationIcon(R.drawable.ic_course);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Course");
        rcv_kh.setLayoutManager(new LinearLayoutManager(getContext()));
        list_kh= new ArrayList<>();
        khoahocDao= new KhoahocDao(getContext());
        list_kh = khoahocDao.getAllKhoaHoc(getContext());
        khoahocAdapter = new KhoahocAdapter(getContext(),list_kh);
        rcv_kh.setAdapter(khoahocAdapter);
       fl_add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final  Button btnSta1, btnEn1,btnThem,btnHuy;
               final EditText edtMakh,edtTenkh,edtEnd,edtStart;
               final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
               LayoutInflater layoutInflater= getLayoutInflater();
               View view = layoutInflater.inflate(R.layout.dialog_themkh,null);
               builder.setView(view);

               edtMakh=view.findViewById(R.id.edtMakh);
               edtTenkh=view.findViewById(R.id.edtTenkh);
               edtEnd=view.findViewById(R.id.edtEnd);
               edtStart=view.findViewById(R.id.edtStart);
               btnSta1= view.findViewById(R.id.btnStart);
               btnEn1= view.findViewById(R.id.btnEnd);
               btnThem= view.findViewById(R.id.btnUpdate);
               btnHuy = view.findViewById(R.id.btnHuy);


               final AlertDialog dialog = builder.create();
               dialog.show();
               btnThem.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       String tenkh =edtTenkh.getText().toString();
                       String startt =edtStart.getText().toString();
                       String endt =edtEnd.getText().toString();
                       new KhoahocDao(getContext()).insert(new Khoahoc(null,tenkh,startt,endt));
                       capnhat();
                       dialog.cancel();
                       Toast.makeText(getContext(), " Thêm thành công", Toast.LENGTH_SHORT).show();
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

                       DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),new DatePickerDialog.OnDateSetListener() {
                           @Override
                           public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                               SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                               calendar.set(i,i1,i2);
                               edtStart.setText(simpleDateFormat.format(calendar.getTime()));
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

                       DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),new DatePickerDialog.OnDateSetListener() {
                           @Override
                           public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                               SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                               calendar.set(i,i1,i2);
                               edtEnd.setText(simpleDateFormat.format(calendar.getTime()));
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
       });
        return view;
    }
    public  void  capnhat(){
        list_kh =new ArrayList<>();
        list_kh = KhoahocDao.getAllKhoaHoc(getContext());
        khoahocAdapter = new KhoahocAdapter(getContext(),list_kh);
        rcv_kh.setAdapter(khoahocAdapter);

    }
}