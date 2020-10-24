package clock;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Clock {
    //private Timeline animation = new Timeline(new KeyFrame(Duration.seconds(1),
    //       e -> timeLabel()));

    private Timeline animation = new Timeline(new KeyFrame(Duration.minutes(3),
        event -> timeLabel()));

    private Label label;
    private int day;

    public Clock(Label label, int day) {
        this.label = label;
        this.day = day;
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public void timeLabel() {
        if (day > 0) {
            day++;
        }
        label.setText("Day " + day);
    }

    public int getDay() {
        return day;
    }
}
