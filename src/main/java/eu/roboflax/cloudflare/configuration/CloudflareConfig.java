/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.configuration;

import eu.roboflax.cloudflare.CloudflareAccess;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.concurrent.ExecutorService;

/**
 * Configuration for a {@link eu.roboflax.cloudflare.CloudflareAccess} object.
 */
public record CloudflareConfig(
    @Getter String xAuthKey,
    @Getter String xAuthEmail,
    @Getter String xAuthToken,
    @Getter ExecutorService threadPool,
    @Getter Integer maxThreads
) {

    //
    // Key + Email auth
    //

    public CloudflareConfig(String xAuthKey, String xAuthEmail) {
        this(xAuthKey, xAuthEmail, null, null, null);
    }

    public CloudflareConfig(String xAuthKey, String xAuthEmail, @Nullable Integer maxThreads) {
        this(xAuthKey, xAuthEmail, null, null, maxThreads);
    }

    public CloudflareConfig(String xAuthKey, String xAuthEmail, @Nullable ExecutorService threadPool) {
        this(xAuthKey, xAuthEmail, null, threadPool, null);
    }

    //
    // Token auth
    //

    public CloudflareConfig(String xAuthToken) {
        this(null, null, xAuthToken, null, null);
    }

    public CloudflareConfig(String xAuthToken, @Nullable Integer maxThreads) {
        this(null, null, xAuthToken, null, maxThreads);
    }

    public CloudflareConfig(String xAuthToken, @Nullable ExecutorService threadPool) {
        this(null, null, xAuthToken, threadPool, null);
    }

    /**
     * Creates a new {@link eu.roboflax.cloudflare.CloudflareAccess} from this configuration.
     *
     * @return the new CloudflareAccess
     */
    public CloudflareAccess createAccess() {
        return switch (xAuthToken != null ? 1 : 2) {
            case 1 -> {
                if (this.threadPool != null) {
                    yield new CloudflareAccess(this.xAuthToken, this.threadPool);
                }
                if (this.maxThreads != null) {
                    yield new CloudflareAccess(this.xAuthToken, this.maxThreads);
                }
                yield new CloudflareAccess(this.xAuthToken);
            }
            case 2 -> {
                if (this.threadPool != null) {
                    yield new CloudflareAccess(this.xAuthKey, this.xAuthEmail, this.threadPool);
                }
                if (this.maxThreads != null) {
                    yield new CloudflareAccess(this.xAuthKey, this.xAuthEmail, this.maxThreads);
                }
                yield new CloudflareAccess(this.xAuthKey, this.xAuthEmail);
            }
            default -> throw new IllegalStateException();
        };
    }
}
