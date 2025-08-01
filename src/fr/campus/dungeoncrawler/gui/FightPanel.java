package fr.campus.dungeoncrawler.gui;

import fr.campus.dungeoncrawler.characters.Character;   // ← fixed line
import fr.campus.dungeoncrawler.enemies.Enemy;

import javax.swing.*;
import java.awt.*;

public class FightPanel extends JPanel {

    private final JTextArea log;
    private final Character player;
    private final Enemy enemy;

    public FightPanel(GameWindow window, Character player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;

        setLayout(new BorderLayout());

        log = new JTextArea();
        log.setEditable(false);
        add(new JScrollPane(log), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton attackBtn = new JButton("Attack");
        JButton fleeBtn   = new JButton("Flee");

        attackBtn.addActionListener(e -> {
            log.append("You attacked with " + player.getAttackLevel() + "!\n");
            enemy.setLifeLevel(enemy.getLifeLevel() - player.getAttackLevel());

            if (enemy.getLifeLevel() <= 0) {
                log.append("You defeated the enemy!\n");
                attackBtn.setEnabled(false);
                fleeBtn.setEnabled(false);
            } else {
                log.append("Enemy hits back with " + enemy.getAttackLevel() + "!\n");
                player.setLifeLevel(player.getLifeLevel() - enemy.getAttackLevel());

                if (player.getLifeLevel() <= 0) {
                    log.append("You were defeated...\n");
                    attackBtn.setEnabled(false);
                    fleeBtn.setEnabled(false);
                }
            }
        });

        fleeBtn.addActionListener(e -> {
            log.append("You fled the battle!\n");
            attackBtn.setEnabled(false);
            fleeBtn.setEnabled(false);
            // window.showGamePanel(...) if you later need to return
        });

        buttons.add(attackBtn);
        buttons.add(fleeBtn);
        add(buttons, BorderLayout.SOUTH);
    }
}