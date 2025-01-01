import java.util.Scanner;

class Functions {
	public static char flip(char i) {
		return i == '0' ? '1' : '0';
	}

	public static String convertor(int number) {
		String binary = "";
		if (number == 0) {
			binary = "0";
		}
		while (number > 0) {
			int rem = number % 2;
			binary = rem + binary;
			number /= 2;
		}

		return binary;
	}

	public static String complements(String binary) {
		String ones = "", twos = "";
		int i;
		for (i = 0; i < binary.length(); i++)
			ones += flip(binary.charAt(i));
		twos = ones;
		for (i = binary.length() - 1; i >= 0; i--) {
			if (ones.charAt(i) == '1') {
				twos = twos.substring(0, i) + '0' + twos.substring(i + 1);
			} else {
				twos = twos.substring(0, i) + '1' + twos.substring(i + 1);
				break;
			}
		}
		if (i == -1) {
			twos = '1' + twos;
		}
		return twos;
	}

	public static int reverseConvertor(String binary) {
		int number = 0;
		for (int i = 0; i < binary.length(); i++) {
			int base = binary.charAt(i) - '0';
			number = base * (int) Math.pow(2, binary.length() - i - 1) + number;
		}
		return number;
	}
}

class AnythingToBinary extends Functions {
	public static void integerToBinary() {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter your Interger : ");
		int number = s.nextInt();
		String binary = "";
		int sign = 0;
		if (number < 0) {
			sign = 1;
			number *= -1;
		}
		binary = convertor(number);
		if (sign == 1) {
			binary = complements(binary);
		}

		System.out.println(number + " in binary form is " + binary);
	}

	public static void floatToBinary() {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter your float number: ");
		float number = s.nextFloat();
		int bits = Float.floatToIntBits(number);
		String binary = Integer.toBinaryString(bits);
		binary = String.format("%32s", binary).replace(' ', '0');
		System.out.println("IEEE 754 binary representation of " + number + " is: " + binary);
	}

	public static void stringToBinary() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter your String : ");
		String text = s.nextLine();
		String binary = "";
		for (int i = 0; i < text.length(); i++) {
			int number = text.charAt(i);
			binary = binary + convertor(number) + " ";
		}
		System.out.println(text + " in binary form is " + binary);

	}
}

class BinaryToAnything extends Functions {
	public static void binaryToInteger() {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter your Interger (keep negative sign in front if it is negative number) : ");
		int binary = s.nextInt();
		int number = 0;
		int sign = 0;
		if (binary < 0) {
			sign = 1;
			binary *= -1;
		}
		String binaryStr = String.valueOf(binary);
		if (sign == 1) {
			String numberStr = complements(binaryStr);
			number = reverseConvertor(numberStr);
			number *= -1;
		} else {
			number = reverseConvertor(binaryStr);
		}

		System.out.println(binary + " in the integer is " + number);

	}

	public static void binaryToFloat() {
		Scanner s = new Scanner(System.in);
		String binaryString = s.nextLine();
		int intBits = (int) Long.parseLong(binaryString, 2);

		float floatValue = Float.intBitsToFloat(intBits);

		System.out.println("The float value is: " + floatValue);
	}

	public static void binaryToString() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter your binary String (seperate each char (binary set) by a space) : ");
		String binary = s.nextLine();
		String[] binarySet = binary.split(" ");
		String text = "";
		for (String binaryText : binarySet) {
			int asciiValue = reverseConvertor(binaryText);
			text += new Character((char) asciiValue).toString();
		}
		System.out.println(binary + " as the string is " + text);

	}
}

public class ConvertorIntoBinaryAndViceVersa {
	public static void toBinary() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter\n1---->To convert Integer\n2---->To convert float\n3---->To convert String ");
		int choice = s.nextInt();
		switch (choice) {
			case 1:
				AnythingToBinary.integerToBinary();
				break;
			case 2:
				AnythingToBinary.floatToBinary();
				break;
			case 3:
				AnythingToBinary.stringToBinary();
				break;
		}
	}

	public static void binaryTo() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter\n1---->To get in Integer\n2---->To get in float\n3---->To get in String ");
		int choice = s.nextInt();
		switch (choice) {
			case 1:
				BinaryToAnything.binaryToInteger();
				break;
			case 2:
				BinaryToAnything.binaryToFloat();
				break;
			case 3:
				BinaryToAnything.binaryToString();
				break;
		}

	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int primaryChoice = 0;
		while (primaryChoice != 3) {
			System.out.println("Enter\n1:To convert anything to binary\n2:To convert binary to anything\n3:To Exit");
			primaryChoice = s.nextInt();
			if (primaryChoice == 1)
				toBinary();
			else if (primaryChoice == 2)
				binaryTo();
			else if (primaryChoice == 3)
				System.out.println("Exiting successfully");
			else
				System.out.println("Wrong input");

		}
	}

}
