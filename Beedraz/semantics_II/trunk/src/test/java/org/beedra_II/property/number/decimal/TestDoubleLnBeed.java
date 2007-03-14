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

package org.beedra_II.property.number.decimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.beedra_II.aggregate.AggregateBeed;
import org.junit.Test;




public class TestDoubleLnBeed
    extends AbstractTestDoubleUnaryExpressionBeed<DoubleLnBeed> {

  @Test
  public void testConstructor() {
    DoubleLnBeed inb = new DoubleLnBeed($aggregateBeed);
    assertEquals($aggregateBeed, inb.getOwner());
    assertNull(inb.getArgument());
    assertNull(inb.getDouble());
    assertNull(inb.getDouble());
  }

  @Override
  protected Double expectedValue(Double argumentValue) {
    return Math.log(argumentValue);
  }

  @Override
  protected DoubleLnBeed createSubject(AggregateBeed owner) {
    return new DoubleLnBeed(owner);
  }

  @Override
  protected Double valueFromSubject(DoubleLnBeed argumentBeed) {
    return argumentBeed.getDouble();
  }

}
