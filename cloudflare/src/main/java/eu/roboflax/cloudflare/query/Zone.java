/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.query;

import eu.roboflax.cloudflare.query.zone.ZoneEdit;
import eu.roboflax.cloudflare.query.zone.ZoneLookup;
import eu.roboflax.cloudflare.query.zone.ZoneRatePlan;

public interface Zone extends ZoneLookup, ZoneEdit, ZoneRatePlan {
    
    
}
