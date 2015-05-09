## Description

This project implements **The Message-Digest Algorithm (MD5)**, **Rivest Cipher (RC4)** and **Pretty Good Policy (PGP - RFC 4880)** encryption algorithms. The purpose of the tool is to *let the user learn these algorithms in theory and in action*.

MD5 algorithm code reference: http://www.freevbcode.com/ShowCode.asp?ID=741

PGP algorithm code reference: http://sloanseaman.com/wordpress/2012/05/13/revisited-pgp-encryptiondecryption-in-java

###  Execution

Run the *EncryptionEducationMain* file which is the start point of an application. Select one algorithm from a dropdown and click continue for detail information.

###  The Message-Digest Algorithm (MD5)

MD5 algorithm hashes the plain text entered by user. It has total 64 rounds after which it returns hexadecimal hash for plain text. For detail information: http://en.wikipedia.org/wiki/MD5

###  Rivest Cipher (RC4)

RC4 is an symmetric key encryption algorithm. It uses user supplied Private Key to encypt the plain text and use the same key for decryption. For detail information: http://en.wikipedia.org/wiki/RC4

###  Pretty Good Policy (PGP)

PGP (RFC 4880) is a hybrid cryptosystem. It uses combination of private and public keys for encryption and decryption. It encrypts the message using public key of recever. Receiver decrypts the message using his private key. For detail information: http://www.pgpi.org/doc/pgpintro/

####  Requirements for PGP

* Create public and private keys for PGP from https://www.igolder.com/pgp/generate-key. Save the keys in your computer as you need to pass those to program for encryption and decryption.

* Download this libraries for PGP algorithms:

  - http://www.bouncycastle.org/download/bcprov-jdk15on-147.jar

  - http://www.bouncycastle.org/download/bcpg-jdk15on-152.jar

  - http://apache.cs.utah.edu//commons/io/binaries/commons-io-2.4-bin.zip

#####  JCE Update

The JRE by default ships with security for cryptography called Java Cryptography Extension (JCE). The JCE will support PGP but because import/export of cryptography can be sketchy, it only supports weak keys by default (think keys that wouldn’t be too hard to crack). PGP won’t work under this environment so you need to first address this.

Go to the Java download page and go all the way to the bottom and download the Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files. The number at the end (currently 6 or 7) is just the JRE version.

Extract the files and copy the two jars to your JRE’s /lib/security directory. This will override two existing jars and will allow you to use strong keys in security.
