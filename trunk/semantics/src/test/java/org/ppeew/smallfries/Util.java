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

package org.ppeew.smallfries;

import static org.ppeew.annotations_I.License.Type.APACHE_V2;

import org.ppeew.annotations_I.Copyright;
import org.ppeew.annotations_I.License;
import org.ppeew.annotations_I.vcs.SvnInfo;


@Copyright("2007 - $Date: 2007-05-08 14:01:06 +0200 (Tue, 08 May 2007) $, PeopleWare n.v.")
@License(APACHE_V2)
@SvnInfo(revision = "$Revision: 846 $",
         date     = "$Date: 2007-05-08 14:01:06 +0200 (Tue, 08 May 2007) $")
public class Util {

  public static boolean containsNegative(double... values) {
    for (double d : values) {
      if (d < 0.0) {
        return true;
      }
    }
    return false;
  }

  public static boolean containsZero(double... values) {
    for (double d : values) {
      if (d == 0.0) {
        return true;
      }
    }
    return false;
  }

  public static boolean containsInfinity(double... values) {
    for (double d : values) {
      if (Double.isInfinite(d)) {
        return true;
      }
    }
    return false;
  }

  public static boolean containsNaN(double... values) {
    for (double d : values) {
      if (Double.isNaN(d)) {
        return true;
      }
    }
    return false;
  }

}

