package com.nju.se.team.mota.temp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.script.ScriptException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.nju.se.team.mota.game.hud.HUDAlignment;
import com.nju.se.team.mota.game.hud.HUDDirection;
import com.nju.se.team.mota.game.hud.HUDPanel;
import com.nju.se.team.mota.game.hud.LeftUnitHUD;
import com.nju.se.team.mota.game.unit.Creature;


public class TempUsage {
	public static void main(String[] argss) throws FileNotFoundException, ScriptException, NoSuchMethodException {
		final JFrame f = new JFrame("HUD Test");
		final JPanel contentPane = new JPanel();
		HUDPanel hudPane = new HUDPanel();
		Creature p = Creature.make("Íæ¼Ò", 0, 0, 0);
		p.setName("Ap~Íæ¾Í");
		LeftUnitHUD player = new LeftUnitHUD(p);
		
		contentPane.setBounds(0, 0, 960, 640);
		hudPane.setBounds(0, 0, 960, 640);
		player.setDepending(HUDDirection.IN, hudPane.getRootHUD());
		player.setAlignment(HUDAlignment.LEFT, HUDAlignment.RIGHT);
		hudPane.addHUD(player);
		hudPane.adjustHUD();
		JPanel cp = (JPanel) f.getContentPane();
		cp.setLayout(null);
		cp.add(hudPane);
		cp.add(contentPane);
		int z = 0;
		cp.setComponentZOrder(hudPane, z++);
		cp.setComponentZOrder(contentPane, z++);
		f.setSize(966, 664);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Timer timer = new Timer(20, new ActionListener() {
			long i=0;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				i++;
				int r = (int) (63+i%128);
				int g = (int) (63+(i+128/3)%128);
				int b = (int) (63+(i+128*2/3)%128);
				contentPane.setBackground(new Color(r, g, b));
				f.repaint();
			}
		});
		timer.start();
	}
}