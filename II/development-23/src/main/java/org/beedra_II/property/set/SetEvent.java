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

package org.beedra_II.property.set;


import static org.ppeew.smallfries_I.MultiLineToStringUtil.indent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.beedra_II.edit.Edit;
import org.beedra_II.event.AbstractEvent;
import org.ppeew.annotations_I.vcs.CvsInfo;


/**
 * Event that notifies of changes in a {@link SetBeed}.
 *
 * @author Jan Dockx
 *
 * @invar getSource() instanceof SetBeed
 * @invar getAddedElements() != null;
 * @invar getRemovedElements() != null;
 *
 * @deprecated
 */
@Deprecated
@CvsInfo(revision = "$Revision$",
         date     = "$Date$",
         state    = "$State$",
         tag      = "$Name$")
public final class SetEvent<_Element_>
    extends AbstractEvent {

  /**
   * @pre  source != null;
   * @pre  edit != null;
   * @pre  (edit.getState() == DONE) || (edit.getState() == UNDONE);
   *
   * @post getSource() == source;
   * @post getEdit() == edit;
   * @post getEditState() == edit.getState();
   * @post addedElements != null
   *           ? getAddedElements().equals(addedElements)
   *           : getAddedElements().isEmpty();
   * @post removedElements != null
   *           ? getRemovedElements().equals(removedElements)
   *           : getRemovedElements().isEmpty();
   */
  public SetEvent(SetBeed<_Element_> source,
                  Set<? extends _Element_> addedElements,
                  Set<? extends _Element_> removedElements,
                  Edit<?> edit) {
    super(source, edit);
    $addedElements = addedElements != null ?
                       new HashSet<_Element_>(addedElements) :
                       new HashSet<_Element_>();
    $removedElements = removedElements != null ?
                         new HashSet<_Element_>(removedElements) :
                         new HashSet<_Element_>();
  }

  /**
   * @basic
   */
  public final Set<_Element_> getAddedElements() {
    return Collections.unmodifiableSet($addedElements);
  }

  /**
   * @invar $addedElements != null;
   */
  private final Set<_Element_> $addedElements;

  /**
   * @basic
   */
  public final Set<_Element_> getRemovedElements() {
    return Collections.unmodifiableSet($removedElements);
  }

  /**
   * @invar $removedElements != null;
   */
  private final Set<_Element_> $removedElements;

  @Override
  protected String otherToStringInformation() {
    return super.otherToStringInformation() +
           ", added elements: " + getAddedElements() +
           ", removed elements: " + getRemovedElements();
  }

  @Override
  public void toString(StringBuffer sb, int level) {
    super.toString(sb, level);
    sb.append(indent(level + 1) + "added elements: " + getAddedElements() + "\n");
    sb.append(indent(level + 1) + "removed elements: " + getRemovedElements() + "\n");
  }

}

