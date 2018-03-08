/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import eu.roboflax.cloudflare.constants.Category;
import io.joshworks.restclient.http.HttpMethod;
import io.joshworks.restclient.http.HttpResponse;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static io.joshworks.restclient.http.HttpMethod.*;

/**
 * Used for creating cloudflare requests.
 */
public class CloudflareRequest {
    
    @Getter
    private CloudflareAccess cloudflareAccess;
    
    private HttpMethod httpMethod;
    private String additionalPath;
    private List<String> orderedIdentifiers = Lists.newArrayList();
    private Map<String, Object> queryStrings = Maps.newHashMap();
    private JsonObject body = new JsonObject();
    
    private Pair<HttpResponse<String>, JsonObject> response;
    
    
    public static final Set<HttpMethod> ALLOWED_HTTP_METHODS = ImmutableSet.of( GET, POST, PUT, DELETE, PATCH );
    
    public static final String ERROR_INVALID_BODY = "invalid body";
    public static final String ERROR_INVALID_QUERY_STRING = "invalid query string";
    public static final String ERROR_INVALID_IDENTIFIER = "invalid identifier";
    public static final String ERROR_INVALID_PAGINATION = "invalid pagination";
    
    public static final String ERROR_RESULT_IS_JSON_OBJECT = "Property 'result' is not a json array, because it is a json object use asObject() instead of asObjectList().";
    public static final String ERROR_RESULT_IS_JSON_ARRAY = "Property 'result' is not a json object, because it is a json array use asObjectList() instead of asObject().";
    public static final String ERROR_RESULT_IS_JSON_NULL = "Something went wrong! Property 'result' is a json null.";
    
    public static final String ERROR_CLOUDFLARE_FAILURE = "Cloudflare was unable to determine the result of the requested information. " +
            "The http request still was successful, you can use .getJson() to retrieve further information.";
    
    
    public CloudflareRequest( HttpMethod httpMethod, CloudflareAccess cloudflareAccess ) {
        checkArgument( ALLOWED_HTTP_METHODS.contains( httpMethod ), String.format( "Http method %s not allowed.", httpMethod ) );
        this.httpMethod = httpMethod;
        this.cloudflareAccess = cloudflareAccess;
    }
    
    public CloudflareRequest( HttpMethod httpMethod, String additionalPath, CloudflareAccess cloudflareAccess ) {
        this( httpMethod, cloudflareAccess );
        additionalPath( additionalPath );
    }
    
    public CloudflareRequest( Category category, CloudflareAccess cloudflareAccess ) {
        this( category.getHttpMethod(), category.getAdditionalPath(), cloudflareAccess );
    }
    
    public static CloudflareRequest builder( HttpMethod httpMethod, String additionalPath, CloudflareAccess cloudflareAccess ) {
        return new CloudflareRequest( httpMethod, additionalPath, cloudflareAccess );
    }
    
    public static CloudflareRequest builder( HttpMethod httpMethod, CloudflareAccess cloudflareAccess ) {
        return new CloudflareRequest( httpMethod, cloudflareAccess );
    }
    
    public static CloudflareRequest builder( Category category, CloudflareAccess cloudflareAccess ) {
        return builder( category.getHttpMethod(), category.getAdditionalPath(), cloudflareAccess );
    }
    
    
    public CloudflareRequest additionalPath( String additionalPath ) {
        this.additionalPath = validAdditionalPath( additionalPath );
        return this;
    }
    
    public CloudflareRequest identifiers( String... orderedIdentifiers ) {
        checkNotNull( orderedIdentifiers, ERROR_INVALID_IDENTIFIER );
        if ( Lists.newArrayList( orderedIdentifiers ).contains( null ) )
            throw new NullPointerException( ERROR_INVALID_IDENTIFIER );
        Collections.addAll( this.orderedIdentifiers, orderedIdentifiers );
        return this;
    }
    
    public CloudflareRequest queryString( String parameter, Object value ) {
        queryStrings.put( checkNotNull( parameter, ERROR_INVALID_QUERY_STRING ), checkNotNull( value, ERROR_INVALID_QUERY_STRING ) );
        return this;
    }
    
    public CloudflareRequest queryString( Map<String, Object> parameterValue ) {
        checkNotNull( parameterValue, ERROR_INVALID_QUERY_STRING );
        for ( Map.Entry<String, Object> e : parameterValue.entrySet() )
            queryString( e.getKey(), e.getValue() );
        return this;
    }
    
    public CloudflareRequest queryString( String parameter, Collection<?> values ) {
        checkNotNull( parameter, ERROR_INVALID_QUERY_STRING );
        checkNotNull( values, ERROR_INVALID_QUERY_STRING );
        for ( Object value : values )
            queryString( parameter, value );
        return this;
    }
    
    public CloudflareRequest body( String property, String value ) {
        body.addProperty( checkNotNull( property, ERROR_INVALID_BODY ), checkNotNull( value, ERROR_INVALID_BODY ) );
        return this;
    }
    
    public CloudflareRequest body( String property, Number value ) {
        body.addProperty( checkNotNull( property, ERROR_INVALID_BODY ), checkNotNull( value, ERROR_INVALID_BODY ) );
        return this;
    }
    
    public CloudflareRequest body( String property, Boolean value ) {
        body.addProperty( checkNotNull( property, ERROR_INVALID_BODY ), checkNotNull( value, ERROR_INVALID_BODY ) );
        return this;
    }
    
    public CloudflareRequest body( String property, Character value ) {
        body.addProperty( checkNotNull( property, ERROR_INVALID_BODY ), checkNotNull( value, ERROR_INVALID_BODY ) );
        return this;
    }
    
    public CloudflareRequest body( String property, JsonElement value ) {
        body.add( checkNotNull( property, ERROR_INVALID_BODY ), checkNotNull( value, ERROR_INVALID_BODY ) );
        return this;
    }
    
    public CloudflareRequest pagination( Pagination pagination ) {
        queryString( checkNotNull( pagination, ERROR_INVALID_PAGINATION ).getAsQueryStringsMap() );
        return this;
    }
    
    private HttpResponse<String> sendRequest( ) {
        if ( GET.equals( httpMethod ) ) {
            return cloudflareAccess.getRestClient()
                    .get( categoryPath() )
                    .queryString( queryStrings )
                    .asString();
        } else if ( POST.equals( httpMethod ) ) {
            return cloudflareAccess.getRestClient()
                    .post( categoryPath() )
                    .queryString( queryStrings )
                    .body( body.toString() )
                    .asString();
        } else if ( DELETE.equals( httpMethod ) ) {
            return cloudflareAccess.getRestClient()
                    .delete( categoryPath() )
                    .queryString( queryStrings )
                    .body( body.toString() )
                    .asString();
        } else if ( PUT.equals( httpMethod ) ) {
            return cloudflareAccess.getRestClient()
                    .put( categoryPath() )
                    .queryString( queryStrings )
                    .body( body.toString() )
                    .asString();
        } else if ( PATCH.equals( httpMethod ) ) {
            return cloudflareAccess.getRestClient()
                    .patch( categoryPath() )
                    .queryString( queryStrings )
                    .body( body.toString() )
                    .asString();
        }
        throw new IllegalStateException( "Should never happen because other http methods are blocked." );
    }
    
    private Pair<HttpResponse<String>, JsonObject> response( ) {
        if ( response == null ) {
            HttpResponse<String> httpResponse = sendRequest();
            JsonObject json = new JsonParser().parse( httpResponse.getBody() ).getAsJsonObject();
            response = Pair.of( httpResponse, json );
        }
        return response;
    }
    
    /*
     * asNull
     * asNull
     * asNull
     * asNull
     * asNull
     * asNull
     * asNull
     * asNull
     * asNull
     * asNull
     */
    
    /**
     * Sends request. No object mapping and/or object parsing will be handled.
     *
     * @return CloudflareResponse<ObjectUtils.Null>
     */
    public CloudflareResponse<ObjectUtils.Null> asNull( ) {
        HttpResponse<String> httpResponse = response().getLeft();
        JsonObject json = response().getRight();
        return new CloudflareResponse<>(
                json,
                ObjectUtils.NULL,
                httpResponse.isSuccessful(),
                httpResponse.getStatus(),
                httpResponse.getStatusText()
        );
    }
    
    /**
     * Consumer method for: {@link CloudflareRequest#asNull()}
     */
    public void asNull( Consumer<CloudflareResponse<ObjectUtils.Null>> consumer ) {
        consumer.accept( asNull() );
    }
    
    /**
     * Async CloudflareCallback method for {@link CloudflareRequest#asNull()}
     */
    public void asNullAsync( CloudflareCallback<CloudflareResponse<ObjectUtils.Null>> callback ) {
        asyncCallback( callback, this::asNull );
    }
    
    
    /**
     * Async method for {@link CloudflareRequest#asNull()}
     */
    public CompletableFuture<CloudflareResponse<ObjectUtils.Null>> asNullAsync( ) {
        return CompletableFuture.supplyAsync( this::asNull, getCloudflareAccess().getThreadPool() );
    }
    
    /**
     * Same as {@link CloudflareRequest#asNull()}
     */
    public CloudflareResponse<ObjectUtils.Null> send( ) {
        return asNull();
    }
    
    /**
     * Same as {@link CloudflareRequest#asNull()} but async
     */
    public CompletableFuture<CloudflareResponse<ObjectUtils.Null>> sendAsync( ) {
        return asNullAsync();
    }
    
    /**
     * Same as {@link CloudflareRequest#asNull()} but with consumer
     */
    public void send( Consumer<CloudflareResponse<ObjectUtils.Null>> consumer ) {
        consumer.accept( asNull() );
    }
    
    /**
     * Same as {@link CloudflareRequest#asNull()} but with async callback
     */
    public void sendAsync( CloudflareCallback<CloudflareResponse<ObjectUtils.Null>> callback ) {
        asyncCallback( callback, this::asNull );
    }
    
    /*
     * asObject
     * asObject
     * asObject
     * asObject
     * asObject
     * asObject
     * asObject
     * asObject
     * asObject
     * asObject
     */
    
    /**
     * Sends request. Parses the json result as the object type.
     *
     * @param objectType class of object
     * @param <T>        type of object
     * @return CloudflareResponse<T>
     */
    public <T> CloudflareResponse<T> asObject( Class<T> objectType ) {
        HttpResponse<String> httpResponse = response().getLeft();
        JsonObject json = response().getRight();
        if ( json.get( "result" ).isJsonObject() ) {
            return new CloudflareResponse<>(
                    json,
                    CloudflareAccess.getGson().fromJson( json.getAsJsonObject( "result" ), objectType ),
                    httpResponse.isSuccessful(),
                    httpResponse.getStatus(),
                    httpResponse.getStatusText()
            );
        }
        if ( json.get( "result" ).isJsonArray() )
            throw new IllegalStateException( ERROR_RESULT_IS_JSON_ARRAY );
        throw new IllegalStateException( ERROR_RESULT_IS_JSON_NULL );
    }
    
    /**
     * Async method for {@link CloudflareRequest#asObject(Class)}
     */
    public <T> CompletableFuture<CloudflareResponse<T>> asObjectAsync( Class<T> objectType ) {
        return CompletableFuture.supplyAsync( ( ) -> asObject( objectType ), getCloudflareAccess().getThreadPool() );
    }
    
    /**
     * Consumer method for {@link CloudflareRequest#asObject(Class)}
     */
    public <T> void asObject( Class<T> objectType, Consumer<CloudflareResponse<T>> consumer ) {
        consumer.accept( asObject( objectType ) );
    }
    
    /**
     * Callback method for {@link CloudflareRequest#asObject(Class)}
     */
    public <T> void asObject( Class<T> objectType, CloudflareCallback<CloudflareResponse<T>> callback ) {
        asyncCallback( callback, ( ) -> asObject( objectType ) );
    }
    
    /*
     * asObjectList
     * asObjectList
     * asObjectList
     * asObjectList
     * asObjectList
     * asObjectList
     * asObjectList
     */
    
    /**
     * Sends request. Parses and maps all entries in the json array result as a List<object type>.
     *
     * @param objectType class of object
     * @param <T>        type of object
     * @return CloudflareResponse
     */
    public <T> CloudflareResponse<List<T>> asObjectList( Class<T> objectType ) {
        JsonObject json = response().getRight();
        HttpResponse<String> httpResponse = response().getLeft();
        
        if ( json.get( "result" ).isJsonArray() ) {
            // Map object from json array to object list.
            Type listType = new TypeToken<ArrayList<T>>() {
            }.getType();
            List<T> objectList = CloudflareAccess.getGson().fromJson( json.getAsJsonArray( "result" ), listType );
            
            return new CloudflareResponse<>(
                    json,
                    objectList,
                    httpResponse.isSuccessful(),
                    httpResponse.getStatus(),
                    httpResponse.getStatusText()
            );
        }
        if ( json.get( "result" ).isJsonObject() )
            throw new IllegalStateException( ERROR_RESULT_IS_JSON_OBJECT );
        throw new IllegalStateException( ERROR_RESULT_IS_JSON_NULL );
    }
    
    /**
     * Async method for {@link CloudflareRequest#asObjectList(Class)}
     */
    public <T> CompletableFuture<CloudflareResponse<List<T>>> asObjectListAsync( Class<T> objectType ) {
        return CompletableFuture.supplyAsync( ( ) -> asObjectList( objectType ), getCloudflareAccess().getThreadPool() );
    }
    
    /**
     * Consumer method for {@link CloudflareRequest#asObjectList(Class)}
     */
    public <T> void asObjectList( Class<T> objectType, Consumer<CloudflareResponse<List<T>>> consumer ) {
        consumer.accept( asObjectList( objectType ) );
    }
    
    /**
     * Async callback method for {@link CloudflareRequest#asObjectList(Class)}
     */
    public <T> void asObjectList( Class<T> objectType, CloudflareCallback<CloudflareResponse<List<T>>> callback ) {
        asyncCallback( callback, ( ) -> asObjectList( objectType ) );
    }
    
    /*
     * asObjectOrObjectList
     * asObjectOrObjectList
     * asObjectOrObjectList
     * asObjectOrObjectList
     * asObjectOrObjectList
     * asObjectOrObjectList
     * asObjectOrObjectList
     */
    
    /**
     * Sends the request.
     * The "object" attribute in the returned CloudflareResponse<T> object is variable.
     * It checks if the returned "result" property in the cloudflare response is a json array, json object or json null
     * and adjusts it to the right object with the right type T.
     * <p>
     * Cast "object" of type T in the CloudflareResponse as you need, T or List<T>.
     * <p>
     * You can use it as follows:
     * * <pre>
     * {@code
     * CloudflareResponse response = new CloudflareRequest( Category.LIST_DNS_RECORDS, cloudflareAccess )
     *    .identifiers( zoneId )
     *    .asObjectOrObjectList( DNSRecord.class );
     * List<DNSRecord> records = (List<DNSRecord>) response.getObject();
     * <p>
     * {@code
     * CloudflareResponse response = new CloudflareRequest( Category.DNS_RECORD_DETAILS, cloudflareAccess )
     *    .identifiers( zoneId, "dns_record_id" )
     *    .asObjectOrObjectList( DNSRecord.class );
     * DNSRecord record = (DNSRecord) response.getObject();
     * }
     * </pre>
     *
     * @param objectType class of object type
     * @param <T>        will be the type of the "object" attribute in the returned CloudflareResponse<T> (or List<T>)
     * @return the CloudflareResponse<T> with attribute "object" as type T or List<T>
     */
    public <T> CloudflareResponse<T> asObjectOrObjectList( Class<T> objectType ) {
        JsonObject json = response().getRight();
        HttpResponse<String> httpResponse = response().getLeft();
        
        T object;
        // Check if result is json array.
        if ( json.get( "result" ).isJsonArray() ) {
            // Map object from json array to object list.
            Type listType = new TypeToken<ArrayList<T>>() {
            }.getType();
            List<T> objectList = CloudflareAccess.getGson().fromJson( json.getAsJsonArray( "result" ), listType );
            object = (T) objectList;
        } else if ( json.get( "result" ).isJsonNull() )
            throw new IllegalStateException( ERROR_RESULT_IS_JSON_NULL );
        else
            // Object is normal (not in array or null)
            object = CloudflareAccess.getGson().fromJson( json.getAsJsonObject( "result" ), objectType );
        
        // Return the response
        return new CloudflareResponse<>(
                json,
                object,
                httpResponse.isSuccessful(),
                httpResponse.getStatus(),
                httpResponse.getStatusText()
        );
    }
    
    /**
     * Async method for {@link CloudflareRequest#asObjectOrObjectList(Class)}
     */
    public <T> CompletableFuture<CloudflareResponse<T>> asObjectOrObjectListAsync( Class<T> objectType ) {
        return CompletableFuture.supplyAsync( ( ) -> asObjectOrObjectList( objectType ), getCloudflareAccess().getThreadPool() );
    }
    
    /**
     * Consumer method for {@link CloudflareRequest#asObjectOrObjectList(Class)}
     */
    public <T> void asObjectOrObjectList( Class<T> objectType, Consumer<CloudflareResponse<T>> consumer ) {
        consumer.accept( asObjectOrObjectList( objectType ) );
    }
    
    /**
     * Async callback method for {@link CloudflareRequest#asObjectOrObjectList(Class)}
     */
    public <T> void asObjectOrObjectListAsync( Class<T> objectType, CloudflareCallback<CloudflareResponse<T>> callback ) {
        asyncCallback( callback, ( ) -> asObjectOrObjectList( objectType ) );
    }
    
    /*
     * INTERNAL HELPER METHODS
     * INTERNAL HELPER METHODS
     * INTERNAL HELPER METHODS
     * INTERNAL HELPER METHODS
     * INTERNAL HELPER METHODS
     * INTERNAL HELPER METHODS
     * INTERNAL HELPER METHODS
     * INTERNAL HELPER METHODS
     */
    
    /**
     * INTERNAL HELPER METHOD!
     *
     * @param callback user'S callback
     * @param solution is returning the solution
     * @param <T>      type of "object" in CloudflareResponse
     */
    private <T> void asyncCallback( CloudflareCallback<CloudflareResponse<T>> callback, Callable<CloudflareResponse<T>> solution ) {
        ListenableFuture<CloudflareResponse<T>> future = MoreExecutors
                .listeningDecorator( getCloudflareAccess().getThreadPool() )
                .submit( solution );
        
        HttpResponse<String> httpResponse = response().getLeft();
        JsonObject json = response().getRight();
        future.addListener( ( ) -> {
            Throwable throwable;
            try {
                CloudflareResponse<T> response = future.get();
                // http request successful
                
                // check "success" state in json -> cloudflare couldn't find the result
                if ( !response.isSuccessful() ) {
                    throw new IllegalStateException( ERROR_CLOUDFLARE_FAILURE );
                }
                
                callback.onSuccess( response );
                return;
            } catch ( ExecutionException e ) {
                throwable = e.getCause();
            } catch ( Exception | Error e ) {
                throwable = e;
            }
            if ( throwable != null ) {
                // Errors passed by Cloudflare
                Map<Integer, String> errors = Maps.newHashMap();
                
                // Check if returned json is valid
                if ( json != null ) {
                    JsonObject o;
                    for ( JsonElement e : json.getAsJsonArray( "errors" ) ) {
                        o = e.getAsJsonObject();
                        errors.put( o.get( "code" ).getAsInt(), o.get( "message" ).getAsString() );
                    }
                }
                
                callback.onFailure( throwable, httpResponse.getStatus(), httpResponse.getStatusText(), errors );
            }
            
        }, getCloudflareAccess().getThreadPool() );
    }
    
    /**
     * INTERNAL HELPER METHOD!
     *
     * @return formatted url containing the passed ordered identifiers replaced with {id-ORDER_NUMBER}
     */
    private String categoryPath( ) {
        String additionalCategoryPath = additionalPath;
        
        // pattern is like 'foo/{id-1}/bar/{id-2}'
        for ( int place = 1; place <= orderedIdentifiers.size(); place++ )
            additionalCategoryPath = additionalCategoryPath.replace( "{id-" + place + "}", orderedIdentifiers.get( place - 1 ) );
        
        return additionalCategoryPath;
    }
    
    /**
     * INTERNAL HELPER METHOD!
     * <p>
     * Some format checks of additional path.
     *
     * @param additionalPath
     * @return validated additional path
     */
    private static String validAdditionalPath( String additionalPath ) {
        if ( additionalPath.startsWith( "/" ) )
            additionalPath = additionalPath.substring( 1 );
        return additionalPath;
    }
}
