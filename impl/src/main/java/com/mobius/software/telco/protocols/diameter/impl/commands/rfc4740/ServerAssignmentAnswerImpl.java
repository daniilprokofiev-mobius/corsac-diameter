package com.mobius.software.telco.protocols.diameter.impl.commands.rfc4740;

import java.util.ArrayList;
import java.util.List;

import com.mobius.software.telco.protocols.diameter.annotations.DiameterCommandImplementation;
import com.mobius.software.telco.protocols.diameter.annotations.DiameterValidate;
import com.mobius.software.telco.protocols.diameter.commands.rfc4740.ServerAssignmentAnswer;
import com.mobius.software.telco.protocols.diameter.impl.commands.common.AuthenticationAnswerImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.common.AuthGracePeriodImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.common.AuthSessionStateImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.common.AuthorizationLifetimeImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.rfc4740.SIPSupportedUserDataTypeImpl;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthGracePeriod;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthSessionState;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthSessionStateEnum;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthorizationLifetime;
import com.mobius.software.telco.protocols.diameter.primitives.rfc4740.SIPAccountingInformation;
import com.mobius.software.telco.protocols.diameter.primitives.rfc4740.SIPSupportedUserDataType;
import com.mobius.software.telco.protocols.diameter.primitives.rfc4740.SIPUserData;

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
@DiameterCommandImplementation(applicationId = 6, commandCode = 284, request = false)
public class ServerAssignmentAnswerImpl extends AuthenticationAnswerImpl implements ServerAssignmentAnswer
{
	private AuthSessionState authSessionState;
	
	private List<SIPUserData> sipUserData;
	
	private SIPAccountingInformation sipAccountingInformation;
	
	private List<SIPSupportedUserDataType> sipSupportedUserDataType;
	
	private AuthGracePeriod authGracePeriod;
	
	private AuthorizationLifetime authorizationLifetime;
	
	protected ServerAssignmentAnswerImpl() 
	{
		super();
		setExperimentalResultAllowed(false);
	}
	
	public ServerAssignmentAnswerImpl(String originHost,String originRealm,Boolean isRetransmit, Long resultCode, String sessionID, Long authApplicationId,AuthSessionStateEnum authSessionState)
	{
		super(originHost, originRealm, isRetransmit, resultCode, sessionID, authApplicationId);
		setExperimentalResultAllowed(false);
		
		setAuthSessionState(authSessionState);
	}

	@Override
	public AuthSessionStateEnum getAuthSessionState() 
	{
		if(authSessionState == null)
			return null;
		
		return authSessionState.getEnumerated(AuthSessionStateEnum.class);
	}

	@Override
	public void setAuthSessionState(AuthSessionStateEnum value) 
	{
		if(value == null)
			throw new IllegalArgumentException("Auth-Session-State is required");
		
		this.authSessionState = new AuthSessionStateImpl(value, null, null);
	}
	
	@Override
	public List<SIPUserData> getSIPUserData()
	{
		return sipUserData;
	}
	
	@Override
	public void setSIPUserData(List<SIPUserData> value)
	{
		this.sipUserData = value;
	}
	
	@Override
	public SIPAccountingInformation getSIPAccountingInformation()
	{
		return sipAccountingInformation;
	}
	
	@Override
	public void setSIPAccountingInformation(SIPAccountingInformation value)
	{
		this.sipAccountingInformation = value;
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
	public Long getAuthGracePeriod() 
	{
		if(authGracePeriod == null)
			return null;
		
		return authGracePeriod.getUnsigned();
	}

	@Override
	public void setAuthGracePeriod(Long value) 
	{
		if(value == null)
			this.authGracePeriod = null;
		else
			this.authGracePeriod = new AuthGracePeriodImpl(value, null, null);
	}
	
	@Override
	public Long getAuthorizationLifetime() 
	{
		if(authorizationLifetime == null)
			return null;
		
		return authorizationLifetime.getUnsigned();
	}

	@Override
	public void setAuthorizationLifetime(Long value) 
	{
		if(value == null)
			this.authorizationLifetime = null;
		else
			this.authorizationLifetime = new AuthorizationLifetimeImpl(value, null, null);
	}

	@DiameterValidate
	public String validate()
	{
		if(authSessionState == null)
			return "Auth-Session-State is required";
		
		return super.validate();
	}
}