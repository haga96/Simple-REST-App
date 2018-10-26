package warsztaty.spring.ailleron.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse {
    private ArrayList<String> messages;
    private Result result;
    private String hello;

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public Result getResult() {
        return result;
    }

    public String getHello() {
        return hello;
    }
}
