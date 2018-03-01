/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.service;

import com.google.gson.JsonObject;
import eu.roboflax.cloudflare.CloudflareAccess;
import eu.roboflax.cloudflare.CloudflareRequest;
import eu.roboflax.cloudflare.CloudflareResponse;
import eu.roboflax.cloudflare.constants.Category;
import eu.roboflax.cloudflare.constants.ParamValues;
import eu.roboflax.cloudflare.objects.accessrule.AccessRule;
import eu.roboflax.cloudflare.objects.user.UserDetails;
import eu.roboflax.cloudflare.objects.user.UserOrganization;
import eu.roboflax.cloudflare.objects.user.billing.Billing;
import eu.roboflax.cloudflare.objects.user.billing.BillingProfile;
import eu.roboflax.cloudflare.objects.user.invite.Invite;
import eu.roboflax.cloudflare.objects.user.subscription.Subscription;
import eu.roboflax.cloudflare.query.User;
import io.joshworks.restclient.http.HttpResponse;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static eu.roboflax.cloudflare.util.CloudflareUtils.buildObjectByIdMap;
import static eu.roboflax.cloudflare.util.CloudflareUtils.isValidHttpResponse;

public class UserService extends Service implements User {
    
    public UserService( CloudflareAccess cloudflareAccess ) {
        super( cloudflareAccess );
    }
    
    @Override
    public CompletableFuture<UserDetails> getUserDetails( ) {
        CompletableFuture<UserDetails> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.USER_DETAILS, cloudflareAccess ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( gson.fromJson( http.getBody().getAsObject(), UserDetails.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<UserDetails> updateUserDetails( Map<String, Object> properties ) {
        CompletableFuture<UserDetails> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.UPDATE_USER, cloudflareAccess )
                    .body( properties ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( gson.fromJson( http.getBody().getAsObject(), UserDetails.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Map<String, Subscription>> getSubscriptions( ) {
        CompletableFuture<Map<String, Subscription>> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.USER_SUBSCRIPTION, cloudflareAccess ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( buildObjectByIdMap( http, Subscription.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Subscription> getSubscription( String subscriptionId ) {
        CompletableFuture<Subscription> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            try {
                future.complete( getSubscriptions().get().get( subscriptionId ) );
            } catch ( InterruptedException e ) {
                future.completeExceptionally( e );
            } catch ( ExecutionException e ) {
                future.completeExceptionally( e.getCause() );
            }
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Subscription> updateSubscription( String subscriptionId, int price, String currency, ParamValues.Frequency frequency, Map<String, Object> optionalParameters ) {
        CompletableFuture<Subscription> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.UPDATE_USER_SUBSCRIPTION, cloudflareAccess )
                    .orderedIdentifiers( subscriptionId )
                    .body( "price", price )
                    .body( "currency", currency )
                    .body( "id", subscriptionId )
                    .body( "Frequency", frequency.name().toLowerCase() )
                    .body( optionalParameters ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( gson.fromJson( http.getBody().getAsObject(), Subscription.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<String> deleteSubscription( String subscriptionId ) {
        CompletableFuture<String> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.DELETE_USER_SUBSCRIPTION, cloudflareAccess )
                    .orderedIdentifiers( subscriptionId ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( http.getBody().getAsObject().get( "subscription_id" ).getAsString() );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<BillingProfile> getBillingProfile( ) {
        CompletableFuture<BillingProfile> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.USER_BILLING_PROFILE, cloudflareAccess ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( gson.fromJson( http.getBody().getAsObject(), BillingProfile.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Map<String, Billing>> getBillingHistory( Map<String, Object> optionalQueryParameters ) {
        CompletableFuture<Map<String, Billing>> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.USER_BILLING_HISTORY, cloudflareAccess )
                    .queryString( optionalQueryParameters ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( buildObjectByIdMap( http, Billing.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Map<String, AccessRule>> getAccessRules( Map<String, Object> optionalQueryParameters ) {
        CompletableFuture<Map<String, AccessRule>> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.LIST_USER_ACCESS_RULES, cloudflareAccess )
                    .queryString( optionalQueryParameters ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( buildObjectByIdMap( http, AccessRule.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Map<String, AccessRule>> getAccessRules( ) {
        CompletableFuture<Map<String, AccessRule>> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            try {
                future.complete( getAccessRules( null ).get() );
            } catch ( InterruptedException e ) {
                future.completeExceptionally( e );
            } catch ( ExecutionException e ) {
                future.completeExceptionally( e.getCause() );
            }
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<AccessRule> getAccessRule( String accessRuleId ) {
        CompletableFuture<AccessRule> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            try {
                future.complete( getAccessRules().get().get( accessRuleId ) );
            } catch ( InterruptedException e ) {
                future.completeExceptionally( e );
            } catch ( ExecutionException e ) {
                future.completeExceptionally( e.getCause() );
            }
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<AccessRule> createAccessRule( ParamValues.Mode mode, String target, String value, String notes ) {
        CompletableFuture<AccessRule> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            JsonObject configuration = new JsonObject();
            configuration.addProperty( "target", target );
            configuration.addProperty( "value", value );
            
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.CREATE_USER_ACCESS_RULE, cloudflareAccess )
                    .body( "Mode", mode.name().toLowerCase() )
                    .body( "configuration", configuration )
                    .body( "notes", notes ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( gson.fromJson( http.getBody().getAsObject(), AccessRule.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<AccessRule> createAccessRule( ParamValues.Mode mode, String target, String value ) {
        CompletableFuture<AccessRule> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            try {
                future.complete( createAccessRule( mode, target, value, null ).get() );
            } catch ( InterruptedException e ) {
                future.completeExceptionally( e );
            } catch ( ExecutionException e ) {
                future.completeExceptionally( e.getCause() );
            }
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<AccessRule> updateAccessRule( String accessRuleId, ParamValues.Mode mode, String notes ) {
        CompletableFuture<AccessRule> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.UPDATE_USER_ACCESS_RULE, cloudflareAccess )
                    .orderedIdentifiers( accessRuleId )
                    .body( "Mode", mode )
                    .body( "notes", notes ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( gson.fromJson( http.getBody().getAsObject(), AccessRule.class ) );
        } );
        return future;
        
    }
    
    @Override
    public CompletableFuture<AccessRule> updateAccessRule( String accessRuleId, String notes ) {
        CompletableFuture<AccessRule> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            try {
                future.complete( updateAccessRule( accessRuleId, null, notes ).get() );
            } catch ( InterruptedException e ) {
                future.completeExceptionally( e );
            } catch ( ExecutionException e ) {
                future.completeExceptionally( e.getCause() );
            }
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<AccessRule> updateAccessRule( String accessRuleId, ParamValues.Mode mode ) {
        CompletableFuture<AccessRule> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            try {
                future.complete( updateAccessRule( accessRuleId, mode, null ).get() );
            } catch ( InterruptedException e ) {
                future.completeExceptionally( e );
            } catch ( ExecutionException e ) {
                future.completeExceptionally( e.getCause() );
            }
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Boolean> deleteAccessRule( String accessRuleId ) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.DELETE_USER_ACCESS_RULE, cloudflareAccess )
                    .orderedIdentifiers( accessRuleId ).send();
            if ( isValidHttpResponse( http, future ) ) {
                if ( http.getBody().getAsObject().has( "id" ) )
                    future.complete( accessRuleId.equals( http.getBody().getAsObject().get( "id" ).getAsString() ) );
                future.complete( false );
            }
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Map<String, UserOrganization>> getOrganizations( Map<String, Object> optionalQueryParameters ) {
        CompletableFuture<Map<String, UserOrganization>> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.LIST_USER_ORGANIZATIONS, cloudflareAccess )
                    .queryString( optionalQueryParameters ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( buildObjectByIdMap( http, UserOrganization.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Map<String, UserOrganization>> getOrganizations( ) {
        CompletableFuture<Map<String, UserOrganization>> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            try {
                future.complete( getOrganizations( null ).get() );
            } catch ( InterruptedException e ) {
                future.completeExceptionally( e );
            } catch ( ExecutionException e ) {
                future.completeExceptionally( e.getCause() );
            }
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<UserOrganization> getOrganization( String organizationId ) {
        CompletableFuture<UserOrganization> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.USER_ORGANIZATION_DETAILS, cloudflareAccess )
                    .orderedIdentifiers( organizationId ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( gson.fromJson( http.getBody().getAsObject(), UserOrganization.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Boolean> leaveOrganization( String organizationId ) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.USER_LEAVE_ORGANIZATION, cloudflareAccess )
                    .orderedIdentifiers( organizationId ).send();
            if ( isValidHttpResponse( http, future ) ) {
                if ( http.getBody().getAsObject().has( "id" ) )
                    future.complete( organizationId.equals( http.getBody().getAsObject().get( "id" ).getAsString() ) );
                future.complete( false );
            }
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Map<String, Invite>> getInvites( ) {
        CompletableFuture<Map<String, Invite>> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.LIST_USER_INVITES, cloudflareAccess ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( buildObjectByIdMap( http, Invite.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Invite> getInvite( String inviteId ) {
        CompletableFuture<Invite> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.USER_INVITATION_DETAILS, cloudflareAccess )
                    .orderedIdentifiers( inviteId ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( gson.fromJson( http.getBody().getAsObject(), Invite.class ) );
        } );
        return future;
    }
    
    @Override
    public CompletableFuture<Invite> setInviteStatus( String inviteId, ParamValues.Status status ) {
        CompletableFuture<Invite> future = new CompletableFuture<>();
        cloudflareAccess.getThreadPool().submit( ( ) -> {
            HttpResponse<CloudflareResponse> http = new CloudflareRequest( Category.USER_RESPOND_INVITATION, cloudflareAccess )
                    .orderedIdentifiers( inviteId )
                    .body( "Status", status.name().toLowerCase() ).send();
            if ( isValidHttpResponse( http, future ) )
                future.complete( gson.fromJson( http.getBody().getAsObject(), Invite.class ) );
        } );
        return future;
    }
}

