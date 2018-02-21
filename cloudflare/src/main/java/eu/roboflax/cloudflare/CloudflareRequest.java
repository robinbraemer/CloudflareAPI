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
import io.joshworks.restclient.http.HttpMethod;
import io.joshworks.restclient.http.HttpResponse;

import java.util.*;
import java.util.function.Consumer;

import static io.joshworks.restclient.http.HttpMethod.*;

public class CloudflareRequest {
    
    private CloudflareAccess cfAccess;
    
    private String additionalPath;
    private HttpMethod httpMethod;
    
    private Map<String, Object> queryStrings = new HashMap<>();
    private List<String> orderedIdentifiers = new ArrayList<>();
    
    private JsonObject body = new JsonObject();
    
    public CloudflareRequest( Category category, CloudflareAccess cloudflareAccess ) {
        this.cfAccess = cloudflareAccess;
        this.additionalPath = category.getPath();
        this.httpMethod = category.getHttpMethod();
    }
    
    public CloudflareRequest( HttpMethod httpMethod, String additionalPath, CloudflareAccess cloudflareAccess ) {
        this.cfAccess = cloudflareAccess;
        this.httpMethod = httpMethod;
        this.additionalPath = additionalPath;
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
        String additionalCategoryPath = additionalPath;
        for ( int place = 1; place <= orderedIdentifiers.size(); place++ )
            additionalCategoryPath = additionalCategoryPath.replace( "{id-" + place + "}", orderedIdentifiers.get( place - 1 ) );
        return additionalCategoryPath;
    }
    
    public <T> HttpResponse<T> send( Class<T> tClass ) {
        if ( GET.equals( httpMethod ) ) {
            return cfAccess.getHttpClient()
                    .get( categoryPath() )
                    .queryString( queryStrings )
                    .asObject( tClass );
        }
        if ( POST.equals( httpMethod ) ) {
            return cfAccess.getHttpClient()
                    .post( categoryPath() )
                    .queryString( queryStrings )
                    .body( body.toString() )
                    .asObject( tClass );
        }
        if ( DELETE.equals( httpMethod ) ) {
            return cfAccess.getHttpClient()
                    .delete( categoryPath() )
                    .queryString( queryStrings )
                    .body( body.toString() )
                    .asObject( tClass );
        }
        if ( PUT.equals( httpMethod ) ) {
            return cfAccess.getHttpClient()
                    .put( categoryPath() )
                    .queryString( queryStrings )
                    .body( body.toString() )
                    .asObject( tClass );
        }
        if ( PATCH.equals( httpMethod ) ) {
            return cfAccess.getHttpClient()
                    .patch( categoryPath() )
                    .queryString( queryStrings )
                    .body( body.toString() )
                    .asObject( tClass );
        }
        return null;
    }
    
    public HttpResponse<CloudflareResponse> send( ) {
        return send( CloudflareResponse.class );
    }
    
    public void send( Consumer<HttpResponse<CloudflareResponse>> consumer ) {
        consumer.accept( send() );
    }
    
    public <T> void send( Consumer<HttpResponse<T>> consumer, Class<T> tClass ) {
        consumer.accept( send(tClass) );
    }
}
