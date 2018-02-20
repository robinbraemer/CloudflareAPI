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
    
    IPV4Address( "A" ),
    IPV6Address( "AAAA" ),
    CanonicalName( "CNAME" ),
    MailExchanger( "MX" ),
    NameServer( "NS" ),
    ServiceSupport( "SRV" ),
    Text( "TXT" ),
    Localisation( "LOC" ),
    SenderPolicyFramework( "SPF" ),
    CertificationAuthorityAuthorization( "CAA" );
    
    
    public final String opt;
    
    RecordType( String opt ) {
        this.opt = opt;
    }
}
