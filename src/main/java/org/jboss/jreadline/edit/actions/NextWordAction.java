/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.jreadline.edit.actions;

/**
 * @author Ståle W. Pedersen <stale.pedersen@jboss.org>
 */
public class NextWordAction extends EditAction {

    private boolean removeTrailingSpaces = true;

    public NextWordAction(int start, Action action) {
        super(start, action);
        if(getAction() == Action.CHANGE)
            removeTrailingSpaces = false;
    }

    @Override
    public void doAction(String buffer) {
        int cursor = getStart();

        //if cursor stand on a delimiter, move till its no more delimiters
        if(cursor < buffer.length() && (isDelimiter(buffer.charAt(cursor))))
            while(cursor < buffer.length() && (isDelimiter(buffer.charAt(cursor))))
                cursor++;
            //if we stand on a non-delimiter
        else {
            while(cursor < buffer.length() && !isDelimiter(buffer.charAt(cursor)))
                cursor++;

            //if we end up on a space we move past that too
            if(removeTrailingSpaces)
                if(cursor < buffer.length() && isSpace(buffer.charAt(cursor)))
                    while(cursor < buffer.length() && isSpace(buffer.charAt(cursor)))
                        cursor++;
        }

        setEnd(cursor);
    }
}
