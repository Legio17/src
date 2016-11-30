package database.network;

import java.lang.*;
import java.io.*;
import java.net.*;

public class goofyServer {

	public static void main(String args[]) {
		String data = "Toobie ornaught";
		try {
			ServerSocket srvr = new ServerSocket(1234);
			Socket skt = srvr.accept();
			System.out.print("Server has connected!\n");
			PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
			
			for (int i = 0; i < 10; i++){
				out.print(data);
			}


			System.out.print("Sending string: '" + data + "'\n");

			out.close();
			skt.close();
			srvr.close();
		} catch (Exception e) {
			System.out.print("Whoops! It didn't work!\n");
			
		}
	}

}
