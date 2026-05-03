/*
 * Copyright (c) 2010-2026 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.electroluxappliance.internal.dto;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.google.gson.annotations.SerializedName;

/**
 * The {@link BuiltinOvenStateDTO} class defines the DTO for the Electrolux
 * Built-in ovens.
 *
 * @author Arne Steinmetz - Initial contribution
 */
@NonNullByDefault
public class BuiltinOvenStateDTO extends ApplianceStateDTO {

    private static final String NOT_READ_STRING = "DATA NOT READ FROM API";

    private Properties properties = new Properties();

    public Properties getProperties() {
        return properties;
    }

    public static class Properties {
        private Reported reported = new Reported();

        public Reported getReported() {
            return reported;
        }
    }

    public static class Reported {

        @SerializedName("doorState")
        private String doorState = NOT_READ_STRING;

        @SerializedName("connectivityState")
        private String connectivityState = NOT_READ_STRING;

        @SerializedName("foodProbeInsertionState")
        private String foodProbeInsertionState = NOT_READ_STRING;

        @SerializedName("waterTrayInsertionState")
        private String waterTrayInsertionState = NOT_READ_STRING;

        @SerializedName("waterTankEmpty")
        private String waterTankEmpty = NOT_READ_STRING;

        @SerializedName("processPhase")
        private String processPhase = NOT_READ_STRING;

        @SerializedName("program")
        private String program = NOT_READ_STRING;

        @SerializedName("remoteControl")
        private String remoteControl = NOT_READ_STRING;

        @SerializedName("applianceState")
        private String applianceState = NOT_READ_STRING;

        @SerializedName("runningTime")
        private int runningTime = 0;

        @SerializedName("startTime")
        private int startTime = 0;

        @SerializedName("targetDuration")
        private int targetDuration = 0;

        @SerializedName("cavityLight")
        private boolean cavityLight = false;

        @SerializedName("targetFoodProbeTemperatureC")
        private float targetFoodProbeTemperatureC = 0;

        @SerializedName("targetTemperatureC")
        private int targetTemperatureC = 0;

        @SerializedName("displayFoodProbeTemperatureC")
        private float displayFoodProbeTemperatureC = 0;

        @SerializedName("displayTemperatureC")
        private int displayTemperatureC = 0;

        @SerializedName("networkInterface")
        public NetworkInterface networkInterface = new NetworkInterface();

        @SerializedName("alerts")
        private ArrayList<Map<String, Object>> alerts = new ArrayList<Map<String, Object>>();

        public String getApplianceState() {
            return applianceState;
        }

        public String getConnectivityState() {
            return connectivityState;
        }

        public String getDoorState() {
            return doorState;
        }

        public String getFoodProbeInsertionState() {
            return foodProbeInsertionState;
        }

        public String getWaterTrayInsertionState() {
            return waterTrayInsertionState;
        }

        public String getWaterTankEmpty() {
            return waterTankEmpty;
        }

        public String getProcessPhase() {
            return processPhase;
        }

        public String getProgram() {
            return program;
        }

        public String getRemoteControl() {
            return remoteControl;
        }

        public int getRunningTime() {
            return runningTime;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getTargetDuration() {
            return targetDuration;
        }

        public boolean getCavityLight() {
            return cavityLight;
        }

        public float getTargetFoodProbeTemperatureC() {
            return targetFoodProbeTemperatureC;
        }

        public int getTargetTemperatureC() {
            return targetTemperatureC;
        }

        public float getDisplayFoodProbeTemperatureC() {
            return displayFoodProbeTemperatureC;
        }

        public int getDisplayTemperatureC() {
            return displayTemperatureC;
        }

        public NetworkInterface getNetworkInterface() {
            return networkInterface;
        }

        public ArrayList<Map<String, Object>> getAlerts() {
            return alerts;
        }
    }

    public static class ApplianceInfo {
        private String applianceType = "";

        public String getApplianceType() {
            return applianceType;
        }
    }

    public static class NetworkInterface {
        private String swVersion = "";
        private String linkQualityIndicator = "";
        private String otaState = "";
        private String niuSwUpdateCurrentDescription = "";
        private String swAncAndRevision = "";

        public String getSwVersion() {
            return swVersion;
        }

        public String getLinkQualityIndicator() {
            return linkQualityIndicator;
        }

        public String getOtaState() {
            return otaState;
        }

        public String getNiuSwUpdateCurrentDescription() {
            return niuSwUpdateCurrentDescription;
        }

        public String getSwAncAndRevision() {
            return swAncAndRevision;
        }
    }
}
