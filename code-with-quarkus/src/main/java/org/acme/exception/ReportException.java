package org.acme.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

public class ReportException extends RuntimeException {
    public ReportException(String message) {
        super(message);
    }

    @Provider
    public static class ReportExceptionMapper implements ExceptionMapper<ReportException> {
        @Override
        public Response toResponse(ReportException exception) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + exception.getMessage() + "\"}")
                    .build();
        }
    }
}
