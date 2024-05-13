package com.mobius.software.telco.protocols.diameter.impl.commands.swx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mobius.software.telco.protocols.diameter.annotations.DiameterOrder;
import com.mobius.software.telco.protocols.diameter.annotations.DiameterValidate;
import com.mobius.software.telco.protocols.diameter.commands.swx.MultimediaAuthRequest;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpNotSupportedException;
import com.mobius.software.telco.protocols.diameter.exceptions.DiameterException;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.impl.primitives.common.UserNameImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.cxdx.VisitedNetworkIdentifierImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.gx.RATTypeImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.rfc4740.SIPNumberAuthItemsImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.sta.ANIDImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.sta.ANTrustedImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.swx.AAAFailureIndicationImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.swx.SIPAuthDataItemImpl;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvp;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterUnknownAvp;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthSessionStateEnum;
import com.mobius.software.telco.protocols.diameter.primitives.cxdx.SIPAuthDataItem;
import com.mobius.software.telco.protocols.diameter.primitives.cxdx.VisitedNetworkIdentifier;
import com.mobius.software.telco.protocols.diameter.primitives.gx.RATType;
import com.mobius.software.telco.protocols.diameter.primitives.gx.RATTypeEnum;
import com.mobius.software.telco.protocols.diameter.primitives.rfc4740.SIPNumberAuthItems;
import com.mobius.software.telco.protocols.diameter.primitives.rfc7683.OCSupportedFeatures;
import com.mobius.software.telco.protocols.diameter.primitives.s6a.TerminalInformation;
import com.mobius.software.telco.protocols.diameter.primitives.sta.ANID;
import com.mobius.software.telco.protocols.diameter.primitives.sta.ANTrusted;
import com.mobius.software.telco.protocols.diameter.primitives.sta.ANTrustedEnum;
import com.mobius.software.telco.protocols.diameter.primitives.swx.AAAFailureIndication;

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
public class MultimediaAuthRequestImpl extends SwxRequestImpl implements MultimediaAuthRequest
{
	private RATType ratType;
	
	private ANTrusted anTrusted;
	
	private ANID anid;
	
	private VisitedNetworkIdentifier visitedNetworkIdentifier;
	
	private TerminalInformation terminalInformation;
	
	private SIPNumberAuthItems sipNumberAuthItems;
	
	private SIPAuthDataItem sipAuthDataItem;
	
	private AAAFailureIndication aaaFailureIndication;
	
	private OCSupportedFeatures ocSupportedFeatures;
	
	protected MultimediaAuthRequestImpl() 
	{
		super();
	}
	
	public MultimediaAuthRequestImpl(String originHost,String originRealm,String destinationHost,String destinationRealm,Boolean isRetransmit, String sessionID, AuthSessionStateEnum authSessionState,String username, Long sipNumberAuthItems,SIPAuthDataItem sIPAuthDataItem) throws MissingAvpException, AvpNotSupportedException
	{
		super(originHost, originRealm, destinationHost, destinationRealm, isRetransmit, sessionID, authSessionState);		
		
		setUsername(username);
		setSIPNumberAuthItems(sipNumberAuthItems);
		setSIPAuthDataItem(sIPAuthDataItem);
	}
	
	@Override
	public void setUsername(String value) throws MissingAvpException, AvpNotSupportedException
	{
		if(value==null)
			throw new MissingAvpException("Username is required", Arrays.asList(new DiameterAvp[] { new UserNameImpl() }));
			
		super.setUsername(value);		
	}
	
	@Override
	public RATTypeEnum getRATType()
	{
		if(ratType==null)
			return null;
		
		return this.ratType.getEnumerated(RATTypeEnum.class);
	}
	
	@Override
	public void setRATType(RATTypeEnum value)
	{
		if(value==null)
			this.ratType = null;
		else
			this.ratType = new RATTypeImpl(value, null, null);
	}
	
	@Override
	public ANTrustedEnum getANTrusted()
	{
		if(anTrusted == null)
			return null;
	
		return anTrusted.getEnumerated(ANTrustedEnum.class);
	}
	
	@Override
	public void setANTrusted(ANTrustedEnum value)
	{
		if(value==null)
			this.anTrusted = null;
		else
			this.anTrusted = new ANTrustedImpl(value, null, null);
	}
	
	@Override
	public String getANID()
	{
		if(anid==null)
			return null;
		
		return this.anid.getString();
	}
	
	@Override
	public void setANID(String value)
	{
		if(value==null)
			this.anid = null;
		else
			this.anid = new ANIDImpl(value, null, null);
	}
	
	@Override
	public ByteBuf getVisitedNetworkIdentifier() 
	{
		if(visitedNetworkIdentifier==null)
			return null;
		
		return visitedNetworkIdentifier.getValue();
	}
	
	@Override
	public void setVisitedNetworkIdentifier(ByteBuf value)
	{
		if(value == null)
			this.visitedNetworkIdentifier = null;
		else
			this.visitedNetworkIdentifier = new VisitedNetworkIdentifierImpl(value, null, null);
	}
	
	@Override
	public TerminalInformation getTerminalInformation()
	{
		return this.terminalInformation;
	}
	
	@Override
	public void setTerminalInformation(TerminalInformation value)
	{
		this.terminalInformation = value;
	}
	
	@Override
	public Long getSIPNumberAuthItems()
	{
		if(sipNumberAuthItems == null)
			return null;
		
		return sipNumberAuthItems.getUnsigned();
	}
	
	@Override
	public void setSIPNumberAuthItems(Long value) throws MissingAvpException
	{
		if(value == null)
			throw new MissingAvpException("SIP-Number-Auth-Items is required", Arrays.asList(new DiameterAvp[] { new SIPNumberAuthItemsImpl() }));
		
		this.sipNumberAuthItems = new SIPNumberAuthItemsImpl(value, null, null);
	}
	
	@Override
	public SIPAuthDataItem getSIPAuthDataItem()
	{
		return this.sipAuthDataItem;
	}
	
	@Override
	public void setSIPAuthDataItem(SIPAuthDataItem value) throws MissingAvpException
	{
		if(value == null)
			throw new MissingAvpException("SIP-Auth-Data-Item is required", Arrays.asList(new DiameterAvp[] { new SIPAuthDataItemImpl() }));
			
		this.sipAuthDataItem = value;
	}

	@Override
	public Long getAAAFailureIndication()
	{
		if(aaaFailureIndication==null)
			return null;
		
		return aaaFailureIndication.getUnsigned();
	}
	
	@Override
	public void setAAAFailureIndication(Long value)
	{
		if(value == null)
			this.aaaFailureIndication = null;
		else
			this.aaaFailureIndication = new AAAFailureIndicationImpl(value, null, null);
	}
	
	@Override
	public OCSupportedFeatures getOCSupportedFeatures()
	{
		return this.ocSupportedFeatures;
	}
	 
	@Override
	public void setOCSupportedFeatures(OCSupportedFeatures value)
	{
		this.ocSupportedFeatures = value;
	}
	
	@DiameterValidate
	public DiameterException validate()
	{
		if(username == null)
			return new MissingAvpException("Username is required", Arrays.asList(new DiameterAvp[] { new UserNameImpl() }));
		
		if(sipNumberAuthItems == null)
			return new MissingAvpException("SIP-Number-Auth-Items is required", Arrays.asList(new DiameterAvp[] { new SIPNumberAuthItemsImpl() }));
		
		if(sipAuthDataItem == null)
			return new MissingAvpException("SIP-Auth-Data-Item is required", Arrays.asList(new DiameterAvp[] { new SIPAuthDataItemImpl() }));
		
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
		result.add(ratType);
		result.add(anTrusted);
		result.add(anid);
		result.add(visitedNetworkIdentifier);
		result.add(terminalInformation);
		result.add(sipAuthDataItem);
		result.add(sipNumberAuthItems);
		result.add(aaaFailureIndication);
		result.add(ocSupportedFeatures);
		
		if(supportedFeatures!=null)
			result.addAll(supportedFeatures);
		
		if(optionalAvps!=null)
		{
			for(List<DiameterUnknownAvp> curr:optionalAvps.values())
				result.addAll(curr);
		}				
		
		return result;
	}
}