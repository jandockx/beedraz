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

package org.beedraz.semantics_II.expression.number.real.double64;


import static org.ppeew.annotations_I.License.Type.APACHE_V2;

import org.beedraz.semantics_II.aggregate.AggregateBeed;
import org.ppeew.annotations_I.Copyright;
import org.ppeew.annotations_I.License;
import org.ppeew.annotations_I.vcs.SvnInfo;


/**
 * A beed that is the negative of an {@link #getOperand() operand} {@link DoubleBeed}.
 *
 * @mudo overflow: -MIN_VALUE == MIN_VALUE
 */
@Copyright("2007 - $Date$, Beedraz authors")
@License(APACHE_V2)
@SvnInfo(revision = "$Revision$",
         date     = "$Date$")
public class DoubleNegativeBeed
    extends AbstractRealArgDoubleUnaryExpressionBeed {

  /**
   * @post  getDouble() == null;
   * @post  getOperand() == null;
   */
  public DoubleNegativeBeed() {
    this(null);
  }

  /**
   * @post  getDouble() == null;
   * @post  getOperand() == null;
   * @post  owner != null ? owner.registerAggregateElement(this);
   */
  public DoubleNegativeBeed(AggregateBeed owner) {
    super(owner);
  }

  /**
   * @pre operandValue != null;
   */
  @Override
  protected final double calculateValue(double operandValue) {
    return -operandValue;
  }

  @Override
  public final String getOperatorString() {
    return "-";
  }

}

