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

package org.beedraz.semantics_II.path;


import static org.ppwcode.metainfo_I.License.Type.APACHE_V2;

import java.util.Collections;
import java.util.Set;

import org.beedraz.semantics_II.AbstractBeed;
import org.beedraz.semantics_II.Beed;
import org.ppwcode.metainfo_I.Copyright;
import org.ppwcode.metainfo_I.License;
import org.ppwcode.metainfo_I.vcs.SvnInfo;


/**
 * <p>{@link Path} that always returns the same
 *   beed. Use this if there are no reference beeds in
 *   the path to select the beed.</p>
 * <p>Subtypes should overwrite {@link #get()}. Other methods
 *   are implemented here for a never changing beed path result.</p>
 *
 * @author Jan Dockx
 *
 * @invar get() == 'get();
 * @invar getMaximumRootUpdateSourceDistance() == 0;
 * @invar getUpdateSources().isEmpty();
 */
@Copyright("2007 - $Date$, Beedraz authors")
@License(APACHE_V2)
@SvnInfo(revision = "$Revision$",
         date     = "$Date$")
public abstract class AbstractIndependentPath<_SelectedBeed_ extends Beed<?>>
    extends AbstractBeed<PathEvent<_SelectedBeed_>>
    implements Path<_SelectedBeed_> {

  @Override
  protected String otherToStringInformation() {
    _SelectedBeed_ selected = get();
    return selected == null ? "null" : selected.toString();
  }

  @Override
  public final void toString(StringBuffer sb, int i) {
    sb.append(otherToStringInformation());
  }

  public final int getMaximumRootUpdateSourceDistance() {
    return 0;
  }

  public final Set<? extends Beed<?>> getUpdateSources() {
    return Collections.emptySet();
  }

  public final Set<? extends Beed<?>> getUpdateSourcesTransitiveClosure() {
    return Collections.emptySet();
  }

}

