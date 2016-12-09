package database.network.chatFromClass;

import java.io.Serializable;

public class Message implements Serializable
{
   private int id;
   private String messageBody;

   public Message(int id, String message)
   {
      this.id = id;
      this.messageBody = message;
      if (message == null)
      {
         this.messageBody = "";
      }
   }
   public Message(String message)
   {
      this(0, message);
      setId((int) (messageBody.hashCode() * Math.random()));
   }
   public int getId()
   {
      return id;
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
