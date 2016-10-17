package dw317.lib.creditcard;

public class MasterCardTest {

	public static void main(String[] args) {

		// This should return false
		System.out.println(checkTwoFirstNumbers("20"));
		// This should return true
		System.out.println(checkTwoFirstNumbers("50"));
		// This should return the number of the credit card
		System.out.println(validateNumber("5000000000000000"));
		// This should throw an IllegalArgumentException
		// System.out.println(validateNumber("50"));
		// This should throw an IllegalArgumentException
		// System.out.println(validateNumber("2020202020"));
		// This should throw an IllegalArgumentException
		// System.out.println(validateNumber("1000000000000000"));
	}

	private static boolean checkTwoFirstNumbers(String number) {
		String checkNum = number.substring(0, 2);
		if (checkNum.equals("50") || checkNum.equals("51") || checkNum.equals("52") || checkNum.equals("53")
				|| checkNum.equals("54") || checkNum.equals("55"))
			return true;
		return false;
	}

	private static String validateNumber(String number) throws IllegalArgumentException {
		if (number.length() == 16 && checkTwoFirstNumbers(number))
			return number;
		throw new IllegalArgumentException();
	}
}