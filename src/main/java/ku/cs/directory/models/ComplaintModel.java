package ku.cs.directory.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ComplaintModel {
    private String headline;
    private String detail;
    private String category;
    private String data;
    private String date;
    private int score;
    private String status;
    private String writeBy;

    private String reply;

    private String replyName;

    public ComplaintModel(String headline, String detail, String category, String data, String date, int score, String status, String writeBy, String reply, String replyName) {
        this.headline = headline;
        this.detail = detail;
        this.category = category;
        this.data = data;
        this.date = date;
        this.score = score;
        this.status = status;
        this.writeBy = writeBy;
        this.reply = reply;
        this.replyName = replyName;
    }

    @Override
    public String toString() {
        return headline + "  [Date: " + getDate() + "]" + "  [Vote: " + getScore() + "]";
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getData() {
        return data;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void addScore() {
        this.score += 1;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isHeadline(String headline) {
        return this.headline.equals(headline);
    }

    public boolean isCategory(String category) {
        return this.category.equals(category);
    }

    public String getWriteBy() {
        return writeBy;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    public String toCSV() {
        return headline + "," + detail + "," + category + "," + data + "," + date + "," + getScore() + "," + getStatus() + "," + writeBy
                + "," + getReply() + "," + getReplyName();
    }

}
