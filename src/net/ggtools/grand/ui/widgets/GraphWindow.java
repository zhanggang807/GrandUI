// $Id$
/* ====================================================================
 * Copyright (c) 2002-2003, Christophe Labouisse
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above
 *    copyright notice, this list of conditions and the following
 *    disclaimer in the documentation and/or other materials provided
 *    with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.ggtools.grand.ui.widgets;

import net.ggtools.grand.ui.graph.GraphControler;
import net.ggtools.grand.ui.graph.GraphDisplayer;
import net.ggtools.grand.ui.menu.FileMenuManager;
import net.ggtools.grand.ui.menu.GraphMenu;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * 
 * @author Christophe Labouisse
 */
public class GraphWindow extends ApplicationWindow implements GraphDisplayer {
    private static final Log log = LogFactory.getLog(GraphWindow.class);

    private MenuManager contextMenuManager;

    private Composite drawingArea;

    private FigureCanvas canvas;
    
    private final GraphControler controler;

    private Menu contextMenu;

    private MenuManager manager;

    private final static class CanvasScroller extends MouseAdapter implements
            MouseMoveListener {
        final private FigureCanvas canvas;

        final Viewport viewport;

        private int startDragX, startDragY;

        public CanvasScroller(FigureCanvas c) {
            canvas = c;
            viewport = canvas.getViewport();
        }

        /* (non-Javadoc)
         * @see org.eclipse.swt.events.MouseListener#mouseDown(org.eclipse.swt.events.MouseEvent)
         */
        public void mouseDown(MouseEvent e) {
            if (e.button == 2) {
                final Point vpLocation = viewport.getViewLocation();
                startDragX = vpLocation.x + e.x;
                startDragY = vpLocation.y + e.y;
                canvas.addMouseMoveListener(this);
            }
        }

        /* (non-Javadoc)
         * @see org.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events.MouseEvent)
         */
        public void mouseUp(MouseEvent e) {
            if (e.button == 2) {
                canvas.removeMouseMoveListener(this);
            }
        }

        /* (non-Javadoc)
         * @see org.eclipse.swt.events.MouseMoveListener#mouseMove(org.eclipse.swt.events.MouseEvent)
         */
        public void mouseMove(MouseEvent e) {
            canvas.scrollTo(startDragX - e.x, startDragY - e.y);
        }
    }

    public GraphWindow() {
        this(null);
    }

    public GraphWindow(Shell parent) {
        super(parent);
        controler = new GraphControler(this);
        setBlockOnOpen(true);
        addStatusLine();
        addMenuBar();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents(Composite parent) {
        log.debug("Creating contents");
        final Composite comp = new Composite(parent, SWT.BORDER);
        comp.setLayout(new FillLayout());
        canvas = new FigureCanvas(comp);
        canvas.getViewport().setContentsTracksHeight(true);
        canvas.getViewport().setContentsTracksWidth(true);
        canvas.setBackground(ColorConstants.white);
        canvas.setScrollBarVisibility(FigureCanvas.AUTOMATIC);
        final CanvasScroller synchronizer = new CanvasScroller(canvas);
        canvas.addMouseListener(synchronizer);
        manager = new GraphMenu(this);
        contextMenu = manager.createContextMenu(canvas);
        return canvas;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.window.ApplicationWindow#createMenuManager()
     */
    protected MenuManager createMenuManager() {
        log.debug("Creating menu manager");
        contextMenuManager = new MenuManager();
        contextMenuManager.add(new FileMenuManager(this));
        contextMenuManager.add(new GraphMenu(this));
        contextMenuManager.setVisible(true);
        return contextMenuManager;
    }

    /* (non-Javadoc)
     * @see net.ggtools.grand.ui.graph.GraphDisplayer#beginUpdate(int)
     */
    public void beginUpdate(final int totalWork) {
        final StatusLineManager slManager = getStatusLineManager();
        final IProgressMonitor monitor = slManager.getProgressMonitor();
        getShell().getDisplay().asyncExec(new Runnable() {

            public void run() {
                monitor.beginTask("Updating graph", totalWork);
            }
        });
    }

    /* (non-Javadoc)
     * @see net.ggtools.grand.ui.graph.GraphDisplayer#worked(int)
     */
    public void worked(final int workDone) {
        final StatusLineManager slManager = getStatusLineManager();
        final IProgressMonitor monitor = slManager.getProgressMonitor();
        getShell().getDisplay().asyncExec(new Runnable() {

            public void run() {
                monitor.worked(workDone);
            }
        });
    }

    /* (non-Javadoc)
     * @see net.ggtools.grand.ui.graph.GraphDisplayer#finished()
     */
    public void finished() {
        final StatusLineManager slManager = getStatusLineManager();
        final IProgressMonitor monitor = slManager.getProgressMonitor();
        getShell().getDisplay().asyncExec(new Runnable() {

            public void run() {
                monitor.done();
            }
        });
    }

    /* (non-Javadoc)
     * @see net.ggtools.grand.ui.graph.GraphDisplayer#setGraph(net.ggtools.grand.ui.graph.Graph)
     */
    public void setGraph(final IFigure figure) {
        getShell().getDisplay().asyncExec(new Runnable() {

            public void run() {
                canvas.scrollTo(0,0);
                canvas.setContents(figure);
            }
        });
    }

    /**
     * @return Returns the controler.
     */
    public final GraphControler getControler() {
        return controler;
    }
    
    public final Menu getContextMenu() {
        return contextMenu;
    }
    
    /**
     * @return Returns the contextMenuManager.
     */
    public final MenuManager getContextMenuManager() {
        return contextMenuManager;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Grand");
    }
}