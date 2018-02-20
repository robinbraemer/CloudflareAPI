/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 *
 * Project cloudflare created by RoboFlax.
 * Class created on 22.12.2017 at 17:34.
 *
 */
package eu.roboflax.cloudflare.query.zone;

import eu.roboflax.cloudflare.objects.zone.Zone;
import eu.roboflax.cloudflare.objects.zone.ZoneSetting;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface ZoneEdit {
    
    CompletableFuture<Zone> createZone( String domainName );
    
    CompletableFuture<Zone> createZone( String domainName, Map<String, Object> optionalParameters );
    
    CompletableFuture<Boolean> deleteZone( String zoneId );
    
    CompletableFuture<Boolean> initAnotherZoneActivationCheck( String zoneId );
    
    CompletableFuture<Zone> editProperties( String zoneId, Map<String, Object> optionalParameters );
    
    CompletableFuture<Boolean> purgeAllFiles( String zoneId );
    
    CompletableFuture<Boolean> purgeFilesByUrl( String zoneId, String filesJsonArray );
    
    CompletableFuture<Boolean> purgeFilesByCacheTagsOrHost( String zoneId, @Nullable String cacheTagsJsonArray, @Nullable String hostsJsonArray );
    
    CompletableFuture<Map<String, ZoneSetting>> editSettings( String zoneId, ZoneSetting... zoneSettings );
    
    CompletableFuture<Map<String, ZoneSetting>> editSettings( String zoneId, Collection<ZoneSetting> zoneSettings );
}
