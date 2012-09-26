

package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;


import bl.Serializer;
import bl.Task;
import bl.TaskManager;
/**
 * Contains all the elements needed to set the UI and interact with the bl. Launched by the Main.java in the todo package.
 *
 */
public class FrameUi extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JList taskListUi;
	private DefaultListModel model = new DefaultListModel();  
	TaskManager myTaskManager; 

	/**
	 * Create the frame.
	 */
	public FrameUi() {

		//Load the Tasks from the Business logic
		
		myTaskManager = Serializer.getInstance().load();
		for (Task ta : myTaskManager.getMyTasks())
		{
			model.addElement(ta);
		}
		
		
		
		setTitle("ToDo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 354, 580);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmSave = new JMenuItem("save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Serializer.getInstance().save(myTaskManager);
			}
		});
		mnFile.add(mntmSave);

		JMenuItem mntmExit = new JMenuItem("exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 318, 389);
		contentPane.add(scrollPane);

		

		// JLIST
		taskListUi = new JList(model);
		taskListUi.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		taskListUi.addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent arg0) {
//				//				myTaskManager.getTask(taskListUi.getSelectedIndex()).getNotes()
//				if (infosTextArea.getLineCount()!=0)
//				{
//					infosTextArea.setText("");
//				}
//					infosTextArea.setText(myTaskManager.getTask(taskListUi.getSelectedIndex()).toStringInfos());
//					
//				
//			}
//		});
		taskListUi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskListUi.setFont(new Font("SansSerif", Font.ITALIC, 16));
		scrollPane.setViewportView(taskListUi);


		JPanel panel = new JPanel();
		panel.setBounds(10, 462, 318, 48);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(FrameUi.class.getResource("/uiRessources/delete-icon.png")));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!taskListUi.isSelectionEmpty())
				{
					int i=taskListUi.getSelectedIndex();
					myTaskManager.deleteTask(taskListUi.getSelectedIndex());
					model.removeAllElements();
					for (Task ta : myTaskManager.getMyTasks())
					{
						model.addElement(ta);
					}
					taskListUi.setSelectedIndex(i-1);
				}
			}
		});
		panel.add(btnDelete);

		JButton btnUp = new JButton("");
		btnUp.setIcon(new ImageIcon(FrameUi.class.getResource("/uiRessources/up-icon.png")));
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!taskListUi.isSelectionEmpty())
				{
					int i=taskListUi.getSelectedIndex();
					myTaskManager.upTask(taskListUi.getSelectedIndex());
					model.removeAllElements();
					for (Task ta : myTaskManager.getMyTasks())
					{
						model.addElement(ta);
					}
					taskListUi.setSelectedIndex(i-1);
				}
			}
		});

		JButton btnCheck = new JButton("");
		btnCheck.setIcon(new ImageIcon(FrameUi.class.getResource("/uiRessources/Accept-icon.png")));
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!taskListUi.isSelectionEmpty())
				{
					int i=taskListUi.getSelectedIndex();
					myTaskManager.changeStateTask(taskListUi.getSelectedIndex());
					model.removeAllElements();
					for (Task ta : myTaskManager.getMyTasks())
					{
						model.addElement(ta);
					}
					taskListUi.setSelectedIndex(i);
				}
			}
		});
		panel.add(btnCheck);
		panel.add(btnUp);

		JButton btnDown = new JButton("");
		btnDown.setIcon(new ImageIcon(FrameUi.class.getResource("/uiRessources/down-icon.png")));
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!taskListUi.isSelectionEmpty())
				{
					int i=taskListUi.getSelectedIndex();
					myTaskManager.downTask(taskListUi.getSelectedIndex());
					model.removeAllElements();
					for (Task ta : myTaskManager.getMyTasks())
					{
						model.addElement(ta);
					}
					taskListUi.setSelectedIndex(i+1);
				}

			}
		});
		panel.add(btnDown);

		JButton btnClear = new JButton("");
		btnClear.setIcon(new ImageIcon(FrameUi.class.getResource("/uiRessources/Trash-can-icon.png")));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				myTaskManager.deleteAllTasks();
				model.removeAllElements();


			}
		});
		panel.add(btnClear);

		textField = new JTextField("Add task");
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == e.VK_ENTER) { 
					if(textField.getText() == null || textField.getText().equals(""))
					{

					}
					else
					{
						model.removeAllElements();
						myTaskManager.addTask(textField.getText());
						textField.setText("");
						for (Task ta : myTaskManager.getMyTasks())
						{
							model.addElement(ta);
						}
					}
					
				}
			}
		});

		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField.setText("");
			}
		});
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setToolTipText("");
		textField.setBounds(10, 11, 267, 40);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton button = new JButton();
		button.setIcon(new ImageIcon(FrameUi.class.getResource("/uiRessources/add-1-icon.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(textField.getText() == null || textField.getText().equals(""))
				{

				}
				else
				{
					model.removeAllElements();
					myTaskManager.addTask(textField.getText());
					textField.setText("");
					for (Task ta : myTaskManager.getMyTasks())
					{
						model.addElement(ta);
					}
				}

			}
		});
		button.setBounds(287, 11, 41, 40);
		contentPane.add(button);




	}
}
