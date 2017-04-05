package com.example.learningandroid;

import android.content.Intent;
import android.os.BatteryManager;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ### on 23/03/2017.
 */
public class BatteryStatus {
  public final int level;
  public final int maxValue;
  public final int batteryStatus;
  public final int batteryHealth;
  public final int batteryPlugged;
  public final String batteryTech;
  public final float batteryVoltage;
  public final boolean battery;
  public final float batteryTemp;
  public final int chargedPct;

  public BatteryStatus(Intent intent){
    level = intent.getIntExtra("level", -1);
    maxValue = intent.getIntExtra("scale", -1);
    chargedPct = (level * 100) / maxValue;
    batteryStatus = intent.getIntExtra("status", -1);
    batteryHealth = intent.getIntExtra("health", -1);
    batteryPlugged = intent.getIntExtra("plugged", -1);
    batteryTech = intent.getStringExtra("technology");
    batteryVoltage = (float) intent.getIntExtra("voltage", -1) / 1000;
    battery = intent.getBooleanExtra("present", false);
    batteryTemp = (float) intent.getIntExtra("temperature", -1) / 10;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return  "Battery Info:\n" +
            "Health=" + healthValueMap.get(batteryHealth) + "\n" +
            "Status=" + statusValueMap.get(batteryStatus) + "\n" +
            "Charged % = " + chargedPct + "%\n" +
            "Plugged = " + pluggedValueMap.get(batteryPlugged) + "\n" +
            "Type = " + batteryTech + "\n" +
            "Voltage = " + batteryVoltage + " volts\n" +
            "Temperature = " + batteryTemp + "\n" +
            "Battery present = " + battery + "\n";
  }
  private static final SparseArray<String> healthValueMap = new SparseArray<String>() {
    {
      put(BatteryManager.BATTERY_HEALTH_DEAD, "Dead");
      put(BatteryManager.BATTERY_HEALTH_GOOD, "Good");
      put(BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE, "Over voltage");
      put(BatteryManager.BATTERY_HEALTH_OVERHEAT, "Over heating");
      put(BatteryManager.BATTERY_HEALTH_UNKNOWN, "Unknown");
      put(BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE, "Failure, but unknown");
      put(-1, "Not Reported");
    }
  };

  private static final SparseArray<String> statusValueMap = new SparseArray<String>() {
    {
      put(BatteryManager.BATTERY_STATUS_CHARGING, "Charging");
      put(BatteryManager.BATTERY_STATUS_DISCHARGING, "Discharging");
      put(BatteryManager.BATTERY_STATUS_FULL, "Full");
      put(BatteryManager.BATTERY_STATUS_NOT_CHARGING, "Not Charging");
      put(BatteryManager.BATTERY_STATUS_UNKNOWN, "Unknown");
      put(-1, "Not Reported");
    }
  };

  private static final SparseArray<String> pluggedValueMap = new SparseArray<String>() {
    {
      put(BatteryManager.BATTERY_PLUGGED_AC, "On AC");
      put(BatteryManager.BATTERY_PLUGGED_USB, "On USB");
      put(-1, "Not Reported");
    }
  };

}
