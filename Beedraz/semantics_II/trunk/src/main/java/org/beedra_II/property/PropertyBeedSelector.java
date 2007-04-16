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

package org.beedra_II.property;


import org.beedra_II.BeedMapping;
import org.beedra_II.aggregate.AggregateBeed;
import org.ppeew.annotations_I.vcs.CvsInfo;


/**
 * <p>Selects a {@link PropertyBeed} from a beed owner.</p>
 *
 * @author Jan Dockx
 *
 * @deprecated use paths instead
 */
@CvsInfo(revision = "$Revision$",
         date     = "$Date$",
         state    = "$State$",
         tag      = "$Name$")
@Deprecated
public interface PropertyBeedSelector<_Owner_ extends AggregateBeed,
                                      _PropertyBeed_ extends PropertyBeed<?>>
    extends BeedMapping<_Owner_, _PropertyBeed_> {

  /**
   * @basic
   * @pre owner != null;
   * @result result.getOwner() == owner;
   */
  _PropertyBeed_ map(_Owner_ owner);

}

