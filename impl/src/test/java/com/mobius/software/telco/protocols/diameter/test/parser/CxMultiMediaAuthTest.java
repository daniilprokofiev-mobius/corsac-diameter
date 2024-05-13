package com.mobius.software.telco.protocols.diameter.test.parser;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mobius.software.telco.protocols.diameter.ApplicationIDs;
import com.mobius.software.telco.protocols.diameter.ResultCodes;
import com.mobius.software.telco.protocols.diameter.commands.DiameterMessage;
import com.mobius.software.telco.protocols.diameter.commands.cxdx.MultimediaAuthAnswer;
import com.mobius.software.telco.protocols.diameter.commands.cxdx.MultimediaAuthRequest;
import com.mobius.software.telco.protocols.diameter.exceptions.DiameterException;
import com.mobius.software.telco.protocols.diameter.impl.commands.cxdx.MultimediaAuthRequestImpl;
import com.mobius.software.telco.protocols.diameter.impl.primitives.cxdx.AllowedWAFWWSFIdentitiesImpl;
import com.mobius.software.telco.protocols.diameter.parser.DiameterParser;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthSessionStateEnum;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class CxMultiMediaAuthTest
{
	//this is original
	private ByteBuf multimediaAuthRequestMessage = Unpooled.wrappedBuffer(new byte[] {0x01, 0x00, 0x01, (byte)0xfc, (byte)0xc0, 0x00, 0x01, 0x2f, 0x01, 0x00, 0x00, 0x00, 0x1c, (byte)0x90, (byte)0xdc, 0x0d, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x07, 0x40, 0x00, 0x00, 0x40, 0x73, 0x63, 0x73, 0x63, 0x66, 0x2d, 0x30, 0x31, 0x2e, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x3b, 0x33, 0x38, 0x37, 0x3b, 0x34, 0x37, 0x39, 0x32, 0x35, 0x34, 0x36, 0x36, 0x37, 0x00, 0x00, 0x01, 0x04, 0x40, 0x00, 0x00, 0x20, 0x00, 0x00, 0x01, 0x0a, 0x40, 0x00, 0x00, 0x0c, 0x00, 0x00, 0x28, (byte)0xaf, 0x00, 0x00, 0x01, 0x02, 0x40, 0x00, 0x00, 0x0c, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x15, 0x40, 0x00, 0x00, 0x0c, 0x00, 0x00, 0x00, 0x01, 
			//Public-Identity
			0x00, 0x00, 0x02, 0x59, (byte)0xc0, 0x00, 0x00, 0x41, 0x00, 0x00, 0x28, (byte)0xaf, 0x73, 0x69, 0x70, 0x3a, 0x32, 0x34, 0x30, 0x30, 0x31, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x31, 0x32, 0x40, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00, 
			//UserName
			0x00, 0x00, 0x00, 0x01, 0x40, 0x00, 0x00, 0x39, 0x32, 0x34, 0x30, 0x30, 0x31, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x31, 0x32, 0x40, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00, 
			//Sip-Auth-Num-Items + Server-Name
			0x00, 0x00, 0x02, 0x5f, (byte)0xc0, 0x00, 0x00, 0x10, 0x00, 0x00, 0x28, (byte)0xaf, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x02, 0x5a, (byte)0xc0, 0x00, 0x00, 0x36, 0x00, 0x00, 0x28, (byte)0xaf, 0x73, 0x69, 0x70, 0x3a, 0x73, 0x63, 0x73, 0x63, 0x66, 0x2d, 0x30, 0x31, 0x40, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 
			//Destination-Realm
			0x00, 0x00, 0x01, 0x1b, 0x40, 0x00, 0x00, 0x29, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00, 
			//Sip-Auth-Data-Item
			0x00, 0x00, 0x02, 0x64, (byte)0xc0, 0x00, 0x00, 0x28, 0x00, 0x00, 0x28, (byte)0xaf, 0x00, 0x00, 0x02, 0x60, (byte)0xc0, 0x00, 0x00, 0x1c, 0x00, 0x00, 0x28, (byte)0xaf, 0x44, 0x69, 0x67, 0x65, 0x73, 0x74, 0x2d, 0x41, 0x4b, 0x41, 0x76, 0x31, 0x2d, 0x4d, 0x44, 0x35, 
			//Origin-Host And Origin-Realm
			0x00, 0x00, 0x01, 0x08, 0x40, 0x00, 0x00, 0x32, 0x73, 0x63, 0x73, 0x63, 0x66, 0x2d, 0x30, 0x31, 0x2e, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00, 0x00, 0x01, 0x28, 0x40, 0x00, 0x00, 0x29, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00  
	}); 
	
	//this is patched
	private ByteBuf multimediaAuthRequestMessagePatched = Unpooled.wrappedBuffer(new byte[] {0x01, 0x00, 0x01, (byte)0xfc, (byte)0xc0, 0x00, 0x01, 0x2f, 0x01, 0x00, 0x00, 0x00, 0x1c, (byte)0x90, (byte)0xdc, 0x0d, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x07, 0x40, 0x00, 0x00, 0x40, 0x73, 0x63, 0x73, 0x63, 0x66, 0x2d, 0x30, 0x31, 0x2e, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x3b, 0x33, 0x38, 0x37, 0x3b, 0x34, 0x37, 0x39, 0x32, 0x35, 0x34, 0x36, 0x36, 0x37, 0x00, 0x00, 0x01, 0x04, 0x40, 0x00, 0x00, 0x20, 0x00, 0x00, 0x01, 0x0a, 0x40, 0x00, 0x00, 0x0c, 0x00, 0x00, 0x28, (byte)0xaf, 0x00, 0x00, 0x01, 0x02, 0x40, 0x00, 0x00, 0x0c, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x15, 0x40, 0x00, 0x00, 0x0c, 0x00, 0x00, 0x00, 0x01, 
			//Origin-Host And Origin-Realm
			0x00, 0x00, 0x01, 0x08, 0x40, 0x00, 0x00, 0x32, 0x73, 0x63, 0x73, 0x63, 0x66, 0x2d, 0x30, 0x31, 0x2e, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00, 0x00, 0x01, 0x28, 0x40, 0x00, 0x00, 0x29, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00,  
			//Destination-Realm
			0x00, 0x00, 0x01, 0x1b, 0x40, 0x00, 0x00, 0x29, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00, 
			//UserName
			0x00, 0x00, 0x00, 0x01, 0x40, 0x00, 0x00, 0x39, 0x32, 0x34, 0x30, 0x30, 0x31, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x31, 0x32, 0x40, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00, 
			//Public-Identity
			0x00, 0x00, 0x02, 0x59, (byte)0xc0, 0x00, 0x00, 0x41, 0x00, 0x00, 0x28, (byte)0xaf, 0x73, 0x69, 0x70, 0x3a, 0x32, 0x34, 0x30, 0x30, 0x31, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x31, 0x32, 0x40, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00, 
			//Sip-Auth-Data-Item
			0x00, 0x00, 0x02, 0x64, (byte)0xc0, 0x00, 0x00, 0x28, 0x00, 0x00, 0x28, (byte)0xaf, 0x00, 0x00, 0x02, 0x60, (byte)0xc0, 0x00, 0x00, 0x1c, 0x00, 0x00, 0x28, (byte)0xaf, 0x44, 0x69, 0x67, 0x65, 0x73, 0x74, 0x2d, 0x41, 0x4b, 0x41, 0x76, 0x31, 0x2d, 0x4d, 0x44, 0x35, 
			//Sip-Auth-Num-Items + Server-Name
			0x00, 0x00, 0x02, 0x5f, (byte)0xc0, 0x00, 0x00, 0x10, 0x00, 0x00, 0x28, (byte)0xaf, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x02, 0x5a, (byte)0xc0, 0x00, 0x00, 0x36, 0x00, 0x00, 0x28, (byte)0xaf, 0x73, 0x69, 0x70, 0x3a, 0x73, 0x63, 0x73, 0x63, 0x66, 0x2d, 0x30, 0x31, 0x40, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 
	});
		
	//this is original
	private ByteBuf multimediaAuthAnswerMessage = Unpooled.wrappedBuffer(new byte[] {0x01, 0x00, 0x02, 0x2c, 0x40, 0x00, 0x01, 0x2f, 0x01, 0x00, 0x00, 0x00, 0x1c, (byte)0x90, (byte)0xdc, 0x0d, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x07, 0x40, 0x00, 0x00, 0x40, 0x73, 0x63, 0x73, 0x63, 0x66, 0x2d, 0x30, 0x31, 0x2e, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x3b, 0x33, 0x38, 0x37, 0x3b, 0x34, 0x37, 0x39, 0x32, 0x35, 0x34, 0x36, 0x36, 0x37, 
			//Origin-Host
			0x00, 0x00, 0x01, 0x08, 0x40, 0x00, 0x00, 0x31, 0x6f, 0x64, 0x69, 0x6e, 0x68, 0x73, 0x73, 0x2e, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00,
			//Origin-Realm
			0x00, 0x00, 0x01, 0x28, 0x40, 0x00, 0x00, 0x29, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00, 
			//Auth-Session-State
			0x00, 0x00, 0x01, 0x15, 0x40, 0x00, 0x00, 0x0c, 0x00, 0x00, 0x00, 0x01, 
			//Vendor-Specific-Application-Id
			0x00, 0x00, 0x01, 0x04, 0x40, 0x00, 0x00, 0x20, 0x00, 0x00, 0x01, 0x0a, 0x40, 0x00, 0x00, 0x0c, 0x00, 0x00, 0x28, (byte)0xaf, 0x00, 0x00, 0x01, 0x02, 0x40, 0x00, 0x00, 0x0c, 0x01, 0x00, 0x00, 0x00, 
			//Public-Identity
			0x00, 0x00, 0x02, 0x59, (byte)0xc0, 0x00, 0x00, 0x41, 0x00, 0x00, 0x28, (byte)0xaf, 0x73, 0x69, 0x70, 0x3a, 0x32, 0x34, 0x30, 0x30, 0x31, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x31, 0x32, 0x40, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00, 
			//Username
			0x00, 0x00, 0x00, 0x01, 0x40, 0x00, 0x00, 0x39, 0x32, 0x34, 0x30, 0x30, 0x31, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x31, 0x32, 0x40, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00, 
			//Sip-Number-Auth-Item And Sip-Auth-Data-Item
			0x00, 0x00, 0x02, 0x5f, (byte)0xc0, 0x00, 0x00, 0x10, 0x00, 0x00, 0x28, (byte)0xaf, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x02, 0x64, (byte)0xc0, 0x00, 0x00, (byte)0xb0, 0x00, 0x00, 0x28, (byte)0xaf, 0x00, 0x00, 0x02, 0x65, (byte)0xc0, 0x00, 0x00, 0x10, 0x00, 0x00, 0x28, (byte)0xaf, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x02, 0x60, (byte)0xc0, 0x00, 0x00, 0x1c, 0x00, 0x00, 0x28, (byte)0xaf, 0x44, 0x69, 0x67, 0x65, 0x73, 0x74, 0x2d, 0x41, 0x4b, 0x41, 0x76, 0x31, 0x2d, 0x4d, 0x44, 0x35, 0x00, 0x00, 0x02, 0x61, (byte)0xc0, 0x00, 0x00, 0x2c, 0x00, 0x00, 0x28, (byte)0xaf, 0x3f, (byte)0x9d, (byte)0xf1, (byte)0xbf, (byte)0xbc, 0x0f, (byte)0x97, 0x71, 0x30, (byte)0x9c, (byte)0xf1, 0x01, 0x78, (byte)0xfe, 0x0e, 0x00, (byte)0xa4, (byte)0xcd, 0x19, (byte)0xfe, (byte)0xee, (byte)0xfc, (byte)0xb9, (byte)0xb9, 0x56, 0x5f, 0x29, 0x3d, (byte)0xb7, (byte)0xf0, 0x69, (byte)0xf7, 0x00, 0x00, 0x02, 0x62, (byte)0xc0, 0x00, 0x00, 0x14, 0x00, 0x00, 0x28, (byte)0xaf, (byte)0xaf, (byte)0x9f, 0x1b, (byte)0x8c, (byte)0xda, (byte)0xb6, (byte)0xbf, 0x68, 0x00, 0x00, 0x02, 0x71, (byte)0xc0, 0x00, 0x00, 0x1c, 0x00, 0x00, 0x28, (byte)0xaf, (byte)0xb9, (byte)0xca, (byte)0xbd, (byte)0xfa, (byte)0xce, (byte)0xb0, (byte)0xb3, (byte)0xe2, 0x7e, (byte)0xf5, 0x3a, 0x78, (byte)0x85, 0x77, 0x03, (byte)0xd6, 0x00, 0x00, 0x02, 0x72, (byte)0xc0, 0x00, 0x00, 0x1c, 0x00, 0x00, 0x28, (byte)0xaf, (byte)0xf6, 0x5a, 0x7b, 0x41, 0x11, (byte)0xef, 0x6c, 0x10, (byte)0xec, (byte)0xd9, 0x56, (byte)0x85, (byte)0xc0, 0x50, 0x7c, 0x26, 
			//Result-Code
			0x00, 0x00, 0x01, 0x0c, 0x40, 0x00, 0x00, 0x0c, 0x00, 0x00, 0x07, (byte)0xd1 
	});

	//this is patched
	private ByteBuf multimediaAuthAnswerMessagePatched  = Unpooled.wrappedBuffer(new byte[] {0x01, 0x00, 0x02, 0x2c, 0x40, 0x00, 0x01, 0x2f, 0x01, 0x00, 0x00, 0x00, 0x1c, (byte)0x90, (byte)0xdc, 0x0d, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x07, 0x40, 0x00, 0x00, 0x40, 0x73, 0x63, 0x73, 0x63, 0x66, 0x2d, 0x30, 0x31, 0x2e, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x3b, 0x33, 0x38, 0x37, 0x3b, 0x34, 0x37, 0x39, 0x32, 0x35, 0x34, 0x36, 0x36, 0x37, 
			//Vendor-Specific-Application-Id
			0x00, 0x00, 0x01, 0x04, 0x40, 0x00, 0x00, 0x20, 0x00, 0x00, 0x01, 0x0a, 0x40, 0x00, 0x00, 0x0c, 0x00, 0x00, 0x28, (byte)0xaf, 0x00, 0x00, 0x01, 0x02, 0x40, 0x00, 0x00, 0x0c, 0x01, 0x00, 0x00, 0x00, 
			//Result-Code
			0x00, 0x00, 0x01, 0x0c, 0x40, 0x00, 0x00, 0x0c, 0x00, 0x00, 0x07, (byte)0xd1, 
			//Auth-Session-State
			0x00, 0x00, 0x01, 0x15, 0x40, 0x00, 0x00, 0x0c, 0x00, 0x00, 0x00, 0x01, 
			//Origin-Host
			0x00, 0x00, 0x01, 0x08, 0x40, 0x00, 0x00, 0x31, 0x6f, 0x64, 0x69, 0x6e, 0x68, 0x73, 0x73, 0x2e, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00,
			//Origin-Realm
			0x00, 0x00, 0x01, 0x28, 0x40, 0x00, 0x00, 0x29, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00, 
			//Username
			0x00, 0x00, 0x00, 0x01, 0x40, 0x00, 0x00, 0x39, 0x32, 0x34, 0x30, 0x30, 0x31, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x31, 0x32, 0x40, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00, 
			//Public-Identity
			0x00, 0x00, 0x02, 0x59, (byte)0xc0, 0x00, 0x00, 0x41, 0x00, 0x00, 0x28, (byte)0xaf, 0x73, 0x69, 0x70, 0x3a, 0x32, 0x34, 0x30, 0x30, 0x31, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x31, 0x32, 0x40, 0x69, 0x6d, 0x73, 0x2e, 0x6d, 0x6e, 0x63, 0x30, 0x30, 0x31, 0x2e, 0x6d, 0x63, 0x63, 0x32, 0x34, 0x30, 0x2e, 0x33, 0x67, 0x70, 0x70, 0x6e, 0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x2e, 0x6f, 0x72, 0x67, 0x00, 0x00, 0x00, 
			//Sip-Number-Auth-Item And Sip-Auth-Data-Item
			0x00, 0x00, 0x02, 0x5f, (byte)0xc0, 0x00, 0x00, 0x10, 0x00, 0x00, 0x28, (byte)0xaf, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x02, 0x64, (byte)0xc0, 0x00, 0x00, (byte)0xb0, 0x00, 0x00, 0x28, (byte)0xaf, 0x00, 0x00, 0x02, 0x65, (byte)0xc0, 0x00, 0x00, 0x10, 0x00, 0x00, 0x28, (byte)0xaf, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x02, 0x60, (byte)0xc0, 0x00, 0x00, 0x1c, 0x00, 0x00, 0x28, (byte)0xaf, 0x44, 0x69, 0x67, 0x65, 0x73, 0x74, 0x2d, 0x41, 0x4b, 0x41, 0x76, 0x31, 0x2d, 0x4d, 0x44, 0x35, 0x00, 0x00, 0x02, 0x61, (byte)0xc0, 0x00, 0x00, 0x2c, 0x00, 0x00, 0x28, (byte)0xaf, 0x3f, (byte)0x9d, (byte)0xf1, (byte)0xbf, (byte)0xbc, 0x0f, (byte)0x97, 0x71, 0x30, (byte)0x9c, (byte)0xf1, 0x01, 0x78, (byte)0xfe, 0x0e, 0x00, (byte)0xa4, (byte)0xcd, 0x19, (byte)0xfe, (byte)0xee, (byte)0xfc, (byte)0xb9, (byte)0xb9, 0x56, 0x5f, 0x29, 0x3d, (byte)0xb7, (byte)0xf0, 0x69, (byte)0xf7, 0x00, 0x00, 0x02, 0x62, (byte)0xc0, 0x00, 0x00, 0x14, 0x00, 0x00, 0x28, (byte)0xaf, (byte)0xaf, (byte)0x9f, 0x1b, (byte)0x8c, (byte)0xda, (byte)0xb6, (byte)0xbf, 0x68, 0x00, 0x00, 0x02, 0x71, (byte)0xc0, 0x00, 0x00, 0x1c, 0x00, 0x00, 0x28, (byte)0xaf, (byte)0xb9, (byte)0xca, (byte)0xbd, (byte)0xfa, (byte)0xce, (byte)0xb0, (byte)0xb3, (byte)0xe2, 0x7e, (byte)0xf5, 0x3a, 0x78, (byte)0x85, 0x77, 0x03, (byte)0xd6, 0x00, 0x00, 0x02, 0x72, (byte)0xc0, 0x00, 0x00, 0x1c, 0x00, 0x00, 0x28, (byte)0xaf, (byte)0xf6, 0x5a, 0x7b, 0x41, 0x11, (byte)0xef, 0x6c, 0x10, (byte)0xec, (byte)0xd9, 0x56, (byte)0x85, (byte)0xc0, 0x50, 0x7c, 0x26 
	});
		
	@Test
	public void testMultimediaAuthRequest() throws DiameterException
	{		
		DiameterParser diameterParser=new DiameterParser();
		
		//make sure classes are loaded
		Class<?> clazz = AllowedWAFWWSFIdentitiesImpl.class;
		Class<?> avpClass = MultimediaAuthRequestImpl.class;
		assertNotNull(clazz);
		assertNotNull(avpClass);
		
		diameterParser.registerAvps(Package.getPackage("com.mobius.software.telco.protocols.diameter.impl.primitives"));
		diameterParser.registerApplication(Package.getPackage("com.mobius.software.telco.protocols.diameter.impl.commands.cxdx"));
		
		multimediaAuthRequestMessage.resetReaderIndex();
		DiameterMessage decodeMessage = diameterParser.decode(multimediaAuthRequestMessage, false);
		assertNotNull(decodeMessage);
		assertTrue(decodeMessage instanceof MultimediaAuthRequest);
		MultimediaAuthRequest mar = (MultimediaAuthRequest)decodeMessage;
		assertFalse(mar.getIsRetransmit());
		assertTrue(mar.getIsProxyable());
		assertNotNull(mar.getEndToEndIdentifier());
		assertNotNull(mar.getHopByHopIdentifier());
		assertEquals(mar.getEndToEndIdentifier(),new Long(0x00000000L));
		assertEquals(mar.getHopByHopIdentifier(),new Long(0x1c90dc0dL));
		assertNotNull(mar.getSessionId());
		assertEquals(mar.getSessionId(),"scscf-01.ims.mnc001.mcc240.3gppnetwork.org;387;479254667");
		assertNotNull(mar.getVendorSpecificApplicationId());
		assertNotNull(mar.getVendorSpecificApplicationId().getAuthApplicationId());
		assertEquals(mar.getVendorSpecificApplicationId().getAuthApplicationId(),new Long(ApplicationIDs.CX_DX));
		assertNotNull(mar.getAuthSessionState());
		assertEquals(mar.getAuthSessionState(),AuthSessionStateEnum.NO_STATE_MAINTAINED);
		assertNotNull(mar.getPublicIdentity());
		assertEquals(mar.getPublicIdentity(),"sip:240010000000012@ims.mnc001.mcc240.3gppnetwork.org");
		assertNotNull(mar.getUsername());
		assertEquals(mar.getUsername(),"240010000000012@ims.mnc001.mcc240.3gppnetwork.org");
		assertNotNull(mar.getSIPNumberAuthItems());
		assertEquals(mar.getSIPNumberAuthItems(),new Long(1L));
		assertNotNull(mar.getServerName());
		assertEquals(mar.getServerName(),"sip:scscf-01@mnc001.mcc240.3gppnetwork.org");
		assertNotNull(mar.getDestinationRealm());
		assertEquals(mar.getDestinationRealm(),"ims.mnc001.mcc240.3gppnetwork.org");
		assertNotNull(mar.getSIPAuthDataItem());
		assertNotNull(mar.getSIPAuthDataItem().getSIPAuthenticationScheme());
		assertEquals(mar.getSIPAuthDataItem().getSIPAuthenticationScheme(),"Digest-AKAv1-MD5");
		assertNotNull(mar.getOriginHost());
		assertEquals(mar.getOriginHost(),"scscf-01.ims.mnc001.mcc240.3gppnetwork.org");
		assertNotNull(mar.getOriginRealm());
		assertEquals(mar.getOriginRealm(),"ims.mnc001.mcc240.3gppnetwork.org");
		
		ByteBuf encodedMessage = diameterParser.encode(mar);
		byte[] marEncodedData = new byte[encodedMessage.readableBytes()];
		encodedMessage.readBytes(marEncodedData);
		multimediaAuthRequestMessagePatched.setIndex(0,multimediaAuthRequestMessagePatched.writerIndex());
		byte[] marData = new byte[multimediaAuthRequestMessagePatched.readableBytes()];
		multimediaAuthRequestMessagePatched.readBytes(marData);
		assertArrayEquals(marEncodedData, marData);
		
		multimediaAuthAnswerMessage.resetReaderIndex();
		decodeMessage = diameterParser.decode(multimediaAuthAnswerMessage, false);
		assertNotNull(decodeMessage);
		assertTrue(decodeMessage instanceof MultimediaAuthAnswer);
		MultimediaAuthAnswer maa = (MultimediaAuthAnswer)decodeMessage;
		assertFalse(maa.getIsError());
		assertTrue(maa.getIsProxyable());
		assertFalse(maa.getIsRetransmit());
		assertNotNull(maa.getEndToEndIdentifier());
		assertNotNull(maa.getHopByHopIdentifier());
		assertEquals(maa.getEndToEndIdentifier(),new Long(0x00000000L));
		assertEquals(maa.getHopByHopIdentifier(),new Long(0x1c90dc0dL));
		assertNotNull(maa.getSessionId());
		assertEquals(maa.getSessionId(),"scscf-01.ims.mnc001.mcc240.3gppnetwork.org;387;479254667");
		assertNotNull(maa.getOriginHost());
		assertEquals(maa.getOriginHost(),"odinhss.ims.mnc001.mcc240.3gppnetwork.org");
		assertNotNull(maa.getOriginRealm());
		assertEquals(maa.getOriginRealm(),"ims.mnc001.mcc240.3gppnetwork.org");
		assertNotNull(maa.getAuthSessionState());
		assertEquals(maa.getAuthSessionState(),AuthSessionStateEnum.NO_STATE_MAINTAINED);
		assertNotNull(maa.getVendorSpecificApplicationId());
		assertNotNull(maa.getVendorSpecificApplicationId().getAuthApplicationId());
		assertEquals(maa.getVendorSpecificApplicationId().getAuthApplicationId(),new Long(ApplicationIDs.CX_DX));
		assertNotNull(maa.getPublicIdentity());
		assertEquals(maa.getPublicIdentity(),"sip:240010000000012@ims.mnc001.mcc240.3gppnetwork.org");
		assertNotNull(maa.getUsername());
		assertEquals(maa.getUsername(),"240010000000012@ims.mnc001.mcc240.3gppnetwork.org");
		assertNotNull(maa.getSIPNumberAuthItems());
		assertEquals(maa.getSIPNumberAuthItems(),new Long(1L));
		assertNotNull(maa.getSIPAuthDataItem());
		assertEquals(maa.getSIPAuthDataItem().size(),1);
		assertNotNull(maa.getSIPAuthDataItem().get(0).getSIPItemNumber());
		assertEquals(maa.getSIPAuthDataItem().get(0).getSIPItemNumber(),new Long(1));
		assertNotNull(maa.getSIPAuthDataItem().get(0).getSIPAuthenticationScheme());
		assertEquals(maa.getSIPAuthDataItem().get(0).getSIPAuthenticationScheme(),"Digest-AKAv1-MD5");
		assertNotNull(maa.getSIPAuthDataItem().get(0).getSIPAuthenticate());
		assertEquals(maa.getSIPAuthDataItem().get(0).getSIPAuthenticate().readableBytes(),32);
		assertNotNull(maa.getSIPAuthDataItem().get(0).getSIPAuthorization());
		assertEquals(maa.getSIPAuthDataItem().get(0).getSIPAuthorization().readableBytes(),8);
		assertNotNull(maa.getSIPAuthDataItem().get(0).getConfidentialityKey());
		assertEquals(maa.getSIPAuthDataItem().get(0).getConfidentialityKey().readableBytes(),16);
		assertNotNull(maa.getSIPAuthDataItem().get(0).getIntegrityKey());
		assertEquals(maa.getSIPAuthDataItem().get(0).getIntegrityKey().readableBytes(),16);
		assertNotNull(maa.getResultCode());
		assertEquals(maa.getResultCode(),new Long(ResultCodes.DIAMETER_SUCCESS));
		
		encodedMessage = diameterParser.encode(maa);
		byte[] eaaEncodedData = new byte[encodedMessage.readableBytes()];
		encodedMessage.readBytes(eaaEncodedData);
		multimediaAuthAnswerMessagePatched.setIndex(0,multimediaAuthAnswerMessagePatched.writerIndex());
		byte[] eaaData = new byte[multimediaAuthAnswerMessagePatched.readableBytes()];
		multimediaAuthAnswerMessagePatched.readBytes(eaaData);
		assertArrayEquals(eaaEncodedData, eaaData);
	}
}