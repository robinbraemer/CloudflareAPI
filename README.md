# Cloudflare: The Cloudflare - API/Library for Java

[![Build Status](https://travis-ci.org/RoboFlax/CloudflareAPI.svg?branch=master)](https://travis-ci.org/RoboFlax/CloudflareAPI)
[![](https://jitpack.io/v/RoboFlax/Cloudflare.svg)](https://jitpack.io/#RoboFlax/Cloudflare)
[![Join the chat at https://gitter.im/CloudflareAPI/Lobby](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/CloudflareAPI/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

This Cloudflare API/Library interacts with [Cloudflare's fast API v4](https://api.cloudflare.com/)
and allows you to access every single feature _(even if it isn't added yet)_ of Cloudflare's API faster and much easier!

## Latest release
To add a dependency on this Cloudflare-API/Library using Maven or Gradle use the following:

**Maven:**
```xml
<dependency>
  <groupId>com.github.roboflax</groupId>
  <artifactId>cloudflare</artifactId>
  <version>1.2.1</version>
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
  compile 'com.github.roboflax:cloudflare:1.1'
}

allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

## Features
- **able to access every corner of cloudflare's api**
- **implemented services which simplifies a cloudflare request (+async)**
- **object oriented representation of the cloudflare api** (60%)
- **it is an extremely flexible and stable api**

## Getting Started
First, create an entry-point to use this api.
```java
String CF_EMAIL = "your_cloudflare_email@example.com";
String CF_API_KEY = "your_cloudflare_api_key";

CloudflareAccess cfAccess = new CloudflareAccess(CF_EMAIL, CF_API_KEY);
```
From there you can use already implemented services.
```java
cfAccess.zoneService()...
cfAccess.userService()...
// and more services...
```

## Learn more about the Cloudflare - API/Library

- Our users' guide in the wiki, [Cloudflare - API/Library explained](https://github.com/RoboFlax/Cloudflare/wiki).

### Licensing
Licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.
