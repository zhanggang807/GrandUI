// $Id$
/*
 * ====================================================================
 * Copyright (c) 2002-2003, Christophe Labouisse All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package net.ggtools.grand.ui.actions;

import java.util.Collection;

import net.ggtools.grand.filters.ConnectedToNodeFilter;
import net.ggtools.grand.filters.GraphFilter;
import net.ggtools.grand.ui.graph.Draw2dNode;
import net.ggtools.grand.ui.graph.GraphSelectionListener;
import net.ggtools.grand.ui.widgets.GraphWindow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.action.Action;

/**
 * An action to add a filter to the currently selected node.
 * 
 * @author Christophe Labouisse
 */
public class FilterConnectedToNodeAction extends Action implements GraphSelectionListener {
    private static final Log log = LogFactory.getLog(FilterConnectedToNodeAction.class);

    private static final String DEFAULT_ACTION_NAME = "Filter connected to selected node";

    private final GraphWindow window;

    private String currentNode;

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        final GraphFilter filter = new ConnectedToNodeFilter(currentNode);
        window.getControler().addFilter(filter);
    }

    public FilterConnectedToNodeAction(final GraphWindow parent) {
        super(DEFAULT_ACTION_NAME);
        window = parent;
        setEnabled(false);
        window.getControler().addSelectionListener(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.ggtools.grand.ui.graph.GraphSelectionListener#selectionChanged(java.util.Iterator)
     */
    public void selectionChanged(Collection selectedNodes) {
        final boolean isEnabled = selectedNodes.size() == 1;
        if (isEnabled) {
            currentNode = ((Draw2dNode)selectedNodes.iterator().next()).getName();
        }
        setEnabled(isEnabled);
    }

}