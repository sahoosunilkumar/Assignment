package com.artoo.eventmanager.addevent.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.artoo.eventmanager.R;
import com.artoo.eventmanager.search.model.Event;
import com.artoo.eventmanager.search.view.MainActivity;
import com.artoo.eventmanager.utils.Utility;

public class AddEventActivity extends AppCompatActivity implements IAddEvent, View.OnClickListener {

    private static final int SUCCESS = 5001;
    private Event currentEvent;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private Calendar mCalendar = Calendar.getInstance();

    private EditText subjectET;
    private EditText attendeeET;
    private EditText descriptionET;
    private EditText addressET;
    private EditText timeET;
    private EditText dateET;
    private ImageView locationIV;
    private Button dateBtn;
    private Button timeBtn;
    private Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        init();
        currentEvent = getIntent().getParcelableExtra(MainActivity.KEY_EVENT_DETAIL);
        if (currentEvent == null) {
            currentEvent = new Event();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_date:
                showDatePicker();
                break;
            case R.id.btn_time:
                showTimePicker();
                break;
            case R.id.locate_iv:
                break;
            case R.id.btn_save:
                save();
                break;
        }
    }


    @Override
    public void showDatePicker() {


        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    dateET.setText(mYear + "/" + mMonth + "/" + mDay);
                    setEventTime();
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void showTimePicker() {
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute) -> {
                    setEventTime();
                    timeET.setText(mHour + ":" + minute);
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    @Override
    public void init() {
        subjectET = findViewById(R.id.subject_et);
        attendeeET = findViewById(R.id.attendee_et);
        descriptionET = findViewById(R.id.description_et);
        addressET = findViewById(R.id.address_et);
        dateBtn = findViewById(R.id.btn_date);
        dateBtn.setOnClickListener(this);
        timeBtn = findViewById(R.id.btn_time);
        timeBtn.setOnClickListener(this);
        saveBtn = findViewById(R.id.btn_save);
        saveBtn.setOnClickListener(this);
        locationIV = findViewById(R.id.locate_iv);
        locationIV.setOnClickListener(this);
        dateET = findViewById(R.id.date_et);
        timeET = findViewById(R.id.time_et);
    }

    private void setEventTime() {
        mCalendar.set(mYear, mMonth, mDay, mHour, mMinute);
        currentEvent.setStartTime(mCalendar.getTimeInMillis());
    }

    private void save() {

        currentEvent.setTitle(subjectET.getText().toString());
        currentEvent.setDescription(descriptionET.getText().toString());
        currentEvent.setStartTime(mCalendar.getTimeInMillis());
        currentEvent.setEndTime(mCalendar.getTimeInMillis() + 5 * 60 * 60);
        currentEvent.setAttendee(attendeeET.getText().toString());
        currentEvent.setLocation(Utility.convertToLocation(addressET.getText().toString()));
        Intent intent = new Intent();
        intent.putExtra(MainActivity.KEY_EVENT_DETAIL, currentEvent);
        setResult(SUCCESS, intent);
        finish();
    }
}
