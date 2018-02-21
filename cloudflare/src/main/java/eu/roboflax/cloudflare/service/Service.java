/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 *
 * Project cloudflare created by RoboFlax.
 * Class created on 22.12.2017 at 09:37.
 *
 */
package eu.roboflax.cloudflare.service;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import eu.roboflax.cloudflare.CloudflareAccess;
import lombok.Getter;

public abstract class Service {
    
    @Getter
    public CloudflareAccess cloudflareAccess;
    public static Gson gson = getGson();
    public static JsonParser jsonParser = getJsonParser();
    
    public Service( CloudflareAccess cloudflareAccess ) {
        this.cloudflareAccess = cloudflareAccess;
    }
    
    public static Gson getGson( ) {
        return CloudflareAccess.gson;
    }
    
    public static JsonParser getJsonParser( ) {
        return CloudflareAccess.jsonParser;
    }
}
