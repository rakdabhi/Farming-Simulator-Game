package farm.objects;

import farm.ui.controllers.PlotUIController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Clock {

    private Timeline hoursCount = new Timeline(new KeyFrame(Duration.seconds(10),
        event -> hourLabel()));


    private int day;
    private int hour;
    private Text hourWatch;
    private SimpleIntegerProperty dayWatch;
    private Text ampmWatch;

    public Clock(int day, int hour) {
        this.day = day;
        this.hour = hour;
        hoursCount.setCycleCount(Timeline.INDEFINITE);
        hoursCount.play();

        hourWatch = new Text(((Integer)hour).toString());
        dayWatch = new SimpleIntegerProperty(day);
        ampmWatch = new Text("AM");

    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public Text hourProperty() {
        return hourWatch;
    }

    public SimpleIntegerProperty dayProperty() {
        return dayWatch;
    }

    public Text amPmProperty() {
        return ampmWatch;
    }

    private void hourLabel() {
        if (hour > -2) {
            hour++;
        }
        hour = hour % 24;
        if (hour == 0) {
            day++;
            //advanceGrowthCycle();
            dayWatch.set(day);
            hourWatch.setText(String.format("%02d", 12));
            ampmWatch.setText("AM");
        } else if (hour < 12) {
            hourWatch.setText(String.format("%02d", hour));
            ampmWatch.setText("AM");
        } else if (hour == 12) {
            hourWatch.setText(String.format("%02d", hour));
            ampmWatch.setText("PM");
        } else {
            hourWatch.setText(String.format("%02d", hour % 12));
            ampmWatch.setText("PM");
        }
    }

    public void advanceDay() {
        hour = -1;
        hourLabel();
    }
}
