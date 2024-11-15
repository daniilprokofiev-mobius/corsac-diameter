package com.mobius.software.telco.protocols.diameter.impl.commands.pc2;

import java.util.Arrays;

import com.mobius.software.telco.protocols.diameter.annotations.DiameterValidate;
import com.mobius.software.telco.protocols.diameter.commands.pc2.Pc2Answer;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpNotSupportedException;
import com.mobius.software.telco.protocols.diameter.exceptions.DiameterException;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.impl.commands.common.AuthenticationAnswerImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.common.AuthSessionStateImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.pc2.ProSeRequestTypeImpl;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvp;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthSessionState;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthSessionStateEnum;
import com.mobius.software.telco.protocols.diameter.primitives.pc2.ProSeRequestType;
import com.mobius.software.telco.protocols.diameter.primitives.pc2.ProSeRequestTypeEnum;

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
public abstract class Pc2AnswerImpl extends AuthenticationAnswerImpl implements Pc2Answer
{
	protected AuthSessionState authSessionState;
	
	protected ProSeRequestType proSeRequestType;
	
	protected Pc2AnswerImpl() 
	{
		super();
	}
	
	public Pc2AnswerImpl(String originHost,String originRealm,Boolean isRetransmit, Long resultCode, String sessionID, Long authApplicationId,AuthSessionStateEnum authSessionState, ProSeRequestTypeEnum proSeRequestType) throws AvpNotSupportedException, MissingAvpException
	{
		super(originHost, originRealm, isRetransmit, resultCode, sessionID, authApplicationId);
		
		setAuthSessionState(authSessionState);
		
		setProSeRequestType(proSeRequestType);
	}

	@Override
	public AuthSessionStateEnum getAuthSessionState() 
	{
		if(authSessionState==null)
			return null;
		
		return authSessionState.getEnumerated(AuthSessionStateEnum.class);
	}

	@Override
	public void setAuthSessionState(AuthSessionStateEnum value) throws MissingAvpException 
	{
		if(value == null)
			throw new MissingAvpException("Auth-Session-State is required", Arrays.asList(new DiameterAvp[] { new AuthSessionStateImpl() }));
		
		this.authSessionState = new AuthSessionStateImpl(value, null, null);
	}

	@Override
	public ProSeRequestTypeEnum getProSeRequestType() 
	{
		if(proSeRequestType==null)
			return null;
		
		return proSeRequestType.getEnumerated(ProSeRequestTypeEnum.class);
	}

	@Override
	public void setProSeRequestType(ProSeRequestTypeEnum value) throws MissingAvpException 
	{
		if(value==null)
			throw new MissingAvpException("ProSe-Request-Type is required", Arrays.asList(new DiameterAvp[] { new ProSeRequestTypeImpl() }));
		
		this.proSeRequestType = new ProSeRequestTypeImpl(value, null, null);
	}
	
	@DiameterValidate
	public DiameterException validate()
	{
		if(authSessionState == null)
			return new MissingAvpException("Auth-Session-State is required", Arrays.asList(new DiameterAvp[] { new AuthSessionStateImpl() }));
		
		if(proSeRequestType==null)
			return new MissingAvpException("ProSe-Request-Type is required", Arrays.asList(new DiameterAvp[] { new ProSeRequestTypeImpl() }));
		
		return super.validate();
	}
}