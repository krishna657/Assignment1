package com.example.assignment.meeshoassignment1.data;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by msk on 17/1/18.
 */

public class PullRequest {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
    private long id;
    private String title;
    private long number;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getFormatedUpdatedDate() {
        try {
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date = dateFormat.parse(updatedAt);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            String month = cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            String s =  day + " " + month + " " + year;
            return s;
        } catch (ParseException e) {
            return " --- ";
        }
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
