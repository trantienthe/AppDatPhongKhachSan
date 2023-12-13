package com.example.hotel.Activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotel.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class OptionActivity extends AppCompatActivity {

    private TextView checkIn, checkOut;
    String roomName,price;
    private Spinner spinner;
    private String peopleType,picturePath;
    private Button btNext;
    private Calendar myCalendar;

    private CheckBox cbNguoiLon, cbTreEm, cbTreSoSinh;
    private ArrayList<String> selectedOptions = new ArrayList<>();
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);


        // Gán kết quả của findViewById cho editText1 và editText2
        checkIn = findViewById(R.id.dateStart);
        checkOut = findViewById(R.id.dateEnd);
        btNext = findViewById(R.id.btNext);
        cbNguoiLon = findViewById(R.id.cbNguoiLon);
        cbTreEm = findViewById(R.id.cbTreEm);
        cbTreSoSinh = findViewById(R.id.cbTreSoSinh);


        Intent i =getIntent();
        roomName = i.getStringExtra("roomName");
        price =i.getStringExtra("price");
        picturePath =i.getStringExtra("picture");

        myCalendar = Calendar.getInstance();
        updateLabel(myCalendar, checkIn);
        updateLabel(getNextDay(myCalendar), checkOut);

        DatePickerDialog.OnDateSetListener dateCheckIn = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(myCalendar, checkIn);
            }
        };

        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(OptionActivity.this, dateCheckIn, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DatePickerDialog.OnDateSetListener dateCheckOut = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(myCalendar, checkOut);
            }
        };

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(OptionActivity.this, dateCheckOut, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.arrayPeople, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                // Lấy dữ liệu của item được chọn
//                 peopleType = (String) parentView.getItemAtPosition(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                // Không có item nào được chọn
//            }
//        });


        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOptions.clear();
                if (cbNguoiLon.isChecked()) {
                    selectedOptions.add(cbNguoiLon.getText().toString());
                }
                if (cbTreEm.isChecked()) {
                    selectedOptions.add(cbTreEm.getText().toString());
                }
                if (cbTreSoSinh.isChecked()) {
                    selectedOptions.add(cbTreSoSinh.getText().toString());
                }

                Intent i = new Intent(OptionActivity.this, MainActivity2.class);
                i.putExtra("checkIn", checkIn.getText().toString());
                i.putExtra("checkOut",checkOut.getText().toString());
                i.putExtra("people",peopleType);
                i.putExtra("roomName",roomName);
                i.putExtra("price",price);
                i.putExtra("picturePath",picturePath);
                i.putExtra("selectedOptions", selectedOptions);
                startActivity(i);
            }
        });

    }

    private void updateLabel(Calendar myCalendar, TextView textView) {
        String format = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        textView.setText(sdf.format(myCalendar.getTime()));
    }

    private Calendar getNextDay(Calendar calendar) {
        Calendar nextDay = (Calendar) calendar.clone();
        nextDay.add(Calendar.DAY_OF_MONTH, 1);
        return nextDay;
    }
}