package clock;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Clock {

    private Timeline hoursCount = new Timeline(new KeyFrame(Duration.seconds(10),
        event -> hourLabel()));

    private Label dayLabel;
    private Label hourLabel;
    private Label ampmLabel;
    private int day;
    private int hour;

    public Clock(Label dayLabel, Label hourLabel, Label ampmLabel, int day, int hour) {
        this.dayLabel = dayLabel;
        this.hourLabel = hourLabel;
        this.ampmLabel = ampmLabel;
        this.day = day;
        this.hour = hour;
        dayLabel.setText(String.format("%02d", day));
        hoursCount.setCycleCount(Timeline.INDEFINITE);
        hoursCount.play();
    }

    public void hourLabel() {
        if (hour > -1) {
            hour++;
        }
        hour = hour % 24;
        if (hour == 0) {
            day++;
            dayLabel.setText(String.format("%02d", day));
            hourLabel.setText(String.format("%02d", 12));
            ampmLabel.setText("AM");
        } else if (hour < 12) {
            hourLabel.setText(String.format("%02d", hour));
            ampmLabel.setText("AM");
        } else if (hour == 12) {
            hourLabel.setText(String.format("%02d", hour));
            ampmLabel.setText("PM");
        } else {
            hourLabel.setText(String.format("%02d", hour % 12));
            ampmLabel.setText("PM");
        }

    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }
}
