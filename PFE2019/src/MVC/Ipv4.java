package MVC;
import jbotsim.*;


public class Ipv4 extends Node{
    private String type;
    private String icon;
    public Ipv4(){
        setIcon("./src/img/ipv4.png");
        setSize(30);
        type="IPV4";

    }

    public String getType(){
        return type;
    }
    public void setType (String tp){
       /* if ( tp.equals("IPV6"))
        {
            this.type=tp;
            setIcon("./src/img/ipv6.png");
        }
        else
            this.type=tp;
    }*/
        this.type=tp;

}


    public String getIcon(){
        return  icon;
    }

    public void setIcon1( String icon){
        this.setIcon(icon);
    }
}