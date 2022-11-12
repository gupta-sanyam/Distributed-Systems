package com.mycompany.publisher;

import java.util.Scanner;

/**
 *
 * @author yogesh
 */
public class Main {
    
    public static void main(String[] args) throws Exception{
      Scanner sc = new Scanner(System.in);
      Publisher pub = new Publisher();
      
      while(true){
       
       System.out.println("Choose from menu:");
       System.out.println("Press 1 to Publish new prices");
       System.out.println("Press 2 to advertise new topic");
       System.out.println("Press 3 to deadvertise a topic");
       System.out.println("Press 4 to exit");
       
       int input = sc.nextInt();
       
       if(input == 1)
       {
          pub.publish();

          System.out.println("Latest prices from Gemini published !"); 
       }
       if(input==2)
       {
        System.out.println("Enter cryptocurrency to advertise"); 
        String ad = sc.next();
        pub.advertise(ad);  
        System.out.println(ad+ " Advertised!"); 
       }
       
       if(input==3)
       {
        System.out.println("Enter cryptocurrency to deadvertise"); 
        String deAd = sc.next();
        pub.deadvertise(deAd); 
        System.out.println(deAd+ " Deadvertised!"); 
       }
       
       if(input==4)
       {
        System.exit(0);
       }
       
      
       Thread.sleep(4000);
       System.out.println();
       System.out.println();
       System.out.println();
       }    
}
}


