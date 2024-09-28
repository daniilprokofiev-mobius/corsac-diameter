package com.mobius.software.telco.protocols.diameter.impl.commands.sgd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.mobius.software.telco.protocols.diameter.annotations.DiameterOrder;
import com.mobius.software.telco.protocols.diameter.annotations.DiameterValidate;
import com.mobius.software.telco.protocols.diameter.commands.sgd.MTForwardShortMessageRequest;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpNotSupportedException;
import com.mobius.software.telco.protocols.diameter.exceptions.DiameterException;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.impl.primitives.common.UserNameImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.s6a.MMENumberForMTSMSImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.s6a.SGSNNumberImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.sgd.MaximumRetransmissionTimeImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.sgd.SCAddressImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.sgd.SMDeliveryStartTimeImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.sgd.SMDeliveryTimerImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.sgd.SMRPUIImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.sgd.SMSGMSCAddressImpl;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvp;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterUnknownAvp;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthSessionStateEnum;
import com.mobius.software.telco.protocols.diameter.primitives.s6a.MMENumberForMTSMS;
import com.mobius.software.telco.protocols.diameter.primitives.s6a.SGSNNumber;
import com.mobius.software.telco.protocols.diameter.primitives.sgd.MaximumRetransmissionTime;
import com.mobius.software.telco.protocols.diameter.primitives.sgd.SCAddress;
import com.mobius.software.telco.protocols.diameter.primitives.sgd.SMDeliveryStartTime;
import com.mobius.software.telco.protocols.diameter.primitives.sgd.SMDeliveryTimer;
import com.mobius.software.telco.protocols.diameter.primitives.sgd.SMRPUI;
import com.mobius.software.telco.protocols.diameter.primitives.sgd.SMSGMSCAddress;
import com.mobius.software.telco.protocols.diameter.primitives.sgd.SMSMICorrelationID;
import com.mobius.software.telco.protocols.diameter.primitives.sgd.TFRFlags;

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
public class MTForwardShortMessageRequestImpl extends SgdRequestImpl implements MTForwardShortMessageRequest
{
	private SMSMICorrelationID smSMICorrelationID;
	
	private SCAddress scAddress;
	
	private SMRPUI smRPUI;
	
	private MMENumberForMTSMS mmeNumberForMTSMS;
	
	private SGSNNumber sgsnNumber;
	
	private TFRFlags tfrFlags;
	
	private SMDeliveryTimer smDeliveryTimer;
	 
	private SMDeliveryStartTime smDeliveryStartTime;
	 
	private MaximumRetransmissionTime maximumRetransmissionTime;
	 
	private SMSGMSCAddress smsGMSCAddress;
	 
	protected MTForwardShortMessageRequestImpl() 
	{
		super();
	}
	
	public MTForwardShortMessageRequestImpl(String originHost,String originRealm,String destinationHost,String destinationRealm,Boolean isRetransmit, String sessionID,AuthSessionStateEnum authSessionState,String username,String scAddress,ByteBuf smRPUI) throws MissingAvpException, AvpNotSupportedException
	{
		super(originHost, originRealm, destinationHost, destinationRealm, isRetransmit, sessionID, authSessionState);
		
		setUsername(username);
		
		setSCAddress(scAddress);
		
		setSMRPUI(smRPUI);
	}
	
	@Override
	public void setUsername(String value) throws MissingAvpException, AvpNotSupportedException
	{
		if(value==null)
			throw new MissingAvpException("Username is required", Arrays.asList(new DiameterAvp[] { new UserNameImpl() }));
		
		super.setUsername(value);
	}
	
	@Override
	public SMSMICorrelationID getSMSMICorrelationID()
	{
		return this.smSMICorrelationID;
	}
	 
	@Override
	public void setSMSMICorrelationID(SMSMICorrelationID value)
	{
		this.smSMICorrelationID = value;
	}	
	
	@Override
	public String getSCAddress()
	{
		if(scAddress == null)
			return null;
		
		return scAddress.getAddress();
	}
	
	@Override
	public void setSCAddress(String value) throws MissingAvpException
	{
		if(value == null)
			throw new MissingAvpException("SC-Address is required", Arrays.asList(new DiameterAvp[] { new SCAddressImpl() }));
			
		this.scAddress = new SCAddressImpl(value);
	}
	
	@Override
	public ByteBuf getSMRPUI()
	{
		if(smRPUI == null)
			return null;
		
		return smRPUI.getValue();
	}
	
	@Override
	public void setSMRPUI(ByteBuf value) throws MissingAvpException
	{
		if(value == null)
			throw new MissingAvpException("SM-RP-UI is required", Arrays.asList(new DiameterAvp[] { new SMRPUIImpl() }));
		
		this.smRPUI = new SMRPUIImpl(value, null, null);
	}
	
	@Override
	public String getMMENumberForMTSMS()
	{
		if(mmeNumberForMTSMS == null)
			return null;
		
		return mmeNumberForMTSMS.getAddress();
	}
	
	@Override
	public void setMMENumberForMTSMS(String value)
	{
		if(value == null)
			this.mmeNumberForMTSMS = null;
		else
			this.mmeNumberForMTSMS = new MMENumberForMTSMSImpl(value);
	}
	
	@Override
	public String getSGSNNumber()
	{
		if(sgsnNumber == null)
			return null;
		
		return sgsnNumber.getAddress();
	}
	
	@Override
	public void setSGSNNumber(String value)
	{
		if(value == null)
			this.sgsnNumber = null;
		else
			this.sgsnNumber = new SGSNNumberImpl(value);
	}
	
	@Override
	public TFRFlags getTFRFlags()
	{
		return tfrFlags;
	}
	
	@Override
	public void setTFRFlags(TFRFlags value)
	{
		this.tfrFlags = value;
	}	
	
	@Override
	public Long getSMDeliveryTimer()
	{
		if(smDeliveryTimer == null)
			return null;
		
		return smDeliveryTimer.getUnsigned();
	}
	 
	@Override
	public void setSMDeliveryTimer(Long value)
	{
		if(value == null)
			this.smDeliveryTimer = null;
		else
			this.smDeliveryTimer = new SMDeliveryTimerImpl(value, null, null);
	}
	
	@Override
	public Date getSMDeliveryStartTime()
	{
		if(smDeliveryStartTime == null)
			return null;
		
		return smDeliveryStartTime.getDateTime();
	}
	 
	@Override
	public void setSMDeliveryStartTime(Date value)
	{
		if(value == null)
			this.smDeliveryStartTime = null;
		else
			this.smDeliveryStartTime = new SMDeliveryStartTimeImpl(value, null, null);
	}
	
	@Override
	public Date getMaximumRetransmissionTime()
	{
		if(maximumRetransmissionTime == null)
			return null;
		
		return maximumRetransmissionTime.getDateTime();
	}
	 
	@Override
	public void setMaximumRetransmissionTime(Date value)
	{
		if(value == null)
			this.maximumRetransmissionTime = null;
		else
			this.maximumRetransmissionTime = new MaximumRetransmissionTimeImpl(value, null, null);
	}
	
	@Override
	public String getSMSGMSCAddress()
	{
		if(smsGMSCAddress == null)
			return null;
		
		return smsGMSCAddress.getAddress();
	}
	 
	@Override
	public void setSMSGMSCAddress(String value)
	{
		if(value == null)
			this.smsGMSCAddress = null;
		else
			this.smsGMSCAddress = new SMSGMSCAddressImpl(value);
	}
	
	@DiameterValidate
	public DiameterException validate()
	{
		if(scAddress == null)
			return new MissingAvpException("SC-Address is required", Arrays.asList(new DiameterAvp[] { new SCAddressImpl() }));
		
		if(smRPUI == null)
			return new MissingAvpException("SM-RP-UI is required", Arrays.asList(new DiameterAvp[] { new SMRPUIImpl() }));
		
		return super.validate();
	}	
	
	@DiameterOrder
	public List<DiameterAvp> getOrderedAVPs()
	{
		List<DiameterAvp> result=new ArrayList<DiameterAvp>();
		result.add(sessionId);
		result.add(drmp);
		result.add(vendorSpecificApplicationId);
		result.add(authSessionState);
		result.add(originHost);
		result.add(originRealm);
		result.add(destinationHost);
		result.add(destinationRealm);
		result.add(username);
		
		if(supportedFeatures!=null)
			result.addAll(supportedFeatures);
		
		result.add(smSMICorrelationID);
		result.add(scAddress);
		result.add(smRPUI);
		result.add(mmeNumberForMTSMS);
		result.add(sgsnNumber);
		result.add(tfrFlags);
		result.add(smDeliveryTimer);
		result.add(smDeliveryStartTime);
		result.add(maximumRetransmissionTime);
		result.add(smsGMSCAddress);
		
		if(optionalAvps!=null)
		{
			for(List<DiameterUnknownAvp> curr:optionalAvps.values())
				result.addAll(curr);
		}
		
		if(proxyInfo!=null)
			result.addAll(proxyInfo);
		
		if(routeRecords!=null)
			result.addAll(routeRecords);
		
		return result;
	}	
}