package com.mobius.software.telco.protocols.diameter.commands.gx;
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

import java.util.Date;
import java.util.List;

import com.mobius.software.telco.protocols.diameter.ApplicationIDs;
import com.mobius.software.telco.protocols.diameter.CommandCodes;
import com.mobius.software.telco.protocols.diameter.annotations.DiameterCommandDefinition;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.primitives.common.ReAuthRequestTypeEnum;
import com.mobius.software.telco.protocols.diameter.primitives.gx.CSGInformationReportingEnum;
import com.mobius.software.telco.protocols.diameter.primitives.gx.ChargingRuleInstall;
import com.mobius.software.telco.protocols.diameter.primitives.gx.ChargingRuleRemove;
import com.mobius.software.telco.protocols.diameter.primitives.gx.ConditionalPolicyInformation;
import com.mobius.software.telco.protocols.diameter.primitives.gx.DefaultEPSBearerQoS;
import com.mobius.software.telco.protocols.diameter.primitives.gx.DefaultQoSInformation;
import com.mobius.software.telco.protocols.diameter.primitives.gx.EventReportIndication;
import com.mobius.software.telco.protocols.diameter.primitives.gx.EventTriggerEnum;
import com.mobius.software.telco.protocols.diameter.primitives.gx.IPCANTypeEnum;
import com.mobius.software.telco.protocols.diameter.primitives.gx.PCSCFRestorationIndicationEnum;
import com.mobius.software.telco.protocols.diameter.primitives.gx.PRAInstall;
import com.mobius.software.telco.protocols.diameter.primitives.gx.PRARemove;
import com.mobius.software.telco.protocols.diameter.primitives.gx.QoSInformation;
import com.mobius.software.telco.protocols.diameter.primitives.gx.RemovalOfAccessEnum;
import com.mobius.software.telco.protocols.diameter.primitives.gx.SessionReleaseCauseEnum;
import com.mobius.software.telco.protocols.diameter.primitives.gx.UsageMonitoringInformation;
import com.mobius.software.telco.protocols.diameter.primitives.rfc7683.OCSupportedFeatures;
import com.mobius.software.telco.protocols.diameter.primitives.rfc7944.DRMPEnum;

/**
*
* @author yulian oifa
*
*/

/*
 * 	5.6.4	Re-Auth-Request (RAR) Command
	The RAR command, indicated by the Command-Code field set to 258 and the 'R' bit set in the Command Flags field, is sent by the PCRF to the PCEF in order to provision PCC rules using the PUSH procedure initiate the provision of unsolicited PCC rules. It is used to provision PCC rules, event triggers and event report indications for the session. If the PCRF performs the bearer binding, PCC rules will be provisioned at bearer level.
	Message Format:

	<RA-Request> ::= < Diameter Header: 258, REQ, PXY >
				 < Session-Id >
				 [ DRMP ]
				 { Auth-Application-Id }
				 { Origin-Host }
				 { Origin-Realm }
				 { Destination-Realm }
				 { Destination-Host }
				 { Re-Auth-Request-Type }
				 [ Session-Release-Cause ]
				 [ Origin-State-Id ]
				 [ OC-Supported-Features ]
				*[ Event-Trigger ]
				 [ Event-Report-Indication ]
				*[ Charging-Rule-Remove ]
				*[ Charging-Rule-Install ]
				 [ Default-EPS-Bearer-QoS ]
				*[ QoS-Information ]
				 [ Default-QoS-Information ]
				 [ Revalidation-Time ]
				*[ Usage-Monitoring-Information ]
				 [ PCSCF-Restoration-Indication ]
			  0*4[ Conditional-Policy-Information ]
				 [ Removal-Of-Access ]
				 [ IP-CAN-Type ]
				 [ PRA-Install ]
				 [ PRA-Remove ]
				*[ CSG-Information-Reporting ]
				*[ Proxy-Info ]
				*[ Route-Record ]
				*[ AVP ]

 */
@DiameterCommandDefinition(applicationId = ApplicationIDs.GX, commandCode = CommandCodes.REAUTH, request = true, proxyable = true, name="Re-Auth-Request")
public interface ReAuthRequest extends com.mobius.software.telco.protocols.diameter.commands.commons.ReAuthRequest
{
	DRMPEnum getDRMP();
	
	void setDRMP(DRMPEnum value);
	
	ReAuthRequestTypeEnum getReAuthRequestType();
	
	void setReAuthRequestType(ReAuthRequestTypeEnum value) throws MissingAvpException;
	
	SessionReleaseCauseEnum getSessionReleaseCause();
	
	void setSessionReleaseCause(SessionReleaseCauseEnum value);
	
	OCSupportedFeatures getOCSupportedFeatures();
	
	void setOCSupportedFeatures(OCSupportedFeatures value);
	
	List<EventTriggerEnum> getEventTrigger();
	
	void setEventTrigger(List<EventTriggerEnum> value);	
	
	EventReportIndication getEventReportIndication();
	
	void setEventReportIndication(EventReportIndication value);	
	
	List<ChargingRuleRemove> getChargingRuleRemove();
	
	void setChargingRuleRemove(List<ChargingRuleRemove> value);	
	
	List<ChargingRuleInstall> getChargingRuleInstall();
	
	void setChargingRuleInstall(List<ChargingRuleInstall> value);	
	
	public DefaultEPSBearerQoS getDefaultEPSBearerQoS();
	
	void setDefaultEPSBearerQoS(DefaultEPSBearerQoS value);
	
	List<QoSInformation> getQoSInformation();
	
	void setQoSInformation(List<QoSInformation> value);	
	
	public DefaultQoSInformation getDefaultQoSInformation();
	
	void setDefaultQoSInformation(DefaultQoSInformation value);
	
	public Date getRevalidationTime();
	
	void setRevalidationTime(Date value);
	
	List<UsageMonitoringInformation> getUsageMonitoringInformation();
	
	void setUsageMonitoringInformation(List<UsageMonitoringInformation> value);	
	
	public PCSCFRestorationIndicationEnum getPCSCFRestorationIndication();
	
	void setPCSCFRestorationIndication(PCSCFRestorationIndicationEnum value);
	
	List<ConditionalPolicyInformation> getConditionalPolicyInformation();
	
	void setConditionalPolicyInformation(List<ConditionalPolicyInformation> value);
	
	RemovalOfAccessEnum getRemovalOfAccess();
	
	void setRemovalOfAccess(RemovalOfAccessEnum value);
	
	IPCANTypeEnum getIPCANType();
	
	void setIPCANType(IPCANTypeEnum value);
	
	PRAInstall getPRAInstall();
	
	void setPRAInstall(PRAInstall value);
	
	PRARemove getPRARemove();
	
	void setPRARemove(PRARemove value);
	
	List<CSGInformationReportingEnum> getCSGInformationReporting();
	
	void setCSGInformationReporting(List<CSGInformationReportingEnum> value);		
}