/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 *
 * Project cloudflare created by RoboFlax.
 * Class created on 22.12.2017 at 09:42.
 *
 */
package eu.roboflax.cloudflare.query.user;

import eu.roboflax.cloudflare.objects.user.billing.Billing;
import eu.roboflax.cloudflare.objects.user.billing.BillingProfile;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface UserBilling {
    
    CompletableFuture<BillingProfile> getBillingProfile( );
    
    CompletableFuture<Map<String, Billing>> getBillingHistory( Map<String, Object> optionalQueryParameters);
    
}
