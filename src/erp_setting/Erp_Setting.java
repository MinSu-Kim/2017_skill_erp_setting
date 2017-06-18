package erp_setting;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_setting.service.ExportSettingService;
import erp_setting.service.ImportSettingService;
import erp_setting.service.InitSettingService;
import erp_setting.service.ServiceInterface;

@SuppressWarnings("serial")
public class Erp_Setting extends JFrame {

	private JPanel contentPane;
	private JButton btnInitial;
	private JButton btnBackup;
	private JButton btnRestore;
	private ServiceInterface setting;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Erp_Setting frame = new Erp_Setting();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Erp_Setting() {
		setTitle("ERP_SETTING");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 97);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 10, 0));
		
		btnInitial = new JButton("초기화");
		btnInitial.addActionListener(actions);
		contentPane.add(btnInitial);
		
		btnBackup = new JButton("백업");
		btnBackup.addActionListener(actions);
		contentPane.add(btnBackup);
		
		btnRestore = new JButton("복원");
		btnRestore.addActionListener(actions);
		contentPane.add(btnRestore);
	}

	AbstractAction actions = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource()==btnInitial){
				setting = new InitSettingService();
			}else if (e.getSource()==btnBackup){
				setting = new ExportSettingService();
			}else{
				setting = new ImportSettingService();
			}
			setting.initSetting();
		}
	};

}
