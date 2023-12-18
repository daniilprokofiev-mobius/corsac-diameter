package com.mobius.software.telco.protocols.diameter.primitives.creditcontrol.huawei;
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

import com.mobius.software.telco.protocols.diameter.annotations.DiameterAvpDefinition;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterUnsigned32;
import com.mobius.software.telco.protocols.diameter.primitives.KnownVendorIDs;

/**
*
* @author yulian oifa
*
*/
/*
 * 	3.2.130  Account-Type AVP

	AVP Name
	Account-Type

	AVP Code
	20372

	AVP Data Type
	Unsigned32

	Vendor ID
	2011

	Description
	Indicates the type of an account.

	The specified recharge account type determines that the recharge amount is added to the prepaid account or postpaid account. 
	For the hybrid subscriber, if no recharge account type is specified, the recharge amount is added to the prepaid account by default.
 */
@DiameterAvpDefinition(code = 20324L, vendorId = KnownVendorIDs.HUAWEI_ID, name = "Account-Type")
public interface AccountType extends DiameterUnsigned32
{
}