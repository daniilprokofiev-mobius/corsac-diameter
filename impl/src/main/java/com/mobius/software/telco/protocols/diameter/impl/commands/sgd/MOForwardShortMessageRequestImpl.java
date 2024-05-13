package com.mobius.software.telco.protocols.diameter.impl.commands.sgd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mobius.software.telco.protocols.diameter.annotations.DiameterOrder;
import com.mobius.software.telco.protocols.diameter.annotations.DiameterValidate;
import com.mobius.software.telco.protocols.diameter.commands.sgd.MOForwardShortMessageRequest;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpNotSupportedException;
import com.mobius.software.telco.protocols.diameter.exceptions.DiameterException;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.impl.primitives.s6m.UserIdentifierImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.sgd.SCAddressImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.sgd.SMRPUIImpl;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvp;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterUnknownAvp;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthSessionStateEnum;
import com.mobius.software.telco.protocols.diameter.primitives.s6a.EPSLocationInformation;
import com.mobius.software.telco.protocols.diameter.primitives.s6c.SMDeliveryOutcome;
import com.mobius.software.telco.protocols.diameter.primitives.s6m.UserIdentifier;
import com.mobius.software.telco.protocols.diameter.primitives.sgd.OFRFlags;
import com.mobius.software.telco.protocols.diameter.primitives.sgd.SCAddress;
import com.mobius.software.telco.protocols.diameter.primitives.sgd.SMRPUI;
import com.mobius.software.telco.protocols.diameter.primitives.sgd.SMSMICorrelationID;

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
public class MOForwardShortMessageRequestImpl extends SgdRequestImpl implements MOForwardShortMessageRequest
{
	private SCAddress scAddress;
	
	private OFRFlags ofrFlags;
	
	private UserIdentifier userIdentifier;
	
	private EPSLocationInformation epsLocationInformation;
	
	private SMRPUI smRPUI;
	
	private SMSMICorrelationID smSMICorrelationID;
	
	private SMDeliveryOutcome smDeliveryOutcome;
	
	protected MOForwardShortMessageRequestImpl() 
	{
		super();
	}
	
	public MOForwardShortMessageRequestImpl(String originHost,String originRealm,String destinationHost,String destinationRealm,Boolean isRetransmit, String sessionID,AuthSessionStateEnum authSessionState, UserIdentifier userIdentifier, ByteBuf smRPUI) throws MissingAvpException, AvpNotSupportedException
	{
		super(originHost, originRealm, destinationHost, destinationRealm, isRetransmit, sessionID, authSessionState);		
		
		setUserIdentifier(userIdentifier);

		setSMRPUI(smRPUI);		
	}	
	
	@Override
	public String getSCAddress()
	{
		if(scAddress == null)
			return null;
		
		return scAddress.getAddress();
	}
	
	@Override
	public void setSCAddress(String value)
	{
		if(value == null)
			this.scAddress = null;
		else
			this.scAddress = new SCAddressImpl(value);
	}	
	
	@Override
	public OFRFlags getOFRFlags()
	{
		return ofrFlags;
	}
	
	@Override
	public void setOFRFlags(OFRFlags value)
	{
		this.ofrFlags = value;
	}
	
	@Override
	public UserIdentifier getUserIdentifier() 
	{
		return userIdentifier;
	}
	
	@Override
	public void setUserIdentifier(UserIdentifier value) throws MissingAvpException
	{
		if(value == null)
			throw new MissingAvpException("User-Identifier is required", Arrays.asList(new DiameterAvp[] { new UserIdentifierImpl() }));
		
		this.userIdentifier = value;
	}
	
	@Override
	public EPSLocationInformation getEPSLocationInformation()
	{
		return this.epsLocationInformation;
	}
	 
	@Override
	public void setEPSLocationInformation(EPSLocationInformation value)
	{
		this.epsLocationInformation = value;
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
	public SMSMICorrelationID getSMSMICorrelationID()
	{
		return smSMICorrelationID;
	}
	
	@Override
	public void setSMSMICorrelationID(SMSMICorrelationID value)
	{
		this.smSMICorrelationID = value;
	}
	
	@Override
	public SMDeliveryOutcome getSMDeliveryOutcome()
	{
		return smDeliveryOutcome;
	}
	
	@Override
	public void setSMDeliveryOutcome(SMDeliveryOutcome value)
	{
		this.smDeliveryOutcome = value;
	}
	
	@DiameterValidate
	public DiameterException validate()
	{
		if(userIdentifier == null)
			return new MissingAvpException("User-Identifier is required", Arrays.asList(new DiameterAvp[] { new UserIdentifierImpl() }));
		
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
		result.add(scAddress);
		result.add(ofrFlags);
		
		if(supportedFeatures!=null)
			result.addAll(supportedFeatures);
		
		result.add(userIdentifier);
		result.add(epsLocationInformation);
		result.add(smRPUI);
		result.add(smSMICorrelationID);
		result.add(smDeliveryOutcome);
		
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