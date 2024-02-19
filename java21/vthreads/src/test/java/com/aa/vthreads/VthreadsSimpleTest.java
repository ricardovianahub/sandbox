package com.aa.vthreads;

import org.junit.jupiter.api.Test;

class VthreadsSimpleTest {

    @Test
    void runRegularThreads() throws InterruptedException {
        VthreadsSimple vthreadsSimple = new VthreadsSimple();
        vthreadsSimple.regularThreads();
    }

    @Test
    void runVirtualThreads() throws InterruptedException {
        VthreadsSimple vthreadsSimple = new VthreadsSimple();
        vthreadsSimple.virtualThreads();
    }

}
