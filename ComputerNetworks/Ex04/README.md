Hi, 
Due to some private issues this excersize is far from being complete.
We're not writing this in an attempt to gain points, but as a "good to know". Enjoy.

Implementations:
Analyzer-Downloader -> This is working pretty well. We had an unexplained bug when asking from threads
to notify when they're done. Bottom line - from will return the generated page after a timeout of 15sec.
Web GUI -> Ubuntu style. Nice thing to see is that even though the browser navigates to "execResult.html" 
and displays it properly the page doesn't really exist. High-five to the route controllers from last lab.
Access -> Blocking access attempts to "resultsExec" if the accessing-client isn't the client who invoked the crawlers.
Busy screen -> Showing proper message instead of the form when crawlers run.
Supporting 3xx responses(apparently this is bonus) -> When receiving "3xx" response from the server we re-route our downloaders to the 
new link.
port scanner -> working properly.
Robots.txt -> not implemented.
Show past results -> not implemented.
Exceptions -> might stain the console but won't crash.