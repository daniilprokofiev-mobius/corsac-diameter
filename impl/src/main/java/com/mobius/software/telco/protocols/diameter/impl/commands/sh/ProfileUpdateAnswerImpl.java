package com.mobius.software.telco.protocols.diameter.impl.commands.sh;

import java.util.ArrayList;
import java.util.List;

import com.mobius.software.telco.protocols.diameter.annotations.DiameterOrder;
import com.mobius.software.telco.protocols.diameter.commands.sh.ProfileUpdateAnswer;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpNotSupportedException;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.impl.primitives.cxdx.WildcardedIMPUImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.cxdx.WildcardedPublicIdentityImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.sh.DataReferenceImpl;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvp;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterUnknownAvp;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.OCOLR;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthSessionStateEnum;
import com.mobius.software.telco.protocols.diameter.primitives.cxdx.WildcardedIMPU;
import com.mobius.software.telco.protocols.diameter.primitives.cxdx.WildcardedPublicIdentity;
import com.mobius.software.telco.protocols.diameter.primitives.rfc7683.OCSupportedFeatures;
import com.mobius.software.telco.protocols.diameter.primitives.rfc8583.Load;
import com.mobius.software.telco.protocols.diameter.primitives.sh.DataReference;
import com.mobius.software.telco.protocols.diameter.primitives.sh.DataReferenceEnum;
import com.mobius.software.telco.protocols.diameter.primitives.sh.RepositoryDataID;

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
public class ProfileUpdateAnswerImpl extends ShAnswerImpl implements ProfileUpdateAnswer
{
	private WildcardedPublicIdentity wildcardedPublicIdentity;
	
	private WildcardedIMPU wildcardedIMPU;
	
	private RepositoryDataID repositoryDataID;
	
	private DataReference dataReference;
	
	private OCSupportedFeatures ocSupportedFeatures;
	
	private OCOLR ocOLR;
	
	private List<Load> load;
	
	protected ProfileUpdateAnswerImpl() 
	{
		super();
		setExperimentalResultAllowed(false);
	}
	
	public ProfileUpdateAnswerImpl(String originHost,String originRealm,Boolean isRetransmit, Long resultCode, String sessionID,AuthSessionStateEnum authSessionState) throws MissingAvpException, AvpNotSupportedException
	{
		super(originHost, originRealm, isRetransmit, resultCode, sessionID, authSessionState);
		setExperimentalResultAllowed(false);
	}
	
	public String getWildcardedPublicIdentity()
	{
		if(wildcardedPublicIdentity==null)
			return null;
		
		return wildcardedPublicIdentity.getString();
	}
	
	public void setWildcardedPublicIdentity(String value)
	{
		if(value==null)
			this.wildcardedPublicIdentity = null;
		else
			this.wildcardedPublicIdentity = new WildcardedPublicIdentityImpl(value, null, null);
	}

	public String getWildcardedIMPU()
	{
		if(wildcardedIMPU==null)
			return null;
		
		return wildcardedIMPU.getString();
	}
	
	public void setWildcardedIMPU(String value)
	{
		if(value==null)
			this.wildcardedIMPU = null;
		else
			this.wildcardedIMPU = new WildcardedIMPUImpl(value, null, null);
	}
	
	public RepositoryDataID getRepositoryDataID()
	{
		return this.repositoryDataID;
	}
	
	public void setRepositoryDataID(RepositoryDataID value)
	{
		this.repositoryDataID = value;
	}

	public DataReferenceEnum getDataReference()
	{
		if(dataReference==null)
			return null;
		
		return dataReference.getEnumerated(DataReferenceEnum.class);
	}
	
	public void setDataReference(DataReferenceEnum value)
	{
		if(value==null)
			this.dataReference = null;
		else
			this.dataReference = new DataReferenceImpl(value, null, null);
	}
	
	public OCSupportedFeatures getOCSupportedFeatures()
	{
		return this.ocSupportedFeatures;
	}
	 
	public void setOCSupportedFeatures(OCSupportedFeatures value)
	{
		this.ocSupportedFeatures = value;
	}
	 		
	public OCOLR getOCOLR()
	{
		return this.ocOLR;
	}
	 
	public void setOCOLR(OCOLR value)
	{
		this.ocOLR = value;
	}
	
	public List<Load> getLoad()
	{
		return this.load;
	}
	 
	public void setLoad(List<Load> value)
	{
		this.load = value;
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
		
		result.add(wildcardedPublicIdentity);
		result.add(wildcardedIMPU);
		result.add(repositoryDataID);
		result.add(dataReference);
		
		if(supportedFeatures!=null)
			result.addAll(supportedFeatures);
		
		result.add(ocSupportedFeatures);
		result.add(ocOLR);
		
		if(load!=null)
			result.addAll(load);
		
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