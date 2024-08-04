package ca.terrahacks.backend.ai;

public class AI {
    private String reply;

    public AI(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "AI{" +
                "reply='" + reply + '\'' +
                '}';
    }
}
