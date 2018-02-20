/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 *
 * Project cloudflare created by RoboFlax.
 * Class created on 22.12.2017 at 15:59.
 *
 */
package eu.roboflax.cloudflare.query.user;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface UserOrganization {
    
    CompletableFuture<Map<String, eu.roboflax.cloudflare.objects.user.UserOrganization>> getOrganizations( Map<String, Object> optionalQueryParameters );
    
    CompletableFuture<Map<String, eu.roboflax.cloudflare.objects.user.UserOrganization>> getOrganizations( );
    
    CompletableFuture<eu.roboflax.cloudflare.objects.user.UserOrganization> getOrganization( String organizationId );
    
    CompletableFuture<Boolean> leaveOrganization( String organizationId );
}
