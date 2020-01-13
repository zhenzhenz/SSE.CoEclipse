package sse.coeclipse.views;


import java.util.ArrayList;
import java.io.FileOutputStream;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
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
import sse.coeclipse.listeners.WorkspaceResourceChangeListener;
import sse.coeclipse.processor.EditingOperation;
import sse.coeclipse.processor.RemoteOperationProcessor;


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

 // CoView为插件入口UI
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
	
	public Button buttonRemote = null;
	public Button buttonRemoteDel = null;

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
	 
	MessageBox msgBox = null; 
	
	public ConnectionConfigurationWindow win;
	
	public IWorkspace resourcesWorkspace;
	public WorkspaceResourceChangeListener wrcl;

	// CoView的UI布局
	@Override
	public void createPartControl(Composite parent) {
		top = new Composite(parent, SWT.NONE);
		top.setLayout(null);
		top.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

		buttonStart = new Button(top, SWT.NONE);
		buttonStart.setBounds(new Rectangle(14, 30, 139, 26));
		buttonStart.setText("Connect to Server");
		buttonStart.addListener(SWT.Selection, this);
		
		buttonRemote = new Button(top, SWT.NONE);
		buttonRemote.setBounds(new Rectangle(240, 2, 50, 27));
		buttonRemote.setText("insert");
		buttonRemote.addListener(SWT.Selection, this);
		
		buttonRemoteDel = new Button(top, SWT.NONE);
		buttonRemoteDel.setBounds(new Rectangle(140, 2, 50, 27));
		buttonRemoteDel.setText("delete");
		buttonRemoteDel.addListener(SWT.Selection, this);
		
		
		msgBox = new MessageBox(new Shell(), SWT.OK | SWT.ICON_INFORMATION);
		String msg = "this is the content.";
		msgBox.setMessage(msg);
		msgBox.setText("This is the text");

	}



	@Override
	public void setFocus() {
		
	}
	// 挂载Workbench的监听
	public int startSession()
	{	
		WorkspacePartListener wpl = new WorkspacePartListener();
		wpl.view = this;
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().addPartListener(wpl);
		
		// 挂载资源监听器
		resourcesWorkspace = ResourcesPlugin.getWorkspace();
		wrcl = new WorkspaceResourceChangeListener();
		resourcesWorkspace.addResourceChangeListener(wrcl);
	
		return 0;
	}
	// 打开一个document时，launcher启动一个属于该document的CentralProcessor
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
		launcher.start();
		
		return 0;
	}
	
	public int CloseDocument()
	{
		launcher.stop();
		
		return 0;
	}



	//处理 CoView UI的监听到的事件，主要是点击button等事件
	@Override
	public void handleEvent(Event event) {
		
		// buttonStart的响应事件
		if (event.widget == buttonStart)
		{
		   
		    //msgBox.open();
//			打开连接配置窗口
			win = new ConnectionConfigurationWindow(Display.getCurrent().getActiveShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.OK, this);
			win.open();
			startSession();
//			if ( win.iDialogResult == 1 )
//			{
//    			startSession();
//    			buttonStart.setText("Connected to server.");
//    			buttonStart.setEnabled(false);
//    			buttonStart.setBackground(new Color(null, 255, 0, 0));
//    
//    			//this.OpenDocument();
//    		}
		} // buttonRemote的响应事件,本地debug用
		else if (event.widget == buttonRemote) {
			//msgBox.open();
			EditingOperation eo = new EditingOperation();
			
			eo.type = 1;
			eo.position = 0;
			eo.length = 2;
			eo.content = "ha";
			RemoteOperationProcessor.processRemoteOperation("/OJ/OJ.iml", eo);
	        try {
	            //向文件中写入字节数组
	            String font="test file input";
	            FileOutputStream fos = new FileOutputStream("/Users/lixiangzhen/JavaProject/OJ/FOSDemo.txt");
	            fos.write(font.getBytes());
	            System.out.println("write file"); //debug
	            //关闭此文件输出流并释放与此流有关的所有系统资源。此文件输出流不能再用于写入字节。 如果此流有一个与之关联的通道，则关闭该通道。 
	            fos.close();
	            ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
	            //ResourcesPlugin.getWorkspace().getRoot().getProjectRelativePath()
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		else if (event.widget == buttonRemoteDel) {
			EditingOperation eo = new EditingOperation();
			
			eo.type = 2;
			eo.position = 3;
			eo.length = 4;
			eo.content = "";
			RemoteOperationProcessor.processRemoteOperation("/OJ/OJ.iml", eo);
			System.out.println("path");
			System.out.println(ResourcesPlugin.getWorkspace().getRoot().getFullPath());
			System.out.println(ResourcesPlugin.getWorkspace().getRoot().getLocation());
			System.out.println(ResourcesPlugin.getWorkspace().getRoot().getRawLocationURI());
			System.out.println(ResourcesPlugin.getWorkspace().getRoot().getProjects()[0].getLocation());
		}
		
	}
}
