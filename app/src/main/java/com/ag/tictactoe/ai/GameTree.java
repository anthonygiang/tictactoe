package com.ag.tictactoe.ai;

import com.ag.tictactoe.controller.GameBoardController;
import com.ag.tictactoe.controller.GameController;
import com.ag.tictactoe.controller.PlayerController;
import com.ag.tictactoe.controller.TurnController;
import com.ag.tictactoe.model.Player;
import com.ag.tictactoe.model.Tile;

import java.util.List;

/**
 * Class will use depth first search and determine all possible outcomes for the game. This new
 * will then be used to determine the best move the AI needs to take to try and win the game.
 */
public class GameTree {

    /**
     * The initial state of the game when the AI is prompted to make a move.
     * This is the root of the GameTree.
     */
    private GameTreeNode initialGameTreeNode;

    /**
     * Constructor sets the initial GameController.
     * @param gc
     */
    public GameTree(GameController gc) {
        initialGameTreeNode = new GameTreeNode(gc);
    }

    /**
     * Returns the initial GameTreeNode. This is the root of the GameTree.
     * @return
     */
    public GameTreeNode getInitialGameTreeNode() {
        return initialGameTreeNode;
    }

    /**w
     * Uses depth first search to populate the GameTree.
     *
     * @param node
     */
    private void buildGameTree(GameTreeNode node) {

        GameController gc = node.getGameController();
        GameBoardController gbc = gc.getGameBoardController();
        PlayerController pc = gc.getPlayerController();
        TurnController tc = gc.getTurnController();

        Player player = tc.getPlayerTurn(pc.getPlayers());

        List<Tile> emptyTileList = gbc.getEmptyTileList();


        for(Tile tile : emptyTileList) {
            gc.makePlayerMove(tile);
            if(gbc.getWinConditionForPlayer(player)) {
                node.setWinNode(true);
            }
            if(gbc.getStalemateCondition()) {
                node.setTieNode(true);
            }
            else {
                tc.changeTurn(pc.getPlayers());
                // TODO recurse
                GameTreeNode newNode = new GameTreeNode(gc);
                node.addChildTreeNode(newNode);
                buildGameTree(newNode);
            }

        }
    }

    public void getBestTileMove() {

        buildGameTree(initialGameTreeNode);



    }

    /**
     * Returns a Tile that the AI will place a GamePiece on.
     *
     *
     * @param node
     * @return
     */
    public Tile determinePossibleMove(GameTreeNode node) {

        GameController gc = node.getGameController();
        GameBoardController gbc = gc.getGameBoardController();

        List<Tile> emptyTileList = gbc.getEmptyTileList();

        if(!emptyTileList.isEmpty()) {
            return emptyTileList.get(0);
        }
        return null;
    }



}
