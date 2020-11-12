package farm.objects;

public class Farmhand {
    int skillLevel;
    boolean isActive;
    int daysActive;
    double wage;

    public Farmhand() {
        this(0, 0, 0);
    }

    //skillLevel : 0 = amateur, 1 = expert
    public Farmhand(int skillLevel, int daysActive, int difficultyLevel) {
        this.skillLevel = skillLevel;
        this.daysActive = daysActive;

        if (this.daysActive > 0) {
            isActive = true;
        } else {
            isActive = false;
        }

        if (this.skillLevel == 0) {
            wage = 10;
        } else {
            wage = 20;
        }

        wage *= difficultyLevel;
    }

    public void incrementDaysActive(int days) {
        daysActive += days;

        isActive = (daysActive > 0);
    }

    public boolean isActive() {
        return isActive;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public int getDaysActive(){
        return daysActive;
    }

    public double getWage() {
        return wage;
    }



}
