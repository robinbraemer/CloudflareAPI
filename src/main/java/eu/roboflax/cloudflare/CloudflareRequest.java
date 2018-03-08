/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import eu.roboflax.cloudflare.constants.Category;
import io.joshworks.restclient.http.HttpMethod;
import io.joshworks.restclient.http.HttpResponse;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static io.joshworks.restclient.http.HttpMethod.*;

public class CloudflareRequest {
    
    @Getter
    private CloudflareAccess cloudflareAccess;
    
    private HttpMethod httpMethod;
    private String additionalPath;
    private List<String> orderedIdentifiers = Lists.newArrayList();
    private Map<String, Object> queryStrings = Maps.newHashMap();
    private JsonObject body = new JsonObject();
    
    private static final Set<HttpMethod> ALLOWED_HTTP_METHODS = Sets.newHashSet( GET, POST, PUT, DELETE, PATCH );
    
    private static final String ERROR_INVALID_BODY = "invalid body";
    private static final String ERROR_INVALID_QUERY_STRING = "invalid query string";
    private static final String ERROR_INVALID_IDENTIFIER = "invalid identifier";
    
    private static final String ERROR_RESULT_IS_JSON_OBJECT = "Property 'result' is not a json array, because it is a json object use asObject() instead of asObjectList().";
    private static final String ERROR_RESULT_IS_JSON_ARRAY = "Property 'result' is not a json object, because it is a json array use asObjectList() instead of asObject().";
    private static final String ERROR_RESULT_IS_JSON_NULL = "Something went wrong! Property 'result' is a json null.";
    
    
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
        throw new IllegalStateException( "should never happen" );
    }
    
    /**
     * Sends request. No object mapping and/or object parsing.
     *
     * @return CloudflareResponse<ObjectUtils.Null>
     */
    public CloudflareResponse<ObjectUtils.Null> asNull( ) {
        HttpResponse<String> httpResponse = sendRequest();
        JsonObject json = new JsonParser().parse( httpResponse.getBody() ).getAsJsonObject();
        return new CloudflareResponse<>(
                json,
                ObjectUtils.NULL,
                httpResponse.isSuccessful(),
                httpResponse.getStatus(),
                httpResponse.getStatusText()
        );
    }
    
    /**
     * Consumer method for:
     * <p>
     * {@link CloudflareRequest#asNull()}
     */
    public CloudflareResponse<ObjectUtils.Null> asNull( Consumer<CloudflareResponse<ObjectUtils.Null>> consumer ) {
        CloudflareResponse<ObjectUtils.Null> response = asNull();
        consumer.accept( response );
        return response;
    }
    
    /**
     * Async method for:
     * <p>
     * {@link CloudflareRequest#asNull()}
     */
    public CompletableFuture<CloudflareResponse<ObjectUtils.Null>> asNullAsync( ) {
        return CompletableFuture.supplyAsync( this::asNull, getCloudflareAccess().getThreadPool() );
    }
    
    /**
     * Async consumer method for:
     * <p>
     * {@link CloudflareRequest#asNull()}
     */
    public CompletableFuture<CloudflareResponse<ObjectUtils.Null>> asNullAsync( Consumer<CompletableFuture<CloudflareResponse<ObjectUtils.Null>>> consumer ) {
        CompletableFuture<CloudflareResponse<ObjectUtils.Null>> future = asNullAsync();
        consumer.accept( future );
        return future;
    }
    
    /**
     * Same as:
     * <p>
     * {@link CloudflareRequest#asNull()}
     */
    public CloudflareResponse<ObjectUtils.Null> send( ) {
        return asNull();
    }
    
    /**
     * Same as but async:
     * <p>
     * {@link CloudflareRequest#asNull()}
     */
    public CompletableFuture<CloudflareResponse<ObjectUtils.Null>> sendAsync( ) {
        return asNullAsync();
    }
    
    /**
     * Same as consumer method for:
     * <p>
     * {@link CloudflareRequest#asNull()}
     */
    public CloudflareResponse<ObjectUtils.Null> send( Consumer<CloudflareResponse<ObjectUtils.Null>> consumer ) {
        CloudflareResponse<ObjectUtils.Null> response = asNull();
        consumer.accept( response );
        return response;
    }
    
    /**
     * Same as async consumer method for:
     * <p>
     * {@link CloudflareRequest#asNull()}
     */
    public CompletableFuture<CloudflareResponse<ObjectUtils.Null>> sendAsync( Consumer<CompletableFuture<CloudflareResponse<ObjectUtils.Null>>> consumer ) {
        CompletableFuture<CloudflareResponse<ObjectUtils.Null>> future = asNullAsync();
        consumer.accept( future );
        return future;
    }
    
    /**
     * Sends request. Parses the json result to the object.
     *
     * @param objectType class of object
     * @param <T>        type of object
     * @return CloudflareResponse<T>
     */
    public <T> CloudflareResponse<T> asObject( Class<T> objectType ) {
        HttpResponse<String> httpResponse = sendRequest();
        JsonObject json = new JsonParser().parse( httpResponse.getBody() ).getAsJsonObject();
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
     * Async method for:
     * <p>
     * {@link CloudflareRequest#asObject(Class)}
     */
    public <T> CompletableFuture<CloudflareResponse<T>> asObjectAsync( Class<T> objectType ) {
        return CompletableFuture.supplyAsync( ( ) -> asObject( objectType ), getCloudflareAccess().getThreadPool() );
    }
    
    /**
     * Consumer method for:
     * <p>
     * {@link CloudflareRequest#asObject(Class)}
     */
    public <T> CloudflareResponse<T> asObject( Class<T> objectType, Consumer<CloudflareResponse<T>> consumer ) {
        CloudflareResponse<T> response = asObject( objectType );
        consumer.accept( response );
        return response;
    }
    
    /**
     * Async consumer method for:
     * <p>
     * {@link CloudflareRequest#asObject(Class)}
     */
    public <T> CompletableFuture<CloudflareResponse<T>> asObjectAsync( Class<T> objectType, Consumer<CompletableFuture<CloudflareResponse<T>>> consumer ) {
        CompletableFuture<CloudflareResponse<T>> future = asObjectAsync( objectType );
        consumer.accept( future );
        return future;
    }
    
    /**
     * Sends request. Parses and maps all entries in the json array result to a List<T>.
     *
     * @param objectType class of object
     * @param <T>        type of object
     * @return CloudflareResponse<List                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               <                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               T>>
     */
    public <T> CloudflareResponse<List<T>> asObjectList( Class<T> objectType ) {
        HttpResponse<String> httpResponse = sendRequest();
        JsonObject json = new JsonParser().parse( httpResponse.getBody() ).getAsJsonObject();
        
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
     * Async method for:
     * <p>
     * {@link CloudflareRequest#asObjectList(Class)}
     */
    public <T> CompletableFuture<CloudflareResponse<List<T>>> asObjectListAsync( Class<T> objectType ) {
        return CompletableFuture.supplyAsync( ( ) -> asObjectList( objectType ), getCloudflareAccess().getThreadPool() );
    }
    
    /**
     * Consumer method for:
     *
     * {@link CloudflareRequest#asObjectList(Class)}
     */
    public <T> CloudflareResponse<List<T>> asObjectList( Class<T> objectType, Consumer<CloudflareResponse<List<T>>> consumer ) {
        CloudflareResponse<List<T>> response = asObjectList( objectType );
        consumer.accept( response );
        return response;
    }
    
    /**
     * Async consumer method for:
     *
     * {@link CloudflareRequest#asObjectList(Class)}
     */
    public <T> CompletableFuture<CloudflareResponse<List<T>>> asObjectListAsync( Class<T> objectType, Consumer<CompletableFuture<CloudflareResponse<List<T>>>> consumer ) {
        CompletableFuture<CloudflareResponse<List<T>>> future = asObjectListAsync( objectType );
        consumer.accept( future );
        return future;
    }
    
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
     *    .identifiers( zoneId, "dns_record_id" )
     *    .asObjectOrListOfObjects( DNSRecord.class );
     * System.out.println((List<DNSRecord>) response.getObject());
     * <p>
     * {@code
     * CloudflareResponse response = new CloudflareRequest( Category.DNS_RECORD_DETAILS, cloudflareAccess )
     *    .identifiers( zoneId, "dns_record_id" )
     *    .asObjectOrListOfObjects( DNSRecord.class );
     * System.out.println((DNSRecord) response.getObject());
     * }
     * </pre>
     *
     * @param objectType class of object type
     * @param <T>        will be the type of the "object" attribute in the returned CloudflareResponse<T> (or List<T>)
     * @return the CloudflareResponse<T>, attribute "object" is T or List<T>
     */
    public <T> CloudflareResponse<T> asObjectOrListOfObjects( Class<T> objectType ) {
        HttpResponse<String> httpResponse = sendRequest();
        JsonObject json = new JsonParser().parse( httpResponse.getBody() ).getAsJsonObject();
        
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
     * Async method for:
     * <p>
     * {@link CloudflareRequest#asObjectOrListOfObjects(Class)}
     */
    public <T> CompletableFuture<CloudflareResponse<T>> asObjectOrListOfObjectsAsync( Class<T> objectType ) {
        return CompletableFuture.supplyAsync( ( ) -> asObjectOrListOfObjects( objectType ), getCloudflareAccess().getThreadPool() );
    }
    
    /**
     * Consumer method for:
     * <p>
     * {@link CloudflareRequest#asObjectOrListOfObjects(Class)}
     */
    public <T> CloudflareResponse<T> asObjectOrListOfObjects( Class<T> objectType, Consumer<CloudflareResponse<T>> consumer ) {
        CloudflareResponse<T> response = asObjectOrListOfObjects( objectType );
        consumer.accept( response );
        return response;
    }
    
    /**
     * Async consumer method for:
     * <p>
     * {@link CloudflareRequest#asObjectOrListOfObjects(Class)}
     */
    public <T> CompletableFuture<CloudflareResponse<T>> asObjectOrListOfObjectsAsync( Class<T> objectType, Consumer<CompletableFuture<CloudflareResponse<T>>> consumer ) {
        CompletableFuture<CloudflareResponse<T>> response = asObjectOrListOfObjectsAsync( objectType );
        consumer.accept( response );
        return response;
    }
    
    private String categoryPath( ) {
        String additionalCategoryPath = additionalPath;
        
        // pattern is like 'foo/{id-1}/bar/{id-2}'
        for ( int place = 1; place <= orderedIdentifiers.size(); place++ )
            additionalCategoryPath = additionalCategoryPath.replace( "{id-" + place + "}", orderedIdentifiers.get( place - 1 ) );
        
        return additionalCategoryPath;
    }
    
    private static String validAdditionalPath( String additionalPath ) {
        if ( additionalPath.startsWith( "/" ) )
            additionalPath = additionalPath.substring( 1 );
        return additionalPath;
    }
}
