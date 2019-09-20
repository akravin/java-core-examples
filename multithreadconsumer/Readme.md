Multiple Thread Processing
--------------------------
File multi_thread_messages.txt contains one message per line where values are | delimited.
The first value in each line is the message ID (message IDs are not unique), the second value
is how long it takes to process the message (in milliseconds) and the third value is the message.

This file is simulating a stream of messages over the network.  This is a single producer
multiple consumer scenario where there are five consumers.
(one thread to read the file and five threads to process the messages)

All messages with the same ID need to be processed in the order they appear.

So for example, if these are the messages in the file:

A|1000|Monday
B|1000|Tuesday
A|50|Wednesday
B|100|Thursday
D|3000|Friday

then 'Monday', 'Tuesday', 'Friday' can be processed at the same time independantly of each other,
but 'Wednesday' must be processed after 'Monday', and 'Thursday' after 'Tuesday'.

Each line provides the value for how long to sleep to simulate the message processing time.


Write a Java program to do this properly only using Core Java (no third party libs).
You cannot sort the file, or read through the entire file before sending items to process.
Must treat each line in the file as if you do not know the next line.