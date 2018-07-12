package com.example.fullmetal.internityapp3;

public class Sessions {

    String title_session, details_session, time_session, date_session;

    public Sessions(String title_session, String details_session, String time_session, String date_session) {
        this.title_session = title_session;
        this.details_session = details_session;
        this.time_session = time_session;
        this.date_session = date_session;
    }

    public String getTitle_session() {
        return title_session;
    }

    public String getDetails_session() {
        return details_session;
    }

    public String getTime_session() {
        return time_session;
    }

    public String getDate_session() {
        return date_session;
    }
}
