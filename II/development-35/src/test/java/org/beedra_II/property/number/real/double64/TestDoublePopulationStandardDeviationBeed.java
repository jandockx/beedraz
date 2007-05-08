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

package org.beedra_II.property.number.real.double64;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.beedra_II.aggregate.AggregateBeed;
import org.beedra_II.aggregate.PropagatedEvent;
import org.beedra_II.bean.StubBeanBeed;
import org.beedra_II.edit.EditStateException;
import org.beedra_II.edit.IllegalEditException;
import org.beedra_II.event.StubListener;
import org.beedra_II.property.collection.set.EditableSetBeed;
import org.beedra_II.property.collection.set.SetBeed;
import org.beedra_II.property.collection.set.SetEdit;
import org.beedra_II.property.number.real.RealBeed;
import org.beedra_II.property.number.real.RealEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ppeew.smallfries_I.MathUtil;


public class TestDoublePopulationStandardDeviationBeed {

  public class MyDoublePopulationStandardDeviationBeed extends DoublePopulationStandardDeviationBeed {
    public MyDoublePopulationStandardDeviationBeed(AggregateBeed owner) {
      super(owner);
    }

    /**
     * updateDependents is made public for testing reasons
     */
    public void publicUpdateDependents(ActualDoubleEvent event) {
      updateDependents(event);
    }

    public final void publicRecalculate() {
      assert getSource() != null;
      recalculate(getSource());
    }

  }

  @Before
  public void setUp() throws Exception {
    // NOP
  }

  @After
  public void tearDown() throws Exception {
    // NOP
  }

  private AggregateBeed $owner = new StubBeanBeed();
  private MyDoublePopulationStandardDeviationBeed $doublePopulationStandardDeviationBeed = new MyDoublePopulationStandardDeviationBeed($owner);
  private ActualDoubleEvent $event1 = new ActualDoubleEvent($doublePopulationStandardDeviationBeed, new Double(0), new Double(1), null);
      // @mudo Laatste argument mag niet null zijn??
  private StubListener<PropagatedEvent> $listener1 = new StubListener<PropagatedEvent>();
  private StubListener<PropagatedEvent> $listener2 = new StubListener<PropagatedEvent>();
  private StubListener<RealEvent> $listener3 = new StubListener<RealEvent>();

  @Test
  public void constructor() {
    assertEquals($doublePopulationStandardDeviationBeed.getOwner(), $owner);
    assertEquals($doublePopulationStandardDeviationBeed.getSource(), null);
    assertEquals($doublePopulationStandardDeviationBeed.getDouble(), null);
    // the abstract property beed should be registered with the owner:
    // add listeners to the property beed
    $owner.addListener($listener1);
    $owner.addListener($listener2);
    assertNull($listener1.$event);
    assertNull($listener2.$event);
    // fire a change on the registered beed
    $doublePopulationStandardDeviationBeed.publicUpdateDependents($event1);
    // listeners of the aggregate beed should be notified
    assertNotNull($listener1.$event);
    assertNotNull($listener2.$event);
    assertEquals($event1, $listener1.$event.getCause());
    assertEquals($event1, $listener1.$event.getCause());
  }

  /**
   * Source is null.
   */
  @Test
  public void setSource1() throws EditStateException, IllegalEditException {
    // register listeners to the DoublePopulationStandardDeviationBeed
    $doublePopulationStandardDeviationBeed.addListener($listener3);
    assertNull($listener3.$event);
    // check setSource
    SetBeed<RealBeed<?>, ?> source = null;
    $doublePopulationStandardDeviationBeed.setSource(source);
    assertEquals($doublePopulationStandardDeviationBeed.getSource(), source);
    assertEquals($doublePopulationStandardDeviationBeed.getDouble(), null);
    // value has not changed, so the listeners are not notified
    assertNull($listener3.$event);
  }

  /**
   * Source is effective.
   */
  @Test
  public void setSource2() throws EditStateException, IllegalEditException {
    // register listeners to the DoublePopulationStandardDeviationBeed
    $doublePopulationStandardDeviationBeed.addListener($listener3);
    assertNull($listener3.$event);
    // check setSource
    EditableSetBeed<RealBeed<?>> source = createSource();
    $doublePopulationStandardDeviationBeed.setSource(source);
    assertEquals($doublePopulationStandardDeviationBeed.getSource(), source);
    Double populationStandardDeviation1 = MathUtil.populationStandardDeviation(1.0, 2.0, 3.0, 4.0);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation1, Math.ulp($doublePopulationStandardDeviationBeed.getDouble()) * 4));
    // value has changed, so the listeners of the population standard deviation beed are notified
    assertNotNull($listener3.$event);
    assertEquals($listener3.$event.getSource(), $doublePopulationStandardDeviationBeed);
    assertEquals($listener3.$event.getOldDouble(), null);
    assertTrue(MathUtil.equalValue($listener3.$event.getNewDouble(), populationStandardDeviation1));
    assertEquals($listener3.$event.getEdit(), null);
    // The DoublePopulationStandardDeviationBeed is registered as listener of the source, so when
    // the source changes, the beed should be notified
    $listener3.reset();
    assertNull($listener3.$event);
    SetEdit<RealBeed<?>> setEdit = new SetEdit<RealBeed<?>>(source);
    EditableDoubleBeed goal = createEditableDoubleBeed(5.0);
    setEdit.addElementToAdd(goal);
    setEdit.perform();
    Double populationStandardDeviation2 = MathUtil.populationStandardDeviation(1.0, 2.0, 3.0, 4.0, 5.0);
    assertNotNull($listener3.$event);
    assertEquals($listener3.$event.getSource(), $doublePopulationStandardDeviationBeed);
    assertTrue(MathUtil.equalValue($listener3.$event.getOldDouble(), populationStandardDeviation1));
    assertTrue(MathUtil.equalValue($listener3.$event.getNewDouble(), populationStandardDeviation2));
    assertEquals($listener3.$event.getEdit(), setEdit);
    // The DoublePopulationStandardDeviationBeed is registered as listener of all double beeds in the source,
    // so when one of them changes, the beed should be notified
    $listener3.reset();
    assertNull($listener3.$event);
    DoubleEdit doubleEdit = new DoubleEdit(goal);
    doubleEdit.setGoal(6.0);
    doubleEdit.perform();
    Double populationStandardDeviation3 = MathUtil.populationStandardDeviation(1.0, 2.0, 3.0, 4.0, 6.0);
    assertNotNull($listener3.$event);
    assertEquals($listener3.$event.getSource(), $doublePopulationStandardDeviationBeed);
    assertTrue(MathUtil.equalValue($listener3.$event.getOldDouble(), populationStandardDeviation2));
    assertTrue(MathUtil.equalValue($listener3.$event.getNewDouble(), populationStandardDeviation3));
    assertEquals($listener3.$event.getEdit(), doubleEdit);
    // When a new beed is added to the source, the DoublePopulationStandardDeviationBeed is added as a listener
    // of that beed. See above.

    // When a beed is removed from the source, the DoublePopulationStandardDeviationBeed is removed as listener
    // of that beed.
    $listener3.reset();
    assertNull($listener3.$event);
    setEdit = new SetEdit<RealBeed<?>>(source);
    setEdit.addElementToRemove(goal);
    setEdit.perform();
    Double populationStandardDeviation4 = MathUtil.populationStandardDeviation(1.0, 2.0, 3.0, 4.0);
    assertNotNull($listener3.$event);
    assertEquals($listener3.$event.getSource(), $doublePopulationStandardDeviationBeed);
    assertTrue(MathUtil.equalValue($listener3.$event.getOldDouble(), populationStandardDeviation3));
    assertTrue(MathUtil.equalValue($listener3.$event.getNewDouble(), populationStandardDeviation4));
    assertEquals($listener3.$event.getEdit(), setEdit);
    $listener3.reset();
    assertNull($listener3.$event);
    doubleEdit = new DoubleEdit(goal);
    doubleEdit.setGoal(7.0);
    doubleEdit.perform();
    assertNull($listener3.$event); // the DoublePopulationStandardDeviationBeed is NOT notified
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation4));

  }


  private EditableDoubleBeed createEditableDoubleBeed(Double value) {
    try {
      EditableDoubleBeed editableDoubleBeed = new EditableDoubleBeed($owner);
      DoubleEdit edit = new DoubleEdit(editableDoubleBeed);
      edit.setGoal(value);
      edit.perform();
      assertEquals(editableDoubleBeed.get(), value);
      return editableDoubleBeed;
    }
    catch (EditStateException e) {
      assertTrue(false);
      return null;
    }
    catch (IllegalEditException e) {
      assertTrue(false);
      return null;
    }
  }


  @Test
  public void recalculate() throws EditStateException, IllegalEditException {
    // create beeds
    EditableDoubleBeed beed1 = createEditableDoubleBeed(1.0);
    EditableDoubleBeed beed2 = createEditableDoubleBeed(2.0);
    EditableDoubleBeed beed3 = createEditableDoubleBeed(3.0);
    EditableDoubleBeed beed4 = createEditableDoubleBeed(4.0);
    EditableDoubleBeed beedNull = createEditableDoubleBeed(null);
    // double population standard deviation beed has no source
    assertEquals($doublePopulationStandardDeviationBeed.getDouble(), null);
    // create source
    EditableSetBeed<RealBeed<?>> source =
      new EditableSetBeed<RealBeed<?>>($owner);
    // add source to population standard deviation beed
    $doublePopulationStandardDeviationBeed.setSource(source);
    // recalculate (setBeed contains no elements)
    $doublePopulationStandardDeviationBeed.publicRecalculate();
    double populationStandardDeviation = MathUtil.populationStandardDeviation();
    assertEquals(populationStandardDeviation, $doublePopulationStandardDeviationBeed.getDouble());
    // add beed
    SetEdit<RealBeed<?>> setEdit =
      new SetEdit<RealBeed<?>>(source);
    setEdit.addElementToAdd(beed1);
    setEdit.perform();
    // recalculate (setBeed contains beed 1)
    $doublePopulationStandardDeviationBeed.publicRecalculate();
    populationStandardDeviation = MathUtil.populationStandardDeviation(1.0);
    assertEquals(populationStandardDeviation, $doublePopulationStandardDeviationBeed.getDouble());
    // recalculate (setBeed contains beed 1)
    $doublePopulationStandardDeviationBeed.publicRecalculate();
    assertEquals(populationStandardDeviation, $doublePopulationStandardDeviationBeed.getDouble());
    // add beed
    setEdit = new SetEdit<RealBeed<?>>(source);
    setEdit.addElementToAdd(beed2);
    setEdit.perform();
    // recalculate (setBeed contains beed 1 and 2)
    $doublePopulationStandardDeviationBeed.publicRecalculate();
    populationStandardDeviation = MathUtil.populationStandardDeviation(1.0, 2.0);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation, Math.ulp($doublePopulationStandardDeviationBeed.getDouble()) * 4));
    // recalculate (setBeed contains beed 1 and 2)
    $doublePopulationStandardDeviationBeed.publicRecalculate();
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation));
    // add beed
    setEdit = new SetEdit<RealBeed<?>>(source);
    setEdit.addElementToAdd(beed3);
    setEdit.perform();
    // recalculate (setBeed contains beed 1, 2 and 3)
    $doublePopulationStandardDeviationBeed.publicRecalculate();
    populationStandardDeviation = MathUtil.populationStandardDeviation(1.0, 2.0, 3.0);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation));
    // add beed
    setEdit = new SetEdit<RealBeed<?>>(source);
    setEdit.addElementToAdd(beed4);
    setEdit.perform();
    // recalculate (setBeed contains beed 1, 2, 3 and 4)
    $doublePopulationStandardDeviationBeed.publicRecalculate();
    populationStandardDeviation = MathUtil.populationStandardDeviation(1.0, 2.0, 3.0, 4.0);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation));
    // add beed
    setEdit = new SetEdit<RealBeed<?>>(source);
    setEdit.addElementToAdd(beedNull);
    setEdit.perform();
    // recalculate (setBeed contains beed 1, 2, 3, 4 and null)
    $doublePopulationStandardDeviationBeed.publicRecalculate();
    assertEquals(null, $doublePopulationStandardDeviationBeed.getDouble());
  }

  @Test
  public void createInitialEvent() {
    RealEvent initialEvent = $doublePopulationStandardDeviationBeed.createInitialEvent();
    assertEquals(initialEvent.getSource(), $doublePopulationStandardDeviationBeed);
    assertEquals(initialEvent.getOldDouble(), null);
    assertEquals(initialEvent.getNewDouble(), $doublePopulationStandardDeviationBeed.getDouble());
    assertEquals(initialEvent.getEdit(), null);
  }

  /**
   * Source gooit events bij add en remove.
   * @throws EditStateException
   * @throws IllegalEditException
   */
  @Test
  public void test() throws EditStateException, IllegalEditException {
    // create beeds
    EditableDoubleBeed beed1 = createEditableDoubleBeed(1.0);
    EditableDoubleBeed beed2 = createEditableDoubleBeed(2.0);
    EditableDoubleBeed beed3 = createEditableDoubleBeed(3.0);
    EditableDoubleBeed beed4 = createEditableDoubleBeed(4.0);
    // create set beed
    EditableSetBeed<RealBeed<?>> setBeed =
      new EditableSetBeed<RealBeed<?>>($owner);
    // add set beed to population standard deviation beed
    $doublePopulationStandardDeviationBeed.setSource(setBeed);
    // add beed
    assertEquals($doublePopulationStandardDeviationBeed.getDouble(), Double.NaN);
    SetEdit<RealBeed<?>> setEdit =
      new SetEdit<RealBeed<?>>(setBeed);
    setEdit.addElementToAdd(beed1);
    setEdit.perform();
    assertEquals(0.0, $doublePopulationStandardDeviationBeed.getDouble());
    setEdit = new SetEdit<RealBeed<?>>(setBeed);
    setEdit.addElementToAdd(beed2);
    setEdit.perform();
    double populationStandardDeviation = MathUtil.populationStandardDeviation(1.0, 2.0);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation, Math.ulp($doublePopulationStandardDeviationBeed.getDouble()) * 4));
    setEdit = new SetEdit<RealBeed<?>>(setBeed);
    setEdit.addElementToAdd(beed3);
    setEdit.perform();
    populationStandardDeviation = MathUtil.populationStandardDeviation(1.0, 2.0, 3.0);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation));
    setEdit = new SetEdit<RealBeed<?>>(setBeed);
    setEdit.addElementToAdd(beed4);
    setEdit.perform();
    populationStandardDeviation = MathUtil.populationStandardDeviation(1.0, 2.0, 3.0, 4.0);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation));
    setEdit = new SetEdit<RealBeed<?>>(setBeed); // add an element that is already there
    setEdit.addElementToAdd(beed4);
    setEdit.perform();
    populationStandardDeviation = MathUtil.populationStandardDeviation(1.0, 2.0, 3.0, 4.0);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation));
    setEdit = new SetEdit<RealBeed<?>>(setBeed);
    setEdit.addElementToRemove(beed1);
    setEdit.perform();
    populationStandardDeviation = MathUtil.populationStandardDeviation(2.0, 3.0, 4.0);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation));
    setEdit = new SetEdit<RealBeed<?>>(setBeed);
    setEdit.addElementToRemove(beed2);
    setEdit.perform();
    populationStandardDeviation = MathUtil.populationStandardDeviation(3.0, 4.0);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation));
    setEdit = new SetEdit<RealBeed<?>>(setBeed);
    setEdit.addElementToRemove(beed3);
    setEdit.perform();
    assertEquals(0.0, $doublePopulationStandardDeviationBeed.getDouble());
    setEdit = new SetEdit<RealBeed<?>>(setBeed);
    setEdit.addElementToRemove(beed4);
    setEdit.perform();
    assertEquals($doublePopulationStandardDeviationBeed.getDouble(), Double.NaN);
    setEdit = new SetEdit<RealBeed<?>>(setBeed);
    setEdit.addElementToRemove(beed4); // remove an element that is not there
    setEdit.perform();
    assertEquals($doublePopulationStandardDeviationBeed.getDouble(), Double.NaN);
    // change beeds of the source
    setBeed = new EditableSetBeed<RealBeed<?>>($owner);
    setEdit = new SetEdit<RealBeed<?>>(setBeed);
    setEdit.addElementToAdd(beed1);
    setEdit.perform();
    setEdit = new SetEdit<RealBeed<?>>(setBeed);
    setEdit.addElementToAdd(beed2);
    setEdit.perform();
    $doublePopulationStandardDeviationBeed.setSource(setBeed);
    populationStandardDeviation = MathUtil.populationStandardDeviation(1.0, 2.0);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation));
    DoubleEdit doubleEdit = new DoubleEdit(beed1);
    doubleEdit.setGoal(1.5);
    doubleEdit.perform();
    populationStandardDeviation = MathUtil.populationStandardDeviation(1.5, 2.0);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation));
    doubleEdit = new DoubleEdit(beed2);
    doubleEdit.setGoal(2.5);
    doubleEdit.perform();
    populationStandardDeviation = MathUtil.populationStandardDeviation(1.5, 2.5);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation));
    doubleEdit = new DoubleEdit(beed1);
    doubleEdit.setGoal(1.0);
    doubleEdit.perform();
    populationStandardDeviation = MathUtil.populationStandardDeviation(1.0, 2.5);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation));
    doubleEdit = new DoubleEdit(beed2);
    doubleEdit.setGoal(2.0);
    doubleEdit.perform();
    populationStandardDeviation = MathUtil.populationStandardDeviation(1.0, 2.0);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation));
    // change beeds that are added later to the source
    setEdit = new SetEdit<RealBeed<?>>(setBeed);
    setEdit.addElementToAdd(beed3);
    setEdit.perform();
    populationStandardDeviation = MathUtil.populationStandardDeviation(1.0, 2.0, 3.0);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation));
    doubleEdit = new DoubleEdit(beed3);
    doubleEdit.setGoal(3.5);
    doubleEdit.perform();
    populationStandardDeviation = MathUtil.populationStandardDeviation(1.0, 2.0, 3.5);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation));
    doubleEdit = new DoubleEdit(beed3);
    doubleEdit.setGoal(3.7);
    doubleEdit.perform();
    populationStandardDeviation = MathUtil.populationStandardDeviation(1.0, 2.0, 3.7);
    assertTrue(MathUtil.equalValue($doublePopulationStandardDeviationBeed.getDouble(), populationStandardDeviation));
  }

  private EditableSetBeed<RealBeed<?>> createSource() throws EditStateException, IllegalEditException {
    // create set beed
    EditableSetBeed<RealBeed<?>> setBeed =
      new EditableSetBeed<RealBeed<?>>($owner);
    // create beeds
    EditableDoubleBeed beed1 = createEditableDoubleBeed(1.0);
    EditableDoubleBeed beed2 = createEditableDoubleBeed(2.0);
    EditableDoubleBeed beed3 = createEditableDoubleBeed(3.0);
    EditableDoubleBeed beed4 = createEditableDoubleBeed(4.0);
    // add beeds to set
    SetEdit<RealBeed<?>> setEdit = new SetEdit<RealBeed<?>>(setBeed);
    setEdit.addElementToAdd(beed1);
    setEdit.perform();
    setEdit = new SetEdit<RealBeed<?>>(setBeed);
    setEdit.addElementToAdd(beed2);
    setEdit.perform();
    setEdit = new SetEdit<RealBeed<?>>(setBeed);
    setEdit.addElementToAdd(beed3);
    setEdit.perform();
    setEdit = new SetEdit<RealBeed<?>>(setBeed);
    setEdit.addElementToAdd(beed4);
    setEdit.perform();
    return setBeed;
  }

}
