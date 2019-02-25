package MVC;
import jbotsim.*;
/**
 * Created by bhamdi001 on 25/02/19.
 */
public class Convertisseur extends Node  {
    private String type;
    private String icon;
    public Convertisseur(){
        setIcon("./src/img/Conv.png");
        setSize(30);
        type="Conv";

    }

    public String getType(){
        return type;
    }
    public void setType (String tp){

        this.type=tp;

    }
}
