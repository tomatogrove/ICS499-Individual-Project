package com.team6.socketIO;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.team6.model.Chess;
import com.team6.model.pieces.Piece;
import com.team6.model.util.Session;
import com.team6.model.util.UserAccount;
import com.team6.services.BoardService;
import com.team6.services.ChessService;
import com.team6.services.PieceService;
import com.team6.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

        server.addDisconnectListener(client -> {
            System.out.println("Client disconnected: " + client.getSessionId());
        });

        server.addEventListener("joinGame", String.class, (client, data, ackRequest) -> joinGame(client, data));
        server.addEventListener("movePiece", String.class, (client, data, ackRequest) -> movePiece(client, data));
        server.addEventListener("forfeitGame", String.class, (client, data, ackRequest) -> forfeitGame(client, data));
        server.addEventListener("leaveGame", String.class, (client, data, ackRequest) -> leaveGame(data));
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
                game.setWhitePlayer(user);

                client.sendEvent("onJoinGame", game);
                client.joinRoom(chessID);
            } else { // If we are joining an existing game
                Chess game = chessService.getChessById(Long.parseLong(chessID));

                if (game != null) {
                    if (game.getStatus().equals(Chess.Status.DONE)) {
                        client.sendEvent("onGameEnd", "This game is already over");
                        return;
                    }
                    if (!game.isUserInGame(user) && game.needsPlayer()) {
                        game.setBlackPlayerID(user.getUserAccountID());
                        game = chessService.updateChess(game);
                        game.setBlackPlayer(user);
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
                        if (game.getStatus().equals(Chess.Status.DONE)) {
                            server.getRoomOperations(String.valueOf(chessID)).sendEvent("onGameEnd", user.getUsername());
                        } else {
                            String nextTurn = game.getWhitePlayerID().equals(user.getUserAccountID()) && piece.getColor().equals(Piece.Color.WHITE) ? "onNextTurnBlack" : "onNextTurnWhite";
                            server.getRoomOperations(String.valueOf(chessID)).sendEvent(nextTurn, game);
                        }
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

    private void forfeitGame(SocketIOClient client, String data) {
        String sessionKey = data.split(",")[0];
        String chessID = data.split(",")[1];
        Session session = sessionService.getSessionByKey(sessionKey);

        if (session != null) { // we've found a user
            UserAccount user = session.getUserAccount();
            Chess chess = chessService.getChessById(Long.parseLong(chessID));

            if (chess != null) { // game exists
                Piece.Color winnerColor = chess.getWhitePlayerID().equals(user.getUserAccountID()) ? Piece.Color.BLACK : Piece.Color.WHITE;
                chess.setStatus(Chess.Status.DONE);
                chess.setWinnerByColor(winnerColor);
                chessService.updateChess(chess);

                server.getRoomOperations(chessID).sendEvent("onGameEnd", user.getUsername());
            } else {
                client.sendEvent("onError", "Game not found");
            }
        } else {
            client.sendEvent("onError", "Session not found");
        }
    }

    private void leaveGame(String data) {
        String sessionKey = data.split(",")[0];
        String chessID = data.split(",")[1];
        Session session = sessionService.getSessionByKey(sessionKey);

        server.getRoomOperations(chessID).sendEvent("onLeaveGame", session.getUserAccount().getUserAccountID());
        server.getRoomOperations(chessID).disconnect();

        Chess chess = chessService.getChessById(Long.parseLong(chessID));
        if (chess != null && chess.needsPlayer()) {
            chessService.deleteChessById(Long.parseLong(chessID));
        }
    }

    private int getRoomSize(String roomName) {
        return server.getRoomOperations(roomName).getClients().size();
    }
}