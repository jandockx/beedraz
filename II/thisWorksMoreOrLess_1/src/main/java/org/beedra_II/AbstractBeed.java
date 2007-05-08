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

package org.beedra_II;


import java.util.HashSet;
import java.util.Set;

import org.toryt.util_I.annotations.vcs.CvsInfo;


/**
 * Support for implementations of {@link Beed}.
 *
 * @author Jan Dockx
 */
@CvsInfo(revision = "$Revision$",
         date     = "$Date$",
         state    = "$State$",
         tag      = "$Name$")
public abstract class AbstractBeed<_Event_ extends BeedEvent<? extends Beed<_Event_>>>
    implements Beed<_Event_> {

  public final boolean isChangeListener(BeedListener<_Event_> listener) {
    return $changeListeners.contains(listener);
  }

  public final void addChangeListener(BeedListener<_Event_> listener) {
    assert listener != null;
    $changeListeners.add(listener);
  }

  public final void removeChangeListener(BeedListener<_Event_> listener) {
    $changeListeners.remove(listener);
  }

  protected final void fireChangeEvent(_Event_ event) {
    for (BeedListener<_Event_> listener : $changeListeners) {
      listener.beedChanged(event);
      // same event, because is immutable
      // MUDO needs Dijkstra implementation !!
    }
  }

  /**
   * @invar $changeListeners != null;
   * @invar Collections.noNull($changeListeners);
   */
  private final Set<BeedListener<_Event_>> $changeListeners =
      new HashSet<BeedListener<_Event_>>();

}
