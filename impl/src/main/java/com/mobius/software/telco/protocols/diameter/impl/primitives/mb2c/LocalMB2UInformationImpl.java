package com.mobius.software.telco.protocols.diameter.impl.primitives.mb2c;
/*
 * Mobius Software LTD
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

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import com.mobius.software.telco.protocols.diameter.annotations.DiameterValidate;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpOccursTooManyTimesException;
import com.mobius.software.telco.protocols.diameter.exceptions.DiameterException;
import com.mobius.software.telco.protocols.diameter.impl.primitives.DiameterAvpImpl;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvp;
import com.mobius.software.telco.protocols.diameter.primitives.mb2c.BMSCAddress;
import com.mobius.software.telco.protocols.diameter.primitives.mb2c.BMSCPort;
import com.mobius.software.telco.protocols.diameter.primitives.mb2c.LocalMB2UInformation;

/**
*
* @author yulian oifa
*
*/
public class LocalMB2UInformationImpl extends DiameterAvpImpl implements LocalMB2UInformation
{
	private List<BMSCAddress> bmscAddress;
	
	private BMSCPort bmscPort;
	
	public LocalMB2UInformationImpl()
	{
		
	}
	
	public List<InetAddress> getBMSCAddress()
	{
		if(bmscAddress==null || bmscAddress.size()==0)
			return null;
		
		List<InetAddress> result=new ArrayList<InetAddress>();
		for(BMSCAddress curr:bmscAddress)
			result.add(curr.getAddress());
		
		return result;
	}
	
	public void setMBMSENBIPMulticastAddress(List<InetAddress> value) throws AvpOccursTooManyTimesException
	{
		if(value!=null && value.size()>2)
		{
			List<DiameterAvp> failedAvps=new ArrayList<DiameterAvp>();
			for(InetAddress curr:value)
				failedAvps.add(new BMSCAddressImpl(curr, null, null));
			
			throw new AvpOccursTooManyTimesException("Up to 2 BMSC‑Address are allowed", failedAvps);
		}
		else if(value==null || value.size()==0)
			this.bmscAddress = null;
		else
		{
			this.bmscAddress = new ArrayList<BMSCAddress>();
			for(InetAddress curr:value)
				this.bmscAddress.add(new BMSCAddressImpl(curr, null, null));
		}
	}
	
	public Long getBMSCPort()
	{
		if(bmscPort==null)
			return null;
		
		return bmscPort.getUnsigned();
	}
	
	public void setBMSCPort(Long value)
	{
		if(value==null)
			this.bmscPort = null;
		else
			this.bmscPort = new BMSCPortImpl(value, null, null);			
	}
	
	@DiameterValidate
	public DiameterException validate()
	{
		if(bmscAddress!=null || bmscAddress.size()>2)
		{
			List<DiameterAvp> failedAvps=new ArrayList<DiameterAvp>();
			failedAvps.addAll(bmscAddress);
			return new AvpOccursTooManyTimesException("Up to 2 BMSC‑Address are allowed", failedAvps);
		}
		
		return null;
	}
}