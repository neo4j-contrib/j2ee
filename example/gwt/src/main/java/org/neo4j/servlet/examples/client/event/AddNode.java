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
 * @since 14.05.11 16:55
 */
public class AddNode extends GwtEvent<AddNodeHandler> {
// ------------------------------ FIELDS ------------------------------

    public static Type<AddNodeHandler> TYPE = new Type<AddNodeHandler>();
    private final Long from;
    private final Long to;
    private final String relation;

// --------------------------- CONSTRUCTORS ---------------------------

    public AddNode(final String from, final String relation, final String toValue) {
        this.from = Long.parseLong(from);
        this.to = toValue.isEmpty() ? null : Long.parseLong(toValue);
        this.relation = relation;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public Long getFrom() {
        return from;
    }

    public String getRelation() {
        return relation;
    }

    public Long getTo() {
        return to;
    }

// -------------------------- OTHER METHODS --------------------------

    protected void dispatch(AddNodeHandler handler) {
        handler.onAddNode(this);
    }

    public Type<AddNodeHandler> getAssociatedType() {
        return TYPE;
    }
}
