/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.query.zone;

import eu.roboflax.cloudflare.objects.zone.RatePlan;

import java.util.concurrent.CompletableFuture;

public interface ZoneRatePlan {
    
    CompletableFuture<RatePlan> getRatePlan( String zoneId );
}
