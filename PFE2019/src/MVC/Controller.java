package MVC;
import jbotsim.Node;
import jbotsim.event.SelectionListener;
import jbotsimx.ui.CommandListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements  ActionListener,SelectionListener,CommandListener {
    Model model;
    View view;
    String x;

    public Controller(Model model ,View view){
        this.model=model;
        this.view=view;
////////////////////////////////////// Add Events /////////////////////////////////////////////
        view.tp.addSelectionListener(this);
        view.b1.addActionListener(this);
        view.Baddlink.addActionListener(this);
        view.Bremove.addActionListener(this);
        view.Brun.addActionListener(this);
        view.ipv4.addActionListener(this);
        view.ipv6.addActionListener(this);
        view.jtp.addCommandListener(this);
        view.mrouteur.addActionListener(this);
        view.tp.setDefaultNodeModel(Ipv4.class);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==view.b1)
        {
            view.b1.setToolTipText("<html>"
                + "zekrini lydia"
                +"<br>"
                + "hamdi bissen"
                +"<br>"
                + "akodad oussama"

                + "</html>");
        }
       else if (e.getSource() == view.ipv4)
        {
            view.tp.setDefaultNodeModel(Ipv4.class);
        }
        else if(e.getSource() == view.ipv6)
        {
            view.tp.setDefaultNodeModel(Ipv6.class);
        }
        else if(e.getSource()==view.Baddlink)
        {
            x= "add";
        }
        else if(e.getSource()==view.Bremove)
        {
            x= "Remove";
        }
        else if (e.getSource()==view.mrouteur)
        {
            x= "Modify";
        }
        else if ( e.getSource()==view.Brun)
        {
            model.testConvertTopo(view.tp);
        }
    }




    @Override
    public void onSelection(Node node)
    {
        if (x.equals("add"))
        {
            model.counter(view.tp,node);
        }
        else if (x.equals("Remove"))
        {
            model.countdown(view.tp,node);
        }
        else if (x.equals("Modify"))
        {
            model.modify(view.tp,node);
        }
    }

    @Override
    public void onCommand(String s)
    {
        if (s.equals("Clear"))
        {
            view.tp.clear();
        }
    }
}
