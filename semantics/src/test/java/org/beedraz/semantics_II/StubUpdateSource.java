/*<license>
  Copyright 2007, PeopleWare n.v.
  NO RIGHTS ARE GRANTED FOR THE USE OF THIS SOFTWARE, EXCEPT, IN WRITING,
  TO SELECTED PARTIES.
</license>*/

package org.beedraz.semantics_II;


import static org.ppwcode.metainfo_I.License.Type.APACHE_V2;

import java.util.Collections;
import java.util.Set;

import org.beedraz.semantics_II.Beed;
import org.ppwcode.metainfo_I.Copyright;
import org.ppwcode.metainfo_I.License;
import org.ppwcode.metainfo_I.vcs.SvnInfo;


@Copyright("2007 - $Date$, Beedraz authors")
@License(APACHE_V2)
@SvnInfo(revision = "$Revision$",
         date     = "$Date$")
public final class StubUpdateSource extends AbstractStubUpdateSource {

  public final static int MRUSD = 0;

  public StubUpdateSource() {
    $mrusd = MRUSD;
  }

  public StubUpdateSource(int mrosd) {
    $mrusd = mrosd;
  }

  public int $mrusd;

  public int getMaximumRootUpdateSourceDistance() {
    return $mrusd;
  }

  public final Set<? extends Beed<?>> getUpdateSources() {
    return Collections.emptySet();
  }

  public final Set<? extends Beed<?>> getUpdateSourcesTransitiveClosure() {
    return Collections.emptySet();
  }

}
