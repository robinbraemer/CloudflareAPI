/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 *
 * Project Samples created by RoboFlax.
 * Class created on 20.12.2017 at 17:05.
 *
 */
package eu.roboflax.cloudflare;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import eu.roboflax.cloudflare.json.GsonMapper;
import eu.roboflax.cloudflare.json.ZoneSettingDeserializer;
import eu.roboflax.cloudflare.objects.zone.ZoneSetting;
import eu.roboflax.cloudflare.service.UserService;
import eu.roboflax.cloudflare.service.ZoneService;
import io.joshworks.restclient.http.RestClient;
import lombok.Getter;
import org.apache.http.client.config.CookieSpecs;

import java.io.Closeable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class CloudflareAccess implements Closeable {
    
    @Getter
    private String xAuthKey;
    @Getter
    private String xAuthEmail;
    @Getter
    private RestClient httpClient;
    private ExecutorService threadPool;
    
    
    private ZoneService zoneService;
    private UserService userService;
    
    public static final String API_BASE_URL = "https://api.cloudflare.com/client/v4/";
    
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter( ZoneSetting.class, new ZoneSettingDeserializer() ).create();
    public static JsonParser jsonParser = new JsonParser();
    private static final GsonMapper mapper = new GsonMapper();
    
    public static final Pattern keyPattern = Pattern.compile( "((?:[a-z][a-z0-9_]*))",
            Pattern.CASE_INSENSITIVE | Pattern.DOTALL );
    public static final Pattern emailPattern = Pattern.compile( "([\\w-+]+(?:\\.[\\w-+]+)*@(?:[\\w-]+\\.)+[a-zA-Z]{2,7})",
            Pattern.CASE_INSENSITIVE | Pattern.DOTALL );
    public static final Pattern domainPattern = Pattern.compile( "^([a-zA-Z0-9][\\\\-a-zA-Z0-9]*\\\\.)+[\\\\-a-zA-Z0-9]{2,20}$" );
    
    
    public CloudflareAccess( String authKey, String authEmail, ExecutorService cachedThreadPool ) {
        if ( !(authKey.length() == 37) || !(keyPattern.matcher( authKey ).matches()) )
            throw new IllegalArgumentException( "Format of passed xAuthKey is invalid!" );
        if ( !emailPattern.matcher( authEmail ).matches() )
            throw new IllegalArgumentException( "Format of passed xAuthEmail is invalid!" );
        this.xAuthKey = authKey;
        this.xAuthEmail = authEmail;
        this.threadPool = cachedThreadPool;
        this.httpClient = RestClient.builder()
                .baseUrl( API_BASE_URL )
                .defaultHeader( "Content-Type", "application/json" )
                .defaultHeader( "X-Auth-Key", this.getXAuthKey() )
                .defaultHeader( "X-Auth-Email", this.getXAuthEmail() )
                .followRedirect( false )
                .cookieSpec( CookieSpecs.IGNORE_COOKIES )
                .objectMapper( mapper )
                .build();
    }
    
    public CloudflareAccess( String authKey, String authEmail, int maxThreads ) {
        this( authKey, authEmail, Executors.newFixedThreadPool( maxThreads ) );
    }
    
    public CloudflareAccess( String authKey, String authEmail ) {
        this( authKey, authEmail, null );
    }
    
    public ExecutorService getThreadPool( ) {
        if ( this.threadPool == null )
            this.threadPool = Executors.newFixedThreadPool( 30 );
        return threadPool;
    }
    
    
    public ZoneService zoneService( ) {
        if ( this.zoneService == null )
            this.zoneService = new ZoneService( this );
        return this.zoneService;
    }
    
    public UserService userService( ) {
        if ( this.userService == null )
            this.userService = new UserService( this );
        return this.userService;
    }
    
    @Override
    public void close( ) {
        if ( this.threadPool != null )
            try {
                this.getThreadPool().awaitTermination( 1L, TimeUnit.SECONDS );
                this.getThreadPool().shutdownNow();
            } catch ( InterruptedException ignored ) {
            }
    }
}
