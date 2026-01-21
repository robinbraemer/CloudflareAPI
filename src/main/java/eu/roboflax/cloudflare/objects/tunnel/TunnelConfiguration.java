/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.objects.tunnel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Represents a Cloudflare Tunnel Configuration.
 */
@Getter
@Setter
public class TunnelConfiguration {

    @SerializedName("tunnel_id")
    @Expose
    private String tunnelId;

    @SerializedName("version")
    @Expose
    private Integer version;

    @SerializedName("config")
    @Expose
    private Config config;

    @Getter
    @Setter
    public static class Config {
        @SerializedName("ingress")
        @Expose
        private List<IngressRule> ingress;

        @SerializedName("warp-routing")
        @Expose
        private WarpRouting warpRouting;
    }

    @Getter
    @Setter
    public static class IngressRule {
        @SerializedName("hostname")
        @Expose
        private String hostname;

        @SerializedName("service")
        @Expose
        private String service;

        @SerializedName("path")
        @Expose
        private String path;
    }

    @Getter
    @Setter
    public static class WarpRouting {
        @SerializedName("enabled")
        @Expose
        private Boolean enabled;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("tunnelId", tunnelId)
                .append("version", version)
                .toString();
    }
}
