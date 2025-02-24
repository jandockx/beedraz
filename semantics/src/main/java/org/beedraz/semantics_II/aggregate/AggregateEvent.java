/*<license>
Copyright 2007 - $Date$ by the authors mentioned below.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</license>*/

package org.beedraz.semantics_II.aggregate;


import static org.ppwcode.metainfo_I.License.Type.APACHE_V2;
import static org.ppwcode.util.smallfries_I.MultiLineToStringUtil.indent;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.beedraz.semantics_II.AbstractEvent;
import org.beedraz.semantics_II.Beed;
import org.beedraz.semantics_II.CannotCombineEventsException;
import org.beedraz.semantics_II.CompoundEdit;
import org.beedraz.semantics_II.Edit;
import org.beedraz.semantics_II.Event;
import org.ppwcode.metainfo_I.Copyright;
import org.ppwcode.metainfo_I.License;
import org.ppwcode.metainfo_I.vcs.SvnInfo;


/**
 * <p>Events that report an indirect change, triggered by one or more
 *   events on other sources. Changes of a beed might propagate to other beeds,
 *   e.g., if a component changes, also the aggregate is considered
 *   changed. These changes might come together via the propagation
 *   graph, so that 1 aggregate beed has to report on several changes
 *   upstream.</p>
 * <p>The {@link #getEdit()} of an aggregate event and all its component
 *   events are the same.</p>
 * <p>Aggregate events should not be exposed before they are
 *   {@link #isClosed() closed}.</p>
 *
 * @author Jan Dockx
 *
 * @invar getComponentEvents() != null;
 * @invar Collections.noNull(getComponentEvents());
 * @invar for (Event event : getComponentEvents()) {event.getEdit() == getEdit()}.
 * @invar for (Event event : getComponentEvents()) {event.getEditState() == getEditState()}.
 * @invar getSource() instanceof AggregateBeed;
 */
@Copyright("2007 - $Date$, Beedraz authors")
@License(APACHE_V2)
@SvnInfo(revision = "$Revision$",
         date     = "$Date$")
public class AggregateEvent
    extends AbstractEvent {

  /**
   * @pre source != null;
   * @pre getEdit() != edit;
   * @pre (edit.getState() == DONE) || (edit.getState() == UNDONE)
   * @post getSource() == source;
   * @post getEdit() == getEdit();
   * @post getEditState() == edit.getState();
   * @post ! isClosed();
   */
  public AggregateEvent(AggregateBeed source, Edit<?> edit) {
    super(source, edit);
  }

  /**
   * @basic
   */
  public final boolean isClosed() {
    return $closed;
  }

  /**
   * @post ! isClosed();
   */
  public final void close() {
    $closed = true;
  }

  private boolean $closed;

  /**
   * @return getComponentEventsMap().values();
   */
  public final Set<Event> getComponentEvents() {
    return new HashSet<Event>($componentEvents.values());
  }

  /**
   * @basic
   */
  public final Map<Beed<?>, Event> getComponentEventsMap() {
    return Collections.unmodifiableMap($componentEvents);
  }

  /**
   * @pre event != null;
   * @post getComponentEventsMap().get(event.getSource()) == event;
   * @post 'isClosed() ? false;
   * @throws AggregateEventStateException
   *         isClosed();
   */
  public final void addComponentEvent(Event event) throws AggregateEventStateException {
    assert event != null;
    assert event.getSource() != null;
    if (isClosed()) {
      throw new AggregateEventStateException(this, "cannot add component events when aggregate event is closed");
    }
    $componentEvents.put(event.getSource(), event);
  }

  /**
   * @invar $componentEvents.values() is a set (no duplicates)
   */
  private final Map<Beed<?>, Event> $componentEvents = new HashMap<Beed<?>, Event>();

  /**
   * Events of the same source are combined. Events of sources that exist
   * only in {@code this} or {@code other} are kept as they are.
   *
   * @mudo needs unit test
   */
  @Override
  protected final AggregateEvent createCombinedEvent(Event other, CompoundEdit<?, ?> edit)
      throws CannotCombineEventsException {
    AggregateEvent result = new AggregateEvent((AggregateBeed)getSource(), edit);
    AggregateEvent otherAE = (AggregateEvent)other;
    Map<Beed<?>, Event> otherComponentEvents = otherAE.getComponentEventsMap();
    for (Event e : $componentEvents.values()) {
      Event resultEvent = e;
      Event otherComponentEvent = otherComponentEvents.get(e.getSource());
      if (otherComponentEvent != null) {
        resultEvent = e.combineWith(otherComponentEvent, edit); // CannotCombineEventsException
      }
      try {
        assert ! result.getComponentEventsMap().containsKey(resultEvent.getSource());
        result.addComponentEvent(resultEvent);
      }
      catch (AggregateEventStateException exc) {
        assert false : "AggregateEventStateException should not happen (not closed): " + exc;
      }
    }
    for (Event e : otherComponentEvents.values()) {
      if (! $componentEvents.containsKey(e.getSource())) {
        try {
          result.addComponentEvent(e);
        }
        catch (AggregateEventStateException exc) {
          assert false : "AggregateEventStateException should not happen (not closed): " + exc;
        }
      }
    }
    result.close();
    return result;
  }

  @Override
  protected String otherToStringInformation() {
    return super.otherToStringInformation() +
           ", " + $componentEvents.size() + " component events";
  }

  @Override
  public void toString(StringBuffer sb, int level) {
    super.toString(sb, level);
    sb.append(indent(level + 1) + "component events ("+ $componentEvents.size() + "):\n");
    for (Event event : $componentEvents.values()) {
      event.toString(sb, level + 1);
    }
  }

}

