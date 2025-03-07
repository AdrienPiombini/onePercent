package com.onepercent.goaltracker.utils;

    public record ErrorResponse(int statusCode, String message, String details){
}
