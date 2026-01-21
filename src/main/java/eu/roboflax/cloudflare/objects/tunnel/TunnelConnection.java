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

/**
 * Represents a Cloudflare Tunnel Connection.
 */
@Getter
@Setter
public class TunnelConnection implements Identifiable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("colo_name")
    @Expose
    private String coloName;

    @SerializedName("is_pending_reconnect")
    @Expose
    private Boolean isPendingReconnect;

    @SerializedName("client_id")
    @Expose
    private String clientId;

    @SerializedName("client_version")
    @Expose
    private String clientVersion;

    @SerializedName("opened_at")
    @Expose
    private String openedAt;

    @SerializedName("origin_ip")
    @Expose
    private String originIp;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("coloName", coloName)
                .append("clientVersion", clientVersion)
                .append("openedAt", openedAt)
                .toString();
    }
}
