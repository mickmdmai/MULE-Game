package backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;

/**
 * Created by Edward on 12/4/2015.
 */
public class Chat {

    private static Chat instance = new Chat();

    private ObservableList<ChatMessage> chat = FXCollections
            .observableArrayList();

    private Chat() {
    }

    public void addMessage(ChatMessage msg) {
        chat.add(chat.size(), msg);
    }

    public void reset() {
        chat.clear();
        for (ChatMessage message: getChats()) {
            addMessage(message);
        }
    }

    public ObservableList<ChatMessage> getChat() {
        return chat;
    }

    public void setChat(ObservableList<ChatMessage> chat) {
        this.chat = chat;
    }

    public static Chat getInstance() {
        return instance;
    }

    public String getChatStream() {
        try {
            URL hp = new URL("http://mulegame3.comxa.com/read.php");
            URLConnection hpCon = hp.openConnection();
            InputStream is = hpCon.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String chat = rd.readLine(); //all chats are contained in the first line
            rd.close();
            is.close();
            return chat;
        } catch (IOException io) {
            io.printStackTrace();
        }
        return null;
    }

    public ChatMessage[] getChats() {
        String chats = getChatStream();
        if (chats != null) {
            String[] chatLine = chats.split("<br/>");
            ChatMessage[] chatList = new ChatMessage[chatLine.length];
            for (int i = 0; i < chatLine.length; i++) {
                chatList[i] = stringToChat(chatLine[i]);
            }
            return chatList;
        }
        return null;
    }

    public void postChatStream(ChatMessage message) {
        try {
            URL hp = new URL("http://mulegame3.comxa.com/addChat.php?name=" +
                    encode(message.getUser().trim()) + "&text=" + encode(message.getMsg().trim()));
            URLConnection hpCon = hp.openConnection();
            hpCon.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //todo implement actual encoding
    private String encode(String s) {
        return s.replace(" ", "+");
    }

    private ChatMessage stringToChat(String s) {
        String[] params = s.split(" -> ");
        //LocalDateTime date = LocalDateTime.parse(params[2]);
        return new ChatMessage(params[1], LocalDateTime.now(), params[0]);
    }
}
