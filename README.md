Jester [![Build Status](https://travis-ci.org/jscep/jester.png?branch=master)](https://travis-ci.org/jscep/jester)
======

Jester is an open source Java implementation of [Enrollment over Secure Transport](http://tools.ietf.org/html/rfc7030) (RFC 7030).

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

You should now have a functioning EST server at: [https://localhost:8443/.well-known/est/](http://localhost:8080/.well-known/est/).

Using OpenSSL
-------------

You should be able to download the CA store at [https://localhost:8443/.well-known/est/cacerts](http://localhost:8080/.well-known/est/cacerts) and parse it with OpenSSL, like so:

```bash
curl --insecure --silent https://localhost:8443/.well-known/est/cacerts \
  | base64 --decode \
  | openssl pkcs7 -inform DER -print_certs -text -noout
```

The above command will typically produce the following output:

```
Certificate:
    Data:
        Version: 3 (0x2)
        Serial Number: 331108101 (0x13bc4f05)
        Signature Algorithm: dsaWithSHA1
        Issuer: C=Unknown, ST=Unknown, L=Unknown, O=Unknown, OU=Unknown, CN=Jester
        Validity
            Not Before: Mar  5 16:32:34 2013 GMT
            Not After : Mar  3 16:32:34 2023 GMT
        Subject: C=Unknown, ST=Unknown, L=Unknown, O=Unknown, OU=Unknown, CN=Jester
        Subject Public Key Info:
            Public Key Algorithm: dsaEncryption
            DSA Public Key:
                pub: 
                    7f:cd:b9:39:eb:da:75:f8:c3:82:58:ea:64:89:3a:
                    f0:d2:ed:ec:89:87:c3:02:a3:2d:7c:79:5d:bc:e2:
                    2d:53:b5:cc:08:19:f1:39:f9:73:ad:b9:59:c9:7b:
                    24:59:3e:69:9e:91:ef:0f:1b:bb:b9:99:db:6b:fe:
                    fe:ba:53:ca:82:24:b6:4a:68:06:cb:47:39:98:72:
                    cb:cb:02:8f:98:ec:f6:fb:55:b6:8f:5b:b4:d8:a9:
                    c0:a8:dc:c4:a4:a2:2e:8e:91:3d:b1:e8:26:03:4e:
                    f4:42:27:11:8d:ac:b1:99:c7:6b:e2:6a:34:5b:59:
                    33:a2:f9:8f:52:f2:93:8b
                P:   
                    00:fd:7f:53:81:1d:75:12:29:52:df:4a:9c:2e:ec:
                    e4:e7:f6:11:b7:52:3c:ef:44:00:c3:1e:3f:80:b6:
                    51:26:69:45:5d:40:22:51:fb:59:3d:8d:58:fa:bf:
                    c5:f5:ba:30:f6:cb:9b:55:6c:d7:81:3b:80:1d:34:
                    6f:f2:66:60:b7:6b:99:50:a5:a4:9f:9f:e8:04:7b:
                    10:22:c2:4f:bb:a9:d7:fe:b7:c6:1b:f8:3b:57:e7:
                    c6:a8:a6:15:0f:04:fb:83:f6:d3:c5:1e:c3:02:35:
                    54:13:5a:16:91:32:f6:75:f3:ae:2b:61:d7:2a:ef:
                    f2:22:03:19:9d:d1:48:01:c7
                Q:   
                    00:97:60:50:8f:15:23:0b:cc:b2:92:b9:82:a2:eb:
                    84:0b:f0:58:1c:f5
                G:   
                    00:f7:e1:a0:85:d6:9b:3d:de:cb:bc:ab:5c:36:b8:
                    57:b9:79:94:af:bb:fa:3a:ea:82:f9:57:4c:0b:3d:
                    07:82:67:51:59:57:8e:ba:d4:59:4f:e6:71:07:10:
                    81:80:b4:49:16:71:23:e8:4c:28:16:13:b7:cf:09:
                    32:8c:c8:a6:e1:3c:16:7a:8b:54:7c:8d:28:e0:a3:
                    ae:1e:2b:b3:a6:75:91:6e:a3:7f:0b:fa:21:35:62:
                    f1:fb:62:7a:01:24:3b:cc:a4:f1:be:a8:51:90:89:
                    a8:83:df:e1:5a:e5:9f:06:92:8b:66:5e:80:7b:55:
                    25:64:01:4c:3b:fe:cf:49:2a
        X509v3 extensions:
            X509v3 Subject Key Identifier: 
                1E:F6:81:50:E6:1E:C4:09:D7:B6:0B:DD:8A:CD:0A:0F:6B:FF:8A:89
    Signature Algorithm: dsaWithSHA1
        30:2c:02:14:5e:0c:b7:32:f1:9d:00:96:02:c0:ad:3f:67:cb:
        1e:84:6c:19:e9:ae:02:14:3a:01:2e:74:f3:ee:16:ac:85:84:
        58:e1:3b:5c:89:5e:d5:79:fa:b3
```

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
