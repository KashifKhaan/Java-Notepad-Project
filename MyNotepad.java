import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class MyNotepad extends JFrame implements ActionListener {

    private static JTextArea area;
    private static JScrollPane pane;
    private static String text;


    MyNotepad(){
        setDefaultLookAndFeelDecorated(true);
        setBounds(0,0,1200,700);
        setTitle("Kashif Notepad");

        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");

        JMenuItem newdoc = new JMenuItem("New");
        newdoc.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newdoc.addActionListener(this);

        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);

        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_ESCAPE, 0));
        exit.addActionListener(this);

        // Adding To Menu
        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);



        JMenu edit = new JMenu("Edit");

        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        copy.addActionListener(this);

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);

        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);

        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectAll.addActionListener(this);

        // Adding To The Edit Menu
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectAll);



        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About Us");
        about.addActionListener(this);

        help.add(about);

        // Adding JMenus to the JMenuBar


        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(help);

        setJMenuBar(menuBar);
        // or but above is best
//        add(menuBar);


        JButton btn = new JButton("Clear");
        btn.setBounds(0,600,100,30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setText(" ");
            }
        });
        add(btn);

        area = new JTextArea();
        area.setBounds(0,0,200,200);
        area.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        area.setLineWrap(false);
        area.setWrapStyleWord(true);
        pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    public void about(){
        JFrame frame = new JFrame("About Us");
        frame.setSize(300,300);
        JLabel label = new JLabel("This All About Us");
        label.setBounds(50,50,100,30);
        frame.add(label);
        JLabel label2 = new JLabel("Name:           Mohammad Kashif");
        label2.setBounds(50,100,350,30);
        frame.add(label2);
        JLabel label3 = new JLabel("Organization:           University Of     Swabi");
        frame.add(label3);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if (e.getActionCommand().equals("New")){
            area.setText("");
        } else if(e.getActionCommand().equals("Open")){
            // Reading File from Open
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
            chooser.addChoosableFileFilter(restrict);
            int action = chooser.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION){
                return;
            }

            File file = chooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            }catch (Exception a){

            }

        } else if (e.getActionCommand().equals("Save")){
            // To save a file we need to open overall file directory
            JFileChooser save_as = new JFileChooser();
            // we have to Differentiate between the (open) and (cancel) button when clicked on save
            save_as.setApproveButtonText("Save");
            int action = save_as.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION){
                return;
            }
            File filename = new File(save_as.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
            } catch (Exception a){

            }
        } else if(e.getActionCommand().equals("Print")){
            try{
                area.print();
            } catch(Exception a){

            }
        }  else if(e.getActionCommand().equals("Exit")){
            System.exit(0);
        } else if (e.getActionCommand().equals("Copy")){
            text = area.getSelectedText();

        } else if (e.getActionCommand().equals("Paste")){
            area.insert(text,area.getCaretPosition());
        } else if (e.getActionCommand().equals("Cut")){
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        } else if (e.getActionCommand().equals("Select All")){
            area.selectAll();
        } else if (e.getActionCommand().equals("About Us")){
           about();
        }
    }

    public static void main(String[] args) {
        new MyNotepad().setVisible(true);
    }
}
