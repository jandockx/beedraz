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

package org.beedra_II.property.number;


import static org.ppeew.smallfries_I.MathUtil.equalValue;

import java.util.Map;

import org.beedra_II.aggregate.AggregateBeed;
import org.beedra_II.edit.Edit;
import org.beedra_II.event.Event;
import org.beedra_II.property.number.real.RealBeed;
import org.beedra_II.property.number.real.RealEvent;
import org.beedra_II.topologicalupdate.AbstractUpdateSourceDependentDelegate;
import org.beedra_II.topologicalupdate.Dependent;
import org.ppeew.annotations_I.vcs.CvsInfo;


/**
 * Abstract implementation of binary expression number beeds, that represent a value derived
 * from 2 arguments.
 *
 * @invar getLeftArgument() == null || getRightArgument() == null ? get() == null;
 *
 * @protected
 * Accessor methods for the {@link #getLeftArgument() left argument} and the {@link #getRightArgument()
 * right argument} are kept protected, to force subclasses to provide meaningful public names for the
 * arguments.
 *
 * @invar getArgument() != null && valueFrom(getArgument()) == null ? get() == null;
 * @invar getArgument() != null && ! valueFrom(getArgument()) != null ?
 *              get().equals(calculateValue(valueFrom(getArgument())));
 */
@CvsInfo(revision = "$Revision$",
         date     = "$Date$",
         state    = "$State$",
         tag      = "$Name$")
public abstract class AbstractBinaryExpressionBeed<_Number_ extends Number,
                                                   _LeftArgumentBeed_ extends RealBeed<? extends _LeftArgumentEvent_>,
                                                   _LeftArgumentEvent_ extends RealEvent,
                                                   _RightArgumentBeed_ extends RealBeed<? extends _RightArgumentEvent_>,
                                                   _RightArgumentEvent_ extends RealEvent,
                                                   _NumberEvent_ extends RealEvent>
    extends AbstractExpressionBeed<_Number_, _NumberEvent_>  {

  /**
   * @pre   owner != null;
   * @post  getArgument() == null;
   * @post  get() == null;
   */
  public AbstractBinaryExpressionBeed(AggregateBeed owner) {
    super(owner);
  }

  private final Dependent<RealBeed<?>> $dependent =
    new AbstractUpdateSourceDependentDelegate<RealBeed<?>, _NumberEvent_>(this) {

      @Override
      protected _NumberEvent_ filteredUpdate(Map<RealBeed<?>, Event> events) {
        assert $leftArgument != null || $rightArgument != null;
        _Number_ oldValue = get();
        assignValue(calculateValueInternal($leftArgument == null ? null : valueFromLeft($leftArgument),
                                           $rightArgument == null ? null : valueFromRight($rightArgument)));
        Event leftEvent = events.get($leftArgument);
        Event rightEvent = events.get($rightArgument);
        assert leftEvent != null || rightEvent != null;
        assert leftEvent != null && rightEvent != null ? leftEvent.getEdit() == rightEvent.getEdit() : true;
        // MUDO with compound events, not equal, but we should create a compound ourselfs
        Edit<?> leftEdit = leftEvent != null ? leftEvent.getEdit() : null;
        Edit<?> rightEdit = rightEvent != null ? rightEvent.getEdit() : null;
        Edit<?> edit = leftEdit != null ? leftEdit : rightEdit;
        if (! equalValue(oldValue, get())) {
          return createNewEvent(oldValue, get(), edit);
        }
        else {
          return null;
        }
      }

    };

  public final int getMaximumRootUpdateSourceDistance() {
    /* FIX FOR CONSTRUCTION PROBLEM
     * At construction, the super constructor is called with the future owner
     * of this property beed. Eventually, in the constructor code of AbstractPropertyBeed,
     * this object is registered as update source with the dependent of the
     * aggregate beed. During that registration process, the dependent
     * checks to see if we need to ++ our maximum root update source distance.
     * This involves a call to this method getMaximumRootUpdateSourceDistance.
     * Since however, we are still doing initialization in AbstractPropertyBeed,
     * initialization code (and construction code) further down is not yet executed.
     * This means that our $dependent is still null, and this results in a NullPointerException.
     * On the other hand, we cannot move the concept of $dependent up, since not all
     * property beeds have a dependent.
     * The fix implemented here is the following:
     * This problem only occurs during construction. During construction, we will
     * not have any update sources, so our maximum root update source distance is
     * effectively 0.
     */
    /*
     * TODO This only works if we only add 1 update source during construction,
     *      so a better solution should be sought.
     */
    return $dependent == null ? 0 : $dependent.getMaximumRootUpdateSourceDistance();
  }


  /*<property name="left argument">*/
  //-----------------------------------------------------------------

  /**
   * @basic
   */
  protected final _LeftArgumentBeed_ getLeftArgument() {
    return $leftArgument;
  }

  /**
   * @post getLeftArgument() == leftArgument;
   */
  protected final void setLeftArgument(_LeftArgumentBeed_ leftArgument) {
    _Number_ oldValue = get();
    if ($leftArgument != null) {
      $dependent.removeUpdateSource($leftArgument);
    }
    $leftArgument = leftArgument;
    if (($leftArgument != null) && ($rightArgument != null)) {
      assignValue(calculateValueInternal(valueFromLeft($leftArgument), valueFromRight($rightArgument)));
    }
    else {
      assignValue(null);
    }
    if ($leftArgument != null) {
      $dependent.addUpdateSource($leftArgument);
    }
    if (! equalValue(oldValue, get())) {
      updateDependents(createNewEvent(oldValue, get(), null));
    }
  }

  /**
   * @pre beed != null;
   */
  protected abstract _Number_ valueFromLeft(_LeftArgumentBeed_ beed);

  private _LeftArgumentBeed_ $leftArgument;

  /*</property>*/


  /*<property name="right argument">*/
  //-----------------------------------------------------------------

  /**
   * @basic
   */
  protected final _RightArgumentBeed_ getRightArgument() {
    return $rightArgument;
  }

  /**
   * @post getRightArgument() == rightArgument;
   */
  protected final void setRightArgument(_RightArgumentBeed_ rightArgument) {
    _Number_ oldValue = get();
    if ($rightArgument != null) {
      $dependent.removeUpdateSource($rightArgument);
    }
    $rightArgument = rightArgument;
    if (($leftArgument != null) && ($rightArgument != null)) {
      assignValue(calculateValueInternal(valueFromLeft($leftArgument), valueFromRight($rightArgument)));
    }
    else {
      assignValue(null);
    }
    if ($rightArgument != null) {
      $dependent.addUpdateSource($rightArgument);
    }
    if (! equalValue(oldValue, get())) {
      updateDependents(createNewEvent(oldValue, get(), null));
    }
  }

  /**
   * @pre beed != null;
   */
  protected abstract _Number_ valueFromRight(_RightArgumentBeed_ beed);

  private _RightArgumentBeed_ $rightArgument;

//  private Listener<_RightArgumentEvent_> $rightArgumentListener = new Listener<_RightArgumentEvent_>() {
//
//    public void beedChanged(_RightArgumentEvent_ event) {
//      if ($leftArgument != null) {
//        _Number_ oldValue = get();
//        assignValue(calculateValueInternal(valueFromLeft($leftArgument), newValueFromRight(event)));
//        fireEvent(oldValue, event.getEdit());
//      }
//    }
//
//  };
//
//  protected abstract _Number_ newValueFromRight(_RightArgumentEvent_ event);

  /*</property>*/


  private _Number_ calculateValueInternal(_Number_ leftArgumentValue, _Number_ rightArgumentValue) {
    return ((leftArgumentValue == null) || (rightArgumentValue == null)) ?
             null :
             calculateValue(leftArgumentValue, rightArgumentValue);
  }

  /**
   * @pre leftArgumentValue != null;
   * @pre rightArgumentValue != null;
   */
  protected abstract _Number_ calculateValue(_Number_ leftArgumentValue, _Number_ rightArgumentValue);

}

