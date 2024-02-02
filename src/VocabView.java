package src;  
import javax.swing.*;

import VocabParsing.Verb;
import VocabParsing.Vocab;
import java.awt.*;
import java.util.ArrayList;


public class VocabView extends JPanel {
    String[] pronomen = {"Ich", "Du", "Er/Sie/Es", "Wir", "Ihr", "Sie"};
    String[] zeitformen = {"Präsens", "Imperfekt", "Perfekt", "Plusquamperfekt", "Futur I", "Futur II"};
    int numRows = pronomen.length + 1;
    int numCols = zeitformen.length + 1;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double screenWidth = screenSize.getWidth();
    double screenHeight = screenSize.getHeight();

    // Definieren Sie die Werte relativ zur Bildschirmgröße
    int cellWidth = (int) (screenWidth / 10); // 1/10 der Bildschirmbreite
    int cellHeight = (int) (screenHeight / 20); // 1/20 der Bildschirmhöhe
    int startX = (int) (screenWidth / 20); // 1/20 der Bildschirmbreite
    int startY = (int) (screenHeight / 8); // 1/10 der Bildschirmhöhe
    int xTitle;



    public VocabView(JPanel content, Vocab v) {
        
     
        
        setLayout(null);
        setBackground(Main.BodyColor);

        JLabel title = new JLabel(v.getBasicForm());
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
        title.setForeground(Main.TextColor);

        int length = title.getText().length();
        if (length <= 5) {
        xTitle = 600;
        } else {
         // Hier können Sie die Variable ändern, je nachdem, wie viel der X-Wert pro Buchstabe geändert werden soll
        int xPerLetter = 30; // Ändern Sie dies nach Bedarf
        xTitle = 590 - (xPerLetter * (length - 5) / 2);
        }

        // Setze die Position des Titels
        title.setBounds(xTitle, -50, 400, 200);

        String germanTranslation = v.getGerman().toString().replace("[", "").replace("]", "");
        // Nur den Text bis zum ersten Komma extrahieren
        int commaIndex = germanTranslation.indexOf(",");
        if (commaIndex != -1) {
        germanTranslation = germanTranslation.substring(0, commaIndex);
         }
        JLabel translation = new JLabel(germanTranslation);
        translation.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        translation.setForeground(Main.TextColor);

        // Berechnung der Preferred Size basierend auf dem Inhalt
        Dimension translationSize = translation.getPreferredSize();

        // Berechnung der Position
        int xTranslation = startX + cellWidth / 2 - translationSize.width / 2;
        int yTranslation = startY + cellHeight / 2 - translationSize.height / 2;
        translation.setBounds(xTranslation, yTranslation, translationSize.width, translationSize.height);
        

        for (int i = 0; i < zeitformen.length; i++) {
            JLabel zeitformLabel = new JLabel(zeitformen[i]);
            zeitformLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
            zeitformLabel.setForeground(Main.TextColor);
        
            // Anpassung der X- und Y-Positionen
            int x = startX + (i + 1) * cellWidth + cellWidth / 2 - zeitformLabel.getPreferredSize().width / 2;
            int y = startY + cellHeight / 2 - zeitformLabel.getPreferredSize().height / 2;
        
            zeitformLabel.setBounds(x, y, zeitformLabel.getPreferredSize().width, zeitformLabel.getPreferredSize().height);
            add(zeitformLabel);
        }

        for (int i = 0; i < pronomen.length; i++) {
            JLabel pronounLabel = new JLabel(pronomen[i]);
            pronounLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
            pronounLabel.setForeground(Main.TextColor);
        
            // Anpassung der X- und Y-Positionen
            int xPronoun = startX + (int) (cellWidth / 2 - pronounLabel.getPreferredSize().width / 2);
            int yPronoun = startY + (i + 1) * cellHeight + cellHeight / 2 - pronounLabel.getPreferredSize().height / 2;
        
            pronounLabel.setBounds(xPronoun, yPronoun, pronounLabel.getPreferredSize().width, pronounLabel.getPreferredSize().height);
            add(pronounLabel);
        }
        
        
        if (v instanceof Verb) {
            // Es handelt sich um ein Verb
            displayConjugations((Verb) v);
        } else {
            // Es handelt sich nicht um ein Verb
            // Hier kannst du ggf. eine alternative Aktion durchführen
        }
        add(translation);
        add(title);
        content.add(this);
    }

    private void displayConjugations(Verb verb) {
        // Hole die Zeitformen für "Ich" und "Du"


        ArrayList<String> PräsentsConjugations = verb.getPraesens();

        ArrayList<String> ImperfektConjugations = verb.getImperfekt();  

        ArrayList<String> PerfektConjugations = verb.getPerfekt();  

        ArrayList<String> PlusquamperfektConjugations = verb.getPlusquamperfekt();  

        ArrayList<String> FuturIConjugations = verb.getFuturI();  

        ArrayList<String> FuturIIConjugations = verb.getFuturII();  

    
        // Zeige die Konjugationen in der GUI an
        for (int i = 0; i < PräsentsConjugations.size(); i++) {
            JLabel ichLabel = new JLabel(PräsentsConjugations.get(i));
            ichLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
            ichLabel.setForeground(Main.TextColor);
        
            // Anpassung der X-Position für die zweite Spalte
            int x = startX + cellWidth + cellWidth / 2 - ichLabel.getPreferredSize().width / 2;
            // Anpassung der Y-Position um 3 Boxen nach oben
            int y = startY + 3 * cellHeight - 3 * cellHeight + (i + 1) * cellHeight + cellHeight / 2 - ichLabel.getPreferredSize().height / 2;
        
            ichLabel.setBounds(x, y, ichLabel.getPreferredSize().width, ichLabel.getPreferredSize().height);
            add(ichLabel);
        }
        

        for (int i = 0; i < ImperfektConjugations.size(); i++) {
            JLabel DuLabel = new JLabel(ImperfektConjugations.get(i));
            DuLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
            DuLabel.setForeground(Main.TextColor);

            // Anpassung der X-Position für die zweite Spalte
            int x = startX + cellWidth + cellWidth / 2 - DuLabel.getPreferredSize().width / 2 + cellWidth;  // Hier wurde der Wert um eine Box nach rechts verschoben
            // Anpassung der Y-Position um 3 Boxen nach oben
            int y = startY + 3 * cellHeight - 3 * cellHeight + (i + 1) * cellHeight + cellHeight / 2 - DuLabel.getPreferredSize().height / 2;

            DuLabel.setBounds(x, y, DuLabel.getPreferredSize().width, DuLabel.getPreferredSize().height);
            add(DuLabel);
      }

      for (int i = 0; i < PerfektConjugations.size(); i++) {
        JLabel erLabel = new JLabel(PerfektConjugations.get(i));
        erLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        erLabel.setForeground(Main.TextColor);
    
        // Anpassung der X-Position für die zweite Spalte und um eine Box nach rechts verschoben
        int x = startX + 3 * cellWidth + cellWidth / 2 - erLabel.getPreferredSize().width / 2;  // Hier wurde der Wert um eine zusätzliche Box nach rechts verschoben
        // Anpassung der Y-Position um 3 Boxen nach oben
        int y = startY + 3 * cellHeight - 3 * cellHeight + (i + 1) * cellHeight + cellHeight / 2 - erLabel.getPreferredSize().height / 2;
    
        erLabel.setBounds(x, y, erLabel.getPreferredSize().width, erLabel.getPreferredSize().height);
        add(erLabel);
    }

    for (int i = 0; i < PlusquamperfektConjugations.size(); i++) {
        JLabel wirLabel = new JLabel(PlusquamperfektConjugations.get(i));
        wirLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        wirLabel.setForeground(Main.TextColor);
    
        // Anpassung der X-Position für die zweite Spalte und um eine Box nach rechts verschoben
        int x = startX + 4 * cellWidth + cellWidth / 2 - wirLabel.getPreferredSize().width / 2;  // Hier wurde der Wert um eine zusätzliche Box nach rechts verschoben
        // Anpassung der Y-Position um 3 Boxen nach oben
        int y = startY + 3 * cellHeight - 3 * cellHeight + (i + 1) * cellHeight + cellHeight / 2 - wirLabel.getPreferredSize().height / 2;
    
        wirLabel.setBounds(x, y, wirLabel.getPreferredSize().width, wirLabel.getPreferredSize().height);
        add(wirLabel);
    }

    for (int i = 0; i < FuturIConjugations.size(); i++) {
        JLabel ihrLabel = new JLabel(FuturIConjugations.get(i));
        ihrLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        ihrLabel.setForeground(Main.TextColor);
    
        // Anpassung der X-Position für die zweite Spalte und um eine Box nach rechts verschoben
        int x = startX + 5 * cellWidth + cellWidth / 2 - ihrLabel.getPreferredSize().width / 2;  // Hier wurde der Wert um eine zusätzliche Box nach rechts verschoben
        // Anpassung der Y-Position um 3 Boxen nach oben
        int y = startY + 3 * cellHeight - 3 * cellHeight + (i + 1) * cellHeight + cellHeight / 2 - ihrLabel.getPreferredSize().height / 2;
    
        ihrLabel.setBounds(x, y, ihrLabel.getPreferredSize().width, ihrLabel.getPreferredSize().height);
        add(ihrLabel);
    }

    for (int i = 0; i < FuturIIConjugations.size(); i++) {
        JLabel sieLabel = new JLabel(FuturIIConjugations.get(i));
        sieLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        sieLabel.setForeground(Main.TextColor);
    
        // Anpassung der X-Position für die zweite Spalte und um eine Box nach rechts verschoben
        int x = startX + 6 * cellWidth + cellWidth / 2 - sieLabel.getPreferredSize().width / 2;  // Hier wurde der Wert um eine zusätzliche Box nach rechts verschoben
        // Anpassung der Y-Position um 3 Boxen nach oben
        int y = startY + 3 * cellHeight - 3 * cellHeight + (i + 1) * cellHeight + cellHeight / 2 - sieLabel.getPreferredSize().height / 2;
    
        sieLabel.setBounds(x, y, sieLabel.getPreferredSize().width, sieLabel.getPreferredSize().height);
        add(sieLabel);
    }

   
        
        
        
        
        
    }
    

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Main.TextColor);
        g2d.setStroke(new BasicStroke(5));
    
    
        // Zeichnen Sie vertikale Linien für die Zeitformen
        for (int i = 0; i < numCols; i++) {
            int x = startX + i * cellWidth;
            g2d.drawLine(x, startY, x, startY + numRows * cellHeight);
        }
    
        // Zeichnen Sie horizontale Linien für die Pronomen
        for (int i = 0; i < numRows; i++) {
            int y = startY + i * cellHeight;
            g2d.drawLine(startX, y, startX + numCols * cellWidth, y);
        }

        g2d.drawLine(startX, startY + numRows * cellHeight, startX + numCols * cellWidth, startY + numRows * cellHeight);
        g2d.drawLine(startX + numCols * cellWidth, startY, startX + numCols * cellWidth, startY + numRows * cellHeight);
    }

  
}

