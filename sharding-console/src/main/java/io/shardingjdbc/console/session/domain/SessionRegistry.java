package io.shardingjdbc.console.session.domain;

import com.google.common.base.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Define user session.
 * 
 * @author panjuan
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SessionRegistry {
    
    private static final SessionRegistry INSTANCE = new SessionRegistry();
    
    private final Map<String, Session> sessions = new HashMap<>(128, 1);
    
    /**
     * Get SessionRegistry instance.
     *
     * @return session registry
     */
    public static SessionRegistry getInstance() {
        return INSTANCE;
    }
    
    /**
     * Create the map of id and connection.
     *
     * @param sessionId session id
     * @return user and connection info
     */
    public synchronized Optional<Session> findSession(final String sessionId) {
        return Optional.fromNullable(sessions.get(sessionId));
    }
    
    /**
     * Add session.
     * 
     * @param sessionId session id
     * @param session user session
     */
    public synchronized void addSession(final String sessionId, final Session session) {
        sessions.put(sessionId, session);
    }

    /**
     * Remove session.
     * 
     * @param sessionId session id
     */
    public synchronized void removeSession(final String sessionId) {
        sessions.remove(sessionId);
    }
}