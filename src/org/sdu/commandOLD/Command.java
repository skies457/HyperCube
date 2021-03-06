package org.sdu.commandOLD;

/**
 * Command class defines a series of commands.
 * 
 * @deprecated
 * @version 0.1 rev 8000 Dec. 27, 2012.
 * Copyright (c) HyperCube Dev Team.
 */
public class Command
{
	public static final byte cmdMainLogin = 0x01;
		public static final byte cmdVerifyVersion = 0x01;
		public static final byte cmdLoginSystem = 0x02;
	
	public static final byte cmdMainLogout = 0x02;
		public static final byte cmdLogoutSystem = 0x01;
		
	public static final byte cmdMainSend = 0x03;

	public static final byte cmdMainRespond = 0x04;
		public static final byte cmdRespondEnquiry = 0x00;
		
	public static final byte cmdMainChange = 0x11;
		public static final byte cmdAddUser = 0x01;
		public static final byte cmdDeleteUser = 0x01;
}
