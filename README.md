# The Cloudflare - API/Library for Java

[![Jitpack-Month][jitpack-month-img]][jitpack-url]
[![JitPack][jitpack-img]][jitpack-url]
[![Travis][travis-img]][travis-url]
<!-- [![Gitter][gitter-img]][gitter-url] -->
[![License][license-img]][license-url]

This Cloudflare API/Library interacts with [Cloudflare's fast API v4](https://api.cloudflare.com/)
and allows you to access every single feature _(even if it isn't added yet)_ of Cloudflare's API faster and much easier!

To add a dependency on this Cloudflare-API/Library using Maven or Gradle use the following:

**Maven:**
```xml
<dependency>
  <groupId>com.github.robinbraemer</groupId>
  <artifactId>cloudflareapi</artifactId>
  <version>1.3.2</version>
</dependency>
	
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

**Gradle:**
```
dependencies {
  compile 'com.github.robinbraemer:cloudflareapi:1.3.2'
}

allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

## Features
- **easy to build cloudflare requests (builder pattern)**
- **able to access every corner of cloudflare's api**
- **parsing results as objects (object oriented representation)**
- **it is an extremely flexible api**
- **asynchronicity support**

## Getting Started
First, create an entry-point to use this api.
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

## Learn more about the Cloudflare - API/Library

- Check out our users' guide in the wiki, [Cloudflare - API/Library explained][wiki-url].
- There is a lot to discover about this api/library.

### Licensing
Licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.






[releases-url]: https://github.com/robinbraemer/CloudflareAPI/releases
[wiki-url]: https://github.com/robinbraemer/CloudflareAPI/wiki

[travis-url]: https://travis-ci.org/robinbraemer/CloudflareAPI
[travis-img]: https://travis-ci.org/robinbraemer/CloudflareAPI.svg?branch=master

[jitpack-url]: https://jitpack.io/#robinbraemer/CloudflareAPI
[jitpack-img]: https://jitpack.io/v/robinbraemer/CloudflareAPI.svg
[jitpack-month-img]: https://jitpack.io/v/robinbraemer/CloudflareAPI/month.svg

[gitter-url]: https://gitter.im/CloudflareAPI/Lobby
[gitter-img]: https://badges.gitter.im/Join%20Chat.svg

[license-url]: https://github.com/robinbraemer/CloudflareAPI/blob/master/LICENSE
[license-img]: https://img.shields.io/badge/license-Apache_2.0-blue.svg?style=flat
