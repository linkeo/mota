package com.nju.se.team.mota.util;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Warning extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PANEL_WIDTH_EX = 320, PANEL_HEIGHT_EX = 320;
	private static final int PANEL_WIDTH_ALM = 320, PANEL_HEIGHT_ALM = 180;
	
	private JTextArea tMsg = new JTextArea();
	private JScrollPane spMsg = new JScrollPane(tMsg);
	private JTextArea tMsg2 = new JTextArea();
	private JScrollPane spMsg2 = new JScrollPane(tMsg2);
	private JLabel label = new JLabel("MESSAGE", JLabel.CENTER);
	private JButton bOK = new JButton();
	//for alarming exception
	public Warning(String title, String message,
			StackTraceElement[] stackTraceElements, Window frame) {
		super(frame);
		setTitle(title);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);

		JPanel panel = (JPanel) getContentPane();
		panel.setSize(PANEL_WIDTH_EX, PANEL_HEIGHT_EX);
		panel.setLayout(null);
		panel.add(spMsg);
		panel.add(label);
		panel.add(spMsg2);
		panel.add(bOK);
		tMsg.setLineWrap(true);
		tMsg.setEditable(false);
		tMsg.setText(message);
		label.setText("MESSAGE / STACK TRACES");
		tMsg2.setLineWrap(true);
		tMsg2.setEditable(false);
		for(StackTraceElement e : stackTraceElements)
			tMsg2.append(e.toString()+"\n");
		bOK.setText("退出程序");
		bOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						System.exit(-1);
					}
				});
			}
		});
		spMsg.setBounds(10, 10, PANEL_WIDTH_EX - 20, (PANEL_HEIGHT_EX - 60) / 5);
		label.setBounds(10, (PANEL_HEIGHT_EX - 60) / 5 + 15, PANEL_WIDTH_EX - 20,
				20);
		spMsg2.setBounds(10, (PANEL_HEIGHT_EX - 60) / 5 + 40, PANEL_WIDTH_EX - 20,
				(PANEL_HEIGHT_EX - 60) / 5 * 3);
		bOK.setBounds(10, (PANEL_HEIGHT_EX - 60) / 5 * 4 + 50, PANEL_WIDTH_EX - 20,
				(PANEL_HEIGHT_EX - 60) / 5);
		setVisible(true);
		setSize(getWidth() - panel.getWidth() + PANEL_WIDTH_EX, getHeight()
				- panel.getHeight() + PANEL_HEIGHT_EX);
		setLocationRelativeTo(frame);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(false);
		setVisible(true);
	}
	//Form common alarming
	public Warning(String title, String msg, Window frame, boolean hasButton) {
		super(frame);
		setTitle(title);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);

		JPanel panel = (JPanel) getContentPane();
		panel.setSize(PANEL_WIDTH_EX, PANEL_HEIGHT_EX);
		panel.setLayout(null);
		panel.add(label);
		panel.add(spMsg);
		if(hasButton)
			panel.add(bOK);
		label.setText("MESSAGE");
		tMsg.setLineWrap(true);
		tMsg.setEditable(false);
		tMsg.setText(msg);
		if(hasButton){
			bOK.setText("OK, 知道了");
			bOK.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							Warning.this.dispose();
						}
					});
				}
			});
		}
		label.setBounds(10, 10, PANEL_WIDTH_ALM - 20, 20);
		spMsg.setBounds(10, 35, PANEL_WIDTH_ALM - 20, (PANEL_HEIGHT_ALM - 55) / 2);
		if(hasButton)
			bOK.setBounds(10, (PANEL_HEIGHT_ALM - 55) / 2 + 45, PANEL_WIDTH_ALM - 20,
					(PANEL_HEIGHT_ALM - 55) / 2);

		setVisible(true);
		if(hasButton)
			setSize(getWidth() - panel.getWidth() + PANEL_WIDTH_ALM, getHeight()
					- panel.getHeight() + PANEL_HEIGHT_ALM);
		else
			setSize(getWidth() - panel.getWidth() + PANEL_WIDTH_ALM, getHeight()
					- panel.getHeight() + PANEL_HEIGHT_ALM - (PANEL_HEIGHT_ALM - 55) / 2);
		setLocationRelativeTo(frame);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(false);
		setVisible(true);
	}
	public static Warning alarmException(Exception e, Window frame){
		return new Warning("警告: 程序异常","["+e.getClass().getName()+"]:"+e.getMessage(),e.getStackTrace(), frame);
	}
	public static Warning alarm(String title, String msg, Window frame){
		return new Warning("警告: "+title,msg, frame,true);
	}
	public static Warning alarmWithoutButton(String title, String msg, Window frame){
		Warning w =new Warning(title, msg, frame, false);
		w.setSize(w.getWidth(), w.getHeight()-(PANEL_WIDTH_ALM - 20)-10);
		w.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		w.bOK.setVisible(false);
		return w;
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		alarmException(new Exception("new Exception"),null);
		alarm("崩溃啦!", "大脑短路",null);
	}
}
