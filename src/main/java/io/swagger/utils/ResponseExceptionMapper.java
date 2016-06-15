package io.swagger.utils;

import io.swagger.inflector.utils.ApiException;
import io.swagger.sample.models.ApiResponse;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception mapper for handling exceptions
 * @author koustubh.m
 *
 */
@Provider
public class ResponseExceptionMapper implements ExceptionMapper<Exception> {
    public Response toResponse(Exception exception) {
        if (exception instanceof javax.ws.rs.WebApplicationException) {
            javax.ws.rs.WebApplicationException e = (javax.ws.rs.WebApplicationException) exception;
            return Response
                    .status(e.getResponse().getStatus())
                    .entity(new ApiResponse(e.getResponse().getStatus(),
                            exception.getMessage())).type("application/json").build();
        } else if (exception instanceof com.fasterxml.jackson.core.JsonParseException) {
            return Response.status(400)
                    .entity(new ApiResponse(400, "bad input")).build();
        } else if (exception instanceof NotFoundException) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ApiResponse(ApiResponse.ERROR, exception
                            .getMessage())).type("application/json").build();
        } else if (exception instanceof BadRequestException) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(new ApiResponse(ApiResponse.ERROR, exception
                            .getMessage())).type("application/json").build();
        } else if (exception instanceof ApiException) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(new ApiResponse(ApiResponse.ERROR, exception
                            .getMessage())).type("application/json").build();
        } else {
            return Response.status(500)
                    .entity(new ApiResponse(500, "something bad happened"))
                    .type("application/json")
                    .build();
        }
    }
}
