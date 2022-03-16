package com.amazon.elasticache;

import java.io.IOException;
import java.net.InetSocketAddress;

// Import the &AWS;-provided library with Auto Discovery support 
import net.spy.memcached.MemcachedClient;  

public class AutoDiscoveryDemo {

    public static void main(String[] args) throws IOException { 
        MemcachedClient client1 = new MemcachedClient(new InetSocketAddress("mycluster.fnjyzo.cfg.use1.cache.amazonaws.com", 123)); 
		client1 = new MemcachedClient(new InetSocketAddress("mycluster.fnjyzo.cfg.use1.cache.amazonaws.com2", 123));

		InetSocketAddress address = new InetSocketAddress("mycluster.fnjyzo.cfg.use1.cache.amazonaws.com3", 123);
        MemcachedClient client2 = new MemcachedClient(address);     
		
		InetAddress addr = InetAddress.getByName("127.0.0.1");
		InetSocketAddress address3 = new InetSocketAddress(addr, 123);
        MemcachedClient client3 = new MemcachedClient(address3); 
    }
}