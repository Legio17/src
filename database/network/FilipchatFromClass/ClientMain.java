package database.network.FilipchatFromClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientMain
{
   public static void main(String args[]) throws IOException, 
                      UnknownHostException, ClassNotFoundException
   {
     
      ChatView view = new ChatView();
      view.start();

   }
}
