package learn;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
class BookMake{
    AtomicInteger title = new AtomicInteger(0);
    AtomicInteger mark = new AtomicInteger(0);

    public void titleAdd(int i) {
        title.addAndGet(i);
        mark.set(0);
    }

}