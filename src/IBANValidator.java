import java.math.BigInteger;
import java.util.Locale;

public class IBANValidator {
    private final BigInteger MOD_NUMBER = new BigInteger("97");
    private final int MIN_IBAN_LENGTH = 15;
    private final int MAX_IBAN_LENGTH = 34;

    private final String formatingOutput = "%19.19s\n";
    private final String formatingOutput2 = "%19.19s%s\n";

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

        for (String cCode : Locale.getISOCountries()){ // Slow point
            if (cCode.equals(countryCode)) {
                //System.out.println(IBAN);
                System.out.format(formatingOutput, "Country code: OK   ");
                //System.out.println("-Country code: OK");
                return true;
            }
        }

        System.out.format(formatingOutput, "Country code: WRONG");
        //System.out.println("-Country code: WRONG");
        return false;
    }

    //Checking that the total IBAN length is correct as per the country. If not, the IBAN is invalid
    private boolean checkIBANLength(String IBAN) {
        if (IBAN.length() < MIN_IBAN_LENGTH || IBAN.length() > MAX_IBAN_LENGTH){
            System.out.println("Length: WRONG (detected:"+IBAN.length()+", should be: [15-34])");
            return false;
        }
        //System.out.println("-Length: OK   ");
        System.out.format(formatingOutput, "Length: OK   ");
        return true;
    }

    // Interpret the string as a decimal integer and
    // compute the remainder of that number on division by 97
    private boolean computeRemainder(BigInteger number) {
        if (number.mod(MOD_NUMBER).intValue() == 1) {
            //System.out.println("-Remainder: OK   ");
            System.out.format(formatingOutput, "Remainder: OK   ");
            return true;
        }
        else {
            //System.out.println("-Remainder: WRONG");
            System.out.format(formatingOutput, "Remainder: WRONG");
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
                    System.out.format(formatingOutput2, "CONVERSION: WRONG", " (code contains character, which is not number/letter)");
                    //System.out.println("Error, code contains character, which is not number/letter");
                    return new BigInteger("0");
                }
            }
        }

        BigInteger num = new BigInteger("0");
        boolean error = false;

        try {
            num = new BigInteger(convertedIBAN.toString());
        } catch (NumberFormatException e){ // Shouldn't be the case anymore ...
            System.out.println("Conversion error, can't convert: " + convertedIBAN.toString());
            error = true;
        }

        if (!error){
            System.out.format(formatingOutput, "Conversion: OK   ");
            //System.out.println("-Conversion: OK   ");
        }

        else {
            System.out.format(formatingOutput, "Conversion: WRONG");
            //System.out.println("-Conversion: WRONG");
        }

        return num;
    }

    // Moving the four initial characters to the end of the string
    private String rearrange(String IBAN) {
        return IBAN.substring(4) + IBAN.substring(0, 4);
    }
}