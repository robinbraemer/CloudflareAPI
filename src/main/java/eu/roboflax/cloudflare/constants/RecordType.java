/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.constants;

public enum RecordType {
    
    A( "IPV4Address" ),
    AAAA( "IPV6Address" ),
    CNAME( "CanonicalName" ),
    MX( "MailExchanger" ),
    NS( "NameServer" ),
    SRV( "ServiceSupport" ),
    TXT( "Text" ),
    LOC( "Localisation" ),
    SPF( "SenderPolicyFramework" ),
    CAA( "CertificationAuthorityAuthorization" );
    
    
    public final String name;
    
    RecordType( String name ) {
        this.name = name;
    }
}
