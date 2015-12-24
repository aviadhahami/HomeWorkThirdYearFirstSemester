# Java HMAC-SHA1 Implementation
in order to run the script one should use the following:
 * if on Unix dist. : make the HMAC.jar runnable via `$ chmod +x HMAC.jar`
 * Open terminal/CMD and run the following:
  
	`$ HMAC.jar <PATH TO MESSAGE FILE> <PATH TO DIGEST FILE> <PATH TO KEY FILE> <FUNCTION TO USE ("compute" / "verify")>`
 * If choose "compute" -> The CLI will display "compute" and "done" when it finished calculating the hash.
 * If choose "verify" -> The CLI will display "verify" and then "accept" or "reject" according to the hash-equality check.
