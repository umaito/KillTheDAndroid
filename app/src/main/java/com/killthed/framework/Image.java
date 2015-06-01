package com.killthed.framework;

/**
 * Created by Dylan on 22/05/2015.
 */
import com.killthed.framework.Graphics.ImageFormat;

public interface Image {
    public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();
}
