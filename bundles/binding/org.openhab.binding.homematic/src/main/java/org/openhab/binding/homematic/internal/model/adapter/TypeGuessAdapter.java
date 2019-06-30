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
package org.openhab.binding.homematic.internal.model.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * JAXB Adapter that guesses the type of a string value.
 *
 * @author Gerhard Riegler
 * @since 1.5.0
 */
public class TypeGuessAdapter extends XmlAdapter<String, Object> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object unmarshal(String value) throws Exception {
        if (value == null || "".equals(value)) {
            return null;
        }
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("on")) {
            return (Boolean.TRUE);
        } else if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("off")) {
            return (Boolean.FALSE);
        } else if (value.matches("(-|\\+)?[0-9]+")) {
            return (Integer.valueOf(value));
        } else if (value.matches("(-|\\+)?[0-9]+\\.[0-9]+")) {
            return (Double.valueOf(value));
        } else {
            return value;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String marshal(Object value) throws Exception {
        return value == null ? null : value.toString();
    }

}
