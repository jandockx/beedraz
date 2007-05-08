/*<license>
  Copyright 2007, PeopleWare n.v.
  NO RIGHTS ARE GRANTED FOR THE USE OF THIS SOFTWARE, EXCEPT, IN WRITING,
  TO SELECTED PARTIES.
</license>*/

package org.beedra_II.property.integer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.beedra_II.aggregate.AggregateBeed;
import org.beedra_II.aggregate.PropagatedEvent;
import org.beedra_II.bean.AbstractBeanBeed;
import org.beedra_II.event.Listener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class TestEditableIntegerBeed {

  public class MyEditableIntegerBeed extends EditableIntegerBeed {
    public MyEditableIntegerBeed(AggregateBeed owner) {
      super(owner);
    }

    /**
     * fireChangeEvent is made public for testing reasons
     */
    public void fire(IntegerEvent event) {
      fireChangeEvent(event);
    }
  }

  public class PropagatedEventListener implements Listener<PropagatedEvent> {

    public void beedChanged(PropagatedEvent event) {
      $event = event;
    }

    public void reset() {
      $event = null;
    }

    public PropagatedEvent $event;

  }

  public class MyBeanBeed extends AbstractBeanBeed {
    // NOP
  }

  @Before
  public void setUp() throws Exception {
    $owner = new MyBeanBeed();
    $editableIntegerBeed = new MyEditableIntegerBeed($owner);
    $stringEdit = new IntegerEdit($editableIntegerBeed);
    $stringEdit.perform();
    $event1 = new IntegerEvent($editableIntegerBeed, new Integer(0), new Integer(1), $stringEdit);
    $listener1 = new PropagatedEventListener();
    $listener2 = new PropagatedEventListener();
  }

  @After
  public void tearDown() throws Exception {
    // NOP
  }

  private AggregateBeed $owner;
  private MyEditableIntegerBeed $editableIntegerBeed;
  private IntegerEdit $stringEdit;
  private IntegerEvent $event1;
  private PropagatedEventListener $listener1;
  private PropagatedEventListener $listener2;

  @Test
  public void constructor() {
    assertEquals($editableIntegerBeed.getOwner(), $owner);
    // the abstract property beed should be registered with the owner:
    // add listeners to the property beed
    $owner.addListener($listener1);
    $owner.addListener($listener2);
    assertNull($listener1.$event);
    assertNull($listener2.$event);
    // fire a change on the registered beed
    $editableIntegerBeed.fire($event1);
    // listeners of the aggregate beed should be notified
    assertNotNull($listener1.$event);
    assertNotNull($listener2.$event);
    assertEquals($event1, $listener1.$event.getCause());
    assertEquals($event1, $listener1.$event.getCause());
  }

  @Test
  public void createInitialEvent() {
    IntegerEvent initialEvent = $editableIntegerBeed.createInitialEvent();
    assertEquals(initialEvent.getSource(), $editableIntegerBeed);
    assertEquals(initialEvent.getOldValue(), null);
    assertEquals(initialEvent.getNewValue(), $editableIntegerBeed.get());
    assertEquals(initialEvent.getEdit(), null);
  }

}

