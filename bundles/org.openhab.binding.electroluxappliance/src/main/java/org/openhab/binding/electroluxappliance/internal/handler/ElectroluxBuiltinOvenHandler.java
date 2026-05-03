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
package org.openhab.binding.electroluxappliance.internal.handler;

import static org.openhab.binding.electroluxappliance.internal.ElectroluxApplianceBindingConstants.*;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.electroluxappliance.internal.api.ElectroluxGroupAPI;
import org.openhab.binding.electroluxappliance.internal.dto.ApplianceDTO;
import org.openhab.binding.electroluxappliance.internal.dto.BuiltinOvenStateDTO;
import org.openhab.core.i18n.LocaleProvider;
import org.openhab.core.i18n.TranslationProvider;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.OpenClosedType;
import org.openhab.core.library.types.QuantityType;
import org.openhab.core.library.types.StringType;
import org.openhab.core.library.unit.SIUnits;
import org.openhab.core.storage.Storage;
import org.openhab.core.thing.Bridge;
import org.openhab.core.thing.Channel;
import org.openhab.core.thing.ChannelUID;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingStatus;
import org.openhab.core.thing.ThingStatusDetail;
import org.openhab.core.types.Command;
import org.openhab.core.types.RefreshType;
import org.openhab.core.types.State;
import org.openhab.core.types.UnDefType;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link ElectroluxBuiltinOvenHandler} is responsible for handling commands
 * and status updates for
 * Electrolux built-in ovens.
 *
 *
 * @author Arne Steinmetz - Initial contribution
 */
@NonNullByDefault
public class ElectroluxBuiltinOvenHandler extends ElectroluxApplianceHandler {

    private final Logger logger = LoggerFactory.getLogger(ElectroluxBuiltinOvenHandler.class);

    public ElectroluxBuiltinOvenHandler(Thing thing, @Reference TranslationProvider translationProvider,
            @Reference LocaleProvider localeProvider, @Reference Storage<String> strStore) {
        super(thing, translationProvider, localeProvider);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        logger.debug("Command received: {} on channelID: {}", command, channelUID);
        if (CHANNEL_STATUS.equals(channelUID.getId()) || command instanceof RefreshType) {
            super.handleCommand(channelUID, command);
        }
        ApplianceDTO dto = getApplianceDTO();
        ElectroluxGroupAPI api = getElectroluxGroupAPI();
        if (CHANNEL_OVEN_CAVITY_LIGHT.equals(channelUID.getId())) {
            if (api != null && dto != null) {
                boolean result = api.sendCapabilityRequest(dto.getApplianceId(), "cavityLight", dto,
                        OnOffType.from(command.toString()).equals(OnOffType.ON) ? "true" : "false");
                if (!result) {
                    logger.warn("Failed to send command {} for channel {}", command, channelUID);
                } else {
                    logger.debug("Command {} sent successfully for channel {}", command, channelUID);
                }
            }
        } else if (CHANNEL_OVEN_PROGRAM.equals(channelUID.getId())) {
            if (api != null && dto != null) {
                boolean result = api.sendCapabilityRequest(dto.getApplianceId(), "program", dto, command.toString());
                if (!result) {
                    logger.warn("Failed to send command {} for channel {}", command, channelUID);
                } else {
                    logger.debug("Command {} sent successfully for channel {}", command, channelUID);
                }
            }
        } else if (CHANNEL_OVEN_COMMAND.equals(channelUID.getId())) {
            if (api != null && dto != null) {
                boolean result = api.sendCapabilityRequest(dto.getApplianceId(), "executeCommand", dto,
                        command.toString());
                if (!result) {
                    logger.warn("Failed to send command {} for channel {}", command, channelUID);
                } else {
                    logger.debug("Command {} sent successfully for channel {}", command, channelUID);
                }
            }
        } else {
            logger.warn("Unsupported command {} for channel {}", command, channelUID);
        }
    }

    @Override
    public void update(@Nullable ApplianceDTO dto) {
        if (dto != null) {
            // Update all channels from the updated data
            getThing().getChannels().stream().map(Channel::getUID).filter(channelUID -> isLinked(channelUID))
                    .forEach(channelUID -> {
                        State state = getValue(channelUID.getId(), dto);
                        if (CHANNEL_OVEN_CAVITY_LIGHT.equals(channelUID.getId())) {
                            logger.debug("Cavity Light: {}, State: {}", channelUID, state);
                            updateState(channelUID, OnOffType.from(state.toString()));
                        } else if (CHANNEL_OVEN_FOOD_PROBE_INSERTION_STATE.equals(channelUID.getId())) {
                            logger.debug("Food Probe Insertion State: {}, State: {}", channelUID, state);
                            updateState(channelUID, OnOffType.from("INSERTED".equalsIgnoreCase(state.toString())));
                        } else if (CHANNEL_OVEN_WATER_TRAY_INSERTION_STATE.equals(channelUID.getId())) {
                            logger.debug("Water Tray Insertion State: {}, State: {}", channelUID, state);
                            updateState(channelUID, OnOffType.from("INSERTED".equalsIgnoreCase(state.toString())));
                        } else if (CHANNEL_OVEN_WATER_TANK_EMPTY_STATE.equals(channelUID.getId())) {
                            logger.debug("Water Tank Empty: {}, State: {} [{}]", channelUID, state,
                                    OnOffType.from("STEAM_TANK_EMPTY".equalsIgnoreCase(state.toString())));
                            updateState(channelUID,
                                    OnOffType.from("STEAM_TANK_EMPTY".equalsIgnoreCase(state.toString())));
                        } else if (CHANNEL_OVEN_REMOTE_CONTROL_STATE.equals(channelUID.getId())) {
                            logger.debug("Remote Control State: {}, State: {} [{}]", channelUID, state,
                                    OnOffType.from("enabled".equalsIgnoreCase(state.toString())));
                            updateState(channelUID, OnOffType.from("enabled".equalsIgnoreCase(state.toString())));
                        } else if (CHANNEL_OVEN_PROCESS_PHASE.equals(channelUID.getId())
                                || CHANNEL_OVEN_PROGRAM.equals(channelUID.getId())
                                || CHANNEL_APPLIANCE_STATE.equals(channelUID.getId())) {
                            logger.debug("Process Phase / Program / Appliance State: {}, State: {}", channelUID, state);
                            updateState(channelUID, state);
                        } else if (CHANNEL_OVEN_RUNNING_TIME.equals(channelUID.getId())
                                || CHANNEL_OVEN_START_TIME.equals(channelUID.getId())) {
                            logger.debug("Running Time / Start Time: {}, State: {}", channelUID, state);
                            updateState(channelUID, state);
                        } else if (CHANNEL_OVEN_ALERTS.equals(channelUID.getId())) {
                            if (!"[]".equals(state.toString())) {
                                logger.warn("Alerts: {}, State: {}", channelUID, state);
                            } else {
                                logger.debug("Alerts: {}, State: {}", channelUID, state);
                            }
                            updateState(channelUID, StringType.valueOf(state.toString()));
                        } else if (CHANNEL_APPLIANCE_STATE.equals(channelUID.getId())) {
                            logger.debug("Appliance State: {}, State: {}", channelUID, state);
                            updateState(channelUID, state);
                        } else if (CHANNEL_CONNECTIVITY_STATE.equals(channelUID.getId())) {
                            logger.debug("Connectivity State: {}, State: {}", channelUID, state);
                            updateState(channelUID, state);
                        } else if (CHANNEL_OVEN_COMMAND.equals(channelUID.getId())) {
                            logger.debug("Oven Command Refresh Ignored: {}, State: {}", channelUID, state);
                        } else {
                            logger.trace("Oven Channel Update: {}, State: {}", channelUID, state);
                            updateState(channelUID, state);
                        }
                    });
            if ("Connected".equalsIgnoreCase(dto.getApplianceState().getConnectionState())) {
                updateStatus(ThingStatus.ONLINE);
            } else {
                updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR,
                        getLocalizedText("error.electroluxappliance.wm.not-connected"));
            }
        }
    }

    private State getValue(String channelId, ApplianceDTO dto) {
        var reported = ((BuiltinOvenStateDTO) dto.getApplianceState()).getProperties().getReported();
        switch (channelId) {
            case CHANNEL_DOOR_STATE:
                return "OPEN".equals(reported.getDoorState()) ? OpenClosedType.OPEN : OpenClosedType.CLOSED;
            case CHANNEL_CONNECTIVITY_STATE:
                return OnOffType.from("connected".equalsIgnoreCase(reported.getConnectivityState()));
            case CHANNEL_OVEN_CAVITY_LIGHT:
                return reported.getCavityLight() ? OnOffType.ON : OnOffType.OFF;
            case CHANNEL_OVEN_TARGET_FOOD_PROBE_TEMPERATURE:
                /* @TODO Choose approriate getter */
                /* based on config.tempUnit */
                return new QuantityType<>(reported.getTargetFoodProbeTemperatureC(), SIUnits.CELSIUS);
            case CHANNEL_OVEN_TARGET_TEMPERATURE:
                return new QuantityType<>(reported.getTargetTemperatureC(), SIUnits.CELSIUS);
            case CHANNEL_OVEN_DISPLAY_FOOD_PROBE_TEMPERATURE:
                return new QuantityType<>(reported.getDisplayFoodProbeTemperatureC(), SIUnits.CELSIUS);
            case CHANNEL_OVEN_DISPLAY_TEMPERATURE:
                return new QuantityType<>(reported.getDisplayTemperatureC(), SIUnits.CELSIUS);
            case CHANNEL_OVEN_FOOD_PROBE_INSERTION_STATE:
                return new StringType(reported.getFoodProbeInsertionState());
            case CHANNEL_OVEN_WATER_TRAY_INSERTION_STATE:
                return new StringType(reported.getWaterTrayInsertionState());
            case CHANNEL_OVEN_WATER_TANK_EMPTY_STATE:
                return new StringType(reported.getWaterTankEmpty());
            case CHANNEL_OVEN_PROCESS_PHASE:
                return new StringType(reported.getProcessPhase());
            case CHANNEL_OVEN_PROGRAM:
                return new StringType(reported.getProgram());
            case CHANNEL_OVEN_REMOTE_CONTROL_STATE:
                return new StringType(reported.getRemoteControl());
            case CHANNEL_OVEN_RUNNING_TIME:
                return new QuantityType<>(reported.getRunningTime() + " s");
            case CHANNEL_OVEN_START_TIME:
                return new QuantityType<>(reported.getStartTime() + " s");
            case CHANNEL_APPLIANCE_STATE:
                return new StringType(reported.getApplianceState());
            case CHANNEL_OVEN_ALERTS:
                return new StringType(reported.getAlerts().toString());
            case CHANNEL_OVEN_COMMAND:
                return UnDefType.UNDEF;
            default:
                logger.warn("Unsupported channel: {}", channelId);
                return UnDefType.UNDEF;
        }
    }

    @Override
    public Map<String, String> refreshProperties() {
        Map<String, String> properties = new HashMap<>();
        final Bridge bridge = getBridge();
        if (bridge != null && bridge.getHandler() instanceof ElectroluxApplianceBridgeHandler bridgeHandler) {
            ApplianceDTO dto = bridgeHandler.getElectroluxApplianceThings().get(getApplianceConfig().getSerialNumber());
            if (dto != null) {
                var applianceInfo = dto.getApplianceInfo().getApplianceInfo();
                properties.put(Thing.PROPERTY_VENDOR, applianceInfo.getBrand());
                properties.put(PROPERTY_COLOUR, applianceInfo.getColour());
                properties.put(PROPERTY_DEVICE, applianceInfo.getDeviceType());
                properties.put(Thing.PROPERTY_MODEL_ID, applianceInfo.getModel());
                properties.put(Thing.PROPERTY_SERIAL_NUMBER, applianceInfo.getSerialNumber());
                properties.put(Thing.PROPERTY_FIRMWARE_VERSION, ((BuiltinOvenStateDTO) dto.getApplianceState())
                        .getProperties().getReported().getNetworkInterface().getSwVersion());
            }
        }
        return properties;
    }
}
