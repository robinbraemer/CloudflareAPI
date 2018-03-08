/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare_old.query.zone;

import eu.roboflax.cloudflare_old.Pagination;
import eu.roboflax.cloudflare_old.constants.Category;
import eu.roboflax.cloudflare_old.constants.Match;
import eu.roboflax.cloudflare_old.objects.zone.Zone;
import eu.roboflax.cloudflare_old.objects.zone.ZoneSetting;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


public interface ZoneLookup {
    
    CompletableFuture<Map<String, Zone>> getZones( );
    
    CompletableFuture<Map<String, Zone>> getZones( @Nullable String type, @Nullable String name, @Nullable String content,
                                                   @Nullable Match match, @Nullable Pagination pagination );
    
    CompletableFuture<Zone> getZone( String zoneId );
    
    CompletableFuture<ZoneSetting> getSetting( String zoneId, Category setting );
    
    CompletableFuture<Map<String, ZoneSetting>> getSettings( String zoneId );
}
