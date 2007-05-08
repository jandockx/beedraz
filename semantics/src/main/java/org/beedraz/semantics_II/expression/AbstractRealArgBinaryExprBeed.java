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

package org.beedraz.semantics_II.expression;


import org.beedraz.semantics_II.Event;
import org.beedraz.semantics_II.expression.number.real.RealBeed;
import org.beedraz.semantics_II.expression.number.real.RealEvent;
import org.ppeew.annotations_I.vcs.CvsInfo;


/**
 * Abstract implementation of binary expression beeds, that represent a value derived
 * from two operands of type {@link RealBeed}.
 */
@CvsInfo(revision = "$Revision$",
         date     = "$Date$",
         state    = "$State$",
         tag      = "$Name$")
public abstract class AbstractRealArgBinaryExprBeed<
                                            _Result_ extends Object,
                                            _ResultEvent_ extends Event,
                                            _LeftOperandBeed_ extends RealBeed<? extends _LeftOperandEvent_>,
                                            _LeftOperandEvent_ extends RealEvent,
                                            _RightOperandBeed_ extends RealBeed<? extends _RightOperandEvent_>,
                                            _RightOperandEvent_ extends RealEvent>

    extends AbstractBinaryExprBeed<_Result_,
                                  _ResultEvent_,
                                  _LeftOperandBeed_,
                                  _LeftOperandEvent_,
                                  _RightOperandBeed_,
                                  _RightOperandEvent_>  {

  @Override
  protected boolean hasEffectiveLeftOperand() {
    return getLeftOprnd().isEffective();
  }

  @Override
  protected boolean hasEffectiveRightOperand() {
    return getRightOprnd().isEffective();
  }

}

