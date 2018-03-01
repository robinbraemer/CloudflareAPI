/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.query.user;

import eu.roboflax.cloudflare.constants.ParamValues;
import eu.roboflax.cloudflare.objects.user.invite.Invite;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface UserInvite {
    
    CompletableFuture<Map<String, Invite>> getInvites( );
    
    CompletableFuture<Invite> getInvite( String inviteId );
    
    CompletableFuture<Invite> setInviteStatus( String inviteId, ParamValues.Status status );
}
