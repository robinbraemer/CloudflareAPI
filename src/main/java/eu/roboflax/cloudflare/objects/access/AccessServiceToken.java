/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.objects.access;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.roboflax.cloudflare.objects.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents a Cloudflare Access Service Token (Zero Trust).
 * Service tokens allow automated systems to reach applications protected by Access.
 *
 * @see <a href="https://developers.cloudflare.com/api/resources/zero_trust/subresources/access/subresources/service_tokens/">Cloudflare API</a>
 */
@Getter
@Setter
public class AccessServiceToken implements Identifiable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("client_id")
    @Expose
    private String clientId;

    /**
     * Only returned when creating or rotating a token.
     * Store this securely - it cannot be retrieved again.
     */
    @SerializedName("client_secret")
    @Expose
    private String clientSecret;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("expires_at")
    @Expose
    private String expiresAt;

    @SerializedName("duration")
    @Expose
    private String duration;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("clientId", clientId)
                .append("createdAt", createdAt)
                .append("expiresAt", expiresAt)
                .append("duration", duration)
                .toString();
    }
}
