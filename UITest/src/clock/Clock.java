package clock;

import farm.objects.Crop;
import farm.ui.controllers.PlotUIController;
import farmer.Farmer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Clock {

    private Timeline hoursCount = new Timeline(new KeyFrame(Duration.seconds(2),
        event -> hourLabel()));

    private Farmer farmer;
    private PlotUIController plotu;
    private Group[][] plotArray;
    private Label dayLabel;
    private Label hourLabel;
    private Label ampmLabel;
    private int day;
    private int hour;

    public Clock(Farmer farmer, PlotUIController plotu, Label dayLabel,
                 Label hourLabel, Label ampmLabel, int day, int hour) {
        this.farmer = farmer;
        this.plotu = plotu;
        this.plotArray = plotu.getPlotArray();
        this.dayLabel = dayLabel;
        this.hourLabel = hourLabel;
        this.ampmLabel = ampmLabel;
        this.day = day;
        this.hour = hour;
        dayLabel.setText(String.format("%02d", day));
        hoursCount.setCycleCount(Timeline.INDEFINITE);
        hoursCount.play();
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public void hourLabel() {
        if (hour > -1) {
            hour++;
        }
        hour = hour % 24;
        if (hour == 0) {
            day++;
            advanceGrowthCycle();
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

    public void advanceGrowthCycle() {
        for (int i = 0; i < plotArray.length; i++) {
            for (int j = 0; j < plotArray[i].length; j++) {
                Crop crop = farmer.getField().getPlot(i, j).getCrop();
                if (crop != null) {
                    crop.grow();
                    crop.setWaterLevel(crop.getWaterLevel() - 2);
                }
                String text = (crop == null) ? "This plot is empty.\n\n" : crop.toString();
                ((Label) plotArray[i][j].getChildren().get(3)).setText(text);
                plotu.updateRightPaneInspect(crop);
            }
        }
    }
}
