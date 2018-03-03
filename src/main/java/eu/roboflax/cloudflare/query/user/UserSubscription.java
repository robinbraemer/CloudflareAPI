/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.query.user;

import eu.roboflax.cloudflare.Pagination;
import eu.roboflax.cloudflare.constants.Frequency;
import eu.roboflax.cloudflare.objects.user.subscription.Subscription;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface UserSubscription {
    
    CompletableFuture<Map<String, Subscription>> getSubscriptions( @Nullable Pagination pagination );
    
    CompletableFuture<Map<String, Subscription>> getSubscriptions( );
    
    CompletableFuture<Subscription> getSubscription( String subscriptionId );
    
    CompletableFuture<Subscription> updateSubscription( String subscriptionId, int price, String currency, Frequency frequency, Map<String, Object> optionalParameters );
    
    CompletableFuture<String> deleteSubscription( String subscriptionId );
}
