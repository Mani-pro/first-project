package textediter;

import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.*;
import java.awt.event.InputEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import org.w3c.dom.Document;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.color.*;
import javax.swing.JLayeredPane;

public class SimpleTextEditor {

	private JFrame frame;
	private JTextArea textArea;
	private File openfile;
	private final String title="Simple TextEditor";
	UndoManager um = new UndoManager();
	String Font="Arial",Style="13";
	int size = 13;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					
					SimpleTextEditor window = new SimpleTextEditor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SimpleTextEditor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(title);
		frame.setBounds(100, 100, 882, 618);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setFont(new Font(Font, 0, size));
		
		frame.getContentPane().add(textArea, BorderLayout.NORTH);
		
		textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
			
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				// TODO Auto-generated method stub
				um.addEdit(e.getEdit());
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JLayeredPane layeredPane = new JLayeredPane();
		scrollPane.setRowHeaderView(layeredPane);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Open");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open();
			}
		});
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Save As");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creat();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		mntmNewMenuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Close");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Undo");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				undo();
			}
		});
		mntmNewMenuItem_4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Redo");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				redo();
			}
		});
		mntmNewMenuItem_5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_4 = new JMenu("Theme");
		menuBar.add(mnNewMenu_4);
		
		JMenu mnNewMenu_2 = new JMenu("Font");
		mnNewMenu_2.setFont(new Font("Segoe UI", 0, 12));
		mnNewMenu_4.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_6_1 = new JMenuItem("Arial");
		mntmNewMenuItem_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Font="Arial";
				textArea.setFont(new Font(Font, 0, size));
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_6_1);
		
		JMenuItem mntmNewMenuItem_7_1 = new JMenuItem("Tohama");
		mntmNewMenuItem_7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Font="Tohama";
				textArea.setFont(new Font(Font, 0, size));
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7_1);
		
		JMenuItem mntmNewMenuItem_10_1 = new JMenuItem("Blackadder ITC");
		mntmNewMenuItem_10_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Font="Blackadder ITC";
				textArea.setFont(new Font(Font, 0, size));
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_10_1);
		
		JMenu mnNewMenu_3 = new JMenu("FontSize");
		mnNewMenu_4.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_8_12 = new JMenuItem("16");
		mntmNewMenuItem_8_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				size=16;
				textArea.setFont(new Font(Font, 0, size));
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8_12);
		
		JMenuItem mntmNewMenuItem_8_1_1 = new JMenuItem("18");
		mntmNewMenuItem_8_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				size=18;
				textArea.setFont(new Font(Font, 0, size));
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8_1_1);
		
		JMenuItem mntmNewMenuItem_8_2_1 = new JMenuItem("20");
		mntmNewMenuItem_8_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				size=20;
				textArea.setFont(new Font(Font, 0, size));
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8_2_1);
		
		JMenuItem mntmNewMenuItem_8_3_1 = new JMenuItem("22");
		mntmNewMenuItem_8_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				size=22;
				textArea.setFont(new Font(Font, 0, size));
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8_3_1);
		
		JMenuItem mntmNewMenuItem_8_4_1 = new JMenuItem("24");
		mntmNewMenuItem_8_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				size=23;
				textArea.setFont(new Font(Font, 0, size));
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8_4_1);
		
		JMenuItem mntmNewMenuItem_8_5_1 = new JMenuItem("26");
		mntmNewMenuItem_8_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				size=24;
				textArea.setFont(new Font(Font, 0, size));
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8_5_1);
		
		JMenuItem mntmNewMenuItem_8_6_1 = new JMenuItem("28");
		mntmNewMenuItem_8_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				size=28;
				textArea.setFont(new Font(Font, 0, size));
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8_6_1);
		
		JMenuItem mntmNewMenuItem_8_7_1 = new JMenuItem("30");
		mntmNewMenuItem_8_7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				size=30;
				textArea.setFont(new Font(Font, 0, size));
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8_7_1);
		
		JMenuItem mntmNewMenuItem_8_8_1 = new JMenuItem("32");
		mntmNewMenuItem_8_8_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				size=32;
				textArea.setFont(new Font(Font, 0, size));
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8_8_1);
		
		JMenuItem mntmNewMenuItem_8_9_1 = new JMenuItem("34");
		mntmNewMenuItem_8_9_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				size=34;
				textArea.setFont(new Font(Font, 0, size));
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8_9_1);
		
		JMenuItem mntmNewMenuItem_8_10_1 = new JMenuItem("36");
		mntmNewMenuItem_8_10_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				size=36;
				textArea.setFont(new Font(Font, 0, size));
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8_10_1);
		
		JMenuItem mntmNewMenuItem_8_11_1 = new JMenuItem("38");
		mntmNewMenuItem_8_11_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				size=38;
				textArea.setFont(new Font(Font, 0, size));
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8_11_1);
		
		JMenuItem mntmNewMenuItem_9_1 = new JMenuItem("40");
		mntmNewMenuItem_9_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				size=40;
				textArea.setFont(new Font(Font, 0, size));
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_9_1);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Back ground Color");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcolor();
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Text Color");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcolor2();
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_7);
		
		
	}

	private void open(){
		try {
			JFileChooser chooser=new JFileChooser();
			chooser.setDialogTitle("select a file to open");
			chooser.showOpenDialog(null);
			
			openfile = chooser.getSelectedFile();
			if(openfile==null&&!openfile.exists()) {
				JOptionPane.showMessageDialog(null, "Failed to open: you're file dose not exist","ERROR",JOptionPane.ERROR_MESSAGE);
				openfile=null;
				return;
			}
			
			Scanner reader=new Scanner(openfile);
			String cntence="";
			
			while(reader.hasNextLine()) {
				
				cntence+=reader.nextLine();
				
			}
			
			reader.close();
			textArea.setText(cntence);
			
			frame.setTitle(title+"-"+openfile.getName());
			
		}catch(Exception e){
			
		}
	}
	private void creat(){
		try {
			JFileChooser chooser=new JFileChooser();
			chooser.setDialogTitle("select the saving place");
			chooser.showSaveDialog(null);
			openfile = chooser.getSelectedFile();
			
			Formatter form = new Formatter(openfile);
			String contnc = textArea.getText();
			form.format("%s", contnc);
			form.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private void save(){
		try {
			if(openfile==null) {
				JOptionPane.showMessageDialog(null, "Failed to save: you are not selected a file","ERROR",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
			String contens = textArea.getText();
			Formatter form = new Formatter(openfile);
			form.format("%s", contens);
			form.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private void close(){
		if(openfile==null) {
			JOptionPane.showMessageDialog(null, "Failed to close: you are not selected a file","ERROR",JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
		int input=	JOptionPane.showConfirmDialog(null, "Do you want save befor exit?","Warning",JOptionPane.YES_NO_OPTION);
		
		if(input==JOptionPane.YES_OPTION) {
			save();
		}
		openfile=null;
		frame.setTitle(title);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void undo() {
		um.undo();
	}
	
	public void redo() {
		um.redo();
	}
	
	public void jcolor() {
		Color d= Color.white;
		JFrame g = new JFrame();
		g.setSize(300, 300);
		Color color=JColorChooser.showDialog(g, "Select a color", d);
		textArea.setBackground(color);
	} 
	
	public void jcolor2() {
		Color d= Color.white;
		JFrame k = new JFrame();
		k.setSize(300, 300);
		Color color=JColorChooser.showDialog(k, "Select a color", d);
		textArea.setForeground(color);
	}
}
