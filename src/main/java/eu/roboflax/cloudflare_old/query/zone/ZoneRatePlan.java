/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare_old.query.zone;

import eu.roboflax.cloudflare_old.objects.zone.RatePlan;

import java.util.concurrent.CompletableFuture;

public interface ZoneRatePlan {
    
    CompletableFuture<RatePlan> getRatePlan( String zoneId );
}
