package com.mobius.software.telco.protocols.diameter.impl.commands.s9;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import com.mobius.software.telco.protocols.diameter.annotations.DiameterOrder;
import com.mobius.software.telco.protocols.diameter.annotations.DiameterValidate;
import com.mobius.software.telco.protocols.diameter.commands.s9.CreditControlAnswer;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpNotSupportedException;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpOccursTooManyTimesException;
import com.mobius.software.telco.protocols.diameter.exceptions.DiameterException;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.impl.primitives.common.RouteRecordImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.gx.ANGWAddressImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.gx.BearerControlModeImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.gx.CSGInformationReportingImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.gx.EventTriggerImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.rfc7944.DRMPImpl;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvp;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterUnknownAvp;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.OCOLR;
import com.mobius.software.telco.protocols.diameter.primitives.common.RouteRecord;
import com.mobius.software.telco.protocols.diameter.primitives.creditcontrol.CcRequestTypeEnum;
import com.mobius.software.telco.protocols.diameter.primitives.cxdx.ChargingInformation;
import com.mobius.software.telco.protocols.diameter.primitives.cxdx.SupportedFeatures;
import com.mobius.software.telco.protocols.diameter.primitives.gx.ANGWAddress;
import com.mobius.software.telco.protocols.diameter.primitives.gx.BearerControlMode;
import com.mobius.software.telco.protocols.diameter.primitives.gx.BearerControlModeEnum;
import com.mobius.software.telco.protocols.diameter.primitives.gx.CSGInformationReporting;
import com.mobius.software.telco.protocols.diameter.primitives.gx.CSGInformationReportingEnum;
import com.mobius.software.telco.protocols.diameter.primitives.gx.EventTrigger;
import com.mobius.software.telco.protocols.diameter.primitives.gx.EventTriggerEnum;
import com.mobius.software.telco.protocols.diameter.primitives.gx.QoSInformation;
import com.mobius.software.telco.protocols.diameter.primitives.gxx.QoSRuleInstall;
import com.mobius.software.telco.protocols.diameter.primitives.gxx.QoSRuleRemove;
import com.mobius.software.telco.protocols.diameter.primitives.rfc7683.OCSupportedFeatures;
import com.mobius.software.telco.protocols.diameter.primitives.rfc7944.DRMP;
import com.mobius.software.telco.protocols.diameter.primitives.rfc7944.DRMPEnum;
import com.mobius.software.telco.protocols.diameter.primitives.rfc8583.Load;
import com.mobius.software.telco.protocols.diameter.primitives.s9.SubsessionDecisionInfo;

/*
 * Mobius Software LTD, Open Source Cloud Communications
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
public class CreditControlAnswerImpl extends com.mobius.software.telco.protocols.diameter.impl.commands.common.CreditControlAnswerImpl implements CreditControlAnswer
{
	private DRMP drmp;
	
	private List<SupportedFeatures> supportedFeatures;
	
	private OCSupportedFeatures ocSupportedFeatures;
	
	private OCOLR ocOLR;
	
	private List<QoSRuleRemove> qosRuleRemove;
	
	private List<QoSRuleInstall> qosRuleInstall;
	
	private List<QoSInformation> qosInformation;
	
	private BearerControlMode bearerControlMode;
	
	private List<EventTrigger> eventTrigger;
	
	private ChargingInformation chargingInformation;
	
	private List<SubsessionDecisionInfo> subsessionDecisionInfo;
	
	private List<CSGInformationReporting> csgInformationReporting;
	
	private List<ANGWAddress> anGWAddress;
	
	private List<Load> load;
	
	protected List<RouteRecord> routeRecords;
	
	protected CreditControlAnswerImpl() 
	{
		super();
		setExperimentalResultAllowed(true);
		setOriginStateIdAllowed(true);
		setUsernameAllowed(false);
	}
	
	public CreditControlAnswerImpl(String originHost,String originRealm,Boolean isRetransmit, Long resultCode, String sessionID, Long authApplicationId, CcRequestTypeEnum ccRequestType, Long ccRequestNumber) throws MissingAvpException, AvpNotSupportedException
	{
		super(originHost, originRealm, isRetransmit, resultCode, sessionID, authApplicationId, ccRequestType, ccRequestNumber);
		setExperimentalResultAllowed(true);
		setOriginStateIdAllowed(true);	
		setUsernameAllowed(false);
	}
	
	@Override
	public DRMPEnum getDRMP() 
	{
		if(drmp==null)
			return null;
		
		return drmp.getEnumerated(DRMPEnum.class);
	}

	@Override
	public void setDRMP(DRMPEnum value) 
	{
		if(value==null)
			this.drmp = null;
		else
			this.drmp = new DRMPImpl(value, null, null);
	}
	
	@Override
	public List<SupportedFeatures> getSupportedFeatures()
	{
		return this.supportedFeatures;
	}
	
	@Override
	public void setSupportedFeatures(List<SupportedFeatures> value)
	{
		this.supportedFeatures = value;
	}
	
	@Override
	public OCSupportedFeatures getOCSupportedFeatures()
	{
		return this.ocSupportedFeatures;
	}
	
	@Override
	public void setOCSupportedFeatures(OCSupportedFeatures value)
	{
		this.ocSupportedFeatures = value;
	}
	
	@Override
	public OCOLR getOCOLR()
	{
		return this.ocOLR;
	}
	
	@Override
	public void setOCOLR(OCOLR value)
	{
		this.ocOLR = value;
	}
	
	@Override
	public List<QoSRuleRemove> getQoSRuleRemove()
	{
		return this.qosRuleRemove;
	}
	
	@Override
	public void setQoSRuleRemove(List<QoSRuleRemove> value)
	{
		this.qosRuleRemove = value;
	}
	
	@Override
	public List<QoSRuleInstall> getQoSRuleInstall()
	{
		return this.qosRuleInstall;
	}
	
	@Override
	public void setQoSRuleInstall(List<QoSRuleInstall> value)
	{
		this.qosRuleInstall = value;
	}
	
	@Override
	public List<QoSInformation> getQoSInformation()
	{
		return this.qosInformation;
	}
	
	@Override
	public void setQoSInformation(List<QoSInformation> value)
	{
		this.qosInformation = value;
	}
	
	@Override
	public BearerControlModeEnum getBearerControlMode()
	{
		if(bearerControlMode==null)
			return null;
		
		return bearerControlMode.getEnumerated(BearerControlModeEnum.class);
	}
	
	@Override
	public void setBearerControlMode(BearerControlModeEnum value)
	{
		if(value==null)
			this.bearerControlMode = null;
		else
			this.bearerControlMode = new BearerControlModeImpl(value, null, null);			
	}
	
	@Override
	public List<EventTriggerEnum> getEventTrigger()
	{
		if(eventTrigger==null || eventTrigger.size()==0)
			return null;
		
		List<EventTriggerEnum> result = new ArrayList<EventTriggerEnum>();
		for(EventTrigger curr:eventTrigger)
			result.add(curr.getEnumerated(EventTriggerEnum.class));
		
		return result;
	}
	
	@Override
	public void setEventTrigger(List<EventTriggerEnum> value)
	{
		if(value==null || value.size()==0)
			this.eventTrigger = null;
		else
		{
			this.eventTrigger = new ArrayList<EventTrigger>();
			for(EventTriggerEnum curr:value)
				this.eventTrigger.add(new EventTriggerImpl(curr, null, null));			
		}
	}
	
	@Override
	public ChargingInformation getChargingInformation()
	{
		return this.chargingInformation;
	}
	
	@Override
	public void setChargingInformation(ChargingInformation value)
	{
		this.chargingInformation = value;
	}
	
	@Override
	public List<SubsessionDecisionInfo> getSubsessionDecisionInfo()
	{
		return this.subsessionDecisionInfo;
	}
	
	@Override
	public void setSubsessionDecisionInfo(List<SubsessionDecisionInfo> value)
	{
		this.subsessionDecisionInfo = value;
	}
	
	@Override
	public List<CSGInformationReportingEnum> getCSGInformationReporting()
	{
		if(this.csgInformationReporting==null || this.csgInformationReporting.size()==0)
			return null;
		
		List<CSGInformationReportingEnum> result=new ArrayList<CSGInformationReportingEnum>();
		for(CSGInformationReporting curr:this.csgInformationReporting)
			result.add(curr.getEnumerated(CSGInformationReportingEnum.class));
		
		return result;
	}
	
	@Override
	public void setCSGInformationReporting(List<CSGInformationReportingEnum> value)
	{
		if(value==null || value.size()==0)
			this.csgInformationReporting = null;
		else
		{
			this.csgInformationReporting = new ArrayList<CSGInformationReporting>();
			for(CSGInformationReportingEnum curr:value)
				this.csgInformationReporting.add(new CSGInformationReportingImpl(curr, null, null));
		}
	}
	
	@Override
	public List<InetAddress> getANGWAddress()
	{
		if(anGWAddress==null || anGWAddress.size()==0)
			return null;
		
		List<InetAddress> result=new ArrayList<InetAddress>();
		for(ANGWAddress curr:anGWAddress)
			result.add(curr.getAddress());
		
		return result;
	}
	
	@Override
	public void setANGWAddress(List<InetAddress> value) throws MissingAvpException, AvpOccursTooManyTimesException
	{
		if(value!=null && value.size()>2)
		{
			List<DiameterAvp> failedAvps=new ArrayList<DiameterAvp>();
			for(InetAddress curr:value)
				failedAvps.add(new ANGWAddressImpl(curr, null, null));
			
			throw new AvpOccursTooManyTimesException("Up to 2 AN-GW-Address allowed", failedAvps);
		}
		
		if(value==null)
			this.anGWAddress = null;
		else
		{
			this.anGWAddress = new ArrayList<ANGWAddress>();
			for(InetAddress curr:value)
				this.anGWAddress.add(new ANGWAddressImpl(curr, null, null));
		}
	}
	
	@Override
	public List<Load> getLoad()
	{
		return this.load;
	}
	
	
	@Override
	public void setLoad(List<Load> value)
	{
		this.load = value;
	}

	@Override
	public List<String> getRouteRecords() 
	{
		if(this.routeRecords==null)
			return null;
		else
		{
			List<String> result = new ArrayList<String>();
			for(RouteRecord curr:routeRecords)
				result.add(curr.getIdentity());
			
			return result;
		}
	}

	@Override
	public void setRouteRecords(List<String> value)
	{
		if(value == null || value.size()==0)
			this.routeRecords = null;
		else
		{
			this.routeRecords = new ArrayList<RouteRecord>();
			for(String curr:value)
				this.routeRecords.add(new RouteRecordImpl(curr, null, null));
		}
	}
	
	@DiameterValidate
	public DiameterException validate()
	{
		if(anGWAddress!=null && anGWAddress.size()>2)
		{
			List<DiameterAvp> failedAvps=new ArrayList<DiameterAvp>();
			failedAvps.addAll(anGWAddress);
			
			return new AvpOccursTooManyTimesException("Up to 2 AN-GW-Address allowed", failedAvps);
		}
		
		return super.validate();
	}
	
	@DiameterOrder
	public List<DiameterAvp> getOrderedAVPs()
	{
		List<DiameterAvp> result=new ArrayList<DiameterAvp>();
		result.add(sessionId);
		result.add(drmp);
		result.add(authApplicationId);
		result.add(originHost);
		result.add(originRealm);
		result.add(resultCode);
		result.add(experimentalResult);
		result.add(ccRequestType);
		result.add(ccRequestNumber);		
		
		if(supportedFeatures!=null)
			result.addAll(supportedFeatures);
		
		result.add(ocSupportedFeatures);
		result.add(ocOLR);
		
		if(qosRuleInstall!=null)
			result.addAll(qosRuleInstall);
		
		if(qosRuleRemove!=null)
			result.addAll(qosRuleRemove);
		
		if(qosInformation!=null)
			result.addAll(qosInformation);
		
		result.add(bearerControlMode);
		
		if(eventTrigger!=null)
			result.addAll(eventTrigger);
		
		result.add(chargingInformation);
		
		if(subsessionDecisionInfo!=null)
			result.addAll(subsessionDecisionInfo);
		
		if(csgInformationReporting!=null)
			result.addAll(csgInformationReporting);
		
		if(anGWAddress!=null)
			result.addAll(anGWAddress);
		
		result.add(originStateId);
		result.add(errorMessage);
		result.add(errorReportingHost);
		result.add(failedAvp);
		
		if(proxyInfo!=null)
			result.addAll(proxyInfo);
		
		if(routeRecords!=null)
			result.addAll(routeRecords);
		
		if(load!=null)
			result.addAll(load);
		
		if(optionalAvps!=null)
		{
			for(List<DiameterUnknownAvp> curr:optionalAvps.values())
				result.addAll(curr);
		}
		
		return result;
	}
}