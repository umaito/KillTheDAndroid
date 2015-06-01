package com.killthed.framework.implementation;

/**
 * Created by Dylan on 22/05/2015.
 */
import java.util.List;

import android.view.View.OnTouchListener;

import com.killthed.framework.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<TouchEvent> getTouchEvents();
}