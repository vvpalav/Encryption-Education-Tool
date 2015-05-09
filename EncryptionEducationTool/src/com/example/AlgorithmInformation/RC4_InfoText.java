package com.example.AlgorithmInformation;

public interface RC4_InfoText {
	static String source = "http://en.wikipedia.org/wiki/RC4";
	
	static String file = "<!DOCTYPE html> "
			+ "<html> "
			+ "<body> "
			+ "<p><font size=\"4\" face=\"Arial\">"
			+ "In cryptography, RC4 (Rivest Cipher 4 also known as ARC4 or ARCFOUR "
			+ "meaning Alleged RC4) is the most widely used "
			+ "software stream cipher and is used in popular Internet protocols such "
			+ "as Transport Layer Security (TLS). While remarkable for its simplicity "
			+ "and speed in software, RC4 has weaknesses that argue against its use in "
			+ "new systems. It is especially vulnerable when the beginning of the output "
			+ "keystream is not discarded, or when nonrandom or related keys are used; "
			+ "some ways of using RC4 can lead to very insecure protocols such as WEP. "
			+ "As of 2015, there is speculation that some state cryptologic agencies may "
			+ "possess the capability to break RC4 even when used in the TLS protocol. "
			+ "Mozilla and Microsoft recommend disabling RC4 where possible. "
			+ "RFC 7465 prohibits the use of RC4 in TLS.</font></p>"
			+ "<font size=\"4\" face=\"Arial\">"
			+ "<p>RC4 generates a pseudorandom stream of bits (a keystream). As with any stream cipher, "
			+ "these can be used for encryption by combining it with the plaintext using bit-wise "
			+ "exclusive-or; decryption is performed the same way (since exclusive-or with given "
			+ "data is an involution). (This is similar to the Vernam cipher except that generated "
			+ "pseudorandom bits, rather than a prepared stream, are used.) To generate the keystream, "
			+ "the cipher makes use of a secret internal state which consists of two parts: </p>"
			+ "<ul><li>A permutation of all 256 possible bytes (denoted \"S\" below).</li> "
			+ "<li>Two 8-bit index-pointers (denoted \"i\" and \"j\"). </li></ul>"
			+ "<p>The permutation is initialized with a variable length key, "
			+ "typically between 40 and 256 bits, using the key-scheduling algorithm (KSA). "
			+ "Once this has been completed, the stream of bits is generated using the pseudo-random "
			+ "generation algorithm (PRGA)."
			+ "</p></font>"
			+ "</body> </html>";
}
