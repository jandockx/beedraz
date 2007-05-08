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

package org.beedraz.semantics_II.expression.bool;


import static org.ppeew.annotations_I.License.Type.APACHE_V2;

import org.ppeew.annotations_I.Copyright;
import org.ppeew.annotations_I.License;
import org.ppeew.annotations_I.vcs.SvnInfo;


/**
 * A beed that returns the logical negation of another {@link BooleanBeed}.
 *
 * @invar getOperand() != null && getOperand().isEffective()
 *          ? get() == !getOperand().get()
 *          : !isEffective();
 */
@Copyright("2007 - $Date$, Beedraz authors")
@License(APACHE_V2)
@SvnInfo(revision = "$Revision$",
         date     = "$Date$")
public class BooleanNotBeed extends AbstractBooleanArgBooleanUnaryExpressionBeed {

  /**
   * The operator of this binary expression.
   */
  @Override
  public String getOperatorString() {
    return "not_null";
  }

  @Override
  protected boolean calculateValue(boolean operand) {
    return !operand;
  }
}

