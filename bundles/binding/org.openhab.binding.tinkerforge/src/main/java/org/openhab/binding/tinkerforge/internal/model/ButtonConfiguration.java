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
package org.openhab.binding.tinkerforge.internal.model;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Button Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.openhab.binding.tinkerforge.internal.model.ButtonConfiguration#isTactile <em>Tactile</em>}</li>
 * </ul>
 *
 * @see org.openhab.binding.tinkerforge.internal.model.ModelPackage#getButtonConfiguration()
 * @model
 * @generated
 */
public interface ButtonConfiguration extends TFConfig {
    /**
     * Returns the value of the '<em><b>Tactile</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tactile</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Tactile</em>' attribute.
     * @see #setTactile(boolean)
     * @see org.openhab.binding.tinkerforge.internal.model.ModelPackage#getButtonConfiguration_Tactile()
     * @model default="false" unique="false"
     * @generated
     */
    boolean isTactile();

    /**
     * Sets the value of the '{@link org.openhab.binding.tinkerforge.internal.model.ButtonConfiguration#isTactile
     * <em>Tactile</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Tactile</em>' attribute.
     * @see #isTactile()
     * @generated
     */
    void setTactile(boolean value);

} // ButtonConfiguration
