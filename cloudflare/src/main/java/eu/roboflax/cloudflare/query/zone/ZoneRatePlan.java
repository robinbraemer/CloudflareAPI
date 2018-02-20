/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 *
 * Project cloudflare created by RoboFlax.
 * Class created on 22.12.2017 at 19:37.
 *
 */
package eu.roboflax.cloudflare.query.zone;

import eu.roboflax.cloudflare.objects.zone.RatePlan;

import java.util.concurrent.CompletableFuture;

public interface ZoneRatePlan {
    
    CompletableFuture<RatePlan> getRatePlan( String zoneId );
}
