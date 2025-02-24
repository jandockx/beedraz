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

package org.beedraz.semantics_II.expression.collection.map;


import java.util.Map;

import org.beedraz.semantics_II.Event;
import org.ppwcode.metainfo_I.vcs.CvsInfo;


/**
 * <p>Event that notifies of changes in a {@link MapBeed}.
 *   The changes are represented by a map of {@link #getAddedElements()
 *   added elements} and a map of {@link #getRemovedElements()}.</p>
 *
 * @author  Nele Smeets
 * @author  PeopleWare n.v.
 *
 * @invar   getAddedElements() != null;
 * @invar   !getAddedElements().keySet().contains(null);
 * @invar   getRemovedElements() != null;
 * @invar   !getRemovedElements().keySet().contains(null);
 *
 * @mudo move to collection package; map is not a set
 */
@CvsInfo(revision = "$Revision$",
         date     = "$Date$",
         state    = "$State: Exp $",
         tag      = "$Name:  $")
public interface MapEvent<_Key_, _Value_> extends Event {

  /**
   * @basic
   */
  Map<_Key_, _Value_> getAddedElements();

  /**
   * @basic
   */
  Map<_Key_, _Value_> getRemovedElements();

}

