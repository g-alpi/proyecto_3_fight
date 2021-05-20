import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectPN extends JFrame {
    //declaring variables
    private String user;
    private String MySqlUser;
    private String MySqlPwd;
    private Boolean confirmed=false;
    private JTextField userT = new JTextField();
    private JTextField sqlUserT = new JTextField();
    private JPasswordField sqlPwdT = new JPasswordField();

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMySqlUser() {
        return MySqlUser;
    }

    public void setMySqlUser(String mySqlUser) {
        MySqlUser = mySqlUser;
    }

    public String getMySqlPwd() {
        return MySqlPwd;
    }

    public void setMySqlPwd(String mySqlPwd) {
        MySqlPwd = mySqlPwd;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public ConnectPN(){
        //setting attributes of the frame and adding components
        this.setSize(400,200);
        this.setTitle("LOGIN");
        this.setResizable(false);

        JLabel uT= new JLabel("Username");
        JLabel uM= new JLabel("MySQL username");
        JLabel pM= new JLabel("MySQL password");

        JPasswordField pwd = new JPasswordField();


        JPanel text = new JPanel();

        text.setLayout(new BoxLayout(text,BoxLayout.Y_AXIS));
        text.add(uT);
        uT.setHorizontalAlignment(SwingConstants.CENTER);
        text.add(userT);
        text.add(uM);
        uM.setHorizontalAlignment(SwingConstants.CENTER);
        text.add(sqlUserT);
        text.add(pM);
        pM.setHorizontalAlignment(SwingConstants.CENTER);
        text.add(sqlPwdT);

        JLabel title = new JLabel("CREDENTIALS");

        JButton confirm = new JButton("confirm");

        this.setLayout(new BorderLayout());
        this.add(title,BorderLayout.NORTH);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(text,BorderLayout.CENTER);
        this.add(confirm,BorderLayout.SOUTH);
        title.setOpaque(false);
        text.setOpaque(false);
        confirm.setOpaque(false);

        this.setBackground(Color.RED);
        this.setVisible(true);

        ConnectPN Panel = this;
        //making the confirm button do its purpose
        confirm.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Panel.setUser(userT.getText());
                        Panel.setMySqlUser(sqlUserT.getText());
                        Panel.setMySqlPwd(sqlPwdT.getText());
                        Panel.setConfirmed(true);
                        Panel.dispose();
                    }
                }
        );
    }
}
