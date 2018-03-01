/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.query.zone;

import eu.roboflax.cloudflare.constants.Category;
import eu.roboflax.cloudflare.objects.zone.Zone;
import eu.roboflax.cloudflare.objects.zone.ZoneSetting;

import java.util.Map;
import java.util.concurrent.CompletableFuture;


public interface ZoneLookup {
    
    CompletableFuture<Map<String, Zone>> getZones( );
    
    CompletableFuture<Map<String, Zone>> getZones( Map<String, Object> optionalQueryParameters );
    
    CompletableFuture<Zone> getZone( String zoneId );
    
    CompletableFuture<ZoneSetting> getSetting( String zoneId, Category setting);
    
    CompletableFuture<Map<String, ZoneSetting>> getSettings( String zoneId );
}
