/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.query.user;

import eu.roboflax.cloudflare.constants.ParamValues;
import eu.roboflax.cloudflare.objects.user.subscription.Subscription;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface UserSubscription {
    /**
     * @return Key: Subscription-ID, Value: Subscription
     */
    CompletableFuture<Map<String, Subscription>> getSubscriptions( );
    
    CompletableFuture<Subscription> getSubscription( String subscriptionId );
    
    CompletableFuture<Subscription> updateSubscription( String subscriptionId, int price, String currency, ParamValues.Frequency frequency, Map<String, Object> optionalParameters );
    
    CompletableFuture<String> deleteSubscription( String subscriptionId );
}
