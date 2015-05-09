package com.example.AlgorithmInformation;

public interface MD5_InfoText {
	static String source = "http://en.wikipedia.org/wiki/MD5";
	
	static String file = "<!DOCTYPE html> "
			+ "<html> "
			+ "<body> "
			+ "<p><font size=\"4\" face=\"Arial\">"
			+ "The MD5 message-digest algorithm is a widely used cryptographic hash "
			+ "function producing a 128-bit (16-byte) hash value, typically expressed in "
			+ "text format as a 32 digit hexadecimal number. MD5 has been utilized in a wide "
			+ "variety of cryptographic applications, and is also commonly used to "
			+ "verify data integrity. MD5 was designed by Ronald Rivest in 1991 to "
			+ "replace an earlier hash function, MD4.The source code in RFC 1321 "
			+ "contains a \"by attribution\" RSA license. In 1996 a flaw was found "
			+ "in the design of MD5. While it was not deemed a fatal weakness at the time, "
			+ "cryptographers began recommending the use of other algorithms, such as SHA-1—which "
			+ "has since been found to be vulnerable as well. In 2004 it was shown "
			+ "that MD5 is not collision resistant. As such, MD5 is not suitable for "
			+ "applications like SSL certificates or digital signatures that rely on this "
			+ "property for digital security. Also in 2004 more serious flaws were discovered in MD5, "
			+ "making further use of the algorithm for security purposes questionable; specifically, "
			+ "a group of researchers described how to create a pair of files that "
			+ "share the same MD5 checksum. Further advances were made in breaking MD5 in 2005, 2006, and 2007. "
			+ "In December 2008, a group of researchers used this technique to fake SSL certificate "
			+ "validity, and CMU Software Engineering Institute now says that MD5 \"should be "
			+ "considered cryptographically broken and unsuitable for further use\", and "
			+ "most U.S. government applications now require the SHA-2 family of hash functions. "
			+ "In 2012, the Flame malware exploited the weaknesses in MD5 to fake a Microsoft digital signature. "
			+ "</font></p> </body> </html>";
}
