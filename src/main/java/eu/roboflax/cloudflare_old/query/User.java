/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare_old.query;

import eu.roboflax.cloudflare_old.objects.user.UserDetails;
import eu.roboflax.cloudflare_old.query.user.*;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface User extends UserBilling, UserSubscription, UserFirewall, UserOrganization, UserInvite {
    
    CompletableFuture<UserDetails> getUserDetails( );
    
    CompletableFuture<UserDetails> updateUserDetails( Map<String, Object> properties );
    
}
