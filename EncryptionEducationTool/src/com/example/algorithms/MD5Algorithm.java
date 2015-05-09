package com.example.algorithms;

import java.util.LinkedList;
import java.util.List;

import com.example.AlgorithmInformation.MD5_InfoText;

public class MD5Algorithm extends Algorithm {

	private static long S11 = 7L;
	private static long S12 = 12L;
	private static long S13 = 17L;
	private static long S14 = 22L;
	private static long S21 = 5L;
	private static long S22 = 9L;
	private static long S23 = 14L;
	private static long S24 = 20L;
	private static long S31 = 4L;
	private static long S32 = 11L;
	private static long S33 = 16L;
	private static long S34 = 23L;
	private static long S41 = 6L;
	private static long S42 = 10L;
	private static long S43 = 15L;
	private static long S44 = 21L;

	private static char pad[] = { 128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0 };

	private char bytBuffer[] = new char[64];
	private static long lngState[] = new long[4];
	private long lngByteCount = 0;
	private static List<String> output;

	public MD5Algorithm() {
		super("The Message Digest Algorithm - MD5", MD5_InfoText.file,
				MD5_InfoText.source);
		this.init();
	}

	@Override
	public void executeAlgorithm(AlgorithmDataHolder input) {
		init();
		output.add(input.getInput());
		char chrTestData[] = input.getInput().toCharArray();
		//System.out.println(chrTestData);
		//System.out.println("Data length: " + chrTestData.length);
		update(chrTestData, chrTestData.length);
		md5final();
		output.add("Final Output Hash in Hex for entire input: \n\n[ "
				+ input.getInput() + " ]\n\n" + toHexString().toString());
		input.setOutput(output);
	}

	private static long[] decode(char bytBlock[]) {
		long lngBlock[] = new long[16];
		int j = 0;
		for (int i = 0; i < bytBlock.length; i += 4) {
			lngBlock[j++] = bytBlock[i] + bytBlock[i + 1] * 256L
					+ bytBlock[i + 2] * 65536L + bytBlock[i + 3] * 16777216L;
		}
		return (lngBlock);
	}

	private static void transform(long lngState[], char bytBlock[]) {
		long lngA = lngState[0];
		long lngB = lngState[1];
		long lngC = lngState[2];
		long lngD = lngState[3];
		long x[] = new long[16];

		output.add("Processing block: " + new String(bytBlock));
		x = decode(bytBlock);
		/* Round 1 */
		lngA = ff(lngA, lngB, lngC, lngD, x[0], S11, 0xd76aa478L); /* 1 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngD = ff(lngD, lngA, lngB, lngC, x[1], S12, 0xe8c7b756L); /* 2 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngC = ff(lngC, lngD, lngA, lngB, x[2], S13, 0x242070dbL); /* 3 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngB = ff(lngB, lngC, lngD, lngA, x[3], S14, 0xc1bdceeeL); /* 4 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngA = ff(lngA, lngB, lngC, lngD, x[4], S11, 0xf57c0fafL); /* 5 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngD = ff(lngD, lngA, lngB, lngC, x[5], S12, 0x4787c62aL); /* 6 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngC = ff(lngC, lngD, lngA, lngB, x[6], S13, 0xa8304613L); /* 7 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngB = ff(lngB, lngC, lngD, lngA, x[7], S14, 0xfd469501L); /* 8 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngA = ff(lngA, lngB, lngC, lngD, x[8], S11, 0x698098d8L); /* 9 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngD = ff(lngD, lngA, lngB, lngC, x[9], S12, 0x8b44f7afL); /* 10 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngC = ff(lngC, lngD, lngA, lngB, x[10], S13, 0xffff5bb1L); /* 11 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngB = ff(lngB, lngC, lngD, lngA, x[11], S14, 0x895cd7beL); /* 12 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngA = ff(lngA, lngB, lngC, lngD, x[12], S11, 0x6b901122L); /* 13 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngD = ff(lngD, lngA, lngB, lngC, x[13], S12, 0xfd987193L); /* 14 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngC = ff(lngC, lngD, lngA, lngB, x[14], S13, 0xa679438eL); /* 15 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngB = ff(lngB, lngC, lngD, lngA, x[15], S14, 0x49b40821L); /* 16 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));

		/* Round 2 */
		lngA = gg(lngA, lngB, lngC, lngD, x[1], S21, 0xf61e2562L); /* 17 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngD = gg(lngD, lngA, lngB, lngC, x[6], S22, 0xc040b340L); /* 18 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngC = gg(lngC, lngD, lngA, lngB, x[11], S23, 0x265e5a51L); /* 19 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngB = gg(lngB, lngC, lngD, lngA, x[0], S24, 0xe9b6c7aaL); /* 20 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngA = gg(lngA, lngB, lngC, lngD, x[5], S21, 0xd62f105dL); /* 21 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngD = gg(lngD, lngA, lngB, lngC, x[10], S22, 0x2441453L); /* 22 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngC = gg(lngC, lngD, lngA, lngB, x[15], S23, 0xd8a1e681L); /* 23 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngB = gg(lngB, lngC, lngD, lngA, x[4], S24, 0xe7d3fbc8L); /* 24 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngA = gg(lngA, lngB, lngC, lngD, x[9], S21, 0x21e1cde6L); /* 25 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngD = gg(lngD, lngA, lngB, lngC, x[14], S22, 0xc33707d6L); /* 26 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngC = gg(lngC, lngD, lngA, lngB, x[3], S23, 0xf4d50d87L); /* 27 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngB = gg(lngB, lngC, lngD, lngA, x[8], S24, 0x455a14edL); /* 28 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngA = gg(lngA, lngB, lngC, lngD, x[13], S21, 0xa9e3e905L); /* 29 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngD = gg(lngD, lngA, lngB, lngC, x[2], S22, 0xfcefa3f8L); /* 30 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngC = gg(lngC, lngD, lngA, lngB, x[7], S23, 0x676f02d9L); /* 31 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngB = gg(lngB, lngC, lngD, lngA, x[12], S24, 0x8d2a4c8aL); /* 32 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));

		/* Round 3 */
		lngA = hh(lngA, lngB, lngC, lngD, x[5], S31, 0xfffa3942L); /* 33 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngD = hh(lngD, lngA, lngB, lngC, x[8], S32, 0x8771f681L); /* 34 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngC = hh(lngC, lngD, lngA, lngB, x[11], S33, 0x6d9d6122L); /* 35 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngB = hh(lngB, lngC, lngD, lngA, x[14], S34, 0xfde5380cL); /* 36 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngA = hh(lngA, lngB, lngC, lngD, x[1], S31, 0xa4beea44L); /* 37 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngD = hh(lngD, lngA, lngB, lngC, x[4], S32, 0x4bdecfa9L); /* 38 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngC = hh(lngC, lngD, lngA, lngB, x[7], S33, 0xf6bb4b60L); /* 39 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngB = hh(lngB, lngC, lngD, lngA, x[10], S34, 0xbebfbc70L); /* 40 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngA = hh(lngA, lngB, lngC, lngD, x[13], S31, 0x289b7ec6L); /* 41 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngD = hh(lngD, lngA, lngB, lngC, x[0], S32, 0xeaa127faL); /* 42 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngC = hh(lngC, lngD, lngA, lngB, x[3], S33, 0xd4ef3085L); /* 43 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngB = hh(lngB, lngC, lngD, lngA, x[6], S34, 0x4881d05L); /* 44 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngA = hh(lngA, lngB, lngC, lngD, x[9], S31, 0xd9d4d039L); /* 45 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngD = hh(lngD, lngA, lngB, lngC, x[12], S32, 0xe6db99e5L); /* 46 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngC = hh(lngC, lngD, lngA, lngB, x[15], S33, 0x1fa27cf8L); /* 47 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngB = hh(lngB, lngC, lngD, lngA, x[2], S34, 0xc4ac5665L); /* 48 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));

		/* Round 4 */
		lngA = ii(lngA, lngB, lngC, lngD, x[0], S41, 0xf4292244L); /* 49 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngD = ii(lngD, lngA, lngB, lngC, x[7], S42, 0x432aff97L); /* 50 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngC = ii(lngC, lngD, lngA, lngB, x[14], S43, 0xab9423a7L); /* 51 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngB = ii(lngB, lngC, lngD, lngA, x[5], S44, 0xfc93a039L); /* 52 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngA = ii(lngA, lngB, lngC, lngD, x[12], S41, 0x655b59c3L); /* 53 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngD = ii(lngD, lngA, lngB, lngC, x[3], S42, 0x8f0ccc92L); /* 54 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngC = ii(lngC, lngD, lngA, lngB, x[10], S43, 0xffeff47dL); /* 55 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngB = ii(lngB, lngC, lngD, lngA, x[1], S44, 0x85845dd1L); /* 56 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngA = ii(lngA, lngB, lngC, lngD, x[8], S41, 0x6fa87e4fL); /* 57 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngD = ii(lngD, lngA, lngB, lngC, x[15], S42, 0xfe2ce6e0L); /* 58 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngC = ii(lngC, lngD, lngA, lngB, x[6], S43, 0xa3014314L); /* 59 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngB = ii(lngB, lngC, lngD, lngA, x[13], S44, 0x4e0811a1L); /* 60 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngA = ii(lngA, lngB, lngC, lngD, x[4], S41, 0xf7537e82L); /* 61 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngD = ii(lngD, lngA, lngB, lngC, x[11], S42, 0xbd3af235L); /* 62 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngC = ii(lngC, lngD, lngA, lngB, x[2], S43, 0x2ad7d2bbL); /* 63 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));
		lngB = ii(lngB, lngC, lngD, lngA, x[9], S44, 0xeb86d391L); /* 64 */
		output.add(calculateIntermediateResult(lngA, lngB, lngC, lngD));

		lngState[0] = (lngState[0] + lngA) & 0xFFFFFFFFL;
		lngState[1] = (lngState[1] + lngB) & 0xFFFFFFFFL;
		lngState[2] = (lngState[2] + lngC) & 0xFFFFFFFFL;
		lngState[3] = (lngState[3] + lngD) & 0xFFFFFFFFL;

		StringBuilder str = new StringBuilder(); 
		str.append("Processed Block[" + new String(bytBlock) + "]: \n\n");
		str.append("Hash output: " + toHexString().toString() + "\n\n");
		str.append("Binary output: \n\n");
		str.append(calculateIntermediateResult(lngState[0], lngState[1], lngState[2], lngState[3]));
		output.add(str.toString());
		
		/* clear senstive information */
		x = decode(pad);
	}

	public static String calculateIntermediateResult(long a, long b, long c,
			long d) {
		StringBuilder str = new StringBuilder();
		str.append("a: " + Long.toBinaryString(a) + "\n");
		str.append("b: " + Long.toBinaryString(b) + "\n");
		str.append("c: " + Long.toBinaryString(c) + "\n");
		str.append("d: " + Long.toBinaryString(d));
		return str.toString();
	}

	private static long ff(long lngA, long lngB, long lngC, long lngD,
			long lngX, long lngS, long lngAC) {
		lngA = (lngA + (lngB & lngC | (~lngB) & lngD) + lngX + lngAC) & 0xFFFFFFFFL;
		lngA = ((lngA << lngS) | (lngA >>> (32L - lngS))) & 0xFFFFFFFFL;
		lngA = (lngA + lngB) & 0xFFFFFFFFL;
		return (lngA);
	}

	private static long gg(long lngA, long lngB, long lngC, long lngD,
			long lngX, long lngS, long lngAC) {
		lngA = (lngA + (lngB & lngD | lngC & ~lngD) + lngX + lngAC) & 0xFFFFFFFFL;
		lngA = ((lngA << lngS) | (lngA >>> (32L - lngS))) & 0xFFFFFFFFL;
		lngA = (lngA + lngB) & 0xFFFFFFFFL;
		return (lngA);
	}

	private static long hh(long lngA, long lngB, long lngC, long lngD,
			long lngX, long lngS, long lngAC) {
		lngA = (lngA + (lngB ^ lngC ^ lngD) + lngX + lngAC) & 0xFFFFFFFFL;
		lngA = ((lngA << lngS) | (lngA >>> (32L - lngS))) & 0xFFFFFFFFL;
		lngA = (lngA + lngB) & 0xFFFFFFFFL;
		return (lngA);
	}

	private static long ii(long lngA, long lngB, long lngC, long lngD,
			long lngX, long lngS, long lngAC) {
		lngA = (lngA + (lngC ^ (lngB | ~lngD)) + lngX + lngAC) & 0xFFFFFFFFL;
		lngA = ((lngA << lngS) | (lngA >>> (32L - lngS))) & 0xFFFFFFFFL;
		lngA = (lngA + lngB) & 0xFFFFFFFFL;
		return (lngA);
	}

	private void update(char bytInput[], long lngLen) {
		int index = (int) (this.lngByteCount % 64);
		int i = 0;
		this.lngByteCount += lngLen;
		int partLen = 64 - index;

		if (lngLen >= partLen) {
			for (int j = 0; j < partLen; ++j) {
				this.bytBuffer[j + index] = bytInput[j];
			}
			transform(lngState, this.bytBuffer);

			for (i = partLen; i + 63 < lngLen; i += 64) {
				for (int j = 0; j < 64; ++j) {
					this.bytBuffer[j] = bytInput[j + i];
				}
				transform(lngState, this.bytBuffer);
			}
			index = 0;
		} else {
			i = 0;
		}

		for (int j = 0; j < lngLen - i; ++j) {
			this.bytBuffer[index + j] = bytInput[i + j];
		}

	}

	public void md5final() {
		char bytBits[] = new char[8];
		int index, padLen;
		long bits = this.lngByteCount * 8;

		bytBits[0] = (char) (bits & 0xffL);
		bytBits[1] = (char) ((bits >>> 8) & 0xffL);
		bytBits[2] = (char) ((bits >>> 16) & 0xffL);
		bytBits[3] = (char) ((bits >>> 24) & 0xffL);
		bytBits[4] = (char) ((bits >>> 32) & 0xffL);
		bytBits[5] = (char) ((bits >>> 40) & 0xffL);
		bytBits[6] = (char) ((bits >>> 48) & 0xffL);
		bytBits[7] = (char) ((bits >>> 56) & 0xffL);

		index = (int) this.lngByteCount % 64;
		if (index < 56) {
			padLen = 56 - index;
		} else {
			padLen = 120 - index;
		}
		update(pad, padLen);
		update(bytBits, 8);
	}

	private static StringBuffer toHexString() {
		long myByte = 0;
		StringBuffer mystring = new StringBuffer();
		for (int j = 0; j < 4; ++j) {
			for (int i = 0; i < 32; i += 8) {
				myByte = (lngState[j] >>> i) & 0xFFL;
				if (myByte < 16) {
					mystring.append("0" + Long.toHexString(myByte));
				} else {
					mystring.append(Long.toHexString(myByte));
				}
			}
		}
		return (mystring);
	}

	public void init() {
		output = new LinkedList<String>();
		this.lngByteCount = 0;
		lngState[0] = 0x67452301L;
		lngState[1] = 0xefcdab89L;
		lngState[2] = 0x98badcfeL;
		lngState[3] = 0x10325476L;
	}

}
