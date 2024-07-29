package io.github.jwdeveloper.common.exception;


public class CancellationException extends RuntimeException {
    public CancellationException() {
    }

    public CancellationException(String message) {
        super(message);
    }

    public CancellationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CancellationException(Throwable cause) {
        super(cause);
    }

    public CancellationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

