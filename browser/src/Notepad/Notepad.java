package Notepad;

import java.awt.event.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.undo.UndoManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;
	
public class Notepad {
	private static final int x = 1000;
	private static final int y = 600;
	private static String file = "Untitled";
	private JFrame    Frame,frameFind;
	private JMenuBar  menuBar;
	private JTextArea textArea;
	private JMenu 	  File, Edit, Format, View, Help;
	private JMenuItem filemenuItemNew, filemenuItemSave,
			  		  filemenuItemSaveAs, filemenuItemOpen,
			  		  filemenuItemPageSetup, filemenuItemPrint,
			  		  filemenuItemExit;
	private JMenuItem editMenuItemUndo, editMenuItemCut,
					  editMenuItemCopy, editMenuItemPaste,
					  editMenuItemDelete, editMenuItemFind,
					  editMenuItemFindNext, editMenuItemReplace,
					  editMenuItemGoto, editMenuItemSelectAll,
					  editMenuItemTimeDate;
	private JMenuItem formatMenuItemWordWrap, formatMenuItemFont;
	private JMenuItem viewMenuItemStatusBar;
	private JMenuItem helpMenuItemViewHelp, helpMenuItemAboutNotepad;
	private JFileChooser fileChooser;
	private JOptionPane dialogBox;
	private ImageIcon icon = new ImageIcon("E:\\abhilash\\workspace\\browser\\bin\\Assets\\search.jpg");
	private UndoManager um;
	private JScrollPane jSP;
	
	public Notepad(){
		 
		 Frame = new JFrame(file +" - Notepad");
		 Frame.setLayout(new BorderLayout());
		 Frame.setIconImage(icon.getImage());
		 
		 frameFind = new JFrame();
		 
		 menuBar = new JMenuBar() ;
		 textArea = new JTextArea("");
		 dialogBox = new JOptionPane();
		 		 
		 Frame.add(textArea);
		 Frame.setVisible(true);
		 
		 jSP = new JScrollPane(textArea); 
		 jSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		 jSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		 Frame.add(jSP); 
		 
		 um = new UndoManager();
		 textArea.getDocument().addUndoableEditListener(um);
		 
		//Instantiating Menu BAr Items
		 File = new JMenu("File");
		 Edit = new JMenu("Edit");
		 Format = new JMenu("Format");
		 View = new JMenu("View");
		 Help = new JMenu("Help");
		 
				
		// FILE Menu Sub menu Items
		 filemenuItemNew = new JMenuItem("New");
		 filemenuItemOpen = new JMenuItem("Open");
		 filemenuItemSave = new JMenuItem("Save");
		 filemenuItemSaveAs = new JMenuItem("SaveAs");
		 filemenuItemPageSetup = new JMenuItem("PageSetup");
		 filemenuItemPrint = new JMenuItem("Print");
		 filemenuItemExit = new JMenuItem("Exit");
		
		// EDIT Menu Sub menu Items
		 editMenuItemUndo =new JMenuItem("Undo");
		 editMenuItemCut =new JMenuItem("Cut");
		 editMenuItemCopy =new JMenuItem("Copy");
		 editMenuItemPaste =new JMenuItem("Paste");
		 editMenuItemDelete =new JMenuItem("Delete");
		 editMenuItemFind =new JMenuItem("Find");
		 editMenuItemFindNext =new JMenuItem("FindNext");
		 editMenuItemReplace =new JMenuItem("Replace");
		 editMenuItemGoto =new JMenuItem("Goto");
		 editMenuItemSelectAll =new JMenuItem("SelectAll");
		 editMenuItemTimeDate =new JMenuItem("Time/Date");
		
		// FORMAT Menu Sub menu Items
		 formatMenuItemWordWrap = new JMenuItem("WordWrap");
		 formatMenuItemFont = new JMenuItem("Font...");
		
		// VIEW Menu Sub menu Items
		 viewMenuItemStatusBar = new JMenuItem("StatusBar");
		
		// HELP Menu Sub menu Items
		 helpMenuItemViewHelp = new JMenuItem("View Help");
		 helpMenuItemAboutNotepad = new JMenuItem("About NotePad");
		 
		//JFileChooser 
		 fileChooser = new JFileChooser();
		 fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		 
		//Miscellaneous Sub Menu items
		 exitListener el = new exitListener();
		 openListener ol = new openListener();
		 saveListener sl = new saveListener();
		 
		 cutListener cl = new cutListener();
		 copyListener col = new copyListener();
		 pasteListener pl = new pasteListener();
		 undoListener ul =new undoListener();
		 deleteListener dl = new deleteListener();
		 selectAllListener sal = new selectAllListener();
		 timeDateListener tdl = new timeDateListener();
		 findListener fl = new findListener();
		 wordWrapListener wwl = new wordWrapListener();
		 fontListener fol = new fontListener();
		 statusBarListener sbl = new statusBarListener();
		 viewHelpListener vhl = new viewHelpListener();
		 aboutNotepadListener anl = new aboutNotepadListener();
		 
		 
		 editMenuItemCut.addActionListener(cl);
		 editMenuItemCopy.addActionListener(col);
		 editMenuItemPaste.addActionListener(pl);
		 editMenuItemUndo.addActionListener(ul);		 
		 editMenuItemDelete.addActionListener(dl);
		 editMenuItemSelectAll.addActionListener(sal);
		 editMenuItemTimeDate.addActionListener(tdl);
		 editMenuItemFind.addActionListener(fl);
		 editMenuItemFindNext.addActionListener(fl);
		 
		 formatMenuItemWordWrap.addActionListener(wwl);
		 formatMenuItemFont.addActionListener(fol);
		 
		 viewMenuItemStatusBar.addActionListener(sbl);
		 
		 helpMenuItemViewHelp.addActionListener(vhl);
		 helpMenuItemAboutNotepad.addActionListener(anl);
		 
		 
		 filemenuItemNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String ObjButtons[] = {"Save","Don't Save","Cancel"};
				
				int PromptResult = JOptionPane.showOptionDialog(Frame,"Do You want to save your "
																	+file+"?",
									"Notepad",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.WARNING_MESSAGE, icon, 
									ObjButtons,ObjButtons[0]);
				if(!textArea.equals("")){
					if(PromptResult==JOptionPane.YES_OPTION){
						fileChooser.showSaveDialog(Frame);
						if(fileChooser.showSaveDialog(Frame)== fileChooser.APPROVE_OPTION){
							textArea.setText("");						}
					}
					else if(PromptResult == JOptionPane.NO_OPTION){
						System.exit(0);					
					}			
						
					}
					textArea.setText("");
				}
			});
		 
		 filemenuItemOpen.addActionListener(ol);
		 filemenuItemSave.addActionListener(sl);
		 filemenuItemSaveAs.addActionListener(sl);
		 filemenuItemExit.addActionListener(el);	
		
		//Adding Menu Bar Items
		 menuBar.add(File);
		 menuBar.add(Edit);
		 menuBar.add(Format);
		 menuBar.add(View);
		 menuBar.add(Help);
		
		//ADDIng Menu Item List ------FILE------
		File.add(filemenuItemNew);
		File.add(filemenuItemOpen);
		File.add(filemenuItemSave);
		File.add(filemenuItemSaveAs);
		File.addSeparator();
		File.add(filemenuItemPageSetup);
		File.add(filemenuItemPrint);
		File.addSeparator();
		File.add(filemenuItemExit);
		
		//ADDIng Menu Item List ------EDIT------
		Edit.add(editMenuItemUndo);
		Edit.addSeparator();
		Edit.add(editMenuItemCut);
		Edit.add(editMenuItemCopy);
		Edit.add(editMenuItemPaste);
		Edit.add(editMenuItemDelete);
		Edit.addSeparator();
		Edit.add(editMenuItemFind);
		Edit.add(editMenuItemFindNext);
		Edit.add(editMenuItemReplace);
		Edit.add(editMenuItemGoto);
		Edit.addSeparator();
		Edit.add(editMenuItemSelectAll);
		Edit.add(editMenuItemTimeDate);
		
		//ADDIng Menu Item List ------FORMAT------
		Format.add(formatMenuItemWordWrap);
		Format.add(formatMenuItemFont);
		
		//ADDIng Menu Item List ------VIEW------
		View.add(viewMenuItemStatusBar);
		
		//ADDIng Menu Item List ------HELP------
		Help.add(helpMenuItemViewHelp);
		Help.add(helpMenuItemAboutNotepad);
						
		Frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) {
				String ObjButtons[] = {"Save","Don't Save","Cancel"};
				File file = fileChooser.getSelectedFile();
				int PromptResult = JOptionPane.showOptionDialog(Frame,"Do you want save changes to "
																	+file+" ?"
																,"Notepad",
																JOptionPane.YES_NO_CANCEL_OPTION,
																JOptionPane.WARNING_MESSAGE,
																icon, ObjButtons,ObjButtons[0]);
				if(!textArea.equals("")){
				if(PromptResult == JOptionPane.YES_OPTION ){
					fileChooser.showSaveDialog(Frame);
					if(fileChooser.showSaveDialog(Frame)== fileChooser.APPROVE_OPTION){
						System.exit(0);
					}
				}
				else if(PromptResult == JOptionPane.NO_OPTION){
					System.exit(0);					
				}			
				}
				else System.exit(0);
			}
		});

		Frame.setJMenuBar(menuBar);
		Frame.setSize(x, y);
		Frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}			

		class saveWindowListener  implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
					String ObjButtons[] = {"Save","Don't Save","Cancel"};
					File file = fileChooser.getSelectedFile();
					int PromptResult = JOptionPane.showOptionDialog(Frame,"Do you want save changes to "
																	+file.getName().toString()+"?"
																	,"Notepad",
																	JOptionPane.YES_NO_CANCEL_OPTION,
																	JOptionPane.WARNING_MESSAGE,
																	icon, ObjButtons,ObjButtons[0]);
					if(PromptResult == JOptionPane.YES_OPTION && !textArea.equals("")){
						fileChooser.showSaveDialog(Frame);
						if(fileChooser.showSaveDialog(Frame)== fileChooser.APPROVE_OPTION){
							
						}
					}
					else if(PromptResult == JOptionPane.NO_OPTION){
						textArea.setText("");
						
					}
					else System.exit(0);
				}
			}	

		class exitListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		}
		
		class cutListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				textArea.cut();
			}
	    }
		
		class copyListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				textArea.copy();
			}
	    }
		
		class pasteListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				textArea.paste();
			}
	    }
		
		class undoListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(um.canUndo()){
					um.undo();
				}else{
					warn("Can't undo");
				}
			}
	    }
		
		class deleteListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText().replace(textArea.getSelectedText(), ""));
			}
		}
		
		class selectAllListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				textArea.selectAll();
			}
		}
	
void warn(String msg) {
    JOptionPane.showMessageDialog(null, "Warning: " + msg, "Warning",
    			                  JOptionPane.WARNING_MESSAGE);
  }
		class timeDateListener implements ActionListener{
			Date d = new Date();
			public void actionPerformed(ActionEvent e) {
				textArea.append(d.toString());
			}
		}
		
		class findListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog(Frame, "Find What:");
			}
		}
		
		class wordWrapListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		class fontListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		class statusBarListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				textArea.setSize(1000,580);
			}
		}
		class viewHelpListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		class aboutNotepadListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
			}
		}

		class saveListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				if(fileChooser.showSaveDialog(Frame)== fileChooser.APPROVE_OPTION){
					File file = fileChooser.getSelectedFile();
					Frame.setTitle(file.getName().toString()+" - Notepad");
					PrintWriter out = null;
					try {
						out = new PrintWriter(file);
						String output = textArea.getText();
						System.out.println(output);
						out.println(output);
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						try {out.flush();} catch(Exception ex1) {}
						try {out.close();} catch(Exception ex1) {}
					}
				}
			}
		}

		class openListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				if(fileChooser.showOpenDialog(Frame)== fileChooser.APPROVE_OPTION){
					File file = fileChooser.getSelectedFile();
					Frame.setTitle(file.getName().toString()+" - Notepad");
					
					Scanner in = null;
					try {
						in = new Scanner(file);
						while(in.hasNext()) {
							String line = in.nextLine();
							textArea.append(line+"\n");
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						in.close();
					}
				}
			}
		}
		
		
public static void main(String args[] ){
	Notepad N = new Notepad();
	javax.swing.SwingUtilities.invokeLater( new Runnable(){
		public void run() {

		}
	});
  }
}
