package com.onepercent.goaltracker.Utils;

import org.springframework.http.HttpStatusCode;

    public record ErrorResponse(int statusCode, String message, String details){
}
