/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.xml;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Ryan
 */
public class InstanceConnection {

    public static InstanceConnection defaultInstance() {
        InstanceConnection ic = new InstanceConnection();
        ic.setTo("Float_2");
        ic.setForParameter("X");

        return ic;
    }
    private String to;
    private String forParameter;

    public void setTo(String float_2) {
        this.to = "#" + float_2;
    }

    public void setForParameter(String x) {
        this.forParameter = x;
    }

    @XmlAttribute(name = "to")
    public String getTo() {
        return to;
    }
    @XmlAttribute(name = "forParameter")
    public String getForParameter() {
        return forParameter;
    }
}

