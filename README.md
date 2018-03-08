# Cloudflare: The Cloudflare - API/Library for Java

[![Travis][travis-img]][travis-url]
[![JitPack][jitpack-img]][jitpack-url]
[![Gitter][gitter-img]][gitter-url]
[![License][license-img]][license-url]

This Cloudflare API/Library interacts with [Cloudflare's fast API v4](https://api.cloudflare.com/)
and allows you to access every single feature _(even if it isn't added yet)_ of Cloudflare's API faster and much easier!

## Latest release
To add a dependency on this Cloudflare-API/Library using Maven or Gradle use the following:

**Maven:**
```xml
<dependency>
  <groupId>com.github.roboflax</groupId>
  <artifactId>cloudflareapi</artifactId>
  <version>1.3.0</version>
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
  compile 'com.github.roboflax:cloudflareapi:1.3.0'
}

allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

## Features
- **able to access every corner of cloudflare's api**
- **parsing results as objects**
- **object oriented representation of the cloudflare api responses** _(!= 100%)_
- **it is an extremely flexible api**

## Getting Started
First, create an entry-point to use this api.
```java
String CF_EMAIL = "your_cloudflare_email@example.com";
String CF_API_KEY = "your_cloudflare_api_key";

CloudflareAccess cfAccess = new CloudflareAccess(CF_EMAIL, CF_API_KEY);
```

## Learn more about the Cloudflare - API/Library

- Our users' guide in the wiki, [Cloudflare - API/Library explained](https://github.com/RoboFlax/CloudflareAPI/wiki).

### Licensing
Licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.




[travis-url]: https://travis-ci.org/RoboFlax/CloudflareAPI
[travis-img]: https://travis-ci.org/RoboFlax/CloudflareAPI.svg?branch=master

[jitpack-url]: https://jitpack.io/#RoboFlax/CloudflareAPI
[jitpack-img]: https://jitpack.io/v/RoboFlax/CloudflareAPI.svg

[gitter-url]: https://gitter.im/CloudflareAPI/Lobby
[gitter-img]: https://badges.gitter.im/Join%20Chat.svg

[license-url]: https://github.com/RoboFlax/CloudflareAPI/blob/master/LICENSE
[license-img]: https://img.shields.io/badge/license-Apache_2.0-blue.svg?style=flat
