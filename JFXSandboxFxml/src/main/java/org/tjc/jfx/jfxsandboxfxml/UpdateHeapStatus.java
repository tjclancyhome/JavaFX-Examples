/*
 * The MIT License
 *
 * Copyright 2019 tjclancy.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.tjc.jfx.jfxsandboxfxml;

import javafx.scene.control.Label;
import static org.tjc.common.utils.MemoryMetricsConverters.toMostRelevantString;

/**
 * Updates a label, usually in the Status Bar, that shows heap size, total memory and max memory.
 * Eventually I
 * woule like it to update something like a more pleasant graphic of some sory.
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class UpdateHeapStatus {

    private final Label heapStatusLabel;

    /**
     * <p>
     * Constructor for UpdateHeapStatus.</p>
     *
     * @param heapStatusLabel a {@link javafx.scene.control.Label} object.
     */
    public UpdateHeapStatus(Label heapStatusLabel) {
        this.heapStatusLabel = heapStatusLabel;
        this.heapStatusLabel.setText("");
    }

    /**
     * Updates a label, which is set in the constructor, with current memory state.
     */
    public void update() {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();

        String totalMemoryStr = toMostRelevantString((double) totalMemory);
        String maxMemoryStr = toMostRelevantString((double) maxMemory);
        String freeMemoryStr = toMostRelevantString((double) freeMemory);

        String heapStatStr = String.format("Heap size: %s, of Total: %s, with max: %s",
                freeMemoryStr,
                totalMemoryStr, maxMemoryStr);

        heapStatusLabel.setText(heapStatStr);
    }
}
