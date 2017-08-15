package backend;

import java.time.LocalDateTime;

/**
 * Created by Edward on 12/4/2015.
 */
public class ChatMessage {
    private String msg;
    private LocalDateTime date;
    private String user;
    
    public ChatMessage(String msg, LocalDateTime date, String user) {
        this.msg = msg;
        this.date = date;
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String toString() {
        return user + ": " + msg;
    }
}
