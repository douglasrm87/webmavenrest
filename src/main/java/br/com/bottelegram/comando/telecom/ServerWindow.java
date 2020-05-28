package br.com.bottelegram.comando.telecom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.Random;

public class ServerWindow implements ActionListener {

	private RemoteDataServer serverAtrib;

	private Thread sThread; // server thread

	private static final int WINDOW_HEIGHT = 200;
	private static final int WINDOW_WIDTH = 350;

	private String ipAddress;

	private JFrame window = new JFrame("Remote Control for Android");

	private JLabel addressLabel = new JLabel("");
	private JLabel portLabel = new JLabel("Android Remote Control Port: ");
	private JTextArea[] buffers = new JTextArea[3];
	private JTextField portTxt = new JTextField(5);
	private JLabel serverMessages = new JLabel("Not Connected");

	private JButton connectButton = new JButton("Start Server");
	private JButton disconnectButton = new JButton("Stop Server");

	public boolean connected = false;

	public ServerWindow() {
		this.serverAtrib = new RemoteDataServer();
		this.window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.connectButton.addActionListener(this);
		this.disconnectButton.addActionListener(this);

		Container c = this.window.getContentPane();
		c.setLayout(new FlowLayout());

		try {
			InetAddress ip = InetAddress.getLocalHost();
			this.ipAddress = ip.getHostAddress();
			this.addressLabel.setText("Android Remote Control Server IP Address: " + ipAddress);
		} catch (Exception e) {
			this.addressLabel.setText("IP Address Could Not be Resolved");
		}

		int x;
		for (x = 0; x < 3; x++) {
			this.buffers[x] = new JTextArea("", 1, 30);
			this.buffers[x].setEditable(false);
			this.buffers[x].setBackground(window.getBackground());
		}

		this.portTxt.setEditable(false);
		Random portRandom = new Random();
		for (int i = 0; i < 10; i++) {

			int port = portRandom.nextInt(4998) + 1;
			int portNum = 5000 + port;
			String portString = Integer.toString(portNum);
			this.portTxt.setText(portString);
		}

		c.add(this.addressLabel);
		c.add(this.buffers[0]);
		c.add(this.portLabel);
		c.add(this.portTxt);
		c.add(this.buffers[1]);
		c.add(this.connectButton);
		c.add(this.disconnectButton);
		c.add(this.buffers[2]);
		c.add(this.serverMessages);

		this.window.setLocationRelativeTo(null);
		this.window.setVisible(true);
		this.window.setResizable(false);

	}

	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src instanceof JButton) {
			if ((JButton) src == this.connectButton) {
				int port = Integer.parseInt(this.portTxt.getText());
				runServer(port);
			}

			else if ((JButton) src == this.disconnectButton) {
				closeServer();
			}
		}
	}

	public void runServer(int port) {
		if (port <= 9999) {
			this.serverAtrib.setPort(port);
			this.sThread = new Thread(this.serverAtrib);
			this.sThread.start();
		} else {
			this.serverMessages.setText("The port Number must be less than 10000");
		}
	}

	public void closeServer() {
		this.serverMessages.setText("Disconnected");
		this.serverAtrib.shutdown();
		this.connectButton.setEnabled(true);
	}

	public static void main(String[] args) {
		new ServerWindow();
	}

	public class RemoteDataServer implements Runnable {
		int PORT;
		private DatagramSocket server;
		private byte[] buf;
		private DatagramPacket dgp;

		private String message;

		public RemoteDataServer() {
			this.buf = new byte[1000];
			this.dgp = new DatagramPacket(this.buf, this.buf.length);

			serverMessages.setText("Not Connected");
		}

		public String getIpAddress() {
			String returnStr;
			try {
				InetAddress ip = InetAddress.getLocalHost();
				returnStr = ip.getCanonicalHostName();
			} catch (Exception e) {
				returnStr = new String("Could Not be Resolve Ip Address");
			}
			return returnStr;
		}

		public void setPort(int port) {
			this.PORT = port;
		}

		public void shutdown() {
			try {
				this.server.close();
				serverMessages.setText("Disconnected");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void run() {

			InetAddress ip;
			try {
				ip = InetAddress.getByName("p475034");

				serverMessages.setText("Waiting for connection on " + ip.getCanonicalHostName());
				boolean livre = false;
				do {
					try {
						this.server = new DatagramSocket(PORT, ip);
						livre = true;
					} catch (BindException e) {
						serverMessages.setText("Port " + PORT + " is already in use. Use a different Port");
					} catch (SocketException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} while (!livre);
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}

			connected = true;
			connectButton.setEnabled(false);

			while (connected) {
				// get message from sender
				try {
					this.server.receive(this.dgp);

					// translate and use the message to automate the desktop
					this.message = new String(dgp.getData(), 0, this.dgp.getLength());
					if (this.message.equals("Connectivity")) {
						// send response to confirm connectivity
						serverMessages.setText("Trying to Connect");
						this.server.send(this.dgp); // echo the message back
					} else if (this.message.equals("Connected")) {
						this.server.send(this.dgp); // echo the message back
					} else if (this.message.equals("Close")) {
						serverMessages.setText("Controller has Disconnected. Trying to reconnect."); // echo the message
																										// back
					} else {
						serverMessages.setText("Android Phone Connected to ARD Server");
					}
				} catch (Exception e) {
					serverMessages.setText("Disconnected");
					connected = false;
				}
			}

		}

	}
}
