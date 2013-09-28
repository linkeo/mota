package com.nju.se.team.mota.temp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.script.ScriptException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.nju.se.team.mota.game.hud.ExperienceHUD;
import com.nju.se.team.mota.game.hud.HUDAlignment;
import com.nju.se.team.mota.game.hud.HUDDirection;
import com.nju.se.team.mota.game.hud.HUDPanel;
import com.nju.se.team.mota.game.hud.LeftUnitHUD;
import com.nju.se.team.mota.game.hud.RightUnitHUD;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Player;


public class TempUsage {
	public static void main(String[] argss) throws FileNotFoundException, ScriptException, NoSuchMethodException {
		final JFrame f = new JFrame("HUD Test");
		final JPanel contentPane = new JPanel();
		HUDPanel hudPane = new HUDPanel();
		final Player p = new Player();
		Creature e = Creature.make("碕霜", 0, 0, 0);
		p.setName("ホシぞラましろ");
		LeftUnitHUD player = new LeftUnitHUD(p);
		RightUnitHUD enemy = new RightUnitHUD(e);
		ExperienceHUD exp = new ExperienceHUD(p);
		
		contentPane.setBounds(0, 0, 960, 640);
		hudPane.setBounds(0, 0, 960, 640);
		player.setDepending(HUDDirection.IN, hudPane.getRootHUD());
		player.setAlignment(HUDAlignment.LEFT, HUDAlignment.TOP);
		enemy.setDepending(HUDDirection.IN, hudPane.getRootHUD());
		enemy.setAlignment(HUDAlignment.RIGHT, HUDAlignment.TOP);
		exp.setDepending(HUDDirection.IN, hudPane.getRootHUD());
		exp.setAlignment(HUDAlignment.CENTER, HUDAlignment.BOTTOM);
		hudPane.addHUD(player);
		hudPane.addHUD(enemy);
		hudPane.addHUD(exp);
		hudPane.adjustHUD();
		JPanel cp = (JPanel) f.getContentPane();
		cp.setLayout(null);
		cp.add(hudPane);
		cp.add(contentPane);
		int z = 0;
		cp.setComponentZOrder(hudPane, z++);
		cp.setComponentZOrder(contentPane, z++);
		f.setSize(960, 640);
		f.setVisible(true);
		f.setSize(960*2-cp.getWidth(), 640*2-cp.getHeight());
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Timer timer = new Timer(20, new ActionListener() {
			long i=0;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				i++;
				int r = (int) (0+i%256);
				int g = (int) (0+(i+256/3)%256);
				int b = (int) (0+(i+256*2/3)%256);
				contentPane.setBackground(new Color(r, g, b));
				p.setEXP(p.getEXP()+1);
				f.repaint();
			}
		});
		timer.start();
	}
}