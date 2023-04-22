package com.team4.socketIO;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.team4.model.Chess;
import com.team4.model.pieces.Piece;
import com.team4.model.util.Session;
import com.team4.model.util.UserAccount;
import com.team4.services.BoardService;
import com.team4.services.ChessService;
import com.team4.services.pieces.PieceService;
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
    private PieceService pieceService;

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
        server.addEventListener("movePiece", String.class, (client, data, ackRequest) -> movePiece(client, data));
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
            client.sendEvent("onError", "Session not found");
        }
    }

    private void movePiece(SocketIOClient client, String data) {
        String sessionKey = data.split(",")[0];
        Long pieceID = Long.parseLong(data.split(",")[1]);
        Integer x = Integer.parseInt(data.split(",")[2]);
        Integer y = Integer.parseInt(data.split(",")[3]);
        Session session = sessionService.getSessionByKey(sessionKey);

        if (session != null) {
            UserAccount user = session.getUserAccount();
            Piece piece = pieceService.getPieceById(pieceID);
            if (piece != null) {
                Long chessID = piece.getBoard().getChess().getChessID();
                Chess game = chessService.getChessById(chessID);

                if (getRoomSize(String.valueOf(chessID)) == 2) {
                    if (game != null && game.isUserInGame(user)) {

                        game = pieceService.movePiece(pieceID, x, y);

                        String nextTurn = game.getWhitePlayer().equals(user) && piece.getColor().equals(Piece.Color.WHITE) ? "onNextTurnBlack" : "onNextTurnWhite";

                        server.getRoomOperations(String.valueOf(chessID)).sendEvent(nextTurn, game);
                    } else {
                        client.sendEvent("onError", "Invalid user or game");
                    }
                } else {
                    client.sendEvent("onError", "Room not ready");
                }
            } else {
                client.sendEvent("onError", "Piece not found");
            }
        } else { // If we didn't find a user
            client.sendEvent("onError", "Session not found");
        }
    }

    private int getRoomSize(String roomName) {
        return server.getRoomOperations(roomName).getClients().size();
    }
}