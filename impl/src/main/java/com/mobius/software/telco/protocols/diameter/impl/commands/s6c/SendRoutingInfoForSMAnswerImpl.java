package com.mobius.software.telco.protocols.diameter.impl.commands.s6c;

import java.util.ArrayList;
import java.util.List;

import com.mobius.software.telco.protocols.diameter.annotations.DiameterOrder;
import com.mobius.software.telco.protocols.diameter.commands.s6c.SendRoutingInfoForSMAnswer;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpNotSupportedException;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.impl.primitives.s6c.MMEAbsentUserDiagnosticSMImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.s6c.MSCAbsentUserDiagnosticSMImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.s6c.SGSNAbsentSubscriberDiagnosticSMImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.s6c.SMSF3GPPAbsentUserDiagnosticSMImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.s6c.SMSFNon3GPPAbsentUserDiagnosticSMImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.slh.LMSIImpl;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvp;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterUnknownAvp;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthSessionStateEnum;
import com.mobius.software.telco.protocols.diameter.primitives.s6c.AdditionalServingNode;
import com.mobius.software.telco.protocols.diameter.primitives.s6c.MMEAbsentUserDiagnosticSM;
import com.mobius.software.telco.protocols.diameter.primitives.s6c.MSCAbsentUserDiagnosticSM;
import com.mobius.software.telco.protocols.diameter.primitives.s6c.MWDStatus;
import com.mobius.software.telco.protocols.diameter.primitives.s6c.SGSNAbsentSubscriberDiagnosticSM;
import com.mobius.software.telco.protocols.diameter.primitives.s6c.SMSF3GPPAbsentUserDiagnosticSM;
import com.mobius.software.telco.protocols.diameter.primitives.s6c.SMSF3GPPAddress;
import com.mobius.software.telco.protocols.diameter.primitives.s6c.SMSFNon3GPPAbsentUserDiagnosticSM;
import com.mobius.software.telco.protocols.diameter.primitives.s6c.SMSFNon3GPPAddress;
import com.mobius.software.telco.protocols.diameter.primitives.s6c.ServingNode;
import com.mobius.software.telco.protocols.diameter.primitives.s6m.UserIdentifier;
import com.mobius.software.telco.protocols.diameter.primitives.slh.LMSI;

import io.netty.buffer.ByteBuf;

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
public class SendRoutingInfoForSMAnswerImpl extends S6cAnswerImpl implements SendRoutingInfoForSMAnswer
{
	private ServingNode servingNode;
	
	private AdditionalServingNode additionalServingNode;
	
	private SMSF3GPPAddress smsf3GPPAddress;
	
	private SMSFNon3GPPAddress smsfNon3GPPAddress;
	
	private LMSI lmsi;
	
	private UserIdentifier userIdentifier;
	
	private MWDStatus mwdStatus;
	 
	private MMEAbsentUserDiagnosticSM mmeAbsentUserDiagnosticSM;
	
	private MSCAbsentUserDiagnosticSM mscAbsentUserDiagnosticSM;
	
	private SGSNAbsentSubscriberDiagnosticSM sgsnAbsentUserDiagnosticSM;
	 
	private SMSF3GPPAbsentUserDiagnosticSM smsf3GPPAbsentUserDiagnosticSM;
	 
	private SMSFNon3GPPAbsentUserDiagnosticSM smsfNon3GPPAbsentUserDiagnosticSM;
	
	protected SendRoutingInfoForSMAnswerImpl() 
	{
		super();
		setExperimentalResultAllowed(true);
	}
	
	public SendRoutingInfoForSMAnswerImpl(String originHost,String originRealm,Boolean isRetransmit, Long resultCode, String sessionID,  AuthSessionStateEnum authSessionState) throws MissingAvpException, AvpNotSupportedException
	{
		super(originHost, originRealm, isRetransmit, resultCode, sessionID, authSessionState);
		setExperimentalResultAllowed(true);
	}
		
	@Override
	public ServingNode getServingNode()
	{
		return servingNode;
	}
	
	@Override
	public void setServingNode(ServingNode value)
	{
		this.servingNode = value;
	}
	
	@Override
	public AdditionalServingNode getAdditionalServingNode()
	{
		return this.additionalServingNode;
	}
	 
	@Override
	public void setAdditionalServingNode(AdditionalServingNode value)
	{
		this.additionalServingNode = value;
	}
	 		
	@Override
	public SMSF3GPPAddress getSMSF3GPPAddress()
	{
		return this.smsf3GPPAddress;
	}
	 
	@Override
	public void setSMSF3GPPAddress(SMSF3GPPAddress value)
	{
		this.smsf3GPPAddress = value;
	}
	
	@Override
	public SMSFNon3GPPAddress getSMSFNon3GPPAddress()
	{
		return this.smsfNon3GPPAddress;
	}
	 
	@Override
	public void setSMSFNon3GPPAddress(SMSFNon3GPPAddress value)
	{
		this.smsfNon3GPPAddress = value;
	}
	
	@Override
	public ByteBuf getLMSI()
	{
		if(lmsi==null)
			return null;
		
		return lmsi.getValue();
	}
	
	@Override
	public void setLMSI(ByteBuf value)
	{
		if(value==null)
			this.lmsi = null;
		else
			this.lmsi = new LMSIImpl(value, null, null);
	}
	
	@Override
	public UserIdentifier getUserIdentifier() 
	{
		return userIdentifier;
	}
	
	@Override
	public void setUserIdentifier(UserIdentifier value)
	{
		this.userIdentifier = value;
	}
	 		
	@Override
	public MWDStatus getMWDStatus()
	{
		return mwdStatus;
	}
	 
	@Override
	public void setMWDStatus(MWDStatus value)
	{
		this.mwdStatus = value;
	}
	 		
	@Override
	public Long getMMEAbsentUserDiagnosticSM()
	{
		if(mmeAbsentUserDiagnosticSM==null)
			return null;
		
		return mmeAbsentUserDiagnosticSM.getUnsigned();
	}
	 
	@Override
	public void setMMEAbsentUserDiagnosticSM(Long value)
	{
		if(value==null)
			this.mmeAbsentUserDiagnosticSM = null;
		else
			this.mmeAbsentUserDiagnosticSM = new MMEAbsentUserDiagnosticSMImpl(value, null, null);
	}
	
	@Override
	public Long getMSCAbsentUserDiagnosticSM()
	{
		if(mscAbsentUserDiagnosticSM==null)
			return null;
		
		return mscAbsentUserDiagnosticSM.getUnsigned();
	}
	
	@Override
	public void setMSCAbsentUserDiagnosticSM(Long value)
	{
		if(value==null)
			this.mscAbsentUserDiagnosticSM = null;
		else
			this.mscAbsentUserDiagnosticSM = new MSCAbsentUserDiagnosticSMImpl(value, null, null);
	}
	
	@Override
	public Long getSGSNAbsentUserDiagnosticSM()
	{
		if(sgsnAbsentUserDiagnosticSM==null)
			return null;
		
		return sgsnAbsentUserDiagnosticSM.getUnsigned();
	}
	 
	@Override
	public void setSGSNAbsentUserDiagnosticSM(Long value)
	{
		if(value==null)
			this.sgsnAbsentUserDiagnosticSM = null;
		else
			this.sgsnAbsentUserDiagnosticSM = new SGSNAbsentSubscriberDiagnosticSMImpl(value, null, null);
	}
		
	@Override
	public Long getSMSF3GPPAbsentUserDiagnosticSM()
	{
		if(smsf3GPPAbsentUserDiagnosticSM==null)
			return null;
		
		return smsf3GPPAbsentUserDiagnosticSM.getUnsigned();
	}
	 
	@Override
	public void setSMSF3GPPAbsentUserDiagnosticSM(Long value)
	{
		if(value==null)
			this.smsf3GPPAbsentUserDiagnosticSM = null;
		else
			this.smsf3GPPAbsentUserDiagnosticSM = new SMSF3GPPAbsentUserDiagnosticSMImpl(value, null, null);
	}
	
	@Override
	public Long getSMSFNon3GPPAbsentUserDiagnosticSM()
	{
		if(smsfNon3GPPAbsentUserDiagnosticSM==null)
			return null;
		
		return smsfNon3GPPAbsentUserDiagnosticSM.getUnsigned();
	}
	 
	@Override
	public void setSMSFNon3GPPAbsentUserDiagnosticSM(Long value)
	{
		if(value==null)
			this.smsfNon3GPPAbsentUserDiagnosticSM = null;
		else
			this.smsfNon3GPPAbsentUserDiagnosticSM = new SMSFNon3GPPAbsentUserDiagnosticSMImpl(value, null, null);
	}
	
	@DiameterOrder
	public List<DiameterAvp> getOrderedAVPs()
	{
		List<DiameterAvp> result=new ArrayList<DiameterAvp>();
		result.add(sessionId);
		result.add(drmp);
		result.add(vendorSpecificApplicationId);
		result.add(resultCode);
		result.add(experimentalResult);
		result.add(authSessionState);
		result.add(originHost);
		result.add(originRealm);
		result.add(username);
		
		if(supportedFeatures!=null)
			result.addAll(supportedFeatures);
		
		result.add(servingNode);
		result.add(additionalServingNode);
		result.add(smsf3GPPAddress);
		result.add(smsfNon3GPPAddress);
		result.add(lmsi);
		result.add(userIdentifier);
		result.add(mwdStatus);
		result.add(mmeAbsentUserDiagnosticSM);
		result.add(mscAbsentUserDiagnosticSM);
		result.add(sgsnAbsentUserDiagnosticSM);
		result.add(smsf3GPPAbsentUserDiagnosticSM);
		result.add(smsfNon3GPPAbsentUserDiagnosticSM);
		
		if(optionalAvps!=null)
		{
			for(List<DiameterUnknownAvp> curr:optionalAvps.values())
				result.addAll(curr);
		}
		
		result.add(failedAvp);
		
		if(proxyInfo!=null)
			result.addAll(proxyInfo);
		
		if(routeRecords!=null)
			result.addAll(routeRecords);
		
		return result;
	}
}