package com.example.deividas.personaltrainer_dissertation_15085480;

public class ProgressList {
    private int id;
    private String nameSurname;
    private String meals;
    private String workouts;
    private String steps;

    public ProgressList(int id, String nameSurname, String meals, String workouts, String steps) {
        this.id = id;
        this.nameSurname = nameSurname;
        this.meals = meals;
        this.workouts = workouts;
        this.steps = steps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getMeals() {
        return meals;
    }

    public void setMeals(String meals) {
        this.meals = meals;
    }

    public String getWorkouts() {
        return workouts;
    }

    public void setWorkouts(String workouts) {
        this.workouts = workouts;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
