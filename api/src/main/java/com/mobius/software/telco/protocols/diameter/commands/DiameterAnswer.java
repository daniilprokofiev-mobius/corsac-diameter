package com.mobius.software.telco.protocols.diameter.commands;

import java.util.List;
import java.util.Map;

import com.mobius.software.telco.protocols.diameter.exceptions.AvpNotSupportedException;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvpKey;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterUnknownAvp;
import com.mobius.software.telco.protocols.diameter.primitives.common.ExperimentalResult;

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
/**
*
* @author yulian oifa
*
*/
public interface DiameterAnswer extends DiameterMessage
{
	public Boolean getIsError();
	
	public Long getResultCode();
	
	void setResultCode(Long value) throws MissingAvpException;
	
	public String getErrorMessage();
	
	void setErrorMessage(String value);
	
	public String getErrorReportingHost() throws AvpNotSupportedException;
	
	void setErrorReportingHost(String value) throws AvpNotSupportedException;

	public Map<DiameterAvpKey,List<DiameterUnknownAvp>> getFailedAvp();
	
	void addFailedAvp(DiameterAvpKey avpKey, DiameterUnknownAvp failedAvp);
	
	void setFailedAvp(Map<DiameterAvpKey,List<DiameterUnknownAvp>> failedAvp);
	
	public ExperimentalResult getExperimentalResult() throws AvpNotSupportedException;
	
	void setExperimentalResult(ExperimentalResult value) throws AvpNotSupportedException;		
}