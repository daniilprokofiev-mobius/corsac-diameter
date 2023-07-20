package com.mobius.software.telco.protocols.diameter.impl.primitives.creditcontrol;
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

import com.mobius.software.telco.protocols.diameter.annotations.DiameterAvpImplementation;
import com.mobius.software.telco.protocols.diameter.annotations.DiameterValidate;
import com.mobius.software.telco.protocols.diameter.primitives.creditcontrol.Exponent;
import com.mobius.software.telco.protocols.diameter.primitives.creditcontrol.UnitValue;
import com.mobius.software.telco.protocols.diameter.primitives.creditcontrol.ValueDigits;

/**
*
* @author yulian oifa
*
*/
@DiameterAvpImplementation(code = 445L, vendorId = -1)
public class UnitValueImpl implements UnitValue 
{
	private ValueDigits valueDigits;
	
	private Exponent exponent;
	
	protected UnitValueImpl()
	{
		
	}
	
	public UnitValueImpl(Long valueDigits,Long exponent)
	{
		if(valueDigits==null)
			throw new IllegalArgumentException("Value-Digits is required");
		
		this.valueDigits = new ValueDigitsImpl(valueDigits, null, null);
		
		if(exponent!=null)
			this.exponent = new ExponentImpl(exponent, null, null);
	}
	
	public Long getValueDigits()
	{
		if(valueDigits == null)
			return null;
		
		return valueDigits.getLong();
	}
	
	public void setValueDigits(Long valueDigits)
	{
		if(valueDigits==null)
			throw new IllegalArgumentException("Value-Digits is required");
		
		this.valueDigits = new ValueDigitsImpl(valueDigits, null, null);		
	}
	
	public Long getExponent()
	{
		if(exponent == null)
			return null;
		
		return exponent.getUnsigned();
	}
	
	public void setExponent(Long exponent)
	{
		if(exponent!=null)
			this.exponent = new ExponentImpl(exponent, null, null);
		else
			this.exponent = null;
	}
	
	@DiameterValidate
	public String validate()
	{
		if(valueDigits==null)
			return "Value-Digits is required";
		
		return null;
	}
}