package com.mobius.software.telco.protocols.diameter.impl.primitives.accounting;
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

import com.mobius.software.telco.protocols.diameter.impl.primitives.DiameterAvpImpl;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.AoCService;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.AoCServiceObligatoryType;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.AoCServiceObligatoryTypeEnum;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.AoCServiceType;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.AoCServiceTypeEnum;

/**
*
* @author yulian oifa
*
*/
public class AoCServiceImpl extends DiameterAvpImpl implements AoCService
{
	private AoCServiceObligatoryType aocServiceObligatoryType;
	private AoCServiceType aocServiceType;
	
	public AoCServiceImpl()
	{
		
	}
	
	public AoCServiceObligatoryTypeEnum getAoCServiceObligatoryType()
	{
		if(aocServiceObligatoryType==null)
			return null;
		
		return aocServiceObligatoryType.getEnumerated(AoCServiceObligatoryTypeEnum.class);
	}
	
	public void setAoCServiceObligatoryType(AoCServiceObligatoryTypeEnum value)
	{
		if(value==null)
			this.aocServiceObligatoryType = null;
		else
			this.aocServiceObligatoryType = new AoCServiceObligatoryTypeImpl(value, null, null);
	}
	
	public AoCServiceTypeEnum getAoCServiceType()
	{
		if(aocServiceType==null)
			return null;
		
		return aocServiceType.getEnumerated(AoCServiceTypeEnum.class);
	}
	
	public void setAoCServiceType(AoCServiceTypeEnum value)
	{
		if(value==null)
			this.aocServiceType = null;
		else
			this.aocServiceType = new AoCServiceTypeImpl(value, null, null);			
	}
}