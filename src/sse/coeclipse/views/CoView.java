package sse.coeclipse.views;


import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import sse.coeclipse.core.Launcher;
import sse.coeclipse.listeners.WorkspacePartListener;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class CoView extends ViewPart implements Listener {
	
	public CoView()
	{
	}

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "sse.coeclipse.views.CoView";

	public Composite top = null;

	public Label label = null;

	public Button buttonStart = null;

	public Button buttonReset = null;

	public Table tableShowDALRegion = null;

	public Table tableLegend = null;

	public Launcher launcher = null;

	private Button checkBoxApplyDAL = null;

	public StyledText stNotificationArea = null;

	private Label labelTitle = null;

	public Button checkBoxDelay = null;

	public Button radioPopupNotification = null;

	public Button radioInboxNotification = null;

	public Button buttonTest1 = null;

	private Label label1 = null;

	public StyledText stCaretPosition = null;

	public StyledText stEditingPosition = null;

	public Button buttonReleaseLockingRange = null;

	public Spinner spinnerTimeout = null;

	private Label label2 = null;

	public Button checkBoxTimeoutRelease = null;

	public Button radioButtonSingleLockingRangeMode = null;

	public Button radioButtonMultipleLockingRangeMode = null;

	private Label label3 = null;

	public Spinner spinnerMaximizedLockingRangeAmount = null;

	public Button radioButtonLockAllDependedRegions = null;

	public Button radioButtonLockSelectedDependedRegions = null;

	public Button buttonReleaseAllLocks = null;

	public Button checkBoxLockDependedFields = null;

	public Button checkBoxLockDependedMethods = null;

	public Spinner spinnerLevelOfDependedLocking = null;

	private Label label4 = null;

	public Button buttonConfirmPolicySetting = null;

	private Button buttonManualLock = null;
	 
	
	public ConnectionConfigurationWindow win;
	private Text txtsystemPJoins;
	private Text txtRealtime;
	private Text txtSesionName;
	private Text txtP;
	private Text txtP_1;
	public Table tableProjectCollaborator;
	private Text txtTypeYourMessage;


	@Override
	public void createPartControl(Composite parent) {
		top = new Composite(parent, SWT.NONE);
		top.setLayout(null);
		top.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

		buttonStart = new Button(top, SWT.NONE);
		buttonStart.setBounds(new Rectangle(14, 30, 139, 26));
		buttonStart.setText("Connect to Server");
		buttonStart.addListener(SWT.Selection, this);

		buttonReset = new Button(top, SWT.NONE);
		buttonReset.setBounds(new Rectangle(240, 2, 50, 27));
		buttonReset.setText("Reset");
		buttonReset.addListener(SWT.Selection, this);

		buttonTest1 = new Button(top, SWT.NONE);
		buttonTest1.setBounds(new Rectangle(240, 52, 50, 27));
		buttonTest1.setText("Test1");
		buttonTest1.addListener(SWT.Selection, this);

		tableShowDALRegion = new Table(top, SWT.BORDER);
		tableShowDALRegion.setHeaderVisible(true);
		tableShowDALRegion.setLinesVisible(true);
		tableShowDALRegion.setBounds(new Rectangle(441, 318, 127, 171));

		checkBoxApplyDAL = new Button(top, SWT.CHECK);
		checkBoxApplyDAL.setBounds(new Rectangle(308, 478, 76, 22));
		checkBoxApplyDAL.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		checkBoxApplyDAL.setSelection(true);
		checkBoxApplyDAL.setText("Apply DAL");

		stNotificationArea = new StyledText(top, SWT.BORDER | SWT.WRAP);
		stNotificationArea.setText("Notification Area");
		stNotificationArea.setFont(new Font(Display.getDefault(), "Verdana", 8,
				SWT.BOLD));
		stNotificationArea.setForeground(Display.getCurrent().getSystemColor(
				SWT.COLOR_RED));
		stNotificationArea.setBounds(new Rectangle(299, 30, 126, 75));

		labelTitle = new Label(top, SWT.NONE);
		labelTitle.setBounds(new Rectangle(16, 8, 124, 20));
		labelTitle.setFont(new Font(Display.getDefault(), "Verdana", 12,
				SWT.BOLD | SWT.ITALIC));
		//labelTitle.setForeground(new Color(Display.getCurrent(), 255, 0, 128));
		labelTitle.setForeground(new Color(Display.getCurrent(), 112, 48, 160));
		labelTitle.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		labelTitle.setText("ATCoEclipse");

		checkBoxDelay = new Button(top, SWT.CHECK);
		checkBoxDelay.setBounds(new Rectangle(240, 31, 51, 20));
		checkBoxDelay.setText("Delay");
		checkBoxDelay.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		checkBoxDelay.addListener(SWT.Selection, this);

		TableColumn tableColumn1 = new TableColumn(tableShowDALRegion, SWT.NONE);
		tableColumn1.setWidth(72);
		tableColumn1.setText("Region");

		TableColumn tableColumn2 = new TableColumn(tableShowDALRegion, SWT.NONE);
		tableColumn2.setWidth(50);
		tableColumn2.setText("Lock");

		radioPopupNotification = new Button(top, SWT.RADIO);
		radioPopupNotification.setBounds(new Rectangle(299, 242, 110, 22));
		radioPopupNotification.setText("Popup Message");
		radioPopupNotification.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));
		radioPopupNotification.setSelection(true);
		radioPopupNotification.addListener(SWT.Selection, this);

		radioInboxNotification = new Button(top, SWT.RADIO);
		radioInboxNotification.setBounds(new Rectangle(299, 270, 110, 21));
		radioInboxNotification.setText("Notification Area");
		radioInboxNotification.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));
		radioInboxNotification.setSelection(false);

		label1 = new Label(top, SWT.NONE);
		label1.setBounds(new Rectangle(299, 221, 117, 15));
		label1.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		label1.setText("Notification Method:");

		stCaretPosition = new StyledText(top, SWT.WRAP | SWT.BORDER);
		stCaretPosition.setText("Caret Position");
		stCaretPosition.setBounds(new Rectangle(431, 29, 130, 64));

		stEditingPosition = new StyledText(top, SWT.BORDER | SWT.WRAP);
		stEditingPosition.setText("Editing Position");
		stEditingPosition.setBounds(new Rectangle(431, 118, 130, 190));

		buttonReleaseLockingRange = new Button(top, SWT.NONE);
		buttonReleaseLockingRange.setBounds(new Rectangle(308, 332, 108, 29));
		buttonReleaseLockingRange.setText("Release Lock");
		buttonReleaseLockingRange.addListener(SWT.Selection, this);

		buttonReleaseAllLocks = new Button(top, SWT.NONE);
		buttonReleaseAllLocks.setBounds(new Rectangle(317, 367, 108, 29));
		buttonReleaseAllLocks.setText("Release All Locks");
		buttonReleaseAllLocks.addListener(SWT.Selection, this);

		buttonConfirmPolicySetting = new Button(top, SWT.NONE);
		buttonConfirmPolicySetting.setBounds(new Rectangle(373, 774, 71, 23));
		buttonConfirmPolicySetting.setText("OK");
		buttonManualLock = new Button(top, SWT.NONE);
		buttonManualLock.setBounds(new Rectangle(327, 402, 108, 27));
		buttonManualLock.setText("Lock");

		Group grpLockingRangeMode = new Group(top, SWT.NONE);
		grpLockingRangeMode.setText("Locking Range Mode");
		grpLockingRangeMode.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		grpLockingRangeMode.setBounds(369, 516, 199, 125);

		radioButtonSingleLockingRangeMode = new Button(grpLockingRangeMode,
				SWT.RADIO);
		radioButtonSingleLockingRangeMode.setBounds(10, 21, 179, 19);
		radioButtonSingleLockingRangeMode.setText("Single Locking Range Mode");
		radioButtonSingleLockingRangeMode.setSelection(true);
		radioButtonSingleLockingRangeMode.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));

		radioButtonMultipleLockingRangeMode = new Button(grpLockingRangeMode,
				SWT.RADIO);
		radioButtonMultipleLockingRangeMode.setEnabled(false);
		radioButtonMultipleLockingRangeMode.setBounds(10, 46, 183, 19);
		radioButtonMultipleLockingRangeMode
				.setText("Multiple Locking Range Mode");
		radioButtonMultipleLockingRangeMode.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));

		checkBoxTimeoutRelease = new Button(grpLockingRangeMode, SWT.CHECK);
		checkBoxTimeoutRelease.setBounds(10, 93, 108, 23);
		checkBoxTimeoutRelease.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));
		checkBoxTimeoutRelease.setSelection(true);
		checkBoxTimeoutRelease.setText("Timeout Release");

		spinnerTimeout = new Spinner(grpLockingRangeMode, SWT.BORDER);
		spinnerTimeout.setBounds(119, 94, 44, 23);
		spinnerTimeout.setToolTipText("");
		spinnerTimeout.setSelection(60);
		spinnerTimeout.setMaximum(999);
		spinnerTimeout.setMinimum(1);

		label2 = new Label(grpLockingRangeMode, SWT.SHADOW_NONE);
		label2.setBounds(164, 97, 25, 18);
		label2.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		label2.setText("Sec");

		label3 = new Label(grpLockingRangeMode, SWT.NONE);
		label3.setBounds(10, 71, 60, 16);
		label3.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		label3.setText("Maximum");

		spinnerMaximizedLockingRangeAmount = new Spinner(grpLockingRangeMode,
				SWT.NONE);
		spinnerMaximizedLockingRangeAmount.setBounds(77, 71, 47, 17);
		spinnerMaximizedLockingRangeAmount.setSelection(5);
		spinnerMaximizedLockingRangeAmount.setMinimum(1);
		spinnerMaximizedLockingRangeAmount.setMaximum(20);

		Group grpLockingScope = new Group(top, SWT.NONE);
		grpLockingScope.setText("Locking Scope");
		grpLockingScope.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		grpLockingScope.setBounds(305, 647, 199, 117);

		radioButtonLockAllDependedRegions = new Button(grpLockingScope,
				SWT.RADIO);
		radioButtonLockAllDependedRegions.setBounds(10, 21, 178, 22);
		radioButtonLockAllDependedRegions.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));
		radioButtonLockAllDependedRegions.setText("All Depended Regions");

		radioButtonLockSelectedDependedRegions = new Button(grpLockingScope,
				SWT.RADIO);
		radioButtonLockSelectedDependedRegions.setBounds(10, 36, 180, 25);
		radioButtonLockSelectedDependedRegions.setSelection(true);
		radioButtonLockSelectedDependedRegions.setBackground(Display
				.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		radioButtonLockSelectedDependedRegions
				.setText("Selected Depended Regions");

		checkBoxLockDependedFields = new Button(grpLockingScope, SWT.CHECK);
		checkBoxLockDependedFields.setBounds(10, 61, 55, 21);
		checkBoxLockDependedFields.setSelection(true);
		checkBoxLockDependedFields.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));

		checkBoxLockDependedFields.setText("Fields");
		checkBoxLockDependedMethods = new Button(grpLockingScope, SWT.CHECK);
		checkBoxLockDependedMethods.setBounds(71, 59, 78, 25);
		checkBoxLockDependedMethods.setSelection(true);
		checkBoxLockDependedMethods.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));
		checkBoxLockDependedMethods.setText("Methods");

		label4 = new Label(grpLockingScope, SWT.NONE);
		label4.setBounds(10, 88, 44, 21);
		label4.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		label4.setText("Level");

		spinnerLevelOfDependedLocking = new Spinner(grpLockingScope, SWT.NONE);
		spinnerLevelOfDependedLocking.setBounds(60, 88, 39, 18);
		spinnerLevelOfDependedLocking.setSelection(3);
		spinnerLevelOfDependedLocking.setMinimum(1);
		
		Button btnSwitchMode = new Button(top, SWT.NONE);
		btnSwitchMode.setBounds(240, 435, 191, 29);
		btnSwitchMode.setText("Switch to Non-Real-Time Mode");
		
		Button btnDalSetting = new Button(top, SWT.NONE);
		btnDalSetting.setBounds(278, 774, 84, 25);
		btnDalSetting.setText("DAL Setting");
		
		Group grpInformation = new Group(top, SWT.NONE);
		grpInformation.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		grpInformation.setText("Session Information");
		grpInformation.setBounds(14, 62, 191, 282);
		
		Label lblCollaborationMode = new Label(grpInformation, SWT.NONE);
		lblCollaborationMode.setBounds(10, 63, 114, 15);
		lblCollaborationMode.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblCollaborationMode.setText("Collaboration Mode");
		
		txtRealtime = new Text(grpInformation, SWT.BORDER);
		txtRealtime.setBounds(10, 78, 127, 21);
		txtRealtime.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		txtRealtime.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		txtRealtime.setText("REAL-TIME");
		
		Label lblSessionName = new Label(grpInformation, SWT.NONE);
		lblSessionName.setBounds(10, 105, 84, 15);
		lblSessionName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblSessionName.setText("Session Name");
		
		txtSesionName = new Text(grpInformation, SWT.BORDER);
		txtSesionName.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		txtSesionName.setBounds(10, 120, 171, 21);
		txtSesionName.setText("SampleApplication");
		
		Label lblSessionOwner = new Label(grpInformation, SWT.NONE);
		lblSessionOwner.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblSessionOwner.setBounds(10, 147, 142, 15);
		lblSessionOwner.setText("Session Creator (Owner)");
		
		txtP = new Text(grpInformation, SWT.BORDER);
		txtP.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		txtP.setText("Chengzheng");
		txtP.setBounds(10, 164, 127, 21);
		
		Label lblSiteIdusername = new Label(grpInformation, SWT.NONE);
		lblSiteIdusername.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblSiteIdusername.setBounds(10, 20, 114, 15);
		lblSiteIdusername.setText("Site ID (Username)");
		
		txtP_1 = new Text(grpInformation, SWT.BORDER);
		txtP_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		txtP_1.setText("Hongfei");
		txtP_1.setBounds(10, 36, 127, 21);
		txtP_1.setBackground(new Color(null, 255, 155, 155));
		
		tableLegend = new Table(grpInformation, SWT.BORDER);
		tableLegend.setBounds(10, 198, 171, 72);
		tableLegend.setHeaderVisible(true);
		tableLegend.setLinesVisible(true);
				
		TableColumn tableColumnLegend = new TableColumn(tableLegend, SWT.NONE);
		tableColumnLegend.setWidth(166);
		tableColumnLegend.setText("Active Collaborator(s)");
		
		Group grpSvnOperations = new Group(top, SWT.NONE);
		grpSvnOperations.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		grpSvnOperations.setText("SVN Operation");
		grpSvnOperations.setBounds(240, 516, 191, 58);
		
		Button btnSvnUpdate = new Button(grpSvnOperations, SWT.NONE);
		btnSvnUpdate.setBounds(8, 20, 84, 28);
		btnSvnUpdate.setText("SVN Update");
		
		Button btnSvncommit = new Button(grpSvnOperations, SWT.NONE);
		btnSvncommit.setBounds(100, 20, 84, 28);
		btnSvncommit.setText("SVN Commit");
		
		Group grpNotificationAndInstant = new Group(top, SWT.NONE);
		grpNotificationAndInstant.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		grpNotificationAndInstant.setText("Notification && Instant Chat");
		grpNotificationAndInstant.setBounds(14, 358, 191, 233);
		
		txtsystemPJoins = new Text(grpNotificationAndInstant, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		txtsystemPJoins.setText("[SYSTEM] \"Chengzheng\" joins to edit \"Stack.java\".\r\n\r\n[Chengzheng] Morning! I continue my work on \"popList\".\r\n\r\n[Hongfei] Thanks. I just focus on \"push\".\r\n");
		txtsystemPJoins.setBounds(10, 20, 171, 148);
		
		Button btnSend = new Button(grpNotificationAndInstant, SWT.NONE);
		btnSend.setBounds(106, 201, 75, 25);
		btnSend.setText("Send");
		
		txtTypeYourMessage = new Text(grpNotificationAndInstant, SWT.BORDER);
		txtTypeYourMessage.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		txtTypeYourMessage.setText("Type your message here.");
		txtTypeYourMessage.setBounds(10, 174, 171, 21);
		
		tableProjectCollaborator = new Table(top, SWT.BORDER | SWT.FULL_SELECTION);
		tableProjectCollaborator.setBounds(240, 114, 171, 90);
		tableProjectCollaborator.setHeaderVisible(true);
		tableProjectCollaborator.setLinesVisible(true);
		
		TableColumn tblclmnActiveProjectCollaborators = new TableColumn(tableProjectCollaborator, SWT.NONE);
		tblclmnActiveProjectCollaborators.setWidth(167);
		tblclmnActiveProjectCollaborators.setText("Active Project Collaborator(s)");
		
		radioButtonLockSelectedDependedRegions.addListener(SWT.Selection, this);
		radioButtonLockAllDependedRegions.addListener(SWT.Selection, this);
		radioButtonMultipleLockingRangeMode.addListener(SWT.Selection, this);
		radioButtonSingleLockingRangeMode.addListener(SWT.Selection, this);
		buttonConfirmPolicySetting.addListener(SWT.Selection, this);

		radioInboxNotification.addListener(SWT.Selection, this);
	}




	@Override
	public void setFocus() {
		
	}

	public int startSession()
	{
		boolean isSVNMode = false;
		
//		if ( isSVNMode == false )
//		{
//    		int lengthOfStream;
//    		byte[] stream;
//    
//    		try
//    		{
//    			InetAddress ip = InetAddress.getLocalHost();
//    
//    			Socket socket = new Socket("155.69.144.156", 10029);
//    			DataInputStream dis = new DataInputStream(socket.getInputStream());
//    			DataOutputStream dos = new DataOutputStream(socket
//    					.getOutputStream());
//    			
//    			ProjectManager.sendString(dos, win.strRepository);
//    			ProjectManager.sendString(dos, win.date);
//    
//    			dos.writeInt(Integer.reverseBytes(SMMessage.REQUIRE_DIR));
//    			dos.flush();
//    
//    			lengthOfStream = Integer.reverseBytes(dis.readInt());
//    			stream = new byte[lengthOfStream];
//    			dis.readFully(stream, 0, lengthOfStream);
//    
//    			dis.close();
//    			dos.close();
//    			socket.close();
//    
//    		}
//    		catch (UnknownHostException e)
//    		{
//    			e.printStackTrace();
//    			return -1;
//    		}
//    		catch (IOException e)
//    		{
//    			e.printStackTrace();
//    			return -1;
//    		}
//    
//    		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
//    		IProject[] projects = root.getProjects();
//    		for (int i = 0; i < projects.length; ++i)
//    		{
//    			try
//    			{
//    				projects[i].close(null);
//    			}
//    			catch (CoreException e)
//    			{
//    				e.printStackTrace();
//    			}
//    		}
//    
//    		String projectName = "SampleApplication";
//    		String workspace = Platform.getLocation().toOSString();
//    		workspace = workspace.replace('/', '\\');
//    		workspace += ("\\" + projectName);
//    		// System.out.println(workspace);
//    
//    		File pathCoWorkspace = new File(workspace);
//    		if (pathCoWorkspace.exists() == true)
//    		{
//    			try
//    			{
//    				DirOperator.delDir(workspace);
//    				pathCoWorkspace.delete();
//    			}
//    			catch (IOException e)
//    			{
//    				e.printStackTrace();
//    			}
//    		}
//    
//    		final IProject project = root.getProject(projectName);
//    		try
//    		{
//    			if (project.exists() == true)
//    			{
//    				project.delete(true, null);
//    			}
//    			project.create(null);
//    		}
//    		catch (CoreException e)
//    		{
//    			e.printStackTrace();
//    		}
//    
//    		DirOperator.TransformByteStreamToDirectory(workspace, stream);
//    
//    		
//    		IViewPart[] parts = PlatformUI.getWorkbench()
//    		.getActiveWorkbenchWindow().getActivePage().getViews();
//    		IPackagesViewPart p = null;
//    		for (int i = 0; i < parts.length; ++i)
//    		{
//    			if (parts[i] instanceof IPackagesViewPart)
//    			{
//    				p = (IPackagesViewPart) (parts[i]);
//    				break;
//    			}
//    		}
//    		TreeViewer tv = null;
//    		if ( p != null )
//    		{
//    			tv = p.getTreeViewer();
//    			tv.refresh(true);
//    		}		
//    		
//    		try
//    		{
//    			project.open(null);
//    			
//    			if ( tv != null)
//    			{
//    				tv.expandToLevel(2);
//    			}
//    			
//    		}
//    		catch (CoreException e)
//    		{
//    			e.printStackTrace();
//    		}
//		}

		
		ArrayList<IViewPart> viewParts = new ArrayList<IViewPart>();
		
		IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
		for(int i = 0; i < windows.length; ++i)
		{
			IWorkbenchPage[] pages = windows[i].getPages();
			for(int j = 0; j < pages.length; ++j)
			{
				IViewPart[] views = pages[j].getViews();
				
				for (int k = 0; k < views.length; ++k)
				{
					if (views[k] instanceof IEditorPart)
					{
						viewParts.add(views[k]);
					}
				}
			}
		}
		
		for(int i = 0; i < viewParts.size(); ++i)
		{
			viewParts.get(i).dispose();
		}

		
		WorkspacePartListener wpl = new WorkspacePartListener();
		wpl.view = this;
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().addPartListener(wpl);

		
		// Initiate Project Manager
//		pm = new ProjectManager(this);
//		pm.Start();
		

		
		return 0;
	}
	
	public int OpenDocument(String docName)
	{
		SideView sv = null;

		IViewPart[] viewParts = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getViews();
		for (int i = 0; i < viewParts.length; ++i)
		{
			if (viewParts[i].getAdapter(SideView.class) != null)
			{
				sv = (SideView) (viewParts[i]);
				break;
			}
		}
		
		launcher = new Launcher(this, sv, docName);
		launcher.applyDAL = false;
//		launcher.applyDAL = checkBoxApplyDAL.getSelection();
//		checkBoxDelay.setSelection(false);
		launcher.start();
		
		return 0;
	}
	
	public int CloseDocument()
	{
		launcher.stop();
		
		return 0;
	}




	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if (event.widget == buttonStart)
		{
			startSession();
//			win = new ConnectionConfigurationWindow(Display.getCurrent().getActiveShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.OK, this);
//			win.open();
//			if ( win.iDialogResult == 1 )
//			{
//    			startSession();
//    			buttonStart.setText("Connected to server.");
//    			buttonStart.setEnabled(false);
//    			buttonStart.setBackground(new Color(null, 255, 0, 0));
//    
//    			//this.OpenDocument();
//    		}
		}
//		else if (event.widget == checkBoxDelay)
//		{
//			// testing DAL feature
//			
//			if (launcher != null)
//			{
//				if (launcher.cp != null)
//				{
//					if (checkBoxDelay.getSelection() == true)
//					{
//						launcher.cp.mr.delay = true;
//					}
//					else
//					{
//						launcher.cp.mr.delay = false;
//					}
//				}
//			}
//		}
//		else if (event.widget == buttonReset)
//		{
//					
//			final ITextEditor ed = (ITextEditor) (PlatformUI.getWorkbench()
//					.getActiveWorkbenchWindow().getActivePage()
//					.getActiveEditor());
//			final IDocumentProvider documentProvider = ed.getDocumentProvider();
//			final IDocument doc = documentProvider.getDocument(ed
//					.getEditorInput());
//			
//			try
//			{
//				InputStreamReader isr = new InputStreamReader(
//						new FileInputStream("C:\\template.java"), "GB2312");
//
//				char[] buf = new char[1024 * 512];
//
//				int iLength = isr.read(buf);
//
//				doc.set(new String(buf, 0, iLength));
//				ed.doSave(null);
//
//				isr.close();
//
//			}
//			catch (UnsupportedEncodingException e)
//			{
//				e.printStackTrace();
//			}
//			catch (FileNotFoundException e)
//			{
//				e.printStackTrace();
//			}
//			catch (IOException e)
//			{
//				e.printStackTrace();
//			}
//		}
//		else if (event.widget == radioPopupNotification)
//		{
//			radioPopupNotification.setSelection(true);
//			radioInboxNotification.setSelection(false);
//		}
//		else if (event.widget == radioInboxNotification)
//		{
//			radioPopupNotification.setSelection(false);
//			radioInboxNotification.setSelection(true);
//		}
//		else if (event.widget == buttonReleaseLockingRange)
//		{
//			launcher.cp.dalProcessor.manualReleaseLock();
//		}
//		else if (event.widget == buttonReleaseAllLocks)
//		{
//			launcher.cp.dalProcessor.manualReleaseAll();
//		}
//		else if (event.widget == radioButtonSingleLockingRangeMode)
//		{
//			radioButtonMultipleLockingRangeMode
//					.setSelection(!radioButtonSingleLockingRangeMode
//							.getSelection());
//		}
//		else if (event.widget == radioButtonMultipleLockingRangeMode)
//		{
//			radioButtonSingleLockingRangeMode
//					.setSelection(!radioButtonMultipleLockingRangeMode
//							.getSelection());
//		}
//		else if (event.widget == radioButtonLockAllDependedRegions)
//		{
//			radioButtonLockSelectedDependedRegions
//					.setSelection(!radioButtonLockAllDependedRegions
//							.getSelection());
//		}
//		else if (event.widget == radioButtonLockSelectedDependedRegions)
//		{
//			radioButtonLockAllDependedRegions
//					.setSelection(!radioButtonLockSelectedDependedRegions
//							.getSelection());
//		}
//		else if (event.widget == buttonConfirmPolicySetting)
//		{
//			launcher.cp.dalProcessor.setLocalDALPolicy();
//		}
//		else if (event.widget == buttonTest1)
//		{
//			System.out.println("Test1");
//			initializeWorkspace();
//
//			// ITextEditor ed = (ITextEditor)
//			// (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor());
//			IEditorReference[] editors = PlatformUI.getWorkbench()
//					.getActiveWorkbenchWindow().getActivePage()
//					.getEditorReferences();
//			System.out.println("There is/are " + editors.length + " editors");
//			for (int i = 0; i < editors.length; ++i)
//			{
//				try
//				{
//					System.out.println(editors[i].getEditorInput().getName());
//				}
//				catch (PartInitException e)
//				{
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
		
	}
}
