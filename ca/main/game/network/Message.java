package ca.main.game.network;

import java.io.Serializable;
import java.net.InetAddress;

public class Message implements Serializable
{
   private int id;
   private String messageBody;
   InetAddress IP;
   int port;

   public Message(int id, String message)
   {
      this.id = id;
      this.messageBody = message;
      if (message == null)
      {
         this.messageBody = "";
      }
   }
   public Message(String message, InetAddress IP, int port)
   {
      this(0, message);
      setId((int) (messageBody.hashCode() * Math.random()));
      this.IP=IP;
      this.port=port;
   }

   public int getId()
   {
      return id;
   }

   public InetAddress getIP()
   {
      return IP;
   }
   
   public int getPort()
   {
      return port;
   }
   
   public void setId(int id)
   {
      this.id = id;
   }

   public String getBody()
   {
      return messageBody;
   }

   public String toString()
   {
      return "id=" + id + ", \"" + messageBody + "\"";
   }

}
