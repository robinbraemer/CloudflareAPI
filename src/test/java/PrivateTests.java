/*
 * Copyright (c) RoboFlax.
 * All rights reserved.
 */

import eu.roboflax.cloudflare.CloudflareAccess;
import eu.roboflax.cloudflare.CloudflareConfig;
import eu.roboflax.cloudflare.objects.user.UserDetails;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class PrivateTests {
    
    private static final String CF_EMAIL = "robin.braemer@web.de";
    private static final String CF_API_KEY = "c128d4bcccd0621894155c6cba44580264f8e";
    private static final CloudflareConfig cloudflareConfig = new CloudflareConfig( CF_API_KEY, CF_EMAIL );
    private static final CloudflareAccess cloudflareAccess = new CloudflareAccess( cloudflareConfig );
    
    @Test
    public void runtimeStats( ) {
        System.out.println( "Runtime Stats..." );
        System.out.println( "Available Processors: " + Runtime.getRuntime().availableProcessors() );
        System.out.println( "Free Memory: " + Runtime.getRuntime().freeMemory() + " bytes" );
        System.out.println( "Total Memory: " + Runtime.getRuntime().totalMemory() + " bytes" );
        System.out.println( "Max Memory: " + Runtime.getRuntime().maxMemory() + " bytes" );
        System.out.println( "Used Memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) + " bytes" );
    }
    
    @Test
    public void testUserDetails( ) {
        System.out.println( "UserDetails..." );
        CompletableFuture<UserDetails> userDetailsFuture = cloudflareAccess.userService().getUserDetails();
        try {
            UserDetails details = userDetailsFuture.get();
            System.out.println( details );
            System.out.println( details.getUsername() );
            System.out.println( details.getZipCode() );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        } catch ( ExecutionException e ) {
            System.err.println( "Error while getting user details:" );
            e.getCause().printStackTrace();
        }
        
        Map<String, Object> newDetails = new HashMap<>();
        newDetails.put( "first_name", "Robin" );
        newDetails.put( "country", "DE" );
        cloudflareAccess.userService().updateUserDetails( newDetails ).join();
        
    }
    
    @Test
    public void testZone( ) {
        System.out.println( "Zones..." );
        cloudflareAccess.zoneService().getZones().join()
                .forEach( ( id, zone ) -> System.out.println( zone.getPermissions() ) );
    }
    
}