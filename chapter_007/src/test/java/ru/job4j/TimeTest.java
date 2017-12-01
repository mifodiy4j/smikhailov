package ru.job4j;

import org.junit.Test;

public class TimeTest {

    @Test
    public void whenRunningTwoThread() throws InterruptedException {
        String str = "asd fgh hjk klj ghj yu uio uyy ery yre tt ui ui" +
                " ty tu uy ui yu ty ty ty ty ty yy tt op ui rt io yu uu" +
                " ui uu yu yu yu yu yu yu yu yu yu yu yu yu yu yu yu yu" +
                " xc oi lg jh hj jh hj hj hj hj vb df df df df rt gh hj qe";
        Thread t1 = new Thread(new Time(), "t1");
        Thread t2 = new Thread(new CountChar(str), "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.interrupt();
    }
}