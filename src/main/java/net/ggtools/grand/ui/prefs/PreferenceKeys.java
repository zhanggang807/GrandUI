// $Id$
/*
 * ====================================================================
 * Copyright (c) 2002-2004, Christophe Labouisse All rights reserved.
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
package net.ggtools.grand.ui.prefs;

/**
 * @author Christophe Labouisse
 */
public interface PreferenceKeys {
    /**
     * Field MAX_RECENT_FILES_PREFS_KEY.
     * (value is ""max recent files"")
     */
    String MAX_RECENT_FILES_PREFS_KEY = "max recent files";

    /**
     * Field RECENT_FILES_PREFS_KEY.
     * (value is ""recent files"")
     */
    String RECENT_FILES_PREFS_KEY = "recent files";

    /**
     * Field GRAPH_PREFIX.
     * (value is ""graph."")
     */
    String GRAPH_PREFIX = "graph.";

    /**
     * Field NODE_PREFIX.
     * (value is "GRAPH_PREFIX + "node."")
     */
    String NODE_PREFIX = GRAPH_PREFIX + "node.";

    /**
     * Field GRAPH_BUS_ENABLED_DEFAULT.
     * (value is "GRAPH_PREFIX + "bus.enabled.defaut"")
     */
    String GRAPH_BUS_ENABLED_DEFAULT = GRAPH_PREFIX + "bus.enabled.defaut";

    /**
     * Field GRAPH_BUS_OUT_THRESHOLD.
     * (value is "GRAPH_PREFIX + "bus.out.threshold"")
     */
    String GRAPH_BUS_OUT_THRESHOLD = GRAPH_PREFIX + "bus.out.threshold";

    /**
     * Field GRAPH_BUS_IN_THRESHOLD.
     * (value is "GRAPH_PREFIX + "bus.in.threshold"")
     */
    String GRAPH_BUS_IN_THRESHOLD = GRAPH_PREFIX + "bus.in.threshold";

    /**
     * Field LINK_SUBANT_COLOR.
     * (value is "GRAPH_PREFIX + "link.subant.color"")
     */
    String LINK_SUBANT_COLOR = GRAPH_PREFIX + "link.subant.color";

    /**
     * Field LINK_SUBANT_LINEWIDTH.
     * (value is "GRAPH_PREFIX + "link.subant.linewidth"")
     */
    String LINK_SUBANT_LINEWIDTH = GRAPH_PREFIX + "link.subant.linewidth";

    /**
     * Field LINK_WEAK_COLOR.
     * (value is "GRAPH_PREFIX + "link.weak.color"")
     */
    String LINK_WEAK_COLOR = GRAPH_PREFIX + "link.weak.color";

    /**
     * Field LINK_WEAK_LINEWIDTH.
     * (value is "GRAPH_PREFIX + "link.weak.linewidth"")
     */
    String LINK_WEAK_LINEWIDTH = GRAPH_PREFIX + "link.weak.linewidth";

    /**
     * Field LINK_DEFAULT_COLOR.
     * (value is "GRAPH_PREFIX + "link.default.color"")
     */
    String LINK_DEFAULT_COLOR = GRAPH_PREFIX + "link.default.color";

    /**
     * Field LINK_DEFAULT_LINEWIDTH.
     * (value is "GRAPH_PREFIX + "link.default.linewidth"")
     */
    String LINK_DEFAULT_LINEWIDTH = GRAPH_PREFIX + "link.default.linewidth";

}
