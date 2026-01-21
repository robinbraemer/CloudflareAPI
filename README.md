# The Cloudflare - API/Library for Java

[![Jitpack-Month][jitpack-month-img]][jitpack-url]
[![JitPack][jitpack-img]][jitpack-url]
[![CI][ci-img]][ci-url]
[![License][license-img]][license-url]
<!-- [![Gitter][gitter-img]][gitter-url] -->

This Cloudflare API/Library interacts with [Cloudflare's fast API v4](https://api.cloudflare.com/)
and allows you to access every single feature _(even if it isn't added yet)_ of Cloudflare's API faster and much easier!

## Installation

### Maven Central (Recommended)

**Maven:**
```xml
<dependency>
  <groupId>io.github.robinbraemer</groupId>
  <artifactId>cloudflare-api</artifactId>
  <version>1.5.0</version>
</dependency>
```

**Gradle:**
```groovy
implementation 'io.github.robinbraemer:cloudflare-api:1.5.0'
```

### JitPack (Alternative)

**Maven:**
```xml
<dependency>
  <groupId>com.github.robinbraemer</groupId>
  <artifactId>CloudflareAPI</artifactId>
  <version>master-SNAPSHOT</version>
</dependency>

<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

**Gradle:**
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.robinbraemer:CloudflareAPI:master-SNAPSHOT'
}
```

## Features
- **easy to build cloudflare requests (builder pattern)**
- **able to access every corner of cloudflare's api**
- **parsing results as objects (object oriented representation)**
- **it is an extremely flexible api**
- **asynchronicity support**
- **token & key+email authentication support**

## Getting Started
First, you define the access object.
```java
String CF_API_TOKEN = "your_cloudflare_api_token";
CloudflareAccess cfAccess = new CloudflareAccess(CF_API_TOKEN);
```

Or use key+email authentication:
```java
String CF_API_KEY = "your_cloudflare_api_key";
String CF_EMAIL = "your_cloudflare@email.com";
CloudflareAccess cfAccess = new CloudflareAccess(CF_API_KEY, CF_EMAIL);
```

Then you can create cloudflare requests.
```java
CloudflareResponse<List<Zone>> response =
    new CloudflareRequest( Category.LIST_ZONES, cfAccess )
        .asObjectList( Zone.class );
```

## Learn more about the Cloudflare - client library

- Check out our users' guide in the wiki, [Cloudflare - API/Library explained][wiki-url].
- There is a lot to discover about this api/library.

### Licensing
Licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.






[releases-url]: https://github.com/robinbraemer/CloudflareAPI/releases
[wiki-url]: https://github.com/robinbraemer/CloudflareAPI/wiki

[ci-url]: https://github.com/robinbraemer/CloudflareAPI/actions
[ci-img]: https://github.com/robinbraemer/CloudflareAPI/actions/workflows/ci.yml/badge.svg

[jitpack-url]: https://jitpack.io/#robinbraemer/CloudflareAPI
[jitpack-img]: https://jitpack.io/v/robinbraemer/CloudflareAPI.svg
[jitpack-month-img]: https://jitpack.io/v/robinbraemer/CloudflareAPI/month.svg

[gitter-url]: https://gitter.im/CloudflareAPI/Lobby
[gitter-img]: https://badges.gitter.im/Join%20Chat.svg

[license-url]: https://github.com/robinbraemer/CloudflareAPI/blob/master/LICENSE
[license-img]: https://img.shields.io/badge/license-Apache_2.0-blue.svg?style=flat
