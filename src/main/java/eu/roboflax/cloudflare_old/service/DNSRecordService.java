/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare_old.service;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import eu.roboflax.cloudflare_old.CloudflareAccess;
import eu.roboflax.cloudflare_old.CloudflareRequest;
import eu.roboflax.cloudflare_old.Pagination;
import eu.roboflax.cloudflare_old.constants.Category;
import eu.roboflax.cloudflare_old.constants.Match;
import eu.roboflax.cloudflare_old.constants.RecordType;
import eu.roboflax.cloudflare_old.objects.dns.DNSRecord;
import eu.roboflax.cloudflare_old.query.DNS;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static eu.roboflax.cloudflare_old.util.CloudflareUtils.isValidHttpResponse;

public class DNSRecordService extends Service implements DNS {
    
    
    public DNSRecordService( CloudflareAccess cloudflareAccess ) {
        super( cloudflareAccess );
    }
    
    @Override
    public CompletableFuture<DNSRecord> createDNSRecord( String zoneId, RecordType type, String name, String content,
                                                         @Nullable Integer ttl, @Nullable Boolean proxied ) {
        CompletableFuture<DNSRecord> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) ->
                new CloudflareRequest( Category.CREATE_DNS_RECORD, cloudflareAccess )
                        .orderedIdentifiers( zoneId )
                        .body( "type", type.name() )
                        .body( "content", content )
                        .body( "ttl", ttl )
                        .body( "proxied", proxied )
                        .send( ( httpResponse ) -> {
                            if ( isValidHttpResponse( httpResponse, future ) )
                                future.complete( gson.fromJson( httpResponse.getBody().getAsObject(), DNSRecord.class ) );
                        } ) );
        return future;
    }
    
    @Override
    public CompletableFuture<DNSRecord> createDNSRecord( String zoneId, RecordType type, String name, String content ) {
        return createDNSRecord( zoneId, type, name, content, null, null );
    }
    
    @Override
    public CompletableFuture<Map<String, DNSRecord>> listDNSRecords( String zoneId, @Nullable RecordType type,
                                                                     @Nullable String name, @Nullable String content,
                                                                     @Nullable Match match, @Nullable Pagination pagination ) {
        CompletableFuture<Map<String, DNSRecord>> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) ->
                new CloudflareRequest( Category.LIST_DNS_RECORDS, cloudflareAccess )
                        .orderedIdentifiers( zoneId )
                        .queryString( "type", type != null ? type.name() : null )
                        .queryString( "name", name )
                        .queryString( "content", content )
                        .queryString( "match", match != null ? match.name().toLowerCase() : null )
                        .queryString( pagination != null ? pagination.getAsQueryStringsMap() : null )
                        .send( ( httpResponse ) -> {
                            if ( isValidHttpResponse( httpResponse, future ) ) {
                                Map<String, DNSRecord> dnsRecordByIdMap = Maps.newHashMap();
                                DNSRecord dnsRecord;
                                for ( JsonElement jsonRecord : httpResponse.getBody().getAsArray() ) {
                                    dnsRecord = gson.fromJson( jsonRecord.getAsJsonObject(), DNSRecord.class );
                                    dnsRecordByIdMap.put( dnsRecord.getId(), dnsRecord );
                                }
                                future.complete( dnsRecordByIdMap );
                            }
                        } ) );
        return future;
    }
    
    @Override
    public CompletableFuture<Map<String, DNSRecord>> listDNSRecords( String zoneId, @Nullable Pagination pagination ) {
        return listDNSRecords( zoneId, null, null, null, null, pagination );
    }
    
    @Override
    public CompletableFuture<Map<String, DNSRecord>> listDNSRecords( String zoneId ) {
        return listDNSRecords( zoneId, null );
    }
    
    @Override
    public CompletableFuture<DNSRecord> getDNSRecord( String zoneId, String recordId ) {
        CompletableFuture<DNSRecord> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) ->
                new CloudflareRequest( Category.DNS_RECORD_DETAILS, cloudflareAccess )
                        .orderedIdentifiers( zoneId, recordId )
                        .send( ( httpResponse ) -> {
                            if ( isValidHttpResponse( httpResponse, future ) )
                                future.complete( gson.fromJson( httpResponse.getBody().getAsObject(), DNSRecord.class ) );
                        } ) );
        return future;
    }
    
    @Override
    public CompletableFuture<DNSRecord> updateDNSRecord( String zoneId, String recordId, RecordType type, String name, String content,
                                                         @Nullable Integer ttl, @Nullable Boolean proxied ) {
        CompletableFuture<DNSRecord> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) ->
                new CloudflareRequest( Category.UPDATE_DNS_RECORD, cloudflareAccess )
                        .orderedIdentifiers( zoneId )
                        .body( "type", type != null ? type.name() : null )
                        .body( "content", content )
                        .body( "ttl", ttl )
                        .body( "proxied", proxied )
                        .send( ( httpResponse ) -> {
                            if ( isValidHttpResponse( httpResponse, future ) )
                                future.complete( gson.fromJson( httpResponse.getBody().getAsObject(), DNSRecord.class ) );
                        } ) );
        return future;
    }
    
    @Override
    public CompletableFuture<DNSRecord> updateDNSRecord( String zoneId, String recordId, RecordType type, String name, String content ) {
        return updateDNSRecord( zoneId, recordId, type, name, content, null, null );
    }
    
    @Override
    public CompletableFuture<Boolean> deleteDNSRecord( String zoneId, String recordId ) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) ->
                new CloudflareRequest( Category.DELETE_DNS_RECORD, cloudflareAccess )
                        .orderedIdentifiers( zoneId )
                        .send( ( httpResponse ) -> {
                            if ( isValidHttpResponse( httpResponse, future ) )
                                future.complete( httpResponse.getBody().getAsObject().has( "id" ) );
                            else future.complete( false );
                        } ) );
        return future;
    }
    
    
}
