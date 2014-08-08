/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.model;

import edu.ilstu.dao.ChatReactionDAO;
import edu.ilstu.dao.ChatReactionDAOImpl;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public class ChatReactionModel implements Serializable {

    private int chatid;
    private int userid;
    private String chatmessage;
    private char isPrivate;
    private Date reactionDate;
    private Time reactionTime;

    public ChatReactionModel() {
    }

    private ChatReactionModel(int chatid) {
        this.chatid = chatid;
    }

    private ChatReactionModel(int userid, String chatmessage, char isPrivate, Date reactionDate, Time reactionTime) {
        this.userid = userid;
        this.chatmessage = chatmessage;
        this.isPrivate = isPrivate;
        this.reactionDate = reactionDate;
        this.reactionTime = reactionTime;

    }

    private ChatReactionModel(int chatid, int userid, String chatmessage, char isPrivate, Date reactionDate, Time reactionTime) {
        this(userid, chatmessage, isPrivate, reactionDate, reactionTime);
        this.chatid = chatid;
    }

    /**
     * @return the chatid
     */
    public int getChatid() {
        return chatid;
    }

    /**
     * @param chatid the chatid to set
     */
    public void setChatid(int chatid) {
        this.chatid = chatid;
    }

    /**
     * @return the userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * @return the chatmessage
     */
    public String getChatmessage() {
        return chatmessage;
    }

    /**
     * @param chatmessage the chatmessage to set
     */
    public void setChatmessage(String chatmessage) {
        this.chatmessage = chatmessage;
    }

    /**
     * @return the isPrivate
     */
    public char getIsPrivate() {
        return isPrivate;
    }

    /**
     * @param isPrivate the isPrivate to set
     */
    public void setIsPrivate(char isPrivate) {
        this.isPrivate = isPrivate;
    }

    /**
     * @return the reactionDate
     */
    public Date getReactionDate() {
        return reactionDate;
    }

    /**
     * @param reactionDate the reactionDate to set
     */
    public void setReactionDate(Date reactionDate) {
        this.reactionDate = reactionDate;
    }

    /**
     * @return the reactionTime
     */
    public Time getReactionTime() {
        return reactionTime;
    }

    /**
     * @param reactionTime the reactionTime to set
     */
    public void setReactionTime(Time reactionTime) {
        this.reactionTime = reactionTime;
    }

    public int postNewChatReaction() {

        ChatReactionDAO crdao = new ChatReactionDAOImpl();

        return crdao.postNewReaction(this);
    }

    public void updateChatReaction() {

        ChatReactionDAO crdao = new ChatReactionDAOImpl();

        crdao.updateChatreaction(this);
    }

    public void deleteChatReaction() {

        ChatReactionDAO crdao = new ChatReactionDAOImpl();

        crdao.DeleteChatReaction(this);
    }

    public ArrayList<ChatReactionModel> getChatReactionByChatID() {

        ChatReactionDAO crdao = new ChatReactionDAOImpl();

        return crdao.getChatReactionById(this.getChatid());
    }

    public ChatReactionModel getChatReactionForUser() {

        ChatReactionDAO crdao = new ChatReactionDAOImpl();

        return crdao.getChatReactionForUser(this.getChatid(), this.getUserid());
    }
}
