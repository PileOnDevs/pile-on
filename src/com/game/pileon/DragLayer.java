/*
 * This is a modified version of a class from the Android Open Source Project. 
 * The original copyright and license information follows.
 * 
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.game.pileon;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;

/**
 * A ViewGroup that coordinates dragging across its descendants.
 * 
 * <p>
 * This class used DragLayer in the Android Launcher activity as a model. It is
 * a bit different in several respects: (1) It extends TableLayout; (2) it
 * implements DragSource methods that were done in a separate Workspace class in
 * the Launcher.
 */
public class DragLayer extends TableLayout implements DragSource {
    DragController mDragController;
    
    /**
     * Used to create a new DragLayer from XML.
     * 
     * @param context
     *            The application's context.
     * @param attrs
     *            The attribtues set containing the Workspace's customization
     *            values.
     */
    public DragLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public void setDragController(DragController controller) {
        mDragController = controller;
    }
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return mDragController.dispatchKeyEvent(event)
                || super.dispatchKeyEvent(event);
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragController.onInterceptTouchEvent(ev);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mDragController.onTouchEvent(ev);
    }
    
    @Override
    public boolean dispatchUnhandledMove(View focused, int direction) {
        return mDragController.dispatchUnhandledMove(focused, direction);
    }
    
    /**
	 */
    // DragSource interface methods
    
    /**
     * This method is called to determine if the DragSource has something to
     * drag.
     * 
     * @return True if there is something to drag
     */
    
    public boolean allowDrag() {
        return true;
    }
    
    /**
     * setDragController
     * 
     */
    
    /* setDragController is already defined. See above. */
    
    /**
     * onDropCompleted
     * 
     */
    
    public void onDropCompleted(View target, boolean success) {
        
        Log.i("PO Drop", "DragLayer: Drop operation completed");
    }
    
} // end class
