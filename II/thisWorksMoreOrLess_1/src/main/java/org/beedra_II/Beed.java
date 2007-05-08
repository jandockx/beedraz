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


import org.toryt.util_I.annotations.vcs.CvsInfo;


/**
 * <p>Beeds send {@link BeedEvent BeedChangeEvents} of
 *   type {@code _Event_ extends BeedEvent}
 *   to {@link BeedListener registered listeners}
 *   when they change.</p>
 * <p>Some beeds are <em>read-only</em>,
 *   others are {@link WriteBeed <em>read-write</em>}.</p>
 *
 * @author Jan Dockx
 *
 * @mudo to be added: validation, civilization, propagation
 */
@CvsInfo(revision = "$Revision$",
         date     = "$Date$",
         state    = "$State$",
         tag      = "$Name$")
public interface Beed<_Event_ extends BeedEvent<? extends Beed<_Event_>>> {

  /**
   * @basic
   * @init (forall BeedListener<_Event_> bcl : ! isChangeListener(bcl));
   */
  boolean isChangeListener(BeedListener<_Event_> listener);

  /**
   * @pre listener != null;
   * @post isChangeListener(listener);
   */
  void addChangeListener(BeedListener<_Event_> listener);

  /**
   * @post ! isChangeListener(listener);
   */
  void removeChangeListener(BeedListener<_Event_> listener);

}

