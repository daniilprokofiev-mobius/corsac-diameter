package com.mobius.software.telco.protocols.diameter.impl.commands.pc6;

import com.mobius.software.telco.protocols.diameter.annotations.DiameterCommandImplementation;
import com.mobius.software.telco.protocols.diameter.annotations.DiameterValidate;
import com.mobius.software.telco.protocols.diameter.commands.pc6.ProSeMatchReportInfoRequest;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthSessionStateEnum;
import com.mobius.software.telco.protocols.diameter.primitives.pc6.MatchReportInfo;

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
@DiameterCommandImplementation(applicationId = 16777340, commandCode = 8388671, request = true)
public class ProSeMatchReportInfoRequestImpl extends Pc6RequestImpl implements ProSeMatchReportInfoRequest
{
	private MatchReportInfo matchReportInfo;
	
	protected ProSeMatchReportInfoRequestImpl() 
	{
		super();
	}
	
	public ProSeMatchReportInfoRequestImpl(String originHost,String originRealm,String destinationHost,String destinationRealm,Boolean isRetransmit, String sessionID,AuthSessionStateEnum authSessionState,MatchReportInfo matchReportInfo)
	{
		super(originHost, originRealm, destinationHost, destinationRealm, isRetransmit, sessionID, authSessionState);
		
		setMatchReportInfo(matchReportInfo);
	}
	
	@Override	
	public MatchReportInfo getMatchReportInfo()
	{
		return matchReportInfo;
	}
	 
	@Override	
	public void setMatchReportInfo(MatchReportInfo value)
	{
		if(value == null)
			throw new IllegalArgumentException("Match-Report-Info is required");
		
		this.matchReportInfo = value;
	}
		
	@DiameterValidate
	public String validate()
	{
		if(matchReportInfo == null)
			return "Match-Report-Info is required";
		
		return super.validate();
	}	
}