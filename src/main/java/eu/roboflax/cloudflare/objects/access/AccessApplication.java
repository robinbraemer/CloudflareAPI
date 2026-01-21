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

import java.util.List;

/**
 * Represents a Cloudflare Access Application (Zero Trust).
 * Access applications define the resources protected by Cloudflare Access.
 *
 * @see <a href="https://developers.cloudflare.com/api/resources/zero_trust/subresources/access/subresources/applications/">Cloudflare API</a>
 */
@Getter
@Setter
public class AccessApplication implements Identifiable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("aud")
    @Expose
    private String aud;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("domain")
    @Expose
    private String domain;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("session_duration")
    @Expose
    private String sessionDuration;

    @SerializedName("allowed_idps")
    @Expose
    private List<String> allowedIdps;

    @SerializedName("auto_redirect_to_identity")
    @Expose
    private Boolean autoRedirectToIdentity;

    @SerializedName("enable_binding_cookie")
    @Expose
    private Boolean enableBindingCookie;

    @SerializedName("http_only_cookie_attribute")
    @Expose
    private Boolean httpOnlyCookieAttribute;

    @SerializedName("same_site_cookie_attribute")
    @Expose
    private String sameSiteCookieAttribute;

    @SerializedName("logo_url")
    @Expose
    private String logoUrl;

    @SerializedName("skip_interstitial")
    @Expose
    private Boolean skipInterstitial;

    @SerializedName("app_launcher_visible")
    @Expose
    private Boolean appLauncherVisible;

    @SerializedName("service_auth_401_redirect")
    @Expose
    private Boolean serviceAuth401Redirect;

    @SerializedName("custom_deny_message")
    @Expose
    private String customDenyMessage;

    @SerializedName("custom_deny_url")
    @Expose
    private String customDenyUrl;

    @SerializedName("custom_non_identity_deny_url")
    @Expose
    private String customNonIdentityDenyUrl;

    @SerializedName("tags")
    @Expose
    private List<String> tags;

    @SerializedName("cors_headers")
    @Expose
    private CorsHeaders corsHeaders;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @Getter
    @Setter
    public static class CorsHeaders {
        @SerializedName("allowed_methods")
        @Expose
        private List<String> allowedMethods;

        @SerializedName("allowed_origins")
        @Expose
        private List<String> allowedOrigins;

        @SerializedName("allowed_headers")
        @Expose
        private List<String> allowedHeaders;

        @SerializedName("allow_all_methods")
        @Expose
        private Boolean allowAllMethods;

        @SerializedName("allow_all_origins")
        @Expose
        private Boolean allowAllOrigins;

        @SerializedName("allow_all_headers")
        @Expose
        private Boolean allowAllHeaders;

        @SerializedName("allow_credentials")
        @Expose
        private Boolean allowCredentials;

        @SerializedName("max_age")
        @Expose
        private Integer maxAge;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("domain", domain)
                .append("type", type)
                .append("sessionDuration", sessionDuration)
                .append("createdAt", createdAt)
                .toString();
    }
}
