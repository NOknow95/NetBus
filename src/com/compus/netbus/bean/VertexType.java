package com.compus.netbus.bean;

public class VertexType
{
    private String name;
    private int id;
 
    public VertexType(int i, String a)
    {
        id = i;
        name = a;
    }
 
    public String getName()
    {
        return name;
    }
 
    public void setName(String name)
    {
        this.name = name;
    }
 
    public int getId()
    {
        return id;
    }
 
    public void setId(int id)
    {
        this.id = id;
    }
}
