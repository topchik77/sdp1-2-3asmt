package asmt4;

import java.util.ArrayList;
import java.util.List;


interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}


interface Observer {
    void update(String news);
}


class NewsAgency implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private String news;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this.news);
        }
    }

    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }
}


class NewsChannel implements Observer {
    private String name;
    private String latestNews;

    public NewsChannel(String name) {
        this.name = name;
    }

    public void update(String news) {
        this.latestNews = news;
        displayNews();
    }

    private void displayNews() {
        System.out.println(name + " received news: " + latestNews);
    }
}


class Part_3 {
    public static void main(String[] args) {
        NewsAgency newsAgency = new NewsAgency();
        NewsChannel channel1 = new NewsChannel("HABAR");
        NewsChannel channel2 = new NewsChannel("KTK");
        NewsChannel channel3 = new NewsChannel("BBC");

        newsAgency.addObserver(channel1);
        newsAgency.addObserver(channel2);
        newsAgency.addObserver(channel3);

        newsAgency.setNews("Breaking news: hurricanes in Miami");
        newsAgency.setNews("Update: Weather forecast shows sunny day ahead!");
    }
}