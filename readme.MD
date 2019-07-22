# IBAN number checker v.0.2

### Description
This is JAVA application, which checks if given IBAN number(s) are valid.
It reads IBAN numbers from the file or they can be provided by the user
using command line UI.

### Overview:
- "src" folder contains application code;
- "IBANNumbers.txt" file contains example input, which can be used to test this application;
- "readme.MD" file contains brief information about the application (current file);
- ".idea" folder used by IntelliJ IDEA and is not used for actual application.

    
### Usage
- Navigate too "src" folder;

- Compile:\
                &nbsp; ``javac Runner.java``
- Running:\
&nbsp; 1. Without arguments:   ``java Runner``\
&nbsp; 2. With file name as an argument:   ``java Runner <filename.txt>``\
&nbsp; 3. With full or relative path as an argument:   ``java Runner <pathToFile>``


Running this application without providing any arguments will launch
this application in command line. Using command line UI
users can enter single IBAN number they want to check.

Running this application with parameter “filename.txt” will 
generate result file “filename.out”. 
Application will expect one IBAN code on every line in 
input file. It will analyze those lines for compliance
to IBAN number format rules. Application will 
output every line from source file and indication if codes 
are correct IBAN codes (true) or incorrect (false). 
Parameter “filename.txt” could be either file name, 
relative path or full path.

**Note: Input file should be plain text file (single byte character file without any BOM header).**

#### Examples:

##### Linux:
``java Runner IBANNumber.txt``\
``java Runner ./input/IBANNumber.txt ``\
``java Runner /home/user/projectName/IBANNumber.txt``
##### Windows:
``java Runner IBANNumber.txt``\
``java Runner C:\Users\user\projectName\IBANNumber.txt``\
``java Runner ./inputFolder/inputFile.txt``



#### Tested on:
##### OS and JAVA version:
Windows 10 - 12.0.2;\
Linux(Ubuntu 18.04) - 11.0.2.

#### Should work on:
Any Windows, Linux or MAC operating system with JAVA versions 11 or newer.

### Authors:
Edgar Arakelian