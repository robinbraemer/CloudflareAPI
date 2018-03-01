/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.query.user;

import eu.roboflax.cloudflare.constants.ParamValues;
import eu.roboflax.cloudflare.objects.accessrule.AccessRule;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface UserFirewall {
    
    CompletableFuture<Map<String, AccessRule>> getAccessRules( Map<String, Object> optionalQueryParameters );
    
    CompletableFuture<Map<String, AccessRule>> getAccessRules( );
    
    CompletableFuture<AccessRule> getAccessRule( String accessRuleId );
    
    CompletableFuture<AccessRule> createAccessRule( ParamValues.Mode mode, String target, String value, String notes );
    
    CompletableFuture<AccessRule> createAccessRule( ParamValues.Mode mode, String target, String value );
    
    CompletableFuture<AccessRule> updateAccessRule( String accessRuleId, ParamValues.Mode mode, String notes );
    
    CompletableFuture<AccessRule> updateAccessRule( String accessRuleId, String notes );
    
    CompletableFuture<AccessRule> updateAccessRule( String accessRuleId, ParamValues.Mode mode );
    
    CompletableFuture<Boolean> deleteAccessRule( String accessRuleId );
}
