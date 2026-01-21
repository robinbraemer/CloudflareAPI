/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.objects.tunnel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.roboflax.cloudflare.objects.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Represents a Cloudflare Tunnel (Zero Trust).
 *
 * @see <a href="https://developers.cloudflare.com/api/resources/zero_trust/subresources/tunnels/">Cloudflare API</a>
 */
@Getter
@Setter
public class Tunnel implements Identifiable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("account_tag")
    @Expose
    private String accountTag;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("deleted_at")
    @Expose
    private String deletedAt;

    @SerializedName("conns_active_at")
    @Expose
    private String connsActiveAt;

    @SerializedName("conns_inactive_at")
    @Expose
    private String connsInactiveAt;

    @SerializedName("tun_type")
    @Expose
    private String tunType;

    @SerializedName("metadata")
    @Expose
    private Object metadata;

    @SerializedName("connections")
    @Expose
    private List<TunnelConnection> connections;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("accountTag", accountTag)
                .append("name", name)
                .append("status", status)
                .append("createdAt", createdAt)
                .append("tunType", tunType)
                .toString();
    }
}
