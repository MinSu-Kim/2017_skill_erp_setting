package erp_setting;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import erp_setting.service.InitSettingService;

@SuppressWarnings("serial")
public class Erp_Setting extends JFrame {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new InitSettingService().initSetting();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

}
