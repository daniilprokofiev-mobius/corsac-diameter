package com.mobius.software.telco.protocols.diameter.impl.app.tsp;
/*
 * Mobius Software LTD
 * Copyright 2023, Mobius Software LTD and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

import com.mobius.software.telco.protocols.diameter.app.tsp.AvpFactory;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.impl.primitives.cxdx.SupportedFeaturesImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.tsp.DeviceActionImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.tsp.DeviceNotificationImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.tsp.TriggerDataImpl;
import com.mobius.software.telco.protocols.diameter.primitives.cxdx.SupportedFeatures;
import com.mobius.software.telco.protocols.diameter.primitives.tsp.ActionTypeEnum;
import com.mobius.software.telco.protocols.diameter.primitives.tsp.DeviceAction;
import com.mobius.software.telco.protocols.diameter.primitives.tsp.DeviceNotification;
import com.mobius.software.telco.protocols.diameter.primitives.tsp.TriggerData;

import io.netty.buffer.ByteBuf;
/**
*
* @author yulian oifa
*
*/
public class AvpFactoryImpl extends com.mobius.software.telco.protocols.diameter.impl.app.commons.AvpFactoryImpl implements AvpFactory
{
	public SupportedFeatures getSupportedFeatures(Long vendorId, Long featureListID, Long featureList) throws MissingAvpException
	{
		return new SupportedFeaturesImpl(vendorId, featureListID, featureList);
	}
	
	public DeviceAction getDeviceAction(Long  referenceNumber, ActionTypeEnum actionType) throws MissingAvpException
	{
		return new DeviceActionImpl(referenceNumber, actionType);
	}
	
	public DeviceNotification getDeviceNotification(Long  referenceNumber, ActionTypeEnum actionType) throws MissingAvpException
	{
		return new DeviceNotificationImpl(referenceNumber, actionType);
	}
	
	public TriggerData getTriggerData(ByteBuf payload) throws MissingAvpException
	{
		return new TriggerDataImpl(payload);
	}
}