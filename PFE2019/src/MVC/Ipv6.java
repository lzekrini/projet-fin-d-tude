package MVC;

import jbotsim.*;

public class Ipv6 extends Node{

    private String type;

    public Ipv6()
    {
        setIcon("./src/img/ipv6.png");
        setSize(30);
        type="IPV6";
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