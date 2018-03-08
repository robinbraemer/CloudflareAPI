/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare_old.query.user;

import eu.roboflax.cloudflare_old.objects.user.billing.Billing;
import eu.roboflax.cloudflare_old.objects.user.billing.BillingProfile;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface UserBilling {
    
    CompletableFuture<BillingProfile> getBillingProfile( );
    
    CompletableFuture<Map<String, Billing>> getBillingHistory( Map<String, Object> optionalQueryParameters );
    
}
