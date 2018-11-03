package com.tracking.cartracking.barcode;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tracking.cartracking.Admin.Input_Emp;
import com.tracking.cartracking.Admin.homeFragment;
import com.tracking.cartracking.LoginActivity;
import com.tracking.cartracking.Model.EmpModelReg;
import com.tracking.cartracking.Model.employee;
import com.tracking.cartracking.Model.user;
import com.tracking.cartracking.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import info.androidhive.barcode.BarcodeReader;

public class ScanActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    BarcodeReader barcodeReader;
    String emailEmployee ="",emailAdmin= "", nameEmployee ="";
    Calendar myCalendar;
    EditText Time ;
    FirebaseDatabase database;
    DatabaseReference ref;
    DatePickerDialog.OnDateSetListener date1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);


        // get the barcode reader instance
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);
    }

    @Override
    public void onScanned(Barcode barcode) {

        // playing barcode reader beep sound
        barcodeReader.playBeep();


//        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getApplicationContext());
//        LayoutInflater inflater = (LayoutInflater)getSystemService( Context.LAYOUT_INFLATER_SERVICE );
//        database = FirebaseDatabase.getInstance();
//        ref = database.getReference();
//        View view = inflater.inflate(R.layout.dialoge_scan_emp, null);
//        alertDialog.setView(view);
//        final AlertDialog dialog = alertDialog.create();
//        dialog.show();
//        final AppCompatSpinner spinner = (AppCompatSpinner) findViewById(R.id.sp);
//        Getspinner(spinner);
//        final EditText Time=(EditText)view.findViewById(R.id.time);
//        getdate(Time);
//        final EditText carNo = view.findViewById(R.id.car_id);
//        carNo.setText(barcode.displayValue);
//
//        myCalendar = Calendar.getInstance();
//        date1 = new DatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                  int dayOfMonth) {
//                // TODO Auto-generated method stub
//                myCalendar.set(Calendar.YEAR, year);
//                myCalendar.set(Calendar.MONTH, monthOfYear);
//                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateLabel();
//            }
//        };
//
//
//
//
//
//        Button register=(Button)view.findViewById(R.id.register);
//        register.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (TextUtils.isEmpty(carNo.getText().toString())) {
//                            // الرسالة التى تظهر للمستخدم
//                            Toast.makeText(getApplicationContext(), "Enter Car No", Toast.LENGTH_SHORT).show();
//                            return;
//                        } else if (TextUtils.isEmpty(Time.getText().toString())) {
//                            Toast.makeText(getApplicationContext(), "Enter Time", Toast.LENGTH_SHORT).show();
//                            return;
//                        }  else  if (spinner.getSelectedItem().toString().equals("Select Status ...")){
//                            Toast.makeText(getApplicationContext(), "Select  status ", Toast.LENGTH_SHORT).show();}
//
//
//                            Query fireQuery = ref.child("employee").orderByChild("car_no").equalTo(carNo.getText().toString());
//                            fireQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                    // ازا غير موجود قم بتخزينه
//                                    if (dataSnapshot.getValue() == null) {
//                                        Toast.makeText(getApplicationContext(), "this employee no member ", Toast.LENGTH_LONG).show();
//                                    }else{
//
//                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                                            employee employee = snapshot.getValue(employee.class);
//                                             nameEmployee = employee.getName();
//                                             emailEmployee = employee.getEmail();
//                                             emailAdmin = employee.getAdmin();
//
//                                        }
//
//                                        // اوبجكت من نوع يوزر لتخزين بيانات الادمن الجديد
//                                        EmpModelReg user = new EmpModelReg();
//                                        user.setEmail(emailEmployee);
//                                        user.setCar_Num(carNo.getText().toString());
//                                        user.setAdmin(emailAdmin);
//                                        user.setName(nameEmployee);
//                                        if (spinner.getSelectedItem().toString().equals("Arrival")){
//                                        user.setArrival("Arrival" );}
//
//                                        else if (spinner.getSelectedItem().toString().equals("Departure")){
//                                        user.setDeparture("Departure");}
//
//
//                                        // حفظه ك اوبجكت في جدول اليوزر بالفيربيس
//                                        ref.child("Record").push().setValue(user);
//
//
//
//                                        // رسالة عند الانتهاء
//                                        Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
//
//                                    }
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//
//                                    // رساله خطأ
//                                    Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
//
//                                }
//                            });
//
//
//
//                    }
//                }
//        );
//







        // ticket details activity by passing barcode
       // Toast.makeText(getApplicationContext(), "Done "   , Toast.LENGTH_SHORT).show();
//        Fragment fragment = new homeFragment();
//
//        Bundle bundle = new Bundle();
//        bundle.putString("code", barcode.displayValue);
//
//        fragment.setArguments(bundle);
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add( R.id.frame2,fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();

        Intent intent = new Intent(ScanActivity.this, Input_Emp.class);
        intent.putExtra("code", barcode.displayValue);
        startActivity(intent);
    }

    @Override
    public void onScannedMultiple(List<Barcode> list) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String s) {
        Toast.makeText(getApplicationContext(), "Error occurred while scanning " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraPermissionDenied() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }




    private void updateLabel() {

        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        Time.setText(sdf.format(myCalendar.getTime()) + "");

    }
    public void getdate(final EditText editTextDate){

        final Calendar currentDate = Calendar.getInstance();
        final Calendar date = Calendar.getInstance();
        final DatePickerDialog datePickerDialog = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(getBaseContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        String myFormat = "dd-mm-yyyy HH:mm";
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        editTextDate.setText(sdf.format(date.getTime()));
                        // editTextDate.setText(new SimpleDateFormat("dd-MMM-yyyy h:mm").format(date.getTime()));
                    }
                },currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();

            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
        //   datePickerDialog.getDatePicker().setMinDate(currentDate.getTimeInMillis());
        editTextDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                datePickerDialog.show();
            }});
    }
    public void Getspinner(AppCompatSpinner spinner) {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.arrival_departure));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                ((TextView) view).setTextColor(Color.BLACK);
                if (item != null) {



                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






    }
}
