/*
 * MIT License
 *
 * Copyright (c) 2021 Hasan Demirtaş
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.kingOf0.smartinventory.smartinventory.handle;

import com.kingOf0.smartinventory.smartinventory.Handle;
import com.kingOf0.smartinventory.smartinventory.event.abs.SmartEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * an implementation for {@link Handle}.
 *
 * @param <T> type of the event.
 */
public final class BasicHandle<T extends SmartEvent> implements Handle<T> {

    /**
     * the class.
     */
    @NotNull
    private final Class<T> clazz;

    /**
     * the consumer.
     */
    @NotNull
    private final Consumer<T> consumer;

    /**
     * the requirements.
     */
    @NotNull
    private final List<Predicate<T>> requirements;

    public BasicHandle(@NotNull Class<T> clazz, @NotNull Consumer<T> consumer, @NotNull List<Predicate<T>> requirements) {
        this.clazz = clazz;
        this.consumer = consumer;
        this.requirements = requirements;
    }

    @Override
    public void accept(@NotNull final T t) {
        if (this.requirements.stream().allMatch(req -> req.test(t))) {
            this.consumer.accept(t);
        }
    }

    @NotNull
    @Override
    public Class<T> type() {
        return this.clazz;
    }
}
