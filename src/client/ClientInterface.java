package client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Team 02
 */

import network.StatusUpdate;
import org.jfree.chart.ChartPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class ClientInterface {

    public interface ChannelChangeListerner {
        public void onChannelChange(int channel);
    }

    public final int STATUS_SIZE = 100;
    public final Color STATUS_OK = Color.GREEN;
    public final Color STATUS_NOT_OK = Color.RED;
    private ClientStatsManager handler;
    private ClientHandler clientHandler;
    private JFrame fromClient;
    private JButton jButton1;
    private JCheckBox jCheckBox1;
    private JComboBox jComboBox1;
    private JLabel HighestValueText;
    private JLabel FrequencyTextInput;
    private JLabel LowestText;
    private JLabel AverageText;
    private JLabel ChannelText;
    private JLabel FrequencyText;
    private JLabel HighestTextInput;
    private JLabel LowestValueInput;
    private JLabel AverageTextInput;
    private JPanel Panelwithbutton;
    private JPanel GraphPanel;
    private ChannelChangeListerner mChannelListerner;

    //Plot panel
    private static ClientPlotPanel clientPlotPanel;

    /**
     * Create the application.
     */
    public ClientInterface(int numChannel) {
        initcomponents(numChannel);
    }

    public JFrame getFromClient() {
        return fromClient;
    }

    public void setAverageValue(int val) {
        AverageTextInput.setText(Integer.toString(val));
    }

    public void setLowestValue(int val) {
        LowestValueInput.setText(Integer.toString(val));
    }

    public void setHighestValue(int val) {
        HighestTextInput.setText(Integer.toString(val));
    }

    public void setFrequency(int val) {
        FrequencyTextInput.setText(Integer.toString(val));
    }

    public void setChannelChangeListener(ChannelChangeListerner listerner) {
        mChannelListerner = listerner;
    }

    private void initcomponents(int numChannel) {

        clientHandler = ClientHandler.getInstance();
        fromClient = new JFrame();
        fromClient.setTitle("Client");
        fromClient.setBounds(100, 100, 448, 342);
        fromClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fromClient.getContentPane().setLayout(null);

        Color lightblue = new Color(153, 180, 209);
        Border border = BorderFactory.createLineBorder(lightblue);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(220, 220, 220));
        //panel.setBounds(10, 41, 392, 196);
        panel.setBounds(10, 30, 410, 220);
        fromClient.getContentPane().add(panel);
        panel.setLayout(null);

        jCheckBox1 = new JCheckBox();
        Panelwithbutton = new JPanel();
        jComboBox1 = new JComboBox();
        AverageText = new JLabel();
        LowestText = new JLabel("<html>Lowest<br> Value:</html>");
        FrequencyText = new JLabel();
        FrequencyTextInput = new JLabel();
        LowestValueInput = new JLabel();
        ChannelText = new JLabel();
        AverageTextInput = new JLabel();
        HighestTextInput = new JLabel();
        HighestValueText = new JLabel("<html>Highest <br>Value:</html> ");

        GraphPanel = new JPanel();

        final JButton startStopButton = new JButton( "Start / Stop" );
        startStopButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        startStopButton.setBounds(315, 6, 107, 23);
        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("event" + e);
                boolean currentState = clientHandler.getClientReceiveStatus();
                clientHandler.setClientReceiveStatus(!currentState);
                if(clientHandler.getClientReceiveStatus()){
                    System.out.println("Client Started");
                } else {
                    System.out.println("Client Stopped");
                }
            }

        });

        fromClient.getContentPane().add(startStopButton);
        jCheckBox1.setText("1");
        jCheckBox1.getAccessibleContext().setAccessibleDescription("");


        Panelwithbutton.setBackground(new Color(191, 205, 219));
        Panelwithbutton.setBorder(BorderFactory.createEtchedBorder());

        jComboBox1.setBackground(new Color(191, 205, 219));
        String[] strChannel = new String[numChannel];
        for (int i = 1; i <= numChannel; ++i) {
            strChannel[i - 1] = Integer.toString(i);
        }
        jComboBox1.setModel(new DefaultComboBoxModel(strChannel));
        jComboBox1.setOpaque(true);
        jComboBox1.setBounds(328,133,74,38);
        jComboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        panel.add(jComboBox1);
        HighestTextInput.setBackground(Color.PINK);
        HighestTextInput.setBorder(BorderFactory.createEtchedBorder());
        HighestTextInput.setOpaque(true);
        HighestTextInput.setBounds(328, 11, 74, 38);
      //  HighestTextInput.setText(String.valueOf(handler.getHighestValue(chosenchannel)));
        panel.add(HighestTextInput);

        HighestValueText.setBackground(new Color(191, 205, 219));
        HighestValueText.setBorder(BorderFactory.createEtchedBorder());
        HighestValueText.setOpaque(true);
        HighestValueText.setBounds(252, 11, 74, 38);
        panel.add(HighestValueText);


        LowestText.setBorder(BorderFactory.createEtchedBorder());
        LowestText.setOpaque(true);
        LowestText.setSize(new Dimension(20, 20));
        LowestText.setBounds(252, 52, 74, 38);
        LowestText.setBackground(Color.PINK);
        panel.add(LowestText);

        LowestValueInput.setBackground(new Color(191, 205, 219));
        LowestValueInput.setBorder(BorderFactory.createEtchedBorder());
        LowestValueInput.setOpaque(true);
        LowestValueInput.setBounds(328, 52, 74, 38);
       // LowestValueInput.setText((String.valueOf(handler.getLowestValue(chosenchannel))));
        panel.add(LowestValueInput);

        AverageText.setBackground(new Color(191, 205, 219));
        AverageText.setText("Average:");
        AverageText.setBorder(BorderFactory.createEtchedBorder());
        AverageText.setOpaque(true);
        AverageText.setSize(new Dimension(20, 20));
        AverageText.setBounds(252, 93, 74, 38);
        panel.add(AverageText);

        AverageTextInput.setBackground(Color.PINK);
        AverageTextInput.setBorder(BorderFactory.createEtchedBorder());
        AverageTextInput.setOpaque(true);
        AverageTextInput.setBounds(328, 93, 74, 38);
       // AverageTextInput.setText( String.valueOf( handler.getAverageValue(chosenchannel) ) );
        panel.add(AverageTextInput);




        ChannelText.setText("Channel:");
        ChannelText.setBorder(BorderFactory.createEtchedBorder());
        ChannelText.setOpaque(true);
        ChannelText.setSize(new Dimension(20, 20));
        ChannelText.setBackground(Color.PINK);
        ChannelText.setBounds(252,133,74,38);
        panel.add(ChannelText);

        FrequencyText.setBackground(new Color(191, 205, 219));
        FrequencyText.setText("Frequency(Hz):");
        FrequencyText.setBorder(BorderFactory.createEtchedBorder());
        FrequencyText.setOpaque(true);
        FrequencyText.setSize(new Dimension(20, 20));
        FrequencyText.setBounds(252,173,74,38);
        panel.add(FrequencyText);

        FrequencyTextInput.setBackground(Color.PINK);
        FrequencyTextInput.setBorder(BorderFactory.createEtchedBorder());
        FrequencyTextInput.setOpaque(true);
        FrequencyTextInput.setBounds(328,173,74,38);
        panel.add(FrequencyTextInput);

        GraphPanel.setBorder(new LineBorder(SystemColor.activeCaption));
        GraphPanel.setBackground(Color.pink);
        GraphPanel.setBounds(10, 11, 239, 200);
        panel.add(GraphPanel);
        clientPlotPanel = new ClientPlotPanel();
        GraphPanel.setLayout(new BorderLayout(0, 0));
        GraphPanel.add(clientPlotPanel.getChartPanel());
        GraphPanel.validate();

        JPanel consolePanel = new JPanel();
        consolePanel.setBorder(new LineBorder(SystemColor.activeCaption));
        consolePanel.setBackground(new Color(220, 220, 220));
        consolePanel.setBounds(10, 255, 412, 60);
        fromClient.getContentPane().add(consolePanel);
        consolePanel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel(" Console:");
        lblNewLabel_1.setFont(new Font("Monospaced", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(0, 0, 81, 15);
        consolePanel.add(lblNewLabel_1);
    }

    private void jComboBox1ActionPerformed(ActionEvent evt) {
        String chosenselection = (String)jComboBox1.getSelectedItem();
        int chosenchannel = Integer.parseInt(chosenselection);
        if (mChannelListerner != null) {
            mChannelListerner.onChannelChange(chosenchannel);
        }
    }

    public ClientPlotPanel getClientPlotPanel(){
        return clientPlotPanel;
    }
}
