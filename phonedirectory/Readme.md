Search inside the phone directory
--------------------------
Included is a simple phone directory.  Each line represents a person and a phone number.
The format per line is as follows:
  First Name (no spaces)
  then a space
  then Middle Initial (one character) followed by a "."
  then a space
  then Last Name (no spaces)
  then "|"
  then Area Code (three digits)
  then "-"
  then rest of phone number (three digits followed by "-" then four more digits).

We want you to write a Java program that returns the number of times either a Name or Area code is found in the phone directory.
The user input is not specific, it can be area code, first name, last name, first and last name, or full name with middle initial.

Case should not matter so the search for "mel" should be the same as the search for "Mel" and "MEL".

When the user searches for an empty string (just presses Enter), the program should exit.

Below is an example of how the program should function:


----------------------------------------------------------------------
Enter Name/Area Code: 212
    Numer of people with area code (212): 43


Enter Name/Area Code: mel
    Number of people whose first name is mel: 124


Enter Name/Area Code: Mel
    Number of people whose first name is Mel: 124


Enter Name/Area Code: Brooks
    Number of people whose last name is Brooks: 203


Enter Name/Area Code: Mel Brooks
    Number of people whose name is Mel Brooks: 14


Enter Name/Area Code: Mel M. Brooks
    Number of people whose full name is Mel M. Brooks: 2


Enter Name/Area Code: Madison
    Number of people whose first name is Madison: 130
    Number of people whose last name is Madison: 402

Enter Name/Area Code:

Process finished with exit code 0
----------------------------------------------------------------------