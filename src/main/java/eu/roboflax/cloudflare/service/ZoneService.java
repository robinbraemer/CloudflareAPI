/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import eu.roboflax.cloudflare.CloudflareAccess;
import eu.roboflax.cloudflare.CloudflareRequest;
import eu.roboflax.cloudflare.CloudflareResponse;
import eu.roboflax.cloudflare.Pagination;
import eu.roboflax.cloudflare.constants.Category;
import eu.roboflax.cloudflare.constants.Match;
import eu.roboflax.cloudflare.objects.zone.RatePlan;
import eu.roboflax.cloudflare.objects.zone.Zone;
import eu.roboflax.cloudflare.objects.zone.ZoneSetting;
import io.joshworks.restclient.http.HttpMethod;
import io.joshworks.restclient.http.HttpResponse;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static eu.roboflax.cloudflare.util.CloudflareUtils.buildObjectByIdMap;
import static eu.roboflax.cloudflare.util.CloudflareUtils.isValidHttpResponse;

public class ZoneService extends Service implements eu.roboflax.cloudflare.query.Zone {
    
    public ZoneService( CloudflareAccess cloudflareAccess ) {
        super( cloudflareAccess );
    }
    
    @Override
    public CompletableFuture<Zone> createZone( String domainName ) {
        CompletableFuture<Zone> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            try {
                future.complete( createZone( domainName, null ).get() );
            } catch ( InterruptedException e ) {
                future.completeExceptionally( e );
            } catch ( ExecutionException e ) {
                future.completeExceptionally( e.getCause() );
            }
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Zone> createZone( String domainName, Map<String, Object> optionalParameters ) {
        CompletableFuture<Zone> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.CREATE_ZONE, cloudflareAccess )
                    .body( "name", domainName )
                    .body( optionalParameters ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( gson.fromJson( http.getBody().getAsObject(), Zone.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Boolean> deleteZone( String zoneId ) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.DELETE_ZONE, cloudflareAccess )
                    .orderedIdentifiers( zoneId ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( http.getBody().getAsObject().has( "id" ) );
            else future.complete( false );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Boolean> initAnotherZoneActivationCheck( String zoneId ) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.INITIATE_ANOTHER_ZONE_ACTIVATION_CHECK, cloudflareAccess )
                    .orderedIdentifiers( zoneId ).send();
            if ( isValidHttpResponse( http, future ) ) {
                if ( http.getBody().getAsObject().has( "id" ) )
                    future.complete( zoneId.equals( http.getBody().getAsObject().get( "id" ).getAsString() ) );
                future.complete( false );
            }
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Zone> editProperties( String zoneId, Map<String, Object> optionalParameters ) {
        CompletableFuture<Zone> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.EDIT_ZONE_PROPERTIES, cloudflareAccess )
                    .orderedIdentifiers( zoneId )
                    .body( optionalParameters ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( gson.fromJson( http.getBody().getAsObject(), Zone.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Boolean> purgeAllFiles( String zoneId ) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.PURGE_ZONE_ALL_FILES, cloudflareAccess )
                    .orderedIdentifiers( zoneId )
                    .body( "purge_everything", "true" ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( true );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Boolean> purgeFilesByUrl( String zoneId, String filesJsonArray ) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.PURGE_ZONE_FILES_BY_URL, cloudflareAccess )
                    .orderedIdentifiers( zoneId )
                    .body( "files", filesJsonArray ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( true );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Boolean> purgeFilesByCacheTagsOrHost( String zoneId, @Nullable String cacheTagsJsonArray, @Nullable String hostsJsonArray ) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.PURGE_ZONE_FILES_BY_CACHE_TAG_OR_HOST, cloudflareAccess )
                    .orderedIdentifiers( zoneId )
                    .body( "tags", cacheTagsJsonArray )
                    .body( "hosts", hostsJsonArray ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( true );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Map<String, ZoneSetting>> editSettings( String zoneId, ZoneSetting... zoneSettings ) {
        CompletableFuture<Map<String, ZoneSetting>> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            JsonArray items = new JsonArray();
            JsonObject jsonObject;
            for ( int i = 0; i <= zoneSettings.length - 1; i++ ) {
                jsonObject = jsonParser.parse( gson.toJson( zoneSettings[i] ) ).getAsJsonObject();
                jsonObject.remove( "modified_on" );
                jsonObject.remove( "editable" );
                items.add( jsonParser.parse( gson.toJson( zoneSettings[i] ) ) );
            }
            
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.EDIT_ZONE_SETTINGS_INFO, cloudflareAccess )
                    .orderedIdentifiers( zoneId )
                    .body( "items", items ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( buildObjectByIdMap( http, ZoneSetting.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Map<String, ZoneSetting>> editSettings( String zoneId, Collection<ZoneSetting> zoneSettings ) {
        CompletableFuture<Map<String, ZoneSetting>> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            try {
                future.complete( editSettings( zoneId, zoneSettings.toArray( new ZoneSetting[zoneSettings.size()] ) ).get() );
            } catch ( InterruptedException e ) {
                future.completeExceptionally( e );
            } catch ( ExecutionException e ) {
                future.completeExceptionally( e.getCause() );
            }
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Map<String, Zone>> getZones( @Nullable String type, @Nullable String name, @Nullable String content,
                                                          @Nullable Match match, @Nullable Pagination pagination ) {
        CompletableFuture<Map<String, Zone>> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) ->
                new CloudflareRequest( Category.LIST_ZONES, cloudflareAccess )
                        .queryString( "type", type )
                        .queryString( "name", name )
                        .queryString( "content", content )
                        .queryString( "match", match )
                        .queryString( pagination != null ? pagination.getAsQueryStringsMap() : null )
                        .send( (httpResponse -> {
                            if ( isValidHttpResponse( httpResponse, future ) )
                                future.complete( buildObjectByIdMap( httpResponse, Zone.class ) );
                        }) ) );
        return future;
    }
    
    @Override
    public CompletableFuture<Map<String, Zone>> getZones( ) {
        return getZones( null, null, null, null, null );
    }
    
    @Override
    public CompletableFuture<Zone> getZone( String zoneId ) {
        CompletableFuture<Zone> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.ZONE_DETAILS, cloudflareAccess )
                    .orderedIdentifiers( zoneId ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( gson.fromJson( http.getBody().getAsObject(), Zone.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<ZoneSetting> getSetting( String zoneId, Category setting ) {
        CompletableFuture<ZoneSetting> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            if ( !(setting.getHttpMethod().equals( HttpMethod.GET ) && setting.getPath().contains( "zones/{id-1}/settings/" )) )
                throw new IllegalArgumentException( String.format( "Passed category '%s' is not a single gettable zone setting!", setting.name() ) );
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( setting, cloudflareAccess )
                    .orderedIdentifiers( zoneId ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( gson.fromJson( http.getBody().getAsObject(), ZoneSetting.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Map<String, ZoneSetting>> getSettings( String zoneId ) {
        CompletableFuture<Map<String, ZoneSetting>> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.LIST_ZONE_SETTINGS, cloudflareAccess )
                    .orderedIdentifiers( zoneId ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( buildObjectByIdMap( http, ZoneSetting.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<RatePlan> getRatePlan( String zoneId ) {
        CompletableFuture<RatePlan> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) ->
                new CloudflareRequest( Category.ZONE_RATE_PLAN, cloudflareAccess )
                        .orderedIdentifiers( zoneId )
                        .send( httpResponse -> {
                            if ( isValidHttpResponse( httpResponse, future ) )
                                future.complete( gson.fromJson( httpResponse.getBody().getAsObject(), RatePlan.class ) );
                        } ) );
        return future;
    }
}
