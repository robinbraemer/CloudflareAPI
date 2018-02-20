/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 *
 * Project cloudflare created by RoboFlax.
 * Class created on 21.12.2017 at 18:06.
 *
 */
package eu.roboflax.cloudflare;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import eu.roboflax.cloudflare.constants.Category;
import io.joshworks.restclient.http.HttpResponse;

import java.util.*;
import java.util.function.Consumer;

import static io.joshworks.restclient.http.HttpMethod.*;

public class CloudflareRequest {
    
    private CloudflareAccess cf;
    
    private Category category;
    
    private Map<String, Object> queryStrings = new HashMap<>();
    private List<String> orderedIdentifiers = new ArrayList<>();
    
    private JsonObject body = new JsonObject();
    
    public CloudflareRequest( Category category, CloudflareAccess cloudflareAccess ) {
        this.cf = cloudflareAccess;
        this.category = category;
    }
    
    public CloudflareRequest orderedIdentifiers( String... orderedIdentifiers ) {
        if ( orderedIdentifiers != null ) {
            this.orderedIdentifiers.clear();
            Collections.addAll( this.orderedIdentifiers, orderedIdentifiers );
        }
        return this;
    }
    
    public CloudflareRequest queryString( String property, Object value ) {
        if ( property != null && value != null )
            queryStrings.put( property, value );
        return this;
    }
    
    public CloudflareRequest queryString( Map<String, Object> keyValues ) {
        if ( keyValues != null )
            queryStrings.putAll( keyValues );
        return this;
    }
    
    public CloudflareRequest body( String property, Object value ) {
        if ( property != null && value != null )
            body.addProperty( property, value.toString() );
        return this;
    }
    
    public CloudflareRequest body( String property, JsonElement value ) {
        if ( property != null && value != null )
            body.add( property, value );
        return this;
    }
    
    
    public CloudflareRequest body( Map<String, Object> keyValues ) {
        if ( keyValues != null )
            for ( Map.Entry<String, Object> entry : keyValues.entrySet() )
                body( entry.getKey(), entry.getValue() );
        return this;
    }
    
    
    private String categoryPath( ) {
        String additionalCategoryPath = category.getPath();
        for ( int place = 1; place <= orderedIdentifiers.size(); place++ )
            additionalCategoryPath = additionalCategoryPath.replace( "{id-" + place + "}", orderedIdentifiers.get( place - 1 ) );
        return additionalCategoryPath;
    }
    
    public void send( Consumer<HttpResponse<CloudflareResponse>> consumer ) {
        HttpResponse<CloudflareResponse> result;
        if ( GET.equals( category.getHttpMethod() ) ) {
            result = cf.getHttpClient()
                    .get( categoryPath() )
                    .queryString( queryStrings )
                    .asObject( CloudflareResponse.class );
        } else if ( POST.equals( category.getHttpMethod() ) ) {
            result = cf.getHttpClient()
                    .post( categoryPath() )
                    .queryString( queryStrings )
                    .body( body.toString() )
                    .asObject( CloudflareResponse.class );
        } else if ( DELETE.equals( category.getHttpMethod() ) ) {
            result = cf.getHttpClient()
                    .delete( categoryPath() )
                    .queryString( queryStrings )
                    .body( body.toString() )
                    .asObject( CloudflareResponse.class );
        } else if ( PUT.equals( category.getHttpMethod() ) ) {
            result = cf.getHttpClient()
                    .put( categoryPath() )
                    .queryString( queryStrings )
                    .body( body.toString() )
                    .asObject( CloudflareResponse.class );
        } else if ( PATCH.equals( category.getHttpMethod() ) ) {
            result = cf.getHttpClient()
                    .patch( categoryPath() )
                    .queryString( queryStrings )
                    .body( body.toString() )
                    .asObject( CloudflareResponse.class );
        } else result = null;
        consumer.accept( result );
    }
    
    public HttpResponse<CloudflareResponse> send( ) {
        if ( GET.equals( category.getHttpMethod() ) ) {
            return cf.getHttpClient()
                    .get( categoryPath() )
                    .queryString( queryStrings )
                    .asObject( CloudflareResponse.class );
        }
        if ( POST.equals( category.getHttpMethod() ) ) {
            return cf.getHttpClient()
                    .post( categoryPath() )
                    .queryString( queryStrings )
                    .body( body.toString() )
                    .asObject( CloudflareResponse.class );
        }
        if ( DELETE.equals( category.getHttpMethod() ) ) {
            return cf.getHttpClient()
                    .delete( categoryPath() )
                    .queryString( queryStrings )
                    .body( body.toString() )
                    .asObject( CloudflareResponse.class );
        }
        if ( PUT.equals( category.getHttpMethod() ) ) {
            return cf.getHttpClient()
                    .put( categoryPath() )
                    .queryString( queryStrings )
                    .body( body.toString() )
                    .asObject( CloudflareResponse.class );
        }
        if ( PATCH.equals( category.getHttpMethod() ) ) {
            return cf.getHttpClient()
                    .patch( categoryPath() )
                    .queryString( queryStrings )
                    .body( body.toString() )
                    .asObject( CloudflareResponse.class );
        }
        return null;
    }
}
