# Computer networks HTTP server
We've implemented Java based server with the following methods:
 * POST
 * GET
 
And we also support chunked encoding.

At start one can supply via the CLI path to personal configuration file.
Unless supplied - the fallback "config.ini" will be under the running directory, in "config/config.ini".
We parse the config and break it into relevant variables which we contain in a "ServerConfigObj" which we pass to the server.
Assuming parsing went ok (if not - CLI will provide proper message & exit), we supply the thread-pool manager with the size, and we hold the root and default page on the running server which start listening.

Once client connects, it receives it's own thread and we start accepting data via socket.
If the HTTP request supplied in the first line doesn't match a KNOWN HTTP request method we kill the socket with proper error.
Assuming the request is fine, we pass the request (HTTPRequest object) to the response handler (ResponseHandler.class).
We parse the request type, and body/params and proccess it.
Proccessing depends on the route.
We've added route controllers which bind to specific route at runtime (Routes.class:19), so if there's a match between the requested path and a bounded route controller - the controller will handle the logic.
If not - the server will serve the resource regularly.
[This and more in the bonus.txt]

We serve clients with their custom-generated response(HTTPResponse.class) while checking for chunked encoding.
[Disclaimer : We hard-coded chunks to 10KB]

Assuming client tries to reach 'out of bound' path, we remove illegal characthers from the path and force the search on root folder.
[More on security module in bonus.txt]

# Bonuses
Security reach:

	Each connection's cookie gets read and looking for "level" cookie.
	If such exists, the integer it holds marks the user's "level" in the system.
	on Routes.class:11 you can see that we bind levels with routes, hence if someone will try to reach "/private/pass.txt"
	he will hit 403 if he's below level 5.
Security update:

	Assuming the "/private/pass.txt" from previous section, if on runtime someone added "/private/private2/x.html", once 
	a request will be made to this path it will inherit it's closest known-parent security level and add itself to the 
	routes-levels binding. [logs are provided within the CLI on such event]
Out-of-root reach:

	If client is trying to reach outside the root folder, the illegal chars are getting removed hence the path will be
	reconstructed and the client will be passed to the root again. (i.e "root/../something" -> "root/something")
Database:

	We've implemented simple NoSQL (hash) database
Material Design:

	Some client-side magic
Route-controllers:

	On Routes.class:19 we bind each route to a specific controller.
	Each controller can implemented which-ever method it likes, such that '/api/db' can serve both for GET or for POST
	and behave differently.
	


##### Disclaimer : 
Some things should've been added to the config (i.e controllers, timeout and more) but we didn't have time nor
	man-power for this 8)
	Hope this one satisfies :d

