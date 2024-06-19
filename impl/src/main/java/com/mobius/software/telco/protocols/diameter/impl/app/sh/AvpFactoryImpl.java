package com.mobius.software.telco.protocols.diameter.impl.app.sh;
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

import com.mobius.software.telco.protocols.diameter.app.sh.AvpFactory;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpOccursTooManyTimesException;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.impl.primitives.cxdx.SupportedFeaturesImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.rfc7683.OCOLRImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.rfc7683.OCSupportedFeaturesImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.rfc8583.LoadImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.sh.CallReferenceInfoImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.sh.RepositoryDataIDImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.sh.RequestedNodesImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.sh.UDRFlagsImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.sh.UserIdentityImpl;
import com.mobius.software.telco.protocols.diameter.primitives.cxdx.SupportedFeatures;
import com.mobius.software.telco.protocols.diameter.primitives.rfc7683.OCOLR;
import com.mobius.software.telco.protocols.diameter.primitives.rfc7683.OCReportTypeEnum;
import com.mobius.software.telco.protocols.diameter.primitives.rfc7683.OCSupportedFeatures;
import com.mobius.software.telco.protocols.diameter.primitives.rfc8583.Load;
import com.mobius.software.telco.protocols.diameter.primitives.sh.CallReferenceInfo;
import com.mobius.software.telco.protocols.diameter.primitives.sh.RepositoryDataID;
import com.mobius.software.telco.protocols.diameter.primitives.sh.RequestedNodes;
import com.mobius.software.telco.protocols.diameter.primitives.sh.UDRFlags;
import com.mobius.software.telco.protocols.diameter.primitives.sh.UserIdentity;

import io.netty.buffer.ByteBuf;
/**
*
* @author yulian oifa
*
*/
public class AvpFactoryImpl extends com.mobius.software.telco.protocols.diameter.impl.app.commons.AvpFactoryImpl implements AvpFactory
{
	public Load getLoad()
	{
		return new LoadImpl();
	}
	
	public OCOLR getOCOLR(Long ocSequenceNumber, OCReportTypeEnum ocReportType) throws MissingAvpException
	{
		return new OCOLRImpl(ocSequenceNumber, ocReportType);
	}
	
	public OCSupportedFeatures getOCSupportedFeatures()
	{
		return new OCSupportedFeaturesImpl();
	}
	
	public SupportedFeatures getSupportedFeatures(Long vendorId, Long featureListID, Long featureList) throws MissingAvpException
	{
		return new SupportedFeaturesImpl(vendorId, featureListID, featureList);
	}
	
	public UserIdentity getUserIdentity(String publicIdentity, String msisdn, String externalIdentifier) throws MissingAvpException, AvpOccursTooManyTimesException
	{
		return new UserIdentityImpl(publicIdentity, msisdn, externalIdentifier);
	}
	
	public RepositoryDataID getRepositoryDataID(ByteBuf serviceIndication, Long sequenceNumber) throws MissingAvpException
	{
		return new RepositoryDataIDImpl(serviceIndication, sequenceNumber);
	}
	
	public CallReferenceInfo getCallReferenceInfo(ByteBuf callReferenceNumber, ByteBuf asNumber) throws MissingAvpException
	{
		return new CallReferenceInfoImpl(callReferenceNumber, asNumber);
	}
	
	public RequestedNodes getRequestedNodes()
	{
		return new RequestedNodesImpl();
	}
	
	public UDRFlags getUDRFlags()
	{
		return new UDRFlagsImpl();
	}
}
