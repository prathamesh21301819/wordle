import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class wordle_frame extends JFrame implements ActionListener{
    JPanel p1,main;
    JLabel chn;
    JButton check, clear;
    int chances=5;
     static ArrayList<JTextField>tf =new ArrayList<>();
    Font f1=new Font("Tahoma",Font.BOLD, 20);
    boolean[] flag= new boolean[wordle_logic.wordsize];
    wordle_frame() {
        setSize(500,500);
        setTitle("Wordle game");
        main= new JPanel();
        setContentPane(main);
        main.setBackground(Color.decode("#1a1918"));

        p1=new JPanel();
        p1.setBounds(100,80,300,60);
        p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
        add(p1);
        generateTextfields();

        chn=new JLabel("Chances Remaining :"+chances);
        chn.setBounds(130,250,300,50);
        chn.setForeground(Color.white);
        chn.setFont(f1);
        add(chn);

        check=new JButton("Check");
        check.setBounds(100,350,100,50);
        check.setFont(f1);
        check.addActionListener(this);
        add(check);

        clear=new JButton("Clear");
        clear.setBounds(300,350,100,50);
        clear.setFont(f1);
        clear.addActionListener(this);
        add(clear);

        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    void  generateTextfields()
    {
        for(int i=0;i<wordle_logic.wordsize;i++)
        {
            tf.add(new JTextField());
            tf.get(i).setHorizontalAlignment(JTextField.CENTER);
            tf.get(i).setFont(f1);
            tf.get(i).setBackground(Color.decode("#3b3a39"));
            tf.get(i).setForeground(Color.white);

        }
       for(int i=0;i<wordle_logic.wordsize;i++)
       {
           p1.add(tf.get(i));
       }
    }
  public void actionPerformed(ActionEvent ae)
  {
      UIManager.put("OptionPane.messageFont", f1);

      if(ae.getSource()==check)
      {
          if (chances > 0) {
              for (int i = 0; i < wordle_logic.wordsize; i++) {
                  if (wordle_logic.matchExactletter(i)) {
                      tf.get(i).setBackground(Color.decode("#2fa84f"));
                      flag[i] = true;
                  } else if (wordle_logic.containsletter(i)) {
                      tf.get(i).setBackground(Color.decode("#d1d424"));
                  } else {
                      tf.get(i).setBackground(Color.decode("#3b3a39"));
                  }
              }
              for (boolean ele : flag) {
                  if(ele==false){
                      break;
                  }
                  else{
                      JOptionPane.showMessageDialog(null,"YOU ARE EXPERT!");
                      System.exit(0);
                  }
              }
              chances--;

              chn.setText("Chances remaining: "+chances);
          }
          else{
              JOptionPane.showMessageDialog(null,"You are a noob!");
              System.exit(0);
          }
      }

      if(ae.getSource()==clear){
          for (int i = 0; i < wordle_logic.wordsize; i++) {
              tf.get(i).setText("");
              tf.get(i).setBackground(Color.decode("#3b3a39"));
              for(boolean ele: flag){
                  ele = false;
              }
          }
      }
  }




    public static void main(String[] args) {

        new wordle_logic();
        new wordle_frame();
    }




}
