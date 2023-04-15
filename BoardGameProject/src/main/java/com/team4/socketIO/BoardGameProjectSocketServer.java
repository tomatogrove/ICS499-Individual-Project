package com.team4.socketIO;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.team4.model.Chess;
import com.team4.model.util.Session;
import com.team4.model.util.UserAccount;
import com.team4.services.BoardService;
import com.team4.services.ChessService;
import com.team4.services.util.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardGameProjectSocketServer implements CommandLineRunner {

    private final SocketIOServer server;

    @Autowired
    private SessionService sessionService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private ChessService chessService;

    @Autowired
    public BoardGameProjectSocketServer(SocketIOServer server) {
        this.server = server;
    }

    @Override
    public void run(String... args) throws Exception {
        registerEventListeners(server);
        server.start();
    }

    private void registerEventListeners(SocketIOServer server) {
        server.addConnectListener(client -> {
            System.out.println("Client connected: " + client.getSessionId());
        });

        server.addEventListener("joinGame", String.class, (client, data, ackRequest) -> joinGame(client, data));
    }

    private void joinGame(SocketIOClient client, String data) {
        String sessionKey = data.split(",")[0];
        String chessID = data.split(",")[1];
        Session session = sessionService.getSessionByKey(sessionKey);

        if (session != null) { // If we found a user
            UserAccount user = session.getUserAccount();
            if ("-1".equals(chessID)) { // If we are creating a new game
                Chess game = chessService.createChess(new Chess(user));
                chessID = game.getChessID().toString();

                client.sendEvent("onJoinGame", game);
                client.joinRoom(chessID);
            } else { // If we are joining an existing game
                Chess game = chessService.getChessById(Long.parseLong(chessID));

                if (game != null) {
                    if (!game.isUserInGame(user) && game.needsPlayer()) {
                        game.setBlackPlayer(user);
                        game = chessService.updateChess(game);
                    } else if (!game.isUserInGame(user) && !game.needsPlayer()) {
                        client.sendEvent("onError", "User not authorized");
                        return;
                    }

                    client.sendEvent("onJoinGame", game);

                    client.joinRoom(chessID);
                    if (getRoomSize(chessID) == 2) {
                        server.getRoomOperations(chessID).sendEvent("onGameReady", game);
                    }
                } else {
                    client.sendEvent("onError", "Game not found");
                }
            }
        } else { // If we didn't find a user
            client.sendEvent("onError", "Session key not found");
        }
    }

    private int getRoomSize(String roomName) {
        return server.getRoomOperations(roomName).getClients().size();
    }
}