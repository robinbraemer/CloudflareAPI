/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 *
 * Project cloudflare created by RoboFlax.
 * Class created on 21.12.2017 at 16:04.
 *
 */
package eu.roboflax.cloudflare.query;

import eu.roboflax.cloudflare.query.zone.ZoneEdit;
import eu.roboflax.cloudflare.query.zone.ZoneLookup;
import eu.roboflax.cloudflare.query.zone.ZoneRatePlan;

public interface Zone extends ZoneLookup, ZoneEdit, ZoneRatePlan {
    
    
}
