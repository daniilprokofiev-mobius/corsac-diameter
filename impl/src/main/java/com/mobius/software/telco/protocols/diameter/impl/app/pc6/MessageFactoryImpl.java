package com.mobius.software.telco.protocols.diameter.impl.app.pc6;
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

import org.restcomm.cluster.IDGenerator;

import com.mobius.software.telco.protocols.diameter.ApplicationIDs;
import com.mobius.software.telco.protocols.diameter.VendorIDs;
import com.mobius.software.telco.protocols.diameter.app.pc6.MessageFactory;
import com.mobius.software.telco.protocols.diameter.commands.pc6.ProSeAlertRequest;
import com.mobius.software.telco.protocols.diameter.commands.pc6.ProSeAuthorizationRequest;
import com.mobius.software.telco.protocols.diameter.commands.pc6.ProSeCancellationRequest;
import com.mobius.software.telco.protocols.diameter.commands.pc6.ProSeDiscoveryRequest;
import com.mobius.software.telco.protocols.diameter.commands.pc6.ProSeLocationUpdateRequest;
import com.mobius.software.telco.protocols.diameter.commands.pc6.ProSeMatchReportInfoRequest;
import com.mobius.software.telco.protocols.diameter.commands.pc6.ProSeMatchRequest;
import com.mobius.software.telco.protocols.diameter.commands.pc6.ProSeProximityRequest;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpNotSupportedException;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpOccursTooManyTimesException;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.impl.commands.pc6.ProSeAlertRequestImpl;
import com.mobius.software.telco.protocols.diameter.impl.commands.pc6.ProSeAuthorizationRequestImpl;
import com.mobius.software.telco.protocols.diameter.impl.commands.pc6.ProSeCancellationRequestImpl;
import com.mobius.software.telco.protocols.diameter.impl.commands.pc6.ProSeDiscoveryRequestImpl;
import com.mobius.software.telco.protocols.diameter.impl.commands.pc6.ProSeLocationUpdateRequestImpl;
import com.mobius.software.telco.protocols.diameter.impl.commands.pc6.ProSeMatchReportInfoRequestImpl;
import com.mobius.software.telco.protocols.diameter.impl.commands.pc6.ProSeMatchRequestImpl;
import com.mobius.software.telco.protocols.diameter.impl.commands.pc6.ProSeProximityRequestImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.common.VendorSpecificApplicationIdImpl;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthSessionStateEnum;
import com.mobius.software.telco.protocols.diameter.primitives.common.VendorSpecificApplicationId;
import com.mobius.software.telco.protocols.diameter.primitives.pc6.DiscoveryAuthRequest;
import com.mobius.software.telco.protocols.diameter.primitives.pc6.MatchReportInfo;
import com.mobius.software.telco.protocols.diameter.primitives.pc6.MatchRequest;
import com.mobius.software.telco.protocols.diameter.primitives.pc6.PRRFlags;
import com.mobius.software.telco.protocols.diameter.primitives.sh.UserIdentity;

import io.netty.buffer.ByteBuf;
/**
*
* @author yulian oifa
*
*/
public class MessageFactoryImpl implements MessageFactory
{
	public static final long APPLICATION_ID=ApplicationIDs.PC6;
	
	private IDGenerator<?> idGenerator;
	
	private Long applicationId = APPLICATION_ID;
	
	public MessageFactoryImpl(IDGenerator<?> idGenerator)
	{
		this.idGenerator = idGenerator;
	}
	
	public MessageFactoryImpl(IDGenerator<?> idGenerator, long applicationId)
	{
		this.idGenerator = idGenerator;
		this.applicationId = applicationId;
	}
	
	public ProSeAlertRequest createProSeAlertRequest(String originHost,String originRealm,String destinationHost,String destinationRealm,String appLayerUserId,String targetedEPUID) throws MissingAvpException, AvpNotSupportedException, AvpOccursTooManyTimesException
	{
		VendorSpecificApplicationId appId = new VendorSpecificApplicationIdImpl(VendorIDs.TGPP_ID, applicationId, null);
		ProSeAlertRequest request = new ProSeAlertRequestImpl(originHost, originRealm, destinationHost, destinationRealm, false, idGenerator.generateID().toString(), AuthSessionStateEnum.NO_STATE_MAINTAINED, appLayerUserId, targetedEPUID);
		request.setVendorSpecificApplicationId(appId);
		return request;
	}		
	
	public ProSeAuthorizationRequest createProSeAuthorizationRequest(String originHost,String originRealm,String destinationHost,String destinationRealm,UserIdentity userIdentity,ByteBuf visitedPLMNId) throws MissingAvpException, AvpNotSupportedException, AvpOccursTooManyTimesException
	{
		VendorSpecificApplicationId appId = new VendorSpecificApplicationIdImpl(VendorIDs.TGPP_ID, applicationId, null);
		ProSeAuthorizationRequest request = new ProSeAuthorizationRequestImpl(originHost, originRealm, destinationHost, destinationRealm, false, idGenerator.generateID().toString(), AuthSessionStateEnum.NO_STATE_MAINTAINED, userIdentity, visitedPLMNId);
		request.setVendorSpecificApplicationId(appId);
		return request; 
	}
	
	public ProSeCancellationRequest createProSeCancellationRequest(String originHost,String originRealm,String destinationHost,String destinationRealm,String requestingEPUID,String targetedEPUID) throws MissingAvpException, AvpNotSupportedException, AvpOccursTooManyTimesException
	{
		VendorSpecificApplicationId appId = new VendorSpecificApplicationIdImpl(VendorIDs.TGPP_ID, applicationId, null);
		ProSeCancellationRequest request = new ProSeCancellationRequestImpl(originHost, originRealm, destinationHost, destinationRealm, false, idGenerator.generateID().toString(), AuthSessionStateEnum.NO_STATE_MAINTAINED, requestingEPUID, targetedEPUID);
		request.setVendorSpecificApplicationId(appId);
		return request; 
	}
	
	public ProSeDiscoveryRequest createProSeDiscoveryRequest(String originHost,String originRealm,String destinationHost,String destinationRealm,DiscoveryAuthRequest discoveryAuthRequest) throws MissingAvpException, AvpNotSupportedException, AvpOccursTooManyTimesException
	{
		VendorSpecificApplicationId appId = new VendorSpecificApplicationIdImpl(VendorIDs.TGPP_ID, applicationId, null);
		ProSeDiscoveryRequest request = new ProSeDiscoveryRequestImpl(originHost, originRealm, destinationHost, destinationRealm, false, idGenerator.generateID().toString(), AuthSessionStateEnum.NO_STATE_MAINTAINED, discoveryAuthRequest);
		request.setVendorSpecificApplicationId(appId);
		return request; 
	}
	
	public ProSeLocationUpdateRequest createProSeLocationUpdateRequest(String originHost,String originRealm,String destinationHost,String destinationRealm,String targetedEPUID,ByteBuf locationEstimate) throws MissingAvpException, AvpNotSupportedException, AvpOccursTooManyTimesException
	{
		VendorSpecificApplicationId appId = new VendorSpecificApplicationIdImpl(VendorIDs.TGPP_ID, applicationId, null);
		ProSeLocationUpdateRequest request = new ProSeLocationUpdateRequestImpl(originHost, originRealm, destinationHost, destinationRealm, false, idGenerator.generateID().toString(), AuthSessionStateEnum.NO_STATE_MAINTAINED, targetedEPUID, locationEstimate);
		request.setVendorSpecificApplicationId(appId);
		return request; 
	}
	
	public ProSeMatchReportInfoRequest createProSeMatchReportInfoRequest(String originHost,String originRealm,String destinationHost,String destinationRealm,MatchReportInfo matchReportInfo) throws MissingAvpException, AvpNotSupportedException, AvpOccursTooManyTimesException
	{
		VendorSpecificApplicationId appId = new VendorSpecificApplicationIdImpl(VendorIDs.TGPP_ID, applicationId, null);
		ProSeMatchReportInfoRequest request = new ProSeMatchReportInfoRequestImpl(originHost, originRealm, destinationHost, destinationRealm, false, idGenerator.generateID().toString(), AuthSessionStateEnum.NO_STATE_MAINTAINED, matchReportInfo);
		request.setVendorSpecificApplicationId(appId);
		return request; 
	}
	
	public ProSeMatchRequest createProSeMatchRequest(String originHost,String originRealm,String destinationHost,String destinationRealm,MatchRequest matchRequest) throws MissingAvpException, AvpNotSupportedException, AvpOccursTooManyTimesException
	{
		VendorSpecificApplicationId appId = new VendorSpecificApplicationIdImpl(VendorIDs.TGPP_ID, applicationId, null);
		ProSeMatchRequest request = new ProSeMatchRequestImpl(originHost, originRealm, destinationHost, destinationRealm, false, idGenerator.generateID().toString(), AuthSessionStateEnum.NO_STATE_MAINTAINED, matchRequest);
		request.setVendorSpecificApplicationId(appId);
		return request; 
	}
	
	public ProSeProximityRequest createProSeProximityRequest(String originHost,String originRealm,String destinationHost,String destinationRealm,PRRFlags prrFlags,String requestingEPUID,String targetedEPUID,Long timeWindow,ByteBuf locationEstimate) throws MissingAvpException, AvpNotSupportedException, AvpOccursTooManyTimesException
	{
		VendorSpecificApplicationId appId = new VendorSpecificApplicationIdImpl(VendorIDs.TGPP_ID, applicationId, null);
		ProSeProximityRequest request = new ProSeProximityRequestImpl(originHost, originRealm, destinationHost, destinationRealm, false, idGenerator.generateID().toString(), AuthSessionStateEnum.NO_STATE_MAINTAINED, prrFlags, requestingEPUID, targetedEPUID, timeWindow, locationEstimate);
		request.setVendorSpecificApplicationId(appId);
		return request; 
	}
}