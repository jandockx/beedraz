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
 * Listen to changes in {@code Beed Beeds}.
 * Changes are expressed in {@code _Event_} instances.
 *
 * There is only one generic listener type in this framework.
 * By generic instantion, each event type creates its own
 * listener type.
 *
 * @author Jan Dockx
 */
@CvsInfo(revision = "$Revision$",
         date     = "$Date$",
         state    = "$State$",
         tag      = "$Name$")
public interface BeedListener<_Event_ extends BeedEvent> {

  /**
   * @pre event != null;
   */
  void beedChanged(_Event_ event);

}

