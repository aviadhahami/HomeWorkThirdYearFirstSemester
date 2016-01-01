# Java HMAC-SHA1 Implementation
## Description
The following program is an example of HMAC-SHA1 implementation. The SHA1 code was created via following the [pseudo-code from Wikipedia](https://en.wikipedia.org/wiki/SHA-1#SHA-1_pseudocode). Similarly, the HMAC pseudo-code was taken from [here](https://en.wikipedia.org/wiki/Hash-based_message_authentication_code#Implementation).
The program can verify a given hash or generate a new one.

## Overview
The program can either calculate an HMAC value for a message (when a *BASE64* formatted key is given) yeild *BASE64* formatted hash into `digest.txt`, or it can verify that a given *BASE64* formatted `digest.txt` is the actual hash an HMAC would generate for the given `key.txt` and `input.txt`
## Tech specs
See attached *Javadoc* in `/doc`

## How to run
in order to run the script one should use the following:
 * if on Unix dist. : make sure the HMAC.jar is runnable via `$ chmod +x HMAC.jar`
 * Open terminal/CMD and run the following:
  
	`$ java -jar HMAC.jar <PATH TO MESSAGE FILE> <PATH TO DIGEST FILE> <PATH TO KEY FILE> <FUNCTION TO USE ("compute" / "verify")>`
 * If choose "compute" -> The CLI will display "compute" and "done" when it finished calculating the hash.
 * If choose "verify" -> The CLI will display "verify" and then "accept" or "reject" according to the hash-equality check.

## Examples
One may want to see more examples of hash generation/verification.
In order to do so, one may start by going into the `Executable` directory.
Once inside, you will see the following:
* `digest.txt`
* `input.txt`
* `key.txt`
* `HMAC.jar`
* `1`(directory)
* `2`(directory)

We've supplied three examples for this executable, you can run `HMAC.jar` in the current folder (`Executable`) or in `2` or `1` (each contains a copy of `HMAC.jar` for demo purposes)


