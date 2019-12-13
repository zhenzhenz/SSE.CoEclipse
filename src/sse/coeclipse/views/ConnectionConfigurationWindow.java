package sse.coeclipse.views;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.wb.swt.SWTResourceManager;



public class ConnectionConfigurationWindow extends Dialog {
	public int iDialogResult;
	
	public CoView view;
	public String strRepository;
	public String strUsername;
	public String strPassword;
	public boolean authenticate;
	
	
	public String date;	
	
	
	protected Object result;
	protected Shell shlConnectToServer;

	public List listSessions;
	
	public ConnectionConfigurationWindow _this;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ConnectionConfigurationWindow(Shell parent, int style, CoView _view)
	{
		super(parent, style);
		_this = this;
		setText("SWT Dialog");
		view = _view;
		iDialogResult = 0;
	}
	
		
	private Text textSVNRepository;
	private Text textSVNUsername;
	private Text textSVNPassword;
	private Text textConnectionResult;
	Button btnRadioSingle;
	Button btnRadioMultiple;
	Button btnRadioJoin;
	private Label lblStatus;
	
	TreeViewer treeViewer;
	
	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open()
	{
		createContents();
		shlConnectToServer.open();
		shlConnectToServer.layout();
		Display display = getParent().getDisplay();
		while (!shlConnectToServer.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
		return result;
	}


	/**
	 * Create contents of the dialog.
	 */
	private void createContents()
	{
		shlConnectToServer = new Shell(getParent(), getStyle());
		shlConnectToServer.setSize(621, 348);
		shlConnectToServer.setText("Connect to ATCoEclipse Server");
		
		Button btnConnect = new Button(shlConnectToServer, SWT.NONE);
		btnConnect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				authenticate = false;
				
				if ( textSVNUsername.getText().equals("P1") 
						&& textSVNPassword.getText().equals("P1") )
				{
					authenticate = true;
				}
				else if ( textSVNUsername.getText().equals("P2") 
						&& textSVNPassword.getText().equals("P2") )
				{
					authenticate = true;
				}
				else if ( textSVNUsername.getText().equals("P3") 
						&& textSVNPassword.getText().equals("P3") )
				{
					authenticate = true;	
				}	
				else
				{
					authenticate = false;
				}
				
				if ( authenticate == true )
				{
				
					strRepository = textSVNRepository.getText();
					strUsername = textSVNUsername.getText();
					strPassword = textSVNPassword.getText();
					
					textConnectionResult.setText("Authentication passed. Select your collaboration mode to proceed.");
				
					try
					{
						Socket socket = new Socket("155.69.144.156", 10087);
						DataInputStream dis = new DataInputStream(socket.getInputStream());
						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
						
						dos.writeInt(Integer.reverseBytes(1));
						//ProjectManager.sendString(dos, strRepository);
						//ProjectManager.sendString(dos, strUsername);
						
						listSessions.removeAll();
						
						int size = Integer.reverseBytes(dis.readInt());
						for(int i = 0; i < size; ++i)
						{
							//String s = ProjectManager.receiveString(dis);
							//listSessions.add(s);
						}
									
						dos.flush();
						
						socket.close();
					}
					catch (UnknownHostException e1)
					{
						e1.printStackTrace();
					}
					catch (IOException e1)
					{
						e1.printStackTrace();
					}
					
					

				}
				else
				{
					iDialogResult = 0;
					textConnectionResult.setText("Authentication failed. Please try again.");
				}
				
			
				
				
			}
		});
		btnConnect.setBounds(251, 52, 85, 30);
		btnConnect.setText("Connect");
		
		textSVNRepository = new Text(shlConnectToServer, SWT.BORDER);
		textSVNRepository.setText("https://office-pc/svn/SimpleCalculator/");
		textSVNRepository.setBounds(98, 7, 251, 21);
		
		textSVNUsername = new Text(shlConnectToServer, SWT.BORDER);
		textSVNUsername.setText("P2");
		textSVNUsername.setBounds(71, 34, 157, 21);
		
		textSVNPassword = new Text(shlConnectToServer, SWT.BORDER | SWT.PASSWORD);
		textSVNPassword.setText("P2");
		textSVNPassword.setBounds(71, 61, 157, 21);
		
		Label lblPassword = new Label(shlConnectToServer, SWT.NONE);
		lblPassword.setBounds(10, 64, 55, 15);
		lblPassword.setText("Password");
		
		Label lblSvnRepository = new Label(shlConnectToServer, SWT.NONE);
		lblSvnRepository.setBounds(10, 10, 82, 15);
		lblSvnRepository.setText("SVN Repository");
		
		Label lblUsername = new Label(shlConnectToServer, SWT.NONE);
		lblUsername.setBounds(10, 37, 55, 15);
		lblUsername.setText("Username");
		
		textConnectionResult = new Text(shlConnectToServer, SWT.BORDER | SWT.MULTI);
		textConnectionResult.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		textConnectionResult.setText("Ready.");
		textConnectionResult.setToolTipText("");
		textConnectionResult.setBounds(10, 285, 481, 25);
		
		Group grpOption = new Group(shlConnectToServer, SWT.NONE);
		grpOption.setText("Create New Session");
		grpOption.setBounds(370, 7, 235, 68);
		
		btnRadioSingle = new Button(grpOption, SWT.RADIO);
		btnRadioSingle.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnRadioJoin.setSelection(false);
			}
		});
		btnRadioSingle.setSelection(true);
		btnRadioSingle.setBounds(10, 20, 123, 16);
		btnRadioSingle.setText("Non-Real-Time");
		
		btnRadioMultiple = new Button(grpOption, SWT.RADIO);
		btnRadioMultiple.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnRadioJoin.setSelection(false);
			}
		});
		btnRadioMultiple.setBounds(10, 42, 82, 16);
		btnRadioMultiple.setText("Real-Time");
		
		lblStatus = new Label(shlConnectToServer, SWT.NONE);
		lblStatus.setBounds(10, 264, 55, 15);
		lblStatus.setText("Status");
		
		Group grpJoinAnExisting = new Group(shlConnectToServer, SWT.NONE);
		grpJoinAnExisting.setText("Join Existing Real-Time Session");
		grpJoinAnExisting.setBounds(370, 102, 235, 166);
		
		btnRadioJoin = new Button(grpJoinAnExisting, SWT.RADIO);
		btnRadioJoin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnRadioSingle.setSelection(false);
				btnRadioMultiple.setSelection(false);
			}
		});
		btnRadioJoin.setBounds(10, 22, 139, 16);
		btnRadioJoin.setText("Join a Session Below");
		
		listSessions = new List(grpJoinAnExisting, SWT.BORDER);
		listSessions.setBounds(10, 44, 215, 112);
		
		Label lblOr = new Label(shlConnectToServer, SWT.NONE);
		lblOr.setBounds(480, 81, 24, 15);
		lblOr.setText("OR");
		
		Label label = new Label(shlConnectToServer, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(373, 90, 64, -13);
		
		Label label_1 = new Label(shlConnectToServer, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(505, 90, 90, 2);
		
		Label label_2 = new Label(shlConnectToServer, SWT.SEPARATOR | SWT.VERTICAL);
		label_2.setBounds(355, 10, 12, 259);
		
		treeViewer = new TreeViewer(shlConnectToServer, SWT.BORDER);
		Tree treeRepository = treeViewer.getTree();
		treeRepository.setBounds(10, 92, 339, 166);
		
		Button btnProceed = new Button(shlConnectToServer, SWT.NONE);
		btnProceed.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if (btnRadioSingle.getSelection() == true )
				{
					try
					{
						Socket socket = new Socket("155.69.144.156", 10087);
						DataInputStream dis = new DataInputStream(socket.getInputStream());
						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
						
						dos.writeInt(Integer.reverseBytes(2));
						//ProjectManager.sendString(dos, strRepository);
						
						java.util.Date current = new java.util.Date();
				        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				        String c=sdf.format(current);
						date = "[SINGLE] Created by '" + strUsername + "' @ " + c;
						
						//ProjectManager.sendString(dos, date);
						//ProjectManager.sendString(dos, strUsername);
						
						dos.flush();
						dis.readInt();
						
						socket.close();
					}
					catch (UnknownHostException e1)
					{
						e1.printStackTrace();
					}
					catch (IOException e1)
					{
						e1.printStackTrace();
					}	
				}
				else if (btnRadioMultiple.getSelection() == true)
				{
					try
					{
						Socket socket = new Socket("155.69.144.156", 10087);
						DataInputStream dis = new DataInputStream(socket.getInputStream());
						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
						
						dos.writeInt(Integer.reverseBytes(2));
						//ProjectManager.sendString(dos, strRepository);
						
						java.util.Date current = new java.util.Date();
				        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				        String c=sdf.format(current);
						date = "[GROUP] Created by '" + strUsername + "' @ " + c;
						
						//ProjectManager.sendString(dos, date);
						//ProjectManager.sendString(dos, "0");
						
						dos.flush();
						dis.readInt();
						
						socket.close();
					}
					catch (UnknownHostException e1)
					{
						e1.printStackTrace();
					}
					catch (IOException e1)
					{
						e1.printStackTrace();
					}			
				}
				else if (btnRadioJoin.getSelection() == true )
				{
					date = listSessions.getSelection()[0];
				}
				
				iDialogResult = 1;
				shlConnectToServer.close();
				
			}
		});
		
		btnProceed.setBounds(505, 280, 100, 30);
		btnProceed.setText("Proceed");
		
		Label label_3 = new Label(shlConnectToServer, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_3.setBounds(380, 90, 90, 2);

	}
}
