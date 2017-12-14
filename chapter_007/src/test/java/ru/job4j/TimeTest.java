package ru.job4j;

import org.junit.Test;

public class TimeTest {

    @Test
    public void whenRunningTwoThread() throws InterruptedException {
        String str = "asd fgh hjk klj ghj yu uio uyy ery yre tt ui ui" +
                " ty tu uy ui yu ty ty ty ty ty yy tt op ui rt io yu uu" +
                " ui uu yu yu yu yu yu yu yu yu yu yu yu yu yu yu yu yu" +
                " ui uu yu yu yu yu yu yu yu yu yu yu yu yu yu yu yu yu" +
                " ui uu yu yu yu yu yu yu yu yu yu yu yu yu yu yu yu yu" +
                " xc oi lg jh hj jh hj hj hj hj vb df df df df rt gh hj qe";
        Thread time = new Thread(new Time(1000), "t1");
        Thread count = new Thread(new CountChar(str), "t2");

        time.start();
        count.start();

        time.join();
        count.interrupt();
    }
}