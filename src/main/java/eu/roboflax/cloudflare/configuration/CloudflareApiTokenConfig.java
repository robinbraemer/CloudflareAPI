/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.configuration;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.concurrent.ExecutorService;

/**
 * Configuration for a {@link eu.roboflax.cloudflare.CloudflareAccess} object.
 */
@Getter
@Setter
public class CloudflareApiTokenConfig {

    private String xApiToken;

    private ExecutorService threadPool;
    private Integer maxThreads;

    public CloudflareApiTokenConfig(String xApiToken) {
        this.xApiToken = xApiToken;
    }

    public CloudflareApiTokenConfig(String xApiToken, @Nullable Integer maxThreads ) {
        this(xApiToken);
        this.maxThreads = maxThreads;
    }

    public CloudflareApiTokenConfig(String xApiToken, @Nullable ExecutorService threadPool ) {
        this(xApiToken);
        this.threadPool = threadPool;
    }
}

