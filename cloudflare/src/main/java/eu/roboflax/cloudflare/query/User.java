/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.query;

import eu.roboflax.cloudflare.objects.user.UserDetails;
import eu.roboflax.cloudflare.query.user.*;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface User extends UserBilling, UserSubscription, UserFirewall, UserOrganization, UserInvite {
    
    CompletableFuture<UserDetails> getUserDetails( );
    
    CompletableFuture<UserDetails> updateUserDetails( Map<String, Object> properties );
    
}
