package com.example.cis183_hw01_rgb;

public class Colors
{
    private int r;
    private int g;
    private int b;
    private String hex;

    public Colors(int r, int g, int b, String hex)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hex = hex;
    }

    public int getR()
    {
        return r;
    }

    public void setR(int r)
    {
        this.r = r;
    }

    public int getG()
    {
        return g;
    }

    public void setG(int g)
    {
        this.g = g;
    }

    public int getB()
    {
        return b;
    }

    public void setB(int b)
    {
        this.b = b;
    }

    public String getHex()
    {
        return hex;
    }

    public void setHex(String hex)
    {
        this.hex = hex;
    }
}
