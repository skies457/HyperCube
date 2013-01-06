package org.sdu.server;


import org.sdu.database.Database;
import org.sdu.net.Packet;
import org.sdu.server.ProcessTools.SecPro.*;

import java.nio.ByteBuffer;
/**
 * process for Incoming Packet and return the request data
 * @author Celr
 *
 */
public class process {
	private static ByteBuffer indata;
	private static Database db1;
	public static void Push(ByteBuffer p,Database db){
		indata = p;
		db1 = db;
	}
	
	public static Packet GetData(){
		PacketDataPro ProD = new PacketDataPro(indata);
		switch(ProD.GetFInst())
		{
		case 0x01: 
			 if(ProD.GetSInst() == 0x01) 
			 {return login.Push(ProD,db1);}
		case 0x02: 
			 if(ProD.GetSInst() == 0x01) 
			 {return logout.Push(ProD,db1);}
		//case 0x03: return trans.Push(cmd2,ProD);....
		//case 0x04: return detect.Push(cmd2,ProD);....
		//default : return Warming....;
		}
		return null;
		

	}
}
