import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class OptionsPN extends JPanel {

    Frame framePrincipal = (Frame) Frame.getFrames()[0];
    JComboBox resolutions = new JComboBox();
    JCheckBox fullScreen = new JCheckBox("Fullscreen",true);
    JButton applyBT = new JButton("Apply");
    JButton atras = new JButton("Back");

    public OptionsPN(){

        this.setLayout(new FlowLayout());

        /* Adding resolutions to the game */
        resolutions.addItem("1920x1080");
        resolutions.addItem("1600x900");
        resolutions.addItem("1280x720");


        /* Adding components to panel */
        fullScreen.setEnabled(true);
        this.add(resolutions);
        this.add(fullScreen);


        /* Applying the selected parameters */
        applyBT.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {

                        /* Resolution */
                        String[] res = resolutions.getSelectedItem().toString().split("x");
                        framePrincipal.setWidth(Integer.valueOf(res[0]));
                        framePrincipal.setHeight(Integer.valueOf(res[1]));
                        framePrincipal.setSize(framePrincipal.getWidth(),framePrincipal.getHeight());

                        /* Full Screen */
                        if (fullScreen.isSelected() && Integer.valueOf(res[0])==1920) {
                            framePrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        }
                    }
                }
        );
        this.add(applyBT);

        /* Make fullscreen function, only for 1920x1080 */
        resolutions.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent itemEvent) {

                        String[] res = resolutions.getSelectedItem().toString().split("x");
                        if (Integer.valueOf(res[0]) !=1920){
                            fullScreen.setEnabled(false);
                        }else{
                            fullScreen.setEnabled(true);
                        }
                    }
                }
        );

        /* Back to Main menu button */
        atras.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        CardLayout cl = (CardLayout) (framePrincipal.getCards().getLayout());
                        cl.show(framePrincipal.getCards(),"MainMenu");
                    }
                }
        );
        this.add(atras);


    }

}
