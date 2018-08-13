package com.acmerobotics.dashboard;

import fi.iki.elonen.NanoWSD;

/**
 * WebSocket server that handles dashboard client connections.
 */
public class RobotWebSocketServer extends NanoWSD {
    private static final int PORT = 8000;
    
    private FtcDashboard dashboard;
    
    public RobotWebSocketServer(FtcDashboard dashboard) {
        super(PORT);
        this.dashboard = dashboard;
    }

    @Override
    protected WebSocket openWebSocket(IHTTPSession handshake) {
        return new RobotWebSocket(handshake, dashboard);
    }

}
