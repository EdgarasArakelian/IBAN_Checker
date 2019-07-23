import java.math.BigInteger;
import java.util.Locale;

public class IBANValidator {
    private final BigInteger MOD_NUMBER = new BigInteger("97");
    private final int MIN_IBAN_LENGTH = 15;
    private final int MAX_IBAN_LENGTH = 34;
    private final String formattingOutput = "%14.14s%s\n";

    // Checks if provided IBAN is valid ...
    boolean checkIBAN(String IBAN){
        if (checkIBANLength(IBAN) && countryCodeCheck(IBAN)) { // Missing length per country
            IBAN = rearrange(IBAN);
            BigInteger newIBAN = convertToInteger(IBAN);

            if (newIBAN.intValue() != 0)
                return computeRemainder(newIBAN);
        }

        return false;
    }

    // Checks first 2 letters for country code.
    private boolean countryCodeCheck(String IBAN) {
        String countryCode = IBAN.substring(0,2);

        for (String cCode : Locale.getISOCountries()){
            if (cCode.equals(countryCode)) {
                System.out.format(formattingOutput, "Country code:", " OK");

                return true;
            }
        }

        System.out.format(formattingOutput, "Country code:", " WRONG");
        return false;
    }

    //Checking that the total IBAN length is correct as per the country. If not, the IBAN is invalid
    private boolean checkIBANLength(String IBAN) {
        if (IBAN.length() < MIN_IBAN_LENGTH || IBAN.length() > MAX_IBAN_LENGTH){
            System.out.format(formattingOutput, "Length:", " WRONG (detected:\"+IBAN.length()+\", should be: [15-34])");
            return false;
        }

        System.out.format(formattingOutput, "Length:", " OK");
        return true;
    }

    // Interpret the string as a decimal integer and
    // compute the remainder of that number on division by 97
    private boolean computeRemainder(BigInteger number) {
        if (number.mod(MOD_NUMBER).intValue() == 1) {
            System.out.format(formattingOutput, "Remainder:", " OK");

            return true;
        }
        else {
            System.out.format(formattingOutput, "Remainder:", " WRONG");

            return false;
        }
    }

    //Replacing each letter in the string with two digits,
    // thereby expanding the string, where A = 10, B = 11, ..., Z = 35
    private BigInteger convertToInteger(String IBAN) {
        char array[] = IBAN.toCharArray();
        StringBuilder convertedIBAN = new StringBuilder();

        for (char character: array) {
            if (Character.isLetter(character)){
                convertedIBAN.append(Character.getNumericValue(character));
            }
            else {
                if (Character.isDigit(character)) {
                    convertedIBAN.append(character);
                }
                else {
                    System.out.format(formattingOutput, "CONVERSION:", " WRONG (code contains character, which is not number/letter)");
                    return new BigInteger("0"); // BigInteger == 0 is my error value
                }
            }
        }


        BigInteger num = new BigInteger(convertedIBAN.toString());
        System.out.format(formattingOutput, "Conversion:", " OK");

        return num;
    }

    // Moving the four initial characters to the end of the string
    private String rearrange(String IBAN) {
        return IBAN.substring(4) + IBAN.substring(0, 4);
    }
}