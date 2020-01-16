package org.informationsystem.ismsuite.modeler.process.simulator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class InvalidatorAdapter implements Adapter {
	
	private BasicPNIDSimulator simulator;
	
	public InvalidatorAdapter(BasicPNIDSimulator simulator) {
		this.simulator = simulator;
	}

	@Override
	public void notifyChanged(Notification notification) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				if (MessageDialog.openConfirm(null, "PNID Simulator", 
						"The underlying net has been modified." + System.lineSeparator() +
						"It is unsafe to continue using the application, and it will show wrong results." + System.lineSeparator() +
						"Do you want stop this application (recommended)?")) {
					simulator.dispose();
				}
			}
		});
	}

	@Override
	public Notifier getTarget() {
		return null;
	}

	@Override
	public void setTarget(Notifier newTarget) {
	}

	@Override
	public boolean isAdapterForType(Object type) {
		// TODO Auto-generated method stub
		return false;
	}

}
