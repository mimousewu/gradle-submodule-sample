package com.thoughtworks.exceptions;


public class JsonParseException extends RuntimeException {

    private static final String JSON_PARSE_FAILED = "json.parse.failed";

    public JsonParseException(Throwable cause) {
        super(JSON_PARSE_FAILED, cause);
    }

    public JsonParseException() {
        super(JSON_PARSE_FAILED);
    }
}
