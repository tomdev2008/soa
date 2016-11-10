package com.ttfc.dubbo.proxy.filter.digest;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.digest.DigestUtils;

public class Nonce {
	
	private static Hashtable<String, Hashtable<Integer, LocalDateTime>>  nonces = new Hashtable<String, Hashtable<Integer, LocalDateTime>>();
	
	public static String Generate(){
		SecureRandom random = new SecureRandom();
	      byte bytes[] = new byte[20];
	      random.nextBytes(bytes);	 
	      String ran = DigestUtils.md5Hex(bytes);
	      Hashtable<Integer, LocalDateTime> finger = new Hashtable<Integer, LocalDateTime>();
	      finger.put(0, LocalDateTime.now().plusMinutes(10));
	      nonces.put(ran, finger);
	      return ran;
	}
	
	public static boolean IsValid(String rnonce, String nonceCount){
		Hashtable<Integer, LocalDateTime> finger = (Hashtable<Integer, LocalDateTime>) nonces.get(rnonce);
        if (finger != null) // nonce is found
        {
        	Entry<Integer, LocalDateTime> entry = finger.entrySet().iterator().next();
              // nonce count is greater than the one in record
        	System.out.println(entry.getKey());
            if (Integer.valueOf(nonceCount) > entry.getKey())
            {
                // nonce has not expired yet
                if (entry.getValue().compareTo(LocalDateTime.now())>0)
                {
                    // update the dictionary to reflect the nonce count just received in this request
                	nonces.remove(rnonce);
                	Hashtable<Integer, LocalDateTime> newfinger = new Hashtable<Integer, LocalDateTime>();
                	newfinger.put(Integer.valueOf(nonceCount), LocalDateTime.now().plusMinutes(10));
          	        nonces.put(rnonce, newfinger);

                    // Every thing looks ok - server nonce is fresh and nonce count seems to be 
                    // incremented. Does not look like replay.
                    return true;
                }
            }
        }

        return false;	
	}
}
