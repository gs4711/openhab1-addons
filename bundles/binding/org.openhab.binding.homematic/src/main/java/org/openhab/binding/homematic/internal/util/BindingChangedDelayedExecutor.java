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
package org.openhab.binding.homematic.internal.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.openhab.binding.homematic.internal.communicator.HomematicCommunicator;
import org.openhab.binding.homematic.internal.config.binding.HomematicBindingConfig;
import org.openhab.core.items.Item;

/**
 * DelayedExecutor used to publish items after startup or after an item reload.
 * A extra thread with a short delay is necessary, because sometimes the values
 * are Uninitialized even if they are published correctly.
 *
 * @author Gerhard Riegler
 * @since 1.5.0
 */
public class BindingChangedDelayedExecutor extends DelayedExecutor {
    private HomematicCommunicator communicator;

    private Map<Item, HomematicBindingConfig> bindingConfigs = new HashMap<Item, HomematicBindingConfig>();

    /**
     * Creates the Executor for the communicator.
     */
    public BindingChangedDelayedExecutor(HomematicCommunicator communicator) {
        this.communicator = communicator;
    }

    /**
     * Adds the item and binding which has changed.
     */
    public void addBindingConfig(Item item, HomematicBindingConfig bindingConfig) {
        bindingConfigs.put(item, bindingConfig);
    }

    /**
     * Publishes the items to openHAB.
     */
    public void publishChangedBindings() {
        Iterator<Map.Entry<Item, HomematicBindingConfig>> iterator = bindingConfigs.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Item, HomematicBindingConfig> entry = iterator.next();
            communicator.publishChangedItemToOpenhab(entry.getKey(), entry.getValue());
            iterator.remove();
        }
    }
}
