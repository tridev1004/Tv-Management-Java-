import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainScreen extends JFrame {
    //User reg panel;

    JPanel subscriberpanel;
    JTextField subName;
    JTextField subLastName;
    JTextField subMobile;
    JTextField subCity;

    JLabel namelabel;
    JLabel Lastlabel;
    JLabel mobileLabel;
    JLabel cityLabel;

//    panel2 for subscriptioncycle


    JTextField startCycle;
    JTextField endCycle;
    JTextField numberTv;
    JLabel todaylabel;
    JPanel cyclePanel;
    SimpleDateFormat df;
    Date currentdate;
    JLabel startCylelabel;
    JLabel endCyclelabel;
    JLabel numbertvLabel;

    // panel3 channel package;

    JCheckBox sportCheckbox;
    JCheckBox movieCheckbox;
    JCheckBox DocCheckbox;
    JPanel packagespanel;


    // panel4 package details
    JTextArea channelareaS;
    JTextArea channelareaM;
    JTextArea channelareaD;
    JPanel detailsPanel;


    // panel5 fees

    JLabel INstallfeeLabel;
    JPanel feepanel;
    JLabel Packagefeelabel;
    JLabel totalFeeLabel;


    //panel6  table data of sub

    JTable table;
    DefaultTableModel tablemodel;
    JPanel panel6table;

    // panel 7 button
    JButton saveBtn;
    JButton loadBtn;
    JButton newBtn;
    JPanel p7Actions;

    // Classes Ob
    Subscriber subscriber;
    Subscription subscription;
    int packagesSelectedPrice = 0;
    int totalPrice;


    // save
    ArrayList<Subscription> listtoSave = new ArrayList<>();
    File file;


    // Constructor
    public MainScreen() {
        //                              Panel 1 subscription
        subscriberpanel = new JPanel();
        Border panel1title = BorderFactory.createTitledBorder("Subscriber Details");
        subscriberpanel.setBorder(panel1title);
        subscriberpanel.setBounds(15, 15, 300, 200);
        subscriberpanel.setLayout(new GridLayout(4, 2));


        //Jbael
        namelabel = new JLabel("Name");
        Lastlabel = new JLabel("LastName");
        mobileLabel = new JLabel("Mobile:");
        cityLabel = new JLabel("City");


        // textField
        subName = new JTextField();
        subName.setOpaque(false);
        subLastName = new JTextField();
        subLastName.setOpaque(false);
        subMobile = new JTextField();
        subMobile.setOpaque(false);
        subCity = new JTextField();
        subCity.setOpaque(false);


        // Add component to panel1;
        subscriberpanel.add(namelabel);
        subscriberpanel.add(subName);
        subscriberpanel.add(Lastlabel);
        subscriberpanel.add(subLastName);
        subscriberpanel.add(mobileLabel);
        subscriberpanel.add(subMobile);
        subscriberpanel.add(cityLabel);
        subscriberpanel.add(subCity);

        //                           panel2  cycle;

        cyclePanel = new JPanel();
        cyclePanel.setBounds(15, 230, 300, 500);
        cyclePanel.setLayout(new GridLayout(14, 1));

        Border CycleBorder = BorderFactory.createTitledBorder("Cycle Details");
        cyclePanel.setBorder(CycleBorder);


        // components of cycle panel
        todaylabel = new JLabel();
        df = new SimpleDateFormat("dd/MM/yyyy");
        currentdate = new Date();
        todaylabel.setText("Today" + df.format(currentdate));

        // Startcycle date
        startCylelabel = new JLabel("Start Cycle Date(DD/MM/YYYY):");
        startCycle = new JTextField();

        // endCycle date
        endCyclelabel = new JLabel("End Cycle Date(DD/MM/YYYY):");
        endCycle = new JTextField();


        // Number of tv
        numbertvLabel = new JLabel("Number of TV");
        numberTv = new JTextField();


        cyclePanel.add(todaylabel);
        cyclePanel.add(startCylelabel);
        cyclePanel.add(startCycle);
        cyclePanel.add(endCyclelabel);
        cyclePanel.add(endCycle);
        cyclePanel.add(numbertvLabel);
        cyclePanel.add(numberTv);


        // Opacity for field
        startCycle.setOpaque(false);
        endCycle.setOpaque(false);
        numberTv.setOpaque(false);

//                                         panel 3   for channels


        packagespanel = new JPanel();
        packagespanel.setBounds(330, 15, 300, 200);
        packagespanel.setLayout(new GridLayout(5, 1));
        Border packagetitle = BorderFactory.createTitledBorder("Available Packages");
        packagespanel.setBorder(packagetitle);

        JLabel packageslabes = new JLabel("Please Select Your Packages");
        sportCheckbox = new JCheckBox("Sports Package");
        movieCheckbox = new JCheckBox("Movie Packages");
        DocCheckbox = new JCheckBox("Documentary Package");

        JButton subscribebtn = new JButton("Subscribe");
        packagespanel.add(packageslabes);
        packagespanel.add(sportCheckbox);
        packagespanel.add(movieCheckbox);
        packagespanel.add(DocCheckbox);
        packagespanel.add(subscribebtn);  // white one are locally created here purpleone are global

        // CheckBox ItemListener
        sportCheckbox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (sportCheckbox.isSelected()) {
                    DisplaySportsChannel();
                    // price change
                } else {
                    channelareaS.setText("");

                }
            }
        });

        movieCheckbox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if (movieCheckbox.isSelected()) {
                    DisplaymoviesChannel();
                    // price change
                } else {
                    channelareaM.setText("");
                }
            }
        });

        DocCheckbox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (DocCheckbox.isSelected()) {
                    DisplayDocChannel();

                    // price change
                } else {
                    channelareaD.setText("");
                }
            }
        });
        subscribebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GetsubscriberData();
                } catch (Exception ee) {
                }
            }
        });


        ///                            panel4 ////////////
        detailsPanel = new JPanel();
        detailsPanel.setBounds(330, 230, 300, 500);
        detailsPanel.setLayout(new GridLayout(3, 1));
        Border p4Border = BorderFactory.createTitledBorder("Available Channel");
        detailsPanel.setBorder(p4Border);

        channelareaS = new JTextArea(5, 1);
        channelareaS.setEditable(false);
        channelareaS.setOpaque(false);
        channelareaS.setLineWrap(true);

        channelareaM = new JTextArea(5, 1);
        channelareaM.setEditable(false);
        channelareaM.setOpaque(false);
        channelareaM.setLineWrap(true);

        channelareaD = new JTextArea(5, 1);
        channelareaD.setEditable(false);
        channelareaD.setOpaque(false);
        channelareaD.setLineWrap(true);

        detailsPanel.add(channelareaS);
        detailsPanel.add(channelareaM);
        detailsPanel.add(channelareaD);

        // /                        panel 5///////////////\

        feepanel = new JPanel();
        feepanel.setBounds(645, 15, 200, 200);
        feepanel.setLayout(new GridLayout(3, 1));
        Border blackline5 = BorderFactory.createTitledBorder("Fee & Check");
        feepanel.setBorder(blackline5);
        INstallfeeLabel = new JLabel("Installation Fee:");
        Packagefeelabel = new JLabel("Packages Fee:");
        totalFeeLabel = new JLabel("Total Amount to Pay");

        feepanel.add(INstallfeeLabel);
        feepanel.add(Packagefeelabel);
        feepanel.add(totalFeeLabel);

        //                                          panel 6

        panel6table = new JPanel();
        panel6table.setBounds(645, 230, 515, 500);
        panel6table.setLayout(new GridLayout(3, 1));

        Border border6 = BorderFactory.createTitledBorder("Our Customer:");
        panel6table.setBorder(border6);

        //Table
        tablemodel = new DefaultTableModel();
        table = new JTable(tablemodel);
        tablemodel.addColumn("First Name");
        tablemodel.addColumn("Last Name");
        tablemodel.addColumn("Phone Num:");
        tablemodel.addColumn("Start Cycle");
        tablemodel.addColumn("End Cycle");
        tablemodel.addColumn("Total Fee:");

        //now add scroll

        JScrollPane scrollpane2 = new JScrollPane(table);
        panel6table.add(scrollpane2);


        ///                                      panel 7///

        p7Actions = new JPanel();
        p7Actions.setBounds(860, 15, 300, 200);
        Border border7 = BorderFactory.createTitledBorder("Action Section");
        p7Actions.setBorder(border7);
        p7Actions.setLayout(new GridLayout(4, 1));
        saveBtn = new JButton("Save Subscription");
        loadBtn = new JButton("Load Subscription");
        newBtn = new JButton("New Subscription");
        p7Actions.add(newBtn);
        p7Actions.add(saveBtn);
        p7Actions.add(loadBtn);

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveSubscriptiontoDisk();
            }
        });
        newBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewSubscription();
            }
        });
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Subscription> k = LoadDataFrmDisk();
            }
        });


        //connect panel to jframe since directly extending jframe
        // directly set properties

        setLayout(null);
        add(subscriberpanel);      // panel1
        add(cyclePanel);          // panel2
        add(packagespanel);      // panel3
        add(detailsPanel);      //panel4
        add(feepanel);          //panel5
        add(panel6table);
        add(p7Actions);

    }


    public static void main(String[] args) {
        MainScreen mainscreen = new MainScreen();
        mainscreen.setVisible(true);
        mainscreen.setBounds(20, 10, 1200, 800);


    }

    //       functions for operation
    public int DisplaySportsChannel() {
        SportsChannel s1 = new SportsChannel("AFN Sports", "EN", "SPRT", 5);
        SportsChannel s2 = new SportsChannel("beIn Sports", "FR", "SPRT", 3);
        SportsChannel s3 = new SportsChannel("Eleven Sports", "EN", "SPRT", 8);
        SportsChannel s4 = new SportsChannel("NBA Tv", "EN", "SPRT", 6);
        SportsChannel s5 = new SportsChannel("NFl Network", "AR", "SPRT", 3);
        SportsChannel s6 = new SportsChannel("The Ski Channel", "USA", "SPRT", 1);

        ArrayList<SportsChannel> sportlist = new ArrayList<>();
        sportlist.add(s1);
        sportlist.add(s2);
        sportlist.add(s3);
        sportlist.add(s4);
        sportlist.add(s5);
        sportlist.add(s6);
        String SportChannelString = "";
        int packagePrice = 0;
        for (int i = 0; i < sportlist.size(); i++) {
            SportChannelString +=
                    "     " + sportlist.get(i).getChannelName() + "     " +
                            "  " + sportlist.get(i).getLanguage() + "   " +
                            "     " + sportlist.get(i).getPrice()
                            + "\n";

            channelareaS.setText(SportChannelString);
            packagePrice += sportlist.get(i).getPrice();
        }

        return packagePrice;

    }

    public int DisplaymoviesChannel() {

        MovieChannel m1 = new MovieChannel("MBC Bundle", "SP", "MOV", 4);
        MovieChannel m2 = new MovieChannel("Cinema one", "EN", "MOV", 5);
        MovieChannel m3 = new MovieChannel("Cinema pro", "RU", "MOV", 6);
        MovieChannel m4 = new MovieChannel("Cinema 1", "AR", "MOV", 2);
        MovieChannel m5 = new MovieChannel("Movie Home", "GR", "MOV", 4);
        MovieChannel m6 = new MovieChannel("Film4", "FR", "MOV", 2);

        ArrayList<MovieChannel> movielist = new ArrayList<>();
        movielist.add(m1);
        movielist.add(m2);
        movielist.add(m3);
        movielist.add(m4);
        movielist.add(m5);
        movielist.add(m6);
        String MovChannelString = "";
        int packagePrice = 0;
        for (int i = 0; i < movielist.size(); i++) {
            MovChannelString +=
                    "     " + movielist.get(i).getChannelName() + "     " +
                            "  " + movielist.get(i).getLanguage() + "   " +
                            "     " + movielist.get(i).getPrice()
                            + "\n";
            channelareaM.setText(MovChannelString);
            packagePrice += movielist.get(i).getPrice();
        }
        return packagePrice;

    }

    public int DisplayDocChannel() {
        DocumentaryChannel m1 = new DocumentaryChannel("NAT GEO", "SP", "DOC", 3);
        DocumentaryChannel m2 = new DocumentaryChannel("PBS America", "EN", "DOC", 2);
        DocumentaryChannel m3 = new DocumentaryChannel("Discovery", "IN", "DOC", 3);
        DocumentaryChannel m4 = new DocumentaryChannel("History", "EN", "DOC", 4);
        DocumentaryChannel m5 = new DocumentaryChannel("World Documentary", "AR", "DOC", 5);
        DocumentaryChannel m6 = new DocumentaryChannel("Canal D", "GR", "DOC", 1);

        ArrayList<DocumentaryChannel> documentaryChannels = new ArrayList<>();
        documentaryChannels.add(m1);
        documentaryChannels.add(m2);
        documentaryChannels.add(m3);
        documentaryChannels.add(m4);
        documentaryChannels.add(m5);
        documentaryChannels.add(m6);

        String docChannelString = "";
        int packagePrice = 0;
        for (int i = 0; i < documentaryChannels.size(); i++) {
            docChannelString +=
                    "     " + documentaryChannels.get(i).getChannelName() + "     " +
                            "  " + documentaryChannels.get(i).getLanguage() + "   " +
                            "     " + documentaryChannels.get(i).getPrice()
                            + "\n";
            channelareaD.setText(docChannelString);
            packagePrice += documentaryChannels.get(i).getPrice();

        }
        return packagePrice;
    }

    private void GetsubscriberData() throws ParseException {
        Date currentdate = new Date();
        //New SUb
        subscriber = new Subscriber(subName.getText(),
                subLastName.getText(), subCity.getText(),
                Integer.parseInt(subMobile.getText()));

        //cycle
        Date startcycle = df.parse(startCycle.getText());
        Date endcycle = df.parse(endCycle.getText());

        Subscriptioncycle cycle = new Subscriptioncycle(
                df.format(startcycle),
                df.format(endcycle)
        );
        subscription = new Subscription(
                Integer.parseInt(numberTv.getText()),
                subscriber,
                cycle,
                df.format(currentdate));
        INstallfeeLabel.setText("Installation Fee:" + subscription.getTotalFee() + "$");

        ShowPrice();

    }

    public void ShowPrice() {
        if (DocCheckbox.isSelected())
            packagesSelectedPrice += DisplayDocChannel();
        else if (movieCheckbox.isSelected()) {
            packagesSelectedPrice += DisplaymoviesChannel();
        } else if (sportCheckbox.isSelected()) {
            packagesSelectedPrice += DisplaySportsChannel();
        }


        Packagefeelabel.setText("Packages Fee:" + packagesSelectedPrice + "$");
        totalPrice = subscription.getTotalFee() + packagesSelectedPrice;
        totalFeeLabel.setText("Total Amount to Pay:" + totalPrice + "$");


    }

    public void SaveSubscriptiontoDisk() {
        listtoSave.add(subscription);
        file = new File("d:\\myfile.dat");
        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            // save
            oos.writeObject(listtoSave);
            oos.flush();
            oos.close();
            os.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void NewSubscription() {
        subName.setText("");
        subLastName.setText("");
        subCity.setText("");
        subMobile.setText("");
        startCycle.setText("");
        endCycle.setText("");
        numberTv.setText("");


        INstallfeeLabel.setText("Installation Fee:");
        Packagefeelabel.setText("Packages Fee:");
        totalFeeLabel.setText("Total Amount to Pay");


        movieCheckbox.setSelected(false);
        DocCheckbox.setSelected(false);
        sportCheckbox.setSelected(false);
    }

    public ArrayList<Subscription> LoadDataFrmDisk() {
        ArrayList<Subscription> s = new ArrayList<>();
        file = new File("d:\\myfile.dat");
        try {
            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);
            s = (ArrayList) ois.readObject();
            ois.close();
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        for (Subscription sub : s) {
            DisplaysubInTable(sub);
        }

        return s;
    }

    private void DisplaysubInTable(Subscription sub) {
        // Displaying data from disk to table
        tablemodel.addRow(new Object[]{
                sub.getSubscriber().getFirstName(),
                sub.getSubscriber().getLAstName(),
                sub.getSubscriber().getPhone(),
                sub.getCycle().getStartDate(),
                sub.getCycle().getEndDate(),
                sub.getTotalFee()
        });
    }

}