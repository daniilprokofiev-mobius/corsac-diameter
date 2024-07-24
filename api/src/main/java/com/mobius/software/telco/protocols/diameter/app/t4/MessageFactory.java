package com.mobius.software.telco.protocols.diameter.app.t4;
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

import com.mobius.software.telco.protocols.diameter.commands.t4.DeliveryReportAnswer;
import com.mobius.software.telco.protocols.diameter.commands.t4.DeliveryReportRequest;
import com.mobius.software.telco.protocols.diameter.commands.t4.DeviceTriggerAnswer;
import com.mobius.software.telco.protocols.diameter.commands.t4.DeviceTriggerRequest;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpNotSupportedException;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpOccursTooManyTimesException;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.primitives.s6m.UserIdentifier;
import com.mobius.software.telco.protocols.diameter.primitives.t4.SMDeliveryOutcomeT4Enum;

import io.netty.buffer.ByteBuf;

public interface MessageFactory
{
	public DeliveryReportRequest createDeliveryReportRequest(String originHost,String originRealm,String destinationHost,String destinationRealm,UserIdentifier userIdentifier,ByteBuf smRPSMEA,SMDeliveryOutcomeT4Enum smDeliveryOutcomeT4) throws MissingAvpException, AvpNotSupportedException, AvpOccursTooManyTimesException;			

	public DeliveryReportAnswer createDeliveryReportAnswer(DeliveryReportRequest request, Long hopByHopIdentifier, Long endToEndIdentifier,Long resultCode) throws AvpOccursTooManyTimesException, MissingAvpException, AvpNotSupportedException;	
	
	public DeliveryReportAnswer createDeliveryReportAnswer(String originHost,String originRealm, Long hopByHopIdentifier, Long endToEndIdentifier,Long resultCode, String sessionID) throws AvpOccursTooManyTimesException, MissingAvpException, AvpNotSupportedException;	
	
	public DeviceTriggerRequest createDeviceTriggerRequest(String originHost,String originRealm,String destinationHost,String destinationRealm,UserIdentifier userIdentifier,ByteBuf smRPSMEA,ByteBuf payload) throws MissingAvpException, AvpNotSupportedException, AvpOccursTooManyTimesException;

	public DeviceTriggerAnswer createDeviceTriggerAnswer(DeviceTriggerRequest request, Long hopByHopIdentifier, Long endToEndIdentifier,Long resultCode) throws AvpOccursTooManyTimesException, MissingAvpException, AvpNotSupportedException;	
	
	public DeviceTriggerAnswer createDeviceTriggerAnswer(String originHost,String originRealm, Long hopByHopIdentifier, Long endToEndIdentifier,Long resultCode, String sessionID) throws AvpOccursTooManyTimesException, MissingAvpException, AvpNotSupportedException;	
	
}