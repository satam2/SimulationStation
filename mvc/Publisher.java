package mvc;

import java.util.LinkedList;
import java.util.List;

public class Publisher {

    private List<Subscriber> subscribers = new LinkedList<>();

    public void subscribe(Subscriber observer) {
        subscribers.add(observer);
    }

    public void unsubscribe(Subscriber observer) {
        subscribers.remove(observer);
    }

    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers)
            subscriber.update();
    }

}