package com.mobius.software.telco.protocols.diameter.impl.commands.rfc4740;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mobius.software.telco.protocols.diameter.annotations.DiameterOrder;
import com.mobius.software.telco.protocols.diameter.annotations.DiameterValidate;
import com.mobius.software.telco.protocols.diameter.commands.rfc4740.ServerAssignmentRequest;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpNotSupportedException;
import com.mobius.software.telco.protocols.diameter.exceptions.DiameterException;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.impl.primitives.common.AuthSessionStateImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.rfc4590.SIPAORImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.rfc4740.SIPServerAssignmentTypeImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.rfc4740.SIPServerURIImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.rfc4740.SIPSupportedUserDataTypeImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.rfc4740.SIPUserDataAlreadyAvailableImpl;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvp;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterUnknownAvp;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthSessionState;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthSessionStateEnum;
import com.mobius.software.telco.protocols.diameter.primitives.rfc4590.SIPAOR;
import com.mobius.software.telco.protocols.diameter.primitives.rfc4740.SIPServerAssignmentType;
import com.mobius.software.telco.protocols.diameter.primitives.rfc4740.SIPServerAssignmentTypeEnum;
import com.mobius.software.telco.protocols.diameter.primitives.rfc4740.SIPServerURI;
import com.mobius.software.telco.protocols.diameter.primitives.rfc4740.SIPSupportedUserDataType;
import com.mobius.software.telco.protocols.diameter.primitives.rfc4740.SIPUserDataAlreadyAvailable;
import com.mobius.software.telco.protocols.diameter.primitives.rfc4740.SIPUserDataAlreadyAvailableEnum;

/*
 * Mobius Software LTD, Open Source Cloud Communications
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

/**
*
* @author yulian oifa
*
*/
public class ServerAssignmentRequestImpl extends com.mobius.software.telco.protocols.diameter.impl.commands.common.AuthenticationRequestWithHostBase implements ServerAssignmentRequest
{
	private AuthSessionState authSessionState;
	
	private SIPServerAssignmentType sipServerAssignmentType;
	
	private SIPUserDataAlreadyAvailable sipUserDataAlreadyAvailable;
	
	private SIPServerURI sipServerURI;
	
	private List<SIPSupportedUserDataType> sipSupportedUserDataType;
	
	private List<SIPAOR> sipAOR;
	
	protected ServerAssignmentRequestImpl() 
	{
		super();
	}
	
	public ServerAssignmentRequestImpl(String originHost,String originRealm,String destinationHost,String destinationRealm,Boolean isRetransmit, String sessionID, Long authApplicationId, AuthSessionStateEnum authSessionState, SIPServerAssignmentTypeEnum sipServerAssignmentType,SIPUserDataAlreadyAvailableEnum sipUserDataAlreadyAvailable) throws MissingAvpException, AvpNotSupportedException
	{
		super(originHost, originRealm, destinationHost, destinationRealm, isRetransmit, sessionID, authApplicationId);
		
		setAuthSessionState(authSessionState);
		
		setSIPServerAssignmentType(sipServerAssignmentType);
		
		setSIPUserDataAlreadyAvailable(sipUserDataAlreadyAvailable);
	}

	@Override
	public AuthSessionStateEnum getAuthSessionState() 
	{
		if(authSessionState == null)
			return null;
		
		return authSessionState.getEnumerated(AuthSessionStateEnum.class);
	}

	@Override
	public void setAuthSessionState(AuthSessionStateEnum value) throws MissingAvpException 
	{
		if(value == null)
			throw new MissingAvpException("Auth-Session-State is required is required", Arrays.asList(new DiameterAvp[] { new AuthSessionStateImpl() }));
			
		this.authSessionState = new AuthSessionStateImpl(value, null, null);
	}

	@Override
	public SIPServerAssignmentTypeEnum getSIPServerAssignmentType() 
	{
		if(sipServerAssignmentType == null)
			return null;
		
		return sipServerAssignmentType.getEnumerated(SIPServerAssignmentTypeEnum.class);
	}

	@Override
	public void setSIPServerAssignmentType(SIPServerAssignmentTypeEnum value) throws MissingAvpException 
	{
		if(value == null)
			throw new MissingAvpException("SIP-Server-Assignment-Type is required is required", Arrays.asList(new DiameterAvp[] { new SIPServerAssignmentTypeImpl() }));
			
		this.sipServerAssignmentType = new SIPServerAssignmentTypeImpl(value, null, null);
	}
	
	@Override
	public SIPUserDataAlreadyAvailableEnum getSIPUserDataAlreadyAvailable()
	{
		if(sipUserDataAlreadyAvailable == null)
			return null;
		
		return sipUserDataAlreadyAvailable.getEnumerated(SIPUserDataAlreadyAvailableEnum.class);
	}
	
	@Override
	public void setSIPUserDataAlreadyAvailable(SIPUserDataAlreadyAvailableEnum value) throws MissingAvpException
	{
		if(value == null)
			throw new MissingAvpException("SIP-User-Data-Already-Available is required is required", Arrays.asList(new DiameterAvp[] { new SIPUserDataAlreadyAvailableImpl() }));
			
		this.sipUserDataAlreadyAvailable = new SIPUserDataAlreadyAvailableImpl(value, null, null);
	}
	
	@Override
	public String getSIPServerURI()
	{
		if(sipServerURI == null)
			return null;
		
		return sipServerURI.getString();
	}
	
	@Override
	public void setSIPServerURI(String value)
	{
		if(value == null)
			this.sipServerURI = null;
		else
			this.sipServerURI = new SIPServerURIImpl(value, null, null);
	}
	
	@Override
	public List<String> getSIPSupportedUserDataType()
	{
		if(sipSupportedUserDataType == null)
			return null;
		
		List<String> result = new ArrayList<String>();
		for(SIPSupportedUserDataType curr:sipSupportedUserDataType)
			result.add(curr.getString());
		
		return result;
	}
	
	@Override
	public void setSIPSupportedUserDataType(List<String> value)
	{
		if(value == null || value.size()==0)
			this.sipSupportedUserDataType = null;
		else
		{
			this.sipSupportedUserDataType = new ArrayList<SIPSupportedUserDataType>();
			for(String curr:value)
				this.sipSupportedUserDataType.add(new SIPSupportedUserDataTypeImpl(curr, null, null));
		}
	}
	
	@Override
	public List<String> getSIPAOR()
	{
		if(sipAOR == null)
			return null;
		
		List<String> result = new ArrayList<String>();
		for(SIPAOR curr:sipAOR)
			result.add(curr.getString());
		
		return result;
	}
	
	@Override
	public void setSIPAOR(List<String> value)
	{
		if(value == null || value.size()==0)
			this.sipAOR = null;
		else
		{
			this.sipAOR = new ArrayList<SIPAOR>();
			for(String curr:value)
				this.sipAOR.add(new SIPAORImpl(curr, null, null));
		}
	}
	
	@DiameterValidate
	public DiameterException validate()
	{
		if(authSessionState == null)
			return new MissingAvpException("Auth-Session-State is required is required", Arrays.asList(new DiameterAvp[] { new AuthSessionStateImpl() }));
		
		if(sipServerAssignmentType == null)
			return new MissingAvpException("SIP-Server-Assignment-Type is required is required", Arrays.asList(new DiameterAvp[] { new SIPServerAssignmentTypeImpl() }));
		
		if(sipUserDataAlreadyAvailable == null)
			return new MissingAvpException("SIP-User-Data-Already-Available is required is required", Arrays.asList(new DiameterAvp[] { new SIPUserDataAlreadyAvailableImpl() }));
		
		return super.validate();
	}
	
	@DiameterOrder
	public List<DiameterAvp> getOrderedAVPs()
	{
		List<DiameterAvp> result=new ArrayList<DiameterAvp>();
		result.add(sessionId);
		result.add(authApplicationId);
		result.add(authSessionState);
		result.add(originHost);
		result.add(originRealm);
		result.add(destinationRealm);
		result.add(sipServerAssignmentType);
		result.add(sipUserDataAlreadyAvailable);
		result.add(destinationHost);
		result.add(username);
		result.add(sipServerURI);
		
		if(sipSupportedUserDataType!=null)
			result.addAll(sipSupportedUserDataType);
		
		if(sipAOR!=null)
			result.addAll(sipAOR);
		
		if(proxyInfo!=null)
			result.addAll(proxyInfo);
		
		if(routeRecords!=null)
			result.addAll(routeRecords);				
		
		if(optionalAvps!=null)
		{
			for(List<DiameterUnknownAvp> curr:optionalAvps.values())
				result.addAll(curr);
		}
		
		return result;
	}
}