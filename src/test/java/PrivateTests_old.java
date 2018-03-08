/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */

import eu.roboflax.cloudflare_old.CloudflareAccess;
import eu.roboflax.cloudflare_old.CloudflareConfig;
import eu.roboflax.cloudflare_old.Pagination;
import eu.roboflax.cloudflare_old.constants.Direction;
import eu.roboflax.cloudflare_old.constants.RecordType;
import eu.roboflax.cloudflare_old.objects.dns.DNSRecord;
import eu.roboflax.cloudflare_old.objects.user.UserDetails;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class PrivateTests_old {
    
    private static final String CF_EMAIL = "robin.braemer@web.de";
    private static final String CF_API_KEY = "8d06955636df588026368fa0211a05c234a69";
    private static final CloudflareConfig cloudflareConfig = new CloudflareConfig( CF_API_KEY, CF_EMAIL );
    private static final CloudflareAccess cloudflareAccess = new CloudflareAccess( cloudflareConfig );
    
    private static final String zoneId = "2f92391d4b0b2bfdc19ac61a6ffd51ff";
    
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
    public void testDNSService( ) {
        CompletableFuture<Map<String, DNSRecord>> future = cloudflareAccess.dnsRecordService().listDNSRecords(
                zoneId, Pagination.builder()
                        .page( 1 )
                        .direction( Direction.ASC )
                        .order( "type" )
                        .perPage( 1 )
                        .build()
        );
        try {
            Map<String, DNSRecord> records = future.get();
            records.forEach( ( k, v ) -> System.out.println( k + ": " + v ) );
        } catch ( InterruptedException | ExecutionException e ) {
            e.printStackTrace();
        }
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
