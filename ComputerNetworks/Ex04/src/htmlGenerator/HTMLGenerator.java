package htmlGenerator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import crawler.CrawlResultObject;

public class HTMLGenerator {
	private static final String HTML_HEAD_AND_TITLE = "<!DOCTYPE html>\r\n<html>\r\n<head>\r\n    <title>Welcome!</title>\r\n   <link href='https://fonts.googleapis.com/css?family=Ubuntu' rel='stylesheet' type='text/css'>\r\n    <style>\r\n        body {\r\n            padding: 0;\r\n            margin: 0;\r\n            width: 100%;\r\n            height: 100%;\r\n            font-family: 'Ubuntu', sans-serif;\r\n            font-weight: 300;\r\n            color: rgba(255,255,255,0.85);\r\n        }\r\n        *:focus {\r\n            outline: none;\r\n        }\r\n        .container{\r\n            min-height: 100vh;\r\n            background-color: #5E2750;\r\n           background-repeat: no-repeat;\r\n            background-size: 100%;\r\n        }\r\n        .header{\r\n            padding-top: 20vh;\r\n        }\r\n        .main-title{\r\n            font-size: 5vh;\r\n            margin: 0 auto;\r\n            font-family: 'Ubuntu', sans-serif;\r\n            font-weight: 300;\r\n            text-align: center;\r\n        }\r\n        .content{\r\n            padding: 5vh 0 5vh 0 ;\r\n            margin: 0 auto;\r\n            text-align: center;\r\n            font-size: 2vh;\r\n            width:90%\r\n        }\r\n        input{\r\n            font-family: 'Ubuntu', sans-serif;\r\n            background-color: transparent;\r\n            color: rgba(255,255,255,0.85);\r\n            border:0;\r\n            border-bottom: 1px solid white;\r\n            cursor: pointer;\r\n        }\r\n        fieldset{\r\n            margin: 0 auto;\r\n            width:20%;\r\n            border-radius: 10px;\r\n            text-align: justify;\r\n        }\r\n        input[type=\"submit\"]{\r\n            border:0;\r\n            font-size: 3vh;\r\n            transition: all 200ms ease;\r\n\r\n        }\r\n        input[type=\"submit\"]:hover{\r\n            border:0;\r\n            font-size: 3vh;\r\n            color: #DD4814;\r\n        }\r\n        .previousCrawl{\r\n            height:30vh;\r\n            width:30%;\r\n        }\r\n        ul{\r\n            list-style-type: none;\r\n            overflow-x: hidden;\r\n            height: 80%;\r\n            overflow-y: scroll;\r\n\r\n        }\r\n        /*Scrollbar*/\r\n        ::-webkit-scrollbar {\r\n            width: 12px;\r\n        }\r\n\r\n        ::-webkit-scrollbar-track {\r\n            -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);\r\n            border-radius: 10px;\r\n            background-color: rgba(250,250,250,0.2);\r\n        }\r\n\r\n        ::-webkit-scrollbar-thumb {\r\n            border-radius: 10px;\r\n            -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.5);\r\n            background-color: rgba(250,250,250,0.8);\r\n        }\r\n        .error{\r\n            text-align: center;\r\n            color: #DD4814;\r\n            font-size: 20px;\r\n            font-weight: 100;\r\n        }\r\n    </style>\r\n</head>\r\n<body>\r\n\r\n<div class=\"container\">\r\n    <div class=\"header\">\r\n        <h2 class=\"main-title\">Creepy Crawlers</h2>\r\n    </div>\r\n    <p class=\"error\">%ERROR_MESSAGE%</p>\r\n    <div class=\"content\">\r\n";
	private static final String HTML_FORM = "<form action=\"execResult.html\" method=\"get\">\r\n            <label for=\"domain\">Domain:</label>\r\n            <input type=\"text\" id=\"domain\" name=\"domain\" required pattern=\"https?://.+\">\r\n            &nbsp;\r\n            <fieldset>\r\n                <legend style=\"text-align: left\">Additional options</legend>\r\n                <input type=\"checkbox\" id=\"portScan\" name=\"portScan\">\r\n                <label for=\"portScan\">Perform full TCP port scan</label>\r\n                <br>\r\n                <input type=\"checkbox\" id=\"robots\" name=\"robots\">\r\n                <label for=\"robots\">Disrespect robots.txt</label>\r\n            </fieldset>\r\n            <br>\r\n            <input type=\"submit\" value=\"Start crawler\">\r\n        </form>\r\n\r\n     ";
	private static final String HTML_FOOTER = "  <br>\r\n        <fieldset class=\"previousCrawl\">\r\n            <legend>Previously crawled</legend>\r\n\r\n            <ul>\r\n                %CRAWLED_LIST_ITEMS_CONTAINER%\r\n            </ul>\r\n\r\n        </fieldset>\r\n    </div>\r\n</div>\r\n<!--Load JQ-->\r\n<script src=\"public/scripts/jquery-1.11.3.min.js\"></script>\r\n<script src=\"public/scripts/jquery-migrate-1.2.1.min.js\"></script>\r\n\r\n<!--load personal script-->\r\n<script src=\"public/scripts/index.js\"></script>\r\n</body>\r\n</html>";
	static Pattern errorPattern = Pattern.compile("%ERROR_MESSAGE%");
	static Matcher m;

	public static String generateCrawlerErrorPage(String message) {
		String HTMLerrorPage = HTML_HEAD_AND_TITLE + HTML_FORM + HTML_FOOTER;
		m = errorPattern.matcher(HTMLerrorPage);
		return m.replaceFirst(message);
	}

	public static String generateCrawlResultsPage() {
		StringBuilder sb = new StringBuilder();
		sb.append(HTML_HEAD_AND_TITLE);
		sb.append(CrawlResultObject.getInstance().toHTML());
		sb.append(HTML_FOOTER);
		m = errorPattern.matcher(sb.toString());
		return m.replaceFirst("");
	}

	public static String generateCrawlersBusyPage() {
		StringBuilder sb = new StringBuilder();
		sb.append(HTML_HEAD_AND_TITLE);
		sb.append(HTML_FOOTER);
		m = errorPattern.matcher(sb.toString());

		return m.replaceFirst("Crawlers already running..");
	}

	public static String generateMainPage() {
		StringBuilder sb = new StringBuilder();
		sb.append(HTML_HEAD_AND_TITLE);
		sb.append(HTML_FORM);
		sb.append(HTML_FOOTER);
		m = errorPattern.matcher(sb.toString());

		return m.replaceFirst("");
	}

}
