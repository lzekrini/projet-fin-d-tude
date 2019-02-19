package MVC;
import jbotsim.Node;
import jbotsim.event.SelectionListener;
import jbotsimx.ui.CommandListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements  ActionListener,SelectionListener,CommandListener {
    Model model;
    View view;
    String x;


    public Controller(Model model ,View view){
        this.model=model;
        this.view=view;
        ////////////////////////////Ajout des évenements///////////////////////////
        view.tp.addSelectionListener(this);
        view.b1.addActionListener(this);
        view.b2.addActionListener(this);
        view.b3.addActionListener(this);
        view.Baddlink.addActionListener(this);
        view.Bremove.addActionListener(this);
        view.ipv4.addActionListener(this);
        view.ipv6.addActionListener(this);
        view.jtp.addCommandListener(this);
        view.mrouteur.addActionListener(this);
        view.tp.setDefaultNodeModel(Ipv4.class);





    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==view.b1){JTextArea textArea = new JTextArea(5, 20);
            JScrollPane scrollPane = new JScrollPane(textArea); }

       else if(e.getSource()==view.b2){ view.b2.setText("this application"); }
       else if(e.getSource()==view.b3){ view.b3.setText("this application"); }


       else if (e.getSource() == view.ipv4) {
            view.tp.setDefaultNodeModel(Ipv4.class);
        }
        else if(e.getSource() == view.ipv6) {
            view.tp.setDefaultNodeModel(Ipv6.class);
        }

        else if(e.getSource()==view.Baddlink) { x= "add";
        }
        else if(e.getSource()==view.Bremove) { x= "Remove";
        }
        else if (e.getSource()==view.mrouteur) {x= "Modify";
        }
    }




    @Override
    public void onSelection(Node node) {

        if (x.equals("add")) { model.counter(view.tp,node);
        }

       else if (x.equals("Remove")) { model.countdown(view.tp,node);
        }
        else if (x.equals("Modify")){model.modify(view.tp,node);}

    }

    @Override
    public void onCommand(String s) {

        if (s.equals("Clear") ) {

           view.tp.clear();

        }

    }
}
