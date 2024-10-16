package asmt3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

enum RoomType { STANDARD, DELUXE, PRESIDENTIAL }

class BookingRequest {
    private final int roomId;
    private final String guestName;
    private final RoomType roomType;

    public BookingRequest(int roomId, String guestName, RoomType roomType) {
        this.roomId = roomId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getGuest() {
        return guestName;
    }

    public RoomType getRoomType() {
        return roomType;
    }
}

abstract class BookingHandler {
    protected BookingHandler nextHandler;

    public void setNextHandler(BookingHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(BookingRequest request);
}

class StandardRoomHandler extends BookingHandler {
    public void handleRequest(BookingRequest request) {
        if (request.getRoomType() == RoomType.STANDARD){
            System.out.println("StandardRoomHandler booked room: " + request.getRoomId() +" for " + request.getGuest());
        } else if  (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class DeluxeRoomHandler extends BookingHandler {
    public void handleRequest(BookingRequest request) {
        if (request.getRoomType() == RoomType.DELUXE){
            System.out.println("DeluxeRoomHandler booked room: " + request.getRoomId() +" for " + request.getGuest());
        } else if  (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class PresidentialRoomHandler extends BookingHandler {
    public void handleRequest(BookingRequest request) {
        if (request.getRoomType() == RoomType.PRESIDENTIAL){
            System.out.println("PresidentialRoomHandler booked room: " + request.getRoomId() +" for " + request.getGuest());
        } else if  (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

interface BookingCommand {
    void execute();
}

class BookRoomCommand implements BookingCommand {
    private final BookingRequest request;

    public BookRoomCommand(BookingRequest request) {
        this.request = request;
    }

    public void execute() {
        System.out.println(request.getGuest() + " booking room: " + request.getRoomId());
    }
}

class BookingMediator {
    private final BookingHandler standardRoomHandler;
    private final BookingHandler deluxeRoomHandler;
    private final BookingHandler presidentialRoomHandler;

    public BookingMediator() {
        standardRoomHandler = new StandardRoomHandler();
        deluxeRoomHandler = new DeluxeRoomHandler();
        presidentialRoomHandler = new PresidentialRoomHandler();

        standardRoomHandler.setNextHandler(deluxeRoomHandler);
        deluxeRoomHandler.setNextHandler(presidentialRoomHandler);
    }

    public void processRequest(BookingRequest request, BookingCommand command) {
        if (request.getRoomType() == RoomType.STANDARD) {
            standardRoomHandler.handleRequest(request);
        } else if (request.getRoomType() == RoomType.DELUXE) {
            deluxeRoomHandler.handleRequest(request);
        } else if (request.getRoomType() == RoomType.PRESIDENTIAL) {
            presidentialRoomHandler.handleRequest(request);
        }
        command.execute();
    }
}

class BookingRequestIterator implements Iterator<BookingRequest> {
    private List<BookingRequest> requests;
    private int index;

    public BookingRequestIterator(List<BookingRequest> requests) {
        this.requests = requests;
        this.index = 0;
    }

    public boolean hasNext() {
        return index < requests.size();
    }

    public BookingRequest next() {
        return requests.get(index++);
    }
}

public class BehavioralPatterns {
    public static void main(String[] args) {
        List<BookingRequest> requests = new ArrayList<>();
        requests.add (new BookingRequest(777, "Yan Lalov", RoomType.STANDARD));
        requests.add (new BookingRequest(77, "Adil Bolatov", RoomType.DELUXE));
        requests.add (new BookingRequest(1, "President", RoomType.PRESIDENTIAL));

        BookingRequestIterator iterator = new BookingRequestIterator(requests);

        BookingMediator mediator = new BookingMediator();

        while (iterator.hasNext()) {
            BookingRequest request = iterator.next();
            BookingCommand command = new BookRoomCommand(request);
            mediator.processRequest(request, command);
        }
    }
}
