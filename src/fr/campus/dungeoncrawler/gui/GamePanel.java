package fr.campus.dungeoncrawler.gui;


import fr.campus.dungeoncrawler.characters.Character;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import fr.campus.dungeoncrawler.enemies.Dragon;
import fr.campus.dungeoncrawler.game_engine.Board;
import fr.campus.dungeoncrawler.game_engine.Tile;
import fr.campus.dungeoncrawler.normal_tiles.EnemyTile;
import fr.campus.dungeoncrawler.surprise_tiles.Potion;
import fr.campus.dungeoncrawler.surprise_tiles.Spell;
import fr.campus.dungeoncrawler.surprise_tiles.Weapon;
import fr.campus.dungeoncrawler.enemies.Enemy;

// Displays player stats and "start" game
public class GamePanel extends JPanel {
    private final int boardSize = Board.BOARD_SIZE;
    private final int gridSize = (int) Math.sqrt(boardSize);
    private JPanel boardPanel;
    private JLabel[][] tiles = new JLabel[gridSize][gridSize];
    private Board board;
    private Map<String, ImageIcon> enemyIcons = new HashMap<>();

    // Icons for the player and tile types
    private ImageIcon playerIcon;
    private ImageIcon potionIcon;

    private ImageIcon swordIcon;
    private ImageIcon maceIcon;


    private ImageIcon enemyIcon;
    private ImageIcon smallPotionIcon;
    private ImageIcon bigPotionIcon;

    private ImageIcon fireballSpell;
    private ImageIcon thunderSpell;



    public GamePanel(MainWindow window, Character player, Board board) {

        this.board = board;
        setLayout(new BorderLayout());
        Tile[] boardTiles = board.getTiles();

        int tilePixelSize = 750 / gridSize;
        loadEnemyIcons(tilePixelSize);

        playerIcon = scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/warrior-small.png"), tilePixelSize, tilePixelSize);

        swordIcon = scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/sword.png"), tilePixelSize, tilePixelSize);
        maceIcon = scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/mace.png"), tilePixelSize, tilePixelSize);

        smallPotionIcon = scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/small-potion.png"), tilePixelSize, tilePixelSize);
        bigPotionIcon = scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/big-potion.png"), tilePixelSize, tilePixelSize);

        fireballSpell = scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/fireball.png"), tilePixelSize, tilePixelSize);
        thunderSpell = scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/thunder.png"), tilePixelSize, tilePixelSize);


        // Create the board panel
        boardPanel = new JPanel(new GridLayout(gridSize, gridSize));
        boardPanel.setPreferredSize(new Dimension(600, 600)); // size of the board

        // Initialize tiles
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                JLabel tile = new JLabel();
                tile.setOpaque(true);
                tile.setBackground(Color.GREEN); // default background
                tile.setHorizontalAlignment(SwingConstants.CENTER);
                tile.setVerticalAlignment(SwingConstants.CENTER);
                tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tiles[row][col] = tile;
                boardPanel.add(tile);
            }
        }

        add(boardPanel, BorderLayout.CENTER);
        refreshBoard();
    }

    public void refreshBoard() {
        Tile[] flatTiles = board.getTiles();
        int playerPos = board.getPlayerPosition();

        for (int i = 0; i < boardSize; i++) {
            int row = i / gridSize;
            int col = i % gridSize;

            JLabel tile = tiles[row][col];
            tile.setIcon(null); // Clear previous image
            tile.setBackground(Color.GREEN); // Reset background

            Tile currentTile = flatTiles[i];

            if (i == playerPos) {
                tile.setIcon(playerIcon);
            } else if (currentTile instanceof EnemyTile enemyTile) {
                Enemy enemy = enemyTile.getEnemy();
                if (enemy != null) {
                    String enemyType = enemy.getType();
                    ImageIcon icon = enemyIcons.get(enemyType);
                    tile.setIcon(icon != null ? icon : enemyIcon);
                }
            } else if (currentTile instanceof Potion potion) {
                switch (potion.getPotionType()) {
                    case SMALL -> tile.setIcon(smallPotionIcon);
                    case BIG -> tile.setIcon(bigPotionIcon);
                }

            } else if (currentTile instanceof Spell spell) {
                switch (spell.getSpellType()) {
                    case FIREBALL -> tile.setIcon(fireballSpell);
                    case THUNDER -> tile.setIcon(thunderSpell);
                }
            }
            else if (currentTile instanceof Weapon weapon) {
                switch (weapon.getWeaponType()) {
                    case SWORD -> tile.setIcon(swordIcon);
                    case MACE -> tile.setIcon(maceIcon);
                }
            } else {
                tile.setIcon(null); // No icon for unknown/empty tile
            }
        }

        revalidate();
        repaint();
    }

    private ImageIcon loadIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find image file: " + path);
            return null;
        }
    }

    private ImageIcon scaleIcon(ImageIcon originalIcon, int width, int height) {
        if (originalIcon == null) return null;
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private void loadEnemyIcons(int tilePixelSize) {
        enemyIcons.put("Goblin", scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/goblin.png"), tilePixelSize, tilePixelSize));
        enemyIcons.put("Orc", scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/orc.png"), tilePixelSize, tilePixelSize));
        enemyIcons.put("Warlock", scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/warlock.png"), tilePixelSize, tilePixelSize));
        enemyIcons.put("EvilSpirit", scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/evilspirit.png"), tilePixelSize, tilePixelSize));
        enemyIcons.put("Dragon", scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/dragon.png"), tilePixelSize, tilePixelSize));
    }
}
