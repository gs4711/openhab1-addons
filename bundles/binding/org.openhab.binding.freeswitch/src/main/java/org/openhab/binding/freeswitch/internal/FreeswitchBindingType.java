/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
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
package org.openhab.binding.freeswitch.internal;

/**
 * Freeswitch bindings have 3 different types: 'ACITVE' will bind to call
 * life cycle events, 'MESSAGE_WAITING' will bind to voice mail and message
 * waiting indicator events (MWI), 'CMD_API' will bind items to api command
 * events which allow api control over a Freeswitch server.
 * 
 * @author Dan Cunningham
 * @since 1.4.0
 */
public enum FreeswitchBindingType {
    ACTIVE("active"),
    MESSAGE_WAITING("message_waiting"),
    CMD_API("api");

    private String label;

    private FreeswitchBindingType(String label) {
        this.label = label;
    }

    public static FreeswitchBindingType fromString(String bindingType) {

        if ("".equals(bindingType)) {
            return null;
        }

        for (FreeswitchBindingType type : FreeswitchBindingType.values()) {
            if (type.label.equals(bindingType)) {
                return type;
            }
        }

        throw new IllegalArgumentException("invalid bindingType '" + bindingType + "'");
    }
}
