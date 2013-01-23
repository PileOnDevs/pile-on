package com.game.pileon;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="root")
public class Example {

   @Element(name="message")
   private String text;

   @Attribute(name="id")
   private int index;

   public Example() {
      super();
   }  

   public Example(String text, int index) {
      this.text = text;
      this.index = index;
   }

   public String getMessage() {
      return text;
   }

   public int getId() {
      return index;
   }
   
   public String toString(){
	   return "message: " + text + " id: " + Integer.toString(index);
   }
}