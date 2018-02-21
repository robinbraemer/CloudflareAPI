/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 *
 * Project Samples created by RoboFlax.
 * Class created on 20.12.2017 at 17:41.
 *
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
