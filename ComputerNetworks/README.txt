## README ##

Hi!
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

