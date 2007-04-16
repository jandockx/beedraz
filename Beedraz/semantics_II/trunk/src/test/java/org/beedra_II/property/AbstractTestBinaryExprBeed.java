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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.beedra_II.Beed;
import org.beedra_II.Event;
import org.beedra_II.StubListener;
import org.beedra_II.aggregate.AggregateBeed;
import org.beedra_II.aggregate.StubAggregateBeed;
import org.beedra_II.edit.EditStateException;
import org.beedra_II.edit.IllegalEditException;
import org.beedra_II.path.ConstantPath;
import org.beedra_II.path.NullPath;
import org.beedra_II.path.Path;
import org.beedra_II.property.number.real.RealBeed;
import org.beedra_II.property.number.real.RealEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public abstract class AbstractTestBinaryExprBeed<_Result_ extends Object,
                                                 _ResultEvent_ extends Event,
                                                 _Argument_ extends Object,
                                                 _LeftArgumentBeed_ extends Beed<?>,
                                                 _RightArgumentBeed_ extends Beed<?>,
                                                 _UEB_ extends AbstractBinaryExprBeed<_Result_, _ResultEvent_, _LeftArgumentBeed_, ? extends Event, _RightArgumentBeed_, ? extends Event>,
                                                 _LeftEAB_ extends _LeftArgumentBeed_,
                                                 _RightEAB_ extends _RightArgumentBeed_> {


  protected abstract _UEB_ createSubject(AggregateBeed owner);

  protected abstract _LeftEAB_ createEditableLeftArgumentBeed(AggregateBeed owner);

  protected abstract _RightEAB_ createEditableRightArgumentBeed(AggregateBeed owner);

  protected abstract StubListener<_ResultEvent_> createStubListener();

  protected abstract void initGoals();

  protected abstract void setLeftArgumentPath(Path<? extends _LeftArgumentBeed_> leftArgumentPath);

  protected abstract void setRightArgumentPath(Path<? extends _RightArgumentBeed_> rightArgumentPath);

  protected abstract _LeftArgumentBeed_ getLeftArgument();

  protected abstract _RightArgumentBeed_ getRightArgument();

  protected abstract void changeLeftArgument(_LeftEAB_ editableArgumentBeed, _Argument_ newValue);

  protected abstract void changeRightArgument(_RightEAB_ editableArgumentBeed, _Argument_ newValue);

  protected abstract _Result_ expectedValue(_Argument_ leftArgumentValue, _Argument_ rightArgumentValue);

  protected abstract _Result_ valueFromSubject(_UEB_ argumentBeed);

  protected abstract _Argument_ valueFromLeft(_LeftArgumentBeed_ argumentBeed);

  protected abstract _Argument_ valueFromRight(_RightArgumentBeed_ argumentBeed);

  protected abstract _Result_ oldValueFrom(_ResultEvent_ argumentBeed);

  protected abstract _Result_ newValueFrom(_ResultEvent_ argumentBeed);

  @Before
  public void setUp() throws Exception {
    initGoals();
    $aggregateBeed = new StubAggregateBeed();
    $leftArgumentDoubleBeed = createEditableLeftArgumentBeed($aggregateBeed);
    $leftArgumentDoubleBeedPath = new ConstantPath<_LeftEAB_>($leftArgumentDoubleBeed);
    $leftArgumentDoubleBeed2 = createEditableLeftArgumentBeed($aggregateBeed);
    $leftArgumentDoubleBeedPath2 = new ConstantPath<_LeftEAB_>($leftArgumentDoubleBeed2);
    $rightArgumentDoubleBeed = createEditableRightArgumentBeed($aggregateBeed);
    $rightArgumentDoubleBeedPath = new ConstantPath<_RightEAB_>($rightArgumentDoubleBeed);
    $rightArgumentDoubleBeed2 = createEditableRightArgumentBeed($aggregateBeed);
    $rightArgumentDoubleBeedPath2 = new ConstantPath<_RightEAB_>($rightArgumentDoubleBeed2);
    $subject = createSubject($aggregateBeed);
    $listener = createStubListener();
  }

  @After
  public void tearDown() throws Exception {
    $leftArgumentDoubleBeed = null;
    $leftArgumentDoubleBeedPath = null;
    $leftArgumentDoubleBeed2 = null;
    $leftArgumentDoubleBeedPath2 = null;
    $rightArgumentDoubleBeed = null;
    $rightArgumentDoubleBeedPath = null;
    $rightArgumentDoubleBeed2 = null;
    $rightArgumentDoubleBeedPath2 = null;
    $subject = null;
    $aggregateBeed = null;
    $listener = null;
    $leftGoal1 = null;
    $leftGoal2 = null;
    $leftGoalMIN = null;
    $leftGoalMAX = null;
    $rightGoal1 = null;
    $rightGoal2 = null;
    $rightGoalMIN = null;
    $rightGoalMAX = null;
  }

  protected _UEB_ $subject;
  protected AggregateBeed $aggregateBeed;
  private _LeftEAB_ $leftArgumentDoubleBeed;
  private Path<_LeftEAB_> $leftArgumentDoubleBeedPath;
  private _LeftEAB_ $leftArgumentDoubleBeed2;
  private Path<_LeftEAB_> $leftArgumentDoubleBeedPath2;
  private _RightEAB_ $rightArgumentDoubleBeed;
  private Path<_RightEAB_> $rightArgumentDoubleBeedPath;
  private _RightEAB_ $rightArgumentDoubleBeed2;
  private Path<_RightEAB_> $rightArgumentDoubleBeedPath2;
  protected _Argument_ $leftGoal1;
  protected _Argument_ $leftGoal2;
  protected _Argument_ $leftGoalMIN;
  protected _Argument_ $leftGoalMAX;
  protected _Argument_ $rightGoal1;
  protected _Argument_ $rightGoal2;
  protected _Argument_ $rightGoalMIN;
  protected _Argument_ $rightGoalMAX;
  StubListener<_ResultEvent_> $listener;

  @Test
  public void testSetArgument_1L() {
    $subject.addListener($listener);
    setLeftArgumentPath(new NullPath<_LeftArgumentBeed_>());
    validateSubjectFromArgument(null, null);
    validateEvent(null, null, null, null);
  }

  @Test
  public void testSetArgument_1R() {
    $subject.addListener($listener);
    setRightArgumentPath(new NullPath<_RightArgumentBeed_>());
    validateSubjectFromArgument(null, null);
    validateEvent(null, null, null, null);
  }

  @Test
  public void testSetArgument_1LR() {
    $subject.addListener($listener);
    setLeftArgumentPath(new NullPath<_LeftArgumentBeed_>());
    setRightArgumentPath(new NullPath<_RightArgumentBeed_>());
    validateSubjectFromArgument(null, null);
    validateEvent(null, null, null, null);
  }

  @Test
  public void testSetArgument_2L() {
    $subject.addListener($listener);
    setLeftArgumentPath($leftArgumentDoubleBeedPath);
    validateSubjectFromArgument($leftArgumentDoubleBeed, null);
    validateEvent(null, null, null, null);
  }

  @Test
  public void testSetArgument_2R() {
    $subject.addListener($listener);
    setRightArgumentPath($rightArgumentDoubleBeedPath);
    validateSubjectFromArgument(null, $rightArgumentDoubleBeed);
    validateEvent(null, null, null, null);
  }

  @Test
  public void testSetArgument_2LR() {
    $subject.addListener($listener);
    setLeftArgumentPath($leftArgumentDoubleBeedPath);
    setRightArgumentPath($rightArgumentDoubleBeedPath);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed);
    validateEvent(null, null, null, null);
  }

  @Test
  public void testSetArgument_3L() {
    changeLeftArgument($leftArgumentDoubleBeed, $leftGoal1);
    changeRightArgument($rightArgumentDoubleBeed, $rightGoal1);
    $subject.addListener($listener);
    setLeftArgumentPath($leftArgumentDoubleBeedPath);
    validateSubjectFromArgument($leftArgumentDoubleBeed, null);
    validateEvent(null, $leftGoal1, null, null);
    setRightArgumentPath($rightArgumentDoubleBeedPath);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed);
    validateEvent($leftGoal1, $leftGoal1, null, $rightGoal1);
    setLeftArgumentPath($leftArgumentDoubleBeedPath);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed);
    validateEvent(null, null, $rightGoal1, $rightGoal1);
    setLeftArgumentPath(new NullPath<_LeftArgumentBeed_>());
    validateSubjectFromArgument(null, $rightArgumentDoubleBeed);
    validateEvent($leftGoal1, null, $rightGoal1, $rightGoal1);
    setLeftArgumentPath($leftArgumentDoubleBeedPath);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed);
    validateEvent(null, $leftGoal1, $rightGoal1, $rightGoal1);
    changeLeftArgument($leftArgumentDoubleBeed2, $leftGoal2);
    setLeftArgumentPath($leftArgumentDoubleBeedPath2);
    validateSubjectFromArgument($leftArgumentDoubleBeed2, $rightArgumentDoubleBeed);
    validateEvent($leftGoal1, $leftGoal2, $rightGoal1, $rightGoal1);
    changeLeftArgument($leftArgumentDoubleBeed, $leftGoal2);
    setLeftArgumentPath($leftArgumentDoubleBeedPath);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed);
    validateEvent(null, null, $rightGoal1, $rightGoal1);
  }

  @Test
  public void testSetArgument_3R() {
    changeLeftArgument($leftArgumentDoubleBeed, $leftGoal1);
    changeRightArgument($rightArgumentDoubleBeed, $rightGoal1);
    $subject.addListener($listener);
    setRightArgumentPath($rightArgumentDoubleBeedPath);
    validateSubjectFromArgument(null, $rightArgumentDoubleBeed);
    validateEvent(null, null, null, $rightGoal1);
    setLeftArgumentPath($leftArgumentDoubleBeedPath);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed);
    validateEvent(null, $leftGoal1, $rightGoal1, $rightGoal1);
    setRightArgumentPath($rightArgumentDoubleBeedPath);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed);
    validateEvent($leftGoal1, $leftGoal1, null, null);
    setRightArgumentPath(new NullPath<_RightArgumentBeed_>());
    validateSubjectFromArgument($leftArgumentDoubleBeed, null);
    validateEvent($leftGoal1, $leftGoal1, $rightGoal1, null);
    setRightArgumentPath($rightArgumentDoubleBeedPath);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed);
    validateEvent($leftGoal1, $leftGoal1, null, $rightGoal1);
    changeRightArgument($rightArgumentDoubleBeed2, $rightGoal2);
    setRightArgumentPath($rightArgumentDoubleBeedPath2);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed2);
    validateEvent($leftGoal1, $leftGoal1, $rightGoal1, $rightGoal2);
    changeRightArgument($rightArgumentDoubleBeed, $rightGoal2);
    setRightArgumentPath($rightArgumentDoubleBeedPath);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed);
    validateEvent($leftGoal1, $leftGoal1, null, null);
  }


  @Test
  public void testDynamics() {
    $subject.addListener($listener);
    assertTrue($subject.isListener($listener));
    changeLeftArgument($leftArgumentDoubleBeed, $leftGoal1);
    setLeftArgumentPath($leftArgumentDoubleBeedPath);
    validateSubjectFromArgument($leftArgumentDoubleBeed, null);
    validateEvent(null, null, null, null);
    changeRightArgument($rightArgumentDoubleBeed, $rightGoal1);
    setRightArgumentPath($rightArgumentDoubleBeedPath);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed);
    validateEvent($leftGoal1, $leftGoal1, null, $rightGoal1);
    changeLeftArgument($leftArgumentDoubleBeed, $leftGoal2);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed);
    validateEvent($leftGoal1, $leftGoal2, $rightGoal1, $rightGoal1);
    changeLeftArgument($leftArgumentDoubleBeed, $leftGoal2);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed);
    validateEvent(null, null, $rightGoal1, $rightGoal1);
    changeLeftArgument($leftArgumentDoubleBeed, null);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed);
    validateEvent($leftGoal2, null, $rightGoal1, $rightGoal1);
    changeLeftArgument($leftArgumentDoubleBeed, null);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed);
    validateEvent(null, null, $rightGoal1, $rightGoal1);
    changeLeftArgument($leftArgumentDoubleBeed, $leftGoalMIN);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed);
    validateEvent(null, $leftGoalMIN, $rightGoal1, $rightGoal1);
    changeLeftArgument($leftArgumentDoubleBeed, $leftGoalMAX);
    validateSubjectFromArgument($leftArgumentDoubleBeed, $rightArgumentDoubleBeed);
    validateEvent($leftGoalMIN, $leftGoalMAX, $rightGoal1, $rightGoal1);
    // MUDO more tests changing right argument
  }

  private void validateEvent(_Argument_ oldLeftV, _Argument_ newLeftV, _Argument_ oldRightV, _Argument_ newRightV) {
    _Result_ expectedOldValue = ((oldLeftV == null) || (oldRightV == null)) ? null : expectedValue(oldLeftV, oldRightV);
    _Result_ expectedNewValue = ((newLeftV == null) || (newRightV == null)) ? null : expectedValue(newLeftV, newRightV);
    if (! $subject.equalValue(expectedOldValue, expectedNewValue)) {
      assertNotNull($listener.$event);
      if ((oldLeftV == null) || (oldRightV == null)) {
        assertNull(oldValueFrom($listener.$event));
      }
      else {
        assertNotNull(oldValueFrom($listener.$event));
        assertTrue($subject.equalValue(expectedValue(oldLeftV, oldRightV), oldValueFrom($listener.$event)));
      }
      if ((newLeftV == null) || (newRightV == null)) {
        assertNull(newValueFrom($listener.$event));
      }
      else {
        assertNotNull(newValueFrom($listener.$event));
        assertTrue($subject.equalValue(expectedValue(newLeftV, newRightV), newValueFrom($listener.$event)));
      }
    }
    else {
      assertNull($listener.$event);
    }
    $listener.$event = null;
  }

  private void validateSubjectFromArgument(_LeftEAB_ leftArgument, _RightEAB_ rightArgument) {
//    System.out.println("argument: " + leftArgument + "  ##  $subject: "+ $subject);
    System.out.println("leftArgument: " + leftArgument + ", rightArgument: " + rightArgument + ", result: " + $subject.get());
    assertEquals(leftArgument, getLeftArgument());
    assertEquals(rightArgument, getRightArgument());
    _Argument_ leftArgumentValue = null;
    _Argument_ rightArgumentValue = null;
    if (leftArgument != null) {
      assertNotNull(getLeftArgument());
      leftArgumentValue = valueFromLeft(leftArgument);
      if (leftArgumentValue == null) {
        assertNull($subject.get());
      }
    }
    else {
      assertNull(getLeftArgument());
    }
    if (rightArgument != null) {
      assertNotNull(getRightArgument());
      rightArgumentValue = valueFromRight(rightArgument);
      if (rightArgumentValue == null) {
        assertNull($subject.get());
      }
    }
    else {
      assertNull(getRightArgument());
    }
    if ((leftArgument != null) && (rightArgument != null) && (leftArgumentValue != null) && (rightArgumentValue != null)) {
      assertNotNull($subject.get());
      assertTrue($subject.equalValue(expectedValue(leftArgumentValue, rightArgumentValue), valueFromSubject($subject)));
    }
  }

  @Test
  public void testToString() {
    String result = $subject.toString();
    assertNotNull(result);
  }

  @Test
  public void testToString_StringBuffer_int_1() {
    StringBuffer stub = new StringBuffer();
    $subject.toString(stub, 1);
  }

  @Test
  public void testToString_StringBuffer_int_2L() throws EditStateException, IllegalEditException {
    StringBuffer stub = new StringBuffer();
    $subject.toString(stub, 1);
    setLeftArgumentPath($leftArgumentDoubleBeedPath);
    $subject.toString(stub, 1);
    changeLeftArgument($leftArgumentDoubleBeed, $leftGoal1);
    $subject.toString(stub, 1);
    changeLeftArgument($leftArgumentDoubleBeed, $leftGoal2);
    $subject.toString(stub, 1);

    setRightArgumentPath($rightArgumentDoubleBeedPath);
    $subject.toString(stub, 1);

    setLeftArgumentPath($leftArgumentDoubleBeedPath);
    $subject.toString(stub, 1);
    changeLeftArgument($leftArgumentDoubleBeed, $leftGoal1);
    $subject.toString(stub, 1);
    changeLeftArgument($leftArgumentDoubleBeed, $leftGoal2);
    $subject.toString(stub, 1);

    changeRightArgument($rightArgumentDoubleBeed, $leftGoal1);
    $subject.toString(stub, 1);

    setLeftArgumentPath($leftArgumentDoubleBeedPath);
    $subject.toString(stub, 1);
    changeLeftArgument($leftArgumentDoubleBeed, $leftGoal1);
    $subject.toString(stub, 1);
    changeLeftArgument($leftArgumentDoubleBeed, $leftGoal2);
    $subject.toString(stub, 1);

    changeRightArgument($rightArgumentDoubleBeed, $leftGoal2);
    $subject.toString(stub, 1);

    setLeftArgumentPath($leftArgumentDoubleBeedPath);
    $subject.toString(stub, 1);
    changeLeftArgument($leftArgumentDoubleBeed, $leftGoal1);
    $subject.toString(stub, 1);
    changeLeftArgument($leftArgumentDoubleBeed, $leftGoal2);
    $subject.toString(stub, 1);
  }

}
