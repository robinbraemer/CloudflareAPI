/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.query;

import eu.roboflax.cloudflare.Pagination;
import eu.roboflax.cloudflare.constants.Match;
import eu.roboflax.cloudflare.objects.dns.DNSRecord;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface DNS {
    
    CompletableFuture<DNSRecord> createDNSRecord( String zoneId, String type, String name, String content,
                                                  @Nullable Integer ttl, @Nullable Boolean proxied );
    
    CompletableFuture<DNSRecord> createDNSRecord( String zoneId, String type, String name, String content );
    
    CompletableFuture<Map<String, DNSRecord>> listDNSRecords( String zoneId, @Nullable String type,
                                                              @Nullable String name, @Nullable String content,
                                                              @Nullable Match match, @Nullable Pagination pagination );
    
    CompletableFuture<Map<String, DNSRecord>> listDNSRecords( String zoneId, @Nullable Pagination pagination );
    
    CompletableFuture<Map<String, DNSRecord>> listDNSRecords( String zoneId );
    
    CompletableFuture<DNSRecord> getDNSRecord( String zoneId, String recordId );
    
    
    CompletableFuture<DNSRecord> updateDNSRecord( String zoneId, String recordId, String type, String name, String content,
                                                  @Nullable Integer ttl, @Nullable Boolean proxied );
    
    CompletableFuture<DNSRecord> updateDNSRecord( String zoneId, String recordId, String type, String name, String content );
    
    CompletableFuture<Boolean> deleteDNSRecord( String zoneId, String recordId );
    
    // todo CompletableFuture<Boolean> importDNSRecords( String zoneId, File bindConfig );
}
