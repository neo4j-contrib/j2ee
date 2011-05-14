/**
 * Copyright (c) 2002-2011 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.servlet.examples.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author tbaum
 * @since 14.05.11 17:58
 */
public class RemoveNode extends GwtEvent<RemoveNodeHandler> {
// ------------------------------ FIELDS ------------------------------

    public static Type<RemoveNodeHandler> TYPE = new Type<RemoveNodeHandler>();
    private final long id;
    private final long showNode;

// --------------------------- CONSTRUCTORS ---------------------------

    public RemoveNode(final long id, final long showNode) {
        this.id = id;
        this.showNode = showNode;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public long getId() {
        return id;
    }

    public long getShowNode() {
        return showNode;
    }

// -------------------------- OTHER METHODS --------------------------

    protected void dispatch(RemoveNodeHandler handler) {
        handler.onRemoveNode(this);
    }

    public Type<RemoveNodeHandler> getAssociatedType() {
        return TYPE;
    }
}
