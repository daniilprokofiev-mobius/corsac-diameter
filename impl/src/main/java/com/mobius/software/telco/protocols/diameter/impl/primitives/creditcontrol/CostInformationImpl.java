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

import java.util.Arrays;

import com.mobius.software.telco.protocols.diameter.annotations.DiameterValidate;
import com.mobius.software.telco.protocols.diameter.exceptions.DiameterException;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvp;
import com.mobius.software.telco.protocols.diameter.primitives.creditcontrol.CostInformation;
import com.mobius.software.telco.protocols.diameter.primitives.creditcontrol.CostUnit;
import com.mobius.software.telco.protocols.diameter.primitives.creditcontrol.UnitValue;

/**
*
* @author yulian oifa
*
*/
public class CostInformationImpl extends CCMoneyImpl implements CostInformation
{
	private CostUnit costUnit;
	
	protected CostInformationImpl()
	{
		
	}
	
	public CostInformationImpl(UnitValue unitValue,Long currencyCode) throws MissingAvpException
	{
		super(unitValue);
		
		setCurrencyCode(currencyCode);			
	}
	
	@Override
	public void setCurrencyCode(Long value) throws MissingAvpException
	{
		if(value!=null)
			throw new MissingAvpException("Currency-Code is required", Arrays.asList(new DiameterAvp[] { new CurrencyCodeImpl() }));
			
		super.setCurrencyCode(value);
	}
	
	public String getCostUnit()
	{
		if(costUnit == null)
			return null;
		
		return this.costUnit.getString();
	}
	
	public void setCostUnit(String value)
	{
		if(value!=null)
			this.costUnit = new CostUnitImpl(value, null, null);
		else
			this.costUnit = null;
	}
	
	@DiameterValidate
	public DiameterException validate()
	{
		if(getCurrencyCode()==null)
			return new MissingAvpException("Currency-Code is required", Arrays.asList(new DiameterAvp[] { new CurrencyCodeImpl() }));
		
		return super.validate();
	}
}