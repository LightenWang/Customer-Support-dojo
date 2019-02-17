package com.pidojo;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionIdListener{

    private SimpleDateFormat formatter =
            new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");

    /**
     * Receives notification that a session has been created.
     *
     * @param e the HttpSessionEvent containing the session
     */
    @Override
    public void sessionCreated(HttpSessionEvent e) {
        System.out.println(this.date() + ": Session" + e.getSession().getId() +
                " created.");
        SessionRegistry.addSession(e.getSession());
    }

    /**
     * Receives notification that a session is about to be invalidated.
     *
     * @param e the HttpSessionEvent containing the session
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
        System.out.println(this.date() + ": Session" + e.getSession().getId() +
                " destroyed.");
        SessionRegistry.removeSession(e.getSession());
    }

    /**
     * Receives notification that session id has been changed in a
     * session.
     *
     * @param e           the HttpSessionBindingEvent containing the session
     *                     and the name and (old) value of the attribute that was replaced
     * @param oldSessionId the old session id
     */
    @Override
    public void sessionIdChanged(HttpSessionEvent e, String oldSessionId) {
        System.out.println(this.date() + ": Session ID " + oldSessionId +
                " changed to " + e.getSession().getId());
        SessionRegistry.updateSessionId(e.getSession(), oldSessionId);
    }

    private String date() {
        return this.formatter.format(new Date());
    }
}
