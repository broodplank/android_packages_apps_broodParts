package com.broodplank.broodparts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;




public class VACUUM extends mainActivity {


    private static final String TAG = "CPUSettings";

		
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	       
	        
	        VACUUMNOW();
		}
		
		

public static String readOneLine(String fname) {
    BufferedReader br;
    String line = null;

    try {
        br = new BufferedReader(new FileReader(fname), 512);
        try {
            line = br.readLine();
        } finally {
            br.close();
        }
    } catch (Exception e) {
        Log.e(TAG, "IO Exception when reading /sys/ file", e);
    }
    return line;
}
	
public void VACUUMNOW() {
	
	 final AlertDialog.Builder builder1=new AlertDialog.Builder(this);
	 builder1.setTitle("Fragmentation Log");
	 builder1.setIcon(android.R.drawable.ic_dialog_alert);

	
	   final AlertDialog.Builder builder=new AlertDialog.Builder(this);
	      builder.setTitle("Defragment Databases");
	      builder.setIcon(android.R.drawable.ic_dialog_alert);
	      builder.setMessage("Press OK to start defragmenting SQLite databases.\nOr press Cancel to exit");
	     builder.setPositiveButton("OK", new OnClickListener() {
	    	 
	    	 
	    	 

	    	   @Override
	    	   public void onClick(DialogInterface dialog, int which) {
	    		   
	    		   
	    		   CommandCapture command = new CommandCapture(0, "rm -f /mnt/sdcard/vacuumlog", "cd /system/etc/broodrom", "sh vacuum > /mnt/sdcard/vacuumlog"); {
	    		 		try {
	    		 	
	    		 			RootTools.getShell(true).add(command).waitForFinish();
	    		 			
	    		 		
	    		 			
	    		 		} catch (InterruptedException e1) {
	    		 			// TODO Auto-generated catch block
	    		 			e1.printStackTrace();
	    		 		} catch (IOException e1) {
	    		 			// TODO Auto-generated catch block
	    		 			e1.printStackTrace();
	    		 		} catch (TimeoutException e1) {
	    		 			// TODO Auto-generated catch block
	    		 			e1.printStackTrace();
	    		 		}
	    	
			 	    	
	    			     try {
	    			 	 	String cardPath = "/mnt/sdcard";
	    			 		 BufferedReader r = new BufferedReader(new FileReader(cardPath + "/vacuumlog"));
	    			 		 StringBuilder total = new StringBuilder();
	    			 		 String line;
	    			 		 while((line = r.readLine()) != null) {
	    			 		     total.append(line+"\n");
	    				 	     
	    			 	 	      builder1.setMessage(total);
	    			 	 	      r.close();
	    			 		 }
	    			     } catch (IOException ex) {
	    			    		return;
	    			    	}
	    			     builder1.setPositiveButton("OK", new OnClickListener() {	
	    			     
	    			     @Override
	    			     public void onClick(DialogInterface dialog, int which) {

	    			     }
	    			    });
	    			    	 builder1.show();
	    			    	 
	    			    	 
	    			    	
	    		
	    		   }}});
	     

	     builder.setNegativeButton("Cancel", new OnClickListener() {

	    	   @Override
	    	   public void onClick(DialogInterface dialog, int which) {
	    		   
	    		   CommandCapture command2 = new CommandCapture(0, "rm /sdcard/vacuumlog"); {
	    		  		try {
	    		  	
	    		  			RootTools.getShell(true).add(command2).waitForFinish();
	    		  		

	    		  		} catch (InterruptedException e3) {
	    		  			// TODO Auto-generated catch block
	    		  			e3.printStackTrace();
	    		  		} catch (IOException e3) {
	    		  			// TODO Auto-generated catch block
	    		  			e3.printStackTrace();
	    		  		} catch (TimeoutException e3) {
	    		  			// TODO Auto-generated catch block
	    		  			e3.printStackTrace();
	    		  		}
	    		      }
	    	    // TODO Auto-generated method stub

	    	   }
	    	  });

	    builder.show();

 			 

 		
     }
	 

	 
}
 	 
    	
	


