package com.mobius.software.telco.protocols.diameter.impl.commands.nas;

import java.util.ArrayList;
import java.util.List;

import com.mobius.software.telco.protocols.diameter.annotations.DiameterOrder;
import com.mobius.software.telco.protocols.diameter.commands.nas.SessionTerminationAnswer;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpNotSupportedException;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.impl.primitives.nas.OriginAAAProtocolImpl;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvp;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterUnknownAvp;
import com.mobius.software.telco.protocols.diameter.primitives.nas.OriginAAAProtocol;
import com.mobius.software.telco.protocols.diameter.primitives.nas.OriginAAAProtocolEnum;

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
public class SessionTerminationAnswerImpl extends com.mobius.software.telco.protocols.diameter.impl.commands.common.SessionTerminationAnswerImpl implements SessionTerminationAnswer
{
	private OriginAAAProtocol originAAAProtocol;
	
	protected SessionTerminationAnswerImpl() 
	{
	}
	
	public SessionTerminationAnswerImpl(String originHost,String originRealm,Boolean isRetransmit, Long resultCode, String sessionID) throws MissingAvpException, AvpNotSupportedException
	{
		super(originHost, originRealm, isRetransmit, resultCode, sessionID);
	}

	@Override
	public OriginAAAProtocolEnum getOriginAAAProtocol() 
	{
		if(originAAAProtocol == null)
			return null;
		
		return originAAAProtocol.getEnumerated(OriginAAAProtocolEnum.class);
	}

	@Override
	public void setOriginAAAProtocol(OriginAAAProtocolEnum value) 
	{
		if(value == null)
			this.originAAAProtocol = null;
		else
			this.originAAAProtocol = new OriginAAAProtocolImpl(value, null, null);
	}
	
	@DiameterOrder
	public List<DiameterAvp> getOrderedAVPs()
	{
		List<DiameterAvp> result=new ArrayList<DiameterAvp>();
		result.add(sessionId);
		result.add(resultCode);
		result.add(originHost);
		result.add(originRealm);
		result.add(username);
		
		if(diameterClass!=null)
			result.addAll(diameterClass);
		
		result.add(errorMessage);
		result.add(errorReportingHost);
		result.add(failedAvp);
		result.add(originAAAProtocol);
		result.add(originStateId);
		
		if(redirectHost!=null)
			result.addAll(redirectHost);
		
		result.add(redirectHostUsage);
		result.add(redirectMaxCacheTime);
		
		if(proxyInfo!=null)
			result.addAll(proxyInfo);
		
		if(optionalAvps!=null)
		{
			for(List<DiameterUnknownAvp> curr:optionalAvps.values())
				result.addAll(curr);
		}
		
		return result;
	}
}