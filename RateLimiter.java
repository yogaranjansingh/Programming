import java.util.*;

class Solution {
    
    static Map<String, Info> requestMap = new HashMap<String, Info>();
    static Map<String, Integer> creditMap = new HashMap<String,Integer>();
    
    static int Y = 3;
    static int X = 3;
    static long allowedTimeWindow = (Y-1)*1000;
    
    public static void main(String args[]) throws Exception
    {
        creditMap.put("yoga", 5);
        System.out.println(rateLimit("yoga"));
        System.out.println(rateLimit("yoga"));
        System.out.println(rateLimit("yoga"));
        System.out.println(rateLimit("yoga"));
        System.out.println(rateLimit("yoga"));
        System.out.println(rateLimit("yoga"));
        
        System.out.println(rateLimit("xyz"));
        System.out.println(rateLimit("xyz"));
        System.out.println(rateLimit("xyz"));
        System.out.println(rateLimit("xyz"));
        System.out.println(rateLimit("xyz"));
        System.out.println(rateLimit("xyz"));
        Thread.sleep(4000);
        System.out.println(rateLimit("yoga"));
        System.out.println(rateLimit("xyz"));
    }
    
    static boolean rateLimit(String customerId)
    {
        Info newInfo = new Info(1, System.currentTimeMillis());
        if(requestMap.containsKey(customerId))
        {
            Info info = requestMap.get(customerId);
    
            if(System.currentTimeMillis()-info.time > allowedTimeWindow)
            {
                requestMap.put(customerId, newInfo);
            }
            else{
                
            int creditsLeft = creditMap.get(customerId);
                
            if(info.counter>X-1 && creditsLeft<=0) {
                return false;
            }
                info.counter++;
                requestMap.put(customerId, info);
            }
        }
        else
        {
            requestMap.put(customerId, newInfo);
        }
        
        creditMap.put(customerId, creditMap.getOrDefault(customerId,0)-1);
        return true;
    }
    
}

class Info {
    int counter;
    long time;
    
    public Info(int counter, long time)
    {
        this.counter = counter;
        this.time = time;
    }
}
