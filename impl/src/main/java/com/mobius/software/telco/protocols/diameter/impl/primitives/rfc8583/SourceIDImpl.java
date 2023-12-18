package com.mobius.software.telco.protocols.diameter.impl.primitives.rfc8583;
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
import com.mobius.software.telco.protocols.diameter.impl.primitives.DiameterIdentityImpl;
import com.mobius.software.telco.protocols.diameter.primitives.rfc8583.SourceID;

/**
*
* @author yulian oifa
*
*/
@DiameterAvpImplementation(code = 649L, vendorId = -1L)
public class SourceIDImpl extends DiameterIdentityImpl implements SourceID
{
	protected SourceIDImpl() 
	{
		super();
	}
		
	protected SourceIDImpl(Integer minLength,Integer maxLength) 
	{
		super(minLength,maxLength);		
	}
		
	public SourceIDImpl(String value,Integer minLength,Integer maxLength) 
	{
		super(value,minLength,maxLength);			
	}
}