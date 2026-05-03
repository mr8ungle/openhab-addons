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
package org.openhab.binding.electroluxappliance.internal;

import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.core.thing.ThingTypeUID;

/**
 * The {@link ElectroluxApplianceBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Jan Gustafsson - Initial contribution
 */
@NonNullByDefault
public class ElectroluxApplianceBindingConstants {

    public static final String BINDING_ID = "electroluxappliance";

    // List of all Thing Type UIDs
    public static final ThingTypeUID THING_TYPE_ELECTROLUX_AIR_PURIFIER = new ThingTypeUID(BINDING_ID, "air-purifier");
    public static final ThingTypeUID THING_TYPE_ELECTROLUX_WASHING_MACHINE = new ThingTypeUID(BINDING_ID,
            "washing-machine");
    public static final ThingTypeUID THING_TYPE_ELECTROLUX_BUILT_IN_OVEN = new ThingTypeUID(BINDING_ID,
            "built-in-oven");
    public static final ThingTypeUID THING_TYPE_ELECTROLUX_PORTABLE_AIR_CONDITIONER = new ThingTypeUID(BINDING_ID,
            "portable-air-conditioner");
    public static final ThingTypeUID THING_TYPE_BRIDGE = new ThingTypeUID(BINDING_ID, "api");

    // List of all common Channel ids
    public static final String CHANNEL_DOOR_STATE = "door-state";

    // List of all Channel ids for Air Purifers
    public static final String CHANNEL_STATUS = "status";
    public static final String CHANNEL_TEMPERATURE = "temperature";
    public static final String CHANNEL_HUMIDITY = "humidity";
    public static final String CHANNEL_TVOC = "tvoc";
    public static final String CHANNEL_PM1 = "pm1";
    public static final String CHANNEL_PM25 = "pm2_5";
    public static final String CHANNEL_PM10 = "pm10";
    public static final String CHANNEL_CO2 = "co2";
    public static final String CHANNEL_FILTER_LIFE = "filter-life";
    public static final String CHANNEL_FAN_SPEED = "fan-speed";
    public static final String CHANNEL_WORK_MODE = "work-mode";
    public static final String CHANNEL_IONIZER = "ionizer";
    public static final String CHANNEL_UI_LIGHT = "ui-light";
    public static final String CHANNEL_SAFETY_LOCK = "safety-lock";

    // List of all Channel ids for Washing Machines
    public static final String CHANNEL_DOOR_LOCK = "door-lock";
    public static final String CHANNEL_TIME_TO_START = "time-to-start";
    public static final String CHANNEL_TIME_TO_END = "time-to-end";
    public static final String CHANNEL_APPLIANCE_UI_SW_VERSION = "appliance-ui-sw-version";
    public static final String CHANNEL_APPLIANCE_TOTAL_WORKING_TIME = "appliance-total-working-time";
    public static final String CHANNEL_APPLIANCE_STATE = "appliance-state";
    public static final String CHANNEL_APPLIANCE_MODE = "appliance-mode";
    public static final String CHANNEL_OPTISENSE_RESULT = "optisense-result";
    public static final String CHANNEL_DETERGENT_EXTRA_DOSAGE = "detergent-extradosage";
    public static final String CHANNEL_SOFTENER_EXTRA_DOSAGE = "softener-extradosage";
    public static final String CHANNEL_WATER_USAGE = "water-usage";
    public static final String CHANNEL_CYCLE_PHASE = "cycle-phase";
    public static final String CHANNEL_TOTAL_WASH_CYCLES_COUNT = "total-wash-cycles-count";
    public static final String CHANNEL_ANALOG_TEMPERATURE = "analog-temperature";
    public static final String CHANNEL_ANALOG_SPIN_SPEED = "analog-spin-speed";
    public static final String CHANNEL_STEAM_VALUE = "steam-value";
    public static final String CHANNEL_PROGRAMS_ORDER = "programs-order";

    // List of all Channel Ids for Portable Air Conditioner
    public static final String CHANNEL_DEVICE_RUNNING = "appliance-running";
    public static final String CHANNEL_AMBIENT_TEMPERATURE = "ambient-temperature";
    public static final String CHANNEL_TARGET_TEMPERATURE = "target-temperature";
    public static final String CHANNEL_SLEEP_MODE = "sleep-mode";
    public static final String CHANNEL_FAN_SWING = "fan-swing";
    public static final String CHANNEL_CHILD_LOCK = "child-ui-lock";
    public static final String CHANNEL_FAN_MODE = "fan-mode";
    public static final String CHANNEL_MODE = "mode";
    public static final String CHANNEL_NETWORK_QUALITY_INDICATOR = "network-quality-indicator";
    public static final String CHANNEL_NETWORK_RSSI = "network-rssi";
    public static final String CHANNEL_COMPRESSOR_STATE = "compressor-state";
    public static final String CHANNEL_FOURWAY_VALVE_STATE = "fourway-valve-state";
    public static final String CHANNEL_EVAP_DEFROST_STATE = "evap-defrost-state";
    public static final String CHANNEL_OFF_TIMER_ACTIVE = "off-timer-active";
    public static final String CHANNEL_OFF_TIMER_DURATION = "off-timer-duration";
    public static final String CHANNEL_OFF_TIMER_TIME = "off-timer-time";
    public static final String CHANNEL_ON_TIMER_ACTIVE = "on-timer-active";
    public static final String CHANNEL_ON_TIMER_DURATION = "on-timer-duration";
    public static final String CHANNEL_ON_TIMER_TIME = "on-timer-time";
    public static final String CHANNEL_FILTER_STATE = "filter-state";

    // List of all Channel Ids for Built-in Oven
    public static final String CHANNEL_OVEN_CAVITY_LIGHT = "oven-cavity-light";
    public static final String CHANNEL_OVEN_DISPLAY_TEMPERATURE = "oven-display-temperature";
    public static final String CHANNEL_OVEN_TARGET_TEMPERATURE = "oven-target-temperature";
    public static final String CHANNEL_OVEN_TARGET_FOOD_PROBE_TEMPERATURE = "oven-target-food-probe-temperature";
    public static final String CHANNEL_OVEN_DISPLAY_FOOD_PROBE_TEMPERATURE = "oven-display-food-probe-temperature";
    public static final String CHANNEL_OVEN_FOOD_PROBE_INSERTION_STATE = "oven-food-probe-insertion-state";
    public static final String CHANNEL_OVEN_WATER_TRAY_INSERTION_STATE = "oven-water-tray-insertion-state";
    public static final String CHANNEL_OVEN_WATER_TANK_EMPTY_STATE = "oven-water-tank-empty-state";
    public static final String CHANNEL_OVEN_PROCESS_PHASE = "oven-process-phase";
    public static final String CHANNEL_OVEN_PROGRAM = "oven-program";
    public static final String CHANNEL_OVEN_REMOTE_CONTROL_STATE = "oven-remote-control-state";
    public static final String CHANNEL_OVEN_RUNNING_TIME = "oven-running-time";
    public static final String CHANNEL_OVEN_START_TIME = "oven-start-time";
    public static final String CHANNEL_OVEN_ALERTS = "oven-alerts";
    public static final String CHANNEL_CONNECTIVITY_STATE = "connectivity-state";
    public static final String CHANNEL_OVEN_COMMAND = "oven-commands";

    // List of all Properties ids
    public static final String PROPERTY_BRAND = "brand";
    public static final String PROPERTY_COLOUR = "colour";
    public static final String PROPERTY_MODEL = "model";
    public static final String PROPERTY_DEVICE = "device";
    public static final String PROPERTY_FW_VERSION = "fwVersion";
    public static final String PROPERTY_SERIAL_NUMBER = "serialNumber";
    public static final String PROPERTY_WORKMODE = "workmode";
    public static final String PROPERTY_NIU_FW_VERSION = "nioFwVersion";
    public static final String PROPERTY_MCU_FW_VERSION = "mcuFwVersion";

    // List of all Commands for Air Purifiers
    public static final String COMMAND_WORKMODE_POWEROFF = "PowerOff";
    public static final String COMMAND_WORKMODE_AUTO = "Auto";
    public static final String COMMAND_WORKMODE_MANUAL = "Manual";

    public static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Set.of(THING_TYPE_BRIDGE,
            THING_TYPE_ELECTROLUX_AIR_PURIFIER, THING_TYPE_ELECTROLUX_WASHING_MACHINE,
            THING_TYPE_ELECTROLUX_BUILT_IN_OVEN);
}
