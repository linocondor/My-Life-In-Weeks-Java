package com.linocondor.mylifeinweeksjava;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText ageEditText;
    private Button calculateButton;
    private Button dateText;
    private TextView date;
    private int year;
    private int month;
    private int dayOfMonth;
    private TextView yourAge;
    private TextView timeLeftText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);



        callViewId();

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDate();

            }
        });





    }

    private void calculateTimeLeft(int year, int month, int dayOfMonth) {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                int actual_year = calendar.get(Calendar.YEAR);

                String wished_age_string = ageEditText.getText().toString();
                int wished_age_int = Integer.parseInt(wished_age_string);

                int number_of_wished_days = 365 * wished_age_int;
                int number_of_wished_months = 12 * wished_age_int;
                int number_of_wished_weeks = 53 * wished_age_int;

                int number_of_years_now = actual_year - year-1;

                int number_of_days_left = number_of_wished_days - ((365* number_of_years_now) + (31 * month) + dayOfMonth);
                int number_of_months_left = number_of_wished_months - ((12 * number_of_years_now) + month );
                int number_of_weeks_left = number_of_wished_weeks - ((53 * number_of_years_now) + (4 * month) + Math.round(dayOfMonth/7));

                String timeLeftTextConverted = String.valueOf("Days: " + number_of_days_left + ", Weeks: " + number_of_weeks_left + ", Months: " + number_of_months_left);

                timeLeftText.setText(timeLeftTextConverted);







            }
        });
    }

    private void calculateYourAge(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        int actual_year = calendar.get(Calendar.YEAR);
        int actual_month = calendar.get(Calendar.MONTH);
        int actual_dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        int age_years = actual_year - year-1;
        int age_month = month;

        String year_string = String.valueOf( age_years +" Years, " + month + " Months, "+ dayOfMonth +" Days");

        yourAge.setText(year_string);


    }

    private void pickDate() {


        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //date.setText(year + " " + (month + 1)  + " " + dayOfMonth + " ");
                calculateYourAge(year, month + 1 , dayOfMonth);
                calculateTimeLeft(year, month + 1 , dayOfMonth);

            }

        }, year, month, dayOfMonth);
        //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();


    }

    //Method to call id
    private void callViewId() {

        //date = findViewById(R.id.show_date);
        ageEditText = findViewById(R.id.select_age);
        calculateButton = findViewById(R.id.calculate);
        dateText = findViewById(R.id.select_date);
        yourAge = findViewById(R.id.your_age);
        timeLeftText = findViewById(R.id.time_left_calculated);

    }


}