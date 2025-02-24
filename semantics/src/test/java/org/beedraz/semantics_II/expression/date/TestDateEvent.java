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

package org.beedraz.semantics_II.expression.date;


import static org.junit.Assert.assertEquals;
import static org.ppwcode.metainfo_I.License.Type.APACHE_V2;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.beedraz.semantics_II.EditStateException;
import org.beedraz.semantics_II.IllegalEditException;
import org.beedraz.semantics_II.TestActualOldNewEvent;
import org.beedraz.semantics_II.aggregate.AbstractAggregateBeed;
import org.beedraz.semantics_II.aggregate.AggregateBeed;
import org.beedraz.semantics_II.bean.StubBeanBeed;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ppwcode.metainfo_I.Copyright;
import org.ppwcode.metainfo_I.License;
import org.ppwcode.metainfo_I.vcs.SvnInfo;


@Copyright("2007 - $Date$, Beedraz authors")
@License(APACHE_V2)
@SvnInfo(revision = "$Revision$",
         date     = "$Date$")
public class TestDateEvent
    extends TestActualOldNewEvent<Date, EditableDateBeed, DateEvent> {

  @Before
  public void setUp() throws Exception {
    // NOP
  }

  @After
  public void tearDown() throws Exception {
    // NOP
  }

  @Test
  public void constructor() throws EditStateException, IllegalEditException {
    // source
    AggregateBeed owner = new StubBeanBeed();
    DateBeed source = new EditableDateBeed(owner);
    // old and new value
    Date oldValue = Util.createDate(12, 10, 1973);
    Date newValue = Util.createDate(1, 11, 1979);
    // edit
    EditableDateBeed target = new EditableDateBeed(owner);
    DateEdit edit = new DateEdit(target);
    edit.perform();
    // test constructor
    DateEvent dateEvent = new DateEvent(source, oldValue, newValue, edit);
    assertEquals(dateEvent.getSource(), source);
    assertEquals(dateEvent.getOldValue(), oldValue);
    assertEquals(dateEvent.getNewValue(), newValue);
    assertEquals(dateEvent.getEdit(), edit);
    assertEquals(dateEvent.getEditState(), edit.getState());
    // old and new value can be null
    oldValue = null;
    newValue = Util.createDate(5, 9, 1970);
    // test constructor
    dateEvent = new DateEvent(source, oldValue, newValue, edit);
    assertEquals(dateEvent.getSource(), source);
    assertEquals(dateEvent.getOldValue(), null);
    assertEquals(dateEvent.getNewValue(), newValue);
    assertEquals(dateEvent.getEdit(), edit);
    assertEquals(dateEvent.getEditState(), edit.getState());
    // edit is null
    edit = null;
    // test constructor
    dateEvent = new DateEvent(source, oldValue, newValue, edit);
    assertEquals(dateEvent.getSource(), source);
    assertEquals(dateEvent.getOldValue(), null);
    assertEquals(dateEvent.getNewValue(), newValue);
    assertEquals(dateEvent.getEdit(), null);
    assertEquals(dateEvent.getEditState(), null);
  }

  @Override
  protected DateEvent createActualOldNewEvent(EditableDateBeed source, Date old, Date newV) {
    return new DateEvent(source, old, newV, null);
  }

  @Override
  protected EditableDateBeed createSource() throws IllegalEditException {
    return DateBeeds.editableDateBeed(new Date(), new AbstractAggregateBeed() {/*NOP*/});
  }

  @Override
  protected Date oldValue() {
    GregorianCalendar gc = new GregorianCalendar(1997, Calendar.APRIL, 2);
    return gc.getTime();
  }

  @Override
  protected Date middleValue() {
    GregorianCalendar gc = new GregorianCalendar(1993, Calendar.JUNE, 21);
    return gc.getTime();
  }

  @Override
  protected Date newValue() {
    GregorianCalendar gc = new GregorianCalendar(1991, Calendar.OCTOBER, 26);
    return gc.getTime();
  }

  @Override
  protected Date otherValue(Date value) {
    if (value == null) {
      return new Date();
    }
    else {
      return new Date(value.getTime() + 1000);
    }
  }

}