package fr.campus.dungeoncrawler.gui;

import fr.campus.dungeoncrawler.characters.Character;
import fr.campus.dungeoncrawler.enemies.Enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FightPanel extends JDialog {

    private static final long serialVersionUID = 1L;

    private final JTextArea log;
    private final JLabel statsLabel;
    private final JButton attackBtn;
    private final JButton fleeBtn;

    private Character player;
    private Enemy enemy;
    private FightResult result;

    public enum FightResult {
        PLAYER_DEAD,
        ENEMY_DEAD,
        PLAYER_FLED
    }

    public FightPanel() {
        // no parent frame used (null owner). Modal dialog by passing true to super.
        super((Frame) null, "Fight!", true);

        statsLabel = new JLabel("", SwingConstants.CENTER);
        log = new JTextArea(10, 40);
        log.setEditable(false);
        JScrollPane scroll = new JScrollPane(log);

        attackBtn = new JButton("Attack");
        fleeBtn = new JButton("Flee");

        JPanel top = new JPanel(new BorderLayout());
        top.add(statsLabel, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.add(attackBtn);
        buttons.add(fleeBtn);

        setLayout(new BorderLayout(8, 8));
        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /**
     * Shows the modal fight dialog and returns the result after the fight finishes.
     * This blocks until the dialog is disposed (but the dialog's UI stays responsive).
     */
    public FightResult startFight(Character player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.result = null;

        // Make sure we don't accumulate listeners if same FightPanel instance is reused.
        for (ActionListener al : attackBtn.getActionListeners()) attackBtn.removeActionListener(al);
        for (ActionListener al : fleeBtn.getActionListeners()) fleeBtn.removeActionListener(al);

        attackBtn.setEnabled(true);
        fleeBtn.setEnabled(true);

        attackBtn.addActionListener(e -> {
            log.append("You attack with " + player.getAttackLevel() + "!\n");
            enemy.setLifeLevel(enemy.getLifeLevel() - player.getAttackLevel());

            if (enemy.getLifeLevel() <= 0) {
                log.append("You defeated the " + enemy.getType() + "!\n");
                // keep your previous behaviour (you decreased attack in FightMenu)
                try { player.decreaseAttackLevel(1); } catch (Exception ignored) {}
                result = FightResult.ENEMY_DEAD;
                dispose(); // closes modal dialog; startFight() will return result
                return;
            }

            log.append("The " + enemy.getType() + " hits back with " + enemy.getAttackLevel() + "!\n");
            player.setLifeLevel(player.getLifeLevel() - enemy.getAttackLevel());
            log.append("Your remaining health: " + player.getLifeLevel() + "\n");

            if (player.getLifeLevel() <= 0) {
                log.append("You have been defeated...\n");
                result = FightResult.PLAYER_DEAD;
                dispose();
                return;
            }

            updateStats();
        });

        fleeBtn.addActionListener(e -> {
            log.append("You fled the battle!\n");
            result = FightResult.PLAYER_FLED;
            dispose();
        });

        // initial text + stats
        log.setText("");
        log.append("-------- Fight Started! -----------\n");
        log.append("Enemy: " + enemy.getType() + " (Atk: " + enemy.getAttackLevel() + " Life: " + enemy.getLifeLevel() + ")\n");
        log.append("You   : (Atk: " + player.getAttackLevel() + " Life: " + player.getLifeLevel() + ")\n\n");
        updateStats();

        // show modal dialog (blocks until dispose)
        setVisible(true);

        // when setVisible returns, dialog is disposed and result has been set by listener
        return result;
    }

    private void updateStats() {
        if (player == null || enemy == null) {
            statsLabel.setText("");
            return;
        }
        statsLabel.setText(
                "You: Life=" + player.getLifeLevel() + "  Atk=" + player.getAttackLevel()
                        + "  |  " + enemy.getType() + ": Life=" + enemy.getLifeLevel() + "  Atk=" + enemy.getAttackLevel()
        );
    }
}
