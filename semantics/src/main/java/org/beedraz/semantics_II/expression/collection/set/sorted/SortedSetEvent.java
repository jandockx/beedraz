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

package org.beedraz.semantics_II.expression.collection.set.sorted;


import static org.ppwcode.metainfo_I.License.Type.APACHE_V2;

import java.util.SortedSet;

import org.beedraz.semantics_II.expression.collection.set.SetEvent;
import org.ppwcode.metainfo_I.Copyright;
import org.ppwcode.metainfo_I.License;
import org.ppwcode.metainfo_I.vcs.SvnInfo;


/**
 * <p>Event that notifies of changes in a {@link SortedSetBeed}.
 *   The changes are represented by a sorted set of {@link #getAddedElements()
 *   added elements} and a soreted set of {@link #getRemovedElements()},
 *   with the same comparator as the {@link #getSource() source sorted set beed}.</p>
 *
 * @note In contrast to supertypes, the collection type is not generic in this
 *       class, since there are no more subtypes defined in the Java Collection
 *       API below {@link SortedSet}. When this should change, this class should
 *       be made generic with respect to the collection type too.</p>
 *
 * @package
 * <p>Actual {@link SortedSetBeed} instances will send instances of
 *   {@link ActualSortedSetEvent} as events, but that is hidden for the user.</p>
 *
 * @author Jan Dockx
 *
 * @invar getSource() instanceof SortedSetBeed
 * @invar getSource().getComparator() == getAddedElements().getComparator();
 * @invar getSource().getComparator() == getRemovedElements().getComparator();
 */
@Copyright("2007 - $Date$, Beedraz authors")
@License(APACHE_V2)
@SvnInfo(revision = "$Revision$",
         date     = "$Date$")
public interface SortedSetEvent<_Element_> extends SetEvent<_Element_> {

  /**
   * @basic
   */
  SortedSet<_Element_> getAddedElements();

  /**
   * @basic
   */
  SortedSet<_Element_> getRemovedElements();

}

