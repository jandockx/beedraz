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

package org.beedra_II.property.integer;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.beedra_II.aggregate.AggregateBeed;
import org.beedra_II.bean.AbstractBeanBeed;
import org.beedra_II.bean.BeanBeed;
import org.beedra_II.edit.Edit;
import org.beedra_II.edit.EditStateException;
import org.beedra_II.edit.IllegalEditException;
import org.beedra_II.edit.ValidityListener;
import org.beedra_II.edit.Edit.State;
import org.beedra_II.event.Listener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestIntegerEdit {

  public class MyEditableIntegerBeed extends EditableIntegerBeed {

    public MyEditableIntegerBeed(AggregateBeed owner) {
      super(owner);
    }

    @Override
    public boolean isAcceptable(Integer goal) {
      return goal != null && goal > 0;
    }

  }

  public class MyBeanBeed extends AbstractBeanBeed {
    // NOP
  }

  public class StubValidityListener implements ValidityListener {

    public void listenerRemoved(Edit<?> target) {
      $target = target;
    }

    public void validityChanged(Edit<?> target, boolean newValidity) {
      $target = target;
      $validity = newValidity;
    }

    public void reset() {
      $target = null;
      $validity = null;
    }

    public boolean isEmpty() {
      return $target == null && $validity == null;
    }

    public Edit<?> $target;

    public Boolean $validity;

  }

  public class StubIntegerListener implements Listener<IntegerEvent> {

    public void beedChanged(IntegerEvent event) {
      $event = event;
    }

    public void reset() {
      $event = null;
    }

    public IntegerEvent $event;

  }

  @Before
  public void setUp() throws Exception {
    // NOP
  }

  @After
  public void tearDown() throws Exception {
    // NOP
  }

  BeanBeed $beanBeed = new MyBeanBeed();
  MyEditableIntegerBeed $target = new MyEditableIntegerBeed($beanBeed);
  private IntegerEdit $booleanEdit = new IntegerEdit($target);
  StubValidityListener $listener1 = new StubValidityListener();
  StubValidityListener $listener2 = new StubValidityListener();
  StubIntegerListener $listener3 = new StubIntegerListener();

  @Test
  public void constructor() {
    assertEquals($booleanEdit.getTarget(), $target);
  }

  @Test
  // incorrect begin-state
  public void perform1() {
    try {
      $booleanEdit.perform();
      $booleanEdit.perform();
      // should not be reached
      assertTrue(false);
    }
    catch (EditStateException e) {
      assertEquals(e.getEdit(), $booleanEdit);
      assertEquals(e.getCurrentState(), $booleanEdit.getState());
      assertEquals(e.getExpectedState(), State.NOT_YET_PERFORMED);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // incorrect begin-state
  public void perform2() {
    try {
      $booleanEdit.perform();
      $booleanEdit.undo();
      $booleanEdit.perform();
      // should not be reached
      assertTrue(false);
    }
    catch (EditStateException e) {
      assertEquals(e.getEdit(), $booleanEdit);
      assertEquals(e.getCurrentState(), $booleanEdit.getState());
      assertEquals(e.getExpectedState(), State.NOT_YET_PERFORMED);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // incorrect begin-state
  public void perform3() {
    try {
      $booleanEdit.kill();
      $booleanEdit.perform();
      // should not be reached
      assertTrue(false);
    }
    catch (EditStateException e) {
      assertEquals(e.getEdit(), $booleanEdit);
      assertEquals(e.getCurrentState(), $booleanEdit.getState());
      assertEquals(e.getExpectedState(), State.NOT_YET_PERFORMED);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // correct begin-state, check end-state
  public void perform4() {
    try {
      $booleanEdit.perform();
      assertEquals($booleanEdit.getState(), State.DONE);
    }
    catch (EditStateException e) {
      // should not be reached
      assertTrue(false);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // correct begin-state, validity listeners should be removed, listeners of the beed are notified
  public void perform5() {
    try {
      // add listener to beed
      $target.addListener($listener3);
      assertTrue($target.isListener($listener3));
      assertNull($listener3.$event);
      // add validity listeners to edit
      $booleanEdit.addValidityListener($listener1);
      $booleanEdit.addValidityListener($listener2);
      assertTrue($booleanEdit.isValidityListener($listener1));
      assertTrue($booleanEdit.isValidityListener($listener2));
      // perform
      Integer goal = 1;
      $booleanEdit.setGoal(goal);
      $booleanEdit.perform();
      // listeners should be removed
      assertFalse($booleanEdit.isValidityListener($listener1));
      assertFalse($booleanEdit.isValidityListener($listener2));
      // listeners of the beed are notified
      assertNotNull($listener3.$event);
      assertEquals($listener3.$event.getEdit(), $booleanEdit);
      assertEquals($listener3.$event.getOldValue(), null);
      assertEquals($listener3.$event.getNewValue(), goal);
      assertEquals($listener3.$event.getSource(), $target);
    }
    catch (EditStateException e) {
      // should not be reached
      assertTrue(false);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // correct begin-state, edit is no change, so validity listeners are not removed, listeners of the beed are not notified
  public void perform6() {
    try {
      // add listener to beed
      $target.addListener($listener3);
      assertTrue($target.isListener($listener3));
      assertNull($listener3.$event);
      // add validity listeners to edit
      $booleanEdit.addValidityListener($listener1);
      $booleanEdit.addValidityListener($listener2);
      assertTrue($booleanEdit.isValidityListener($listener1));
      assertTrue($booleanEdit.isValidityListener($listener2));
      // perform
      $booleanEdit.setGoal(null);
      $booleanEdit.perform();
      // listeners are not removed
      assertTrue($booleanEdit.isValidityListener($listener1));
      assertTrue($booleanEdit.isValidityListener($listener2));
      // listeners of the beed are not notified
      assertNull($listener3.$event);
    }
    catch (EditStateException e) {
      // should not be reached
      assertTrue(false);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // when the edit is not valid, an exception should be thrown
  public void perform7() {
    try {
      IntegerEdit edit = new IntegerEdit($target);
      Integer goal = 1;
      edit.setGoal(goal);
      edit.perform();
      // perform
      $booleanEdit.setGoal(null);
      $booleanEdit.perform();
      // should not be reached
      assertTrue(false);
    }
    catch (EditStateException e) {
      // should not be reached
      assertTrue(false);
    }
    catch (IllegalEditException e) {
      assertEquals(e.getEdit(), $booleanEdit);
      assertEquals(e.getMessage(), null);
    }
  }

  @Test
  // check whether the initial state is stored
  public void perform8() {
    try {
      // perform
      IntegerEdit edit1 = new IntegerEdit($target);
      assertNull(edit1.getInitial());
      Integer goal1 = 1;
      edit1.setGoal(goal1);
      edit1.perform();
      assertNull(edit1.getInitial());
      // perform
      IntegerEdit edit2 = new IntegerEdit($target);
      assertNull(edit2.getInitial());
      Integer goal2 = 2;
      edit2.setGoal(goal2);
      edit2.perform();
      assertEquals(edit2.getInitial(), goal1);
      // perform - no change
      IntegerEdit edit3 = new IntegerEdit($target);
      assertNull(edit3.getInitial());
      edit3.setGoal(goal2);
      edit3.perform();
      assertEquals(edit3.getInitial(), goal2);
    }
    catch (EditStateException e) {
      // should not be reached
      assertTrue(false);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // check whether the goal is stored in the beed
  public void perform9() {
    try {
      // perform
      Integer goal = 1;
      $booleanEdit.setGoal(goal);
      $booleanEdit.perform();
      assertEquals($target.get(), goal);
    }
    catch (EditStateException e) {
      // should not be reached
      assertTrue(false);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // incorrect begin-state
  public void undo1() {
    try {
      $booleanEdit.undo();
      // should not be reached
      assertTrue(false);
    }
    catch (EditStateException e) {
      assertEquals(e.getEdit(), $booleanEdit);
      assertEquals(e.getCurrentState(), $booleanEdit.getState());
      assertEquals(e.getExpectedState(), State.DONE);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // incorrect begin-state
  public void undo2() {
    try {
      $booleanEdit.perform();
      $booleanEdit.undo();
      $booleanEdit.undo();
      // should not be reached
      assertTrue(false);
    }
    catch (EditStateException e) {
      assertEquals(e.getEdit(), $booleanEdit);
      assertEquals(e.getCurrentState(), $booleanEdit.getState());
      assertEquals(e.getExpectedState(), State.DONE);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // incorrect begin-state
  public void undo3() {
    try {
      $booleanEdit.kill();
      $booleanEdit.undo();
      // should not be reached
      assertTrue(false);
    }
    catch (EditStateException e) {
      assertEquals(e.getEdit(), $booleanEdit);
      assertEquals(e.getCurrentState(), $booleanEdit.getState());
      assertEquals(e.getExpectedState(), State.DONE);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // correct begin-state, check end-state
  public void undo4() {
    try {
      Integer goal = 1;
      $booleanEdit.setGoal(goal);
      $booleanEdit.perform();
      $booleanEdit.undo();
      assertEquals($booleanEdit.getState(), State.UNDONE);
    }
    catch (EditStateException e) {
      // should not be reached
      assertTrue(false);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // correct begin-state, so there are no validity listeners, listeners of the beed are notified
  public void undo5() {
    try {
      // add listener to beed
      $target.addListener($listener3);
      assertTrue($target.isListener($listener3));
      assertNull($listener3.$event);
      // add validity listeners to edit
      $booleanEdit.addValidityListener($listener1);
      $booleanEdit.addValidityListener($listener2);
      assertTrue($booleanEdit.isValidityListener($listener1));
      assertTrue($booleanEdit.isValidityListener($listener2));
      // perform
      Integer goal = 1;
      $booleanEdit.setGoal(goal);
      $booleanEdit.perform();
      assertFalse($booleanEdit.isValidityListener($listener1));
      assertFalse($booleanEdit.isValidityListener($listener2));
      $booleanEdit.undo();
      // there are no listeners
      assertFalse($booleanEdit.isValidityListener($listener1));
      assertFalse($booleanEdit.isValidityListener($listener2));
      // listeners of the beed are notified
      assertNotNull($listener3.$event);
      assertEquals($listener3.$event.getSource(), $target);
      assertEquals($listener3.$event.getOldValue(), goal);
      assertEquals($listener3.$event.getNewValue(), null);
      assertEquals($listener3.$event.getEdit(), $booleanEdit);
    }
    catch (EditStateException e) {
      // should not be reached
      assertTrue(false);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // when the goal state does not match the current state, an exception should be thrown
  public void undo7() {
    IntegerEdit edit1 = null;
    try {
      // edit1
      edit1 = new IntegerEdit($target);
      Integer goal1 = 1;
      edit1.setGoal(goal1);
      edit1.perform();
      // edit2
      IntegerEdit edit2 = new IntegerEdit($target);
      Integer goal2 = 2;
      edit2.setGoal(goal2);
      edit2.perform();
      // undo edit1
      edit1.undo();
      // should not be reached
      assertTrue(false);
    }
    catch (EditStateException e) {
      // should not be reached
      assertTrue(false);
    }
    catch (IllegalEditException e) {
      assertEquals(e.getEdit(), edit1);
      assertEquals(e.getMessage(), null);
    }
  }

  @Test
  // is the value of the beed set to the original value?
  public void undo8() {
    try {
      // edit1
      IntegerEdit edit1 = new IntegerEdit($target);
      Integer goal1 = 1;
      edit1.setGoal(goal1);
      edit1.perform();
      // $simpleEdit
      Integer goal2 = 2;
      $booleanEdit.setGoal(goal2);
      $booleanEdit.perform();
      $booleanEdit.undo();
      assertEquals($target.get(), goal1);
    }
    catch (EditStateException e) {
      // should not be reached
      assertTrue(false);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // incorrect begin-state
  public void redo1() {
    try {
      $booleanEdit.redo();
      // should not be reached
      assertTrue(false);
    }
    catch (EditStateException e) {
      assertEquals(e.getEdit(), $booleanEdit);
      assertEquals(e.getCurrentState(), $booleanEdit.getState());
      assertEquals(e.getExpectedState(), State.UNDONE);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // incorrect begin-state
  public void redo2() {
    try {
      $booleanEdit.perform();
      $booleanEdit.redo();
      // should not be reached
      assertTrue(false);
    }
    catch (EditStateException e) {
      assertEquals(e.getEdit(), $booleanEdit);
      assertEquals(e.getCurrentState(), $booleanEdit.getState());
      assertEquals(e.getExpectedState(), State.UNDONE);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // incorrect begin-state
  public void redo3() {
    try {
      $booleanEdit.kill();
      $booleanEdit.redo();
      // should not be reached
      assertTrue(false);
    }
    catch (EditStateException e) {
      assertEquals(e.getEdit(), $booleanEdit);
      assertEquals(e.getCurrentState(), $booleanEdit.getState());
      assertEquals(e.getExpectedState(), State.UNDONE);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // correct begin-state, check end-state
  public void redo4() {
    try {
      $booleanEdit.perform();
      $booleanEdit.undo();
      $booleanEdit.redo();
      assertEquals($booleanEdit.getState(), State.DONE);
    }
    catch (EditStateException e) {
      // should not be reached
      assertTrue(false);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // correct begin-state, so there are no validity listeners, listeners of the beed are notified
  public void redo5() {
    try {
      // add listener to beed
      $target.addListener($listener3);
      assertTrue($target.isListener($listener3));
      assertNull($listener3.$event);
      // add validity listeners to edit
      $booleanEdit.addValidityListener($listener1);
      $booleanEdit.addValidityListener($listener2);
      assertTrue($booleanEdit.isValidityListener($listener1));
      assertTrue($booleanEdit.isValidityListener($listener2));
      // perform
      Integer goal = 1;
      $booleanEdit.setGoal(goal);
      $booleanEdit.perform();
      assertFalse($booleanEdit.isValidityListener($listener1));
      assertFalse($booleanEdit.isValidityListener($listener2));
      $booleanEdit.undo();
      $booleanEdit.redo();
      // there are no listeners
      assertFalse($booleanEdit.isValidityListener($listener1));
      assertFalse($booleanEdit.isValidityListener($listener2));
      // listeners of the beed are notified
      assertNotNull($listener3.$event);
      assertEquals($listener3.$event.getEdit(), $booleanEdit);
      assertEquals($listener3.$event.getOldValue(), null);
      assertEquals($listener3.$event.getNewValue(), goal);
      assertEquals($listener3.$event.getSource(), $target);
    }
    catch (EditStateException e) {
      // should not be reached
      assertTrue(false);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  // when the goal state does not match the current state, an exception should be thrown
  public void redo7() {
    IntegerEdit edit1 = null;
    try {
      // edit1
      edit1 = new IntegerEdit($target);
      Integer goal1 = 1;
      edit1.setGoal(goal1);
      edit1.perform();
      edit1.undo();
      // edit2
      IntegerEdit edit2 = new IntegerEdit($target);
      Integer goal2 = 2;
      edit2.setGoal(goal2);
      edit2.perform();
      // redo edit1
      edit1.redo();
      // should not be reached
      assertTrue(false);
    }
    catch (EditStateException e) {
      // should not be reached
      assertTrue(false);
    }
    catch (IllegalEditException e) {
      assertEquals(e.getEdit(), edit1);
      assertEquals(e.getMessage(), null);
    }
  }

  @Test
  // is the value of the beed set to the goal value?
  public void redo8() {
    try {
      // edit1
      IntegerEdit edit1 = new IntegerEdit($target);
      Integer goal1 = 1;
      edit1.setGoal(goal1);
      edit1.perform();
      // $simpleEdit
      Integer goal2 = 2;
      $booleanEdit.setGoal(goal2);
      $booleanEdit.perform();
      $booleanEdit.undo();
      $booleanEdit.redo();
      assertEquals($target.get(), goal2);
    }
    catch (EditStateException e) {
      // should not be reached
      assertTrue(false);
    }
    catch (IllegalEditException e) {
      // should not be reached
      assertTrue(false);
    }
  }

  @Test
  public void createEvent() throws EditStateException, IllegalEditException {
    IntegerEvent createdEvent = $booleanEdit.createEvent();
    assertEquals(createdEvent.getEdit(), $booleanEdit);
    assertEquals(createdEvent.getOldValue(), null);
    assertEquals(createdEvent.getNewValue(), null);
    assertEquals(createdEvent.getSource(), $target);
    // perform
    Integer goal = 1;
    $booleanEdit.setGoal(goal);
    $booleanEdit.perform();
    // create event
    createdEvent = $booleanEdit.createEvent();
    assertEquals(createdEvent.getEdit(), $booleanEdit);
    assertEquals(createdEvent.getOldValue(), null);
    assertEquals(createdEvent.getNewValue(), goal);
    assertEquals(createdEvent.getSource(), $target);
    // undo
    $booleanEdit.undo();
    // create event
    createdEvent = $booleanEdit.createEvent();
    assertEquals(createdEvent.getEdit(), $booleanEdit);
    assertEquals(createdEvent.getOldValue(), goal);
    assertEquals(createdEvent.getNewValue(), null);
    assertEquals(createdEvent.getSource(), $target);
  }

}