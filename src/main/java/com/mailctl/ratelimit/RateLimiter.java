package com.mailctl.ratelimit;

public class RateLimiter {
    @SuppressWarnings("unused")
    private final int rate;

    public RateLimiter(int rate) {
        this.rate = rate;
    }

    /**
     * No-op pause method. Currently does nothing.
     */
    public void pause() {
        // Intentionally left blank.
        // Later, you could implement Thread.sleep(1000 / rate) logic here.
    }
}
