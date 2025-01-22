For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t is concatenated with itself one or more times).

Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.



Example 1:

Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"
Example 2:

Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"
Example 3:

Input: str1 = "LEET", str2 = "CODE"
Output: ""


Constraints:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of English uppercase letters.



///

Euclidean Algorithm
The key principle of the Euclidean Algorithm is:

The GCD of two integers a and b is the same as the GCD of b and the remainder of a divided by b.

Mathematically:

GCD(a, b) = GCD(b, a % b)
This repeats until the remainder becomes 0, at which point b becomes the GCD.

Find GCD(48, 18):



Initial Values:
a = 48, b = 18

Inside the loop:

temp = b = 18
b = a % b = 48 % 18 = 12
a = temp = 18
Second Iteration:
a = 18, b = 12

Inside the loop:

temp = b = 12
b = a % b = 18 % 12 = 6
a = temp = 12
Third Iteration:
a = 12, b = 6

Inside the loop:

temp = b = 6
b = a % b = 12 % 6 = 0
a = temp = 6
Termination:
When b = 0, the loop stops, and the function returns a = 6, which is the GCD.