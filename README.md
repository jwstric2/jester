Jester [![Build Status](https://travis-ci.org/jscep/jester.png?branch=master)](https://travis-ci.org/jscep/jester)
======

Java Implementation of [Enrollment over Secure Transport](http://www.rfc-editor.org/rfc/rfc7030.txt).

Requirements
------------

* Java 7

Running Jester
--------------

You can start the Jester server like so:

```bash
mvn clean install
cd jester-sample-war
mvn jetty:run
```

You should now have a functioning EST server at: [http://localhost:8080/.well-known/est/](http://localhost:8080/.well-known/est/) and should be able to download the CA store at [http://localhost:8080/.well-known/est/cacerts](http://localhost:8080/.well-known/est/cacerts).

Related Documents
-----------------

  - [Certificate Management over CMS (CMC)](http://tools.ietf.org/html/rfc5272)
  - [Certificate Management over CMS (CMC): Transport Protocols](http://tools.ietf.org/html/rfc5273)
  - [Certificate Management Messages over CMS (CMC): Compliance Requirements](http://tools.ietf.org/html/rfc5274)
  - [The Transport Layer Security (TLS) Protocol Version 1.1](http://tools.ietf.org/html/rfc4346)
  - [Hypertext Transfer Protocol -- HTTP/1.1](http://tools.ietf.org/html/rfc2616)
  - [Internet X.509 Public Key Infrastructure Certificate Management Protocol (CMP)](http://tools.ietf.org/html/rfc4210)
  - [HTTP Over TLS](http://tools.ietf.org/html/rfc2818)
  - [PKCS #10: Certification Request Syntax Version 1.5](http://tools.ietf.org/html/rfc2314)
  - [Using the Secure Remote Password (SRP) Protocol for TLS Authentication](http://tools.ietf.org/html/rfc5054)
  - [Uniform Resource Identifier (URI): Generic Syntax](http://tools.ietf.org/html/rfc3986)
  - [Suite B Profile of Certificate Management over CMS](http://tools.ietf.org/html/rfc6403)
  - [HTTP Authentication: Basic and Digest Access Authentication](http://tools.ietf.org/html/rfc2617)
  - [The application/pkcs10 Media Type](http://tools.ietf.org/html/rfc5967)

Out of Scope
------------

  - §3.3.3 - Certificate-less TLS Mutual Authentication (No RFC 5054 in JSSE)
  - §3.5 - Linking Identity and PoP information (No RFC 5929 in JSSE)
