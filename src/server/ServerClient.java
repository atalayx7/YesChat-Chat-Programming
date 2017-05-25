package server;

import java.net.InetAddress;

public class ServerClient {
	// This class exist to store information about client that is connected to
	// us.

	public String name;
	public InetAddress address; // IP address of the client
	public int port;
	private final int ID; // identification number that for client.
	public int attempt = 0; // if disconnect, it will attempt to re-connect

	public ServerClient(String name, InetAddress address, int port, final int ID) {
		this.name = name;
		this.ID = ID;
		this.address = address;
		this.port = port;
	}

	public int getID() {
		return ID;
	}

}
