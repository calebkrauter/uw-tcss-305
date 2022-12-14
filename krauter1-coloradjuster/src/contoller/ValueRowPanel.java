//package contoller;
//import static java.lang.Integer.parseInt;
//import static model.PropertyChangeEnabledMutableColor.PROPERTY_BLUE;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.FocusAdapter;
//import java.awt.event.FocusEvent;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//import javax.swing.AbstractAction;
//import javax.swing.BorderFactory;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JSlider;
//import javax.swing.JTextField;
//import model.ColorModel;
//import model.PropertyChangeEnabledMutableColor;
//
//public class ValueRowPanel extends JPanel implements PropertyChangeListener{
//    /**
//     * A generated serial version UID for object Serialization.
//     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
//     */
//    private static final long serialVersionUID = 2284116355218892348L;
//
//    /** The size of the increase/decrease buttons. */
//    private static final Dimension BUTTON_SIZE = new Dimension(26, 26);
//
//    /** The size of the text label. */
//    private static final Dimension LABEL_SIZE = new Dimension(45, 26);
//
//    /** The number of columns in width of the TextField. */
//    private static final int TEXT_FIELD_COLUMNS = 3;
//
//    /** The amount of padding for the change panel. */
//    private static final int HORIZONTAL_PADDING = 30;
//
//    /** The backing model for the system. */
//    private final PropertyChangeEnabledMutableColor myColor;
//
//    /** The CheckBox that enables/disables editing of the TextField. */
//    private final JCheckBox myEnableEditButton;
//
//    /** The TextField that allows the user to type input for the color value. */
//    private final JTextField myValueField;
//
//    /** The Button that when clicked increases the color value. */
//    private final JButton myIncreaseButton;
//
//    /** The Button that when clicked decreases the color value. */
//    private final JButton myDecreaseButton;
//
//    /** The Slider that when adjusted, changes the color value. */
//    private final JSlider myValueSlider;
//
//    /** The panel that visually displays ONLY the BLUE value for the color. */
//    private final JPanel myColorDisplayPanel;
//
//    /**
//     * Creates a Panel with components used to change and display the Blue value for the
//     * backing Color model.
//     * @param theColor the backing model for the system
//     */
//    public ValueRowPanel(final PropertyChangeEnabledMutableColor theColor) {
//        super();
//        myColor = theColor;
//        myEnableEditButton = new JCheckBox("Enable edit");
//        myValueField = new JTextField();
//        myIncreaseButton = new JButton();
//        myDecreaseButton = new JButton();
//        myValueSlider = new JSlider();
//        myColorDisplayPanel = new JPanel();
//        layoutComponents();
//        addListeners();
//    }
//
//    /**
//     * Setup and add the GUI components for this panel.
//     */
//    private void layoutComponents() {
//        myColorDisplayPanel.setPreferredSize(BUTTON_SIZE);
//        if (myColor.getClass().equals(myColor.getBlue())) {
//            myColorDisplayPanel.setBackground(new Color(0, 0, myColor.getBlue()));
//        }
//        final JLabel rowLabel = new JLabel("Blue: ");
//        rowLabel.setPreferredSize(LABEL_SIZE);
//        myValueField.setText(String.valueOf(myColor.getBlue()));
//        myValueField.setEditable(false);
//        myValueField.setColumns(TEXT_FIELD_COLUMNS);
//        myValueField.setHorizontalAlignment(JTextField.RIGHT);
//
//        final JPanel rightPanel = new JPanel();
//        rightPanel.setBorder(BorderFactory.createEmptyBorder(0,
//                HORIZONTAL_PADDING,
//                0,
//                HORIZONTAL_PADDING));
//        rightPanel.setBackground(rightPanel.getBackground().darker());
//        myIncreaseButton.setIcon(new ImageIcon("./images/ic_increase_value.png"));
//        myIncreaseButton.setPreferredSize(BUTTON_SIZE);
//        myValueSlider.setMaximum(ColorModel.MAX_VALUE);
//        myValueSlider.setMinimum(ColorModel.MIN_VALUE);
//        myValueSlider.setValue(myColor.getBlue());
//        myValueSlider.setBackground(rightPanel.getBackground());
//        myDecreaseButton.setIcon(new ImageIcon("./images/ic_decrease_value.png"));
//        myDecreaseButton.setPreferredSize(BUTTON_SIZE);
//        rightPanel.add(myDecreaseButton);
//        rightPanel.add(myValueSlider);
//        rightPanel.add(myIncreaseButton);
//
//        add(myColorDisplayPanel);
//        add(rowLabel);
//        add(myValueField);
//        add(myEnableEditButton);
//        add(rightPanel);
//    }
//
//    /**
//     * addListeners uses listeners to handle
//     * events from user interaction with the gui.
//     */
//    private void addListeners() {
//        final int initialValue = myColor.getBlue();
//        final int minValue = 0;
//        final int maxValue = 255;
//        //DO not remove the following statement.
//        myColor.addPropertyChangeListener(this);
//        myDecreaseButton.addActionListener(theEvent -> {
//            if (myColor.getBlue() == minValue) {
//                myDecreaseButton.setEnabled(false);
//            } else {
//                myColor.adjustBlue(-1);
//            }
//            if (myColor.getBlue() < maxValue && !myIncreaseButton.isEnabled()) {
//                myIncreaseButton.setEnabled(true);
//            }
//        });
//        myIncreaseButton.addActionListener(theEvent -> {
//            if (myColor.getBlue() == maxValue) {
//                myIncreaseButton.setEnabled(false);
//            } else {
//                myColor.adjustBlue(1);
//            }
//            if (myColor.getBlue() > minValue && !myDecreaseButton.isEnabled()) {
//                myDecreaseButton.setEnabled(true);
//            }
//        });
//        myValueSlider.addChangeListener(theEvent -> {
//            if (myValueSlider.getValueIsAdjusting()) {
//                myColor.setBlue(myValueSlider.getValue());
//            }
//            if (myColor.getBlue() > minValue && !myDecreaseButton.isEnabled()) {
//                myDecreaseButton.setEnabled(true);
//            } else if (myColor.getBlue() == minValue) {
//                myDecreaseButton.setEnabled(false);
//            }
//            if (myColor.getBlue() < maxValue && !myIncreaseButton.isEnabled()) {
//                myIncreaseButton.setEnabled(true);
//            } else if (myColor.getBlue() == maxValue) {
//                myIncreaseButton.setEnabled(false);
//            }
//        });
//
//        myEnableEditButton.addChangeListener(theEvent -> {
//            if (myEnableEditButton.isSelected()) {
//                myValueField.setEditable(true);
//            } else {
//                myValueField.setEditable(false);
//            }
//        });
//
//        myValueField.addActionListener(new AbstractAction() {
//            @Override
//            public void actionPerformed(final ActionEvent theEvent) {
//                try {
//                    if (myColor.getBlue() >= minValue && myColor.getBlue() <= maxValue) {
//                        myColor.setBlue(Integer.valueOf(parseInt(myValueField.getText())));
//                    }
//                } catch (final Exception exception) {
//                    myColor.setBlue(initialValue);
//                    myValueField.setText(String.valueOf(myColor.getBlue()));
//                }
//            }
//        });
//        myValueField.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusLost(final FocusEvent theEvent) {
//                try {
//                    if (myColor.getBlue() >= minValue && myColor.getBlue() <= maxValue) {
//                        myColor.setBlue(Integer.valueOf(parseInt(myValueField.getText())));
//                    }
//                } catch (final Exception exception) {
//                    myColor.setBlue(initialValue);
//                    myValueField.setText(String.valueOf(myColor.getBlue()));
//                }
//            }
//        });
//    }
//
//    @Override
//    public void propertyChange(final PropertyChangeEvent theEvent) {
//        if (PROPERTY_BLUE.equals(theEvent.getPropertyName())) {
//            myValueField.setText(theEvent.getNewValue().toString());
//            myValueSlider.setValue((Integer) theEvent.getNewValue());
//            myColorDisplayPanel.
//                    setBackground(new Color(0, 0, (Integer) theEvent.getNewValue()));
//        }
//    }
//
//}
