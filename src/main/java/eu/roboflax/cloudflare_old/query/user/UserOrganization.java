/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare_old.query.user;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface UserOrganization {
    
    CompletableFuture<Map<String, eu.roboflax.cloudflare_old.objects.user.UserOrganization>> getOrganizations( Map<String, Object> optionalQueryParameters );
    
    CompletableFuture<Map<String, eu.roboflax.cloudflare_old.objects.user.UserOrganization>> getOrganizations( );
    
    CompletableFuture<eu.roboflax.cloudflare_old.objects.user.UserOrganization> getOrganization( String organizationId );
    
    CompletableFuture<Boolean> leaveOrganization( String organizationId );
}
