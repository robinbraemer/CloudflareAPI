/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare_old;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import eu.roboflax.cloudflare_old.json.GsonMapper;
import eu.roboflax.cloudflare_old.json.ZoneSettingDeserializer;
import eu.roboflax.cloudflare_old.objects.zone.ZoneSetting;
import eu.roboflax.cloudflare_old.service.DNSRecordService;
import eu.roboflax.cloudflare_old.service.UserService;
import eu.roboflax.cloudflare_old.service.ZoneService;
import io.joshworks.restclient.http.RestClient;
import lombok.Getter;
import org.apache.http.client.config.CookieSpecs;

import javax.annotation.Nullable;
import java.io.Closeable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
    private DNSRecordService dnsRecordService;
    
    public static final String API_BASE_URL = "https://api.cloudflare_old.com/client/v4/";
    
    public static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter( ZoneSetting.class, new ZoneSettingDeserializer() )
            .create();
    public static final JsonParser jsonParser = new JsonParser();
    private static final GsonMapper mapper = new GsonMapper();
    
    /*public static final Pattern X_AUTH_KEY_PATTERN = Pattern.compile( "((?:[a-z][a-z0-9_]*))",
            Pattern.CASE_INSENSITIVE | Pattern.DOTALL );
    public static final Pattern X_AUTH_EMAIL_PATTERN = Pattern.compile( "([\\w-+]+(?:\\.[\\w-+]+)*@(?:[\\w-]+\\.)+[a-zA-Z]{2,7})",
            Pattern.CASE_INSENSITIVE | Pattern.DOTALL );
    public static final Pattern domainPattern = Pattern.compile( "^([a-zA-Z0-9][\\\\-a-zA-Z0-9]*\\\\.)+[\\\\-a-zA-Z0-9]{2,20}$" );*/
    
    public CloudflareAccess( String xAuthKey, String xAuthEmail, @Nullable ExecutorService threadPool ) {
        /*if ( !(xAuthKey.length() == 37) || !(X_AUTH_KEY_PATTERN.matcher( xAuthKey ).matches()) )
            throw new IllegalArgumentException( "Format of passed xAuthKey is invalid!" );
        if ( !X_AUTH_EMAIL_PATTERN.matcher( xAuthEmail ).matches() )
            throw new IllegalArgumentException( "Format of passed xAuthEmail is invalid!" );*/
        this.xAuthKey = xAuthKey;
        this.xAuthEmail = xAuthEmail;
        this.threadPool = threadPool;
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
    
    public CloudflareAccess( String xAuthKey, String xAuthEmail, int maxThreads ) {
        this( xAuthKey, xAuthEmail, Executors.newFixedThreadPool( maxThreads ) );
    }
    
    public CloudflareAccess( String xAuthKey, String xAuthEmail ) {
        this( xAuthKey, xAuthEmail, null );
    }
    
    public CloudflareAccess( CloudflareConfig config ) {
        this( config.getXAuthKey(), config.getXAuthEmail(), config.getThreadPool() );
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
    public DNSRecordService dnsRecordService( ) {
        if ( this.dnsRecordService == null )
            this.dnsRecordService = new DNSRecordService( this );
        return this.dnsRecordService;
    }
    
    
    public static Gson getGson( ) {
        return gson;
    }
    
    public static JsonParser getJsonParser( ) {
        return jsonParser;
    }
    
    public static GsonMapper getMapper( ) {
        return mapper;
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
