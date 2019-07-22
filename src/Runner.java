import java.io.*;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        // We expecting file name to be provided otherwise we deal with user using command line

        if(args.length > 0) {
            try {
                dealWithFile(args[0]);
            }
            catch (FileNotFoundException e){
                System.out.println("File with IBAN numbers not found.");
            }
            catch (IOException e) {
                System.out.println("ERROR, while writing to the results file.");
            }
        }

        else {
            dealWithUser();
        }
    }

    private static void dealWithFile(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        IBANValidator IBANValidator = new IBANValidator();
        String EOL = System.getProperty("line.separator"); // Should work on all OS

        String outputFileName = getOutputFileName(file.getName());
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));

        while (sc.hasNextLine()){
            String IBAN = sc.nextLine().trim();
            boolean result = IBANValidator.checkIBAN(IBAN);

            System.out.println(IBAN);
            System.out.println(result);

            writer.write(IBAN+";"+ result+EOL);
        }

        writer.close();
    }

    private static String getOutputFileName(String fileName) {
        // Getting file name without extension:
        int index = fileName.lastIndexOf(".");
        String currentFileNameWithoutExtension = fileName.substring(0,index);

        // Adding .out extension to the file:
        String stringBuilder = currentFileNameWithoutExtension +
                ".out";

        return stringBuilder;
    }

    private static void dealWithUser() {
        Scanner sc = new Scanner(System.in);
        IBANValidator IBANValidator = new IBANValidator();

        System.out.println("Enter IBAN number and press enter: ");

        if (sc.hasNext()){
            String IBAN = sc.next().trim(); //String IBAN = "GB82WEST12345698765432";
            //System.out.println(IBANValidator.checkIBAN(IBAN));

            if (IBANValidator.checkIBAN(IBAN)){
                System.out.println("IBAN is correct");
            }
            else {
                System.out.println("IBAN is incorrect");
            }
        }

        sc.close();
    }
}
