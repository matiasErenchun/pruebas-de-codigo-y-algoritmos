package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        /*String pattern = "MM/dd/yyyy HH:mm:ss";

// Create an instance of SimpleDateFormat used for formatting
// the string representation of date according to the chosen pattern
        DateFormat df = new SimpleDateFormat(pattern);

// Get the today date using Calendar object.
        Date today = Calendar.getInstance().getTime();
// Using DateFormat format method we can create a string
// representation of a date with the defined format.
        String todayAsString = df.format(today);

// Print the result!
        System.out.println("Today is: " + todayAsString);

*/
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String data = "2015-09-20";
        try {
            Date date = formatter.parse(data);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
