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
import com.mobius.software.telco.protocols.diameter.primitives.accounting.PoCUserRole;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.PoCUserRoleIDs;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.PoCUserRoleInfoUnits;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.PoCUserRoleInfoUnitsEnum;

/**
*
* @author yulian oifa
*
*/
public class PoCUserRoleImpl extends DiameterAvpImpl implements PoCUserRole
{
	private PoCUserRoleIDs pocUserRoleIds;
	private PoCUserRoleInfoUnits pocUserRoleInfoUnits;
			
	public PoCUserRoleImpl()
	{
		
	}
	
	public String getPoCUserRoleIDs()
	{
		if(pocUserRoleIds==null)
			return null;
		
		return pocUserRoleIds.getString();
	}
	
	public void setPoCUserRoleIDs(String value)
	{
		if(value==null)
			this.pocUserRoleIds = null;
		else
			this.pocUserRoleIds = new PoCUserRoleIDsImpl(value, null, null);			
	}
	
	public PoCUserRoleInfoUnitsEnum getPoCUserRoleInfoUnits()
	{
		if(pocUserRoleInfoUnits==null)
			return null;
		
		return pocUserRoleInfoUnits.getEnumerated(PoCUserRoleInfoUnitsEnum.class);
	}
	
	public void setPoCUserRoleInfoUnits(PoCUserRoleInfoUnitsEnum value)
	{
		if(value==null)
			this.pocUserRoleInfoUnits = null;
		else
			this.pocUserRoleInfoUnits = new PoCUserRoleInfoUnitsImpl(value, null, null);			
	}
}