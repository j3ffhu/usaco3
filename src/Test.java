
public class Test {

	public static void main(String[] args) {
		String data = "Confucius say to ada: Madam, I'm Ada;m hdjfh fahjfa ";
		//String data = "Madam, I'm Ada;";
		findPalInStr(data);
	}

	/**
	 * Determine if an string is palindrome, case insensitive and don't pay attention to non-letter characters
	 */
	public static boolean isPal(String data) {
		int len = data.length();
		int middle = len / 2;
		int startCursor = 0;
		int endCursor = len - 1;
		while (startCursor < middle && endCursor > middle) {
			if (!Character.isLetter(data.charAt(startCursor))) {
				startCursor++;
				continue;
			}
			if (!Character.isLetter(data.charAt(endCursor))) {
				endCursor--;
				continue;
			}
			if (Character.toLowerCase(data.charAt(startCursor)) != Character.toLowerCase(data.charAt(endCursor)))
				return false;
			startCursor++;
			endCursor--;
		}
		return true;
	}

	public static boolean isPalindrome(char[] data, int start, int end) {
		int len = (end - start) + 1;
		for (int cursor = start; cursor < start + (len / 2); cursor++) {
			if (data[cursor] != data[end - cursor + start])
				return false;
		}
		return true;
	}

	public static String findPalInStr(String data) {
		char[] letters = new char[data.length()];
		int len = extractLetters(data, letters);
		int max = 8;
		int bestStart = 0, bestEnd = 0, bestLen = 0;
		for (int start = 0; start < len; start++) {
			int end = len - 1;
			int palLen = 0;
			while (end > start && (palLen = (end - start) + 1)> bestLen) {
				if (letters[start] == letters[end]) {					
					if (isPalindrome(letters, start, end)) {
						bestStart = start;
						bestEnd = end;
						bestLen = palLen;
						break;
					}
				}
				end--;
			}
		}

		// System.out.print(letters[i]);
		// System.out.println();
		// for (int i = len - 1; i >= 0; i--)
		// System.out.print(letters[i]);
		for (int i = bestStart; i <= bestEnd; i++) {
			System.out.print(letters[i]);
		}
		return null;
	}

	public static int extractLetters(String data, char[] buffer) {
		int i = 0;
		for (char c : data.toCharArray())
			if (Character.isLetter(c))
				buffer[i++] = Character.toLowerCase(c);
		return i;
	}

	public static boolean isPal2(String data) {
		char[] letters = new char[data.length()];
		int i = extractLetters(data, letters);
		for (int cursor = 0; cursor < i / 2; cursor++) {
			if (letters[cursor] != letters[i - 1 - cursor])
				return false;
		}
		return true;
	}

	public static boolean isLetter(char c) {
		// determined using ASCII characters table
		return (c > 64 && c < 91) || (c > 96 && c < 123);
	}
}
