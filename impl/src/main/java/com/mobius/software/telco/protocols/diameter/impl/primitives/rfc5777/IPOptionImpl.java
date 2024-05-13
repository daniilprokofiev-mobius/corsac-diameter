package com.mobius.software.telco.protocols.diameter.impl.primitives.rfc5777;
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

import java.util.Arrays;

import com.mobius.software.telco.protocols.diameter.annotations.DiameterValidate;
import com.mobius.software.telco.protocols.diameter.exceptions.DiameterException;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.impl.primitives.DiameterGroupedAvpImpl;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvp;
import com.mobius.software.telco.protocols.diameter.primitives.rfc5777.IPOption;
import com.mobius.software.telco.protocols.diameter.primitives.rfc5777.IPOptionType;
import com.mobius.software.telco.protocols.diameter.primitives.rfc5777.IPOptionTypeEnum;
import com.mobius.software.telco.protocols.diameter.primitives.rfc5777.IPOptionValue;
import com.mobius.software.telco.protocols.diameter.primitives.rfc5777.Negated;
import com.mobius.software.telco.protocols.diameter.primitives.rfc5777.NegatedEnum;

import io.netty.buffer.ByteBuf;

/**
*
* @author yulian oifa
*
*/

public class IPOptionImpl extends DiameterGroupedAvpImpl implements IPOption
{
	private IPOptionType ipOptionType;
	
	private IPOptionValue ipOptionValue;
	
	private Negated negated;
	
	protected IPOptionImpl() 
	{
	}
	
	public IPOptionImpl(IPOptionTypeEnum ipOptionType) throws MissingAvpException
	{
		setIPOptionType(ipOptionType);
	}
	
	public IPOptionTypeEnum getIPOptionType()
	{
		if(this.ipOptionType==null)
			return null;
		
		return this.ipOptionType.getEnumerated(IPOptionTypeEnum.class);
	}
	
	public void setIPOptionType(IPOptionTypeEnum value) throws MissingAvpException
	{
		if(value==null)
			throw new MissingAvpException("IP-Option-Type is required", Arrays.asList(new DiameterAvp[] { new IPOptionTypeImpl() }));
			
		this.ipOptionType = new IPOptionTypeImpl(value, null, null);	
	}
	
	public ByteBuf getIPOptionValue()
	{
		if(ipOptionValue==null)
			return null;
		
		return this.ipOptionValue.getValue();
	}
	
	public void setIPOptionValue(ByteBuf value)
	{
		if(value==null)
			this.ipOptionValue = null;
		else
			this.ipOptionValue = new IPOptionValueImpl(value, null, null);
	}
	
	public NegatedEnum getNegated()
	{
		if(negated==null)
			return null;
		
		return this.negated.getEnumerated(NegatedEnum.class);
	}
	
	public void setNegated(NegatedEnum value)
	{
		if(value==null)
			this.negated = null;
		else
			this.negated = new NegatedImpl(value, null, null);
	}
	
	@DiameterValidate
	public DiameterException validate()
	{
		if(ipOptionType==null)
			return new MissingAvpException("IP-Option-Type is required", Arrays.asList(new DiameterAvp[] { new IPOptionTypeImpl() }));
		
		return null;
	}
}