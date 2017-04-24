package com.wangzh.vultr.others.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WangZH on 2017/4/24.
 */

public class HttpResponseUtil {



    public static Map<Integer,String> getCodeMap(){
        HashMap<Integer, String> codeMaps = new HashMap<>();
        codeMaps.put(100, "Continue: Request received, please continue");
        codeMaps.put(101, "Switching Protocols: Switching to new protocol; obey Upgrade header");
        codeMaps.put(102, "Processing: WebDAV; RFC 2518");
        codeMaps.put(200, "OK: Request fulfilled, document follows");
        codeMaps.put(201, "Created: Document created, URL follows");
        codeMaps.put(202, "Accepted: Request accepted, processing continues off-line");
        codeMaps.put(203, "Non-Authoritative Information: Request fulfilled from cache");
        codeMaps.put(204, "No Content: Request fulfilled, nothing follows");
        codeMaps.put(205, "Reset Content: Clear input form for further input.");
        codeMaps.put(206, "Partial Content: Partial content follows.");
        codeMaps.put(207, "Multi-status: WebDAV; RFC 4918");
        codeMaps.put(208, "Already Reported: WebDAV; RFC 5842");
        codeMaps.put(226, "IM Used: RFC 3229");
        codeMaps.put(300, "Multiple Choices: Object has several resources -- see URI list");
        codeMaps.put(301, "Moved Permanently: Object moved permanently -- see URI list");
        codeMaps.put(302, "Found: Object moved temporarily -- see URI list");
        codeMaps.put(303, "See Other: Object moved -- see Method and URL list");
        codeMaps.put(304, "Not Modified: Document has not changed since given time");
        codeMaps.put(305, "Use Proxy: You must use proxy specified in Location to access this resource");
        codeMaps.put(306, "Switch Proxy: Subsequent requests should use the specified proxy");
        codeMaps.put(307, "Temporary Redirect: Object moved temporarily -- see URI list");
        codeMaps.put(308, "Permanent Redirect: Object moved permanently");
        codeMaps.put(400, "Bad Request: Bad request syntax or unsupported method");
        codeMaps.put(401, "Unauthorized: No permission -- see authorization schemes");
        codeMaps.put(402, "Payment Required: No payment -- see charging schemes");
        codeMaps.put(403, "Forbidden: Request forbidden -- authorization will not help");
        codeMaps.put(404, "Not Found: Nothing matches the given URI");
        codeMaps.put(405, "Method Not Allowed: Specified method is invalid for this resource.");
        codeMaps.put(406, "Not Acceptable: URI not available in preferred format.");
        codeMaps.put(407, "Proxy Authentication Required: You must authenticate with this proxy before proceeding.");
        codeMaps.put(408, "Request Timeout: Request timed out; try again later.");
        codeMaps.put(409, "Conflict: Request conflict.");
        codeMaps.put(410, "Gone: URI no longer exists and has been permanently removed.");
        codeMaps.put(411, "Length Required: Client must specify Content-Length.");
        codeMaps.put(412, "Precondition Failed: Precondition in headers is false.");
        codeMaps.put(413, "Request Entity Too Large: Entity is too large.");
        codeMaps.put(414, "Request-URI Too Long: URI is too long.");
        codeMaps.put(415, "Unsupported Media Type: Entity body in unsupported format.");
        codeMaps.put(416, "Requested Range Not Satisfiable: Cannot satisfy request range.");
        codeMaps.put(417, "Expectation Failed: Expect condition could not be satisfied.");
        codeMaps.put(418, "I'm a teapot: The HTCPCP server is a teapot");
        codeMaps.put(419, "Authentication Timeout: previously valid authentication has expired");
        codeMaps.put(420, "Method Failure / Enhance Your Calm: Spring Framework / Twitter");
        codeMaps.put(422, "Unprocessable Entity: WebDAV; RFC 4918");
        codeMaps.put(423, "Locked: WebDAV; RFC 4918");
        codeMaps.put(424, "Failed Dependency / Method Failure: WebDAV; RFC 4918");
        codeMaps.put(425, "Unordered Collection: Internet draft");
        codeMaps.put(426, "Upgrade Required: client should switch to a different protocol");
        codeMaps.put(428, "Precondition Required: RFC 6585");
        codeMaps.put(429, "Too Many Requests: RFC 6585");
        codeMaps.put(431, "Request Header Fields Too Large: RFC 6585");
        codeMaps.put(440, "Login Timeout: Microsoft");
        codeMaps.put(444, "No Response: Nginx");
        codeMaps.put(449, "Retry With: Microsoft");
        codeMaps.put(450, "Blocked by Windows Parental Controls: Microsoft");
        codeMaps.put(451, "Unavailable For Legal Reasons: Internet draft");
        codeMaps.put(494, "Request Header Too Large: Nginx");
        codeMaps.put(495, "Cert Error: Nginx");
        codeMaps.put(496, "No Cert: Nginx");
        codeMaps.put(497, "HTTP to HTTPS: Nginx");
        codeMaps.put(498, "Token expired/invalid: Esri");
        codeMaps.put(499, "Client Closed Request: Nginx");
        codeMaps.put(500, "Internal Server Error: Server got itself in trouble");
        codeMaps.put(501, "Not Implemented: Server does not support this operation");
        codeMaps.put(502, "Bad Gateway: Invalid responses from another server/proxy.");
        codeMaps.put(503, "Service Unavailable: The server cannot process the request due to a high load");
        codeMaps.put(504, "Gateway Timeout: The gateway server did not receive a timely response");
        codeMaps.put(505, "HTTP Version Not Supported: Cannot fulfill request.");
        codeMaps.put(506, "Variant Also Negotiates: RFC 2295");
        codeMaps.put(507, "Insufficient Storage: WebDAV; RFC 4918");
        codeMaps.put(508, "Loop Detected: WebDAV; RFC 5842");
        codeMaps.put(509, "Bandwidth Limit Exceeded: Apache bw/limited extension");
        codeMaps.put(510, "Not Extended: RFC 2774");
        codeMaps.put(511, "Network Authentication Required: RFC 6585");
        codeMaps.put(598, "Network read timeout error: Unknown");
        codeMaps.put(599, "Network connect timeout error: Unknown");
        return codeMaps;
    }


    public static String getcodeMapsAndValue(int code){
        Map<Integer,String> codeMap = getCodeMap();
        if (codeMap.containsKey(code)){
            return code +","+ codeMap.get(code);
        }else {
            return code +","+ "undefined";
        }

    }
}
