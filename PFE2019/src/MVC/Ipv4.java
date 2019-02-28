package MVC;
import jbotsim.*;


public class Ipv4 extends Node{

    private String type;

    public Ipv4(){
        setIcon("./src/img/ipv4.png");
        setSize(30);
        type="IPV4";

    }

    public String getType()
    {
        return type;
    }
    public void setType (String tp)
    {
        this.type=tp;
    }

}