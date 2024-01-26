package src;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Main {

    //button colors
    static Color defaultButton = Color.decode("#4d6190");
    static Color hoverButton = Color.decode("#4255ff");
    static Color clickButton = Color.decode("#2f3990");
    static Color BodyColor = Color.decode("#111827");
    static Color TextColor = Color.decode("#f9fafb");

    private JPanel contentPane;
    
    public Main() {
        // Set the cross-platform look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Global Content pane (including navbar)
        JPanel globalPane = new JPanel();
        globalPane.setLayout(new BorderLayout());

        // Window
        JFrame frame = new JFrame();
        frame.setIconImage(new ImageIcon("resources/images/logo.png").getImage());
        frame.setTitle("Vokabeltrainer");
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(globalPane);
        // temp?: press esc to quit
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        Action escapeAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Quit the application
            }
        };
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        frame.getRootPane().getActionMap().put("ESCAPE", escapeAction);
        
        frame.setVisible(true);

        // UI Content Pane
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        globalPane.add(contentPane, BorderLayout.CENTER);

        // Navigation bar
        new NavBar(globalPane, contentPane, this);

        // create the mainMenu
        new MainMenu(contentPane, this);


        // Refresh screen to make stuff show up
        globalPane.revalidate();
        globalPane.repaint();
    }

    public static void main(String[] args) {
        new Main();
    }

    public void newUI() {
        try {
            contentPane.removeAll();
        }
        catch (Exception e) {
            
        }
        contentPane.revalidate();
        contentPane.repaint();
    }
    public void newMainMenu() {
        newUI();
        new MainMenu(contentPane, this);
    }
    public void newSettingsMenu() {
        newUI();
        new SettingsMenu(contentPane);
    }
    public void newLearnMenu() {
        newUI();
        System.out.println("newLearnMenu");
    }
    public void newLibraryMenu() {
        newUI();
        System.out.println("newLibraryMenu");
    }
    public void newGamesMenu() {
        newUI();
        System.out.println("newGamesMenu");
    }
}
