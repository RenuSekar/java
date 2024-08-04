import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

class Artwork {
    String title;
    String artist;
    double price;
    ImageIcon image;

    public Artwork(String title, String artist, double price, String imagePath) {
        this.title = title;
        this.artist = artist;
        this.price = price;
        this.image = new ImageIcon(imagePath);
    }

    @Override
    public String toString() {
        return title + " by " + artist + " - $" + price;
    }
}

class Exhibition {
    String name;
    DefaultListModel<Artwork> exhibits = new DefaultListModel<>();

    public Exhibition(String name) {
        this.name = name;
    }

    public void addArtwork(Artwork artwork) {
        exhibits.addElement(artwork);
    }

    public DefaultListModel<Artwork> getExhibits() {
        return exhibits;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Gallery {
    DefaultListModel<Artwork> artworks = new DefaultListModel<>();
    DefaultListModel<Exhibition> exhibitions = new DefaultListModel<>();

    public void addArtwork(Artwork artwork) {
        artworks.addElement(artwork);
    }

    public void organizeExhibition(Exhibition exhibition) {
        exhibitions.addElement(exhibition);
    }

    public void sellArtwork(Artwork artwork) {
        artworks.removeElement(artwork);
    }

    public DefaultListModel<Artwork> getArtworks() {
        return artworks;
    }

    public DefaultListModel<Exhibition> getExhibitions() {
        return exhibitions;
    }
}

class ArtworkCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Artwork) {
            Artwork artwork = (Artwork) value;
            label.setText("<html><b>" + artwork.title + "</b><br>" +
                    "by " + artwork.artist + "<br>" +
                    "$" + artwork.price + "</html>");
            label.setIcon(artwork.image);
        }
        return label;
    }
}

public class ArtGalleryManagementSystem extends JFrame {
    private Gallery gallery;

    private JList<Exhibition> exhibitionList;
    private JList<Artwork> artworkList;

    public ArtGalleryManagementSystem() {
        gallery = new Gallery();

        JButton addArtworkButton = new JButton("Add Artwork");
        JButton organizeExhibitionButton = new JButton("Organize Exhibition");
        JButton sellArtworkButton = new JButton("Sell Artwork");

        exhibitionList = new JList<>(gallery.getExhibitions());
        artworkList = new JList<>(gallery.getArtworks());
        JScrollPane exhibitionScrollPane = new JScrollPane(exhibitionList);
        JScrollPane artworkScrollPane = new JScrollPane(artworkList);

        exhibitionList.setCellRenderer(new DefaultListCellRenderer());
        artworkList.setCellRenderer(new ArtworkCellRenderer());

        addArtworkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter Artwork Title:");
                String artist = JOptionPane.showInputDialog("Enter Artist:");
                double price = Double.parseDouble(JOptionPane.showInputDialog("Enter Price:"));

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    String imagePath = fileChooser.getSelectedFile().getPath();
                    Artwork artwork = new Artwork(title, artist, price, imagePath);
                    gallery.addArtwork(artwork);
                    JOptionPane.showMessageDialog(null, "Artwork added successfully!");
                }
            }
        });

        organizeExhibitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String exhibitionName = JOptionPane.showInputDialog("Enter Exhibition Name:");
                Exhibition exhibition = new Exhibition(exhibitionName);

                DefaultListModel<Artwork> artworks = gallery.getArtworks();
                for (int i = 0; i < artworks.getSize(); i++) {
                    exhibition.addArtwork(artworks.getElementAt(i));
                }

                gallery.organizeExhibition(exhibition);
                updateExhibitionList();
                JOptionPane.showMessageDialog(null, "Exhibition organized successfully!");
            }
        });

        sellArtworkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = artworkList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Artwork selectedArtwork = gallery.getArtworks().getElementAt(selectedIndex);
                    gallery.sellArtwork(selectedArtwork);
                    JOptionPane.showMessageDialog(null, "Artwork sold successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an artwork to sell.");
                }
            }
        });

        exhibitionList.addListSelectionListener(e -> updateArtworkList());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(addArtworkButton, BorderLayout.NORTH);
        panel.add(organizeExhibitionButton, BorderLayout.CENTER);
        panel.add(sellArtworkButton, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.WEST);
        add(exhibitionScrollPane, BorderLayout.NORTH);
        add(artworkScrollPane, BorderLayout.CENTER);

        setTitle("Art Gallery Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateExhibitionList() {
        exhibitionList.setModel(gallery.getExhibitions());
    }

    private void updateArtworkList() {
        Exhibition selectedExhibition = exhibitionList.getSelectedValue();
        if (selectedExhibition != null) {
            artworkList.setModel(selectedExhibition.getExhibits());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ArtGalleryManagementSystem();
            }
        });
    }
}
