package com.example.emman.hidroapp.tasks;

import java.util.List;

import pojo.Sensor;

/**
 * Created by emman on 4/24/2016.
 */
public interface AsyncTaskSensorsCallback {

    void updateSensors(List<Sensor> items);
}
