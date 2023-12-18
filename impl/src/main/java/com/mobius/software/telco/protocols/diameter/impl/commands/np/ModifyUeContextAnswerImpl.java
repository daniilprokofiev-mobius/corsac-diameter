package com.mobius.software.telco.protocols.diameter.impl.commands.np;

import java.util.List;

import com.mobius.software.telco.protocols.diameter.annotations.DiameterCommandImplementation;
import com.mobius.software.telco.protocols.diameter.commands.np.ModifyUeContextAnswer;
import com.mobius.software.telco.protocols.diameter.impl.primitives.np.ConditionalRestrictionImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.np.RUCIActionImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.np.ReportingRestrictionImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.s9a.PCRFAddressImpl;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthSessionStateEnum;
import com.mobius.software.telco.protocols.diameter.primitives.np.ConditionalRestriction;
import com.mobius.software.telco.protocols.diameter.primitives.np.CongestionLevelDefinition;
import com.mobius.software.telco.protocols.diameter.primitives.np.RUCIAction;
import com.mobius.software.telco.protocols.diameter.primitives.np.RUCIActionEnum;
import com.mobius.software.telco.protocols.diameter.primitives.np.ReportingRestriction;
import com.mobius.software.telco.protocols.diameter.primitives.np.ReportingRestrictionEnum;
import com.mobius.software.telco.protocols.diameter.primitives.s9a.PCRFAddress;

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
@DiameterCommandImplementation(applicationId = 16777342, commandCode = 8388722, request = false)
public class ModifyUeContextAnswerImpl extends NpAnswerImpl implements ModifyUeContextAnswer
{
	private ReportingRestriction reportingRestriction;
	 
	private ConditionalRestriction conditionalRestriction;
	 
	private RUCIAction ruciAction;
	 
	private List<CongestionLevelDefinition> congestionLevelDefinition;
	
	private PCRFAddress pcrfAddress;
	
	protected ModifyUeContextAnswerImpl() 
	{
		super();
	}
	
	public ModifyUeContextAnswerImpl(String originHost,String originRealm,Boolean isRetransmit, Long resultCode, String sessionID,  AuthSessionStateEnum authSessionState)
	{
		super(originHost, originRealm, isRetransmit, resultCode, sessionID, authSessionState);
	}
	
	@Override
	public ReportingRestrictionEnum getReportingRestriction()
	{
		if(reportingRestriction==null)
			return null;
		
		return reportingRestriction.getEnumerated(ReportingRestrictionEnum.class);
	}
	 
	@Override
	public void setReportingRestriction(ReportingRestrictionEnum value)
	{
		if(value==null)
			this.reportingRestriction = null;
		else
			this.reportingRestriction = new ReportingRestrictionImpl(value, null, null);
	}
	
	@Override
	public Long getConditionalRestriction()
	{
		if(conditionalRestriction==null)
			return null;
		
		return conditionalRestriction.getUnsigned();
	}
	 
	@Override
	public void setConditionalRestriction(Long value)
	{
		if(value==null)
			this.conditionalRestriction = null;
		else
			this.conditionalRestriction = new ConditionalRestrictionImpl(value, null, null);
	}
	 		
	@Override
	public RUCIActionEnum getRUCIAction()
	{
		if(ruciAction==null)
			return null;
		
		return ruciAction.getEnumerated(RUCIActionEnum.class);
	}
	 
	@Override
	public void setRUCIAction(RUCIActionEnum value)
	{
		if(value==null)
			this.ruciAction = null;
		else
			this.ruciAction = new RUCIActionImpl(value, null, null);
	}
	
	@Override
	public List<CongestionLevelDefinition> getCongestionLevelDefinition()
	{
		return congestionLevelDefinition;
	}
	
	@Override
	public void setCongestionLevelDefinition(List<CongestionLevelDefinition> value)
	{
		this.congestionLevelDefinition = value;
	}
					 
	@Override
	public String getPCRFAddress()
	{
		if(pcrfAddress==null)
			return null;
		
		return pcrfAddress.getIdentity();
	}
	
	@Override
	public void setPCRFAddress(String value)
	{
		if(value==null)
			this.pcrfAddress = null;
		else
			this.pcrfAddress = new PCRFAddressImpl(value, null, null);
	}
}