package com.mobius.software.telco.protocols.diameter.primitives.creditcontrol;
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

import com.mobius.software.telco.protocols.diameter.AvpCodes;
import com.mobius.software.telco.protocols.diameter.annotations.DiameterAvpDefinition;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvp;

/**
*
* @author yulian oifa
*
*/

/* 8.22.  CC-Money AVP

   The CC-Money AVP (AVP Code 413) is of type Grouped and specifies the
   monetary amount in the given currency.  The Currency-Code AVP SHOULD
   be included.  The CC-Money AVP is defined as follows (per
   grouped-avp-def as defined in [RFC6733]):

         CC-Money ::= < AVP Header: 413 >
                      { Unit-Value }
                      [ Currency-Code ]
*/

@DiameterAvpDefinition(code = AvpCodes.CC_MONEY, vendorId = -1, name = "CC-Money")
public interface CCMoney extends DiameterAvp 
{
	UnitValue getUnitValue();
	
	void setUnitValue(UnitValue value) throws MissingAvpException;
	
	Long getCurrencyCode();
	
	void setCurrencyCode(Long value) throws MissingAvpException;	
}