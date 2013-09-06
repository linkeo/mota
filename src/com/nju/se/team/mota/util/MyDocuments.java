package com.nju.se.team.mota.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class MyDocuments {
	public static Document getIntDocument(final int min, final int max){
		return new PlainDocument() {
			private static final long serialVersionUID = 1L;
			public void insertString(int offset, String str, AttributeSet attr)
					throws BadLocationException {
				if (str == null)
					return;
				try {
					int result = Integer
							.parseInt(getText(0, getLength()) + str);
					if (result <= max && result >= min)
						super.insertString(offset, str, attr);
					else if (result > max) {
						super.remove(0, getLength());
						super.insertString(0, String.valueOf(max), attr);
					}
					else if (result < min) {
						super.remove(0, getLength());
						super.insertString(0, String.valueOf(min), attr);
					}
				} catch (NumberFormatException e) {
					return;
				}
			}
		};
	}
	public static Document getDoubleDocument(final double min, final double max){
		return new PlainDocument() {
			private static final long serialVersionUID = 1L;
			public void insertString(int offset, String str, AttributeSet attr)
					throws BadLocationException {
				if (str == null)
					return;
				try {
					double result = Double.parseDouble(getText(0, getLength())
							+ str);
					if (result <= max && result >= min)
						super.insertString(offset, str, attr);
					else if (result > max) {
						super.remove(0, getLength());
						super.insertString(0, String.valueOf(max), attr);
					}
					else if (result < min) {
						super.remove(0, getLength());
						super.insertString(0, String.valueOf(min), attr);
					}
				} catch (NumberFormatException e) {
					return;
				}
			}
		};
	}
	public static Document getSizeLimitDocument(final int size){
		return new PlainDocument() {
			private static final long serialVersionUID = 1L;
			public void insertString(int offset, String str, AttributeSet attr)
					throws BadLocationException {
				if (str == null)
					return;
				if(getLength()>size){
					super.remove(0, getLength());
					super.insertString(0, getText(0, size), attr);
				}else if(getLength()+str.length()>size){
					super.insertString(offset, str.substring(0, size-getLength()), attr);
				}else
					super.insertString(offset, str, attr);
			}
		};
	}
}
