package org.sdu.command;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import org.sdu.net.Packet;

/**
 * Resolve the packet on the client received from the server.
 * 
 * @version 0.1 rev 8000 Jan. 3, 2013.
 * Copyright (c) HyperCube Dev Team.
 */

public class PacketResolver {

	private byte statusMain;
	private byte statusSub;
	private byte instMain;
	private byte instSub;
	
	private LinkedList list;
	
	class par{
		int pos;
		int length;
	}

	private par part;
	
	/**
	 * Initialize a PacketResovler object.
	 * @param p --> The Packet received.
	 */
	public PacketResolver(Packet p){
		resolveBuffer(p.getData());
	}
	
	/**
	 * Resolve the ByteBuffer in the Packet.
	 * @param buf --> The ByteBuffer in the Packet.
	 */
	public void resolveBuffer(ByteBuffer buf){
		setStatusMain(buf.get());
		setStatusSub(buf.get());
		setInstMain(buf.get());
		setInstSub(buf.get());

		list = new LinkedList();
		
		while (buf.position() <= buf.capacity()){
		buf.get();
		part = new par();
		part.pos = buf.position();
		part.length = (buf.get() << 8) + buf.get();
		list.add(part);
		buf.position(buf.position() + part.length);
		}
		
	}

	public byte getInstSub() {
		return instSub;
	}

	public void setInstSub(byte instSub) {
		this.instSub = instSub;
	}

	public byte getInstMain() {
		return instMain;
	}

	public void setInstMain(byte instMain) {
		this.instMain = instMain;
	}

	public byte getStatusSub() {
		return statusSub;
	}

	public void setStatusSub(byte statusSub) {
		this.statusSub = statusSub;
	}

	public byte getStatusMain() {
		return statusMain;
	}
;
	public void setStatusMain(byte statusMain) {
		this.statusMain = statusMain;
	}	
	
	/**
	 * A LinkedList of the params' positions and lengths.
	 */
	public LinkedList getList(){
		return list;
	}
}
