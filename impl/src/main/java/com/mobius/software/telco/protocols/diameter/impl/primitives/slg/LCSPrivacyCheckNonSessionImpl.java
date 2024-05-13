package com.mobius.software.telco.protocols.diameter.impl.primitives.slg;
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
import com.mobius.software.telco.protocols.diameter.impl.primitives.DiameterAvpImpl;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvp;
import com.mobius.software.telco.protocols.diameter.primitives.slg.LCSPrivacyCheck;
import com.mobius.software.telco.protocols.diameter.primitives.slg.LCSPrivacyCheckEnum;
import com.mobius.software.telco.protocols.diameter.primitives.slg.LCSPrivacyCheckNonSession;

/**
*
* @author yulian oifa
*
*/
public class LCSPrivacyCheckNonSessionImpl extends DiameterAvpImpl implements LCSPrivacyCheckNonSession
{
	private LCSPrivacyCheck lcsPrivacyCheck;
	
	protected LCSPrivacyCheckNonSessionImpl() 
	{
	}
	
	public LCSPrivacyCheckNonSessionImpl(LCSPrivacyCheckEnum lcsPrivacyCheck) throws MissingAvpException
	{
		setLCSPrivacyCheck(lcsPrivacyCheck);
	}
	
	public LCSPrivacyCheckEnum getLCSPrivacyCheck()
	{
		if(lcsPrivacyCheck==null)
			return null;
		
		return lcsPrivacyCheck.getEnumerated(LCSPrivacyCheckEnum.class);
	}
	
	public void setLCSPrivacyCheck(LCSPrivacyCheckEnum value) throws MissingAvpException
	{
		if(value==null)
			throw new MissingAvpException("LCS-Privacy-Check is required", Arrays.asList(new DiameterAvp[] { new LCSPrivacyCheckImpl() }));
			
		this.lcsPrivacyCheck = new LCSPrivacyCheckImpl(value, null, null);
	}
	
	@DiameterValidate
	public DiameterException validate()
	{
		if(lcsPrivacyCheck==null)
			return new MissingAvpException("LCS-Privacy-Check is required", Arrays.asList(new DiameterAvp[] { new LCSPrivacyCheckImpl() }));
		
		return null;
	}
}