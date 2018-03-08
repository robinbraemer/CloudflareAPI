/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare_old.query;

import eu.roboflax.cloudflare_old.query.zone.ZoneEdit;
import eu.roboflax.cloudflare_old.query.zone.ZoneLookup;
import eu.roboflax.cloudflare_old.query.zone.ZoneRatePlan;

public interface Zone extends ZoneLookup, ZoneEdit, ZoneRatePlan {
    
    
}
