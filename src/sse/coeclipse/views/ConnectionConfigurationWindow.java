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


// 连接配置窗口
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
		shlConnectToServer.setText("Connect to CoEclipse Server");
		
		Button btnConnect = new Button(shlConnectToServer, SWT.NONE);
		btnConnect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				shlConnectToServer.close();				
				
			}
		});
		btnConnect.setBounds(251, 52, 85, 30);
		btnConnect.setText("Connect");
		
		
		textSVNUsername = new Text(shlConnectToServer, SWT.BORDER);
		textSVNUsername.setText("zhenzhenz");
		textSVNUsername.setBounds(71, 34, 157, 21);
		
		textSVNPassword = new Text(shlConnectToServer, SWT.BORDER | SWT.PASSWORD);
		textSVNPassword.setText("123456");
		textSVNPassword.setBounds(71, 61, 157, 21);
		
		Label lblPassword = new Label(shlConnectToServer, SWT.NONE);
		lblPassword.setBounds(10, 64, 55, 15);
		lblPassword.setText("Password");
		
		
		Label lblUsername = new Label(shlConnectToServer, SWT.NONE);
		lblUsername.setBounds(10, 37, 55, 15);
		lblUsername.setText("Username");


	}
}
