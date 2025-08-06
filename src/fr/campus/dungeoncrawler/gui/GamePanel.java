package fr.campus.dungeoncrawler.gui;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.enemies.Enemy;
import fr.campus.dungeoncrawler.game_engine.Board;
import fr.campus.dungeoncrawler.game_engine.Tile;
import fr.campus.dungeoncrawler.normal_tiles.EnemyTile;
import fr.campus.dungeoncrawler.surprise_tiles.Potion;
import fr.campus.dungeoncrawler.surprise_tiles.Spell;
import fr.campus.dungeoncrawler.surprise_tiles.Weapon;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel {
    private final Board board;
    private final Character player;
    private final MainWindow mainWindow;

    private JLabel statsLabel;
    private JTextArea logArea;
    private JButton rollDiceButton;
    private JPanel boardGrid;

    private final int TILE_SIZE = 64;
    private final int GRID_WIDTH = 8;

    private ImageIcon playerIcon;
    private final Map<String, ImageIcon> enemyIcons = new HashMap<>();
    private ImageIcon swordIcon, maceIcon, smallPotionIcon, bigPotionIcon, fireballSpell, thunderSpell;

    public GamePanel(MainWindow mainWindow, Character player, Board board) {
        this.mainWindow = mainWindow;
        this.player = player;
        this.board = board;

        int gridSize = board.getTiles().length;
        int tilePixelSize = 600 / GRID_WIDTH;

        loadIcons(tilePixelSize);

        setLayout(new BorderLayout());

        // Top stats panel
        statsLabel = new JLabel();
        updateStats();
        add(statsLabel, BorderLayout.NORTH);

        // Center board panel
        boardGrid = new JPanel(new GridLayout(0, GRID_WIDTH));
        drawBoard();
        add(boardGrid, BorderLayout.CENTER);

        // Bottom panel (dice + log)
        JPanel bottomPanel = new JPanel(new BorderLayout());

        rollDiceButton = new JButton("🎲 Roll Dice");
        rollDiceButton.addActionListener(e -> handlePlayerMove());
        bottomPanel.add(rollDiceButton, BorderLayout.WEST);

        logArea = new JTextArea(5, 40);
        logArea.setEditable(false);
        bottomPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void loadIcons(int tilePixelSize) {
        playerIcon = scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/warrior-small.png"), tilePixelSize, tilePixelSize);

        swordIcon = scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/sword.png"), tilePixelSize, tilePixelSize);
        maceIcon = scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/mace.png"), tilePixelSize, tilePixelSize);

        smallPotionIcon = scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/small-potion.png"), tilePixelSize, tilePixelSize);
        bigPotionIcon = scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/big-potion.png"), tilePixelSize, tilePixelSize);

        fireballSpell = scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/fireball.png"), tilePixelSize, tilePixelSize);
        thunderSpell = scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/thunder.png"), tilePixelSize, tilePixelSize);

        // Enemy icons by type
        enemyIcons.put("Goblin", scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/goblin.png"), tilePixelSize, tilePixelSize));
        enemyIcons.put("Orc", scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/orc.png"), tilePixelSize, tilePixelSize));
        enemyIcons.put("Warlock", scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/warlock.png"), tilePixelSize, tilePixelSize));
        enemyIcons.put("EvilSpirit", scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/evilspirit.png"), tilePixelSize, tilePixelSize));
        enemyIcons.put("Dragon", scaleIcon(loadIcon("/fr/campus/dungeoncrawler/images/dragon.png"), tilePixelSize, tilePixelSize));
    }

    private void drawBoard() {
        boardGrid.removeAll();
        Tile[] tiles = board.getTiles();
        int playerPos = board.getPlayerPosition();

        for (int i = 0; i < tiles.length; i++) {
            JLabel tileLabel = new JLabel();
            tileLabel.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
            tileLabel.setHorizontalAlignment(JLabel.CENTER);
            tileLabel.setVerticalAlignment(JLabel.CENTER);
            tileLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // ✅ Add this

            ImageIcon iconToSet = null;

            if (i == playerPos) {
                iconToSet = playerIcon;
            } else if (tiles[i] instanceof EnemyTile enemyTile) {
                Enemy enemy = enemyTile.getEnemy();
                if (enemy != null) {
                    iconToSet = enemyIcons.get(enemy.getType());
                }
            } else if (tiles[i] instanceof Potion potion) {
                iconToSet = switch (potion.getPotionType()) {
                    case SMALL -> smallPotionIcon;
                    case BIG -> bigPotionIcon;
                };
            } else if (tiles[i] instanceof Spell spell) {
                iconToSet = switch (spell.getSpellType()) {
                    case FIREBALL -> fireballSpell;
                    case THUNDER -> thunderSpell;
                };
            } else if (tiles[i] instanceof Weapon weapon) {
                iconToSet = switch (weapon.getWeaponType()) {
                    case SWORD -> swordIcon;
                    case MACE -> maceIcon;
                };
            }

            if (iconToSet != null) {
                Image scaled = iconToSet.getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH);
                tileLabel.setIcon(new ImageIcon(scaled));
            }

            boardGrid.add(tileLabel);
        }

        boardGrid.revalidate();
        boardGrid.repaint();
    }


    private void handlePlayerMove() {
        appendLog("🎲 Rolling the dice...");
        boolean gameOver = board.movePlayer();

        if (gameOver) {
            appendLog("🎉 Game Over!");
            rollDiceButton.setEnabled(false);
        } else {
            appendLog("📍 Moved to tile " + board.getPlayerPosition());
        }

        updateStats();
        drawBoard();
    }

    private void updateStats() {
        statsLabel.setText(" Life: " + player.getLifeLevel() + " | ⚔️ Attack: " + player.getAttackLevel());
    }

    private void appendLog(String msg) {
        logArea.append(msg + "\n");
    }

    private ImageIcon loadIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) return new ImageIcon(imgURL);
        System.err.println("Couldn't find image file: " + path);
        return null;
    }

    private ImageIcon scaleIcon(ImageIcon originalIcon, int width, int height) {
        if (originalIcon == null) return null;
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
