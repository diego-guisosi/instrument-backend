package br.com.b3.instrument.backend.data.json.model;

import java.util.Objects;

public class SessionDateJSON {

    private String currentDate;

    private String dateFormat;

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionDateJSON that = (SessionDateJSON) o;
        return Objects.equals(currentDate, that.currentDate) &&
                Objects.equals(dateFormat, that.dateFormat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentDate, dateFormat);
    }

    @Override
    public String toString() {
        return "SessionDateJSON{" +
                "currentDate='" + currentDate + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                '}';
    }

}
