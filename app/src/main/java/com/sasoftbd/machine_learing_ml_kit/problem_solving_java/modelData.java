package com.sasoftbd.machine_learing_ml_kit.problem_solving_java;


public class modelData {
    public modelData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

