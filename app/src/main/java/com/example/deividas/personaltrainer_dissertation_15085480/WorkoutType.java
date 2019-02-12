package com.example.deividas.personaltrainer_dissertation_15085480;

class WorkoutType {

    private String title;
    private String wod;

    public WorkoutType(String title, String wod){
        this.title = title;
        this.wod = wod;
    }

    public WorkoutType() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWod() {
        return wod;
    }

    public void setWod(String wod) {
        this.wod = wod;
    }
}
