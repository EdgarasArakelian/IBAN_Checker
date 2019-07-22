# Sąskaitų numerių IBAN tikrinimas (IBAN number checker)

### Description
This is JAVA application, which checks if given IBAN numbers are valid.
It reads IBAN numbers from the file or they can be provided by the user
using command line UI.
    
### Usage
- Navigate too "src" folder

- Compile:\
                &nbsp; javac Runner.java
- Running:\
&nbsp; 1. Without arguments: "java Runner"\
&nbsp; 2. With arguments: "java Runner filename.txt"

Running this application without providing any arguments will launch
this application in command line. Using command line UI
users can enter IBAN numbers they want to check by themselves.


Running my application with file name(path), which contains
IBAN numbers will run my application and will create file called "results.txt"
if it wasn't created before. Results file will contain all IBAN numbers
and either they are correct(true) or incorrect(false).

### Authors:
Edgar Arakelian