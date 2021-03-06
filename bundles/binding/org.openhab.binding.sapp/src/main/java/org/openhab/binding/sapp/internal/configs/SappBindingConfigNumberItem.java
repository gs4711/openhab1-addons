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
package org.openhab.binding.sapp.internal.configs;

import org.apache.commons.lang.ArrayUtils;
import org.openhab.binding.sapp.internal.model.SappAddressDecimal;
import org.openhab.binding.sapp.internal.model.SappAddressType;
import org.openhab.core.items.Item;
import org.openhab.model.item.binding.BindingConfigParseException;

// Number binding format
// <pnmasid status>:<status address type, I/O/V>:<status address, 1-250/1-250/1-2500>:<status subaddress, */H/L/1-16>:<min value>:<max value>
// <min value>:<max value> can be omitted. If present value is scaled
// example: { sapp="home:V:200:L" }
// example: { sapp="home:V:200:L:10:180" }

/**
 * This is a helper class holding NumberItem binding specific configuration details
 *
 * @author Paolo Denti
 * @since 1.8.0
 *
 */
public class SappBindingConfigNumberItem extends SappBindingConfig {

    private SappAddressDecimal status;

    /**
     * Constructor
     */
    public SappBindingConfigNumberItem(Item item, String bindingConfig) throws BindingConfigParseException {

        super(item.getName());

        this.status = parseSappAddressStatus(bindingConfig);
    }

    /**
     * status getter
     */
    public SappAddressDecimal getStatus() {
        return status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[itemName:%s: status:%s ]", getItemName(), this.status.toString());
    }

    private String errorMessage(String bindingConfig) {
        return String.format("Invalid Sapp binding configuration for NumberItem '%s'", bindingConfig);
    }

    private SappAddressDecimal parseSappAddressStatus(String bindingStringAddress) throws BindingConfigParseException {

        String pnmasId;
        SappAddressType addressType;
        int address;
        String subAddress;
        int minScale;
        int maxScale;

        String[] bindingAddress = bindingStringAddress.split(":");
        if (bindingAddress.length != 4 && bindingAddress.length != 6) {
            throw new BindingConfigParseException(errorMessage(bindingStringAddress));
        }

        // pnmasId
        pnmasId = bindingAddress[0];
        if (pnmasId.length() == 0) {
            throw new BindingConfigParseException(errorMessage(bindingStringAddress));
        }

        // addressType
        addressType = SappAddressType.fromString(bindingAddress[1].toUpperCase());
        if (!validAddresses.keySet().contains(addressType)) {
            throw new BindingConfigParseException(errorMessage(bindingStringAddress));
        }

        // address
        try {
            address = Integer.parseInt(bindingAddress[2]);
            if (address < validAddresses.get(addressType).getLoRange()
                    || address > validAddresses.get(addressType).getHiRange()) {
                throw new BindingConfigParseException(errorMessage(bindingStringAddress));
            }
        } catch (NumberFormatException e) {
            throw new BindingConfigParseException(errorMessage(bindingStringAddress));
        }

        // subaddress
        subAddress = bindingAddress[3].toUpperCase();
        if (!ArrayUtils.contains(validSubAddresses, subAddress)) {
            throw new BindingConfigParseException(errorMessage(bindingStringAddress));
        }

        if (bindingAddress.length == 6) {
            try {
                minScale = Integer.parseInt(bindingAddress[4]);
                maxScale = Integer.parseInt(bindingAddress[5]);
            } catch (NumberFormatException e) {
                throw new BindingConfigParseException(errorMessage(bindingStringAddress));
            }

            return new SappAddressDecimal(pnmasId, addressType, address, subAddress, minScale, maxScale);
        } else {
            return new SappAddressDecimal(pnmasId, addressType, address, subAddress);
        }
    }
}
