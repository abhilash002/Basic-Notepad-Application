package Notepad;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.undo.UndoManager;

	
public class Notepad2 {
	private static final int x = 1000;
	private static final int y = 600;
	String [] fontSize = {"8","9","10","11","12","14","16","18","20","22",
						  "24","26","28","36","48","72"};
	private static String file = "Untitled";
	private JFrame    Frame,frameFind,frameReplace,frameGoto;
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
					  editMenuItemGoto,     editMenuItemSelectAll,
					  editMenuItemTimeDate;
	JMenuItem formatMenuItemWordWrap;
	private JMenuItem formatMenuItemFont;
	private JMenuItem viewMenuItemStatusBar ;
	private JMenuItem helpMenuItemViewHelp, helpMenuItemAboutNotepad,item1,item2,item3,item4,item5,item6;
	private JFileChooser fileChooser;
	private JOptionPane dialogBox;
	private ImageIcon icon = new ImageIcon("E:\\abhilash\\workspace\\browser\\bin\\Assets\\search.jpg");
	private UndoManager um;
	private JScrollPane jSP;
	private JPanel findPanel, fontPanel,fontStylePanel,fontSizePanel,statusPanel;
	private JButton findNext, cancel,replace,replaceAll, Goto,ok ;
	private JRadioButton up,down;
	private JTextField findText,replaceText,lineText,statusText;
	private JLabel findLabel, replaceLabel,lineLabel, font,fontStyle,size,script;
	private JCheckBox matchCase , statusBarCheck, wordWrapCheck;
	private PrinterJob print ;
	private PrintRequestAttributeSet pageSetup;
	private JDialog fontDialog,aboutHelpDialog;
	private JPopupMenu popupMenu;
	private static JComboBox fontSizeCombo;
	private JComboBox fontStyleCombo;
	private JComboBox fontCombo;
	private Font f;
	private JScrollBar jSB;
	private JFontChooser fontChooser;
			
	public Notepad2(){
		 
		 Frame = new JFrame(file +" - Notepad");
		 Frame.setLayout(new BorderLayout());
		 Frame.setIconImage(icon.getImage());
		 fontChooser= new JFontChooser();

		 pageSetup = new HashPrintRequestAttributeSet();
		 print =  PrinterJob.getPrinterJob();
		 frameReplace = new JFrame("Replace");
		 frameGoto = new JFrame("Go to line");
		 statusText = new JTextField("Ln 1,Col 1");
		 statusPanel = new JPanel();
		 ok = new JButton("OK");
		 fontDialog = new JDialog();
		 font = new JLabel("Font:");
		 fontStyle = new JLabel("Font style:");
		 size = new JLabel("Size:");
		 script = new JLabel("Script:");
		 fontSizeCombo = new JComboBox(fontSize);
		 fontStyleCombo = new JComboBox(fontChooser.getFontStyleNames());
		 fontCombo = new JComboBox(fontChooser.getFontFamilies());
		 fontPanel = new JPanel();
		 fontStylePanel = new JPanel();
		 fontSizePanel = new JPanel();
		 aboutHelpDialog = new JDialog();
		 fontPanel.add(font);
		 fontStylePanel.add(fontStyle);
		 fontSizePanel.add(size);
		 fontPanel.add(fontCombo);
		 fontStylePanel.add(fontStyleCombo);
		 fontSizePanel.add(fontSizeCombo);
		 Frame.add(statusPanel,BorderLayout.SOUTH);
		 statusPanel.setPreferredSize(new Dimension(Frame.getWidth(),21));
		 wordWrapCheck =new JCheckBox("Wordwrap");
		 updateStatus(1,1);
		 
		// statusText.setSize(100, statusPanel.getHeight());
		 f = new Font("Arial",Font.PLAIN, 11);
				 
		 popupMenu = new JPopupMenu("Test Popup Menu");
		 popupMenu.setFont(f);
		 item1 = new JMenuItem("Undo");
		 item2 = new JMenuItem("Cut");
		 item3 = new JMenuItem("Copy");
		 item4 = new JMenuItem("Paste");
		 item5 = new JMenuItem("Delete");
		 item6= new JMenuItem("Select All");
		 
		 popupMenu.add(item1);
		 popupMenu.addSeparator();
		 popupMenu.add(item2);
		 popupMenu.add(item3);
		 popupMenu.add(item4);
		 popupMenu.add(item5);
		 popupMenu.addSeparator();
		 popupMenu.add(item6);
		 		 
		 statusBarCheck = new JCheckBox("StatusBar");
		 frameFind = new JFrame("Find");
		 frameFind.setFont(f);
		 findPanel = new JPanel();
		 findPanel.setFont(f);
		 findPanel.setBorder(new TitledBorder("Direction"));
		 findNext = new JButton("Find Next");
		 cancel = new JButton("Cancel");
		 replace = new JButton("Replace");
		 replaceAll = new JButton("Replace All");
		 Goto = new JButton("Goto");
		 up = new JRadioButton("Up");
		 down = new JRadioButton("Down");
		 findText =new JTextField();
		 findText.setFont(f);
		 replaceText = new JTextField();
		 replaceText.setFont(f);
		 lineText = new JTextField();
		 lineText.setFont(f);
		 findLabel = new JLabel("Find what:");
		 findLabel.setFont(f);
		 replaceLabel = new JLabel("Replace with: ");
		 replaceLabel.setFont(f);
		 lineLabel = new JLabel("Line number:");
		 lineLabel.setFont(f);
		 matchCase = new JCheckBox("Match case");

		 menuBar = new JMenuBar() ;
		 textArea = new JTextArea("");
		 dialogBox = new JOptionPane();
		 		 
		 Frame.add(textArea);
		 Frame.setVisible(true);
		 textArea.setFont(f);
		 
		 
//		 Scroll Pane in textArea
		 jSP = new JScrollPane(textArea); 
//		 jSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		 jSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		 Frame.add(jSP); 
		 
//		 jSB = new JScrollBar();
//		 Frame.add(jSB);
		 
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
		 fileChooser.setFont(f);
		 fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		 
		//Miscellaneous Sub Menu items
		 exitListener el = new exitListener();
		 openListener ol = new openListener();
		 saveListener sl = new saveListener();
		 printListener prl = new printListener();
		 pageSetupListener psl = new pageSetupListener();
		 
		 cutListener cl = new cutListener();
		 copyListener col = new copyListener();
		 pasteListener pl = new pasteListener();
		 undoListener ul =new undoListener();
		 deleteListener dl = new deleteListener();
		 replaceListener rl = new replaceListener();
		 gotoListener gl = new gotoListener();
		 selectAllListener sal = new selectAllListener();
		 timeDateListener tdl = new timeDateListener();
		 findListener fl = new findListener();
		 wordWrapListener wwl = new wordWrapListener();
		 fontListener fol = new fontListener();
		 statusBarListener sbl = new statusBarListener();
		 viewHelpListener vhl = new viewHelpListener();
		 aboutNotepadListener anl = new aboutNotepadListener();
		 caretListener crl = new caretListener();
		 
		 //file menu Action listener
		 filemenuItemPrint.addActionListener(prl);;
		 filemenuItemPageSetup.addActionListener(psl);
		 filemenuItemNew.addActionListener(new ActionListener(){
				@Override
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
							if(fileChooser.showSaveDialog(Frame)== JFileChooser.APPROVE_OPTION){
								textArea.setText("");						}
						}
						else if(PromptResult == JOptionPane.NO_OPTION){
//							System.exit(0);	
							textArea.setText("");							
						}			
						}
						
					}
				});
     	 filemenuItemOpen.addActionListener(ol);
		 filemenuItemSave.addActionListener(sl);
		 filemenuItemSaveAs.addActionListener(sl);
		 filemenuItemExit.addActionListener(el);	
		 
		 //Edit menu Action listener
		 editMenuItemReplace.addActionListener(rl);
		 editMenuItemCut.addActionListener(cl);
		 editMenuItemCopy.addActionListener(col);
		 editMenuItemPaste.addActionListener(pl);
		 editMenuItemUndo.addActionListener(ul);		 
		 editMenuItemDelete.addActionListener(dl);
		 editMenuItemSelectAll.addActionListener(sal);
		 editMenuItemTimeDate.addActionListener(tdl);
		 editMenuItemFind.addActionListener(fl);
		 editMenuItemFindNext.addActionListener(fl);
		 editMenuItemGoto.addActionListener(gl);
		 
		//Format menu Action listener
		 formatMenuItemWordWrap.addActionListener(wwl);
		 formatMenuItemFont.addActionListener(fol);
		 
		//View menu Action listener
		 statusBarCheck.addActionListener(sbl);
		 statusBarCheck.setSelected(false);
		 
		//Help menu Action listener
		 helpMenuItemViewHelp.addActionListener(vhl);
		 helpMenuItemAboutNotepad.addActionListener(anl);
		 
		//Popup Menu  Action listener
		 item1.addActionListener(ul);
		 item2.addActionListener(cl);
		 item3.addActionListener(col);
		 item4.addActionListener(pl);
		 item5.addActionListener(dl);
		 item6.addActionListener(sal);
		 
		 //StatusBar CaretListener
		 textArea.addCaretListener(crl);
		
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
		Format.add(wordWrapCheck);
		Format.add(formatMenuItemFont);
		
		//ADDIng Menu Item List ------VIEW------
		View.add(statusBarCheck);
		
		//ADDIng Menu Item List ------HELP------
		Help.add(helpMenuItemViewHelp);
		Help.add(helpMenuItemAboutNotepad);
						
		Frame.addWindowListener(new WindowAdapter(){
			@Override
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
					if(fileChooser.showSaveDialog(Frame)== JFileChooser.APPROVE_OPTION){
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

		
		popupListener pul = new popupListener(popupMenu);	

		textArea.addMouseListener(pul);
		statusPanel.setVisible(false);
		Frame.setFont(f);
		Frame.setJMenuBar(menuBar);
		menuBar.setFont(f);
		Frame.setSize(x, y);
		Frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}			

		class saveWindowListener  implements ActionListener{
			@Override
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
						if(fileChooser.showSaveDialog(Frame)== JFileChooser.APPROVE_OPTION){
							
						}
					}
					else if(PromptResult == JOptionPane.NO_OPTION){
						textArea.setText("");
						
					}
					else System.exit(0);
				}
			}	

		class exitListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		}
		
		class cutListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.cut();
			}
	    }
		
		class copyListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.copy();
			}
	    }
		
		class pasteListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.paste();
			}
	    }
		
		class undoListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(um.canUndo()){
					um.undo();
				}else{
					warn("Can't undo");
				}
			}
	    }
		
		class deleteListener implements ActionListener {
			public void actionPerformed(ActionEvent e) throws NullPointerException{
				try{
				textArea.setText(textArea.getText().replace(textArea.getSelectedText(), ""));
				}		
				catch (NullPointerException e1){
				}
			}
		}
		
		class selectAllListener implements ActionListener{
			@Override
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
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append(d.toString());
			}
		}
		class findListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				frameFind.setIconImage(icon.getImage());
				frameFind.setTitle("Find");
				frameFind.setVisible(true);
				frameFind.setSize(370, 140);
				frameFind.add(findPanel);
				findPanel.setSize(50, 50);
				findPanel.setLayout(null);
				frameFind.add(findNext);
				frameFind.add(cancel);
				frameFind.add(findText);
				frameFind.add(findLabel);
				frameFind.setLayout(null);
				frameFind.add(matchCase);
				frameFind.setResizable(false);
				findNext.setBounds(274, 10, 73, 21);
				cancel.setBounds(274, 37, 73, 21);
				findPanel.setBounds(161, 42, 110, 45);
				findPanel.add(up);
				findPanel.add(down);
				up.setBounds(5, 20, 40, 20);
				down.setBounds(45, 20, 60, 20);
				findText.setBounds(67, 13, 200, 20);
				findLabel.setBounds(5,13,60,20);
				matchCase.setBounds(5, 70, 100, 20);
			}
		}
		class replaceListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				frameReplace.setIconImage(icon.getImage());
				frameReplace.setSize(361, 193);
				frameReplace.add(findNext);
				frameReplace.setLayout(null);
				frameReplace.add(cancel);
				frameReplace.add(findText);
				frameReplace.add(findLabel);
				frameReplace.add(matchCase);
				frameReplace.add(replace);
				frameReplace.add(replaceAll);
				frameReplace.add(replaceLabel);
				frameReplace.add(replaceText);
				frameReplace.setResizable(false);
				findNext.setBounds(248, 10, 90, 21);
				cancel.setBounds(248, 91, 90, 21);
				findText.setBounds(80, 13, 162, 20);
				replaceText.setBounds(80,35,162,20);
				findLabel.setBounds(4,13,65,20);
				replaceLabel.setBounds(4,35,83,20);
				matchCase.setBounds(5,90, 100, 20);
				replace.setBounds(248, 37, 90, 21);
				replaceAll.setBounds(248,64,90,21);
				frameReplace.setVisible(true);
			}
		}
		class gotoListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				frameGoto.setIconImage(icon.getImage());
				frameGoto.setSize(265, 138);
				frameGoto.setLayout(null);
				frameGoto.setVisible(true);
				frameGoto.add(lineLabel);
				frameGoto.add(lineText);
				frameGoto.add(cancel);
				frameGoto.add(Goto);
				frameGoto.setResizable(false);
				cancel.setBounds(160, 68, 75, 21);
				Goto.setBounds(80,68, 75, 21);
				lineText.setBounds(7,34,236,21);
				lineLabel.setBounds(7,7,100,20);
			}
		}
		class wordWrapListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				boolean chk = wordWrapCheck.isSelected();
				if(chk==true){
//					jSP.set(true);
					
				}
				else{
//					jSP.setVisible(false);;
				}	
			}
		}
		class fontListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				   int result = fontChooser.showDialog(Frame);
				    if (result == JFontChooser.OK_OPTION)
				    {
				       Font font = fontChooser.getSelectedFont(); 
				       textArea.setFont(font);
				    }
			}
		}
		class statusBarListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean checked = statusBarCheck.isSelected();
				 statusPanel.add(statusText,BorderLayout.WEST);
				 statusText.setPreferredSize(new Dimension(100,21));
				 statusText.setEditable(false);
				 statusText.setBounds(700,1 ,150, 18);
				 statusPanel.setLayout(null);
		//		 statusText.setBorder(null);
				if(checked){
					statusPanel.setVisible(true);
				}
				else 
					statusPanel.setVisible(false);
				}
		}
		class viewHelpListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		class aboutNotepadListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				aboutHelpDialog.setSize(474, 413);
				aboutHelpDialog.setVisible(true);
			}
		}
		class caretListener implements CaretListener{
			public void caretUpdate(CaretEvent e) {
				textArea = (JTextArea) e.getSource();
				int linenum = 1;
                int columnnum = 1;
                
                try {
                    int caretpos = textArea.getCaretPosition();
                    linenum = textArea.getLineOfOffset(caretpos);
                    columnnum = caretpos - textArea.getLineStartOffset(linenum);
                    linenum += 1;
                }
                catch(Exception ex) { }
                updateStatus(linenum, columnnum+1);
			}

		}
		private void updateStatus(int linenumber, int columnnumber) {
	        statusText.setText("Ln " + linenumber + ", Col" + columnnumber);
	    }
		class saveListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(fileChooser.showSaveDialog(Frame)== JFileChooser.APPROVE_OPTION){
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
				if(fileChooser.showOpenDialog(Frame)== JFileChooser.APPROVE_OPTION){
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
		
		class printListener implements Printable,ActionListener{
			@Override
			public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
				if(page > 0){
					return NO_SUCH_PAGE;
				}
				Graphics2D g2d = (Graphics2D)g;
		        g2d.translate(pf.getImageableX(), pf.getImageableY());
		        
		        g.drawString(textArea.getText().toString(), 100, 100
		        		);
		        
		        return PAGE_EXISTS;

			}

			public void actionPerformed(ActionEvent e) {
				
		         print.setPrintable(this);
		         boolean ok = print.printDialog();
		         if (ok) {
		             try {
		                  print.print();
		             } catch (PrinterException ex) {
		              /* The job did not successfully complete */
		            	 JOptionPane.showMessageDialog(null, "Print unsucccessful.");
		             }
		         }
			}
		}
		
		class pageSetupListener implements Printable,ActionListener{
			public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
				if (page > 0) { /* We have only one page, and 'page' is zero-based */
		            return NO_SUCH_PAGE;
		        }
				Graphics2D g2d = (Graphics2D)g;
		        g2d.translate(pf.getImageableX(), pf.getImageableY());
		        
		        g.drawString(textArea.getText(), x, y);
		        
		        return PAGE_EXISTS;
				}
			public void actionPerformed(ActionEvent e) {
				PageFormat pf = print.pageDialog(pageSetup);
		        boolean ok = print.printDialog(pageSetup);
		        print.setPrintable( new pageSetupListener(), pf);
		        if (ok) {
		            try {
		                 print.print(pageSetup);
		            } catch (PrinterException ex) {
		            	JOptionPane.showMessageDialog(Frame, "Print unsucccessful.");		            }
		        }
								
			}
		}
		
		class popupListener extends MouseAdapter{
			JPopupMenu popup;
			popupListener(JPopupMenu popupMenu) {
	            popup = popupMenu;
	        }
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
		           popupMenu.show(e.getComponent(),e.getX(), e.getY());
		        }
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
			           popupMenu.show(e.getComponent(),e.getX(), e.getY());
			        }
			}
		}

public static void main(String args[] ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

	javax.swing.SwingUtilities.invokeLater( new Runnable(){
		@Override
		public void run() {
			Notepad2 N = new Notepad2();

		}
	});
  }
}
