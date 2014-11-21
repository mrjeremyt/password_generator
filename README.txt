UTEID: jmt2939; dbd453;
FIRSTNAME: Jeremy; Daniel;
LASTNAME: Thompson; Durbin;
CSACCOUNT: jmt2939; majisto;
EMAIL: jthompson2214@gmail.com; majisto@gmail.com;

[Program 5]
[Description]
This program takes in a reference text and two integers N and k, where N is the number of passwords to return 
and k is the length of those passwords. We take in the text file and make a scanner over it, retreiving each line. 
for each line we remove all non-alphabetical characters, change it to lowercase and split it on whitespace to 
get the words. for each word we then start iterating over the letters. the first one is added to the starters 
table and it's count is increased and then goes to the next letter. that one's count is increased and the 
first letter's follower's count for the current letter is increased. this is repeated for each word. Everything 
is accessible in constant time so the amount of work needed to process a text is O(N) where N is the total number 
of letters. 

Then when the table is constructed we get a random number from 0 to total number of letters. Then we start summing 
the counts of the Starter's array, when we reach a sum that is >= to the random number that is the letter we chose 
to use. Then we do this again but for the array from the followers table for the previously chosen letter. Through 
this process the letters that show up the most have a larger sum, so when we get a random number and start summing 
the counts we're really just choosing a random letter from that list based on probabilities. We go ahead and do this 
til we have reached the predetermined length of the password and then convert those numbers to ASCII and output them 
as the somewhat pronounceable password. this is then done N times where N is the number of passwords that was 
requested. 

[Finish]
We finished all of this program. It prints out the followers table and N number of passwords of length k.
 
[Source of Reference File]
We used a random phrase generator we created located at http://www.nerdemipsum.me to generate our input.  
Included in the zip is our test file, named reference.txt, but more can be generated at the URL.  It's lots of fun!
reference.txt contains 1997 English words and has 125 lines.

[Test Cases]
[Input of test 1]
java Passwords reference-test_input.txt 7 8

[Output of test 1]

Passwords are: 
momorist
gitoosth
qupthuke

4 Bad Passwords: 
Bad password: sanderin, Substring is: sand
Bad password: mashewer, Substring is: mash
Bad password: rearithe, Substring is: rear
Bad password: erthatwo, Substring is: that


[Input of test 2]
java Passwords reference-test_input.txt 1 8

[Output of test 2]

Passwords are: 

1 Bad Passwords: 
Bad password: bcerrend, Substring is: rend

[Input of test 3]
java Passwords reference-test_input.txt 0 8

[Output of test 3]

Enter a larger number for amount of passwords to generate.

[Input of test 4]
java Passwords reference-test_input.txt 8 0

[Output of test 4]

How can I generate a password with length 0...or less?!  You cray cray.

[Extra Credit]
We did the second extra credit option which takes in the Linux /usr/share/dict/words dictionary, and also allows for the user to add in their own.
The Linux dictionary is hard-coded into the program, so this path should exist when the program is run, or else the program will fail.  The user
dictionary is optional.  When running the program, it must be the last parameter.

The extra credit adds a LinkedHashSet which stores all the words from the dictionary and user input file.  The LinkedHashSet, hereby referred to as LHS,
is a set, so it prevents any duplicate entries and also has a very fast lookup time due to its hashing nature.  When we create a password, we call the method
parse_password and create substrings of size, 4 <= size <= len(password),  or greater and call LHS.contains on each substring.  The lookups are near constant
time so this is not a major performance penalty.

After we find a bad password, we write the String containing the bad password and the substring to an ArrayList called bad_password.  The purpose is to allow
all the good passwords to be printed first, and then we print all the bad ones with the number of bad ones detected.