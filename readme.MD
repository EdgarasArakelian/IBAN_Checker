# Sąskaitų numerių IBAN tikrinimas (IBAN number checker)

### Description
This is JAVA application to check if provided IBAN numbers are correct.
It can read IBAN numbers from the file and/or provided by the user using command line UI.
    
### Usage

- Compile:\
                &nbsp; javac Runner.java
- Running:\
&nbsp; 1. Without arguments: "java Runner"\
&nbsp; 2. With arguments: "java Runner filename.txt"

Running this application without providing file name will launch
this application with command line UI. Using command line UI
user can enter IBAN number he wants to check himself.


Running my application with file name, which contains
IBAN numbers will create file called "results.txt"
if it wasn't created before. Results file will contain information
about each IBAN either it is correct(true) or incorrect(false).

### Authors:
Edgar Arakelian