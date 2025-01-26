/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.constants;

import eu.roboflax.cloudflare.CloudflareAccess;
import eu.roboflax.cloudflare.http.HttpMethod;

import static eu.roboflax.cloudflare.http.HttpMethod.*;

/**
 * Every request that can be made on api.cloudflare.com.
 * @version March 1st, 2018
 */
public enum Category {
    
    // User (The currently logged in/authenticated User)
    USER_DETAILS( GET, "user" ),
    UPDATE_USER( PATCH, "user" ),
    
    // User Billing Profile (A user billing profile)
    USER_BILLING_PROFILE( GET, "user/billing/profile" ),
    // User Billing History (A user billing history)
    USER_BILLING_HISTORY( GET, "user/billing/history" ),
    
    // User Subscription (Listing of a user's subscriptions)
    USER_SUBSCRIPTION( GET, "user/subscription" ),
    UPDATE_USER_SUBSCRIPTION( PUT, "user/subscriptions/{id-1}" ),
    DELETE_USER_SUBSCRIPTION( DELETE, "user/subscriptions/{id-1}" ),
    
    // User-level Firewall access rule (A firewall access rule applied to all zones owned by the user)
    LIST_USER_ACCESS_RULES( GET, "user/firewall/access_rules/rules" ),
    CREATE_USER_ACCESS_RULE( POST, "user/firewall/access_rules/rules" ),
    UPDATE_USER_ACCESS_RULE( PATCH, "user/firewall/access_rules/rules/{id-1}" ),
    DELETE_USER_ACCESS_RULE( DELETE, "user/firewall/access_rules/rules/{id-1}" ),
    
    // User's Organizations (A list of organizations this user is a member of)
    LIST_USER_ORGANIZATIONS( GET, "user/organizations" ),
    USER_ORGANIZATION_DETAILS( GET, "user/organizations/{id-1}" ),
    USER_LEAVE_ORGANIZATION( DELETE, "user/organizations/{id-1}" ),
    
    // User's Invites (Your pending invitations to organizations)
    LIST_USER_INVITES( GET, "user/invites" ),
    USER_INVITATION_DETAILS( GET, "user/invites/{id-1}" ),
    USER_RESPOND_INVITATION( PATCH, "user/invites/{id-1}" ),
    
    // Service (A Service is a domain name along with its subdomains and other identities)
    CREATE_ZONE( POST, "zones" ),
    INITIATE_ANOTHER_ZONE_ACTIVATION_CHECK( PUT, "zones/{id-1}/activation_check" ),
    LIST_ZONES( GET, "zones" ),
    ZONE_DETAILS( GET, "zones/{id-1}" ),
    EDIT_ZONE_PROPERTIES( PATCH, "zones/{id-1}" ),
    PURGE_ZONE_ALL_FILES( DELETE, "zones/{id-1}/purge_cache" ),
    PURGE_ZONE_FILES_BY_URL( DELETE, "zones/{id-1}/purge_cache" ),
    PURGE_ZONE_FILES_BY_CACHE_TAG_OR_HOST( DELETE, "zones/{id-1}/purge_cache" ),
    DELETE_ZONE( DELETE, "zones/{id-1}" ),
    
    // Service Rate Plan (A zone rate plan from the billing service)
    ZONE_RATE_PLAN( GET, "zones/{id-1}/available_rate_plans" ),
    
    // Service Settings (A Service setting changes how the Service works
    // in relation to caching, security, or other features of Cloudflare)
    LIST_ZONE_SETTINGS( GET, "zones/{id-1}/settings" ),
    DDOS_SETTING( GET, "zones/{id-1}/settings/advanced_ddos" ),
    ALWAYS_ONLINE_SETTING( GET, "zones/{id-1}/settings/always_online" ),
    ALWAYS_USE_HTTPS_SETTING( GET, "zones/{id-1}/settings/always_use_https" ),
    AUTOMATIC_HTTPS_REWRITES_SETTING( GET, "zones/{id-1}/settings/automatic_https_rewrites" ),
    BROWSER_CACHE_TTL_SETTING( GET, "zones/{id-1}/settings/browser_cache_ttl" ),
    BROWSER_CHECK_SETTING( GET, "zones/{id-1}/settings/browser_check" ),
    CACHE_LEVEL_SETTING( GET, "zones/{id-1}/settings/cache_level" ),
    CHALLENGE_TTL_SETTING( GET, "zones/{id-1}/settings/challenge_ttl" ),
    DEV_MODE_SETTING( GET, "zones/{id-1}/settings/development_mode" ),
    EMAIL_OBFUSCATION_SETTING( GET, "zones/{id-1}/settings/email_obfuscation" ),
    HOTLINK_PROTECTION_SETTING( GET, "zones/{id-1}/settings/hotlink_protection" ),
    IP_GEOLOCATION_SETTING( GET, "zones/{id-1}/settings/ip_geolocation" ),
    IPV6_SETTING( GET, "zones/{id-1}/settings/ipv6" ),
    MINIFY_SETTING( GET, "zones/{id-1}/settings/minify" ),
    MOBILE_REDIRECT_SETTING( GET, "zones/{id-1}/settings/mobile_redirect" ),
    MIRAGE_SETTING( GET, "zones/{id-1}/settings/mirage" ),
    ENABLE_ERROR_PAGES_SETTING( GET, "zones/{id-1}/settings/origin_error_page_pass_thru" ),
    OPPORTUNISTIC_ENCRYPTION_SETTING( GET, "zones/{id-1}/settings/opportunistic_encryption" ),
    POLISH_SETTING( GET, "zones/{id-1}/settings/polish" ),
    WEBP_SETTING( GET, "zones/{id-1}/settings/webp" ),
    PREFECH_PRELOAD_SETTING( GET, "zones/{id-1}/settings/prefetch_preload" ),
    RESPONSE_BUFFERING_SETTING( GET, "zones/{id-1}/settings/response_buffering" ),
    ROCKET_LOADER_SETTING( GET, "zones/{id-1}/settings/rocket_loader" ),
    SECURITY_HEADER_SETTING( GET, "zones/{id-1}/settings/security_header" ),
    SECURITY_LEVEL_SETTING( GET, "zones/{id-1}/settings/security_level" ),
    SERVER_SIDE_EXCLUDE_SETTING( GET, "zones/{id-1}/settings/server_side_exclude" ),
    ENABLE_QUERY_STRING_SORT_SETTING( GET, "zones/{id-1}/settings/sort_query_string_for_cache" ),
    SSL_SETTING( GET, "zones/{id-1}/settings/ssl" ),
    ENABLE_TLS_1_2_SETTING( GET, "zones/{id-1}/settings/tls_1_2_only" ),
    ENABLE_TLS_1_3_SETTING( GET, "zones/{id-1}/settings/tls_1_3" ),
    TLS_CLIENT_AUTH_SETTING( GET, "zones/{id-1}/settings/tls_client_auth" ),
    TRUE_CLIENT_IP_SETTING( GET, "zones/{id-1}/settings/true_client_ip_header" ),
    WEB_APPLICATION_FIREWALL_SETTING( GET, "zones/{id-1}/settings/waf" ),
    HTTP2_SETTING( GET, "zones/{id-1}/settings/http2" ),
    PSEUDO_IPV4_SETTING( GET, "zones/{id-1}/settings/pseudo_ipv4" ),
    WEBSOCKETS_SETTING( GET, "zones/{id-1}/settings/websockets" ),
    
    EDIT_ZONE_SETTINGS_INFO( PATCH, "zones/{id-1}/settings" ),
    CHANGE_ALWAYS_ONLINE_SETTING( PATCH, "zones/{id-1}/settings/always_online" ),
    CHANGE_ALWAYS_USE_HTTPS_SETTING( PATCH, "zones/{id-1}/settings/always_use_https" ),
    CHANGE_AUTOMATIC_HTTPS_REWRITES_SETTING( PATCH, "zones/{id-1}/settings/automatic_https_rewrites" ),
    CHANGE_BROWSER_CACHE_TTL_SETTING( PATCH, "zones/{id-1}/settings/browser_cache_ttl" ),
    CHANGE_BROWSER_CHECK_SETTING( PATCH, "zones/{id-1}/settings/browser_check" ),
    CHANGE_CACHE_LEVEL_SETTING( PATCH, "zones/{id-1}/settings/cache_level" ),
    CHANGE_CHALLENGE_TTL_SETTING( PATCH, "zones/{id-1}/settings/challenge_ttl" ),
    CHANGE_DEV_MODE_SETTING( PATCH, "zones/{id-1}/settings/development_mode" ),
    CHANGE_EMAIL_OBFUSCATION_SETTING( PATCH, "zones/{id-1}/settings/email_obfuscation" ),
    CHANGE_ENABLE_ERROR_PAGES_SETTING( PATCH, "zones/{id-1}/settings/origin_error_page_pass_thru" ),
    CHANGE_ENABLE_QUERY_STRING_SORT_SETTING( PATCH, "zones/{id-1}/settings/sort_query_string_for_cache" ),
    CHANGE_HOTLINK_PROTECTION_SETTING( PATCH, "zones/{id-1}/settings/hotlink_protection" ),
    CHANGE_IP_GEOLOCATION_SETTING( PATCH, "zones/{id-1}/settings/ip_geolocation" ),
    CHANGE_IPV6_SETTING( PATCH, "zones/{id-1}/settings/ipv6" ),
    CHANGE_MINIFY_SETTING( PATCH, "zones/{id-1}/settings/minify" ),
    CHANGE_MOBILE_REDIRECT_SETTING( PATCH, "zones/{id-1}/settings/mobile_redirect" ),
    CHANGE_MIRAGE_SETTING( PATCH, "zones/{id-1}/settings/mirage" ),
    CHANGE_OPPORTUNISTIC_ENCRYPTION_SETTING( PATCH, "zones/{id-1}/settings/opportunistic_encryption" ),
    CHANGE_POLISH_SETTING( PATCH, "zones/{id-1}/settings/polish" ),
    CHANGE_WEBP_SETTING( PATCH, "zones/{id-1}/settings/webp" ),
    CHANGE_PREFECH_PRELOAD_SETTING( PATCH, "zones/{id-1}/settings/prefetch_preload" ),
    CHANGE_RESPONSE_BUFFERING_SETTING( PATCH, "zones/{id-1}/settings/response_buffering" ),
    CHANGE_ROCKET_LOADER_SETTING( PATCH, "zones/{id-1}/settings/rocket_loader" ),
    CHANGE_SECURITY_HEADER_SETTING( PATCH, "zones/{id-1}/settings/security_header" ),
    CHANGE_SECURITY_LEVEL_SETTING( PATCH, "zones/{id-1}/settings/security_level" ),
    CHANGE_SERVER_SIDE_EXCLUDE_SETTING( PATCH, "zones/{id-1}/settings/server_side_exclude" ),
    CHANGE_SSL_SETTING( PATCH, "zones/{id-1}/settings/ssl" ),
    CHANGE_ENABLE_TLS_1_2_SETTING( PATCH, "zones/{id-1}/settings/tls_1_2_only" ),
    CHANGE_ENABLE_TLS_1_3_SETTING( PATCH, "zones/{id-1}/settings/tls_1_3" ),
    CHANGE_TLS_CLIENT_AUTH_SETTING( PATCH, "zones/{id-1}/settings/tls_client_auth" ),
    CHANGE_TRUE_CLIENT_IP_SETTING( PATCH, "zones/{id-1}/settings/true_client_ip_header" ),
    CHANGE_WEB_APPLICATION_FIREWALL_SETTING( PATCH, "zones/{id-1}/settings/waf" ),
    CHANGE_HTTP2_SETTING( PATCH, "zones/{id-1}/settings/http2" ),
    CHANGE_PSEUDO_IPV4_SETTING( PATCH, "zones/{id-1}/settings/pseudo_ipv4" ),
    CHANGE_WEBSOCKETS_SETTING( PATCH, "zones/{id-1}/settings/websockets" ),
    
    // DNS Records for a Service (Documentation for Cloudflare DNS records)
    CREATE_DNS_RECORD( POST, "zones/{id-1}/dns_records" ),
    LIST_DNS_RECORDS( GET, "zones/{id-1}/dns_records" ),
    DNS_RECORD_DETAILS( GET, "zones/{id-1}/dns_records/{id-2}" ),
    UPDATE_DNS_RECORD( PUT, "zones/{id-1}/dns_records/{id-2}" ),
    DELETE_DNS_RECORD( DELETE, "zones/{id-1}/dns_records/{id-2}" ),
    IMPORT_DNS_RECORDS( POST, "zones/{id-1}/dns_records/import" ),
    
    // Railgun connections for a Service (Railguns associated with a zone)
    ZONE_RAILGRUNS_AVAILABLE( GET, "zones/{id-1}/railguns" ),
    ZONE_RAILGUN_DETAILS( GET, "zones/{id-1}/railguns/{id-2}" ),
    ZONE_TEST_RAILGUN_CONNECTION( GET, "zones/{id-1}/railguns/{id-2}/diagnose" ),
    ZONE_CONNECT_OR_DISCONNECT_RAILGUN( PATCH, "zones/{id-1}/railguns/{id-2}" ),
    
    // Service Analytics (Analytics data for a zone)
    ANALYTICS_DASHBOARD( GET, "zones/{id-1}/analytics/dashboard" ),
    ANALYTICS_BY_CO_LOCATIONS( GET, "zones/{id-1}/analytics/colos" ),
    ANALYTICS_TABLE( GET, "zones/{id-1}/dns_analytics/report" ),
    ANALYTICS_BY_TIME( GET, "zones/{id-1}/dns_analytics/report/bytime" ),
    
    // Railgun (Cloudflare Railgun)
    CREATE_RAILGUN( POST, "railguns" ),
    LIST_RAILGUNS( GET, "railguns" ),
    RAILGUN_DETAILS( GET, "railguns/{id-1}" ),
    ZONES_CONNECTED_TO_RAILGUN( GET, "railguns/{id-1}/zones" ),
    ENABLE_OR_DISABLE_RAILGUN( PATCH, "railguns/{id-1}" ),
    DELETE_RAILGUN( DELETE, "railguns/{id-1}" ),
    
    // Custom Pages for a Service (Custom pages associated with a zone)
    CUSTOM_PAGES_AVAILABLE( GET, "zones/{id-1}/custom_pages" ),
    CUSTOM_PAGE_DETAILS( GET, "zones/{id-1}/custom_pages/{id-2}" ),
    UPDATE_CUSTOM_PAGE_URL( PUT, "zones/{id-1}/custom_pages/{id-2}" ),
    
    // Custom SSL for a Service (Custom SSL certificate for a zone)
    CREATE_SSL_CONFIGURATION( POST, "zones/{id-1}/custom_certificates" ),
    LIST_SSL_CONFIGURATIONS( GET, "zones/{id-1}/custom_certificates" ),
    SSL_CONFIGURATION_DETAILS( GET, "zones/{id-1}/custom_certificates/{id-2}" ),
    UPDATE_SSL_CONFIGURATION( PATCH, "zones/{id-1}/custom_certificates/{id-2}" ),
    RE_PRIORITIZE_SSL_CERTIFICATES( PUT, "zones/{id-1}/custom_certificates/prioritize" ),
    DELETE_SSL_CERTIFICATE( DELETE, "zones/{id-1}/custom_certificates/{id-2}" ),
    
    // Custom Hostname for a Service (Manage hostnames for your zone that are routed via CNAME.)
    CREATE_CUSTOM_HOSTNAME( POST, "zones/{id-1}/custom_hostnames" ),
    LIST_CUSTOM_HOSTNAMES( GET, "zones/{id-1}/custom_hostnames" ),
    CUSTOM_HOSTNAME_CONFIGURATION_DETAILS( GET, "zones/{id-1}/custom_hostnames/{id-2}" ),
    UPDATE_CUSTOM_HOSTNAME_CONFIGURATION( PATCH, "zones/{id-1}/custom_hostnames/{id-2}" ),
    DELETE_CUSTOM_HOSTNAME_CONFIGURATION( DELETE, "zones/{id-1}/custom_hostnames/{id-2}" ),
    
    // Keyless SSL for a Service (A Keyless certificate is an SSL certificate where the SSL private key is not stored on Cloudflare)
    CREATE_KEYLESS_SSL_CONFIGURATION( POST, "zones/{id-1}/keyless_certificates" ),
    LIST_KEYLESS_SSL_CONFIGURATIONS( GET, "zones/{id-1}/keyless_certificates" ),
    KEYLESS_SSL_DETAILS( GET, "zones/{id-1}/keyless_certificates/{id-2}" ),
    UPDATE_KEYLESS_SSL_CONFIGURATION( PATCH, "zones/{id-1}/keyless_certificates/{id-2}" ),
    DELETE_KEYLESS_CONFIGURATION( DELETE, "zones/{id-1}/keyless_certificates/{id-2}" ),
    
    // Page rules for a Service (A rule describing target patterns for requests and actions to perform on matching requests)
    CREATE_PAGE_RULE( POST, "zones/{id-1}/pagerules" ),
    LIST_PAGE_RULES( GET, "zones/{id-1}/pagerules" ),
    PAGE_RULE_DETAILS( GET, "zones/{id-1}/pagerules/{id-2}" ),
    CHANGE_PAGE_RULE( PATCH, "zones/{id-1}/pagerules/{id-2}" ),
    UPDATE_PAGE_RULE( PUT, "zones/{id-1}/pagerules/{id-2}" ),
    DELETE_PAGE_RULE( DELETE, "zones/{id-1}/pagerules/{id-2}" ),
    
    // Rate Limits for a Service (Documentation for Cloudflare Rate Limits)
    LIST_RATE_LIMITS( GET, "zones/{id-1}/rate_limits" ),
    CREATE_RATE_LIMIT( POST, "zones/{id-1}/rate_limits" ),
    RATE_LIMIT_DETAILS( GET, "zones/{id-1}/rate_limits/{id-2}" ),
    UPDATE_RATE_LIMIT( PUT, "zones/{id-1}/rate_limits/{id-2}" ),
    DELETE_RATE_LIMIT( DELETE, "zones/{id-1}/rate_limits/{id-2}" ),
    
    // Firewall access rule for a Service (An IP, IP range, or country specific firewall rule applied
    // directly to a zone or inherited from user or organization-level rules.)
    LIST_ZONE_ACCESS_RULES( GET, "zones/{id-1}/firewall/access_rules/rules" ),
    CREATE_ZONE_ACCESS_RULE( POST, "zones/{id-1}/firewall/access_rules/rules" ),
    UPDATE_ZONE_ACCESS_RULE( PATCH, "zones/{id-1}/firewall/access_rules/rules/{id-2}" ),
    DELETE_ZONE_ACCESS_RULE( DELETE, "zones/{id-1}/firewall/access_rules/rules/{id-2}" ),
    
    // WAF Rule Packages (Web application firewall rule package applied to a zone)
    LIST_FIREWALL_PACKAGES( GET, "zones/{id-1}/firewall/waf/packages" ),
    FIREWALL_PACKAGE_INFO( GET, "zones/{id-1}/firewall/waf/packages/{id-2}" ),
    CHANGE_ANOMALY_DETECTION_WEB_APP_FIREWALL_PACKAGE_SETTINGS( PATCH, "zones/{id-1}/firewall/waf/packages/{id-2}" ),
    
    // WAF Rule Groups (A group of web application firewall rules that share common functionality and traits)
    LIST_RULE_GROUPS( GET, "zones/{id-1}/firewall/waf/packages/{id-2}/groups" ),
    RULE_GROUP_INFO( GET, "zones/{id-1}/firewall/waf/packages/{id-2}/groups/{id-3}" ),
    UPDATE_RULE_GROUP( PATCH, "zones/{id-1}/firewall/waf/packages/{id-2}/groups/{id-3}" ),
    
    // WAF Rules (A firewall rule for a zone)
    LIST_RULES( GET, "zones/{id-1}/firewall/waf/packages/{id-2}/rules" ),
    RULE_INFO( GET, "zones/{id-1}/firewall/waf/packages/{id-2}/rules/{id-3}" ),
    UPDATE_RULE( PATCH, "zones/{id-1}/firewall/waf/packages/{id-2}/rules/{id-3}" ),
    
    // User-Agent Blocking Rules (Perform access control when matching the exact UserAgent reported by the client)
    LIST_USERAGENT_RULES( GET, "zones/{id-1}/firewall/ua_rules" ),
    CREATE_USERAGENT_RULE( POST, "zones/{id-1}/firewall/ua_rules" ),
    USERAGENT_RULE_DETAILS( GET, "zones/{id-1}/firewall/ua_rules/{id-2}" ),
    DELETE_USERAGENT_RULE( DELETE, "zones/{id-1}/firewall/ua_rules/{id-2}" ),
    
    // Zone Lockdown (Lock access to URLs in this zone to only permitted addresses or address ranges.)
    LIST_LOCKDOWN_RULES( GET, "zones/{id-1}/firewall/lockdowns" ),
    CREATE_LOCKDOWN_RULE( POST, "zones/{id-1}/firewall/lockdowns" ),
    LOCKDOWN_RULE_DETAILS( GET, "zones/{id-1}/firewall/lockdowns/{id-2}" ),
    UPDATE_LOCKDOWN_RULE( PUT, "zones/{id-1}/firewall/lockdowns/{id-2}" ),
    DELETE_LOCKDOWN_RULE( DELETE, "zones/{id-1}/firewall/lockdowns/{id-2}" ),
    
    // Analyze Certificate
    ANALYZE_CERTIFICATE( POST, "zones/{id-1}/ssl/analyze" ),
    // Certificate Packs
    LIST_CERTIFICATE_PACKS( GET, "zones/{id-1}/ssl/certificate_packs" ),
    ORDER_CERTIFICATE_PACK( PUT, "zones/{id-1}/ssl/certificate_packs" ),
    EDIT_CERTIFICATE_PACK( PATCH, "zones/{id-1}/ssl/certificate_packs/{id-2}" ),
    
    // SSL Verification (SSL Verification for a Zone)
    SSL_VERIFICATION( GET, "zones/{id-1}/ssl/verification" ),
    
    // Universal SSL Settings for a Zone (Universal SSL Settings for a Zone)
    UNIVERSAL_SSL_SETTINGS( GET, "zones/{id-1}/ssl/universal/settings" ),
    CHANGE_UNIVERSAL_SSL_SETTINGS( GET, "zones/{id-1}/ssl/universal/settings" ),
    
    // Zone Subscription (A subscription associated with a zone containing plan and add-ons)
    ZONE_SUBSCRIPTION( GET, "zones/{id-1}/subscription" ),
    CREATE_ZONE_SUBSCRIPTION( POST, "zones/{id-1}/subscription" ),
    UPDATE_ZONE_SUBSCRIPTION( PUT, "zones/{id-1}/subscription" ),
    
    // Organizations (An Organization is an entity which holds a set of zones for multiple users to interact with.)
    ORGANIZATION_DETAILS( GET, "organizations/{id-1}" ),
    UPDATE_ORGANIZATION( PATCH, "organizations/{id-1}" ),
    
    // Organization Members (A member is the association of a Cloudflare user with an Organization.)
    LIST_ORGANIZATION_MEMBERS( GET, "organizations/{id-1}/members" ),
    ORGANIZATION_MEMBER_DETAILS( GET, "organizations/{id-1}/members/{id-2}" ),
    UPDATE_ORGANIZATION_MEMBER_RULES( PATCH, "organizations/{id-1}/members/{id-2}" ),
    REMOVE_ORGANIZATION_MEMBER( PATCH, "organizations/{id-1}/members/{id-2}" ),
    
    // Organization Invites (Invitations to potential members that this organization has created)
    CREATE_INVITATION( POST, "organizations/{id-1}/invites" ),
    LIST_INVITATIONS( GET, "organizations/{id-1}/invites" ),
    INVITATION_DETAILS( GET, "organizations/{id-1}/invites/{id-2}" ),
    UPDATE_INVITATION_ROLES( PATCH, "organizations/{id-1}/invites/{id-2}" ),
    CANCELINVITATION( DELETE, "organizations/{id-1}/invites/{id-2}" ),
    
    // Organization Roles (A role defines what permissions a Member of an Organization has.)
    LIST_ORGANIZATION_ROLES( GET, "organizations/{id-1}/roles" ),
    ORGANIZATION_ROLE_DETAILS( GET, "organizations/{id-1}/roles/{id-2}" ),
    
    // Organization-level Firewall access rule (A firewall access rule applied to all zones owned by the organization)
    LIST_ORGANIZATION_ACCESS_RULES( GET, "organizations/{id-1}/firewall/access_rules/rules" ),
    CREATE_ORGANIZATION_ACCESS_RULE( POST, "organizations/{id-1}/firewall/access_rules/rules" ),
    UPDATE_ORGANIZATION_ACCESS_RULE( PATCH, "organizations/{id-1}/firewall/access_rules/rules/{id-2}" ),
    DELETE_ORGANIZATION_ACCESS_RULE( DELETE, "organizations/{id-1}/firewall/access_rules/rules/{id-2}" ),
    
    // Organization Railgun (Cloudflare Railgun for Organizations)
    CREATE_ORGANIZATION_RAILGUN( POST, "organizations/{id-1}/railguns" ),
    LIST_ORGANIZATION_RAILGUNS( GET, "organizations/{id-1}/railguns" ),
    ORGANIZATION_RAILGUN_DETAILS( GET, "organizations/{id-1}/railguns/{id-2}" ),
    ORGANIZATION_ZONES_CONNECTED_TO_RAILGUNS( GET, "organizations/{id-1}/railguns/{id-2}/zones" ),
    ENABLE_ORGANIZATION_RAILGUN( PATCH, "organizations/{id-1}/railguns/{id-2}" ),
    DELETE_ORGANIZATION_RAILGUN( DELETE, "organizations/{id-1}/railguns/{id-2}" ),
    
    // Cloudflare CA (API to create Cloudflare-issued SSL certificates that can be installed
    // on your origin server. Use your Certificates API Key as your User Service Key when calling
    // these endpoints (see the section on request headers for details).)
    LIST_CERTIFICATES( GET, "certificates" ),
    CREATE_CERTIFICATE( POST, "certificates" ),
    CERTIFICATE_DETAILS( GET, "certificates/{id-1}" ),
    REVOKE_CERTIFICATE( DELETE, "certificates/{id-1}" ),
    
    // Virtual DNS (Users) (User-level Virtual DNS Management)
    LIST_VIRTUAL_USER_DNS_CLUSTER( GET, "user/virtual_dns" ),
    CREATE_VIRTUAL_USER_DNS_CLUSTER( POST, "user/virtual_dns" ),
    VIRTUAL_USER_DNS_CLUSTER_DETAILS( GET, "user/virtual_dns/{id-1}" ),
    DELETE_VIRTUAL_USER_DNS_CLUSTER( DELETE, "user/virtual_dns/{id-1}" ),
    MODIFY_VIRTUAL_USER_DNS_CLUSTER( PATCH, "user/virtual_dns/{id-1}" ),
    
    // Virtual DNS (Organizations) (Organization-level Virtual DNS Management)
    LIST_VIRTUAL_ORGANIZATION_DNS_CLUSTER( GET, "organizations/{id-1}/virtual_dns" ),
    CREATE_VIRTUAL_ORGANIZATION_DNS_CLUSTER( POST, "organizations/{id-1}/virtual_dns/{id-2}" ),
    VIRTUAL_ORGANIZATION_DNS_CLUSTER_DETAILS( GET, "organizations/{id-1}/virtual_dns/{id-2}" ),
    DELETE_VIRTUAL_ORGANIZATION_DNS_CLUSTER( DELETE, "organizations/{id-1}/virtual_dns/{id-2}" ),
    MODIFY_VIRTUAL_ORGANIZATION_DNS_CLUSTER( PATCH, "organizations/{id-1}/virtual_dns/{id-2}" ),
    
    // Virtual DNS Analytics (Users) (Analytics data for a Virtual DNS cluster.)
    VIRTUAL_USER_DNS_ANALYTICS_TABLE( GET, "user/virtual_dns/{id-1}/dns_analytics/report" ),
    VIRTUAL_USER_DNS_ANALYTICS_TABLE_BY_TIME( GET, "user/virtual_dns/{id-1}/dns_analytics/report/bytime" ),
    
    // Virtual DNS Analytics (Organizations) (Analytics data for a Virtual DNS cluster.)
    VIRTUAL_ORGANIZATION_DNS_ANALYTICS_TABLE( GET, "organizations/virtual_dns/{id-1}/dns_analytics/report" ),
    VIRTUAL_ORGANIZATION_DNS_ANALYTICS_TABLE_BY_TIME( GET, "organizations/virtual_dns/{id-1}/dns_analytics/report/bytime" ),
    
    // Cloudflare IPs (Cloudflare IP space)
    CLOUDFLARE_IPS( GET, "ips" ),
    
    // AML (Accelerated Mobile Links)
    AML_SETTINGS( GET, "zones/{id-1}/amp/viewer" ),
    UPDATE_AML_SETTINGS( PUT, "zones/{id-1}/amp/viewer" ),
    
    // Load Balancer Monitors (User-level Monitor configurations. Monitors define whether
    // we check over HTTP or HTTPS, the Status code(s) we look for, the interval at
    // which we check, timeouts and response body matching.)
    LIST_USER_MONITORS( GET, "user/load_balancers/monitors" ),
    CREATE_USER_MONITOR( POST, "user/load_balancers/monitors" ),
    MONITOR_USER_DETAILS( POST, "user/load_balancers/monitors/{id-1}" ),
    DELETE_USER_MONITOR( DELETE, "user/load_balancers/monitors/{id-1}" ),
    MODIFY_USER_MONITOR( PUT, "user/load_balancers/monitors/{id-1}" ),
    
    // Load Balancer Pools (User-level Load Balancer Pools)
    LIST_USER_POOLS( GET, "user/load_balancers/pools" ),
    CREATE_USER_POOL( POST, "user/load_balancers/pools" ),
    POOL_USER_DETAILS( GET, "user/load_balancers/pools/{id-1}" ),
    DELETE_USER_POOL( DELETE, "user/load_balancers/pools/{id-1}" ),
    MODIFY_USER_POOL( PUT, "user/load_balancers/pools/{id-1}" ),
    
    // Organization Load Balancer Monitors (Organization-level Monitor configurations.
    // Monitors define whether we check over HTTP or HTTPS, the Status code(s) we look for,
    // the interval at which we check, timeouts and response body matching.)
    LIST_ORGANIZATION_MONITORS( GET, "organizations/{id-1}/load_balancers/monitors" ),
    CREATE_ORGANIZATION_MONITOR( POST, "organizations/{id-1}/load_balancers/monitors" ),
    MONITOR_ORGANIZATION_DETAILS( POST, "organizations/{id-1}/load_balancers/monitors/{id-2}" ),
    DELETE_ORGANIZATION_MONITOR( DELETE, "organizations/{id-1}/load_balancers/monitors/{id-2}" ),
    MODIFY_ORGANIZATION_MONITOR( PUT, "organizations/{id-1}/load_balancers/monitors/{id-2}" ),
    
    // Organization Load Balancer Pools (Organization-level Load Balancer Pools)
    LIST_ORGANIZATION_POOLS( GET, "organizations/{id-1}/load_balancers/pools" ),
    CREATE_ORGANIZATION_POOL( POST, "organizations/{id-1}/load_balancers/pools" ),
    POOL_ORGANIZATION_DETAILS( GET, "organizations/{id-1}/load_balancers/pools/{id-2}" ),
    DELETE_ORGANIZATION_POOL( DELETE, "organizations/{id-1}/load_balancers/pools/{id-2}" ),
    MODIFY_ORGANIZATION_POOL( PUT, "organizations/{id-1}/load_balancers/pools/{id-2}" ),
    
    // Load Balancers (Zone-level Load Balancers)
    LIST_ZONE_LOAD_BALANCERS( GET, "zones/{id-1}/load_balancers" ),
    CREATE_ZONE_LOAD_BALANCER( POST, "zones/{id-1}/load_balancers" ),
    ZONE_LOAD_BALANSER_DETAILS( GET, "zones/{id-1}/load_balancers/{id-2}" ),
    DELETE_ZONE_LOAD_BALANCER( DELETE, "zones/{id-1}/load_balancers/{id-2}" ),
    MODIFY_ZONE_LOAD_BALANCER( PUT, "zones/{id-1}/load_balancers/{id-2}" ),
    
    // Audit Logs (A log of changes made to your Cloudflare account)
    LIST_USER_AUDIT_LOGS( GET, "user/audit_logs" ),
    LIST_ORGANIZATION_AUDIT_LOGS( GET, "organizations/audit_logs" ),

    // Spectrum Applications
    CREATE_SPECTRUM_APPLICATION( POST, "zones/{id-1}/spectrum/apps" ),
    LIST_SPECTRUM_APPLICATIONS( GET, "zones/{id-1}/spectrum/apps" ),
    SPECTRUM_APPLICATION_DETAILS( GET, "zones/{id-1}/spectrum/apps/{id-2}" ),
    UPDATE_SPECTRUM_APPLICATION( PUT, "zones/{id-1}/spectrum/apps/{id-2}" ),
    DELETE_SPECTRUM_APPLICATION( DELETE, "zones/{id-1}/spectrum/apps/{id-2}" ),

    // Worker Script
    UPLOAD_WORKER_SCRIPT( PUT, "accounts/{id-1}/workers/scripts/{id-2}" ),
    LIST_WORKER_SCRIPTS( GET, "accounts/{id-1}/workers/scripts" ),
    DOWNLOAD_WORKER_SCRIPT( GET, "accounts/{id-1}/workers/scripts/{id-2}" ),
    DELETE_WORKER_SCRIPT( DELETE, "accounts/{id-1}/workers/scripts/{id-2}" ),

    // Worker Route
    CREATE_WORKER_ROUTE( POST, "zones/{id-1}/workers/routes" ),
    LIST_WORKER_ROUTES( GET, "zones/{id-1}/workers/routes" ),
    GET_WORKER_ROUTE( GET, "zones/{id-1}/workers/routes/{id-2}" ),
    UPDATE_WORKER_ROUTE( PUT, "zones/{id-1}/workers/routes/{id-2}" ),
    DELETE_WORKER_ROUTE( DELETE, "zones/{id-1}/workers/routes/{id-2}" );

    private final HttpMethod httpMethod;
    private final String additionalPath;
    
    Category( HttpMethod httpMethod, String additionalPath ) {
        this.httpMethod = httpMethod;
        this.additionalPath = additionalPath;
    }
    
    public HttpMethod getHttpMethod( ) {
        return httpMethod;
    }
    
    public String getAdditionalPath( ) {
        return additionalPath;
    }
    
    public String getPath( ) {
        return CloudflareAccess.API_BASE_URL + additionalPath;
    }
}
